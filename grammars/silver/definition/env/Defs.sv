grammar silver:definition:env;

import silver:definition:regex; -- soley for Terms. TODO : fix?
import silver:definition:type;

--TODO: unparse
nonterminal Defs with typeList, valueList, attrList, prodOccursList, occursList;

synthesized attribute typeList :: [EnvItem];
synthesized attribute valueList :: [EnvItem];
synthesized attribute attrList :: [EnvItem];
synthesized attribute prodOccursList :: [Decorated DclInfo];
synthesized attribute occursList :: [Decorated DclInfo];

-- I'm leaving "Defsironment" here just for the lols
----------------------------------------------------------------------------------------------------
--Defsironment creation functions--------------------------------------------------------------------
----------------------------------------------------------------------------------------------------

function unparseDefs
String ::= d::Defs bv::[TyVar]
{
  production attribute dclinfos::[Decorated DclInfo] with ++;
  dclinfos := mapGetDcls(d.typeList) ++
              mapGetDcls(d.valueList) ++
              mapGetDcls(d.attrList) ++
              d.prodOccursList ++
              d.occursList;
  
  return implode(",\n ", mapUnparseDcls(dclinfos, bv));
}

function mapUnparseDcls
[String] ::= d::[Decorated DclInfo] bv::[TyVar]
{
  local attribute h :: DclInfo;
  h = new(head(d));
  h.boundVariables = bv;

  return if null(d) then [] else h.unparse :: mapUnparseDcls(tail(d), bv);
}

function isEmptyOfValues
Boolean ::= d::Defs
{
  return null(d.valueList);
}

--------------------------------------------------------------------------------

abstract production emptyDefs 
top::Defs ::= 
{
  top.typeList = [];
  top.valueList = [];
  top.attrList = [];
  top.prodOccursList = [];
  top.occursList = [];
}

abstract production appendDefs 
top::Defs ::= e1::Defs e2::Defs
{
  top.typeList = e1.typeList ++ e2.typeList;
  top.valueList = e1.valueList ++ e2.valueList;
  top.attrList = e1.attrList ++ e2.attrList;
  top.prodOccursList = e1.prodOccursList ++ e2.prodOccursList;
  top.occursList = e1.occursList ++ e2.occursList;
}

abstract production substitutedDefs
top::Defs ::= e1::Defs s::Substitution
{
  -- Only exists for production attributes at this time, so I was lazy!
  top.valueList = performSubstitutionEnvItem(e1.valueList, s);
  
  forwards to emptyDefs();
}

--------------------------------------------------------------------------------

abstract production consTypeDef
top::Defs ::= d::EnvItem e2::Defs
{
  top.typeList = d :: forward.typeList;
  forwards to e2;
}
abstract production consValueDef
top::Defs ::= d::EnvItem e2::Defs
{
  top.valueList = d :: forward.valueList;
  forwards to e2;
}
abstract production consAttrDef
top::Defs ::= d::EnvItem e2::Defs
{
  top.attrList = d :: forward.attrList;
  forwards to e2;
}
abstract production consProdOccursDef
top::Defs ::= d::Decorated DclInfo e2::Defs
{
  top.prodOccursList = d :: forward.prodOccursList;
  forwards to e2;
}
abstract production consOccursDef
top::Defs ::= d::Decorated DclInfo e2::Defs
{
  top.occursList = d :: forward.occursList;
  forwards to e2;
}


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

----------------------------------------------------------------------------------------------------
--Defs Helper Functions------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------

function addChildDcl
Defs ::= sg::String sl::Location fn::String ty::TypeExp defs::Defs
{
  return consValueDef(defaultEnvItem(decorate childDcl(sg,sl,fn,ty) with {}), defs);
}
function addLhsDcl
Defs ::= sg::String sl::Location fn::String ty::TypeExp defs::Defs
{
  return consValueDef(defaultEnvItem(decorate lhsDcl(sg,sl,fn,ty) with {}), defs);
}
function addLocalDcl
Defs ::= sg::String sl::Location fn::String ty::TypeExp defs::Defs
{
  return consValueDef(defaultEnvItem(decorate localDcl(sg,sl,fn,ty) with {}), defs);
}
function addProdDcl
Defs ::= sg::String sl::Location ns::Decorated NamedSignature defs::Defs
{
  return consValueDef(defaultEnvItem(decorate prodDcl(sg,sl,ns) with {}), defs);
}
function addFunDcl
Defs ::= sg::String sl::Location ns::Decorated NamedSignature defs::Defs
{
  return consValueDef(defaultEnvItem(decorate funDcl(sg,sl,ns) with {}), defs);
}
function addGlobalValueDcl
Defs ::= sg::String sl::Location fn::String ty::TypeExp defs::Defs
{
  return consValueDef(defaultEnvItem(decorate globalValueDcl(sg,sl,fn,ty) with {}), defs);
}
function addNtDcl
Defs ::= sg::String sl::Location fn::String bound::[TyVar] ty::TypeExp defs::Defs
{
  return consTypeDef(defaultEnvItem(decorate ntDcl(sg,sl,fn,bound,ty) with {}), defs);
}
function addTermDcl
Defs ::= sg::String sl::Location fn::String regex::Regex_R defs::Defs
{
  return consTypeDef(defaultEnvItem(decorate termDcl(sg,sl,fn,regex) with {}), defs);
}
function addLexTyVarDcl
Defs ::= sg::String sl::Location fn::String ty::TypeExp defs::Defs
{
  return consTypeDef(defaultEnvItem(decorate lexTyVarDcl(sg,sl,fn,ty) with {}), defs);
}
function addSynDcl
Defs ::= sg::String sl::Location fn::String bound::[TyVar] ty::TypeExp defs::Defs
{
  return consAttrDef(defaultEnvItem(decorate synDcl(sg,sl,fn,bound,ty) with {}), defs);
}
function addInhDcl
Defs ::= sg::String sl::Location fn::String bound::[TyVar] ty::TypeExp defs::Defs
{
  return consAttrDef(defaultEnvItem(decorate inhDcl(sg,sl,fn,bound,ty) with {}), defs);
}
function addPaDcl
Defs ::= sg::String sl::Location fn::String outty::TypeExp intys::[TypeExp] dcls::Defs defs::Defs
{
  return consProdOccursDef(decorate paDcl(sg,sl,fn,outty,intys,dcls) with {}, defs);
}
function addForwardDcl
Defs ::= sg::String sl::Location ty::TypeExp defs::Defs
{
  return consValueDef(defaultEnvItem(decorate forwardDcl(sg,sl,ty) with {}), defs);
}
function addOccursDcl
Defs ::= sg::String sl::Location fnnt::String fnat::String ntty::TypeExp atty::TypeExp defs::Defs
{
  return consOccursDef(decorate occursDcl(sg,sl,fnnt,fnat,ntty,atty) with {}, defs);
}
-- These aliased functions are used for aspects.
function addAliasedLhsDcl
Defs ::= sg::String sl::Location fn::String ty::TypeExp alias::String defs::Defs
{
  return consValueDef(renamedEnvItem(alias, decorate lhsDcl(sg,sl,fn,ty) with {}), defs);
}
function addAliasedChildDcl
Defs ::= sg::String sl::Location fn::String ty::TypeExp alias::String defs::Defs
{
  return consValueDef(renamedEnvItem(alias, decorate childDcl(sg,sl,fn,ty) with {}), defs);
}

