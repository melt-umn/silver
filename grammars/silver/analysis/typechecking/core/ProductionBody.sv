grammar silver:analysis:typechecking:core;

import silver:util;

attribute typeErrors, upSubst, downSubst, finalSubst occurs on ProductionBody, ProductionStmts, ProductionStmt;

aspect production defaultProductionBody
top::ProductionBody ::= stmts::ProductionStmts
{
  top.typeErrors := stmts.typeErrors;
  
  stmts.downSubst = top.downSubst;
  top.upSubst = stmts.upSubst;
}

aspect production productionStmtsNone
top::ProductionStmts ::= 
{
  top.typeErrors := [];
  top.upSubst = top.downSubst;
}

aspect production productionStmts
top::ProductionStmts ::= stmt::ProductionStmt
{
  top.typeErrors := stmt.typeErrors;
  
  stmt.downSubst = top.downSubst;
  top.upSubst = stmt.upSubst;
}

aspect production productionStmtsCons
top::ProductionStmts ::= h::ProductionStmt t::ProductionStmts
{
  top.typeErrors := h.typeErrors ++ t.typeErrors;
  
  h.downSubst = top.downSubst;
  t.downSubst = h.upSubst;
  top.upSubst = t.upSubst;
}

aspect production productionStmtsAppend
top::ProductionStmts ::= h::ProductionStmts t::ProductionStmts
{
  top.typeErrors := h.typeErrors ++ t.typeErrors;
  
  h.downSubst = top.downSubst;
  t.downSubst = h.upSubst;
  top.upSubst = t.upSubst;
}

aspect production forwardsTo
top::ProductionStmt ::= 'forwards' 'to' e::Expr ';'
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  
  top.typeErrors := e.typeErrors;
  
  e.downSubst = top.downSubst;
  errCheck1.downSubst = e.upSubst;
  top.upSubst = errCheck1.upSubst;

  errCheck1 = check(e.typerep, top.signature.outputElement.typerep);
  top.typeErrors <- if errCheck1.typeerror
                    then [err(e.location, "Forward's expected type is " ++ errCheck1.rightpp ++ ", but the actual type supplied is " ++ errCheck1.leftpp)]
                    else [];
}

aspect production forwardsToWith
top::ProductionStmt ::= 'forwards' 'to' e::Expr 'with' '{' inh::ForwardInhs '}' ';'
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  
  top.typeErrors := e.typeErrors;
  
  e.downSubst = top.downSubst;
  errCheck1.downSubst = e.upSubst;
  inh.downSubst = errCheck1.upSubst;
  top.upSubst = inh.upSubst;

  errCheck1 = unifyCheck(e.typerep, top.signature.outputElement.typerep);
  top.typeErrors <- if errCheck1.typeerror
                    then [err(e.location, "Forward's expected type is " ++ errCheck1.rightpp ++ ", but the actual type supplied is " ++ errCheck1.leftpp)]
                    else [];
}

aspect production forwardingWith
top::ProductionStmt ::= 'forwarding' 'with' '{' inh::ForwardInhs '}' ';'
{
  top.typeErrors := inh.typeErrors;
  
  inh.downSubst = top.downSubst;
  top.upSubst = inh.upSubst;
}

aspect production forwardInhsOne
top::ForwardInhs ::= lhs::ForwardInh
{
  top.typeErrors := lhs.typeErrors;
  
  lhs.downSubst = top.downSubst;
  top.upSubst = lhs.upSubst;
}

aspect production forwardInhsCons
top::ForwardInhs ::= lhs::ForwardInh rhs::ForwardInhs
{
  top.typeErrors := lhs.typeErrors ++ rhs.typeErrors;
  
  lhs.downSubst = top.downSubst;
  rhs.downSubst = lhs.upSubst;
  top.upSubst = rhs.upSubst;
}

aspect production forwardInh
top::ForwardInh ::= lhs::ForwardLHSExpr '=' e::Expr ';'
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  top.typeErrors := e.typeErrors ++ lhs.typeErrors; 
  
  lhs.downSubst = top.downSubst;
  e.downSubst = lhs.upSubst;
  errCheck1.downSubst = e.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(lhs.typerep, e.typerep);
  top.typeErrors <- 
       if errCheck1.typeerror
       then [err(e.location, lhs.pp ++ " has expected type " ++ errCheck1.leftpp
                              ++ ", but the expression has type " ++ errCheck1.rightpp)]
       else [];
}

