grammar silver:compiler:analysis:uniqueness;

monoid attribute hasSharedRefs::Boolean with false, ||;

attribute sharedRefs, hasSharedRefs occurs on InterfaceItems, InterfaceItem;
propagate sharedRefs, hasSharedRefs on InterfaceItems, InterfaceItem;

aspect production consInterfaceItem
top::InterfaceItems ::= h::InterfaceItem t::InterfaceItems
{
  top.interfaceErrors <- if !top.hasSharedRefs then ["Missing item sharedRefs"] else [];
}

aspect default production
top::InterfaceItem ::=
{
  propagate sharedRefs, hasSharedRefs;
}

abstract production sharedRefs
top::InterfaceItem ::= val::[(String, SharedRefSite)]
{
  propagate isEqual;
  top.sharedRefs <- val;
  top.hasSharedRefs <- true;
}

aspect function packInterfaceItems
InterfaceItems ::= r::Decorated RootSpec
{
  interfaceItems <- [sharedRefs(r.sharedRefs)];
}

attribute sharedRefs occurs on RootSpec;
propagate sharedRefs on RootSpec;
