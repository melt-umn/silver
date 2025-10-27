grammar silver:compiler:extension:doc:core;

synthesized attribute argNames::[String] 
occurs on FunctionSignature, ProductionSignature, AspectProductionSignature,
          AspectFunctionSignature, AspectRHS, AspectRHSElem, ProductionRHS, ProductionRHSElem;

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
top::ProductionRHSElem ::= ms::MaybeShared id::Name '::' t::TypeExpr
{
  top.argNames = [id.name];
}

aspect production productionRHSElemType
top::ProductionRHSElem ::= ms::MaybeShared t::TypeExpr
{
  top.argNames = []; -- Don't consider unnamed parameters against count
}




aspect production aspectProductionSignature
top::AspectProductionSignature ::= lhs::AspectProductionLHS '::=' rhs::AspectRHS 
{
  top.argNames = rhs.argNames;
}

aspect production aspectFunctionSignature
top::AspectFunctionSignature ::= lhs::AspectFunctionLHS '::=' rhs::AspectRHS 
{
  top.argNames = rhs.argNames;
}


aspect production aspectRHSElemNil
top::AspectRHS ::= 
{
  top.argNames = [];
}

aspect production aspectRHSElemCons
top::AspectRHS ::= h::AspectRHSElem t::AspectRHS
{
  top.argNames = h.argNames ++ t.argNames;
}

aspect production aspectRHSElemNone
top::AspectRHSElem ::= '_'
{
  top.argNames = []; -- Don't consider _ parameters against count
}

aspect production aspectRHSElemFull
top::AspectRHSElem ::= shared::Boolean id::Name t::Type
{
  top.argNames = [id.name];
}
