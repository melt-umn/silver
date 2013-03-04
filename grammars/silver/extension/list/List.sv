grammar silver:extension:list;

import silver:definition:type:syntax;

terminal LSqr_t '[' ;
terminal RSqr_t ']' ;

-- The TYPE --------------------------------------------------------------------
concrete production listType
top::Type ::= '[' te::Type ']'
{
  top.pp = "[" ++ te.pp ++ "]";

  top.typerep = listTypeExp(te.typerep);

  forwards to refType('Decorated', 
    nominalType(qNameTypeId(terminal(IdUpper_t, "core:List"), location=top.location),
      botlSome('<', typeListSingle(te, location=te.location), '>', location=top.location), location=top.location), location=top.location);
}

-- The expressions -------------------------------------------------------------

concrete production emptyList
top::Expr ::= '[' ']'
{
  top.pp = "[]";

  forwards to mkStrFunctionInvocation(top.location, "core:nil", []);
}

-- TODO: BUG: '::' is HasType_t.  We probably want to have a different
-- terminal here, with different precedence!

concrete production consListOp
top::Expr ::= h::Expr '::' t::Expr
{
  top.pp = "(" ++ h.pp ++ " :: " ++ t.pp ++ ")" ;
  
  h.downSubst = top.downSubst; t.downSubst = top.downSubst; -- TODO BUG: don't know what this is needed... pp apparently??
  
  forwards to mkStrFunctionInvocation(top.location, "core:cons", [h, t]);
}

concrete production fullList
top::Expr ::= '[' es::Exprs ']'
{ 
  top.pp = "[ " ++ es.pp ++ " ]";
  
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
  top.errors <- e.errors;
  forwards to mkStrFunctionInvocationDecorated(e.location, "core:listLength", [e]);
}

