grammar silver:extension:bidirtransform;

synthesized attribute transformRules :: [TransformRule];
synthesized attribute rwrules :: RewriteRuleList;

nonterminal TransformRuleList with transformRules, rwrules, env, errors, location, absGroup, cncGroup, pp;
nonterminal TransformRule with matchProd, namedSig, outputStmt, env, errors, location, absGroup, cncGroup, pp;

concrete production transformRuleCons
trl::TransformRuleList ::= Vbar_kwd l::TransformRule r::TransformRuleList
{
    l.env = trl.env;
    r.env = trl.env;

    trl.pp = "|" ++ l.pp ++ r.pp;

    trl.errors := l.errors ++ r.errors;
    trl.transformRules = [l] ++ r.transformRules;
    
    -- error check: is the exact transformation l found in r?
    -- equality checking is non trivial so we aren't doing this
    -- trl.errors <- if !contains(r.transformRules, l) then []
    --               else [err(trl.location, "Duplicate transformation rule definition")];
}

concrete production transformRuleSingle
trl::TransformRuleList ::= Vbar_kwd rule::TransformRule 
{
    rule.env = trl.env;
    
    trl.pp = "|" ++ rule.pp;

    trl.errors := rule.errors;
    trl.transformRules = [rule];
}


function getTrans
Maybe<TransformRule> ::= rules::[TransformRule] dcl::[Decorated NamedSignature]
{
    return if null(rules) then nothing()
        else if null(dcl) then nothing()
        else if head(dcl).fullName == head(rules).namedSig.fullName 
            then just(head(rules))
            else getTrans(tail(rules), dcl);
}