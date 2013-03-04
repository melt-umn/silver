grammar silver:modification:collection;

attribute attrBaseDefDispatcher, attrAppendDefDispatcher, baseDefDispatcher, appendDefDispatcher occurs on DclInfo;

synthesized attribute attrBaseDefDispatcher :: (ProductionStmt ::= Decorated DefLHS  Decorated QNameAttrOccur  Expr  Location);
synthesized attribute attrAppendDefDispatcher :: (ProductionStmt ::= Decorated DefLHS  Decorated QNameAttrOccur  Expr  Location);

synthesized attribute baseDefDispatcher :: (ProductionStmt ::= Decorated QName  Expr  Location);
synthesized attribute appendDefDispatcher :: (ProductionStmt ::= Decorated QName  Expr  Location);

-- TODO: the 'operation' value on these declarations is never used.
-- Please take a moment to think about whether it should even exist or not.

aspect default production
top::DclInfo ::=
{
  top.attrBaseDefDispatcher = errorCollectionDefDispatcher(_, _, _, location=_);
  top.attrAppendDefDispatcher = errorCollectionDefDispatcher(_, _, _, location=_);

  top.baseDefDispatcher = errorCollectionValueDef(_, _, location=_);
  top.appendDefDispatcher = errorCollectionValueDef(_, _, location=_);
}

abstract production synCollectionDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::TypeExp o::Operation
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  ty.boundVariables = top.boundVariables ++ bound; -- explicit to make sure it errors if we can't
  top.unparse = "syncol(" ++ sl.unparse ++ ", '" ++ fn ++ "', " ++ unparseTyVars(bound, ty.boundVariables) ++ ", " ++ ty.unparse ++ ", " ++ o.unparse ++ ")";
  
  top.typerep = ty;
  top.dclBoundVars = bound;

  top.decoratedAccessHandler = synDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler(_, _, location=_), _, _, _);
  top.attrDefDispatcher = errorColNormalAttributeDef(_, _, _, location=_);

  top.attrBaseDefDispatcher = synBaseColAttributeDef(_, _, _, location=_);
  top.attrAppendDefDispatcher = synAppendColAttributeDef(_, _, _, location=_);

  forwards to synDcl(sg,sl,fn,bound,ty);
}
abstract production inhCollectionDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::TypeExp o::Operation
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  ty.boundVariables = top.boundVariables ++ bound; -- explicit to make sure it errors if we can't
  top.unparse = "inhcol(" ++ sl.unparse ++ ", '" ++ fn ++ "', " ++ unparseTyVars(bound, ty.boundVariables) ++ ", " ++ ty.unparse ++ ", " ++ o.unparse ++ ")";
  
  top.typerep = ty;
  top.dclBoundVars = bound;

  top.decoratedAccessHandler = inhDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(inhDecoratedAccessHandler(_, _, location=_), _, _, _); -- TODO: should probably be an error handler!
  top.attrDefDispatcher = errorColNormalAttributeDef(_, _, _, location=_);

  top.attrBaseDefDispatcher = inhBaseColAttributeDef(_, _, _, location=_);
  top.attrAppendDefDispatcher = inhAppendColAttributeDef(_, _, _, location=_);

  forwards to inhDcl(sg,sl,fn,bound,ty);
}

abstract production localCollectionDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::TypeExp o::Operation
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  ty.boundVariables = top.boundVariables; -- explicit to make sure it errors if we can't
  top.unparse = "loccol(" ++ sl.unparse ++ ", '" ++ fn ++ "', " ++ ty.unparse ++ ", " ++ o.unparse ++ ")";
  
  top.typerep = ty;
  
  top.refDispatcher = localReference(_, location=_);
  top.defDispatcher = errorColNormalValueDef(_, _, location=_);
  top.defLHSDispatcher = localDefLHS(_, location=_);

  top.baseDefDispatcher = baseCollectionValueDef(_, _, location=_);
  top.appendDefDispatcher = appendCollectionValueDef(_, _, location=_);

  forwards to localDcl(sg,sl,fn,ty);
  
  top.substitutedDclInfo = localCollectionDcl(sg,sl,fn, performSubstitution(ty, top.givenSubstitution), o);
}


-- Defs
function synColDef
Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::TypeExp o::Operation
{
  return attrDef(defaultEnvItem(synCollectionDcl(sg,sl,fn,bound,ty,o)));
}
function inhColDef
Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::TypeExp o::Operation
{
  return attrDef(defaultEnvItem(inhCollectionDcl(sg,sl,fn,bound,ty,o)));
}
function localColDef
Def ::= sg::String sl::Location fn::String ty::TypeExp o::Operation
{
  return valueDef(defaultEnvItem(localCollectionDcl(sg,sl,fn,ty,o)));
}

