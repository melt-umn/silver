grammar flow;

attribute isEqual, isEqualTo occurs on Expr;
flowtype isEqual {isEqualTo} on Expr;
propagate isEqual, isEqualTo on Expr;

function isEqual
attribute isEqualTo<a> occurs on a,
attribute isEqual {isEqualTo} occurs on a =>
Boolean ::= x::a y::a
{
  x.isEqualTo = y;
  return x.isEqual;
}

warnCode "Equation has transitive dependency on child x's inherited attribute for flow:env1 but this equation appears to be missing." {
function isEqualBad
attribute isEqualTo<a> occurs on a,
attribute isEqual {isEqualTo, env1} occurs on a =>
Boolean ::= x::a y::a
{
  x.isEqualTo = y;
  return x.isEqual;
}
}
