grammar silver:analysis:typechecking:core;
import silver:definition:core;
import silver:definition:env;
import silver:util;

import core;

aspect production defaultProductionBody
top::ProductionBody ::= stmts::ProductionStmts
{
  top.typeErrors = stmts.typeErrors;
}

aspect production productionStmtsNone
top::ProductionStmts ::= 
{
  top.typeErrors = [];
}

aspect production productionStmts
top::ProductionStmts ::= stmt::ProductionStmt
{
  top.typeErrors = stmt.typeErrors;
}

aspect production productionStmtsCons
top::ProductionStmts ::= h::ProductionStmt t::ProductionStmts
{
  top.typeErrors = h.typeErrors ++ t.typeErrors;
}

aspect production productionStmtsAppend
top::ProductionStmts ::= h::ProductionStmts t::ProductionStmts
{
  top.typeErrors = h.typeErrors ++ t.typeErrors;
}

aspect production productionStmtAttributeDef
top::ProductionStmt ::= a::AttributeDef
{
  top.typeErrors = a.typeErrors;
}

aspect production productionStmtReturnDef
top::ProductionStmt ::= a::ReturnDef
{
  top.typeErrors = a.typeErrors;
}

aspect production productionStmtLocalAttribute
top::ProductionStmt ::= a::LocalAttributeDcl
{
  top.typeErrors = a.typeErrors;
}

aspect production productionStmtProductionAttribute
top::ProductionStmt ::= a::ProductionAttributeDcl
{
  top.typeErrors = a.typeErrors;
}

aspect production productionStmtForwardsTo
top::ProductionStmt ::= a::ForwardsToDcl
{
  top.typeErrors = a.typeErrors;
}

aspect production productionStmtForwardingWith
top::ProductionStmt ::= a::ForwardingWithDcl
{
  top.typeErrors = a.typeErrors;
}

aspect production forwardsTo
top::ForwardsToDcl ::= 'forwards' 'to' e::Expr ';'
{
  local attribute er1 :: [Decorated Message];
  er1 = if e.typerep.isNonTerminal then [] else [err(top.location, "You must forward to a NonTerminal.")];

  local attribute lhsT :: Decorated TypeRep;
  lhsT = top.signature.outputElement.typerep;

  local attribute er2 :: [Decorated Message];
  er2 = if lhsT.typeEquals(lhsT, e.typerep).bValue
	then []
	else [err(top.location, "You must forward to a NonTerminal of type '" ++ lhsT.typeName ++ "'.")];

  top.typeErrors = e.typeErrors ++ er1 ++ er2;
}

aspect production forwardsToWith
top::ForwardsToDcl ::= 'forwards' 'to' e::Expr 'with' '{' inh::ForwardInhs '}' ';'
{
  local attribute er1 :: [Decorated Message];
  er1 = if e.typerep.isNonTerminal then [] else [err(top.location, "You must forward to a NonTerminal.")];

  local attribute lhsT :: Decorated TypeRep;
  lhsT = top.signature.outputElement.typerep;

  local attribute er2 :: [Decorated Message];
  er2 = if lhsT.typeEquals(lhsT, e.typerep).bValue
	then []
	else [err(top.location, "You must forward to a NonTerminal of type '" ++ lhsT.typeName ++ "'.")];


  top.typeErrors = e.typeErrors ++ inh.typeErrors ++ er1 ++ er2;
}

aspect production forwardingWith
top::ForwardingWithDcl ::= 'forwarding' 'with' '{' inh::ForwardInhs '}' ';'
{
  top.typeErrors = inh.typeErrors;
}

aspect production forwardInh
top::ForwardInh ::= lhs::ForwardLHSExpr '=' e::Expr ';'
{
  local attribute valid :: Boolean;
  valid = lhs.typerep.typeEquals(lhs.typerep, e.typerep).bValue;

  local attribute er :: [Decorated Message];
  er = if valid 
       then [] 
       else [err(top.location, "The LHS and RHS of the assignment must be the same type.\n" ++
           "\tInstead they are '" ++ lhs.typerep.unparse ++ "' and '" ++ e.typerep.unparse ++ "'")] ; 

  top.typeErrors = er ++ e.typeErrors ++ lhs.typeErrors; 
}

