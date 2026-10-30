grammar flow;

-- The root equation vertex of a hole (a local, forward or translation attribute whose
-- tree is not a production application) must carry the hole's dependencies, and the root
-- of a translation attribute from another production must depend on the attribute itself.
-- Otherwise a synthesized attribute read from such a tree does not pick up what building
-- the tree needs, and flow type inference misses it.

nonterminal RDExprL;
nonterminal RDExprF;
nonterminal RDExprT;
nonterminal RDRows;
inherited attribute rdEnv::Integer occurs on RDExprL, RDExprF, RDExprT;
inherited attribute rdExtra::Integer occurs on RDExprL, RDExprF, RDExprT;
synthesized attribute rdType::Integer occurs on RDExprL, RDExprF, RDExprT;

-- Hole local: x is built by a function from rdExtra, and only x.rdType is read.
abstract production rdLitL
top::RDExprL ::= i::Integer
{ top.rdType = i + top.rdEnv; }

function rdMkL
RDExprL ::= i::Integer
{ return rdLitL(i); }

abstract production rdHoleLocal
top::RDExprL ::=
{
  local x::RDExprL = rdMkL(top.rdExtra);
  x.rdEnv = top.rdEnv;
  x.rdExtra = top.rdExtra;
  top.rdType = x.rdType;
}

warnCode "Access of synthesized attribute rdType on e requires missing inherited attribute(s) flow:rdExtra to be supplied to child e of production flow:rdUserL" {
abstract production rdUserL
top::RDExprL ::= e::RDExprL
{
  e.rdEnv = top.rdEnv;
  top.rdType = e.rdType;
}
}

-- Hole forward: the forward is built by a function from rdExtra.
abstract production rdLitF
top::RDExprF ::= i::Integer
{ top.rdType = i + top.rdEnv; }

function rdMkF
RDExprF ::= i::Integer
{ return rdLitF(i); }

abstract production rdHoleFwd
top::RDExprF ::=
{
  forwards to rdMkF(top.rdExtra);
}

warnCode "Access of synthesized attribute rdType on e requires missing inherited attribute(s) flow:rdExtra to be supplied to child e of production flow:rdUserF" {
abstract production rdUserF
top::RDExprF ::= e::RDExprF
{
  e.rdEnv = top.rdEnv;
  top.rdType = e.rdType;
}
}

-- Translation attribute from another production: building rows.rdTrans needs rows.rdConds,
-- which rdParentT supplies from rdExtra, and only rows.rdTrans.rdType is read.
abstract production rdLitT
top::RDExprT ::= i::Integer
{ top.rdType = i + top.rdEnv; }

abstract production rdPlusT
top::RDExprT ::= e1::RDExprT e2::RDExprT
{
  e1.rdEnv = top.rdEnv;
  e2.rdEnv = top.rdEnv;
  e1.rdExtra = top.rdExtra;
  e2.rdExtra = top.rdExtra;
  top.rdType = e1.rdType + e2.rdType;
}

inherited attribute rdConds::Integer occurs on RDRows;
translation attribute rdTrans::RDExprT occurs on RDRows;

abstract production rdCons
top::RDRows ::= e::RDExprT rest::RDRows
{
  rest.rdConds = top.rdConds + 1;
  top.rdTrans = rdPlusT(@e, @rest.rdTrans);
}

abstract production rdNil
top::RDRows ::=
{
  top.rdTrans = rdLitT(top.rdConds);
}

abstract production rdParentT
top::RDExprT ::= rows::RDRows
{
  rows.rdConds = top.rdExtra;
  rows.rdTrans.rdEnv = top.rdEnv;
  rows.rdTrans.rdExtra = top.rdExtra;
  top.rdType = rows.rdTrans.rdType;
}

warnCode "Access of synthesized attribute rdType on e requires missing inherited attribute(s) flow:rdExtra to be supplied to child e of production flow:rdUserT" {
abstract production rdUserT
top::RDExprT ::= e::RDExprT
{
  e.rdEnv = top.rdEnv;
  top.rdType = e.rdType;
}
}

-- Sharing at a decoration site: the forward is the shared local x, built by a function from
-- rdExtra, so the forward's root depends on building x.
nonterminal RDExprS;
attribute rdEnv, rdExtra, rdType occurs on RDExprS;

abstract production rdLitS
top::RDExprS ::= i::Integer
{ top.rdType = i + top.rdEnv; }

function rdMkS
RDExprS ::= i::Integer
{ return rdLitS(i); }

abstract production rdShareLocal
top::RDExprS ::=
{
  local x::RDExprS = rdMkS(top.rdExtra);
  x.rdEnv = top.rdEnv;
  x.rdExtra = top.rdExtra;
  forwards to @x;
}

warnCode "Access of synthesized attribute rdType on e requires missing inherited attribute(s) flow:rdExtra to be supplied to child e of production flow:rdUserS" {
abstract production rdUserS
top::RDExprS ::= e::RDExprS
{
  e.rdEnv = top.rdEnv;
  top.rdType = e.rdType;
}
}

-- An inherited attribute read on a sharing site: the site is the shared local x, so reading
-- anything on it needs x built, which needs rdExtra.
nonterminal RDExprI;
attribute rdEnv, rdExtra, rdType occurs on RDExprI;

abstract production rdLitI
top::RDExprI ::= i::Integer
{ top.rdType = i + top.rdEnv; }

function rdMkI
RDExprI ::= i::Integer
{ return rdLitI(i); }

abstract production rdShareInh
top::RDExprI ::=
{
  local x::RDExprI = rdMkI(top.rdExtra);
  x.rdEnv = top.rdEnv;
  x.rdExtra = top.rdExtra;
  forwards to @x;
  top.rdType = forward.rdEnv;
}

warnCode "Access of synthesized attribute rdType on e requires missing inherited attribute(s) flow:rdExtra to be supplied to child e of production flow:rdUserI" {
abstract production rdUserI
top::RDExprI ::= e::RDExprI
{
  e.rdEnv = top.rdEnv;
  top.rdType = e.rdType;
}
}
