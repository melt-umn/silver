grammar silver:modification:let_fix:java;
import silver:modification:let_fix;
import silver:translation:java:core;
import silver:definition:core;
import silver:util;
import silver:definition:env;
import silver:translation:java:env;

import silver:translation:java:concrete_syntax:copper; -- TODO part of ugly hack

aspect production letp
top::Expr ::= 'let' la::LetAssigns 'in' e::Expr 'end'
{
  top.translation = "((" ++ top.typerep.transType ++ ")common.Util.let(context, new String[]{" ++ folds(", ", la.nameTrans) ++ "}, " ++ 
					     "new common.Lazy[]{" ++ folds(", ", la.valueTrans) ++ "}, " ++ 
					     "new common.Lazy(){public Object eval(common.DecoratedNode context) {return " ++ 
							e.translation ++ ";}}" ++ "))"; 
}

attribute nameTrans, valueTrans occurs on LetAssigns, AssignExpr;

aspect production assigns
top::LetAssigns ::= ae::AssignExpr ',' list::LetAssigns
{
  top.nameTrans = ae.nameTrans ++ list.nameTrans;
  top.valueTrans = ae.valueTrans ++ list.valueTrans;
}

aspect production assignListSingle 
top::LetAssigns ::= ae::AssignExpr
{
  top.nameTrans = ae.nameTrans;
  top.valueTrans = ae.valueTrans;
}

aspect production assignExpr
top::AssignExpr ::= id::Name '::' t::Type '=' e::Expr
{
  top.nameTrans = ["\"" ++ fName ++ "\""];
  top.valueTrans = ["new common.Lazy(){public Object eval(common.DecoratedNode context) {return " ++ e.translation ++ ";}}"];
}

--TODO BUG HACK UGLY FIXME OHGOD
attribute actionCodeType occurs on LetAssigns, AssignExpr;
