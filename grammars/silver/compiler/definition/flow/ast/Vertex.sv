grammar silver:compiler:definition:flow:ast;

{--
 - Data structure representing vertices in the flow graph within a single production.
 -
 - See VertexType for some extra information organizing these vertexes somewhat.
 -}
data FlowVertex =
{--
- A vertex representing the signature LHS.
- This has no direct dependencies, but is needed to record dependencies on the LHS for tile stitch points.
-}
  lhsEqVertex

{--
- A vertex representing a synthesized attribute on the nonterminal being constructed by this production.
-
- @param attrName  the full name of a synthesized attribute on the lhs.
-}
| lhsSynVertex attrName::String

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
- A vertex representing an element of the signature RHS.
- This has no direct dependencies, but is needed to record dependencies on RHS values for tile stitch points.
-
- @param sigName  the name given to a signature nonterminal.
-}
| rhsEqVertex sigName::String

{--
- A vertex representing the outer dependencies of an element of the signature RHS.
- This has no direct dependencies, but is needed to record outer dependencies on RHS values for tile stitch points.
-
- @param sigName  the name given to a signature nonterminal.
-}
| rhsOuterEqVertex sigName::String

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
 - A vertex representing a local equation. i.e. local attribute, production
 - attribute, etc.  Note that this may be defined for MORE than just those with
 - decorable type!! (e.g. local foo :: String  will appear!)
 - This is because the dependencies for these local equations still matter, of coursee.
 -
 - @param fName  the full name of the NTA/FWD being defined
 -}
| localEqVertex fName::String

{--
 - A vertex representing the outer dependencies of a local equation. i.e. local,
 - production attribute, etc.  This should only be defined for decorable trees.
 -
 - @param fName  the full name of the NTA/FWD being defined
 -}
| localOuterEqVertex fName::String

{--
 - A vertex representing the outer dependencies of a translation attribute,
 - just like localOuterEqVertex. The regular eq vertex with the full deps for
 - taking a reference is just base.synVertex(transAttr)
 -
 - @param base  the vertex type of the tree with the translation attribute.
 - @param transAttr  the name of the translation attribute
 -}
| transAttrOuterEqVertex base::VertexType transAttr::String

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
 - A vertex representing the forward tree.
 - Note that this has only the deps for the outer node;
 - lhsSynVertex("forward") has the deps for taking a reference to the forward tree.
 -}
| forwardOuterEqVertex

{--
 - A vertex representing a synthesized attribute on the forward tree.
 - 
 - @param attrName  the full name of the attribute on the forward
 -}
| forwardSynVertex attrName::String

{--
 - A vertex representing an inherited attribute on the forward tree.
 - 
 - @param attrName  the full name of the attribute on the forward
 -}
| forwardInhVertex attrName::String

{--
 - A vertex representing the tree that forwarded to this one.
 - Only appears in prods with signature sharing.
 -}
| forwardParentEqVertex

{--
 - A vertex representing a synthesized attribute on the parent that forwarded to this one.
 - Only appears in prods with signature sharing.
 -
 - @param attrName  the full name of the attribute on that tree
 -}
| forwardParentSynVertex attrName::String

{--
 - A vertex representing an inherited attribute on the parent that forwarded to this one.
 - Only appears in prods with signature sharing.
 - 
 - @param attrName  the full name of the attribute on that tree
 -}
| forwardParentInhVertex attrName::String

{--
 - A vertex representing an anonymous equation. i.e. a 'decorate e with..'
 - expression, this production will represent 'e'.
 - Note we don't bother distinguishing outer eq deps for anon equations.
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
 - A vertex corresponding to a sub-term of an expression with a known decoration site.
 - e.g. 'local foo::Foo = bar(baz(@x), y.trans);', we need to capture the dependencies of y.trans
 -
 - @param parent  the decoration site of the enclosing term
 - @param prodName  the full name of the applied production
 - @param sigName  the name given to the corresponding child
 -}
| subtermEqVertex parent::VertexType prodName::String sigName::String

{--
 - A vertex corresponding to the outer dependencies of a sub-term of an expression with a known decoration site.
 - 
 - @param parent  the decoration site of the enclosing term
 - @param prodName  the full name of the applied production
 - @param sigName  the name given to the corresponding child
 -}
| subtermOuterEqVertex parent::VertexType prodName::String sigName::String

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

attribute vertexName occurs on FlowVertex;
aspect vertexName on FlowVertex of
| lhsEqVertex() -> "!"
| lhsSynVertex(attrName) -> attrName
| lhsInhVertex(attrName) -> attrName
| rhsEqVertex(sigName) -> sigName ++ "!"
| rhsOuterEqVertex(sigName) -> sigName ++ "~"
| rhsSynVertex(sigName, attrName) -> s"${sigName}.${attrName}"
| rhsInhVertex(sigName, attrName) -> s"${sigName}.${attrName}"
| localEqVertex(fName) -> fName ++ "!"
| localOuterEqVertex(fName) -> fName ++ "~"
| localSynVertex(fName, attrName) -> s"${fName}.${attrName}"
| localInhVertex(fName, attrName) -> s"${fName}.${attrName}"
| transAttrOuterEqVertex(vt, fName) -> s"${vt.vertexName}.${fName}~"
| forwardOuterEqVertex() -> "forward~"
| forwardSynVertex(attrName) -> s"forward.${attrName}"
| forwardInhVertex(attrName) -> s"forward.${attrName}"
| forwardParentEqVertex() -> "forwardParent!"
| forwardParentSynVertex(attrName) -> s"forwardParent.${attrName}"
| forwardParentInhVertex(attrName) -> s"forwardParent.${attrName}"
| anonEqVertex(fName) -> fName
| anonSynVertex(fName, attrName) -> s"${fName}.${attrName}"
| anonInhVertex(fName, attrName) -> s"${fName}.${attrName}"
| subtermEqVertex(parent, prodName, sigName) ->
    s"${parent.vertexName}[${prodName}:${sigName}]!"
| subtermOuterEqVertex(parent, prodName, sigName) ->
    s"${parent.vertexName}[${prodName}:${sigName}]~"
| subtermSynVertex(parent, prodName, sigName, attrName) ->
    s"${parent.vertexName}[${prodName}:${sigName}].${attrName}"
| subtermInhVertex(parent, prodName, sigName, attrName) ->
    s"${parent.vertexName}[${prodName}:${sigName}].${attrName}"
end;

--derive Eq, Ord on FlowVertex;

-- More efficient equality and ordering by just comparing vertex names:
instance Eq FlowVertex {
  eq = \ v1::FlowVertex v2::FlowVertex -> v1.vertexName == v2.vertexName;
}

instance Ord FlowVertex {
  compare = \ v1::FlowVertex v2::FlowVertex -> compare(v1.vertexName, v2.vertexName);
}

-- Shorthand for the forward equation vertex
global forwardEqVertex :: FlowVertex = lhsSynVertex("forward");
