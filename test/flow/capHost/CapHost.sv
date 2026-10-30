grammar flow:capHost;

imports flow:capHost:impl;
exports flow:capHost:ovr;

-- An implementation applied by name where the override's grammar cannot see it: capQ applies capJ,
-- from flow:capHost:impl, which this grammar imports without exporting.  capP's override is in
-- flow:capHost:ovr, which this grammar exports, so the override is recorded where capP's x is
-- shared (see defLHSRecorded).
inherited attribute capK::String;
inherited attribute capF::String;
synthesized attribute capS::String;
nonterminal CapE with capK, capF, capS;
flowtype CapE = decorate {}, forward {capF}, capS {capK};

production capLeaf
top::CapE ::=
{
  top.capS = top.capK;
}
dispatch CapD = CapE ::= a::CapE;
production capOwn implements CapD
top::CapE ::= a::CapE
{
  a.capK = top.capK;
  top.capS = "";
}
production capQ
top::CapE ::= @q::CapE
{
  top.capS = "";
  forwards to capJ(@q);
}
