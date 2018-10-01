grammar silver:definition:core;

{--
 - Top-level declarations of a Silver grammar. The "meat" of a file.
 -}
nonterminal AGDcls with config, grammarName, env, location, unparse, errors, defs, moduleNames, compiledGrammars, grammarDependencies, jarName;
nonterminal AGDcl  with config, grammarName, env, location, unparse, errors, defs, moduleNames, compiledGrammars, grammarDependencies, jarName;

flowtype forward {grammarName, env} on AGDcl;

concrete production nilAGDcls
top::AGDcls ::=
{
  top.unparse = "";

  top.defs = [];
  top.errors := [];
  top.moduleNames = [];
  top.jarName = nothing();
}

concrete production consAGDcls
top::AGDcls ::= h::AGDcl t::AGDcls
{
  top.unparse = h.unparse ++ "\n" ++ t.unparse;

  top.defs = h.defs ++ t.defs;
  top.errors := h.errors ++ t.errors ++ warnIfMultJarName(h.jarName, t.jarName, top.location);
  top.moduleNames = h.moduleNames ++ t.moduleNames;
  top.jarName = orElse(h.jarName, t.jarName);
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

  top.errors := [];
  top.jarName = nothing();
}

abstract production errorAGDcl
top::AGDcl ::= e::[Message]
{
  top.unparse = s"{- Errors:\n${messagesToString(e)} -}";
  top.errors := e;
  top.jarName = nothing();
}

{--
 - Permits extensions to expand an AGDcl into a series of AGDcl's.
 -}
abstract production appendAGDcl
top::AGDcl ::= h::AGDcl t::AGDcl
{
  top.unparse = h.unparse ++ "\n" ++ t.unparse;

  top.defs = h.defs ++ t.defs;
  top.errors := h.errors ++ t.errors ++ warnIfMultJarName(h.jarName, t.jarName, top.location);
  top.moduleNames = h.moduleNames ++ t.moduleNames;
  top.jarName = orElse(h.jarName, t.jarName);
}

abstract production jarNameDcl
top::AGDcl ::= n::Name
{
  top.unparse = "jarName " ++ n.unparse;
  top.errors := [];
  top.moduleNames = [];
  top.defs = [];
  top.jarName = just(n.name);
}

aspect default production
top::AGDcl ::=
{
  -- can't provide unparse or location!
  top.moduleNames = [];
  top.defs = [];
  top.jarName = nothing();
  --top.errors := []; -- should never be omitted, really.
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

