{-- GENERAL PURPOSE LIST OF LIST MANIPULATION FUNCTIONS --}

{--
  Determines if a list of lists contains the empty list as one of its elements.
  This is equivalent to determining if a nonterminal can produce the empty string.
  @param listOfLists the list of lists (in this case the nonterminal productions)
  @return true if a list of lists contains the empty list as one of its elements. False otherwise.
--}
-- can the nonterminal produce the empty string.
-- this is equivalent to the nonterminal having a production with no input elements.
function emptyListWithinListOfLists
Boolean ::= listOfLists::[[a]]
{
  return any(map(null, listOfLists));
}
{--
  Removes empty list elements from lists of lists
  @param listOfLists the list of lists
  @return the original list of lists with empty list elements removed
--}
function removeEmptyLists
[[a]] ::= listOfLists::[[a]]
{
  return snd(partition(null, listOfLists));
}

{--
  ConsToAll cons elem to each list within the list of lists
  @param elem The element to cons onto each list in the list of lists
  @param listOfLists The list of lists
  @return The list of lists with elem cons to each list within the list of lists
--}
function consToAll
[[a]] ::= elem::a listOfLists::[[a]]
{
  return map(cons(elem, _), listOfLists);
}

{--
  Returns the value associated with a key from the key list. It returns the
  first match found by the earliest key in the key list. If no key matches
  then nothing is returned.
--}
function lookupByList
Maybe<b> ::= eq::(Boolean ::= a a) dict::[Pair<a b>] keyList::[a]
{
  return
  if null(keyList) then nothing()
  else
    orElse(lookupBy(eq, head(keyList), dict), lookupByList(eq, dict, tail(keyList)));
}
