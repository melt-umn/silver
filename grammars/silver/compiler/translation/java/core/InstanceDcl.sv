grammar silver:compiler:translation:java:core;

aspect production instanceDcl
top::AGDcl ::= 'instance' cl::ConstraintList '=>' id::QNameType ty::TypeExpr '{' body::InstanceBody '}'
{
  local className :: String = "I" ++ substitute(":", "_", fName) ++ "_" ++ transTypeName(ty.typerep);

  local contexts::Contexts = foldContexts(cl.contexts);
  contexts.boundVariables = boundVars;

  top.genFiles := [pair(className ++ ".java", s"""

package ${makeName(top.grammarName)};

public class ${className} implements ${makeClassName(fName)} {
	final static common.DecoratedNode context = common.TopNode.singleton; // For decoration in member bodies

	public ${className}(${contexts.contextParamTrans}) {
${contexts.contextInitTrans}
	}

${contexts.contextMemberDeclTrans}
${superContexts.transContextSuperAccessors}

${body.translation}

}
""")];
}

synthesized attribute contextMemberDeclTrans::String occurs on Contexts, Context;
synthesized attribute contextParamTrans::String occurs on Contexts, Context;
synthesized attribute contextInitTrans::String occurs on Contexts, Context;

aspect production consContext
top::Contexts ::= h::Context t::Contexts
{
  top.contextMemberDeclTrans = h.contextMemberDeclTrans ++ t.contextMemberDeclTrans;
  top.contextParamTrans = h.contextParamTrans ++ case t of nilContext() -> "" | _ -> ", " ++ t.contextParamTrans end;
  top.contextInitTrans = h.contextInitTrans ++ t.contextInitTrans;
}
aspect production nilContext
top::Contexts ::=
{
  top.contextMemberDeclTrans = "";
  top.contextParamTrans = "";
  top.contextInitTrans = "";
}

aspect production instContext
top::Context ::= fn::String t::Type
{
  top.contextMemberDeclTrans = s"\tpublic final ${top.transType} ${makeConstraintDictName(fn, t, top.boundVariables)};\n";
  top.contextParamTrans = s"${top.transType} ${makeConstraintDictName(fn, t, top.boundVariables)}";
  top.contextInitTrans = s"\t\tthis.${makeConstraintDictName(fn, t, top.boundVariables)} = ${makeConstraintDictName(fn, t, top.boundVariables)};\n";
}

aspect production typeableContext
top::Context ::= t::Type
{
  top.contextMemberDeclTrans = s"\tpublic final common.TypeRep ${makeTypeableName(t, top.boundVariables)};\n";
  top.contextParamTrans = s"common.TypeRep ${makeTypeableName(t, top.boundVariables)}";
  top.contextInitTrans = s"\t\tthis.${makeTypeableName(t, top.boundVariables)} = ${makeTypeableName(t, top.boundVariables)};\n";
}

aspect production inhSubsetContext
top::Context ::= i1::Type i2::Type
{
  top.contextMemberDeclTrans = s"\tpublic final ${top.transType} ${makeInhSubsetName(i1, i2, top.boundVariables)};\n";
  top.contextParamTrans = s"${top.transType} ${makeInhSubsetName(i1, i2, top.boundVariables)}";
  top.contextInitTrans = s"\t\tthis.${makeInhSubsetName(i1, i2, top.boundVariables)} = ${makeInhSubsetName(i1, i2, top.boundVariables)};\n";
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
  local contexts::Contexts = foldContexts(memberContexts);
  contexts.boundVariables = top.instanceType.freeVariables;

  top.translation = s"""
	public ${id.lookupValue.typeScheme.typerep.transCovariantType} ${makeInstanceMemberAccessorName(top.fullName)}(${contexts.contextParamTrans}) {
		return ${e.translation};
	}
""";
}
