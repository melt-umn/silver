grammar silver:definition:env;

--		API		--
----------------------------------

--getDclsTree (String, Decorated EnvTree) -> [Decorated EnvItem]
--getDclsTreeAll (Decorated EnvTree) -> [Decorated EnvItem]
--searchEnvTree (String, EnvSearch, Decorated EnvTree) -> [Decorated EnvItem]
--searchEnvTreeAll (EnvSearch, Decorated EnvTree) -> [Decorated EnvItem]

nonterminal EnvTree with envItems, isEmpty, leftTree, rightTree, unparse ;

synthesized attribute envItems :: [Decorated EnvItem] ;
synthesized attribute isEmpty :: Boolean ;
synthesized attribute leftTree :: Decorated EnvTree ;
synthesized attribute rightTree :: Decorated EnvTree ;


-- Productions for constructing an EnvTree
function emptyEnvTree
Decorated EnvTree ::=
{
  return decorate i_emptyEnvTree() with {};
}

abstract production i_emptyEnvTree
et::EnvTree ::= 
{
  et.unparse = "";
  et.isEmpty = true ;
  et.envItems = error ("Invalied attempt to access envItems on empty EnvTree.");
  et.leftTree  = error ("Invalied attempt to access left tree on EnvTree.");
  et.rightTree = error ("Invalied attempt to access right tree on EnvTree.");
}

function leafEnvTree
Decorated EnvTree ::= eis:: [Decorated EnvItem]
{
  return decorate i_leafEnvTree(eis) with {};
}

abstract production i_leafEnvTree
et::EnvTree ::= eis:: [Decorated EnvItem]
{
  et.unparse = unparseItems(eis);
  et.isEmpty = null(eis);
  et.envItems = eis ;
  et.leftTree  = emptyEnvTree();
  et.rightTree = emptyEnvTree();
}

function envTree
Decorated EnvTree ::= eis:: [Decorated EnvItem]  l::Decorated EnvTree  r::Decorated EnvTree
{
  return decorate i_envTree(eis, l, r) with {};
}
abstract production i_envTree
et::EnvTree ::= eis:: [Decorated EnvItem]  l::Decorated EnvTree  r::Decorated EnvTree
{
 et.unparse = unparseItems(eis) ++ l.unparse ++ r.unparse;
 et.envItems = eis ;
 et.isEmpty = false ;
 et.leftTree = l ;
 et.rightTree = r ;
}



abstract production searchAll
top::EnvSearch ::=
{
  top.found = true;
}

-- Function for searching Decorated EnvTree
function getDclsTree
[Decorated EnvItem] ::= search::String e::Decorated EnvTree
{
  return searchEnvTree(search, searchAll(), e);
}

function getDclsTreeAll
[Decorated EnvItem] ::= e::Decorated EnvTree
{
  return searchEnvTreeAll(searchAll(), e);
}

nonterminal EnvSearch with inEnvItems, found;
synthesized attribute found :: Boolean;
inherited attribute inEnvItems :: [Decorated EnvItem];

function searchEnvTree
[Decorated EnvItem] ::= search::String s::EnvSearch e::Decorated EnvTree
{
  local attribute h :: Decorated EnvItem;
  h = head(e.envItems);

  return if e.isEmpty
         then [ ]
     
         else if search == h.itemName && 
		(decorate s with {inEnvItems = e.envItems;}).found
         then e.envItems

         else if search <  h.itemName
         then searchEnvTree (search, s, e.leftTree)

         else if search >  h.itemName
         then searchEnvTree (search, s, e.rightTree)

         else [ ] ;
}
 
function searchEnvTreeAll
[Decorated EnvItem] ::= s::EnvSearch e::Decorated EnvTree
{
  local attribute h :: Decorated EnvItem;
  h = head(e.envItems);

  return if e.isEmpty
         then [ ]
         else if (decorate s with {inEnvItems = e.envItems;}).found
              then e.envItems ++ searchEnvTreeAll (s, e.leftTree) ++ searchEnvTreeAll (s, e.rightTree)
              else searchEnvTreeAll (s, e.leftTree) ++ searchEnvTreeAll (s, e.rightTree);
}



-- Function for building Decorated EnvTree from list of Decorated EnvItems

--function flatten
--[Decorated EnvItem] ::= t::Decorated EnvTree {
--  return if t.isEmpty then [] else t.envItems ++ flatten(t.leftTree) ++ flatten(t.rightTree);
--}


