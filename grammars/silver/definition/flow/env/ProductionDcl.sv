grammar silver:definition:flow:env;

import silver:modification:defaultattr;

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  top.flowDefs = body.flowDefs ++ 
    if null(body.uniqueSignificantExpression)
    then [prodFlowDef(namedSig.outputElement.typerep.typeName, fName)]
    else [fwdEq(fName)];
}

aspect production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
{
  top.flowDefs = body.flowDefs;
}

------- Default attrs hack sorta

aspect production aspectDefaultProduction
top::AGDcl ::= 'aspect' 'default' 'production' 
               lhs::Name '::' _ '::=' body::ProductionBody 
{
  top.flowDefs = body.flowDefs;
}

