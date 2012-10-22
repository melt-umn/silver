grammar silver:definition:env;

import silver:definition:regex; -- soley for Terminals. TODO : perhaps this shouldn't be here!
import silver:definition:type;

nonterminal Defs with typeList, valueList, attrList, prodOccursList, occursList, prodDclList;
closed nonterminal Def with typeList, valueList, attrList, prodOccursList, occursList, prodDclList, dcl;

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

aspect default production
top::Def ::=
{
  top.typeList = [];
  top.valueList = [];
  top.attrList = [];
  
  top.prodOccursList = [];
  top.occursList = [];
  
  top.prodDclList = [];
}
abstract production typeDef
top::Def ::= d::EnvItem
{
  top.dcl = d.dcl;
  top.typeList = [d];
}
abstract production valueDef
top::Def ::= d::EnvItem
{
  top.dcl = d.dcl;
  top.valueList = [d];
}
abstract production attrDef
top::Def ::= d::EnvItem
{
  top.dcl = d.dcl;
  top.attrList = [d];
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
abstract production prodDclDef
top::Def ::= d::EnvItem
{
  top.dcl = d.dcl;
  top.valueList = [d];
  top.prodDclList = [d.dcl];
}



function childDef
Def ::= sg::String  sl::Location  fn::String  ty::TypeExp
{
  return valueDef(defaultEnvItem(childDcl(sg,sl,fn,ty)));
}
function lhsDef
Def ::= sg::String  sl::Location  fn::String  ty::TypeExp
{
  return valueDef(defaultEnvItem(lhsDcl(sg,sl,fn,ty)));
}
function localDef
Def ::= sg::String  sl::Location  fn::String  ty::TypeExp
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
Def ::= sg::String  sl::Location  fn::String  ty::TypeExp
{
  return valueDef(defaultEnvItem(globalValueDcl(sg,sl,fn,ty)));
}
function ntDef
Def ::= sg::String  sl::Location  fn::String  bound::[TyVar]  ty::TypeExp
{
  return typeDef(defaultEnvItem(ntDcl(sg,sl,fn,bound,ty,false)));
}
function closedNtDef
Def ::= sg::String  sl::Location  fn::String  bound::[TyVar]  ty::TypeExp
{
  return typeDef(defaultEnvItem(ntDcl(sg,sl,fn,bound,ty,true)));
}
function termDef
Def ::= sg::String  sl::Location  fn::String  regex::Regex_R
{
  return typeDef(defaultEnvItem(termDcl(sg,sl,fn,regex)));
}
function lexTyVarDef
Def ::= sg::String  sl::Location  fn::String  ty::TypeExp
{
  return typeDef(defaultEnvItem(lexTyVarDcl(sg,sl,fn,ty)));
}
function synDef
Def ::= sg::String  sl::Location  fn::String  bound::[TyVar]  ty::TypeExp
{
  return attrDef(defaultEnvItem(synDcl(sg,sl,fn,bound,ty)));
}
function inhDef
Def ::= sg::String  sl::Location  fn::String  bound::[TyVar]  ty::TypeExp
{
  return attrDef(defaultEnvItem(inhDcl(sg,sl,fn,bound,ty)));
}
function prodOccursDef
Def ::= sg::String  sl::Location  fn::String  outty::TypeExp  intys::[TypeExp]  dcls::[Def]
{ 
  return paDef(paDcl(sg,sl,fn,outty,intys,dcls));
}
function forwardDef
Def ::= sg::String  sl::Location  ty::TypeExp
{
  return valueDef(defaultEnvItem(forwardDcl(sg,sl,ty)));
}
function occursDef
Def ::= sg::String  sl::Location  fnnt::String  fnat::String  ntty::TypeExp  atty::TypeExp
{
  return oDef(occursDcl(sg,sl,fnnt,fnat,ntty,atty));
}
-- These aliased functions are used for aspects.
function aliasedLhsDef
Def ::= sg::String  sl::Location  fn::String  ty::TypeExp  alias::String
{
  return valueDef(onlyRenamedEnvItem(alias, lhsDcl(sg,sl,fn,ty)));
}
function aliasedChildDef
Def ::= sg::String  sl::Location  fn::String  ty::TypeExp  alias::String
{
  return valueDef(onlyRenamedEnvItem(alias, childDcl(sg,sl,fn,ty)));
}

-- I'm leaving "Defsironment" here just for the lols
----------------------------------------------------------------------------------------------------
--Defsironment creation functions--------------------------------------------------------------------
----------------------------------------------------------------------------------------------------

function unparseDefs
String ::= d::[Def] bv::[TyVar]
{
  return implode(",\n ", mapUnparseDcls(map((.dcl), d), bv));
}

function mapUnparseDcls
[String] ::= d::[DclInfo] bv::[TyVar]
{
  local h :: DclInfo = head(d);
  h.boundVariables = bv;

  return if null(d) then [] else h.unparse :: mapUnparseDcls(tail(d), bv);
}

--

{--
 - Used only to substitute defs from paDcls...
 - And so we screw up a few things:
 - 1. We expect ONLY valueDefs.
 - 2. We expect ONLY 'defaultEnvItems'
 -}
function performSubstitutionDef
Def ::= d::Def  s::Substitution
{
  return valueDef(defaultEnvItem(performSubstitutionDclInfo(d.dcl, s)));
}

-- TODO: these are non-extensible. Fixme.
function filterDefOnEnvItem
Boolean ::= fn::(Boolean ::= EnvItem)  d::Def
{
  return case d of
  | valueDef(ei) -> fn(ei)
  | typeDef(ei) -> fn(ei)
  | attrDef(ei) -> fn(ei)
  | _ -> true -- preserve all others for now (legit don't consider occurs, pa)
  end;
}
function mapDefOnEnvItem
Def ::= fn::(EnvItem ::= EnvItem)  d::Def
{
  return case d of
  | valueDef(ei) -> valueDef(fn(ei))
  | typeDef(ei) -> typeDef(fn(ei))
  | attrDef(ei) -> attrDef(fn(ei))
  | _ -> d -- ditto
  end;
}


{- TODO
abstract production filterDefsInclude
top::Defs ::= d::Defs incl::[String]
{
  top.typeList = filterEnvItemsInclude(forward.typeList, incl);
  top.attrList = filterEnvItemsInclude(forward.attrList, incl);
  top.valueList = filterEnvItemsInclude(forward.valueList, incl);
  
  forwards to d;
}
abstract production filterDefsExclude
top::Defs ::= d::Defs excl::[String]
{
  top.typeList = filterEnvItemsExclude(forward.typeList, excl);
  top.attrList = filterEnvItemsExclude(forward.attrList, excl);
  top.valueList = filterEnvItemsExclude(forward.valueList, excl);
  
  forwards to d;
}
abstract production mapPrependDefs
top::Defs ::= d::Defs pfx::String
{
  top.typeList = mapPrependEnvItems(forward.typeList, pfx);
  top.attrList = mapPrependEnvItems(forward.attrList, pfx);
  top.valueList = mapPrependEnvItems(forward.valueList, pfx);
  
  forwards to d;
}
abstract production mapRenameDefs
top::Defs ::= d::Defs rns::[Pair<String String>]
{
  top.typeList = mapRenameEnvItems(forward.typeList, rns);
  top.attrList = mapRenameEnvItems(forward.attrList, rns);
  top.valueList = mapRenameEnvItems(forward.valueList, rns);
  
  forwards to d;
}
-}

----------------------------------------------------------------------------------------------------
--Defs Helper Functions------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------


