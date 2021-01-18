

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
synthesized attribute hiddenAttr :: String;
nonterminal BarExpr with ppList, value, hiddenAttr;

abstract production barInit1
top::BarExpr ::= ppList::[String] value::Integer
{
  top.ppList = ppList;
  top.value = value;
  top.hiddenAttr = toString(value);
}

abstract production barInit2
top::BarExpr ::= ppList::[String] value::Integer
{
  top.ppList = ppList;
  top.value = value + 1;
  top.hiddenAttr = "aardvarks";
}

abstract production barInit3
top::BarExpr ::= ppList::[String] value::Integer
{
  top.ppList = ppList ++ ["extra"];
  top.value = value;
  top.hiddenAttr = toString(value);
}

abstract production barInit4
top::BarExpr ::=
{
  top.ppList = [];
  top.value = 10;
  top.hiddenAttr = "aardvarks";
}

attribute foopp occurs on BarExpr;

aspect foopp on top::BarExpr of
| barInit1([],_) -> "emptyFoopp"
| barInit1(h::t,_) -> h ++ "Foopp" ++ top.hiddenAttr
| barInit2(h :: t,value) -> h ++ " and then " ++ toString(value)
| barInit3(_,val) -> toString(val) ++ top.hiddenAttr
| barInit4() -> "Foopp"
| barInit5() -> "Foopp"
| _ -> top.hiddenAttr
end;
-- Which is tranformed to the following.
-- (This is the unparse output of the generated tree)
-- ----->

-- aspect production barInit1
-- top ::= __generated_305::[String] __generated_306::Integer

-- 	top.foopp = (case __generated_305, __generated_306 of [], _ -> "emptyFoopp" | h::t, _ -> h ++ "Foopp" ++ top.hiddenAttr | _ -> silver:core:error(, "Error: pattern match failed at ConvenienceAspects.sv:64:2\n",) end :: a);
-- aspect production barInit2
-- top ::= __generated_308::[String] __generated_309::Integer

-- 	top.foopp = (case __generated_308, __generated_309 of h::t, value -> h ++ " and then " ++ toString(value) | _ -> silver:core:error(, "Error: pattern match failed at ConvenienceAspects.sv:66:2\n",) end :: a);
-- aspect production barInit3
-- top ::= __generated_311::[String] __generated_312::Integer

-- 	top.foopp = (case __generated_311, __generated_312 of _, val -> toString(val) ++ top.hiddenAttr | _ -> silver:core:error(, "Error: pattern match failed at ConvenienceAspects.sv:67:2\n",) end :: a);
-- aspect default production
-- top::BarExpr ::=

-- 	top.foopp = top.hiddenAttr;
