-- imports silver:langutil;
imports silver:extension:list;

--TODO: These precedence numbers are pulled from a magician's hat
terminal OriginLParen_t    '^('    precedence = 50;
terminal NoOriginLParen_t  '^^('   precedence = 23;
terminal OriginNew_t       'new^'  precedence = 24;
terminal OriginRParen_t    ')^'    precedence = 24;
terminal TokOriginRParen_t ')^^'   precedence = 24;
terminal OriginNote_t      'note^' precedence = 24;
terminal OriginNoteFor_t   'for^'  precedence = 24;
terminal OriginDot_t       '^.'    precedence = 50;

concrete production originApplicationExprNoComment
top::Expr ::= prod::Expr '^(' args::AppExprs ')'
{
  local bogonLabel :: Expr = Silver_Expr{[]};
  forwards to originApplicationExpr(prod, '^(', args, ')^', bogonLabel, location=top.location);
}

concrete production originNote
top::Expr ::= 'note^' n::Expr 'for^' e::Expr
{
  forwards to e with {originsRules = n::top.originsRules;};
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
          $Expr{label} ++ $Expr{listExprOfExprList(top.originsRules)},
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
        [silver:extension:otx:childruntime:otxDbgNote("From ^^-expr")] ++ $Expr{listExprOfExprList(top.originsRules)})})),
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
    [silver:extension:otx:childruntime:otxDbgNote("new")] ++ $Expr{listExprOfExprList(top.originsRules)})};
  forwards to app;
}


concrete production originAccess
top::Expr ::= e::Expr '^.' q::QNameAttrOccur
{
  local accessexp :: Expr = access(e, '.', q, location=top.location);
  local shucked :: Expr = otxShuckValueImpl(accessexp, location=top.location);
  local lhsexpr :: Expr = baseExpr(
      qNameId(
        name(top.frame.signature.outputElement.elementName,
      top.location), location=top.location), location=top.location);

  accessexp.downSubst = top.downSubst;
  shucked.downSubst = accessexp.upSubst;

  local fwd :: Expr = Silver_Expr{silver:extension:otx:childruntime:javaCopy($Expr{shucked}, $Expr{lhsexpr},
    [silver:extension:otx:childruntime:otxDbgNote("^.")] ++ $Expr{listExprOfExprList(top.originsRules)})};
  forwards to fwd;
}

-- terminal OriginsKwd    'origins' precedence = 24;

-- concrete production originsOnDcl
-- top::AGDcl ::= 'origins' 'on' nt::QName ';'
-- {
--   top.unparse = "origins on " ++ nt.unparse;

--   forwards to makeDuplicateImpls(top.env, nt);
-- }
