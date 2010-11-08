grammar core;
{- Note to self: Remember that the type equivalence of ['a] is Decorated List<'a>.
   It can get confusing if you believe that ['a] is List<'a>. (NOT TRUE)
 -}


function map
[b] ::= f::Function(b ::= a)  l::[a]
{
  return if null(l)
         then []
         else f(head(l)) :: map(f, tail(l));
}

function foldr
b ::= f::Function(b ::= a b)  i::b  l::[a]
{
  return if null(l)
         then i
         else foldr(f, f(head(l), i), tail(l));
}

function filter
[a] ::= f::Function(Boolean ::= a) lst::[a]
{
  return if null(lst)
         then []
         else if f(head(lst))
              then head(lst) :: filter(f, tail(lst))
              else filter(f, tail(lst));
}

function containsBy
Boolean ::= eq::Function(Boolean ::= a a)  elem::a  lst::[a]
{
  return (!null(lst)) && (eq(elem, head(lst)) || containsBy(eq, elem, tail(lst)));
}


function last
a ::= lst::[a]
{
  return if null(tail(lst))
         then head(lst)
         else last(tail(lst));
}

function drop
[a] ::= number::Integer lst::[a]
{
  return if number <= 0
         then lst
         else drop(number-1, tail(lst));
}
function take
[a] ::= number::Integer lst::[a]
{
  return if number <= 0
         then []
         else head(lst) :: take(number-1, tail(lst));
}
function reverse
[a] ::= lst::[a]
{
  return reverseHelp(lst, []);
}
function reverseHelp -- do not use
[a] ::= lst::[a] sofar::[a]
{
  return if null(lst)
         then sofar
         else reverseHelp(tail(lst), head(lst) :: sofar);
}


function sortBy
[a] ::= lte::Function(Boolean ::= a a) lst::[a]
{
  return sortByHelp(lte, lst, length(lst));
}
function sortByHelp -- do not use
[a] ::= lte::Function(Boolean ::= a a) lst::[a] upTo::Integer
{
  return if upTo == 0 then []
         else if upTo == 1 then [head(lst)]
         else mergeBy(lte, front_half, back_half);

  local attribute front_half :: [a];
  front_half = sortByHelp(lte, lst, middle);

  local attribute back_half :: [a];
  back_half = sortByHelp(lte, drop(middle, lst), upTo - middle);

  local attribute middle :: Integer ;
  middle = toInt(toFloat(upTo) / 2.0) ;
}
function mergeBy -- do not use
[a] ::= lte::Function(Boolean ::= a a) l1::[a] l2::[a]
{
  return if null(l1) then l2
    else if null(l2) then l1
         else if lte(head(l1), head(l2))
              then head(l1) :: mergeBy(lte, tail(l1), l2)
              else head(l2) :: mergeBy(lte, l1, tail(l2)) ;
}


--------------------------------------------------------------------------------

function nil
[a] ::=
{
  return decorate i_nilList() with {};
} foreign {
  "java" : return "common.ConsCell.nil";
}

function cons
[a] ::= h::a  t::[a]
{
  return decorate i_consList(h, t) with {};
} foreign {
  "java" : return "new common.ConsCell(%h%, %t%)";
}

function append
[a] ::= l1::[a] l2::[a]
{
  return if l1.i_emptyList
         then l2
         else cons(head(l1), append(tail(l1), l2));
} foreign {
  "java" : return "new common.AppendCell(%l1%, %l2%)";
}


function null
Boolean ::= l::[a]
{
  return l.i_emptyList;
} foreign {
  "java" : return "%l%.nil()";
}

function listLength  -- not called 'length' since this is a builtin language feature, but thats how you should call it.
Integer ::= l::[a]
{
  return l.i_lengthList;
} foreign {
  "java" : return "new Integer(%l%.length())";
}

function head
a ::= l::[a]
{
  return l.i_headList;
} foreign {
  "java" : return "%l%.head()";
}

function tail
[a] ::= l::[a]
{
  return l.i_tailList;
} foreign {
  "java" : return "%l%.tail()";
}

--------------------------------------------------------------------------------

synthesized attribute i_headList<a> :: a;
synthesized attribute i_tailList<a> :: Decorated List<a>;
synthesized attribute i_emptyList :: Boolean;
synthesized attribute i_lengthList :: Integer;

nonterminal List<a> with i_headList<a>, i_tailList<a>, i_emptyList, i_lengthList;

abstract production i_nilList
l::List<a> ::=
{
  l.i_emptyList = true;
  l.i_lengthList = 0;
  l.i_headList = error("requested head of nil");
  l.i_tailList = error("requested tail of nil");
}

abstract production i_consList
l::List<a> ::= h::a  t::Decorated List<a>
{
  l.i_emptyList = false;
  l.i_lengthList = t.i_lengthList + 1;
  l.i_headList = h;
  l.i_tailList = t;
}

