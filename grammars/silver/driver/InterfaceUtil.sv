grammar silver:driver;

nonterminal DependencyAnalysis with compiledList, needGrammars, interfaces, compiledGrammars, forceTaint;

inherited attribute forceTaint::[String] with ++;
synthesized attribute needGrammars :: [String];

  -- at this point we need to partition everything into groups:
  -- Group 1: ALTERED.  = extraUnit.compiledList ++ unit.compiledList ++ condUnit.compiledList;
  -- Group 2: TAINTED.  = anything that exports anything ALTERED or TAINTED (inductively)
  -- Group 3: SUSPECT.  = Anything that imports anything ALTERED or TAINTED (noninductively)
  -- Group 4: SAFE.     = All interfaces that aren't in any above group.

{--
 - Inputs:
 -   @param ifaces  The list of interface files that were used, instead of the sources
 -   @inh compiledGrammars  The list of all RootSpec obtained up to this point
 - Outputs:
 -   @syn compiledList  The list of all RootSpecs obtained up to this point (these get translated!)
 -   @syn needGrammars  The list of grammars that must be recompiled.
 -   @syn interfaces  The list of safe interfaces.
 -}
abstract production dependencyAnalysis
top::DependencyAnalysis ::= ifaces::[Decorated Interface]
{
  production attribute ifspecs::[Decorated RootSpec];
  ifspecs = getSpecs(ifaces);
  
  production attribute exportsnifs::[[String]];
  exportsnifs = normalizeExports(ifspecs);

  production attribute importsnifs::[[String]];
  importsnifs = normalizeImports(ifspecs);
  
  production attribute altered::[String];
  altered = collectGrammars(top.compiledGrammars); -- actually underwent changes
  
  production attribute taintedaltered::[String]; -- exports something that underwent changes
  taintedaltered = makeSet( top.forceTaint ++ inductivelyExpand(altered, exportsnifs));
  
  production attribute suspect::[String]; -- directly imports something tainted or altered
  suspect = noninductiveExpansion(taintedaltered, importsnifs);
  
  production attribute safe::[String];
  safe = rem(collectGrammars(ifspecs), taintedaltered ++ suspect);

  -- tainted == rem(taintedaltered, altered)
  
  top.compiledList = top.compiledGrammars;
  top.needGrammars = rem(taintedaltered ++ suspect, altered);
  top.interfaces = keepInterfaces(safe, ifaces);
}

function getSpecs
[Decorated RootSpec] ::= s::[Decorated Interface]
{
  return map((.rSpec), s);
}

function collectGrammars
[String] ::= lst::[Decorated RootSpec]
{
  return map((.declaredName), lst);
}

-- All of these functions give rules of the form [value, conditions.....]
-- put another way, (cadr OR caddr OR cadddr OR ... IMPLIES car)
-- [build what, if what is included]
function normalizeCondBuilds
[[String]] ::= lst::[Decorated RootSpec]
{
  return if null(lst) then [] else head(lst).condBuild ++ normalizeCondBuilds(tail(lst));
}
-- [grammar, grammars exported by this grammar]
function normalizeExports
[[String]] ::= ifs::[Decorated RootSpec]
{
  local attribute n :: String;
  n = head(ifs).declaredName;

  return if null(ifs) then [] else [[n] ++ head(ifs).exportedGrammars] ++ normalizeExports(tail(ifs));
}
-- [grammar, grammars imported by this grammar]
function normalizeImports
[[String]] ::= ifs::[Decorated RootSpec]
{
  local attribute n :: String;
  n = head(ifs).declaredName;

  return if null(ifs) then [] else [[n] ++ head(ifs).moduleNames] ++ normalizeImports(tail(ifs));
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

function keepInterfaces
[Decorated Interface] ::= k::[String] d::[Decorated Interface]
{
  return if null(d) then [] else (if contains(head(d).rSpec.declaredName, k) then [head(d)] else []) ++ keepInterfaces(k, tail(d));
}
