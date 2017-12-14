grammar silver:extension:bidirtransform;

-- qAttr creates a QNameAttrOccur from a string
abstract production qAttr
top::QNameAttrOccur ::= name::String 
{
    forwards to qNameAttrOccur(qName(top.location, name), location=top.location);
}

-- emptyFunc returns an empty application of the function or production name
abstract production emptyFunc
top::Expr ::= name::String 
{
    forwards to applicationEmpty(baseName(name, location=top.location),'(',')', location=top.location);
}

-- argFunc returns an application of the function or production name
-- with the given arguments.
abstract production argFunc
top::Expr ::= name::String args::AppExprs 
{
    forwards to applicationExpr(baseName(name, location=top.location), '(',args,')', location=top.location);
}

-- oneArgFunc returns an application of the function or production name
-- with the single given argument.
abstract production oneArgFunc
top::Expr ::= name::String arg::AppExpr
{
    forwards to argFunc(name, appExprList([arg], location=top.location), location=top.location);
}

-- fullFunc returns an application of the function or production name
-- with the input arguments and annotations.
abstract production fullFunc
top::Expr ::= name::String args::AppExprs annos::AnnoAppExprs 
{
    forwards to application(baseName(name, location=top.location), '(',args,',',annos,')', location=top.location);
}

-- synAttr defines "synthesized attribute nme::tyexpr"
abstract production synAttr
top::AGDcl ::= nme::String tyexpr::TypeExpr
{
    forwards to attributeDclSyn('synthesized', 'attribute', name(nme, top.location),
        botlNone(location=top.location), '::', tyexpr, ';', location=top.location);
}

-- mkLhsDef creates a DefLHS from a string
abstract production mkLhsDef
top::DefLHS ::= s::String 
{
    forwards to concreteDefLHS(qName(top.location, s), location=top.location);
}

-- autocAttr defines "autocopy attribute nme::tyexpr" 
abstract production autocAttr
top::AGDcl ::= nme::String tyexpr::TypeExpr
{
    forwards to attributeDclAuto('autocopy', 'attribute', name(nme, top.location),
        botlNone(location=top.location), '::', tyexpr, ';', location=top.location);
}

-- annoOn declares that the annotation name occurs on the set of onNames
abstract production annoOn
top::AGDcl ::= name::String onNames::[String]
{
    local qntlName::QNameWithTL = qNameWithTL(qName(top.location, name), botlNone(location=top.location));

    local qntOnNames::[QNameWithTL] = map(\ s::String -> 
            qNameWithTL(qName(top.location, s), botlNone(location=top.location)), 
        onNames);

    forwards to makeOccursDclsHelp(top.location, qntlName, qntOnNames);
}

-- attrOn declares that the attribute name occurs on the set of onNames
abstract production attrOn
top::AGDcl ::= name::String onNames::[String]
{
    forwards to if null(onNames) then emptyAGDcl(location=top.location)
        else appendAGDcl(
            attributionDcl('attribute', qName(top.location, name), botlNone(location=top.location), 
                'occurs', 'on', qName(top.location, head(onNames)), botlNone(location=top.location), ';', location=top.location),
            attrOn(name, tail(onNames), location=top.location), location=top.location);
}

-- consAnnoAppExprs combines two AnnoAppExprs lists
abstract production consAnnoAppExprs
top::AnnoAppExprs ::= a::AnnoAppExprs b::AnnoAppExprs
{
    forwards to case b of 
        | snocAnnoAppExprs(c,_,expr) -> consAnnoAppExprs(snocAnnoAppExprs(a, ',', expr, location=top.location), c, location=top.location)
        | oneAnnoAppExprs(expr) -> snocAnnoAppExprs(a, ',', expr, location=top.location)
        | _ -> a
    end;
}

-- nsAspectProdSig creates an AspectProductionSignature from a NamedSignature
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

-- nsAspectProdRHS creates an aspect production RHS from a list of named 
-- signature elements
abstract production nsAspectProdRHS
top::AspectRHS ::= inElements::[NamedSignatureElement]
{
    local hd::NamedSignatureElement = head(inElements);

    forwards to if null(inElements) then aspectRHSElemNil(location=top.location)
        else aspectRHSElemCons(
            aspectRHSElemTyped(name(hd.elementName, top.location), '::', typerepTypeExpr(hd.typerep, location=top.location), location=top.location), 
            nsAspectProdRHS(tail(inElements), location=top.location), location=top.location);
}

