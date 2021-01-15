

synthesized attribute value :: Integer;
synthesized attribute prettierprint :: String;

nonterminal FooExpr with prettierprint, value;

abstract production subtractFoo
sum::FooExpr ::= l::FooExpr r::FooExpr
{
 sum.prettierprint = l.prettierprint ++ "-" ++ r.prettierprint;
 sum.value = l.value - r.value;
}

abstract production addfoo
sum::FooExpr ::= l::FooExpr r::FooExpr
{
  sum.prettierprint = l.prettierprint ++ "+" ++ r.prettierprint;
  sum.value = l.value + r.value;
}

synthesized attribute foopp :: String;
attribute foopp occurs on FooExpr;



aspect foopp on FooExpr of
| addfoo(l, _) -> "foo " ++ l.prettierprint
| subtractFoo(l,r) -> "foo " ++ l.prettierprint ++ "-" ++ r.prettierprint
| _ -> "default"
end;

synthesized attribute ppList :: [String];
nonterminal BarExpr with ppList, value;

abstract production barInit1
top::BarExpr ::= ppList::[String] value::Integer
{
  top.ppList = ppList;
  top.value = value;
}

abstract production barInit2
top::BarExpr ::= ppList::[String] value::Integer
{
  top.ppList = ppList;
  top.value = value + 1;
}

abstract production barInit3
top::BarExpr ::= ppList::[String] value::Integer
{
  top.ppList = ppList ++ ["extra"];
  top.value = value;
}

attribute foopp occurs on BarExpr;

aspect foopp on BarExpr of
| barInit1([],_) -> "emptyFoopp"
| barInit1(h::t,_) -> h ++ "Foopp"
| barInit2(h :: t,value) -> h ++ " and then " ++ toStringFromInteger(value)
| barInit3(_,val) -> toStringFromInteger(val)
| _ -> "testing"
end;



-- Which is tranformed to the following.
-- (This is the unparse output of the generated tree)
-- ----->

-- aspect production addfoo
-- top ::= __generated_284::silver_features:FooExpr __generated_285::silver_features:FooExpr
-- 	top.foopp = case __generated_284, __generated_285,  of l, _ -> "foo " ++ l.prettierprint end;
-- aspect production subtractFoo
-- top ::= __generated_286::silver_features:FooExpr __generated_287::silver_features:FooExpr

-- 	top.foopp = case __generated_286, __generated_287,  of l, r -> "foo " ++ l.prettierprint ++ "-" ++ r.prettierprint end;
-- aspect default production
-- top::FooExpr ::=

-- 	top.foopp = "default";

-- aspect production barInit1
-- top ::= __generated_288::[String] __generated_289::Integer

-- 	top.foopp = case __generated_288, __generated_289,  of [], _ -> "emptyFoopp" end;
-- aspect production barInit1
-- top ::= __generated_290::[String] __generated_291::Integer

-- 	top.foopp = case __generated_290, __generated_291,  of h::t, _ -> h ++ "Foopp" end;
-- aspect production barInit2
-- top ::= __generated_292::[String] __generated_293::Integer

-- 	top.foopp = case __generated_292, __generated_293,  of h::t, value -> h ++ " and then " ++ toStringFromInteger(value,) end;
-- aspect production barInit3
-- top ::= __generated_294::[String] __generated_295::Integer

-- 	top.foopp = case __generated_294, __generated_295,  of _, val -> toStringFromInteger(val,) end;
