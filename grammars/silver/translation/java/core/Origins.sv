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
}

function wrapAccessWithOT
String ::= top::Decorated Expr expr::String
{
  local ty :: Type = finalType(top);
  return if ty.transType == "Object"
    then s"((${ty.transType})${makeOriginContextRef(top)}.attrAccessCopyPoly(${expr}))"
    else if !ty.isPrimitiveForDuplicate
    then s"((${ty.transType})${makeOriginContextRef(top)}.attrAccessCopy((common.Node)${expr}))"
    else s"((${ty.transType})${expr})";

  -- The extra (common.Node) cast in the non-generic non-primitive case is sometimes required for reasons I don't fully understand.

}