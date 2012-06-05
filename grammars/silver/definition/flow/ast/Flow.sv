grammar silver:definition:flow:ast;

import silver:definition:env only quoteString, unparse;

nonterminal FlowDefs with synTreeContribs, inhTreeContribs, defTreeContribs, fwdTreeContribs, fwdInhTreeContribs, unparses, prodTreeContribs, prodGraphContribs, refTreeContribs, localInhTreeContribs;
nonterminal FlowDef with synTreeContribs, inhTreeContribs, defTreeContribs, fwdTreeContribs, fwdInhTreeContribs, unparses, prodTreeContribs, prodGraphContribs, flowEdges, refTreeContribs, localInhTreeContribs;

{-- lookup (production, attribute) to find synthesized equations -}
synthesized attribute synTreeContribs :: [Pair<String FlowDef>];
{-- lookup (production, sig, attribute) to find inherited equation -}
synthesized attribute inhTreeContribs :: [Pair<String FlowDef>];
{-- lookup (nonterminal, attribute) to find default syn equations -}
synthesized attribute defTreeContribs :: [Pair<String FlowDef>];
{-- lookup (production) to find forward equations -}
synthesized attribute fwdTreeContribs :: [Pair<String FlowDef>];
{-- lookup (production, attr) to find forward INHERITED equations -}
synthesized attribute fwdInhTreeContribs :: [Pair<String FlowDef>];
{-- lookup (production, local, attr) to find local INHERITED equations -}
synthesized attribute localInhTreeContribs :: [Pair<String FlowDef>];
{-- lookup (nonterminal) to find all non-forwarding production -}
synthesized attribute prodTreeContribs :: [Pair<String FlowDef>];
{-- lookup (nonterminal) to find all inherited attributes in the host -}
synthesized attribute refTreeContribs :: [Pair<String FlowDef>];
{-- find all equations having to do DIRECTLY with a production
    (directly meaning e.g. no default equations, even if they might
    affect it) -}
synthesized attribute prodGraphContribs :: [Pair<String FlowDef>];
{-- Edge lists from equations -}
synthesized attribute flowEdges :: [Pair<FlowVertex FlowVertex>];

synthesized attribute unparses :: [String];

abstract production consFlow
top::FlowDefs ::= h::FlowDef  t::FlowDefs
{
  top.synTreeContribs = h.synTreeContribs ++ t.synTreeContribs;
  top.inhTreeContribs = h.inhTreeContribs ++ t.inhTreeContribs;
  top.defTreeContribs = h.defTreeContribs ++ t.defTreeContribs;
  top.fwdTreeContribs = h.fwdTreeContribs ++ t.fwdTreeContribs;
  top.fwdInhTreeContribs = h.fwdInhTreeContribs ++ t.fwdInhTreeContribs;
  top.prodTreeContribs = h.prodTreeContribs ++ t.prodTreeContribs;
  top.prodGraphContribs = h.prodGraphContribs ++ t.prodGraphContribs;
  top.refTreeContribs = h.refTreeContribs ++ t.refTreeContribs;
  top.localInhTreeContribs = h.localInhTreeContribs ++ t.localInhTreeContribs;
  top.unparses = h.unparses ++ t.unparses;
}

abstract production nilFlow
top::FlowDefs ::=
{
  top.synTreeContribs = [];
  top.inhTreeContribs = [];
  top.defTreeContribs = [];
  top.fwdTreeContribs = [];
  top.fwdInhTreeContribs = [];
  top.prodTreeContribs = [];
  top.prodGraphContribs = [];
  top.refTreeContribs = [];
  top.localInhTreeContribs = [];
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
  top.inhTreeContribs = [];
  top.defTreeContribs = [];
  top.fwdTreeContribs = [];
  top.fwdInhTreeContribs = [];
  top.prodTreeContribs = [];
  top.refTreeContribs = [];
  top.localInhTreeContribs = [];
}

{--
 - Declaration of a NON-FORWARDING production. Exists to allow lookups of productions
 - from nonterminal name.
 -
 - @param nt  The full name of the nonterminal it constructs
 - @param prod  The full name of the production
 -}
abstract production prodFlowDef
top::FlowDef ::= nt::String  prod::String
{
  top.prodTreeContribs = [pair(nt, top)];
  top.prodGraphContribs = [];
  top.flowEdges = error("Internal compiler error: this sort of def should not be in a context where edges are requested.");
  top.unparses = ["prod(" ++ quoteString(nt) ++ ", " ++ quoteString(prod) ++ ")"];
}

