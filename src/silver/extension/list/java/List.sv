grammar silver:extension:list:java;

import silver:definition:core;
import silver:definition:env;
import silver:extension:list;
import silver:translation:java:core;
import silver:translation:java:env;

aspect production emptyList
top::Expr ::= '[' ']'
{
  top.translation = "common.ConsCell.nil";
}

aspect production emptyListWType
top::Expr ::= '[' '::' t::Type ']'
{
  top.translation = "common.ConsCell.nil";
}

aspect production fullList
top::Expr ::= '[' es::Exprs ']'
{ 
  top.translation = buildListLiteral(es.exprs);
}

function buildListLiteral
String ::= exps::[Decorated Expr]
{
  return if null(exps)
         then "common.ConsCell.nil"
         else "new common.ConsCell(" ++ wrapThunk(head(exps)) ++ ", " ++ buildListLiteral(tail(exps)) ++ ")";
}

aspect production consList
top::Expr ::= 'cons' '(' h::Expr ',' t::Expr ')'
{ 
  top.translation = "(new common.ConsCell(" ++ wrapThunk(h) ++ ", " ++ wrapThunk(t) ++ "))";
}

aspect production appendList
top::Expr ::= l::Expr r::Expr
{ 
  top.translation = "(new common.AppendCell(" ++ wrapThunk(l) ++ ", " ++ wrapThunk(r) ++ "))";
}

aspect production listLength
top::Expr ::= e::Expr
{
  top.translation = "(new Integer(((common.ConsCell)" ++ e.translation ++ ").length()))";
}

aspect production nullList
top::Expr ::= 'null' '(' l::Expr ')'
{ 
  top.translation = "((common.ConsCell)" ++ l.translation ++ ").nil()";
}

aspect production headList
top::Expr ::= 'head' '(' l::Expr ')'
{ 
  top.translation = "((" ++ l.typerep.listComponent.transType ++ ")((common.ConsCell)" ++ l.translation ++ ").head())";
}

aspect production tailList
top::Expr ::= 'tail' '(' l::Expr ')'
{ 
  top.translation = "((common.ConsCell)" ++ l.translation ++ ").tail()";
}

-- TypeRep

aspect production i_listTypeRep
top::TypeRep ::= e::Boolean tr::Decorated TypeRep
{
  top.transType = "common.ConsCell";
}

