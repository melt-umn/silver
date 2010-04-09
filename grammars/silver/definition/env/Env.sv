grammar silver:definition:env;

--look at the things taht use getDclsAll
--move EnvFilter EnvMap to Defs?
 
closed nonterminal Env with typeTree, valueTree, attrTree, nameTree, restTree, productionTree, occursTree;

synthesized attribute typeTree :: [Decorated EnvScope]; -- Associates typename with typerep (e.g. Expr)
synthesized attribute valueTree :: [Decorated EnvScope]; -- Associates name with typerep
synthesized attribute nameTree :: [Decorated EnvScope]; -- Associates short-name with full-qualified-name
synthesized attribute attrTree :: [Decorated EnvScope]; -- Associates an attribute's name with its type
synthesized attribute productionTree :: [Decorated EnvScope]; -- Production signatures

synthesized attribute occursTree :: [Decorated EnvScope]; -- Attribute fname occurs on nonterminal (fname?)

synthesized attribute restTree :: [Decorated EnvScope]; -- this (useless?), close (purpose?), prodAttributes (fix and make its own), lexer classes (move to typeTree?),


----------------------------------------------------------------------------------------------------
--Environment creation functions--------------------------------------------------------------------
----------------------------------------------------------------------------------------------------
abstract production i_emptyEnv 
top::Env ::= 
{
  top.typeTree =  [emptyEnvScope()];
  top.valueTree = [emptyEnvScope()];
  top.nameTree =  [emptyEnvScope()];
  top.attrTree =  [emptyEnvScope()];
  top.productionTree = [emptyEnvScope()];  
  top.occursTree = [emptyEnvScope()]; 

  top.restTree =  [emptyEnvScope()];
}

function emptyEnv
Decorated Env ::=
{
  return decorate i_emptyEnv() with {};
}


function toEnv
Decorated Env ::= d::Decorated Defs {
  return decorate i_toEnv(d) with {};
}

abstract production i_toEnv
top::Env ::= d::Decorated Defs {

  top.typeTree =  [oneEnvScope(buildTree(d.typeList))];
  top.valueTree = [oneEnvScope(buildTree(d.valueList))];
  top.nameTree =  [oneEnvScope(buildTree(d.nameList))];
  top.attrTree =  [oneEnvScope(buildTree(d.attrList))];
  top.productionTree = [oneEnvScope(buildTree(d.productionList))];

  top.occursTree = [oneEnvScope(buildTree(d.occursList))]; 

  top.restTree = [oneEnvScope(buildTree(d.restList))];
}

function appendEnv
Decorated Env ::= e1::Decorated Env  e2::Decorated Env {
  return decorate i_appendEnv(e1, e2) with {};
}


--this should pad with extra scopes
abstract production i_appendEnv
top::Env ::= e1::Decorated Env  e2::Decorated Env {

  top.typeTree =  e1.typeTree ++  e2.typeTree;
  top.valueTree = e1.valueTree ++ e2.valueTree;
  top.nameTree =  e1.nameTree ++ e2.nameTree;
  top.attrTree =  e1.attrTree ++ e2.attrTree;
  top.productionTree = e1.productionTree ++ e2.productionTree;

  top.occursTree = e1.occursTree ++ e2.occursTree;

  top.restTree = e1.restTree ++ e2.restTree;
}

-- Better replacement for appendDefsEnv(x, pushScope(env)) pattern
function newScopeEnv
Decorated Env ::= e1::Decorated Defs  e2::Decorated Env {
  return decorate i_newScopeEnv(e1, e2) with {};
}

abstract production i_newScopeEnv
top::Env ::= d::Decorated Defs  e::Decorated Env {
  top.typeTree = oneEnvScope(buildTree(d.typeList)) :: e.typeTree;
  top.valueTree = oneEnvScope(buildTree(d.valueList)) :: e.valueTree;
  top.nameTree =  oneEnvScope(buildTree(d.nameList)) :: e.nameTree;
  top.attrTree =  oneEnvScope(buildTree(d.attrList)) :: e.attrTree;
  top.productionTree = oneEnvScope(buildTree(d.productionList)) :: e.productionTree;

  top.occursTree = oneEnvScope(buildTree(d.occursList)) :: e.occursTree;

  top.restTree = oneEnvScope(buildTree(d.restList)) :: e.restTree;
}

