grammar silver:extension:doc:core;

aspect production annotationDcl
top::AGDcl ::= 'annotation' a::QName tl::BracketedOptTypeList '::' te::Type ';'
{

}

