grammar silver:extension:bidirtransform;

-- todo: right now this means all elements of a rewrite rule need to be 
-- anonymous variables. This should change, so some variables can be skipped
-- or provided explicitly like integer values

abstract production applyRw
top::Expr ::= rwr::Decorated RewriteRule rhsTy::String lhsTy::String elemName::String
{
    forwards to rwr.outputStmt(
        -- Pass the rhs of an origin into
        -- rewrite rules that want that type
        -- We can't use restored$typeName here because 
        -- that would infinitely recurse. 
        if rhsTy == lhsTy || !rwr.shouldRestore then baseName(elemName, location=top.location) 
        -- Otherwise pass the appropriate restored type from
        -- this origin into the rule
        else exprAccess(restoreNm(unFull(rwr.inputType.typeName)), "o", location=top.location));
} 

-- LHS is a concrete syntax type name
abstract production applyRwProd
top::Expr ::= rwr::Decorated RewriteRule ns::Decorated NamedSignature
{   
    local fwdFunc::(Expr ::= Expr) = if rwr.shouldRestore then rwr.restoreStmt
        else rwr.outputStmt;

    forwards to fwdFunc(
        fullFunc(
            rwr.inputProduction.name, 
            strAppExprs(ns.inputNames, location=top.location),
            emptyAnnoAppExprs(location=top.location), location=top.location));
}