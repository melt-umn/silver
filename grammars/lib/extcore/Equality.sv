grammar lib:extcore ;

{- We are accumulating functions in List.sv that require a function to
   test for equality.  It might be nice to establish a naming
   convention for these, and other types of functions.

   EVW proposes that we name functions by a common prefix indicating
   the computation followed by the name of the type of the values
   involved.

   Thus, for equality testing functions we have names of the form
      equalsTYPE
   where TYPE is the actual name of the type of the values being tested.
   Thus, equalsInteger, equalsString, equalsTypeExpr, etc.

   Note that the function stringEq in core/String.sv does not follow
   this convention.  EVW thinks we should change it.
-}

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
Boolean ::= eq::Function(Boolean ::= a a) l1::[a] l2::[a]
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
Boolean ::= neq::Function(Boolean ::= a a) l1::[a] l2::[a]
{ return if   null(l1)
         then ! null(l2)
         else null(l2) ||
              neq(head(l1), head(l2)) ||
              notEqualsList(neq, tail(l1), tail(l2)) ;
}
