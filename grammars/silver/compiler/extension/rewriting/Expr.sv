grammar silver:compiler:extension:rewriting;

-- Environment mapping variables that were defined on the rule LHS to Booleans indicating whether
-- the variable was explicitly (i.e. not implicitly) decorated in the pattern.
inherited attribute boundVars::[Pair<String Boolean>] occurs on Expr, Exprs, ExprInhs, ExprInh, AppExprs, AppExpr, AnnoAppExprs, AnnoExpr, AssignExpr, PrimPatterns, PrimPattern;
propagate boundVars on Expr, Exprs, ExprInhs, ExprInh, AppExprs, AppExpr, AnnoAppExprs, AnnoExpr, AssignExpr, PrimPatterns, PrimPattern
  excluding letp, prodPatternNormal, prodPatternGadt;

attribute transform<ASTExpr> occurs on Expr;

synthesized attribute decRuleExprs::[(String, Decorated Expr with {decorate, decSiteVertexInfo, boundVars})] occurs on Expr, AssignExpr, PrimPatterns, PrimPattern;

aspect default production
top::Expr ::=
{
  top.transform =
    antiquoteASTExpr(Silver_Expr {
      -- Constrain the type of the wrapped expression to the type that was inferred here,
      -- to allow for any type class constraints to be resolved in the translation.
      silver:rewrite:anyASTExpr(
        let rewrite_rule_anyAST_val__::$TypeExpr{typerepTypeExpr(top.finalType)} = $Expr{top}
        in rewrite_rule_anyAST_val__
        end)
    });
  top.decRuleExprs = []; -- Only needed on things resulting from the translation of caseExpr
}

aspect production lexicalLocalReference
top::Expr ::= q::Decorated! QName _ _ _
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
      if top.finalType.isDecorated && !bindingIsDecorated
      then
        -- We want the decorated version, but the bound value is undecorated
        applyASTExpr(
          antiquoteASTExpr(
            Silver_Expr {
              silver:rewrite:anyASTExpr(
                \ e::$TypeExpr{typerepTypeExpr(top.finalType.decoratedType)} ->
                  $Expr{
                    decorateExprWithEmpty(
                      'decorate', Silver_Expr { e }, 'with', '{', '}')})
            }),
          consASTExpr(varASTExpr(q.name), nilASTExpr()),
          nilNamedASTExpr())
      else if isDecorable(top.finalType, top.env) && bindingIsDecorated
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
      if isDecorable(q.lookupValue.typeScheme.typerep, top.env) && !top.finalType.isDecorated
      then antiquoteASTExpr(Silver_Expr { silver:rewrite:anyASTExpr(silver:core:new($Expr{top})) })
      else antiquoteASTExpr(Silver_Expr { silver:rewrite:anyASTExpr($Expr{top}) })
    end;
}

aspect production childReference
top::Expr ::= q::Decorated! QName
{
  top.transform =
    -- Explicitly undecorate the variable, if appropriate for the final expected type
    if isDecorable(q.lookupValue.typeScheme.typerep, top.env) && !top.finalType.isDecorated
    then antiquoteASTExpr(Silver_Expr { silver:rewrite:anyASTExpr(silver:core:new($Expr{top})) })
    else antiquoteASTExpr(Silver_Expr { silver:rewrite:anyASTExpr($Expr{top}) });
}

aspect production lhsReference
top::Expr ::= q::Decorated! QName
{
  top.transform =
    -- Explicitly undecorate the variable, if appropriate for the final expected type
    if isDecorable(q.lookupValue.typeScheme.typerep, top.env) && !top.finalType.isDecorated
    then antiquoteASTExpr(Silver_Expr { silver:rewrite:anyASTExpr(silver:core:new($Expr{top})) })
    else antiquoteASTExpr(Silver_Expr { silver:rewrite:anyASTExpr($Expr{top}) });
}

