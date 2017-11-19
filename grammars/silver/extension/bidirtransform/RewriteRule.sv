grammar silver:extension:bidirtransform;

synthesized attribute rewriteRules::[RewriteRule];
synthesized attribute outputStmt::(Expr ::= Expr);
synthesized attribute inputType::Type;
synthesized attribute inputProduction::Maybe<RewriteProduction>;
synthesized attribute shouldRestore::Boolean;
synthesized attribute absStrings::[String];
synthesized attribute cncStrings::[String];

nonterminal RewriteRuleList with rewriteRules, env, errors, location, absStrings, cncStrings, pp;
nonterminal RewriteRule with inputType, inputProduction, typerep, outputStmt, shouldRestore, env, errors, location, absGroup, cncGroup, pp;
nonterminal RewriteProduction with name, inputNames, typerep, env, errors, location, absGroup, cncGroup, pp;
nonterminal RewriteProductionArgs with inputNames, errors, pp;

terminal RestoreArrow_t '~~>' lexer classes {SPECOP};

concrete production rewriteRuleCons
rrl::RewriteRuleList ::= Vbar_kwd l::RewriteRule r::RewriteRuleList
{
    l.env = rrl.env;
    r.env = rrl.env;

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
    rule.env = rrl.env;

    rrl.pp = "| " ++ rule.pp;

    rrl.rewriteRules = [rule];
    rrl.errors := rule.errors;
}

-- rewrite an abstract production as a concrete production
concrete production rewriteRuleProd
rule::RewriteRule ::= prd::RewriteProduction '->' e::Expr
{
    rule.pp = prd.pp ++ "->" ++ e.pp;

    forwards to rewriteRule(e, "", prd.typerep, e.typerep, just(prd), false, location=rule.location);
}

-- rewrite an abstract production as a concrete production
concrete production rewriteRuleRestoreProd
rule::RewriteRule ::= prd::RewriteProduction '~~>' e::Expr
{
    rule.pp = prd.pp ++ "~~>" ++ e.pp;
    
    forwards to rewriteRule(e, "", prd.typerep, e.typerep, just(prd), true, location=rule.location);
}

-- rewrite a concrete type as another concrete type through plugging it into
-- an expression
concrete production rewriteRuleType
rule::RewriteRule ::= name::QName '::' t::TypeExpr '->' e::Expr 
{
    rule.pp = name.pp ++ "::" ++ t.pp ++ "->" ++ e.pp;

    forwards to rewriteRule(e, name.name, t.typerep, e.typerep, nothing(), false, location=rule.location);    
}

-- rewrite a concrete type as another concrete type through plugging it into
-- an expression, and referring to its own restored$t element
concrete production rewriteRuleRestoreType
rule::RewriteRule ::= name::QName '::' t::TypeExpr '~~>' e::Expr
{
    rule.pp = name.pp ++ "::" ++ t.pp ++ "~~>" ++ e.pp;

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
prd::RewriteProduction ::= qn::QName '(' args::RewriteProductionArgs ')'
{
    prd.pp = qn.pp ++ "(" ++ args.pp ++ ")";

    prd.inputNames = args.inputNames;
    prd.name = qn.name;

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
} 

concrete production rewriteProductionArgMany
arg::RewriteProductionArgs ::= args::RewriteProductionArgs ',' name::QName {
    arg.pp = args.pp ++ "," ++ name.pp;
    arg.inputNames = args.inputNames ++ [name.name];
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
Expr ::= rwr::RewriteRule lhs::String loc::Location ns::Decorated NamedSignature
{   
    return rwr.outputStmt(
        fullFunc(
            rwr.inputProduction.fromJust.name, 
            lhsRestoredTypesAppExprs(lhs, loc, ns.inputNames, map((.typeName), ns.inputTypes), rwr.shouldRestore),
            emptyAnnoAppExprs(location=loc), loc));
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



-- todo: these assume there is only one rewrite 
-- rule for any given rewrite output type, or at
-- least ignores any others that exist after head().
-- 
-- Changing this in a meaningful way would involve
-- no longer requiring that every type has all
-- restored$type variants defined, then doing
-- a search to find a pair of defined rewrite rules
-- that produce the expected rhs output while 
-- using a defined restored type on the lhs, and 
-- if that fails not attempting to define that
-- rhs type on this lhs type. 

-- Return either rwProd or rwID, preferring the former, or nothing.
function rwMatch
Maybe<RewriteRule> ::= rwrs::[RewriteRule] outType::String ns::Decorated NamedSignature
{
    return case rwProd(rwrs, outType, ns) of
        | nothing() -> rwID(rwrs, ns.typerep.typeName, outType)
        | just(rule) -> just(rule)
    end;
}


-- Return a rule which operates on the arguments of the production defined
-- by ns and returns outType
function rwProd
Maybe<RewriteRule> ::= rwrs::[RewriteRule] outType::String ns::Decorated NamedSignature
{
    local hd::RewriteRule = head(rwrs);

    return if null(rwrs) then nothing()
        else if hd.inputProduction.isJust &&
                hd.inputProduction.fromJust.name == ns.fullName &&
                hd.typerep.typeName == outType
        then just(hd)
        else rwProd(tail(rwrs), outType, ns);
}

-- Return a rule which takes in tyName and returns outType
function rwID
Maybe<RewriteRule> ::= rwrs::[RewriteRule] inType::String outType::String 
{
    local hd::RewriteRule = head(rwrs);

    return if null(rwrs) then nothing()
        else if hd.typerep.typeName == outType &&  hd.inputType.typeName == inType 
        then just(hd)
        else rwID(tail(rwrs), inType, outType);
}