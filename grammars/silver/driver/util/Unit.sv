grammar silver:driver:util;

closed nonterminal Unit with ioIn, io, code, order;

synthesized attribute code :: Integer;
synthesized attribute order :: Integer;
inherited attribute ioIn :: IO;

abstract production wrapUnit
top::Unit ::= f::(IOVal<Integer> ::= IO) order::Integer
{
  local call :: IOVal<Integer> = f(top.ioIn);
  top.io = call.io;
  top.code = call.iovalue;
  top.order = order;
}

{--
 - Run units until a non-zero error code is encountered.
 -}
function runAll
IOVal<Integer> ::= l::[Unit] i::IO
{
  local attribute now :: Unit;
  now = head(l);
  now.ioIn = i;

  return  if unsafeTrace(null(l), i) -- TODO: this is just to force strictness...
	  then ioval(i, 0)
	  else if now.code != 0
	       then ioval(now.io, now.code)
	       else runAll(tail(l), now.io);
}

{--
 - Sorts a list of Units by priority order. (small to large)
 -}
function sortUnits
[Unit] ::= c1::[Unit]
{
  return sortBy(unitLTE, c1);
}
function unitLTE
Boolean ::= l::Unit r::Unit
{
  return l.order <= r.order;
}


