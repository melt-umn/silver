grammar tutorials:dc ;

{- bpp is a "better" pretty print attribute that avoids unneccessary
   use of parenthesis by passing down contextual information to child
   operands so that they can determine if they need to wrap themselves
   in parenthesis or not.  
-}
synthesized attribute bpp::String occurs on Root, Expr ;


{-  Assocativity and Precedence of operators
     Ops            Precedence     Associativity
     +, -           1              left
     *, /           2              left
     ^              3              right

     Note that operators of the same precedence have the same
     associativity.
 -}

inherited attribute enclosingOpPrecedence :: Integer occurs on Expr ;
{- The "root" productions sends down a precedence of 0.  This is
   lower than any other operator to avoid wrapping children in parens.
-}

inherited attribute leftOrRight :: String occurs on Expr ;
{- the position of the tree under a binary operator, "left" or
   "right".  Otherwise "none".   
-}

function wrapInParens
Boolean ::= enclosingPrecedence::Integer thisPrecedence::Integer
            thisPosition::String opAssociativity::String
{
 return enclosingPrecedence > thisPrecedence ||
       (enclosingPrecedence == thisPrecedence &&
        thisPosition != opAssociativity) ;
}

aspect production root
r::Root ::= e::Expr
{
  r.bpp = e.bpp ;
  e.enclosingOpPrecedence = 0 ; -- lower than anything else
  e.leftOrRight = "none" ; {- does not matter since higher precedence prevents
                              wrapping without considering associativity -}
}

aspect production power
pwr::Expr ::= l::Expr r::Expr
{
 pwr.bpp = if wrapInParens ( pwr.enclosingOpPrecedence, 3,
                             pwr.leftOrRight, "right" )
           then "(" ++ l.bpp ++ " ^ " ++ r.bpp ++ ")" 
           else l.bpp ++ " ^ " ++ r.bpp ;

 l.enclosingOpPrecedence = 3 ;
 r.enclosingOpPrecedence = 3 ;
 l.leftOrRight = "left" ;
 r.leftOrRight = "right" ;
}

aspect production add
sum::Expr ::= l::Expr r::Expr
{
 sum.bpp = if wrapInParens ( sum.enclosingOpPrecedence, 1,
                             sum.leftOrRight, "left" )
           then "(" ++ l.bpp ++ " + " ++ r.bpp ++ ")" 
           else l.bpp ++ " + " ++ r.bpp ;

 l.enclosingOpPrecedence = 1 ;
 r.enclosingOpPrecedence = 1 ;
 l.leftOrRight = "left" ;
 r.leftOrRight = "right" ;
}

aspect production sub
dff::Expr ::= l::Expr r::Expr
{
 dff.bpp = if wrapInParens ( dff.enclosingOpPrecedence, 1,
                             dff.leftOrRight, "left" )
           then "(" ++ l.bpp ++ " - " ++ r.bpp ++ ")" 
           else l.bpp ++ " - " ++ r.bpp ;

 l.enclosingOpPrecedence = 1 ;
 r.enclosingOpPrecedence = 1 ;
 l.leftOrRight = "left" ;
 r.leftOrRight = "right" ;
}

aspect production mul
prd::Expr ::= l::Expr r::Expr
{
 prd.bpp = if wrapInParens ( prd.enclosingOpPrecedence, 2,
                             prd.leftOrRight, "left" )
           then "(" ++ l.bpp ++ " * " ++ r.bpp ++ ")" 
           else l.bpp ++ " * " ++ r.bpp ;

 l.enclosingOpPrecedence = 2 ;
 r.enclosingOpPrecedence = 2 ;
 l.leftOrRight = "left" ;
 r.leftOrRight = "right" ;
}

aspect production div
quo::Expr ::= l::Expr r::Expr
{
 quo.bpp = if wrapInParens ( quo.enclosingOpPrecedence, 2,
                             quo.leftOrRight, "left" )
           then "(" ++ l.bpp ++ " / " ++ r.bpp ++ ")" 
           else l.bpp ++ " / " ++ r.bpp ;

 l.enclosingOpPrecedence = 2 ;
 r.enclosingOpPrecedence = 2 ;
 l.leftOrRight = "left" ;
 r.leftOrRight = "right" ;
}

aspect production integerConstant
e::Expr ::= i::IntLit_t
{
 e.bpp = i.lexeme ;
}
