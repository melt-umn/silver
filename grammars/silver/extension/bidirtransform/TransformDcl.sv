grammar silver:extension:bidirtransform;

imports silver:definition:env;
imports silver:definition:core;
imports silver:definition:concrete_syntax;
imports silver:definition:type;
imports silver:definition:type:syntax;
imports silver:extension:convenience;
imports silver:modification:defaultattr;
imports silver:modification:ffi;
imports silver:extension:list;
imports silver:modification:autocopyattr;
imports silver:extension:patternmatching;
imports silver:util;
imports silver:modification:let_fix;

terminal Transform_kwd 'transform' lexer classes {KEYWORD,RESERVED};
terminal Transform_kwd 'rewrite' lexer classes {KEYWORD,RESERVED};

concrete production transformAGDcl
ag::AGDcls ::= 'transform' tName::QName '::' transType::TypeExpr 
    '{' trRules::TransformRuleList '}' 
    'rewrite' '{' rwRules::RewriteRuleList '}'
    -- todo: find these elements through inspecting the environment
    -- given the transform and rewrite rules
    'abstract' '{' absNames::NameList '}' 
    'concrete' '{' cncNames::NameList '}'
{

    ----------------
    -- Propagation of attributes

    ag.errors := trRules.errors + absNames.errors;

    trRules.rewriteRules = rwRules;
    trRules.env = ag.env;
    trRules.attrName = tName;

    -----------------
    -- Initialization of lists of things we need to know
    
    -- We need to know all string names for the types used

    local locCncNamesPair :: Pair<[String] [String]> = partition(\ s::String -> 
        hasLocDcl(getAttrsOn(s,ag.env)), --inhDcl("location",_,_,_,_)),
    cncNames);
    local locCncNames :: [String] = locCncNamesPair.fst;
    local nonLocCncNames :: [String] = locCncNamesPair.fst;


    -- We need to know everything's type

    local absTypeDcls :: [[DclInfo]] = map(\ s::String -> getTypeDclAll(s,ag.env), absNames);
    local cncTypeDcls :: [[DclInfo]] = map(\ s::String -> getTypeDclAll(s,ag.env), cncNames);
    local locCncTypeDcls :: [[DclInfo]] = map(\ s::String -> getTypeDclAll(s,ag.env), locCncNames);
    local nonLocCncTypeDcls :: [[DclInfo]] = map(\ s::String -> getTypeDclAll(s,ag.env), nonLocCncNames);
    local allTypeDcls :: [[DclInfo]] = absTypeDcls ++ cncTypeDcls;
    
    -- We need to know everything's qname

    local cncQNames :: [QName] = map(dclQName(ag.location), cncTypeDcls);
    local absQNames :: [QName] = map(dclQName(ag.location), absTypeDcls);
    local locCncQNames :: [QName] = map(dclQName(ag.location), locCncTypeDcls);
    local nonLocCncQNames :: [QName] = map(dclQName(ag.location), nonLocCncTypeDcls);
    local allQNames :: [QName] = cncQNames ++ absQNames;

    -- We need to know all the productions on all of the known types

    local absProdDcls :: [[DclInfo]] = map(\ s::QName -> getProdsForNt(s.name,ag.env), absQNames);
    local cncProdDcls :: [[DclInfo]] = map(\ s::QName -> getProdsForNt(s.name,ag.env), cncQNames);
    local locCncProdDcls :: [[DclInfo]] = map(\ s::QName -> getProdsForNt(s.name,ag.env), locCncQNames);
    local nonLocCncProdDcls :: [[DclInfo]] = map(\ s::QName -> getProdsForNt(s.name,ag.env), nonLocCncQNames);
    local allProdDcls :: [[DclInfo]] = absProdDcls ++ cncProdDcls;

    ---------------
    -- Error checking

    -- If any [DclInfo] has multiple elements (that shouldn't),
    -- that should be caught as an error somewhere else.
    -- If any is size zero, that might not be caught somewhere else.

    ag.errors <- foldl(\ errs::[Error] dcl::[DclInfo] -> 
        if !null(dcl) then errs
        else errs ++ [err(nts.location, "Unknown Nonterminal Type")], -- not worrying about pretty errors yet
        [],
        allTypeDcls);

    -- If there are no productions for a given nonterminal, we don't care,
    -- so we don't need to check that the other lists are non-empty

    -- We expect all [DclInfo] to be specific productions, that may change if I misunderstood
    -- something but for now here are checks for that

    ag.errors <- foldl(\ errs::[Error] dcl::[DclInfo] -> 
        case head(dcl) of 
            |  ntDcl(_,_,_,_,_,_) -> errs
            |  _ -> errs ++ [err(nts.location, "Non-Nonterminal Type")]
        end,
        [],
        allTypeDcls);

    ag.errors <- foldl(\ errs::[Error] dcl::[DclInfo] -> 
        case head(dcl) of 
            |  prodDcl(_,_,_) -> errs
            |  _ -> errs ++ [err(nts.location, "Non-Production Type")]
        end,
        [],
        allProdDcls);

    -- If there's a concrete type pair we don't have a rewrite rule to create
    -- ag.errors

    -----------------------
    -- Generating code

    -- New attributes and annotations

    local inhRedexName::String = "inhRedex_" ++ tName;

    -- autocopy attribute inRedex_$tName :: Maybe<Origin>; 
    local agDcls::AGDcls = consAGDcls(autocAttr(qName(ag.location, inhRedexName),
        nominalTypeExpr(qNameTypeId('Maybe'), 
        botlSome('<', nominalTypeExpr(qNameTypeId('Origin'), botlNone()), '>'))), nilAGDcls());

    -- for $cncType in cncTypes
    -- synthesized attribute restored$cncType :: $cncType;
    agDcls = foldl(\ agDcls::AGDcls name::QName-> 
            consAGDcls(synAttr("restored"+name.name, nominalTypeExpr(qNameTypeId(name.name))), agDcls),
        agDcls, cncQNames);

    -- synthesized attribute $tName :: $tType;
    agDcls = consAGDcls(synAttr(tName, transType), agDcls);

    -- Occurances of attributes, annotations

    -- for $type in allTypes
    -- attribute inhRedex_$tName occurs on $type;
    agDcls = consAGDcls(attrOn(inhRedexName, allQNames), agDcls);
    
    -- attribute suppliedOrigin occurs on $cncType;
    agDcls = consAGDcls(attrOn("suppliedOrigin", cncQNames), agDcls);

    -- for $absType in absTypes
    -- attribute restored$cncType occurs on Origin, $absType;
    agDcls = foldl(\ agDcls::AGDcls name::QName->
            consAGDcls(attrOn("restored"+name.name), absQNames ++ [qName(ag.location, "Origin")]),
        agDcls, cncQNames);

    -- annotation redex occurs on $absType;
    agDcls = consAGDcls(annoOn("redex", absQNames), agDcls);
    
    -- annotation labels occurs on $absType;
    agDcls = consAGDcls(annoOn("labels", absQNames), agDcls);
    
    -- annotation origin occurs on $absType;
    agDcls = consAGDcls(annoOn("origin", absQNames), agDcls);
    
    -- attribute wasTransformed occurs on $absType;
    agDcls = consAGDcls(attrOn("wasTransformed", absQNames), agDcls);  

    -- attribute $tName occurs on $absType;
    agDcls = consAGDcls(attrOn(tName, absQNames), agDcls);      

    -- Rewrite rule manipulation
    --
    -- add the identity rule for each type, if an identity rule doesn't already exist
    -- (new(x) <- x) 
    trRules.rwRules = foldl(\ rwRules::RewriteRuleList name::QName ->
            if rwContainsID(rwRules.rewriteRules, name) then rwRules
            else rewriteRuleCons('|', 
                rewriteRuleSingle(rewriteRuleType(
                    newFunction('new', '(', baseExpr("a"), ')'),
                    '<-', qName(ag.location, "a"), '::', qTyExpr(ag.location, name))), rwRules),
        trRules.rwRules, cncQNames);

    -- Generating origin productions
    --
    -- abstract production origin_$type
    -- o::Origin ::= e::Decorated $type
    -- {
    --      o.isBottomOrigin = false;
    -- }
    --
    agDcls = consAGDcls(foldl(\ agDcls::AGDcls name::QName->
         consAGDcls(productionDcl('abstract', 'production', 
            mkOriginName(name.name), "o::Origin ::= e::Decorated " ++ name.name,
                prdStmtList(ag.location, [
                attributeDef(lhsDefLHS(qName(ag.location, "o")), '.',
                    qAttr(ag.location, "isBottomOrigin"), '=', 
                    lhsReference(qName(ag.location, "false")), ';')]), -- no clue if this is the right production here
            agDcls), nilAGDcls(), allQNames)), agDcls);
    
    -- Aspecting origin productions

    -- aspect all cnc origins with:
    --
    -- o.wasTransformed = false;
    -- o.concreteOrigin = o;
    agDcls = foldl(\ agDcls::AGDcls name::QName->
        consAGDcls(aspectProductionDcl('aspect', 'production', 
            mkOriginName(name.name), "o::Origin ::= e::Decorated " ++ name.name,
                prdStmtList(ag.location, [
                attributeDef(lhsDefLHS(qName(ag.location, "o")), '.',
                    qAttr(ag.location, "wasTransformed"), '=', 
                    lhsReference(qName(ag.location, "false")), ';'),
                attributeDef(lhsDefLHS(qName(ag.location, "o")), '.',
                    qAttr(ag.location, "concreteOrigin"), '=', 
                    lhsReference(qName(ag.location, "o")), ';')])
            ), agDcls),
        agDcls, cncQNames);

    -- restored$cncType attributes
    --
    agDcls = foldl(\ agDcls::AGDcls lhs::QName->
        consAGDcls(aspectProductionDcl('aspect', 'production', 
            mangledName, "o::Origin ::= e::Decorated " ++ lhs.name,
                foldl(\ rhs::QName stmts::ProductionStmts ->
                    if !rwContainsID(trRules.rwRules, rhs) then stmts -- this is also probably an error
                    else attributeDef(lhsDefLHS(qName(ag.location, "o")), '.',
                            qAttr(ag.location, "restored"+rhs.name, '=', 
                                -- todo: this assumes there is only one rewrite 
                                -- rule for any given rewrite output type, or at
                                -- least ignores any others that exist after head().
                                -- 
                                -- Changing this in a meaningful way would involve
                                -- no longer requiring that every type has all
                                -- restored$type variants defined, then doing
                                -- a search to find a pair of defined rewrite rules
                                -- that produce the expected rhs output while 
                                -- using a defined restored type on the lhs, and 
                                -- if that fails not attempting to define that
                                -- rhs type on this lhs type. 
                                applyRw(head(rwID(trRules.rwRules, rhs)), rhs.name, lhs.name, ag.location, "e")                                    
                            , ';')),
                cncQNames, productionStmtsNil())
            ), agDcls),
        agDcls, cncQNames);

    -- aspect all abstract origins with:
    --
    -- o.wasTransformed = wasTransformed(e.origin, e.redex);
    -- o.concreteOrigin = getConcreteOrigin(e.origin, o);
    agDcls = foldl(\ agDcls::AGDcls name::QName->
        consAGDcls(aspectProductionDcl('aspect', 'production', 
            mangledName, "o::Origin ::= e::Decorated " ++ name.name,
                prdStmtList(ag.location, [
                attributeDef(lhsDefLHS(qName(ag.location, "o")), '.',
                    qAttr(ag.location, "wasTransformed"), '=',
                    argFunc(ag.location, "wasTransformed", snocAppExprs(oneAppExprs(
                        namedAccess(ag.location, "origin", "e"),
                        namedAccess(ag.location, "redex", "e")
                    ))), ";"),
                attributeDef(lhsDefLHS(qName(ag.location, "o")), '.',
                    qAttr(ag.location, "concreteOrigin"), '=',
                    argFunc(ag.location, "getConcreteOrigin", snocAppExprs(oneAppExprs(
                        namedAccess(ag.location, "origin", "e"),
                        presentAppExpr(lhsReference(qName(ag.location, "o")))
                    ))), ";")])
            ), agDcls),
        agDcls, absQNames);

    -- Non-origin aspecting

    -- for each abstract production
    -- top.wasTransformed = wasTransformed(top.origin, top.redex) || <rhs>.wasTransformed;
    agDcls = foldl(\ agDcls::AGDcls dcl::[DclInfo] ->
        consAGDcls(aspectProdStmt(dcl,\ sg::String l::Location ns::NamedSignature ->
            foldl(\ ie::NamedSignatureElement e::Expr -> 
                if contains(getAttrsOn(ie.typerep.typeName, ag.env), synDcl("wasTransformed", _, _, _, _))
                then or(e, '||', ie.elementName)
                else e,
            ns.inputElements,
            attributeDef(lhsDefLHS(qName(ag.location, ns.outputElement.elementName)), '.', 
                qAttr(ag.location, "wasTransformed"), '=', 
                applicationExpr(qName(ag.location, "wasTransformed"),'(',
                    snocAppExprs(oneAppExprs(lhsAccess(ag.location, "origin", ns)),
                        lhsAccess(ag.location, "redex", ns)),
                    ')',';')))
            ), agDcls),
        agDcls, absProdDcls);

    -- top.restored$cncType = < rewrite + transformation rules ...>
    agDcls = foldl(\ agDcls::AGDcls dcl::[DclInfo] ->
        consAGDcls(aspectProdStmt(dcl,\ sg::String l::Location ns::NamedSignature ->
            foldl(\ rhs::QName stmts::ProductionStmts ->
                -- if there is a transformation rule from this production to this lhs then use that
                if !rwContains(trRules.rwRules, rhs) then stmts
                else productionStmtsSnoc(stmts, attributeDef(lhsDefLHS(qName(ag.location, ns.outputElement.elementName)), '.',
                    qAttr(ag.location, "restored"+rhs.name), '=',
                    if rwContainsProd(trRules.rwRules, ns.fullName, rhs) 
                    then ifThenElse(
                        'if', lhsAccess(ag.location, "wasTransformed", ns),
                        -- use the rewrite production
                        'then', applyRwProd(rwProd(trRules.rwRules, ns.fullName, rhs), ag.location, ns, trRules),
                        -- refer to the concrete origin's restored element
                        'else', access(access(
                            lhsAccess(ag.location, "origin", ns), '.', qNameAttrOccur(qName(ag.location, "concreteOrigin"))),
                            '.', qNameAttrOccur(qName(ag.location, "restored"+rhs.name))) 
                    )
                    else applyRw(head(rwID(trRules.rwRules, rhs)), rhs.name, ns.typerep.typeName, ag.location, ns.outputElement.elementName)    
                , ';')),
            cncQNames, productionStmtsNil())
            ), agDcls),
        agDcls, absProdDcls);

    -- top.$tName = ...
    --  if this abstract production has no transformations defined for it,
    --  then,
    --    if top is the same type as the transformation
    --    then $thisProd($arg.$tName, origin=$thisType_Origin(top), redex=(..).inhOrigin_$tName, labels=[])
    --    else don't define this?    ^
    --  else if transformed_$tName   |
    --    then apply transformation  |
    --    else see ------------------/
    agDcls = foldl(\ agDcls::AGDcls dcl::[DclInfo] ->
        consAGDcls(aspectProdStmt(dcl,\ sg::String l::Location ns::NamedSignature ->
            if !hasTrans(trRules, dcl) && !tyCheck(ns.outputElement.typerep, transType.typerep)
            then productionStmtsNil()
            else prdStmtList(ag.location, 
                [attributeDef(lhsDefLHS(qName(ag.location, ns.outputElement.elementName)), '.',
                qAttr(ag.location, tName), '=',
                if hasTrans(trRules, dcl)
                then ifThenElse(
                    'if', lhsAccess(ag.location, "transformed"+tName, ns),
                    -- todo: add annotations to anything here that is one of 
                    -- our abstract productions
                    'then', getTrans(trRules, dcl).outputStmt(ag.location, nsApply(ns)),
                    'else', prdRecurse(ag.location, ns, tName)
                )
                else oneApp(prdRecurse(ag.location, ns, tName))
            )])
            ), agDcls),
        agDcls, absProdDcls);

    -- top.transformed_$tName = ...
    --  if this abstract production has no transformation defined for it,
    --  then don't define this
    --  else if the rhs matches this transformation, 
    --    then true
    --    else false
    agDcls = foldl(\ agDcls::AGDcls dcl::[DclInfo] ->
        if !hasTrans(trRules, dcl) then agDcls 
        else consAGDcls(aspectProdStmt(dcl,\ sg::String l::Location ns::NamedSignature ->
            prdStmtList(ag.location, [
                attributeDef(lhsDefLHS(qName(ag.location, ns.outputElement.elementName)), '.',
                    qAttr(ag.location, "transformed_"+tName), '=', trRule(trRules, dcl).matchProd)
            ])
            ), agDcls),
        agDcls, absProdDcls);

    -- <rhs>.inhRedex_$tName = ...
    --  if this abstract production has no transformation defined for it,
    --  then nothing()
    --  else if transformed$tName
    --    then just($thisType_Origin(top))
    --    else nothing()
    agDcls = foldl(\ agDcls::AGDcls dcl::[DclInfo] ->
        consAGDcls(aspectProdStmt(dcl,\ sg::String l::Location ns::NamedSignature ->
            foldl(\ rhs::NamedSignatureElement stmts::ProductionStmts ->
                productionStmtsSnoc(stmts, 
                    attributeDef(lhsDefLHS(qName(ag.location, rhs.name)), '.',
                        qAttr(ag.location, inhRedexName), '=',
                            if !hasTrans(trRules, dcl) 
                            then emptyFunc(ag.location, "nothing") -- this might error because it has to be a production
                            else ifThenElse(
                                'if', lhsAccess(ag.location, "transformed_"+tName, ns),
                                'then', argFunc(ag.location, "just", oneApp(mkOrigin(ag.location, ns))),
                                'else', emptyFunc(ag.location, "nothing")
                            )
                    )),
            ns.inputElements, productionStmtsNil())
            ), agDcls),
        agDcls, absProdDcls);
    
    -- for each concrete type, if it has location, aspect all of its creating
    -- productions with 
    --
    -- top.suppliedOrigin = locationOrigin(top.location);
    agDcls = foldl(\ agDcls::AGDcls dcl::[DclInfo] ->
        consAGDcls(aspectProdStmt(dcl,\ sg::String l::Location ns::NamedSignature ->
            attributeDef(lhsDefLHS(qName(ag.location, ns.outputElement.elementName)), '.', 
                qAttr(ag.location, "suppliedOrigin"), '=', 
                applicationExpr(qName(ag.location, "locationOrigin"),'(',
                    lhsAccess(ag.location, "location", ns),
                    ')',';'))
            ), agDcls),
        agDcls, locCncProdDcls);

    -- or if they don't have location:
    --
    -- top.suppliedOrigin = bottomOrigin();
    agDcls = foldl(\ agDcls::AGDcls dcl::[DclInfo] ->
        consAGDcls(aspectProdStmt(dcl,\ sg::String l::Location ns::NamedSignature ->
            attributeDef(lhsDefLHS(qName(ag.location, ns.outputElement.elementName)), '.',
                        qAttr(ag.location, "suppliedOrigin"), '=', 
                        applicationEmpty(qName(ag.location, "bottomOrigin"),'(',')'), ';')
        ), agDcls), agDcls, nonLocCncProdDcls);

    forwards to agDcls;
}