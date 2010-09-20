grammar silver:analysis:typechecking:core;

aspect production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '::' t::Type '=' e::Expr ';'
{
  top.typeErrors := e.typeErrors;
  
  local attribute fusedTyVars :: [TyVar];
  fusedTyVars = t.typerep.freeVariables ++ e.typerep.freeVariables;
  
  top.typeErrors <- if unify(e.typerep, t.typerep).failure
                    then [err(top.location, "Declaration of global " ++ id.name ++ " with type " ++ prettyTypeWith(t.typerep, fusedTyVars) ++ " has initialization expression with type " ++ prettyTypeWith(e.typerep, fusedTyVars))]
                    else [];

  e.downSubst = emptySubst();
  e.finalSubst = e.upSubst;
}

