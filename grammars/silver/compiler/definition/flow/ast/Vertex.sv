grammar silver:compiler:definition:flow:ast;

{--
 - Data structure representing vertices in the flow graph within a single production.
 -
 - See VertexType for some extra information organizing these vertexes somewhat.
 -}
nonterminal FlowVertex with compareTo, isEqual, compareKey, compare;
propagate compareTo, isEqual, compareKey, compare on FlowVertex;

{--
 - A vertex representing a synthesized attribute on the nonterminal being constructed by this production.
 -
 - @param attrName  the full name of a synthesized attribute on the lhs.
 -}
abstract production lhsSynVertex
top::FlowVertex ::= attrName::String
{}

{--
 - A vertex representing an inherited attribute on the nonterminal being constructed by this production.
 -
 - Inherited attributes are separate for 'lhs' and not for 'rhs' because we care rather specially about lhsInh,
 - as that's the bit that contributes to computing a flow type.
 -
 - @param attrName  the full name of an inherited attribute on the lhs.
 -}
abstract production lhsInhVertex
top::FlowVertex ::= attrName::String
{}

{--
 - A vertex representing a synthesized attribute on an element of the signature RHS.
 -
 - @param sigName  the name given to a signature nonterminal.
 - @param attrName  the full name of an attribute on that signature element.
 -}
abstract production rhsSynVertex
top::FlowVertex ::= sigName::String  attrName::String
{}

{--
 - A vertex representing an inherited attribute on an element of the signature RHS.
 -
 - @param sigName  the name given to a signature nonterminal.
 - @param attrName  the full name of an attribute on that signature element.
 -}
abstract production rhsInhVertex
top::FlowVertex ::= sigName::String  attrName::String
{}

{--
 - A vertex representing a local equation. i.e. forward, local attribute, production
 - attribute, etc.  Note that this may be defined for MORE than just those with
 - decorable type!! (e.g. local foo :: String  will appear!)
 - This is because the dependencies for these local equations still matter, of coursee.
 -
 - @param fName  the full name of the NTA/FWD being defined
 -}
abstract production localEqVertex
top::FlowVertex ::= fName::String
{}

{--
 - A vertex representing a synthesized attribute on a local equation. i.e. forward, local
 - attribute, production attribute, etc.  Note this this implies the equation
 - above IS a decorable type!
 -
 - @param fName  the full name of the NTA/FWD
 - @param attrName  the full name of the attribute on that element
 -}
abstract production localSynVertex
top::FlowVertex ::= fName::String  attrName::String
{}

{--
 - A vertex representing an inherited attribute on a local equation. i.e. forward, local
 - attribute, production attribute, etc.  Note this this implies the equation
 - above IS a decorable type!
 -
 - @param fName  the full name of the NTA/FWD
 - @param attrName  the full name of the attribute on that element
 -}
abstract production localInhVertex
top::FlowVertex ::= fName::String  attrName::String
{}

{--
 - A vertex representing a synthesized attribute on a (syn or inh) translation attribute
 - on an lhs/rhs/local.
 -
 - @param v  the flow vertex of the translation attribute instance.
 - @param attrName  the full name of the attribute on that element
 -}
abstract production transAttrSynVertex
top::FlowVertex ::= v::FlowVertex  attrName::String
{}

{--
 - A vertex representing an inherited attribute on a (syn or inh) translation attribute
 - on an lhs/rhs/local.
 -
 - @param v  the flow vertex of the translation attribute instance.
 - @param attrName  the full name of the attribute on that element
 -}
abstract production transAttrInhVertex
top::FlowVertex ::= v::FlowVertex  attrName::String
{}


-- TODO: we should distinguish these!

-- The forward equation for this production. We do not care to distinguish it.
function forwardEqVertex
FlowVertex ::=
{
  return localEqVertex("forward");
}

-- An attribute on the forward node for this production
function forwardSynVertex
FlowVertex ::= attrName::String
{
  return localSynVertex("forward", attrName);
}
function forwardInhVertex
FlowVertex ::= attrName::String
{
  return localInhVertex("forward", attrName);
}

{--
 - A vertex representing an anonymous equation. i.e. a 'decorate e with..'
 - expression, this production will represent 'e'.
 -
 - @param fName  an anonymous name (typically generated with genInt)
 -}
abstract production anonEqVertex
top::FlowVertex ::= fName::String
{}

{--
 - A vertex representing a synthesized attribute on an anonymous equation.
 - e.g. 'decorate e with { a = d } . b' this represents 'b'.
 -
 - @param fName  the anonymous name
 - @param attrName  the full name of the attribute on that element
 -}
abstract production anonSynVertex
top::FlowVertex ::= fName::String  attrName::String
{}

{--
 - A vertex representing an inherited attribute on an anonymous equation.
 - e.g. 'decorate e with { a = d }' this represents 'e_nt.a's deps 'd'.
 -
 - @param fName  the anonymous name
 - @param attrName  the full name of the attribute on that element
 -}
abstract production anonInhVertex
top::FlowVertex ::= fName::String  attrName::String
{}

{--
 - A vertex corresponding to a synthesized attribute on a sub-term of an expression with a known decoration site.
 - e.g. 'local foo::Foo = bar(baz(@x));', we need a vertex for the attributes on baz(@x)
 - for decoration site projections.
 -
 - @param parent  the decoration site of the enclosing term
 - @param prodName  the full name of the applied production
 - @param sigName  the name given to the corresponding child
 - @param attrName  the full name of an attribute on this subterm, when decorated
 -}
abstract production subtermSynVertex
top::FlowVertex ::= parent::VertexType prodName::String sigName::String  attrName::String
{}

{--
 - A vertex corresponding to an inherited synthesized attribute on a sub-term of an expression with a known decoration site.
 - e.g. 'local foo::Foo = bar(baz(@x));', we need a vertex for the attributes on baz(@x)
 - for decoration site projections.
 -
 - @param parent  the decoration site of the enclosing term
 - @param prodName  the full name of the applied production
 - @param sigName  the name given to the corresponding child
 - @param attrName  the full name of an attribute on this subterm, when decorated
 -}
abstract production subtermInhVertex
top::FlowVertex ::= parent::VertexType prodName::String sigName::String  attrName::String
{}
