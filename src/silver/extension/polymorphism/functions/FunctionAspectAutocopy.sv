grammar silver:extension:polymorphism:functions;

import core;
import silver:base;
import silver:extension:autocopy ;
import silver:extension:functions;
import silver:extension:polymorphism;

aspect production plmFnDclParams
 d::AGDcl ::=
   f::Function_kwd id::Id_t ps::TypeParams
   s::FunctionSignature body::ProductionBody 
{
  body.isAspect = false;
}