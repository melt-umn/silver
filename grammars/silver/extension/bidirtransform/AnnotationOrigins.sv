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