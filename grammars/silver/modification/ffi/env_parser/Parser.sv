grammar silver:modification:ffi:env_parser;

import silver:definition:core only grammarName, location, env;
import silver:definition:env:env_parser;
import silver:modification:ffi;
import silver:definition:env;

terminal FTTerm          'foreigntype'        lexer classes {C_1};


concrete production aTypeRepForeign
top::ITypeRep ::= 'foreigntype' '(' n::IName ',' ty::ITypeReps ')'
{
  top.typerep = foreignType(n.aname, ty.typereps);
}

