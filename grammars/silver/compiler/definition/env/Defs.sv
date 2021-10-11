grammar silver:compiler:definition:env;

nonterminal Defs with defs, typeList, valueList, attrList, instList, prodOccursList, prodDclList, filterItems, filterOnly, filterHiding, withRenames, renamed, pfx, prepended;

-- The standard namespaces
synthesized attribute typeList :: [EnvItem<TypeDclInfo>];
synthesized attribute valueList :: [EnvItem<ValueDclInfo>];
synthesized attribute attrList :: [EnvItem<AttributeDclInfo>];

-- Type class instances
synthesized attribute instList :: [InstDclInfo];

-- Production attributes.
synthesized attribute prodOccursList :: [ProductionAttrDclInfo];

-- Extra space for production list
synthesized attribute prodDclList :: [ValueDclInfo];

-- Transformations on lists of Def
-- This is to support computing the defs introduced by qualified imports
-- (import foo only bar, import foo as bar, import foo with bar as baz)
synthesized attribute filterOnly :: Defs;
synthesized attribute filterHiding :: Defs;

propagate withRenames, renamed, pfx, prepended on Defs;

abstract production nilDefs 
top::Defs ::= 
{
  top.defs := [];

  top.typeList = [];
  top.valueList = [];
  top.attrList = [];
  top.instList = [];
  
  top.prodOccursList = [];
  
  top.prodDclList = [];
  
  top.filterOnly = top;
  top.filterHiding = top;
}

abstract production consDefs 
top::Defs ::= e1::Def e2::Defs
{
  top.defs := e1 :: e2.defs;

  top.typeList = e1.typeList ++ e2.typeList;
  top.valueList = e1.valueList ++ e2.valueList;
  top.attrList = e1.attrList ++ e2.attrList;
  top.instList = e1.instList ++ e2.instList;
  
  top.prodOccursList = e1.prodOccursList ++ e2.prodOccursList;
  
  top.prodDclList = e1.prodDclList ++ e2.prodDclList;

  top.filterOnly = if e1.filterIncludeOnly then consDefs(e1, e2.filterOnly) else e2.filterOnly;
  top.filterHiding = if e1.filterIncludeHiding then consDefs(e1, e2.filterHiding) else e2.filterHiding;
}

--------------------------------------------------------------------------------

closed nonterminal Def with typeList, valueList, attrList, instList, prodOccursList, prodDclList, filterItems, filterIncludeOnly, filterIncludeHiding, withRenames, renamed, pfx, prepended;

propagate filterItems, filterIncludeOnly, filterIncludeHiding, withRenames, renamed, pfx, prepended on Def;

aspect default production
top::Def ::=
{
  top.typeList = [];
  top.valueList = [];
  top.attrList = [];
  top.instList = [];
  
  top.prodOccursList = [];
  
  top.prodDclList = [];
}
abstract production typeDef
top::Def ::= d::EnvItem<TypeDclInfo>
{
  top.typeList = [d];
}
abstract production valueDef
top::Def ::= d::EnvItem<ValueDclInfo>
{
  top.valueList = [d];
}
abstract production typeValueDef
top::Def ::= td::EnvItem<TypeDclInfo> vd::EnvItem<ValueDclInfo> 
{
  top.typeList = [td];
  top.valueList = [vd];
}
abstract production attrDef
top::Def ::= d::EnvItem<AttributeDclInfo>
{
  top.attrList = [d];
}
abstract production prodDclDef
top::Def ::= d::EnvItem<ValueDclInfo>
{
  top.valueList = [d];
  -- unlike normal valueDef, also affect production lookups:
  top.prodDclList = [d.dcl];
}
abstract production paDef
top::Def ::= d::ProductionAttrDclInfo
{
  top.prodOccursList = [d];
}
abstract production tcInstDef
top::Def ::= d::InstDclInfo
{
  top.instList = [d];
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
Def ::= sg::String  sl::Location  fn::String bound::[TyVar] contexts::[Context] ty::Type
{
  return valueDef(defaultEnvItem(globalValueDcl(fn, bound, contexts, ty,sourceGrammar=sg,sourceLocation=sl)));
}
function classMemberDef
Def ::= sg::String  sl::Location  fn::String  bound::[TyVar] head::Context contexts::[Context] ty::Type
{
  return valueDef(defaultEnvItem(classMemberDcl(fn,bound,head,contexts,ty,sourceGrammar=sg,sourceLocation=sl)));
}
function ntDef
Def ::= sg::String  sl::Location  fn::String  ks::[Kind]  closed::Boolean  tracked::Boolean
{
  return typeDef(defaultEnvItem(ntDcl(fn,ks,closed,tracked,sourceGrammar=sg,sourceLocation=sl)));
}
function termDef
Def ::= sg::String  sl::Location  fn::String  regex::Regex  easyName::Maybe<String>
{
  -- Terminals are also in the value namespace as terminal identifiers
  return typeValueDef(
    defaultEnvItem(termDcl(fn,regex,easyName,sourceGrammar=sg,sourceLocation=sl)),
    defaultEnvItem(termIdDcl(fn,sourceGrammar=sg,sourceLocation=sl)));
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
function typeAliasDef
Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  return typeDef(defaultEnvItem(typeAliasDcl(fn,bound,ty,sourceGrammar=sg,sourceLocation=sl)));
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
function classDef
Def ::= sg::String  sl::Location  fn::String  supers::[Context]  tv::TyVar  k::Kind  members::[Pair<String Boolean>]
{
  return typeDef(defaultEnvItem(clsDcl(fn,supers,tv,k,members,sourceGrammar=sg,sourceLocation=sl)));
}
function instDef
Def ::= sg::String  sl::Location  fn::String  bound::[TyVar]  contexts::[Context]  ty::Type
{
  return tcInstDef(instDcl(fn,bound,contexts,ty,sourceGrammar=sg,sourceLocation=sl));
}
function sigConstraintDef
Def ::= sg::String  sl::Location  fn::String  ty::Type  ns::NamedSignature
{
  return tcInstDef(sigConstraintDcl(fn,ty,ns,sourceGrammar=sg,sourceLocation=sl));
}
function currentInstDef
Def ::= sg::String  sl::Location  fn::String  ty::Type
{
  return tcInstDef(currentInstDcl(fn,ty,sourceGrammar=sg,sourceLocation=sl));
}
function instSuperDef
Def ::= sg::String  sl::Location  fn::String  baseDcl::InstDclInfo
{
  return tcInstDef(instSuperDcl(fn,baseDcl,sourceGrammar=sg,sourceLocation=sl));
}
function typeableSuperDef
Def ::= sg::String  sl::Location  baseDcl::InstDclInfo
{
  return tcInstDef(typeableSuperDcl(baseDcl,sourceGrammar=sg,sourceLocation=sl));
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
  return 
    case d of
    | valueDef(ei) -> valueDef(defaultEnvItem(performSubstitutionDclInfo(ei.dcl, s)))
    | _ -> error("Prod attr def not a valueDef")
    end;
}