aspect production localReference
top::Expr ::= q::Decorated! QName
{
  top.transform =
    -- Explicitly undecorate the variable, if appropriate for the final expected type
    if isDecorable(q.lookupValue.typeScheme.typerep, top.env) && !top.finalType.isDecorated
    then antiquoteASTExpr(Silver_Expr { silver:rewrite:anyASTExpr(silver:core:new($Expr{top})) })
    else antiquoteASTExpr(Silver_Expr { silver:rewrite:anyASTExpr($Expr{top}) });
}

aspect production forwardReference
top::Expr ::= q::Decorated! QName
{
  top.transform =
    -- Explicitly undecorate the variable, if appropriate for the final expected type
    if isDecorable(q.lookupValue.typeScheme.typerep, top.env) && !top.finalType.isDecorated
    then antiquoteASTExpr(Silver_Expr { silver:rewrite:anyASTExpr(silver:core:new($Expr{top})) })
    else antiquoteASTExpr(Silver_Expr { silver:rewrite:anyASTExpr($Expr{top}) });
}

aspect production errorApplication
top::Expr ::= e::Decorated! Expr es::Decorated! AppExprs anns::Decorated! AnnoAppExprs
{
  top.transform = applyASTExpr(e.transform, es.transform, anns.transform);
}

aspect production functionInvocation
top::Expr ::= e::Decorated! Expr es::Decorated! AppExprs anns::Decorated! AnnoAppExprs
{
  top.transform =
    case e, es of
    | productionReference(q), _ -> prodCallASTExpr(q.lookupValue.fullName, es.transform, anns.transform)

    -- Special cases for efficiency.
    | classMemberReference(q), snocAppExprs(oneAppExprs(presentAppExpr(e1)), _, presentAppExpr(e2)) 
        when e1.finalType.isPrimitive ->
      case q.lookupValue.fullName of
      | "silver:core:conj" -> andASTExpr(e1.transform, e2.transform)
      | "silver:core:disj" -> orASTExpr(e1.transform, e2.transform)
      | "silver:core:eq" -> eqeqASTExpr(e1.transform, e2.transform)
      | "silver:core:neq" -> neqASTExpr(e1.transform, e2.transform)
      | "silver:core:lt" -> ltASTExpr(e1.transform, e2.transform)
      | "silver:core:lte" -> lteqASTExpr(e1.transform, e2.transform)
      | "silver:core:gt" -> gtASTExpr(e1.transform, e2.transform)
      | "silver:core:gte" -> gteqASTExpr(e1.transform, e2.transform)
      | "silver:core:add" -> plusASTExpr(e1.transform, e2.transform)
      | "silver:core:sub" -> minusASTExpr(e1.transform, e2.transform)
      | "silver:core:mul" -> multiplyASTExpr(e1.transform, e2.transform)
      | "silver:core:div" -> divideASTExpr(e1.transform, e2.transform)
      | "silver:core:mod" -> modulusASTExpr(e1.transform, e2.transform)
      | "silver:core:append" -> appendASTExpr(e1.transform, e2.transform)
      | _ -> applyASTExpr(e.transform, es.transform, anns.transform)
      end
    | classMemberReference(q), oneAppExprs(presentAppExpr(e)) 
        when e.finalType.isPrimitive ->
      case q.lookupValue.fullName of
      | "silver:core:not" -> notASTExpr(e.transform)
      | "silver:core:negate" -> negASTExpr(e.transform)
      | "silver:core:toString" -> toStringASTExpr(e.transform)
      | "silver:core:toInteger" -> toIntegerASTExpr(e.transform)
      | "silver:core:toFloat" -> toFloatASTExpr(e.transform)
      | "silver:core:toBoolean" -> toBooleanASTExpr(e.transform)
      | "silver:core:length" -> lengthASTExpr(e.transform)
      | _ -> applyASTExpr(e.transform, es.transform, anns.transform)
      end
    | classMemberReference(q), oneAppExprs(presentAppExpr(e)) 
        when e.finalType matches appType(listCtrType(), _) ->
      case q.lookupValue.fullName of
      | "silver:core:length" -> lengthASTExpr(e.transform)
      | _ -> applyASTExpr(e.transform, es.transform, anns.transform)
      end

    | _, _ -> applyASTExpr(e.transform, es.transform, anns.transform)
    end;
}

