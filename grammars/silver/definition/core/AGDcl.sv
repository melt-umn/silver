grammar silver:definition:core;

{--
 - Top-level declarations of a Silver grammar. The "meat" of a file.
 -}
nonterminal AGDcls with config, grammarName, file, env, location, pp, errors, defs, moduleNames, compiledGrammars, grammarDependencies;
nonterminal AGDcl  with config, grammarName, file, env, location, pp, errors, defs, moduleNames, compiledGrammars, grammarDependencies;

concrete production nilAGDcls
top::AGDcls ::=
{
  top.pp = "";
  top.location = loc(top.file, -1, -1);

  top.defs = [];
  top.errors := [];
  top.moduleNames = [];
}

concrete production consAGDcls
top::AGDcls ::= h::AGDcl t::AGDcls
{
  top.pp = h.pp ++ "\n" ++ t.pp;
  top.location = h.location;

  top.defs = h.defs ++ t.defs;
  top.errors := h.errors ++ t.errors;
  top.moduleNames = h.moduleNames ++ t.moduleNames;
}

--------
-- AGDcl

{--
 - A semantically meaningless declaration. Does nothing.
 - Used for: (1) 'nil' counterpart to appendAgDcl
 - (2) annotations or other meaningless AGDcls.
 -}
abstract production emptyAGDcl
top::AGDcl ::=
{
  top.pp = "";
  top.location = loc(top.file, -1, -1);

  top.errors := [];
}

{--
 - Permits extensions to expand an AGDcl into a series of AGDcl's.
 -}
abstract production appendAGDcl
top::AGDcl ::= h::AGDcl t::AGDcl
{
  top.pp = h.pp ++ "\n" ++ t.pp;
  top.location = h.location;

  top.defs = h.defs ++ t.defs;
  top.errors := h.errors ++ t.errors;
  top.moduleNames = h.moduleNames ++ t.moduleNames;
}

aspect default production
top::AGDcl ::=
{
  -- can't provide pp or location!
  top.moduleNames = [];
  top.defs = [];
  --top.errors := []; -- should never be omitted, really.
}

-- All AGDcls have their own file, or modification. None here.