function appendDefsEnv
Decorated Env ::= e1::Decorated Defs  e2::Decorated Env {
  return decorate i_appendDefsEnv(e1, e2) with {};
}

abstract production i_appendDefsEnv
top::Env ::= d::Decorated Defs e::Decorated Env {

  top.typeTree = [appendEnvScope(oneEnvScope(buildTree(d.typeList)), head(e.typeTree))] ++ tail(e.typeTree);
  top.valueTree = [appendEnvScope(oneEnvScope(buildTree(d.valueList)), head(e.valueTree))] ++ tail(e.valueTree);
  top.nameTree =  [appendEnvScope(oneEnvScope(buildTree(d.nameList)), head(e.nameTree))] ++ tail(e.nameTree);
  top.attrTree =  [appendEnvScope(oneEnvScope(buildTree(d.attrList)), head(e.attrTree))] ++ tail(e.attrTree);
  top.productionTree = [appendEnvScope(oneEnvScope(buildTree(d.productionList)), head(e.productionTree))] ++ tail(e.productionTree);

  top.occursTree = [appendEnvScope(oneEnvScope(buildTree(d.occursList)), head(e.occursTree))] ++ tail(e.occursTree);

  top.restTree = [appendEnvScope(oneEnvScope(buildTree(d.restList)), head(e.restTree))] ++ tail(e.restTree);
}

function pushScope
Decorated Env ::= e::Decorated Env {
  return decorate i_pushScope(e) with {};
}

abstract production i_pushScope
top::Env ::= e::Decorated Env {
  top.typeTree = [emptyEnvScope()] ++ e.typeTree;
  top.valueTree = [emptyEnvScope()] ++ e.valueTree;
  top.nameTree =  [emptyEnvScope()] ++ e.nameTree;
  top.attrTree =  [emptyEnvScope()] ++ e.attrTree;
  top.productionTree = [emptyEnvScope()] ++ e.productionTree;

  top.occursTree = [emptyEnvScope()] ++ e.occursTree;

  top.restTree = [emptyEnvScope()] ++ e.restTree;
}

----------------------------------------------------------------------------------------------------
--Environment query functions-----------------------------------------------------------------------
----------------------------------------------------------------------------------------------------

-- Normally, finds all such occurances.
-- -One variants only search the closest scope
-- TODO: Search for all occurances up to the closest scope that contains an occurance (i.e. lexical scoping rules)

function searchDclsOne
[Decorated EnvItem] ::= search::String e::[Decorated EnvScope]
{
  return getDclsScope(search, head(e));
}

function searchDcls
[Decorated EnvItem] ::= search::String e::[Decorated EnvScope]
{
  return if null(e) then [] else getDclsScope(search, head(e)) ++ searchDcls(search, tail(e));
}

function getDclsOne
[Decorated EnvItem] ::= e::[Decorated EnvScope]
{
  return getDclsScopeAll(head(e));
}

function getDcls
[Decorated EnvItem] ::= e::[Decorated EnvScope]
{
  return if null(e) then [] else getDclsScopeAll(head(e)) ++ getDcls(tail(e));
}

function getValueDclOne
[Decorated EnvItem] ::= search::String e::Decorated Env
{
  return searchDclsOne(search, e.valueTree);
}

function getValueDcl
[Decorated EnvItem] ::= search::String e::Decorated Env
{
  return searchDcls(search, e.valueTree);
}

function getTypeDclOne
[Decorated EnvItem] ::= search::String e::Decorated Env
{
  return searchDclsOne(search, e.typeTree);
}

function getTypeDcl
[Decorated EnvItem] ::= search::String e::Decorated Env
{
  return searchDcls(search, e.typeTree);
}

function getAttributeDclOne
[Decorated EnvItem] ::= search::String e::Decorated Env
{
  return searchDclsOne(search, e.attrTree);
}

function getAttributeDcl
[Decorated EnvItem] ::= search::String e::Decorated Env
{
  return searchDcls(search, e.attrTree);
}

function getProductionDclOne
[Decorated EnvItem] ::= search::String e::Decorated Env
{
  return searchDclsOne(search, e.productionTree);
}

function getProductionDcl
[Decorated EnvItem] ::= search::String e::Decorated Env
{
  return searchDcls(search, e.productionTree);
}

function getFunctionDclOne
[Decorated EnvItem] ::= search::String e::Decorated Env
{
  return searchDclsOne(search, e.productionTree);
}

