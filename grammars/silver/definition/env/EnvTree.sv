grammar silver:definition:env;

-- searchEnvTree   [Decorated DclInfo] ::= search::String et::EnvTree
-- buildTree       EnvTree ::= eis::[Decorated EnvItem]
-- collapseEnvTree [Decorated DclInfo] ::= et::EnvTree


nonterminal EnvTree with searchKey, searchResult, treeCollapse;

autocopy attribute searchKey :: String;
synthesized attribute searchResult :: [Decorated DclInfo];
synthesized attribute treeCollapse :: [Decorated DclInfo];

abstract production emptyEnvTree
top::EnvTree ::= 
{
  top.searchResult = [];
  top.treeCollapse = [];
}

abstract production envTree
top::EnvTree ::= iname::String eis::[Decorated DclInfo] l::EnvTree r::EnvTree
{
  top.searchResult = if top.searchKey <= iname
                     then if top.searchKey == iname
                          then eis
                          else l.searchResult
                     else r.searchResult;
  top.treeCollapse = l.treeCollapse ++ (if iname == head(eis).fullName then eis else []) ++ r.treeCollapse;
}

function searchEnvTree
[Decorated DclInfo] ::= search::String et::EnvTree
{
  et.searchKey = search;
  return et.searchResult;
}

function collapseEnvTree
[Decorated DclInfo] ::= et::EnvTree
{
  return et.treeCollapse;
}

-- Function for building EnvTree from list of Decorated EnvItems

function buildTree
EnvTree ::= eis::[Decorated EnvItem]
{
  return buildTreeFromCollected(groupEnvItems(sortEnvItems(explodeEnvItems(eis)))) ;
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
EnvTree ::= collected::[[Decorated EnvItem]]
{
  return buildTreeFromCollectedHelp(collected, length(collected));
}

function buildTreeFromCollectedHelp
EnvTree ::= collected::[[Decorated EnvItem]] upTo::Integer
{
  return  if upTo == 0 then emptyEnvTree()
          else if upTo == 1
          then envTree(head(head(collected)).itemName, mapGetDcls(head(collected)), emptyEnvTree(), emptyEnvTree())
          else envTree(head(head(right_list)).itemName, mapGetDcls(head(right_list)), ltree, rtree);

  local attribute ltree :: EnvTree;
  ltree = buildTreeFromCollectedHelp(collected, middle);

  local attribute rtree :: EnvTree;
  rtree = buildTreeFromCollectedHelp(tail(right_list), upTo - middle - 1);

  -- this in fact includes the current as well as the right side.
  local attribute right_list :: [[Decorated EnvItem]];
  right_list = drop(middle,collected);
 
  local attribute middle :: Integer;
  middle = toInt(toFloat(upTo) / 2.0);
}

