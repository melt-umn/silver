grammar silver:compiler:extension:scopegraphs;

--

-- s -[ lex ]-> s2;
production edgeAssertionLocal
top::ProductionStmt ::= src::QName lab::String tgt::Expr
{
  propagate env;

  forwards to edgeAssertionBoth(
    \e::Expr -> Silver_ProductionStmt{$QName{^src}.$QName{qName(lab)} <- [$Expr{e}];},
    localReference(src).typerep, lab, ^tgt, []--labels
  );
}

-- n.s1 -[ lex ]-> s2;
production edgeAssertionInh
top::ProductionStmt ::= dl::DefLHS attr::QNameAttrOccur lab::String tgt::Expr
{
  propagate env;
  attr.attrFor = dl.typerep;

  forwards to edgeAssertionBoth(
    \e::Expr ->
      Silver_ProductionStmt{$QName{qName(dl.name)}.$QName{qnScopeAttr(attr.name, lab)} <- [$Expr{e}];},
    attr.typerep, lab, ^tgt, []--labels
  );
}

--

production edgeAssertionBoth
top::ProductionStmt ::=
  lhs::(ProductionStmt ::= Expr) srcTy::Type lab::String tgt::Expr possibleLabs::[String]
{
  propagate env;

  local labs::[String] =
    let res::[ScopeGraphDclInfo] = lookupGraphDcl("_Scope_Default", top.sgEnv) in
      case res of | h::[] -> h.labelsFn | _ -> [] end
    end;

  nondecorated local expectTy::Type =
    decoratedType(nonterminalType("silver:compiler:extension:scopegraphs:Scope", [], false, false),
                  inhSetType(labs));

  local attribute errCheck::TypeCheck = check(^srcTy, expectTy);
  thread downSubst, upSubst on top, tgt, errCheck, top;

  top.errors :=
    if !contains(top.grammarName ++ ":" ++ lab, labs)
    then [errFromOrigin(top, "No known scope graph label '" ++ lab ++ "'.")]
    else if errCheck.typeerror
    then [errFromOrigin(top, "Scope edge source must be a Decorated Scope " ++
                             "but is instead has type " ++ srcTy.typepp)]
    else contrib.errors;

  local contrib::ProductionStmt = lhs(^tgt);
  forwards to @contrib;
}
