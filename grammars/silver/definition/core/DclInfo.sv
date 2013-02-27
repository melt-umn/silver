grammar silver:definition:core;

import silver:definition:regex;  -- soley for Terms. TODO : fix?

{--
 - The production a variable reference should forward to for this type of value
 -}
synthesized attribute refDispatcher :: (Expr ::= Decorated QName) occurs on DclInfo;
{--
 - The production an "assignment" should forward to for this type of value
 -}
synthesized attribute defDispatcher :: (ProductionStmt ::= Decorated QName  Equal_t  Expr) occurs on DclInfo;
{--
 - The production an "equation" left hand side should forward to for this type of value (i.e. the 'x' in 'x.a = e')
 -}
synthesized attribute defLHSDispatcher :: (DefLHS ::= Decorated QName) occurs on DclInfo;

{--
 - The handler for 'x.a' for 'a', given that 'x' is DECORATED.
 - @see accessDispather in TypeExp.sv, for the first step in that process...
 - @see decoratedAccessHandler production for where this is used
 -}
synthesized attribute decoratedAccessHandler :: (Expr ::= Decorated Expr Dot_t Decorated QNameAttrOccur) occurs on DclInfo;
{--
 - The handler for 'x.a' for 'a', given that 'x' is UNdecorated.
 - @see accessDispather in TypeExp.sv, for the first step in that process...
 - @see undecoratedAccessHandler production for where this is used
 -}
synthesized attribute undecoratedAccessHandler :: (Expr ::= Decorated Expr Dot_t Decorated QNameAttrOccur) occurs on DclInfo;
{--
 - The production an "equation" shuld forward to for this type of attribute (i.e. the 'a' in 'x.a = e')
 -}
synthesized attribute attrDefDispatcher :: (ProductionStmt ::= Decorated DefLHS Dot_t Decorated QNameAttrOccur Equal_t Expr) occurs on DclInfo;

aspect default production
top::DclInfo ::=
{
  -- again, blank.
  
  -- all values must provide refDispatcher, defDispatcher, dehLHSDispatcher.
  top.refDispatcher = error("Internal compiler error: must be defined for all value declarations");
  top.defDispatcher = error("Internal compiler error: must be defined for all value declarations");
  top.defLHSDispatcher = error("Internal compiler error: must be defined for all value declarations");
  -- all attributes must provide decoratedAccessHandler, attrDefDispatcher.
  top.decoratedAccessHandler = error("Internal compiler error: must be defined for all attribute declarations");
  top.undecoratedAccessHandler = error("Internal compiler error: must be defined for all attribute declarations");
  top.attrDefDispatcher = error("Internal compiler error: must be defined for all attribute declarations");  
}

-- -- non-interface values
aspect production childDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::TypeExp
{
  top.refDispatcher = childReference;
  top.defDispatcher = errorValueDef; -- TODO: we should be smarted about error messages, and mention its a child
  top.defLHSDispatcher = childDefLHS;
}
aspect production lhsDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::TypeExp
{
  top.refDispatcher = lhsReference;
  top.defDispatcher = errorValueDef; -- TODO: be smarter about the error message
  top.defLHSDispatcher = lhsDefLHS;
}
aspect production localDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::TypeExp
{
  top.refDispatcher = localReference;
  top.defDispatcher = localValueDef;
  top.defLHSDispatcher = localDefLHS;
}


-- -- interface values
aspect production prodDcl
top::DclInfo ::= sg::String sl::Location ns::NamedSignature
{
  top.refDispatcher = productionReference;
   -- Note that we still need production references, even though bug #16 removes the production type.
  top.defDispatcher = errorValueDef;
  top.defLHSDispatcher = errorDefLHS;
}
aspect production funDcl
top::DclInfo ::= sg::String sl::Location ns::NamedSignature
{
  top.refDispatcher = functionReference;
  top.defDispatcher = errorValueDef;
  top.defLHSDispatcher = errorDefLHS;
}
aspect production globalValueDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::TypeExp
{
  top.refDispatcher = globalValueReference;
  top.defDispatcher = errorValueDef;
  top.defLHSDispatcher = errorDefLHS;
}

-- -- interface Attributes
aspect production synDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::TypeExp
{
  top.decoratedAccessHandler = synDecoratedAccessHandler;
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler, _, _, _);
  top.attrDefDispatcher = synthesizedAttributeDef;
}
aspect production inhDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::TypeExp
{
  top.decoratedAccessHandler = inhDecoratedAccessHandler;
  top.undecoratedAccessHandler = accessBounceDecorate(inhDecoratedAccessHandler, _, _, _); -- TODO: should probably be an error handler!
  top.attrDefDispatcher = inheritedAttributeDef;
}
aspect production annoDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::TypeExp
{
  top.decoratedAccessHandler = accessBounceUndecorate(annoAccessHandler, _, _, _);
  top.undecoratedAccessHandler = annoAccessHandler;
}

-- -- interface Production attr (values)
aspect production forwardDcl
top::DclInfo ::= sg::String sl::Location ty::TypeExp
{
  top.refDispatcher = forwardReference;
  top.defDispatcher = errorValueDef; -- TODO: better error message
  top.defLHSDispatcher = forwardDefLHS;
}

