grammar silver:compiler:analysis:uniqueness;

attribute uniqueRefs occurs on Expr, Exprs, AppExprs, AppExpr, PrimPatterns, PrimPattern;
propagate uniqueRefs on Expr, Exprs, AppExprs, AppExpr, PrimPatterns, PrimPattern
  excluding
    errorAccessHandler, terminalAccessHandler,
    synDecoratedAccessHandler, inhDecoratedAccessHandler,
    transDecoratedAccessHandler,
    annoAccessHandler, synDataAccessHandler,
    unknownDclAccessHandler, inhUndecoratedAccessErrorHandler, transUndecoratedAccessErrorHandler,
    ifThenElse, lambdap, letp, matchPrimitiveReal, consPattern;

-- Unique references taken when this expression is wrapped in an attribute access
synthesized attribute accessUniqueRefs::[(String, UniqueRefSite)] occurs on Expr;

aspect default production
top::Expr ::=
{
  top.accessUniqueRefs = top.uniqueRefs;
}

aspect production childReference
top::Expr ::= q::Decorated! QName
{
  top.uniqueRefs <-
    case refSet of
    | just(inhs) when
        top.finalType.isUniqueDecorated &&
        isExportedBy(top.grammarName, [q.lookupValue.dcl.sourceGrammar], top.compiledGrammars) ->
      [(top.frame.fullName ++ ":" ++ q.lookupValue.fullName,
        uniqueRefSite(
          sourceGrammar=top.grammarName,
          sourceLocation=q.location,
          refSet=inhs,
          refFlowDeps=top.flowDeps
        ))]
    | _ -> []
    end;
  top.accessUniqueRefs = [];

  top.errors <-
    if !q.lookupValue.found || !top.finalType.isUniqueDecorated
    then []
    -- Check that we are exported by the decoration site.
    else if !isExportedBy(top.grammarName, [q.lookupValue.dcl.sourceGrammar], top.compiledGrammars)
    then [err(top.location, s"Orphaned unique reference to ${q.lookupValue.fullName} in production ${top.frame.fullName} (reference has type ${prettyType(top.finalType)}).")]
    -- Check that there is at most one unique reference taken to this decoration site.
    else if length(lookupUniqueRefs(top.frame.fullName, q.lookupValue.fullName, top.flowEnv)) > 1
    then [err(top.location, s"Multiple unique references taken to ${q.name} in production ${top.frame.fullName} (reference has type ${prettyType(top.finalType)}).")]
    else [];
}
aspect production localReference
top::Expr ::= q::Decorated! QName
{
  top.uniqueRefs <-
    case refSet of
    | just(inhs) when
          top.finalType.isUniqueDecorated &&
          isExportedBy(top.grammarName, [q.lookupValue.dcl.sourceGrammar], top.compiledGrammars) ->
        [(q.lookupValue.fullName,
          uniqueRefSite(
            sourceGrammar=top.grammarName,
            sourceLocation=q.location,
            refSet=inhs,
            refFlowDeps=top.flowDeps
          ))]
    | _ -> []
    end;
  top.accessUniqueRefs = [];

  top.errors <-
    if !q.lookupValue.found || !top.finalType.isUniqueDecorated
    then []
    -- Check that we are exported by the decoration site.
    else if !isExportedBy(top.grammarName, [q.lookupValue.dcl.sourceGrammar], top.compiledGrammars)
    then [err(top.location, s"Orphaned unique reference to ${q.lookupValue.fullName} in production ${top.frame.fullName} (reference has type ${prettyType(top.finalType)}).")]
    -- Check that there is at most one unique reference taken to this decoration site.
    else if length(lookupLocalUniqueRefs(q.lookupValue.fullName, top.flowEnv)) > 1
    then [err(top.location, s"Multiple unique references taken to ${q.name} in production ${top.frame.fullName} (reference has type ${prettyType(top.finalType)}).")]
    else [];
}
aspect production lhsReference
top::Expr ::= q::Decorated! QName
{
  top.errors <-
    if top.finalType.isUniqueDecorated
    then [err(top.location, s"Cannot take a unique reference (of type ${prettyType(top.finalType)}) to ${q.name}.")]
    else [];
}
aspect production forwardReference
top::Expr ::= q::Decorated! QName
{
  top.errors <-
    if top.finalType.isUniqueDecorated
    then [err(top.location, s"Cannot take a unique reference (of type ${prettyType(top.finalType)}) to the forward tree.")]
    else [];
}
aspect production productionReference
top::Expr ::= q::Decorated! QName
{
  top.errors <- flatMap(\ tv::TyVar ->
    let substTy::Type = performSubstitution(varType(tv), top.finalSubst)
    in if substTy.isUniqueDecorated
       then [err(top.location, s"Cannot specialize type variable ${prettyTypeWith(varType(tv), top.typerep.freeVariables)} of ${q.name}::${prettyType(top.typerep)} to a unique reference type ${prettyType(substTy)}")]
       else []
    end,
    top.typerep.freeVariables);
}
aspect production functionReference
top::Expr ::= q::Decorated! QName
{
  top.errors <- flatMap(\ tv::TyVar ->
    let substTy::Type = performSubstitution(varType(tv), top.finalSubst)
    in if substTy.isUniqueDecorated
       then [err(top.location, s"Cannot specialize type variable ${prettyTypeWith(varType(tv), top.typerep.freeVariables)} of ${q.name}::${prettyType(top.typerep)} to a unique reference type ${prettyType(substTy)}")]
       else []
    end,
    top.typerep.freeVariables);
}
aspect production classMemberReference
top::Expr ::= q::Decorated! QName
{
  top.errors <- flatMap(\ tv::TyVar ->
    let substTy::Type = performSubstitution(varType(tv), top.finalSubst)
    in if substTy.isUniqueDecorated
       then [err(top.location, s"Cannot specialize type variable ${prettyTypeWith(varType(tv), top.typerep.freeVariables)} of ${q.name}::${prettyType(top.typerep)} to a unique reference type ${prettyType(substTy)}")]
       else []
    end,
    top.typerep.freeVariables);
}
aspect production globalValueReference
top::Expr ::= q::Decorated! QName
{
  top.errors <- flatMap(\ tv::TyVar ->
    let substTy::Type = performSubstitution(varType(tv), top.finalSubst)
    in if substTy.isUniqueDecorated
       then [err(top.location, s"Cannot specialize type variable ${prettyTypeWith(varType(tv), top.typerep.freeVariables)} of ${q.name}::${prettyType(top.typerep)} to a unique reference type ${prettyType(substTy)}")]
       else []
    end,
    top.typerep.freeVariables);
}

