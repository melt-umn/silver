grammar lib:xml:test;

import lib:xml;
import lib:xml:foreigntypes;

function main
IOVal<Integer> ::= args::[String] ioin::IO
{

  local attribute pr :: ParseResult<XMLDocument>;
  pr = parseXMLFileAsAST(head(args));
  
  local attribute io1:: IO;
  io1 = print(pr.parseTree.xmlUnparse ++ "\n\n", print( hackUnparse(pr) ++ "\n\n", ioin ));
  
  local attribute doc :: XML_Document;
  doc = parseXMLFileAsForeignType("books.xml").parseTree;
  
  local attribute io2 :: IO;
  io2 = print( hackUnparse(nodeSetXPathQuery("//book[author=\"Neal Stephenson\"]", doc)) ++ "\n\n", io1);
  
  return ioval( io2, 0);

}
