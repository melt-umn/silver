grammar lib:xml:foreigntypes;

import lib:xml:ast;

nonterminal foreign type XML_Document;
nonterminal foreign type XML_NodeList;
nonterminal foreign type XML_Node;


{--
 - WARNING: this is buggy! we're parsing a file without demanding an IO token!!
 - WILL CHANGE IN THE FUTURE.
 -}
function parseXMLFileF
ParseResult<XML_Document> ::= filename::String
{
  return error("parseXMLFileF not yet implemented");
} foreign {
  "java" : return "common.rawlib.RawXML.parseXMLFileF(%filename%)";
}

-- NATIVE AST response. This is just a helper for 
function nodeListXPathQueryN
XMLNodeList ::= query::String doc::XML_Document
{
  return xmlNodeListF2N(nodeListXPathQueryF(query, doc));
}

-- FOREIGN DOM response
function nodeListXPathQueryF
XML_NodeList ::= query::String doc::XML_Document
{
  return error("nodeListXPathQueryN not yet implemented");
} foreign {
  "java" : return "common.rawlib.RawXML.xpathQueryNodeSet(%doc%, %query%.toString(), null)";
}

function stringXPathQuery
String ::= query::String doc::XML_Document
{
  return error("stringXPathQuery not yet implemented");
} foreign {
  "java" : return "common.rawlib.RawXML.xpathQueryString(%doc%, %query%.toString(), null)";
}

-- REQUERYING a previous query result...
function nodeListXPathReQueryF
XML_NodeList ::= query::String doc::XML_Node
{
  return error("nodeListXPathReQueryF not yet implemented");
} foreign {
  "java" : return "common.rawlib.RawXML.xpathQueryNodeSet(%doc%, %query%.toString(), null)";
}
function stringXPathReQuery
String ::= query::String doc::XML_Node
{
  return error("stringXPathReQuery not yet implemented");
} foreign {
  "java" : return "common.rawlib.RawXML.xpathQueryString(%doc%, %query%.toString(), null)";
}

----- namespace variants ------------------------------------------------------

-- FOREIGN DOM response
function nodeListXPathQueryFns
XML_NodeList ::= query::String ns::[Pair<String String>] doc::XML_Document
{
  return error("nodeListXPathQueryN not yet implemented");
} foreign {
  "java" : return "common.rawlib.RawXML.xpathQueryNodeSet(%doc%, %query%.toString(), %ns%)";
}

function stringXPathQueryns
String ::= query::String ns::[Pair<String String>] doc::XML_Document
{
  return error("stringXPathQuery not yet implemented");
} foreign {
  "java" : return "common.rawlib.RawXML.xpathQueryString(%doc%, %query%.toString(), %ns%)";
}

-- REQUERYING a previous query result...
function nodeListXPathReQueryFns
XML_NodeList ::= query::String ns::[Pair<String String>] doc::XML_Node
{
  return error("nodeListXPathReQueryF not yet implemented");
} foreign {
  "java" : return "common.rawlib.RawXML.xpathQueryNodeSet(%doc%, %query%.toString(), %ns%)";
}
function stringXPathReQueryns
String ::= query::String ns::[Pair<String String>] doc::XML_Node
{
  return error("stringXPathReQuery not yet implemented");
} foreign {
  "java" : return "common.rawlib.RawXML.xpathQueryString(%doc%, %query%.toString(), %ns%)";
}

-------------------------------------------------------------------------------


function xmlNodeListF2N
XMLNodeList ::= nl::XML_NodeList
{
  return error("xmlNodeListF2N not yet implemented");
} foreign {
  "java" : return "common.rawlib.RawXML.nodeListF2N((org.w3c.dom.NodeList)%nl%)";
}

function nodeListF2NPartial
[XML_Node] ::= nl::XML_NodeList
{
  return error("nodeListF2NPartial not yet implemented");
} foreign {
  "java" : return "common.rawlib.RawXML.nodeListF2NPartial((org.w3c.dom.NodeList)%nl%)";
}

-- TODO: xmlNodeListN2F? Can accomplish for now by wrapping in a document, perhaps.
-- TODO: Node conversions?

function xmlDocumentF2N
XMLDocument ::= doc::XML_Document
{
  return error("xmlDocumentF2N not yet implemented");
} foreign {
  "java" : return "common.rawlib.RawXML.documentF2N((org.w3c.dom.Document)%doc%)";
}

function xmlDocumentN2F
XML_Document ::= doc::XMLDocument
{
  return error("xmlDocumentN2F not yet implemented");
} foreign {
  "java" : return "common.rawlib.RawXML.documentN2F((lib.xml.ast.NXMLDocument)%doc%)";
}

function xmlDocumentF2String
String ::= doc::XML_Document
{
  return error("xmlDocumentF2String not yet implemented");
} foreign {
  "java" : return "common.rawlib.RawXML.documentF2String((org.w3c.dom.Document)%doc%)";
}

function xmlDocumentN2String
String ::= doc::XMLDocument
{
  return xmlDocumentF2String(xmlDocumentN2F(doc));
}
