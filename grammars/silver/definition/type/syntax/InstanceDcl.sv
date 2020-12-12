grammar silver:definition:type:syntax;

aspect production instanceDcl
top::AGDcl ::= 'instance' cl::OptConstraintList id::QName ty::TypeExpr '{' body::InstanceBody '}'
{
  headDefs <- addNewLexicalTyVars(top.grammarName, top.location, ty.lexicalTypeVariables);
}
