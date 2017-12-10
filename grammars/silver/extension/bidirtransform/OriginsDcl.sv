grammar silver:extension:bidirtransform;

concrete production originDcl
top::AGDcl ::= 'apply' 'origins' 'on' nts::NonterminalList ';'
{
    forwards to applyOrigins(nts.ntList, location=top.location);
}

concrete production cncOriginDcl
top::AGDcl ::= 'apply' 'concrete' 'origins' 'on' nts::NonterminalList ';'
{
    forwards to cncApplyOrigins(nts.ntList, location=top.location);
}

abstract production cncApplyOrigins
top::AGDcl ::= ntList::[Decorated FullNonterminal]
{
    local cncNames :: [String] = map((.name), ntList);

    -- attribute suppliedOrigin occurs on $cncType;
    local agDcls::AGDcl = attrOn("suppliedOrigin", cncNames);

    -- abstract production origin_$type
    -- o::Origin ::= e::Decorated $type
    -- {
    --      o.isBottomOrigin = false;
    -- }
    --
    local agDcls2::AGDcl = appendAGDcl(foldl(\ agDcls::AGDcl qn::String->
         appendAGDcl(productionDcl('abstract', 'production', 
            name(mkOriginName(qn),top.location), mkProdSigDec("o", "Origin", "e", qn),
                prdBody([
                    attribDef("o", "isBottomOrigin", mkFalse())
                ])),
            agDcls),
        emptyAGDcl(), cncNames), agDcls);

    -- aspect all cnc origins with:
    --
    -- o.wasTransformed = false;
    -- o.concreteOrigin = o;
    local agDcls3::AGDcl = foldl(\ agDcls::AGDcl name::String->
        appendAGDcl(aspectProductionDcl('aspect', 'production', 
            qName(top.location, mkOriginName(name)), mkAspectProdSigDec("o", "Origin", "e", name),
                prdBody([
                    attribDef("o", "wasTransformed", mkFalse()),
                    attribDef("o", "concreteOrigin", baseName("o"))
                ])), agDcls),
        agDcls2, cncNames);

    default annotation location = top.location;    
    
    forwards to agDcls3;
}

abstract production applyOrigins 
top::AGDcl ::= ntList::[Decorated FullNonterminal]
{
    local absNames :: [String] = map((.name), ntList);    

    -- Occurances of attributes, annotations

    -- Problem in future: only apply this on attributes that they are not 
    -- already defined on. This doesn't work because checking if an attribute
    -- occurs on an element we're working with causes a loop.

    -- attribute wasTransformed occurs on $absType;
    local agDcls::AGDcl = attrOn("wasTransformed", absNames);

    -- abstract production origin_$type
    -- o::Origin ::= e::Decorated $type
    -- {
    --      o.isBottomOrigin = false;
    -- }
    --
    local agDcls2::AGDcl = appendAGDcl(foldl(\ agDcls::AGDcl qn::String->
         appendAGDcl(productionDcl('abstract', 'production', 
            name(mkOriginName(qn),top.location), mkProdSigDec("o", "Origin", "e", qn),
                prdBody([
                    attribDef("o", "isBottomOrigin", mkFalse())
                ])),
            agDcls),
        emptyAGDcl(), absNames), agDcls);

    -- aspect all abstract origins with:
    --
    -- o.wasTransformed = wasTransformed(e.origin, e.redex);
    -- o.concreteOrigin = getConcreteOrigin(e.origin, o);
    local agDcls3::AGDcl = foldl(\ agDcls::AGDcl name::String->
        appendAGDcl(aspectProductionDcl('aspect', 'production', 
            qName(top.location, mkOriginName(name)), mkAspectProdSigDec("o", "Origin", "e", name),
                prdBody([
                attribDef("o", "wasTransformed",
                    argFunc("wasTransformed", appExprList([
                        namedAccess("redex", "e"),
                        namedAccess("origin", "e")
                    ]))),
                attribDef("o", "concreteOrigin", 
                    argFunc("getConcreteOrigin", appExprList([
                        presentName("o"),
                        namedAccess("origin", "e")
                    ])))
                ])), agDcls),
        agDcls2, absNames);

    -- annotation redex occurs on $absType;
    local agDcls4::AGDcl = appendAGDcl(annoOn("redex", absNames), agDcls3);
    
    -- annotation labels occurs on $absType;
    local agDcls5::AGDcl = appendAGDcl(annoOn("labels", absNames), agDcls4);
    
    -- annotation origin occurs on $absType;
    local agDcls6::AGDcl = appendAGDcl(annoOn("origin", absNames), agDcls5);
    
    default annotation location = top.location;

    forwards to agDcls6;
}

