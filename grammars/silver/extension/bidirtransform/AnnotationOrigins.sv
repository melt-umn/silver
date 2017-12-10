grammar silver:extension:bidirtransform;

terminal Origins_kwd 'origins' lexer classes {KEYWORD,RESERVED};
terminal Apply_kwd 'apply' lexer classes {KEYWORD,RESERVED};
terminal Optional_kws 'optional' lexer classes {KEYWORD};

concrete production originEq
top::Expr ::= 'origins' '{' e::Expr '}'
{
    local newAnnos::AnnoAppExprs = annoAppExprList([
        annExpr("labels", emptyList('[',']', location=top.location), location=top.location),
        annExpr("redex", emptyFunc("nothing", location=top.location), location=top.location),
        annExpr("origin", oneArgFunc(
              mkOriginName(unFull(top.prodOutput.typerep.typeName)), 
              presentName(top.prodOutput.elementName, location=top.location), 
              location=top.location), location=top.location)
    ], location=top.location);
    
    forwards to 
        case e of 
            | applicationExpr(e2,_,es,_) -> application(e2,'(',es,',',newAnnos,')', location=e.location)
            | applicationAnno(e2,_,anns,_) -> applicationAnno(e2,'(',consAnnoAppExprs(anns,newAnnos, location=top.location),')', location=e.location)
            | application(e2,_,es,_,anns,_) -> application(e2,'(',es,',',consAnnoAppExprs(anns,newAnnos, location=top.location), ')', location=e.location)
            | applicationEmpty(e2,_,_) -> applicationAnno(e2,'(',newAnnos,')', location=e.location)
            | _ -> e -- we can't add annotations to non-applications
        end;
}

concrete production originPrdStmt
top::ProductionStmt ::= 'apply' 'origins' ';'
{
    forwards to productionStmtAppend(productionStmtAppend(
        mkDefaultProdAnno(qName(top.location, "labels"), 
          presentAppExpr(emptyList('[',']', location=top.location), location=top.location), location=top.location), 
        mkDefaultProdAnno(qName(top.location, "redex"),  
          presentAppExpr(emptyFunc("nothing", location=top.location), location=top.location), location=top.location), location=top.location),
        mkDefaultProdAnno(qName(top.location, "origin"), 
          presentAppExpr(oneArgFunc(
              mkOriginName(unFull(top.prodOutput.typerep.typeName)), 
              presentName(top.prodOutput.elementName, location=top.location), 
              location=top.location), 
        location=top.location), location=top.location), location=top.location);
}

autocopy attribute prodOutput::NamedSignatureElement occurs on ProductionStmt, ProductionStmts, Expr, AppExpr, AppExprs, 
    AnnoExpr, AnnoAppExprs, ForwardInh, ForwardInhs, ExprInh, ExprInhs, AssignExpr, PrimPattern, PrimPatterns, 
    ProductionBody, AGDcl, ProductionDclStmt;

aspect production mkProductionDcl
top::AGDcl ::= id::Name ns::ProductionSignature body::ProductionBody isAbstract::Boolean
{
    body.prodOutput = ns.namedSignature.outputElement;
}

aspect production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
{
    body.prodOutput = ns.namedSignature.outputElement;
}

aspect production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody 
{
    body.prodOutput = ns.namedSignature.outputElement;    
}

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 
{
    body.prodOutput = ns.namedSignature.outputElement;    
}

aspect production functionDclFFI
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 'foreign' '{' ffidefs::FFIDefs '}'
{
    body.prodOutput = ns.namedSignature.outputElement;
}

aspect production aspectDefaultProduction
top::AGDcl ::= 'aspect' d::Default_kwd 'production' 
               lhs::Name '::' te::TypeExpr '::=' body::ProductionBody 
{
    body.prodOutput = namedSignatureElement(lhs.name, te.typerep);    
}

aspect production productionDclStmt
top::ProductionDclStmt ::= optn::OptionalName v::ProdVBar
                           rhs::ProductionRHS
                           mods::ProductionModifiers
                           body::ProductionBody
                           opta::OptionalAction
{
    body.prodOutput = top.lhsdcl.outputElement;
}