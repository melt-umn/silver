grammar silver:util:fixedmap;

-- One should always import this via 'import silver:util:fixedmap as ...'
-- The names are too general otherwise.

{--
 - Looks up an element from the map, empty list if not contained.
 -}
function lookup
[b] ::= key::String mp::Map<b>
{
  mp.treeKey = key;
  return mp.treeLookup;
}

{--
 - Creates a map from a list of pairs
 -}
function create
Map<b> ::= lst::[Pair<String b>]
{
  return internalBuild(groupBy(fstStringEq, sortBy(fstStringLte, lst)));
}

{--
 - Converts a multimap back to a list of pairs, in sorted order by key.
 -}
function toList
[Pair<String b>] ::= mp::Map<b>
{
  return mp.treeToList;
}


nonterminal Map<b> with treeKey<String>, treeLookup<b>, treeToList<String b>;

inherited attribute treeKey<a> :: a;
synthesized attribute treeLookup<b>   :: [b];
synthesized attribute treeToList<a b> :: [Pair<a b>];

{--
 - Returns a new, empty, string multimap.
 -}
abstract production empty
top::Map<b> ::= 
{
  top.treeLookup = [];
  top.treeToList = [];
}

abstract production node
top::Map<b> ::= label::String values::[b] l::Map<b> r::Map<b>
{
  l.treeKey = top.treeKey;
  r.treeKey = top.treeKey;
  top.treeLookup = if top.treeKey <= label
                   then if top.treeKey == label
                        then values
                        else l.treeLookup
                   else r.treeLookup;

  top.treeToList = l.treeToList ++ treeMapKeyValues(label, values) ++ r.treeToList;
}

-- internal stuffs

function treeMapKeyValues
[Pair<a b>] ::= k::a v::[b]
{
  return if null(v) then [] else pair(k, head(v)) :: treeMapKeyValues(k, tail(v));
}

function fstStringLte
Boolean ::= l::Pair<String a> r::Pair<String b>
{
  return l.fst <= r.fst;
}
function fstStringEq
Boolean ::= l::Pair<String a> r::Pair<String b>
{
  return l.fst == r.fst;
}

function internalBuild
Map<b> ::= collected::[[Pair<String b>]]
{
  return internalBuildHelp(collected, length(collected));
}

function internalBuildHelp
Map<b> ::= collected::[[Pair<String b>]] upTo::Integer
{
  return  if upTo == 0 then empty()
          else if upTo == 1
          then node(head(head(collected)).fst, map(getSnd, head(collected)), empty(), empty())
          else node(head(head(right_list)).fst, map(getSnd, head(right_list)), ltree, rtree);

  local attribute ltree :: Map<b>;
  ltree = internalBuildHelp(collected, middle);

  local attribute rtree :: Map<b>;
  rtree = internalBuildHelp(tail(right_list), upTo - middle - 1); -- note the tail

  -- this in fact includes the current as well as the right side.
  local attribute right_list :: [[Pair<String b>]];
  right_list = drop(middle,collected);
 
  local attribute middle :: Integer;
  middle = toInt(toFloat(upTo) / 2.0);
}

function getSnd
b ::= l::Pair<a b>
{ return l.snd; }
