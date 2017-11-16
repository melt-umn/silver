grammar silver:extension:bidirtransform;

synthesized attribute transformRules :: [TransformRule];
synthesized attribute rwrules :: RewriteRuleList;

nonterminal TransformRuleList with transformRules, rwrules, env, errors, location, absStrings, cncStrings;
nonterminal TransformRule with matchProd, namedSig, outputStmt, env, errors, location, absStrings, cncStrings;

concrete production transformRuleCons
trl::TransformRuleList ::= Vbar_kwd l::TransformRule r::TransformRuleList
{
    l.env = trl.env;
    r.env = trl.env;

    trl.errors := l.errors ++ r.errors;
    trl.transformRules = [l] ++ r.transformRules;
    trl.absStrings = l.absStrings ++ r.absStrings;
    trl.cncStrings = l.cncStrings ++ r.cncStrings;
    
    -- error check: is the exact transformation l found in r?
    -- equality checking is non trivial so we aren't doing this
    -- trl.errors <- if !contains(r.transformRules, l) then []
    --               else [err(trl.location, "Duplicate transformation rule definition")];
}

concrete production transformRuleSingle
trl::TransformRuleList ::= Vbar_kwd rule::TransformRule 
{
    rule.env = trl.env;
    trl.errors := rule.errors;
    trl.transformRules = [rule];
    trl.absStrings = rule.absStrings;
    trl.cncStrings = rule.cncStrings;
}

concrete production transformRule
tr::TransformRule ::= l::ProductionDef '->' r::Expr
{
    l.env = tr.env;

    tr.namedSig = l.namedSig;
    tr.matchProd = l.matchProd;    
    tr.errors := l.errors ++ r.errors;
    tr.outputStmt = (\ e::Expr ->
        case e of application(_,_,aexpr,_,_,_) ->
            fillExprPattern(r, aexpr, l.patternList)
        end
    );

    tr.absStrings = l.absStrings;
    tr.cncStrings = l.cncStrings;

    -- Do the productions in both the lhs and rhs result in the same type?
    tr.errors <- if !check(l.typerep, r.typerep).typeerror then []
                 else [err(tr.location, "Transformation rule type mismatch")];
}

    -- -- b: are there anonymous variables referred to in the rhs that are not defined in the lhs?
    -- --    (error rhs)
    -- local anonVarsMatch :: Boolean = containsAll(prd.definedAnonVars, trr.providedVars); 
    -- trr.errors <- if anonVarsMatch then [] 
    --               else err(trr.location, "Undefined anonymous variables in transformation rule")
    -- -- c: are there anonymous variables defined in the lhs that are not used in the rhs? 
    -- --    (message: replace these with _)
    -- --    (error rhs)
    -- trr.errors <- if containsAll(trr.providedVars, prd.definedAnonVars) then [] 
    --               else err(trr.location, "Unused anonymous variables in transformation rule")
    -- -- d: are anonymous variables taken from the lhs used as the same type in the rhs?
    -- --    (error rhs)
    -- trr.errors <- if !anonVarsMatch then []
    --               else if typesMatch(prd.definedAnonVars, trr.providedVars) then []
    --               else err(trr.location, "Type mismatch in transformation rule")

function getTrans
Maybe<TransformRule> ::= rules::[TransformRule] dcl::[DclInfo]
{
    return if null(rules) then nothing()
        else case head(dcl) of 
            | prodDcl(_,_,ns) -> if ns.fullName == head(rules).namedSig.fullName 
                then just(head(rules))
                else getTrans(tail(rules), dcl)
            | _ -> getTrans(tail(rules), dcl)
        end;
}