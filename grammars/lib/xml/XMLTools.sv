grammar lib:xml;

exports lib:xml:ast; -- don't force users to import anything but lib:xml.

import lib:xml:ast;

{--
 - WARNING: this is buggy! we're parsing a file without demanding an IO token!!
 - WILL CHANGE IN THE FUTURE.
 -}
function parseXMLFileN
ParseResult<XMLDocument> ::= filename::String
{
  return error("parseXMLFileN not yet implemented");
} foreign {
  "java" : return "common.rawlib.RawXML.parseXMLFileN(%filename%)";
}

