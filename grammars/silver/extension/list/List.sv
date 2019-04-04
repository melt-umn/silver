grammar silver:extension:list;

import silver:definition:type:syntax;

terminal LSqr_t '[' ;
terminal RSqr_t ']' ;

-- The TYPE --------------------------------------------------------------------
concrete production listTypeExpr
top::TypeExpr ::= '[' te::TypeExpr ']'
{
  top.unparse = "[" ++ te.unparse ++ "]";

  top.typerep = listType(te.typerep);

  forwards to refTypeExpr('Decorated', 
    nominalTypeExpr(qNameTypeId(terminal(IdUpper_t, "core:List"), location=top.location),
      botlSome('<', typeListSingle(te, location=te.location), '>', location=top.location), location=top.location), location=top.location);
}

-- The expressions -------------------------------------------------------------

concrete production emptyList
top::Expr ::= '[' ']'
{
  top.unparse = "[]";

  forwards to mkStrFunctionInvocation(top.location, "core:nil", []);
}

-- TODO: BUG: '::' is HasType_t.  We probably want to have a different
-- terminal here, with different precedence!

concrete production consListOp
top::Expr ::= h::Expr '::' t::Expr
{
  top.unparse = "(" ++ h.unparse ++ " :: " ++ t.unparse ++ ")" ;
  
  h.downSubst = top.downSubst; t.downSubst = top.downSubst; -- TODO BUG: don't know what this is needed... unparse apparently??
  
  forwards to mkStrFunctionInvocation(top.location, "core:cons", [h, t]);
}

concrete production fullList
top::Expr ::= '[' es::Exprs ']'
{ 
  top.unparse = "[ " ++ es.unparse ++ " ]";
  
  es.downSubst = top.downSubst; -- TODO again, pretty printing garbage.

  forwards to es.listtrans;
}

synthesized attribute listtrans :: Expr occurs on Exprs;

aspect production exprsEmpty
top::Exprs ::=
{
  top.listtrans = emptyList('[',']', location=top.location);
}

aspect production exprsSingle
top::Exprs ::= e::Expr
{
  top.listtrans = mkStrFunctionInvocation(e.location, "core:cons", [e, emptyList('[',']', location=top.location)]);
}

aspect production exprsCons
top::Exprs ::= e1::Expr ',' e2::Exprs
{
  top.listtrans = mkStrFunctionInvocation(e1.location, "core:cons", [e1, e2.listtrans]);
}

-- Overloaded operators --------------------------------------------------------

abstract production listPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  forwards to mkStrFunctionInvocationDecorated(e1.location, "core:append", [e1,e2]);
}

abstract production listLengthBouncer
top::Expr ::= e::Decorated Expr
{
  -- Because `e` will get wrapped in `exprRef`, it's errors will not appear in the
  -- forward tree.
  top.errors := forward.errors ++ e.errors;

  forwards to mkStrFunctionInvocationDecorated(e.location, "core:listLength", [e]);
}

