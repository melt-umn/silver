grammar silver:definition:type:syntax;

nonterminal OptConstraintList with config, grammarName, env, location, unparse, errors, defs, contexts, lexicalTypeVariables;
nonterminal ConstraintList with config, grammarName, env, location, unparse, errors, defs, contexts, lexicalTypeVariables;
nonterminal Constraint with config, grammarName, env, location, unparse, errors, defs, contexts, lexicalTypeVariables;

propagate errors, defs, lexicalTypeVariables on OptConstraintList, ConstraintList, Constraint;

concrete production someConstraintList
top::OptConstraintList ::= cl::ConstraintList '=>'
{
  top.unparse = cl.unparse ++ " =>";
  top.contexts = cl.contexts;
}
concrete production noConstraintList
top::OptConstraintList ::=
{
  top.unparse = "";
  top.contexts = [];
}

concrete production consConstraint
top::ConstraintList ::= h::Constraint ',' t::ConstraintList
{
  top.unparse = h.unparse ++ ", " ++ t.unparse;
  top.contexts = h.contexts ++ t.contexts;
}
concrete production oneConstraint
top::ConstraintList ::= h::Constraint
{
  top.unparse = h.unparse;
  top.contexts = h.contexts;
}
abstract production nilConstraint
top::ConstraintList ::=
{
  top.unparse = "";
  top.contexts = [];
}

concrete production classConstraint
top::Constraint ::= c::QName t::TypeExpr
{
  top.contexts = [instContext(dcl.fullName, t.typerep)];
  
  production dcl::DclInfo = c.lookupType.dcl;
  dcl.givenInstanceType = t.typerep;
  
  top.errors <- c.lookupType.errors;
  top.errors <-
    if dcl.isClass then []
    else [err(c.location, c.name ++ " is not a type class.")];
  top.errors <- t.errorsTyVars;
  
  top.defs <- [instConstraintDef(top.grammarName, top.location, dcl.fullName, t.typerep)];
  top.defs <- map(
    \ c::Context -> c.contextSuperDef(top.grammarName, top.location, dcl.fullName),
    dcl.superContexts);
}
