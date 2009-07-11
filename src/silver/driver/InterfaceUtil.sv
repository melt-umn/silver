grammar silver:driver;
import silver:util;
import silver:definition:env;

--abstract production main
--top::Main ::= args::String{
--
--  local attribute strings :: [[String]];
--  strings = [["a","b"],["b","a"]];
----  strings = [["patternmatching:lib","lists:anytype"],["lists:anytype","patternmatching:lib"]];
--
--  local attribute io1 :: IO;
--  io1 = print(folds(", ", findValidInterfaces(strings)) ++ "\n", top.ioIn);
--
--  local attribute io2 :: IO;
--  io2 = print(folds(", ", findInvalidInterfaces(strings)) ++ "\n", io1);
--
--  top.ioOut = io2;
--}

function getInvalidInterfaces
[Decorated Interface] ::= ifs::[Decorated Interface]{
  local attribute nifs::[[String]];
  nifs = normalizeInterfaces(ifs);

  local attribute inv::[String];
  inv = findInvalidInterfaces(nifs);

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
  return if null(d) then [::Decorated Interface] else (if contains(head(d).rSpec.declaredName, k) then [head(d)] else [::Decorated Interface]) ++ keepInterfaces(k, tail(d));
}

function normalizeInterfaces
[[String]] ::= ifs::[Decorated Interface]{

  local attribute n :: String;
  n = head(ifs).rSpec.declaredName;

  return if null(ifs) then [::[String]] else [[n] ++ remove(n, head(ifs).rSpec.moduleNames)] ++ normalizeInterfaces(tail(ifs));
}

function findValidInterfaces
[String] ::= s::[[String]]{
  return rem(getHeads(s), findInvalidInterfaces(s));
}


function findInvalidInterfaces
[String] ::= is::[[String]]{

  local attribute valid::[String];
  valid = findCurrentValidInterfaces(is);

  local attribute invalid::[[String]];
  invalid = removeValidInterfaces(is);

  local attribute difference::[[String]]; 
  difference = removeInterfaceDependancies(valid, invalid);

  return if null(valid) then getHeads(is) else findInvalidInterfaces(difference);
}

--function findRecursiveInterfaces
--[String] ::= is::[[String]]{
--  return findRecursiveInterfacesHelp(largestSet(is), is);
--}
--
--
--function findRecursiveInterfacesHelp
--[String] ::= s::Integer is::[[String]]{
--
--  local attribute current :: [[String]];
--  current = getSetsOfSize(s, is);
--
--  local attribute valid :: [String];
--  valid = findRecursiveInterfacesN(current, current);
--  
--  return if s == 0 then[] else  valid ++ findRecursiveInterfacesHelp(s-1, is);
--
--}
--
--function getSetsOfSize 
--[[String]] ::= s::Integer is::[[String]]{
--  return if null(is) then [] else (if length(head(is)) == s then [head(is)] else []) ++ getSetsOfSize(s, tail(is));
--}
--
--function findRecursiveInterfacesN
--[String] ::= is::[[String]] all::[[String]]{
--  return if null(is) then [] else (if containsSet(head(is), all) then [head(head(is))] else []) ++ findRecursiveInterfacesN(tail(is), all);
--}
--
--function largestSet
--Integer ::= is::[[String]]{
--  return largestSetHelp(0, is);
--}
--
--function largestSetHelp
--Integer ::= current::Integer is::[[String]]{
--  return if null(is) then current else if length(head(is)) > current then largestSetHelp(length(head(is)), tail(is)) else largestSetHelp(current, tail(is));
--}


--[[a, b], [a], [c]] -> [a,c]
function findCurrentValidInterfaces
[String] ::= is::[[String]]{
  return if null(is) then [] else ((if null(tail(head(is))) then [head(head(is))] else []) ++ findCurrentValidInterfaces(tail(is)));
}

--[a,b], [[d, b, a], [c,a,b], [c]] -> [[d], [c], [c]]
function removeInterfaceDependancies
[[String]] ::= s::[String] is::[[String]]{
  return if null(s) then is else removeInterfaceDependancy(head(s), removeInterfaceDependancies(tail(s), is));
}

--[a], [[b, a], [c,a,b], [c]] -> [[b], [c,b], [c]]
function removeInterfaceDependancy
[[String]] ::= s::String is::[[String]]{
  return if null(is) then [::[String]] else [[head(head(is))] ++ remove(s, tail(head(is)))] ++ removeInterfaceDependancy(s, tail(is));
}

--[[a], [a,b]] -> [[a,b]]
function removeValidInterfaces
[[String]] ::= is::[[String]]{
  return if null(is) then [::[String]] else ((if null(tail(head(is))) then [::[String]] else [head(is)]) ++ removeValidInterfaces(tail(is)));
}

function getHeads
[String] ::= s::[[String]]{
  return if null(s) then [] else [head(head(s))] ++ getHeads(tail(s));
}

function getSpecs
[Decorated RootSpec] ::= s::[Decorated Interface]{
  return if null(s) then [] else [head(s).rSpec] ++ getSpecs(tail(s));
}