{--
 - Declaration of the inherited attributes known from the host language
 -}
abstract production ntRefFlowDef
top::FlowDef ::= nt::String  inhs::[String]
{
  top.refTreeContribs = [pair(nt, top)];
  top.prodGraphContribs = [];
  top.flowEdges = error("Internal compiler error: this sort of def should not be in a context where edges are requested.");
  top.unparses = error("TODO");
}

{--
 - The definition of a synthesized attribute in a production.
 -
 - @param prod  the full name of the production
 - @param attr  the full name of the attribute
 - @param deps  the dependencies of this equation on other flow graph elements
 - CONTRIBUTIONS ARE POSSIBLE
 -}
abstract production synEq
top::FlowDef ::= prod::String  attr::String  deps::[FlowVertex]
{
  top.synTreeContribs = [pair(crossnames(prod, attr), top)];
  top.prodGraphContribs = [pair(prod, top)];
  top.flowEdges = map(pair(lhsVertex(attr), _), deps);
  top.unparses = ["syn(" ++ implode(", ", [quoteString(prod), quoteString(attr), unparseVertices(deps)]) ++ ")"];
}

{--
 - The definition of a inherited attribute for a signature element in a production.
 -
 - @param prod  the full name of the production
 - @param sigName  the name of the RHS element
 - @param attr  the full name of the attribute
 - @param deps  the dependencies of this equation on other flow graph elements
 - CONTRIBUTIONS ARE POSSIBLE
 -}
abstract production inhEq
top::FlowDef ::= prod::String  sigName::String  attr::String  deps::[FlowVertex]
{
  top.inhTreeContribs = [pair(crossnames(prod, crossnames(sigName, attr)), top)];
  top.prodGraphContribs = [pair(prod, top)];
  top.flowEdges = map(pair(rhsVertex(sigName, attr), _), deps);
  top.unparses = ["inh(" ++ implode(", ", [quoteString(prod), quoteString(sigName), quoteString(attr), unparseVertices(deps)]) ++ ")"];
}

{--
 - The definition of a default equation for a synthesized attribute on a nonterminal.
 -
 - @param nt  the full name of the *nonterminal*
 - @param attr  the full name of the attribute
 - @param deps  the dependencies of this equation on other flow graph elements
 - CONTRIBUTIONS ARE POSSIBLE
 - TODO: rename defaultEq because this is confusingly named
 -}
abstract production defEq
top::FlowDef ::= nt::String  attr::String  deps::[FlowVertex]
{
  top.defTreeContribs = [pair(crossnames(nt, attr), top)];
  top.prodGraphContribs = []; -- defaults don't show up in the prod graph!!
  top.flowEdges = map(pair(lhsVertex(attr), _), deps);
  top.unparses = ["def(" ++ implode(", ", [quoteString(nt), quoteString(attr), unparseVertices(deps)]) ++ ")"];
}

{--
 - The definition of the forward of a production.
 -
 - @param prod  the full name of the production
 - @param deps  the dependencies of this equation on other flow graph elements
 - CONTRIBUTIONS ARE *NOT* repeat *NOT* POSSIBLE
 -}
abstract production fwdEq
top::FlowDef ::= prod::String  deps::[FlowVertex]
{
  top.fwdTreeContribs = [pair(prod, top)];
  top.prodGraphContribs = [pair(prod, top)];
  top.flowEdges = map(pair(forwardEqVertex(), _), deps);
  top.unparses = ["fwd(" ++ implode(", ", [quoteString(prod), unparseVertices(deps)]) ++ ")"];
}

{--
 - The definition of an inherited attribute on the forward
 -
 - @param prod  the full name of the production
 - @param attrName  the full name of the inherited attribute given to the forward
 - @param deps  the dependencies of this equation on other flow graph elements
 - CONTRIBUTIONS ARE POSSIBLE
 -}
abstract production fwdInhEq
top::FlowDef ::= prod::String  attr::String  deps::[FlowVertex]
{
  top.fwdInhTreeContribs = [pair(crossnames(prod, attr), top)];
  top.prodGraphContribs = [pair(prod, top)];
  top.flowEdges = map(pair(forwardVertex(attr), _), deps);
  top.unparses = ["fwdInh(" ++ implode(", ", [quoteString(prod), quoteString(attr), unparseVertices(deps)]) ++ ")"];
}