-- aspectProdStmts takes a named signature and a function which will define 
-- a production stmt given that named signature and creates an aspect production
-- (that has no access to production attributes) declaring that stmt. 
abstract production aspectProdStmt
top::AGDcl ::= dcl::Decorated NamedSignature fn::(ProductionStmt ::= Decorated NamedSignature)
{
    forwards to aspectProdStmts(dcl,\ ns::Decorated NamedSignature ->
        productionStmtsSnoc(productionStmtsNil(location=top.location), fn(ns), location=top.location),
      location=top.location);
}

-- aspectProdStmts takes a named signature and a function which will define 
-- production stmts given that named signature and creates an aspect production
-- (that has no access to production attributes) declaring those stmts. 
abstract production aspectProdStmts
top::AGDcl ::= dcl::Decorated NamedSignature fn::(ProductionStmts ::= Decorated NamedSignature)
{
    forwards to
        fakeAspectProductionDcl('aspect', 'production', 
            qName(top.location, dcl.fullName), nsAspectProdSig(dcl, location=top.location), 
            productionBody('{', fn(dcl), '}', location=top.location), location=top.location);

}

-- prdStmtList converts a list of ProductionStmt into a ProductionStmts
abstract production prdStmtList
top::ProductionStmts ::= stmts::[ProductionStmt]
{
    forwards to if null(stmts) then productionStmtsNil(location=top.location)
        else productionStmtsSnoc(prdStmtList(tail(stmts), location=top.location), head(stmts), location=top.location);
}

-- prdBody converts a list of ProductionStmt into a ProductionBody
abstract production prdBody
top::ProductionBody ::= stmts::[ProductionStmt]
{
    forwards to productionBody('{', prdStmtList(stmts, location=top.location), '}', location=top.location);
}

-- attribDef defines a new atribute in a production of "lhs.att = eqs"
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

-- qnTyId creates a QNameType from the input string
abstract production qnTyId
top::QNameType ::= s::String 
{
    forwards to qNameTypeId(terminal(IdUpper_t, s), location=top.location);
}

-- sTyExpr creates a nominal type expression from the given String
abstract production sTyExpr
top::TypeExpr ::= s::String 
{
    forwards to nominalTypeExpr(qNameTypeId(terminal(IdUpper_t, s), location=top.location),
        botlNone(location=top.location), location=top.location);
}

-- decTyExpr creates a type expression representing "Decorated s"
abstract production decTyExpr
top::TypeExpr ::= s::String 
{
    forwards to refTypeExpr('Decorated', sTyExpr(s, location=top.location), location=top.location);
}

-- qTyExpr creates a nominal type expression from the given QName
abstract production qTyExpr
top::TypeExpr ::= q::QName 
{
    forwards to sTyExpr(q.name, location=top.location);
}

-- lhsAccess returns an AppExpr of the output element of ns accessing name.
abstract production lhsAccess
top::AppExpr ::= name::String ns::Decorated NamedSignature 
{
    forwards to namedAccess(name, ns.outputElement.elementName, location=top.location);
}  

-- namedAccess returns an AppExpr of accessOn accessing name.
abstract production namedAccess
top::AppExpr ::= name::String accessOn::String 
{
    forwards to presentAppExpr(exprAccess(name, accessOn, location=top.location), location=top.location);
} 

-- lhsExprAccess creates an expression of the ns's output element accessing name.
abstract production lhsExprAccess
top::Expr ::= name::String ns::Decorated NamedSignature
{
    forwards to exprAccess(name, ns.outputElement.elementName, location=top.location);
}

-- qAccess creates an expression of the accessOn Expr accessing name.
abstract production qAccess
top::Expr ::= q::String accessOn::Expr 
{
    forwards to access(accessOn, '.', qNameAttrOccur(qName(top.location, q), location=top.location), location=top.location);
}

-- exprAccess creates an expression of the accessOn name accessing name.
abstract production exprAccess
top::Expr ::= name::String accessOn::String 
{
    forwards to qAccess(name, baseName(accessOn, location=top.location), location=top.location); 
} 

