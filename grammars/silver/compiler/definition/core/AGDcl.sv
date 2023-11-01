grammar silver:compiler:definition:core;

{--
 - Top-level declarations of a Silver grammar. The "meat" of a file.
 -}
tracked nonterminal AGDcls with config, grammarName, env, unparse, errors, defs, occursDefs, moduleNames, compiledGrammars, grammarDependencies, jarName;
tracked nonterminal AGDcl  with config, grammarName, env, unparse, errors, defs, occursDefs, moduleNames, compiledGrammars, grammarDependencies, jarName;

flowtype decorate {config, grammarName, env, flowEnv, compiledGrammars, grammarDependencies} on AGDcls, AGDcl;
flowtype forward {} on AGDcls;
flowtype forward {decorate} on AGDcl;
flowtype errors {decorate} on AGDcls, AGDcl;
flowtype defs {decorate} on AGDcls, AGDcl;
flowtype occursDefs {decorate} on AGDcls, AGDcl;
flowtype jarName {decorate} on AGDcls, AGDcl;

propagate config, grammarName, compiledGrammars, grammarDependencies, errors, moduleNames, jarName
  on AGDcls, AGDcl;
propagate env, defs, occursDefs on AGDcls;

concrete production nilAGDcls
top::AGDcls ::=
{
  top.unparse = "";
}

concrete production consAGDcls
top::AGDcls ::= h::AGDcl t::AGDcls
{
  top.unparse = h.unparse ++ "\n" ++ t.unparse;

  top.errors <- warnIfMultJarName(h.jarName, t.jarName);
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
  top.unparse = s"{- Defs:\n${genericShow(d)} -}";
  top.defs := d;
}

{--
 - Permits extensions to expand an AGDcl into a series of AGDcl's.
 -}
abstract production appendAGDcl
top::AGDcl ::= h::AGDcl t::AGDcl
{
  top.unparse = h.unparse ++ "\n" ++ t.unparse;
  propagate env, defs, occursDefs;

  top.errors <- warnIfMultJarName(h.jarName, t.jarName);
}

function makeAppendAGDclOfAGDcls
AGDcl ::= dcls::AGDcls
{
  return case dcls of
         | nilAGDcls() -> emptyAGDcl()
         | consAGDcls(dcl, rest) -> appendAGDcl(dcl, makeAppendAGDclOfAGDcls(rest))
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
[Message] ::= n1::Maybe<String>  n2::Maybe<String>
{
  return if n1.isJust && n2.isJust
         then [wrnFromOrigin(ambientOrigin(),
                 "Duplicate specification of jar name: " ++
                 n1.fromJust ++ " and " ++ n2.fromJust)]
         else [];
}

-- All AGDcls have their own file, or modification. None here.

