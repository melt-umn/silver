grammar silver:compiler:extension:scopegraphs;

--

-- s -[ lex ]-> s';
production edgeAssertionLocal
top::ProductionStmt ::= src::QName lab::String tgt::Expr lst::Boolean
{
  propagate env, sgEnv;

  nondecorated local srcTy::Type = localReference(src).typerep;

  forwards to edgeAssertionBoth(
    \e::Expr -> Silver_ProductionStmt{$QName{^src}.$QName{qName(lab)} <- 
      $Expr{if !lst then Silver_Expr{[$Expr{e}]} else e};},
    srcTy, lab, ^tgt, lst
  );
}

-- top.s -[ lex ]-> s';
production edgeAssertionInh
top::ProductionStmt ::= dl::DefLHS attr::QNameAttrOccur lab::String tgt::Expr lst::Boolean
{
  propagate env, sgEnv;

  dl.defLHSattr = decorate qNameAttrOccur(qName(attr.name ++ "_" ++ lab))
                  with {attrFor = dl.typerep; grammarName = top.grammarName;
                        env = top.env; config = top.config;};
  attr.attrFor = dl.typerep;

  local assert::ProductionStmt = edgeAssertionBoth(
    \e::Expr ->
      Silver_ProductionStmt{$QName{qName(dl.name)}.$QName{qnScopeAttr(attr.name, lab)} <- 
        $Expr{if !lst then Silver_Expr{[$Expr{e}]} else e};},
    attr.typerep, lab, ^tgt, lst
  );

  forwards to @assert;

  local lhsErrs::[Message] =
    if !top.frame.hasFullSignature
    then [errFromOrigin(dl, "Edge LHS in function context must be a reference to a locally defined scope")]
    else 
      case dl of
      | lhsDefLHS(_) -> dl.errors
      | _ -> [errFromOrigin(dl, "Edge LHS must be " ++ top.frame.signature.outputElement.elementName ++ 
                                ".s for some scope attribute s, or a reference to a locally declared scope")]
      end;

  top.errors := lhsErrs ++ attr.errors ++ assert.errors;
}

--

production edgeAssertionBoth
top::ProductionStmt ::=
  lhs::(ProductionStmt ::= Expr) srcTy::Type lab::String tgt::Expr lst::Boolean
{
  propagate env, flowEnv, sgEnv, compiledGrammars, grammarName, frame, finalSubst;

  tgt.config = top.config;
  tgt.decSiteVertexInfo = nothing();
  tgt.appDecSiteVertexInfo = nothing();
  tgt.isRoot = true;

  local sg::Maybe<ScopeGraphDclInfo> = lookupGraphDclOpt("_Scope_Default", top.sgEnv);
  local labsfn::[String] = mapOrElse([], (.labelsFn), sg);
  local sgScopeTy::Type = mapOrElse(errorType(), (.scopeType), sg);

  local attribute errCheck1::TypeCheck = check(^srcTy, ^sgScopeTy);
  errCheck1.finalSubst = top.finalSubst;
  
  local attribute errCheck2::TypeCheck =
    if !lst then check(tgt.typerep, ^sgScopeTy) else check(tgt.typerep, listType(^sgScopeTy));
  errCheck2.finalSubst = top.finalSubst;

  thread downSubst, upSubst on top, tgt, errCheck1, errCheck2, top;

  local expectTgtTyPp::String = if !lst then "Decorated Scope" else "[Decorated Scope]";

  production attribute edgeErrs::[Message] with ++;
  edgeErrs := 
    if !sg.isJust
    then [errFromOrigin(top, "No scope graph decl found!")]
    else [];
  edgeErrs <-
    if !contains(top.grammarName ++ ":" ++ lab, labsfn)
    then [errFromOrigin(top, "No known scope graph label '`" ++ lab ++ "'.")]
    else [];
  edgeErrs <-
    if errCheck1.typeerror
    then [errFromOrigin(top, "Scope edge source must be a Decorated Scope" ++ 
                             " but is instead has type " ++ errCheck1.leftpp)]
    else [];
  edgeErrs <-
    if errCheck2.typeerror
    then [errFromOrigin(top, "Scope edge target must be a " ++ expectTgtTyPp ++ 
                             " but is instead has type " ++ errCheck2.leftpp)]
    else [];

  local contrib::ProductionStmt = lhs(^tgt);

  top.errors := edgeErrs;
  top.errors <- tgt.errors;

  forwards to @contrib;
}