function buildTree
Decorated EnvTree ::= eis::[Decorated EnvItem]
{
 return buildTreeFromCollected(collectEnvItems(sortEnvItems(eis))) ;
}

function buildTreeFromCollected
Decorated EnvTree ::= collected::[[Decorated EnvItem]]
{
 return  if null(collected)
         then emptyEnvTree()
         else envTree( head(right_list), ltree, rtree );

 local attribute ltree :: Decorated EnvTree ;
 ltree = buildTreeFromCollected(takeEnvItemLists(middle,collected)) ;

 local attribute rtree :: Decorated EnvTree ;
 rtree = buildTreeFromCollected(tail(right_list));

 local attribute right_list :: [[Decorated EnvItem]] ;
 right_list = dropEnvItemLists(middle,collected) ;
 
 local attribute middle :: Integer ;
 middle = toInt(toFloat(length(collected)) / 2.0) ;
}


-- This function takes a sorted list and collects equal-valued
-- elements into sub-lists.
-- e.g. collectEnvItems(["a","b","b","c","d","d","e"])
-- returns [ ["a"],["b","b"],["c"],["d","d"],["e"] ]
function collectEnvItems
[[Decorated EnvItem]] ::= eis::[Decorated EnvItem]
{
 return collectAccum([],eis);
}

-- current_group is an accumulating parameter that collects the equal-valued
-- elements at the front of eis.
function collectAccum
[[Decorated EnvItem]] ::= current_group::[Decorated EnvItem] eis::[Decorated EnvItem]
{
 return if null(current_group) && null(eis)
        then [ ]

        else if null(current_group) && ! null(eis)  -- starting new group
        then collectAccum( [head(eis)], tail(eis) )

        -- now, current_group != [ ] 
        else if null(eis)
        then [ current_group ]

        else if head(current_group).itemName == head(eis).itemName
        then collectAccum ( current_group ++ [head(eis)], tail(eis) ) 
        else [ current_group ] ++  collectAccum ( [head(eis)], tail(eis) )  ;
}

-- Sort function
function sortEnvItems
[Decorated EnvItem] ::= eis::[Decorated EnvItem]
{
 return if length(eis) <= 1 then eis else mergeEnvItems(front_half,back_half);

 local attribute front_half :: [Decorated EnvItem] ;
 front_half = sortEnvItems(takeEnvItems(middle,eis)) ;

 local attribute back_half :: [Decorated EnvItem] ;
 back_half = sortEnvItems(dropEnvItems(middle,eis)) ;

 local attribute middle :: Integer ;
 middle = toInt(toFloat(length(eis)) / 2.0) ;
}


function mergeEnvItems
[Decorated EnvItem] ::= l1::[Decorated EnvItem] l2::[Decorated EnvItem]
{
 return if null(l1) 
        then l2

        else if null(l2) 
        then l1

        else if head(l1).itemName < head(l2).itemName
        then [head(l1)] ++ mergeEnvItems(tail(l1),l2)

        else [head(l2)] ++ mergeEnvItems(l1,tail(l2)) ;
}


-- Take and Drop functions for the two types of lists
function takeEnvItems
[Decorated EnvItem] ::= n::Integer l::[Decorated EnvItem]
{
 return if n <= 0 then [ ]
        else cons(head(l),takeEnvItems(n-1,tail(l)));
}

function dropEnvItems
[Decorated EnvItem] ::= n::Integer l::[Decorated EnvItem]
{
 return if n <= 0 then l
        else dropEnvItems(n-1,tail(l));
}


function takeEnvItemLists
[[Decorated EnvItem]] ::= n::Integer l::[[Decorated EnvItem]]
{
 return if n <= 0 then [ ]
        else cons(head(l),takeEnvItemLists(n-1,tail(l)));
}

function dropEnvItemLists
[[Decorated EnvItem]] ::= n::Integer l::[[Decorated EnvItem]]
{
 return if n <= 0 then l
        else dropEnvItemLists(n-1,tail(l));
}

function unparseTrees
String ::= s::[Decorated EnvTree]{
  return "[" ++ unparseTreesHelp(s) ++ "]";
}

function unparseTreesHelp
String ::= s::[Decorated EnvTree]{
  return if null(s) then "" else (head(s).unparse ++ if null(tail(s)) then "" else (", " ++ unparseTreesHelp(tail(s))));
}
