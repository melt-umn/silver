import silver:definition:origins;
import silver:util:cmdargs only CmdArgs;
import silver:driver;

-- Information on how to compute origins for stuff constructed/modified in different block contexts
-- How do we get an OriginContext representing this?
synthesized attribute contextRef :: String occurs on ContextOriginInfoSource;

-- Given a string holding java code to produce a NOriginRule[] array, produce a string holding java code
--  to produce a OriginContext for this context adding those rules (faster than getting contextRef and
--  adding rules)
synthesized attribute contextRefAddingRules :: (String ::= String) occurs on ContextOriginInfoSource;

-- Is this context something that's `interesting` (= er flag = isNewConstruction) all the time?
-- Only `false` for cases where we are in a production body
synthesized attribute alwaysConsideredInteresting :: Boolean occurs on ContextOriginInfoSource;

aspect production useContextLhsAndRules
top::ContextOriginInfoSource ::=
{
  top.contextRef = "new common.OriginContext(context.undecorate(), new java.util.ArrayList<core.NOriginNote>())";
  top.contextRefAddingRules = (\x::String -> s"new common.OriginContext(context.undecorate(), common.OriginsUtil.arrayListOfArray(${x}))");
  top.alwaysConsideredInteresting = false;
}

aspect production useRuntimePassedInfo
top::ContextOriginInfoSource ::=
{
  top.contextRef = "originCtx";
  top.contextRefAddingRules = (\x::String -> s"new common.OriginContext(${top.contextRef}, ${x})");
  top.alwaysConsideredInteresting = true;
}

aspect production useBogusInfo
top::ContextOriginInfoSource ::= name::String
{
  top.contextRef = "common.OriginContext."++name;
  top.contextRefAddingRules = (\x::String -> s"new common.OriginContext(${top.contextRef}, ${x})");
  top.alwaysConsideredInteresting = true;
}

function makeOriginContextRef
String ::= top::Decorated Expr --need .frame anno
{
  local rulesTrans :: [String] = (if top.config.tracingOrigins then [locRule] else []) ++ map((.translation), top.originRules);
  local locRule :: String = s"new core.PoriginDbgNote(null, new common.StringCatter(\"${substitute("\"", "\\\"", hackUnparse(top.location))}\"))";

  return if top.config.noOrigins then "null" else 
    if length(rulesTrans)==0 then top.frame.originsContextSource.contextRef else
      top.frame.originsContextSource.contextRefAddingRules(s"new core.NOriginNote[]{${implode(", ", rulesTrans)}}");
}

global newConstructionOriginUsingCtxRef :: String =
	"originCtx.makeNewConstructionOrigin(true)";

function makeNewConstructionOrigin
String ::= top::Decorated Expr  inInteresting::Boolean --need .frame anno
{
  local ty :: Type = finalType(top);
  local interesting :: Boolean = top.frame.originsContextSource.alwaysConsideredInteresting || !top.isRoot || inInteresting;

  return if typeWantsTracking(ty, top.config, top.env) then makeOriginContextRef(top)++s".makeNewConstructionOrigin(${if interesting then "true" else "false"})" else "null";
}

function typeWantsTracking
Boolean ::= ty::Type conf::Decorated CmdArgs env::Decorated Env
{
  return if conf.noOrigins then false else
            case ty of
            | nonterminalType(fn, _, tracked) -> conf.forceOrigins || tracked
            | _ -> false
            end;
}

function wrapAccessWithOT
String ::= top::Decorated Expr expr::String
{
  local ty :: Type = finalType(top);

  -- The complexity here is needed because of silver generics. A nonterminal like Maybe<a> is monomorphized in such a way
  -- that the parameter type is Object. As a result we can't tell, when it's possible that we are doing something on a type
  -- that may be a parameter, if it will want OI or not (or for that matter, if it's even a subclass of Node or if it's a
  -- boxed primitive). As a result our paths are:
  --  - directCopy is the fastpath for things we know want OI
  --  - noop is the "fastpath" where we do nothing, either because we know it's a Node and it dosen't want OI, or because
  --     we know it's a primitive
  --  - polyCopy is the slowpath that uses java instanceof to check if it's a node, and then send OI if it is

  local polyCopy   :: String = s"((${ty.transType})${makeOriginContextRef(top)}.attrAccessCopyPoly(${expr}))";
  local directCopy :: String = s"((${ty.transType})${makeOriginContextRef(top)}.attrAccessCopy((common.Node)${expr}))";
  local noop       :: String = s"((${ty.transType})${expr})";

  local impl :: String = if ty.transType == "Object" then polyCopy else
          (if typeWantsTracking(ty, top.config, top.env) then directCopy else noop);

  return if (top.config.noRedex || top.config.noOrigins) then noop else impl;
  -- The extra (common.Node) cast in the non-generic non-primitive case is sometimes required for reasons I don't fully understand.

}

function wrapNewWithOT
String ::= top::Decorated Expr expr::String
{
  local ty :: Type = finalType(top);

  local directDup :: String = s"${expr}.duplicate(${makeOriginContextRef(top)})";

  return if ((!top.config.noRedex) && typeWantsTracking(ty, top.config, top.env)) then directDup else expr;
}