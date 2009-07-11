grammar silver:translation:java:concrete_syntax:copper;
import silver:definition:env;
import silver:definition:core;
import silver:definition:type:anytype;

 
synthesized attribute isParserAttrType :: Boolean;
synthesized attribute parserAttrType :: String;

attribute isParserAttrType occurs on TypeRep;
attribute parserAttrType occurs on TypeRep;

aspect production i_integerTypeRep
top::TypeRep ::= 
{
   top.isParserAttrType = true;
   top.parserAttrType = "Integer";
}

aspect production i_stringTypeRep
top::TypeRep ::= 
{
   top.isParserAttrType = true;
   top.parserAttrType = "String";
}

aspect production i_anyTypeTypeRep
top::TypeRep ::= 
{
  top.isParserAttrType = true;
  top.parserAttrType = "AnyType";
}

aspect production i_defaultTypeRep
top::TypeRep ::=
{
  top.isParserAttrType = false;
  top.parserAttrType = "";
}

