grammar silver:core;

{-
Ord represents ordering relationships between data.

Laws are based on Haskell's Ord type class:

Transitivity
  if x <= y && y <= z = true, then x <= z = true
Reflexivity
  x <= x = true
Antisymmetry
  if x <= y && y <= x = true, then x == y = true

Note that the following operator interactions are expected to hold:
1. x >= y = y <= x
2. x < y = x <= y && x != y
3. x > y = y < x
4. x < y = compare x y < 0
5. x > y = compare x y > 0
6. x == y = compare x y == 0
7. min(x, y) == if x <= y then x else y = true
8. max(x, y) == if x >= y then x else y = true

Note that (7.) and (8.) do not require min and max to return either of their arguments. The result is merely required to equal one of the arguments in terms of (==).

Minimal complete definition: either compare or <=. Using compare can be more efficient for complex types.
-}
class Eq a => Ord a {
  compare :: (Integer ::= a a) = \ x::a y::a ->
    if x == y then 0 else if x <= y then -1 else 1;
  
  lt :: (Boolean ::= a a) = \ x::a y::a -> compare(x, y) < 0;
  lte :: (Boolean ::= a a) = \ x::a y::a -> compare(x, y) <= 0;
  gt :: (Boolean ::= a a) = \ x::a y::a -> compare(x, y) > 0;
  gte :: (Boolean ::= a a) = \ x::a y::a -> compare(x, y) >= 0;
  
  max :: (a ::= a a) = \ x::a y::a -> if x <= y then y else x;
  min :: (a ::= a a) = \ x::a y::a -> if x >= y then y else x;
}

instance Ord Integer {
  compare = \ x::Integer y::Integer -> x - y;
  lt = ltInteger;
  lte = lteInteger;
  gt = gtInteger;
  gte = gteInteger;
}
function ltInteger
Boolean ::= x::Integer y::Integer
{
  return error("Foreign function");
} foreign {
  "java" : return "(%x% < (int)%y%)";
}
function lteInteger
Boolean ::= x::Integer y::Integer
{
  return error("Foreign function");
} foreign {
  "java" : return "(%x% <= (int)%y%)";
}
function gtInteger
Boolean ::= x::Integer y::Integer
{
  return error("Foreign function");
} foreign {
  "java" : return "(%x% > (int)%y%)";
}
function gteInteger
Boolean ::= x::Integer y::Integer
{
  return error("Foreign function");
} foreign {
  "java" : return "(%x% >= (int)%y%)";
}

instance Ord Float {
  lt = ltFloat;
  lte = lteFloat;
  gt = gtFloat;
  gte = gteFloat;
}
function ltFloat
Boolean ::= x::Float y::Float
{
  return error("Foreign function");
} foreign {
  "java" : return "(%x% < (float)%y%)";
}
function lteFloat
Boolean ::= x::Float y::Float
{
  return error("Foreign function");
} foreign {
  "java" : return "(%x% <= (float)%y%)";
}
function gtFloat
Boolean ::= x::Float y::Float
{
  return error("Foreign function");
} foreign {
  "java" : return "(%x% > (float)%y%)";
}
function gteFloat
Boolean ::= x::Float y::Float
{
  return error("Foreign function");
} foreign {
  "java" : return "(%x% >= (float)%y%)";
}

instance Ord Boolean {
  lt = ltBoolean;
  lte = lteBoolean;
  gt = gtBoolean;
  gte = gteBoolean;
}
function ltBoolean
Boolean ::= x::Boolean y::Boolean
{
  return x == false && y == true;
}
function lteBoolean
Boolean ::= x::Boolean y::Boolean
{
  return x != true || y != false;
}
function gtBoolean
Boolean ::= x::Boolean y::Boolean
{
  return x == true && y == false;
}
function gteBoolean
Boolean ::= x::Boolean y::Boolean
{
  return x != true || y != false;
}

instance Ord String {
  compare = compareString;
  lt = ltString;
  lte = lteString;
  gt = gtString;
  gte = gteString;
}
function compareString
Integer ::= l::String  r::String
{
  return if l <= r then if l == r then 0 else -1 else 1;
} foreign {
  "java" : return "Integer.valueOf(%l%.toString().compareTo(%r%.toString()))";
}

function ltString
Boolean ::= x::String y::String
{
  return error("Foreign function");
} foreign {
  "java" : return "(%x%.toString().compareTo(%y%.toString()) < 0)";
}
function lteString
Boolean ::= x::String y::String
{
  return error("Foreign function");
} foreign {
  "java" : return "(%x%.toString().compareTo(%y%.toString()) <= 0)";
}
function gtString
Boolean ::= x::String y::String
{
  return error("Foreign function");
} foreign {
  "java" : return "(%x%.toString().compareTo(%y%.toString()) > 0)";
}
function gteString
Boolean ::= x::String y::String
{
  return error("Foreign function");
} foreign {
  "java" : return "(%x%.toString().compareTo(%y%.toString()) >= 0)";
}

instance Ord TerminalId {
  compare = compareTerminalId;
  lt = ltTerminalId;
  lte = lteTerminalId;
  gt = gtTerminalId;
  gte = gteTerminalId;
}
function compareTerminalId
Integer ::= x::TerminalId y::TerminalId
{
  return error("Foreign function");
} foreign {
  "java" : return "(%x% - (int)%y%)";
}
function ltTerminalId
Boolean ::= x::TerminalId y::TerminalId
{
  return error("Foreign function");
} foreign {
  "java" : return "(%x% < (int)%y%)";
}
function lteTerminalId
Boolean ::= x::TerminalId y::TerminalId
{
  return error("Foreign function");
} foreign {
  "java" : return "(%x% <= (int)%y%)";
}
function gtTerminalId
Boolean ::= x::TerminalId y::TerminalId
{
  return error("Foreign function");
} foreign {
  "java" : return "(%x% > (int)%y%)";
}
function gteTerminalId
Boolean ::= x::TerminalId y::TerminalId
{
  return error("Foreign function");
} foreign {
  "java" : return "(%x% >= (int)%y%)";
}

instance Ord a => Ord [a] {
  lte = \ x::[a] y::[a] ->
    case x, y of
    | h1::t1, h2::t2 -> h1 <= h2 && t1 <= t2
    | [], _ -> true
    | _, _ -> false
    end;
}

instance Ord a => Ord Maybe<a> {
  lte = \ x::Maybe<a> y::Maybe<a> ->
    case x, y of
    | just(w), just(z) -> w <= z
    | nothing(), _ -> true
    | _, _ -> false
    end;
}

instance Ord a, Ord b => Ord Pair<a b> {
  lte = \ x::Pair<a b> y::Pair<a b> -> x.fst <= y.fst && x.snd <= y.snd;
}

instance Ord a, Ord b => Ord Either<a b> {
  compare = \ x::Either<a b> y::Either<a b> ->
    case x, y of
    | left(w), left(z) -> compare(w, z)
    | left(_), right(_) -> -1
    | right(_), left(_) -> 1
    | right(w), right(z) -> compare(w, z)
    end;
}

instance Ord Unit {
  compare = \ Unit Unit -> 0;
}

instance Ord Location {
  lte = \ l1::Location l2::Location ->
    -- TODO: We could probably just compare based on filename and index
    -- For the moment, though, use line & column instead.
    l1.filename < l2.filename || (l1.filename == l2.filename &&
    (l1.line < l2.line || (l1.line == l2.line &&
    (l1.column < l2.column))));
}

