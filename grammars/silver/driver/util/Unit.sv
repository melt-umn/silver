grammar silver:driver:util;

closed nonterminal Unit with ioIn, io, code, order;

synthesized attribute code :: Integer;
synthesized attribute order :: Integer;
inherited attribute ioIn :: IO;
synthesized attribute ioOut :: IO;

{--
 - Run units until a non-zero error code is encountered.
 -}
function runAll
IOVal<Integer> ::= i::IO l::[Unit]
{
  local attribute now :: Unit;
  now = head(l);
  now.ioIn = i;

  return  if null(l) 
	  then ioval(i, 0)
	  else if now.code != 0
	       then ioval(now.io, now.code)
	       else runAll(now.io, tail(l));
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


