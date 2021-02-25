grammar silver:compiler:translation:java:core;

aspect production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '::' cl::ConstraintList '=>' t::TypeExpr '=' e::Expr ';'
{
  top.initValues :=
    s"\tpublic static final common.Thunk<${t.typerep.transType}> global_${id.name} = ${wrapThunkText(e.translation, t.typerep.transType)};\n";
}