aspect production forwardInhsOne
top::ForwardInhs ::= lhs::ForwardInh
{
  top.typeErrors = lhs.typeErrors;
}

aspect production forwardInhsCons
top::ForwardInhs ::= lhs::ForwardInh rhs::ForwardInhs
{
  top.typeErrors = lhs.typeErrors ++ rhs.typeErrors;
}

aspect production forwardLhsExpr
top::ForwardLHSExpr ::= q::QName
{
  top.typeErrors = [];
}

aspect production localAttributeDcl
top::LocalAttributeDcl ::= 'local' 'attribute' a::Name '::' te::Type ';'
{
  top.typeErrors = [];
}

aspect production productionAttributeDcl
top::ProductionAttributeDcl ::= 'production' 'attribute' a::Name '::' te::Type ';'
{
  top.typeErrors = [];
}

aspect production attributeDef
top::AttributeDef ::= lhs::LHSExpr '=' e::Expr ';'
{
  local attribute valid :: Boolean;
  valid = lhs.typerep.typeEquals(lhs.typerep, e.typerep).bValue;

  local attribute er :: [Decorated Message];
  er = if valid 
       then [] 
       else [err(top.location, "The LHS and RHS of the assignment must be the same type.\n" ++
                                  "\tInstead they are '" ++ lhs.typerep.unparse ++ "' and '" ++ e.typerep.unparse ++ "'")] ; 

  top.typeErrors = er ++ lhs.typeErrors ++ e.typeErrors;
}

aspect production returnDef
top::ReturnDef ::= 'return' e::Expr ';'
{
  local attribute valid :: Boolean;
  valid = top.signature.outputElement.typerep.typeEquals(top.signature.outputElement.typerep, e.typerep).bValue;

  local attribute er :: [Decorated Message];
  er = if valid 
       then [] 
       else [err(top.location, "Invalid return type.\n" ++
                                  "(expected)\n   " ++ top.signature.outputElement.typerep.unparse ++ "\n(actual)\n   " ++ e.typerep.unparse)] ; 

  top.typeErrors = er ++ e.typeErrors;
}

aspect production fakeLHSExpr
top::LHSExpr ::= c1::QName c2::Decorated TypeRep
{
  top.typeErrors = [];
}
aspect production lhsExprOne
top::LHSExpr ::= id::Name
{
  top.typeErrors = [];
}

--TODO look at these errors again Do we need signature and signatureEnv?
aspect production lhsExprTwo
top::LHSExpr ::= id::Name '.' q::QName
{

  --binding checking verifies that everything is here.
  local attribute occursOn :: Boolean;
  occursOn = doesOccurOn(fName2, head(vals1).typerep.typeName, top.env);
             
  local attribute e1 :: [Decorated Message];
  e1 = if occursOn then []
       else [err(top.location, "Attribute '" ++ fName2 ++ "' does not decorate type '" ++ head(vals1).typerep.typeName ++ "'.")];

  local attribute e2 :: [Decorated Message];
  e2 = if id.name == top.signature.outputElement.elementName && isInherited(fName2, top.env)
       then [err(top.location, "Cannot assign to the lhs's inherited attributes.")]
       else [];

  local attribute e3 :: [Decorated Message];
  e3 = if contains(id.name, getNamesSignature(top.signature.inputElements)) && isSynthesized(fName2, top.env)
       then [err(top.location, "Cannot assign to a child's synthesized attributes.")]
       else [];

  local attribute e4 :: [Decorated Message];
  e4 = if !null(getValueDclOne(fName1, top.localsEnv)) && isSynthesized(fName2, top.env)
       then [err(top.location, "Cannot assign to a locals synthesized attributes.")]
       else [];

  top.typeErrors = e1 ++ e2 ++ e3 ++ e4;
}

