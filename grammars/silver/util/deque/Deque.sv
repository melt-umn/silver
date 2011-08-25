grammar silver:util:deque;

-- This is all based on Okasaki 1998.

nonterminal Deque<a>;

abstract production deque
top::Deque<a> ::= ln::Integer l::[a] rn::Integer r::[a]
{}

function dqEmpty
Deque<a> ::=
{
  return deque(0, [], 0, []);
}

function dqCons
Deque<a> ::= e::a q::Deque<a>
{
  return case q of deque(ln,l,rn,r) -> dqCheck(ln+1,e::l,rn,r) end;
}
function dqHead
a ::= q::Deque<a>
{
  return case q of 
  | deque(_,[],_,x::_) -> x -- single element
  | deque(_,x::_,_,_) -> x
  end;
}
function dqTail
Deque<a> ::= q::Deque<a>
{
  return case q of 
  | deque(_,[],_,_::_) -> dqEmpty() -- single element
  | deque(ln,lh::lt,rn,r) -> dqCheck(ln-1, lt, rn, r)
  end;
}
function dqSnoc
Deque<a> ::= q::Deque<a> e::a
{
  return case q of deque(ln,l,rn,r) -> dqCheck(ln,l,rn+1,e::r) end;
}
function dqLast
a ::= q::Deque<a>
{
  return case q of 
  | deque(_,x::_,_,[]) -> x -- single element
  | deque(_,_,_,x::_) -> x
  end;
}
function dqInit
Deque<a> ::= q::Deque<a>
{
  return case q of 
  | deque(_,_::_,_,[]) -> dqEmpty() -- single element
  | deque(ln,l,rn,rh::rt) -> dqCheck(ln, l, rn-1, rt)
  end;
}
function dqIsEmpty
Boolean ::= q::Deque<a>
{
  return case q of deque(ln,_,rn,_) -> ln == 0 && rn == 0 end;
}
function dqReverse
Deque<a> ::= q::Deque<a>
{
  return case q of deque(ln,l,rn,r) -> deque(rn,r,ln,l) end;
} 

function dqCheck
Deque<a> ::= lenf::Integer f::[a] lenr::Integer r::[a]
{
  local mid::Integer = (lenf + lenr) / 2;
  local rest::Integer = lenf + lenr - mid;
  return if lenf > 2 * lenr + 1 then
           let fprime ::[a]= take(mid,f),
               rprime ::[a] = r ++ reverse(drop(mid,f))
            in deque(mid, fprime, rest, rprime) end
         else if lenr > 2*lenf + 1 then
           let rprime ::[a]= take(mid, r),
               fprime ::[a]= f ++ reverse(drop(mid, r))
            in deque(rest, fprime, mid, rprime) end
         else deque(lenf, f, lenr, r);
}

