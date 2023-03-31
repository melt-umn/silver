grammar silver:compiler:analysis:uniqueness;

attribute exprDecSite occurs on Expr, AppExprs, AppExpr;
propagate exprDecSite on AppExprs;

attribute uniqueRefs occurs on Expr, Exprs, AppExprs, AppExpr, PrimPatterns, PrimPattern;
propagate uniqueRefs on Expr, Exprs, AppExprs, AppExpr, PrimPatterns, PrimPattern
  excluding
    errorAccessHandler, annoAccessHandler, terminalAccessHandler, synDecoratedAccessHandler, inhDecoratedAccessHandler, errorDecoratedAccessHandler,
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
  local refSiteName::String = top.frame.fullName ++ ":" ++ q.lookupValue.fullName;
  top.uniqueRefs <-
    case finalTy, refSet of
    | uniqueDecoratedType(_, _), just(inhs)
      when isExportedBy(top.grammarName, [q.lookupValue.dcl.sourceGrammar], top.compiledGrammars) ->
      [(refSiteName, uniqueRefSite(sourceGrammar=top.grammarName, sourceLocation=q.location, refSet=inhs, decSite=top.exprDecSite))]
    | _, _ -> []
    end;
  top.accessUniqueRefs = [];

  local allUniqueRefs::[UniqueRefSite] = getUniqueRefs(refSiteName, top.flowEnv);
  top.errors <-
    case finalTy of
    | uniqueDecoratedType(_, _) when q.lookupValue.found ->
      -- Check that we are exported by the decoration site.
      if q.lookupValue.found
      && !isExportedBy(top.grammarName, [q.lookupValue.dcl.sourceGrammar], top.compiledGrammars)
      then [err(top.location, s"Orphaned unique reference to ${q.lookupValue.fullName} in production ${top.frame.fullName} (reference has type ${prettyType(finalTy)}).")]
      -- Check that there is at most one partial reference taken to this decoration site.
      else if length(allUniqueRefs) > 1
      then [err(top.location, s"Multiple unique references taken to ${q.name} in production ${top.frame.fullName} (reference has type ${prettyType(finalTy)}).")]
      else []
    | _ -> []
    end;
}
aspect production localReference
top::Expr ::= q::Decorated! QName
{
  local finalTy::Type = performSubstitution(top.typerep, top.finalSubst);
  local refSiteName::String = q.lookupValue.fullName;
  top.uniqueRefs <-
    case finalTy, refSet of
    | uniqueDecoratedType(_, _), just(inhs)
      when isExportedBy(top.grammarName, [q.lookupValue.dcl.sourceGrammar], top.compiledGrammars) ->
      [(refSiteName, uniqueRefSite(sourceGrammar=top.grammarName, sourceLocation=q.location, refSet=inhs, decSite=top.exprDecSite))]
    | _, _ -> []
    end;
  top.accessUniqueRefs = [];

  local allUniqueRefs::[UniqueRefSite] = getUniqueRefs(refSiteName, top.flowEnv);
  top.errors <-
    case finalTy of
    | uniqueDecoratedType(_, _) when q.lookupValue.found ->
      -- Check that we are exported by the decoration site.
      if q.lookupValue.found
      && !isExportedBy(top.grammarName, [q.lookupValue.dcl.sourceGrammar], top.compiledGrammars)
      then [err(top.location, s"Orphaned unique reference to ${q.lookupValue.fullName} in production ${top.frame.fullName} (reference has type ${prettyType(finalTy)}).")]
      -- Check that there is at most one partial reference taken to this decoration site.
      else if length(allUniqueRefs) > 1
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

-- The named signature of the applied production.
-- Note that we don't project functions at the moment, since we don't build function flow graphs during inference.
inherited attribute appProd::Maybe<NamedSignature> occurs on AppExprs, AppExpr;
propagate appProd on AppExprs;

-- Whether nonterminal uniqueness is preserved for this argument position,
-- i.e. this is an argument to a direct function or production application
-- that will be copied upon undecoration.
inherited attribute isNtUniquenessPreserving::Boolean occurs on AppExprs, AppExpr;
propagate isNtUniquenessPreserving on AppExprs;

monoid attribute appExprUniquenessErrors::[Message] occurs on AppExprs, AppExpr, AnnoAppExprs, AnnoExpr;
propagate appExprUniquenessErrors on AppExprs, AppExpr, AnnoAppExprs, AnnoExpr;

aspect production errorApplication
top::Expr ::= e::Decorated! Expr es::Decorated! AppExprs anns::Decorated! AnnoAppExprs
{
  e.exprDecSite = nothing();
  es.exprDecSite = nothing();
  es.appProd = nothing();
}
aspect production functionInvocation
top::Expr ::= e::Decorated! Expr es::Decorated! AppExprs anns::Decorated! AnnoAppExprs
{
  e.exprDecSite = nothing();
  es.exprDecSite = top.exprDecSite;
  top.errors <- es.appExprUniquenessErrors ++ anns.appExprUniquenessErrors;
  es.appProd =
    case e of
    | productionReference(q) -> just(q.lookupValue.dcl.namedSignature)
    | _ -> nothing()
    end;
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
  e.exprDecSite = nothing();
  es.exprDecSite = nothing();
  top.errors <- es.appExprUniquenessErrors ++ anns.appExprUniquenessErrors;
  es.appProd =
    case e of
    | productionReference(q) -> just(q.lookupValue.dcl.namedSignature)
    | _ -> nothing()
    end;
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
  e.exprDecSite = nothing();
  e.appProd = nothing();
  e.isNtUniquenessPreserving = false;
}

aspect production presentAppExpr
top::AppExpr ::= e::Expr
{
  production sigName::String =
    case top.appProd of
    | just(ns) when top.appExprIndex < length(ns.inputNames) -> head(drop(top.appExprIndex, ns.inputNames))
    | _ -> "err"
    end;
  e.exprDecSite = do {
    parent::ExprDecSite <- top.exprDecSite;
    ns::NamedSignature <- top.appProd;
    if top.appExprIndex < length(ns.inputNames)
      then just(subtermDecSite(parent, ns.fullName, sigName))
      else nothing();
  };
  top.appExprUniquenessErrors <-
    case top.appExprTyperep.baseType of
    | varType(_) -> uniqueContextErrors(e.uniqueRefs)  -- Would need linear types to make this work...
    | nonterminalType(_, _, _) when !top.isNtUniquenessPreserving -> uniqueContextErrors(e.uniqueRefs)
    | _ -> []
    end;
}

aspect production noteAttachment
top::Expr ::= 'attachNote' note::Expr 'on' e::Expr 'end'
{
  note.exprDecSite = nothing();
  e.exprDecSite = nothing();
}

aspect production forwardAccess
top::Expr ::= e::Expr '.' 'forward'
{
  e.exprDecSite = nothing();
}
aspect production errorAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  e.exprDecSite = nothing();
  top.uniqueRefs := e.accessUniqueRefs;
}
aspect production annoAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  e.exprDecSite = nothing();
  top.uniqueRefs := e.accessUniqueRefs;
}
aspect production terminalAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  e.exprDecSite = nothing();
  top.uniqueRefs := e.accessUniqueRefs;
}
aspect production synDecoratedAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  e.exprDecSite = nothing();
  top.uniqueRefs := e.accessUniqueRefs;
}
aspect production inhDecoratedAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  e.exprDecSite = nothing();
  top.uniqueRefs := e.accessUniqueRefs;
}
aspect production errorDecoratedAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  e.exprDecSite = nothing();
  top.uniqueRefs := e.accessUniqueRefs;
}

