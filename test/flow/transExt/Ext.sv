grammar flow:transExt;

imports flow;

-- An extension translation attribute on a host nonterminal. In the host forwarding production
-- tsFFwd it is not known to occur, so its implicit forward copy is suspect; the translation still
-- depends on what is supplied to it.

translation attribute tsXT::TSM;
attribute tsXT occurs on TSF;

aspect production tsFBase
top::TSF ::=
{
  top.tsXT = tsM();
}

synthesized attribute tsOutX::Integer occurs on TSF;
flowtype tsOutX {} on TSF;

aspect production tsFBase
top::TSF ::=
{
  top.tsOutX = 0;
}

warnCode "Synthesized equation tsOutX exceeds flow type with dependencies on flow:transExt:tsXT.flow:tsJ" {
aspect production tsFFwd
top::TSF ::=
{
  top.tsOutX = top.tsXT.tsS;
}
}

-- The same for a translation of a translation.
translation attribute tsXT2::TSM2;
attribute tsXT2 occurs on TSF;

aspect production tsFBase
top::TSF ::=
{
  top.tsXT2 = tsM2();
}

synthesized attribute tsOutX2::Integer occurs on TSF;
flowtype tsOutX2 {} on TSF;

aspect production tsFBase
top::TSF ::=
{
  top.tsOutX2 = 0;
}

warnCode "Synthesized equation tsOutX2 exceeds flow type with dependencies on flow:transExt:tsXT2.flow:tsU.flow:tsJ" {
aspect production tsFFwd
top::TSF ::=
{
  top.tsOutX2 = top.tsXT2.tsU.tsS;
}
}

-- An extension may not define even its own translation attribute in an aspect of a forwarding host
-- production.
translation attribute tsXT3::TSM;
attribute tsXT3 occurs on TSF;

aspect production tsFBase
top::TSF ::=
{
  top.tsXT3 = tsM();
}

warnCode "Translation attribute flow:transExt:tsXT3 is defined in production flow:tsFFwd, which forwards; a forwarding production must take its translation attributes from its forward" {
aspect production tsFFwd
top::TSF ::=
{
  top.tsXT3 = tsM();
}
}

-- An override in a production of this grammar is recorded at the decoration sites of the overridden
-- tree, although the grammar of the attribute's occurrence does not export this grammar (see
-- defLHSRecorded): through foSigX's equation for foX, foS depends on what the override on e (in
-- foUnrec) or on y (in foUnrecLocal) depends on.
synthesized attribute foX::String occurs on FO;
aspect production foLeaf
top::FO ::=
{
  top.foX = "";
}
production foSigX
top::FO ::= @a::FO
{
  top.foX = a.foK;
  forwards to foLeaf();
}
warnCode "Synthesized equation foS exceeds flow type with dependencies on flow:foM" {
production foUnrec
top::FO ::= e::FO
{
  e.foK = top.foM;
  top.foS = forward.foX;
  forwards to foSigX(e);
}
}
warnCode "Synthesized equation foS exceeds flow type with dependencies on flow:foM" {
production foUnrecLocal
top::FO ::=
{
  local y::FO = foLeaf();
  y.foK = top.foM;
  top.foS = forward.foX;
  forwards to foSigX(y);
}
}
