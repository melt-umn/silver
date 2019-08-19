import silver:definition:origins;

synthesized attribute contextRef :: String occurs on ContextOriginInfoSource;

aspect production useContextLhsAndRules
top::ContextOriginInfoSource ::=
{
  top.contextRef = "new common.OriginContext(context.undecorate(), new java.util.ArrayList<core.NOriginLink>())";
}

aspect production useRuntimePassedInfo
top::ContextOriginInfoSource ::=
{
  top.contextRef = "originCtx";
}

aspect production useBogusInfo
top::ContextOriginInfoSource ::= name::String
{
  top.contextRef = "common.OriginContext."++name;
}

function makeOriginContextRef
String ::= top::Decorated Expr --need .frame anno
{
  return top.frame.originsContextSource.contextRef;
  -- ORIGINS TODO: rules ref from top.originRules
}

global newConstructionOriginUsingCtxRef :: String =
	"originCtx.makeNewConstructionOrigin(false)";

function makeNewConstructionOrigin
String ::= top::Decorated Expr --need .frame anno
{
  -- ORIGINS TODO: rules ref from top.originRules, er from top.isRoot
  return makeOriginContextRef(top)++".makeNewConstructionOrigin(false)";
  -- return if top.frame.permitPluck then "common.OriginContext.PARSERACTION_CONTEXT.makeNewConstructionOrigin(false)"
  -- 	else if willUseOriginCtxRef(top) then newConstructionOriginUsingCtxRef else
  -- 	s"new core.PoriginOriginInfo(null, common.OriginsUtil.SET_AT_CONSTRUCTION_OIT, context.undecorate().wrapInLink(), common.ConsCell.nil, false)";
}
