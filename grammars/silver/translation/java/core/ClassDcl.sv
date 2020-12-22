grammar silver:translation:java:core;

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

${sflatMap(\ c::Context -> s"\tpublic ${c.transType} ${c.transContextSuperAccessorName}();\n", cl.contexts)}

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

aspect production classBodyItem
top::ClassBodyItem ::= id::Name '::' ty::TypeExpr ';'
{
  top.translation = s"\t${ty.typerep.transType} ${makeInstanceMemberAccessorName(id.name)}();";
}

aspect production defaultClassBodyItem
top::ClassBodyItem ::= id::Name '::' ty::TypeExpr '=' e::Expr ';'
{
  top.translation = s"""
	default ${ty.typerep.transClassType} ${makeInstanceMemberAccessorName(id.name)}() {
		return ${e.translation};
	}
""";
}

function makeInstanceMemberAccessorName
String ::= s::String
{
  return "getMember_" ++ last(explode(":", s));
}
