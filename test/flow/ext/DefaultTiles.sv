grammar flow:ext;

-- Default contributions from this grammar to collections of flow, which does not export this
-- grammar, may not affect the flow types of those collections (see flow's DefaultTiles.sv).  The
-- inferred flow type of dftZ stays without dftZK, as dftZLeaf's graph has the contribution only as
-- a suspect edge.
warnCode "Synthesized equation dftZ exceeds flow type with dependencies on flow:dftZK" {
aspect default production
top::DftSusp ::=
{ top.dftZ <- [top.dftZK]; }
}
-- Within the declared flow type of dftY; dftYLeaf's tile flow graph has the contribution's
-- dependency on dftYK (see dftUseY).
aspect default production
top::DftSuspD ::=
{ top.dftY <- [top.dftYK]; }
