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

  local lhsexpr :: Expr = mkLhsRef(top);

  local typeNameSnipped :: String = top.frame.signature.outputElement.typerep.typeName;
  local ntWapperRefExpr :: Expr = baseExpr(qNameId(name(
    "otxLink" ++ last(explode(":", typeNameSnipped)),
    top.location), location=top.location), location=top.location);

  local sameProd :: Boolean = case prod of
    | baseExpr(qn) -> qn.name == last(explode(":", top.frame.fullName))
    | _ -> false
  end;

  local isContractum :: Boolean = !(sameProd && top.isRuleRoot);

  local allNotes :: Expr = Silver_Expr{$Expr{label} ++ $Expr{listExprOfExprList(top.originsRules)}};

  local originValue :: Expr = if (!sameProd) && top.isRuleRoot then
    Silver_Expr {silver:extension:otx:childruntime:originAndRedexOtxInfo(
        $Expr{ntWapperRefExpr}($Expr{lhsexpr}), $Expr{allNotes},
        $Expr{ntWapperRefExpr}($Expr{lhsexpr}), $Expr{allNotes},
        $Expr{boolExprOfBool(isContractum)})}
    else Silver_Expr {silver:extension:otx:childruntime:originOtxInfo(
        $Expr{ntWapperRefExpr}($Expr{lhsexpr}), $Expr{allNotes},
        $Expr{boolExprOfBool(isContractum)})};
  
  local computedAnnos :: AnnoAppExprs = oneAnnoAppExprs(
    mkAnnoExpr(pair("otxinfo", originValue)),
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

  local notes :: Expr = Silver_Expr {$Expr{label} ++
    [silver:extension:otx:childruntime:otxDbgNote("new")] ++ $Expr{listExprOfExprList(top.originsRules)}};

  local shuckedLhs :: Expr = otxShuckValueImpl(mkLhsRef(top), location=top.location);

  local app :: Expr = if top.isRuleRoot then 
    Silver_Expr {silver:extension:otx:childruntime:javaDup($Expr{shucked}, $Expr{shuckedLhs}, $Expr{notes})}
    else
    Silver_Expr {silver:extension:otx:childruntime:javaDupNullRedex($Expr{shucked}, $Expr{notes})};
  forwards to app;
}


concrete production originAccess
top::Expr ::= e::Expr '^.' q::QNameAttrOccur
{
  local accessexp :: Expr = access(e, '.', q, location=top.location);
  local shucked :: Expr = otxShuckValueImpl(accessexp, location=top.location);
  local lhsexpr :: Expr = mkLhsRef(top);

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


function mkLhsRef
Expr ::= top::Decorated Expr --need .frame anno
{
  return baseExpr(
      qNameId(
        name(top.frame.signature.outputElement.elementName,
      top.location), location=top.location), location=top.location);
}