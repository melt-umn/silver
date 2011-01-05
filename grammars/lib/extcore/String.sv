grammar lib:extcore ;

{- We have a need for various "toString" functions.

   EVW proposes that we name functions by a common prefix indicating
   the computation followed by the name of the type of the values
   involved.

   For printing, toStringTYPE - toStringInteger, toStringTypeExpr, etc.

   We need functions for many of the built-in types. These are included below.
-}

-- toString functions
function toStringFromInteger
String ::= v::Integer
{ return toString(v) ; }

function toStringFromFloat
String ::= v::Float
{ return toString(v) ; }

function toStringFromBoolean
String ::= v::Boolean
{ return if v then "true" else "false" ; }

function toStringFromString
String ::= v::String
{ return v ; }

function toStringFromList
String ::= toStr::Function(String ::= a) xs::[a]
{ return "[" ++ toStringFromListHelper(toStr, xs) ++ "]" ; }

function toStringFromListHelper
String ::= toStr::Function(String ::= a) xs::[a]
{ return if  null(xs) 
         then ""
         else toStr(head(xs)) ++
              if null(tail(xs))
              then ""
              else ", " ++ toStringFromListHelper(toStr, tail(xs)) ;
}
