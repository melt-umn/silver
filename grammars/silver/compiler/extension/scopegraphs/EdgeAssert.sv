grammar silver:compiler:extension:scopegraphs;

--

-- s -[ lex ]-> s';
production edgeAssertionLocal
top::ProductionStmt ::= src::QName lab::String tgt::Expr
{
  propagate env;

  forwards to edgeAssertionBoth(
    \e::Expr -> Silver_ProductionStmt{$QName{^src}.$QName{qName(lab)} <- [$Expr{e}];},
    localReference(src).typerep, lab, ^tgt
  );
}

-- top.s -[ lex ]-> s';
production edgeAssertionInh
top::ProductionStmt ::= dl::DefLHS attr::QNameAttrOccur lab::String tgt::Expr
{
  propagate env;
  dl.defLHSattr = decorate qNameAttrOccur(qName(attr.name ++ "_" ++ lab))
                  with {attrFor = dl.typerep; grammarName = top.grammarName;
                        env = top.env; config = top.config;};
  attr.attrFor = dl.typerep;

  local assert::ProductionStmt = edgeAssertionBoth(
    \e::Expr ->
      Silver_ProductionStmt{$QName{qName(dl.name)}.$QName{qnScopeAttr(attr.name, lab)} <- [$Expr{e}];},
    attr.typerep, lab, ^tgt
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
  lhs::(ProductionStmt ::= Expr) srcTy::Type lab::String tgt::Expr
{
  propagate env;

  tgt.config = top.config;

  local sg::Maybe<ScopeGraphDclInfo> = lookupGraphDclOpt("_Scope_Default", top.sgEnv);
  local labsfn::[String] = mapOrElse([], (.labelsFn), sg);
  local sgScopeTy::Type = mapOrElse(errorType(), (.scopeType), sg);

  local attribute errCheck1::TypeCheck = check(^srcTy, ^sgScopeTy);
  local attribute errCheck2::TypeCheck = check(tgt.typerep, ^sgScopeTy);
  thread downSubst, upSubst on top, tgt, errCheck1, errCheck2, top;

  production attribute edgeErrs::[Message] with ++;
  edgeErrs := 
    if !sg.isJust
    then [errFromOrigin(top, "No scope graph decl found!")]
    else [];
  edgeErrs <-
    if !contains(top.grammarName ++ ":" ++ lab, labsfn)
    then [errFromOrigin(top, "No known scope graph label '" ++ lab ++ "'.")]
    else [];
  edgeErrs <-
    if errCheck1.typeerror
    then [errFromOrigin(top, "Scope edge source must be a Decorated Scope " ++ 
                             "but is instead has type " ++ errCheck1.leftpp)]
    else [];
  edgeErrs <-
    if errCheck2.typeerror
    then [errFromOrigin(top, "Scope edge target must be a Decorated Scope " ++ 
                             "but is instead has type " ++ errCheck2.leftpp)]
    else [];

  local contrib::ProductionStmt = lhs(^tgt);

  top.errors := edgeErrs;
  top.errors <- tgt.errors;

  --top.errors := if null(edgeErrs) then contrib.errors else edgeErrs;

  forwards to @contrib;
}
