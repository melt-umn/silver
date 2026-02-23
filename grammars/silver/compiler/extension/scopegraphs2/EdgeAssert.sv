grammar silver:compiler:extension:scopegraphs2;

--

-- s -[ lex ]-> s2;
production edgeAssertionLocal
top::ProductionStmt ::= src::QName lab::String tgt::Expr
{
  forwards to
    Silver_ProductionStmt {
      $QName{^src}.$QName{qName(lab)} <- [$Expr{^tgt}];
    };
}

-- n.s1 -[ lex ]-> s2;
production edgeAssertionInh
top::ProductionStmt ::= dl::DefLHS attr::QNameAttrOccur lab::String tgt::Expr
{
  forwards to
    Silver_ProductionStmt {
      $QName{qName(dl.name)}.$QName{qnScopeAttr(attr.name, lab)} <- [$Expr{^tgt}];
    };
}
