grammar flow;

-- A forward production attribute shared as the forward is that attribute's tree, whose forward
-- parent is already this production: the forward's synthesized and inherited attributes are the
-- attribute's (see addSharingEqs and addDecSiteTreeEqs).  Decorating the tree as the forward
-- supplies nothing to it, except that its translation attributes then get the inherited attributes
-- on this production's (see test/silver_features/TransFwdProdAttr.sv).

inherited attribute fpfK1::String;
inherited attribute fpfK2::String;
synthesized attribute fpfS::String;
synthesized attribute fpfT2::String;
nonterminal FPF with fpfK1, fpfK2, fpfS, fpfT2;
flowtype FPF = forward {fpfK1}, fpfS {fpfK2}, fpfT2 {};
production fpfBase
top::FPF ::=
{
  top.fpfS = top.fpfK2;
  top.fpfT2 = "";
}
production fpfConst
top::FPF ::=
{
  top.fpfS = "";
  top.fpfT2 = "";
}

-- The forward's fpfS is x's, which depends on x's fpfK2, which this production supplies from fpfK1.
warnCode "In production flow:fpfFwd, the implicit copy equation for flow:fpfS (due to forwarding) would exceed the attribute's flow type with dependencies on flow:fpfK1" {
production fpfFwd
top::FPF ::=
{
  forward x = fpfBase();
  x.fpfK2 = top.fpfK1;
  forwards to @x;
}
}
-- The shape in real code, shared in one branch: accessing fpfS needs x's fpfK2, which x gets from
-- this production.
production fpfCond
top::FPF ::= b::Boolean
{
  forward x = fpfBase();
  forwards to if b then @x else fpfConst();
}
warnCode "Access of synthesized attribute fpfS on c requires missing inherited attribute(s) flow:fpfK2" {
production fpfCondUse
top::FPF ::=
{
  local c::FPF = fpfCond(true);
  top.fpfS = c.fpfS;
  top.fpfT2 = "";
}
}
-- A chain of forward production attributes (forward y = @x) is not allowed (see decorationSiteExpr
-- in Inh.sv).
warnCode "Forward production attribute flow:fpfChain:local:flow:x may only be shared in a forward decoration site" {
production fpfChain
top::FPF ::=
{
  forward x = fpfBase();
  x.fpfK2 = top.fpfK1;
  forward y = @x;
  forwards to @y;
}
}
-- The forward's fpfK2 is x's, which x copies from this production: the forward equation has no
-- effect.
warnCode "Synthesized equation fpfT2 exceeds flow type with dependencies on flow:fpfK2" {
production fpfDeadFwdInh
top::FPF ::=
{
  forward x = fpfBase();
  forward.fpfK2 = "";
  top.fpfT2 = forward.fpfK2;
  forwards to @x;
}
}

-- So such equations have no effect where a forward production attribute is shared unconditionally
-- (a plain warning)...
warnCode "Inherited equation for flow:fpfK2 on forward has no effect" {
production fpfNoEffectFwd
top::FPF ::=
{
  forward x = fpfBase();
  forward.fpfK2 = "";
  forwards to @x;
}
}
warnCode "Inherited equation for flow:fpfK2 on forward has no effect" {
production fpfNoEffectWith
top::FPF ::=
{
  forward x = fpfBase();
  forwards to @x;
  forwarding with { fpfK2 = ""; };
}
}
warnCode "Forward production attribute flow:fpfNoEffectChain:local:flow:x may only be shared in a forward decoration site" {
production fpfNoEffectChain
top::FPF ::=
{
  forward x = fpfBase();
  forward y = @x;
  y.fpfK2 = "";
  forwards to @y;
}
}
-- ...but not where it is shared in one branch only.
noWarnCode "has no effect" {
production fpfEffectCond
top::FPF ::= b::Boolean
{
  forward x = fpfBase();
  forward.fpfK2 = "";
  forwards to if b then @x else fpfConst();
}
}

-- Translation attributes.  The forward's fptT is this production's own, as for any forward, and x's
-- tree gets this production's fptT.fptK through the forward.
inherited attribute fptK::String;
synthesized attribute fptV::String;
nonterminal FPTT with fptK, fptV;
production fptLeaf
top::FPTT ::=
{
  top.fptV = top.fptK;
}
synthesized attribute fptS::String;
translation attribute fptT::FPTT;
nonterminal FPT with fptS, fptT;
production fptQ
top::FPT ::=
{
  top.fptT = fptLeaf();
  top.fptS = top.fptT.fptV;
}

-- Accessing fptT.fptV on an application of fptLhsTrans goes through x's tree to the fptT.fptK the
-- application is given.
production fptLhsTrans
top::FPT ::=
{
  forward x = fptQ();
  top.fptS = "";
  forwards to @x;
}
warnCode "Access of synthesized attribute fptV on c.fptT requires missing inherited attribute(s) flow:fptK" {
production fptLhsTransUse
top::FPF ::=
{
  local c::FPT = fptLhsTrans();
  top.fpfS = c.fptT.fptV;
  top.fpfT2 = "";
}
}
noWarnCode "requires missing inherited attribute" {
production fptLhsTransUseK
top::FPF ::=
{
  local c::FPT = fptLhsTrans();
  c.fptT.fptK = "";
  top.fpfS = c.fptT.fptV;
  top.fpfT2 = "";
}
}

-- Accessing x directly may happen before the forward is evaluated, so x's fptT.fptK comes only from
-- x's own equations and the trees shared into x (see findDecSitesChain)...
warnCode "Access of synthesized attribute fptS on x requires missing inherited attribute(s) flow:fptT.flow:fptK" {
production fptXAccess
top::FPT ::=
{
  forward x = fptQ();
  top.fptS = x.fptS;
  forwards to @x;
}
}
-- ...while through the forward it has this production's.
noWarnCode "requires missing inherited attribute" {
production fptFwdInhAccess
top::FPT ::=
{
  forward x = fptQ();
  top.fptS = forward.fptT.fptK;
  forwards to @x;
}
production fptFwdTrans
top::FPT ::=
{
  forward x = fptQ();
  top.fptS = forward.fptT.fptV;
  forwards to @x;
}
production fptCopy
top::FPT ::=
{
  forward x = fptQ();
  forwards to @x;
}
production fptExplicit
top::FPT ::=
{
  forward x = fptQ();
  x.fptT.fptK = "";
  top.fptS = x.fptS ++ forward.fptT.fptV;
  forwards to @x;
}
}
-- A chain of forward production attributes (forward y = @x) is not allowed.
warnCode "Forward production attribute flow:fptChain:local:flow:x may only be shared in a forward decoration site" {
production fptChain
top::FPT ::=
{
  forward x = fptQ();
  forward y = @x;
  forwards to @y;
}
}
warnCode "Forward production attribute flow:fptChainDead:local:flow:x may only be shared in a forward decoration site" {
production fptChainDead
top::FPT ::=
{
  forward x = fptQ();
  forward y = @x;
  y.fptT.fptK = top.fptT.fptK;
  top.fptS = y.fptT.fptV;
  forwards to @y;
}
}
-- A forwarding production may not define fptT itself (see FwdTransBan.sv).
warnCode "Translation attribute flow:fptT is defined in production flow:fptOwnCopy, which forwards; a forwarding production must take its translation attributes from its forward" {
production fptOwnCopy
top::FPT ::=
{
  forward x = fptQ();
  top.fptT = fptLeaf();
  forwards to @x;
}
}
