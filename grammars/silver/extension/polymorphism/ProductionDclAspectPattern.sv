grammar silver:extension:polymorphism;

import silver:base;
import silver:extension:patternmatching;

aspect production plmProdDcl
d::AGDcl ::= a::Abstract_kwd p::Production_kwd  pd::Id_t ps::TypeParams
	s::PlmProductionSignature body::ProductionBody
{
  local attribute ns :: ProductionSignature;
  ns = s.nSig;
  ns.env = d.env;

  body.isAspect = false; 
  body.lhsName_down = ns.lhsName_up;
  body.rhsListExpr = ns.genRhsListExpr;
  body.prodName = pd ;
}



