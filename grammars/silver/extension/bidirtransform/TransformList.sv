grammar silver:extension:bidirtransform;

synthesized attribute transformDcls::[Decorated TransformDcl];
synthesized attribute transType::TypeExpr;

nonterminal TransformDcl with transformRules, name, transType, env, errors, location, absGroup, cncGroup, pp, downSubst, upSubst, finalSubst, config, inhProds, typeName;
nonterminal TransformList with transformDcls, env, errors, location, absGroup, cncGroup, pp, downSubst, upSubst, finalSubst, config, inhProds;

concrete production transformDcl
top::TransformDcl ::= qn::QName '::' transType::TypeExpr '{' trRules::TransformRuleList '}'
{
    forwards to mkTransformDcl(qn.name, transType, trRules, location=top.location);
}

abstract production mkTransformDcl
top::TransformDcl ::= s::String transType::TypeExpr trRules::TransformRuleList 
{
    top.pp = s ++ "::" ++ transType.pp ++ "{" ++ trRules.pp ++ "}";
    top.errors := trRules.errors;

    top.transformRules = trRules.transformRules;
    top.transType = transType;
    top.name = s;
    top.typeName = transType.typerep.typeName;
    
    trRules.downSubst = top.downSubst;
    top.upSubst = trRules.upSubst;
    trRules.finalSubst = top.upSubst;
}

concrete production transformListSingle
top::TransformList ::= dcl::TransformDcl
{
    top.pp = dcl.pp;
    top.errors := dcl.errors;

    dcl.downSubst = top.downSubst;
    top.upSubst = dcl.upSubst;
    dcl.finalSubst = top.upSubst;

    top.transformDcls = [dcl];
}

concrete production transformListCons
top::TransformList ::= dcl::TransformDcl lst::TransformList 
{
    top.pp = dcl.pp ++ lst.pp;
    top.errors := dcl.errors ++ lst.errors;

    lst.downSubst = top.downSubst;
    top.upSubst = lst.upSubst;
    lst.finalSubst = top.upSubst;

    top.transformDcls = [dcl] ++ lst.transformDcls;
}

abstract production declareTNameAttributes
top::AGDcl ::= tdcl::Decorated TransformDcl absNames::[String] cncNames::[String]
{

    local tName::String = tdcl.name;
    local inhRedexName::String = inhRedexNm(tName);

    -- autocopy attribute inRedex_$tName :: Maybe<Origin>; 
    local agDcls::AGDcl = autocAttr(inhRedexName, mkMaybeTypeExpr("Origin", location=top.location), location=top.location);

    -- for $cncType in cncTypes
    -- synthesized attribute restored$cncType :: $cncType;
    local agDcls2::AGDcl = foldl(\ agDcls::AGDcl name::String-> 
            appendAGDcl(synAttr(restoreNm(unFull(name)), sTyExpr(name, location=top.location), location=top.location), agDcls, location=top.location),
        agDcls, cncNames);

    -- synthesized attribute $tName :: $tType;
    local agDcls3::AGDcl = appendAGDcl(synAttr(tName, tdcl.transType, location=top.location), agDcls2, location=top.location);

    -- synthesized attribute transformed_$tName :: Boolean;
    local agDcls4::AGDcl = appendAGDcl(synAttr(transformNm(tName), mkBoolTypeExpr(location=top.location), location=top.location), agDcls3, location=top.location);    

    -- Occurances of attributes, annotations

    -- Problem in future: only apply this on attributes that they are not 
    -- already defined on. This doesn't work because checking if an attribute
    -- occurs on an element we're working with causes a loop.

    -- for $type in allTypes
    -- attribute inhRedex_$tName occurs on $type;
    local agDcls5::AGDcl = appendAGDcl(attrOn(inhRedexName, absNames++cncNames, location=top.location), agDcls4, location=top.location);
    
    -- for $absType in absTypes
    -- attribute restored$cncType occurs on Origin, $absType;
    local agDcls6::AGDcl = foldl(\ agDcls::AGDcl name::String->
            appendAGDcl(attrOn(restoreNm(unFull(name)), absNames ++ ["Origin"], location=top.location), agDcls, location=top.location),
        agDcls5, cncNames);

    -- attribute transformed_$tName occurs on $absType;
    local agDcls7::AGDcl = appendAGDcl(attrOn(transformNm(tName), absNames, location=top.location), agDcls6, location=top.location);  

    -- attribute $tName occurs on $absType;
    local agDcls8::AGDcl = appendAGDcl(attrOn(tName, absNames, location=top.location), agDcls7, location=top.location);      
    
    agDcls8.compiledGrammars = top.compiledGrammars;
    agDcls8.grammarName = top.grammarName;
    agDcls8.flowEnv = top.flowEnv;

    forwards to agDcls8;
}
