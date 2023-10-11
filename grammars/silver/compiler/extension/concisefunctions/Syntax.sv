grammar silver:compiler:extension:concisefunctions;

terminal Fun_kwd 'fun';

concrete production shortFunctionDcl
top::AGDcl ::= 'fun' id::Name ns::FunctionSignature '=' e::Expr ';'
{
  forwards to 
    globalValueDclConcrete (
      'global', id, '::', ns.cl, '=>', typerepTypeExpr(ns.namedSignature.typerep), '=', 
        lambda_c('\', ns.rhs.toLamRHS, '->', e),';'
    );
}

synthesized attribute cl::ConstraintList occurs on FunctionSignature;
synthesized attribute lhs::FunctionLHS occurs on FunctionSignature;
synthesized attribute rhs::ProductionRHS occurs on FunctionSignature;

aspect production functionSignature
top::FunctionSignature ::= cl::ConstraintList '=>' lhs::FunctionLHS '::=' rhs::ProductionRHS 
{
  top.cl = cl;
  top.lhs = lhs;
  top.rhs = rhs;
  top.t = lhs.t;
}

aspect production functionSignatureNoCL
top::FunctionSignature ::= lhs::FunctionLHS '::=' rhs::ProductionRHS 
{
  top.cl = nilConstraint();
  top.lhs = lhs;
  top.rhs = rhs;
  top.t = lhs.t;
}

synthesized attribute t::TypeExpr occurs on FunctionLHS, FunctionSignature;

aspect production functionLHS
top::FunctionLHS ::= t::TypeExpr
{
  top.t = t;
}

synthesized attribute toLamRHS::LambdaRHS occurs on ProductionRHS;

aspect production productionRHSNil
top::ProductionRHS ::=
{
  top.toLamRHS = lambdaRHSNil();
}

aspect production productionRHSCons
top::ProductionRHS ::= h::ProductionRHSElem t::ProductionRHS
{
  top.toLamRHS = lambdaRHSCons(h.toLamRHSElem, t.toLamRHS);
}

synthesized attribute toLamRHSElem::LambdaRHSElem occurs on ProductionRHSElem;

aspect production productionRHSElem
top::ProductionRHSElem ::= id::Name '::' t::TypeExpr
{
  top.toLamRHSElem = lambdaRHSElemIdTy(id, '::', t);
}

aspect production productionRHSElemType
top::ProductionRHSElem ::= t::TypeExpr
{
  top.toLamRHSElem = lambdaRHSElemTy('_', '::', t);
}