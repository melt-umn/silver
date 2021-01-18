

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
| _ -> top.hiddenAttr
end;


wrongCode "Undeclared value 'barInit5'." {
    aspect foopp on top::BarExpr of
    | barInit5() -> "Foopp"
    | _ -> top.hiddenAttr
    end;
}

wrongCode "barInit5 is not a production." {
    aspect foopp on top::BarExpr of
    | barInit5() -> "Foopp"
    | _ -> top.hiddenAttr
    end;
}
