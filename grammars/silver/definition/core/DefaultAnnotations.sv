grammar silver:definition:core;

autocopy attribute defaultInheritedAnnos::[Pair<String AppExpr>];
synthesized attribute defaultSuppliedAnnos::[Pair<String AppExpr>] with ++;

attribute defaultInheritedAnnos occurs on ProductionStmt, ProductionStmts, Expr, AppExpr, AppExprs, AnnoExpr, AnnoAppExprs, ForwardInh, ForwardInhs, ExprInh, ExprInhs;
attribute defaultSuppliedAnnos occurs on ProductionStmt, ProductionStmts;

-- Todo: I moved this from the DefaultAttr extension as a quick fix
terminal Default_kwd 'default' lexer classes {KEYWORD, RESERVED};

concrete production defaultProdAnno
top::ProductionStmt ::= 'default' 'annotation' qn::QName '=' aexpr::AppExpr ';'
{
    top.defaultSuppliedAnnos := [];
    top.defaultSuppliedAnnos <- [pair(qn.name, aexpr)];

    top.errors := aexpr.errors;

    top.pp = "\tdefault annotation" ++ qn.name ++ aexpr.pp ++ ";";

    aexpr.downSubst = emptySubst();

    local annoAttrs::[DclInfo] = getAttrDcl(qn.name, top.env);

    aexpr.appExprTyperep = if null(annoAttrs) then errorType()
        else if head(annoAttrs).isAnnotation then head(annoAttrs).typerep
        else errorType();
} 

aspect production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '::' t::TypeExpr '=' e::Expr ';'
{
    e.defaultInheritedAnnos = [];
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

aspect default production
top::ProductionStmt ::=
{
  top.defaultSuppliedAnnos := [];
}

aspect production productionBody
top::ProductionBody ::= '{' stmts::ProductionStmts '}'
{
    stmts.defaultInheritedAnnos = stmts.defaultSuppliedAnnos;
}

function fillMissingAnnos
AnnoAppExprs ::= toFill::AnnoAppExprs namedTypes::[NamedArgType] annos::[Pair<String AppExpr>]
{ 
    local invalid::Boolean = null(annos) || null(namedTypes);

    local needed::Boolean = if invalid then false
        else neededAnno(namedTypes, head(annos).fst);

    local missing::Boolean = if invalid || !needed then false
        else !annoAppExprsContainsAnno(toFill, head(annos).fst);    

    local recAnns::AnnoAppExprs = if invalid then toFill 
        else fillMissingAnnos(toFill, namedTypes, tail(annos));

    local thisAnno::AnnoExpr = annoExpr(qName(toFill.location, head(annos).fst), '=', 
        head(annos).snd, location=toFill.location);

    return if missing 
        then case recAnns of 
            | emptyAnnoAppExprs() -> oneAnnoAppExprs(thisAnno, location=toFill.location)
            | _ -> snocAnnoAppExprs(recAnns, ',', thisAnno, location=toFill.location)
        end
        else recAnns;
}

function neededAnno
Boolean ::= namedTypes::[NamedArgType] anno::String
{
    return if null(namedTypes) then false
        else if head(namedTypes).argName == anno then true
        else neededAnno(tail(namedTypes), anno);
}

function annoAppExprsContainsAnno
Boolean ::= toFill::AnnoAppExprs anno::String 
{
    -- We explicitly aren't using attributes here
    -- because we don't want to decorate the AnnoAppExprs
    return case toFill of 
        | emptyAnnoAppExprs() -> false
        | snocAnnoAppExprs(xs,_,x) -> annoExprContainsAnno(x, anno) || annoAppExprsContainsAnno(xs, anno)
        | oneAnnoAppExprs(x) -> annoExprContainsAnno(x, anno)
    end;
}

function annoExprContainsAnno
Boolean ::= aexpr::AnnoExpr anno::String
{
    return case aexpr of 
        | annoExpr(qn, _, _) -> qn.name == anno
        | _ -> false -- I don't think this is possible right now
    end;
}