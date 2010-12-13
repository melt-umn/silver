grammar dc ;

{- The abstract syntax is defined here.  Defining abstract syntax is
   not required in Silver since attributes can decorate the concrete
   syntax as well.  But in many cases, especially when the concrete
   syntax is complicated by parsing requirements, it is useful to do
   so.  -}

nonterminal Root;

{- The "pretty print" of a tree. Should parse to the "same" tree. -}
synthesized attribute pp :: String;

attribute pp occurs on Root;

-- Attribute declarations and occurs on declrations can be combined.
-- This attribute will compute the value of the expresssion.
synthesized attribute value :: Integer occurs on Root;

-- Productions with the 'abstract' modifier are not used by the parser
-- generator.
abstract production root
r::Root ::= e::Expr
{
  r.pp = e.pp ;
  r.value = e.value ;
}

nonterminal Expr with pp, value;

abstract production add
sum::Expr ::= l::Expr r::Expr
{
 sum.pp = "(" ++ l.pp ++ " + " ++ r.pp ++ ")";
 sum.value = l.value + r.value ;
}

abstract production sub
dff::Expr ::= l::Expr r::Expr
{
 dff.pp = "(" ++ l.pp ++ " - " ++ r.pp ++ ")";
 dff.value = l.value - r.value ;
}

abstract production mul
prd::Expr ::= l::Expr r::Expr
{
 prd.pp = "(" ++ l.pp ++ " * " ++ r.pp ++ ")";
 prd.value = l.value * r.value ;
}

abstract production div
quo::Expr ::= l::Expr r::Expr
{
 quo.pp = "(" ++ l.pp ++ " / " ++ r.pp ++ ")";
 quo.value = l.value / r.value ;
}

abstract production integerConstant
e::Expr ::= i::IntLit_t
{
 e.pp = i.lexeme ;
 e.value = toInt(i.lexeme);
}

