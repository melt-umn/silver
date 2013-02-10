grammar silver:definition:env;


-- emptyEnv    Decorated Env ::=
-- toEnv       Decorated Env ::= d::Defs
-- appendEnv   Decorated Env ::= e1::Decorated Env  e2::Decorated Env
-- newScopeEnv Decorated Env ::= e1::Defs  e2::Decorated Env

-- [DclInfo] ::= search::String e::Decorated Env
-- getValueDclInScope getValueDcl getValueDclAll
-- getTypeDcl
-- getAttrDcl

-- getProdAttrs [DclInfo] ::= prod::String e::Decorated Env

nonterminal Env with typeTree, valueTree, attrTree, prodOccursTree, occursTree;

synthesized attribute typeTree      :: [Decorated EnvScope<DclInfo>]; -- Expr is type tau
synthesized attribute valueTree     :: [Decorated EnvScope<DclInfo>]; -- x has type tau
synthesized attribute attrTree      :: [Decorated EnvScope<DclInfo>]; -- attr a has type tau

synthesized attribute prodOccursTree :: Decorated EnvScope<DclInfo>; -- value on prod
synthesized attribute occursTree     :: Decorated EnvScope<DclInfo>; -- attr on NT

----------------------------------------------------------------------------------------------------
--Environment creation functions--------------------------------------------------------------------
----------------------------------------------------------------------------------------------------

function emptyEnv
Decorated Env ::=
{
  return decorate i_emptyEnv() with {};
}
abstract production i_emptyEnv
top::Env ::=
{
  top.typeTree = [emptyEnvScope()];
  top.valueTree = [emptyEnvScope()];
  top.attrTree = [emptyEnvScope()];
  
  top.prodOccursTree = emptyEnvScope();
  top.occursTree = emptyEnvScope();
}

function toEnv
Decorated Env ::= d::[Def]
{
  return newScopeEnv(d, emptyEnv());
}

function appendEnv
Decorated Env ::= e1::Decorated Env  e2::Decorated Env
{
  return decorate i_appendEnv(e1, e2) with {};
}
abstract production i_appendEnv
top::Env ::= e1::Decorated Env  e2::Decorated Env
{
  top.typeTree = e1.typeTree ++ e2.typeTree;
  top.valueTree = e1.valueTree ++ e2.valueTree;
  top.attrTree = e1.attrTree ++ e2.attrTree;

  top.prodOccursTree = appendEnvScope(e1.prodOccursTree, e2.prodOccursTree);
  top.occursTree = appendEnvScope(e1.occursTree, e2.occursTree);
}

-- Better replacement for appendDefsEnv(x, pushScope(env)) pattern
function newScopeEnv
Decorated Env ::= d::[Def]  e::Decorated Env
{
  return decorate i_newScopeEnv(foldr(consDefs, nilDefs(), d), e) with {};
}
abstract production i_newScopeEnv
top::Env ::= d::Defs  e::Decorated Env
{
  top.typeTree = oneEnvScope(buildTree(d.typeList)) :: e.typeTree;
  top.valueTree = oneEnvScope(buildTree(d.valueList)) :: e.valueTree;
  top.attrTree = oneEnvScope(buildTree(d.attrList)) :: e.attrTree;

  top.prodOccursTree = consEnvScope(buildTree(mapFullnameDcls(d.prodOccursList)), e.prodOccursTree);
  top.occursTree = consEnvScope(buildTree(mapFullnameDcls(d.occursList)), e.occursTree);
}

----------------------------------------------------------------------------------------------------
--Environment query functions-----------------------------------------------------------------------
----------------------------------------------------------------------------------------------------

function searchEnvAll
[a] ::= search::String e::[Decorated EnvScope<a>]
{
  return if null(e) then []
         else searchEnvScope(search, head(e)) ++ searchEnvAll(search, tail(e));
}

function searchEnv
[a] ::= search::String e::[Decorated EnvScope<a>]
{
  local attribute found :: [a];
  found = searchEnvScope(search, head(e));
  
  return if null(e) then []
         else if null(found) then searchEnv(search, tail(e))
         else found;
}

function getValueDclInScope
[DclInfo] ::= search::String e::Decorated Env
{
  return searchEnvScope(search, head(e.valueTree));
}
function getValueDcl
[DclInfo] ::= search::String e::Decorated Env
{
  return searchEnv(search, e.valueTree);
}
function getValueDclAll
[DclInfo] ::= search::String e::Decorated Env
{
  return searchEnvAll(search, e.valueTree);
}

function getTypeDclInScope
[DclInfo] ::= search::String e::Decorated Env
{
  return searchEnvScope(search, head(e.typeTree));
}
function getTypeDcl
[DclInfo] ::= search::String e::Decorated Env
{
  return searchEnv(search, e.typeTree);
}
function getTypeDclAll
[DclInfo] ::= search::String e::Decorated Env
{
  return searchEnvAll(search, e.typeTree);
}

function getAttrDclInScope
[DclInfo] ::= search::String e::Decorated Env
{
  return searchEnvScope(search, head(e.attrTree));
}
function getAttrDcl
[DclInfo] ::= search::String e::Decorated Env
{
  return searchEnv(search, e.attrTree);
}
function getAttrDclAll
[DclInfo] ::= search::String e::Decorated Env
{
  return searchEnvAll(search, e.attrTree);
}

function getOccursDcl
[DclInfo] ::= fnat::String fnnt::String e::Decorated Env
{
  -- retrieve all attribute Dcls on NT fnnt
  return occursOnHelp(searchEnvScope(fnnt, e.occursTree), fnat);
}
function occursOnHelp
[DclInfo] ::= i::[DclInfo] fnat::String
{
  -- Inefficiency. Linear search for attribute on a nonterminal
  return if null(i) then []
         else if head(i).attrOccurring == fnat
              then head(i) :: occursOnHelp(tail(i), fnat)
              else occursOnHelp(tail(i), fnat);
}

function getProdAttrs
[DclInfo] ::= fnprod::String e::Decorated Env
{
  return searchEnvScope(fnprod, e.prodOccursTree);
}


-- It's never possible to know "all" attributes, so the next function is okay,
-- but it is possible to know all non-forwarding productions, but the normal
-- environment can't do it.  So you should consult the flow env for that info.
--function getProdsOn

function getAttrsOn
[DclInfo] ::= fnnt::String e::Decorated Env
{
  return searchEnvScope(fnnt, e.occursTree);
}

-- This ensure the annotation list is in the properly sorted order!
function annotationsForNonterminal
[NamedSignatureElement] ::= nt::TypeExp  env::Decorated Env
{
  local annos :: [DclInfo] =
    filter((.isAnnotation), getAttrsOn(nt.typeName, env));
  
  return sortBy(namedSignatureElementLte, map(annoInstanceToNamed(nt, _), annos));
}
-- only used by the above
function annoInstanceToNamed
NamedSignatureElement ::= nt::TypeExp  anno::DclInfo
{
  -- Used to compute the local typerep for this nonterminal
  anno.givenNonterminalType = nt;
  
  -- We are currently cutting annotations down to just their SHORT NAME for the purpose of supplying the values.
  -- Basically, nonterminals should have curated the list of annotations so the names do not overlap.
  
  local annoShortName :: String =
    substring(lastIndexOf(":", anno.attrOccurring) + 1, length(anno.attrOccurring), anno.attrOccurring);
  
  return namedSignatureElement(annoShortName, anno.typerep);
}

