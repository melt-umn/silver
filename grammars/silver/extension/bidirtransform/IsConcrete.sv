grammar silver:extension:bidirtransform;

inherited attribute isConcrete::Boolean occurs on NamedSignature;

------
--
-- This probably doesn't work! concreteProductionDcl forwards
-- to productionDcl (where abstract productions are formed),
-- so if the named signature used in both is the same, we're
-- defining it twice as opposing values?
--
-----

aspect production concreteProductionDcl
top::AGDcl ::= 'concrete' 'production' id::Name ns::ProductionSignature pm::ProductionModifiers body::ProductionBody
{
    namedSig.isConcrete = true;
}

aspect production functionDclFFI
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 'foreign' '{' ffidefs::FFIDefs '}'
{
    namedSig.isConcrete = false;
}

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
    namedSig.isConcrete = false;
}

aspect production aspectDefaultProduction
top::AGDcl ::= 'aspect' 'default' 'production' 
               lhs::Name '::' te::TypeExpr '::=' body::ProductionBody 
{
    namedSig.isConcrete = false;
}