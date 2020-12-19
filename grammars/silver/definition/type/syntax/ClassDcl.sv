grammar silver:definition:type:syntax;

aspect production typeClassDcl
top::AGDcl ::= 'class' cl::ConstraintList '=>' id::QNameType var::TypeExpr '{' body::ClassBody '}'
{
  headDefs <- addNewLexicalTyVars(top.grammarName, top.location, var.lexicalTypeVariables);
}
