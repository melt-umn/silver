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
    local agDcls1::AGDcl = autocAttr(inhRedexName, mkMaybeString("Origin", location=top.location), location=top.location);

    -- synthesized attribute $tName :: $tType;
    local agDcls2::AGDcl = appendAGDcl(synAttr(tName, tdcl.transType, location=top.location), agDcls1, location=top.location);

    -- synthesized attribute transformed_$tName :: Boolean;
    local agDcls3::AGDcl = appendAGDcl(synAttr(transformNm(tName), mkBoolTypeExpr(location=top.location), location=top.location), agDcls2, location=top.location);    

    -- Occurances of attributes, annotations

    -- Problem in future: only apply this on attributes that they are not 
    -- already defined on. This doesn't work because checking if an attribute
    -- occurs on an element we're working with causes a loop.

    -- for $type in allTypes
    -- attribute inhRedex_$tName occurs on $type;
    local agDcls4::AGDcl = appendAGDcl(attrOn(inhRedexName, absNames++cncNames, location=top.location), agDcls3, location=top.location);

    -- attribute transformed_$tName occurs on $absType;
    local agDcls5::AGDcl = appendAGDcl(attrOn(transformNm(tName), absNames, location=top.location), agDcls4, location=top.location);  

    -- attribute $tName occurs on $absType;
    local agDcls6::AGDcl = appendAGDcl(attrOn(tName, absNames, location=top.location), agDcls5, location=top.location);      
    
    agDcls6.compiledGrammars = top.compiledGrammars;
    agDcls6.grammarName = top.grammarName;
    agDcls6.flowEnv = top.flowEnv;

    forwards to agDcls6;
}

abstract production defineTNameAttributes
top::AGDcl ::= tdcl::Decorated TransformDcl absNames::[String] allNames::[String] absProdDcls::[Decorated NamedSignature]
{
    local tName::String = tdcl.name;
    local inhRedexName::String = inhRedexNm(tName);
    local absProdNames :: [String] = map(unFull, map((.fullName), absProdDcls));

    -- top.$tName = ...
    --  if this abstract production has no transformations defined for it,
    --  then,
    --    if top is the same type as the transformation
    --    then $thisProd($arg.$tName, origin=$thisType_Origin(top), redex=(..).inhRedex_$tName, labels=[])
    --    else don't define this?    ^
    --  else if transformed_$tName   |
    --    then apply transformation  |
    --    else see ------------------/
    agDcls1::AGDcl = foldl(\ agDcls::AGDcl dcl::Decorated NamedSignature ->
        appendAGDcl(aspectProdStmts(dcl,\ ns::Decorated NamedSignature ->
            if !hasTrans(tdcl.transformRules, dcl) && ns.outputElement.typerep.typeName != tdcl.typeName
            then productionStmtsNil(location=top.location)
            else prdStmtList( 
                [attribDef(ns.outputElement.elementName, tdcl.name,
                if !hasTrans(tdcl.transformRules, dcl) 
                then prdRecurse(ns, tdcl.name, absNames, location=top.location)
                else mkCond(
                        lhsExprAccess(transformNm(tdcl.name), ns, location=top.location),
                        injectAnnos(
                            applyTrans(tdcl.transformRules, dcl, location=top.location),
                            annoAppExprList([
                                annExpr("labels", emptyList('[',']', location=top.location), location=top.location),
                                annExpr("redex", exprAccess(inhRedexNm(tdcl.name), inhRedexNameSig(ns, allNames), location=top.location), location=top.location),
                                annExpr("origin", mkOrigin(ns, location=top.location), location=top.location)
                                ], location=top.location), 
                            absProdNames, location=top.location),
                        prdRecurse(ns, tdcl.name, absNames, location=top.location),
                    location=top.location),
            location=top.location)], location=top.location),
            location=top.location), agDcls, location=top.location),
        emptyAGDcl(location=top.location), absProdDcls);

    -- top.transformed_$tName = ...
    --  if this abstract production has no transformation defined for it,
    --  then don't define this
    --  else if the rhs matches this transformation, 
    --    then true
    --    else false
    agDcls2::AGDcl = foldl(\ agDcls::AGDcl dcl::Decorated NamedSignature ->
        if !hasTrans(tdcl.transformRules, dcl) then agDcls 
        else appendAGDcl(aspectProdStmts(dcl,\ ns::Decorated NamedSignature ->
            prdStmtList([
                attribDef(ns.outputElement.elementName, transformNm(tdcl.name),
                    getTrans(tdcl.transformRules, dcl).matchProd, location=top.location)
            ], location=top.location),
            location=top.location), agDcls, location=top.location),
        agDcls1, absProdDcls);

    -- <rhs>.inhRedex_$tName = ...
    --  if this abstract production has no transformation defined for it,
    --  then nothing()
    --  else if transformed$tName
    --    then just($thisType_Origin(top))
    --    else nothing()
    agDcls3::AGDcl = foldl(\ agDcls::AGDcl dcl::Decorated NamedSignature ->
        appendAGDcl(aspectProdStmts(dcl,\ ns::Decorated NamedSignature ->
            foldl(\ stmts::ProductionStmts rhs::NamedSignatureElement ->
                if !contains(unFull(rhs.typerep.typeName), allNames) then stmts else
                productionStmtsSnoc(stmts, 
                    attribDef(rhs.elementName, inhRedexNm(tdcl.name),
                            if !hasTrans(tdcl.transformRules, dcl)
                            then emptyFunc("nothing", location=top.location) -- this might error because it has to be a production
                            else mkCond(
                                lhsExprAccess(transformNm(tdcl.name), ns, location=top.location),
                                argFunc("just", oneApp(mkOrigin(ns, location=top.location), location=top.location), location=top.location),
                                emptyFunc("nothing", location=top.location),
                            location=top.location),
                    location=top.location), location=top.location),
            productionStmtsNil(location=top.location), ns.inputElements), location=top.location), agDcls, location=top.location),
        agDcls2, absProdDcls);

    agDcls3.compiledGrammars = top.compiledGrammars;
    agDcls3.grammarName = top.grammarName;
    agDcls3.flowEnv = top.flowEnv;

    forwards to agDcls3;
}