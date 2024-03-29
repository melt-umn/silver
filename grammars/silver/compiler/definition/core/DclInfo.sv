grammar silver:compiler:definition:core;

import silver:compiler:modification:copper only terminalIdReference;

{--
 - The production a variable reference should forward to for this type of value
 -}
synthesized attribute refDispatcher :: (Expr ::= Decorated! QName) occurs on ValueDclInfo;
{--
 - The production an "assignment" should forward to for this type of value
 -}
synthesized attribute defDispatcher :: (ProductionStmt ::= Decorated! QName  Expr) occurs on ValueDclInfo;
{--
 - The production an "equation" left hand side should forward to for this type of value (i.e. the 'x' in 'x.a = e')
 -}
synthesized attribute defLHSDispatcher :: (DefLHS ::= Decorated! QName) occurs on ValueDclInfo;
{--
 - The production a translation attribute left hand side should forward to, for this type of value (i.e. the 'x.a' in 'x.a.b = e')
 -}
synthesized attribute transDefLHSDispatcher :: (DefLHS ::= Decorated! QName  Decorated!  QNameAttrOccur) occurs on ValueDclInfo;

{--
 - The handler for 'x.a' for 'a', given that 'x' is DECORATED.
 - @see decoratedAccessHandler production for where this is used
 -}
synthesized attribute decoratedAccessHandler :: (Expr ::= Decorated! Expr  Decorated! QNameAttrOccur) occurs on AttributeDclInfo;
{--
 - The handler for 'x.a' for 'a', given that 'x' is UNdecorated.
 - @see undecoratedAccessHandler production for where this is used
 -}
synthesized attribute undecoratedAccessHandler :: (Expr ::= Decorated! Expr  Decorated! QNameAttrOccur) occurs on AttributeDclInfo;
{--
 - The handler for 'x.a' for 'a', given that 'x' is data.
 - @see dataAccessHandler production for where this is used
 -}
synthesized attribute dataAccessHandler :: (Expr ::= Decorated! Expr  Decorated! QNameAttrOccur) occurs on AttributeDclInfo;
{--
 - The production an "equation" should forward to for this type of attribute (i.e. the 'a' in 'x.a = e')
 -}
synthesized attribute attrDefDispatcher :: (ProductionStmt ::= Decorated! DefLHS  Decorated! QNameAttrOccur  Expr) occurs on AttributeDclInfo;
{--
 - The production an "occurs on" decl should forward to for this type of attribute (for extension use, defaultAttributionDcl for all syn/inh attrs.)
 -}
synthesized attribute attributionDispatcher :: (AGDcl ::= Decorated! QName  BracketedOptTypeExprs  QName  BracketedOptTypeExprs) occurs on AttributeDclInfo;

-- -- non-interface values
aspect production childDcl
top::ValueDclInfo ::= fn::String ty::Type
{
  top.refDispatcher = childReference;
  top.defDispatcher = errorValueDef; -- TODO: we should be smarted about error messages, and mention its a child
  top.defLHSDispatcher = childDefLHS;
  top.transDefLHSDispatcher = childTransAttrDefLHS;
}
aspect production lhsDcl
top::ValueDclInfo ::= fn::String ty::Type
{
  top.refDispatcher = lhsReference;
  top.defDispatcher = errorValueDef; -- TODO: be smarter about the error message
  top.defLHSDispatcher = lhsDefLHS;
  top.transDefLHSDispatcher = errorTransAttrDefLHS;
}
aspect production localDcl
top::ValueDclInfo ::= fn::String ty::Type _
{
  top.refDispatcher = localReference;
  top.defDispatcher = localValueDef;
  top.defLHSDispatcher = localDefLHS;
  top.transDefLHSDispatcher = localTransAttrDefLHS;
}


