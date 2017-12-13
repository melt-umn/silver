grammar silver:extension:bidirtransform;

synthesized attribute allDefs::[Def] occurs on Env;

aspect default production 
top::Env ::= 
{
    top.allDefs = [];
}

aspect production i_emptyEnv
top::Env ::=
{
    top.allDefs = [];
}

aspect production i_appendEnv
top::Env ::= e1::Decorated Env  e2::Decorated Env
{
    top.allDefs = e1.allDefs ++ e2.allDefs;
}

aspect production i_newScopeEnv
top::Env ::= d::Defs  e::Decorated Env
{
    top.allDefs = [d] ++ e.allDefs;
}