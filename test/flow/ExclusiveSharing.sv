grammar flow;

-- A tree can be shared in at most one place, except in mutually exclusive branches of the same equation.
-- Sharing a tree also shares its translation attributes, so the same goes for a tree and its
-- translation attributes (at any depth).

inherited attribute xsEnv::String;
synthesized attribute xsOut::String;

nonterminal XSY with xsEnv, xsOut;
production xsY
top::XSY ::=
{ top.xsOut = top.xsEnv; }

translation attribute xsA::XSY;
nonterminal XSX with xsEnv, xsOut, xsA;
production xsX
top::XSX ::=
{
  top.xsOut = top.xsEnv;
  top.xsA = xsY();
}

-- Decoration sites
nonterminal XSW with xsOut;
production xsWX
top::XSW ::= c::XSX
{
  c.xsEnv = "wX";
  c.xsA.xsEnv = "wX.a";
  top.xsOut = c.xsOut ++ c.xsA.xsOut;
}
production xsWX2
top::XSW ::= c::XSX
{
  c.xsEnv = "wX2";
  c.xsA.xsEnv = "wX2.a";
  top.xsOut = c.xsOut ++ c.xsA.xsOut;
}
production xsWY
top::XSW ::= c::XSY
{
  c.xsEnv = "wY";
  top.xsOut = c.xsOut;
}
production xsPair
top::XSW ::= l::XSW r::XSW
{ top.xsOut = l.xsOut ++ r.xsOut; }

nonterminal XSSel;
production xsSelA
top::XSSel ::=
{}

-- A tree and its translation attribute can be shared in mutually exclusive branches.
production xsExclIf
top::XSW ::= b::Boolean x::XSX
{
  local y::XSW = if b then xsWX(@x) else xsWY(@x.xsA);
  top.xsOut = y.xsOut;
}

production xsExclCase
top::XSW ::= s::XSSel x::XSX
{
  local y::XSW = case s of xsSelA() -> xsWX(@x) | _ -> xsWY(@x.xsA) end;
  top.xsOut = y.xsOut;
}

production xsExclNested
top::XSW ::= b1::Boolean b2::Boolean x::XSX
{
  local y::XSW = if b1 then if b2 then xsWX(@x) else xsWY(@x.xsA) else xsWX2(@x);
  top.xsOut = y.xsOut;
}

-- The failure branch of this match is bound in a let, which is used in both of the primitive matches.
production xsExclCaseLet
top::XSW ::= s1::XSSel s2::XSSel x::XSX
{
  local y::XSW = case s1, s2 of xsSelA(), xsSelA() -> xsWX(@x) | _, _ -> xsWY(@x.xsA) end;
  top.xsOut = y.xsOut;
}

production xsExclFwd
top::XSW ::= b::Boolean x::XSX
{
  forwards to if b then xsWX(@x) else xsWY(@x.xsA);
}

-- Guarded alternatives fall through to later ones, so the failure branches of this match are nested lets.
production xsExclGuards
top::XSW ::= m::Maybe<Integer> c::Boolean x::XSX
{
  local y::XSW = case m of just(0) -> xsWX(@x) | _ when c -> xsWX2(@x) | just(1) -> xsWY(xsY()) | _ -> xsWY(@x.xsA) end;
  top.xsOut = y.xsOut;
}

production xsSelB
top::XSSel ::=
{}

-- A guard in an inner column falls through to a failure branch that the outer column also uses.
production xsExclGuardInner
top::XSW ::= s1::XSSel s2::XSSel c::Boolean x::XSX
{
  local y::XSW = case s1, s2 of xsSelA(), xsSelA() -> xsWY(xsY()) | xsSelA(), _ when c -> xsWX(@x) | _, _ -> xsWX2(@x) end;
  top.xsOut = y.xsOut;
}

production xsExclGuardInnerTrans
top::XSW ::= s1::XSSel s2::XSSel c::Boolean x::XSX
{
  local y::XSW = case s1, s2 of xsSelA(), xsSelA() -> xsWY(xsY()) | xsSelA(), _ when c -> xsWY(@x.xsA) | _, _ -> xsWX2(@x) end;
  top.xsOut = y.xsOut;
}

