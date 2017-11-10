grammar silver:extension:bidirtransform;

autocopy attribute attrName :: QName;

synthesized attribute transformRules :: [TransformRule];
synthesized attribute cncTypeDcls :: [DclInfo];
synthesized attribute rwrules :: RewriteRuleList;

nonterminal TransformRuleList with matchProd, attrName, transformRules, rwrules, env, errors;
nonterminal TransformRule with attrName, env, errors;

concrete production transformRuleCons
trl::TransformRuleList ::= '|' l::TransformRule r::TransformRuleList
{
    l.env = trl.env;
    r.env = trl.env;

    trl.errors := l.errors ++ r.errors;
    
    -- error check: is the exact transformation l found in r?
    trl.errors <- if !contains(r.transformRules, l) then []
                  else [err(trl.location, "Duplicate transformation rule definition")];
}

concrete production transformRuleSingle
trl::TransformRuleList ::= '|' rule::TransformRule 
{
    rule.env = trl.env;

    trl.cncTypeDcls = rule.cncTypeDcls;
    trl.errors := rule.errors;
}

concrete production transformRule
tr::TransformRule ::= l::ProductionDef '->' r::Expr
{
    l.env = tr.env;

    tr.matchProd = l.matchProd;    
    tr.errors := l.errors ++ r.errors;
    tr.outputStmt = (\ e::Expr ->
        case e of application(_,_,aexpr,_,_,_) ->
            fillExprPattern(r, aexpr, l.patternList)
        end
    );

    -- Do the productions in both the lhs and rhs result in the same type?
    tr.errors <- if !check(l.typerep, r.typerep).typeerror then []
                 else [err(tr.location, "Transformation rule type mismatch")];
}

nonterminal TransformRuleRHS with typerep, env, errors;

concrete production transformRuleRHS
trr::TransformRuleRHS ::= e::Expr
{
    trr.errors := e.errors;
    trr.typerep = e.typerep;
    
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
}
