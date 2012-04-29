grammar silver:definition:flow:ast;

import silver:definition:env only quoteString;

nonterminal FlowDefs with synTreeContribs, defTreeContribs, fwdTreeContribs, unparses;
nonterminal FlowDef with synTreeContribs, defTreeContribs, fwdTreeContribs, unparses;

synthesized attribute synTreeContribs :: [Pair<String FlowDef>];
synthesized attribute defTreeContribs :: [Pair<String FlowDef>];
synthesized attribute fwdTreeContribs :: [Pair<String FlowDef>];
synthesized attribute unparses :: [String];

abstract production consFlow
top::FlowDefs ::= h::FlowDef  t::FlowDefs
{
  top.synTreeContribs = h.synTreeContribs ++ t.synTreeContribs;
  top.defTreeContribs = h.defTreeContribs ++ t.defTreeContribs;
  top.fwdTreeContribs = h.fwdTreeContribs ++ t.fwdTreeContribs;
  top.unparses = h.unparses ++ t.unparses;
}

abstract production nilFlow
top::FlowDefs ::=
{
  top.synTreeContribs = [];
  top.defTreeContribs = [];
  top.fwdTreeContribs = [];
  top.unparses = [];
}

-- At the time of writing, this is one giant work in progress.
-- Currently, all we're going to report is whether a synthesized
-- equation EXISTS or whether a production forwards at all.
-- This will be implemented in such a way that it returns the
-- FlowDef, but presently that has no special information.

aspect default production
top::FlowDef ::=
{
  top.synTreeContribs = [];
  top.defTreeContribs = [];
  top.fwdTreeContribs = [];
}

abstract production synEq
top::FlowDef ::= prod::String  attr::String
{
  top.synTreeContribs = [pair(crossnames(prod, attr), top)];
  top.unparses = ["syn(" ++ quoteString(prod) ++ ", " ++ quoteString(attr) ++ ")"];
}

abstract production defEq
top::FlowDef ::= nt::String  attr::String
{
  top.defTreeContribs = [pair(crossnames(nt, attr), top)];
  top.unparses = ["def(" ++ quoteString(nt) ++ ", " ++ quoteString(attr) ++ ")"];
}

abstract production fwdEq
top::FlowDef ::= prod::String
{
  top.fwdTreeContribs = [pair(prod, top)];
  top.unparses = ["fwd(" ++ quoteString(prod) ++ ")"];
}

--

function crossnames
String ::= a::String b::String
{
  return a ++ " @ " ++ b;
}
