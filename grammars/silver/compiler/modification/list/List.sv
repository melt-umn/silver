grammar silver:compiler:modification:list;

import silver:compiler:definition:type:syntax;

terminal LSqr_t '[' ;
terminal RSqr_t ']' ;

-- The TYPE --------------------------------------------------------------------
concrete production listTypeExpr
top::TypeExpr ::= '[' te::TypeExpr ']'
{
  top.unparse = "[" ++ te.unparse ++ "]";
  propagate grammarName, env, flowEnv;

  top.typerep = listType(te.typerep);
  
  top.errorsKindStar :=
    if top.typerep.kindrep != starKind()
    then [err(top.location, s"${top.unparse} has kind ${prettyKind(top.typerep.kindrep)}, but kind * is expected here")]
    else [];

  forwards to
    appTypeExpr(
      listCtrTypeExpr('[', ']', location=top.location),
      bTypeList('<', typeListSingle(te, location=te.location), '>', location=top.location),
      location=top.location);
}

concrete production listCtrTypeExpr
top::TypeExpr ::= '[' ']'
{
  top.unparse = "[]";

  top.typerep = listCtrType();
  
  top.errorsKindStar :=
    if top.typerep.kindrep != starKind()
    then [err(top.location, s"${top.unparse} has kind ${prettyKind(top.typerep.kindrep)}, but kind * is expected here")]
    else [];

  forwards to typerepTypeExpr(listCtrType(),location=top.location);
}

-- The expressions -------------------------------------------------------------

concrete production emptyList
top::Expr ::= '[' ']'
{
  top.unparse = "[]";

  forwards to mkStrFunctionInvocation(top.location, "silver:core:nil", []);
}

-- TODO: BUG: '::' is HasType_t.  We probably want to have a different
-- terminal here, with different precedence!

concrete production consListOp
top::Expr ::= h::Expr '::' t::Expr
{
  top.unparse = "(" ++ h.unparse ++ " :: " ++ t.unparse ++ ")" ;

  forwards to mkStrFunctionInvocation(top.location, "silver:core:cons", [h, t]);
}

concrete production fullList
top::Expr ::= '[' es::Exprs ']'
{ 
  top.unparse = "[ " ++ es.unparse ++ " ]";

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
  top.listtrans = mkStrFunctionInvocation(e.location, "silver:core:cons", [e, emptyList('[',']', location=top.location)]);
}

aspect production exprsCons
top::Exprs ::= e1::Expr ',' e2::Exprs
{
  top.listtrans = mkStrFunctionInvocation(e1.location, "silver:core:cons", [e1, e2.listtrans]);
}
