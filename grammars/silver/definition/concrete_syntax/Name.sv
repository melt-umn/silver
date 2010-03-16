grammar silver:definition:concrete_syntax;
import silver:definition:core;

terminal Parse_kwd /parse/ lexer classes {KEYWORD};

concrete production nameParse
top::Name ::= 'parse'
{
   forwards to nameId(terminal(Id_t, "parse", $1)); 
}
