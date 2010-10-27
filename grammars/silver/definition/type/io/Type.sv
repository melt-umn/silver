grammar silver:definition:type:io;
import silver:definition:core;
import silver:definition:type:syntax;
import silver:definition:type;
import silver:definition:env;

terminal IO_kwd /IO/ lexer classes {KEYWORD};

concrete production ioType
top::Type ::= 'IO'
{
  top.pp = "IO";
  top.location = loc(top.file, $1.line, $1.column);
  
  top.typerep = ioTypeExp();

  top.errors := [];
  
  top.lexicalTypeVariables = [];
}

