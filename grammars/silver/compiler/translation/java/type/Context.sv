grammar silver:compiler:translation:java:type;

import silver:compiler:definition:env;

-- Translation of *solved* contexts, not *constraint* contexts
synthesized attribute transContexts::[String] occurs on Contexts;
synthesized attribute transTypeableContexts::[String] occurs on Contexts; -- Hack to inject varTypeRep bindings
synthesized attribute transContextSuperAccessors::String occurs on Contexts;

aspect production consContext
top::Contexts ::= h::Context t::Contexts
{
  top.transContexts = h.transContext :: t.transContexts;
  top.transTypeableContexts = h.transTypeableContext :: t.transTypeableContexts;
  top.transContextSuperAccessors = h.transContextSuperAccessor ++ t.transContextSuperAccessors;
}
aspect production nilContext
top::Contexts ::=
{
  top.transContexts = [];
  top.transTypeableContexts = [];
  top.transContextSuperAccessors = "";
}

attribute transType occurs on Context;
synthesized attribute transContext::String occurs on Context;
synthesized attribute transTypeableContext::String occurs on Context;

synthesized attribute transContextSuperAccessorName::String occurs on Context;
synthesized attribute transContextSuperAccessor::String occurs on Context;

aspect production instContext
top::Context ::= fn::String t::Type
{
  top.transType = makeClassName(fn);
  
  resolvedDcl.transContextDeps = requiredContexts.transContexts;
  top.transContext = resolvedDcl.transContext;
  top.transTypeableContext = top.transContext; -- Shouldn't be demanded?
  
  top.transContextSuperAccessorName = makeInstanceSuperAccessorName(fn);
  top.transContextSuperAccessor = s"""
	public final ${top.transType} ${top.transContextSuperAccessorName}() {
		return ${top.transContext};
	}
""";
}

aspect production typeableContext
top::Context ::= t::Type
{
  top.transType = "common.TypeRep";
  
  resolvedDcl.transContextDeps = requiredContexts.transTypeableContexts;
  top.transTypeableContext =
    case top.resolved, t of
    -- We might need translate this context even when resolution fails;
    -- in that case fall back to a rigid skolem constant.
    | [], skolemType(tv) -> s"new common.BaseTypeRep(\"b${toString(tv.extractTyVarRep)}\")"
    | _, _ -> resolvedDcl.transContext
    end;
  top.transContext =
      if null(t.freeVariables) then top.transTypeableContext
      -- Workaround to inject variable bindings
      else s"""(new common.Typed() {
				public final common.TypeRep getType() {
${makeTyVarDecls(5, t.freeVariables)}
					return ${top.transTypeableContext};
				}
			}).getType()""";
  
  top.transContextSuperAccessorName = "getType";
  top.transContextSuperAccessor = s"""
	public final common.TypeRep getType() {
		return ${top.transTypeableContext};
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
top::DclInfo ::= fntc::String baseDcl::DclInfo
{
  baseDcl.transContextDeps = top.transContextDeps;
  top.transContext = baseDcl.transContext ++ s".${makeInstanceSuperAccessorName(fntc)}()";
}
aspect production typeableInstConstraintDcl
top::DclInfo ::= ty::Type
{
  top.transContext = makeTypeableName(ty);
}
aspect production typeableSigConstraintDcl
top::DclInfo ::= ty::Type fnsig::String
{
  top.transContext = s"((${makeProdName(fnsig)})(context.undecorate())).${makeTypeableName(ty)}"; 
}
aspect production typeableSuperDcl
top::DclInfo ::= baseDcl::DclInfo
{
  baseDcl.transContextDeps = top.transContextDeps;
  top.transContext = baseDcl.transContext ++ ".getType()";
}
aspect production typeableDcl
top::DclInfo ::= ty::Type
{
  ty.skolemTypeReps = zipWith(pair, ty.freeVariables, top.transContextDeps);
  top.transContext = ty.transTypeRep;
}

function makeConstraintDictName
String ::= s::String t::Type
{
  t.boundVariables = [];
  return "d_" ++ substitute(":", "_", s) ++ "_" ++ t.transTypeName;
}

function makeTypeableName
String ::= t::Type
{
  t.boundVariables = [];
  return "typeRep_" ++ t.transTypeName;
}

function makeInstanceSuperAccessorName
String ::= s::String
{
  return "getSuper_" ++ substitute(":", "_", s);
}
