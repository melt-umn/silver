grammar silver:translation:java:type;

import silver:definition:env;

synthesized attribute transContexts::[String] occurs on Contexts;
synthesized attribute transContextAccessors::String occurs on Contexts;

aspect production consContext
top::Contexts ::= h::Context t::Contexts
{
  top.transContexts = h.transContext :: t.transContexts;
  top.transContextAccessors = h.transContextAccessor ++ t.transContextAccessors;
}
aspect production nilContext
top::Contexts ::=
{
  top.transContexts = [];
  top.transContextAccessors = "";
}

attribute transType occurs on Context;
synthesized attribute transContext::String occurs on Context;

synthesized attribute transContextAccessorName::String occurs on Context;
synthesized attribute transContextAccessor::String occurs on Context;

aspect production instContext
top::Context ::= fn::String t::Type
{
  top.transType = makeClassName(fn);
  
  resolvedDcl.transContextDeps = requiredContexts.transContexts;
  top.transContext = resolvedDcl.transContext;
  
  top.transContextAccessorName = makeInstanceSuperAccessorName(fn);
  top.transContextAccessor = s"""
	public final ${top.transType} ${top.transContextAccessorName}() {
		return ${top.transContext};
	}
""";
}

-- The translations of the narrowed forms of the instDcl's contexts are fed back as dcl.transContextDeps 
inherited attribute transContextDeps::[String] occurs on DclInfo;
attribute transContext occurs on DclInfo;

aspect default production
top::DclInfo ::=
{
  -- See TODO in the env DclInfo
  top.transContext = error("Internal compiler error: must be defined for all instance declarations");
}
aspect production instDcl
top::DclInfo ::= fn::String bound::[TyVar] contexts::[Context] ty::Type
{
  top.transContext = s"new ${makeInstanceName(fn, ty)}(${implode(", ", top.transContextDeps)})";
}
aspect production instConstraintDcl
top::DclInfo ::= fntc::String ty::Type
{
  top.transContext = makeConstraintInstanceValName(fntc, ty);
}
aspect production instSuperDcl
top::DclInfo ::= fntc::String baseDcl::DclInfo ty::Type
{
  baseDcl.transContextDeps = top.transContextDeps;
  top.transContext = baseDcl.transContext ++ s".${makeInstanceSuperAccessorName(fntc)}()";
}

function makeConstraintInstanceValName
String ::= s::String t::Type
{
  return "d_" ++ last(explode(":", s)) ++ "_" ++ t.transTypeName;
}

function makeInstanceSuperAccessorName
String ::= s::String
{
  return "getSuper" ++ makeName(s);
}
