grammar silver:compiler:extension:scopegraphs2;

--

-- s -[ lex ]-> s2;
production edgeAssertionLocal
top::ProductionStmt ::= src::QName lab::String tgt::Expr
{
  nondecorated local contrib::ProductionStmt = 
    Silver_ProductionStmt{
      $QName{qnScopeAttr(src.name, lab)} <- [$Expr{^tgt}];
    }
  ;

  forwards to contrib;
}

-- n.s1 -[ lex ]-> s2;
production edgeAssertionInh
top::ProductionStmt ::= dl::DefLHS attr::QNameAttrOccur lab::String tgt::Expr
{
  forwards to
    productionStmtAppend( 
      Silver_ProductionStmt {
        $QName{qName(dl.name)} . $QName{qnScopeAttr(attr.name, lab)} <-
          [$Expr{^tgt}];
      },
      -- base equation. may be multiple of these. todo: need a way to only use one
      Silver_ProductionStmt {
        $QName{qName(dl.name)} . $QName{qnScopeAttr(attr.name, lab)} := [];
      }
    )
  ;
}

--

fun qnScopeAttr QName ::= s::String l::String = 
  qName(s ++ "_" ++ l)
; 
