grammar silver:definition:type:gatherfreevars;

import silver:definition:core;
import silver:definition:env;

attribute lexicalTypeVariables occurs on ProductionBody, ProductionStmts, ProductionStmt;

aspect production defaultProductionBody
top::ProductionBody ::= stmts::ProductionStmts
{
  top.lexicalTypeVariables = stmts.lexicalTypeVariables;
}

aspect production productionStmtsNone
top::ProductionStmts ::= 
{
  top.lexicalTypeVariables = [];
}

aspect production productionStmts
top::ProductionStmts ::= stmt::ProductionStmt
{
  top.lexicalTypeVariables = stmt.lexicalTypeVariables;
}

aspect production productionStmtsCons
top::ProductionStmts ::= h::ProductionStmt t::ProductionStmts
{
  top.lexicalTypeVariables = makeSet(h.lexicalTypeVariables ++ t.lexicalTypeVariables);
}

aspect production productionStmtsAppend
top::ProductionStmts ::= h::ProductionStmts t::ProductionStmts
{
  top.lexicalTypeVariables = makeSet(h.lexicalTypeVariables ++ t.lexicalTypeVariables);
}

aspect production returnDef
top::ProductionStmt ::= 'return' e::Expr ';'
{
  top.lexicalTypeVariables = [];
}

aspect production localAttributeDcl
top::ProductionStmt ::= 'local' 'attribute' a::Name '::' te::Type ';'
{
  top.lexicalTypeVariables = te.lexicalTypeVariables;
}

aspect production productionAttributeDcl
top::ProductionStmt ::= 'production' 'attribute' a::Name '::' te::Type ';'
{
  top.lexicalTypeVariables = te.lexicalTypeVariables;
}

aspect production forwardsTo
top::ProductionStmt ::= 'forwards' 'to' e::Expr ';'
{
  top.lexicalTypeVariables = [];
}

aspect production forwardsToWith
top::ProductionStmt ::= 'forwards' 'to' e::Expr 'with' '{' inh::ForwardInhs '}' ';'
{
  top.lexicalTypeVariables = [];
}

aspect production forwardingWith
top::ProductionStmt ::= 'forwarding' 'with' '{' inh::ForwardInhs '}' ';'
{
  top.lexicalTypeVariables = [];
}

aspect production attributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::QName '=' e::Expr ';'
{
  top.lexicalTypeVariables = [];
}

aspect production errorAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  top.lexicalTypeVariables = [];
}

aspect production synthesizedAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  top.lexicalTypeVariables = [];
}

aspect production inheritedAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  top.lexicalTypeVariables = [];
}

aspect production valueDef
top::ProductionStmt ::= val::QName '=' e::Expr ';'
{
  top.lexicalTypeVariables = [];
}

aspect production errorValueDef
top::ProductionStmt ::= val::Decorated QName '=' e::Expr
{
  top.lexicalTypeVariables = [];
}

aspect production localValueDef
top::ProductionStmt ::= val::Decorated QName '=' e::Expr
{
  top.lexicalTypeVariables = [];
}

