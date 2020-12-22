grammar silver:definition:type:syntax;

aspect production instanceDcl
top::AGDcl ::= 'instance' cl::ConstraintList '=>' id::QNameType ty::TypeExpr '{' body::InstanceBody '}'
{
  headPreDefs <- addNewLexicalTyVars(top.grammarName, top.location, ty.lexicalTypeVariables);
}
