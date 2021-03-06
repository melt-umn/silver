grammar silver:compiler:extension:rewriting;

-- Environment mapping variables that were defined on the rule RHS to Booleans indicating whether
-- the variable was explicitly (i.e. not implicitly) decorated in the pattern.
-- TODO: Lots of flow errors in this grammar because we are pretending this attribute is in the reference set 
autocopy attribute boundVars::[Pair<String Boolean>] occurs on Expr, Exprs, ExprInhs, ExprInh, AppExprs, AppExpr, AnnoAppExprs, AnnoExpr, AssignExpr, PrimPatterns, PrimPattern;

attribute transform<ASTExpr> occurs on Expr;

synthesized attribute decRuleExprs::[Pair<String Decorated Expr>] occurs on Expr, AssignExpr, PrimPatterns, PrimPattern;

aspect default production
top::Expr ::=
{
  top.transform =
    antiquoteASTExpr(Silver_Expr { silver:rewrite:anyASTExpr($Expr{top}) });
  top.decRuleExprs = []; -- Only needed on things resulting from the translation of caseExpr
}

aspect production lexicalLocalReference
top::Expr ::= q::Decorated QName _ _
{
  -- In regular pattern matching nonterminal values are always effectively decorated, but we are
  -- using the same typing behavior while matching on *undecorated* trees.  So when a variable is
  -- referenced as decorated we must seperately handle the cases of when it was already decorated
  -- or was decorated implicitly (in which case we need to explicitly decorate it here.)  The same
  -- problem exists when dealing with implicit undecoration.
  top.transform =
    case lookup(q.name, top.boundVars) of
    | just(bindingIsDecorated) ->
      -- The variable is bound in the rule
      if finalType(top).isDecorated && !bindingIsDecorated
      then
        -- We want the decorated version, but the bound value is undecorated
        applyASTExpr(
          antiquoteASTExpr(
            Silver_Expr {
              silver:rewrite:anyASTExpr(
                \ e::$TypeExpr{typerepTypeExpr(finalType(top).decoratedType, location=builtin)} ->
                  $Expr{
                    decorateExprWithEmpty(
                      'decorate', Silver_Expr { e }, 'with', '{', '}',
                      location=top.location)})
            }),
          consASTExpr(varASTExpr(q.name), nilASTExpr()),
          nilNamedASTExpr())
      else if finalType(top).isDecorable && bindingIsDecorated
      -- We want the undecorated version, but the bound value is decorated
      then
        applyASTExpr(
          antiquoteASTExpr(
            Silver_Expr {
              silver:rewrite:anyASTExpr(silver:core:new)
            }),
          consASTExpr(varASTExpr(q.name), nilASTExpr()),
          nilNamedASTExpr())
      -- Both (or neither) the bound value/desired type is a decorated nonterminal - just return the value
      else varASTExpr(q.name)
    | nothing() ->
      -- The variable is bound in an enclosing let/match
      -- Explicitly undecorate the variable, if appropriate for the final expected type
      if q.lookupValue.typeScheme.isDecorable && !finalType(top).isDecorated
      then antiquoteASTExpr(Silver_Expr { silver:rewrite:anyASTExpr(silver:core:new($Expr{top})) })
      else antiquoteASTExpr(Silver_Expr { silver:rewrite:anyASTExpr($Expr{top}) })
    end;
}

aspect production childReference
top::Expr ::= q::Decorated QName
{
  top.transform =
    -- Explicitly undecorate the variable, if appropriate for the final expected type
    if q.lookupValue.typeScheme.isDecorable && !finalType(top).isDecorated
    then antiquoteASTExpr(Silver_Expr { silver:rewrite:anyASTExpr(silver:core:new($Expr{top})) })
    else antiquoteASTExpr(Silver_Expr { silver:rewrite:anyASTExpr($Expr{top}) });
}

