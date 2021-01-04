grammar silver:compiler:extension:deprecation;

import silver:compiler:definition:type:syntax;

terminal Function_tkwd    'Function'   lexer classes {TYPE,RESERVED};
terminal Production_tkwd  'Production' lexer classes {TYPE,RESERVED};

concrete production prodTypeExpr
top::TypeExpr ::= 'Production' '(' sig::Signature ')'
{
  top.errors <- [wrn(top.location, "'Production' keyword in types is no longer necessary. Remove it.")];
  forwards to funTypeExpr($2, sig, $4, location=top.location);
}
concrete production funTypeLegacy
top::TypeExpr ::= 'Function' '(' sig::Signature ')'
{
  top.errors <- [wrn(top.location, "'Function' keyword in types is no longer necessary. Remove it.")];
  forwards to funTypeExpr($2, sig, $4, location=top.location);
}

