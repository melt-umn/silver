grammar silver:definition:core;

autocopy attribute defaultInheritedAnnos::[Pair<String AppExpr>];
synthesized attribute defaultSuppliedAnnos::[Pair<String AppExpr>] with ++;

attribute defaultInheritedAnnos occurs on ProductionStmt, ProductionStmts, Expr;
attribute defaultSuppliedAnnos occurs on ProductionStmt, ProductionStmts;

-- Todo: I moved this from the DefaultAttr extension as a quick fix
terminal Default_kwd 'default' lexer classes {KEYWORD, RESERVED};

concrete production defaultProdAnno
top::ProductionStmt ::= 'default' 'annotation' qn::QName '=' aexpr::AppExpr ';'
{
    top.defaultSuppliedAnnos <- [pair(qn.name, aexpr)];
}

aspect production productionStmtsNil
top::ProductionStmts ::= 
{
  top.defaultSuppliedAnnos := [];
}

aspect production productionStmtsSnoc
top::ProductionStmts ::= h::ProductionStmts t::ProductionStmt
{
  top.defaultSuppliedAnnos := h.defaultSuppliedAnnos ++ t.defaultSuppliedAnnos;    
}

aspect production productionStmtAppend
top::ProductionStmt ::= l::ProductionStmt r::ProductionStmt
{
    top.defaultSuppliedAnnos := l.defaultSuppliedAnnos ++ r.defaultSuppliedAnnos;
}

aspect production productionBody
top::ProductionBody ::= '{' stmts::ProductionStmts '}'
{
    stmts.defaultInheritedAnnos = stmts.defaultSuppliedAnnos;
}

function fillMissingAnnos
AnnoAppExprs ::= toFill::AnnoAppExprs annos::[Pair<String AppExpr>]
{
    return if missingContainsAnno(toFill.missingAnnotations, head(annos)) 
        then snocAnnoAppExprs(fillMissingAnnos(toFill, tail(annos)), ',', 
            annoExpr(
                qName(toFill.location, head(annos).fst),
                '=', 
                head(annos).snd, location=toFill.location),
            location=toFill.location)
        else fillMissingAnnos(toFill, tail(annos));
}

function missingContainsAnno
Boolean ::= missing::[NamedArgType] anno::Pair<String AppExpr> 
{
    return if head(missing).argName == anno.fst then true
        else missingContainsAnno(tail(missing), anno);
}