aspect production lhsReference
top::Expr ::= q::Decorated QName
{
  top.transform =
    -- Explicitly undecorate the variable, if appropriate for the final expected type
    if q.lookupValue.typeScheme.isDecorable && !finalType(top).isDecorated
    then antiquoteASTExpr(Silver_Expr { silver:rewrite:anyASTExpr(silver:core:new($Expr{top})) })
    else antiquoteASTExpr(Silver_Expr { silver:rewrite:anyASTExpr($Expr{top}) });
}

aspect production localReference
top::Expr ::= q::Decorated QName
{
  top.transform =
    -- Explicitly undecorate the variable, if appropriate for the final expected type
    if q.lookupValue.typeScheme.isDecorable && !finalType(top).isDecorated
    then antiquoteASTExpr(Silver_Expr { silver:rewrite:anyASTExpr(silver:core:new($Expr{top})) })
    else antiquoteASTExpr(Silver_Expr { silver:rewrite:anyASTExpr($Expr{top}) });
}

aspect production forwardReference
top::Expr ::= q::Decorated QName
{
  top.transform =
    -- Explicitly undecorate the variable, if appropriate for the final expected type
    if q.lookupValue.typeScheme.isDecorable && !finalType(top).isDecorated
    then antiquoteASTExpr(Silver_Expr { silver:rewrite:anyASTExpr(silver:core:new($Expr{top})) })
    else antiquoteASTExpr(Silver_Expr { silver:rewrite:anyASTExpr($Expr{top}) });
}

aspect production errorApplication
top::Expr ::= e::Decorated Expr es::Decorated AppExprs anns::Decorated AnnoAppExprs
{
  top.transform = applyASTExpr(e.transform, es.transform, anns.transform);
}

aspect production functionInvocation
top::Expr ::= e::Decorated Expr es::Decorated AppExprs anns::Decorated AnnoAppExprs
{
  top.transform =
    case e, es of
    | productionReference(q), _ -> prodCallASTExpr(q.lookupValue.fullName, es.transform, anns.transform)
    
    -- Special cases for efficiency (and workaround for inability to use applyAST on functions with constraints)
    | classMemberReference(q), snocAppExprs(oneAppExprs(presentAppExpr(e1)), _, presentAppExpr(e2))
      when q.name == "silver:core:eq" -> eqeqASTExpr(e1.transform, e2.transform)
    | classMemberReference(q), snocAppExprs(oneAppExprs(presentAppExpr(e1)), _, presentAppExpr(e2))
      when q.name == "silver:core:neq" -> neqASTExpr(e1.transform, e2.transform)
    | classMemberReference(q), snocAppExprs(oneAppExprs(presentAppExpr(e1)), _, presentAppExpr(e2))
      when q.name == "silver:core:lt" -> ltASTExpr(e1.transform, e2.transform)
    | classMemberReference(q), snocAppExprs(oneAppExprs(presentAppExpr(e1)), _, presentAppExpr(e2))
      when q.name == "silver:core:lte" -> lteqASTExpr(e1.transform, e2.transform)
    | classMemberReference(q), snocAppExprs(oneAppExprs(presentAppExpr(e1)), _, presentAppExpr(e2))
      when q.name == "silver:core:gt" -> gtASTExpr(e1.transform, e2.transform)
    | classMemberReference(q), snocAppExprs(oneAppExprs(presentAppExpr(e1)), _, presentAppExpr(e2))
      when q.name == "silver:core:gte" -> gteqASTExpr(e1.transform, e2.transform)
    | classMemberReference(q), snocAppExprs(oneAppExprs(presentAppExpr(e1)), _, presentAppExpr(e2))
      when q.name == "silver:core:append" -> appendASTExpr(e1.transform, e2.transform)
    
    | _, _ -> applyASTExpr(e.transform, es.transform, anns.transform)
    end;
}

aspect production partialApplication
top::Expr ::= e::Decorated Expr es::Decorated AppExprs anns::Decorated AnnoAppExprs
{
  top.transform = applyASTExpr(e.transform, es.transform, anns.transform);
}

