grammar lib:xml:test;

import lib:xml;
import lib:xml:foreigntypes;

function main
IOVal<Integer> ::= args::[String] ioin::IO
{

  local attribute pr :: ParseResult<XMLDocument>;
  pr = parseXMLFileN(head(args));
  
  local attribute io1:: IO;
  io1 = print(pr.parseTree.xmlUnparse ++ "\n\n", print( hackUnparse(pr) ++ "\n\n", ioin ));
  
  local attribute doc :: XML_Document;
  doc = parseXMLFileF("books.xml").parseTree;
  
  local attribute io2 :: IO;
  io2 = print( hackUnparse(nodeListXPathQueryN("//book[author=\"Neal Stephenson\"]", doc)) ++ "\n\n", io1);
  
  local attribute io3 :: IO;
  io3 = print( xmlDocumentF2String(doc) ++ "\n\n", io2);
  
  local attribute io4 :: IO;
  io4 = print( hackUnparse(xmlNodeListF2N(nodeListXPathReQueryF("//book[title=\"Snow Crash\"]", head(nodeListF2NPartial(nodeListXPathQueryF("//book[author=\"Neal Stephenson\"]", doc)))))) ++ "\n\n", io3);
  
  return ioval( io4, 0);

}
