grammar silver:core;

@{-
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

ordering attribute compareKey, compare with compareTo;

instance attribute compareTo<a {}> occurs on a,
         attribute isEqual {compareTo} occurs on a, -- Needed by Eq superclass
         --attribute compareKey {compareTo} occurs on a, -- Typically present, but not needed as a constraint
         attribute compare {compareTo} occurs on a
         => Ord a {
  compare = \ x::a y::a -> decorate x with {compareTo = decorate y with {};}.compare;
}

instance typeError "Comparison is not supported for Decorated types"
         => Ord Decorated a with i {
  compare = error("type error");
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