aspect production forwardAccess
top::Expr ::= e::Expr '.' 'forward'
{
  -- Flow analysis has no way to track what e is decorated with across reflect/reify,
  -- so if the inh set is unspecialized, assume that it has the reference set.
  local finalTy::Type =
    case finalType(e) of
    | decoratedType(nt, varType(_)) ->
      decoratedType(nt, inhSetType(sort(concat(getInhsForNtRef(nt.typeName, top.flowEnv)))))
    | t -> t
    end;
  top.transform =
    applyASTExpr(
      antiquoteASTExpr(
        Silver_Expr {
          silver:rewrite:anyASTExpr(
            \ e::$TypeExpr{typerepTypeExpr(finalTy, location=builtin)} -> e.forward)
        }),
      consASTExpr(e.transform, nilASTExpr()),
      nilNamedASTExpr());
}

aspect production errorAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.transform =
    applyASTExpr(
      antiquoteASTExpr(
        Silver_Expr {
          silver:rewrite:anyASTExpr(
            \ e::$TypeExpr{typerepTypeExpr(finalType(e), location=builtin)} -> e.$qName{q.name})
        }),
      consASTExpr(e.transform, nilASTExpr()),
      nilNamedASTExpr());
}

aspect production annoAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.transform =
    applyASTExpr(
      antiquoteASTExpr(
        Silver_Expr {
          silver:rewrite:anyASTExpr(
            \ e::$TypeExpr{typerepTypeExpr(finalType(e), location=builtin)} -> e.$qName{q.name})
        }),
      consASTExpr(e.transform, nilASTExpr()),
      nilNamedASTExpr());
}

aspect production terminalAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.transform =
    applyASTExpr(
      antiquoteASTExpr(
        Silver_Expr {
          silver:rewrite:anyASTExpr(
            \ e::$TypeExpr{typerepTypeExpr(finalType(e), location=builtin)} -> e.$qName{q.name})
        }),
      consASTExpr(e.transform, nilASTExpr()),
      nilNamedASTExpr());
}


aspect production synDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  -- Flow analysis has no way to track what e is decorated with across reflect/reify,
  -- so if the inh set is unspecialized, assume that it has the reference set.
  local finalTy::Type =
    case finalType(e) of
    | decoratedType(nt, varType(_)) ->
      decoratedType(nt, inhSetType(sort(concat(getInhsForNtRef(nt.typeName, top.flowEnv)))))
    | t -> t
    end;
  top.transform =
    case e of
    -- Special cases to avoid introducing a reference and causing flow errors.
    | decorateExprWith(_, eUndec, _, _, inh, _) ->
      applyASTExpr(
        antiquoteASTExpr(
          Silver_Expr {
            silver:rewrite:anyASTExpr(
              $Expr{
                lambdap(
                  productionRHSCons(
                    productionRHSElem(
                      name("_e", builtin), '::',
                      typerepTypeExpr(finalType(eUndec), location=builtin),
                      location=builtin),
                    inh.lambdaParams,
                    location=builtin),
                  Silver_Expr {
                    $Expr{
                      decorateExprWith(
                        'decorate', baseExpr(qName(builtin, "_e"), location=builtin),
                        'with', '{', inh.bodyExprInhTransform, '}',
                        location=builtin)}.$qName{q.name}
                  },
                  location=builtin)})
          }),
        consASTExpr(eUndec.transform, inh.transform),
        nilNamedASTExpr())
    | lexicalLocalReference(qn, _, _) when
        case lookup(qn.name, top.boundVars) of
        | just(bindingIsDecorated) -> !bindingIsDecorated
        | nothing() -> false
        end -> 
      applyASTExpr(
        antiquoteASTExpr(
          Silver_Expr {
            silver:rewrite:anyASTExpr(
              \ e::$TypeExpr{typerepTypeExpr(finalType(e).decoratedType, location=builtin)} -> e.$qName{q.name})
          }),
        consASTExpr(varASTExpr(qn.name), nilASTExpr()),
        nilNamedASTExpr())
    | _ ->
      applyASTExpr(
        antiquoteASTExpr(
          Silver_Expr {
            silver:rewrite:anyASTExpr(
              \ e::$TypeExpr{typerepTypeExpr(finalTy, location=builtin)} -> e.$qName{q.name})
          }),
        consASTExpr(e.transform, nilASTExpr()),
        nilNamedASTExpr())
    end;
}

