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

  -- Needed to satisfy flow analysis, since we demand translation of h and t.
  h.decSiteVertexInfo = nothing();
  t.decSiteVertexInfo = nothing();
  h.alwaysDecorated = false;
  t.alwaysDecorated = false;

  forwards to application(
    baseExpr(
      qName(top.location, "silver:core:cons"),
      location=top.location), '(',
    snocAppExprs(
      snocAppExprs(
        emptyAppExprs(location=top.location), ',',
        presentAppExpr(@h, location=top.location),
        location=top.location), ',',
      presentAppExpr(@t, location=top.location),
      location=top.location),
    ',',
    emptyAnnoAppExprs(location=top.location),
    ')', location=top.location);
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
  top.listtrans = consListOp(e, '::', emptyList('[',']', location=top.location), location=top.location);
}

aspect production exprsCons
top::Exprs ::= e1::Expr ',' e2::Exprs
{
  top.listtrans = consListOp(e1, '::', e2.listtrans, location=top.location);
}
