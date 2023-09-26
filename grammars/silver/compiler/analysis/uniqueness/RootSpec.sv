grammar silver:compiler:analysis:uniqueness;

monoid attribute hasUniqueRefs::Boolean with false, ||;

attribute uniqueRefs, hasUniqueRefs occurs on InterfaceItems, InterfaceItem;
propagate uniqueRefs, hasUniqueRefs on InterfaceItems, InterfaceItem;

aspect production consInterfaceItem
top::InterfaceItems ::= h::InterfaceItem t::InterfaceItems
{
  top.interfaceErrors <- if !top.hasUniqueRefs then ["Missing item uniqueRefs"] else [];
}

aspect default production
top::InterfaceItem ::=
{
  propagate uniqueRefs, hasUniqueRefs;
}

abstract production uniqueRefs
top::InterfaceItem ::= val::[(String, UniqueRefSite)]
{
  propagate isEqual;
  top.uniqueRefs <- val;
  top.hasUniqueRefs <- true;
}

aspect function packInterfaceItems
InterfaceItems ::= r::Decorated RootSpec
{
  interfaceItems <- [uniqueRefs(r.uniqueRefs)];
}

attribute uniqueRefs occurs on RootSpec;
propagate uniqueRefs on RootSpec;