aspect production inhDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  -- Flow analysis has no way to track what e is decorated with across reflect/reify,
  -- so if the inh set is unspecialized, assume that it has the reference set.
  local finalTy::Type =
    case finalType(e) of
    | decoratedType(nt, varType(_)) ->
      decoratedType(nt, inhSetType(sort(concat(getInhsForNtRef(nt.typeName, top.flowEnv)))))
    | t -> t
    end;
  top.transform =
    applyASTExpr(
      antiquoteASTExpr(
        Silver_Expr {
          silver:rewrite:anyASTExpr(
            \ e::$TypeExpr{typerepTypeExpr(finalTy, location=builtin)} -> e.$qName{q.name})
        }),
      consASTExpr(e.transform, nilASTExpr()),
      nilNamedASTExpr());
}

aspect production errorDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  -- Flow analysis has no way to track what e is decorated with across reflect/reify,
  -- so if the inh set is unspecialized, assume that it has the reference set.
  local finalTy::Type =
    case finalType(e) of
    | decoratedType(nt, varType(_)) ->
      decoratedType(nt, inhSetType(sort(concat(getInhsForNtRef(nt.typeName, top.flowEnv)))))
    | t -> t
    end;
  top.transform =
    applyASTExpr(
      antiquoteASTExpr(
        Silver_Expr {
          silver:rewrite:anyASTExpr(
            \ e::$TypeExpr{typerepTypeExpr(finalTy, location=builtin)} -> e.$qName{q.name})
        }),
      consASTExpr(e.transform, nilASTExpr()),
      nilNamedASTExpr());
}

aspect production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
  top.transform =
    applyASTExpr(
      antiquoteASTExpr(
        Silver_Expr {
          silver:rewrite:anyASTExpr(
            $Expr{
              lambdap(
                productionRHSCons(
                  productionRHSElem(
                    name("_e", builtin), '::',
                    typerepTypeExpr(finalType(e), location=builtin),
                    location=builtin),
                  inh.lambdaParams,
                  location=builtin),
                decorateExprWith(
                  'decorate', baseExpr(qName(builtin, "_e"), location=builtin),
                  'with', '{', inh.bodyExprInhTransform, '}',
                  location=builtin),
                location=builtin)})
        }),
      consASTExpr(e.transform, inh.transform),
      nilNamedASTExpr());
}

attribute transform<ASTExprs> occurs on ExprInhs;
synthesized attribute lambdaParams::ProductionRHS occurs on ExprInhs;
functor attribute bodyExprInhTransform occurs on ExprInhs, ExprInh;
propagate bodyExprInhTransform on ExprInhs;

aspect production exprInhsEmpty
top::ExprInhs ::= 
{
  top.transform = nilASTExpr();
  top.lambdaParams = productionRHSNil(location=builtin);
}

aspect production exprInhsOne
top::ExprInhs ::= lhs::ExprInh
{
  top.transform = consASTExpr(lhs.transform, nilASTExpr());
  top.lambdaParams =
    productionRHSCons(lhs.lambdaParam, productionRHSNil(location=builtin), location=builtin);
}

aspect production exprInhsCons
top::ExprInhs ::= lhs::ExprInh inh::ExprInhs
{
  top.transform = consASTExpr(lhs.transform, inh.transform);
  top.lambdaParams = productionRHSCons(lhs.lambdaParam, inh.lambdaParams, location=builtin);
}

