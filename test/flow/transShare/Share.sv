grammar flow:transShare;

imports flow;

-- A grammar declaring the occurrence of a translation attribute on the chain of a translation attribute
-- of a translation attribute can share it in an aspect of a host production.
-- (flow:transShare:orphan does the same from an unrelated grammar, where it is orphaned.)

-- An extension translation attribute on a host nonterminal, with host translation attributes on its translation.
translation attribute ntsExtB::NtsY;
attribute ntsExtB occurs on NtsX;
aspect production ntsX
top::NtsX ::=
{
  top.ntsExtB = ntsY();
}

-- An extension translation attribute on the translation of a host translation attribute.
translation attribute ntsExtA::NtsZ;
attribute ntsExtA occurs on NtsY;
aspect production ntsY
top::NtsY ::=
{
  top.ntsExtA = ntsZ();
}

noWarnCode "Orphaned sharing" {
aspect production ntsExtHost
top::NtsP ::= x::NtsX
{
  local extSite::NtsW = ntsW(@x.ntsExtB.ntsA);
}
}

noWarnCode "Orphaned sharing" {
aspect production ntsExtHostInner
top::NtsP ::= x::NtsX
{
  local extSite::NtsW = ntsW(@x.ntsB.ntsExtA);
}
}

-- An extension translation attribute on a host tree that a host production signature-shares in its forward.
-- Sharing the translation attribute as well, in a forward production attribute of an aspect, conflicts.
translation attribute xsExtT::XSY;
attribute xsExtT occurs on XSS;
aspect production xsS
top::XSS ::=
{
  top.xsExtT = xsY();
}

wrongFlowCode "Cannot share x.flow:transShare:xsExtT in production flow:xsFwdSig, because child x is also shared" {
aspect production xsFwdSig
top::XSW ::= x::XSS
{
  forward production attribute fpa;
  fpa = xsWYShared(x.xsExtT);
}
}
