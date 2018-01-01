grammar core:reflect;

imports silver:langutil;
imports silver:langutil:pp;

synthesized attribute pps :: [Document];

nonterminal AST with pp;

abstract production nonterminalAST
top::AST ::= prodName::String children::ASTs annotations::NamedASTs
{
  top.pp = cat(text(prodName), parens(ppImplode(pp", ", children.pps ++ annotations.pps)));
}

abstract production listAST
top::AST ::= vals::ASTs
{
  top.pp = brackets(ppImplode(pp", ", vals.pps));
}

abstract production stringAST
top::AST ::= s::String
{
  
}

abstract production integerAST
top::AST ::= i::Integer
{
  
}

abstract production floatAST
top::AST ::= f::Float
{
  
}

abstract production booleanAST
top::AST ::= b::Boolean
{
  
}

abstract production foreignAST
top::AST ::= x::a
{
  
}

nonterminal ASTs with pps;

abstract production consAST
top::ASTs ::= h::AST t::ASTs
{
  
}

abstract production nilAST
top::ASTs ::=
{
  
}

nonterminal NamedASTs with pps;

abstract production consNamedAST
top::NamedASTs ::= h::NamedAST t::NamedASTs
{
  
}

abstract production nilNamedAST
top::NamedASTs ::=
{
  
}

nonterminal NamedAST with pp;

abstract production namedAST
top::NamedAST ::= n::String v::AST
{
  
}
