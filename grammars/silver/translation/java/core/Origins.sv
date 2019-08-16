function willUseOriginCtxRef
Boolean ::= top::Decorated Expr
{
	return top.frame.permitReturn;
}

function makeOriginContextRef
String ::= top::Decorated Expr --need .frame anno
{
  return if willUseOriginCtxRef(top) then "originCtx" else
    s"new common.OriginContext(context.undecorate(), new java.util.ArrayList<core.NOriginLink>())";
  -- ORIGINS TODO: rules ref from top.originRules
}

global newConstructionOriginUsingCtxRef :: String =
	"originCtx.makeNewConstructionOrigin(false)";

function makeNewConstructionOrigin
String ::= top::Decorated Expr --need .frame anno
{
  -- ORIGINS TODO: rules ref from top.originRules, er from top.isRoot
  return if top.frame.permitPluck then "common.OriginContext.PARSERACTION_CONTEXT.makeNewConstructionOrigin(false)"
  	else if willUseOriginCtxRef(top) then newConstructionOriginUsingCtxRef else
  	s"new core.PoriginOriginInfo(null, new core.PsetAtConstructionOIT(null), context.undecorate().wrapInLink(), common.ConsCell.nil, false)";
}
