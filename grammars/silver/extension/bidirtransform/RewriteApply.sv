grammar silver:extension:bidirtransform;

abstract production applyRw
top::Expr ::= rwr::Decorated RewriteRule rhsTy::String lhsTy::String elemName::String 
{   
    forwards to applyRwOrigin(rwr,rhsTy,lhsTy,elemName,elemName, location=top.location);
} 

abstract production applyRwOrigin
top::Expr ::= rwr::Decorated RewriteRule rhsTy::String lhsTy::String elemName::String rhsName::String
{
    forwards to rwr.outputStmt(
        -- Pass the rhs into rewrite rules that want that type
        -- We can't use restored$typeName here because 
        -- that would infinitely recurse. 
        if rhsTy == lhsTy || !rwr.shouldRestore then baseName(rhsName, location=top.location) 
        -- Otherwise pass the appropriate restored type from
        -- the lhs of this production into the rule
        else exprAccess(restoreNm(unFull(rwr.inputType.typeName)), elemName, location=top.location));
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
            strAppExprs(reverse(ns.inputNames), location=top.location),
            emptyAnnoAppExprs(location=top.location), location=top.location));
}