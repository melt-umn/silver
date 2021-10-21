grammar silver:compiler:definition:flow:ast;


{--
 - Info for constructing production flow graphs.
 - Follows Figure 5.12 (p138, pdf p154 of Kaminski's PhD)
 - with notable additions of:
 -  - ntRef, specification (for dealing with 'flowtype' decls and the ref set)
 -  - implicitFwdAffects (for improving accuracy of inference)
 -  - extraEq (handling collections '<-')
 - which the thesis does not address.
 -}
nonterminal FlowDef with synTreeContribs, inhTreeContribs, defTreeContribs, fwdTreeContribs, fwdInhTreeContribs, prodTreeContribs, prodGraphContribs, flowEdges, localInhTreeContribs, suspectFlowEdges, hostSynTreeContribs, nonSuspectContribs, localTreeContribs;
nonterminal FlowDefs with synTreeContribs, inhTreeContribs, defTreeContribs, fwdTreeContribs, fwdInhTreeContribs, prodTreeContribs, prodGraphContribs, localInhTreeContribs, hostSynTreeContribs, nonSuspectContribs, localTreeContribs;

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

{-- A list of extension syn attr occurrences, subject to ft lower bounds. -}
synthesized attribute hostSynTreeContribs :: [Pair<String FlowDef>];

{-- A list of attributes for a production that are non-suspect -}
synthesized attribute nonSuspectContribs :: [Pair<String [String]>];

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
  top.localInhTreeContribs = h.localInhTreeContribs ++ t.localInhTreeContribs;
  top.localTreeContribs = h.localTreeContribs ++ t.localTreeContribs;
  top.hostSynTreeContribs = h.hostSynTreeContribs ++ t.hostSynTreeContribs;
  top.nonSuspectContribs = h.nonSuspectContribs ++ t.nonSuspectContribs;
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
  top.localInhTreeContribs = [];
  top.localTreeContribs = [];
  top.hostSynTreeContribs = [];
  top.nonSuspectContribs = [];
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
  top.localInhTreeContribs = [];
  top.localTreeContribs = [];
  top.hostSynTreeContribs = [];
  top.nonSuspectContribs = [];
  top.suspectFlowEdges = []; -- flowEdges is required, but suspect is typically not!
  -- require prodGraphContibs, flowEdges
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
  top.prodTreeContribs = [(nt, top)];
  top.prodGraphContribs = [];
  top.flowEdges = error("Internal compiler error: this sort of def should not be in a context where edges are requested.");
}

{--
 - Declaration that a synthesized attribute occurrence is in the host language
 - (exported by the nonterminal) and therefore is *NOT* subject to the restriction
 - for extensions synthesized attributes (i.e. must be ft(syn) >= ft(fwd)).
 -
 - @param nt  the full name of the nonterminal
 - @param attr  the full name of the synthesized attribute
 -}
abstract production hostSynFlowDef
top::FlowDef ::= nt::String  attr::String
{
  top.hostSynTreeContribs = [(nt, top)];
  top.prodGraphContribs = [];
  top.flowEdges = error("Internal compiler error: this sort of def should not be in a context where edges are requested.");
}

{--
 - The definition of a default equation for a synthesized attribute on a nonterminal.
 -
 - @param nt  the full name of the *nonterminal*
 - @param attr  the full name of the attribute
 - @param deps  the dependencies of this equation on other flow graph elements
 - CONTRIBUTIONS ARE POSSIBLE
 -}
abstract production defaultSynEq
top::FlowDef ::= nt::String  attr::String  deps::[FlowVertex]
{
  top.defTreeContribs = [(crossnames(nt, attr), top)];
  top.prodGraphContribs = []; -- defaults don't show up in the prod graph!!
  top.flowEdges = map(pair(fst=lhsSynVertex(attr), snd=_), deps); -- but their edges WILL end up added to graphs in fixup-phase!!
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
  top.synTreeContribs = [(crossnames(prod, attr), top)];
  top.prodGraphContribs = [(prod, top)];
  local edges :: [Pair<FlowVertex FlowVertex>] = map(pair(fst=lhsSynVertex(attr), snd=_), deps);
  top.flowEdges = if mayAffectFlowType then edges else [];
  top.suspectFlowEdges = if mayAffectFlowType then [] else edges;
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
  top.inhTreeContribs = [(crossnames(prod, crossnames(sigName, attr)), top)];
  top.prodGraphContribs = [(prod, top)];
  top.flowEdges = map(pair(fst=rhsVertex(sigName, attr), snd=_), deps);
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
  top.fwdTreeContribs = [(prod, top)];
  top.prodGraphContribs = [(prod, top)];
  local edges :: [Pair<FlowVertex FlowVertex>] = map(pair(fst=forwardEqVertex(), snd=_), deps);
  top.flowEdges = if mayAffectFlowType then edges else [];
  top.suspectFlowEdges = if mayAffectFlowType then [] else edges;
}

{--
 - Attributes that are non-suspect.
 - 
 - These are *allowed* to have their *implicit forward copy equations* affect the flow type.
 -
 - This is basically a hack to make flow type inference work a little better.
 -}
