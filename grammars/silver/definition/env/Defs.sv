grammar silver:definition:env;

import silver:definition:regex; -- soley for Terms. TODO : fix?
import silver:definition:type;

nonterminal Defs with typeList, valueList, attrList, prodOccursList, occursList, constructorList;

-- The standard namespaces
synthesized attribute typeList :: [EnvItem];
synthesized attribute valueList :: [EnvItem];
synthesized attribute attrList :: [EnvItem];

-- Attribute occurs and production attributes.
synthesized attribute prodOccursList :: [DclInfo];
synthesized attribute occursList :: [DclInfo];

-- Special namespace for looking up productions by nonterminal they construct
synthesized attribute constructorList :: [Pair<String DclInfo>];

-- I'm leaving "Defsironment" here just for the lols
----------------------------------------------------------------------------------------------------
--Defsironment creation functions--------------------------------------------------------------------
----------------------------------------------------------------------------------------------------

function unparseDefs
String ::= d::Defs bv::[TyVar]
{
  production attribute dclinfos::[DclInfo] with ++;
  dclinfos := mapGetDcls(d.typeList) ++
              mapGetDcls(d.valueList) ++
              mapGetDcls(d.attrList) ++
              d.prodOccursList ++
              d.occursList;
  
  return implode(",\n ", mapUnparseDcls(dclinfos, bv));
}

function mapUnparseDcls
[String] ::= d::[DclInfo] bv::[TyVar]
{
  local attribute h :: DclInfo;
  h = head(d);
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
  
  top.constructorList = [];
}

abstract production appendDefs 
top::Defs ::= e1::Defs e2::Defs
{
  top.typeList = e1.typeList ++ e2.typeList;
  top.valueList = e1.valueList ++ e2.valueList;
  top.attrList = e1.attrList ++ e2.attrList;
  
  top.prodOccursList = e1.prodOccursList ++ e2.prodOccursList;
  top.occursList = e1.occursList ++ e2.occursList;
  
  top.constructorList = e1.constructorList ++ e2.constructorList;
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
top::Defs ::= d::DclInfo e2::Defs
{
  top.prodOccursList = d :: forward.prodOccursList;
  forwards to e2;
}
abstract production consOccursDef
top::Defs ::= d::DclInfo e2::Defs
{
  top.occursList = d :: forward.occursList;
  forwards to e2;
}

abstract production consConstructorDef
top::Defs ::= d::EnvItem e2::Defs
{
  -- we're going to do BOTH value and constructor here
  top.valueList = d :: forward.valueList;
  top.constructorList = pair(d.dcl.typerep.outputType.typeName, d.dcl) :: forward.constructorList;
  
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
  return consValueDef(defaultEnvItem(childDcl(sg,sl,fn,ty)), defs);
}
function addLhsDcl
Defs ::= sg::String sl::Location fn::String ty::TypeExp defs::Defs
{
  return consValueDef(defaultEnvItem(lhsDcl(sg,sl,fn,ty)), defs);
}
function addLocalDcl
Defs ::= sg::String sl::Location fn::String ty::TypeExp defs::Defs
{
  return consValueDef(defaultEnvItem(localDcl(sg,sl,fn,ty)), defs);
}
function addProdDcl
Defs ::= sg::String sl::Location ns::NamedSignature defs::Defs
{
  -- special cons here that puts it in value and constructor namespaces
  return consConstructorDef(defaultEnvItem(prodDcl(sg,sl,ns)), defs);
}
function addFunDcl
Defs ::= sg::String sl::Location ns::NamedSignature defs::Defs
{
  return consValueDef(defaultEnvItem(funDcl(sg,sl,ns)), defs);
}
function addGlobalValueDcl
Defs ::= sg::String sl::Location fn::String ty::TypeExp defs::Defs
{
  return consValueDef(defaultEnvItem(globalValueDcl(sg,sl,fn,ty)), defs);
}
function addNtDcl
Defs ::= sg::String sl::Location fn::String bound::[TyVar] ty::TypeExp defs::Defs
{
  return consTypeDef(defaultEnvItem(ntDcl(sg,sl,fn,bound,ty, false)), defs);
}
function addClosedNtDcl
Defs ::= sg::String sl::Location fn::String bound::[TyVar] ty::TypeExp defs::Defs
{
  return consTypeDef(defaultEnvItem(ntDcl(sg,sl,fn,bound,ty, true)), defs);
}
function addTermDcl
Defs ::= sg::String sl::Location fn::String regex::Regex_R defs::Defs
{
  return consTypeDef(defaultEnvItem(termDcl(sg,sl,fn,regex)), defs);
}
function addLexTyVarDcl
Defs ::= sg::String sl::Location fn::String ty::TypeExp defs::Defs
{
  return consTypeDef(defaultEnvItem(lexTyVarDcl(sg,sl,fn,ty)), defs);
}
function addSynDcl
Defs ::= sg::String sl::Location fn::String bound::[TyVar] ty::TypeExp defs::Defs
{
  return consAttrDef(defaultEnvItem(synDcl(sg,sl,fn,bound,ty)), defs);
}
function addInhDcl
Defs ::= sg::String sl::Location fn::String bound::[TyVar] ty::TypeExp defs::Defs
{
  return consAttrDef(defaultEnvItem(inhDcl(sg,sl,fn,bound,ty)), defs);
}
function addPaDcl
Defs ::= sg::String sl::Location fn::String outty::TypeExp intys::[TypeExp] dcls::Defs defs::Defs
{
  return consProdOccursDef(paDcl(sg,sl,fn,outty,intys,dcls), defs);
}
function addForwardDcl
Defs ::= sg::String sl::Location ty::TypeExp defs::Defs
{
  return consValueDef(defaultEnvItem(forwardDcl(sg,sl,ty)), defs);
}
function addOccursDcl
Defs ::= sg::String sl::Location fnnt::String fnat::String ntty::TypeExp atty::TypeExp defs::Defs
{
  return consOccursDef(occursDcl(sg,sl,fnnt,fnat,ntty,atty), defs);
}
-- These aliased functions are used for aspects.
function addAliasedLhsDcl
Defs ::= sg::String sl::Location fn::String ty::TypeExp alias::String defs::Defs
{
  return consValueDef(onlyRenamedEnvItem(alias, lhsDcl(sg,sl,fn,ty)), defs);
}
function addAliasedChildDcl
Defs ::= sg::String sl::Location fn::String ty::TypeExp alias::String defs::Defs
{
  return consValueDef(onlyRenamedEnvItem(alias, childDcl(sg,sl,fn,ty)), defs);
}