attribute transform<ASTExpr> occurs on ExprInh;
synthesized attribute lambdaParam::ProductionRHSElem occurs on ExprInh;

aspect production exprInh
top::ExprInh ::= lhs::ExprLHSExpr '=' e::Expr ';'
{
  top.transform = e.transform;
  
  local paramName::String = implode("_", explode(":", lhs.name));
  top.lambdaParam =
    productionRHSElem(
      name(paramName, builtin), '::',
      typerepTypeExpr(finalType(e), location=builtin),
      location=builtin);
  top.bodyExprInhTransform =
    exprInh(
      lhs, '=', baseExpr(qName(builtin, paramName), location=builtin), ';',
      location=builtin);
}

aspect production trueConst
top::Expr ::= 'true'
{
  top.transform = booleanASTExpr(true);
}

aspect production falseConst
top::Expr ::= 'false'
{
  top.transform = booleanASTExpr(false);
}

aspect production and
top::Expr ::= e1::Expr '&&' e2::Expr
{
  top.transform = andASTExpr(e1.transform, e2.transform);
}

aspect production or
top::Expr ::= e1::Expr '||' e2::Expr
{
  top.transform = orASTExpr(e1.transform, e2.transform);
}

aspect production not
top::Expr ::= '!' e::Expr
{
  top.transform = notASTExpr(e.transform);
}

aspect production intConst
top::Expr ::= i::Int_t
{
  top.transform = integerASTExpr(toInteger(i.lexeme));
}

aspect production floatConst
top::Expr ::= f::Float_t
{
  top.transform = floatASTExpr(toFloat(f.lexeme));
} 

aspect production noteAttachment
top::Expr ::= 'attachNote' note::Expr 'on' e::Expr 'end'
{
  top.transform = noteAttachmentASTExpr(note.transform, e.transform);
}

aspect production plus
top::Expr ::= e1::Expr '+' e2::Expr
{
  top.transform = plusASTExpr(e1.transform, e2.transform);
}

aspect production minus
top::Expr ::= e1::Expr '-' e2::Expr
{
  top.transform = minusASTExpr(e1.transform, e2.transform);
}

aspect production multiply
top::Expr ::= e1::Expr '*' e2::Expr
{
  top.transform = multiplyASTExpr(e1.transform, e2.transform);
}

aspect production divide
top::Expr ::= e1::Expr '/' e2::Expr
{
  top.transform = divideASTExpr(e1.transform, e2.transform);
}

aspect production modulus
top::Expr ::= e1::Expr '%' e2::Expr
{
  top.transform = modulusASTExpr(e1.transform, e2.transform);
}

aspect production neg
top::Expr ::= '-' e::Expr
{
  top.transform = negASTExpr(e.transform);
}

aspect production stringConst
top::Expr ::= s::String_t
{
  top.transform = stringASTExpr(unescapeString(substring(1, length(s.lexeme) - 1, s.lexeme)));
}

aspect production errorLength
top::Expr ::= e::Decorated Expr
{
  top.transform = lengthASTExpr(e.transform);
}

aspect production stringLength
top::Expr ::= e::Decorated Expr
{
  top.transform = lengthASTExpr(e.transform);
}

aspect production toIntegerFunction
top::Expr ::= 'toInteger' '(' e::Expr ')'
{
  top.transform = toIntegerASTExpr(e.transform);
}

aspect production toBooleanFunction
top::Expr ::= 'toBoolean' '(' e::Expr ')'
{
  top.transform = toBooleanASTExpr(e.transform);
}

aspect production toFloatFunction
top::Expr ::= 'toFloat' '(' e::Expr ')'
{
  top.transform = toFloatASTExpr(e.transform);
}

aspect production toStringFunction
top::Expr ::= 'toString' '(' e::Expr ')'
{
  top.transform = toStringASTExpr(e.transform);
}