aspect production forwardLhsExpr
top::ForwardLHSExpr ::= q::QName
{
  top.typeErrors := [];
  
  top.upSubst = top.downSubst;
}

aspect production localAttributeDcl
top::ProductionStmt ::= 'local' 'attribute' a::Name '::' te::Type ';'
{
  top.typeErrors := [];
  
  top.upSubst = top.downSubst;
}

aspect production productionAttributeDcl
top::ProductionStmt ::= 'production' 'attribute' a::Name '::' te::Type ';'
{
  top.typeErrors := [];
  
  top.upSubst = top.downSubst;
}

aspect production returnDef
top::ProductionStmt ::= 'return' e::Expr ';'
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  top.typeErrors := e.typeErrors;
  
  e.downSubst = top.downSubst;
  errCheck1.downSubst = e.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e.typerep, top.signature.outputElement.typerep);
  top.typeErrors <-
       if errCheck1.failure
       then [err(top.location, "Expected return type is " ++ errCheck1.rightpp ++ ", but the expression has actual type " ++ errCheck1.leftpp)]
       else [];
}

aspect production errorAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  top.typeErrors := [];
  
  e.downSubst = top.downSubst;
  top.upSubst = e.upSubst;
}

aspect production synthesizedAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  top.typeErrors := e.typeErrors;

  local attribute occursCheck :: [Decorated DclInfo];
  occursCheck = getOccursDcl(attr.lookupAttribute.fullName, dl.typerep.typeName, top.env);

  top.typeErrors <-
       if null(occursCheck)
       then [err(top.location, "Attribute '" ++ attr.name ++ "' does not decorate type '" ++ dl.typerep.typeName ++ "'.")]
       else [];

  production attribute attrType :: TypeExp;
  attrType = determineAttributeType(head(occursCheck), dl.typerep);

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e.downSubst = top.downSubst;
  errCheck1.downSubst = e.upSubst;
  top.upSubst = if null(occursCheck) then e.upSubst else errCheck1.upSubst;  -- BYPASS THE CHECK if it doesn't occur on

  errCheck1 = check(attrType, e.typerep);
  top.typeErrors <-
       if !null(occursCheck) && errCheck1.typeerror
       then [err(top.location, "Attribute " ++ attr.name ++ " has type " ++ errCheck1.leftpp ++ " but the expression being assigned to it has type " ++ errCheck1.rightpp)]
       else [];
}

aspect production inheritedAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  top.typeErrors := e.typeErrors;

  local attribute occursCheck :: [Decorated DclInfo];
  occursCheck = getOccursDcl(attr.lookupAttribute.fullName, dl.typerep.typeName, top.env);

  top.typeErrors <-
       if null(occursCheck)
       then [err(top.location, "Attribute '" ++ attr.name ++ "' does not decorate type '" ++ dl.typerep.typeName ++ "'.")]
       else [];

  production attribute attrType :: TypeExp;
  attrType = determineAttributeType(head(occursCheck), dl.typerep);

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e.downSubst = top.downSubst;
  errCheck1.downSubst = e.upSubst;
  top.upSubst = if null(occursCheck) then e.upSubst else errCheck1.upSubst;  -- BYPASS THE CHECK if it doesn't occur on

  errCheck1 = check(attrType, e.typerep);
  top.typeErrors <-
       if !null(occursCheck) && errCheck1.typeerror
       then [err(top.location, "Attribute " ++ attr.name ++ " has type " ++ errCheck1.leftpp ++ " but the expression being assigned to it has type " ++ errCheck1.rightpp)]
       else [];
}

aspect production localValueDef
top::ProductionStmt ::= val::Decorated QName '=' e::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  top.typeErrors := e.typeErrors;
  
  e.downSubst = top.downSubst;
  errCheck1.downSubst = e.upSubst;
  top.upSubst = errCheck1.upSubst;

  errCheck1 = check(e.typerep, val.lookupValue.typerep);
  top.typeErrors <-
       if errCheck1.typeerror
       then [err(top.location, "Value " ++ val.name ++ " has type " ++ errCheck1.rightpp ++ " but the expression being assigned to it has type " ++ errCheck1.leftpp)]
       else [];
}

aspect production errorValueDef
top::ProductionStmt ::= val::Decorated QName '=' e::Expr
{
  top.typeErrors := [];
  
  e.downSubst = top.downSubst;
  top.upSubst = e.upSubst;
}

