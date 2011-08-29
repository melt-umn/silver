grammar silver:util:treemap;

-- The API:

{--
 - Creates a new (empty) tree.
 - 
 - @param TLEop  The "less that or equal to" operator on the key values for this tree
 - @param EQop  The "equal to" operator on the key values for this tree
 - @return A new empty tree.
 -}
function treeNew
TreeMap<a b> ::= CMP :: Function(Integer ::= a a)
{
  return leaf(CMP);
}

{--
 - Inserts a key-value into the tree. If the key exists already, it will be
 - associated with multiple values, not replaced.
 - 
 - @param x  The key to insert
 - @param v  The value to insert
 - @param t  The existing tree
 - @return  The tree with (x,v) inserted.
 -}
function treeInsert
TreeMap<a b> ::= x::a v::b t::TreeMap<a b>
{
  t.treeKey = x;
  t.treeValue = v;
  return t.treeInsert.makeBlack;
}

{--
 - Retrieves all values associated with a key in the tree.
 - 
 - @param x  The key to loopup
 - @param t  The tree
 - @return  All values inserted for that key in the tree.
 -}
function treeLookup
[b] ::= x::a t::TreeMap<a b>
{
  t.treeKey = x;
  return t.treeLookup;
}

{--
 - Inserts multiple key-value pairs into the tree.
 - 
 - @param l  A list of key-value pairs.
 - @param t  The existing tree
 - @return  The tree with all new key-value pairs inserted.
 -}
function treeConvert
TreeMap<a b> ::= l::[Pair<a b>] t::TreeMap<a b>
{
  return if null(l) then t
         else treeConvert(tail(l), treeInsert(head(l).fst, head(l).snd, t));
}

{--
 - Reverts a tree back into an association list. Any key associated with
 - multiple values will appear multiple times in the list, for each value.
 - 
 - @param t  The existing tree
 - @return  A list of key-value pairs.
 -}
function treeDeconvert
[Pair<a b>] ::= t::TreeMap<a b>
{
  return t.treeDeconvert;
}
-- The implementation:

nonterminal TreeMap<a b> with treeKey<a>, treeLookup<b>, treeValue<b>, treeInsert<a b>, makeBlack<a b>, treeDeconvert<a b>;

inherited attribute treeKey<a> :: a;
synthesized attribute treeLookup<b> :: [b]; -- key

inherited attribute treeValue<b> :: b;
synthesized attribute treeInsert<a b> :: TreeMap<a b>; -- key, value

synthesized attribute makeBlack<a b> :: TreeMap<a b>;

synthesized attribute treeDeconvert<a b> :: [Pair<a b>];

abstract production leaf
top::TreeMap<a b> ::= CMP :: Function(Integer ::= a a)
{
  top.treeLookup = [];
  top.treeInsert = node(false, top, top, top.treeKey, [top.treeValue], CMP);
  top.makeBlack = top;
  top.treeDeconvert = [];
}

abstract production node
top::TreeMap<a b> ::= black::Boolean lefttree::TreeMap<a b> righttree::TreeMap<a b>
                      label::a  values::[b] 
                      CMP :: Function(Integer ::= a a)
{
  top.treeLookup = let cmpr :: Integer = CMP(top.treeKey, label)
                   in if cmpr <= 0
                   then if cmpr == 0
                        then values
                        else lefttree.treeLookup
                   else righttree.treeLookup
                   end;

  top.treeInsert = let cmpr :: Integer = CMP(top.treeKey, label)
                   in if cmpr <= 0
                   then if cmpr == 0
                        then     node(black, lefttree,            righttree,            label, top.treeValue :: values, CMP)
                        else 
                        if black
                        then balanceL(       lefttree.treeInsert, righttree,            label, values,                  CMP)
                        else     node(false, lefttree.treeInsert, righttree,            label, values,                  CMP)
                   else if black
                        then balanceR(       lefttree,            righttree.treeInsert, label, values,                  CMP)
                        else     node(false, lefttree,            righttree.treeInsert, label, values,                  CMP)
                   end;

  top.makeBlack = if black then top else node(true, lefttree, righttree, label, values, CMP);
  top.treeDeconvert = lefttree.treeDeconvert ++ treeMapKeyValues(label, values) ++ righttree.treeDeconvert;
  
  lefttree.treeKey = top.treeKey;
  lefttree.treeValue = top.treeValue;
  righttree.treeKey = top.treeKey;
  righttree.treeValue = top.treeValue;
}

function treeMapKeyValues
[Pair<a b>] ::= k::a v::[b]
{
  return if null(v) then [] else pair(k, head(v)) :: treeMapKeyValues(k, tail(v));
}

-- Invariant: every black node does not have double reds below it (on any of the 4 paths)
-- Whenever we're reconstructing after an insert, all black nodes will examine the modified subtree
-- to see if there are any double-reds, and act accordingly.
-- These functions are only called by black nodes.
function balanceL
TreeMap<a b> ::= lefttree::TreeMap<a b> righttree::TreeMap<a b>
                 label::a  values::[b]
                 CMP :: Function(Integer ::= a a)
{
return case lefttree of
  node(false, node(false, a, b, x1, x2,_), c, y1, y2,_) -> 
                                                    node(false, node(true, a, b, x1, x2, CMP), node(true, c, righttree, label, values, CMP), y1, y2, CMP)
| node(false, a, node(false, b, c, y1, y2,_), x1, x2,_) -> 
                                                    node(false, node(true, a, b, x1, x2, CMP), node(true, c, righttree, label, values, CMP), y1, y2, CMP)
| a -> node(true, a, righttree, label, values, CMP)
end;
}
function balanceR
TreeMap<a b> ::= lefttree::TreeMap<a b> righttree::TreeMap<a b>
                 label::a  values::[b]
                 CMP :: Function(Integer ::= a a)
{
return case righttree of
  node(false, node(false, b, c, y1, y2,_), d, z1, z2,_) -> 
                                                    node(false, node(true, lefttree, b, label, values, CMP), node(true, c, d, z1, z2, CMP), y1, y2, CMP)
| node(false, b, node(false, c, d, z1, z2,_), y1, y2,_) -> 
                                                    node(false, node(true, lefttree, b, label, values, CMP), node(true, c, d, z1, z2, CMP), y1, y2, CMP)
| b -> node(true, lefttree, b, label, values, CMP)
end;
}

