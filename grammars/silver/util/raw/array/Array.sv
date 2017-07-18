grammar silver:util:raw:array;

{--
 - A (strict) array. Strict meaning all elements are immediately evaluated to
 - WHNF and not left as raw thunks. This is frequently the more-desired behavior
 - for arrays.
 -}
type Array<a> foreign;

-- Note: I've chosen to use placeholder/verbose names like "emptyArray" in
-- order to make it easier to actually use this library "normally" by just
-- importing it. I suspect we may want to use this library enough that we
-- don't want the added annoyance of dealing with importing it in funny ways.

-- In the long run, we'll probably want libraries designed with type-based
-- namespaces anyway. (e.g. `Array/empty()` or `an_array.map(...)`)

-- I'm also using the generate convention of taking the array as the last element
-- of functions that operate on it.


--------------------------------------------------------------------------------
-- Primitive Array Operations


{--
 - Returns an empty array of any element type.
 -}
function emptyArray
Array<a> ::=
{
}

{--
 - Constructs an array of a given size and uses `generate` to initialize each index.
 -}
function buildArray
Array<a> ::= size::Integer  generate::(a ::= Integer)
{
}

{--
 - Retrieve an element of the array.
 -}
function indexArray
a ::= index::Integer  array::Array<a>
{
}

{--
 - Retrieve the length of an array.
 -}
function lengthArray
Integer ::= array::Array<a>
{
}

{--
 - Return a new copy of `array`, with the value at `index` replaced by `element`.
 -}
function updateArray
Array<a> ::= index::Integer  element::a  array::Array<a>
{
}

{--
 - Appends together two arrays.
 -}
function appendArray
Array<a> ::= l::Array<a>  r::Array<a>
{
}


--------------------------------------------------------------------------------
-- Basic Array Transformations


{--
 - Apply `f` to every element of `array`, returning a new array of equal size.
 -}
function mapArray
Array<b> ::= f::(b ::= a)  array::Array<a>
{
}

{--
 - Decide whether to `keep` each element of the `array`, returning a new array
 - of less than (or equal) size.
 -}
function filterArray
Array<a> ::= keep::(Boolean ::= a)  array::Array<a>
{
}

{--
 - Sorts an array in ascending order, according to a given `lte` function
 - (less than or equal to).
 -}
function sortArray
Array<a> ::= lte::(Boolean ::= a a)  array::Array<a>
{
}

-- TODO: consider partition?
-- TODO: deduplicate?
-- TODO: Indexed version of map? (f also passed index)
-- TODO: flatMap? (What kind? list interaction, too?)


--------------------------------------------------------------------------------
-- List Interactions


{--
 - Constructs an array from a list.
 -}
function listToArray
Array<a> ::= list::[a]
{
}

{--
 - Constructs a list from an array.
 -}
function arrayToList
[a] ::= array::Array<a>
{
}

{--
 - Converts an association list to an array.
 - N.B. if any indexes are left unfilled, a fatal exception will be raised.
 - So this function may be unsafe!
 -}
function assocToArray
Array<a> ::= assoc::[Pair<Integer a>]
{
}

{--
 - Returns an association list for an array.
 -}
function arrayToAssoc
[Pair<Integer a>] ::= array::Array<a>
{
}

{--
 - Updates several indexes in the array to new values at once.
 -}
function assocUpdateArray
Array<a> ::= assoc::[Pair<Integer a>]  array::Array<a>
{
}


--------------------------------------------------------------------------------
-- Sorted Array Operations


-- TODO: i picked using LTE here, but maybe we should use compare instead.
-- (i.e. Integer ::= a a)
-- otherwise we might have to call it multiple times
-- (i.e. a<=b && b<=a means equal)


-- TODO: Need some kind of "create set-like (sorted & deduplicated) array" function
-- Should it be on arrays? Lists? Both?


{--
 - Finds index of the first element ofthe array equal to `element`.
 - Returns -1 if the element is not found.
 - Uses binary search, so array must be sorted.
 -}
function indexOfArray
Integer ::= lte::(Boolean ::= a a)  element::a  array::Array<a>
{
}

{--
 - Merges two set-like arrays into one.
 -}
function unionArray
Array<a> ::= lte::(Boolean ::= a a)  l::Array<a>  r::Array<a>
{
}

{--
 - Filters one set-like array down to elements contained in another set-like array.
 -}
function intersectArray
Array<a> ::= lte::(Boolean ::= a a)  l::Array<a>  r::Array<a>
{
}

-- TODO: add to set? remove from set?

