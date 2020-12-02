grammar silver:definition:env;

import silver:definition:regex; -- soley for Terminals. TODO : perhaps this shouldn't be here!

nonterminal Defs with typeList, valueList, attrList, prodOccursList, prodDclList;

-- The standard namespaces
synthesized attribute typeList :: [EnvItem];
synthesized attribute valueList :: [EnvItem];
synthesized attribute attrList :: [EnvItem];

-- Production attributes.
synthesized attribute prodOccursList :: [DclInfo];

-- Extra space for production list
synthesized attribute prodDclList :: [DclInfo];


abstract production nilDefs 
top::Defs ::= 
{
  top.typeList = [];
  top.valueList = [];
  top.attrList = [];
  
  top.prodOccursList = [];
  
  top.prodDclList = [];
}

abstract production consDefs 
top::Defs ::= e1::Def e2::Defs
{
  top.typeList = e1.typeList ++ e2.typeList;
  top.valueList = e1.valueList ++ e2.valueList;
  top.attrList = e1.attrList ++ e2.attrList;
  
  top.prodOccursList = e1.prodOccursList ++ e2.prodOccursList;
  
  top.prodDclList = e1.prodDclList ++ e2.prodDclList;
}

--------------------------------------------------------------------------------

-- Transformations on lists of Def
-- This is to support computing the defs introduced by qualified imports
-- (import foo only bar, import foo as bar, import foo with bar as baz)
inherited attribute filterFn::(Boolean ::= EnvItem);
synthesized attribute filterDef::Boolean;
inherited attribute mapFn::(EnvItem ::= EnvItem);
synthesized attribute mapDef::Def;

closed nonterminal Def with typeList, valueList, attrList, prodOccursList, prodDclList, dcl, filterFn, filterDef, mapFn, mapDef;

aspect default production
top::Def ::=
{
  top.typeList = [];
  top.valueList = [];
  top.attrList = [];
  
  top.prodOccursList = [];
  
  top.prodDclList = [];
  
  top.filterDef = true; -- We don't do any renaming for production attribute or occurs defs
  top.mapDef = top; -- ditto
}
abstract production typeDef
top::Def ::= d::EnvItem
{
  top.dcl = d.dcl;
  top.typeList = [d];
  top.filterDef = top.filterFn(d);
  top.mapDef = typeDef(top.mapFn(d));
}
abstract production valueDef
top::Def ::= d::EnvItem
{
  top.dcl = d.dcl;
  top.valueList = [d];
  top.filterDef = top.filterFn(d);
  top.mapDef = valueDef(top.mapFn(d));
}
abstract production typeValueDef
top::Def ::= d::EnvItem
{
  top.dcl = d.dcl;
  top.typeList = [d];
  top.valueList = [d];
  top.filterDef = top.filterFn(d);
  top.mapDef = typeValueDef(top.mapFn(d));
}
abstract production attrDef
top::Def ::= d::EnvItem
{
  top.dcl = d.dcl;
  top.attrList = [d];
  top.filterDef = top.filterFn(d);
  top.mapDef = attrDef(top.mapFn(d));
}
abstract production prodDclDef
top::Def ::= d::EnvItem
{
  top.dcl = d.dcl;
  top.valueList = [d];
  -- unlike normal valueDef, also affect production lookups:
  top.prodDclList = [d.dcl];
  top.filterDef = top.filterFn(d);
  top.mapDef = prodDclDef(top.mapFn(d));
}
abstract production paDef
top::Def ::= d::DclInfo
{
  top.dcl = d;
  top.prodOccursList = [d];
}


