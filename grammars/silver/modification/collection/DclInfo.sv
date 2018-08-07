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
  top.attrBaseDefDispatcher =
    \ dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr  l::Location ->
      errorAttributeDef([err(l, "The ':=' operator can only be used for collections. " ++ attr.pp ++ " is not a collection.")], dl, attr, e, location=l);
  top.attrAppendDefDispatcher =
    \ dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr  l::Location ->
      errorAttributeDef([err(l, "The '<-' operator can only be used for collections. " ++ attr.pp ++ " is not a collection.")], dl, attr, e, location=l);

  top.baseDefDispatcher = errorCollectionValueDef(_, _, location=_);
  top.appendDefDispatcher = errorCollectionValueDef(_, _, location=_);
}

abstract production synCollectionDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type o::Operation
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.typerep = ty;
  top.dclBoundVars = bound;

  top.decoratedAccessHandler = synDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler(_, _, location=_), _, _, _);
  top.attrDefDispatcher = 
    \ dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr  l::Location ->
      errorAttributeDef([err(l, attr.pp ++ " is a collection attribute, and you must use ':=' or '<-', not '='.")], dl, attr, e, location=l);

  top.attrBaseDefDispatcher = synBaseColAttributeDef(_, _, _, location=_);
  top.attrAppendDefDispatcher = synAppendColAttributeDef(_, _, _, location=_);

  forwards to synDcl(sg,sl,fn,bound,ty);
}
abstract production inhCollectionDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type o::Operation
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.typerep = ty;
  top.dclBoundVars = bound;

  top.decoratedAccessHandler = inhDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(inhDecoratedAccessHandler(_, _, location=_), _, _, _); -- TODO: above should probably be an error handler!
  top.attrDefDispatcher =
    \ dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr  l::Location ->
      errorAttributeDef([err(l, attr.pp ++ " is a collection attribute, and you must use ':=' or '<-', not '='.")], dl, attr, e, location=l);

  top.attrBaseDefDispatcher = inhBaseColAttributeDef(_, _, _, location=_);
  top.attrAppendDefDispatcher = inhAppendColAttributeDef(_, _, _, location=_);

  forwards to inhDcl(sg,sl,fn,bound,ty);
}

abstract production localCollectionDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::Type o::Operation
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.typerep = ty;
  
  top.refDispatcher = localReference(_, location=_);
  top.defDispatcher = errorColNormalValueDef(_, _, location=_);
  top.defLHSDispatcher = localDefLHS(_, location=_);

  top.baseDefDispatcher = baseCollectionValueDef(_, _, location=_);
  top.appendDefDispatcher = appendCollectionValueDef(_, _, location=_);

  forwards to localDcl(sg,sl,fn,ty);
  
  top.substitutedDclInfo = localCollectionDcl(sg,sl,fn, performRenaming(ty, top.givenSubstitution), o);
}


-- Defs
function synColDef
Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type o::Operation
{
  return attrDef(defaultEnvItem(synCollectionDcl(sg,sl,fn,bound,ty,o)));
}
function inhColDef
Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type o::Operation
{
  return attrDef(defaultEnvItem(inhCollectionDcl(sg,sl,fn,bound,ty,o)));
}
function localColDef
Def ::= sg::String sl::Location fn::String ty::Type o::Operation
{
  return valueDef(defaultEnvItem(localCollectionDcl(sg,sl,fn,ty,o)));
}

