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
Decorated AnnoAppExprs ::= toFill::Decorated AnnoAppExprs annos::[Pair<String AppExpr>]
{ 
    local missing::Boolean = if null(toFill.missingAnnotations) || null(annos) then false
        else missingContainsAnno(toFill.missingAnnotations, head(annos));    

    local recAnns::Decorated AnnoAppExprs = if null(annos) then toFill 
        else fillMissingAnnos(toFill, tail(annos));

    local thisAnno::AnnoExpr = annoExpr(qName(recAnns.location, head(annos).fst), '=', 
        head(annos).snd, location=recAnns.location);

    local annsWithDefaults::Decorated AnnoAppExprs = if missing
        then case recAnns of 
            | emptyAnnoAppExprs() -> decorate oneAnnoAppExprs(thisAnno, location=recAnns.location) with {
                env = toFill.env;
                config = toFill.config;
                grammarName = toFill.grammarName;
                frame = toFill.frame;
                compiledGrammars = toFill.compiledGrammars;
                appExprApplied = toFill.appExprApplied;
                funcAnnotations = recAnns.funcAnnotations;
                remainingFuncAnnotations = recAnns.remainingFuncAnnotations;
                downSubst = toFill.downSubst;
                finalSubst = toFill.finalSubst;
                defaultInheritedAnnos = annos;
                flowDefs = toFill.flowDefs;
            }
            | _ -> decorate snocAnnoAppExprs(new(recAnns), ',', thisAnno, location=recAnns.location) with {
                env = toFill.env;
                config = toFill.config;
                grammarName = toFill.grammarName;
                frame = toFill.frame;
                compiledGrammars = toFill.compiledGrammars;
                appExprApplied = toFill.appExprApplied;
                funcAnnotations = recAnns.funcAnnotations;
                remainingFuncAnnotations = recAnns.remainingFuncAnnotations;
                downSubst = toFill.downSubst;
                finalSubst = toFill.finalSubst;
                defaultInheritedAnnos = annos;
                flowDefs = toFill.flowDefs;                
            }
        end
        else recAnns; 
    
    return annsWithDefaults;
}

function missingContainsAnno
Boolean ::= missing::[NamedArgType] anno::Pair<String AppExpr> 
{
    return if null(missing) then false 
        else if head(missing).argName == anno.fst then true
        else missingContainsAnno(tail(missing), anno);
}

function stripMissingAnnos
[NamedArgType] ::= missing::[NamedArgType] annos::[Pair<String AppExpr>]
{
    return if null(annos) then missing 
        else stripMissingAnnos(stripMissingAnnosOne(missing, head(annos)), tail(annos));   
}

function stripMissingAnnosOne
[NamedArgType] ::= missing::[NamedArgType] anno::Pair<String AppExpr>
{
    return if null(missing) then missing
        -- We assume here that missing has no duplicates
        else if head(missing).argName == anno.fst then tail(missing)
        else [head(missing)] ++ stripMissingAnnosOne(tail(missing), anno);
}