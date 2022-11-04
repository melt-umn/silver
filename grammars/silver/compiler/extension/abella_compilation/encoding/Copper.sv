grammar silver:compiler:extension:abella_compilation:encoding;


aspect production blockStmt
top::ProductionStmt ::= '{' stmts::ProductionStmts '}'
{
  stmts.treeTerm = top.treeTerm;
  stmts.nodetreeTerm = top.nodetreeTerm;

  top.synAttrEqInfo := [];
  top.inhAttrEqInfo := [];
}

aspect production ifElseStmt
top::ProductionStmt ::= 'if' '(' condition::Expr ')' th::ProductionStmt 'else' el::ProductionStmt
{
  th.treeTerm = top.treeTerm;
  el.treeTerm = top.treeTerm;
  th.nodetreeTerm = top.nodetreeTerm;
  el.nodetreeTerm = top.nodetreeTerm;

  top.synAttrEqInfo := [];
  top.inhAttrEqInfo := [];
}

