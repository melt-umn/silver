grammar core;

function map
[b] ::= f::Function(b ::= a)  l::[a]
{ return if null(l)
         then []
         else f(head(l)) :: map(f, tail(l));
}

function map_p
[b] ::= f::Production(b ::= a)  l::[a]
{ return if null(l)
         then []
         else f(head(l)) :: map_p(f, tail(l));
}

-- folds - left/right, function/production, zero/one min num elements
function foldr
b ::= f::Function(b ::= a b)  i::b  l::[a]
{ return if null(l)
         then i
         else f(head(l), foldr(f, i, tail(l)));
}

function foldr_p
b ::= f::Production(b ::= a b)  i::b  l::[a]
{ return if null(l)
         then i
         else f(head(l), foldr_p(f, i, tail(l)));
}

function foldl
a ::= f::Function(a ::= a b)  i::a  l::[b]
{ return if null(l)
         then i
         else foldl(f, f(i, head(l)), tail(l)) ;
}

function foldl_p
a ::= f::Production(a ::= a b)  i::a  l::[b]
{ return if null(l) 
         then i
         else foldl_p(f, f(i, head(l)), tail(l)) ;
}

function foldr1
a ::= f::Function(a ::= a a) l::[a]
{ return if null(l)
         then error ("Applying foldr1 to empty list.")
         else
         if length(l) == 1
         then head(l) 
         else f(head(l), foldr1(f, tail(l))); 
}

function foldr1_p
a ::= f::Production(a ::= a a) l::[a]
{ return if null(l)
         then error ("Applying foldr1_p to empty list.")
         else
         if length(l) == 1
         then head(l) 
         else f(head(l), foldr1_p(f, tail(l))); 
}

function foldl1
a ::= f::Function(a ::= a a) l::[a]
{ return if null(l)
         then error ("Applying foldl1 to empty list.")
         else foldl(f, head(l), tail(l)) ;
}

