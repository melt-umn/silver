grammar silver:extension:bidirtransform;

function dclQName
(QName ::= String) ::= loc::Location
{
    return (\ s::String -> qName(loc, s));
}

-- This isn't here because this is difficult,
-- but so we are consistent whenver we create this name
function mkOriginName
String ::= typeName::String
{
    return "origin_" ++ typeName;
}

function inhOriginName
String ::= ns::Decorated NamedSignature
{
    return if !null(ns.inputElements)
        then validInhOrigin(head(ns.inputNames), ns.outputElement.elementName)
        else ns.outputElement.elementName;
}

function validInhOrigin
String ::= test::String def::String
{
    -- For now just check against built ins
    return if test == "Integer" then def
        else if test == "String" then def
        -- else etc.
        else test;
}

function allHead
[a] ::= ls::[a]
{
    return if length(ls) == 1 then [] else head(ls) :: allHead(tail(ls));
}

function hasLocDcl
Boolean ::= dcl::[DclInfo]
{
    return if null(dcl) then false 
        else if head(dcl).isAnnotation && head(dcl).fullName == "location"
        then true
        else hasLocDcl(tail(dcl));
}

function hasNamedAttr
Boolean ::= tyName::String env::Decorated Env hasAttr::String
{
    return containsAttr(getAttrsOn(tyName, env), hasAttr);
}

function containsAttr
Boolean ::= dcl::[DclInfo] hasAttr::String
{   
    return if null(dcl) then false
        else if head(dcl).fullName == hasAttr
        then true
        else containsAttr(tail(dcl), hasAttr); 
}

function transformNm 
String ::= tName::String 
{
    return "transformed_" ++ tName;
}