abstract production implicitFwdAffects
top::FlowDef ::= prod::String  attrs::[String]
{
  top.nonSuspectContribs = [(prod, attrs)];
  top.prodGraphContribs = [];
  top.flowEdges = error("Internal compiler error: this sort of def should not be in a context where edges are requested.");
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
  top.fwdInhTreeContribs = [(crossnames(prod, attr), top)];
  top.prodGraphContribs = [(prod, top)];
  top.flowEdges = map(pair(fst=forwardVertex(attr), snd=_), deps);
}

{--
 - The definition of a local or production attribute's equation.
 - MAY not be a nonterminal type!
 -
 - @param prod  the full name of the production
 - @param fName  the name of the local/production attribute
 - @param typeName  the full name of the type, or empty string if not a decorable type!
 - @param isNT  true if the type is a nonterminal
 - @param deps  the dependencies of this equation on other flow graph elements
 - CONTRIBUTIONS ARE POSSIBLE
 -}
abstract production localEq
top::FlowDef ::= prod::String  fName::String  typeName::String  isNT::Boolean  deps::[FlowVertex]
{
  top.localTreeContribs = [(crossnames(prod, fName), top)];
  top.prodGraphContribs = [(prod, top)];
  top.flowEdges = map(pair(fst=localEqVertex(fName), snd=_), deps);
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
  top.localInhTreeContribs = [(crossnames(prod, crossnames(fName, attr)), top)];
  top.prodGraphContribs = [(prod, top)];
  top.flowEdges = map(pair(fst=localVertex(fName, attr), snd=_), deps);
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
  top.prodGraphContribs = [(prod, top)];
  local edges :: [Pair<FlowVertex FlowVertex>] = map(pair(fst=src, snd=_), deps);
  top.flowEdges = if mayAffectFlowType then edges else [];
  top.suspectFlowEdges = if mayAffectFlowType then [] else edges;
}

{--
 - The definition of an anonymous decoration site e.g. 'decorate with'
 -
 - @param prod  the full name of the production
 - @param fName  the generated anonymous name for this decoration site
 - @param typeName  the full name of the type (usually a nonterminal, but may be a decorable type var)
 - @param isNT  true if the type is a nonterminal
 - @param deps  the dependencies of this equation on other flow graph elements
 - (no contributions are possible)
 -}
abstract production anonEq
top::FlowDef ::= prod::String  fName::String  typeName::String  isNT::Boolean  loc::Location  deps::[FlowVertex]
{
  top.localTreeContribs = [(crossnames(prod, fName), top)];
  top.prodGraphContribs = [(prod, top)];
  top.flowEdges = map(pair(fst=anonEqVertex(fName), snd=_), deps);
}

{--
 - The definition of an inherited attribute for an anonymous decoration site.
 -
 - @param prod  the full name of the production
 - @param fName  the generated anonymous name for this decoration site
 - @param attr  the full name of the attribute
 - @param deps  the dependencies of this equation on other flow graph elements
 - (no contributions are possible)
 -}
abstract production anonInhEq
top::FlowDef ::= prod::String  fName::String  attr::String  deps::[FlowVertex]
{
  top.localInhTreeContribs = [(crossnames(prod, crossnames(fName, attr)), top)];
  top.prodGraphContribs = [(prod, top)];
  top.flowEdges = map(pair(fst=anonVertex(fName, attr), snd=_), deps);
}

{--
 - A synthesized occurs-on context for a decoration site of a type variable.
 -
 - @param prod  the full name of the production
 - @param vt    the decoration site
 - @param attr  the full name of the synthesized attribute
 - @param deps  the full names of the inherited attribute dependencies specified in the occurs-on context.
 -}
abstract production synOccursContextEq
top::FlowDef ::= prod::String  vt::VertexType  attr::String  deps::[String]
{
  top.prodGraphContribs = [(prod, top)];
  top.flowEdges = map(pair(fst=vt.synVertex(attr), snd=_), map(vt.inhVertex, deps));
}

{--
 - A match rule from a pattern matching equation.
 - Matching against 'scrutinee' this rule matches 'matchProd'. It extracts the pattern variables in 'vars'.
 - This info is used, not to directly create edges, but to inform which stitch points are needed.
 -}
abstract production patternRuleEq
top::FlowDef ::= prod::String  matchProd::String  scrutinee::VertexType  vars::[PatternVarProjection]
{
  top.prodGraphContribs = [(prod, top)];
  top.flowEdges = [];
}


nonterminal PatternVarProjection;

abstract production patternVarProjection
top::PatternVarProjection ::= child::String  typeName::String  patternVar::String
{
}

--

function crossnames
String ::= a::String b::String
{
  return a ++ " @ " ++ b;
}

--

-- Used to get better error messages
function collectAnonOrigin
[Pair<String  Location>] ::= f::[FlowDef]
{
  return foldr(collectAnonOriginItem, [], f);
}
function collectAnonOriginItem
[Pair<String  Location>] ::= f::FlowDef  rest::[Pair<String  Location>]
{
  return case f of
  | anonEq(_, fN, _, _, l, _) ->
      -- Small hack to improve error messages. Ignore anonEq's that come from patterns
      if startsWith("__scrutinee", fN)
      then rest
      else (fN, l) :: rest
  | _ -> rest
  end;
}
