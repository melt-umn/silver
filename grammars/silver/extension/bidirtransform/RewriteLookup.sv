grammar silver:extension:bidirtransform;

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

function hasRwMatch
Boolean ::= rwrs::[Decorated RewriteRule] outType::String ns::Decorated NamedSignature
{
    return hasRwProd(rwrs, outType, ns) || hasRwID(rwrs, unFull(ns.typerep.typeName), unFull(outType));
}

-- Return either rwProd or rwID, preferring the former.
function rwMatch
Decorated RewriteRule ::= rwrs::[Decorated RewriteRule] outType::String ns::Decorated NamedSignature
{
    return if hasRwProd(rwrs, outType, ns) 
        then rwProd(rwrs, outType, ns)
        else rwID(rwrs, unFull(ns.typerep.typeName), outType);
}

function hasRwProd
Boolean ::= rwrs::[Decorated RewriteRule] outType::String ns::Decorated NamedSignature
{
    local hd::Decorated RewriteRule = head(rwrs);

    return if null(rwrs) then false
        else if hd.hasProduction &&
                unFull(hd.inputProduction.name) == unFull(ns.fullName) &&
                unFull(hd.typerep.typeName) == unFull(outType)
        then true
        else hasRwProd(tail(rwrs), outType, ns);
}

function hasRwID
Boolean ::= rwrs::[Decorated RewriteRule] inType::String outType::String 
{
    return hasRwEq(rwrs, inType, outType) || hasRwOut(rwrs, outType);
}

function hasRwEq
Boolean ::= rwrs::[Decorated RewriteRule] inType::String outType::String 
{
    local hd::Decorated RewriteRule = head(rwrs);

    return if null(rwrs) then false
        else if !hd.hasProduction && 
            unFull(hd.typerep.typeName) == unFull(outType) &&
            unFull(hd.inputType.typeName) == unFull(inType)
        then true
        else hasRwEq(tail(rwrs), inType, outType);
}

function hasRwOut
Boolean ::= rwrs::[Decorated RewriteRule] outType::String
{
    local hd::Decorated RewriteRule = head(rwrs);    

    return if null(rwrs) then false
        else if !hd.hasProduction && 
            unFull(hd.typerep.typeName) == unFull(outType)
        then true
        else hasRwOut(tail(rwrs), outType);
}   

-- Return a rule which operates on the arguments of the production defined
-- by ns and returns outType
function rwProd
Decorated RewriteRule ::= rwrs::[Decorated RewriteRule] outType::String ns::Decorated NamedSignature
{
    local hd::Decorated RewriteRule = head(rwrs);

    return -- if null(rwrs) then nothing() else
        if hd.hasProduction &&
            unFull(hd.inputProduction.name) == unFull(ns.fullName) &&
            unFull(hd.typerep.typeName) == unFull(outType)
        then hd
        else rwProd(tail(rwrs), outType, ns);
}

function rwID
Decorated RewriteRule ::= rwrs::[Decorated RewriteRule] inType::String outType::String 
{
    return if hasRwEq(rwrs, inType, outType) 
        then rwEq(rwrs, inType, outType)
        else rwOut(rwrs, outType);
}

function rwEq
Decorated RewriteRule ::= rwrs::[Decorated RewriteRule] inType::String outType::String 
{
    local hd::Decorated RewriteRule = head(rwrs);

    return if null(rwrs) then false
        else if !hd.hasProduction && 
            unFull(hd.typerep.typeName) == unFull(outType) &&
            unFull(hd.inputType.typeName) == unFull(inType)
        then hd
        else rwEq(tail(rwrs), inType, outType);
}

-- Return a rule which returns outType
function rwOut
Decorated RewriteRule ::= rwrs::[Decorated RewriteRule] outType::String 
{
    local hd::Decorated RewriteRule = head(rwrs);

    return -- if null(rwrs) then nothing() else
        if !hd.hasProduction &&
            unFull(hd.typerep.typeName) == unFull(outType)
        then hd
        else rwOut(tail(rwrs), outType);
}

