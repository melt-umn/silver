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

function inhRedexNameSig
String ::= ns::Decorated NamedSignature allowedTypes::[String]
{
    local hd::NamedSignatureElement = head(ns.inputElements);
    local def::String = ns.outputElement.elementName;

    return if null(ns.inputElements) then def
        else if contains(unFull(hd.typerep.typeName), allowedTypes)
        then hd.elementName else def;
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
Boolean ::= tyName::String env::Decorated Env attr::String
{
    return containsAttr(getAttrsOn(tyName, env), attr);
}

function containsAttr
Boolean ::= dcl::[DclInfo] hasAttr::String
{   
    return if null(dcl) then false
        else if unFull(head(dcl).fullName) == unFull(hasAttr)
        then true
        else containsAttr(tail(dcl), hasAttr); 
}

function transformNm 
String ::= tName::String 
{
    return "transformed_" ++ tName;
}

function restoreNm
String ::= rName::String
{
    return "restored_" ++ rName;
}

-- take a full name i.e. "grammar:name" and produce "name"
function unFull
String ::= s::String 
{   
    return if length(s) == 0 then s else 
      substring(lastIndexOf(":", s)+1, length(s), s);
}

function inhRedexNm
String ::= tName::String 
{
    return "inhRedex_" ++ tName;
}

function filterDefs 
[Def] ::= input::[Def]
{
    local hd::Def = head(input);
    local tl::[Def] = filterDefs(tail(input));

    return if null(input) then []
        else case hd of 
            | aliasedLhsDef(_,_,_,_,_) -> tl
            | lhsDef(_,_,_,_) -> tl
            | forwardDef(_,_,_) -> tl
            | prodDclDef(_) -> tl
            -- also try oDef, attrDef
            | _ -> [hd] ++ tl
        end;
}

function headN
[a] ::= input::[a] n::Integer
{
    return if n == 0 then [] 
        else if null(input) then []
        else [head(input)] ++ headN(tail(input), n+1);
}

function sTyExprDec
Decorated TypeExpr ::= s::String loc::Location env::Decorated Env
{
    return decorate sTyExpr(s, location=loc) with {env=env;};
}