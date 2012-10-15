grammar silver:definition:flow:ast;

imports silver:definition:env only quoteString,unparseStrings, unparse;

nonterminal FlowDefs with synTreeContribs, inhTreeContribs, defTreeContribs, fwdTreeContribs, fwdInhTreeContribs, unparses, prodTreeContribs, prodGraphContribs, refTreeContribs, localInhTreeContribs, nonHostSynAttrs, localTreeContribs;
nonterminal FlowDef with synTreeContribs, inhTreeContribs, defTreeContribs, fwdTreeContribs, fwdInhTreeContribs, unparses, prodTreeContribs, prodGraphContribs, flowEdges, refTreeContribs, localInhTreeContribs, suspectFlowEdges, nonHostSynAttrs, localTreeContribs;

{-- lookup (production, attribute) to find synthesized equations
 - Used to ensure a necessary lhs.syn equation exists.
 - Also decides whether to add a forward or default equation while computing flow types. -}
synthesized attribute synTreeContribs :: [Pair<String FlowDef>];

{-- lookup (production, sig, attribute) to find inherited equation
 - Used to ensure a necessary rhs.inh equation exists.
 - Also decides whether to add a copy equation for autocopy attributes to rhs elements. -}
synthesized attribute inhTreeContribs :: [Pair<String FlowDef>];

{-- lookup (nonterminal, attribute) to find default syn equations
 - Used to obtain default equation dependencies, when it exists. -}
synthesized attribute defTreeContribs :: [Pair<String FlowDef>];

{-- lookup (production) to find forward equations.
 - Decides whether default or forward equations should be added. -}
synthesized attribute fwdTreeContribs :: [Pair<String FlowDef>];

{-- lookup (production, attr) to find forward INHERITED equations
 - Used to ensure equations for inherited attributes exist for all inh of a fwd. -}
synthesized attribute fwdInhTreeContribs :: [Pair<String FlowDef>];

{-- lookup (production, local, attr) to find local INHERITED equations.
 - ONLY used to check whether an equation exists. -}
synthesized attribute localInhTreeContribs :: [Pair<String FlowDef>];

{-- lookup (production, local) to find the local equation -}
synthesized attribute localTreeContribs :: [Pair<String FlowDef>];

{-- lookup (nonterminal) to find all non-forwarding production.
 - ONLY used to determine all productions that need an equation for a new attribute. -}
synthesized attribute prodTreeContribs :: [Pair<String FlowDef>];

{-- lookup (nonterminal) to find all inherited attributes in the host -}
synthesized attribute refTreeContribs :: [Pair<String FlowDef>];

{-- find all equations having to do DIRECTLY with a production
    (directly meaning e.g. no default equations, even if they might
    affect it)  These FlowDefs MUST have a flowEdges for this production. -}
synthesized attribute prodGraphContribs :: [Pair<String FlowDef>];

{-- Edge lists from equations
 - ONLY used to extract edges for a production graph from production-internal flowDefs. -}
synthesized attribute flowEdges :: [Pair<FlowVertex FlowVertex>];

{-- Like flowEdges, but these edges originate from equations that are not
 - allowed to affect their OWN flow type.  We must still track them because
 - they may affect others' flow types.
 - (e.g.  extsyn = hostsyn; hostsyn = hostinh; need to reflect extsyn's dep on hostinh) -}
synthesized attribute suspectFlowEdges :: [Pair<FlowVertex FlowVertex>];

{-- A list of non-host synthesized occurrences, to patch up flow types -}
synthesized attribute nonHostSynAttrs :: [FlowDef];

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
  top.localTreeContribs = h.localTreeContribs ++ t.localTreeContribs;
  top.nonHostSynAttrs = h.nonHostSynAttrs ++ t.nonHostSynAttrs;
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
  top.localTreeContribs = [];
  top.nonHostSynAttrs = [];
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
  top.localTreeContribs = [];
  top.nonHostSynAttrs = [];
  top.suspectFlowEdges = []; -- flowEdges is required, but suspect is typically not!
  -- require unparses, prodGraphContibs
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
  top.unparses = ["ntRefFlowDef(" ++quoteString(nt)++ ", " ++ unparseStrings(inhs) ++ ")"];
}

{--
 - Declaration that a synthesized attribute occurrence is not in the host
 - and therefore subject to the forward flow type's whims.
 - @param attr  the full name of the synthesized attribute
 - @param nt  the full name of the nonterminal
 -}
abstract production nonHostSynDef
top::FlowDef ::= attr::String  nt::String
{
  top.nonHostSynAttrs = [top];
  top.prodGraphContribs = [];
  top.flowEdges = error("Internal compiler error: this sort of def should not be in a context where edges are requested.");
  top.unparses = ["nonHostSyn(" ++ quoteString(attr) ++ ", " ++ quoteString(nt) ++ ")"];
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
top::FlowDef ::= prod::String  attr::String  deps::[FlowVertex]  mayAffectFlowType::Boolean
{
  top.synTreeContribs = [pair(crossnames(prod, attr), top)];
  top.prodGraphContribs = [pair(prod, top)];
  local edges :: [Pair<FlowVertex FlowVertex>] = map(pair(lhsSynVertex(attr), _), deps);
  top.flowEdges = if mayAffectFlowType then edges else [];
  top.suspectFlowEdges = if mayAffectFlowType then [] else edges;
  
  top.unparses = ["syn(" ++ implode(", ", [quoteString(prod), quoteString(attr), unparseVertices(deps),if mayAffectFlowType then "t" else "f"]) ++ ")"];
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
  top.flowEdges = map(pair(lhsSynVertex(attr), _), deps); -- but their edges WILL end up added to graphs in fixup-phase!!
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
top::FlowDef ::= prod::String  deps::[FlowVertex]  mayAffectFlowType::Boolean
{
  top.fwdTreeContribs = [pair(prod, top)];
  top.prodGraphContribs = [pair(prod, top)];
  local edges :: [Pair<FlowVertex FlowVertex>] = map(pair(forwardEqVertex(), _), deps);
  top.flowEdges = if mayAffectFlowType then edges else [];
  top.suspectFlowEdges = if mayAffectFlowType then [] else edges;
  top.unparses = ["fwd(" ++ implode(", ", [quoteString(prod), unparseVertices(deps),if mayAffectFlowType then "t" else "f"]) ++ ")"];
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
  top.localTreeContribs = [pair(crossnames(prod, fName), top)];
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
top::FlowDef ::= prod::String  src::FlowVertex  deps::[FlowVertex]  mayAffectFlowType::Boolean
{
  top.prodGraphContribs = [pair(prod, top)];
  local edges :: [Pair<FlowVertex FlowVertex>] = map(pair(src, _), deps);
  top.flowEdges = if mayAffectFlowType then edges else [];
  top.suspectFlowEdges = if mayAffectFlowType then [] else edges;
  top.unparses = ["extra(" ++ implode(", ", [quoteString(prod), src.unparse, unparseVertices(deps),if mayAffectFlowType then "t" else "f"]) ++ ")"];
}

--

function crossnames
String ::= a::String b::String
{
  return a ++ " @ " ++ b;
}


