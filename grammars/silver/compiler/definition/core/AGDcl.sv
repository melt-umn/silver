grammar silver:compiler:definition:core;

{--
 - Top-level declarations of a Silver grammar. The "meat" of a file.
 -}
nonterminal AGDcls with config, grammarName, env, location, unparse, errors, defs, occursDefs, moduleNames, compiledGrammars, grammarDependencies, jarName;
nonterminal AGDcl  with config, grammarName, env, location, unparse, errors, defs, occursDefs, moduleNames, compiledGrammars, grammarDependencies, jarName;

flowtype decorate {config, grammarName, env, flowEnv, compiledGrammars} on AGDcls, AGDcl;
flowtype forward {decorate} on AGDcls, AGDcl;
flowtype jarName {decorate} on AGDcls, AGDcl;

propagate errors, moduleNames, jarName on AGDcls, AGDcl;
propagate defs, occursDefs on AGDcls;

concrete production nilAGDcls
top::AGDcls ::=
{
  top.unparse = "";
}

concrete production consAGDcls
top::AGDcls ::= h::AGDcl t::AGDcls
{
  top.unparse = h.unparse ++ "\n" ++ t.unparse;

  top.errors <- warnIfMultJarName(h.jarName, t.jarName, top.location);
}

--------
-- AGDcl

{--
 - A semantically meaningless declaration. Does nothing.
 - Used for: (1) 'nil' counterpart to appendAgDcl
 -}
abstract production emptyAGDcl
top::AGDcl ::=
{
  top.unparse = "";
}

abstract production errorAGDcl
top::AGDcl ::= e::[Message]
{
  top.unparse = s"{- Errors:\n${messagesToString(e)} -}";
  top.errors <- e;
}

abstract production defsAGDcl
top::AGDcl ::= d::[Def]
{
  top.unparse = s"{- Defs:\n${hackUnparse(d)} -}";
  top.defs := d;
}

{--
 - Permits extensions to expand an AGDcl into a series of AGDcl's.
 -}
abstract production appendAGDcl
top::AGDcl ::= h::AGDcl t::AGDcl
{
  top.unparse = h.unparse ++ "\n" ++ t.unparse;
  propagate defs, occursDefs;

  top.errors <- warnIfMultJarName(h.jarName, t.jarName, top.location);
}

function makeAppendAGDclOfAGDcls
AGDcl ::= dcls::AGDcls
{
  return case dcls of
         | nilAGDcls(location=l) -> emptyAGDcl(location=l)
         | consAGDcls(dcl, rest, location=l) -> appendAGDcl(dcl, makeAppendAGDclOfAGDcls(rest), location=l)
         end;
}

abstract production jarNameDcl
top::AGDcl ::= n::Name
{
  top.unparse = "jarName " ++ n.unparse;
  top.jarName <- just(n.name);
}

aspect default production
top::AGDcl ::=
{
  propagate moduleNames, defs, occursDefs, jarName;
}

function warnIfMultJarName
[Message] ::= n1::Maybe<String>  n2::Maybe<String>  loc::Location
{
  return if n1.isJust && n2.isJust
         then [wrn(loc, "Duplicate specification of jar name: " ++
               n1.fromJust ++ " and " ++ n2.fromJust)]
         else [];
}

-- All AGDcls have their own file, or modification. None here.