-- mkOrigin creates an Expr referring to the origin production called with 
-- ns's output element.
abstract production mkOrigin
top::Expr ::= ns::Decorated NamedSignature
{
    forwards to argFunc(
        mkOriginName(unFull(ns.outputElement.typerep.typeName)), 
        oneApp(baseName(ns.outputElement.elementName, location=top.location), location=top.location),
        location=top.location);
}

-- oneApp converts an Expr into an AppExprs
abstract production oneApp
top::AppExprs ::= e::Expr
{
    forwards to oneAppExprs(presentAppExpr(e, location=top.location), location=top.location);
}

-- argTransAttrs creates an AppExprs of the defined named signature elements where
-- each element applied will access the attr attribute, if they are
-- one of the types in the allowedTypes set.
abstract production argTransAttrs
top::AppExprs ::= nsElems::[NamedSignatureElement] attr::String allowedTypes::[String]
{   
    forwards to if length(nsElems) == 1 
        then oneApp(builtinAccess(attr, head(nsElems), allowedTypes, location=top.location), location=top.location)
        else snocAppExprs(argTransAttrs(tail(nsElems), attr, allowedTypes, location=top.location), ',',
            presentAppExpr(builtinAccess(attr, head(nsElems), allowedTypes, location=top.location),
        location=top.location), location=top.location);
}

-- builtinAccess will either create an access on attr if the named signature element's type
-- is in the set of allowed types or just an expression of the named signature element's name
abstract production builtinAccess
top::Expr ::= attr::String ne::NamedSignatureElement allowedTypes::[String]
{
    forwards to if contains(unFull(ne.typerep.typeName), allowedTypes) then
        exprAccess(attr, ne.elementName, location=top.location)
        else baseName(ne.elementName, location=top.location);
}

-- prdRecurse creates an application of the defined named signature where
-- each element applied will recurse into their .tName attribute, if they are
-- one of the types in the allowedTypes set. It also applies origin annotations.
abstract production prdRecurse
top::Expr ::= ns::Decorated NamedSignature tName::String allowedTypes::[String]
{
    forwards to application(baseName(ns.fullName, location=top.location), '(',
        argTransAttrs(reverse(ns.inputElements), tName, allowedTypes, location=top.location),
        ',',
        annoAppExprList([
            annExpr("labels", emptyList('[',']', location=top.location), location=top.location),
            annExpr("redex", exprAccess(inhRedexNm(tName), inhRedexNameSig(ns, allowedTypes), location=top.location), location=top.location),
            annExpr("origin", mkOrigin(ns, location=top.location), location=top.location)
        ], location=top.location),
        ')', location=top.location);
}

-- mkQName creates a QName from a string.
abstract production mkQName
top::QName ::= name::String 
{
    forwards to qName(top.location, name);
}

-- baseName converts a string into an Expr
abstract production baseName
top::Expr ::= name::String 
{
    forwards to baseExpr(qName(top.location, name), location=top.location);
}

-- presentName returns an AppExpr representing the present named element
abstract production presentName
top::AppExpr ::= name::String
{
    forwards to presentAppExpr(baseName(name, location=top.location), location=top.location);
}

-- appExprList converts a list of AppExpr into an AppExprs
abstract production appExprList
top::AppExprs ::= aExprs::[AppExpr] 
{
    forwards to if length(aExprs) == 1 
        then oneAppExprs(head(aExprs), location=top.location)
        else snocAppExprs(
            appExprList(tail(aExprs), location=top.location), ',', head(aExprs), location=top.location);
}

-- annoAppExprList converts a list of AnnoExpr to an AnnoAppExprs
abstract production annoAppExprList
top::AnnoAppExprs ::= aaExprs::[AnnoExpr] 
{  
    forwards to if length(aaExprs) == 1 
        then oneAnnoAppExprs(head(aaExprs), location=top.location)
        else snocAnnoAppExprs(
            annoAppExprList(tail(aaExprs), location=top.location), ',', head(aaExprs), location=top.location);
}

-- annExpr creates an AnnoExpr assigning the annotation annoName to e
abstract production annExpr
top::AnnoExpr ::= annoName::String e::Expr
{
    forwards to annoExpr(qName(top.location, annoName), '=', presentAppExpr(e,
        location=top.location),location=top.location);
}

