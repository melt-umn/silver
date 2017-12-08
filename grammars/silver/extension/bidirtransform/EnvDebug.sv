grammar silver:extension:bidirtransform;

attribute ppDebug occurs on Env;

aspect default production 
top::Env ::=
{
  top.ppDebug = "default Env";
}

aspect production i_emptyEnv
top::Env ::=
{
  top.ppDebug = "<>";
}

aspect production i_appendEnv
top::Env ::= e1::Decorated Env  e2::Decorated Env
{
  top.ppDebug = "<<" ++ e1.ppDebug ++ ">\n<" ++ e2.ppDebug ++ ">>"; 
}

aspect production i_newScopeEnv
top::Env ::= d::Defs  e::Decorated Env
{
  top.ppDebug = "<" ++ d.ppDebug ++ ',' ++ e.ppDebug ++ ">"; 
}