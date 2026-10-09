grammar flow;

-- Each anonymous decoration site ('decorate ... with') has its own vertices.
-- The decoration site's equations and the accesses on it must use the same vertices.

inherited attribute adInh::String;
synthesized attribute adSyn::String;

nonterminal AdNT with adInh, adSyn;
production adLeaf
top::AdNT ::=
{ top.adSyn = top.adInh; }

nonterminal AdUser with adInh, adSyn;
flowtype adSyn {} on AdUser;

-- The inherited equations of a 'decorate ... with' reach accesses on it, for a constructed tree...
warnCode "Synthesized equation adSyn exceeds flow type with dependencies on flow:adInh" {
production adDecorateConstructed
top::AdUser ::=
{
  top.adSyn = decorate adLeaf() with { adInh = top.adInh; }.adSyn;
}
}

-- ...and for a tree of unknown production.
warnCode "Synthesized equation adSyn exceeds flow type with dependencies on flow:adInh" {
production adDecorateHole
top::AdUser ::= c::AdNT
{
  top.adSyn = decorate ^c with { adInh = top.adInh; }.adSyn;
}
}
