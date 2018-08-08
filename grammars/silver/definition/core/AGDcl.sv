grammar silver:definition:core;

{--
 - Top-level declarations of a Silver grammar. The "meat" of a file.
 -}
nonterminal AGDcls with config, grammarName, env, location, unparse, errors, defs, moduleNames, compiledGrammars, grammarDependencies;
nonterminal AGDcl  with config, grammarName, env, location, unparse, errors, defs, moduleNames, compiledGrammars, grammarDependencies;

flowtype forward {grammarName, env} on AGDcl;

concrete production nilAGDcls
top::AGDcls ::=
{
  top.unparse = "";

  top.defs = [];
  top.errors := [];
  top.moduleNames = [];
}

concrete production consAGDcls
top::AGDcls ::= h::AGDcl t::AGDcls
{
  top.unparse = h.unparse ++ "\n" ++ t.unparse;

  top.defs = h.defs ++ t.defs;
  top.errors := h.errors ++ t.errors;
  top.moduleNames = h.moduleNames ++ t.moduleNames;
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
}

abstract production errorAGDcl
top::AGDcl ::= e::[Message]
{
  top.unparse = s"{- Errors:\n${foldMessages(e)} -}";
  top.errors := e;
}

{--
 - Permits extensions to expand an AGDcl into a series of AGDcl's.
 -}
abstract production appendAGDcl
top::AGDcl ::= h::AGDcl t::AGDcl
{
  top.unparse = h.unparse ++ "\n" ++ t.unparse;

  top.defs = h.defs ++ t.defs;
  top.errors := h.errors ++ t.errors;
  top.moduleNames = h.moduleNames ++ t.moduleNames;
}

aspect default production
top::AGDcl ::=
{
  -- can't provide unparse or location!
  top.moduleNames = [];
  top.defs = [];
  --top.errors := []; -- should never be omitted, really.
}

-- All AGDcls have their own file, or modification. None here.

