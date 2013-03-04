grammar silver:extension:deprecation;

import silver:definition:type:syntax;

terminal Function_tkwd    'Function'   lexer classes {TYPE,RESERVED};
terminal Production_tkwd  'Production' lexer classes {TYPE,RESERVED};

concrete production prodType
top::Type ::= 'Production' '(' sig::Signature ')'
{
  top.errors <- [wrn(top.location, "'Production' keyword in types is no longer necessary. Remove it.")];
  forwards to funType($2, sig, $4, location=top.location);
}
concrete production funTypeLegacy
top::Type ::= 'Function' '(' sig::Signature ')'
{
  top.errors <- [wrn(top.location, "'Function' keyword in types is no longer necessary. Remove it.")];
  forwards to funType($2, sig, $4, location=top.location);
}

