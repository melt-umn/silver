-- imports silver:langutil;

-- Needed to be able to use their respective extensions in Silver_Expr blocks :/
imports silver:extension:list;
imports silver:modification:let_fix;

--TODO: These precedence numbers are pulled from a magician's hat
terminal OriginLParen_t    '^('    precedence = 50;
terminal NoOriginLParen_t  '^^('   precedence = 23;
terminal OriginNew_t       'new^'  precedence = 24;
terminal OriginRParen_t    ')^'    precedence = 24;
terminal TokOriginRParen_t ')^^'   precedence = 24;
terminal OriginNote_t      'note^' precedence = 24;
terminal OriginNoteFor_t   'for^'  precedence = 24;
terminal OriginDot_t       '^.'    precedence = 50;
terminal OriginNonterminal_t       'nonterminal^'  precedence = 24;

concrete production originApplicationExprNoComment
top::Expr ::= prod::Expr '^(' args::AppExprs ')'
{
  forwards to originApplicationExpr(prod, '^(', args, ')^', Silver_Expr{[]}, location=top.location);
}

concrete production originApplicationExprNoArgsNoComment
top::Expr ::= prod::Expr '^(' ')'
{
  forwards to originApplicationExpr(prod, '^(', emptyAppExprs(location=top.location), ')^', Silver_Expr{[]}, location=top.location);
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
    "originLink" ++ last(explode(":", typeNameSnipped)),
    top.location), location=top.location), location=top.location);

  local sameProd :: Boolean = case prod of
    | baseExpr(qn) -> qn.name == last(explode(":", top.frame.fullName))
    | _ -> false
  end;

  local isContractum :: Boolean = !(sameProd && top.isRuleRoot);

  local allNotes :: Expr = Silver_Expr{$Expr{label} ++ $Expr{listExprOfExprList(top.originsRules)}};

  local originValue :: Expr = if (!sameProd) && top.isRuleRoot then
    Silver_Expr {let cx :: OriginLink = $Expr{ntWapperRefExpr}($Expr{lhsexpr}) in
        silver:modification:origintracking:childruntime:originAndRedexOriginInfo(
        cx, $Expr{allNotes},
        cx, $Expr{allNotes},
        $Expr{boolExprOfBool(isContractum)}) end}
    else Silver_Expr {silver:modification:origintracking:childruntime:originOriginInfo(
        $Expr{ntWapperRefExpr}($Expr{lhsexpr}), $Expr{allNotes},
        $Expr{boolExprOfBool(isContractum)})};
  
  local computedAnnos :: AnnoAppExprs = oneAnnoAppExprs(
    mkAnnoExpr(pair("origininfo", originValue)),
    location=top.location);

  forwards to application(prod, '(', args, ',', computedAnnos, ')', location=top.location);
}

concrete production noOriginApplicationExpr
top::Expr ::= prod::Expr '^^(' args::AppExprs ')'
{
  top.unparse = prod.unparse ++ "^^(" ++ args.unparse ++ ")";

  local computedAnnos :: AnnoAppExprs = oneAnnoAppExprs(
    mkAnnoExpr(pair("origininfo",
      Silver_Expr {silver:modification:origintracking:childruntime:otherOriginInfo("noOriginApplicationExpr",
        [silver:modification:origintracking:childruntime:originDbgNote("From ^^-expr")] ++ $Expr{listExprOfExprList(top.originsRules)})})),
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
  local shucked :: Expr = originShuckValueImpl(e, location=top.location);
  shucked.downSubst = top.downSubst;

  local notes :: Expr = Silver_Expr {$Expr{label} ++
    [silver:modification:origintracking:childruntime:originDbgNote("new")] ++ $Expr{listExprOfExprList(top.originsRules)}};

  local shuckedLhs :: Expr = originShuckValueImpl(mkLhsRef(top), location=top.location);

  local app :: Expr = if top.isRuleRoot then 
    Silver_Expr {silver:modification:origintracking:childruntime:javaDup($Expr{shucked}, $Expr{shuckedLhs}, $Expr{notes})}
    else
    Silver_Expr {silver:modification:origintracking:childruntime:javaDupNullRedex($Expr{shucked}, $Expr{notes})};
  forwards to app;
}


concrete production originAccess
top::Expr ::= e::Expr '^.' q::QNameAttrOccur
{
  top.unparse = e.unparse ++ "^." ++ q.unparse;
  local accessexp :: Expr = access(e, '.', q, location=top.location);
  local shucked :: Expr = originShuckValueImpl(accessexp, location=top.location);
  local lhsexpr :: Expr = mkLhsRef(top);

  accessexp.downSubst = top.downSubst;
  shucked.downSubst = accessexp.upSubst;

  local fwd :: Expr = Silver_Expr{silver:modification:origintracking:childruntime:javaCopy($Expr{shucked}, $Expr{lhsexpr},
    [silver:modification:origintracking:childruntime:originDbgNote("^.")] ++ $Expr{listExprOfExprList(top.originsRules)})};
  forwards to fwd;
}

terminal OriginsKwd    'origins' precedence = 24;

concrete production originsOnDcl
top::AGDcl ::= 'origins' 'on' nt::QName ';'
{
  top.unparse = "origins on " ++ nt.unparse;

  local l :: Location = top.location;

  forwards to annotateDcl('annotation', qName(l, "origininfo"), botlNone(location=l),
    'occurs', 'on', nt, botlNone(location=l), ';', location=l);
}

concrete production originNonterminalDcl
top::AGDcl ::= cl::ClosedOrNot 'nonterminal^' id::Name tl::BracketedOptTypeExprs ';'
{
  top.unparse = "nonterminal^ " ++ id.unparse ++ tl.unparse ++ ";";

  local l :: Location = top.location;

  local originLinkTE :: TypeExpr = nominalTypeExpr(qNameTypeId(terminal(IdUpper_t, "OriginLink", l),
    location=l), botlNone(location=l), location=l);
  local ntTE :: TypeExpr = nominalTypeExpr(qNameTypeId(terminal(IdUpper_t, id.name, l),
    location=l), botlNone(location=l), location=l);

  local nextLinkNothingImpl :: Expr = Silver_Expr{nothing()};
  local nextLinkSomethingImpl :: Expr = Silver_Expr{just(n.origininfo)};
  local hasOrigins :: Boolean = findNamedSigElem("silver:modification:origintracking:childruntime:origininfo",
      annotationsForNonterminal(nonterminalType(top.grammarName ++ ":" ++ id.name, []), top.env), 0) != -1;
  local selectedImpl :: Expr = if hasOrigins then nextLinkSomethingImpl else nextLinkNothingImpl;
  local implStmt :: ProductionStmt = attributeDef(concreteDefLHS(qName(l, "top"), location=l),
    '.', qNameAttrOccur(qName(l, "nextOrigin"), location=l), '=', selectedImpl, ';', location=l);

  local newdcl :: AGDcl = productionDcl('abstract', 'production', name("originLink" ++ id.name, l),
    productionSignature(productionLHS(name("top", l), '::', originLinkTE, location=l), '::=',
      productionRHSCons(productionRHSElem(name("n", l), '::', ntTE, location=l), productionRHSNil(location=l),
        location=l), location=l), productionBody('{',
          productionStmtsSnoc(productionStmtsNil(location=l), implStmt, location=l), '}', location=l), location=l);

  forwards to appendAGDcl(
    nonterminalDcl(cl, 'nonterminal', id, tl, ';', location=l),
    newdcl, location=l);
}