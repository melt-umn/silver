grammar silver:extension:bidirtransform;

synthesized attribute rewriteRules::[Decorated RewriteRule];
synthesized attribute outputStmt::(Expr ::= Expr);
synthesized attribute restoreStmt::(Expr ::= Expr);
synthesized attribute inputType::Type;
synthesized attribute inputProduction::RewriteProduction;
synthesized attribute hasProduction::Boolean;
synthesized attribute shouldRestore::Boolean;
synthesized attribute decSig::Decorated NamedSignature;

nonterminal RewriteRuleList with rewriteRules, env, errors, location, absGroup, cncGroup, pp, downSubst, upSubst, finalSubst, config;
nonterminal RewriteRule with inputType, inputProduction, hasProduction, typerep, outputStmt, restoreStmt, shouldRestore, env, errors, location, absGroup, cncGroup, pp, downSubst, upSubst, finalSubst, config;
nonterminal RewriteProduction with name, inputNames, typerep, env, errors, location, absGroup, cncGroup, pp, config, decSig;
nonterminal RewriteProductionArgs with inputNames, errors, pp, config;

terminal RestoreArrow_t '~~>' lexer classes {SPECOP};

concrete production rewriteRuleCons
rrl::RewriteRuleList ::= Vbar_kwd l::RewriteRule r::RewriteRuleList
{
    l.config = rrl.config;
    r.config = rrl.config;

    l.env = rrl.env;
    r.env = rrl.env;

    l.downSubst = rrl.downSubst;
    r.downSubst = l.upSubst;
    rrl.upSubst = r.upSubst;
    l.finalSubst = r.upSubst;
    r.finalSubst = l.finalSubst;

    rrl.pp = "| " ++ l.pp ++ r.pp;

    rrl.errors := l.errors ++ r.errors;
    rrl.rewriteRules = r.rewriteRules ++ [l];
    
    -- error check: is the exact rule l found in r?
    -- equality checking is non trivial so we aren't doing this
    -- rrl.errors <- if !containsBy(\ a::RewriteRule b::RewriteRule -> eq(a,b), l, r.rewriteRules) then []
    --               else [err(rrl.location, "Duplicate rewrite rule definition")];

}

concrete production rewriteRuleSingle
rrl::RewriteRuleList ::= Vbar_kwd rule::RewriteRule 
{
    rule.config = rrl.config;
    rule.env = rrl.env;
    
    rule.downSubst = rrl.downSubst;
    rrl.upSubst = rule.upSubst;
    rule.finalSubst = rrl.upSubst;

    rrl.pp = "| " ++ rule.pp;

    rrl.rewriteRules = [rule];
    rrl.errors := rule.errors;
}

-- rewrite an abstract production as a concrete production
concrete production rewriteRuleProd
rule::RewriteRule ::= prd::RewriteProduction '->' e::Expr
{
    rule.pp = prd.pp ++ "->" ++ e.pp;

    e.downSubst = rule.downSubst;
    rule.upSubst = e.upSubst;
    e.finalSubst = rule.upSubst;
    e.defaultInheritedAnnos = [];      

    forwards to rewriteRule(e, "", prd.typerep, e.typerep, prd, true, false, location=rule.location);
}

-- rewrite an abstract production as a concrete production
concrete production rewriteRuleRestoreProd
rule::RewriteRule ::= prd::RewriteProduction '~~>' e::Expr
{
    rule.pp = prd.pp ++ "~~>" ++ e.pp;

    e.downSubst = rule.downSubst;
    rule.upSubst = e.upSubst;
    e.finalSubst = rule.upSubst;
    e.defaultInheritedAnnos = [];      
    
    forwards to rewriteRule(e, "", prd.typerep, e.typerep, prd, true, true, location=rule.location);
}

-- rewrite a concrete type as another concrete type through plugging it into
-- an expression
concrete production rewriteRuleType
rule::RewriteRule ::= name::QName '::' t::TypeExpr '->' e::Expr 
{
    rule.pp = name.pp ++ "::" ++ t.pp ++ "->" ++ e.pp;

    e.downSubst = rule.downSubst;
    rule.upSubst = e.upSubst;
    e.finalSubst = rule.upSubst;    
    e.defaultInheritedAnnos = [];      

    forwards to rewriteRule(e, name.name, t.typerep, e.typerep, 
      emptyRewriteProduction(location=rule.location), false, false, location=rule.location);    
}

-- rewrite a concrete type as another concrete type through plugging it into
-- an expression, and referring to its own restored$t element
concrete production rewriteRuleRestoreType
rule::RewriteRule ::= name::QName '::' t::TypeExpr '~~>' e::Expr
{
    rule.pp = name.pp ++ "::" ++ t.pp ++ "~~>" ++ e.pp;

    -- I shouldn't need to have to redefine this, as the forward defines this, but I do.
    e.downSubst = rule.downSubst;
    rule.upSubst = e.upSubst;
    e.finalSubst = rule.upSubst;
    e.defaultInheritedAnnos = [];  
    
    forwards to rewriteRule(e, name.name, t.typerep, e.typerep, 
      emptyRewriteProduction(location=rule.location), false, true, location=rule.location);
}

abstract production rewriteRule
rule::RewriteRule ::= rhs::Expr inName::String inType::Type outType::Type inProd::RewriteProduction hasProd::Boolean restore::Boolean
{
    rhs.config = rule.config;

    rhs.downSubst = rule.downSubst;
    rule.upSubst = rhs.upSubst;
    rhs.finalSubst = rule.upSubst;

    rhs.defaultInheritedAnnos = [];

    rule.errors := []; -- We explicitly ignore rhs errors here
    rule.errors <- inProd.errors;

    local rhsNs::Maybe<Decorated NamedSignature> = case rhs of 
        | application(e,_,_,_,_,_) -> case e of
            | baseExpr(qn) -> just(head(getProdFromGroups(qn.name, rule.absGroup, rule.cncGroup)))
            | _ -> nothing()
        end
        | _ -> nothing()
    end;

    rule.hasProduction = hasProd;
    rule.typerep = outType;
    rule.inputType = inType;
    rule.inputProduction = inProd;
    rule.shouldRestore = restore;
    rule.outputStmt = if !hasProd
        then (\ e::Expr -> fillExpr(rhs, [e], [inName], location=e.location))
        else (\ e::Expr ->
            case e of application(_, _, aexpr, _, _, _) -> 
                fillExpr(rhs, pullOutAppExprs(aexpr), inProd.inputNames, location=e.location)
            end
        );

    rule.restoreStmt = (\ e::Expr ->
            case e of application(_, _, aexpr, _, _, _) -> 
                restoreExpr(rhs, pullOutAppExprs(aexpr), inProd.inputNames, rhsNs.fromJust, location=e.location)
            end
        );
}

function getProdFromGroups
[Decorated NamedSignature] ::= s::String absGroup::Decorated NonterminalList cncGroup::Decorated NonterminalList
{
    local absSig::[Decorated NamedSignature] = getProdFromGroup(s, absGroup);
    local cncSig::[Decorated NamedSignature] = getProdFromGroup(s, cncGroup);

    return if length(absSig) != 0 then [head(absSig)]
        else if length(cncSig) != 0 then [head(cncSig)]
        else []; 
}