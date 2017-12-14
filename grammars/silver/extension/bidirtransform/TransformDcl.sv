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
    trsl.downSubst = emptySubst();
    trsl.finalSubst = rwRules.upSubst;

    rwRules.absGroup = absGroup;
    rwRules.cncGroup = cncGroup;
    rwRules.downSubst = emptySubst();    
    rwRules.finalSubst = trsl.finalSubst;

    -----------------
    -- Initialization of lists of things we need to know
    
    -- We need to know everything's name

    local absNames :: [String] = map((.name), absGroup.ntList);    
    local cncNames :: [String] = map((.name), cncGroup.ntList);
    local locCncNames :: [String] = []; --todo
    local nonLocCncNames :: [String] = cncNames;
    local allNames :: [String] = cncNames ++ absNames;

    -- We need to know all the productions on all of the known types

    local absProdDcls :: [Decorated NamedSignature] = absProdsFromDefs(nestedAgs.defs);
    local cncProdDcls :: [Decorated NamedSignature] = cncProdsFromDefs(nestedAgs.defs);
    local locCncProdDcls :: [Decorated NamedSignature] = [];
    local nonLocCncProdDcls :: [Decorated NamedSignature] = cncProdDcls;
    local allProdDcls :: [Decorated NamedSignature] = absProdDcls ++ cncProdDcls;

    trsl.inhProds = allProdDcls;

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


    -----------------------
    -- Generating code

    local agDcls0::AGDcl = foldl(\ agDcls::AGDcl tdcl::Decorated TransformDcl ->
        appendAGDcl(
            declareTNameAttributes(tdcl, absNames, cncNames, location=ag.location),
            agDcls, location=ag.location),
    emptyAGDcl(location=ag.location), trsl.transformDcls);

    -- for $cncType in cncTypes
    -- synthesized attribute restored$cncType :: $cncType;
    local agDcls1::AGDcl = foldl(\ agDcls::AGDcl name::String-> 
            appendAGDcl(synAttr(restoreNm(unFull(name)), sTyExpr(name, location=ag.location), location=ag.location), agDcls, location=ag.location),
        agDcls0, cncNames);

    -- for $absType in absTypes
    -- attribute restored$cncType occurs on Origin, $absType;
    local agDcls2::AGDcl = foldl(\ agDcls::AGDcl name::String->
            appendAGDcl(attrOn(restoreNm(unFull(name)), absNames ++ ["Origin"], location=ag.location), agDcls, location=ag.location),
        agDcls1, cncNames);

    -- Aspecting origin productions

    -- restored$cncType attributes
    --
    local agDcls3::AGDcl = foldl(\ agDcls::AGDcl lhs::String->
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
        agDcls2, cncNames);

    -- Non-origin aspecting

    -- for each abstract production
    -- top.wasTransformed = wasTransformed(top.origin, top.redex) || <rhs>.wasTransformed;
    local agDcls4::AGDcl = foldl(\ agDcls::AGDcl dcl::Decorated NamedSignature ->
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
        agDcls3, absProdDcls);

    -- top.restored$cncType = < rewrite + transformation rules ...>
    local agDcls5::AGDcl = foldl(\ agDcls::AGDcl dcl::Decorated NamedSignature ->
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
        agDcls4, absProdDcls);

    -- define transformation attributes (those dependent on each transformation declared)
    local agDcls6::AGDcl = foldl(\ agDcls::AGDcl tdcl::Decorated TransformDcl ->
        appendAGDcl(
            defineTNameAttributes(tdcl, absNames, allNames, absProdDcls, location=ag.location),
            agDcls, location=ag.location),
    agDcls5, trsl.transformDcls);
    
    -- for each concrete type, if it has location, aspect all of its creating
    -- productions with 
    --
    -- top.suppliedOrigin = locationOrigin(ag.location);
    local agDcls7::AGDcl = foldl(\ agDcls::AGDcl dcl::Decorated NamedSignature ->
        appendAGDcl(aspectProdStmt(dcl,\ ns::Decorated NamedSignature ->
            attribDef(ns.outputElement.elementName, "suppliedOrigin",
                argFunc("locationOrigin", appExprList([
                    lhsAccess("location", ns, location=ag.location)
                ], location=ag.location), location=ag.location),
            location=ag.location), location=ag.location), agDcls, location=ag.location),
        agDcls6, locCncProdDcls);

    -- or if they don't have location:
    --
    -- top.suppliedOrigin = bottomOrigin();
    local agDcls8::AGDcl = foldl(\ agDcls::AGDcl dcl::Decorated NamedSignature ->
        appendAGDcl(aspectProdStmt(dcl,\ ns::Decorated NamedSignature ->
            attribDef(ns.outputElement.elementName, "suppliedOrigin",
                emptyFunc("bottomOrigin", location=ag.location), location=ag.location),
            location=ag.location), agDcls, location=ag.location), 
        agDcls7, nonLocCncProdDcls);


    -- origin generation
    local toForward::AGDcl = appendAGDcl(
        applyOrigins(absGroup.ntList, location=ag.location), 
        appendAGDcl(
            cncApplyOrigins(cncGroup.ntList, location=ag.location),
            agDcls8, location=ag.location), location=ag.location);

    toForward.env = nestedAgs.env;
    nestedAgs.env = appendEnv(ag.env, toEnv(toForward.defs));

    ----- 
    -- LOGS
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
    -----

    forwards to consAGDcls(toForward, nestedAgs, location=ag.location);
}