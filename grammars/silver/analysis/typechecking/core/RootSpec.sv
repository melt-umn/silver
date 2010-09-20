grammar silver:analysis:typechecking:core;

attribute typeErrors occurs on RootSpec;

aspect production i_emptyRootSpec
top::RootSpec ::= 
{
  top.typeErrors := [];  
}

aspect production i_rootSpecRoot
top::RootSpec ::= c1::Decorated Root
{
  top.typeErrors := c1.typeErrors;
}

aspect production i_consRootSpec
top::RootSpec ::= c1::Decorated Root c2::Decorated RootSpec
{
  top.typeErrors := c1.typeErrors ++ c2.typeErrors;  
}

aspect production i_appendRootSpec
top::RootSpec ::= c1::Decorated RootSpec c2::Decorated RootSpec
{
  top.typeErrors := c1.typeErrors ++ c2.typeErrors;  
}

