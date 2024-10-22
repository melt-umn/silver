grammar silver:compiler:extension:deprecation;

concrete production emptyProductionBodySemi
top::ProductionBody ::= ';'
{
  top.unparse = ";";
  
  top.errors <- [wrnFromOrigin(top, "Terminating a production declaration with a semicolon is deprecated. Use {}.")];

  forwards to productionBody('{', productionStmtsNil(), '}');
}


