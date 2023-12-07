grammar silver:compiler:extension:concisefunctions;

terminal Fun_kwd 'fun' lexer classes {KEYWORD};

{--
 - Concise function declarations - these forward to globals with lambda expressions
 - @param id The name of the concise function
 - @param ns The signature of the function
 - @param e The expression that serves as the body of the function
 -}
concrete production shortFunctionDcl
top::AGDcl ::= 'fun' id::Name ns::FunctionSignature '=' e::Expr ';'
{
  propagate moduleNames, grammarName, flowEnv;

  top.unparse = "fun " ++ id.unparse ++ ns.unparse ++ " = " ++ e.unparse ++ ";";

  local rhs::ProductionRHS = ns.rhs;
  rhs.env = top.env;

  ns.signatureName = top.grammarName ++ ":" ++ id.name;

  forwards to
    globalValueDclConcrete (
      'global', @id, '::', ns.cl, '=>',
        funTypeExpr ('(', psignature(presentSignatureLhs(ns.lhs.tyExpr), '::=', rhs.tyExprs), ')'),
      '=', lambda_c('\', rhs.toLamRHS, '->', @e), ';'
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
}

aspect production functionSignatureNoCL
top::FunctionSignature ::= lhs::FunctionLHS '::=' rhs::ProductionRHS 
{
  top.cl = nilConstraint();
  top.lhs = lhs;
  top.rhs = rhs;
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