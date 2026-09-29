grammar silver_features;

-- An access of a synthesized attribute on a tree that the production constructs requires only the inherited
-- attributes that the productions it is built from use (see test/flow/ConstructedAccess.sv),
-- so equations for the rest of the flow type can be omitted.

inherited attribute cacEnv1::String;
inherited attribute cacEnv2::String;
synthesized attribute cacOut::String;

nonterminal CacExpr with cacEnv1, cacEnv2, cacOut;
flowtype cacOut {cacEnv1, cacEnv2} on CacExpr;

production cacBoth
top::CacExpr ::=
{ top.cacOut = "both(" ++ top.cacEnv1 ++ ", " ++ top.cacEnv2 ++ ")"; }
production cacTwo
top::CacExpr ::=
{ top.cacOut = "two(" ++ top.cacEnv2 ++ ")"; }
production cacWrap
top::CacExpr ::= c::CacExpr
{
  c.cacEnv1 = top.cacEnv1;
  c.cacEnv2 = top.cacEnv2;
  top.cacOut = "wrap(" ++ c.cacOut ++ ")";
}

-- No equation for w.cacEnv1.
production cacUse
top::CacExpr ::=
{
  local w::CacExpr = cacWrap(cacWrap(cacTwo()));
  w.cacEnv2 = "2";
  top.cacOut = w.cacOut;
}

equalityTest(decorate cacUse() with {}.cacOut, "wrap(wrap(two(2)))", String, silver_tests);
