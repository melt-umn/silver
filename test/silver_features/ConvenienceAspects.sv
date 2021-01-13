

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
| barInit2(h :: t,value) -> h ++ " and then " ++ toStringFromInteger(value)
| barInit3(_,val) -> toStringFromInteger(val)
end;
