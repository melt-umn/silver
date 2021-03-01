grammar silver:compiler:translation:java:core;

aspect production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '::' cl::ConstraintList '=>' t::TypeExpr '=' e::Expr ';'
{
  top.initValues :=
    if null(cl.contexts) then
      s"\tpublic static final common.Thunk<${t.typerep.transCovariantType}> global_${id.name} = ${wrapThunkText(e.translation, t.typerep.transCovariantType)};\n"
    else s"""
	public static final common.Thunk<${t.typerep.transCovariantType}> global_${id.name}(${implode(", ", map((.contextSigElem), cl.contexts))}){
		return ${wrapThunkText(e.translation, t.typerep.transCovariantType)};
	}
""";
}
