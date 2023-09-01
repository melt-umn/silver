grammar implicit_monads;

nonterminal X with i, j, pp, fun, ann;

annotation ann::Integer;
implicit inherited attribute i::Maybe<Integer>;
implicit synthesized attribute j::Maybe<X>;

--have to use the name ann because we can't write a function with an
--annotation argument
implicit synthesized attribute fun::Maybe<(X ::= X; ann::Integer)>;

synthesized attribute pp::String;

abstract production x
top::X ::= sub::X
{
  sub.i = top.i + 2;
  top.j = x(sub.j, ann=sub.ann);

  top.pp = "x(" ++ sub.pp ++ ")";

  top.fun = sub.fun;
}

abstract production y
top::X ::= sub::X
{
  sub.i = top.i;
  top.j = sub.fun(sub.j, ann=top.i);

  top.pp = "y(" ++ sub.pp ++ ")";

  top.fun = sub.fun;
}

abstract production endX
top::X ::=
{
  top.j = endX(ann=top.i);

  top.pp = "endX()";

  top.fun = x;
}
