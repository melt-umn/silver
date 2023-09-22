grammar silver:compiler:modification:collection;

attribute isCollection, operation, attrBaseDefDispatcher, attrAppendDefDispatcher occurs on AttributeDclInfo;
attribute operation, baseDefDispatcher, appendDefDispatcher occurs on ValueDclInfo;

synthesized attribute isCollection::Boolean;

synthesized attribute attrBaseDefDispatcher :: (ProductionStmt ::= Decorated! DefLHS  Decorated! QNameAttrOccur  Expr);
synthesized attribute attrAppendDefDispatcher :: (ProductionStmt ::= Decorated! DefLHS  Decorated! QNameAttrOccur  Expr);

synthesized attribute baseDefDispatcher :: (ProductionStmt ::= Decorated! QName  Expr);
synthesized attribute appendDefDispatcher :: (ProductionStmt ::= Decorated! QName  Expr);

aspect default production
top::AttributeDclInfo ::=
{
  top.isCollection = false;
  top.operation = error("Internal compiler error: must be defined for all collection attribute declarations");
  
  top.attrBaseDefDispatcher = nonCollectionAttrBaseDefError;
  top.attrAppendDefDispatcher = nonCollectionAttrAppendDefError;
}

aspect default production
top::ValueDclInfo ::=
{
  top.operation = error("Internal compiler error: must be defined for all collection attribute declarations");
  
  top.baseDefDispatcher = errorCollectionValueDef;
  top.appendDefDispatcher = errorCollectionValueDef;
}

abstract production synCollectionDcl
top::AttributeDclInfo ::= fn::String bound::[TyVar] ty::Type o::Operation
{
  top.fullName = fn;
  propagate compareTo, compareKey;
  top.isEqual =
    top.compareKey == top.compareTo.compareKey &&
    fn == top.compareTo.fullName &&
    top.typeScheme == top.compareTo.typeScheme &&
    o.isEqual;

  top.typeScheme = polyType(bound, ty);
  top.isSynthesized = true;
  top.isCollection = true;
  top.operation = o;

  top.decoratedAccessHandler = synDecoratedAccessHandler;
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler, _, _, _);
  top.dataAccessHandler = synDataAccessHandler;
  top.attrDefDispatcher = collectionAttrDefError;
  top.attributionDispatcher = defaultAttributionDcl;

  top.attrBaseDefDispatcher = synBaseColAttributeDef;
  top.attrAppendDefDispatcher = synAppendColAttributeDef;
}
abstract production inhCollectionDcl
top::AttributeDclInfo ::= fn::String bound::[TyVar] ty::Type o::Operation
{
  top.fullName = fn;
  propagate compareTo, compareKey;
  top.isEqual =
    top.compareKey == top.compareTo.compareKey &&
    fn == top.compareTo.fullName &&
    top.typeScheme == top.compareTo.typeScheme &&
    o.isEqual;

  top.typeScheme = polyType(bound, ty);
  top.isInherited = true;
  top.isCollection = true;
  top.operation = o;

  top.decoratedAccessHandler = inhDecoratedAccessHandler;
  top.undecoratedAccessHandler = inhUndecoratedAccessErrorHandler;
  top.dataAccessHandler = inhUndecoratedAccessErrorHandler;
  top.attrDefDispatcher = collectionAttrDefError;
  top.attributionDispatcher = defaultAttributionDcl;

  top.attrBaseDefDispatcher = inhBaseColAttributeDef;
  top.attrAppendDefDispatcher = inhAppendColAttributeDef;
}

abstract production localCollectionDcl
top::ValueDclInfo ::= fn::String ty::Type o::Operation
{
  top.fullName = fn;
  propagate compareTo, isEqual;

  top.typeScheme = monoType(ty);
  top.operation = o;
  
  top.refDispatcher = localReference;
  top.defDispatcher = errorColNormalValueDef;
  top.defLHSDispatcher = localDefLHS;

  top.baseDefDispatcher = baseCollectionValueDef;
  top.appendDefDispatcher = appendCollectionValueDef;
  
  top.substitutedDclInfo = localCollectionDcl(fn, performRenaming(ty, top.givenSubstitution), o, sourceGrammar=top.sourceGrammar, sourceLocation=top.sourceLocation);
  
  -- TODO: attrOccursIndex
  -- We shouldn't be forwarding here
  forwards to localDcl(fn,ty,false,sourceGrammar=top.sourceGrammar,sourceLocation=top.sourceLocation);
}

global nonCollectionAttrBaseDefError::(ProductionStmt ::= Decorated! DefLHS  Decorated! QNameAttrOccur  Expr) =
  \ dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  e::Expr  l::Location ->
    errorAttributeDef([err(l, "The ':=' operator can only be used for collections. " ++ attr.name ++ " is not a collection.")], dl, attr, e);

global nonCollectionAttrAppendDefError::(ProductionStmt ::= Decorated! DefLHS  Decorated! QNameAttrOccur  Expr) =
  \ dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  e::Expr  l::Location ->
    errorAttributeDef([err(l, "The '<-' operator can only be used for collections. " ++ attr.name ++ " is not a collection.")], dl, attr, e);

global collectionAttrDefError::(ProductionStmt ::= Decorated! DefLHS  Decorated! QNameAttrOccur  Expr) =
  \ dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  e::Expr  l::Location ->
    errorAttributeDef([err(l, attr.name ++ " is a collection attribute, and you must use ':=' or '<-', not '='.")], dl, attr, e);


-- Defs
function synColDef
Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type o::Operation
{
  return attrDef(defaultEnvItem(synCollectionDcl(fn,bound,ty,o,sourceGrammar=sg,sourceLocation=sl)));
}
function inhColDef
Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type o::Operation
{
  return attrDef(defaultEnvItem(inhCollectionDcl(fn,bound,ty,o,sourceGrammar=sg,sourceLocation=sl)));
}
function localColDef
Def ::= sg::String sl::Location fn::String ty::Type o::Operation
{
  return valueDef(defaultEnvItem(localCollectionDcl(fn,ty,o,sourceGrammar=sg,sourceLocation=sl)));
}

