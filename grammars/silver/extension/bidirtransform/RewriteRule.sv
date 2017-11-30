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
rule::RewriteRule ::= lhs::Expr inName::String inType::Type outType::Type inProd::RewriteProduction hasProd::Boolean restore::Boolean
{
    lhs.config = rule.config;

    lhs.downSubst = rule.downSubst;
    rule.upSubst = lhs.upSubst;
    lhs.finalSubst = rule.upSubst;

    lhs.defaultInheritedAnnos = [];

    rule.errors := []; -- We explicitly ignore lhs errors here
    rule.errors <- inProd.errors;

    rule.hasProduction = hasProd;
    rule.typerep = outType;
    rule.inputType = inType;
    rule.inputProduction = inProd;
    rule.shouldRestore = restore;
    rule.outputStmt = if !hasProd
        then (\ e::Expr -> fillExpr(lhs, [e], [inName], location=e.location))
        else (\ e::Expr ->
            case e of application(_, _, aexpr, _, _, _) -> 
                fillExpr(lhs, pullOutAppExprs(aexpr), inProd.inputNames, location=e.location)
            end
        );

    rule.restoreStmt = (\ e::Expr ->
            case e of application(_, _, aexpr, _, _, _) -> 
                restoreExpr(lhs, pullOutAppExprs(aexpr), inProd.inputNames, inProd.decSig, location=e.location)
            end
        );
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

    prd.decSig = if length(absSig) != 0 then head(absSig)
        else head(cncSig); 

    prd.typerep = prd.decSig.typerep;
}

abstract production emptyRewriteProduction
prd::RewriteProduction ::= 
{
    prd.pp = "";
    prd.errors := [];
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
top::Expr ::= rwr::Decorated RewriteRule ns::Decorated NamedSignature
{   
    local fwdFunc::(Expr ::= Expr) = if rwr.shouldRestore then rwr.restoreStmt
        else rwr.outputStmt;

    forwards to fwdFunc(
        fullFunc(
            rwr.inputProduction.name, 
            strAppExprs(ns.inputNames, location=top.location),
            emptyAnnoAppExprs(location=top.location), location=top.location));
}

-- LHS is a concrete syntax type name
abstract production strAppExprs
top::AppExprs ::= inputNames::[String] 
{
    forwards to if length(inputNames) == 1
    then oneAppExprs(presentName(head(inputNames), location=top.location), location=top.location)
    else snocAppExprs(strAppExprs(tail(inputNames), location=top.location),
            ',', presentName(head(inputNames), location=top.location),   
        location=top.location);
}