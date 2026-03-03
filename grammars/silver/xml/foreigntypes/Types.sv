grammar silver:xml:foreigntypes;

import silver:xml:ast;

type XML_Document foreign;
type XML_NodeList foreign;
type XML_Node foreign;


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
fun nodeListXPathQueryN XMLNodeList ::= q::String doc::XML_Document =
  xmlNodeListF2N(nodeListXPathQueryF(q, doc));

-- FOREIGN DOM response
function nodeListXPathQueryF
XML_NodeList ::= q::String doc::XML_Document
{
  return error("nodeListXPathQueryN not yet implemented");
} foreign {
  "java" : return "common.rawlib.RawXML.xpathQueryNodeSet(%doc%, %q%.toString(), null)";
}

function stringXPathQuery
String ::= q::String doc::XML_Document
{
  return error("stringXPathQuery not yet implemented");
} foreign {
  "java" : return "common.rawlib.RawXML.xpathQueryString(%doc%, %q%.toString(), null)";
}

-- REQUERYING a previous q result...
function nodeListXPathReQueryF
XML_NodeList ::= q::String doc::XML_Node
{
  return error("nodeListXPathReQueryF not yet implemented");
} foreign {
  "java" : return "common.rawlib.RawXML.xpathQueryNodeSet(%doc%, %q%.toString(), null)";
}
function stringXPathReQuery
String ::= q::String doc::XML_Node
{
  return error("stringXPathReQuery not yet implemented");
} foreign {
  "java" : return "common.rawlib.RawXML.xpathQueryString(%doc%, %q%.toString(), null)";
}

----- namespace variants ------------------------------------------------------

-- FOREIGN DOM response
function nodeListXPathQueryFns
XML_NodeList ::= q::String ns::[Pair<String String>] doc::XML_Document
{
  return error("nodeListXPathQueryN not yet implemented");
} foreign {
  "java" : return "common.rawlib.RawXML.xpathQueryNodeSet(%doc%, %q%.toString(), %ns%)";
}

function stringXPathQueryns
String ::= q::String ns::[Pair<String String>] doc::XML_Document
{
  return error("stringXPathQuery not yet implemented");
} foreign {
  "java" : return "common.rawlib.RawXML.xpathQueryString(%doc%, %q%.toString(), %ns%)";
}

-- REQUERYING a previous q result...
function nodeListXPathReQueryFns
XML_NodeList ::= q::String ns::[Pair<String String>] doc::XML_Node
{
  return error("nodeListXPathReQueryF not yet implemented");
} foreign {
  "java" : return "common.rawlib.RawXML.xpathQueryNodeSet(%doc%, %q%.toString(), %ns%)";
}
function stringXPathReQueryns
String ::= q::String ns::[Pair<String String>] doc::XML_Node
{
  return error("stringXPathReQuery not yet implemented");
} foreign {
  "java" : return "common.rawlib.RawXML.xpathQueryString(%doc%, %q%.toString(), %ns%)";
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
  "java" : return "common.rawlib.RawXML.documentN2F((silver.xml.ast.NXMLDocument)%doc%)";
}

function xmlDocumentF2String
String ::= doc::XML_Document
{
  return error("xmlDocumentF2String not yet implemented");
} foreign {
  "java" : return "common.rawlib.RawXML.documentF2String((org.w3c.dom.Document)%doc%)";
}

fun xmlDocumentN2String String ::= doc::XMLDocument =
  xmlDocumentF2String(xmlDocumentN2F(doc));
