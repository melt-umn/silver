grammar silver:composed:idetest;

import silver:definition:core;
import silver:definition:concrete_syntax; --concreteProductionDcl
import silver:modification:defaultattr; --aspectDefaultProduction

synthesized attribute foldableRanges :: [Location] with ++;
attribute foldableRanges occurs on AGDcl, AGDcls;

-- AGDcls
aspect production nilAGDcls
top::AGDcls ::=
{
  top.foldableRanges := [];
}

aspect production consAGDcls
top::AGDcls ::= h::AGDcl t::AGDcls
{
  top.foldableRanges := h.foldableRanges ++ t.foldableRanges;
}

-- AGDcl
aspect production emptyAGDcl
top::AGDcl ::=
{
  top.foldableRanges := [];
}

aspect production appendAGDcl
top::AGDcl ::= h::AGDcl t::AGDcl
{
  top.foldableRanges := h.foldableRanges ++ t.foldableRanges;
}

aspect default production
top::AGDcl ::=
{
  top.foldableRanges := [];
}

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 
{
  top.foldableRanges := [body.location];
}

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  top.foldableRanges := [body.location];
}

aspect production concreteProductionDcl
top::AGDcl ::= 'concrete' 'production' id::Name ns::ProductionSignature pm::ProductionModifiers body::ProductionBody
{
  top.foldableRanges := [body.location];
}

aspect production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
{
  top.foldableRanges := [body.location];
}

aspect production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody 
{
  top.foldableRanges := [body.location];
}

aspect production aspectDefaultProduction
top::AGDcl ::= 'aspect' 'default' 'production' lhs::Name '::' _ '::=' body::ProductionBody 
{
  top.foldableRanges := [body.location];
}
