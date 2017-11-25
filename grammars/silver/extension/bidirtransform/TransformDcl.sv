grammar silver:extension:bidirtransform;

imports silver:definition:env;
imports silver:definition:core;
imports silver:definition:concrete_syntax;
imports silver:definition:type;
imports silver:definition:type:syntax;
imports silver:extension:convenience;
imports silver:extension:list;
imports silver:extension:patternmatching;
imports silver:util;
imports silver:modification:let_fix;
imports silver:modification:primitivepattern; 
imports silver:modification:copper; 
imports silver:modification:defaultattr;
imports silver:modification:ffi;
imports silver:modification:autocopyattr;
imports silver:modification:impide;

terminal Transform_kwd 'transmute' lexer classes {KEYWORD,RESERVED};
terminal Rewrite_kwd 'rewrite' lexer classes {KEYWORD,RESERVED};
terminal From_kwd 'from' lexer classes{KEYWORD,RESERVED};

concrete production transformAGDclFull
ag::AGDcls ::= 'transmute' '{' subAg::AGDcls '}' qn::QName '::' transType::TypeExpr 
    '{' trRules::TransformRuleList '}' 
    'rewrite' '{' rwRules::RewriteRuleList '}' 
    -- 'from' cncGroupName::QName 'to' absGroupName::QName ';'
    'from' cncGroupIn::NonterminalList 'to' absGroupIn::NonterminalList ';'
{
    ag.pp = "transmute " ++ qn.pp ++ "::" ++ transType.pp ++
        "{" ++ trRules.pp ++ "} rewrite {" ++ rwRules.pp ++ "};";
        --"} abstract {" ++ absNames.pp ++ "} concrete {" ++ cncNames.pp ++ "};";

    -- local absGroups::[Decorated NonterminalList] = searchNtGroup(absGroupName.name, ag.env);
    -- local cncGroups::[Decorated NonterminalList] = searchNtGroup(cncGroupName.name, ag.env);

    -- ag.errors := if length(absGroups) != 0 then []
    --     else [err(ag.location, "Unknown nonterminal group " ++ absGroupName.name)];

    -- ag.errors <- if length(cncGroups) != 0 then []
    --     else [err(ag.location, "Unknown nonterminal group " ++ cncGroupName.name)];

    -- local toForward::AGDcl = transformRewrite(tName.name, transType, trRules, rwRules, 
    --     head(absGroup), head(cncGroup), location=ag.location);

    local tName::String = qn.name;
    -- local absGroup::Decorated NonterminalList = head(absGroups);
    -- local cncGroup::Decorated NonterminalList = head(cncGroups);
    local absGroup::Decorated NonterminalList = decorate absGroupIn with { env=ag.env; };
    local cncGroup::Decorated NonterminalList = decorate cncGroupIn with { env=ag.env; };

    trRules.config = ag.config;
    rwRules.config = ag.config;

    -- todo?
    trRules.downSubst = emptySubst();
    rwRules.downSubst = emptySubst();
    trRules.finalSubst = rwRules.upSubst;
    rwRules.finalSubst = trRules.finalSubst;

--     ag.defs = [lockDef()] ++ toForward.defs;

--     forwards to toForward;
-- }

