grammar silver:compiler:extension:abella_compilation;


aspect production blockStmt
top::ProductionStmt ::= '{' stmts::ProductionStmts '}'
{
  top.attrEqInfo := [];
}

aspect production ifElseStmt
top::ProductionStmt ::= 'if' '(' condition::Expr ')' th::ProductionStmt 'else' el::ProductionStmt
{
  top.attrEqInfo := [];
}

