grammar flow;

synthesized attribute errors1::Boolean;
synthesized attribute errors2::Boolean;

nonterminal UDExpr with env1, env2, errors1, errors2;
flowtype UDExpr = forward {env1, env2}, decorate {env1, env2}, errors1 {env1}, errors2 {env1, env2};

production directOverloadThing
top::UDExpr ::= e::UDExpr
{
  e.env1 = top.env1;
  top.errors2 = e.errors2;
  forwards to shareThing(e);
}

production indirectOverloadThing
top::UDExpr ::= e::UDExpr
{
  e.env1 = top.env1;
  top.errors2 = e.errors2;
  local prod::DispatchOp = if e.errors1 then dispatchThing1 else dispatchThing2;
  forwards to prod(e);
}

production shareThing
top::UDExpr ::= @e::UDExpr
{
  e.env2 = top.env2;
  top.errors1 = e.errors1;
  top.errors2 = !null(e.env1);
}

dispatch DispatchOp = UDExpr ::= @e1::UDExpr;

production dispatchThing1 implements DispatchOp
top::UDExpr ::= @e::UDExpr
{
  e.env2 = top.env2;
  top.errors1 = e.errors1;
  top.errors2 = !null(e.env1);
}

production dispatchThing2 implements DispatchOp
top::UDExpr ::= @e::UDExpr
{
  e.env2 = top.env1;
  top.errors1 = e.errors1;
  top.errors2 = !null(e.env1);
}

production dispatchThing3 implements DispatchOp
top::UDExpr ::= @e::UDExpr i::Integer b::Boolean
{
  e.env2 = if b then [] else top.env2;
  top.errors1 = b;
  top.errors2 = i > 0;
}

global dt3::DispatchOp = dispatchThing3(3, false);

production dispatchThing4 implements DispatchOp
top::UDExpr ::= @e::UDExpr
{
  forwards to dispatchThing3(e, 42, true);
}

wrongFlowCode "Tree e in production flow:overloadThing2 is shared in multiple places" {
production overloadThing2
top::UDExpr ::= e::UDExpr
{
  local otherRef::UDExpr = @e;
  e.env1 = top.env1;
  forwards to shareThing(e);
}
}

wrongFlowCode "Tree e in production flow:shareThing2 is shared in multiple places" {
production shareThing2
top::UDExpr ::= @e::UDExpr
{
  local otherRef::UDExpr = @e;
  local otherRef2::UDExpr = @e;
}
}

warnCode "Non-dispatch production shareThing has shared children in its signature, and can only be referenced by applying it in the root position of a forward or forward production attribute equation" {
function dispatchFunction
UDExpr ::= e::UDExpr
{
  e.env1 = [];
  return shareThing(e);
}
}

warnCode "Potentially missing inherited override equation for flow:env2 on e; a cycle may exist via its sharing decoration site forward[flow:DispatchOp:e1]" {
production dispatchCycle
top::UDExpr ::= e::UDExpr
{
  e.env1 = top.env1;
  local prod::DispatchOp = if null(e.env2) then dispatchThing1 else dispatchThing2;
  forwards to prod(e);
}
}

dispatch DispatchOp2 = UDExpr ::= e::UDExpr;

-- An inherited collection attribute, to check contributions in extension implementations.
inherited attribute icoll::[String] with ++;
attribute icoll occurs on UDExpr;
-- An implementation in an extension can supply e.env1 itself and then forward to doimpl1 or doimpl2.
-- The extension's equation for e.env1 may depend on anything in the forward flow type, and overrides the ones here.
warnCode "Synthesized equation errors1 exceeds flow type with dependencies on flow:env2" {
production doimpl1 implements DispatchOp2
top::UDExpr ::= e::UDExpr
{
  e.env1 = top.env1;
  e.env2 = top.env2;
  top.errors1 = e.errors1;
  top.errors2 = !null(e.env1);
}
}
warnCode "Synthesized equation errors1 exceeds flow type with dependencies on flow:env2" {
production doimpl2 implements DispatchOp2
top::UDExpr ::= e::UDExpr
{
  e.env1 = top.env1;
  e.env2 = top.env1;
  top.errors1 = e.errors1;
  top.errors2 = !null(e.env1);
}
}

