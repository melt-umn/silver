grammar silver:compiler:translation:java:core;

aspect production instanceDcl
top::AGDcl ::= 'instance' cl::ConstraintList '=>' id::QNameType ty::TypeExpr '{' body::InstanceBody '}'
{
  local className :: String = "I" ++ substitute(":", "_", fName) ++ "_" ++ transTypeName(ty.typerep);

  local contexts::Contexts = foldContexts(cl.contexts);
  contexts.boundVariables = boundVars;

  top.genFiles := if contexts.isTypeError then [] else [(className ++ ".java", s"""

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

aspect default production
top::Context ::=
{
  top.contextMemberDeclTrans = s"\tpublic final ${top.transType} ${top.transContextMemberName};\n";
  top.contextParamTrans = s"${top.transType} ${top.transContextMemberName}";
  top.contextInitTrans = s"\t\tthis.${top.transContextMemberName} = ${top.transContextMemberName};\n";
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
  contexts.boundVariables = boundVars;

  top.translation = s"""
	public ${e.finalType.transType} ${makeInstanceMemberAccessorName(top.fullName)}(${contexts.contextParamTrans}) {
		//${e.unparse}
		return ${e.generalizedTranslation};
	}
""";
}
