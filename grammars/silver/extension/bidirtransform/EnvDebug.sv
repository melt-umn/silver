grammar silver:extension:bidirtransform;

attribute ppDebug occurs on Env;
synthesized attribute filteredProds::Decorated Env occurs on Env;

aspect default production 
top::Env ::=
{
  top.ppDebug = "default Env";
  top.filteredProds = top;  
}

aspect production i_emptyEnv
top::Env ::=
{
  top.ppDebug = "<>";
  top.filteredProds = top;
}

aspect production i_appendEnv
top::Env ::= e1::Decorated Env  e2::Decorated Env
{
  top.ppDebug = "<<" ++ e1.ppDebug ++ ">\n<" ++ e2.ppDebug ++ ">>"; 
  top.filteredProds = decorate i_appendEnv(e1.filteredProds, e2.filteredProds) with {};  
}

aspect production i_newScopeEnv
top::Env ::= d::Defs  e::Decorated Env
{
  top.ppDebug = "<" ++ d.ppDebug ++ "," ++ e.ppDebug ++ ">"; 
  top.filteredProds = decorate i_newScopeEnv(d.filteredProdDefs, e.filteredProds) with {};
}