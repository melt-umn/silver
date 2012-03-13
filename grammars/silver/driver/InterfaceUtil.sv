grammar silver:driver;

nonterminal DependencyAnalysis with compiledList, needGrammars, interfaces;

synthesized attribute needGrammars :: [String];

  -- at this point we need to partition everything into groups:
  -- Group 1: ALTERED.  = anything parsed from source; interface invalid/nonexistant.
  -- Group 2: TAINTED.  = anything that exports anything ALTERED or TAINTED (inductively)
  -- Group 3: SUSPECT.  = Anything that imports anything ALTERED or TAINTED (noninductively)
  -- Group 4: SAFE.     = All interfaces that aren't in any above group.

{--
 - Inputs:
 -   @param ifaces  The list of interface files that were used, instead of the sources
 -   @param compiledRootSpecs  The list of all RootSpec obtained up to this point, from actually parsing a grammar
 - Outputs:
 -   @syn compiledList  The list of all RootSpecs obtained up to this point (these get translated!)
 -   @syn needGrammars  The list of grammars that must be recompiled.
 -   @syn interfaces  The list of safe interfaces.
 -}
abstract production dependencyAnalysis
top::DependencyAnalysis ::= ifaces::[Decorated Interface]  compiledRootSpecs::[Decorated RootSpec]
{
  production attribute ifspecs::[Decorated RootSpec];
  ifspecs = getSpecs(ifaces);
  
  production attribute exportsnifs::[[String]];
  exportsnifs = normalizeExports(ifspecs);

  production attribute importsnifs::[[String]];
  importsnifs = normalizeImports(ifspecs);
  
  {--
   - The names of the grammars that were dirty, and actually underwent changes.
   -}
  production attribute altered::[String];
  altered = collectGrammars(compiledRootSpecs);
  
  {--
   - Those interfaces that _export_ something that was altered.
   -}
  production attribute taintedaltered::[String];
  taintedaltered = inductivelyExpand(altered, exportsnifs);
  
  {--
   - Those interfaces the _import_ something that was tainted/altered.
   -}
  production attribute suspect::[String]; -- directly imports something tainted or altered
  suspect = noninductiveExpansion(taintedaltered, importsnifs);
  
  {--
   - Those interfaces that are not affected at all by any changes.
   -}
  production attribute safe::[String];
  safe = rem(collectGrammars(ifspecs), taintedaltered ++ suspect);

  -- not used, so not computed..
  -- tainted == rem(taintedaltered, altered)
  
  top.needGrammars = rem(taintedaltered ++ suspect, altered); -- i.e. tainted + suspect
  top.interfaces = keepInterfaces(safe, ifaces);
  top.compiledList = compiledRootSpecs;
}

function keepInterfaces
[Decorated Interface] ::= k::[String] d::[Decorated Interface]
{
  return if null(d) then [] else (if contains(head(d).rSpec.declaredName, k) then [head(d)] else []) ++ keepInterfaces(k, tail(d));
}

function normalizer
[String] ::= f::Function([String] ::= Decorated RootSpec)  r::Decorated RootSpec
{
  return r.declaredName :: f(r);
}
function normalizeExports
[[String]] ::= ifs::[Decorated RootSpec]
{
  return map(normalizer((.exportedGrammars), _), ifs);
}
function normalizeImports
[[String]] ::= ifs::[Decorated RootSpec]
{
  return map(normalizer((.moduleNames), _), ifs);
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

