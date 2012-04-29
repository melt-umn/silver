grammar silver:definition:flow:env;

attribute flowDefs occurs on ProductionBody, ProductionStmts, ProductionStmt;


aspect production defaultProductionBody
top::ProductionBody ::= stmts::ProductionStmts
{
  top.flowDefs = stmts.flowDefs;
}

----

aspect production productionStmtsNone
top::ProductionStmts ::= 
{
  top.flowDefs = [];
}

aspect production productionStmts
top::ProductionStmts ::= stmt::ProductionStmt
{
  top.flowDefs = stmt.flowDefs;
}

aspect production productionStmtsCons
top::ProductionStmts ::= h::ProductionStmt  t::ProductionStmts
{
  top.flowDefs = h.flowDefs ++ t.flowDefs;
}

aspect production productionStmtsAppend
top::ProductionStmts ::= h::ProductionStmts  t::ProductionStmts
{
  top.flowDefs = h.flowDefs ++ t.flowDefs;
}

----

aspect production productionStmtAppend
top::ProductionStmt ::= h::ProductionStmt  t::ProductionStmt
{
  top.flowDefs = h.flowDefs ++ t.flowDefs;
}

aspect default production
top::ProductionStmt ::=
{
  top.flowDefs = [];
}

----

aspect production synthesizedAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  top.flowDefs = [synEq(top.signature.fullName, attr.lookupAttribute.fullName)];
}


