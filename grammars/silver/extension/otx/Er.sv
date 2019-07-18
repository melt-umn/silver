
autocopy attribute isRuleRoot :: Boolean occurs on Expr;

-- aspect default production
-- top::Expr ::=
-- {
--   top.isRuleRoot = false;
-- }

aspect production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
{
  e1.isRuleRoot = false;
  -- autocopy to e1 and e2
}

aspect production returnDef
top::ProductionStmt ::= 'return' e::Expr ';'
{
  e.isRuleRoot = true;
}

aspect production localAttributeDcl
top::ProductionStmt ::= 'local' 'attribute' a::Name '::' te::TypeExpr ';'
{
}

aspect production productionAttributeDcl
top::ProductionStmt ::= 'production' 'attribute' a::Name '::' te::TypeExpr ';'
{
}

aspect production forwardsTo
top::ProductionStmt ::= 'forwards' 'to' e::Expr ';'
{
  e.isRuleRoot = true;
}

aspect production attributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::QNameAttrOccur '=' e::Expr ';'
{
  e.isRuleRoot = true;
}