-- nsApply creates an expression representing the given named signature being called,
-- with the names of its arguments the same as those defined in the named signature.
abstract production nsApply
top::Expr ::= ns::Decorated NamedSignature
{
    forwards to if null(ns.inputElements) 
        then emptyFunc(ns.fullName, location=top.location)
        else argFunc(ns.fullName, nsElemsToAppExprs(ns.inputElements, location=top.location),
            location=top.location);
}

-- nsElemToAppExprs returns an AppExprs representing the underlying 
-- element names of the NamedSignatureElements
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

-- nsElemToAppExpr returns an AppExpr representing the underlying 
-- element name of the NamedSignatureElement
abstract production nsElemToAppExpr
top::AppExpr ::= nsElem::NamedSignatureElement
{
    forwards to presentName(nsElem.elementName, location=top.location);
}

-- botlOneString converts an input string into a single type in a
-- BracketedOptTypeExprs.
abstract production botlOneString
top::BracketedOptTypeExprs ::= s::String
{
    forwards to botlOneTyExpr(sTyExpr(s, location=top.location), location=top.location);
}

abstract production botlOneTyExpr
top::BracketedOptTypeExprs ::= te::TypeExpr 
{
    forwards to botlSome('<',
        typeListSingle(te, location=top.location),
    '>', loation=top.location);
}

-- mkProdSig returns a production signature of "lhsName::lhsType ::= rhsName::rhsType"
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

-- mkProdDecSig returns a production signature of "lhsName::lhsType ::= rhsName::Decorated rhsType"
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

-- mkAspectProdSig returns an aspect production signature of "lhsName::lhsType ::= rhsName::rhsType"
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

-- mkAspectProdSigDec returns an aspect production signature of "lhsName::lhsType ::= rhsName::Decorated rhsType"
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

-- mkFalse returns the Boolean false expression
abstract production mkFalse
top::Expr ::= 
{
    forwards to falseConst('false', location=top.location);
}

-- mkTrue returns the Boolean true expression
abstract production mkTrue
top::Expr ::= 
{
    forwards to trueConst('true', location=top.location);
}

-- mkBoolTypeExpr returns the Boolean TypeExpr
abstract production mkBoolTypeExpr
top::TypeExpr ::=
{
    forwards to booleanTypeExpr('Boolean', location=top.location);
}

-- mkMaybeTypeExpr returns a TypeExpr which is "Maybe<inner>" for the string inner.
abstract production mkMaybeString
top::TypeExpr ::= inner::String
{
    forwards to nominalTypeExpr(qnTyId("Maybe", location=top.location), 
        botlOneString(inner, location = top.location), location=top.location);
}

-- mkMaybeTypeExpr returns a TypeExpr which is "Maybe<inner>" for the type expr inner.
abstract production mkMaybeTypeExpr
top::TypeExpr ::= inner::TypeExpr
{
    forwards to nominalTypeExpr(qnTyId("Maybe", location=top.location), 
        botlOneTyExpr(inner, location = top.location), location=top.location);
}

-- mkCond creates an if,then,else statement on the input expressions.
abstract production mkCond
top::Expr ::= if_e::Expr then_e::Expr else_e::Expr
{
    forwards to ifThenElse(
        'if', if_e,
        'then', then_e,
        'else', else_e, 
        location=top.location);
}

-- mkNew returns "new(nme)" as an Expr for the input string
abstract production mkNew
top::Expr ::= nme::String
{
    forwards to newFunction('new', '(', baseName(nme, location=top.location), ')', location=top.location);
}

-- joinAGDcls recursively converts a list of AGDcl into a single AGDcl
-- through appendAGDcl. 
abstract production joinAGDcls
top::AGDcl ::= dcls::[AGDcl]
{
    forwards to if null(dcls) then emptyAGDcl(location=top.location)
        else appendAGDcl(head(dcls), joinAGDcls(tail(dcls), location=top.location), location=top.location);
}

-- strAppExprs converts a set of input names into an AppExprs. 
-- these names will be reversed, because of how AppExprs are formed.
-- todo: consider this being a helper that reverses the inputs and 
-- then converts that into AppExprs.
abstract production strAppExprs
top::AppExprs ::= inputNames::[String]
{
    forwards to if length(inputNames) == 1
    then oneAppExprs(presentName(head(inputNames), location=top.location), location=top.location)
    else snocAppExprs(strAppExprs(tail(inputNames), location=top.location),
            ',', presentName(head(inputNames), location=top.location),   
        location=top.location);
}