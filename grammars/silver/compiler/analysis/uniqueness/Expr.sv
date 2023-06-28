grammar silver:compiler:analysis:uniqueness;

attribute uniqueRefs occurs on Expr, Exprs, AppExprs, AppExpr, PrimPatterns, PrimPattern;
propagate uniqueRefs on Expr, Exprs, AppExprs, AppExpr, PrimPatterns, PrimPattern
  excluding
    errorAccessHandler, annoAccessHandler, terminalAccessHandler,
    synDecoratedAccessHandler, inhDecoratedAccessHandler,
    transDecoratedAccessHandler,
    errorDecoratedAccessHandler,
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
  local finalTy::Type = performSubstitution(top.typerep, top.finalSubst);
  top.uniqueRefs <-
    case finalTy, refSet of
    | uniqueDecoratedType(_, _), just(inhs)
      when isExportedBy(top.grammarName, [q.lookupValue.dcl.sourceGrammar], top.compiledGrammars) ->
        [(top.frame.fullName ++ ":" ++ q.lookupValue.fullName,
          uniqueRefSite(
            sourceGrammar=top.grammarName,
            sourceLocation=q.location,
            refSet=inhs,
            refFlowDeps=top.flowDeps
          ))]
    | _, _ -> []
    end;
  top.accessUniqueRefs = [];

  top.errors <-
    case finalTy of
    | uniqueDecoratedType(_, _) when q.lookupValue.found ->
      -- Check that we are exported by the decoration site.
      if !isExportedBy(top.grammarName, [q.lookupValue.dcl.sourceGrammar], top.compiledGrammars)
      then [err(top.location, s"Orphaned unique reference to ${q.lookupValue.fullName} in production ${top.frame.fullName} (reference has type ${prettyType(finalTy)}).")]
      -- Check that there is at most one unique reference taken to this decoration site.
      else if length(lookupUniqueRefs(top.frame.fullName, q.lookupValue.fullName, top.flowEnv)) > 1
      then [err(top.location, s"Multiple unique references taken to ${q.name} in production ${top.frame.fullName} (reference has type ${prettyType(finalTy)}).")]
      else []
    | _ -> []
    end;
}
aspect production localReference
top::Expr ::= q::Decorated! QName
{
  local finalTy::Type = performSubstitution(top.typerep, top.finalSubst);
  top.uniqueRefs <-
    case finalTy, refSet of
    | uniqueDecoratedType(_, _), just(inhs)
      when isExportedBy(top.grammarName, [q.lookupValue.dcl.sourceGrammar], top.compiledGrammars) ->
        [(q.lookupValue.fullName,
          uniqueRefSite(
            sourceGrammar=top.grammarName,
            sourceLocation=q.location,
            refSet=inhs,
            refFlowDeps=top.flowDeps
          ))]
    | _, _ -> []
    end;
  top.accessUniqueRefs = [];

  top.errors <-
    case finalTy of
    | uniqueDecoratedType(_, _) when q.lookupValue.found ->
      -- Check that we are exported by the decoration site.
      if !isExportedBy(top.grammarName, [q.lookupValue.dcl.sourceGrammar], top.compiledGrammars)
      then [err(top.location, s"Orphaned unique reference to ${q.lookupValue.fullName} in production ${top.frame.fullName} (reference has type ${prettyType(finalTy)}).")]
      -- Check that there is at most one unique reference taken to this decoration site.
      else if length(lookupLocalUniqueRefs(q.lookupValue.fullName, top.flowEnv)) > 1
      then [err(top.location, s"Multiple unique references taken to ${q.name} in production ${top.frame.fullName} (reference has type ${prettyType(finalTy)}).")]
      else []
    | _ -> []
    end;
}
aspect production lhsReference
top::Expr ::= q::Decorated! QName
{
  local finalTy::Type = performSubstitution(top.typerep, top.finalSubst);
  top.errors <-
    case finalTy of
    | uniqueDecoratedType(_, _) ->
      [err(top.location, s"Cannot take a unique reference of type ${prettyType(finalTy)} to ${q.name}.")]
    | _ -> []
    end;
}
aspect production forwardReference
top::Expr ::= q::Decorated! QName
{
  local finalTy::Type = performSubstitution(top.typerep, top.finalSubst);
  top.errors <-
    case finalTy of
    | uniqueDecoratedType(_, _) ->
      [err(top.location, s"Cannot take a unique reference of type ${prettyType(finalTy)} to the forward tree.")]
    | _ -> []
    end;
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
    | nonterminalType(_, _, _) when !top.isNtUniquenessPreserving -> uniqueContextErrors(e.uniqueRefs)
    | _ -> []
    end;
}