{--
 - The definition of a local or production attribute's equation.
 - MAY not be a nonterminal type!
 -
 - @param prod  the full name of the production
 - @param fName  the name of the local/production attribute
 - @param typeName  the full name of the type, or empty string if not a decorable type!
 - @param deps  the dependencies of this equation on other flow graph elements
 - CONTRIBUTIONS ARE POSSIBLE
 -}
abstract production localEq
top::FlowDef ::= prod::String  fName::String  typeName::String  deps::[FlowVertex]
{
  top.prodGraphContribs = [pair(prod, top)];
  top.flowEdges = map(pair(localEqVertex(fName), _), deps);
  top.unparses = ["local(" ++ implode(", ", [quoteString(prod), quoteString(fName), quoteString(typeName), unparseVertices(deps)]) ++ ")"];
}

{--
 - The definition of an inherited attribute for a local attribute.
 -
 - @param prod  the full name of the production
 - @param fName  the name of the local/production attribute
 - @param attr  the full name of the attribute
 - @param deps  the dependencies of this equation on other flow graph elements
 - CONTRIBUTIONS ARE POSSIBLE
 -}
abstract production localInhEq
top::FlowDef ::= prod::String  fName::String  attr::String  deps::[FlowVertex]
{
  top.localInhTreeContribs = [pair(crossnames(prod, crossnames(fName, attr)), top)];
  top.prodGraphContribs = [pair(prod, top)];
  top.flowEdges = map(pair(localVertex(fName, attr), _), deps);
  top.unparses = ["localInh(" ++ implode(", ", [quoteString(prod), quoteString(fName), quoteString(attr), unparseVertices(deps)]) ++ ")"];
}

{--
 - Used for contributions to collections. Allows tacking on dependencies
 - to vertices.
 -
 - @param prod  the full name of the production
 - @param src  the vertex to add dependencies to
 - @param deps  the dependencies of this vertex
 -}
abstract production extraEq
top::FlowDef ::= prod::String  src::FlowVertex  deps::[FlowVertex]
{
  top.prodGraphContribs = [pair(prod, top)];
  top.flowEdges = map(pair(src, _), deps);
  top.unparses = ["extra(" ++ implode(", ", [quoteString(prod), src.unparse, unparseVertices(deps)]) ++ ")"];
}

--

function crossnames
String ::= a::String b::String
{
  return a ++ " @ " ++ b;
}

--

{--
 - Data structure representing vertices in the flow graph within a single production.
 -}
nonterminal FlowVertex with unparse;

{--
 - A vertex representing an attribute on the nonterminal being constructed by this production.
 -
 - @param attrName  the full name of an attribute on the lhs.
 -}
abstract production lhsVertex
top::FlowVertex ::= attrName::String
{
  top.unparse = "lhsV(" ++ quoteString(attrName) ++ ")";
}

{--
 - A vertex representing an attribute on an element of the signature RHS.
 -
 - @param sigName  the name given to a signature nonterminal.
 - @param attrName  the full name of an attribute on that signature element.
 -}
abstract production rhsVertex
top::FlowVertex ::= sigName::String  attrName::String
{
  top.unparse = "rhsV(" ++ quoteString(sigName) ++ ", " ++ quoteString(attrName) ++ ")";
}

{--
 - A vertex representing a local equation. i.e. forward, local attribute, production
 - attribute, etc.  Note that this may be defined for MORE than just those with
 - nonterminal type!! (e.g. local foo :: String  will appear!)
 -
 - @param fName  the full name of the NTA/FWD being defined
 -}
abstract production localEqVertex
top::FlowVertex ::= fName::String
{
  top.unparse = "localEqV(" ++ quoteString(fName) ++ ")";
}

{--
 - A vertex representing an attribute on a local equation. i.e. forward, local
 - attribute, production attribute, etc.  Note this this implies the equation
 - above IS a nonterminal type!!
 -
 - @param fName  the full name of the NTA/FWD
 - @param attrName  the fulle name of the attribute on that element
 -}
abstract production localVertex
top::FlowVertex ::= fName::String  attrName::String
{
  top.unparse = "localV(" ++ quoteString(fName) ++ ", " ++ quoteString(attrName) ++ ")";
}


function forwardEqVertex
FlowVertex ::=
{
  return localEqVertex("forward");
}
function forwardVertex
FlowVertex ::= attrName::String
{
  return localVertex("forward", attrName);
}

function unparseVertices
String ::= fvs::[FlowVertex]
{
  return "[" ++ implode(", ", map((.unparse), fvs)) ++ "]";
}

