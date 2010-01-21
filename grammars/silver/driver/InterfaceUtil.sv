grammar silver:driver;
import silver:util;
import silver:definition:env;

function getInvalidInterfaces
[Decorated Interface] ::= ifs::[Decorated Interface]{
  local attribute nifs::[[String]];
  nifs = normalizeInterfaces(ifs);

  local attribute inv::[String];
  inv = findInvalidInterfaces([], nifs);

  return keepInterfaces(inv, ifs);
}

function getValidInterfaces
[Decorated Interface] ::= ifs::[Decorated Interface]{
  local attribute nifs::[[String]];
  nifs = normalizeInterfaces(ifs);

  local attribute v::[String];
  v = findValidInterfaces(nifs);

  return keepInterfaces(v, ifs);
}

function keepInterfaces
[Decorated Interface] ::= k::[String] d::[Decorated Interface]{ 
  return if null(d) then [] else (if contains(head(d).rSpec.declaredName, k) then [head(d)] else []) ++ keepInterfaces(k, tail(d));
}

function normalizeInterfaces
[[String]] ::= ifs::[Decorated Interface]{

  local attribute n :: String;
  n = head(ifs).rSpec.declaredName;

  return if null(ifs) then [] else [[n] ++ remove(n, head(ifs).rSpec.moduleNames)] ++ normalizeInterfaces(tail(ifs));
}

function findValidInterfaces
[String] ::= s::[[String]]{
  return rem(getHeads(s), findInvalidInterfaces([],s));
}

{- Historical note:
 - We previously did this co-inductively. i.e. assumed everything is invalid,
 - and iteratively removing things we could tell were, in fact, valid. (zero dependencies)
 - This fails horribly for circular dependencies, which we do allow. (it could never conclude they were valid)
 - So, now we do it inductively.
 
 - Assume everything is valid.  Remove everything we have an interface for from dependencies.
 - If anything has something left in its dependencies, it must be INvalid (was compiled, rather than interface)
 - However, we then have to go add back & add anything that depends on this invalid set.
 - So we iterate to a fixed point again.
 -}

-- finds "valid" interfaces (those with no dependancies)
-- removes the valid interfaces from the dependancies of the remaining interfaces
-- iterate until fixpoint (i.e. no valid interfaces left)

-- key idea: anything that has a dependancy outside the list of interfaces needs to be recompiled because that was compiled
-- PROBLEM: circular dependancies.
function findInvalidInterfaces
[String] ::= invalid::[String] maybevalid::[[String]]{

  -- Everything we have an interface for
  local attribute haveifacefor::[String];
  haveifacefor = getHeads(maybevalid);

  -- Remove dependencies on things we have a (maybevalid) interface for
  local attribute difference::[[String]]; 
  difference = removeInterfaceDependancies(haveifacefor, maybevalid);

  -- Find anything with dependencies left. These are invalid.
  local attribute moreinvalid::[String];
  moreinvalid = findCurrentInvalidInterfaces(difference);

  -- Remove definitely invalid from maybevalid
  local attribute newmaybevalid::[[String]];
  newmaybevalid = removeInvalids(moreinvalid, maybevalid);

  return if null(moreinvalid) then invalid else findInvalidInterfaces(invalid ++ moreinvalid, newmaybevalid);
}

function findCurrentInvalidInterfaces
[String] ::= is::[[String]]{
  return if null(is)
         then []
         else ((if null(tail(head(is))) -- "Do you have any dependancies listed?"
                then []                 -- No? Well, you're (maybe) valid.
                else [head(head(is))])  -- Yes? Well, you MUST be invalid!
              ++ findCurrentInvalidInterfaces(tail(is)));
}

-- Removes every interface listed in "s" from the dependencies of everything in "is"
function removeInterfaceDependancies
[[String]] ::= s::[String] is::[[String]]{
  return if null(s)
         then is
         else removeInterfaceDependancy(head(s), removeInterfaceDependancies(tail(s), is));
}

-- Removes the interface "s" from the dependencies of everything in "is"
function removeInterfaceDependancy
[[String]] ::= s::String is::[[String]]{
  return if null(is) then [] else [[head(head(is))] ++ remove(s, tail(head(is)))] ++ removeInterfaceDependancy(s, tail(is));
}

-- Removes the interfaces from the set
function removeInvalid
[[String]] ::= inv::String lst::[[String]]
{
  return if null(lst)
         then []
         else if head(head(lst)) == inv
              then removeInvalid(inv, tail(lst))
              else [head(lst)] ++ removeInvalid(inv, tail(lst));
}

function removeInvalids
[[String]] ::= inv::[String] lst::[[String]]
{
  return if null(inv)
         then lst
         else removeInvalids(tail(inv), removeInvalid(head(inv), lst));
}

function getHeads
[String] ::= s::[[String]]{
  return if null(s) then [] else [head(head(s))] ++ getHeads(tail(s));
}

function getSpecs
[Decorated RootSpec] ::= s::[Decorated Interface]{
  return if null(s) then [] else [head(s).rSpec] ++ getSpecs(tail(s));
}
