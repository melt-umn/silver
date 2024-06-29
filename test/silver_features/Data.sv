grammar silver_features;

synthesized attribute dItems::[Integer];
inherited attribute dItemsIn::[Integer];
annotation dId::Integer;

data nonterminal FooData with dItems;
data nonterminal BarData<a b> with dItems, dId;
nonterminal BazNonData with dItems, dItemsIn;

production aFD
top::FooData ::= i::Integer x::FooData
{ top.dItems = i :: x.dItems; }

production bFD
top::FooData ::= x::BarData<FooData Boolean>
{ top.dItems = x.dId :: x.dItems; }

production cFD
top::FooData ::= x::BazNonData
{
  x.dItemsIn = [5];
  top.dItems = x.dItems;
}

production bBD
attribute dItems {} occurs on a =>
top::BarData<a b> ::= i::Integer x::a y::b
{
  top.dItems = i :: x.dItems;
}

production bND
top::BazNonData ::=
{ top.dItems = 4 :: top.dItemsIn; }

function dItems
[Integer] ::= x::FooData
{
  return
    case x of
    | aFD(i, y) -> i :: dItems(y)
    | bFD(bBD(j, x, y, dId=i)) -> i :: j :: dItems(^x)
    | cFD(r) -> r.dItems
    end;
}

global dataTerm::FooData = aFD(1, bFD(bBD(3, cFD(bND()), false, dId=2)));

equalityTest(dataTerm.dItems, [1, 2, 3, 4, 5], [Integer], silver_tests);
equalityTest(dItems(dataTerm), [1, 2, 3, 4, 5], [Integer], silver_tests);

wrongCode "Inherited attributes may not occur on data nonterminals." {
  inherited attribute dataInh::Integer occurs on FooData;
}

data DataDcl1
  = aDD1 Integer x::FooData
  | bDD1 BarData<FooData Boolean>
  | cDD1 x::BazNonData
  with dId;

global ddThing::DataDcl1 = aDD1(42, cFD(bND()), dId=37);
