grammar silver:compiler:extension:scopegraphs2;

--

-- s -[ lex ]-> s2;
production edgeAssertionLocal
top::ProductionStmt ::= src::QName lab::String tgt::Expr
{
  forwards to
    Silver_ProductionStmt {
      local attribute foo::Integer;
    }
  ;
}

-- n.s1 -[ lex ]-> s2;
production edgeAssertionInh
top::ProductionStmt ::= dl::DefLHS attr::QNameAttrOccur lab::String tgt::Expr
{
  forwards to
    Silver_ProductionStmt {
      local attribute foo::Integer;
    }
  ;
}
