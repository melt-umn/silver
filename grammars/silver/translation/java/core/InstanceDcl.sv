grammar silver:translation:java:core;

aspect production instanceDcl
top::AGDcl ::= 'instance' cl::OptConstraintList id::QName ty::TypeExpr '{' body::InstanceBody '}'
{
  local className :: String = "C" ++ id.name ++ "_" ++ ty.typerep.transTypeName;

  top.genFiles := [pair(className ++ ".java", s"""

package ${makeName(top.grammarName)};

public class ${className} extends ${makeClassName(fName)} {

	public ${className}(${implode(", ", map((.contextParamTrans), cl.contexts))}) {
${sflatMap((.contextInitTrans), cl.contexts)}
	}

${sflatMap((.contextMemberDeclTrans), cl.contexts)}
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
  top.contextMemberDeclTrans = s"\public final ${top.transType} ${makeConstraintDictName(fn, t)};\n";
  top.contextParamTrans = s"${top.transType} ${makeConstraintDictName(fn, t)}";
  top.contextInitTrans = s"\t\tthis.${makeConstraintDictName(fn, t)} = ${makeConstraintDictName(fn, t)};\n";
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
	public ${id.lookupValue.typeScheme.typerep.transType} ${makeInstanceMemberAccessorName(top.fullName)}() {
		return ${e.translation};
	}
""";
}