-- -- this uses QNames because Names is commented out right now of the silver convenience source

-- -- copy attributes from origin's RHS elements to their LHS for all RHS that can. 
-- -- example usage: pp. 
-- concrete production originAttributeDcl
-- top::AGDcls ::= 'origins' 'attribute' qns::QNameList '->>' subAg::AGDcls
-- {
--     default annotation location = top.location;

--     local addAttrs::AGDcl = originAttributes(qns);

--     addAttrs.env = subAg.env;

--     top.moduleNames = [];
--     top.mdaSpecs = [];
    
--     subAg.compiledGrammars = top.compiledGrammars;
--     subAg.config = top.config;
--     subAg.grammarName = top.grammarName;
--     subAg.flowEnv = top.flowEnv;
--     subAg.env = appendEnv(top.env, toEnv(addAttrs.defs));

--     top.defs = subAg.defs; 

--     --forwards to consAGDcls(addAttrs, subAg);

--     top.ideSpecs = addAttrs.ideSpecs ++ subAg.ideSpecs;
--     top.syntaxAst = addAttrs.syntaxAst ++ subAg.syntaxAst;
--     top.parserSpecs = addAttrs.parserSpecs ++ subAg.parserSpecs;
--     top.flowDefs = addAttrs.flowDefs ++ subAg.flowDefs;
--     top.docs := addAttrs.docs ++ subAg.docs;
--     top.docsHeader = addAttrs.docsHeader ++ subAg.docsHeader;
--     top.docsSplit = addAttrs.docsSplit ++ subAg.docsSplit;
--     top.docsNoDoc = addAttrs.docsNoDoc || subAg.docsNoDoc;
--     top.docDcls := addAttrs.docDcls ++ subAg.docDcls;
--     top.genFiles := addAttrs.genFiles ++ subAg.genFiles;
--     top.setupInh := addAttrs.setupInh ++ subAg.setupInh;
--     top.initProd := addAttrs.initProd ++ subAg.initProd;
--     top.initValues := addAttrs.initValues ++ subAg.initValues;
--     top.postInit := addAttrs.postInit ++ subAg.postInit;
--     top.initWeaving := addAttrs.initWeaving ++ subAg.initWeaving;
--     top.valueWeaving := addAttrs.valueWeaving ++ subAg.valueWeaving;
--     top.errors := addAttrs.errors ++ subAg.errors;
-- }

-- abstract production originAttributes
-- top::AGDcl ::= qns::QNameList
-- {
--     local qnsTail::QNameList = case qns of
--         | qNameListCons(_,_,tl) -> tl
--         | _ -> qNameListEmpty()
--     end;

--     default annotation location = top.location;

--     forwards to if null(qns.qList) then emptyAGDcl()
--       else appendAGDcl(originAttribute(head(qns.qList)), originAttributes(qnsTail));
-- }

-- abstract production originAttribute
-- top::AGDcl ::= qn::QName
-- {      
--     local oProds::[Decorated NamedSignature] = 
--       prodsFromDcls(getProdsForNt("Origin", top.env));
--     --prodsFromDcls(getProdsFromNtHack("Origin", new(top.env), "silver:extension:bidirtransform"));

--     default annotation location = top.location;

--     forwards to appendAGDcl(
--         attrOn(qn.name, ["Origin"]),
--         -- find all origin productions and give them this attribute if it's defined on their RHS
--         foldl(\ agDcls::AGDcl ns::Decorated NamedSignature ->
--             appendAGDcl(
--                     if null(ns.inputTypes) then emptyAGDcl()
--                     else if hasNamedAttr(head(ns.inputTypes).typeName, top.env, qn.name)
--                     then aspectProdStmt([ns],\ ns::Decorated NamedSignature ->
--                             attribDef(ns.outputElement.elementName, qn.name, 
--                                 exprAccess(qn.name, head(ns.inputNames))))
--                     else emptyAGDcl(),
--                 agDcls),
--           emptyAGDcl(), oProds));
-- }

