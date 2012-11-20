grammar silver:driver:util;

{-
Quick guide to graph closure computations:
(in order from smallest set to largest)

exports | triggers | true deps | options | mentioned | USE:
   X         X                                       | computeDependencies
   X         X           X                           | expandAllDeps
   X         X           X          X                | computeOptionalDeps
   X         X           X          X         X      | completeDependencyClosure

Use cases:

computeDependencies: True exports of a grammar (for parsers, just exports & triggers)
expandAllDeps: True dependencies of a grammar (for translation, true dependencies of root grammar.)
computeOptionalDeps: Optional exports of a set (for flow analysis, include options, only triggered triggers.)
completeDependencyClosure: Any mentioned grammar (for build process, follows options, all triggers.)
-}


{--
 - Closes over exports only.
 -
 - @param need  An initial set of grammars
 - @param seen  Initially []
 - @param e  All built grammars
 - @return  The initial set, plus any grammar directly or indirectly exported by it
 -}
function expandExports
[String] ::= need::[String]  seen::[String]  e::EnvTree<Decorated RootSpec>
{
  local attribute g :: [Decorated RootSpec];
  g = searchEnvTree(head(need), e);

  return if null(need) then seen
         -- If the grammar has already been taken care of, discard it.
         else if contains(head(need), seen) then expandExports(tail(need), seen, e)
         -- If the grammar does not exist, skip over it. (DO NOT REMOVE, as it may have been added by another loop)
         else if null(g) then expandExports(tail(need), head(need) :: seen, e)
         -- Otherwise, tack its exported list to the need list, and add this grammar to the taken care of list.
         else expandExports(tail(need) ++ head(g).exportedGrammars, head(need) :: seen, e);
}

{--
 - Closes over all actually depended upon grammars
 -
 - @param need  An initial set of grammars
 - @param seen  Initially []
 - @param e  All built grammars
 - @return  The initial set, plus any grammar directly or indirectly truly depended upon by it
 -}
function expandAllDeps
[String] ::= need::[String]  seen::[String]  e::EnvTree<Decorated RootSpec>
{
  local attribute g :: [Decorated RootSpec];
  g = searchEnvTree(head(need), e);

  return if null(need) then seen
         -- If the grammar has already been taken care of, or doesn't exist, discard it.
         else if contains(head(need), seen) then expandAllDeps(tail(need), seen, e)
         -- If the grammar does not exist, skip over it. (DO NOT REMOVE, as it may have been added by another loop)
         else if null(g) then expandAllDeps(tail(need), head(need) :: seen, e)
         -- Otherwise, tack its all deps list to the need list, and add this grammar to the taken care of list.
         else expandAllDeps(tail(need) ++ head(g).allGrammarDependencies, head(need) :: seen, e);
}

