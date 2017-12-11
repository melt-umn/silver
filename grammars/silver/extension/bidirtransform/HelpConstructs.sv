grammar silver:extension:bidirtransform;


abstract production qAttr
top::QNameAttrOccur ::= name::String 
{
    forwards to qNameAttrOccur(qName(top.location, name), location=top.location);
}

abstract production emptyFunc
top::Expr ::= name::String 
{
    forwards to applicationEmpty(baseName(name, location=top.location),'(',')', location=top.location);
}

abstract production argFunc
top::Expr ::= name::String args::AppExprs 
{
    forwards to applicationExpr(baseName(name, location=top.location), '(',args,')', location=top.location);
}

abstract production oneArgFunc
top::Expr ::= name::String arg::AppExpr
{
    forwards to argFunc(name, appExprList([arg], location=top.location), location=top.location);
}

abstract production fullFunc
top::Expr ::= name::String args::AppExprs annos::AnnoAppExprs 
{
    forwards to application(baseName(name, location=top.location), '(',args,',',annos,')', location=top.location);
}

abstract production synAttr
top::AGDcl ::= nme::String tyexpr::TypeExpr
{
    forwards to attributeDclSyn('synthesized', 'attribute', name(nme, top.location),
        botlNone(location=top.location), '::', tyexpr, ';', location=top.location);
}

abstract production mkLhsDef
top::DefLHS ::= s::String 
{
    forwards to concreteDefLHS(qName(top.location, s), location=top.location);
}

abstract production autocAttr
top::AGDcl ::= nme::String tyexpr::TypeExpr
{
    forwards to attributeDclAuto('autocopy', 'attribute', name(nme, top.location),
        botlNone(location=top.location), '::', tyexpr, ';', location=top.location);
}

abstract production annoOn
top::AGDcl ::= name::String onNames::[String]
{
    local qntlName::QNameWithTL = qNameWithTL(qName(top.location, name), botlNone(location=top.location));

    local qntOnNames::[QNameWithTL] = map(\ s::String -> 
            qNameWithTL(qName(top.location, s), botlNone(location=top.location)), 
        onNames);

    forwards to makeOccursDclsHelp(top.location, qntlName, qntOnNames);

    -- forwards to if null(onNames) then emptyAGDcl(location=top.location)
    --     else appendAGDcl(
    --         annotateDcl('annotation', qName(top.location, name), botlNone(location=top.location),
    --             'occurs', 'on', qName(top.location, head(onNames)), botlNone(location=top.location), ';', location=top.location),
    --         annoOn(name, tail(onNames), location=top.location), location=top.location);
}

abstract production attrOn
top::AGDcl ::= name::String onNames::[String]
{
    forwards to if null(onNames) then emptyAGDcl(location=top.location)
        else appendAGDcl(
            attributionDcl('attribute', qName(top.location, name), botlNone(location=top.location), 
                'occurs', 'on', qName(top.location, head(onNames)), botlNone(location=top.location), ';', location=top.location),
            attrOn(name, tail(onNames), location=top.location), location=top.location);
}

abstract production consAnnoAppExprs
top::AnnoAppExprs ::= a::AnnoAppExprs b::AnnoAppExprs
{
    forwards to case b of 
        | snocAnnoAppExprs(c,_,expr) -> consAnnoAppExprs(snocAnnoAppExprs(a, ',', expr, location=top.location), c, location=top.location)
        | oneAnnoAppExprs(expr) -> snocAnnoAppExprs(a, ',', expr, location=top.location)
        | _ -> a
    end;
}

abstract production nsAspectProdSig
top::AspectProductionSignature ::= ns::Decorated NamedSignature 
{
    forwards to aspectProductionSignature(
        aspectProductionLHSFull(
            name(ns.outputElement.elementName, top.location), 
            ns.outputElement.typerep, location=top.location),
        '::=',
        nsAspectProdRHS(ns.inputElements, location=top.location), location=top.location);
}

abstract production nsAspectProdRHS
top::AspectRHS ::= inElements::[NamedSignatureElement]
{
    local hd::NamedSignatureElement = head(inElements);

    forwards to if null(inElements) then aspectRHSElemNil(location=top.location)
        else aspectRHSElemCons(
            aspectRHSElemTyped(name(hd.elementName, top.location), '::', typerepTypeExpr(hd.typerep, location=top.location), location=top.location), 
            nsAspectProdRHS(tail(inElements), location=top.location), location=top.location);
}

