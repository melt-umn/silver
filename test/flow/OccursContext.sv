grammar flow;

attribute isEqual, compareTo occurs on Expr;
flowtype isEqual {compareTo} on Expr;
aspect production zero top::Expr ::=
{ propagate isEqual, compareTo; }
aspect production succ top::Expr ::= _
{ propagate isEqual, compareTo; }
aspect default production
top::Expr ::=
{ top.isEqual = false; }

function isEqual
attribute compareTo<a {}> occurs on a,
attribute isEqual {compareTo} occurs on a =>
Boolean ::= x::a y::a
{
  x.compareTo = y;
  return x.isEqual;
}

warnCode "Equation has transitive dependency on child x's inherited attribute for flow:env1 but this equation appears to be missing." {
function isEqualBad
attribute compareTo<a {}> occurs on a,
attribute isEqual {compareTo, env1} occurs on a =>
Boolean ::= x::a y::a
{
  x.compareTo = y;
  return x.isEqual;
}
}

global isEqualGlobal ::
  attribute compareTo<a {}> occurs on a,
  attribute isEqual {compareTo} occurs on a =>
  (Boolean ::= a a) = \ x::a y::a ->
    decorate x with {compareTo = decorate y with {};}.isEqual;

warnCode "Decoration requires inherited attribute for flow:env1." {
global isEqualGlobalBad ::
  attribute compareTo<a {}> occurs on a,
  attribute isEqual {compareTo, env1} occurs on a =>
  (Boolean ::= a a) = \ x::a y::a ->
    decorate x with {compareTo = decorate y with {};}.isEqual;
}

function isEqualTooSmall
attribute compareTo<a {}> occurs on a,
attribute isEqual {} occurs on a =>
Boolean ::= x::a y::a
{
  x.compareTo = y;
  return x.isEqual;
}

warnCode "The instance for attribute silver:core:isEqual {} occurs on flow:Expr (arising from the use of isEqualTooSmall) has a flow type exceeding the constraint with dependencies on silver:core:compareTo" {
  global isEqualTooSmallErr::Boolean = isEqualTooSmall(zero(), zero());
}

warnCode "The instance for attribute silver:core:isEqual {} occurs on Decorated a with {} (arising from the use of isEqualTooSmall) depends on an unbounded set of inherited attributes" {
function isEqualUnboundedError
attribute compareTo<a {}> occurs on a,
attribute isEqual i occurs on a =>
Boolean ::= x::a y::a
{
  return isEqualTooSmall(x, y);
}
}

class Equal1 a {
  isEqual1 :: (Boolean ::= a a);
}

warnCode "Decoration requires inherited attribute for flow:env1." {
instance attribute compareTo<a {}> occurs on a,
         attribute isEqual {compareTo, env1} occurs on a =>
         Equal1 a {
  isEqual1 = \ x::a y::a ->
    decorate x with {compareTo = decorate y with {};}.isEqual;
}
}

warnCode "Decoration requires inherited attribute for flow:env1." {
class attribute compareTo<a {}> occurs on a,
      attribute isEqual {compareTo, env1} occurs on a =>
      Equal2 a {
  isEqual2 :: (Boolean ::= a a) = \ x::a y::a ->
    decorate x with {compareTo = decorate y with {};}.isEqual;
}
}

synthesized attribute value::Integer occurs on Expr;
flowtype value {env1} on Expr;
aspect value on Expr of
| zero() -> 0
| succ(e) -> e.value + 1
end;

production valueThing1
attribute env1 occurs on a,
attribute value {env1} occurs on a =>
top::Expr ::= x::a
{
  x.env1 = top.env1;
  top.value = x.value;
}

warnCode "Equation has transitive dependency on child x's inherited attribute for flow:env2 but this equation appears to be missing." {
production valueThing1Bad
attribute env1 occurs on a,
attribute value {env1, env2} occurs on a =>
top::Expr ::= x::a
{
  x.env1 = top.env1;
  top.value = x.value;
}
}

production valueThing2
attribute env1 occurs on a,
attribute env2 occurs on a,
attribute value i occurs on a,
i subset {env1, env2} =>
top::Expr ::= x::a
{
  x.env1 = top.env1;
  x.env2 = top.env1;
  top.value = x.value;
}

warnCode "Access of syn attribute value on x requires missing inherited attributes flow:env2 to be supplied" {
production valueThing2Bad
attribute env1 occurs on a,
attribute env2 occurs on a,
attribute value i occurs on a,
i subset {env1, env2} =>
top::Expr ::= x::a
{
  x.env1 = top.env1;
  top.value = x.value;
}
}

warnCode "Access of value from Decorated a with {} requires an unbounded set of inherited attributes" {
production valueThing3Bad
attribute value i occurs on a =>
top::Expr ::= x::a
{
  top.value = x.value;
}
}

production valueThing4
attribute value i occurs on a, i subset i1 =>
top::Expr ::= x::Decorated a with i1
{
  top.value = x.value;
}

warnCode "Access of value from reference of type Decorated a with i1 requires one of the following sets of inherited attributes not known to be supplied to this reference: i" {
production valueThing4Bad
attribute value i occurs on a =>
top::Expr ::= x::Decorated a with i1
{
  top.value = x.value;
}
}

warnCode "warning: Access of value from reference of type Decorated a with i1 requires one of the following sets of inherited attributes not known to be supplied to this reference: i, i2" {
production valueThing4AlsoBad
attribute value i occurs on a, i subset i2 =>
top::Expr ::= x::Decorated a with i1
{
  top.value = x.value;
}
}

-- Attribute sections
production valueThing5
attribute env1 occurs on a,
attribute value {env1} occurs on a =>
top::Expr ::= x::Decorated a with {env1}
{
  top.value = head(map((.value), [x, x]));
}

warnCode "Access of value from reference of type Decorated a with {flow:env1} requires inherited attributes not known to be supplied to this reference: flow:env2" {
production valueThing5Bad
attribute env1 occurs on a,
attribute value {env1, env2} occurs on a =>
top::Expr ::= x::Decorated a with {env1}
{
  top.value = head(map((.value), [x, x]));
}
}

-- Type vars as flow types in syn occurs constraints can be satisfied by contexts
production valueThing
attribute value i occurs on a =>
top::Expr ::= x::Decorated a with i
{
  top.value = x.value;
}

function callValueThing
attribute value i occurs on a, i subset i1 =>
Expr ::= x::Decorated a with i1
{
  return valueThing(x);
}

warnCode "The instance for attribute flow:value i1 occurs on a (arising from the use of valueThing) exceeds the flow type constraint with dependencies on one of the following sets of inherited attributes: i" {
function callValueThingBad
attribute value i occurs on a =>
Expr ::= x::Decorated a with i1
{
  return valueThing(x);
}
}

-- Specializing the reference set based on the flow type.
function getValuePoly
attribute value i occurs on a =>
Integer ::= x::Decorated a with i
{
  return x.value;
}

function getValueExpr
Integer ::= e::Expr
{
  e.env1 = [];
  return getValuePoly(e);
}