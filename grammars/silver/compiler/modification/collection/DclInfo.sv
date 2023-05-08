grammar silver:compiler:modification:collection;

attribute isCollection, operation, attrBaseDefDispatcher, attrAppendDefDispatcher occurs on AttributeDclInfo;
attribute operation, baseDefDispatcher, appendDefDispatcher occurs on ValueDclInfo;

synthesized attribute isCollection::Boolean;

synthesized attribute attrBaseDefDispatcher :: (ProductionStmt ::= Decorated! DefLHS  Decorated! QNameAttrOccur  Expr  Location);
synthesized attribute attrAppendDefDispatcher :: (ProductionStmt ::= Decorated! DefLHS  Decorated! QNameAttrOccur  Expr  Location);

synthesized attribute baseDefDispatcher :: (ProductionStmt ::= Decorated! QName  Expr  Location);
synthesized attribute appendDefDispatcher :: (ProductionStmt ::= Decorated! QName  Expr  Location);

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
  
  top.baseDefDispatcher = errorCollectionValueDef(_, _, location=_);
  top.appendDefDispatcher = errorCollectionValueDef(_, _, location=_);
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

  top.decoratedAccessHandler = synDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler(_, _, location=_), _, _, _);
  top.attrDefDispatcher = collectionAttrDefError;
  top.attributionDispatcher = defaultAttributionDcl(_, _, _, _, location=_);

  top.attrBaseDefDispatcher = synBaseColAttributeDef(_, _, _, location=_);
  top.attrAppendDefDispatcher = synAppendColAttributeDef(_, _, _, location=_);
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

  top.decoratedAccessHandler = inhDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(inhDecoratedAccessHandler(_, _, location=_), _, _, _); -- TODO: above should probably be an error handler!
  top.attrDefDispatcher = collectionAttrDefError;
  top.attributionDispatcher = defaultAttributionDcl(_, _, _, _, location=_);

  top.attrBaseDefDispatcher = inhBaseColAttributeDef(_, _, _, location=_);
  top.attrAppendDefDispatcher = inhAppendColAttributeDef(_, _, _, location=_);
}

abstract production localCollectionDcl
top::ValueDclInfo ::= fn::String ty::Type o::Operation
{
  top.fullName = fn;
  propagate compareTo, isEqual;

  top.typeScheme = monoType(ty);
  top.operation = o;
  
  top.refDispatcher = localReference(_, location=_);
  top.defDispatcher = errorColNormalValueDef(_, _, location=_);
  top.defLHSDispatcher = localDefLHS(_, location=_);

  top.baseDefDispatcher = baseCollectionValueDef(_, _, location=_);
  top.appendDefDispatcher = appendCollectionValueDef(_, _, location=_);
  
  top.substitutedDclInfo = localCollectionDcl(fn, performRenaming(ty, top.givenSubstitution), o, sourceGrammar=top.sourceGrammar, sourceLocation=top.sourceLocation);
  
  -- TODO: attrOccursIndex
  -- We shouldn't be forwarding here
  forwards to localDcl(fn,ty,sourceGrammar=top.sourceGrammar,sourceLocation=top.sourceLocation);
}

global nonCollectionAttrBaseDefError::(ProductionStmt ::= Decorated! DefLHS  Decorated! QNameAttrOccur  Expr  Location) =
  \ dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  e::Expr  l::Location ->
    errorAttributeDef([err(l, "The ':=' operator can only be used for collections. " ++ attr.name ++ " is not a collection.")], dl, attr, e, location=l);

global nonCollectionAttrAppendDefError::(ProductionStmt ::= Decorated! DefLHS  Decorated! QNameAttrOccur  Expr  Location) =
  \ dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  e::Expr  l::Location ->
    errorAttributeDef([err(l, "The '<-' operator can only be used for collections. " ++ attr.name ++ " is not a collection.")], dl, attr, e, location=l);

global collectionAttrDefError::(ProductionStmt ::= Decorated! DefLHS  Decorated! QNameAttrOccur  Expr  Location) =
  \ dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  e::Expr  l::Location ->
    errorAttributeDef([err(l, attr.name ++ " is a collection attribute, and you must use ':=' or '<-', not '='.")], dl, attr, e, location=l);


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

