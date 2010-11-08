grammar silver:driver;

function unitMergeSort
[Unit] ::= c1::[Unit]
{
  return sortBy(unitLTE, c1);
}

function unitLTE
Boolean ::= l::Unit r::Unit
{
  return l.order <= r.order;
}

-- TODO: this file got obliterated by poly silver and sortBy.  merge into BuildProcess?
