grammar silver:core;

-- Deprecated functions from lib:extcore, these are currently still required by the testing extension.
-- Should be deleted ASAP as soon as we have an Eq type class.

-- equals functions
function equalsInteger
Boolean ::= a::Integer b::Integer 
{ return a == b ; }

function equalsFloat
Boolean ::= a::Float b::Float 
{ return a == b ; }

function equalsString
Boolean ::= a::String b::String 
{ return a == b ; }

function equalsBoolean
Boolean ::= a::Boolean b::Boolean 
{ return a == b ; }

function equalsList
Boolean ::= eq::(Boolean ::= a a) l1::[a] l2::[a]
{ return if   null(l1)
         then null(l2)
         else !null(l2) &&
              eq(head(l1), head(l2)) && 
              equalsList(eq, tail(l1), tail(l2)) ;
}

-- not equals functions
-- (Can be removed if Silver gets curried functions.)
function notEqualsInteger
Boolean ::= a::Integer b::Integer 
{ return a != b ; }

function notEqualsFloat
Boolean ::= a::Float b::Float 
{ return a != b ; }

function notEqualsString
Boolean ::= a::String b::String 
{ return a != b ; }

function notEqualsBoolean
Boolean ::= a::Boolean b::Boolean 
{ return a != b ; }

function notEqualsList
Boolean ::= neq::(Boolean ::= a a) l1::[a] l2::[a]
{ return if   null(l1)
         then ! null(l2)
         else null(l2) ||
              neq(head(l1), head(l2)) ||
              notEqualsList(neq, tail(l1), tail(l2)) ;
}

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
String ::= toStr::(String ::= a) xs::[a]
{ return "[" ++ toStringFromListHelper(toStr, xs) ++ "]" ; }

function toStringFromListHelper
String ::= toStr::(String ::= a) xs::[a]
{ return if  null(xs) 
         then ""
         else toStr(head(xs)) ++
              if null(tail(xs))
              then ""
              else ", " ++ toStringFromListHelper(toStr, tail(xs)) ;
}
