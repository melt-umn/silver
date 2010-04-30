grammar silver:definition:type:higherorder;
import silver:definition:core;
import silver:definition:env;

concrete production nttType
top::Type ::= q::QName
{
  top.pp = q.pp;
  top.location = q.location;

  top.typerep = q.lookupType.typerep;

  top.warnings := [];
  top.errors := q.lookupType.errors;
}
