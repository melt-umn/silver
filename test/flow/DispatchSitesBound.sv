grammar flow;

-- An equation for an inherited attribute of a tree, before the tree is shared with an application
-- of a dispatch signature, may depend only on what implementations may supply in its place (see
-- unknownImplSets).  Following what a production applied around the application supplies itself
-- leaves out the sharing site's own equations for the tree, which that production's flow types do
-- not include (see mapApplied).

inherited attribute dnA::String;
inherited attribute dnI::String;
inherited attribute dnM::String;
synthesized attribute dnB::String;
synthesized attribute dnS::String;
nonterminal DNC with dnA, dnB;
nonterminal DNE with dnI, dnM, dnS;
flowtype dnB {dnA} on DNC;
flowtype DNE = decorate {}, forward {dnI}, dnS {dnI};

production dncLeaf
top::DNC ::=
{ top.dnB = top.dnA; }

-- An implementation in another grammar may supply a.dnA from the forward flow type, {dnI}.
dispatch DND = DNE ::= a::DNC;

production dndImpl implements DND
top::DNE ::= a::DNC
{
  a.dnA = "";
  top.dnS = a.dnB;
}

-- dnOuter supplies c.dnI from c.dnS, which dndImpl gives from a.dnB.
production dnOuter
top::DNE ::= c::DNE
{
  c.dnI = c.dnS;
  top.dnS = "";
}

-- Following dnOuter's c.dnI through the application back to x.dnA would allow anything.
warnCode "Inherited override equation for flow:dnA on child x may exceed a flow type with hidden transitive dependencies on flow:dnM; on some reference to this tree, this attribute may be expected to depend on no left-side inherited attributes (from forward[flow:dnOuter:c][flow:DND:a]); flow:dnM is excluded by what flow:DND allows" {
production dnSite
top::DNE ::= x::DNC
{
  x.dnA = top.dnM;
  local prod::DND = dndImpl;
  forwards to dnOuter(prod(@x));
}
}

-- The same where a local holds the application.
warnCode "Inherited override equation for flow:dnA on child x may exceed a flow type with hidden transitive dependencies on flow:dnM; on some reference to this tree, this attribute may be expected to depend on no left-side inherited attributes" {
production dnLocal
top::DNE ::= x::DNC
{
  x.dnA = top.dnM;
  local prod::DND = dndImpl;
  local y::DNE = prod(@x);
  forwards to dnOuter(@y);
}
}

inherited attribute dpA::String;
inherited attribute dpI::String;
inherited attribute dpM::String;
synthesized attribute dpB::String;
synthesized attribute dpS::String;
nonterminal DPC with dpA, dpB;
nonterminal DPE with dpI, dpM, dpS;
flowtype dpB {dpA} on DPC;
flowtype DPE = decorate {}, forward {dpI}, dpS {dpI};

production dpcLeaf
top::DPC ::=
{ top.dpB = top.dpA; }

dispatch DPD = DPE ::= a::DPC;

production dpdImpl implements DPD
top::DPE ::= a::DPC
{
  a.dpA = "";
  top.dpS = a.dpB;
}

production dpOuter
top::DPE ::= c::DPE
{
  c.dpI = c.dpS;
  top.dpS = "";
}

-- The same where the tree comes through dpPass's signature: dpPass has no equations for it, and
-- dpSite's own equation, which the tree keeps, is bounded the same way.
production dpPass
top::DPE ::= @a::DPC
{
  local prod::DPD = dpdImpl;
  forwards to dpOuter(prod(@a));
}
warnCode "Inherited override equation for flow:dpA on child x may exceed a flow type with hidden transitive dependencies on flow:dpM; on some reference to this tree, this attribute may be expected to depend on no left-side inherited attributes (from forward[flow:dpPass:a])" {
production dpSite
top::DPE ::= x::DPC
{
  x.dpA = top.dpM;
  forwards to dpPass(x);
}
}

inherited attribute fqA::String;
synthesized attribute fqS::String;
nonterminal FQ with fqA, fqS;

production fqLeaf
top::FQ ::=
{ top.fqS = top.fqA; }

dispatch FQD = FQ ::= @a::FQ;

production fqImpl implements FQD
top::FQ ::= @a::FQ
{ top.fqS = a.fqS; }

-- A function sharing a tree with a signature-shared child is reported rather than stopping flow
-- type inference, which projects what an application supplies to that child from the applying
-- production's graph: a function has none.
warnCode "Dispatch flow:FQD has shared children in its signature, and can only be applied in the root position of a forward or forward production attribute equation" {
function fqFun
String ::= c::FQ
{
  c.fqA = "";
  local l::FQ = fqImpl(c);
  return l.fqS;
}
}
