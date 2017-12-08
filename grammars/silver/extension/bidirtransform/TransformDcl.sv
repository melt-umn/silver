grammar silver:extension:bidirtransform;

imports silver:translation:java:core only genFiles, setupInh, initProd, initValues, postInit, initWeaving, valueWeaving, translation;
--imports silver:modification:impide only ideSpecs;
imports silver:modification:copper_mda only mdaSpecs;
--imports silver:composed:idetest only foldableRanges;
imports silver:extension:doc:core only bodilessDclCommentItem, docs, docsHeader, docsSplit, docsNoDoc, docDcls;
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

terminal Transform_kwd 'transform' lexer classes {KEYWORD};
terminal Rewrite_kwd 'rewrite' lexer classes {KEYWORD,RESERVED};
terminal From_kwd 'from' lexer classes{KEYWORD,RESERVED};
terminal DblArrow_kwd '->>' lexer classes{KEYWORD, RESERVED};

concrete production transformAGDclFull
ag::AGDcls ::= 'transform' qn::QName '::' transType::TypeExpr 
    '{' trRules::TransformRuleList '}' 
    'rewrite' '{' rwRules::RewriteRuleList '}' 
    -- 'from' cncGroupName::QName 'to' absGroupName::QName ';'
    'from' cncGroupIn::NonterminalList 'to' absGroupIn::NonterminalList '->>' nestedAgs::AGDcls
{
    ag.pp = "transmute " ++ qn.pp ++ "::" ++ transType.pp ++
        "{" ++ trRules.pp ++ "} rewrite {" ++ rwRules.pp ++ "};";

    local tName::String = unFull(qn.name);

    local absGroup::Decorated NonterminalList = decorate absGroupIn with { env=ag.env; };
    local cncGroup::Decorated NonterminalList = decorate cncGroupIn with { env=ag.env; };

    ----------------
    -- Propagation of attributes

    ag.errors := trRules.errors ++ rwRules.errors;

    trRules.absGroup = absGroup;
    trRules.cncGroup = cncGroup;
    trRules.env = ag.env;
    trRules.config = ag.config;
    trRules.downSubst = emptySubst();
    trRules.finalSubst = rwRules.upSubst;

    rwRules.absGroup = absGroup;
    rwRules.cncGroup = cncGroup;
    rwRules.downSubst = emptySubst();    
    rwRules.env = ag.env;
    rwRules.finalSubst = trRules.finalSubst;
    rwRules.config = ag.config;    

    -- todo: think about the env we're providing to the transform/rewrite rules

    -----------------
    -- Initialization of lists of things we need to know
    
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

    local inhRedexName::String = inhRedexNm(tName);

    -- autocopy attribute inRedex_$tName :: Maybe<Origin>; 
    local agDcls::AGDcl = autocAttr(inhRedexName, mkMaybeTypeExpr("Origin", location=ag.location), location=ag.location);

    -- for $cncType in cncTypes
    -- synthesized attribute restored$cncType :: $cncType;
    local agDcls2::AGDcl = foldl(\ agDcls::AGDcl name::String-> 
            appendAGDcl(synAttr(restoreNm(unFull(name)), sTyExpr(name, location=ag.location), location=ag.location), agDcls, location=ag.location),
        agDcls, cncNames);

    -- synthesized attribute $tName :: $tType;
    local agDcls3::AGDcl = appendAGDcl(synAttr(tName, transType, location=ag.location), agDcls2, location=ag.location);

    -- synthesized attribute transformed_$tName :: Boolean;
    local agDcls4::AGDcl = appendAGDcl(synAttr(transformNm(tName), mkBoolTypeExpr(location=ag.location), location=ag.location), agDcls3, location=ag.location);    

    -- Occurances of attributes, annotations

    -- Problem in future: only apply this on attributes that they are not 
    -- already defined on. This doesn't work because checking if an attribute
    -- occurs on an element we're working with causes a loop.

    -- for $type in allTypes
    -- attribute inhRedex_$tName occurs on $type;
    local agDcls5::AGDcl = appendAGDcl(attrOn(inhRedexName, allNames, location=ag.location), agDcls4, location=ag.location);
    
    -- for $absType in absTypes
    -- attribute restored$cncType occurs on Origin, $absType;
    local agDcls6::AGDcl = foldl(\ agDcls::AGDcl name::String->
            appendAGDcl(attrOn(restoreNm(unFull(name)), absNames ++ ["Origin"], location=ag.location), agDcls, location=ag.location),
        agDcls5, cncNames);

    -- attribute transformed_$tName occurs on $absType;
    local agDcls7::AGDcl = appendAGDcl(attrOn(transformNm(tName), absNames, location=ag.location), agDcls6, location=ag.location);  

    -- attribute $tName occurs on $absType;
    local agDcls8::AGDcl = appendAGDcl(attrOn(tName, absNames, location=ag.location), agDcls7, location=ag.location);      

    -- Rewrite rule manipulation
    --
    -- add the identity rule for each type, if an identity rule doesn't already exist
    -- (x -> new(x)) 
    local newRwRules::Decorated RewriteRuleList = foldl(\ rules::Decorated RewriteRuleList name::String ->
            if hasRwID(rules.rewriteRules, name, name) then rules
            else decorate rewriteRuleCons(terminal(Vbar_kwd, "|"), 
                rewriteRuleType(qName(ag.location, "a"), '::', qTyExpr(qName(ag.location, name), location=ag.location), '->',
                    mkNew("a", location=ag.location), location=ag.location), 
                    new(rules), location=ag.location) with {
                        absGroup=rules.absGroup;
                        cncGroup=rules.cncGroup;
                        env=rules.env;
                        downSubst=rules.downSubst;
                        finalSubst=rules.finalSubst;
                        config=rules.config;
                    },
        decorate rwRules with {
            absGroup=absGroup;
            cncGroup=cncGroup;
            env=ag.env;
            downSubst=emptySubst();
            finalSubst=trRules.finalSubst;
            config=ag.config;
        }, cncNames);

    -- Aspecting origin productions

    -- restored$cncType attributes
    --
    local agDcls9::AGDcl = foldl(\ agDcls::AGDcl lhs::String->
        appendAGDcl(fakeAspectProductionDcl('aspect', 'production', 
            qName(ag.location, mkOriginName(lhs)), mkAspectProdSigDec("o", "Origin", "e", lhs, location=ag.location),
                productionBody('{', foldl(\ stmts::ProductionStmts rhs::String ->
                    if !hasRwID(newRwRules.rewriteRules, lhs, rhs) 
                    then stmts -- this is also probably an error 
                    else prdStmtList([
                            attribDef( "o", restoreNm(unFull(rhs)),  
                                applyRw(rwID(newRwRules.rewriteRules, lhs, rhs), rhs, lhs, "e", location=ag.location), location=ag.location)
                        ], location=ag.location),
                productionStmtsNil(location=ag.location), cncNames), '}', location=ag.location), location=ag.location), agDcls, location=ag.location),
        agDcls8, cncNames);

    -- Non-origin aspecting

    -- for each abstract production
    -- top.wasTransformed = wasTransformed(top.origin, top.redex) || <rhs>.wasTransformed;
    local agDcls10::AGDcl = foldl(\ agDcls::AGDcl dcl::[Decorated NamedSignature] ->
        appendAGDcl(aspectProdStmt(dcl,\ ns::Decorated NamedSignature ->
            attribDef( ns.outputElement.elementName, "wasTransformed",
                foldl(\ e::Expr ie::NamedSignatureElement -> 
                    if contains(unFull(ie.typerep.typeName), absNames)
                    then or(e, '||', exprAccess("wasTransformed", ie.elementName, location=ag.location), location=ag.location)
                    else e,
                argFunc("wasTransformed",
                    appExprList([
                            lhsAccess("redex", ns, location=ag.location),
                            lhsAccess("origin", ns, location=ag.location)
                        ], location=ag.location),
                    location=ag.location), ns.inputElements), location=ag.location), location=ag.location), agDcls, location=ag.location),
        agDcls9, absProdDcls);

    -- top.restored$cncType = < rewrite + transformation rules ...>
    local agDcls11::AGDcl = foldl(\ agDcls::AGDcl dcl::[Decorated NamedSignature] ->
        appendAGDcl(aspectProdStmts(dcl,\ ns::Decorated NamedSignature ->
            foldl(\ stmts::ProductionStmts rhs::String ->
                -- if there isn't a rewrite rule from this production to this lhs then don't define this
                if !hasRwMatch(newRwRules.rewriteRules, rhs, ns) then stmts -- not happening in error case
                else productionStmtsSnoc(stmts, 
                        attribDef(ns.outputElement.elementName, restoreNm(unFull(rhs)),
                        if rwMatch(newRwRules.rewriteRules, rhs, ns).hasProduction 
                        then mkCond(
                            lhsExprAccess("wasTransformed", ns, location=ag.location), 
                            -- use the rewrite production
                            applyRwProd(rwMatch(newRwRules.rewriteRules, rhs, ns), ns, location=ag.location),
                            -- refer to the concrete origin's restored element
                            qAccess(restoreNm(unFull(rhs)),
                                qAccess("concreteOrigin",
                                    lhsExprAccess("origin", ns, location=ag.location), 
                                    location=ag.location),
                                location=ag.location),
                            location=ag.location)
                        else applyRw(rwMatch(newRwRules.rewriteRules, rhs, ns), rhs, unFull(ns.typerep.typeName), ns.outputElement.elementName, location=ag.location),    
                    location=ag.location), location=ag.location),
            productionStmtsNil(location=ag.location), cncNames), location=ag.location), agDcls, location=ag.location),
        agDcls10, absProdDcls);

    -- top.$tName = ...
    --  if this abstract production has no transformations defined for it,
    --  then,
    --    if top is the same type as the transformation
    --    then $thisProd($arg.$tName, origin=$thisType_Origin(top), redex=(..).inhRedex_$tName, labels=[])
    --    else don't define this?    ^
    --  else if transformed_$tName   |
    --    then apply transformation  |
    --    else see ------------------/
    local agDcls12::AGDcl = foldl(\ agDcls::AGDcl dcl::[Decorated NamedSignature] ->
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
        agDcls11, absProdDcls);

    -- top.transformed_$tName = ...
    --  if this abstract production has no transformation defined for it,
    --  then don't define this
    --  else if the rhs matches this transformation, 
    --    then true
    --    else false
    local agDcls13::AGDcl = foldl(\ agDcls::AGDcl dcl::[Decorated NamedSignature] ->
        if !hasTrans(trRules.transformRules, dcl, absGroup, cncGroup) then agDcls 
        else appendAGDcl(aspectProdStmts(dcl,\ ns::Decorated NamedSignature ->
            prdStmtList([
                attribDef( ns.outputElement.elementName, transformNm(tName),
                    getTrans(trRules.transformRules, dcl, location=ag.location).matchProd, location=ag.location)
            ], location=ag.location),
            location=ag.location), agDcls, location=ag.location),
        agDcls12, absProdDcls);

    -- <rhs>.inhRedex_$tName = ...
    --  if this abstract production has no transformation defined for it,
    --  then nothing()
    --  else if transformed$tName
    --    then just($thisType_Origin(top))
    --    else nothing()
    local agDcls14::AGDcl = foldl(\ agDcls::AGDcl dcl::[Decorated NamedSignature] ->
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
        agDcls13, absProdDcls);
    
    -- for each concrete type, if it has location, aspect all of its creating
    -- productions with 
    --
    -- top.suppliedOrigin = locationOrigin(ag.location);
    local agDcls15::AGDcl = foldl(\ agDcls::AGDcl dcl::[Decorated NamedSignature] ->
        appendAGDcl(aspectProdStmt(dcl,\ ns::Decorated NamedSignature ->
            attribDef(ns.outputElement.elementName, "suppliedOrigin", 
                argFunc("locationOrigin", appExprList([
                    lhsAccess("location", ns, location=ag.location)
                ], location=ag.location), location=ag.location),
            location=ag.location), location=ag.location), agDcls, location=ag.location),
        agDcls14, locCncProdDcls);

    -- or if they don't have location:
    --
    -- top.suppliedOrigin = bottomOrigin();
    local agDcls16::AGDcl = foldl(\ agDcls::AGDcl dcl::[Decorated NamedSignature] ->
        appendAGDcl(aspectProdStmt(dcl,\ ns::Decorated NamedSignature ->
            attribDef(ns.outputElement.elementName, "suppliedOrigin", 
                        emptyFunc("bottomOrigin", location=ag.location), location=ag.location),
            location=ag.location), agDcls, location=ag.location), 
        agDcls15, nonLocCncProdDcls);


    -- add origin specific generation

    local toForward::AGDcl = appendAGDcl(
        applyOrigins(absGroup.ntList, location=ag.location), 
        appendAGDcl(
            cncApplyOrigins(cncGroup.ntList, location=ag.location),
            agDcls16, location=ag.location), location=ag.location);


    ag.moduleNames = [];--agDclsP3.moduleNames ++ nestedAgs.moduleNames;
    ag.mdaSpecs = toForward.mdaSpecs ++ nestedAgs.mdaSpecs;
    ag.ideSpecs = toForward.ideSpecs ++ nestedAgs.ideSpecs;
    ag.syntaxAst = toForward.syntaxAst ++ nestedAgs.syntaxAst;
    ag.parserSpecs = toForward.parserSpecs ++ nestedAgs.parserSpecs;
    ag.flowDefs = toForward.flowDefs ++ nestedAgs.flowDefs;
    ag.docs := toForward.docs ++ nestedAgs.docs;
    ag.docsHeader = toForward.docsHeader ++ nestedAgs.docsHeader;
    ag.docsSplit = toForward.docsSplit ++ nestedAgs.docsSplit;
    ag.docsNoDoc = toForward.docsNoDoc || nestedAgs.docsNoDoc;
    ag.docDcls := toForward.docDcls ++ nestedAgs.docDcls;
    ag.genFiles := toForward.genFiles ++ nestedAgs.genFiles;
    ag.setupInh := toForward.setupInh ++ nestedAgs.setupInh;
    ag.initProd := toForward.initProd ++ nestedAgs.initProd;
    ag.initValues := toForward.initValues ++ nestedAgs.initValues;
    ag.postInit := toForward.postInit ++ nestedAgs.postInit;
    ag.initWeaving := toForward.initWeaving ++ nestedAgs.initWeaving;
    ag.valueWeaving := toForward.valueWeaving ++ nestedAgs.valueWeaving;
    ag.errors <- toForward.errors ++ nestedAgs.errors;

    toForward.compiledGrammars = ag.compiledGrammars;
    toForward.config = ag.config;    
    toForward.grammarName = ag.grammarName;
    toForward.flowEnv = ag.flowEnv;
    toForward.env = nestedAgs.env;

    nestedAgs.env = appendEnv(ag.env, toEnv(toForward.defs));
    nestedAgs.flowEnv = ag.flowEnv;
    nestedAgs.grammarName = ag.grammarName;
    nestedAgs.config = ag.config;
    nestedAgs.compiledGrammars = ag.compiledGrammars;
    -- nestedAgs.env = newScopeEnv(toForward.defs, ag.env); -- did not work
    -- nestedAgs.env = ag.env; -- did not work

    --ag.defs = nestedAgs.defs;
    ag.defs = toForward.defs ++ nestedAgs.defs; -- <- duplicate attributes
    --ag.defs = toForward.defs; 
    --ag.defs = filterDefs(toForward.defs) ++ nestedAgs.defs;
    -- ag.errors <- map(\ d::Def -> 
    --     err(ag.location, "Def pp: " ++ d.ppDebug),
    -- toForward.defs);

    --ag.liftedAGDcls = agDcls22; 
    --forwards to consAGDcls(toForward, nestedAgs, location=ag.location);
}