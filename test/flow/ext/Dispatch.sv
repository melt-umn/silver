grammar flow:ext;

-- An implementation's equation for an inherited attribute on a child may depend on anything in the forward flow
-- type, which is {env1, env2} here.  It may also depend on what host-language implementations' equations for that
-- attribute depend on.
warnCode "Inherited override equation for flow:env2 on child e has excess dependencies on flow:icoll" {
production extDispatchInhExceedsHost implements DispatchOp2
top::UDExpr ::= e::UDExpr
{
  e.env2 = if e.errors1 then [] else top.icoll;

  local prod::DispatchOp2 = doimpl1;
  forwards to prod(@e);
}
}

-- An application's equation for e.env2 may depend on anything in the forward flow type.
warnCode "Synthesized equation errors1 exceeds flow type with dependencies on flow:env2" {
production extDispatchSynExceedsHost implements DispatchOp2
top::UDExpr ::= e::UDExpr
{
  top.errors1 = !null(e.env2);

  local prod::DispatchOp2 = doimpl1;
  forwards to prod(@e);
}
}

-- An implementation's override equation for an inherited attribute on its forward may depend on anything in the
-- forward flow type.  The attributes that the implementation copies from the forward can then depend on more than
-- in any host-language implementation, as errors2 does here.  Applications of the dispatch signature allow for this
-- (see dispatchOp2SupplyLess.)
noWarnCode "has excess dependencies" {
production extDispatchFwdInhOverride implements DispatchOp2
top::UDExpr ::= e::UDExpr
{
  top.errors1 = false;
  forward.env1 = top.env2;

  local prod::DispatchOp2 = doimpl1;
  forwards to prod(@e);
}
}

-- An implementation may rely on the inherited attributes that every application in the host language supplies to
-- a child shared through the signature.  Here e.errors1 needs env1, which applyOp3 supplies.
noWarnCode "requires missing inherited attribute" {
production extDispatchFwdUsesArg implements DispatchOp3
top::UDExpr ::= @e::UDExpr
{
  forward.env2 = if e.errors1 then [] else top.env2;
  local prod::DispatchOp3 = doimpl3;
  forwards to prod(e);
}
}

-- Contributions to inherited collection attributes are checked like other inherited equations.
warnCode "Inherited contribution equation for flow:icoll on child e has excess dependencies on flow:icoll" {
production extDispatchInhColExceedsHost implements DispatchOp2
top::UDExpr ::= e::UDExpr
{
  e.icoll := [];
  e.icoll <- if e.errors1 then [] else top.icoll;

  local prod::DispatchOp2 = doimpl1;
  forwards to prod(@e);
}
}

-- An extra child cannot launder dependencies.
warnCode "Inherited override equation for flow:env2 on child e has excess dependencies on flow:icoll" {
production extDispatchInhLaunders implements DispatchOp2
top::UDExpr ::= e::UDExpr y::UDExpr
{
  y.env1 = top.icoll;
  y.env2 = [];
  e.env2 = if y.errors1 then [] else top.env2;
  local prod::DispatchOp2 = doimpl1;
  forwards to prod(@e);
}
}

-- ...nor what a synthesized attribute depends on.
warnCode "Synthesized equation errors1 exceeds flow type with dependencies on flow:icoll" {
production extDispatchSynLaunders implements DispatchOp2
top::UDExpr ::= e::UDExpr y::UDExpr
{
  y.env1 = top.icoll;
  y.env2 = [];
  top.errors1 = y.errors1;
  local prod::DispatchOp2 = doimpl1;
  forwards to prod(@e);
}
}

-- An argument built by applying a production, whose child depends on env2.
warnCode "the implicit copy equation for flow:errors1 (due to forwarding) would exceed the attribute's flow type with dependencies on flow:env2" {
production extApplyOp5Value
top::UDExpr ::=
{
  local prod::DispatchOp5 = doimpl5;
  forwards to prod(valueLit(null(top.env2)));
}
}
noWarnCode "would exceed" {
production extApplyOp5ValueEnv1
top::UDExpr ::=
{
  local prod::DispatchOp5 = doimpl5;
  forwards to prod(valueLit(null(top.env1)));
}
}
