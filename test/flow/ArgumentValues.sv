grammar flow;

-- Argument values.  A production may use a child's value: by undecorating it, or by matching on it
-- and using a pattern variable that is not a tree.  An application then passes the argument's value
-- in, so the applying production's attributes depend on what the argument's expression depends on,
-- through the subterm's equation vertex.

inherited attribute vaM::String;
synthesized attribute vaPP::String;
nonterminal VaExpr with vaM, vaPP;
flowtype VaExpr = decorate {}, forward {vaM}, vaPP {};

production vaLit
top::VaExpr ::= s::String
{ top.vaPP = s; }

production vaWrap2
top::VaExpr ::= x::VaExpr
{
  -- A match on a tree bound by a pattern follows its forwards, whose flow type is {vaM}.
  x.vaM = "";
  top.vaPP = x.vaPP;
}

-- vaPP is built from the value of c.
production vaP
top::VaExpr ::= c::VaExpr
{ forwards to vaWrap2(new(c)); }

-- The argument, vaLit(top.vaM), applies a known production and depends on vaM, so the implicit copy
-- of vaPP needs vaM.
warnCode "In production flow:vaApply, the implicit copy equation for flow:vaPP (due to forwarding) would exceed the attribute's flow type with dependencies on flow:vaM" {
production vaApply
top::VaExpr ::=
{ forwards to vaP(vaLit(top.vaM)); }
}
noWarnCode "would exceed" {
production vaApplyConst
top::VaExpr ::=
{ forwards to vaP(vaLit("x")); }
}

-- The same at a local, where the access is checked directly.
warnCode "Synthesized equation vaPP exceeds flow type with dependencies on flow:vaM" {
production vaLocalUse
top::VaExpr ::=
{
  local w::VaExpr = vaP(vaLit(top.vaM));
  top.vaPP = w.vaPP;
}
}

-- vaPP is built from a pattern variable that is not a tree, bound by matching on c.
production vaPat
top::VaExpr ::= c::VaExpr
{
  c.vaM = "";  -- matching on c follows its forwards, whose flow type is {vaM}
  top.vaPP = case c of vaLit(s) -> s | _ -> "" end;
}
warnCode "In production flow:vaPatApply, the implicit copy equation for flow:vaPP (due to forwarding) would exceed the attribute's flow type with dependencies on flow:vaM" {
production vaPatApply
top::VaExpr ::=
{ forwards to vaPat(vaLit(top.vaM)); }
}

-- The match alone, following forwards, needs no value: only a use of a pattern variable does.
production vaPatOnly
top::VaExpr ::= c::VaExpr
{
  c.vaM = "";
  top.vaPP = case c of vaLit(_) -> "lit" | _ -> "" end;
}
noWarnCode "would exceed" {
production vaPatOnlyApply
top::VaExpr ::=
{ forwards to vaPatOnly(vaLit(top.vaM)); }
}

-- A tree bound by a pattern is part of the scrutinee's value too, so a match on it reaches that
-- value.
production vaNest
top::VaExpr ::= c::VaExpr
{
  c.vaM = "";
  top.vaPP = case c of vaWrap2(x) -> case x of vaLit(s) -> s | _ -> "" end | _ -> "" end;
}
warnCode "In production flow:vaNestApply, the implicit copy equation for flow:vaPP (due to forwarding) would exceed the attribute's flow type with dependencies on flow:vaM" {
production vaNestApply
top::VaExpr ::=
{ forwards to vaNest(vaWrap2(vaLit(top.vaM))); }
}

-- Undecorating a tree bound by a pattern uses its value, which is part of the scrutinee's value.
production vaUndec
top::VaExpr ::= c::VaExpr
{
  c.vaM = "";
  local w::VaExpr = case c of vaWrap2(x) -> ^x | _ -> vaLit("") end;
  top.vaPP = w.vaPP;
}
warnCode "In production flow:vaUndecApply, the implicit copy equation for flow:vaPP (due to forwarding) would exceed the attribute's flow type with dependencies on flow:vaM" {
production vaUndecApply
top::VaExpr ::=
{ forwards to vaUndec(vaWrap2(vaLit(top.vaM))); }
}

-- An attribute accessed on a tree bound by a pattern is evaluated on a subtree of the scrutinee's
-- value, so it depends on that value too.
production vaChildAcc
top::VaExpr ::= c::VaExpr
{
  c.vaM = "";
  top.vaPP = case c of vaWrap2(x) -> x.vaPP | _ -> "" end;
}
warnCode "In production flow:vaChildAccApply, the implicit copy equation for flow:vaPP (due to forwarding) would exceed the attribute's flow type with dependencies on flow:vaM" {
production vaChildAccApply
top::VaExpr ::=
{ forwards to vaChildAcc(vaWrap2(vaLit(top.vaM))); }
}
noWarnCode "would exceed" {
production vaChildAccConst
top::VaExpr ::=
{ forwards to vaChildAcc(vaWrap2(vaLit("x"))); }
}
