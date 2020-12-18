grammar silver:translation:java:core;

aspect production typeClassDcl
top::AGDcl ::= 'class' cl::OptConstraintList id::Name var::TypeExpr '{' body::ClassBody '}'
{
  local className :: String = "C" ++ id.name;

  top.genFiles := [pair(className ++ ".java", s"""

package ${makeName(top.grammarName)};

public interface ${className} {

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
  top.translation = s"\tpublic ${ty.typerep.transType} ${makeInstanceMemberAccessorName(id.name)}();";
}

function makeInstanceMemberAccessorName
String ::= s::String
{
  return "getMember" ++ last(explode(":", s));
}
