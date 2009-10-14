grammar silver:modification:patternmatching:copper;

import silver:definition:core;
import silver:definition:concrete_syntax;
import silver:modification:patternmatching;
import silver:translation:java:concrete_syntax:copper;

aspect production concreteProductionDclAction
top::AGDcl ::= 'concrete' 'production' id::Name ns::ProductionSignature body::ProductionBody 'action' acode::ActionCode_c
{
   body.prodName = id ;
   body.lhsName_down = ns.lhsName_up ;
   body.rhsListExpr = ns.genRhsListExpr ;
   body.isAspect = false ;
}

aspect production concreteProductionDclModifiersAction
top::AGDcl ::= 'concrete' 'production' id::Name ns::ProductionSignature pm::ProductionModifiers body::ProductionBody 'action' acode::ActionCode_c
{
   body.prodName = id ;
   body.lhsName_down = ns.lhsName_up ;
   body.rhsListExpr = ns.genRhsListExpr ;
   body.isAspect = false ;
}