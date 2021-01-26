grammar silver:core;

{-
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

equality attribute isEqualTo, isEqual;
{- TODO: once we have occurence constraints...
instance isEqual {isEqualTo} occurs on a => Eq a {
  eq = \ x::a y::a -> decorate x with {isEqualTo = y;}.isEqual;
}
Do something similar for Ord.
-}

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

instance Eq a => Eq [a] {
  eq = \ x::[a] y::[a] -> length(x) == length(y) && all(zipWith(eq, x, y));
  neq = \ x::[a] y::[a] -> length(x) != length(y) || any(zipWith(neq, x, y));
}

instance Eq a => Eq Maybe<a> {
  eq = \ x::Maybe<a> y::Maybe<a> ->
    case x, y of
    | just(w), just(z) -> w == z
    | nothing(), nothing() -> true
    | _, _ -> false
    end;
}

instance Eq a, Eq b => Eq Pair<a b> {
  eq = \ x::Pair<a b> y::Pair<a b> -> x.fst == y.fst && x.snd == y.snd;
  neq = \ x::Pair<a b> y::Pair<a b> -> x.fst != y.fst || x.snd != y.snd;
}

instance Eq a, Eq b => Eq Either<a b> {
  eq = \ x::Either<a b> y::Either<a b> ->
    case x, y of
    | left(w), left(z) -> w == z
    | right(w), right(z) -> w == z
    | _, _ -> false
    end;
}

instance Eq Unit {
  eq = \ Unit Unit -> true;
}

instance Eq Location {
  eq = \ l1::Location l2::Location ->
    -- TODO: We could probably just compare based on filename and index
    -- For the moment, though, use line & column instead.
    l1.filename == l2.filename &&
    l1.line == l2.line &&
    l1.column == l2.column;
}
