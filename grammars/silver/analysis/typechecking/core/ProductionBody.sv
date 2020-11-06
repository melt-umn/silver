grammar silver:analysis:typechecking:core;

import silver:util;

attribute upSubst, downSubst, finalSubst occurs on ProductionStmt, ForwardInhs, ForwardInh, ForwardLHSExpr;

{--
 - These need an initial state only due to aspects (I think? maybe not. Investigate someday.)
 - They otherwise confine their contexts to each individual Stmt.
 -}
attribute downSubst occurs on ProductionBody, ProductionStmts;


aspect production productionBody
top::ProductionBody ::= '{' stmts::ProductionStmts '}'
{
  stmts.downSubst = top.downSubst;
}

aspect production productionStmtsNil
top::ProductionStmts ::= 
{
}

aspect production productionStmtsSnoc
top::ProductionStmts ::= h::ProductionStmts t::ProductionStmt
{
  h.downSubst = top.downSubst;

  t.downSubst = top.downSubst;
  t.finalSubst = t.upSubst;
}

aspect production productionStmtAppend
top::ProductionStmt ::= h::ProductionStmt t::ProductionStmt
{
  -- We treat this as though each is independent here as well.
  h.downSubst = top.downSubst;
  h.finalSubst = h.upSubst;

  t.downSubst = top.downSubst;
  t.finalSubst = t.upSubst;
  
  top.upSubst = error("Shouldn't ever be needed anywhere. (Should only ever be fed back here as top.finalSubst)");
  -- Of course, this means do not use top.finalSubst here!
}

aspect production errorProductionStmt
top::ProductionStmt ::= e::[Message]
{
  top.upSubst = top.downSubst;
}

aspect production forwardsTo
top::ProductionStmt ::= 'forwards' 'to' e::Expr ';'
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  
  e.downSubst = top.downSubst;
  errCheck1.downSubst = e.upSubst;
  top.upSubst = errCheck1.upSubst;

  errCheck1 = check(e.typerep, top.frame.signature.outputElement.typerep);
  top.errors <- if errCheck1.typeerror
                then [err(e.location, "Forward's expected type is " ++ errCheck1.rightpp ++ ", but the actual type supplied is " ++ errCheck1.leftpp)]
                else [];
}

aspect production forwardingWith
top::ProductionStmt ::= 'forwarding' 'with' '{' inh::ForwardInhs '}' ';'
{
  inh.downSubst = top.downSubst;
  top.upSubst = inh.upSubst;
}

aspect production forwardInhsOne
top::ForwardInhs ::= lhs::ForwardInh
{
  lhs.downSubst = top.downSubst;
  top.upSubst = lhs.upSubst;
}

aspect production forwardInhsCons
top::ForwardInhs ::= lhs::ForwardInh rhs::ForwardInhs
{
  lhs.downSubst = top.downSubst;
  rhs.downSubst = lhs.upSubst;
  top.upSubst = rhs.upSubst;
}

aspect production forwardInh
top::ForwardInh ::= lhs::ForwardLHSExpr '=' e::Expr ';'
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  lhs.downSubst = top.downSubst;
  e.downSubst = lhs.upSubst;
  errCheck1.downSubst = e.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(lhs.typerep, e.typerep);
  top.errors <- 
       if errCheck1.typeerror
       then [err(e.location, lhs.name ++ " has expected type " ++ errCheck1.leftpp
                              ++ ", but the expression has type " ++ errCheck1.rightpp)]
       else [];
}

aspect production forwardLhsExpr
top::ForwardLHSExpr ::= q::QNameAttrOccur
{
  top.upSubst = top.downSubst;
}

aspect production localAttributeDcl
top::ProductionStmt ::= 'local' 'attribute' a::Name '::' te::TypeExpr ';'
{
  top.upSubst = top.downSubst;
}

aspect production returnDef
top::ProductionStmt ::= 'return' e::Expr ';'
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e.downSubst = top.downSubst;
  errCheck1.downSubst = e.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e.typerep, top.frame.signature.outputElement.typerep);
  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Expected return type is " ++ errCheck1.rightpp ++ ", but the expression has actual type " ++ errCheck1.leftpp)]
       else [];
}

aspect production errorAttributeDef
top::ProductionStmt ::= msg::[Message] dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  e.downSubst = top.downSubst;
  top.upSubst = e.upSubst;
}

aspect production synthesizedAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e.downSubst = top.downSubst;
  errCheck1.downSubst = e.upSubst;
  top.upSubst = errCheck1.upSubst; 

  errCheck1 = check(attr.typerep, e.typerep);
  top.errors <-
    if errCheck1.typeerror
    then [err(top.location, "Attribute " ++ attr.name ++ " has type " ++ errCheck1.leftpp ++ " but the expression being assigned to it has type " ++ errCheck1.rightpp)]
    else [];
}

aspect production inheritedAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e.downSubst = top.downSubst;
  errCheck1.downSubst = e.upSubst;
  top.upSubst = errCheck1.upSubst; 

  errCheck1 = check(attr.typerep, e.typerep);
  top.errors <-
    if errCheck1.typeerror
    then [err(top.location, "Attribute " ++ attr.name ++ " has type " ++ errCheck1.leftpp ++ " but the expression being assigned to it has type " ++ errCheck1.rightpp)]
    else [];
}

aspect production childDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.errors <- if top.typerep.isDecorable then []
                else [err(top.location, "Inherited attributes can only be defined on (undecorated) nonterminals.")];
}

aspect production localDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.errors <- if top.typerep.isDecorable then []
                else [err(top.location, "Inherited attributes can only be defined on (undecorated) nonterminals.")];
}

aspect production localValueDef
top::ProductionStmt ::= val::Decorated QName  e::Expr
{
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e.downSubst = top.downSubst;
  errCheck1.downSubst = e.upSubst;
  top.upSubst = errCheck1.upSubst;

  errCheck1 = check(e.typerep, val.lookupValue.typerep);
  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Local " ++ val.name ++ " has type " ++ errCheck1.rightpp ++ " but the expression being assigned to it has type " ++ errCheck1.leftpp)]
       else [];
}

aspect production errorValueDef
top::ProductionStmt ::= val::Decorated QName  e::Expr
{
  e.downSubst = top.downSubst;
  top.upSubst = e.upSubst;
}

