grammar silver:core;

@{-
Eq represents equality/inequality relationships between data.

Laws are based on Haskell's Eq type class:

Reflexivity
  x == x = true
Symmetry
  x == y = y == x
Transitivity
  if x == y && y == z = true, then x == z = true
Substitutivity
  if x == y = true and f is a "public" function whose return type is an instance of Eq, then f x == f y = true
Negation
  x != y = !(x == y)

Minimal complete definition: either == or !=.
-}
class Eq a {
  eq :: (Boolean ::= a a) = \ x::a y::a -> !(x != y);
  neq :: (Boolean ::= a a) = \ x::a y::a -> !(x == y);
}

destruct attribute compareTo;
equality attribute isEqual with compareTo;

instance attribute compareTo<a {}> occurs on a,
         attribute isEqual {compareTo} occurs on a
         => Eq a {
  eq = \ x::a y::a -> decorate x with {compareTo = decorate y with {};}.isEqual;
}

instance typeError "Equality is not supported for Decorated types"
         => Eq Decorated a with i {
  eq = error("type error");
}

instance Eq Integer {
  eq = eqInteger;
  neq = neqInteger;
}

function eqInteger
Boolean ::= x::Integer y::Integer
{
  return error("Foreign function");
} foreign {
  "java" : return "(%x% == (int)%y%)";
}

function neqInteger
Boolean ::= x::Integer y::Integer
{
  return error("Foreign function");
} foreign {
  "java" : return "(%x% != (int)%y%)";
}

instance Eq Float {
  eq = eqFloat;
  neq = neqFloat;
}

function eqFloat
Boolean ::= x::Float y::Float
{
  return error("Foreign function");
} foreign {
  "java" : return "(%x% == (float)%y%)";
}

function neqFloat
Boolean ::= x::Float y::Float
{
  return error("Foreign function");
} foreign {
  "java" : return "(%x% != (float)%y%)";
}

instance Eq Boolean {
  eq = eqBoolean;
  neq = neqBoolean;
}

function eqBoolean
Boolean ::= x::Boolean y::Boolean
{
  return error("Foreign function");
} foreign {
  "java" : return "(%x% == (boolean)%y%)";
}

function neqBoolean
Boolean ::= x::Boolean y::Boolean
{
  return error("Foreign function");
} foreign {
  "java" : return "(%x% != (boolean)%y%)";
}

instance Eq String {
  eq = eqString;
  neq = neqString;
}

function eqString
Boolean ::= x::String y::String
{
  return error("Foreign function");
} foreign {
  "java" : return "(%x%.equals(%y%))";
}

function neqString
Boolean ::= x::String y::String
{
  return error("Foreign function");
} foreign {
  "java" : return "(!%x%.equals(%y%))";
}

instance Eq TerminalId {
  eq = eqTerminalId;
  neq = neqTerminalId;
}

function eqTerminalId
Boolean ::= x::TerminalId y::TerminalId
{
  return error("Foreign function");
} foreign {
  "java" : return "(%x% == (int)%y%)";
}

function neqTerminalId
Boolean ::= x::TerminalId y::TerminalId
{
  return error("Foreign function");
} foreign {
  "java" : return "(%x% != (int)%y%)";
}

instance Eq ByteArray {
  eq = eqByteArray;
}

function eqByteArray
Boolean ::= x::ByteArray  y::ByteArray
{
  return error("Foreign function");
} foreign {
  "java" : return "java.util.Arrays.equals(%x%, %y%)";
}

@{-
  - Compute the fixed point of a function by repeatedly applying it
  - until its result remains constant.
  -}
function fixpoint
Eq a => a ::= f::(a ::= a) x::a
{
  local fx :: a = f(x);
  return if fx == x then fx else fixpoint(f, fx);
}
