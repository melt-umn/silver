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
    'from' cncGroupIn::NonterminalList 'to' absGroupIn::NonterminalList '->>' nestedAgs::AGDcls
{
    ag.pp = "transmute " ++ qn.pp ++ "::" ++ transType.pp ++
        "{" ++ trRules.pp ++ "} rewrite {" ++ rwRules.pp ++ "};";

    local tName::String = unFull(qn.name);

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

    ag.errors := trRules.errors ++ newRwRules.errors ++ absGroup.errors ++ cncGroup.errors;

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

    local absProdDcls :: [Decorated NamedSignature] = absProdsFromDefs(nestedAgs.defs);
    local cncProdDcls :: [Decorated NamedSignature] = cncProdsFromDefs(nestedAgs.defs);
    local locCncProdDcls :: [Decorated NamedSignature] = [];
    local nonLocCncProdDcls :: [Decorated NamedSignature] = cncProdDcls;
    local allProdDcls :: [Decorated NamedSignature] = absProdDcls ++ cncProdDcls;

    trRules.inhProds = allProdDcls;

    local absProdNames :: [String] = map(unFull, map((.fullName), absProdDcls));

    local logStuff :: Boolean = false;

    ag.errors <- if logStuff then map(\ fnt::Decorated FullNonterminal ->
        err(ag.location, "Abs nt: " ++ fnt.name),
    absGroup.ntList) else [];

    ag.errors <- if logStuff then map(\ fnt::Decorated FullNonterminal ->
        err(ag.location, "Cnc nt: " ++ fnt.name),
    cncGroup.ntList) else [];

    ag.errors <- if logStuff then map(\ d::Def -> 
        err(ag.location, "Nested Def: " ++ d.ppDebug),
    nestedAgs.defs) else [];

    ag.errors <- if logStuff then map(\ dec::Decorated NamedSignature ->
        err(ag.location, "Abs prod: " ++ dec.fullName),
    absProdDcls) else [];

    ag.errors <- if logStuff then map(\ dec::Decorated NamedSignature ->
        err(ag.location, "Cnc prod: " ++ dec.fullName),
    cncProdDcls) else [];

    -----------------------
    -- Generating code

    -- New attributes and annotations

    local inhRedexName::String = inhRedexNm(tName);

    -- autocopy attribute inRedex_$tName :: Maybe<Origin>; 
    local agDcls1::AGDcl = autocAttr(inhRedexName, mkMaybeTypeExpr("Origin", location=ag.location), location=ag.location);

    -- for $cncType in cncTypes
    -- synthesized attribute restored$cncType :: $cncType;
    local agDcls2::AGDcl = foldl(\ agDcls::AGDcl name::String-> 
            appendAGDcl(synAttr(restoreNm(unFull(name)), sTyExpr(name, location=ag.location), location=ag.location), agDcls, location=ag.location),
        agDcls1, cncNames);

    -- synthesized attribute $tName :: $tType;
    local agDcls3::AGDcl = appendAGDcl(synAttr(tName, transType, location=ag.location), agDcls2, location=ag.location);

    -- synthesized attribute transformed_$tName :: Boolean;
    local agDcls4::AGDcl = appendAGDcl(synAttr(transformNm(tName), mkBoolTypeExpr(location=ag.location), location=ag.location), agDcls3, location=ag.location);    

    -- Occurances of attributes, annotations

    -- Problem in future: only apply this on attributes that they are not 
    -- already defined on. This doesn't work because checking if an attribute
    -- occurs on an element we're working with causes a circularity.

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
            finalSubst=trRules.finalSubst;
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
                                applyRw(rwID(newRwRules.rewriteRules, lhs, rhs), rhs, lhs, "e", location=ag.location), location=ag.location)
                        , location=ag.location),
                productionStmtsNil(location=ag.location), cncNames), '}', location=ag.location), location=ag.location), agDcls, location=ag.location),
        agDcls8, cncNames);

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
                        -- if rwMatch(newRwRules.rewriteRules, rhs, ns).hasProduction 
                        -- then mkCond(
                        --     lhsExprAccess("wasTransformed", ns, location=ag.location), 
                        --     -- use the rewrite production
                        --     applyRwProd(rwMatch(newRwRules.rewriteRules, rhs, ns), ns, location=ag.location),
                        --     -- refer to the concrete origin's restored element
                        --     qAccess(restoreNm(unFull(rhs)),
                        --         qAccess("concreteOrigin",
                        --             lhsExprAccess("origin", ns, location=ag.location), 
                        --             location=ag.location),
                        --         location=ag.location),
                        --     location=ag.location)
                        -- else 
                        applyRw(rwMatch(newRwRules.rewriteRules, rhs, ns), rhs, unFull(ns.typerep.typeName), ns.outputElement.elementName, location=ag.location),    
                    location=ag.location), location=ag.location),
            productionStmtsNil(location=ag.location), cncNames), location=ag.location), agDcls, location=ag.location),
        agDcls10, absProdDcls);
    --local agDcls11::AGDcl = agDcls10;

    -- top.$tName = ...
    --  if this abstract production has no transformations defined for it,
    --  then,
    --    if top is the same type as the transformation
    --    then $thisProd($arg.$tName, origin=$thisType_Origin(top), redex=(..).inhRedex_$tName, labels=[])
    --    else don't define this?    ^
    --  else if transformed_$tName   |
    --    then apply transformation  |
    --    else see ------------------/
    local agDcls12::AGDcl = foldl(\ agDcls::AGDcl dcl::Decorated NamedSignature ->
        appendAGDcl(aspectProdStmts(dcl,\ ns::Decorated NamedSignature ->
            if !hasTrans(trRules.transformRules, dcl) && ns.outputElement.typerep.typeName != transType.typerep.typeName
            then productionStmtsNil(location=ag.location)
            else prdStmtList( 
                [attribDef(ns.outputElement.elementName, tName,
                if !hasTrans(trRules.transformRules, dcl) 
                  then prdRecurse(ns, tName, absNames, location=ag.location)
                  else mkCond(
                        lhsExprAccess(transformNm(tName), ns, location=ag.location),
                        injectAnnos(
                            applyTrans(trRules.transformRules, dcl, location=ag.location),
                            annoAppExprList([
                                annExpr("labels", emptyList('[',']', location=ag.location), location=ag.location),
                                annExpr("redex", exprAccess(inhRedexNm(tName), inhRedexNameSig(ns, allNames), location=ag.location), location=ag.location),
                                annExpr("origin", mkOrigin(ns, location=ag.location), location=ag.location)
                                ], location=ag.location), 
                            absProdNames, location=ag.location),
                        prdRecurse(ns, tName, absNames, location=ag.location),
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
    local agDcls13::AGDcl = foldl(\ agDcls::AGDcl dcl::Decorated NamedSignature ->
        if !hasTrans(trRules.transformRules, dcl) then agDcls 
        else appendAGDcl(aspectProdStmts(dcl,\ ns::Decorated NamedSignature ->
            prdStmtList([
                attribDef(ns.outputElement.elementName, transformNm(tName),
                    getTrans(trRules.transformRules, dcl).matchProd, location=ag.location)
            ], location=ag.location),
            location=ag.location), agDcls, location=ag.location),
        agDcls12, absProdDcls);

    -- <rhs>.inhRedex_$tName = ...
    --  if this abstract production has no transformation defined for it,
    --  then nothing()
    --  else if transformed$tName
    --    then just($thisType_Origin(top))
    --    else nothing()
    local agDcls14::AGDcl = foldl(\ agDcls::AGDcl dcl::Decorated NamedSignature ->
        appendAGDcl(aspectProdStmts(dcl,\ ns::Decorated NamedSignature ->
            foldl(\ stmts::ProductionStmts rhs::NamedSignatureElement ->
                if !contains(unFull(rhs.typerep.typeName), allNames) then stmts else
                productionStmtsSnoc(stmts, 
                    attribDef(rhs.elementName, inhRedexName,
                            if !hasTrans(trRules.transformRules, dcl)
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
    local agDcls15::AGDcl = foldl(\ agDcls::AGDcl dcl::Decorated NamedSignature ->
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