-- A let binding used in another binding, exclusively with a share there, and in the body.
production xsExclLetInLet
top::XSW ::= b::Boolean c::Boolean x::XSX
{
  local y::XSW = let w::XSW = xsWX(@x) in let z::XSW = if b then w else xsWX2(@x) in if c then z else w end end;
  top.xsOut = y.xsOut;
}

-- Shares in mutually exclusive anonymous decorations.
production xsExclAnon
top::XSW ::= b::Boolean x::XSX
{
  local s::String = if b then (decorate xsWX(@x) with {}).xsOut else (decorate xsWX2(@x) with {}).xsOut;
  top.xsOut = s;
}

-- A let binding that shares a tree, used twice.
wrongFlowCode "Tree x in production flow:xsLetTwice is shared in multiple places" {
production xsLetTwice
top::XSW ::= x::XSX
{
  local y::XSW = let w::XSW = xsWX(@x) in xsPair(w, w) end;
  top.xsOut = y.xsOut;
}
}

-- A tree signature-shared in the forward.  flow:transShare shares a translation attribute of it
-- in a forward production attribute of an aspect, which conflicts.
nonterminal XSS with xsEnv, xsOut;
production xsS
top::XSS ::=
{ top.xsOut = top.xsEnv; }
production xsWSShared
top::XSW ::= @c::XSS
{
  c.xsEnv = "shared";
  top.xsOut = c.xsOut;
}
production xsWYShared
top::XSW ::= @c::XSY
{
  c.xsEnv = "sharedY";
  top.xsOut = c.xsOut;
}
production xsFwdSig
top::XSW ::= x::XSS
{
  forwards to xsWSShared(x);
}

-- A tree shared in the failure branches of a match, and also outside of the match.
wrongFlowCode "Tree x in production flow:xsTwiceAroundCase is shared in multiple places" {
production xsTwiceAroundCase
top::XSW ::= m::Maybe<Integer> c::Boolean x::XSX
{
  local y::XSW = xsPair(xsWX(@x), case m of just(0) -> xsWY(xsY()) | _ when c -> xsWY(xsY()) | _ -> xsWX2(@x) end);
  top.xsOut = y.xsOut;
}
}

wrongFlowCode "Cannot share x.flow:xsA in production flow:xsBothAroundCase, because child x is also shared" {
production xsBothAroundCase
top::XSW ::= m::Maybe<Integer> c::Boolean x::XSX
{
  local y::XSW = xsPair(xsWY(@x.xsA), case m of just(0) -> xsWY(xsY()) | _ when c -> xsWY(xsY()) | _ -> xsWX(@x) end);
  top.xsOut = y.xsOut;
}
}

-- A tree shared twice in one branch, where the other branch also shares it.
wrongFlowCode "Tree x in production flow:xsTwiceInElse is shared in multiple places" {
production xsTwiceInElse
top::XSW ::= b::Boolean x::XSX
{
  local y::XSW = if b then xsPair(xsWX(@x), xsWY(xsY())) else xsPair(xsWX(@x), xsWX2(@x));
  top.xsOut = y.xsOut;
}
}

wrongFlowCode "Tree x in production flow:xsTwiceInThen is shared in multiple places" {
production xsTwiceInThen
top::XSW ::= b::Boolean x::XSX
{
  local y::XSW = if b then xsPair(xsWX(@x), xsWX2(@x)) else xsPair(xsWX(@x), xsWY(xsY()));
  top.xsOut = y.xsOut;
}
}

wrongFlowCode "Tree x in production flow:xsTwiceInCase is shared in multiple places" {
production xsTwiceInCase
top::XSW ::= s::XSSel x::XSX
{
  local y::XSW = case s of xsSelA() -> xsPair(xsWX(@x), xsWY(xsY())) | _ -> xsPair(xsWX(@x), xsWX2(@x)) end;
  top.xsOut = y.xsOut;
}
}

-- A tree and its translation attribute shared in the same branch.
wrongFlowCode "Cannot share x.flow:xsA in production flow:xsBothInThen, because child x is also shared" {
production xsBothInThen
top::XSW ::= b::Boolean x::XSX
{
  local y::XSW = if b then xsPair(xsWX(@x), xsWY(@x.xsA)) else xsWY(xsY());
  top.xsOut = y.xsOut;
}
}

