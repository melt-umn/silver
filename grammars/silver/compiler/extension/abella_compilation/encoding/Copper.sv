grammar silver:compiler:extension:abella_compilation:encoding;


aspect production blockStmt
top::ProductionStmt ::= '{' stmts::ProductionStmts '}'
{
  top.synAttrEqInfo := [];
  top.inhAttrEqInfo := [];
}

aspect production ifElseStmt
top::ProductionStmt ::= 'if' '(' condition::Expr ')' th::ProductionStmt 'else' el::ProductionStmt
{
  top.synAttrEqInfo := [];
  top.inhAttrEqInfo := [];
}

