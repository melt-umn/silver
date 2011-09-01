
import silver:testing;
import lib:extcore;
import lib:xml;
import lib:xml:foreigntypes;
import stdlib;

global books :: XML_Document = parseXMLFileF("xml/books.xml").parseTree;

global smallxhtml :: XMLDocument = parseXMLFileN("xml/smallxhtml.xml").parseTree;

global mynamespaces :: [Pair<String String>] = [pair("x", "http://www.asdfexample.com/books")];

-- querying tests
global query1 :: XMLNodeList = xmlNodeListF2N(nodeListXPathQueryFns("//x:book[x:author=\"Neal Stephenson\"]", mynamespaces, books));

equalityTest ( indexOf("Snow Crash", query1.xmlText) != -1, true, Boolean, core_tests ) ;
equalityTest ( indexOf("Zodiac", query1.xmlText) != -1, true, Boolean, core_tests ) ;
equalityTest ( null(query1.xmlSubNodeList), false, Boolean, core_tests ) ;

-- namespace tests
global query2 :: XMLNodeList = xmlNodeListF2N(nodeListXPathQueryFns("//x:book[x:author=\"Neal Stephenson\"]", [], books));

equalityTest ( null(query2.xmlSubNodeList), true, Boolean, core_tests ) ;

global query3 :: XMLNodeList = xmlNodeListF2N(nodeListXPathQueryFns("//book[author=\"Neal Stephenson\"]", [], books));

equalityTest ( null(query3.xmlSubNodeList), true, Boolean, core_tests ) ;

-- requerying tests
global query4 :: XMLNodeList = xmlNodeListF2N(nodeListXPathReQueryFns("//x:book[x:title=\"Snow Crash\"]", mynamespaces, 
                                 head(nodeListF2NPartial(nodeListXPathQueryFns("//x:book[x:author=\"Neal Stephenson\"]", mynamespaces, books)))));

equalityTest ( length(query4.xmlSubNodeList), 1, Integer, core_tests ) ;

-- unparsing test
global mydoc :: XMLDocument = xmlDocument(xmlNoDocumentType(), xmlNodeListCons(
                         xmlNodeElement("foo", [], xmlNodeListCons(
                           xmlNodeElement("bar", [xmlAttribute("baz","&")], xmlNodeListNil()), xmlNodeListNil())),
                        xmlNodeListNil()));

global mydocpp :: String = xmlDocumentN2String(mydoc);

equalityTest(mydocpp, "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><foo><bar baz=\"&amp;\"/></foo>", String, core_tests ) ;

-- CDATA test
equalityTest( indexOf("getElementById", smallxhtml.xmlText) != -1, true, Boolean, core_tests ) ;

-- DOCTYPE
equalityTest( smallxhtml.xmlDTD.xmlName, "html", String, core_tests ) ;