function getFunctionDcl
[Decorated EnvItem] ::= search::String e::Decorated Env
{
  return searchDcls(search, e.productionTree);
}

function getFullNameDclOne
[Decorated EnvItem] ::= search::String e::Decorated Env
{
  return searchDclsOne(search, e.nameTree);
}

function getFullNameDcl
[Decorated EnvItem] ::= search::String e::Decorated Env
{
  return searchDcls(search, e.nameTree);
}

function doesOccurOn
--attribute, nt, env
Boolean ::= search::String dn::String e::Decorated Env
{
  return doesOccurOnHelp(dn, searchDcls(search, e.occursTree));
}

function doesOccurOnHelp
Boolean ::= dn::String e::[Decorated EnvItem]
{
  return  (!null(e) && head(e).isOccursDeclaration && head(e).decoratesName == dn) 
       || (!null(e) && doesOccurOnHelp(dn, tail(e)));
}

function isClosed
Boolean ::= search::String e::Decorated Env
{
  return isClosedHelp(searchDcls(search, e.restTree));
}

--abstract production searchIsClosed
--top::EnvSearch ::=
--{
--  top.found = isClosedHelp(top.inEnvItems);
----  top.continue = false;
--}

function isClosedHelp
Boolean ::= e::[Decorated EnvItem]
{
  return  (!null(e) && (head(e).isCloseDeclaration))
       || (!null(e) && isClosedHelp(tail(e)));
}

function getProductionAttributes
Decorated Defs ::= search::String e::Decorated Env
{
  return getProductionAttributesHelp(search, getDcls(e.restTree));
}


function getProductionAttributesHelp
Decorated Defs ::= search::String e::[Decorated EnvItem]
{
  local attribute recurse :: Decorated Defs;
  recurse = getProductionAttributesHelp(search, tail(e));

  return if null(e) then emptyDefs()
         else if (head(e).isProductionAttributesDeclaration && head(e).itemName == search)
	      then head(e).attributes
	      else recurse;
}


----------------------------------------------------------------------------------------------------
--Generic environment query functions---------------------------------------------------------------
----------------------------------------------------------------------------------------------------

nonterminal EnvMap with inEnvItem, newEnvItem;
inherited attribute inEnvItem :: Decorated EnvItem;
synthesized attribute newEnvItem :: Decorated EnvItem;

nonterminal EnvFilter with inEnvItem, keep;
synthesized attribute keep :: Boolean;

abstract production reverseLookUp
top::EnvFilter ::= fName::String
{
  top.keep = top.inEnvItem.isFullNameDeclaration
	  && top.inEnvItem.fullName == fName;
}

----------------------------------------------------------------------------------------------------
--General query functions---------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------

function getAllTypeDcls
[Decorated EnvItem] ::= e::Decorated Env
{
  return getDcls(e.typeTree);
}

function getAllValueDcls
[Decorated EnvItem] ::= e::Decorated Env
{
  return getDcls(e.valueTree);
}

function getAllAttributeDcls
[Decorated EnvItem] ::= e::Decorated Env
{
  return getDcls(e.attrTree);
}

function getAllProductionDcls
[Decorated EnvItem] ::= e::Decorated Env
{
  return getDcls(e.productionTree);
}

function getAllFullNameDcls
[Decorated EnvItem] ::= e::Decorated Env
{
  return getDcls(e.nameTree);
}

function getOccursDcls
[Decorated EnvItem] ::= e::Decorated Env
{
  return getDcls(e.occursTree);
}

function getOtherDcls
[Decorated EnvItem] ::= e::Decorated Env
{
  return getDcls(e.restTree);
}

function getAllOccuringOn
[Decorated EnvItem] ::= dn::String e::Decorated Env
{
  return getAllOccuringOnHelp(dn, getDcls(e.occursTree), e);
}

function getAllOccuringOnHelp
[Decorated EnvItem] ::= dn::String e::[Decorated EnvItem] fullEnv::Decorated Env
{
  return if null(e)
	 then []
	 else if (head(e).isOccursDeclaration) && (head(e).decoratesName == dn)
     	      then getAttributeDcl(head(e).itemName, fullEnv) ++ recurse
       	      else recurse;

  local attribute recurse :: [Decorated EnvItem];
  recurse = getAllOccuringOnHelp(dn, tail(e), fullEnv);
}

