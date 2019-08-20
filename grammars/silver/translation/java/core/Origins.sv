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
  local ty :: Type = finalType(top);
  return case ty of
    | nonterminalType(fn, _) ->
              if nonterminalWantsTracking(fn, top.env)
              then makeOriginContextRef(top)++".makeNewConstructionOrigin(false)" else "null"
    | _ -> "null"
  end;
}

function nonterminalWantsTracking
Boolean ::= fn::String env::Decorated Env
{
  return case getTypeDclAll(fn, env) of
    | ntDcl(_, _, _, _, _, _, t)::_ -> t
    | [] -> unsafeTracePrint(true, "Origins Warn: no ntDcl for type "++fn++"\n")--error("Internal Error: no ntDcl for type "++fn)
  end;
}

function wrapAccessWithOT
String ::= top::Decorated Expr expr::String
{
  local ty :: Type = finalType(top);

  local polyCopy   :: String = s"((${ty.transType})${makeOriginContextRef(top)}.attrAccessCopyPoly(${expr}))";
  local directCopy :: String = s"((${ty.transType})${makeOriginContextRef(top)}.attrAccessCopy((common.Node)${expr}))";
  local noop       :: String = s"((${ty.transType})${expr})";

  return if ty.transType == "Object" then polyCopy else
          case ty of
            | nonterminalType(fn, _) ->
              if nonterminalWantsTracking(fn, top.env) then directCopy else noop
            | _ -> noop
          end;
  -- return if ty.transType == "Object"
  --   then s"((${ty.transType})${makeOriginContextRef(top)}.attrAccessCopyPoly(${expr}))"
  --   else if !ty.isPrimitiveForDuplicate
  --   then s"((${ty.transType})${makeOriginContextRef(top)}.attrAccessCopy((common.Node)${expr}))"
  --   else s"((${ty.transType})${expr})";

  -- The extra (common.Node) cast in the non-generic non-primitive case is sometimes required for reasons I don't fully understand.

}