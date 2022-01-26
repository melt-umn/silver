grammar silver:compiler:driver:util;

closed nonterminal DriverAction with config, ioIn, io, code, order;

synthesized attribute code :: Integer;
synthesized attribute order :: Integer;
inherited attribute ioIn :: IOToken;

abstract production wrapUnit
top::DriverAction ::= f::(IOVal<Integer> ::= IOToken) order::Integer
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
IOVal<Integer> ::= l::[DriverAction] i::IOToken
{
  local now :: DriverAction = head(l);
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
[DriverAction] ::= c1::[DriverAction]
{
  return sortBy(unitLTE, c1);
}
function unitLTE
Boolean ::= l::DriverAction r::DriverAction
{
  return l.order <= r.order;
}


