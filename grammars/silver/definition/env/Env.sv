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

nonterminal Env with typeTree, valueTree, attrTree, prodOccursTree, occursTree, constructorTree;

inherited attribute typeTree      :: [Decorated EnvScope<DclInfo>]; -- Expr is type tau
inherited attribute valueTree     :: [Decorated EnvScope<DclInfo>]; -- x has type tau
inherited attribute attrTree      :: [Decorated EnvScope<DclInfo>]; -- attr a has type tau

inherited attribute prodOccursTree :: Decorated EnvScope<DclInfo>; -- value on prod
inherited attribute occursTree     :: Decorated EnvScope<DclInfo>; -- attr on NT

inherited attribute constructorTree :: Decorated EnvScope<DclInfo>; -- productions by nonterminal

----------------------------------------------------------------------------------------------------
--Environment creation functions--------------------------------------------------------------------
----------------------------------------------------------------------------------------------------

abstract production i_env_dummy_record
top::Env ::=
{
}

function emptyEnv
Decorated Env ::=
{
  production attribute top::Env;  top = i_env_dummy_record();
  
  top.typeTree = [emptyEnvScope()];
  top.valueTree = [emptyEnvScope()];
  top.attrTree = [emptyEnvScope()];
  
  top.prodOccursTree = emptyEnvScope();
  top.occursTree = emptyEnvScope();
  
  top.constructorTree = emptyEnvScope();
  
  return top;
}

function toEnv
Decorated Env ::= d::Defs
{
  production attribute top::Env;  top = i_env_dummy_record();
  
  top.typeTree = [oneEnvScope(buildTree(d.typeList))];
  top.valueTree = [oneEnvScope(buildTree(d.valueList))];
  top.attrTree = [oneEnvScope(buildTree(d.attrList))];

  top.prodOccursTree = oneEnvScope(buildTree(mapFullnameDcls(d.prodOccursList)));
  top.occursTree = oneEnvScope(buildTree(mapFullnameDcls(d.occursList)));
  
  top.constructorTree = oneEnvScope(directBuildTree(d.constructorList));
  
  return top;
}
function appendEnv
Decorated Env ::= e1::Decorated Env  e2::Decorated Env
{
  production attribute top::Env;  top = i_env_dummy_record();
  
  top.typeTree = e1.typeTree ++ e2.typeTree;
  top.valueTree = e1.valueTree ++ e2.valueTree;
  top.attrTree = e1.attrTree ++ e2.attrTree;

  top.prodOccursTree = appendEnvScope(e1.prodOccursTree, e2.prodOccursTree);
  top.occursTree = appendEnvScope(e1.occursTree, e2.occursTree);
  
  top.constructorTree = appendEnvScope(e1.constructorTree, e2.constructorTree);

  return top;
}

-- Better replacement for appendDefsEnv(x, pushScope(env)) pattern
function newScopeEnv
Decorated Env ::= d::Defs  e::Decorated Env
{
  production attribute top::Env;  top = i_env_dummy_record();
  
  top.typeTree = oneEnvScope(buildTree(d.typeList)) :: e.typeTree;
  top.valueTree = oneEnvScope(buildTree(d.valueList)) :: e.valueTree;
  top.attrTree = oneEnvScope(buildTree(d.attrList)) :: e.attrTree;

  top.prodOccursTree = consEnvScope(buildTree(mapFullnameDcls(d.prodOccursList)), e.prodOccursTree);
  top.occursTree = consEnvScope(buildTree(mapFullnameDcls(d.occursList)), e.occursTree);
  
  top.constructorTree = consEnvScope(directBuildTree(d.constructorList), e.constructorTree);

  return top;
}

----------------------------------------------------------------------------------------------------
--Environment query functions-----------------------------------------------------------------------
----------------------------------------------------------------------------------------------------

function searchEnvAll
[DclInfo] ::= search::String e::[Decorated EnvScope<DclInfo>]
{
  return if null(e) then []
         else searchEnvScope(search, head(e)) ++ searchEnvAll(search, tail(e));
}

function searchEnv
[DclInfo] ::= search::String e::[Decorated EnvScope<DclInfo>]
{
  local attribute found :: [DclInfo];
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


function getProdsOn
[DclInfo] ::= fnnt::String e::Decorated Env
{
  return searchEnvScope(fnnt, e.constructorTree);
}

function getAttrsOn
[DclInfo] ::= fnnt::String e::Decorated Env
{
  return searchEnvScope(fnnt, e.occursTree);
}
