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
