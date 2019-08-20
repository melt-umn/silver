import silver:definition:origins;

synthesized attribute contextRef :: String occurs on ContextOriginInfoSource;

aspect production useContextLhsAndRules
top::ContextOriginInfoSource ::=
{
  top.contextRef = "new common.OriginContext(context.undecorate(), new java.util.ArrayList<core.NOriginNote>())";
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
  -- As is this is limited because the ntDcl may not be in scope, even though a production producing it is.
  -- For example, if you do `import silver:langutil only err` you'll be able to construct `err`s but not have
  -- `Message` in scope to see if it wants origins. As a result we are liberal by default and send stuff origins
  -- unless we specifically know it _dosen't_ want it. If it gets it and didn't want it it simply throws it away,
  -- and as a result we only get a speed hit as opposed to a (possibly fatal) memory hit.

  return case getTypeDclAll(fn, env) of
    | ntDcl(_, _, _, _, _, _, t)::_ -> t
    | [] -> unsafeTracePrint(true, "Origins Warn: no ntDcl for type "++fn++"\n")--error("Internal Error: no ntDcl for type "++fn)
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
  --  - directCopy is the fastpath for things we know want OI (or at least, things we know are Nodes and don't know don't
  --     want OI - see limitations with scope lookup.)
  --  - noop is the "fastpath" where we do nothing, either because we know it's a Node and it dosen't want OI, or because
  --     we know it's a primitive
  --  - polyCopy is the slowpath that uses java instanceof to check if it's a node, and then send OI if it is

  local polyCopy   :: String = s"((${ty.transType})${makeOriginContextRef(top)}.attrAccessCopyPoly(${expr}))";
  local directCopy :: String = s"((${ty.transType})${makeOriginContextRef(top)}.attrAccessCopy((common.Node)${expr}))";
  local noop       :: String = s"((${ty.transType})${expr})";

  return if ty.transType == "Object" then polyCopy else
          case ty of
            | nonterminalType(fn, _) ->
              if nonterminalWantsTracking(fn, top.env) then directCopy else noop
            | _ -> noop
          end;
  -- The extra (common.Node) cast in the non-generic non-primitive case is sometimes required for reasons I don't fully understand.

}

function wrapNewWithOT
String ::= top::Decorated Expr expr::String
{
  local ty :: Type = finalType(top);

  -- Similar logic to wrapAccessWithOT. Since nonterminalWantsTracking is a liberal estimate it's possible
  --  calling this is a waste of time (in which case .duplicate will simple be a noop.)

  local directDup :: String = s"${expr}.duplicate(${makeOriginContextRef(top)}.lhs, common.ConsCell.nil)";

  return case ty of
            | nonterminalType(fn, _) ->
              if nonterminalWantsTracking(fn, top.env) then directDup else expr
            | _ -> expr
          end;
}