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

nonterminal Env with typeTree, valueTree, attrTree, prodOccursTree, occursTree, prodsForNtTree;

synthesized attribute typeTree      :: [EnvScope<DclInfo>]; -- Expr is type tau
synthesized attribute valueTree     :: [EnvScope<DclInfo>]; -- x has type tau
synthesized attribute attrTree      :: [EnvScope<DclInfo>]; -- attr a has type tau

synthesized attribute prodOccursTree :: EnvScope<DclInfo>; -- value on prod
synthesized attribute occursTree     :: EnvScope<DclInfo>; -- attr on NT

synthesized attribute prodsForNtTree :: [EnvScope<DclInfo>]; -- maps nt fname to prods known to construct it

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
  
  top.prodsForNtTree = [emptyEnvScope()];
}

function toEnv
Decorated Env ::= d::[Def]
{
  return newScopeEnv(d, emptyEnv());
}

{--
 - appendEnv exists because we do a weird scope swizzling.
 - Our scopes go [global, import, grammar] but hierarchically we
 - should have   [global, grammar, import] because grammar-wide names would
 - seem to have wider scope than per-file imports.
 - But this would be horrible semantics: the file in question is even included
 - in the grammar-wide scope. So imports would hide things in the local file, even.
 - 
 - Instead, we build these three scopes as essentially separate environments,
 - and append them together in the correct order.
 -}
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

  top.prodsForNtTree = e1.prodsForNtTree ++ e2.prodsForNtTree;
}

{--
 - The usual means of introducing new defs to an environment, by creating a new nested scope.
 -}
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

  top.prodsForNtTree = oneEnvScope(buildTree(map(envItemNTFromProdDcl, d.prodDclList))) :: e.prodsForNtTree;
}

----------------------------------------------------------------------------------------------------
--Environment query functions-----------------------------------------------------------------------
----------------------------------------------------------------------------------------------------

function searchEnvAll
[a] ::= search::String e::[EnvScope<a>]
{
  return flatMap(searchEnvScope(search, _), e);
}

function searchEnv
[a] ::= search::String e::[EnvScope<a>]
{
  local found :: [a] = searchEnvScope(search, head(e));
  
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

-- Do not rely on this just yet, it's wonky.
-- It'll find all productions known locally to construct a nt.
-- This ought to be more limited than that... perhaps only those know to the nt declaration, or only those non-forwarding.
function getProdsForNt
[DclInfo] ::= fnnt::String e::Decorated Env
{
  return searchEnvAll(fnnt, e.prodsForNtTree);
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
[NamedSignatureElement] ::= nt::Type  env::Decorated Env
{
  local annos :: [DclInfo] =
    filter((.isAnnotation), getAttrsOn(nt.typeName, env));
  
  return sortBy(namedSignatureElementLte, map(annoInstanceToNamed(nt, _), annos));
}
-- only used by the above
function annoInstanceToNamed
NamedSignatureElement ::= nt::Type  anno::DclInfo
{
  -- Used to compute the local typerep for this nonterminal
  anno.givenNonterminalType = nt;
  
  return namedSignatureElement(anno.attrOccurring, anno.typerep);
}

