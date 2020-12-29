grammar silver:extension:list:java;

import silver:extension:list;
import silver:definition:type;
import silver:definition:env;
import silver:definition:core;
import silver:translation:java:type;
import silver:translation:java:core; -- for the nil hack

aspect production listCtrType
top::Type ::=
{
  top.transType = "common.ConsCell";
  top.transTypeRep = "new common.BaseTypeRep(\"List\")";
  top.transFreshTypeRep = top.transTypeRep;
  top.transTypeName = "List";
}


-- A wonderous hack
aspect production emptyList
top::Expr ::= '[' ']'
{
  -- Technically, this is interfering, however it's hardly the worst violation
  -- in the Silver compiler, and it's not too bad: we do the same thing, just
  -- slightly more efficiently.
  -- For the time being, this is easier than (and as effective as) amending
  -- function calls with special optimization cases.

  -- This makes `[]` act like a constant literal (e.g. a number) instead of
  -- a function call.
  top.translation = "(common.ConsCell)common.ConsCell.nil";
  top.lazyTranslation = top.translation;
}
