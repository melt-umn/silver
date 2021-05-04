

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
| barInit2([],value) -> toString(value)
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


nonterminal BazExpr with ppList, value, hiddenAttr;

abstract production bazInit1
top::BazExpr ::= ppList::[String] value::Integer
{
  top.ppList = ppList;
  top.value = value;
  top.hiddenAttr = toString(value);
}

abstract production bazInit2
top::BazExpr ::= ppList::[String] value::Integer
{
  top.ppList = ppList;
  top.value = value + 1;
  top.hiddenAttr = "aardvarks";
}

abstract production bazInit3
top::BazExpr ::= ppList::[String] value::Integer
{
  top.ppList = ppList ++ ["extra"];
  top.value = value;
  top.hiddenAttr = toString(value);
}

abstract production bazInit4
top::BazExpr ::=
{
  top.ppList = [];
  top.value = 10;
  top.hiddenAttr = "aardvarks";
}

synthesized attribute bazList :: [Integer] with ++ occurs on BazExpr;

aspect production bazInit1
top::BazExpr ::= ppList::[String] value::Integer
{
  top.bazList := [4];
}

aspect bazList on BazExpr using := of
| bazInit2(h::t,value) -> [length(h), value]
| bazInit2([],value) -> [0, value]
| bazInit3(_,val) -> [val]
| bazInit4() -> []
end;


synthesized attribute bagList :: [String] with ++ occurs on BazExpr;


aspect bagList on top::BazExpr using <- of
| bazInit2(h::t,value) -> [h, toString(value)]
| bazInit2([],value) -> [toString(value)]
| bazInit3(_,val) -> [top.hiddenAttr]
| bazInit4() -> explode(top.hiddenAttr,"\t")
| _ -> []
end;

wrongCode "Undeclared value 'top'." {
    aspect foopp on BarExpr of
    | barInit1() -> "Foopp" ++ top.hiddenAttr
    | _ -> top.hiddenAttr
    end;
}


synthesized attribute bagList2 :: [String] with ++ occurs on BazExpr;

warnCode "This pattern and the ones that follow are being ignored." {
    aspect bagList2 on top::BazExpr using <- of
    | bazInit2(h::t,value) -> [h, toString(value)]
    | _ -> []
    | _ -> ["ignored"]
    end;
}

synthesized attribute bagList3 :: [String] with ++ occurs on BazExpr;

warnCode "This pattern and the ones that follow are being ignored." {
    aspect bagList2 on top::BazExpr using <- of
    | bazInit2(h::t,value) -> [h, toString(value)]
    | bazInit2([],value) -> [toString(value)]
    | coolName -> [coolName.hiddenAttr]
    | _ -> ["ignored"]
    end;
}


warnCode "warning: This pattern-matching is not exhaustive.  Here is an example of a case that is not matched:  [], _" {
    synthesized attribute bagList4 :: [String] with ++ occurs on BazExpr;
        aspect bagList2 on top::BazExpr using <- of
        | bazInit2(h::t,value) -> [h, toString(value)]
        | coolName -> [coolName.hiddenAttr]
        end;
}

synthesized attribute gAttribute :: String occurs on BazExpr;
aspect gAttribute on BazExpr of
    | bazInit2(h::t,value) -> h ++ toString(value)
    | bazInit2([],value) -> toString(value)
    | bazInit3(_,val) -> toString(val)
    | coolName -> coolName.hiddenAttr
end;