{--
 - Closes over exports and conditional exports.
 -
 - @param need  An initial set of grammars (typically grammar + grammar's direct imports)
 - @param e  All built grammars
 - @return  The initial set, plus any grammar directly or indirectly exported by it, even conditionally.
 -}
function computeDependencies
[String] ::= need::[String] e::EnvTree<Decorated RootSpec>
{
  return expandCondBuilds(expandExports(need, [], e), [], [], e);
}

{--
 - Closes over triggered grammars, including the exports (and triggers ofc) of those triggered grammars.
 -
 - @see computeDependencies
 -
 - @param need  An initial set of grammars
 - @param seen  Initially []
 - @param triggers  Initially []
 - @param e  All built grammars
 - @return  The initial set, plus any grammar directly or indirectly exported by it
 -}
function expandCondBuilds
[String] ::= need::[String]  seen::[String]  triggers::[[String]]  e::EnvTree<Decorated RootSpec>
{
  -- Map each grammar name to its triggers, and concat.
  local attribute newtriggers :: [[String]];
  newtriggers = foldr(append, triggers, map(skipNulls((.condBuild), _), map(searchEnvTree(_, e), need)));

  local attribute newset :: [String];
  newset = need ++ seen;

  -- Find out about any new triggers as a result of adding 'need' to the set, plus need's triggers
  local attribute triggered :: [String];
  triggered = noninductiveExpansion(newset, newtriggers);

  return if null(need) || null(triggered) then newset
         -- If new triggers fire, continue with the new triggers as need:
         -- And don't forget anything exported by those triggers.
         else expandCondBuilds(expandExports(triggered, newset, e), newset, newtriggers, e);
}

{--
 - Does one iteration of expanding optionals.
 - What does that mean? Well, it means there may be exports / cond builds that aren't yet included.
 -}
function expandOptionalsIter
[String] ::= need::[String]  seen::[String]  e::EnvTree<Decorated RootSpec>
{
  local attribute g :: [Decorated RootSpec];
  g = searchEnvTree(head(need), e);

  return if null(need) then seen
         -- If the grammar has already been taken care of, discard it.
         else if contains(head(need), seen) then expandOptionalsIter(tail(need), seen, e)
         -- If the grammar does not exist, skip over it. (DO NOT REMOVE, as it may have been added by another loop)
         else if null(g) then expandOptionalsIter(tail(need), head(need) :: seen, e)
         -- Otherwise, tack its exported list to the need list, and add this grammar to the taken care of list.
         else expandOptionalsIter(tail(need) ++ head(g).optionalGrammars, head(need) :: seen, e);
}

{--
 - Close over options only, exports, and triggered cond exports
 -}
function computeOptionalDeps
[String] ::= init::[String]  e::EnvTree<Decorated RootSpec>
{
  local initPlusExported :: [String] = computeDependencies(init, e);
  local closeOptions :: [String] = expandOptionalsIter(initPlusExported, [], e);
  
  return if null(rem(closeOptions, initPlusExported)) then initPlusExported
         else computeOptionalDeps(closeOptions, e);
}

{--
 - Close over imports, options, exports, and triggered cond exports.
 - Note that we might trigger more things here than previously...
 -}
function completeDependencyClosure
[String] ::= init::[String]  e::EnvTree<Decorated RootSpec>
{
  local n :: [String] = rem(makeSet(foldr(append, [], map(skipNulls((.moduleNames), _), map(searchEnvTree(_, e), init)))), init);
  
  return if null(n) then init
  else completeDependencyClosure(computeOptionalDeps(n ++ init, e), e);
}

{--
 - Process rules, but this time DO allow triggers to trigger more triggers.
 - @see noninductiveExpansion
 -}
function inductivelyExpand
[String] ::= initial::[String] rules::[[String]]
{
  local attribute result::[String];
  result = noninductiveExpansion(initial, rules);
  
  -- We're doing a slight optimization here:
  -- We only have to recursively call with 'result' as the initial set
  -- because the only additions will be those that trigger on one of those
  -- as anything that triggers on anything in 'initial' is in 'result'.
  -- This is thanks to our rules being all disjunctive
  
  return if null(result) then initial else inductivelyExpand(result, rules) ++ initial;
}

{--
 - Return those triggers set off by the initial set. One iteration only.
 - (i.e. don't consider triggers triggering triggers)
 -
 - @param initial  A set of strings
 - @param rules  A set of rules [[trigger, triggered by any of...]...]
 - @return  A list of triggers that the initial set tripped, not in the inital set already.
 -}
function noninductiveExpansion
[String] ::= initial::[String] rules::[[String]]
{
  return if null(rules) then []
         else if containsAny(tail(head(rules)), initial) && !contains(head(head(rules)), initial)
              then head(head(rules)) :: noninductiveExpansion(initial, tail(rules))
              else noninductiveExpansion(initial, tail(rules));
}

function skipNulls
[b] ::= f::([b] ::= a)  l::[a]
{
  return if null(l) then [] else f(head(l));
}

