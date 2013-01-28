grammar silver:extension:deprecation;

import silver:definition:type:syntax;

terminal Function_tkwd		'Function'	lexer classes {TYPE_KEYWORD};
terminal Production_tkwd	'Production'	lexer classes {TYPE_KEYWORD};

concrete production prodType
top::Type ::= 'Production' '(' sig::Signature ')'
{
  top.errors <- [wrn(forward.location, "'Production' keyword in types is no longer necessary. Remove it.")];
  forwards to funType($2, sig, $4);
}
concrete production funTypeLegacy
top::Type ::= 'Function' '(' sig::Signature ')'
{
  top.errors <- [wrn(forward.location, "'Function' keyword in types is no longer necessary. Remove it.")];
  forwards to funType($2, sig, $4);
}

