grammar silver_features;

synthesized attribute dItems::[Integer];
inherited attribute dItemsIn::[Integer];
annotation dId::Integer;

data nonterminal FooData with dItems;
data nonterminal BarData<a> with dItems, dId;
nonterminal BazNonData with dItems, dItemsIn;

production aFD
top::FooData ::= i::Integer x::FooData
{ top.dItems = i :: x.dItems; }

production bFD
top::FooData ::= x::BarData<FooData>
{ top.dItems = x.dId :: x.dItems; }

production cFD
top::FooData ::= x::BazNonData
{
  x.dItemsIn = [5];
  top.dItems = x.dItems;
}

production bBD
attribute dItems {} occurs on a =>
top::BarData<a> ::= i::Integer x::a
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
    | bFD(bBD(j, y, dId=i)) -> i :: j :: dItems(y)
    | cFD(r) -> r.dItems
    end;
}

global dataTerm::FooData = aFD(1, bFD(bBD(3, cFD(bND()), dId=2)));

equalityTest(dataTerm.dItems, [1, 2, 3, 4, 5], [Integer], silver_tests);
equalityTest(dItems(dataTerm), [1, 2, 3, 4, 5], [Integer], silver_tests);

wrongCode "Inherited attributes may not occur on data nonterminals." {
  inherited attribute dataInh::Integer occurs on FooData;
}
