grammar silver:extension:bidirtransform;

terminal Origins_kwd 'origins' lexer classes {KEYWORD,RESERVED};

concrete production originEq
top::ProductionStmt ::= val::QName '=' 'origins' e::Expr ';'
{
    local newAnnos::AnnoAppExprs = annoAppExprList([
        annExpr(top.location, "labels", emptyList('[',']', location=top.location)),
        annExpr(top.location, "redex", emptyFunc(top.location, "nothing")),
        annExpr(top.location, "origin", emptyFunc(top.location, mkOriginName(top.prodOutput.typeName)))
    ], top.location);
    
    forwards to valueEq(val, '=', 
        case e of 
            | applicationExpr(e2,_,es,_) -> application(e2,'(',es,',',newAnnos,')', location=e.location)
            | applicationAnno(e2,_,anns,_) -> applicationAnno(e2,'(',consAnnoAppExprs(top.location, anns,newAnnos),')', location=e.location)
            | application(e2,_,es,_,anns,_) -> application(e2,'(',es,',',consAnnoAppExprs(top.location, anns,newAnnos), ')', location=e.location)
            | applicationEmpty(e2,_,_) -> applicationAnno(e2,'(',newAnnos,')', location=e.location)
            | _ -> e -- we can't add annotations to non-applications
        end, ';', location=top.location);
}

concrete production originAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::QNameAttrOccur '=' 'origins' e::Expr ';'
{
    local newAnnos::AnnoAppExprs = annoAppExprList([
        annExpr(top.location, "labels", emptyList('[',']', location=top.location)),
        annExpr(top.location, "redex", emptyFunc(top.location, "nothing")),
        annExpr(top.location, "origin", emptyFunc(top.location, mkOriginName(top.prodOutput.typeName)))
    ], top.location);

    forwards to attributeDef(dl,'.',attr,'=',
        case e of 
            | applicationExpr(e2,_,es,_) -> application(e2,'(',es,',',newAnnos,')', location=e.location)
            | applicationAnno(e2,_,anns,_) -> applicationAnno(e2,'(',consAnnoAppExprs(top.location, anns,newAnnos),')', location=e.location)
            | application(e2,_,es,_,anns,_) -> application(e2,'(',es,',',consAnnoAppExprs(top.location, anns,newAnnos), ')', location=e.location)
            | applicationEmpty(e2,_,_) -> applicationAnno(e2,'(',newAnnos,')', location=e.location)
            | _ -> e -- we can't add annotations to non-applications
        end,';',location=top.location);
}

autocopy attribute prodOutput::Type occurs on ProductionStmt, ProductionStmts, ProductionBody, AGDcl, ProductionDclStmt;

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody 
{
    body.prodOutput = ns.namedSignature.outputElement.typerep;
}

aspect production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
{
    body.prodOutput = ns.namedSignature.outputElement.typerep;
}

aspect production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody 
{
    body.prodOutput = ns.namedSignature.outputElement.typerep;    
}

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 
{
    body.prodOutput = ns.namedSignature.outputElement.typerep;    
}

aspect production functionDclFFI
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 'foreign' '{' ffidefs::FFIDefs '}'
{
    body.prodOutput = ns.namedSignature.outputElement.typerep;
}

aspect production aspectDefaultProduction
top::AGDcl ::= 'aspect' 'default' 'production' 
               lhs::Name '::' te::TypeExpr '::=' body::ProductionBody 
{
    body.prodOutput = te.typerep;    
}

aspect production productionDclStmt
top::ProductionDclStmt ::= optn::OptionalName v::ProdVBar
                           rhs::ProductionRHS
                           mods::ProductionModifiers
                           body::ProductionBody
                           opta::OptionalAction
{
    body.prodOutput = top.lhsdcl.outputElement.typerep;
}