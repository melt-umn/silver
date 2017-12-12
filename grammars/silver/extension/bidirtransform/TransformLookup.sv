grammar silver:extension:bidirtransform;

function hasTrans
Boolean ::= rules::[Decorated TransformRule] dcl::Decorated NamedSignature
{
    local hd::Decorated TransformRule = head(rules);

    return if null(rules) then false
        else if dcl.fullName == hd.namedSig.fullName then true
        else hasTrans(tail(rules), dcl);
}

function getTrans
Decorated TransformRule ::= rules::[Decorated TransformRule] dcl::Decorated NamedSignature
{
    local hd::Decorated TransformRule = head(rules);

    return --if null(rules) then nothing()
        --else if null(dcl) then nothing() else
        if dcl.fullName == hd.namedSig.fullName 
        then hd
        else getTrans(tail(rules), dcl);
}

abstract production applyTrans 
top::Expr ::= rules::[Decorated TransformRule] ns::Decorated NamedSignature
{
    local trans::Decorated TransformRule = getTrans(rules, ns);

    forwards to trans.outputStmt(nsApply(trans.namedSig, location=top.location));
}