aspect production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
  e.exprDecSite = just(anonDecSite(inh.decorationVertex));
}
aspect production exprInh
top::ExprInh ::= lhs::ExprLHSExpr '=' e::Expr ';'
{
  e.exprDecSite = nothing();
}

aspect production decorationSiteExpr
top::Expr ::= '@' e::Expr
{
  e.exprDecSite = top.exprDecSite;
}

aspect production and
top::Expr ::= e1::Expr '&&' e2::Expr
{
  e1.exprDecSite = nothing();
  e2.exprDecSite = nothing();
}
aspect production or
top::Expr ::= e1::Expr '||' e2::Expr
{
  e1.exprDecSite = nothing();
  e2.exprDecSite = nothing();
}
aspect production notOp
top::Expr ::= '!' e::Expr
{
  e.exprDecSite = nothing();
}

aspect production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
{
  e1.exprDecSite = nothing();
  e2.exprDecSite = nothing();
  e3.exprDecSite = nothing();
  top.uniqueRefs :=
    e1.uniqueRefs ++
    unionMutuallyExclusiveRefs(e1.uniqueRefs, e2.uniqueRefs);
}

aspect production plus
top::Expr ::= e1::Expr '+' e2::Expr
{
  e1.exprDecSite = nothing();
  e2.exprDecSite = nothing();
}
aspect production minus
top::Expr ::= e1::Expr '-' e2::Expr
{
  e1.exprDecSite = nothing();
  e2.exprDecSite = nothing();
}
aspect production multiply
top::Expr ::= e1::Expr '*' e2::Expr
{
  e1.exprDecSite = nothing();
  e2.exprDecSite = nothing();
}
aspect production divide
top::Expr ::= e1::Expr _ e2::Expr
{
  e1.exprDecSite = nothing();
  e2.exprDecSite = nothing();
}
aspect production modulus
top::Expr ::= e1::Expr '%' e2::Expr
{
  e1.exprDecSite = nothing();
  e2.exprDecSite = nothing();
}
aspect production neg
top::Expr ::= '-' e::Expr
{
  e.exprDecSite = nothing();
}

