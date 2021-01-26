grammar silver:compiler:translation:java:type;

import silver:compiler:definition:env;

-- Translation of *solved* contexts, not *constraint* contexts
synthesized attribute transContexts::[String] occurs on Contexts;
synthesized attribute transContextSuperAccessors::String occurs on Contexts;

aspect production consContext
top::Contexts ::= h::Context t::Contexts
{
  top.transContexts = h.transContext :: t.transContexts;
  top.transContextSuperAccessors = h.transContextSuperAccessor ++ t.transContextSuperAccessors;
}
aspect production nilContext
top::Contexts ::=
{
  top.transContexts = [];
  top.transContextSuperAccessors = "";
}

attribute transType occurs on Context;
synthesized attribute transContext::String occurs on Context;

synthesized attribute transContextSuperAccessorName::String occurs on Context;
synthesized attribute transContextSuperAccessor::String occurs on Context;

aspect production instContext
top::Context ::= fn::String t::Type
{
  top.transType = makeClassName(fn);
  
  resolvedDcl.transContextDeps = requiredContexts.transContexts;
  top.transContext = resolvedDcl.transContext;
  
  top.transContextSuperAccessorName = makeInstanceSuperAccessorName(fn);
  top.transContextSuperAccessor = s"""
	public final ${top.transType} ${top.transContextSuperAccessorName}() {
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
  top.transContext = s"new ${makeInstanceName(top.sourceGrammar, fn, ty)}(${implode(", ", top.transContextDeps)})";
}
aspect production instConstraintDcl
top::DclInfo ::= fntc::String ty::Type
{
  top.transContext = makeConstraintDictName(fntc, ty);
}
aspect production sigConstraintDcl
top::DclInfo ::= fntc::String ty::Type fnsig::String
{
  top.transContext = s"((${makeProdName(fnsig)})(context.undecorate())).${makeConstraintDictName(fntc, ty)}";
}
aspect production currentInstDcl
top::DclInfo ::= fntc::String ty::Type
{
  top.transContext = s"currentInstance()";
}
aspect production instSuperDcl
top::DclInfo ::= fntc::String baseDcl::DclInfo ty::Type
{
  baseDcl.transContextDeps = top.transContextDeps;
  top.transContext = baseDcl.transContext ++ s".${makeInstanceSuperAccessorName(fntc)}()";
}

function makeConstraintDictName
String ::= s::String t::Type
{
  t.boundVariables = [];
  return "d_" ++ substitute(":", "_", s) ++ "_" ++ t.transTypeName;
}

function makeInstanceSuperAccessorName
String ::= s::String
{
  return "getSuper_" ++ substitute(":", "_", s);
}
