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
imports silver:modification:primitivepattern; 
imports silver:modification:copper; 

terminal Transform_kwd 'transmute' lexer classes {KEYWORD,RESERVED};
terminal Rewrite_kwd 'rewrite' lexer classes {KEYWORD,RESERVED};

concrete production transformAGDclFull
ag::AGDcl ::= 'transmute' tName::QName '::' transType::TypeExpr 
    '{' trRules::TransformRuleList '}' 
    'rewrite' '{' rwRules::RewriteRuleList '}'
    'abstract' '{' absNames::NameList '}' 
    'concrete' '{' cncNames::NameList '}' ';'
{
    ag.pp = "transmute " ++ tName.pp ++ "::" ++ transType.pp ++
        "{" ++ trRules.pp ++ "} rewrite {" ++ rwRules.pp ++ 
        "} abstract {" ++ absNames.pp ++ "} concrete {" ++ cncNames.pp ++ "};";

    forwards to transformRewrite(tName.name, transType, trRules, rwRules, 
        absNames.names ++ trRules.absStrings ++ rwRules.absStrings, 
        cncNames.names ++ trRules.cncStrings ++ rwRules.cncStrings, location=ag.location);
}

concrete production transformAGDclNoNames
ag::AGDcl ::= 'transmute' tName::QName '::' transType::TypeExpr 
    '{' trRules::TransformRuleList '}' 
    'rewrite' '{' rwRules::RewriteRuleList '}' ';'
{
    ag.pp = "transmute " ++ tName.pp ++ "::" ++ transType.pp ++
        "{" ++ trRules.pp ++ "} rewrite {" ++ rwRules.pp ++ "};";

    forwards to transformRewrite(tName.name, transType, trRules, rwRules,
        trRules.absStrings ++ rwRules.absStrings, trRules.cncStrings ++ rwRules.cncStrings, location=ag.location);
}

