grammar lib:xml:foreigntypes;

import lib:xml:ast;

nonterminal foreign type XML_Document;


{--
 - WARNING: this is buggy! we're parsing a file without demanding an IO token!!
 - WILL CHANGE IN THE FUTURE.
 -}
function parseXMLFileAsForeignType
ParseResult<XML_Document> ::= filename::String
{
  return error("parseXMLFileAsForeignType not yet implemented");
} foreign {
  "java" : return "common.xml.Util.parseXMLFileToForeignType(%filename%)";
}


function nodeSetXPathQuery
XMLNodeList ::= query::String doc::XML_Document
{
  return error("nodeSetXPathQuery not yet implemented");
} foreign {
  "java" : return "common.xml.Util.nodeSetXPathQuery((org.w3c.dom.Document)%doc%, %query%.toString())";
}

