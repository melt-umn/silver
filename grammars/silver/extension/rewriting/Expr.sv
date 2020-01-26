grammar silver:extension:rewriting;

autocopy attribute boundVars::[String] occurs on Expr, Exprs, ExprInhs, ExprInh, AppExprs, AppExpr, AnnoAppExprs, AnnoExpr, AssignExpr, PrimPatterns, PrimPattern;
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
  top.transform =
    if containsBy(stringEq, q.name, top.boundVars)
    then case q.lookupValue.typerep of
      | ntOrDecType(ntType, _) when finalType(top).isDecorated ->
        -- The variable is from the rule referenced as decorated, decorate it with nothing
        applyASTExpr(
          antiquoteASTExpr(
            Silver_Expr {
              silver:rewrite:anyASTExpr(
                \ e::$TypeExpr{typerepTypeExpr(ntType, location=builtin)} ->
                  decorate e with {})
            }),
          consASTExpr(varASTExpr(q.name), nilASTExpr()), nilNamedASTExpr())
      -- The variable is from the rule and referenced as undecorated
      | _ -> varASTExpr(q.name)
      end
    -- The variable comes from an enclosing let/match
    else antiquoteASTExpr(Silver_Expr { silver:rewrite:anyASTExpr($Expr{top}) });
}

aspect production childReference
top::Expr ::= q::Decorated QName
{
  top.transform =
    -- Explicitly undecorate the variable, if appropriate for the final expected type
    if q.lookupValue.typerep.isDecorable && !finalType(top).isDecorated
    then antiquoteASTExpr(Silver_Expr { silver:rewrite:anyASTExpr(new($Expr{top})) })
    else antiquoteASTExpr(Silver_Expr { silver:rewrite:anyASTExpr($Expr{top}) });
}

aspect production lhsReference
top::Expr ::= q::Decorated QName
{
  top.transform =
    -- Explicitly undecorate the variable, if appropriate for the final expected type
    if q.lookupValue.typerep.isDecorable && !finalType(top).isDecorated
    then antiquoteASTExpr(Silver_Expr { silver:rewrite:anyASTExpr(new($Expr{top})) })
    else antiquoteASTExpr(Silver_Expr { silver:rewrite:anyASTExpr($Expr{top}) });
}

aspect production localReference
top::Expr ::= q::Decorated QName
{
  top.transform =
    -- Explicitly undecorate the variable, if appropriate for the final expected type
    if q.lookupValue.typerep.isDecorable && !finalType(top).isDecorated
    then antiquoteASTExpr(Silver_Expr { silver:rewrite:anyASTExpr(new($Expr{top})) })
    else antiquoteASTExpr(Silver_Expr { silver:rewrite:anyASTExpr($Expr{top}) });
}

aspect production forwardReference
top::Expr ::= q::Decorated QName
{
  top.transform =
    -- Explicitly undecorate the variable, if appropriate for the final expected type
    if q.lookupValue.typerep.isDecorable && !finalType(top).isDecorated
    then antiquoteASTExpr(Silver_Expr { silver:rewrite:anyASTExpr(new($Expr{top})) })
    else antiquoteASTExpr(Silver_Expr { silver:rewrite:anyASTExpr($Expr{top}) });
}

aspect production errorApplication
top::Expr ::= e::Decorated Expr es::AppExprs anns::AnnoAppExprs
{
  top.transform = applyASTExpr(e.transform, es.transform, anns.transform);
}

aspect production functionInvocation
top::Expr ::= e::Decorated Expr es::Decorated AppExprs anns::Decorated AnnoAppExprs
{
  top.transform =
    case e of
    | productionReference(q) -> prodCallASTExpr(q.lookupValue.fullName, es.transform, anns.transform)
    | _ -> applyASTExpr(e.transform, es.transform, anns.transform)
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
  top.transform =
    applyASTExpr(
      antiquoteASTExpr(
        Silver_Expr {
          silver:rewrite:anyASTExpr(
            \ e::$TypeExpr{typerepTypeExpr(finalType(e), location=builtin)} -> e.forward)
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

aspect production inhDecoratedAccessHandler
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

aspect production errorDecoratedAccessHandler
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
synthesized attribute bodyExprInhTransform<a>::a;
attribute bodyExprInhTransform<ExprInhs> occurs on ExprInhs;

aspect production exprInhsEmpty
top::ExprInhs ::= 
{
  top.transform = nilASTExpr();
  top.lambdaParams = productionRHSNil(location=builtin);
  propagate bodyExprInhTransform;
}

aspect production exprInhsOne
top::ExprInhs ::= lhs::ExprInh
{
  top.transform = consASTExpr(lhs.transform, nilASTExpr());
  top.lambdaParams =
    productionRHSCons(lhs.lambdaParam, productionRHSNil(location=builtin), location=builtin);
  propagate bodyExprInhTransform;
}

aspect production exprInhsCons
top::ExprInhs ::= lhs::ExprInh inh::ExprInhs
{
  top.transform = consASTExpr(lhs.transform, inh.transform);
  top.lambdaParams = productionRHSCons(lhs.lambdaParam, inh.lambdaParams, location=builtin);
  propagate bodyExprInhTransform;
}

attribute transform<ASTExpr> occurs on ExprInh;
synthesized attribute lambdaParam::ProductionRHSElem occurs on ExprInh;
attribute bodyExprInhTransform<ExprInh> occurs on ExprInh;

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

aspect production gt
top::Expr ::= e1::Expr '>' e2::Expr
{
  top.transform = gtASTExpr(e1.transform, e2.transform);
}

aspect production lt
top::Expr ::= e1::Expr '<' e2::Expr
{
  top.transform = ltASTExpr(e1.transform, e2.transform);
}

aspect production gteq
top::Expr ::= e1::Expr '>=' e2::Expr
{
  top.transform = gteqASTExpr(e1.transform, e2.transform);
}

aspect production lteq
top::Expr ::= e1::Expr '<=' e2::Expr
{
  top.transform = lteqASTExpr(e1.transform, e2.transform);
}

aspect production eqeq
top::Expr ::= e1::Expr '==' e2::Expr
{
  top.transform = eqeqASTExpr(e1.transform, e2.transform);
}

aspect production neq
top::Expr ::= e1::Expr '!=' e2::Expr
{
  top.transform = neqASTExpr(e1.transform, e2.transform);
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

aspect production stringPlusPlus
top::Expr ::= e1::Decorated Expr   e2::Decorated Expr
{
  top.transform = appendASTExpr(e1.transform, e2.transform);
}

aspect production errorPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  top.transform = appendASTExpr(e1.transform, e2.transform);
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

aspect production newFunction
top::Expr ::= 'new' '(' e::Expr ')'
{
  top.transform =
    applyASTExpr(
      antiquoteASTExpr(
        Silver_Expr {
          silver:rewrite:anyASTExpr(
            \ e::$TypeExpr{typerepTypeExpr(finalType(e), location=builtin)} -> new(e))
        }),
      consASTExpr(e.transform, nilASTExpr()),
      nilNamedASTExpr());
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

aspect production rewriteExpr
top::Expr ::= 'rewriteWith' '(' s::Expr ',' e::Expr ')'
{
  -- More efficient implementation that avoids reifying e's value.
  top.transform = rewriteASTExpr(s.transform, e.transform);
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
  top.varBindings = [id.name];
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
  top.transform = wildASTExpr();
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