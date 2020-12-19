grammar silver:analysis:typechecking:core;

aspect production instanceDcl
top::AGDcl ::= 'instance' cl::ConstraintList '=>' id::QNameType ty::TypeExpr '{' body::InstanceBody '}'
{
  superContexts.contextLoc = id.location;
  superContexts.contextSource = "instance superclasses";
  top.errors <- superContexts.contextErrors;
}

aspect production consInstanceBody
top::InstanceBody ::= h::InstanceBodyItem t::InstanceBody
{
  
}
aspect production nilInstanceBody
top::InstanceBody ::= 
{
  
}

aspect production instanceBodyItem
top::InstanceBodyItem ::= id::QName '=' e::Expr ';'
{
  production expectedType::Type =
    fromMaybe(errorType(), lookupBy(stringEq, top.fullName, top.expectedClassMembers));

  local errCheck1::TypeCheck = check(e.typerep, expectedType);
  top.errors <-
    if errCheck1.typeerror
    then [err(e.location, s"Member ${id.name} has expected type ${errCheck1.leftpp}, but the expression has actual type ${errCheck1.rightpp}")]
    else []; 
  
  e.downSubst = emptySubst();
  errCheck1.downSubst = e.upSubst;
  e.finalSubst = e.upSubst;
  errCheck1.finalSubst = e.finalSubst;
}
