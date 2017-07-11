grammar core;

{--
 - hackUnparse takes any value, and produce a string that represents
 - that value.  It is mainly useful for debugging.
 -
 - Not considered part of the "standard" library of Silver.  It may change from
 - release to release. Don't rely on it.
 -
 - In the distant future, this may become obsolete when a 'Show' typeclass is available.
 -}
function hackUnparse
String ::= nt::a
{
  return error("Not impl");
} foreign {
  "java" : return "(common.Util.hackyhackyUnparse(%nt%))";
}