-- abstract production transformRewrite 
-- ag::AGDcl ::= tName::String transType::TypeExpr 
--     trRules::TransformRuleList 
--     rwRules::RewriteRuleList
--     absGroup::NonterminalList  
--     cncGroup::NonterminalList
-- {

    ----------------
    -- Propagation of attributes

    ag.errors <- trRules.errors ++ rwRules.errors;

    trRules.absGroup = absGroup;
    trRules.cncGroup = cncGroup;

    trRules.env = ag.env;

    -- absGroup.env = ag.env;
    -- cncGroup.env = ag.env;

    -- ag.moduleNames = [];
    -- ag.terminalPrefixes = [];

    -----------------
    -- Initialization of lists of things we need to know

    -- local locCncNamesPair :: Pair<[String] [String]> = partition(\ s::String -> 
    --     hasLocDcl(getAttrsOn(s,ag.env)),
    -- cncStrings);
    -- local locCncNames :: [String] = locCncNamesPair.fst;
    -- local nonLocCncNames :: [String] = locCncNamesPair.snd;
    
    -- We need to know everything's name

    local absNames :: [String] = map((.name), absGroup.ntList);    
    local cncNames :: [String] = map((.name), cncGroup.ntList);
    local locCncNames :: [String] = [];
    local nonLocCncNames :: [String] = cncNames;
    local allNames :: [String] = cncNames ++ absNames;

    -- We need to know all the productions on all of the known types

    local absProdDcls :: [[Decorated NamedSignature]] = map((.ntProds), absGroup.ntList);
    local cncProdDcls :: [[Decorated NamedSignature]] = map((.ntProds), cncGroup.ntList);
    local locCncProdDcls :: [[Decorated NamedSignature]] = [];
    local nonLocCncProdDcls :: [[Decorated NamedSignature]] = cncProdDcls;
    local allProdDcls :: [[Decorated NamedSignature]] = absProdDcls ++ cncProdDcls;

    -----------------------
    -- Generating code

    -- New attributes and annotations

    local inhRedexName::String = "inhRedex_" ++ tName;

    -- autocopy attribute inRedex_$tName :: Maybe<Origin>; 
    local agDcls::AGDcl = autocAttr(inhRedexName,
        nominalTypeExpr(qnTyId("Maybe", location=ag.location), 
        botlOneString("Origin", location=ag.location), location=ag.location), location=ag.location);

    -- for $cncType in cncTypes
    -- synthesized attribute restored$cncType :: $cncType;
    local agDcls2::AGDcl = foldl(\ agDcls::AGDcl name::String-> 
            appendAGDcl(synAttr(restoreNm(name), sTyExpr(name, location=ag.location), location=ag.location), agDcls, location=ag.location),
        agDcls, cncNames);

    -- synthesized attribute $tName :: $tType;
    local agDcls3::AGDcl = appendAGDcl(synAttr(tName, transType, location=ag.location), agDcls2, location=ag.location);

    -- synthesized attribute transformed_$tName :: Boolean;
    local agDcls3_1::AGDcl = appendAGDcl(synAttr(transformNm(tName), mkBoolTypeExpr(location=ag.location), location=ag.location), agDcls2, location=ag.location);    

    -- Occurances of attributes, annotations

    -- Problem in future: only apply this on attributes that they are not 
    -- already defined on. This doesn't work because checking if an attribute
    -- occurs on an element we're working with causes a loop.

    -- for $type in allTypes
    -- attribute inhRedex_$tName occurs on $type;
    local agDcls4::AGDcl = appendAGDcl(attrOn(inhRedexName, allNames, location=ag.location), agDcls3_1, location=ag.location);
    
    -- attribute suppliedOrigin occurs on $cncType;
    local agDcls5::AGDcl = appendAGDcl(attrOn("suppliedOrigin", cncNames, location=ag.location), agDcls4, location=ag.location);

    -- for $absType in absTypes
    -- attribute restored$cncType occurs on Origin, $absType;
    local agDcls6::AGDcl = foldl(\ agDcls::AGDcl name::String->
            appendAGDcl(attrOn(restoreNm(name), absNames ++ ["Origin"], location=ag.location), agDcls, location=ag.location),
        agDcls5, cncNames);

    -- annotation redex occurs on $absType;
    local agDcls7::AGDcl = appendAGDcl(annoOn("redex", absNames, location=ag.location), agDcls6, location=ag.location);
    
    -- annotation labels occurs on $absType;
    local agDcls8::AGDcl = appendAGDcl(annoOn("labels", absNames, location=ag.location), agDcls7, location=ag.location);
    
    -- annotation origin occurs on $absType;
    local agDcls9::AGDcl = appendAGDcl(annoOn("origin", absNames, location=ag.location), agDcls8, location=ag.location);
    
    -- attribute wasTransformed occurs on $absType;
    local agDcls10::AGDcl = appendAGDcl(attrOn("wasTransformed", absNames, location=ag.location), agDcls9, location=ag.location);

    -- attribute transformed_$tName occurs on $absType;
    local agDcls10_1::AGDcl = appendAGDcl(attrOn(transformNm(tName), absNames, location=ag.location), agDcls9, location=ag.location);  

    -- attribute $tName occurs on $absType;
    local agDcls11::AGDcl = appendAGDcl(attrOn(tName, absNames, location=ag.location), agDcls10_1, location=ag.location);      

    -- Rewrite rule manipulation
    --
    -- add the identity rule for each type, if an identity rule doesn't already exist
    -- (x -> new(x)) 
    local newRwRules::RewriteRuleList = foldl(\ rwRules::RewriteRuleList name::String ->
            if hasRwID(rwRules.rewriteRules, name, name) then rwRules
            else rewriteRuleCons(terminal(Vbar_kwd, "|"), 
                rewriteRuleType(qName(ag.location, "a"), '::', qTyExpr(qName(ag.location, name), location=ag.location), '->',
                    mkNew("a", location=ag.location), location=ag.location), 
                    rwRules, location=ag.location),
        rwRules, cncNames);

    -- Generating origin productions
    --
    -- abstract production origin_$type
    -- o::Origin ::= e::Decorated $type
    -- {
    --      o.isBottomOrigin = false;
    -- }
    --

    local agDcls12::AGDcl = appendAGDcl(foldl(\ agDcls::AGDcl qn::String->
         appendAGDcl(productionDcl('abstract', 'production', 
            name(mkOriginName(qn),ag.location), mkProdSig("o", "Origin", "e", "Decorated" ++ qn, location=ag.location),
                prdBody([
                    attribDef("o", "isBottomOrigin", mkFalse(location=ag.location), location=ag.location)
                ], location=ag.location), location=ag.location),
            agDcls, location=ag.location),
        emptyAGDcl(location=ag.location), allNames), agDcls11, location=ag.location);

    -- Aspecting origin productions

    -- aspect all cnc origins with:
    --
    -- o.wasTransformed = false;
    -- o.concreteOrigin = o;
    local agDcls13::AGDcl = foldl(\ agDcls::AGDcl name::String->
        appendAGDcl(aspectProductionDcl('aspect', 'production', 
            qName(ag.location, mkOriginName(name)), mkAspectProdSig("o", "Origin", "e", "Decorated" ++ name, location=ag.location),
                prdBody([
                    attribDef("o", "wasTransformed", mkFalse(location=ag.location), location=ag.location),
                    attribDef("o", "concreteOrigin", baseName("o", location=ag.location), location=ag.location)
                ], location=ag.location), location=ag.location), agDcls, location=ag.location),
        agDcls12, cncNames);

    -- restored$cncType attributes
    --
    local agDcls14::AGDcl = foldl(\ agDcls::AGDcl lhs::String->
        appendAGDcl(aspectProductionDcl('aspect', 'production', 
            qName(ag.location, mkOriginName(lhs)), mkAspectProdSig("o", "Origin", "e", "Decorated" ++ lhs, location=ag.location),
                productionBody('{', foldl(\ stmts::ProductionStmts rhs::String ->
                    if !hasRwID(newRwRules.rewriteRules, lhs, rhs) 
                    then stmts -- this is also probably an error 
                    else prdStmtList([
                            attribDef( "o", restoreNm(rhs),  
                                applyRw(rwID(newRwRules.rewriteRules, lhs, rhs, location=ag.location), rhs, lhs, "e", location=ag.location), location=ag.location)
                        ], location=ag.location),
                productionStmtsNil(location=ag.location), cncNames), '}', location=ag.location), location=ag.location), agDcls, location=ag.location),
        agDcls13, cncNames);

    -- aspect all abstract origins with:
    --
    -- o.wasTransformed = wasTransformed(e.origin, e.redex);
    -- o.concreteOrigin = getConcreteOrigin(e.origin, o);
    local agDcls15::AGDcl = foldl(\ agDcls::AGDcl name::String->
        appendAGDcl(aspectProductionDcl('aspect', 'production', 
            qName(ag.location, mkOriginName(name)), mkAspectProdSig("o", "Origin", "e", "Decorated" ++ name, location=ag.location),
                prdBody([
                attribDef("o", "wasTransformed",
                    argFunc("wasTransformed", appExprList([
                        namedAccess("origin", "e", location=ag.location),
                        namedAccess("redex", "e", location=ag.location)
                    ], location=ag.location), location=ag.location), location=ag.location),
                attribDef("o", "concreteOrigin", 
                    argFunc("getConcreteOrigin", appExprList([
                        namedAccess("origin", "e", location=ag.location), 
                        presentName("o", location=ag.location)
                    ], location=ag.location), location=ag.location), location=ag.location)
                ], location=ag.location), location=ag.location), agDcls, location=ag.location),
        agDcls14, absNames);

    -- Non-origin aspecting

    -- for each abstract production
    -- top.wasTransformed = wasTransformed(top.origin, top.redex) || <rhs>.wasTransformed;
    local agDcls16::AGDcl = foldl(\ agDcls::AGDcl dcl::[Decorated NamedSignature] ->
        appendAGDcl(aspectProdStmt(dcl,\ ns::Decorated NamedSignature ->
            attribDef( ns.outputElement.elementName, "wasTransformed",
                foldl(\ e::Expr ie::NamedSignatureElement -> 
                    if contains(unFull(ie.typerep.typeName), absNames)
                    then or(e, '||', exprAccess("wasTransformed", ie.elementName, location=ag.location), location=ag.location)
                    else e,
                argFunc("wasTransformed",
                    appExprList([
                            lhsAccess("origin", ns, location=ag.location),
                            lhsAccess("redex", ns, location=ag.location) 
                        ], location=ag.location),
                    location=ag.location), ns.inputElements), location=ag.location), location=ag.location), agDcls, location=ag.location),
        agDcls15, absProdDcls);

    -- top.restored$cncType = < rewrite + transformation rules ...>
    local agDcls17::AGDcl = foldl(\ agDcls::AGDcl dcl::[Decorated NamedSignature] ->
        appendAGDcl(aspectProdStmts(dcl,\ ns::Decorated NamedSignature ->
            foldl(\ stmts::ProductionStmts rhs::String ->
                -- if there is a rewrite rule from this production to this lhs then use that
                if !hasRwMatch(newRwRules.rewriteRules, rhs, ns) then stmts 
                else productionStmtsSnoc(stmts, 
                        attribDef(ns.outputElement.elementName, restoreNm(rhs),
                        if rwMatch(newRwRules.rewriteRules, rhs, ns, location=ag.location).inputProduction.isJust 
                        then mkCond(
                            lhsExprAccess("wasTransformed", ns, location=ag.location),
                            -- use the rewrite production
                            applyRwProd(rwMatch(newRwRules.rewriteRules, rhs, ns, location=ag.location), rhs, ns, location=ag.location),
                            -- refer to the concrete origin's restored element
                            qAccess(qAccess(
                                lhsExprAccess("origin", ns, location=ag.location), 
                                  "concreteOrigin", location=ag.location),
                                  restoreNm(rhs), location=ag.location), 
                        location=ag.location)
                        else applyRw(rwMatch(newRwRules.rewriteRules, rhs, ns, location=ag.location), rhs, unFull(ns.typerep.typeName), ns.outputElement.elementName, location=ag.location),    
                    location=ag.location), location=ag.location),
            productionStmtsNil(location=ag.location), cncNames), location=ag.location), agDcls, location=ag.location),
        agDcls16, absProdDcls);

    -- top.$tName = ...
    --  if this abstract production has no transformations defined for it,
    --  then,
    --    if top is the same type as the transformation
    --    then $thisProd($arg.$tName, origin=$thisType_Origin(top), redex=(..).inhOrigin_$tName, labels=[])
    --    else don't define this?    ^
    --  else if transformed_$tName   |
    --    then apply transformation  |
    --    else see ------------------/
    local agDcls18::AGDcl = foldl(\ agDcls::AGDcl dcl::[Decorated NamedSignature] ->
        appendAGDcl(aspectProdStmts(dcl,\ ns::Decorated NamedSignature ->
            if !hasTrans(trRules.transformRules, dcl, absGroup, cncGroup) && ns.outputElement.typerep.typeName != transType.typerep.typeName
            then productionStmtsNil(location=ag.location)
            else prdStmtList( 
                [attribDef(ns.outputElement.elementName, tName,
                if !hasTrans(trRules.transformRules, dcl, absGroup, cncGroup) 
                  then prdRecurse(ns, tName, location=ag.location)
                  else mkCond(
                        lhsExprAccess(transformNm(tName), ns, location=ag.location),
                        -- todo: what did I mean by the todo below this? Have I done that already?
                        -- todo: add annotations to anything here that is one of 
                        -- our abstract productions
                        getTrans(trRules.transformRules, dcl, location=ag.location).outputStmt(nsApply(ns, location=ag.location)),
                        prdRecurse(ns, tName, location=ag.location),
                    location=ag.location),
            location=ag.location)], location=ag.location),
            location=ag.location), agDcls, location=ag.location),
        agDcls17, absProdDcls);

    -- top.transformed_$tName = ...
    --  if this abstract production has no transformation defined for it,
    --  then don't define this
    --  else if the rhs matches this transformation, 
    --    then true
    --    else false
    local agDcls19::AGDcl = foldl(\ agDcls::AGDcl dcl::[Decorated NamedSignature] ->
        if !hasTrans(trRules.transformRules, dcl, absGroup, cncGroup) then agDcls 
        else appendAGDcl(aspectProdStmts(dcl,\ ns::Decorated NamedSignature ->
            prdStmtList([
                attribDef( ns.outputElement.elementName, transformNm(tName),
                    getTrans(trRules.transformRules, dcl, location=ag.location).matchProd, location=ag.location)
            ], location=ag.location),
            location=ag.location), agDcls, location=ag.location),
        agDcls18, absProdDcls);

    -- <rhs>.inhRedex_$tName = ...
    --  if this abstract production has no transformation defined for it,
    --  then nothing()
    --  else if transformed$tName
    --    then just($thisType_Origin(top))
    --    else nothing()
    local agDcls20::AGDcl = foldl(\ agDcls::AGDcl dcl::[Decorated NamedSignature] ->
        appendAGDcl(aspectProdStmts(dcl,\ ns::Decorated NamedSignature ->
            foldl(\ stmts::ProductionStmts rhs::NamedSignatureElement ->
                productionStmtsSnoc(stmts, 
                    attribDef( rhs.elementName, inhRedexName,
                            if !hasTrans(trRules.transformRules, dcl, absGroup, cncGroup)
                            then emptyFunc("nothing", location=ag.location) -- this might error because it has to be a production
                            else mkCond(
                                lhsExprAccess(transformNm(tName), ns, location=ag.location),
                                argFunc("just", oneApp(mkOrigin(ns, location=ag.location), location=ag.location), location=ag.location),
                                emptyFunc("nothing", location=ag.location),
                            location=ag.location),
                    location=ag.location), location=ag.location),
            productionStmtsNil(location=ag.location), ns.inputElements), location=ag.location), agDcls, location=ag.location),
        agDcls19, absProdDcls);
    
    -- for each concrete type, if it has location, aspect all of its creating
    -- productions with 
    --
    -- top.suppliedOrigin = locationOrigin(ag.location);
    local agDcls21::AGDcl = foldl(\ agDcls::AGDcl dcl::[Decorated NamedSignature] ->
        appendAGDcl(aspectProdStmt(dcl,\ ns::Decorated NamedSignature ->
            attribDef(ns.outputElement.elementName, "suppliedOrigin", 
                argFunc("locationOrigin", appExprList([
                    lhsAccess("location", ns, location=ag.location)
                ], location=ag.location), location=ag.location),
            location=ag.location), location=ag.location), agDcls, location=ag.location),
        agDcls20, locCncProdDcls);

    -- or if they don't have location:
    --
    -- top.suppliedOrigin = bottomOrigin();
    local agDcls22::AGDcl = foldl(\ agDcls::AGDcl dcl::[Decorated NamedSignature] ->
        appendAGDcl(aspectProdStmt(dcl,\ ns::Decorated NamedSignature ->
            attribDef(ns.outputElement.elementName, "suppliedOrigin", 
                        emptyFunc("bottomOrigin", location=ag.location), location=ag.location),
            location=ag.location), agDcls, location=ag.location), agDcls21, nonLocCncProdDcls);

    -- default annotation location = ag.location;

    agDcls11.grammarName = ag.grammarName;

    ag.moduleNames = agDcls11.moduleNames;
    ag.mdaSpecs = agDcls11.mdaSpecs;
    ag.ideSpecs = agDcls11.ideSpecs;
    ag.syntaxAst = agDcls11.syntaxAst;
    ag.parserSpecs = agDcls11.parserSpecs;
    ag.flowDefs = agDcls11.flowDefs;
    ag.docs := agDcls11.docs;
    ag.docsHeader = agDcls11.docsHeader;
    ag.docsSplit = agDcls11.docsSplit;
    ag.docsNoDoc = agDcls11.docsNoDoc;
    ag.docDcls := agDcls11.docDcls;
    ag.genFiles := agDcls11.genFiles;
    ag.setupInh := agDcls11.setupInh;
    ag.initProd := agDcls11.initProd;
    ag.initValues := agDcls11.initValues;
    ag.postInit := agDcls11.postInit;
    ag.initWeaving := agDcls11.initWeaving;
    ag.valueWeaving := agDcls11.valueWeaving;

    subAg.env = newScopeEnv(agDcls11.defs, ag.env); -- did not work
    --subAg.env = ag.env;

    ag.defs = agDcls11.defs ++ subAg.defs;

    --ag.liftedAGDcls = agDcls22; 
    forwards to consAGDcls(agDcls11, subAg, location=ag.location);
}