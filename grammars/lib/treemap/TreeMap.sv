grammar lib:treemap;

-- The API:

{--
 - Creates a new (empty) tree.
 - 
 - @param TLEop  The "less that or equal to" operator on the key values for this tree
 - @param EQop  The "equal to" operator on the key values for this tree
 - @return A new empty tree.
 -}
function treeNew
TreeMap<a b> ::= LTEop :: Function(Boolean ::= a a)
                 EQop :: Function(Boolean ::= a a)
{
  return leaf(LTEop, EQop);
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
top::TreeMap<a b> ::= LTEop :: Function(Boolean ::= a a)
                      EQop :: Function(Boolean ::= a a)
{
  top.treeLookup = [];
  top.treeInsert = node(false, top, top, top.treeKey, [top.treeValue], LTEop, EQop);
  top.makeBlack = top;
  top.treeDeconvert = [];
}

abstract production node
top::TreeMap<a b> ::= black::Boolean lefttree::TreeMap<a b> righttree::TreeMap<a b>
                      label::a  values::[b] 
                      LTEop :: Function(Boolean ::= a a)
                      EQop :: Function(Boolean ::= a a)
{
  top.treeLookup = if LTEop(top.treeKey, label)
                   then if EQop(top.treeKey, label)
                        then values
                        else lefttree.treeLookup
                   else righttree.treeLookup;

  top.treeInsert = if LTEop(top.treeKey, label)
                   then if EQop(top.treeKey, label)
                        then    node(black, lefttree,            righttree,            label, top.treeValue :: values, LTEop, EQop)
                        else balance(black, lefttree.treeInsert, righttree,            label, values,                  LTEop, EQop)
                   else      balance(black, lefttree,            righttree.treeInsert, label, values,                  LTEop, EQop);

  top.makeBlack = if black then top else node(true, lefttree, righttree, label, values, LTEop, EQop);
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

function balance
TreeMap<a b> ::= black::Boolean lefttree::TreeMap<a b> righttree::TreeMap<a b>
                 label::a  values::[b]
                 LTEop :: Function(Boolean ::= a a)
                 EQop :: Function(Boolean ::= a a)
{
-- Invariant: every black node does not have double reds below it (on any of the 4 paths)
return case black, lefttree, righttree, label, values of
-- left tree is red
  true, node(false, node(false, a, b, x1, x2,_,_), c, y1, y2,_,_), d, z1, z2 -> 
                                                    node(false, node(true, a, b, x1, x2, LTEop, EQop), node(true, c, d, z1, z2, LTEop, EQop), y1, y2, LTEop, EQop)
| true, node(false, a, node(false, b, c, y1, y2,_,_), x1, x2,_,_), d, z1, z2 -> 
                                                    node(false, node(true, a, b, x1, x2, LTEop, EQop), node(true, c, d, z1, z2, LTEop, EQop), y1, y2, LTEop, EQop)
-- right tree is red
| true, a, node(false, node(false, b, c, y1, y2,_,_), d, z1, z2,_,_), x1, x2 -> 
                                                    node(false, node(true, a, b, x1, x2, LTEop, EQop), node(true, c, d, z1, z2, LTEop, EQop), y1, y2, LTEop, EQop)
| true, a, node(false, b, node(false, c, d, z1, z2,_,_), y1, y2,_,_), x1, x2 -> 
                                                    node(false, node(true, a, b, x1, x2, LTEop, EQop), node(true, c, d, z1, z2, LTEop, EQop), y1, y2, LTEop, EQop)
-- we're red, or black with no double red children
| color, a, b, x1, x2 -> node(color, a, b, x1, x2, LTEop, EQop)
end;
}

