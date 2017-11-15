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
terminal Rewrite_kwd 'rewrite' lexer classes {KEYWORD,RESERVED};

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

    ag.errors := trRules.errors ++ rwRules.errors;

    trRules.env = ag.env;

    -----------------
    -- Initialization of lists of things we need to know

    local locCncNamesPair :: Pair<[String] [String]> = partition(\ s::String -> 
        hasLocDcl(getAttrsOn(s,ag.env)),
    cncNames.names);
    local locCncNames :: [String] = locCncNamesPair.fst;
    local nonLocCncNames :: [String] = locCncNamesPair.snd;

    -- We need to know everything's type

    local absTypeDcls :: [[DclInfo]] = map(\ s::String -> getTypeDclAll(s,ag.env), absNames.names);
    local cncTypeDcls :: [[DclInfo]] = map(\ s::String -> getTypeDclAll(s,ag.env), cncNames.names);
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

    local absProdDcls :: [[DclInfo]] = map(\ qn::QName -> getProdsForNt(qn.name,ag.env), absQNames);
    local cncProdDcls :: [[DclInfo]] = map(\ qn::QName -> getProdsForNt(qn.name,ag.env), cncQNames);
    local locCncProdDcls :: [[DclInfo]] = map(\ qn::QName -> getProdsForNt(qn.name,ag.env), locCncQNames);
    local nonLocCncProdDcls :: [[DclInfo]] = map(\ qn::QName -> getProdsForNt(qn.name,ag.env), nonLocCncQNames);
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

    local inhRedexName::String = "inhRedex_" ++ tName.name;

    -- autocopy attribute inRedex_$tName :: Maybe<Origin>; 
    local agDcls::AGDcls = consAGDcls(autocAttr(ag.location, inhRedexName,
        nominalTypeExpr(qnTyId(ag.location, "Maybe"), 
        botlOneString(ag.location, "Origin"))
        ), nilAGDcls());

    -- for $cncType in cncTypes
    -- synthesized attribute restored$cncType :: $cncType;
    agDcls = foldl(\ agDcls::AGDcls name::QName-> 
            consAGDcls(synAttr(ag.location, "restored"++name.name, sTyExpr(ag.location, name.name)), agDcls),
        agDcls, cncQNames);

    -- synthesized attribute $tName :: $tType;
    agDcls = consAGDcls(synAttr(ag.location, tName.name, transType), agDcls);

    -- Occurances of attributes, annotations

    -- for $type in allTypes
    -- attribute inhRedex_$tName occurs on $type;
    agDcls = joinAGDcls(attrOn(ag.location, inhRedexName, allQNames), agDcls);
    
    -- attribute suppliedOrigin occurs on $cncType;
    agDcls = joinAGDcls(attrOn(ag.location, "suppliedOrigin", cncQNames), agDcls);

    -- for $absType in absTypes
    -- attribute restored$cncType occurs on Origin, $absType;
    agDcls = foldl(\ agDcls::AGDcls name::QName->
            joinAGDcls(attrOn(ag.location, "restored"++name.name, absQNames ++ [qName(ag.location, "Origin")]), agDcls),
        agDcls, cncQNames);

    -- annotation redex occurs on $absType;
    agDcls = joinAGDcls(annoOn(ag.location, "redex", absQNames), agDcls);
    
    -- annotation labels occurs on $absType;
    agDcls = joinAGDcls(annoOn(ag.location, "labels", absQNames), agDcls);
    
    -- annotation origin occurs on $absType;
    agDcls = joinAGDcls(annoOn(ag.location, "origin", absQNames), agDcls);
    
    -- attribute wasTransformed occurs on $absType;
    agDcls = joinAGDcls(attrOn(ag.location, "wasTransformed", absQNames), agDcls);  

    -- attribute $tName occurs on $absType;
    agDcls = joinAGDcls(attrOn(ag.location, tName.name, absQNames), agDcls);      

    -- Rewrite rule manipulation
    --
    -- add the identity rule for each type, if an identity rule doesn't already exist
    -- (x -> new(x)) 
    local newRwRules::RewriteRuleList = foldl(\ rwRules::RewriteRuleList name::QName ->
            case rwID(rwRules.rewriteRules, name.name, name.name) of
                | just(_) -> rwRules
                | nothing() -> rewriteRuleCons(terminal(Vbar_kwd, "|"), 
                    rewriteRuleType(qName(ag.location, "a"), '::', qTyExpr(ag.location, name), '->',
                        newFunction('new', '(', baseName(ag.location, "a"), ')')), 
                        rwRules)
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
    agDcls = joinAGDcls(foldl(\ agDcls::AGDcls qn::QName->
         consAGDcls(productionDcl('abstract', 'production', 
            name(mkOriginName(qn.name),ag.location), mkProdSig(ag.location, "o", "Origin", "e", "Decorated" ++ qn.name),
                productionBody('{', prdStmtList(ag.location, [
                    attribDef(ag.location, "o", "isBottomOrigin", falseConst('false'))
                ]), '}')),
            agDcls),
        nilAGDcls(), allQNames), agDcls);
    
    -- Aspecting origin productions

    -- aspect all cnc origins with:
    --
    -- o.wasTransformed = false;
    -- o.concreteOrigin = o;
    agDcls = foldl(\ agDcls::AGDcls name::QName->
        consAGDcls(aspectProductionDcl('aspect', 'production', 
            qName(ag.location, mkOriginName(name.name)), mkAspectProdSig(ag.location, "o", "Origin", "e", "Decorated" ++ name.name),
                prdBody(ag.location, [
                    attribDef(ag.location, "o", "wasTransformed", falseConst('false')),
                    attribDef(ag.location, "o", "concreteOrigin", baseName(ag.location, "o"))
                ])
            ), agDcls),
        agDcls, cncQNames);

    -- restored$cncType attributes
    --
    agDcls = foldl(\ agDcls::AGDcls lhs::QName->
        consAGDcls(aspectProductionDcl('aspect', 'production', 
            qName(ag.location, mkOriginName(lhs.name)), mkAspectProdSig(ag.location, "o", "Origin", "e", "Decorated" ++ lhs.name),
                productionBody('{', foldl(\ stmts::ProductionStmts rhs::QName ->
                    case rwID(newRwRules.rewriteRules, lhs.name, rhs.name) of 
                        | nothing() -> stmts -- this is also probably an error 
                        | just(rule) -> prdStmtList(ag.location, [
                            attribDef(ag.location, "o", "restored"++rhs.name,  
                                applyRw(rule, rhs.name, lhs.name, ag.location, "e"))
                        ])
                    end,
                productionStmtsNil(), cncQNames), '}', location=ag.location)
            ), agDcls),
        agDcls, cncQNames);

    -- aspect all abstract origins with:
    --
    -- o.wasTransformed = wasTransformed(e.origin, e.redex);
    -- o.concreteOrigin = getConcreteOrigin(e.origin, o);
    agDcls = foldl(\ agDcls::AGDcls name::QName->
        consAGDcls(aspectProductionDcl('aspect', 'production', 
            qName(ag.location, mkOriginName(name.name)), mkAspectProdSig(ag.location, "o", "Origin", "e", "Decorated" ++ name.name),
                prdBody(ag.location, [
                attribDef(ag.location, "o", "wasTransformed",
                    argFunc(ag.location, "wasTransformed", appExprList([
                        namedAccess(ag.location, "origin", "e"),
                        namedAccess(ag.location, "redex", "e")
                    ], ag.location))),
                attribDef(ag.location, "o", "concreteOrigin", 
                    argFunc(ag.location, "getConcreteOrigin", appExprList([
                        namedAccess(ag.location, "origin", "e"), 
                        presentAppExpr(baseName(ag.location, "o"))
                    ], ag.location)))
                ])
            ), agDcls),
        agDcls, absQNames);

    -- Non-origin aspecting

    -- for each abstract production
    -- top.wasTransformed = wasTransformed(top.origin, top.redex) || <rhs>.wasTransformed;
    agDcls = foldl(\ agDcls::AGDcls dcl::[DclInfo] ->
        consAGDcls(aspectProdStmt(ag.location,dcl,\ sg::String l::Location ns::NamedSignature ->
            attribDef(ag.location, ns.outputElement.elementName, "wasTransformed",
                foldl(\ e::Expr ie::NamedSignatureElement -> 
                    if hasNamedAttr(ie.typerep.typeName, ag.env, "wasTransformed")
                    then or(e, '||', exprAccess(ag.location, "wasTransformed", ie.elementName))
                    else e,
                argFunc(ag.location, "wasTransformed",
                    appExprList([
                            lhsAccess(ag.location, "origin", ns),
                            lhsAccess(ag.location, "redex", ns) 
                        ], ag.location)
                    ), ns.inputElements))
            ), agDcls),
        agDcls, absProdDcls);

    -- top.restored$cncType = < rewrite + transformation rules ...>
    agDcls = foldl(\ agDcls::AGDcls dcl::[DclInfo] ->
        consAGDcls(aspectProdStmts(ag.location, dcl, \ sg::String l::Location ns::NamedSignature ->
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
                                lhsExprAccess(ag.location, "origin", ns), '.', qNameAttrOccur(qName(ag.location, "concreteOrigin"))),
                                '.', qNameAttrOccur(qName(ag.location, "restored"++rhs.name))) 
                        )
                        else applyRw(rule, rhs.name, ns.typerep.typeName, ag.location, ns.outputElement.elementName)    
                    ))
                end,
            productionStmtsNil(), cncQNames)
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
        consAGDcls(aspectProdStmts(ag.location,dcl,\ sg::String l::Location ns::NamedSignature ->
            if !getTrans(trRules.transformRules, dcl).isJust && ns.outputElement.typerep.typeName != transType.typerep.typeName
            then productionStmtsNil()
            else prdStmtList(ag.location, 
                [attribDef(ag.location, ns.outputElement.elementName, tName.name,
                case getTrans(trRules.transformRules, dcl) of 
                    | nothing() -> prdRecurse(ag.location, ns, tName.name)
                    | just(rule) -> ifThenElse(
                        'if', lhsExprAccess(ag.location, "transformed"++tName.name, ns),
                        -- todo: add annotations to anything here that is one of 
                        -- our abstract productions
                        'then', rule.outputStmt(nsApply(ag.location, ns)),
                        'else', prdRecurse(ag.location, ns, tName.name)
                    )
                end
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
        if !getTrans(trRules.transformRules, dcl).isJust then agDcls 
        else consAGDcls(aspectProdStmts(ag.location,dcl,\ sg::String l::Location ns::NamedSignature ->
            prdStmtList(ag.location, [
                attribDef(ag.location, ns.outputElement.elementName, "transformed_"++tName.name,
                    getTrans(trRules.transformRules, dcl).fromJust.matchProd)
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
        consAGDcls(aspectProdStmts(ag.location,dcl,\ sg::String l::Location ns::NamedSignature ->
            foldl(\ stmts::ProductionStmts rhs::NamedSignatureElement ->
                productionStmtsSnoc(stmts, 
                    attribDef(ag.location, rhs.elementName, inhRedexName,
                            if !getTrans(trRules.transformRules, dcl).isJust
                            then emptyFunc(ag.location, "nothing") -- this might error because it has to be a production
                            else ifThenElse(
                                'if', lhsExprAccess(ag.location, "transformed_"++tName.name, ns),
                                'then', argFunc(ag.location, "just", oneApp(ag.location, mkOrigin(ag.location, ns))),
                                'else', emptyFunc(ag.location, "nothing")
                            )
                    )),
            productionStmtsNil(), ns.inputElements)
            ), agDcls),
        agDcls, absProdDcls);
    
    -- for each concrete type, if it has location, aspect all of its creating
    -- productions with 
    --
    -- top.suppliedOrigin = locationOrigin(top.location);
    agDcls = foldl(\ agDcls::AGDcls dcl::[DclInfo] ->
        consAGDcls(aspectProdStmt(ag.location,dcl,\ sg::String l::Location ns::NamedSignature ->
            attribDef(ag.location, ns.outputElement.elementName, "suppliedOrigin", 
                argFunc(ag.location, "locationOrigin", appExprList([
                    lhsAccess(ag.location, "location", ns)
                ], ag.location))
            )), agDcls),
        agDcls, locCncProdDcls);

    -- or if they don't have location:
    --
    -- top.suppliedOrigin = bottomOrigin();
    agDcls = foldl(\ agDcls::AGDcls dcl::[DclInfo] ->
        consAGDcls(aspectProdStmt(ag.location,dcl,\ sg::String l::Location ns::NamedSignature ->
            attribDef(ag.location, ns.outputElement.elementName, "suppliedOrigin", 
                        emptyFunc(ag.location, "bottomOrigin"))
        ), agDcls), agDcls, nonLocCncProdDcls);

    default annotation location = ag.location;

    forwards to agDcls;
}