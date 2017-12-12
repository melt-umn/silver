grammar silver:extension:bidirtransform;

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