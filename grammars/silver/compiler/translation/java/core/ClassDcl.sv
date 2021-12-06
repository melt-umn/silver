grammar silver:compiler:translation:java:core;

aspect production typeClassDcl
top::AGDcl ::= 'class' cl::ConstraintList '=>' id::QNameType var::TypeExpr '{' body::ClassBody '}'
{
  local className :: String = "C" ++ last(explode(":", fName));

  top.genFiles := [pair(className ++ ".java", s"""

package ${makeName(top.grammarName)};

public interface ${className} {

	// Needed since 'this' may refer to something else inside member translations
	public default ${className} currentInstance() {
		return this;
	}

${flatMap(\ c::Context -> s"\tpublic ${c.transType} ${c.transContextSuperAccessorName}();\n", cl.contexts)}

${body.translation}

}
""")];
}

attribute translation occurs on ClassBody, ClassBodyItem;

aspect production consClassBody
top::ClassBody ::= h::ClassBodyItem t::ClassBody
{
  top.translation = h.translation ++ t.translation;
}
aspect production nilClassBody
top::ClassBody ::= 
{
  top.translation = "";
}

aspect production constraintClassBodyItem
top::ClassBodyItem ::= id::Name '::' cl::ConstraintList '=>' ty::TypeExpr ';'
{
  local contexts::Contexts = foldContexts(cl.contexts);
  contexts.boundVariables = boundVars;

  top.translation = s"\t${ty.typerep.transCovariantType} ${makeInstanceMemberAccessorName(id.name)}(${contexts.contextParamTrans});";
}

aspect production defaultConstraintClassBodyItem
top::ClassBodyItem ::= id::Name '::' cl::ConstraintList '=>' ty::TypeExpr '=' e::Expr ';'
{
  local contexts::Contexts = foldContexts(cl.contexts);
  contexts.boundVariables = boundVars;

  top.translation = s"""
	default ${ty.typerep.transCovariantType} ${makeInstanceMemberAccessorName(id.name)}(${contexts.contextParamTrans}) {
		final common.DecoratedNode context = common.TopNode.singleton;
		//${e.unparse}
		return ${e.generalizedTranslation};
	}
""";
}

function makeInstanceMemberAccessorName
String ::= s::String
{
  return "getMember_" ++ last(explode(":", s));
}
