grammar core;

{- Remember that the type equivalence of ['a] is Decorated List<'a>.

   It can get confusing if you believe that ['a] is List<'a>. (NOT TRUE)
 -}
nonterminal List<`a>;

synthesized attribute i_headList<`a> :: `a;
attribute i_headList<`a> occurs on List<`a>;
synthesized attribute i_tailList<`a> :: Decorated List<`a>;
attribute i_tailList<`a> occurs on List<`a>;
synthesized attribute i_emptyList :: Boolean;
attribute i_emptyList occurs on List<`a>;
synthesized attribute i_lengthList :: Integer;
attribute i_lengthList occurs on List<`a>;

abstract production i_nilList
l::List<`a> ::=
{
  l.i_emptyList = true;
  l.i_lengthList = 0;
  l.i_headList = error("requested head of nil");
  l.i_tailList = error("requested tail of nil");
}

abstract production i_consList
l::List<`a> ::= h::`a  t::Decorated List<`a>
{
  l.i_emptyList = false;
  l.i_lengthList = t.i_lengthList + 1;
  l.i_headList = h;
  l.i_tailList = t;
}

--------------------------------------------------------------------------------

function nil
[`a] ::=
{
  return decorate i_nilList() with {};
} foreign {
  "java" : return "common.ConsCell.nil";
}

function cons
[`a] ::= h::`a  t::[`a]
{
  return decorate i_consList(h, t) with {};
} foreign {
  "java" : return "new common.ConsCell(%h%, %t%)";
}

function append
[`a] ::= l1::[`a] l2::[`a]
{
  return if l1.i_emptyList
         then l2
         else cons(head(l1), append(tail(l1), l2));
} foreign {
  "java" : return "new common.AppendCell(%l1%, %l2%)";
}


function null
Boolean ::= l::[`a]
{
  return l.i_emptyList;
} foreign {
  "java" : return "%l%.nil()";
}

function listLength  -- not called 'length' since this is a builtin language feature, but that`s how you should call it.
Integer ::= l::[`a]
{
  return l.i_lengthList;
} foreign {
  "java" : return "new Integer(%l%.length())";
}

function head
`a ::= l::[`a]
{
  return l.i_headList;
} foreign {
  "java" : return "%l%.head()";
}

function tail
[`a] ::= l::[`a]
{
  return l.i_tailList;
} foreign {
  "java" : return "%l%.tail()";
}

--------------------------------------------------------------------------------

function map
[`b] ::= f::Function(`b ::= `a)  l::[`a]
{
  return if null(l)
         then []
         else f(head(l)) :: map(f, tail(l));
}

function foldr
`b ::= f::Function(`b ::= `a `b)  i::`b  l::[`a]
{
  return if null(l)
         then i
         else foldr(f, f(head(l), i), tail(l));
}

