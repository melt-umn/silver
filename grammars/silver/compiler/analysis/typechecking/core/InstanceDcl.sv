grammar silver:compiler:analysis:typechecking:core;

aspect production instanceDcl
top::AGDcl ::= 'instance' cl::ConstraintList '=>' id::QNameType ty::TypeExpr '{' body::InstanceBody '}'
{
  top.errors <-
    if ty.typerep.kindArity == id.lookupType.typeScheme.typerep.kindArity then []
    else [err(ty.location, s"${ty.unparse} has kind arity ${toString(ty.typerep.kindArity)}, but the class ${id.name} expected kind arity ${toString(id.lookupType.typeScheme.typerep.kindArity)}")];

  superContexts.contextLoc = id.location;
  superContexts.contextSource = "instance superclasses";
  top.errors <- superContexts.contextErrors;
}

aspect production instanceBodyItem
top::InstanceBodyItem ::= id::QName '=' e::Expr ';'
{
  production expectedType::Type =
    fromMaybe(pair(errorType(), false), lookupBy(stringEq, top.fullName, top.expectedClassMembers)).fst;

  local errCheck1::TypeCheck = check(expectedType, e.typerep);
  top.errors <-
    if errCheck1.typeerror
    then [err(e.location, s"Member ${id.name} has expected type ${errCheck1.leftpp}, but the expression has actual type ${errCheck1.rightpp}")]
    else []; 
  
  e.downSubst = emptySubst();
  errCheck1.downSubst = e.upSubst;
  e.finalSubst = errCheck1.upSubst;
  errCheck1.finalSubst = e.finalSubst;
}
