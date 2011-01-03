grammar silver:definition:env;


-- emptyEnv    Decorated Env ::=
-- toEnv       Decorated Env ::= d::Defs
-- appendEnv   Decorated Env ::= e1::Decorated Env  e2::Decorated Env
-- newScopeEnv Decorated Env ::= e1::Defs  e2::Decorated Env

-- [Decorated DclInfo] ::= search::String e::Decorated Env
-- getValueDclInScope getValueDcl getValueDclAll
-- getTypeDcl
-- getAttrDcl

-- getProdAttrs [Decorated DclInfo] ::= prod::String e::Decorated Env

nonterminal Env with typeTree, valueTree, attrTree, prodOccursTree, occursTree;

synthesized attribute typeTree      ::  Decorated EnvScope ; -- Expr is type tau
synthesized attribute valueTree     :: [Decorated EnvScope]; -- x has type tau
synthesized attribute attrTree      ::  Decorated EnvScope ; -- attr a has type tau

synthesized attribute prodOccursTree :: Decorated EnvScope; -- value on prod
synthesized attribute occursTree     :: Decorated EnvScope; -- attr on NT

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
  top.typeTree = emptyEnvScope();
  top.valueTree = [emptyEnvScope()];
  top.attrTree = emptyEnvScope();
  top.prodOccursTree = emptyEnvScope();
  top.occursTree = emptyEnvScope();
}

function toEnv
Decorated Env ::= d::Defs
{
  return decorate i_toEnv(d) with {};
}
abstract production i_toEnv
top::Env ::= d::Defs
{
  top.typeTree = oneEnvScope(buildTree(d.typeList));
  top.valueTree = [oneEnvScope(buildTree(d.valueList))];
  top.attrTree = oneEnvScope(buildTree(d.attrList));

  top.prodOccursTree = oneEnvScope(buildTree(mapFullnameDcls(d.prodOccursList)));
  top.occursTree = oneEnvScope(buildTree(mapFullnameDcls(d.occursList)));
}

-- This is a weird function due to scoping. Can we eliminate it? TODO
function appendEnv
Decorated Env ::= e1::Decorated Env  e2::Decorated Env
{
  return decorate i_appendEnv(e1, e2) with {};
}
abstract production i_appendEnv
top::Env ::= e1::Decorated Env  e2::Decorated Env
{
  top.typeTree =  appendEnvScope(e1.typeTree, e2.typeTree);
  top.valueTree = e1.valueTree ++ e2.valueTree; -- See? Here we're creating new scopes! a bunch, too.
  top.attrTree =  appendEnvScope(e1.attrTree, e2.attrTree);

  top.prodOccursTree = appendEnvScope(e1.prodOccursTree, e2.prodOccursTree);
  top.occursTree = appendEnvScope(e1.occursTree, e2.occursTree);
}

-- Better replacement for appendDefsEnv(x, pushScope(env)) pattern
function newScopeEnv
Decorated Env ::= e1::Defs  e2::Decorated Env
{
  return decorate i_newScopeEnv(e1, e2) with {};
}
abstract production i_newScopeEnv
top::Env ::= d::Defs  e::Decorated Env
{
  top.typeTree = consEnvScope(buildTree(d.typeList), e.typeTree);
  top.valueTree = oneEnvScope(buildTree(d.valueList)) :: e.valueTree; -- new scope of values
  top.attrTree = consEnvScope(buildTree(d.attrList), e.attrTree);

  top.prodOccursTree = consEnvScope(buildTree(mapFullnameDcls(d.prodOccursList)), e.prodOccursTree);
  top.occursTree = consEnvScope(buildTree(mapFullnameDcls(d.occursList)), e.occursTree);
}

----------------------------------------------------------------------------------------------------
--Environment query functions-----------------------------------------------------------------------
----------------------------------------------------------------------------------------------------

function searchEnvAll
[Decorated DclInfo] ::= search::String e::[Decorated EnvScope]
{
  return if null(e) then []
         else searchEnvScope(search, head(e)) ++ searchEnvAll(search, tail(e));
}

function searchEnv
[Decorated DclInfo] ::= search::String e::[Decorated EnvScope]
{
  local attribute found :: [Decorated DclInfo];
  found = searchEnvScope(search, head(e));
  
  return if null(e) then []
         else if null(found) then searchEnv(search, tail(e))
         else found;
}

function getValueDclInScope
[Decorated DclInfo] ::= search::String e::Decorated Env
{
  return searchEnvScope(search, head(e.valueTree));
}
function getValueDcl
[Decorated DclInfo] ::= search::String e::Decorated Env
{
  return searchEnv(search, e.valueTree);
}
function getValueDclAll
[Decorated DclInfo] ::= search::String e::Decorated Env
{
  return searchEnvAll(search, e.valueTree);
}

function getTypeDcl
[Decorated DclInfo] ::= search::String e::Decorated Env
{
  return searchEnvScope(search, e.typeTree);
}

function getAttrDcl
[Decorated DclInfo] ::= search::String e::Decorated Env
{
  return searchEnvScope(search, e.attrTree);
}

function getOccursDcl
[Decorated DclInfo] ::= fnat::String fnnt::String e::Decorated Env
{
  -- retrieve all attribute Dcls on NT fnnt
  return occursOnHelp(searchEnvScope(fnnt, e.occursTree), fnat);
}
function occursOnHelp
[Decorated DclInfo] ::= i::[Decorated DclInfo] fnat::String
{
  -- Inefficiency. Linear search for attribute on a nonterminal
  return if null(i) then []
         else if head(i).attrOccurring == fnat
              then head(i) :: occursOnHelp(tail(i), fnat)
              else occursOnHelp(tail(i), fnat);
}

function getProdAttrs
[Decorated DclInfo] ::= fnprod::String e::Decorated Env
{
  return searchEnvScope(fnprod, e.prodOccursTree);
}

