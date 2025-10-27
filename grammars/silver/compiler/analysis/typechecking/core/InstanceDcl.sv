grammar silver:compiler:analysis:typechecking:core;

aspect production instanceDcl
top::AGDcl ::= 'instance' cl::ConstraintList '=>' id::QNameType ty::TypeExpr '{' body::InstanceBody '}'
{
  top.errors <-
    if ty.typerep.kindrep == id.lookupType.typeScheme.typerep.kindrep then []
    else [errFromOrigin(ty, s"${ty.unparse} has kind ${prettyKind(ty.typerep.kindrep)}, but the class ${id.name} expected kind ${prettyKind(id.lookupType.typeScheme.typerep.kindrep)}")];

  superContexts.contextLoc = id.nameLoc;
  superContexts.contextSource = "instance superclasses";
  top.errors <- superContexts.contextErrors;
}

aspect production instanceBodyItem
top::InstanceBodyItem ::= id::QName '=' e::Expr ';'
{
  local errCheck1::TypeCheck = check(typeScheme.typerep, e.typerep);
  top.errors <-
    if errCheck1.typeerror
    then [errFromOrigin(e, s"Member ${id.name} has expected type ${errCheck1.leftpp}, but the expression has actual type ${errCheck1.rightpp}")]
    else []; 
  
  e.downSubst = instSubst;
  errCheck1.downSubst = e.upSubst;
  e.downSubst2 = errCheck1.upSubst;
  e.finalSubst = e.upSubst2;
  errCheck1.finalSubst = e.finalSubst;
}
