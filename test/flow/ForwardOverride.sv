grammar flow;

-- An override equation on a tree that becomes the forward hides the forward's equation for the same
-- attribute.  As the flow type of an extension synthesized attribute need only contain the forward
-- flow type, the override may depend only on the forward flow type and on the attribute itself, as
-- forward.foK = ... may (see overrideBounds).

inherited attribute foK::String;
inherited attribute foF::String;
inherited attribute foM::String;
synthesized attribute foS::String;
nonterminal FO with foK, foF, foM, foS;
flowtype FO = decorate {}, forward {foF}, foS {foK};

production foLeaf
top::FO ::=
{ top.foS = top.foK; }

inherited attribute foC::[String] with ++;
attribute foC occurs on FO;

translation attribute foT::FO;
nonterminal FOB with foF, foM, foT;
flowtype FOB = decorate {}, forward {foF};

production fobLeaf
top::FOB ::=
{ top.foT = foLeaf(); }

warnCode "Inherited override equation for flow:foK on child e may exceed a flow type with hidden transitive dependencies on flow:foM; on some reference to this tree, this attribute may be expected to depend only on flow:foK (from forward)" {
production foRoot
top::FO ::= e::FO
{
  e.foK = top.foM;
  top.foS = "";
  forwards to @e;
}
}

-- The forward flow type is allowed, and foM is not.
warnCode "Inherited override equation for flow:foK on child e may exceed a flow type with hidden transitive dependencies on flow:foM; on some reference to this tree, this attribute may be expected to depend only on flow:foF, flow:foK (from forward)" {
production foRootMixed
top::FO ::= e::FO
{
  e.foK = top.foF ++ top.foM;
  top.foS = "";
  forwards to @e;
}
}

-- The same where e becomes the forward in one branch only.
warnCode "Inherited override equation for flow:foK on child e may exceed a flow type with hidden transitive dependencies on flow:foM; on some reference to this tree, this attribute may be expected to depend only on flow:foF, flow:foK (from forward)" {
production foRootCond
top::FO ::= e::FO
{
  e.foK = top.foM;
  top.foS = "";
  forwards to if top.foF == "" then @e else foLeaf();
}
}

-- The same for a child shared into a local that is shared at the forward...
warnCode "Inherited override equation for flow:foK on child e may exceed a flow type with hidden transitive dependencies on flow:foM; on some reference to this tree, this attribute may be expected to depend only on flow:foK (from local flow:foRootLocal:local:" {
production foRootLocal
top::FO ::= e::FO
{
  e.foK = top.foM;
  local y::FO = @e;
  top.foS = "";
  forwards to @y;
}
}
-- ...and through two locals.
warnCode "Inherited override equation for flow:foK on child e may exceed a flow type with hidden transitive dependencies on flow:foM; on some reference to this tree, this attribute may be expected to depend only on flow:foK (from local flow:foRootChain:local:" {
production foRootChain
top::FO ::= e::FO
{
  e.foK = top.foM;
  local y::FO = @e;
  local z::FO = @y;
  top.foS = "";
  forwards to @z;
}
}

-- The same for an equation on a local that becomes the forward.
warnCode "may exceed a flow type with hidden transitive dependencies on flow:foM; on some reference to this tree, this attribute may be expected to depend only on flow:foK (from forward)" {
production foLocalEq
top::FO ::=
{
  local y::FO = foLeaf();
  y.foK = top.foM;
  top.foS = "";
  forwards to @y;
}
}

-- The same for a collection attribute, with a base equation and with a contribution.
warnCode "Inherited override equation for flow:foC on child e may exceed a flow type with hidden transitive dependencies on flow:foM; on some reference to this tree, this attribute may be expected to depend only on flow:foC (from forward)" {
production foCollBase
top::FO ::= e::FO
{
  e.foC := [top.foM];
  top.foS = "";
  forwards to @e;
}
}
warnCode "Inherited contribution equation for flow:foC on child e may exceed a flow type with hidden transitive dependencies on flow:foM; on some reference to this tree, this attribute may be expected to depend only on flow:foC (from forward); flow:foM is excluded by the forward flow type of flow:FO" {
production foCollAppend
top::FO ::= e::FO
{
  e.foC := [top.foM];
  e.foC <- [top.foM];
  top.foS = "";
  forwards to @e;
}
}