abstract production transformRewrite 
ag::AGDcl ::= tName::String transType::TypeExpr 
    trRules::TransformRuleList 
    rwRules::RewriteRuleList
    absStrings::[String]  
    cncStrings::[String] 
{

    ----------------
    -- Propagation of attributes

    ag.errors := trRules.errors ++ rwRules.errors;

    trRules.env = ag.env;

    -- ag.moduleNames = [];
    -- ag.terminalPrefixes = [];

    local env::Decorated Env = newScopeEnv([], ag.env);

    -----------------
    -- Initialization of lists of things we need to know

    -- local locCncNamesPair :: Pair<[String] [String]> = partition(\ s::String -> 
    --     hasLocDcl(getAttrsOn(s,ag.env)),
    -- cncStrings);
    -- local locCncNames :: [String] = locCncNamesPair.fst;
    -- local nonLocCncNames :: [String] = locCncNamesPair.snd;

    local locCncNames :: [String] = [];
    local nonLocCncNames :: [String] = cncStrings;

    -- We need to know everything's type

    local absTypeDcls :: [[DclInfo]] = map(\ s::String -> getTypeDclAll(s,env), absStrings);
    local cncTypeDcls :: [[DclInfo]] = map(\ s::String -> getTypeDclAll(s,env), cncStrings);
    local locCncTypeDcls :: [[DclInfo]] = map(\ s::String -> getTypeDclAll(s,env), locCncNames);
    local nonLocCncTypeDcls :: [[DclInfo]] = map(\ s::String -> getTypeDclAll(s,env), nonLocCncNames);
    local allTypeDcls :: [[DclInfo]] = absTypeDcls ++ cncTypeDcls;
    
    -- We need to know everything's qname

    local cncQNames :: [QName] = map(dclQName(ag.location), cncStrings);
    local absQNames :: [QName] = map(dclQName(ag.location), absStrings);
    local locCncQNames :: [QName] = map(dclQName(ag.location), locCncNames);
    local nonLocCncQNames :: [QName] = map(dclQName(ag.location), nonLocCncNames);
    local allQNames :: [QName] = cncQNames ++ absQNames;

    -- We need to know all the productions on all of the known types

    local absProdDcls :: [[DclInfo]] = map(\ qn::QName -> getProdsForNt(qn.name,env), absQNames);
    local cncProdDcls :: [[DclInfo]] = map(\ qn::QName -> getProdsForNt(qn.name,env), cncQNames);
    local locCncProdDcls :: [[DclInfo]] = map(\ qn::QName -> getProdsForNt(qn.name,env), locCncQNames);
    local nonLocCncProdDcls :: [[DclInfo]] = map(\ qn::QName -> getProdsForNt(qn.name,env), nonLocCncQNames);
    local allProdDcls :: [[DclInfo]] = absProdDcls ++ cncProdDcls;

    ---------------
    -- Error checking

    -- If any [DclInfo] has multiple elements (that shouldn't),
    -- that should be caught as an error somewhere else.
    -- If any is size zero, that might not be caught somewhere else.

    ag.errors <- foldl(\ errs::[Message] dcl::[DclInfo] -> 
        if !null(dcl) then errs
        else errs ++ [err(ag.location, "Unknown Nonterminal Type")], -- not worrying about pretty errors yet
        [],
        allTypeDcls);

    -- If there are no productions for a given nonterminal, we don't care,
    -- so we don't need to check that the other lists are non-empty

    -- We expect all [DclInfo] to be specific productions, that may change if I misunderstood
    -- something but for now here are checks for that

    ag.errors <- foldl(\ errs::[Message] dcl::[DclInfo] -> 
        case head(dcl) of 
            |  ntDcl(_,_,_,_,_,_) -> errs
            |  _ -> errs ++ [err(ag.location, "Non-Nonterminal Type")]
        end,
        [],
        allTypeDcls);

    ag.errors <- foldl(\ errs::[Message] dcl::[DclInfo] -> 
        case head(dcl) of 
            |  prodDcl(_,_,_) -> errs
            |  _ -> errs ++ [err(ag.location, "Non-Production Type")]
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
    local agDcls::AGDcl = autocAttr(ag.location, inhRedexName,
        nominalTypeExpr(qnTyId(ag.location, "Maybe"), 
        botlOneString(ag.location, "Origin"), location=ag.location));

    -- for $cncType in cncTypes
    -- synthesized attribute restored$cncType :: $cncType;
    local agDcls2::AGDcl = foldl(\ agDcls::AGDcl name::QName-> 
            appendAGDcl(synAttr(ag.location, "restored"++name.name, sTyExpr(ag.location, name.name)), agDcls, location=ag.location),
        agDcls, cncQNames);

    -- synthesized attribute $tName :: $tType;
    local agDcls3::AGDcl = appendAGDcl(synAttr(ag.location, tName, transType), agDcls2, location=ag.location);

    -- Occurances of attributes, annotations

    -- Problem in future: only apply this on attributes that they are not 
    -- already defined on. This doesn't work because checking if an attribute
    -- occurs on an element we're working with causes a loop.

    -- for $type in allTypes
    -- attribute inhRedex_$tName occurs on $type;
    local agDcls4::AGDcl = appendAGDcl(attrOn(ag.location, inhRedexName, allQNames), agDcls3, location=ag.location);
    
    -- attribute suppliedOrigin occurs on $cncType;
    local agDcls5::AGDcl = appendAGDcl(attrOn(ag.location, "suppliedOrigin", cncQNames), agDcls4, location=ag.location);

    -- for $absType in absTypes
    -- attribute restored$cncType occurs on Origin, $absType;
    local agDcls6::AGDcl = foldl(\ agDcls::AGDcl name::QName->
            appendAGDcl(attrOn(ag.location, "restored"++name.name, absQNames ++ [qName(ag.location, "Origin")]), agDcls, location=ag.location),
        agDcls5, cncQNames);

    -- annotation redex occurs on $absType;
    local agDcls7::AGDcl = appendAGDcl(annoOn(ag.location, "redex", absQNames), agDcls6, location=ag.location);
    
    -- annotation labels occurs on $absType;
    local agDcls8::AGDcl = appendAGDcl(annoOn(ag.location, "labels", absQNames), agDcls7, location=ag.location);
    
    -- annotation origin occurs on $absType;
    local agDcls9::AGDcl = appendAGDcl(annoOn(ag.location, "origin", absQNames), agDcls8, location=ag.location);
    
    -- attribute wasTransformed occurs on $absType;
    local agDcls10::AGDcl = appendAGDcl(attrOn(ag.location, "wasTransformed", absQNames), agDcls9, location=ag.location);  

    -- attribute $tName occurs on $absType;
    local agDcls11::AGDcl = appendAGDcl(attrOn(ag.location, tName, absQNames), agDcls10, location=ag.location);      

    -- Rewrite rule manipulation
    --
    -- add the identity rule for each type, if an identity rule doesn't already exist
    -- (x -> new(x)) 
    local newRwRules::RewriteRuleList = foldl(\ rwRules::RewriteRuleList name::QName ->
            case rwID(rwRules.rewriteRules, name.name, name.name) of
                | just(_) -> rwRules
                | nothing() -> rewriteRuleCons(terminal(Vbar_kwd, "|"), 
                    rewriteRuleType(qName(ag.location, "a"), '::', qTyExpr(ag.location, name), '->',
                        newFunction('new', '(', baseName(ag.location, "a"), ')', location=ag.location), location=ag.location), 
                        rwRules, location=ag.location)
            end,
        rwRules, cncQNames);

    -- Generating origin productions
    --
    -- abstract production origin_$type
    -- o::Origin ::= e::Decorated $type
    -- {
    --      o.isBottomOrigin = false;
    -- }
    --
    local agDcls12::AGDcl = appendAGDcl(foldl(\ agDcls::AGDcl qn::QName->
         appendAGDcl(productionDcl('abstract', 'production', 
            name(mkOriginName(qn.name),ag.location), mkProdSig(ag.location, "o", "Origin", "e", "Decorated" ++ qn.name),
                productionBody('{', prdStmtList(ag.location, [
                    attribDef(ag.location, "o", "isBottomOrigin", falseConst('false', location=ag.location))
                ]), '}', location=ag.location), location=ag.location),
            agDcls, location=ag.location),
        emptyAGDcl(location=ag.location), allQNames), agDcls11, location=ag.location);
    
    -- Aspecting origin productions

    -- aspect all cnc origins with:
    --
    -- o.wasTransformed = false;
    -- o.concreteOrigin = o;
    local agDcls13::AGDcl = foldl(\ agDcls::AGDcl name::QName->
        appendAGDcl(aspectProductionDcl('aspect', 'production', 
            qName(ag.location, mkOriginName(name.name)), mkAspectProdSig(ag.location, "o", "Origin", "e", "Decorated" ++ name.name),
                prdBody(ag.location, [
                    attribDef(ag.location, "o", "wasTransformed", falseConst('false', location=ag.location)),
                    attribDef(ag.location, "o", "concreteOrigin", baseName(ag.location, "o"))
                ]), location=ag.location), agDcls, location=ag.location),
        agDcls12, cncQNames);

    -- restored$cncType attributes
    --
    local agDcls14::AGDcl = foldl(\ agDcls::AGDcl lhs::QName->
        appendAGDcl(aspectProductionDcl('aspect', 'production', 
            qName(ag.location, mkOriginName(lhs.name)), mkAspectProdSig(ag.location, "o", "Origin", "e", "Decorated" ++ lhs.name),
                productionBody('{', foldl(\ stmts::ProductionStmts rhs::QName ->
                    case rwID(newRwRules.rewriteRules, lhs.name, rhs.name) of 
                        | nothing() -> stmts -- this is also probably an error 
                        | just(rule) -> prdStmtList(ag.location, [
                            attribDef(ag.location, "o", "restored"++rhs.name,  
                                applyRw(rule, rhs.name, lhs.name, ag.location, "e"))
                        ])
                    end,
                productionStmtsNil(location=ag.location), cncQNames), '}', location=ag.location), location=ag.location), agDcls, location=ag.location),
        agDcls13, cncQNames);

    -- aspect all abstract origins with:
    --
    -- o.wasTransformed = wasTransformed(e.origin, e.redex);
    -- o.concreteOrigin = getConcreteOrigin(e.origin, o);
    local agDcls15::AGDcl = foldl(\ agDcls::AGDcl name::QName->
        appendAGDcl(aspectProductionDcl('aspect', 'production', 
            qName(ag.location, mkOriginName(name.name)), mkAspectProdSig(ag.location, "o", "Origin", "e", "Decorated" ++ name.name),
                prdBody(ag.location, [
                attribDef(ag.location, "o", "wasTransformed",
                    argFunc("wasTransformed", appExprList([
                        namedAccess(ag.location, "origin", "e"),
                        namedAccess(ag.location, "redex", "e")
                    ], ag.location), ag.location)),
                attribDef(ag.location, "o", "concreteOrigin", 
                    argFunc("getConcreteOrigin", appExprList([
                        namedAccess(ag.location, "origin", "e"), 
                        presentAppExpr(baseName(ag.location, "o"), location=ag.location)
                    ], ag.location), ag.location))
                ]), location=ag.location), agDcls, location=ag.location),
        agDcls14, absQNames);

    -- Non-origin aspecting

    -- for each abstract production
    -- top.wasTransformed = wasTransformed(top.origin, top.redex) || <rhs>.wasTransformed;
    local agDcls16::AGDcl = foldl(\ agDcls::AGDcl dcl::[DclInfo] ->
        appendAGDcl(aspectProdStmt(ag.location,dcl,\ sg::String l::Location ns::NamedSignature ->
            attribDef(ag.location, ns.outputElement.elementName, "wasTransformed",
                foldl(\ e::Expr ie::NamedSignatureElement -> 
                    -- Can't do this? 
                    --if hasNamedAttr(ie.typerep.typeName, env, "wasTransformed")
                    if contains(ie.typerep.typeName, absStrings)
                    then or(e, '||', exprAccess(ag.location, "wasTransformed", ie.elementName), location=ag.location)
                    else e,
                argFunc("wasTransformed",
                    appExprList([
                            lhsAccess(ag.location, "origin", ns),
                            lhsAccess(ag.location, "redex", ns) 
                        ], ag.location),
                    ag.location), ns.inputElements))), agDcls, location=ag.location),
        agDcls15, absProdDcls);

    -- top.restored$cncType = < rewrite + transformation rules ...>
    local agDcls17::AGDcl = foldl(\ agDcls::AGDcl dcl::[DclInfo] ->
        appendAGDcl(aspectProdStmts(ag.location, dcl, \ sg::String l::Location ns::NamedSignature ->
            foldl(\ stmts::ProductionStmts rhs::QName ->
                -- if there is a rewrite rule from this production to this lhs then use that
                case rwMatch(newRwRules.rewriteRules, rhs.name, ns) of 
                    | nothing() -> stmts
                    | just(rule) -> productionStmtsSnoc(stmts, 
                        attribDef(ag.location, ns.outputElement.elementName, "restored"++rhs.name,
                        if rule.inputProduction.isJust 
                        then ifThenElse(
                            'if', lhsExprAccess(ag.location, "wasTransformed", ns),
                            -- use the rewrite production
                            'then', applyRwProd(rule, rhs.name, ag.location, ns),
                            -- refer to the concrete origin's restored element
                            'else', access(access(
                                lhsExprAccess(ag.location, "origin", ns), '.', qNameAttrOccur(qName(ag.location, "concreteOrigin"), location=ag.location), location=ag.location),
                                '.', qNameAttrOccur(qName(ag.location, "restored"++rhs.name), location=ag.location), location=ag.location), 
                        location=ag.location)
                        else applyRw(rule, rhs.name, ns.typerep.typeName, ag.location, ns.outputElement.elementName)    
                    ), location=ag.location)
                end,
            productionStmtsNil(location=ag.location), cncQNames)), agDcls, location=ag.location),
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
    local agDcls18::AGDcl = foldl(\ agDcls::AGDcl dcl::[DclInfo] ->
        appendAGDcl(aspectProdStmts(ag.location,dcl,\ sg::String l::Location ns::NamedSignature ->
            if !getTrans(trRules.transformRules, dcl).isJust && ns.outputElement.typerep.typeName != transType.typerep.typeName
            then productionStmtsNil(location=ag.location)
            else prdStmtList(ag.location, 
                [attribDef(ag.location, ns.outputElement.elementName, tName,
                case getTrans(trRules.transformRules, dcl) of 
                    | nothing() -> prdRecurse(ag.location, ns, tName)
                    | just(rule) -> ifThenElse(
                        'if', lhsExprAccess(ag.location, "transformed" ++ tName, ns),
                        -- todo: add annotations to anything here that is one of 
                        -- our abstract productions
                        'then', rule.outputStmt(nsApply(ag.location, ns)),
                        'else', prdRecurse(ag.location, ns, tName),
                    location=ag.location)
                end
            )])
            ), agDcls, location=ag.location),
        agDcls17, absProdDcls);

    -- top.transformed_$tName = ...
    --  if this abstract production has no transformation defined for it,
    --  then don't define this
    --  else if the rhs matches this transformation, 
    --    then true
    --    else false
    local agDcls19::AGDcl = foldl(\ agDcls::AGDcl dcl::[DclInfo] ->
        if !getTrans(trRules.transformRules, dcl).isJust then agDcls 
        else appendAGDcl(aspectProdStmts(ag.location,dcl,\ sg::String l::Location ns::NamedSignature ->
            prdStmtList(ag.location, [
                attribDef(ag.location, ns.outputElement.elementName, "transformed_" ++ tName,
                    getTrans(trRules.transformRules, dcl).fromJust.matchProd)
            ])
            ), agDcls, location=ag.location),
        agDcls18, absProdDcls);

    -- <rhs>.inhRedex_$tName = ...
    --  if this abstract production has no transformation defined for it,
    --  then nothing()
    --  else if transformed$tName
    --    then just($thisType_Origin(top))
    --    else nothing()
    local agDcls20::AGDcl = foldl(\ agDcls::AGDcl dcl::[DclInfo] ->
        appendAGDcl(aspectProdStmts(ag.location,dcl,\ sg::String l::Location ns::NamedSignature ->
            foldl(\ stmts::ProductionStmts rhs::NamedSignatureElement ->
                productionStmtsSnoc(stmts, 
                    attribDef(ag.location, rhs.elementName, inhRedexName,
                            if !getTrans(trRules.transformRules, dcl).isJust
                            then emptyFunc("nothing", ag.location) -- this might error because it has to be a production
                            else ifThenElse(
                                'if', lhsExprAccess(ag.location, "transformed_"++tName, ns),
                                'then', argFunc("just", oneApp(ag.location, mkOrigin(ag.location, ns)), ag.location),
                                'else', emptyFunc("nothing", ag.location),
                            location=ag.location)
                    ), location=ag.location),
            productionStmtsNil(location=ag.location), ns.inputElements)), agDcls, location=ag.location),
        agDcls19, absProdDcls);
    
    -- for each concrete type, if it has location, aspect all of its creating
    -- productions with 
    --
    -- top.suppliedOrigin = locationOrigin(top.location);
    local agDcls21::AGDcl = foldl(\ agDcls::AGDcl dcl::[DclInfo] ->
        appendAGDcl(aspectProdStmt(ag.location,dcl,\ sg::String l::Location ns::NamedSignature ->
            attribDef(ag.location, ns.outputElement.elementName, "suppliedOrigin", 
                argFunc("locationOrigin", appExprList([
                    lhsAccess(ag.location, "location", ns)
                ], ag.location), ag.location)
            )), agDcls, location=ag.location),
        agDcls20, locCncProdDcls);

    -- or if they don't have location:
    --
    -- top.suppliedOrigin = bottomOrigin();
    local agDcls22::AGDcl = foldl(\ agDcls::AGDcl dcl::[DclInfo] ->
        appendAGDcl(aspectProdStmt(ag.location,dcl,\ sg::String l::Location ns::NamedSignature ->
            attribDef(ag.location, ns.outputElement.elementName, "suppliedOrigin", 
                        emptyFunc("bottomOrigin", ag.location))), agDcls, location=ag.location), agDcls21, nonLocCncProdDcls);

    -- default annotation location = ag.location;

    --ag.liftedAGDcls = agDcls22; 
    forwards to agDcls22;
}