grammar silver:extension:bidirtransform;

abstract production declareTNameAttributes
top::AGDcl ::= tdcl::Decorated TransformDcl absNames::[String] cncNames::[String]
{
    -- Define an overarching transform name that will act as the combination
    -- of all individual transform rules. 
    -- synthesized attribute $tName :: $tType;
    local agDcls1::AGDcl = synAttr(tdcl.name, tdcl.transType, location=top.location);

    agDcls1.compiledGrammars = top.compiledGrammars;
    agDcls1.grammarName = top.grammarName;
    agDcls1.flowEnv = top.flowEnv;

    forwards to foldl(\ agDcls::AGDcl tr::Decorated TransformRule ->
        appendAGDcl(declareTRuleAttributes(tr, tdcl.name, tdcl.transType, absNames, cncNames),
            agDlcs, location=top.location),
    agDcls1, tdcl.transformRules);
}
    
abstract production declareTRuleAttributes
top::AGDcl ::= tr::Decorated TransformRule nm::String ty::TypeExpr absNames::[String] cncNames::[String]
{   
    -- For this transform rule, define a unique attribute
    local tName::String = nm ++ toString(tr.trIndex);
    local inhRedexName::String = inhRedexNm(tName);

    -- autocopy attribute inRedex_$tName :: Maybe<Origin>; 
    local agDcls1::AGDcl = autocAttr(inhRedexName, mkMaybeString("Origin", location=top.location), location=top.location);

    -- synthesized attribute $tName :: $tType;
    local agDcls2::AGDcl = appendAGDcl(synAttr(tName, ty, location=top.location), agDcls1, location=top.location);

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
    -- for each abstract production,
    -- top.$tName = ... chain all rules together, e.g.
    -- top.$tName = top.$tName1.$tName2.$tName3.$tName4
    local agDcls1::AGDcl = foldl(\ agDcls::AGDcl dcl::Decorated NamedSignature ->
        appendAGDcl(aspectProdStmts(dcl,\ ns::Decorated NamedSignature ->
            prdStmtList([
                attribDef(ns.outputElement.elementName, tdcl.name, 
                    transAccess(tdcl.name, tdcl.transformRules, ns.outputElement.elementName, location=top.location),
                location=top.location)
            ], location=top.location)
        emptyAGDcl(location=top.location), absProdDcls);

    agDcls1.compiledGrammars = top.compiledGrammars;
    agDcls1.grammarName = top.grammarName;
    agDcls1.flowEnv = top.flowEnv;

    forwards to foldl(\ agDcls::AGDcl tr::Decorated TransformRule ->
        appendAGDcl(defineTRuleAttributes(tr, tdcl.name, tdcl.transType, absNames, allNames, absProdDcls),
            agDlcs, location=top.location),
    agDcls1, tdcl.transformRules);
}

abstract production defineTRuleAttributes
top::AGDcl ::= tr::Decorated TransformRule nm::String ty::TypeExpr absNames::[String] allNames::[String] absProdDcls::[Decorated NamedSignature]
{
    local tName::String = nm ++ toString(tr.trIndex);
    local inhRedexName::String = inhRedexNm(tName);
    local transformName::String = transformNm(tName);
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
    local agDcls1::AGDcl = foldl(\ agDcls::AGDcl dcl::Decorated NamedSignature ->
        appendAGDcl(aspectProdStmts(dcl,\ ns::Decorated NamedSignature ->
            if !hasTrans([tr], dcl) && ns.outputElement.typerep.typeName != ty
            then productionStmtsNil(location=top.location)
            else prdStmtList( 
                [attribDef(ns.outputElement.elementName, tName,
                if !hasTrans([tr], dcl) 
                then prdRecurse(ns, tName, absNames, location=top.location)
                else mkCond(
                        lhsExprAccess(transformName, ns, location=top.location),
                        injectAnnos(
                            applyTrans([tr], dcl, location=top.location),
                            annoAppExprList([
                                annExpr("labels", emptyList('[',']', location=top.location), location=top.location),
                                annExpr("redex", exprAccess(inhRedexName, inhRedexNameSig(ns, allNames), location=top.location), location=top.location),
                                annExpr("origin", mkOrigin(ns, location=top.location), location=top.location)
                                ], location=top.location), 
                            absProdNames, location=top.location),
                        prdRecurse(ns, tName, absNames, location=top.location),
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
    local agDcls2::AGDcl = foldl(\ agDcls::AGDcl dcl::Decorated NamedSignature ->
        if !hasTrans([tr], dcl) then agDcls 
        else appendAGDcl(aspectProdStmts(dcl,\ ns::Decorated NamedSignature ->
            prdStmtList([
                attribDef(ns.outputElement.elementName, transformName,
                    getTrans([tr], dcl).matchProd, location=top.location)
            ], location=top.location),
            location=top.location), agDcls, location=top.location),
        agDcls1, absProdDcls);

    -- <rhs>.inhRedex_$tName = ...
    --  if this abstract production has no transformation defined for it,
    --  then nothing()
    --  else if transformed$tName
    --    then just($thisType_Origin(top))
    --    else nothing()
    local agDcls3::AGDcl = foldl(\ agDcls::AGDcl dcl::Decorated NamedSignature ->
        appendAGDcl(aspectProdStmts(dcl,\ ns::Decorated NamedSignature ->
            foldl(\ stmts::ProductionStmts rhs::NamedSignatureElement ->
                if !contains(unFull(rhs.typerep.typeName), allNames) then stmts else
                productionStmtsSnoc(stmts, 
                    attribDef(rhs.elementName, inhRedexName,
                            if !hasTrans([tr], dcl)
                            then emptyFunc("nothing", location=top.location) -- this might error because it has to be a production
                            else mkCond(
                                lhsExprAccess(transformName, ns, location=top.location),
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