-- The same, where the other branch also shares the tree.
wrongFlowCode "Cannot share x.flow:xsA in production flow:xsBothInElse, because child x is also shared" {
production xsBothInElse
top::XSW ::= b::Boolean x::XSX
{
  local y::XSW = if b then xsPair(xsWX(@x), xsWY(xsY())) else xsPair(xsWX2(@x), xsWY(@x.xsA));
  top.xsOut = y.xsOut;
}
}

wrongFlowCode "Cannot share x.flow:xsA in production flow:xsBothInElseRev, because child x is also shared" {
production xsBothInElseRev
top::XSW ::= b::Boolean x::XSX
{
  local y::XSW = if b then xsPair(xsWX(@x), xsWY(xsY())) else xsPair(xsWY(@x.xsA), xsWX2(@x));
  top.xsOut = y.xsOut;
}
}

-- Branches of different equations are not mutually exclusive.
wrongFlowCode "Cannot share x.flow:xsA in production flow:xsTwoEquations, because child x is also shared" {
production xsTwoEquations
top::XSW ::= b::Boolean x::XSX
{
  local y1::XSW = if b then xsWX(@x) else xsWY(xsY());
  local y2::XSW = if b then xsWY(xsY()) else xsWY(@x.xsA);
  top.xsOut = y1.xsOut ++ y2.xsOut;
}
}

-- The same for a translation attribute of a translation attribute.
translation attribute xsB::XSX;
nonterminal XSZ with xsB;
production xsZ
top::XSZ ::=
{ top.xsB = xsX(); }

wrongFlowCode "Cannot share z.flow:xsB.flow:xsA in production flow:xsNestedBoth, because translation attribute flow:xsB of child z is also shared" {
production xsNestedBoth
top::XSW ::= z::XSZ
{
  local y::XSW = xsPair(xsWX(@z.xsB), xsWY(@z.xsB.xsA));
  top.xsOut = y.xsOut;
}
}

-- Only trees that can be shared have translation attributes that can be shared.
wrongFlowCode "Cannot share a translation attribute of the production LHS" {
production xsShareLhsTrans
top::XSX ::=
{
  top.xsOut = "";
  top.xsA = xsY();
  local site::XSW = xsWY(@top.xsA);
}
}

wrongFlowCode "Cannot share a translation attribute of the forward tree" {
production xsShareFwdTrans
top::XSX ::=
{
  local site::XSW = xsWY(@forward.xsA);
  forwards to xsX();
}
}

wrongFlowCode "Cannot share a translation attribute of an anonymously decorated tree" {
production xsShareAnonTrans
top::XSW ::=
{
  local site::XSW = xsWY(@(decorate xsX() with { xsEnv = ""; }).xsA);
  top.xsOut = site.xsOut;
}
}

wrongFlowCode "Cannot share a translation attribute of a pattern variable" {
production xsSharePatternTrans
top::XSW ::= w::XSW
{
  local site::XSW = case w of xsWX(c) -> xsWY(@c.xsA) | _ -> xsWY(xsY()) end;
  top.xsOut = site.xsOut;
}
}

production xsXShareY
top::XSX ::= @c::XSY
{
  c.xsEnv = top.xsEnv;
  top.xsOut = c.xsOut;
  top.xsA = xsY();
}

wrongFlowCode "Cannot share a translation attribute of the production LHS" {
production xsSigShareLhsTrans
top::XSX ::=
{
  top.xsA = xsY();
  forwards to xsXShareY(top.xsA);
}
}

-- The forward parent is decorated by its own parent, like the LHS.
wrongFlowCode "Cannot share the forward parent" {
production xsShareFwdParent
top::XSX ::= @c::XSY
{
  c.xsEnv = top.xsEnv;
  top.xsOut = c.xsOut;
  top.xsA = xsY();
  local site::XSW = xsWX(@forwardParent);
}
}

wrongFlowCode "Cannot share a translation attribute of the forward parent" {
production xsShareFwdParentTrans
top::XSX ::= @c::XSY
{
  c.xsEnv = top.xsEnv;
  top.xsOut = c.xsOut;
  top.xsA = xsY();
  local site::XSW = xsWY(@forwardParent.xsA);
}
}

wrongFlowCode "Cannot share a translation attribute of the forward parent" {
production xsSigShareFwdParentTrans
top::XSX ::= @c::XSY
{
  c.xsEnv = top.xsEnv;
  forwards to xsXShareY(forwardParent.xsA);
}
}