function childDef
Def ::= sg::String  sl::Location  fn::String  ty::Type
{
  return valueDef(defaultEnvItem(childDcl(fn,ty,sourceGrammar=sg,sourceLocation=sl)));
}
function lhsDef
Def ::= sg::String  sl::Location  fn::String  ty::Type
{
  return valueDef(defaultEnvItem(lhsDcl(fn,ty,sourceGrammar=sg,sourceLocation=sl)));
}
function localDef
Def ::= sg::String  sl::Location  fn::String  ty::Type
{
  return valueDef(defaultEnvItem(localDcl(fn,ty,sourceGrammar=sg,sourceLocation=sl)));
}
function prodDef
Def ::= sg::String  sl::Location  ns::NamedSignature  hasForward::Boolean
{
  return prodDclDef(defaultEnvItem(prodDcl(ns,hasForward,sourceGrammar=sg,sourceLocation=sl)));
}
function funDef
Def ::= sg::String  sl::Location  ns::NamedSignature
{
  return valueDef(defaultEnvItem(funDcl(ns,sourceGrammar=sg,sourceLocation=sl)));
}
function globalDef
Def ::= sg::String  sl::Location  fn::String  ty::Type
{
  return valueDef(defaultEnvItem(globalValueDcl(fn,ty,sourceGrammar=sg,sourceLocation=sl)));
}
function ntDef
Def ::= sg::String  sl::Location  fn::String  arity::Integer  closed::Boolean
{
  return typeDef(defaultEnvItem(ntDcl(fn,arity,ty,closed,sourceGrammar=sg,sourceLocation=sl)));
}
function termDef
Def ::= sg::String  sl::Location  fn::String  regex::Regex
{
  -- Terminals are also in the value namespace as terminal identifiers
  return typeValueDef(defaultEnvItem(termDcl(fn,regex,sourceGrammar=sg,sourceLocation=sl)));
}
function lexTyVarDef
Def ::= sg::String  sl::Location  fn::String  tv::TyVar
{
  return typeDef(defaultEnvItem(lexTyVarDcl(fn,false,tv,sourceGrammar=sg,sourceLocation=sl)));
}
function aspectLexTyVarDef
Def ::= sg::String  sl::Location  fn::String  tv::TyVar
{
  return typeDef(defaultEnvItem(lexTyVarDcl(fn,true,tv,sourceGrammar=sg,sourceLocation=sl)));
}
function synDef
Def ::= sg::String  sl::Location  fn::String  bound::[TyVar]  ty::Type
{
  return attrDef(defaultEnvItem(synDcl(fn,bound,ty,sourceGrammar=sg,sourceLocation=sl)));
}
function inhDef
Def ::= sg::String  sl::Location  fn::String  bound::[TyVar]  ty::Type
{
  return attrDef(defaultEnvItem(inhDcl(fn,bound,ty,sourceGrammar=sg,sourceLocation=sl)));
}
function prodOccursDef
Def ::= sg::String  sl::Location  ns::NamedSignature  dcls::[Def]
{ 
  return paDef(paDcl(ns,dcls,sourceGrammar=sg,sourceLocation=sl));
}
function forwardDef
Def ::= sg::String  sl::Location  ty::Type
{
  return valueDef(defaultEnvItem(forwardDcl(ty,sourceGrammar=sg,sourceLocation=sl)));
}
-- These aliased functions are used for aspects.
function aliasedLhsDef
Def ::= sg::String  sl::Location  fn::String  ty::Type  alias::String
{
  return valueDef(onlyRenamedEnvItem(alias, lhsDcl(fn,ty,sourceGrammar=sg,sourceLocation=sl)));
}
function aliasedChildDef
Def ::= sg::String  sl::Location  fn::String  ty::Type  alias::String
{
  return valueDef(onlyRenamedEnvItem(alias, childDcl(fn,ty,sourceGrammar=sg,sourceLocation=sl)));
}
function annoDef
Def ::= sg::String  sl::Location  fn::String  bound::[TyVar]  ty::Type
{
  return attrDef(defaultEnvItem(annoDcl(fn,bound,ty,sourceGrammar=sg,sourceLocation=sl)));
}



-- I'm leaving "Defsironment" here just for the lols
----------------------------------------------------------------------------------------------------
--Defsironment creation functions--------------------------------------------------------------------
----------------------------------------------------------------------------------------------------

{--
 - Used only on what we get from production attributes.
 - We encode those assumptions:
 - 1. We expect ONLY valueDefs.
 - 2. We expect ONLY 'defaultEnvItems'
 -}
function performSubstitutionDef
Def ::= d::Def  s::Substitution
{
  return valueDef(defaultEnvItem(performSubstitutionDclInfo(d.dcl, s)));
}

function filterDefOnEnvItem
Boolean ::= fn::(Boolean ::= EnvItem)  d::Def
{
  d.filterFn = fn;
  return d.filterDef;
}
function mapDefOnEnvItem
Def ::= fn::(EnvItem ::= EnvItem)  d::Def
{
  d.mapFn = fn;
  return d.mapDef;
}

