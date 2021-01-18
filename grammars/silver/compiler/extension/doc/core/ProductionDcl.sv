synthesized attribute argNames::[String] 
occurs on FunctionSignature, ProductionSignature, ProductionRHS, ProductionRHSElem;

aspect production functionSignature
top::FunctionSignature ::= cl::ConstraintList '=>' lhs::FunctionLHS '::=' rhs::ProductionRHS 
{
  top.argNames = rhs.argNames;
}

aspect production productionSignature
top::ProductionSignature ::= cl::ConstraintList '=>' lhs::ProductionLHS '::=' rhs::ProductionRHS
{
  top.argNames = rhs.argNames;
}

aspect production productionRHSNil
top::ProductionRHS ::=
{
  top.argNames = [];
}

aspect production productionRHSCons
top::ProductionRHS ::= h::ProductionRHSElem t::ProductionRHS
{
  top.argNames = h.argNames ++ t.argNames;
}

aspect production productionRHSElem
top::ProductionRHSElem ::= id::Name '::' t::TypeExpr
{
  top.argNames = [id.name];
}

aspect production productionRHSElemType
top::ProductionRHSElem ::= t::TypeExpr
{
  top.argNames = [""];
}
