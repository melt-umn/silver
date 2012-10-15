grammar silver:definition:flow:env;


attribute flowDefs occurs on RootSpec;

aspect function unparseRootSpec
String ::= r::Decorated RootSpec
{
  
  unparses <- ["flow [" ++ implode(",\n ", foldr(consFlow, nilFlow(), r.flowDefs).unparses) ++ "]"];
  
}

aspect production i_emptyRootSpec
top::RootSpec ::= 
{
  top.flowDefs = [];
}
aspect production i_rootSpecRoot
top::RootSpec ::= c1::Decorated Root
{
  top.flowDefs = c1.flowDefs;
}
aspect production i_appendRootSpec
top::RootSpec ::= c1::Decorated RootSpec  c2::Decorated RootSpec
{
  top.flowDefs = c1.flowDefs ++ c2.flowDefs;
}