aspect production terminalConstructor
top::Expr ::= 'terminal' '(' t::TypeExpr ',' es::Expr ',' el::Expr ')'
{
  top.transform = terminalASTExpr(t.typerep.typeName, es.transform, el.transform);
}

aspect production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
{
  top.transform = ifThenElseASTExpr(e1.transform, e2.transform, e3.transform);
  top.decRuleExprs = e1.decRuleExprs ++ e2.decRuleExprs ++ e3.decRuleExprs;
}

-- Extensions
aspect production emptyList
top::Expr ::= '[' ']'
{
  top.transform = nilListASTExpr();
}

aspect production consListOp
top::Expr ::= h::Expr '::' t::Expr
{
  top.transform = consListASTExpr(h.transform, t.transform);
}

aspect production fullList
top::Expr ::= '[' es::Exprs ']'
{ 
  top.transform = listASTExpr(es.transform);
}

aspect production listPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  top.transform = appendASTExpr(e1.transform, e2.transform);
}

aspect production listLengthBouncer
top::Expr ::= e::Decorated Expr
{
  top.transform = lengthASTExpr(e.transform);
}

-- TODO: Awful hack to allow case to appear on rule RHS.
-- This is interfering (should really be defined on primitive match)
-- and only supports variables from the rule LHS appearing in the match expressions.
aspect production caseExpr_c
top::Expr ::= 'case' es::Exprs 'of' o::Opt_Vbar_t ml::MRuleList 'end'
{
  local decEs::Exprs = es;
  decEs.downSubst = top.downSubst;
  decEs.finalSubst = top.finalSubst;
  decEs.frame = top.frame;
  decEs.config = top.config;
  decEs.compiledGrammars = top.compiledGrammars;
  decEs.grammarName = top.grammarName;
  decEs.env = top.env;
  decEs.flowEnv = top.flowEnv;
  decEs.boundVars = top.boundVars;
  
  top.transform =
    applyASTExpr(
      antiquoteASTExpr(
        Silver_Expr {
          silver:rewrite:anyASTExpr(
            $Expr{
              lambdap(
                decEs.lambdaParams,
                caseExpr_c(
                  'case', decEs.lambdaParamRefs, 'of',
                  o, ml, 'end',
                  location=builtin),
                location=builtin)})
        }),
      decEs.transform,
      nilNamedASTExpr());
}

-- Modifications
aspect production letp
top::Expr ::= la::AssignExpr e::Expr
{
  top.transform = letASTExpr(la.transform, e.transform);
  top.decRuleExprs = la.decRuleExprs ++ e.decRuleExprs;
  
  e.boundVars = top.boundVars ++ la.varBindings;
}

attribute transform<NamedASTExprs> occurs on AssignExpr;
attribute varBindings occurs on AssignExpr;

aspect production appendAssignExpr
top::AssignExpr ::= a1::AssignExpr a2::AssignExpr
{
  top.transform = appendNamedASTExprs(a1.transform, a2.transform);
  top.varBindings = a1.varBindings ++ a2.varBindings;
  top.decRuleExprs = a1.decRuleExprs ++ a2.decRuleExprs;
}

aspect production assignExpr
top::AssignExpr ::= id::Name '::' t::TypeExpr '=' e::Expr
{
  top.transform =
    consNamedASTExpr(namedASTExpr(id.name, e.transform), nilNamedASTExpr());
  
  -- If this is a generated pattern variable binding, figure out whether the corresponding
  -- primitive pattern variable was implictly decorated.
  local isDecorated::Boolean =
    case e of
    | lexicalLocalReference(qn, _, _) ->
      fromMaybe(finalType(e).isDecorated, lookup(qn.name, top.boundVars))
    | _ -> finalType(e).isDecorated
    end;
  top.varBindings = [pair(id.name, isDecorated)];
  top.decRuleExprs = e.decRuleExprs;
}

aspect production matchPrimitiveReal
top::Expr ::= e::Expr t::TypeExpr pr::PrimPatterns f::Expr
{
  top.decRuleExprs = e.decRuleExprs ++ pr.decRuleExprs ++ f.decRuleExprs;
}

