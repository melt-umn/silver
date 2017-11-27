grammar silver:extension:bidirtransform;

synthesized attribute rewriteRules::[Decorated RewriteRule];
synthesized attribute outputStmt::(Expr ::= Expr);
synthesized attribute inputType::Type;
synthesized attribute inputProduction::Maybe<RewriteProduction>;
synthesized attribute shouldRestore::Boolean;

nonterminal RewriteRuleList with rewriteRules, env, errors, location, absGroup, cncGroup, pp, downSubst, upSubst, finalSubst, config;
nonterminal RewriteRule with inputType, inputProduction, typerep, outputStmt, shouldRestore, env, errors, location, absGroup, cncGroup, pp, downSubst, upSubst, finalSubst, config;
nonterminal RewriteProduction with name, inputNames, typerep, env, errors, location, absGroup, cncGroup, pp, config;
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

    forwards to rewriteRule(e, "", prd.typerep, e.typerep, just(prd), false, location=rule.location);
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
    
    forwards to rewriteRule(e, "", prd.typerep, e.typerep, just(prd), true, location=rule.location);
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

    forwards to rewriteRule(e, name.name, t.typerep, e.typerep, nothing(), false, location=rule.location);    
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
    
    forwards to rewriteRule(e, name.name, t.typerep, e.typerep, nothing(), true, location=rule.location);
}

abstract production rewriteRule
rule::RewriteRule ::= lhs::Expr inName::String inType::Type outType::Type inProd::Maybe<RewriteProduction> restore::Boolean
{
    lhs.config = rule.config;

    lhs.downSubst = rule.downSubst;
    rule.upSubst = lhs.upSubst;
    lhs.finalSubst = rule.upSubst;

    lhs.defaultInheritedAnnos = [];

    rule.errors := []; -- We explicitly ignore lhs errors here
    rule.errors <- if inProd.isJust then inProd.fromJust.errors else [];

    rule.typerep = outType;
    rule.inputType = inType;
    rule.inputProduction = inProd;
    rule.shouldRestore = restore;
    rule.outputStmt = case inProd of 
        | nothing() -> (\ e::Expr -> fillExpr(lhs, [e], [inName], location=e.location))
        | just(prd) -> (\ e::Expr ->
            case e of application(_, _, aexpr, _, _, _) -> 
                fillExpr(lhs, pullOutAppExprs(aexpr), prd.inputNames, location=e.location)
            end
        )
    end;
}

concrete production rewriteProduction
prd::RewriteProduction ::= qn::QName '(' args::RewriteProductionArgs ')'
{
    prd.pp = qn.pp ++ "(" ++ args.pp ++ ")";

    prd.inputNames = args.inputNames;
    prd.name = qn.name;

    prd.errors := args.errors;

    local absSig::[Decorated NamedSignature] = getProdFromGroup(qn.name, prd.absGroup);
    local cncSig::[Decorated NamedSignature] = getProdFromGroup(qn.name, prd.cncGroup);

    -- prd.namedSig = if length(absSig) != 0 then head(absSig)
    --     else head(cncSig);

    prd.typerep = if length(absSig) != 0 then head(absSig).typerep
        else head(cncSig).typerep;
}

concrete production rewriteProductionArgSingle
arg::RewriteProductionArgs ::= name::QName
{
    arg.pp = name.pp;
    arg.inputNames = [name.name];
    arg.errors := [];
} 

concrete production rewriteProductionArgMany
arg::RewriteProductionArgs ::= args::RewriteProductionArgs ',' name::QName {
    arg.pp = args.pp ++ "," ++ name.pp;
    arg.inputNames = args.inputNames ++ [name.name];
    arg.errors := args.errors;
}
-- todo: right now this means all elements of a rewrite rule need to be 
-- anonymous variables. This should change, so some variables can be skipped
-- or provided explicitly like integer values

abstract production applyRw
top::Expr ::= rwr::Decorated RewriteRule rhsTy::String lhsTy::String elemName::String
{
    forwards to rwr.outputStmt(
        -- Pass the rhs of an origin into
        -- rewrite rules that want that type
        -- We can't use restored$typeName here because 
        -- that would infinitely recurse. 
        if rhsTy == lhsTy || !rwr.shouldRestore then baseName(elemName, location=top.location) 
        -- Otherwise pass the appropriate restored type from
        -- this origin into the rule
        else exprAccess(restoreNm(unFull(rwr.inputType.typeName)), "o", location=top.location));
} 

-- LHS is a concrete syntax type name
abstract production applyRwProd
top::Expr ::= rwr::Decorated RewriteRule lhs::String ns::Decorated NamedSignature
{   
    forwards to rwr.outputStmt(
        fullFunc(
            rwr.inputProduction.fromJust.name, 
            lhsRestoredTypesAppExprs(lhs, ns.inputNames, map((.typeName), ns.inputTypes), rwr.shouldRestore, location=top.location),
            emptyAnnoAppExprs(location=top.location), location=top.location));
}

-- LHS is a concrete syntax type name
abstract production lhsRestoredTypesAppExprs
top::AppExprs ::= lhs::String inputNames::[String] inputTypes::[String] shouldRestore::Boolean
{
    forwards to if length(inputTypes) == 1 
    then oneAppExprs(
        if shouldRestore 
        -- We don't, at this point, know what type we need to restore to,
        --          this v---------------------------------v needs to be moved down the line
        then namedAccess(restoreNm(unFull(head(inputTypes))), head(inputNames), location=top.location)
        else presentName(head(inputNames), location=top.location), location=top.location)
    else snocAppExprs(lhsRestoredTypesAppExprs(lhs, tail(inputNames), tail(inputTypes), shouldRestore, location=top.location),
            ',',
            if shouldRestore 
            then namedAccess(restoreNm(unFull(head(inputTypes))), head(inputNames), location=top.location)
            else presentName(head(inputNames), location=top.location),   
        location=top.location);
}