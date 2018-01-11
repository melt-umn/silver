grammar core:reflect;

imports silver:langutil;
imports silver:langutil:pp;

synthesized attribute pps :: [Document]; -- TODO: Move to langutil

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
  top.pp = pp"\"${text(s)}\"";
}

abstract production integerAST
top::AST ::= i::Integer
{
  top.pp = text(toString(i));
}

abstract production floatAST
top::AST ::= f::Float
{
  top.pp = text(toString(f));
}

abstract production booleanAST
top::AST ::= b::Boolean
{
  top.pp = text(toString(b));
}

abstract production foreignAST
top::AST ::= x::a
{
  top.pp = pp"<FOREIGN>";
}

nonterminal ASTs with pps;

abstract production consAST
top::ASTs ::= h::AST t::ASTs
{
  top.pps = h.pp :: t.pps;
}

abstract production nilAST
top::ASTs ::=
{
  top.pps = [];
}

nonterminal NamedASTs with pps;

abstract production consNamedAST
top::NamedASTs ::= h::NamedAST t::NamedASTs
{
  top.pps = h.pp :: t.pps;
}

abstract production nilNamedAST
top::NamedASTs ::=
{
  top.pps = [];
}

nonterminal NamedAST with pp;

abstract production namedAST
top::NamedAST ::= n::String v::AST
{
  top.pp = pp"${text(n)}=${v.pp}";
}
