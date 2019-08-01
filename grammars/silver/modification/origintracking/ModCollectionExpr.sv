imports silver:modification:collection;

aspect production synAppendColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  e.isRuleRoot = true;
  e.originsRules = [makeRuleLocNote(top, attr.name, attr.location)];
}

aspect production synBaseColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  e.isRuleRoot = true;
  e.originsRules = [makeRuleLocNote(top, attr.name, attr.location)];
}

aspect production inhBaseColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  e.isRuleRoot = true;
  e.originsRules = [makeRuleLocNote(top, attr.name, attr.location)];
}

aspect production inhAppendColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  e.isRuleRoot = true;
  e.originsRules = [makeRuleLocNote(top, attr.name, attr.location)];
}
