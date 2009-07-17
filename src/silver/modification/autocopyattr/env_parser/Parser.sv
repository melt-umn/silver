grammar silver:modification:autocopyattr:env_parser;
import silver:modification:autocopyattr;
import silver:definition:env;
import silver:definition:env:parser;

terminal AutoCopyTerm 'autocopy' lexer classes {C_1}, precedence = 1;

concrete production aTypeRepCollection
top::aTypeRep ::= 'autocopy' '(' t::aTypeRep ')'{
  top.typerep = copyTypeRep(t.typerep);
}

