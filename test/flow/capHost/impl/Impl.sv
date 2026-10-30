grammar flow:capHost:impl;

imports flow:capHost;

-- Not exported by flow:capHost, so capJ forwards to an application of CapD with its child (see
-- OrphanedProduction.sv).
production capJ implements CapD
top::CapE ::= a::CapE
{
  a.capK = top.capK;
  local p::CapD = capOwn;
  forwards to p(@a);
}
