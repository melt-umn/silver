grammar silver:translation:java:core;

aspect production instanceDcl
top::AGDcl ::= 'instance' cl::OptConstraintList id::QName ty::TypeExpr '{' body::InstanceBody '}'
{
  local className :: String = "C" ++ id.name ++ "_" ++ ty.typerep.transTypeName;

  top.genFiles := [pair(className ++ ".java", s"""

package ${makeName(top.grammarName)};

public class ${className} extends ${makeClassName(fName)} {

	public ${className}(${cl.constructorParamTrans}) {
${cl.constructorInitTrans}
	}

${cl.translation}
${superContexts.transContextAccessors}

${body.translation}

}
""")];
}

attribute translation occurs on OptConstraintList, ConstraintList, Constraint;
synthesized attribute constructorParamTrans::String occurs on OptConstraintList, ConstraintList, Constraint;
synthesized attribute constructorInitTrans::String occurs on OptConstraintList, ConstraintList, Constraint;

aspect production someConstraintList
top::OptConstraintList ::= cl::ConstraintList '=>'
{
  top.translation = cl.translation;
  top.constructorParamTrans = cl.constructorParamTrans;
  top.constructorInitTrans = cl.constructorInitTrans;
}
aspect production noConstraintList
top::OptConstraintList ::=
{
  top.translation = "";
  top.constructorParamTrans = "";
  top.constructorInitTrans = "";
}

aspect production consConstraint
top::ConstraintList ::= h::Constraint ',' t::ConstraintList
{
  top.translation = h.translation ++ t.translation;
  top.constructorParamTrans = h.constructorParamTrans ++ ", " ++ t.constructorParamTrans;
  top.constructorInitTrans = h.constructorInitTrans ++ ", " ++ t.constructorInitTrans;
}
aspect production oneConstraint
top::ConstraintList ::= h::Constraint
{
  top.translation = h.translation;
  top.constructorParamTrans = h.constructorParamTrans;
  top.constructorInitTrans = h.constructorInitTrans;
}
aspect production nilConstraint
top::ConstraintList ::=
{
  top.translation = "";
  top.constructorParamTrans = "";
  top.constructorInitTrans = "";
}

aspect production classConstraint
top::Constraint ::= c::QName t::TypeExpr
{
  top.translation = s"\tprivate final ${t.typerep.transType} ${makeConstraintInstanceValName(dcl.fullName, t.typerep)};\n";
  top.constructorParamTrans = s"${t.typerep.transType} ${makeConstraintInstanceValName(dcl.fullName, t.typerep)}";
  top.constructorInitTrans = s"\t\tthis.${makeConstraintInstanceValName(dcl.fullName, t.typerep)} = ${makeConstraintInstanceValName(dcl.fullName, t.typerep)};\n";
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
