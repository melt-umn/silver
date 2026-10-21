grammar flow:capHost:ovr;

imports flow:capHost;

-- capP shares x through capQ's signature, so capQ shows the override on x, and capJ's own equation
-- for its child is assumed as CapD allows (see enterLevel): capJ is not in scope here, but its
-- graph says what it implements.
noWarnCode "hidden transitive" {
production capP
top::CapE ::= x::CapE
{
  x.capK = top.capF;
  top.capS = "";
  forwards to capQ(x);
}
}
