grammar silver:compiler:definition:env;


-- emptyEnv    Decorated Env ::=
-- toEnv       Decorated Env ::= d::Defs
-- appendEnv   Decorated Env ::= e1::Decorated Env  e2::Decorated Env
-- newScopeEnv Decorated Env ::= e1::Defs  e2::Decorated Env

-- [DclInfo] ::= search::String e::Decorated Env
-- getValueDclInScope getValueDcl getValueDclAll
-- getTypeDcl
-- getAttrDcl

-- getProdAttrs [DclInfo] ::= prod::String e::Decorated Env

nonterminal Env with typeTree, valueTree, attrTree, instTree, prodOccursTree, occursTree, prodsForNtTree;

synthesized attribute typeTree      :: [EnvTree<DclInfo>]; -- Expr is type tau
synthesized attribute valueTree     :: [EnvTree<DclInfo>]; -- x has type tau
synthesized attribute attrTree      :: [EnvTree<DclInfo>]; -- attr a has type tau

synthesized attribute instTree       :: EnvTree<DclInfo>; -- class on type
synthesized attribute prodOccursTree :: EnvTree<DclInfo>; -- value on prod
synthesized attribute occursTree     :: EnvTree<DclInfo>; -- attr on NT

synthesized attribute prodsForNtTree :: [EnvTree<DclInfo>]; -- maps nt fname to prods known to construct it

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
  top.typeTree = [emptyEnvTree()];
  top.valueTree = [emptyEnvTree()];
  top.attrTree = [emptyEnvTree()];
  
  top.instTree = emptyEnvTree();
  top.prodOccursTree = emptyEnvTree();
  top.occursTree = emptyEnvTree();
  
  top.prodsForNtTree = [emptyEnvTree()];
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

  top.instTree = appendEnvTree(e1.instTree, e2.instTree);
  top.prodOccursTree = appendEnvTree(e1.prodOccursTree, e2.prodOccursTree);
  top.occursTree = appendEnvTree(e1.occursTree, e2.occursTree);

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
  top.typeTree = buildTree(d.typeList) :: e.typeTree;
  top.valueTree = buildTree(d.valueList) :: e.valueTree;
  top.attrTree = buildTree(d.attrList) :: e.attrTree;

  top.instTree = consEnvTree(mapFullnameDcls(d.instList), e.instTree);
  top.prodOccursTree = consEnvTree(mapFullnameDcls(d.prodOccursList), e.prodOccursTree);
  top.occursTree = e.occursTree;

  top.prodsForNtTree = buildTree(map(envItemNTFromProdDcl, d.prodDclList)) :: e.prodsForNtTree;
}

{--
 - Introduces new occurs defs to an environment.
 - This is seperate from newScopeEnv as we must be able to build the other env trees without having the occurs tree.
 -}
function occursEnv
Decorated Env ::= d::[DclInfo]  e::Decorated Env
{
  return decorate i_occursEnv(d, e) with {};
}
abstract production i_occursEnv
top::Env ::= d::[DclInfo]  e::Decorated Env
{
  top.typeTree = e.typeTree;
  top.valueTree = e.valueTree;
  top.attrTree = e.attrTree;
  
  top.instTree = e.instTree;
  top.prodOccursTree = e.prodOccursTree;
  top.occursTree = consEnvTree(mapFullnameDcls(d), e.occursTree);
  
  top.prodsForNtTree = e.prodsForNtTree;
}

----------------------------------------------------------------------------------------------------
--Environment query functions-----------------------------------------------------------------------
----------------------------------------------------------------------------------------------------

function searchEnvAll
[a] ::= search::String e::[EnvTree<a>]
{
  return flatMap(searchEnvTree(search, _), e);
}

function searchEnv
[a] ::= search::String e::[EnvTree<a>]
{
  local found :: [a] = searchEnvTree(search, head(e));
  
  return if null(e) then []
         else if null(found) then searchEnv(search, tail(e))
         else found;
}

function getValueDclInScope
[DclInfo] ::= search::String e::Decorated Env
{
  return searchEnvTree(search, head(e.valueTree));
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
  return searchEnvTree(search, head(e.typeTree));
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
  return searchEnvTree(search, head(e.attrTree));
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
  return occursOnHelp(searchEnvTree(fnnt, e.occursTree), fnat);
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
  return searchEnvTree(fnprod, e.prodOccursTree);
}

