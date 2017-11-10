grammar silver:extension:bidirtransform;

synthesized attribute rewriteRules::[RewriteRule];
synthesized attribute outputStmt::(Expr ::= Expr);
synthesized attribute inputType::Type;
synthesized attribute inputProduction::Maybe<RewriteProduction>;
synthesized attribute shouldRestore::Boolean;

nonterminal RewriteRuleList with rewriteRules, env, errors, location;
nonterminal RewriteRule with inputType, inputProduction, typerep, outputStmt, shouldRestore, env, errors, location;
nonterminal RewriteProduction with name, inputNames, typerep, env, errors, location;
nonterminal RewriteProductionArgs with inputNames, errors;

terminal RestoreArrow_t '~~>' lexer classes {SPECOP};

concrete production rewriteRuleCons
rrl::RewriteRuleList ::= Vbar_kwd l::RewriteRule r::RewriteRuleList
{
    l.env = rrl.env;
    r.env = rrl.env;

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
    rule.env = rrl.env;

    rrl.rewriteRules = [rule];
    rrl.errors := rule.errors;
}

-- rewrite an abstract production as a concrete production
concrete production rewriteRuleProd
rule::RewriteRule ::= prd::RewriteProduction '->' e::Expr
{
    forwards to rewriteRule(e, "", prd.typerep, e.typerep, just(prd), false, location=rule.location);
}

-- rewrite an abstract production as a concrete production
concrete production rewriteRuleRestoreProd
rule::RewriteRule ::= prd::RewriteProduction '~~>' e::Expr
{
    forwards to rewriteRule(e, "", prd.typerep, e.typerep, just(prd), true, location=rule.location);
}

-- rewrite a concrete type as another concrete type through plugging it into
-- an expression
concrete production rewriteRuleType
rule::RewriteRule ::= name::QName '::' t::TypeExpr '->' e::Expr 
{
    forwards to rewriteRule(e, name.name, t.typerep, e.typerep, nothing(), false, location=rule.location);    
}

-- rewrite a concrete type as another concrete type through plugging it into
-- an expression, and referring to its own restored$t element
concrete production rewriteRuleRestoreType
rule::RewriteRule ::= name::QName '::' t::TypeExpr '~~>' e::Expr
{
    forwards to rewriteRule(e, name.name, t.typerep, e.typerep, nothing(), true, location=rule.location);
}

abstract production rewriteRule
rule::RewriteRule ::= lhs::Expr inName::String inType::Type outType::Type inProd::Maybe<RewriteProduction> restore::Boolean
{
    rule.typerep = outType;
    rule.inputType = inType;
    rule.inputProduction = inProd;
    rule.shouldRestore = restore;
    rule.outputStmt = case inProd of 
        | nothing() -> (\ e::Expr -> fillExpr(lhs, [e], [inName]))
        | just(prd) -> (\ e::Expr ->
            case e of application(_, _, aexpr, _, _, _) -> 
                    fillExpr(lhs, pullOutAppExprs(aexpr), prd.inputNames)
            end
        )
    end;
}

concrete production rewriteProduction
prd::RewriteProduction ::= name::QName '(' args::RewriteProductionArgs ')'
{
    prd.inputNames = args.inputNames;
    prd.name = name.name;
    -- I don't know, but am guessing that productions fall under
    -- 'values' as opposed to types or attributes
    prd.typerep = case head(getValueDcl(name.name, prd.env)) of 
        | prodDcl(_,_,ns) -> ns.typerep
        -- todo: errors
    end;
}

concrete production rewriteProductionArgSingle
arg::RewriteProductionArgs ::= name::QName
{
    arg.inputNames = [name.name];
} 

concrete production rewriteProductionArgMany
arg::RewriteProductionArgs ::= arg2::RewriteProductionArgs ',' name::QName {
    arg.inputNames = arg2.inputNames ++ [name.name];
}
-- todo: right now this means all elements of a rewrite rule need to be 
-- anonymous variables. This should change, so some variables can be skipped
-- or provided explicitly like integer values

function applyRw
Expr ::= rwr::RewriteRule rhsTy::String lhsTy::String loc::Location elemName::String
{
    return rwr.outputStmt(
        -- Pass the rhs of an origin into
        -- rewrite rules that want that type
        -- We can't use restored$typeName here because 
        -- that would infinitely recurse. 
        if rhsTy == lhsTy || !rwr.shouldRestore then baseName(loc, elemName) 
        -- Otherwise pass the appropriate restored type from
        -- this origin into the rule
        else exprAccess(loc, elemName, "restored"++rwr.inputType.typeName));
} 

function applyRwProd
Expr ::= rwr::RewriteRule lhs::String loc::Location ns::NamedSignature
{   
    return rwr.outputStmt(
        fullFunc(loc, 
            rwr.inputProduction.fromJust.name, 
            lhsRestoredTypesAppExprs(lhs, loc, ns.inputNames, map((.typeName), ns.inputTypes), rwr.shouldRestore),
            emptyAnnoAppExprs(location=loc)));
}

function lhsRestoredTypesAppExprs
AppExprs ::= lhs::String loc::Location inputNames::[String] inputTypes::[String] shouldRestore::Boolean
{
    return if length(inputTypes) == 1 
    then oneAppExprs(
        if shouldRestore 
        then namedAccess(loc, "restored"++head(inputTypes), lhs)
        else presentName(loc, head(inputNames)), location=loc)
    else snocAppExprs(lhsRestoredTypesAppExprs(lhs, loc, tail(inputNames), tail(inputTypes), shouldRestore), ',',
            if shouldRestore 
            then namedAccess(loc, "restored"++head(inputTypes), lhs)
            else presentName(loc, head(inputNames)),   
        location=loc);
}