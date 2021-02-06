# Tuple Syntax

Tuples use a comma "`,`" as an infix operator to seperate individual tuple elements. They are implemented inductively using the construction of ordered pairs, e.g. we forward a tuple `(a, b, c, d)` to `Pair<a Pair<b Pair<c d>>>`. This means the following examples are equivalent:

    ("I", ("am", "a", (5, "tuple"))) = ("I", "am", "a", 5, "tuple")

And these both represent the following construction of ordered pairs

    pair("I", pair("am", pair("a", pair(5, "tuple"))))

In contrast, the 3-tuple

    (("I'm", "not"), ("a", 5), "tuple")

represents

    pair(pair("I'm", "not"), pair(pair("a", 5), "tuple"))

Notably, an empty tuple `()` forwards to `unit()`. Although not explicitly defined here, a single element `e` can be thought of as a singleton tuple `(e)`, which is relevant for understanding how `Type` works on tuples.

Individual tuple elements may be accessed using the tuple selector syntax, which utilizes a dot "`.`" operator following the tuple expression and the position of the element we would like to access expressed as an integer constant. For example, `("Here's a", 3, "tuple").2` would yield `3` and `(("I'm", "not"), ("a", 5), "tuple").3` would yield `"tuple"`. Tuple access indices begin at `1`.

## Implementation

We add a production `tupleExpr` on the `Expr` nonterminal and define a new nonterminal `TupleList`, whose associated productions are responsible for extracting tuple elements and inductively constructing them as pair expressions. Similarly, the production `tuplePattern` on `Pattern` enables pattern matching on tuples, in conjunction with the new nonterminal `TuplePatternList`.

In order to declare a tuple type as some `t::(a,b)` rather than `t::Pair<a b>`, we add a new production `tupleTypeExpr` on the `TypeExpr` nonterminal. This works much like `tupleExpr` and `tuplePattern`, and defines a new nonterminal `ListofTypeExprs`.

In sum, the new nonterminals include:

* `TupleList`
* `TuplePatternList`
* `ListOfTypeExprs`

For the selector syntax, we add a production `selector` which forwards to a function `select`. This returns the tuple element at the specified access index, an `Expr`.

### Type

We want to properly express error messages on tuples, e.g. rather than printing some "... has actual type `Pair <Integer Pair <String Integer>>`," we would like to notify the user "... has actual type `(Integer, String, Integer)`." In order to do this, we introduce a new synthesized attribute `tupleElems` of type `[Type]` that occurs on `Type`; this will be a list of all the distinct types in the tuple. For example, a tuple of type

    (Integer, String, Integer)

would have a `tupleElems` of `[Integer, String, Integer]`. To construct our `tupleElems`, we aspect the `appType` production for constructor types; for any `Pair` type, its type elements are appropriately extracted and appended to tupleElems. As mentioned above, a single element `e` can be thought of as a singleton tuple `(e)`, and thus a default production is defined such that for any type its `tupleElems` is assigned to the singleton list of that type.

The production `tupleType` is responsible for converting the `tupleElems` of a given tuple into the appropriate pretty print for the tuple type. It then appropriately forwards elements of `tupleElems` to the `Pair` type.

`tupleType` is used by the productions `tupleTypeExpr` and `tupleExpr` to construct the tuple type representation for a given tuple.

### Relevant new attributes

* `translation` : this attribute is of type `Expr` and occurs on `tupleList`. It's used to convert the comma-separated list of expressions that make up the tuple into a pair expression.
* `asTuplePattern` : this attribute is of type `Pattern` and occurs on `TuplePatternList`. Like `translation`, it is used to convert the comma-separated list of patterns that make up the tuple into a pair pattern.
* `te_translation` : this attribute is of type `TypeExpr` and occurs on `ListOfTypeExprs`. It functions identically to `translation` and `asTuplePattern`.
* `tupleElems` : this attribute is of type `[Type]` and occurs on `Type`. It is a list of the types that make up the tuple. 