grammar silver:definition:flow:env;

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  top.flowDefs = 
    if null(body.uniqueSignificantExpression) then []
    else [fwdEq(fName)];
}

