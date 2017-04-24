grammar silver:composed:monto;

{--
  Maps an IO-performing function over a list.

  TODO Someone should inspect this to make sure I'm not doing anything dumb wrt IO.
  TODO Should this (and ioFold{l,r} be in core?)
-}
function ioMap
IOVal<[b]> ::= f::(IOVal<b> ::= a IO) l::[a] ioin::IO
{
  local h :: IOVal<b> = f(head(l), ioin);
  local t :: IOVal<[b]> = ioMap(f, tail(l), h.io);
  return case l of
  | [] -> ioval(ioin, [])
  | _  -> ioval(t.io, h.iovalue :: t.iovalue)
  end;
}

{--
  Wraps a pure 1-argument function with IO.
 -}
function ioWrap
(IOVal<b> ::= a IO) ::= f::(b ::= a)
{
  return \x::a ioin::IO -> ioval(ioin, f(x));
}

{--
  Wraps a pure 2-argument function with IO.
 -}
function ioWrap2
(IOVal<c> ::= a b IO) ::= f::(c ::= a b)
{
  return \x::a y::b ioin::IO -> ioval(ioin, f(x, y));
}
