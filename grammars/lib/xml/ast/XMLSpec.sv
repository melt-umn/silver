grammar lib:xml:ast;

-- Author: August Schwerdfeger for Adventium Enterprises LLC, 2011.

synthesized attribute xmlAttributeList :: [XMLAttribute];
synthesized attribute xmlAttributeValue :: String;
synthesized attribute xmlAttributes :: XMLAttributeList;
synthesized attribute xmlDTD :: XMLDocumentType;
synthesized attribute xmlName :: String;
synthesized attribute xmlSubNodeList :: [XMLNode];
synthesized attribute xmlSubNodes :: XMLNodeList;
synthesized attribute xmlText :: String;

nonterminal XMLDocument with xmlSubNodes, xmlDTD, xmlText;
nonterminal XMLDocumentType with xmlText, xmlName, xmlSubNodes;

nonterminal XMLNodeList with xmlSubNodeList, xmlText;
nonterminal XMLNode with xmlName, xmlAttributes, xmlSubNodes, xmlText;

nonterminal XMLAttributeList with xmlAttributeList;
nonterminal XMLAttribute with xmlName, xmlAttributeValue;

nonterminal XMLElement with xmlName, xmlAttributes, xmlSubNodes, xmlText;
nonterminal XMLText with xmlText;

-- XMLAttribute

abstract production xmlAttribute
top::XMLAttribute ::= name::String value::String
{
   top.xmlName = name;
   top.xmlAttributeValue = value;
}

-- XMLAttributeList

abstract production xmlAttributeListBuilder
top::XMLAttributeList ::= n::[XMLAttribute]
{
   top.xmlAttributeList = n;
}

-- XMLDocument

abstract production xmlDocument
top::XMLDocument ::= xmlDTD::XMLDocumentType elements::XMLNodeList
{
   top.xmlDTD = xmlDTD;
   top.xmlSubNodes = elements;
   top.xmlText = xmlDTD.xmlText ++ elements.xmlText;
}

-- XMLDocumentType

abstract production xmlDocumentType
top::XMLDocumentType ::= xmlDTDName::String entities::XMLNodeList
{
   top.xmlName = xmlDTDName;
   top.xmlSubNodes = entities;
   top.xmlText = xmlDTDName ++ entities.xmlText;
}

abstract production xmlNoDocumentType
top::XMLDocumentType ::=
{
   top.xmlName = "";
   top.xmlSubNodes = xmlNodeListBuilder([]);
   top.xmlText = "";
}

--XMLElement

abstract production xmlElement
top::XMLElement ::= name::String attributes::XMLAttributeList elements::XMLNodeList
{
   top.xmlName = name;
   top.xmlAttributes = attributes;
   top.xmlSubNodes = elements;
   top.xmlText = elements.xmlText;
}

-- XMLNode

function getXMLNodeText
String ::= n::XMLNode
{
   return n.xmlText;
}

abstract production xmlNodeElement
top::XMLNode ::= e::XMLElement
{
   top.xmlName = e.xmlName;
   top.xmlAttributes = e.xmlAttributes;
   top.xmlSubNodes = e.xmlSubNodes;
   top.xmlText = e.xmlText;
}

abstract production xmlNodeText
top::XMLNode ::= t::XMLText
{
   top.xmlName = "__text__";
   top.xmlAttributes = xmlAttributeListBuilder([]);
   top.xmlSubNodes = xmlNodeListBuilder([]);
   top.xmlText = t.xmlText;
}

-- XMLNodeList

abstract production xmlNodeListBuilder
top::XMLNodeList ::= n::[XMLNode]
{
   top.xmlSubNodeList = n;
   top.xmlText = implode("",map(getXMLNodeText,n));
}

-- XMLText

abstract production xmlTextBuilder
top::XMLText ::= t::String
{
   top.xmlText = t;
}
