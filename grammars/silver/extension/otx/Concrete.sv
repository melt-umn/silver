
terminal OriginLParen  '^('      precedence = 24;

concrete production applicationExprPrime
top::Expr ::= prod::Expr '^(' args::AppExprs ')'
{
  top.unparse = prod.unparse ++ "^(" ++ args.unparse ++ ")";

  local frame :: BlockContext = top.frame;
  local sig :: NamedSignature = frame.signature;

  local lhsexpr :: Expr = baseExpr(
  		qNameId(
  			name(sig.outputElement.elementName,
  		top.location), location=top.location), location=top.location);
  
  local attribute computedAnnos :: AnnoAppExprs = oneAnnoAppExprs(
  	mkAnnoExpr(pair("origin",
  		Silver_Expr {just($Expr{lhsexpr})})),
  	location=top.location);

  forwards to application(prod, '(', args, ',', computedAnnos, ')', location=top.location);
}


terminal OTXDebugBuiltin  'otxdebug^';

concrete production otxDebug
top::Expr ::= 'otxdebug^' '(' a::Expr ')'
{
  top.unparse = "otxdebug^(" ++ a.unparse ++ ")";

  forwards to otxDebugImpl(a, location=top.location);
}