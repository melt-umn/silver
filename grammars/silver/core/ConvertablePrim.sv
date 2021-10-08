grammar silver:core;

@{-
  Primitive types that can be converted into other primitive types.

  This class provides the overloaded toString, toInteger, toFloat and toBoolean functions.
-}
class ConvertablePrim a {
  @{- Convert a value into a String -}
  toString :: (String ::= a);

  @{- Convert a value into an Integer -}
  toInteger :: (Integer ::= a);

  @{- Convert a value into a Float -}
  toFloat :: (Float ::= a);

  @{- Convert a value into a Boolean -}
  toBoolean :: (Boolean ::= a);
}

instance ConvertablePrim String {
  toString = id;
  toInteger = stringToInteger;
  toFloat = stringToFloat;
  toBoolean = stringToBoolean;
}

function stringToInteger
Integer ::= x::String
{
  return error("foreign function");
} foreign {
  "java" : return "Integer.valueOf(%x%.toString())";
}

function stringToFloat
Float ::= x::String
{
  return error("foreign function");
} foreign {
  "java" : return "Float.valueOf(%x%.toString())";
}

function stringToBoolean
Boolean ::= x::String
{
  return error("foreign function");
} foreign {
  "java" : return "Boolean.valueOf(%x%.toString())";
}

instance ConvertablePrim Integer {
  toString = integerToString;
  toInteger = id;
  toFloat = integerToFloat;
  toBoolean = \ x::Integer -> x != 0;
}

function integerToString
String ::= x::Integer
{
  return error("foreign function");
} foreign {
  "java" : return "new common.StringCatter(String.valueOf(%x%))";
}

function integerToFloat
Float ::= x::Integer
{
  return error("foreign function");
} foreign {
  "java" : return "Float.valueOf(((Integer)%x%).floatValue())";
}

instance ConvertablePrim Float {
  toString = floatToString;
  toInteger = floatToInteger;
  toFloat = id;
  toBoolean = \ x::Float -> x != 0.0;
}

function floatToString
String ::= x::Float
{
  return error("foreign function");
} foreign {
  "java" : return "new common.StringCatter(String.valueOf(%x%))";
}

function floatToInteger
Integer ::= x::Float
{
  return error("foreign function");
} foreign {
  "java" : return "Integer.valueOf(((Float)%x%).intValue())";
}

instance ConvertablePrim Boolean {
  toString = \ x::Boolean -> if x then "true" else "false";
  toInteger = \ x::Boolean -> if x then 1 else 0;
  toFloat = \ x::Boolean -> if x then 1.0 else 0.0;
  toBoolean = id;
}