synthesized attribute isPrimitive::Boolean occurs on Type;
aspect isPrimitive on Type of
| intType() -> true
| floatType() -> true
| boolType() -> true
| stringType() -> true
| _ -> false
end;

aspect production partialApplication
top::Expr ::= e::Decorated! Expr es::Decorated! AppExprs anns::Decorated! AnnoAppExprs
{
  top.transform = applyASTExpr(e.transform, es.transform, anns.transform);
}

aspect production forwardAccess
top::Expr ::= e::Expr '.' 'forward'
{
  -- Flow analysis has no way to track what e is decorated with across reflect/reify,
  -- so if the inh set is unspecialized, assume that it has the reference set.
  local finalTy::Type =
    case e.finalType of
    | decoratedType(nt, varType(_)) ->
      decoratedType(nt, inhSetType(sort(concat(getInhsForNtRef(nt.typeName, top.flowEnv)))))
    | t -> t
    end;
  top.transform =
    applyASTExpr(
      antiquoteASTExpr(
        Silver_Expr {
          silver:rewrite:anyASTExpr(
            \ e::$TypeExpr{typerepTypeExpr(finalTy)} -> e.forward)
        }),
      consASTExpr(e.transform, nilASTExpr()),
      nilNamedASTExpr());
}

aspect production errorAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  top.transform =
    applyASTExpr(
      antiquoteASTExpr(
        Silver_Expr {
          silver:rewrite:anyASTExpr(
            \ e::$TypeExpr{typerepTypeExpr(e.finalType)} -> e.$qName{q.name})
        }),
      consASTExpr(e.transform, nilASTExpr()),
      nilNamedASTExpr());
}

aspect production annoAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  top.transform =
    applyASTExpr(
      antiquoteASTExpr(
        Silver_Expr {
          silver:rewrite:anyASTExpr(
            \ e::$TypeExpr{typerepTypeExpr(e.finalType)} -> e.$qName{q.name})
        }),
      consASTExpr(e.transform, nilASTExpr()),
      nilNamedASTExpr());
}

aspect production synDataAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  top.transform =
    applyASTExpr(
      antiquoteASTExpr(
        Silver_Expr {
          silver:rewrite:anyASTExpr(
            \ e::$TypeExpr{typerepTypeExpr(e.finalType)} -> e.$qName{q.name})
        }),
      consASTExpr(e.transform, nilASTExpr()),
      nilNamedASTExpr());
}

aspect production terminalAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  top.transform =
    applyASTExpr(
      antiquoteASTExpr(
        Silver_Expr {
          silver:rewrite:anyASTExpr(
            \ e::$TypeExpr{typerepTypeExpr(e.finalType)} -> e.$qName{q.name})
        }),
      consASTExpr(e.transform, nilASTExpr()),
      nilNamedASTExpr());
}


aspect production synDecoratedAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  -- Flow analysis has no way to track what e is decorated with across reflect/reify,
  -- so if the inh set is unspecialized, assume that it has the reference set.
  local finalTy::Type =
    case e.finalType of
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
                  lambdaRHSCons(
                    lambdaRHSElemIdTy(
                      name("_e"), '::',
                      typerepTypeExpr(eUndec.finalType)),
                    inh.lambdaParams),
                  Silver_Expr {
                    $Expr{
                      decorateExprWith(
                        'decorate', baseExpr(qName("_e")),
                        'with', '{', inh.bodyExprInhTransform, '}')}.$qName{q.name}
                  })})
          }),
        consASTExpr(eUndec.transform, inh.transform),
        nilNamedASTExpr())
    | lexicalLocalReference(qn, _, _, _) when
        case lookup(qn.name, top.boundVars) of
        | just(bindingIsDecorated) -> !bindingIsDecorated
        | nothing() -> false
        end -> 
      applyASTExpr(
        antiquoteASTExpr(
          Silver_Expr {
            silver:rewrite:anyASTExpr(
              \ e::$TypeExpr{typerepTypeExpr(e.finalType.decoratedType)} -> e.$qName{q.name})
          }),
        consASTExpr(varASTExpr(qn.name), nilASTExpr()),
        nilNamedASTExpr())
    | _ ->
      applyASTExpr(
        antiquoteASTExpr(
          Silver_Expr {
            silver:rewrite:anyASTExpr(
              \ e::$TypeExpr{typerepTypeExpr(finalTy)} -> e.$qName{q.name})
          }),
        consASTExpr(e.transform, nilASTExpr()),
        nilNamedASTExpr())
    end;
}

