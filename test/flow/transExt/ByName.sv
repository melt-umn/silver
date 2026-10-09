grammar flow:transExt;

-- What a host production supplies to a tree it shares with an implementation applied by name reaches the other
-- implementations, as the implementation may pass the tree on to them: sbSite's x.sbA is top.sbB.  The host's
-- forwarding productions do not know this attribute, so their implicit copies do not show the dependency.
synthesized attribute sbExt::String occurs on SBExpr;
aspect default production
top::SBExpr ::=
{
  top.sbExt = "";
}
aspect production sbRead
top::SBExpr ::= @a::SBExpr
{
  top.sbExt = a.sbA;
}
synthesized attribute sbOut::String;
nonterminal SBRoot with sbOut;
warnCode "Access of synthesized attribute sbExt on y requires missing inherited attribute(s) flow:sbB" {
production sbRoot
top::SBRoot ::= y::SBExpr
{
  top.sbOut = y.sbExt;
}
}