-- -- interface values
aspect production prodDcl
top::ValueDclInfo ::= ns::NamedSignature hasForward::Boolean
{
  top.refDispatcher = productionReference;
   -- Note that we still need production references, even though bug #16 removes the production type.
  top.defDispatcher = errorValueDef;
  top.defLHSDispatcher = errorDefLHS;
  top.transDefLHSDispatcher = errorTransAttrDefLHS;
}
aspect production funDcl
top::ValueDclInfo ::= ns::NamedSignature
{
  top.refDispatcher = functionReference;
  top.defDispatcher = errorValueDef;
  top.defLHSDispatcher = errorDefLHS;
  top.transDefLHSDispatcher = errorTransAttrDefLHS;
}
aspect production globalValueDcl
top::ValueDclInfo ::= fn::String bound::[TyVar] contexts::[Context] ty::Type
{
  top.refDispatcher = globalValueReference;
  top.defDispatcher = errorValueDef;
  top.defLHSDispatcher = errorDefLHS;
  top.transDefLHSDispatcher = errorTransAttrDefLHS;
}
aspect production classMemberDcl
top::ValueDclInfo ::= fn::String bound::[TyVar] head::Context contexts::[Context] ty::Type
{
  top.refDispatcher = classMemberReference;
  top.defDispatcher = errorValueDef;
  top.defLHSDispatcher = errorDefLHS;
  top.transDefLHSDispatcher = errorTransAttrDefLHS;
}

-- -- interface Production attr (values)
aspect production forwardDcl
top::ValueDclInfo ::= ty::Type
{
  top.refDispatcher = forwardReference;
  top.defDispatcher = errorValueDef; -- TODO: better error message
  top.defLHSDispatcher = forwardDefLHS;
  top.transDefLHSDispatcher = errorTransAttrDefLHS;
}

aspect production termIdDcl
top::ValueDclInfo ::= fn::String
{
  top.refDispatcher = terminalIdReference;
  top.defDispatcher = errorValueDef;
  top.defLHSDispatcher = errorDefLHS;
  top.transDefLHSDispatcher = errorTransAttrDefLHS;
}

-- -- interface Attributes
aspect production synDcl
top::AttributeDclInfo ::= fn::String bound::[TyVar] ty::Type
{
  top.decoratedAccessHandler = synDecoratedAccessHandler;
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler, _, _);
  top.dataAccessHandler = synDataAccessHandler;
  top.attrDefDispatcher = synthesizedAttributeDef;
  top.attributionDispatcher = defaultAttributionDcl;
}
aspect production inhDcl
top::AttributeDclInfo ::= fn::String bound::[TyVar] ty::Type
{
  top.decoratedAccessHandler = inhDecoratedAccessHandler;
  top.undecoratedAccessHandler = inhUndecoratedAccessErrorHandler;
  top.dataAccessHandler = inhUndecoratedAccessErrorHandler;
  top.attrDefDispatcher = inheritedAttributeDef;
  top.attributionDispatcher = defaultAttributionDcl;
}
aspect production transDcl
top::AttributeDclInfo ::= fn::String bound::[TyVar] ty::Type
{
  top.decoratedAccessHandler = transDecoratedAccessHandler;
  top.undecoratedAccessHandler = transUndecoratedAccessErrorHandler;
  top.dataAccessHandler = transUndecoratedAccessErrorHandler;
  top.attrDefDispatcher = synthesizedAttributeDef;
  top.attributionDispatcher = defaultAttributionDcl;
}
aspect production annoDcl
top::AttributeDclInfo ::= fn::String bound::[TyVar] ty::Type
{
  top.decoratedAccessHandler = accessBounceUndecorate(annoAccessHandler, _, _);
  top.undecoratedAccessHandler = annoAccessHandler;
  top.dataAccessHandler = annoAccessHandler;
  top.attrDefDispatcher =
    \ dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  e::Expr ->
      errorAttributeDef([errFromOrigin(ambientOrigin(), "Annotations are not defined as equations within productions")], dl, attr, e);
  top.attributionDispatcher = defaultAttributionDcl;
}