-- The same for an inherited attribute of a translation attribute of a tree that becomes the
-- forward...
warnCode "Inherited override equation for flow:foT.flow:foK on child e may exceed a flow type with hidden transitive dependencies on flow:foM; on some reference to this tree, this attribute may be expected to depend only on flow:foT.flow:foK (from forward)" {
production foTransRoot
top::FOB ::= e::FOB
{
  e.foT.foK = top.foM;
  forwards to @e;
}
}
-- ...though this production may not define its own foT, as it forwards...
warnCode "Translation attribute flow:foT is defined in production flow:foTransRootOwnT, which forwards; a forwarding production must take its translation attributes from its forward" {
production foTransRootOwnT
top::FOB ::= e::FOB
{
  e.foT.foK = top.foM;
  top.foT = foLeaf();
  forwards to @e;
}
}
-- ...and for a translation attribute of a local that becomes the forward.
warnCode "Inherited override equation for flow:foK on translation attribute flow:foT of child g may exceed a flow type with hidden transitive dependencies on flow:foM; on some reference to this tree, this attribute may be expected to depend only on flow:foF, flow:foK (from forward)" {
production foTransLocal
top::FO ::= g::FOB
{
  g.foT.foK = top.foM;
  local y::FOB = @g;
  top.foS = "";
  forwards to @y.foT;
}
}

-- An override may depend on the attribute itself (foCopy) and on the forward flow type
-- (foFromForwardFlowType), and a tree that does not become the forward is not bounded by the
-- forward flow type (foLocalOnly).
noWarnCode "hidden transitive" {
production foCopy
top::FO ::= e::FO
{
  e.foK = top.foK;
  top.foS = "";
  forwards to @e;
}
production foFromForwardFlowType
top::FO ::= e::FO
{
  e.foK = top.foF;
  top.foS = "";
  forwards to @e;
}
production foLocalOnly
top::FO ::= e::FO
{
  e.foK = top.foM;
  local y::FO = @e;
  top.foS = "";
  forwards to foLeaf();
}
}

-- The forward rule is the first bound a witness looks at, and one clause covers all the attributes
-- it excludes.
warnCode "Inherited override equation for flow:foK on child e may exceed a flow type with hidden transitive dependencies on flow:foC, flow:foM; on some reference to this tree, this attribute may be expected to depend only on flow:foK (from forward); all are excluded by the forward flow type of flow:FO" {
production foRootTwo
top::FO ::= e::FO
{
  e.foK = top.foM ++ implode("", top.foC);
  top.foS = "";
  forwards to @e;
}
}

-- A contribution on a shared tree is recorded at the decoration sites of that tree, as the base
-- equation for the attribute is (see contributionDecSiteEdges): through fcSigN's equation for fcN,
-- fcCollSig's fcS depends on what the contribution on e depends on, and fcCollLocal's fcS on what
-- the contribution on y depends on.
inherited attribute fcK::String;
inherited attribute fcF::String;
inherited attribute fcC::[String] with ++;
synthesized attribute fcS::String;
synthesized attribute fcN::String;
nonterminal FC with fcK, fcF, fcC, fcS, fcN;
flowtype FC = decorate {}, forward {}, fcS {fcK};

production fcLeaf
top::FC ::=
{
  top.fcS = top.fcK;
  top.fcN = "";
}
production fcSigN
top::FC ::= @a::FC
{
  top.fcS = "";
  top.fcN = implode(",", a.fcC);
}
warnCode "Synthesized equation fcS exceeds flow type with dependencies on flow:fcF" {
production fcCollSig
top::FC ::= e::FC
{
  e.fcC := [];
  e.fcC <- [top.fcF];
  top.fcS = forward.fcN;
  forwards to fcSigN(e);
}
}
warnCode "Synthesized equation fcS exceeds flow type with dependencies on flow:fcF" {
production fcCollLocal
top::FC ::=
{
  local y::FC = fcLeaf();
  y.fcC := [];
  y.fcC <- [top.fcF];
  top.fcS = forward.fcN;
  forwards to fcSigN(y);
}
}