-- An application of a dispatch signature must allow for implementations in independent extensions.
-- The attributes of such an implementation may depend on anything in their flow types.
-- Here errors2 needs only env1 in the implementations above.
warnCode "Access of synthesized attribute errors2 on w requires missing inherited attribute(s) flow:env2" {
production dispatchOp2SupplyLess
top::UDExpr ::= e::UDExpr
{
  local prod::DispatchOp2 = doimpl1;
  local w::UDExpr = prod(@e);
  w.env1 = top.env1;
  top.errors1 = false;
  top.errors2 = w.errors2;
}
}

warnCode "Potentially missing inherited override equation for flow:env2 on e; a cycle may exist via its sharing decoration site forward[flow:DispatchOp2:e]" {
production dispatchCycle2
top::UDExpr ::= e::UDExpr
{
  local prod::DispatchOp2 = if null(e.env2) then doimpl1 else doimpl2;
  forwards to prod(@e);
}
}

-- The same for a child shared through the signature, which was decorated before this production got it.
warnCode "Potentially missing inherited override equation for flow:env2 on e; a cycle may exist via its sharing decoration site forward[flow:DispatchOp2:e]" {
production dispatchCycleSig
top::UDExpr ::= @e::UDExpr
{
  top.errors1 = false;
  top.errors2 = false;
  local prod::DispatchOp2 = if null(e.env2) then doimpl1 else doimpl2;
  forwards to prod(@e);
}
}
production dispatchCycleSigSite
top::UDExpr ::= x::UDExpr
{
  x.env1 = top.env1;
  forwards to dispatchCycleSig(x);
}

-- A dispatch signature whose implementation uses nothing of its argument.
dispatch DispatchOp3 = UDExpr ::= @e::UDExpr;
production doimpl3 implements DispatchOp3
top::UDExpr ::= @e::UDExpr
{
  top.errors1 = false;
  top.errors2 = false;
}
-- In any implementation, errors1 needs only env1.
noWarnCode "implicit copy equation" {
production applyOp3
top::UDExpr ::= e::UDExpr
{
  e.env1 = top.env1;
  local prod::DispatchOp3 = doimpl3;
  forwards to prod(e);
}
}

-- An implementation not known here may build its forward from the value of any argument.
dispatch DispatchOp4 = UDExpr ::= b::Boolean;
production doimpl4 implements DispatchOp4
top::UDExpr ::= b::Boolean
{
  top.errors1 = false;
  top.errors2 = false;
}
warnCode "the implicit copy equation for flow:errors1 (due to forwarding) would exceed the attribute's flow type with dependencies on flow:env2" {
production applyOp4
top::UDExpr ::=
{
  local prod::DispatchOp4 = doimpl4;
  forwards to prod(null(top.env2));
}
}

-- Matching on an application needs its forward.  An implementation not known here may build that forward from
-- anything in the forward flow type.
warnCode "Synthesized equation errors1 exceeds flow type with dependencies on flow:env2" {
production matchApplication
top::UDExpr ::= e::UDExpr
{
  local prod::DispatchOp2 = doimpl1;
  local w::UDExpr = prod(@e);
  w.env1 = top.env1;
  w.env2 = top.env2;
  top.errors1 = case w of doimpl1(_) -> true | _ -> false end;
  top.errors2 = false;
}
}

-- An implementation not known here may access the attributes of an argument.  These can depend on how the argument
-- was built (see ext.)
production valueLit
top::UDExpr ::= f::Boolean
{
  top.errors1 = f;
  top.errors2 = f;
}
dispatch DispatchOp5 = UDExpr ::= b::UDExpr;
production doimpl5 implements DispatchOp5
top::UDExpr ::= b::UDExpr
{
  top.errors1 = false;
  top.errors2 = false;
}
-- The same for a local shared as a signature-shared child.
dispatch DispatchOp6 = UDExpr ::= @e::UDExpr;
production doimpl6 implements DispatchOp6
top::UDExpr ::= @e::UDExpr
{
  top.errors1 = false;
  top.errors2 = false;
}
warnCode "the implicit copy equation for flow:errors1 (due to forwarding) would exceed the attribute's flow type with dependencies on flow:env2" {
production applyOp6Local
top::UDExpr ::=
{
  local x::UDExpr = valueLit(null(top.env2));
  x.env1 = top.env1;
  x.env2 = top.env2;
  local prod::DispatchOp6 = doimpl6;
  forwards to prod(x);
}
}
