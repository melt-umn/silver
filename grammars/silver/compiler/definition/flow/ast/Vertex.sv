grammar silver:compiler:definition:flow:ast;

{--
 - Data structure representing vertices in the flow graph within a single production.
 -
 - See VertexType for some extra information organizing these vertexes somewhat.
 -}
data FlowVertex =
{--
- A vertex representing a synthesized attribute on the nonterminal being constructed by this production.
-
- @param attrName  the full name of a synthesized attribute on the lhs.
-}
  lhsSynVertex attrName::String

{--
- A vertex representing an inherited attribute on the nonterminal being constructed by this production.
-
- Inherited attributes are separate for 'lhs' and not for 'rhs' because we care rather specially about lhsInh,
- as that's the bit that contributes to computing a flow type.
-
- @param attrName  the full name of an inherited attribute on the lhs.
-}
| lhsInhVertex attrName::String

{--
- A vertex representing a synthesized attribute on an element of the signature RHS.
-
- @param sigName  the name given to a signature nonterminal.
- @param attrName  the full name of an attribute on that signature element.
-}
| rhsSynVertex sigName::String  attrName::String

{--
- A vertex representing an inherited attribute on an element of the signature RHS.
-
- @param sigName  the name given to a signature nonterminal.
- @param attrName  the full name of an attribute on that signature element.
-}
| rhsInhVertex sigName::String  attrName::String

{--
 - A vertex representing a local equation. i.e. forward, local attribute, production
 - attribute, etc.  Note that this may be defined for MORE than just those with
 - decorable type!! (e.g. local foo :: String  will appear!)
 - This is because the dependencies for these local equations still matter, of coursee.
 -
 - @param fName  the full name of the NTA/FWD being defined
 -}
| localEqVertex fName::String

{--
 - A vertex representing a synthesized attribute on a local equation. i.e. forward, local
 - attribute, production attribute, etc.  Note this this implies the equation
 - above IS a decorable type!
 -
 - @param fName  the full name of the NTA/FWD
 - @param attrName  the full name of the attribute on that element
 -}
| localSynVertex fName::String  attrName::String

{--
 - A vertex representing an inherited attribute on a local equation. i.e. forward, local
 - attribute, production attribute, etc.  Note this this implies the equation
 - above IS a decorable type!
 -
 - @param fName  the full name of the NTA/FWD
 - @param attrName  the full name of the attribute on that element
 -}
| localInhVertex fName::String  attrName::String

{--
 - A vertex representing an anonymous equation. i.e. a 'decorate e with..'
 - expression, this production will represent 'e'.
 -
 - @param fName  an anonymous name (typically generated with genInt)
 -}
| anonEqVertex fName::String

{--
 - A vertex representing a synthesized attribute on an anonymous equation.
 - e.g. 'decorate e with { a = d } . b' this represents 'b'.
 -
 - @param fName  the anonymous name
 - @param attrName  the full name of the attribute on that element
 -}
| anonSynVertex fName::String  attrName::String

{--
 - A vertex representing an inherited attribute on an anonymous equation.
 - e.g. 'decorate e with { a = d }' this represents 'e_nt.a's deps 'd'.
 -
 - @param fName  the anonymous name
 - @param attrName  the full name of the attribute on that element
 -}
| anonInhVertex fName::String  attrName::String

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
| subtermSynVertex parent::VertexType prodName::String sigName::String  attrName::String

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
| subtermInhVertex parent::VertexType prodName::String sigName::String  attrName::String
;

derive Eq, Ord on FlowVertex;


-- The forward equation for this production. We do not care to distinguish it.
fun forwardEqVertex FlowVertex ::= = localEqVertex("forward");

-- An attribute on the forward node for this production
fun forwardSynVertex FlowVertex ::= attrName::String = localSynVertex("forward", attrName);
fun forwardInhVertex FlowVertex ::= attrName::String = localInhVertex("forward", attrName);

-- An attribute on the production that forwarded to this one
fun forwardParentSynVertex FlowVertex ::= attrName::String = localSynVertex("forwardParent", attrName);
