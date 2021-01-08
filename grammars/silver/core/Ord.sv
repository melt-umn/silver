grammar silver:core;

nonterminal Ordering;
production ordLT top::Ordering ::= {}
production ordEQ top::Ordering ::= {}
production ordGT top::Ordering ::= {}

class Eq a => Ord a {
  compare :: (Ordering ::= a a) = \ x::a y::a ->
    if x == y then ordEQ() else if x <= y then ordLT() else ordGT();
  
  lt :: (Boolean ::= a a) = \ x::a y::a ->
    case compare(x, y) of ordLT() -> true | _ -> false end;
  lte :: (Boolean ::= a a) = \ x::a y::a ->
    case compare(x, y) of ordGT() -> false | _ -> true end;
  gt :: (Boolean ::= a a) = \ x::a y::a ->
    case compare(x, y) of ordGT() -> true | _ -> false end;
  gte :: (Boolean ::= a a) = \ x::a y::a ->
    case compare(x, y) of ordLT() -> false | _ -> true end;
  
  max :: (a ::= a a) = \ x::a y::a -> if x <= y then y else x;
  min :: (a ::= a a) = \ x::a y::a -> if x <= y then y else x;
}

instance Ord Integer {
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
  lt = ltString;
  lte = lteString;
  gt = gtString;
  gte = gteString;
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
  lt = ltTerminalId;
  lte = lteTerminalId;
  gt = gtTerminalId;
  gte = gteTerminalId;
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
