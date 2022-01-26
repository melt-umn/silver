grammar silver:compiler:modification:list:java:type;

import silver:compiler:modification:list;
import silver:compiler:definition:type;
import silver:compiler:translation:java:type;

aspect production listCtrType
top::Type ::=
{
  top.transType = "common.ConsCell";
  top.transCovariantType = "common.ConsCell";
  top.transClassType = "common.ConsCell";
  top.transTypeRep = "new common.BaseTypeRep(\"[]\")";
  top.transTypeName = "List";
}
