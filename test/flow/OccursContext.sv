grammar flow;

attribute isEqual, isEqualTo occurs on Expr;
flowtype isEqual {isEqualTo} on Expr;
aspect production zero top::Expr ::=
{ propagate isEqual, isEqualTo; }
aspect production succ top::Expr ::= _
{ propagate isEqual, isEqualTo; }
aspect default production
top::Expr ::=
{ top.isEqual = false; }

function isEqual
attribute isEqualTo<a> occurs on a,
attribute isEqual {isEqualTo} occurs on a =>
Boolean ::= x::a y::a
{
  x.isEqualTo = y;
  return x.isEqual;
}

warnCode "Equation has transitive dependency on child x's inherited attribute for flow:env1 but this equation appears to be missing." {
function isEqualBad
attribute isEqualTo<a> occurs on a,
attribute isEqual {isEqualTo, env1} occurs on a =>
Boolean ::= x::a y::a
{
  x.isEqualTo = y;
  return x.isEqual;
}
}

global isEqualGlobal ::
  attribute isEqualTo<a> occurs on a,
  attribute isEqual {isEqualTo} occurs on a =>
  (Boolean ::= a a) = \ x::a y::a ->
    decorate x with {isEqualTo = y;}.isEqual;

warnCode "Decoration requires inherited attribute for flow:env1." {
global isEqualGlobalBad ::
  attribute isEqualTo<a> occurs on a,
  attribute isEqual {isEqualTo, env1} occurs on a =>
  (Boolean ::= a a) = \ x::a y::a ->
    decorate x with {isEqualTo = y;}.isEqual;
}

class Equal1 a {
  isEqual1 :: (Boolean ::= a a);
}

warnCode "Decoration requires inherited attribute for flow:env1." {
instance attribute isEqualTo<a> occurs on a,
         attribute isEqual {isEqualTo, env1} occurs on a =>
         Equal1 a {
  isEqual1 = \ x::a y::a ->
    decorate x with {isEqualTo = y;}.isEqual;
}
}

warnCode "Decoration requires inherited attribute for flow:env1." {
class attribute isEqualTo<a> occurs on a,
      attribute isEqual {isEqualTo, env1} occurs on a =>
      Equal2 a {
  isEqual2 :: (Boolean ::= a a) = \ x::a y::a ->
    decorate x with {isEqualTo = y;}.isEqual;
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

warnCode "Attribute section (.value) requires attributes not known to be on 'Decorated a with {flow:env1}': flow:env2" {
production valueThing5Bad
attribute env1 occurs on a,
attribute value {env1, env2} occurs on a =>
top::Expr ::= x::Decorated a with {env1}
{
  top.value = head(map((.value), [x, x]));
}
}
