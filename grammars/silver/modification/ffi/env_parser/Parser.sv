grammar silver:modification:ffi:env_parser;

import silver:definition:env:env_parser;
import silver:modification:ffi;
import silver:definition:env;

terminal FTTerm          'foreigntype'        lexer classes {C_1};


concrete production aTypeRepForeign
top::ITypeRep ::= 'foreigntype' '(' n::IName ',' ty::ITypeReps ')'
{
  top.typerep = foreignTypeExp(n.aname, ty.typereps);
}

