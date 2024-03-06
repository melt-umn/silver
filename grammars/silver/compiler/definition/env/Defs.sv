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

propagate filterItems, withRenames, renamed, pfx, prepended on Defs;

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

closed nonterminal Def with
  typeList, valueList, attrList, instList, prodOccursList, prodDclList,
  filterItems, filterIncludeOnly, filterIncludeHiding, withRenames, renamed, pfx, prepended,
  compareTo, isEqual;

propagate filterItems, filterIncludeOnly, filterIncludeHiding, withRenames, renamed, pfx, prepended, compareTo, isEqual on Def;

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

fun childDef Def ::= sg::String  sl::Location  fn::String  ty::Type =
  valueDef(defaultEnvItem(childDcl(fn,ty,sourceGrammar=sg,sourceLocation=sl)));
fun lhsDef Def ::= sg::String  sl::Location  fn::String  ty::Type =
  valueDef(defaultEnvItem(lhsDcl(fn,ty,sourceGrammar=sg,sourceLocation=sl)));
fun localDef Def ::= sg::String  sl::Location  fn::String  ty::Type  isForward::Boolean =
  valueDef(defaultEnvItem(localDcl(fn,ty,isForward,sourceGrammar=sg,sourceLocation=sl)));
fun prodDef Def ::= sg::String  sl::Location  ns::NamedSignature  dispatch::Maybe<String>  hasForward::Boolean =
  prodDclDef(defaultEnvItem(prodDcl(ns,dispatch,hasForward,sourceGrammar=sg,sourceLocation=sl)));
fun funDef Def ::= sg::String  sl::Location  ns::NamedSignature =
  valueDef(defaultEnvItem(funDcl(ns,sourceGrammar=sg,sourceLocation=sl)));
fun globalDef
Def ::= sg::String  sl::Location  fn::String bound::[TyVar] contexts::[Context] ty::Type =
  valueDef(defaultEnvItem(globalValueDcl(fn, bound, contexts, ty,sourceGrammar=sg,sourceLocation=sl)));
fun classMemberDef
Def ::= sg::String  sl::Location  fn::String  bound::[TyVar] head::Context contexts::[Context] ty::Type =
  valueDef(defaultEnvItem(classMemberDcl(fn,bound,head,contexts,ty,sourceGrammar=sg,sourceLocation=sl)));
fun ntDef
Def ::= sg::String  sl::Location  fn::String  ks::[Kind]  data::Boolean  closed::Boolean  tracked::Boolean =
  typeDef(defaultEnvItem(ntDcl(fn,ks,data,closed,tracked,sourceGrammar=sg,sourceLocation=sl)));
fun termDef
Def ::= sg::String  sl::Location  fn::String  regex::r:Regex  easyName::Maybe<String>  genRepeatProb::Maybe<Float> =
  typeValueDef(
    defaultEnvItem(termDcl(fn,regex,easyName,genRepeatProb,sourceGrammar=sg,sourceLocation=sl)),
    defaultEnvItem(termIdDcl(fn,sourceGrammar=sg,sourceLocation=sl)));
fun lexTyVarDef Def ::= sg::String  sl::Location  fn::String  tv::TyVar =
  typeDef(defaultEnvItem(lexTyVarDcl(fn,false,tv,sourceGrammar=sg,sourceLocation=sl)));
fun aspectLexTyVarDef Def ::= sg::String  sl::Location  fn::String  tv::TyVar =
  typeDef(defaultEnvItem(lexTyVarDcl(fn,true,tv,sourceGrammar=sg,sourceLocation=sl)));
fun typeAliasDef
Def ::= sg::String sl::Location fn::String mentionedAliases::[String] bound::[TyVar] ty::Type =
  typeDef(defaultEnvItem(typeAliasDcl(fn,mentionedAliases,bound,ty,sourceGrammar=sg,sourceLocation=sl)));
fun synDef Def ::= sg::String  sl::Location  fn::String  bound::[TyVar]  ty::Type =
  attrDef(defaultEnvItem(synDcl(fn,bound,ty,sourceGrammar=sg,sourceLocation=sl)));
fun inhDef Def ::= sg::String  sl::Location  fn::String  bound::[TyVar]  ty::Type =
  attrDef(defaultEnvItem(inhDcl(fn,bound,ty,sourceGrammar=sg,sourceLocation=sl)));
fun transDef Def ::= sg::String  sl::Location  fn::String  bound::[TyVar]  ty::Type =
  attrDef(defaultEnvItem(transDcl(fn,bound,ty,sourceGrammar=sg,sourceLocation=sl)));
fun prodOccursDef Def ::= sg::String  sl::Location  ns::NamedSignature  dcls::[Def] =
  paDef(paDcl(ns,dcls,sourceGrammar=sg,sourceLocation=sl));
fun forwardDef Def ::= sg::String  sl::Location  ty::Type =
  valueDef(defaultEnvItem(forwardDcl(ty,sourceGrammar=sg,sourceLocation=sl)));
-- These aliased functions are used for aspects.
fun aliasedLhsDef Def ::= sg::String  sl::Location  fn::String  ty::Type  alias::String =
  valueDef(onlyRenamedEnvItem(alias, lhsDcl(fn,ty,sourceGrammar=sg,sourceLocation=sl)));
fun aliasedChildDef Def ::= sg::String  sl::Location  fn::String  ty::Type  alias::String =
  valueDef(onlyRenamedEnvItem(alias, childDcl(fn,ty,sourceGrammar=sg,sourceLocation=sl)));
fun annoDef Def ::= sg::String  sl::Location  fn::String  bound::[TyVar]  ty::Type =
  attrDef(defaultEnvItem(annoDcl(fn,bound,ty,sourceGrammar=sg,sourceLocation=sl)));
fun classDef
Def ::= sg::String  sl::Location  fn::String  supers::[Context]  tv::TyVar  k::Kind  members::[Pair<String Boolean>] =
  typeDef(defaultEnvItem(clsDcl(fn,supers,tv,k,members,sourceGrammar=sg,sourceLocation=sl)));
fun instDef
Def ::= sg::String  sl::Location  fn::String  bound::[TyVar]  contexts::[Context]  ty::Type  definedMembers::[String] =
  tcInstDef(instDcl(fn,bound,contexts,ty,definedMembers,sourceGrammar=sg,sourceLocation=sl));
fun sigConstraintDef Def ::= sg::String  sl::Location  fn::String  ty::Type  ns::NamedSignature =
  tcInstDef(sigConstraintDcl(fn,ty,ns,sourceGrammar=sg,sourceLocation=sl));
fun currentInstDef Def ::= sg::String  sl::Location  fn::String  ty::Type =
  tcInstDef(currentInstDcl(fn,ty,sourceGrammar=sg,sourceLocation=sl));
fun instSuperDef Def ::= sg::String  sl::Location  fn::String  baseDcl::InstDclInfo =
  tcInstDef(instSuperDcl(fn,baseDcl,sourceGrammar=sg,sourceLocation=sl));
fun typeableSuperDef Def ::= sg::String  sl::Location  baseDcl::InstDclInfo =
  tcInstDef(typeableSuperDcl(baseDcl,sourceGrammar=sg,sourceLocation=sl));


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
