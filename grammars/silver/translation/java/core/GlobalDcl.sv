grammar silver:translation:java:core;

aspect production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '::' t::TypeExpr '=' e::Expr ';'
{
  top.initValues :=
    s"\tpublic static final common.Thunk<${t.typerep.transType}> ${id.name} = ${wrapThunkText(e.translation, t.typerep.transType)};\n";
  
  e.expectedTypeTranslation = makeExpectedTypeDirect(t.typerep);
}
