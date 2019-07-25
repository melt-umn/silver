-- imports silver:langutil;
imports silver:extension:list;

--TODO: These precedence numbers are pulled from a magician's hat
terminal OriginLParen_t  '^('      precedence = 24;
terminal NoOriginLParen_t  '^^('      precedence = 23;
terminal OriginNew_t     'new^'    precedence = 24;
terminal OriginRParen_t  ')^'      precedence = 24;
terminal TokOriginRParen_t  ')^^'      precedence = 24;

concrete production originApplicationExprNoComment
top::Expr ::= prod::Expr '^(' args::AppExprs ')'
{
  local bogonLabel :: Expr = Silver_Expr{[]};
  forwards to originApplicationExpr(prod, '^(', args, ')^', bogonLabel, location=top.location);
}

concrete production originApplicationExprNoArgs
top::Expr ::= prod::Expr '^(' ')^' label::Expr
{
  forwards to originApplicationExpr(prod, '^(', emptyAppExprs(location=top.location), ')^', label, location=top.location);
}

concrete production originApplicationExpr
top::Expr ::= prod::Expr '^(' args::AppExprs ')^' label::Expr
{
  top.unparse = prod.unparse ++ "^(" ++ args.unparse ++ ")^" ++ label.unparse;

  local frame :: BlockContext = top.frame;
  local sig :: NamedSignature = frame.signature;

  local lhsexpr :: Expr = baseExpr(
      qNameId(
        name(sig.outputElement.elementName,
      top.location), location=top.location), location=top.location);

  local typeNameSnipped :: String = sig.outputElement.typerep.typeName;
  local ntWapperRefExpr :: Expr = baseExpr(qNameId(name(
    "otxLink" ++ last(explode(":", typeNameSnipped)),
    top.location), location=top.location), location=top.location);
  
  local computedAnnos :: AnnoAppExprs = oneAnnoAppExprs(
    mkAnnoExpr(pair("otxinfo",
      Silver_Expr {silver:extension:otx:childruntime:originOtxInfo(
        $Expr{ntWapperRefExpr}($Expr{lhsexpr}),
          $Expr{label} ++ [
           silver:extension:otx:childruntime:ruleLocNote($Expr{stringConst(terminal(String_t, "\"" ++ top.location.unparse ++ "\"", top.location), location=top.location)})],
         false)})),
    location=top.location);

  forwards to application(prod, '(', args, ',', computedAnnos, ')', location=top.location);
}

concrete production noOriginApplicationExpr
top::Expr ::= prod::Expr '^^(' args::AppExprs ')'
{
  top.unparse = prod.unparse ++ "^^(" ++ args.unparse ++ ")";

  local computedAnnos :: AnnoAppExprs = oneAnnoAppExprs(
    mkAnnoExpr(pair("otxinfo",
      Silver_Expr {silver:extension:otx:childruntime:otherOtxInfo("noOriginApplicationExpr",
        [silver:extension:otx:childruntime:otxDbgNote("From ^^-expr"),
        silver:extension:otx:childruntime:ruleLocNote($Expr{stringConst(terminal(String_t, "\"" ++ top.location.unparse ++ "\"", top.location), location=top.location)})])})),
    location=top.location);

  forwards to application(prod, '(', args, ',', computedAnnos, ')', location=top.location);
}


concrete production originNewNoComment
top::Expr ::= 'new^' '(' e::Expr ')'
{
  local bogonLabel :: Expr = Silver_Expr{[]};
  forwards to originNew('new^', '(', e, ')^', bogonLabel, location=top.location);
}

concrete production originNew
top::Expr ::= 'new^' '(' e::Expr ')^' label::Expr
{
  local shucked :: Expr = otxShuckValueImpl(e, location=top.location);
  shucked.downSubst = top.downSubst;

  local app :: Expr = Silver_Expr {silver:extension:otx:childruntime:javaDup($Expr{shucked}, $Expr{label} ++
    [silver:extension:otx:childruntime:otxDbgNote("new"),
     silver:extension:otx:childruntime:ruleLocNote($Expr{stringConst(terminal(String_t, "\"" ++ top.location.unparse ++ "\"", top.location), location=top.location)})])};
  forwards to app;
}


terminal OTXDebugBuiltin  'otxdebug^';

concrete production otxDebugManual
top::Expr ::= 'otxdebug^' '(' a::Expr ')'
{
  top.unparse = "otxdebug^(" ++ a.unparse ++ ")";

  forwards to otxDebugImpl(a, location=top.location);
}


terminal OriginsKwd    'origins' precedence = 24;

concrete production originsOnDcl
top::AGDcl ::= 'origins' 'on' nt::QName ';'
{
  top.unparse = "origins on " ++ nt.unparse;

  forwards to makeDuplicateImpls(top.env, nt);
}
