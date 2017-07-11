grammar lib:xml:ast;

-- Authors: August Schwerdfeger for Adventium Enterprises LLC, 2011.
--          Ted Kaminski

nonterminal XMLDocument with xmlSubNodes, xmlDTD, xmlText, xmlUnparse;
nonterminal XMLDocumentType with xmlText, xmlName, xmlSubNodes, xmlUnparse;
nonterminal XMLNodeList with xmlSubNodeList, xmlText, xmlUnparse;
nonterminal XMLNode with xmlName, xmlAttributes, xmlSubNodes, xmlText, xmlUnparse;
nonterminal XMLAttribute with xmlName, xmlAttributeValue, xmlUnparse;

synthesized attribute xmlSubNodes :: XMLNodeList;
synthesized attribute xmlDTD :: XMLDocumentType;

synthesized attribute xmlName :: String;
synthesized attribute xmlText :: String;
synthesized attribute xmlAttributes :: [XMLAttribute];

synthesized attribute xmlAttributeValue :: String;

synthesized attribute xmlSubNodeList :: [XMLNode];

-- WARNING: this does not do proper escaping! Probably shouldn't be relied upon to generate valid XML!
synthesized attribute xmlUnparse :: String;

-- XMLDocument

abstract production xmlDocument
top::XMLDocument ::= xmlDTD::XMLDocumentType elements::XMLNodeList
{
   top.xmlDTD = xmlDTD;
   top.xmlSubNodes = elements;
   top.xmlText = xmlDTD.xmlText ++ elements.xmlText;
   top.xmlUnparse = xmlDTD.xmlUnparse ++ elements.xmlUnparse;
}

-- XMLDocumentType

abstract production xmlDocumentType
top::XMLDocumentType ::= xmlDTDName::String entities::XMLNodeList
{
   top.xmlName = xmlDTDName;
   top.xmlSubNodes = entities;
   top.xmlText = xmlDTDName ++ entities.xmlText;
   top.xmlUnparse = "<!DOCTYPE " ++ xmlDTDName ++ ">\n"; -- Yeah, does entities ever exist here?
}

abstract production xmlNoDocumentType
top::XMLDocumentType ::=
{
   top.xmlName = "";
   top.xmlSubNodes = xmlNodeListNil();
   top.xmlText = "";
   top.xmlUnparse = "";
}

-- XMLNodeList

abstract production xmlNodeListCons
top::XMLNodeList ::= h::XMLNode t::XMLNodeList
{
   top.xmlSubNodeList = h :: t.xmlSubNodeList;
   top.xmlText = h.xmlText ++ t.xmlText;
   top.xmlUnparse = h.xmlUnparse ++ t.xmlUnparse;
}

abstract production xmlNodeListNil
top::XMLNodeList ::=
{
   top.xmlSubNodeList = [];
   top.xmlText = "";
   top.xmlUnparse = "";
}

-- XMLNode

abstract production xmlNodeElement
top::XMLNode ::= name::String attributes::[XMLAttribute] elements::XMLNodeList
{
   top.xmlName = name;
   top.xmlAttributes = attributes;
   top.xmlSubNodes = elements;
   top.xmlText = elements.xmlText;
   top.xmlUnparse = "<" ++ name ++ " " ++ implode(" ", map(xmlUnparseAttr, attributes)) ++ ">" ++ elements.xmlUnparse ++ "</" ++ name ++ ">";
}

abstract production xmlNodeText
top::XMLNode ::= t::String
{
   top.xmlName = "#text"; -- Consistency with java (see org.w3c.dom.Node)
   top.xmlAttributes = [];
   top.xmlSubNodes = xmlNodeListNil();
   top.xmlText = t;
   top.xmlUnparse = t;
}

-- XMLAttribute

abstract production xmlAttribute
top::XMLAttribute ::= name::String value::String
{
   top.xmlName = name;
   top.xmlAttributeValue = value;
   top.xmlUnparse = name ++ "=\"" ++ value ++ "\"";
}

function xmlUnparseAttr
String ::= xa::XMLAttribute
{
   return xa.xmlUnparse;
}
