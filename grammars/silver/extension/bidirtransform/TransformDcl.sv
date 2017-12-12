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
ag::AGDcls ::= 'transform' trsl::TransformList
    'rewrite' '{' rwRules::RewriteRuleList '}' 
    'from' cncGroupIn::NonterminalList 'to' absGroupIn::NonterminalList '->>' nestedAgs::AGDcls
{
    ag.pp = "transform " ++ trsl.pp ++ " rewrite {" ++ rwRules.pp ++ "};";

    local groupEnv::Decorated Env = toEnv(nestedAgs.defs);

    local absGroup::Decorated NonterminalList = decorate absGroupIn with {
         env = groupEnv;
         grantedDefs = nestedAgs.defs;
    };
    local cncGroup::Decorated NonterminalList = decorate cncGroupIn with { 
        env = groupEnv; 
        grantedDefs = nestedAgs.defs;        
    };

    ----------------
    -- Propagation of attributes

    ag.errors := trsl.errors ++ newRwRules.errors ++ absGroup.errors ++ cncGroup.errors;

    trsl.absGroup = absGroup;
    trsl.cncGroup = cncGroup;
    trsl.config = ag.config;
    trsl.downSubst = emptySubst();
    trsl.finalSubst = rwRules.upSubst;

    rwRules.absGroup = absGroup;
    rwRules.cncGroup = cncGroup;
    rwRules.downSubst = emptySubst();    
    rwRules.finalSubst = trsl.finalSubst;
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

    local absProdDcls :: [Decorated NamedSignature] = absProdsFromDefs(nestedAgs.defs);
    local cncProdDcls :: [Decorated NamedSignature] = cncProdsFromDefs(nestedAgs.defs);
    local locCncProdDcls :: [Decorated NamedSignature] = [];
    local nonLocCncProdDcls :: [Decorated NamedSignature] = cncProdDcls;
    local allProdDcls :: [Decorated NamedSignature] = absProdDcls ++ cncProdDcls;

    trsl.inhProds = allProdDcls;

    local absProdNames :: [String] = map(unFull, map((.fullName), absProdDcls));

    local log :: Boolean = false;

    ag.errors <- if log then map(\ fnt::Decorated FullNonterminal ->
        err(ag.location, "Abs nt: " ++ fnt.name),
    absGroup.ntList) else [];

    ag.errors <- if log then map(\ fnt::Decorated FullNonterminal ->
        err(ag.location, "Cnc nt: " ++ fnt.name),
    cncGroup.ntList) else [];

    ag.errors <- if log then map(\ d::Def -> 
        err(ag.location, "Nested Def: " ++ d.ppDebug),
    nestedAgs.defs) else [];

    ag.errors <- if log then map(\ dec::Decorated NamedSignature ->
        err(ag.location, "Abs prod: " ++ dec.fullName),
    absProdDcls) else [];

    ag.errors <- if log then map(\ dec::Decorated NamedSignature ->
        err(ag.location, "Cnc prod: " ++ dec.fullName),
    cncProdDcls) else [];

    ag.errors <- if log then map(\ dec::Decorated NamedSignature ->
        err(ag.location, "Abs output: " ++ dec.outputElement.elementName),
    absProdDcls) else [];

    ag.errors <- if log then map(\ dec::Decorated NamedSignature ->
        err(ag.location, "Abs typerep: " ++ dec.typerep.typeName),
    absProdDcls) else [];
    
    ag.errors <- if log then map(\ s::String ->
        err(ag.location, "Cnc name: " ++ s),
    cncNames) else [];

    -----------------------
    -- Generating code

    local agDcls1::AGDcl = foldl(\ agDcls::AGDcl tdcl::Decorated TransformDcl ->
        appendAGDcl(
            declareTNameAttributes(tdcl, absNames, cncNames, location=ag.location),
            agDcls, location=ag.location),
    emptyAGDcl(location=ag.location), trsl.transformDcls);

    -- Rewrite rule manipulation
    --
    -- add the identity rule for each type, if an identity rule doesn't already exist
    -- (x -> new(x)) 
    local newRwRules::Decorated RewriteRuleList = foldl(\ rules::Decorated RewriteRuleList name::String ->
            if hasRwEq(rules.rewriteRules, name, name) then rules
            else decorate rewriteRuleCons(terminal(Vbar_kwd, "|"), 
                rewriteRule(mkNew("a", location=ag.location),
                    "a",
                    sTyExprDec(name, ag.location, rules.env).typerep,
                    sTyExprDec(name, ag.location, rules.env).typerep,
                    emptyRewriteProduction(location=ag.location),
                    false,
                    false,
                    location=ag.location),
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
            finalSubst=trsl.finalSubst;
            config=ag.config;
        }, cncNames);

    -- Aspecting origin productions

    -- restored$cncType attributes
    --
    local agDcls9::AGDcl = foldl(\ agDcls::AGDcl lhs::String->
        appendAGDcl(
            fakeAspectProductionDcl('aspect', 'production',
            qName(ag.location, mkOriginName(lhs)), mkAspectProdSigDec("o", "Origin", "e", lhs, location=ag.location),
                productionBody('{', foldl(\ stmts::ProductionStmts rhs::String ->
                    if !hasRwID(newRwRules.rewriteRules, lhs, rhs) then stmts -- this is also probably an error 
                    else productionStmtsSnoc(stmts, 
                            attribDef("o", restoreNm(unFull(rhs)),
                                applyRwOrigin(rwID(newRwRules.rewriteRules, lhs, rhs), rhs, lhs, "o", "e", location=ag.location), location=ag.location)
                        , location=ag.location),
                productionStmtsNil(location=ag.location), cncNames), '}', location=ag.location), location=ag.location), agDcls, location=ag.location),
        agDcls1, cncNames);

    -- Non-origin aspecting

    -- for each abstract production
    -- top.wasTransformed = wasTransformed(top.origin, top.redex) || <rhs>.wasTransformed;
    local agDcls10::AGDcl = foldl(\ agDcls::AGDcl dcl::Decorated NamedSignature ->
        appendAGDcl(aspectProdStmt(dcl,\ ns::Decorated NamedSignature ->
            attribDef(ns.outputElement.elementName, "wasTransformed",
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
    local agDcls11::AGDcl = foldl(\ agDcls::AGDcl dcl::Decorated NamedSignature ->
        appendAGDcl(aspectProdStmts(dcl,\ ns::Decorated NamedSignature ->
            foldl(\ stmts::ProductionStmts rhs::String ->
                -- if there isn't a rewrite rule from this production to this lhs then don't define this
                if !hasRwMatch(newRwRules.rewriteRules, rhs, ns) then stmts --  error case
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
    --local agDcls11::AGDcl = agDcls10;

    -- define transformation attributes (those dependent on each transformation declared)
    local agDcls12::AGDcl = foldl(\ agDcls::AGDcl tdcl::Decorated TransformDcl -> 
        joinAGDcls([
        -- top.$tName = ...
        --  if this abstract production has no transformations defined for it,
        --  then,
        --    if top is the same type as the transformation
        --    then $thisProd($arg.$tName, origin=$thisType_Origin(top), redex=(..).inhRedex_$tName, labels=[])
        --    else don't define this?    ^
        --  else if transformed_$tName   |
        --    then apply transformation  |
        --    else see ------------------/
        foldl(\ agDcls::AGDcl dcl::Decorated NamedSignature ->
            appendAGDcl(aspectProdStmts(dcl,\ ns::Decorated NamedSignature ->
                if !hasTrans(tdcl.transformRules, dcl) && ns.outputElement.typerep.typeName != tdcl.typeName
                then productionStmtsNil(location=ag.location)
                else prdStmtList( 
                    [attribDef(ns.outputElement.elementName, tdcl.name,
                    if !hasTrans(tdcl.transformRules, dcl) 
                    then prdRecurse(ns, tdcl.name, absNames, location=ag.location)
                    else mkCond(
                            lhsExprAccess(transformNm(tdcl.name), ns, location=ag.location),
                            injectAnnos(
                                applyTrans(tdcl.transformRules, dcl, location=ag.location),
                                annoAppExprList([
                                    annExpr("labels", emptyList('[',']', location=ag.location), location=ag.location),
                                    annExpr("redex", exprAccess(inhRedexNm(tdcl.name), inhRedexNameSig(ns, allNames), location=ag.location), location=ag.location),
                                    annExpr("origin", mkOrigin(ns, location=ag.location), location=ag.location)
                                    ], location=ag.location), 
                                absProdNames, location=ag.location),
                            prdRecurse(ns, tdcl.name, absNames, location=ag.location),
                        location=ag.location),
                location=ag.location)], location=ag.location),
                location=ag.location), agDcls, location=ag.location),
            emptyAGDcl(location=ag.location), absProdDcls),

        -- top.transformed_$tName = ...
        --  if this abstract production has no transformation defined for it,
        --  then don't define this
        --  else if the rhs matches this transformation, 
        --    then true
        --    else false
        foldl(\ agDcls::AGDcl dcl::Decorated NamedSignature ->
            if !hasTrans(tdcl.transformRules, dcl) then agDcls 
            else appendAGDcl(aspectProdStmts(dcl,\ ns::Decorated NamedSignature ->
                prdStmtList([
                    attribDef(ns.outputElement.elementName, transformNm(tdcl.name),
                        getTrans(tdcl.transformRules, dcl).matchProd, location=ag.location)
                ], location=ag.location),
                location=ag.location), agDcls, location=ag.location),
            emptyAGDcl(location=ag.location), absProdDcls),

        -- <rhs>.inhRedex_$tName = ...
        --  if this abstract production has no transformation defined for it,
        --  then nothing()
        --  else if transformed$tName
        --    then just($thisType_Origin(top))
        --    else nothing()
        foldl(\ agDcls::AGDcl dcl::Decorated NamedSignature ->
            appendAGDcl(aspectProdStmts(dcl,\ ns::Decorated NamedSignature ->
                foldl(\ stmts::ProductionStmts rhs::NamedSignatureElement ->
                    if !contains(unFull(rhs.typerep.typeName), allNames) then stmts else
                    productionStmtsSnoc(stmts, 
                        attribDef(rhs.elementName, inhRedexNm(tdcl.name),
                                if !hasTrans(tdcl.transformRules, dcl)
                                then emptyFunc("nothing", location=ag.location) -- this might error because it has to be a production
                                else mkCond(
                                    lhsExprAccess(transformNm(tdcl.name), ns, location=ag.location),
                                    argFunc("just", oneApp(mkOrigin(ns, location=ag.location), location=ag.location), location=ag.location),
                                    emptyFunc("nothing", location=ag.location),
                                location=ag.location),
                        location=ag.location), location=ag.location),
                productionStmtsNil(location=ag.location), ns.inputElements), location=ag.location), agDcls, location=ag.location),
            emptyAGDcl(location=ag.location), absProdDcls), agDcls], location=ag.location),
    agDcls11, trsl.transformDcls);
    
    -- for each concrete type, if it has location, aspect all of its creating
    -- productions with 
    --
    -- top.suppliedOrigin = locationOrigin(ag.location);
    local agDcls15::AGDcl = foldl(\ agDcls::AGDcl dcl::Decorated NamedSignature ->
        appendAGDcl(aspectProdStmt(dcl,\ ns::Decorated NamedSignature ->
            attribDef(ns.outputElement.elementName, "suppliedOrigin",
                argFunc("locationOrigin", appExprList([
                    lhsAccess("location", ns, location=ag.location)
                ], location=ag.location), location=ag.location),
            location=ag.location), location=ag.location), agDcls, location=ag.location),
        agDcls12, locCncProdDcls);

    -- or if they don't have location:
    --
    -- top.suppliedOrigin = bottomOrigin();
    local agDcls16::AGDcl = foldl(\ agDcls::AGDcl dcl::Decorated NamedSignature ->
        appendAGDcl(aspectProdStmt(dcl,\ ns::Decorated NamedSignature ->
            attribDef(ns.outputElement.elementName, "suppliedOrigin",
                emptyFunc("bottomOrigin", location=ag.location), location=ag.location),
            location=ag.location), agDcls, location=ag.location), 
        agDcls15, nonLocCncProdDcls);


    -- add origins generation

    local toForward::AGDcl = appendAGDcl(
        applyOrigins(absGroup.ntList, location=ag.location), 
        appendAGDcl(
            cncApplyOrigins(cncGroup.ntList, location=ag.location),
            agDcls16, location=ag.location), location=ag.location);

    toForward.compiledGrammars = ag.compiledGrammars;
    nestedAgs.compiledGrammars = ag.compiledGrammars;

    toForward.config = ag.config;    
    nestedAgs.config = ag.config;

    toForward.grammarName = ag.grammarName;
    nestedAgs.grammarName = ag.grammarName;

    toForward.flowEnv = ag.flowEnv;
    nestedAgs.flowEnv = ag.flowEnv;

    toForward.env = nestedAgs.env;
    nestedAgs.env = appendEnv(ag.env, toEnv(toForward.defs));

    forwards to consAGDcls(toForward, nestedAgs, location=ag.location);
}