-- -- todo: this should act like the above, but defines a Maybe<T>, new attribute
-- -- instead of the existing attribute, so the presence of the attribute can be checked.
-- concrete production optOriginAttributeDcl
-- top::AGDcl ::= 'optional' 'origins' 'attribute' qns::QNameList ';'
-- {
--     default annotation location = top.location;    

--     forwards to appendAGDcl(writeOptAttributes(qns, ""), optOriginAttributes(qns, ""));
-- }

-- concrete production optOriginAttributeDclPrefix
-- top::AGDcl ::= 'optional' 'origins' 'attribute' qns::QNameList 'with' 'prefix' pfix::QName ';'
-- {
--     default annotation location = top.location;
    
--     forwards to appendAGDcl(writeOptAttributes(qns, pfix.name), optOriginAttributes(qns, pfix.name));
-- }


-- abstract production writeOptAttributes
-- top::AGDcl ::= qns::QNameList pfix::String 
-- {
--     local prefixedNames::[String] = map(\ qn::QName -> 
--         pfix ++ qn.name, 
--         qns.qList);

--     local tyExprs::[TypeExpr] = map(\ qn::QName ->
--         mkMaybeTypeExpr(head(getAttrDcl(qn.name, top.env)).typerep.typeName),
--         qns.qList);

--     top.errors <- foldl(\ errs::[Message] qn::QName ->
--         if null(getAttrDcl(qn.name, top.env)) 
--         then [err(qn.location, "Unknown attribute " ++ qn.name)] ++ errs
--         else errs,
--         [], qns.qList);

--     default annotation location = top.location;

--     forwards to foldl(\ agDcls::AGDcl attr::Pair<String TypeExpr> ->
--         appendAGDcl(synAttr(attr.fst, attr.snd), agDcls),
--         emptyAGDcl(), zipWith(pair, prefixedNames, tyExprs));
-- }

-- abstract production optOriginAttributes
-- top::AGDcl ::= qns::QNameList pfix::String
-- {
--     local qnsTail::QNameList = case qns of qNameListCons(_,_,tl) -> tl end;

--     default annotation location = top.location;

--     forwards to if null(qns.qList) then emptyAGDcl()
--       else appendAGDcl(optOriginAttribute(head(qns.qList), pfix), optOriginAttributes(qnsTail, pfix));
-- }

-- abstract production optOriginAttribute
-- top::AGDcl ::= qn::QName pfix::String
-- {      
--     local oProds::[Decorated NamedSignature] = 
--       prodsFromDcls(getProdsFromNtHack("Origin", top.env, "transformed"));

--     local lhsAttr::String = pfix ++ qn.name;

--     default annotation location = top.location;

--     forwards to appendAGDcl(
--         attrOn(qn.name, ["Origin"]),
--         -- find all origin productions and give them just(this attribute) if it's defined on their RHS,
--         -- otherwise nothing().
--         foldl(\ agDcls::AGDcl ns::Decorated NamedSignature ->
--             appendAGDcl(
--                     if null(ns.inputTypes) then emptyAGDcl()
--                     else if hasNamedAttr(head(ns.inputTypes).typeName, top.env, qn.name)
--                     then aspectProdStmt([ns],\ ns::Decorated NamedSignature ->
--                             attribDef(ns.outputElement.elementName, lhsAttr, 
--                                 oneArgFunc("just", namedAccess(qn.name, head(ns.inputNames)))))
--                     else aspectProdStmt([ns],\ ns::Decorated NamedSignature ->
--                             attribDef(ns.outputElement.elementName, lhsAttr, emptyFunc("nothing"))),
--                 agDcls),
--           emptyAGDcl(), oProds));
-- }