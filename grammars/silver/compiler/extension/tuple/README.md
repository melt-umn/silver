# Tuple Syntax

Tuples are implemented inductively using the construction of ordered pairs, e.g. we forward a tuple `(a, b, c, d)` to `Pair<a Pair<b Pair<c d>>>`.

## Tuple Expressions

Tuples are expressed as a new production `tupleExpr` on the `Expr` nonterminal (originally defined in `compiler/definition/core/Expr.sv`); we define a new nonterminal `TupleList` whose associated productions are responsible for extracting tuple elements and inductively constructing them as pair expressions.

An attribute `translation` of type `Expr` occurs on `TupleList`. Its value is determined using the silver construction production `Silver_Expr` to create a pair (or recursively create nested pairs) from the comma-separated list of expressions that make up the tuple. (*Allie's Question: how exactly does `Silver_Expr` work?*) The `tupleExpr` production on the `Expr` nonterminal forwards its top-level `Expr` to this `translation` attribute occuring on `TupleList`. 

## Pattern Matching

We add a new production `tuplePattern` on the `Pattern` nonterminal (originally defined in `compiler/extension/patternmatching/PatternTypes.sv`) in order to enable pattern matching on tuples. The structure of this production, and the productions associated with the new nonterminal `TuplePatternList`, are similar to the productions for parsing tuple expressions. An attribute `asTuplePattern` of type `Pattern` occurs on `TuplePatternList`.

The silver construction production `Silver_Pattern` is used to convert the comma-separated list of patterns that make up the tuple into the appropriate pair pattern(s), and this value is assigned to `asTuplePattern`. The production `tuplePattern` forwards its top-level `Pattern` to the `asTuplePattern` attribute occuring on `TuplePatternList`.

## Tuple `TypeExpr`s

In order to declare a tuple type as some `t::(a,b)` rather than `t::Pair<a b>`, we add a new production `tupleTypeExpr` on the `TypeExpr` nonterminal (originally defined in `compiler/definition/type/syntax/TypeExpr.sv`). Like with pattern matching and tuple expressions, we convert the comma-separarted list of type expressions (expressed with the new nonterminal `ListOfTypeExprs`) into the appropriate `Pair` type expressions using `Silver_TypeExpr`.

## Type

We want to properly express error messages on tuples, e.g. rather than printing some "... has actual type `Pair <Integer Pair <String Integer>>`," we would like to notify the user "... has actual type `(Integer, String, Integer)`." In order to do this, we need to create a new `appType` aspect production on the `Type` nonterminal (originally defined in `compiler/definition/type/Type.sv`) that can assist in handling tuples. Then, we also need a new `tupleType` production on the `Type` nonterminal to translate our "tuple type representation" created by `appType` into the appropriate `Pair` type. (Note here that "tuple type representation" is in quotes because it is less of a strict representation and more of a transitional notation for its ultimate representation as a nested `Pair` type).

We define the `tupleType` production to expect a list of `[Type]` that we call `ts` in order to appropriately forward to the `Pair` type construction. Thus, we define a synthesized attribute called `tupleElems` of type `[Type]` that occurs on nonterminal `Type`. (*Allie Thoughts: This seems nonobvious and I would like to expand. Additionally, it seems a bit strange for `tupleElems::[Type]` to occur on `Type`, especially since tuples are the only things that use this. I think this might need some further justification*). 

Since `tupleElems` occurs on `Type`, we can use the `appType` production to append the individual types (like `Integer`) that may be in the the nested pairs into the `tupleElems` list. It is notable that the `appType` production represents the application of a constructor type (like `Pair`), with constructor `c` and argument `a`. In our new production, we pattern match on the `baseType`of `c` and assign the appropriate value to the `tupleElems` of the top-level `Type`. If the `baseType` of the constructor `c` is a `Pair`, then we know its `argTypes` should have only a single element, the `fst` element in the pair. We then append the `tupleElems` of the argument `a` to the list `argTypes` of `c`, which will either recurse on `a` if it is also the application of a constructor type. If it is not, its `tupleElems` will simply be assigned to the singleton list of the top-level as in the `default production`. 

--

Allie has a few thoughts:
1. If we use `tupleElems` the way that it is currently implemented, won't there be a `tupleElems` assigned for every single application of a constructor type, even if it is not a pair? This does not seem ideal...
2. I am not sure if I entirely understand the default production. For tuple purposes, I think it is used to assign a value to `tupleElems` in the case when we have the last element type we need to append?

--

In order to appropriately handle the `typerep` of a tuple, as in productions `tupleTypeExpr` and `tupleExpr` (*Should this also be in `tuplePattern`?*), we assign its value to the direct call of production `tupleType` on the `tupleElems` of the type representation of the `forward`. Please note that we must use `forward` rather than `translation` or `te_translation` in this call because we want to send in a decorated type (*Is this correct?*). Within the `tupleType` production, we properly assign the `typepp` or "pretty print" of the type to be the implosion of the list of types `ts` (that we previously obtained from `tupleElems`). We then forward to the pattern match on `ts`: (1) if we have an empty list of types, we forward to `Unit`; (2) if we have a singleton list, we forward simply to that single type; (3) finally, if we have a proper list of types, we then use the `appType` production to construct the `Pair` type with the head of the list and the recursive call to `tupleType` on the tail of the list.

(*Allie Question: Why do we use/assign attributes `substituted` and `flatrenamed` in `tupleType`?*) 

## Empty Tuples

We forward an empty tuple `()` expression to the `unit()` expression, and similarly the empty tuple `()` type to the `Unit` type.