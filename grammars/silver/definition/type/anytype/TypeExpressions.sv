grammar silver:definition:type:anytype;
import silver:definition:core;
import silver:definition:env;

terminal AnyType_kwd /AnyType/ lexer classes {KEYWORD};

synthesized attribute isAnyType :: Boolean;
attribute isAnyType occurs on TypeRep;

concrete production anyType
top::Type ::= 'AnyType'
{
  top.pp = "AnyType";
  top.location = loc(top.file, $1.line, $1.column);
  top.typerep = anyTypeTypeRep();
  top.errors := [];
  top.warnings := [];
}

function anyTypeTypeRep
Decorated TypeRep ::= 
{
  return decorate i_anyTypeTypeRep() with {};
}

abstract production i_anyTypeTypeRep
top::TypeRep ::= 
{
  top.unparse = "anytype";
  top.typeName = "AnyType";
  top.isAnyType = true ;
 
  top.typeEquals = anyTypeEquals;
  forwards to i_defaultTypeRep();
}


abstract production anyTypeEquals
top::TypeEquals ::= t1::Decorated TypeRep t2::Decorated TypeRep
{
 top.bValue = t1.isAnyType && t2.isAnyType ;
}

aspect production i_topTypeRep
top::TypeRep ::= 
{
  top.isAnyType = true;
}

aspect production i_defaultTypeRep
top::TypeRep ::= 
{
  top.isAnyType = false;
}