-- Whether nonterminal uniqueness is preserved for this argument position,
-- i.e. this is an argument to a direct function or production application
-- that will be copied upon undecoration.
inherited attribute isNtUniquenessPreserving::Boolean occurs on AppExprs, AppExpr;
propagate isNtUniquenessPreserving on AppExprs;

monoid attribute appExprUniquenessErrors::[Message] occurs on AppExprs, AppExpr, AnnoAppExprs, AnnoExpr;
propagate appExprUniquenessErrors on AppExprs, AppExpr, AnnoAppExprs, AnnoExpr;

aspect production functionInvocation
top::Expr ::= e::Decorated! Expr es::Decorated! AppExprs anns::Decorated! AnnoAppExprs
{
  top.errors <- es.appExprUniquenessErrors ++ anns.appExprUniquenessErrors;
  es.isNtUniquenessPreserving =
    case e of
    | functionReference(_) -> true
    | productionReference(_) -> true
    | _ -> false
    end;
}

aspect production partialApplication
top::Expr ::= e::Decorated! Expr es::Decorated! AppExprs anns::Decorated! AnnoAppExprs
{
  top.errors <- es.appExprUniquenessErrors ++ anns.appExprUniquenessErrors;
  es.isNtUniquenessPreserving =
    case e of
    | functionReference(_) -> true
    | productionReference(_) -> true
    | _ -> false
    end;
}

