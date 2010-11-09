grammar core;

{--
 - hackUnparse takes a Decorated nonterminal, and produce a string that represents
 - the tree corresponding to that value.  It is mainly useful for writing tests.
 -
 - Not considered part of the "standard" library of Silver.  It may change from
 - release to release.
 -}
function hackUnparse
String ::= nt::a
{
  return error("Not impl");
} foreign {
  "java" : return "(common.Util.hackyhackyUnparse(%nt%))";
}