aspect production terminalConstructor
top::Expr ::= 'terminal' '(' t::TypeExpr ',' es::Expr ',' el::Expr ')'
{
  es.exprDecSite = nothing();
  el.exprDecSite = nothing();
}

aspect production exprsSingle
top::Exprs ::= e::Expr
{
  e.exprDecSite = nothing();
}
aspect production exprsCons
top::Exprs ::= e1::Expr ',' e2::Exprs
{
  e1.exprDecSite = nothing();
}

aspect production lambdaParamReference
top::Expr ::= q::Decorated! QName
{
  local finalTy::Type = performSubstitution(top.typerep, top.finalSubst);
  top.uniqueRefs <-
    if finalTy.isUniqueDecorated
    then [(q.name, uniqueRefSite(refSet=finalTy.inhSetMembers, decSite=nothing(), sourceGrammar=top.grammarName, sourceLocation=top.location))]
    else [];
  top.accessUniqueRefs = [];
}

aspect production lambdap
top::Expr ::= params::ProductionRHS e::Expr
{
  e.exprDecSite = nothing();
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
top::Expr ::= q::Decorated! QName  fi::ExprVertexInfo  fd::[FlowVertex]  rs::[(String, UniqueRefSite)]
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
      (r.1, uniqueRefSite(refSet=r.2.refSet, decSite=top.exprDecSite, sourceGrammar=top.grammarName, sourceLocation=top.location)),
    rs);
  top.accessUniqueRefs = [];
}

aspect production letp
top::Expr ::= la::AssignExpr  e::Expr
{
  e.exprDecSite = top.exprDecSite;
  -- Excluding refs from la, they flow up through the lexicalLocalReferences in e
  top.uniqueRefs := e.uniqueRefs;
}

aspect production assignExpr
top::AssignExpr ::= id::Name '::' t::TypeExpr '=' e::Expr
{
  e.exprDecSite = nothing();
}

aspect production matchPrimitiveReal
top::Expr ::= e::Expr t::TypeExpr pr::PrimPatterns f::Expr
{
  e.exprDecSite = nothing();
  f.exprDecSite = nothing();
  top.uniqueRefs := e.uniqueRefs ++ unionMutuallyExclusiveRefs(pr.uniqueRefs, f.uniqueRefs);
  top.errors <- uniqueContextErrors(e.uniqueRefs);
}
aspect production consPattern
top::PrimPatterns ::= p::PrimPattern _ ps::PrimPatterns
{
  top.uniqueRefs := unionMutuallyExclusiveRefs(p.uniqueRefs, ps.uniqueRefs);
}
aspect production prodPatternNormal
top::PrimPattern ::= qn::Decorated QName  ns::VarBinders  e::Expr
{
  e.exprDecSite = nothing();
}
aspect production prodPatternGadt
top::PrimPattern ::= qn::Decorated QName  ns::VarBinders  e::Expr
{
  e.exprDecSite = nothing();
}
aspect production integerPattern
top::PrimPattern ::= i::Int_t _ e::Expr
{
  e.exprDecSite = nothing();
}
aspect production floatPattern
top::PrimPattern ::= f::Float_t _ e::Expr
{
  e.exprDecSite = nothing();
}
aspect production stringPattern
top::PrimPattern ::= i::String_t _ e::Expr
{
  e.exprDecSite = nothing();
}
aspect production booleanPattern
top::PrimPattern ::= i::String _ e::Expr
{
  e.exprDecSite = nothing();
}
aspect production nilPattern
top::PrimPattern ::= e::Expr
{
  e.exprDecSite = nothing();
}
aspect production conslstPattern
top::PrimPattern ::= h::Name t::Name e::Expr
{
  e.exprDecSite = nothing();
}