aspect production annoExpr
top::AnnoExpr ::= qn::QName '=' e::AppExpr
{
  e.isNtUniquenessPreserving = false;
}

aspect production presentAppExpr
top::AppExpr ::= e::Expr
{
  top.appExprUniquenessErrors <-
    case top.appExprTyperep.baseType of
    | varType(_) -> uniqueContextErrors(e.uniqueRefs)  -- Would need linear types to make this work...
    | nonterminalType(_, _, true, _) -> uniqueContextErrors(e.uniqueRefs)
    | nonterminalType(_, _, _, _) when !top.isNtUniquenessPreserving -> uniqueContextErrors(e.uniqueRefs)
    -- Note that we permit passing unique refs in parameters declared with unique decorated types,
    -- since we know that any function must be uniqueness-preserving for them.
    | _ -> []
    end;
}

aspect production errorAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  top.uniqueRefs := e.accessUniqueRefs;
}
aspect production terminalAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  top.uniqueRefs := e.accessUniqueRefs;
}
aspect production synDecoratedAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  top.uniqueRefs := e.accessUniqueRefs;
}
aspect production inhDecoratedAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  top.uniqueRefs := e.accessUniqueRefs;
}
aspect production transDecoratedAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  top.uniqueRefs :=
    case refSet of
    | just(inhs) when top.finalType.isUniqueDecorated ->
        [(case e.flowVertexInfo of
          | just(rhsVertexType(sigName)) -> s"${top.frame.fullName}:${sigName}.${q.attrDcl.fullName}"
          | just(localVertexType(fName)) -> s"${fName}.${q.attrDcl.fullName}"
          | _ -> ""
          end,
          uniqueRefSite(
            sourceGrammar=top.grammarName,
            sourceLocation=q.location,
            refSet=inhs,
            refFlowDeps=top.flowDeps
          ))]
    | _ -> []
    end;

  top.errors <-
    if q.found && top.finalType.isUniqueDecorated
    then 
      case e.flowVertexInfo of
      | just(rhsVertexType(sigName)) ->
        -- Check that we are exported by the occurs-on or the production.
        if !isExportedBy(top.grammarName, [q.dcl.sourceGrammar, top.frame.sourceGrammar], top.compiledGrammars)
        then [err(top.location, s"Orphaned unique reference to ${top.unparse} in production ${top.frame.fullName} (reference has type ${prettyType(top.finalType)}).")]
        -- Check that there is at most one unique reference taken to this decoration site.
        else
         (if length(lookupTransUniqueRefs(top.frame.fullName, sigName, q.attrDcl.fullName, top.flowEnv)) > 1
          then [err(top.location, s"Multiple unique references taken to ${top.unparse} in production ${top.frame.fullName} (reference has type ${prettyType(top.finalType)}).")]
          -- Check that there isn't also a unique reference taken to e
          else []) ++
          if !null(lookupUniqueRefs(top.frame.fullName, sigName, top.flowEnv))
          then [err(top.location, s"Cannot take a unique reference to ${top.unparse} in production ${top.frame.fullName} (reference has type ${prettyType(top.finalType)}) since there is also a unique reference taken to ${e.unparse}.")]
          else []
      | just(localVertexType(fName)) ->
        -- Check that we are exported by the occurs-on or the local.
        if !isExportedBy(top.grammarName, [q.dcl.sourceGrammar, head(getValueDcl(fName, top.env)).sourceGrammar], top.compiledGrammars)
        then [err(top.location, s"Orphaned unique reference to ${top.unparse} in production ${top.frame.fullName} (reference has type ${prettyType(top.finalType)}).")]
        -- Check that there is at most one unique reference taken to this decoration site.
        else
         (if length(lookupLocalTransUniqueRefs(fName, q.attrDcl.fullName, top.flowEnv)) > 1
          then [err(top.location, s"Multiple unique references taken to ${top.unparse} in production ${top.frame.fullName} (reference has type ${prettyType(top.finalType)}).")]
          -- Check that there isn't also a unique reference taken to e
          else []) ++
          case lookupLocalUniqueRefs(fName, top.flowEnv) of
          | u :: _ ->
            [err(top.location, s"Cannot take a unique reference to ${top.unparse} in production ${top.frame.fullName} (reference has type ${prettyType(top.finalType)}) since there is also a unique reference taken to ${e.unparse} at ${u.sourceGrammar}:${u.sourceLocation.unparse}.")]
          | [] -> []
          end
      | _ -> [err(top.location, s"Cannot take a unique reference (of type ${prettyType(top.finalType)}) to ${top.unparse}")]
      end
    else [];
}
aspect production annoAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  top.uniqueRefs := e.accessUniqueRefs;
}
aspect production synDataAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  top.uniqueRefs := e.accessUniqueRefs;
}
aspect production inhUndecoratedAccessErrorHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  top.uniqueRefs := e.accessUniqueRefs;
}
aspect production transUndecoratedAccessErrorHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  top.uniqueRefs := e.accessUniqueRefs;
}
aspect production unknownDclAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  top.uniqueRefs := e.accessUniqueRefs;
}

