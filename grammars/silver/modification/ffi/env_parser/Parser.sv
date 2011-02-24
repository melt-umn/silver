grammar silver:modification:ffi:env_parser;

import silver:definition:env:parser;
import silver:modification:ffi;
import silver:definition:env;

terminal FTTerm          'foreigntype'        lexer classes {C_1};


concrete production aTypeRepForeign
top::ITypeRep ::= 'foreigntype' '(' n::Name ')'
{
  top.typerep = foreignTypeExp(n.aname);
}

