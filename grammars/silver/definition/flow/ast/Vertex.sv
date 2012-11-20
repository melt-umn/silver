grammar silver:definition:flow:ast;

{--
 - Data structure representing vertices in the flow graph within a single production.
 -}
nonterminal FlowVertex with unparse;


-- A quick note about the design:
-- For the most part, we don't care about distinguishing what nodes ARE, exactly.
-- To that end, we only have LHS, RHS, localEQ, and local.
-- This are distiguished because they have different requirement for naming them properly.
-- e.g. RHS needs a sigName + attribute, while LHS eqs do not.
--
-- However, we DO care about distinguishing LHS Syn and Inh nodes. Because
-- the inh nodes will be information used in the flow type, for one reason.



{--
 - A vertex representing a synthesized attribute on the nonterminal being constructed by this production.
 -
 - @param attrName  the full name of a synthesized attribute on the lhs.
 -}
abstract production lhsSynVertex
top::FlowVertex ::= attrName::String
{
  top.unparse = "lhsSynV(" ++ quoteString(attrName) ++ ")";
}

{--
 - A vertex representing an inherited attribute on the nonterminal being constructed by this production.
 -
 - @param attrName  the full name of an inherited attribute on the lhs.
 -}
abstract production lhsInhVertex
top::FlowVertex ::= attrName::String
{
  top.unparse = "lhsInhV(" ++ quoteString(attrName) ++ ")";
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
 - This is because the dependencies for these local equations still matter, of coursee.
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
 - above IS a nonterminal type!
 -
 - @param fName  the full name of the NTA/FWD
 - @param attrName  the fulle name of the attribute on that element
 -}
abstract production localVertex
top::FlowVertex ::= fName::String  attrName::String
{
  top.unparse = "localV(" ++ quoteString(fName) ++ ", " ++ quoteString(attrName) ++ ")";
}

-- The forward equation for this production. We do not care to distinguish it.
function forwardEqVertex
FlowVertex ::=
{
  return localEqVertex("forward");
}

-- An attribute on the forward node for this production
function forwardVertex
FlowVertex ::= attrName::String
{
  return localVertex("forward", attrName);
}


-- This set of vertexes are typically used with pattern matching:

-- Demanding the forward equation of a child
function rhsForwardVertex
FlowVertex ::= sigName::String
{
  return rhsVertex(sigName, "forward");
}

-- Demanding the forward equation of a local
function localForwardVertex
FlowVertex ::= fName::String
{
  return localVertex(fName, "forward");
}

-- heh. Demanding the forward equation of a forward!
function forwardForwardVertex
FlowVertex ::=
{
  return localVertex("forward", "forward");
}

--------------------------------------------------------------------------------

function unparseVertices
String ::= fvs::[FlowVertex]
{
  return "[" ++ implode(", ", map((.unparse), fvs)) ++ "]";
}

function equalFlowVertex
Boolean ::= a::FlowVertex  b::FlowVertex
{
  return case a, b of
  | lhsSynVertex(a1), lhsSynVertex(a2) -> a1 == a2
  | lhsInhVertex(a1), lhsInhVertex(a2) -> a1 == a2
  | rhsVertex(s1, a1), rhsVertex(s2, a2) -> s1 == s2 && a1 == a2
  | localEqVertex(f1), localEqVertex(f2) -> f1 == f2
  | localVertex(f1, a1), localVertex(f2, a2) -> f1 == f2 && a1 == a2
  | _, _ -> false
  end;
}

