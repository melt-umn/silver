grammar silver:definition:type:syntax;

aspect production typeClassDcl
top::AGDcl ::= 'class' cl::ConstraintList '=>' id::QNameType var::TypeExpr '{' body::ClassBody '}'
{
  headPreDefs <- addNewLexicalTyVars(top.grammarName, top.location, var.lexicalTypeVariables);
}
