grammar silver:definition:type:io;
import silver:definition:core;
import silver:definition:env;

terminal IO_kwd /IO/ lexer classes {KEYWORD};

synthesized attribute  isIO :: Boolean;
attribute isIO occurs on TypeRep;

concrete production ioType
top::Type ::= 'IO'
{
  top.pp = "IO";
  top.location = loc(top.file, $1.line, $1.column);
  top.typerep = ioTypeRep();

  top.errors := [];
  top.warnings := [];
}


function ioTypeRep
Decorated TypeRep ::= 
{
  return decorate i_ioTypeRep() with {};
}

abstract production i_ioTypeRep
top::TypeRep ::= 
{
  top.unparse = "io";
  top.typeName = "IO";
  top.isIO = true;

  top.typeEquals = ioEquals;
  forwards to i_defaultTypeRep();
}

abstract production ioEquals
top::TypeEquals ::= t1::Decorated TypeRep t2::Decorated TypeRep
{
    top.bValue = t1.typeName == "TOP" || t2.typeName == "TOP" || (t1.isIO && t2.isIO);
}


aspect production i_defaultTypeRep
top::TypeRep ::= 
{
  top.isIO = false;
}