aspect production errorAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  top.uniqueRefs := e.accessUniqueRefs;
}
aspect production annoAccessHandler
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
  local finalTy::Type = performSubstitution(top.typerep, top.finalSubst);
  top.uniqueRefs :=
    case finalTy, refSet of
    | uniqueDecoratedType(_, _), just(inhs)
      when isExportedBy(top.grammarName, [q.attrDcl.sourceGrammar], top.compiledGrammars) ->
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
    | _, _ -> []
    end;

  top.errors <-
    case finalTy of
    | uniqueDecoratedType(_, _) when q.found ->
      case e.flowVertexInfo of
      | just(rhsVertexType(sigName)) ->
        -- Check that we are exported by the occurs-on or the production.
        if !isExportedBy(top.grammarName, [q.dcl.sourceGrammar, top.frame.sourceGrammar], top.compiledGrammars)
        then [err(top.location, s"Orphaned unique reference to ${top.unparse} in production ${top.frame.fullName} (reference has type ${prettyType(finalTy)}).")]
        -- Check that there is at most one unique reference taken to this decoration site.
        else (if length(lookupTransUniqueRefs(top.frame.fullName, sigName, q.attrDcl.fullName, top.flowEnv)) > 1
        then [err(top.location, s"Multiple unique references taken to ${top.unparse} in production ${top.frame.fullName} (reference has type ${prettyType(finalTy)}).")]
        -- Check that there isn't also a unique reference taken to e
        else []) ++
        if !null(lookupUniqueRefs(top.frame.fullName, sigName, top.flowEnv))
        then [err(top.location, s"Cannot take a unique reference to ${top.unparse} in production ${top.frame.fullName} (reference has type ${prettyType(finalTy)}) since there is also a unique reference taken to ${e.unparse}.")]
        else []
      | just(localVertexType(fName)) ->
        -- Check that we are exported by the occurs-on or the production.
        if !isExportedBy(top.grammarName, [q.dcl.sourceGrammar, top.frame.sourceGrammar], top.compiledGrammars)
        then [err(top.location, s"Orphaned unique reference to ${top.unparse} in production ${top.frame.fullName} (reference has type ${prettyType(finalTy)}).")]
        -- Check that there is at most one unique reference taken to this decoration site.
        else (if length(lookupLocalTransUniqueRefs(fName, q.attrDcl.fullName, top.flowEnv)) > 1
        then [err(top.location, s"Multiple unique references taken to ${top.unparse} in production ${top.frame.fullName} (reference has type ${prettyType(finalTy)}).")]
        -- Check that there isn't also a unique reference taken to e
        else []) ++
        case lookupLocalUniqueRefs(fName, top.flowEnv) of
        | u :: _ ->
          [err(top.location, s"Cannot take a unique reference to ${top.unparse} in production ${top.frame.fullName} (reference has type ${prettyType(finalTy)}) since there is also a unique reference taken to ${e.unparse} at ${u.sourceGrammar}:${u.sourceLocation.unparse}.")]
        | [] -> []
        end
      | _ -> [err(top.location, s"Cannot take a unique reference (of type ${prettyType(finalTy)}) to ${top.unparse}")]
      end
    | _ -> []
    end;
}
aspect production errorDecoratedAccessHandler
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
  local finalTy::Type = performSubstitution(top.typerep, top.finalSubst);
  top.uniqueRefs <-
    if finalTy.isUniqueDecorated
    then [(q.name, uniqueRefSite(
        refSet=finalTy.inhSetMembers,
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
  local finalTy::Type = performSubstitution(top.typerep, top.finalSubst);
  top.errors <-
    -- This check is needed due to how we handle let binding auto-undecoration in the type system:
    -- unique and regular references can both undecorate and unique references can become regular ones,
    -- but ensure that we don't create a unique reference out of a regular one.
    case finalTy, q.lookupValue.typeScheme.monoType of
    | uniqueDecoratedType(_, _), uniqueDecoratedType(_, _) -> []
    | uniqueDecoratedType(_, _), _ ->
      [err(top.location, s"${q.name} was not bound as a unique reference, but here it is used with type ${prettyType(finalTy)}.")]
    | _, _ -> []
    end;
  
  top.uniqueRefs <- map(
    \ r::(String, UniqueRefSite) ->
      (r.1, uniqueRefSite(
          refSet=r.2.refSet,
          refFlowDeps=top.flowDeps,
          sourceGrammar=top.grammarName,
          sourceLocation=top.location
        )),
    rs);
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
