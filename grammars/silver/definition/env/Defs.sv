grammar silver:definition:env;

import silver:definition:regex; -- soley for Terminals. TODO : perhaps this shouldn't be here!

nonterminal Defs with typeList, valueList, attrList, prodOccursList, occursList, prodDclList;

-- The standard namespaces
synthesized attribute typeList :: [EnvItem];
synthesized attribute valueList :: [EnvItem];
synthesized attribute attrList :: [EnvItem];

-- Attribute occurs and production attributes.
synthesized attribute prodOccursList :: [DclInfo];
synthesized attribute occursList :: [DclInfo];

-- Extra space for production list
synthesized attribute prodDclList :: [DclInfo];


abstract production nilDefs 
top::Defs ::= 
{
  top.typeList = [];
  top.valueList = [];
  top.attrList = [];
  
  top.prodOccursList = [];
  top.occursList = [];
  
  top.prodDclList = [];
}

abstract production consDefs 
top::Defs ::= e1::Def e2::Defs
{
  top.typeList = e1.typeList ++ e2.typeList;
  top.valueList = e1.valueList ++ e2.valueList;
  top.attrList = e1.attrList ++ e2.attrList;
  
  top.prodOccursList = e1.prodOccursList ++ e2.prodOccursList;
  top.occursList = e1.occursList ++ e2.occursList;
  
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

closed nonterminal Def with typeList, valueList, attrList, prodOccursList, occursList, prodDclList, dcl, filterFn, filterDef, mapFn, mapDef;

aspect default production
top::Def ::=
{
  top.typeList = [];
  top.valueList = [];
  top.attrList = [];
  
  top.prodOccursList = [];
  top.occursList = [];
  
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
abstract production oDef
top::Def ::= d::DclInfo
{
  top.dcl = d;
  top.occursList = [d];
}


function childDef
Def ::= sg::String  sl::Location  fn::String  ty::Type
{
  return valueDef(defaultEnvItem(childDcl(sg,sl,fn,ty)));
}
function lhsDef
Def ::= sg::String  sl::Location  fn::String  ty::Type
{
  return valueDef(defaultEnvItem(lhsDcl(sg,sl,fn,ty)));
}
function localDef
Def ::= sg::String  sl::Location  fn::String  ty::Type
{
  return valueDef(defaultEnvItem(localDcl(sg,sl,fn,ty)));
}
function prodDef
Def ::= sg::String  sl::Location  ns::NamedSignature
{
  return prodDclDef(defaultEnvItem(prodDcl(sg,sl,ns)));
}
function funDef
Def ::= sg::String  sl::Location  ns::NamedSignature
{
  return valueDef(defaultEnvItem(funDcl(sg,sl,ns)));
}
function globalDef
Def ::= sg::String  sl::Location  fn::String  ty::Type
{
  return valueDef(defaultEnvItem(globalValueDcl(sg,sl,fn,ty)));
}
function ntDef
Def ::= sg::String  sl::Location  fn::String  bound::[TyVar]  ty::Type
{
  return typeDef(defaultEnvItem(ntDcl(sg,sl,fn,bound,ty,false)));
}
function closedNtDef
Def ::= sg::String  sl::Location  fn::String  bound::[TyVar]  ty::Type
{
  return typeDef(defaultEnvItem(ntDcl(sg,sl,fn,bound,ty,true)));
}
function termDef
Def ::= sg::String  sl::Location  fn::String  regex::Regex
{
  -- Terminals are also in the value namespace as terminal identifiers
  return typeValueDef(defaultEnvItem(termDcl(sg,sl,fn,regex)));
}
function lexTyVarDef
Def ::= sg::String  sl::Location  fn::String  ty::Type
{
  return typeDef(defaultEnvItem(lexTyVarDcl(sg,sl,fn,ty)));
}
function synDef
Def ::= sg::String  sl::Location  fn::String  bound::[TyVar]  ty::Type
{
  return attrDef(defaultEnvItem(synDcl(sg,sl,fn,bound,ty)));
}
function inhDef
Def ::= sg::String  sl::Location  fn::String  bound::[TyVar]  ty::Type
{
  return attrDef(defaultEnvItem(inhDcl(sg,sl,fn,bound,ty)));
}
function prodOccursDef
Def ::= sg::String  sl::Location  ns::NamedSignature  dcls::[Def]
{ 
  return paDef(paDcl(sg,sl,ns,dcls));
}
function forwardDef
Def ::= sg::String  sl::Location  ty::Type
{
  return valueDef(defaultEnvItem(forwardDcl(sg,sl,ty)));
}
-- These aliased functions are used for aspects.
function aliasedLhsDef
Def ::= sg::String  sl::Location  fn::String  ty::Type  alias::String
{
  return valueDef(onlyRenamedEnvItem(alias, lhsDcl(sg,sl,fn,ty)));
}
function aliasedChildDef
Def ::= sg::String  sl::Location  fn::String  ty::Type  alias::String
{
  return valueDef(onlyRenamedEnvItem(alias, childDcl(sg,sl,fn,ty)));
}
function annoDef
Def ::= sg::String  sl::Location  fn::String  bound::[TyVar]  ty::Type
{
  return attrDef(defaultEnvItem(annoDcl(sg,sl,fn,bound,ty)));
}
function annoInstanceDef
Def ::= sg::String  sl::Location  fnnt::String  fnat::String  ntty::Type  atty::Type
{
  return oDef(annoInstanceDcl(sg,sl,fnnt,fnat,ntty,atty));
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

