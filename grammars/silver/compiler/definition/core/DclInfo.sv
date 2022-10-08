grammar silver:compiler:definition:core;

import silver:compiler:modification:copper only terminalIdReference;

{--
 - The production a variable reference should forward to for this type of value
 -}
synthesized attribute refDispatcher :: (Expr ::= PartiallyDecorated QName  Location) occurs on ValueDclInfo;
{--
 - The production an "assignment" should forward to for this type of value
 -}
synthesized attribute defDispatcher :: (ProductionStmt ::= PartiallyDecorated QName  Expr  Location) occurs on ValueDclInfo;
{--
 - The production an "equation" left hand side should forward to for this type of value (i.e. the 'x' in 'x.a = e')
 -}
synthesized attribute defLHSDispatcher :: (DefLHS ::= PartiallyDecorated QName  Location) occurs on ValueDclInfo;

{--
 - The handler for 'x.a' for 'a', given that 'x' is DECORATED.
 - @see accessDispather in TypeExp.sv, for the first step in that process...
 - @see decoratedAccessHandler production for where this is used
 -}
synthesized attribute decoratedAccessHandler :: (Expr ::= PartiallyDecorated Expr  PartiallyDecorated QNameAttrOccur  Location) occurs on AttributeDclInfo;
{--
 - The handler for 'x.a' for 'a', given that 'x' is UNdecorated.
 - @see accessDispather in TypeExp.sv, for the first step in that process...
 - @see undecoratedAccessHandler production for where this is used
 -}
synthesized attribute undecoratedAccessHandler :: (Expr ::= PartiallyDecorated Expr  PartiallyDecorated QNameAttrOccur  Location) occurs on AttributeDclInfo;
{--
 - The production an "equation" should forward to for this type of attribute (i.e. the 'a' in 'x.a = e')
 -}
synthesized attribute attrDefDispatcher :: (ProductionStmt ::= PartiallyDecorated DefLHS  PartiallyDecorated QNameAttrOccur  Expr  Location) occurs on AttributeDclInfo;
{--
 - The production an "occurs on" decl should forward to for this type of attribute (for extension use, defaultAttributionDcl for all syn/inh/autocopy attrs.)
 -}
synthesized attribute attributionDispatcher :: (AGDcl ::= PartiallyDecorated QName  BracketedOptTypeExprs  QName  BracketedOptTypeExprs  Location) occurs on AttributeDclInfo;

-- -- non-interface values
aspect production childDcl
top::ValueDclInfo ::= fn::String ty::Type
{
  top.refDispatcher = childReference(_, location=_);
  top.defDispatcher = errorValueDef(_, _, location=_); -- TODO: we should be smarted about error messages, and mention its a child
  top.defLHSDispatcher = childDefLHS(_, location=_);
}
aspect production lhsDcl
top::ValueDclInfo ::= fn::String ty::Type
{
  top.refDispatcher = lhsReference(_, location=_);
  top.defDispatcher = errorValueDef(_, _, location=_); -- TODO: be smarter about the error message
  top.defLHSDispatcher = lhsDefLHS(_, location=_);
}
aspect production localDcl
top::ValueDclInfo ::= fn::String ty::Type
{
  top.refDispatcher = localReference(_, location=_);
  top.defDispatcher = localValueDef(_, _, location=_);
  top.defLHSDispatcher = localDefLHS(_, location=_);
}


-- -- interface values
aspect production prodDcl
top::ValueDclInfo ::= ns::NamedSignature hasForward::Boolean
{
  top.refDispatcher = productionReference(_, location=_);
   -- Note that we still need production references, even though bug #16 removes the production type.
  top.defDispatcher = errorValueDef(_, _, location=_);
  top.defLHSDispatcher = errorDefLHS(_, location=_);
}
aspect production funDcl
top::ValueDclInfo ::= ns::NamedSignature
{
  top.refDispatcher = functionReference(_, location=_);
  top.defDispatcher = errorValueDef(_, _, location=_);
  top.defLHSDispatcher = errorDefLHS(_, location=_);
}
aspect production globalValueDcl
top::ValueDclInfo ::= fn::String bound::[TyVar] contexts::[Context] ty::Type
{
  top.refDispatcher = globalValueReference(_, location=_);
  top.defDispatcher = errorValueDef(_, _, location=_);
  top.defLHSDispatcher = errorDefLHS(_, location=_);
}
aspect production classMemberDcl
top::ValueDclInfo ::= fn::String bound::[TyVar] head::Context contexts::[Context] ty::Type
{
  top.refDispatcher = classMemberReference(_, location=_);
  top.defDispatcher = errorValueDef(_, _, location=_);
  top.defLHSDispatcher = errorDefLHS(_, location=_);
}

-- -- interface Production attr (values)
aspect production forwardDcl
top::ValueDclInfo ::= ty::Type
{
  top.refDispatcher = forwardReference(_, location=_);
  top.defDispatcher = errorValueDef(_, _, location=_); -- TODO: better error message
  top.defLHSDispatcher = forwardDefLHS(_, location=_);
}

aspect production termIdDcl
top::ValueDclInfo ::= fn::String
{
  top.refDispatcher = terminalIdReference(_, location=_);
  top.defDispatcher = errorValueDef(_, _, location=_);
  top.defLHSDispatcher = errorDefLHS(_, location=_);
}

-- -- interface Attributes
aspect production synDcl
top::AttributeDclInfo ::= fn::String bound::[TyVar] ty::Type
{
  top.decoratedAccessHandler = synDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler(_, _, location=_), _, _, _);
  top.attrDefDispatcher = synthesizedAttributeDef(_, _, _, location=_);
  top.attributionDispatcher = defaultAttributionDcl(_, _, _, _, location=_);
}
aspect production inhDcl
top::AttributeDclInfo ::= fn::String bound::[TyVar] ty::Type
{
  top.decoratedAccessHandler = inhDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(inhDecoratedAccessHandler(_, _, location=_), _, _, _); -- TODO: above should probably be an error handler! access inh from undecorated?
  top.attrDefDispatcher = inheritedAttributeDef(_, _, _, location=_);
  top.attributionDispatcher = defaultAttributionDcl(_, _, _, _, location=_);
}
aspect production annoDcl
top::AttributeDclInfo ::= fn::String bound::[TyVar] ty::Type
{
  top.decoratedAccessHandler = accessBounceUndecorate(annoAccessHandler(_, _, location=_), _, _, _);
  top.undecoratedAccessHandler = annoAccessHandler(_, _, location=_);
  top.attrDefDispatcher =
    \ dl::PartiallyDecorated DefLHS  attr::PartiallyDecorated QNameAttrOccur  e::Expr  l::Location ->
      errorAttributeDef([err(l, "Annotations are not defined as equations within productions")], dl, attr, e, location=l);
  top.attributionDispatcher = defaultAttributionDcl(_, _, _, _, location=_);
}