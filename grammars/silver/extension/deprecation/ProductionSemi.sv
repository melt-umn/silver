grammar silver:extension:deprecation;

concrete production emptyProductionBodySemi
top::ProductionBody ::= ';'
{
  top.pp = ";";
  
  top.errors <- [wrn(top.location, "Terminating a production declaration with a semicolon is deprecated. Use {}.")];

  forwards to productionBody('{', productionStmtsNil(location=top.location), '}', location=top.location);
}


