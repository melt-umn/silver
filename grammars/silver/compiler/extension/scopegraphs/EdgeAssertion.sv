grammar silver:compiler:extension:scopegraphs;

---------------------
-- `s1 -[ lex ]-> s2`

abstract production edgeAssertionLocal
top::ProductionStmt ::= src::QName lab::String tgt::Expr
{
  --propagate config, grammarName, compiledGrammars, env, flowEnv;

  -- Names of inherited attributes corresponding to labels
  -- local labelNames::[String] = getLabelNames(top.sgEnv);

  -- todo: error checking

  forwards to
    Silver_ProductionStmt{
      $QName{qnScopeAttr(src.name, lab)} <- [$Expr{^tgt}];
    }
  ;
}

-----------------------
-- `n.s1 -[ lex ]-> s2`

abstract production edgeAssertionInh
top::ProductionStmt ::= dl::DefLHS attr::QNameAttrOccur lab::String tgt::Expr
{
  --propagate config, grammarName, compiledGrammars, frame, env, finalSubst, originRules, flowEnv;

  -- Names of inherited attributes corresponding to labels
  -- local labelNames::[String] = getLabelNames(top.sgEnv);

  -- todo: error checking

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
