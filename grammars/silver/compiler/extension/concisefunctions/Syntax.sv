grammar silver:compiler:extension:concisefunctions;

terminal Fun_kwd 'fun';

concrete production shortFunctionDcl
top::AGDcl ::= 'fun' id::Name ns::FunctionSignature '=' e::Expr ';'
{
  propagate flowEnv, grammarName, moduleNames, env;

  top.unparse = "fun " ++ id.unparse ++ ns.unparse ++ " = " ++ e.unparse ++ ";";

  ns.signatureName = top.grammarName ++ ":" ++ id.name;

  local rhs::ProductionRHS = ns.rhs;
  rhs.env = top.env;

  forwards to
    globalValueDclConcrete (
      'global', id, '::', ns.cl, '=>', ns.funTyExpr, '=', 
        lambda_c('\', rhs.toLamRHS, '->', e), ';'
    );
}

synthesized attribute cl::ConstraintList occurs on FunctionSignature;
synthesized attribute lhs::FunctionLHS occurs on FunctionSignature;
synthesized attribute rhs::ProductionRHS occurs on FunctionSignature;
synthesized attribute funTyExpr::TypeExpr occurs on FunctionSignature;

aspect production functionSignature
top::FunctionSignature ::= cl::ConstraintList '=>' lhs::FunctionLHS '::=' rhs::ProductionRHS 
{
  top.cl = cl;
  top.lhs = lhs;
  top.rhs = rhs;
  top.funTyExpr = funTypeExpr ('(', psignature(presentSignatureLhs(lhs.tyExpr), '::=', rhs.tyExprs), ')');
}

aspect production functionSignatureNoCL
top::FunctionSignature ::= lhs::FunctionLHS '::=' rhs::ProductionRHS 
{
  top.cl = nilConstraint();
  top.lhs = lhs;
  top.rhs = rhs;
  rhs.env = top.env;
  top.funTyExpr = funTypeExpr ('(', psignature(presentSignatureLhs(lhs.tyExpr), '::=', rhs.tyExprs), ')');
}

synthesized attribute tyExpr::TypeExpr occurs on FunctionLHS;

aspect production functionLHS
top::FunctionLHS ::= t::TypeExpr
{
  top.tyExpr = t;
}

synthesized attribute toLamRHS::LambdaRHS occurs on ProductionRHS;
synthesized attribute tyExprs::TypeExprs occurs on ProductionRHS;

aspect production productionRHSNil
top::ProductionRHS ::=
{
  top.toLamRHS = lambdaRHSNil();
  top.tyExprs = typeListNone();
}

aspect production productionRHSCons
top::ProductionRHS ::= h::ProductionRHSElem t::ProductionRHS
{
  top.toLamRHS = lambdaRHSCons(h.toLamRHSElem, t.toLamRHS);
  top.tyExprs = typeListCons(h.tyExpr, t.tyExprs);
}

synthesized attribute toLamRHSElem::LambdaRHSElem occurs on ProductionRHSElem;
attribute tyExpr occurs on ProductionRHSElem;

aspect production productionRHSElem
top::ProductionRHSElem ::= id::Name '::' t::TypeExpr
{
  top.toLamRHSElem = lambdaRHSElemIdTy(id, '::', t);
  top.tyExpr = t;
}

aspect production productionRHSElemType
top::ProductionRHSElem ::= t::TypeExpr
{
  top.toLamRHSElem = lambdaRHSElemTy('_', '::', t);
  top.tyExpr = t;
}