{--
 - Get all productions for a nonterminal known to local environment.
 - (forwarding and non-forwarding.)
 - Current known use cases:
 -  1. Interference testing code generation (randomly generate trees)
 -       - Because we test each production independently, we may not actually need this?
 -  2. MWDA checking known forwarding productions on attribute occurrence declaration.
 -       - We need to be able to look from forwarding to occurs, and from
 -         occurs to forwarding to cover all cases.
 -  3. For the automatic attributes extension
 -       - to implement propagate on all the known non-forwarding productions of a nonterminal.
 - You should probably have a good reason for using this, and document it here if you do.
 -}
function getKnownProds
[DclInfo] ::= fnnt::String e::Decorated Env
{
  return searchEnvAll(fnnt, e.prodsForNtTree);
}

-- The list of non-forwarding productions may contain productions from `options` not
-- imported locally, and so we must consult the "flow environment" for that information:
--function getNonforwardingProds

{--
 - Returns all attributes known locally to occur on a nonterminal.
 - Obviously we can never know all attributes, but we generally don't need to for
 - any reason.
 -}
function getAttrsOn
[DclInfo] ::= fnnt::String e::Decorated Env
{
  return searchEnvTree(fnnt, e.occursTree);
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
  
  return namedSignatureElement(anno.attrOccurring, anno.typeScheme.typerep);
}

-- Looks up class instances matching a type
function getInstanceDcl
[DclInfo] ::= fntc::String t::Type e::Decorated Env
{
  local c::Context = instContext(fntc, t);
  c.env = e;
  return c.resolved;
}

-- Compute a lower bound on the members of an InhSet type, including transitive ones arising from subset constraints
function getMinInhSetMembers
([String], [TyVar]) ::= seen::[TyVar] t::Type e::Decorated Env
{
  local c::Context = inhSubsetContext(varType(freshTyVar(inhSetKind())), t);
  c.env = e;
  
  local recurse::[([String], [TyVar])] =
    map(
      \ d::DclInfo -> getMinInhSetMembers(t.freeVariables ++ seen, d.typeScheme.monoType, e),
      c.resolved);

  return
    case t of
    | skolemType(tv) when contains(tv, seen) -> ([], [])
    | varType(_) -> ([], [])  -- If an InhSet is unspecialized after type checking, assume it is empty
    | _ -> (sort(unions(t.inhSetMembers :: map(fst, recurse))), unions(t.freeVariables :: map(snd, recurse))) 
    end;
}

function getMinRefSet
[String] ::= t::Type e::Decorated Env
{
  return
    case t of
    | decoratedType(_, i) -> getMinInhSetMembers([], i, e).fst
    | _ -> []
    end;
}

-- Try to compute an upper bound on the members of an InhSet type, including transitive ones arising from subset constraints
function getMaxInhSetMembers
(Maybe<[String]>, [TyVar]) ::= seen::[TyVar] t::Type e::Decorated Env
{
  local c::Context = inhSubsetContext(t, varType(freshTyVar(inhSetKind())));
  c.env = e;
  
  local recurse::[(Maybe<[String]>, [TyVar])] =
    map(
      \ d::DclInfo -> getMaxInhSetMembers(t.freeVariables ++ seen, d.typerep2, e),
      c.resolved);
  
  return
    case t of
    | skolemType(tv) when contains(tv, seen) -> (nothing(), [])
    | varType(_) -> (just([]), [])  -- If an InhSet is unspecialized after type checking, assume it is empty
    | inhSetType(inhs) -> (just(inhs), [])
    | _ -> (map(sort, foldr(
        \ inhs1::Maybe<[String]> inhs2::Maybe<[String]> -> alt(lift2(intersect, inhs1, inhs2), alt(inhs1, inhs2)),
        empty, map(fst, recurse))),
      unions(t.freeVariables :: map(snd, recurse)))
    end;
}

function getMaxRefSet
Maybe<[String]> ::= t::Type e::Decorated Env
{
  return
    case t of
    | decoratedType(_, i) -> getMaxInhSetMembers([], i, e).fst
    | _ -> just([])
    end;
}
 