-- TODO: Support for lambdas capturing rule LHS variables

-- Expr "collection" productions
attribute transform<ASTExprs> occurs on Exprs;
attribute lambdaParams occurs on Exprs;
synthesized attribute lambdaParamRefs::Exprs occurs on Exprs;

aspect production exprsEmpty
top::Exprs ::=
{
  top.transform = nilASTExpr();
  top.lambdaParams = productionRHSNil(location=builtin);
  top.lambdaParamRefs = exprsEmpty(location=builtin);
}
aspect production exprsSingle
top::Exprs ::= e::Expr
{
  top.transform = consASTExpr(e.transform, nilASTExpr());
  
  local lambdaParamName::String = "__exprs_param_" ++ toString(genInt());
  top.lambdaParams =
    productionRHSCons(
      productionRHSElem(
        name(lambdaParamName, builtin), '::',
        typerepTypeExpr(finalType(e), location=builtin),
        location=builtin),
      productionRHSNil(location=builtin),
      location=builtin);
  top.lambdaParamRefs =
    exprsSingle(
      baseExpr(qName(builtin,lambdaParamName), location=builtin),
      location=builtin);
}
aspect production exprsCons
top::Exprs ::= e1::Expr ',' e2::Exprs
{
  top.transform = consASTExpr(e1.transform, e2.transform);
  
  local lambdaParamName::String = "__exprs_param_" ++ toString(genInt());
  top.lambdaParams =
    productionRHSCons(
      productionRHSElem(
        name(lambdaParamName, builtin), '::',
        typerepTypeExpr(finalType(e1), location=builtin),
        location=builtin),
      e2.lambdaParams,
      location=builtin);
  top.lambdaParamRefs =
    exprsCons(
      baseExpr(qName(builtin, lambdaParamName), location=builtin),
      ',', e2.lambdaParamRefs,
      location=builtin);
}

attribute transform<ASTExpr> occurs on AppExpr;

aspect production missingAppExpr
top::AppExpr ::= '_'
{
  top.transform = missingArgASTExpr();
}
aspect production presentAppExpr
top::AppExpr ::= e::Expr
{
  top.transform = e.transform;
}

attribute transform<ASTExprs> occurs on AppExprs;

aspect production snocAppExprs
top::AppExprs ::= es::AppExprs ',' e::AppExpr
{
  -- Inefficient, ugh.
  top.transform = appendASTExprs(es.transform, consASTExpr(e.transform, nilASTExpr()));
}
aspect production oneAppExprs
top::AppExprs ::= e::AppExpr
{
  top.transform = consASTExpr(e.transform, nilASTExpr());
}
aspect production emptyAppExprs
top::AppExprs ::=
{
  top.transform = nilASTExpr();
}

attribute transform<NamedASTExpr> occurs on AnnoExpr;

aspect production annoExpr
top::AnnoExpr ::= qn::QName '=' e::AppExpr
{
  top.transform = namedASTExpr(qn.lookupAttribute.fullName, e.transform);
}

attribute transform<NamedASTExprs> occurs on AnnoAppExprs;

aspect production snocAnnoAppExprs
top::AnnoAppExprs ::= es::AnnoAppExprs ',' e::AnnoExpr
{
  -- Inefficient, ugh.
  top.transform = appendNamedASTExprs(es.transform, consNamedASTExpr(e.transform, nilNamedASTExpr()));
}

aspect production oneAnnoAppExprs
top::AnnoAppExprs ::= e::AnnoExpr
{
  top.transform = consNamedASTExpr(e.transform, nilNamedASTExpr());
}

aspect production emptyAnnoAppExprs
top::AnnoAppExprs ::=
{
  top.transform = nilNamedASTExpr();
}

aspect production exprRef
top::Expr ::= e::Decorated Expr
{
  top.transform = e.transform;
}