abstract production aspectProdStmt
top::AGDcl ::= dcl::Decorated NamedSignature fn::(ProductionStmt ::= Decorated NamedSignature)
{
    forwards to aspectProdStmts(dcl,\ ns::Decorated NamedSignature ->
        productionStmtsSnoc(productionStmtsNil(location=top.location), fn(ns), location=top.location),
      location=top.location);
}

abstract production aspectProdStmts
top::AGDcl ::= dcl::Decorated NamedSignature fn::(ProductionStmts ::= Decorated NamedSignature)
{
    forwards to
        fakeAspectProductionDcl('aspect', 'production', 
        --aspectProductionDcl('aspect', 'production', 
            qName(top.location, dcl.fullName), nsAspectProdSig(dcl, location=top.location), 
            productionBody('{', fn(dcl), '}', location=top.location), location=top.location);

}

abstract production prdStmtList
top::ProductionStmts ::= stmts::[ProductionStmt]
{
    forwards to if null(stmts) then productionStmtsNil(location=top.location)
        else productionStmtsSnoc(prdStmtList(tail(stmts), location=top.location), head(stmts), location=top.location);
}

abstract production prdBody
top::ProductionBody ::= stmts::[ProductionStmt]
{
    forwards to productionBody('{', prdStmtList(stmts, location=top.location), '}', location=top.location);
}

abstract production attribDef
top::ProductionStmt ::= lhs::String att::String eqs::Expr
{
    forwards to attributeDef(
        mkLhsDef(lhs, location=top.location), 
        '.',
        qAttr(att, location=top.location), 
        '=', 
        eqs, 
        ';', 
        location=top.location);
}

abstract production synChdAttrDef
top::ProductionStmt ::= lhs::String att::String eqs::Expr
{
    forwards to synthesizedAttributeDef(
        decorate childDefLHS(decorate qName(top.location, lhs) with {}, location=top.location) with {},
        decorate qAttr(att, location=top.location) with {},
        eqs,
        location=top.location
    );
}

abstract production inhChdAttrDef
top::ProductionStmt ::= lhs::String att::String eqs::Expr
{
    forwards to inheritedAttributeDef(
        decorate childDefLHS(decorate qName(top.location, lhs) with {}, location=top.location) with {},
        decorate qAttr(att, location=top.location) with {},
        eqs,
        location=top.location
    );
}

abstract production synAttrDef
top::ProductionStmt ::= lhs::String att::String eqs::Expr
{
    forwards to synthesizedAttributeDef(
        decorate lhsDefLHS(decorate qName(top.location, lhs) with {}, location=top.location) with {},
        decorate qAttr(att, location=top.location) with {},
        eqs,
        location=top.location
    );
}

abstract production qnTyId
top::QNameType ::= s::String 
{
    forwards to qNameTypeId(terminal(IdUpper_t, s), location=top.location);
}

abstract production sTyExpr
top::TypeExpr ::= s::String 
{
    forwards to nominalTypeExpr(qNameTypeId(terminal(IdUpper_t, s), location=top.location),
        botlNone(location=top.location), location=top.location);
}

abstract production decTyExpr
top::TypeExpr ::= s::String 
{
    forwards to refTypeExpr('Decorated', sTyExpr(s, location=top.location), location=top.location);
}

abstract production qTyExpr
top::TypeExpr ::= q::QName 
{
    forwards to sTyExpr(q.name, location=top.location);
}

abstract production lhsAccess
top::AppExpr ::= name::String ns::Decorated NamedSignature 
{
    forwards to namedAccess(name, ns.outputElement.elementName, location=top.location);
}  

abstract production namedAccess
top::AppExpr ::= name::String accessOn::String 
{
    forwards to presentAppExpr(exprAccess(name, accessOn, location=top.location), location=top.location);
} 

abstract production lhsExprAccess
top::Expr ::= name::String ns::Decorated NamedSignature
{
    forwards to exprAccess(name, ns.outputElement.elementName, location=top.location);
}

abstract production qAccess
top::Expr ::= q::String accessOn::Expr 
{
    forwards to access(accessOn, '.', qNameAttrOccur(qName(top.location, q), location=top.location), location=top.location);
}

abstract production exprAccess
top::Expr ::= name::String accessOn::String 
{
    forwards to qAccess(name, baseName(accessOn, location=top.location), location=top.location); 
} 


abstract production mkOrigin
top::Expr ::= ns::Decorated NamedSignature
{
    forwards to argFunc(
        mkOriginName(unFull(ns.outputElement.typerep.typeName)), 
        oneApp(baseName(ns.outputElement.elementName, location=top.location), location=top.location),
        location=top.location);
}

abstract production oneApp
top::AppExprs ::= e::Expr
{
    forwards to oneAppExprs(presentAppExpr(e, location=top.location), location=top.location);
}

abstract production argTransAttrs
top::AppExprs ::= nsElems::[NamedSignatureElement] attr::String allowedTypes::[String]
{   
    forwards to if length(nsElems) == 1 
        then oneApp(builtinAccess(attr, head(nsElems), allowedTypes, location=top.location), location=top.location)
        else snocAppExprs(argTransAttrs(tail(nsElems), attr, allowedTypes, location=top.location), ',',
            presentAppExpr(builtinAccess(attr, head(nsElems), allowedTypes, location=top.location),
        location=top.location), location=top.location);
}

abstract production builtinAccess
top::Expr ::= attr::String ne::NamedSignatureElement allowedTypes::[String]
{
    forwards to if contains(unFull(ne.typerep.typeName), allowedTypes) then
        exprAccess(attr, ne.elementName, location=top.location)
        else baseName(ne.elementName, location=top.location);
}

abstract production prdRecurse
top::Expr ::= ns::Decorated NamedSignature tName::String allowedTypes::[String]
{
    forwards to application(baseName(ns.fullName, location=top.location), '(',
        argTransAttrs(ns.inputElements, tName, allowedTypes, location=top.location),
        ',',
        annoAppExprList([
            annExpr("labels", emptyList('[',']', location=top.location), location=top.location),
            -- This is not worked out completely
            -- We want to access the inhRedex of the leftmost rhs element, if it has
            -- an inhRedex, and otherwise top.inhRedex
            annExpr("redex", exprAccess(inhRedexNm(tName), inhRedexNameSig(ns), location=top.location), location=top.location),
            annExpr("origin", mkOrigin(ns, location=top.location), location=top.location)
        ], location=top.location),
        ')', location=top.location);
}

abstract production mkQName
top::QName ::= name::String 
{
    forwards to qName(top.location, name);
}

abstract production baseName
top::Expr ::= name::String 
{
    forwards to baseExpr(qName(top.location, name), location=top.location);
}

abstract production presentName
top::AppExpr ::= name::String
{
    forwards to presentAppExpr(baseName(name, location=top.location), location=top.location);
}

abstract production appExprList
top::AppExprs ::= aExprs::[AppExpr] 
{
    forwards to if length(aExprs) == 1 
        then oneAppExprs(head(aExprs), location=top.location)
        else snocAppExprs(
            appExprList(tail(aExprs), location=top.location), ',', head(aExprs), location=top.location);
}

abstract production annoAppExprList
top::AnnoAppExprs ::= aaExprs::[AnnoExpr] 
{  
    forwards to if length(aaExprs) == 1 
        then oneAnnoAppExprs(head(aaExprs), location=top.location)
        else snocAnnoAppExprs(
            annoAppExprList(tail(aaExprs), location=top.location), ',', head(aaExprs), location=top.location);
}

abstract production annExpr
top::AnnoExpr ::= annoName::String e::Expr
{
    forwards to annoExpr(qName(top.location, annoName), '=', presentAppExpr(e,
        location=top.location),location=top.location);
}

abstract production nsApply
top::Expr ::= ns::Decorated NamedSignature
{
    forwards to if null(ns.inputElements) 
        then emptyFunc(ns.fullName, location=top.location)
        else argFunc(ns.fullName, nsElemsToAppExprs(ns.inputElements, location=top.location),
            location=top.location);
}

abstract production nsElemsToAppExprs
top::AppExprs ::= nsElems::[NamedSignatureElement]
{
    forwards to if null(nsElems) then emptyAppExprs(location=top.location)
        else snocAppExprs(
            nsElemsToAppExprs(allHead(nsElems), location=top.location),
            ',',
            nsElemToAppExpr(last(nsElems), location=top.location),
            location=top.location); 
}

abstract production nsElemToAppExpr
top::AppExpr ::= nsElem::NamedSignatureElement
{
    forwards to presentName(nsElem.elementName, location=top.location);
}

abstract production botlOneString
top::BracketedOptTypeExprs ::= s::String
{
    forwards to botlSome('<', 
        typeListSingle(sTyExpr(s, location=top.location),location=top.location),
        '>', location=top.location);
}

abstract production mkProdSig
top::ProductionSignature ::= lhsName::String lhsType::String rhsName::String rhsType::String
{
    forwards to productionSignature(
        productionLHS(name(lhsName, top.location), '::', sTyExpr(lhsType, location=top.location), location=top.location),
        '::=',
        productionRHSCons(
            productionRHSElem(name(rhsName, top.location), '::', sTyExpr(rhsType, location=top.location), location=top.location),
            productionRHSNil(location=top.location), location=top.location), location=top.location);
}

abstract production mkProdSigDec
top::ProductionSignature ::= lhsName::String lhsType::String rhsName::String rhsType::String
{
    forwards to productionSignature(
        productionLHS(name(lhsName, top.location), '::', sTyExpr(lhsType, location=top.location), location=top.location),
        '::=',
        productionRHSCons(
            productionRHSElem(name(rhsName, top.location), '::', decTyExpr(rhsType, location=top.location), location=top.location),
            productionRHSNil(location=top.location), location=top.location), location=top.location);
}

abstract production mkAspectProdSig
top::AspectProductionSignature ::= lhsName::String lhsType::String rhsName::String rhsType::String
{
    forwards to aspectProductionSignature(
        aspectProductionLHSTyped(name(lhsName, top.location), '::', sTyExpr(lhsType, location=top.location), location=top.location),
        '::=',
        aspectRHSElemCons(
            aspectRHSElemTyped(name(rhsName, top.location), '::', sTyExpr(rhsType, location=top.location), location=top.location),
            aspectRHSElemNil(location=top.location), location=top.location), location=top.location);
}

abstract production mkAspectProdSigDec
top::AspectProductionSignature ::= lhsName::String lhsType::String rhsName::String rhsType::String
{
    forwards to aspectProductionSignature(
        aspectProductionLHSTyped(name(lhsName, top.location), '::', sTyExpr(lhsType, location=top.location), location=top.location),
        '::=',
        aspectRHSElemCons(
            aspectRHSElemTyped(name(rhsName, top.location), '::', decTyExpr(rhsType, location=top.location), location=top.location),
            aspectRHSElemNil(location=top.location), location=top.location), location=top.location);
}

abstract production mkFalse
top::Expr ::= 
{
    forwards to falseConst('false', location=top.location);
}

abstract production mkTrue
top::Expr ::= 
{
    forwards to trueConst('true', location=top.location);
}

abstract production mkBoolTypeExpr
top::TypeExpr ::=
{
    forwards to booleanTypeExpr('Boolean', location=top.location);
}

abstract production mkMaybeTypeExpr
top::TypeExpr ::= inner::String
{
    forwards to nominalTypeExpr(qnTyId("Maybe", location=top.location), 
        botlOneString(inner, location = top.location), location=top.location);
}

abstract production mkCond
top::Expr ::= if_e::Expr then_e::Expr else_e::Expr
{
    forwards to ifThenElse(
        'if', if_e,
        'then', then_e,
        'else', else_e, 
        location=top.location);
}

abstract production mkNew
top::Expr ::= nme::String
{
    forwards to newFunction('new', '(', baseName(nme, location=top.location), ')', location=top.location);
}

abstract production joinAGDcls
top::AGDcl ::= dcls::[AGDcl]
{
    forwards to if null(dcls) then emptyAGDcl(location=top.location)
        else appendAGDcl(head(dcls), joinAGDcls(tail(dcls), location=top.location), location=top.location);
}

-- abstract production strAppExprs
-- top::AppExprs ::= inputNames::[String] 
-- {
--     forwards to strAppExprsHelp(reverse(inputNames), location=top.location);
-- }

abstract production strAppExprs
top::AppExprs ::= inputNames::[String]
{
    forwards to if length(inputNames) == 1
    then oneAppExprs(presentName(head(inputNames), location=top.location), location=top.location)
    else snocAppExprs(strAppExprs(tail(inputNames), location=top.location),
            ',', presentName(head(inputNames), location=top.location),   
        location=top.location);
}