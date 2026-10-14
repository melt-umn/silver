grammar flow:longCase;

imports flow;

-- The failure branches of a long match with guards are nested lets, each used in several places.
-- Checking where a tree shared in the last alternative is shared must not take time exponential
-- in their nesting.  This is a separate grammar so that a regression is a timeout.
nonterminal LongCase with xsOut;

production xsLongCase
top::LongCase ::= m::Maybe<Integer> c0::Boolean c1::Boolean x::XSX
{
  local y::XSW = case m of
  | just(0) -> xsWY(xsY())
  | _ when c0 -> xsWY(xsY())
  | just(1) -> xsWY(xsY())
  | _ when c1 -> xsWY(xsY())
  | just(2) -> xsWY(xsY())
  | _ when c0 -> xsWY(xsY())
  | just(3) -> xsWY(xsY())
  | _ when c1 -> xsWY(xsY())
  | just(4) -> xsWY(xsY())
  | _ when c0 -> xsWY(xsY())
  | just(5) -> xsWY(xsY())
  | _ when c1 -> xsWY(xsY())
  | just(6) -> xsWY(xsY())
  | _ when c0 -> xsWY(xsY())
  | just(7) -> xsWY(xsY())
  | _ when c1 -> xsWY(xsY())
  | just(8) -> xsWY(xsY())
  | _ when c0 -> xsWY(xsY())
  | just(9) -> xsWY(xsY())
  | _ when c1 -> xsWY(xsY())
  | just(10) -> xsWY(xsY())
  | _ when c0 -> xsWY(xsY())
  | just(11) -> xsWY(xsY())
  | _ when c1 -> xsWY(xsY())
  | just(12) -> xsWY(xsY())
  | _ when c0 -> xsWY(xsY())
  | just(13) -> xsWY(xsY())
  | _ when c1 -> xsWY(xsY())
  | just(14) -> xsWY(xsY())
  | _ when c0 -> xsWY(xsY())
  | just(15) -> xsWY(xsY())
  | _ when c1 -> xsWY(xsY())
  | _ -> xsWX(@x)
  end;
  top.xsOut = y.xsOut;
}