aspect production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
{
  top.uniqueRefs :=
    e1.uniqueRefs ++
    unionMutuallyExclusiveRefs(e1.uniqueRefs, e2.uniqueRefs);
}

aspect production lambdaParamReference
top::Expr ::= q::Decorated! QName
{
  top.uniqueRefs <-
    if top.finalType.isUniqueDecorated
    then [(q.name, uniqueRefSite(
        refSet=top.finalType.typeRefSet.inhSetMembers,
        refFlowDeps=top.flowDeps,
        sourceGrammar=top.grammarName,
        sourceLocation=top.location
      ))]
    else [];
  top.accessUniqueRefs = [];
}

aspect production lambdap
top::Expr ::= params::ProductionRHS e::Expr
{
  top.uniqueRefs := filter(\ r::(String, UniqueRefSite) -> !contains(r.1, params.lambdaBoundVars), e.uniqueRefs);
  top.errors <- flatMap(\ n::String ->
    let rs::[UniqueRefSite] = lookupAll(n, e.uniqueRefs)
    in
      if length(rs) > 1
      then map(err(_, s"Multiple uses of unique reference lambda parameter ${n}"), map((.sourceLocation), rs))
      else []
    end,
    params.lambdaBoundVars);
}

aspect production lexicalLocalReference
top::Expr ::= q::Decorated! QName  fi::Maybe<VertexType>  fd::[FlowVertex]  rs::[(String, UniqueRefSite)]
{
  top.uniqueRefs <- rs;
  top.accessUniqueRefs = [];
}

aspect production letp
top::Expr ::= la::AssignExpr  e::Expr
{
  -- Excluding refs from la, they flow up through the lexicalLocalReferences in e
  top.uniqueRefs := e.uniqueRefs;
}
aspect production matchPrimitiveReal
top::Expr ::= e::Expr t::TypeExpr pr::PrimPatterns f::Expr
{
  top.uniqueRefs := e.uniqueRefs ++ unionMutuallyExclusiveRefs(pr.uniqueRefs, f.uniqueRefs);
  top.errors <- uniqueContextErrors(e.uniqueRefs);
}
aspect production consPattern
top::PrimPatterns ::= p::PrimPattern _ ps::PrimPatterns
{
  top.uniqueRefs := unionMutuallyExclusiveRefs(p.uniqueRefs, ps.uniqueRefs);
}
