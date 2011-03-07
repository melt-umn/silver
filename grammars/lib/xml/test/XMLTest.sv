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
  
  -- the books document now specifies a (dummy) namespace for some nodes
  -- So we MUST create this resolver, and use SOME prefix to be able to refer to them!
  local attribute mynamespaces :: [Pair<String String>];
  mynamespaces = [pair("x", "http://www.asdfexample.com/books")];
  
  local attribute io2 :: IO;
  io2 = print( hackUnparse(xmlNodeListF2N(nodeListXPathQueryFns("//x:book[x:author=\"Neal Stephenson\"]", mynamespaces, doc))) ++ "\n\n", io1);
  
  local attribute io3 :: IO;
  io3 = print( xmlDocumentF2String(doc) ++ "\n\n", io2);
  
  local attribute io4 :: IO;
  io4 = print( hackUnparse(xmlNodeListF2N(nodeListXPathReQueryFns("//x:book[x:title=\"Snow Crash\"]", mynamespaces, head(nodeListF2NPartial(nodeListXPathQueryFns("//x:book[x:author=\"Neal Stephenson\"]", mynamespaces, doc)))))) ++ "\n\n", io3);
  
  return ioval( io4, 0);

}
