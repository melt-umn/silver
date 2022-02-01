grammar silver:compiler:translation:java:core;

aspect production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '::' cl::ConstraintList '=>' t::TypeExpr '=' e::Expr ';'
{
  top.initValues :=
    if null(cl.contexts) then
      s"\tpublic static final common.Thunk<${t.typerep.transCovariantType}> global_${id.name} = ${wrapThunkText(e.generalizedTranslation, t.typerep.transType)};\n"
    else s"""
	public static final common.Thunk<${t.typerep.transCovariantType}> global_${id.name}(${implode(", ", map(\ c::Context -> decorate c with {boundVariables = boundVars;}.contextSigElem, cl.contexts))}){
		return ${wrapThunkText(e.generalizedTranslation, t.typerep.transType)};
	}
""";
}
