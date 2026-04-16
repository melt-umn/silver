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
  attr.attrFor = dl.typerep;

  local assert::ProductionStmt = edgeAssertionBoth(
    \e::Expr ->
      Silver_ProductionStmt{$QName{qName(dl.name)}.$QName{qnScopeAttr(attr.name, lab)} <- [$Expr{e}];},
    attr.typerep, lab, ^tgt
  );

  forwards to @assert;

  -- Avoid errors about non-existence of attr_lab for some lab
  top.errors := if !null(attr.errors) then attr.errors else assert.errors;
}

--

production edgeAssertionBoth
top::ProductionStmt ::=
  lhs::(ProductionStmt ::= Expr) srcTy::Type lab::String tgt::Expr
{
  propagate env;

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

  top.errors := if null(edgeErrs) then contrib.errors else edgeErrs;

  forwards to @contrib;
}