function foldl1_p
a ::= f::Production(a ::= a a) l::[a]
{ return if null(l)
         then error ("Applying foldl1_p to empty list.")
         else foldl_p(f, head(l), tail(l)) ;
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

function partition
Pair<[a] [a]> ::= f::Function(Boolean ::= a) lst::[a]
{
  local attribute recurse :: Pair<[a] [a]>;
  recurse = partition(f, tail(lst));
  
  return if null(lst) then pair([],[])
         else if f(head(lst))
              then pair(head(lst) :: recurse.fst, recurse.snd)
              else pair(recurse.fst, head(lst) :: recurse.snd);
}

function containsBy
Boolean ::= eq::Function(Boolean ::= a a)  elem::a  lst::[a]
{
  return (!null(lst)) && (eq(elem, head(lst)) || containsBy(eq, elem, tail(lst)));
}

function nubBy
[a] ::= eq::Function(Boolean ::= a a)  xs::[a]
{
 return if null(xs) then [] 
        else head(xs) :: nubBy(eq, removeBy(eq, head(xs), tail(xs))) ;
}

function removeBy
[a] ::= eq::Function(Boolean ::= a a)  x::a  xs::[a]
{
 return if null(xs) then [ ] 
        else (if eq(x,head(xs)) then [ ] else [head(xs)]) ++
             removeBy (eq, x, tail(xs)) ;
}

function removeAllBy
[a] ::= eq::Function(Boolean ::= a a)  ys::[a]  xs::[a]
{
 return if null(ys) then xs
        else removeAllBy(eq, tail(ys), removeBy(eq, head(ys), xs)) ;
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
function dropWhile
[a] ::= f::Function(Boolean::=a) lst::[a]
{
  return if   null(lst) || ! f(head(lst)) 
         then lst
         else dropWhile(f,tail(lst)) ;
}
function takeWhile
[a] ::= f::Function(Boolean::=a) lst::[a]
{ return if   null(lst) || ! f(head(lst)) 
         then [ ]
         else head(lst) :: takeWhile(f,tail(lst)) ;
}
function takeUntil
[a] ::= f::Function(Boolean::=a) lst::[a]
{
  return if   null(lst) || f(head(lst)) 
         then []
         else head(lst) :: takeUntil(f,tail(lst)) ;
}

function positionOf
Integer ::= eq::Function(Boolean ::= a a) x::a xs::[a]
{
 return positionOfHelper(eq,x,xs,0) ;
}

function positionOfHelper
Integer ::= eq::Function(Boolean ::= a a) x::a xs::[a] currentPos::Integer
{
 return if   null(xs)
        then -1
        else 
        if   eq(x, head(xs))
        then currentPos
        else positionOfHelper(eq, x, tail(xs), currentPos+1) ;
}


function repeat
[a] ::= v::a times::Integer
{ return if   times <= 0
         then [ ]
         else v :: repeat(v, times-1) ;
}
         

function zipWith
[c] ::= l1::[a]  l2::[b] f::Function(c::= a b)
{ return
   if   null(l1) || null(l2)
   then [ ]
   else f( head(l1), head(l2) ) :: zipWith (tail(l1), tail(l2), f) ;
}
function zipWith_p
[c] ::= l1::[a]  l2::[b] f::Production(c::= a b)
{ return
   if   null(l1) || null(l2)
   then [ ]
   else f( head(l1), head(l2) ) :: zipWith_p (tail(l1), tail(l2), f) ;
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

function groupBy
[[a]] ::= eq::Function(Boolean ::= a a) l::[a]
{
  local attribute helpercall :: Pair<[a] [a]>;
  helpercall = groupByHelp(eq, head(l), l);
  
  return if null(l) then [] 
         else helpercall.fst :: if null(helpercall.snd) then []
                                else groupBy(eq, helpercall.snd);
}
function groupByHelp
Pair<[a] [a]> ::= eq::Function(Boolean ::= a a) f::a l::[a]
{
  -- f is the representative element we're comparing with, but is not considered
  -- included when we're called.
  local attribute recurse :: Pair<[a] [a]>;
  recurse = groupByHelp(eq, f, tail(l));
  
  return if null(l) || !eq(f, head(l))
         then pair([], l)
         else pair(head(l) :: recurse.fst, recurse.snd);
}

{--
 - Inserts the separator in between all elements of the list.
 -}
function intersperse 
[a] ::= sep::a xs::[a]
{ return if null(xs) then [ ]
         else if null(tail(xs)) then xs
         else head(xs) :: sep :: intersperse(sep, tail(xs)) ;
}


-- Set operations
function unionBy
[a] ::= eq::Function(Boolean ::= a a) l::[a] r::[a] 
{
 return  if   null(l)
         then r
         else 
         (if   containsBy (eq, head(l), r)
          then [ ]
          else [ head(l) ] )
         ++ unionBy( eq, tail(l), r ) ;
}

function intersectBy
[a] ::= eq::Function(Boolean ::= a a) l::[a] r::[a]
{
 return  if   null(l)
         then [ ]
         else 
         (if   containsBy (eq, head(l), r)
          then [ head(l) ]
          else [ ] )
         ++ intersectBy( eq, tail(l), r ) ;
}

function unionsBy
[a] ::= eq::Function(Boolean ::= a a) ss::[ [a] ] 
{
 return nubBy(eq, foldr (append, [ ], ss) ) ;
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
  "java" : return "new common.ConsCell(%?h?%, %?t?%)";
}

function append
[a] ::= l1::[a] l2::[a]
{
  return if l1.i_emptyList
         then l2
         else cons(head(l1), append(tail(l1), l2));
} foreign {
  "java" : return "common.AppendCell.append(%l1%, %?l2?%)";
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

{- Note to self: Remember that the type equivalence of ['a] is Decorated List<'a>.
   It can get confusing if you believe that ['a] is List<'a>. (NOT TRUE)
 -}


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

