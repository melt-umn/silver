grammar silver:definition:env;

-- searchEnvTree   [Decorated DclInfo] ::= search::String et::Decorated EnvTree
-- buildTree       Decorated EnvTree ::= eis::[Decorated EnvItem]
-- collapseEnvTree [Decorated DclInfo] ::= et::Decorated EnvTree


nonterminal EnvTree with dcls, itemName, isEmpty, leftTree, rightTree;

synthesized attribute dcls :: [Decorated DclInfo];
synthesized attribute isEmpty :: Boolean;
synthesized attribute leftTree :: Decorated EnvTree;
synthesized attribute rightTree :: Decorated EnvTree;

-- Productions for constructing an EnvTree
function emptyEnvTree
Decorated EnvTree ::=
{
  return decorate i_emptyEnvTree() with {};
}

abstract production i_emptyEnvTree
et::EnvTree ::= 
{
  et.isEmpty = true ;
  et.itemName = error("Invalid attempt to access itemName on empty EnvTree.");
  et.dcls = error("Invalid attempt to access dcls on empty EnvTree.");
  et.leftTree  = error("Invalid attempt to access left tree on EnvTree.");
  et.rightTree = error("Invalid attempt to access right tree on EnvTree.");
}

-- assumption: eis is a list of EnvItems with all the same itemName.
function envTree
Decorated EnvTree ::= iname::String eis::[Decorated DclInfo] l::Decorated EnvTree r::Decorated EnvTree
{
  return decorate i_envTree(iname, eis, l, r) with {};
}
abstract production i_envTree
et::EnvTree ::= iname::String eis::[Decorated DclInfo] l::Decorated EnvTree r::Decorated EnvTree
{
  et.dcls = eis;
  et.itemName = iname;
  et.isEmpty = false; -- TODO: should this be different?
  et.leftTree = l;
  et.rightTree = r;
}

function searchEnvTree
[Decorated DclInfo] ::= search::String et::Decorated EnvTree
{
  return if et.isEmpty
         then []
     
         else if search == et.itemName
         then et.dcls

         else if search <  et.itemName
         then searchEnvTree (search, et.leftTree)

         else if search >  et.itemName
         then searchEnvTree (search, et.rightTree)

         else error("searchEnvTree for " ++ search ++ " resulted in imposible outcome?") ; -- TODO remove this.
}

-- Function for building Decorated EnvTree from list of Decorated EnvItems

function buildTree
Decorated EnvTree ::= eis::[Decorated EnvItem]
{
  return buildTreeFromCollected(collectEnvItems(sortEnvItems(explodeEnvItems(eis)))) ;
}

-- Take (shortName, fullName) and turns it into [(shortName, fullName), (fullName, fullName)]
-- So lookups see both.
function explodeEnvItems
[Decorated EnvItem] ::= eis::[Decorated EnvItem]
{
  local attribute h :: Decorated EnvItem;
  h = head(eis);
  
  return if null(eis) then [] else
         if h.itemName == h.dcl.fullName
         then h :: explodeEnvItems(tail(eis))
         else h :: (renamedEnvItem(h.dcl.fullName, h.dcl) :: explodeEnvItems(tail(eis)));
}

function buildTreeFromCollected
Decorated EnvTree ::= collected::[[Decorated EnvItem]]
{
  return buildTreeFromCollectedHelp(collected, length(collected));
}

function buildTreeFromCollectedHelp
Decorated EnvTree ::= collected::[[Decorated EnvItem]] upTo::Integer
{
  return  if upTo == 0
          then emptyEnvTree()
          else if upTo == 1
          then envTree(head(head(collected)).itemName, mapGetDcls(head(collected)), emptyEnvTree(), emptyEnvTree())
          else envTree(head(head(right_list)).itemName, mapGetDcls(head(right_list)), ltree, rtree);

  local attribute ltree :: Decorated EnvTree;
  ltree = buildTreeFromCollectedHelp(collected, middle);

  local attribute rtree :: Decorated EnvTree;
  rtree = buildTreeFromCollectedHelp(tail(right_list), upTo - middle - 1);

  -- this in fact includes the current as well as the right side.
  local attribute right_list :: [[Decorated EnvItem]];
  right_list = dropEnvItemLists(middle,collected);
 
  local attribute middle :: Integer;
  middle = toInt(toFloat(upTo) / 2.0);
}

-- This function takes a sorted list and collects equal-valued
-- elements into sub-lists.
-- e.g. collectEnvItems(["a","b","b","c","d","d","e"])
-- returns [ ["a"],["b","b"],["c"],["d","d"],["e"] ]
function collectEnvItems
[[Decorated EnvItem]] ::= eis::[Decorated EnvItem]
{
  return if null(eis) then [] else collectAccum([head(eis)],tail(eis));
}

-- current_group is an accumulating parameter that collects the equal-valued
-- elements at the front of eis.
function collectAccum
[[Decorated EnvItem]] ::= current_group::[Decorated EnvItem] eis::[Decorated EnvItem]
{
  -- invariant: current_group is never null!
  return if null(eis)
         then [current_group]

         else if head(current_group).itemName == head(eis).itemName
         then collectAccum( head(eis) :: current_group , tail(eis) ) 
        
         else current_group :: collectAccum( [head(eis)], tail(eis) );
}

function dropEnvItemLists
[[Decorated EnvItem]] ::= n::Integer l::[[Decorated EnvItem]]
{
 return if n <= 0 then l
        else dropEnvItemLists(n-1,tail(l));
}


function collapseEnvTree
[Decorated DclInfo] ::= et::Decorated EnvTree
{
  -- Collapses a tree down to its component dcls.
  -- Note: only include those where itemName==fullName, as this should always occurs,
  -- and will eliminate duplicates when a short name is present in the tree
  return if et.isEmpty then []
         else collapseEnvTree(et.leftTree) ++ 
              (if et.itemName == head(et.dcls).fullName then et.dcls else []) ++ 
              collapseEnvTree(et.rightTree);
}
