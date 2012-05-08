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
 - The production an attribute access should forward to for this type of attribute (i.e. the a in 'x.a')
 - WHEN the left hand side is a decorated nonterminal **only** (i.e. the 'x' is decorated)
 - @see accessDispather in TypeExp.sv, for the first step in that process...
 -}
synthesized attribute attrAccessDispatcher :: (Expr ::= Decorated Expr Dot_t Decorated QName) occurs on DclInfo;
{--
 - The production an "equation" shuld forward to for this type of attribute (i.e. the 'a' in 'x.a = e')
 -}
synthesized attribute attrDefDispatcher :: (ProductionStmt ::= DefLHS Dot_t Decorated QName Equal_t Expr) occurs on DclInfo;

aspect default production
top::DclInfo ::=
{
  -- again, blank.
  
  -- all values must provide refDispatcher, defDispatcher, dehLHSDispatcher.
  top.refDispatcher = error("Internal compiler error: must be defined for all value declarations");
  top.defDispatcher = error("Internal compiler error: must be defined for all value declarations");
  top.defLHSDispatcher = error("Internal compiler error: must be defined for all value declarations");
  -- all attributes must provide attrAccessDispatcher, attrDefDispatcher.
  top.attrAccessDispatcher = error("Internal compiler error: must be defined for all attribute declarations");  
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
-- -- interface types
aspect production ntDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::TypeExp closed::Boolean
{
}
aspect production termDcl
top::DclInfo ::= sg::String sl::Location fn::String regex::Regex_R
{
}

-- -- interface Attributes
aspect production synDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::TypeExp
{
  top.attrAccessDispatcher = synDNTAccessDispatcher;
  top.attrDefDispatcher = synthesizedAttributeDef;
}
aspect production inhDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::TypeExp
{
  top.attrAccessDispatcher = inhDNTAccessDispatcher;
  top.attrDefDispatcher = inheritedAttributeDef;
}

-- -- interface Production attr (values)
aspect production paDcl
top::DclInfo ::= sg::String sl::Location fn::String outty::TypeExp intys::[TypeExp] dcls::Defs
{
}
aspect production forwardDcl
top::DclInfo ::= sg::String sl::Location ty::TypeExp
{
  top.refDispatcher = forwardReference;
  top.defDispatcher = errorValueDef; -- TODO: better error message
  top.defLHSDispatcher = forwardDefLHS;
}

-- -- interface other
aspect production occursDcl
top::DclInfo ::= sg::String sl::Location fnnt::String fnat::String ntty::TypeExp atty::TypeExp
{
}

-- TODO THIS SHOULD GO ELSEWHERE
nonterminal OccursCheck with errors, typerep, dcl;

-- Doc note: be sure you've included at.errors, as well as this production's errors!
abstract production occursCheckQName
top::OccursCheck ::= at::Decorated QName  ntty::TypeExp
{
  local attribute occursCheck :: [Decorated DclInfo];
  occursCheck = getOccursDcl(at.lookupAttribute.fullName, ntty.typeName, at.env); -- cheating to get env! :) Must be decorated!

  top.errors := if null(at.lookupAttribute.errors) && null(occursCheck)
                then [err(at.location, "Attribute '" ++ at.name ++ "' does not occur on '" ++ prettyType(ntty) ++ "'")]
                else [];
  top.typerep = if null(at.lookupAttribute.errors) && null(top.errors)
                then determineAttributeType(head(occursCheck), ntty)
                else errorType();
  top.dcl = head(occursCheck);
}


