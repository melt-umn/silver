grammar silver:modification:collection;

import silver:definition:env;
import silver:definition:core;
import silver:definition:type;

attribute operation occurs on DclInfo;


synthesized attribute attrBaseDefDispatcher :: Production (ProductionStmt ::= DefLHS Dot_t Decorated QName Equal_t Expr) occurs on DclInfo;
synthesized attribute attrAppendDefDispatcher :: Production (ProductionStmt ::= DefLHS Dot_t Decorated QName Equal_t Expr) occurs on DclInfo;

synthesized attribute baseDefDispatcher :: Production(ProductionStmt ::= Decorated QName  Equal_t  Expr) occurs on DclInfo;
synthesized attribute appendDefDispatcher :: Production(ProductionStmt ::= Decorated QName  Equal_t  Expr) occurs on DclInfo;


aspect production defaultDcl
top::DclInfo ::=
{
  top.attrBaseDefDispatcher = errorCollectionDefDispatcher;
  top.attrAppendDefDispatcher = errorCollectionDefDispatcher;

  top.baseDefDispatcher = errorCollectionValueDef;
  top.appendDefDispatcher = errorCollectionValueDef;
}

abstract production synCollectionDcl
top::DclInfo ::= sg::String sl::Decorated Location fn::String bound::[TyVar] ty::TypeExp o::Operation
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;
  top.unparse = "syncol(" ++ sl.unparse ++ ", '" ++ fn ++ "', " ++ somehowUnparseTyVars(bound) ++ ", " ++ prettyTypeWith(ty, bound) ++ ", " ++ o.unparse ++ ")";
  
  top.typerep = ty;
  top.dclBoundVars = bound;

  top.operation = o;
  
  top.attrAccessDispatcher = synDNTAccessDispatcher;
  top.attrDefDispatcher = errorColNormalAttributeDef;

  top.attrBaseDefDispatcher = synBaseColAttributeDef;
  top.attrAppendDefDispatcher = synAppendColAttributeDef;

  forwards to defaultDcl();
}
abstract production inhCollectionDcl
top::DclInfo ::= sg::String sl::Decorated Location fn::String bound::[TyVar] ty::TypeExp o::Operation
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;
  top.unparse = "inhcol(" ++ sl.unparse ++ ", '" ++ fn ++ "', " ++ somehowUnparseTyVars(bound) ++ ", " ++ prettyTypeWith(ty, bound) ++ ", " ++ o.unparse ++ ")";
  
  top.typerep = ty;
  top.dclBoundVars = bound;

  top.operation = o;
  
  top.attrAccessDispatcher = inhDNTAccessDispatcher;
  top.attrDefDispatcher = errorColNormalAttributeDef;

  top.attrBaseDefDispatcher = inhBaseColAttributeDef;
  top.attrAppendDefDispatcher = inhAppendColAttributeDef;

  forwards to defaultDcl();
}

abstract production localCollectionDcl
top::DclInfo ::= sg::String sl::Decorated Location fn::String ty::TypeExp o::Operation
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.unparse = "loccol(" ++ sl.unparse ++ ", '" ++ fn ++ "', " ++ unparseType(ty) ++ ", " ++ o.unparse ++ ")";
  
  top.typerep = ty;
  
  top.operation = o;
  
  top.refDispatcher = localReference;
  top.defDispatcher = errorColNormalValueDef;
  top.defLHSDispatcher = localDefLHS;

  top.baseDefDispatcher = baseCollectionValueDef;
  top.appendDefDispatcher = appendCollectionValueDef;

  forwards to defaultDcl();
  
  top.substitutedDclInfo = localCollectionDcl(sg,sl,fn, performSubstitution(ty, top.givenSubstitution), o);
}


-- Defs
function addSynColDcl
Defs ::= sg::String sl::Decorated Location fn::String bound::[TyVar] ty::TypeExp o::Operation defs::Defs
{
  return consAttrDef(defaultEnvItem(decorate synCollectionDcl(sg,sl,fn,bound,ty,o) with {}), defs);
}
function addInhColDcl
Defs ::= sg::String sl::Decorated Location fn::String bound::[TyVar] ty::TypeExp o::Operation defs::Defs
{
  return consAttrDef(defaultEnvItem(decorate inhCollectionDcl(sg,sl,fn,bound,ty,o) with {}), defs);
}
function addLocalColDcl
Defs ::= sg::String sl::Decorated Location fn::String ty::TypeExp o::Operation defs::Defs
{
  return consValueDef(defaultEnvItem(decorate localCollectionDcl(sg,sl,fn,ty,o) with {}), defs);
}

