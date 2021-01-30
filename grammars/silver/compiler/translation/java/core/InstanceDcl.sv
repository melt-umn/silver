grammar silver:compiler:translation:java:core;

aspect production instanceDcl
top::AGDcl ::= 'instance' cl::ConstraintList '=>' id::QNameType ty::TypeExpr '{' body::InstanceBody '}'
{
  local className :: String = "I" ++ substitute(":", "_", fName) ++ "_" ++ transTypeName(ty.typerep);

  top.genFiles := [pair(className ++ ".java", s"""

package ${makeName(top.grammarName)};

public class ${className} implements ${makeClassName(fName)} {
	final static common.DecoratedNode context = common.TopNode.singleton; // For decoration in member bodies

	public ${className}(${implode(", ", map((.contextParamTrans), cl.contexts))}) {
${flatMap((.contextInitTrans), cl.contexts)}
	}

${flatMap((.contextMemberDeclTrans), cl.contexts)}
${superContexts.transContextSuperAccessors}

${body.translation}

}
""")];
}

synthesized attribute contextMemberDeclTrans::String occurs on Context;
synthesized attribute contextParamTrans::String occurs on Context;
synthesized attribute contextInitTrans::String occurs on Context;

aspect production instContext
top::Context ::= fn::String t::Type
{
  top.contextMemberDeclTrans = s"\tprivate final ${top.transType} ${makeConstraintDictName(fn, t)};\n";
  top.contextParamTrans = s"${top.transType} ${makeConstraintDictName(fn, t)}";
  top.contextInitTrans = s"\t\tthis.${makeConstraintDictName(fn, t)} = ${makeConstraintDictName(fn, t)};\n";
}

aspect production typeableContext
top::Context ::= t::Type
{
  top.contextMemberDeclTrans = s"\tprivate final common.TypeRep typeRep_${t.transTypeName};\n";
  top.contextParamTrans = s"common.TypeRep typeRep_${t.transTypeName}";
  top.contextInitTrans = s"\t\tthis.typeRep_${t.transTypeName} = typeRep_${t.transTypeName};\n";
}

attribute translation occurs on InstanceBody, InstanceBodyItem;

aspect production consInstanceBody
top::InstanceBody ::= h::InstanceBodyItem t::InstanceBody
{
  top.translation = h.translation ++ t.translation;
}
aspect production nilInstanceBody
top::InstanceBody ::= 
{
  top.translation = "";
}

aspect production instanceBodyItem
top::InstanceBodyItem ::= id::QName '=' e::Expr ';'
{
  top.translation = s"""
	public ${id.lookupValue.typeScheme.typerep.transCovariantType} ${makeInstanceMemberAccessorName(top.fullName)}(${implode(", ", map((.contextParamTrans), memberContexts))}) {
		return ${e.translation};
	}
""";
}