aspect production inhDecoratedAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  -- Flow analysis has no way to track what e is decorated with across reflect/reify,
  -- so if the inh set is unspecialized, assume that it has the reference set.
  local finalTy::Type =
    case e.finalType of
    | decoratedType(nt, varType(_)) ->
      decoratedType(nt, inhSetType(sort(concat(getInhsForNtRef(nt.typeName, top.flowEnv)))))
    | t -> t
    end;
  top.transform =
    applyASTExpr(
      antiquoteASTExpr(
        Silver_Expr {
          silver:rewrite:anyASTExpr(
            \ e::$TypeExpr{typerepTypeExpr(finalTy)} -> e.$qName{q.name})
        }),
      consASTExpr(e.transform, nilASTExpr()),
      nilNamedASTExpr());
}

aspect production inhUndecoratedAccessErrorHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  -- Flow analysis has no way to track what e is decorated with across reflect/reify,
  -- so if the inh set is unspecialized, assume that it has the reference set.
  local finalTy::Type =
    case e.finalType of
    | decoratedType(nt, varType(_)) ->
      decoratedType(nt, inhSetType(sort(concat(getInhsForNtRef(nt.typeName, top.flowEnv)))))
    | t -> t
    end;
  top.transform =
    applyASTExpr(
      antiquoteASTExpr(
        Silver_Expr {
          silver:rewrite:anyASTExpr(
            \ e::$TypeExpr{typerepTypeExpr(finalTy)} -> e.$qName{q.name})
        }),
      consASTExpr(e.transform, nilASTExpr()),
      nilNamedASTExpr());
}

aspect production transUndecoratedAccessErrorHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  -- Flow analysis has no way to track what e is decorated with across reflect/reify,
  -- so if the inh set is unspecialized, assume that it has the reference set.
  local finalTy::Type =
    case e.finalType of
    | decoratedType(nt, varType(_)) ->
      decoratedType(nt, inhSetType(sort(concat(getInhsForNtRef(nt.typeName, top.flowEnv)))))
    | t -> t
    end;
  top.transform =
    applyASTExpr(
      antiquoteASTExpr(
        Silver_Expr {
          silver:rewrite:anyASTExpr(
            \ e::$TypeExpr{typerepTypeExpr(finalTy)} -> e.$qName{q.name})
        }),
      consASTExpr(e.transform, nilASTExpr()),
      nilNamedASTExpr());
}

aspect production unknownDclAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  -- Flow analysis has no way to track what e is decorated with across reflect/reify,
  -- so if the inh set is unspecialized, assume that it has the reference set.
  local finalTy::Type =
    case e.finalType of
    | decoratedType(nt, varType(_)) ->
      decoratedType(nt, inhSetType(sort(concat(getInhsForNtRef(nt.typeName, top.flowEnv)))))
    | t -> t
    end;
  top.transform =
    applyASTExpr(
      antiquoteASTExpr(
        Silver_Expr {
          silver:rewrite:anyASTExpr(
            \ e::$TypeExpr{typerepTypeExpr(finalTy)} -> e.$qName{q.name})
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
                lambdaRHSCons(
                  lambdaRHSElemIdTy(
                    name("_e"), '::',
                    typerepTypeExpr(e.finalType)),
                  inh.lambdaParams),
                decorateExprWith(
                  'decorate', baseExpr(qName("_e")),
                  'with', '{', inh.bodyExprInhTransform, '}'))})
        }),
      consASTExpr(e.transform, inh.transform),
      nilNamedASTExpr());
}

attribute transform<ASTExprs> occurs on ExprInhs;
synthesized attribute lambdaParams::LambdaRHS occurs on ExprInhs;
functor attribute bodyExprInhTransform occurs on ExprInhs, ExprInh;
propagate bodyExprInhTransform on ExprInhs;

aspect production exprInhsEmpty
top::ExprInhs ::= 
{
  top.transform = nilASTExpr();
  top.lambdaParams = lambdaRHSNil();
}

aspect production exprInhsOne
top::ExprInhs ::= lhs::ExprInh
{
  top.transform = consASTExpr(lhs.transform, nilASTExpr());
  top.lambdaParams =
    lambdaRHSCons(lhs.lambdaParam, lambdaRHSNil());
}

aspect production exprInhsCons
top::ExprInhs ::= lhs::ExprInh inh::ExprInhs
{
  top.transform = consASTExpr(lhs.transform, inh.transform);
  top.lambdaParams = lambdaRHSCons(lhs.lambdaParam, inh.lambdaParams);
}

attribute transform<ASTExpr> occurs on ExprInh;
synthesized attribute lambdaParam::LambdaRHSElem occurs on ExprInh;

aspect production exprInh
top::ExprInh ::= lhs::ExprLHSExpr '=' e::Expr ';'
{
  top.transform = e.transform;
  
  local paramName::String = implode("_", explode(":", lhs.name));
  top.lambdaParam =
    lambdaRHSElemIdTy(
      name(paramName), '::',
      typerepTypeExpr(e.finalType));
  top.bodyExprInhTransform =
    exprInh(lhs, '=', baseExpr(qName(paramName)), ';');
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

aspect production stringConst
top::Expr ::= s::String_t
{
  top.transform = stringASTExpr(unescapeString(substring(1, length(s.lexeme) - 1, s.lexeme)));
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
  top.transform =
    -- TODO: We should be able to override boundVars on h and t here,
    -- but currently the flow analysis forbids this due to the hidden
    -- transitive dependencies check.
    case forward of
    | functionInvocation(_, snocAppExprs(oneAppExprs(presentAppExpr(decH)), _, presentAppExpr(decT)), _) ->
      consListASTExpr(decH.transform, decT.transform)
    | _ -> error("Unexpected forward: " ++ genericShow(forward))
    end;
}

aspect production fullList
top::Expr ::= '[' es::Exprs ']'
{
  -- TODO: Consider refactoring listtrans on Exprs to decorate the expressions here
  -- before forwarding via unique references.
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
  decEs.originRules = top.originRules;

  top.transform = listASTExpr(decEs.transform);
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
  decEs.originRules = top.originRules;
  
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
                  o, ml, 'end'))})
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
  
  la.boundVars = top.boundVars;
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
    | lexicalLocalReference(qn, _, _, _) ->
      fromMaybe(e.finalType.isDecorated, lookup(qn.name, top.boundVars))
    | _ -> e.finalType.isDecorated
    end;
  top.varBindings = [(id.name, isDecorated)];
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
  top.lambdaParams = lambdaRHSNil();
  top.lambdaParamRefs = exprsEmpty();
}
aspect production exprsSingle
top::Exprs ::= e::Expr
{
  top.transform = consASTExpr(e.transform, nilASTExpr());
  
  local lambdaParamName::String = "__exprs_param_" ++ toString(genInt());
  top.lambdaParams =
    lambdaRHSCons(
      lambdaRHSElemIdTy(
        name(lambdaParamName), '::',
        typerepTypeExpr(e.finalType)),
      lambdaRHSNil());
  top.lambdaParamRefs =
    exprsSingle(
      baseExpr(qName(lambdaParamName)));
}
aspect production exprsCons
top::Exprs ::= e1::Expr ',' e2::Exprs
{
  top.transform = consASTExpr(e1.transform, e2.transform);
  
  local lambdaParamName::String = "__exprs_param_" ++ toString(genInt());
  top.lambdaParams =
    lambdaRHSCons(
      lambdaRHSElemIdTy(
        name(lambdaParamName), '::',
        typerepTypeExpr(e1.finalType)),
      e2.lambdaParams);
  top.lambdaParamRefs =
    exprsCons(
      baseExpr(qName(lambdaParamName)),
      ',', e2.lambdaParamRefs);
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
