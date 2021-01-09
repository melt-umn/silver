
import silver:testing;
import silver:langutil:pp;
import silver:util:deque as dq;

-- empty
equalityTest ( s"""""", "", String, silver_tests ) ;
-- water only
equalityTest ( s"""hello""", "hello", String, silver_tests ) ;

global aStrValue :: String = s"""
	$$""";
-- escape only
equalityTest ( s"""${aStrValue}""", "\n\t$", String, silver_tests ) ;
-- full expression
equalityTest ( s"""${toString(1)}""", "1", String, silver_tests ) ;
-- water-escape
equalityTest ( s"""hello${toString(1)}""", "hello1", String, silver_tests ) ;
-- escape-water
equalityTest ( s"""${toString(1)}hello""", "1hello", String, silver_tests ) ;
-- e-w-e
equalityTest ( s"""${toString(1)}a${aStrValue}""", "1a\n\t$", String, silver_tests ) ;
-- w-e-w
equalityTest ( s"""a${toString(1)}b""", "a1b", String, silver_tests ) ;

-- layout
equalityTest ( s"""a${   toString(1)   }b""", "a1b", String, silver_tests ) ;
equalityTest ( s"""${   toString(1)   }""", "1", String, silver_tests ) ;

-- escaping
equalityTest ( s""" " """, " \" ", String, silver_tests ) ; --"
equalityTest ( s""" \" """, " \\\" ", String, silver_tests ) ;
equalityTest ( s" \" ", " \" ", String, silver_tests ) ;
equalityTest ( s" \t ", " \t ", String, silver_tests ) ;
equalityTest ( s" \n ", " \n ", String, silver_tests ) ;
equalityTest ( s" \r ", " \r ", String, silver_tests ) ;
equalityTest ( s" \\ ", " \\ ", String, silver_tests ) ;
equalityTest ( s" \r\"\n\t \t\t\\\\ ", " \r\"\n\t \t\t\\\\ ", String, silver_tests ) ;

-- multiple line
equalityTest (s"""ab
c""", "ab\nc", String, silver_tests );

-- single line
equalityTest ( s"abc", "abc", String, silver_tests );
equalityTest ( s"${toString(1)}", "1", String, silver_tests );

-- pp
equalityTest (show(3, pp"""{${group(ppConcat([line(), nest(3, text("hi")), line()]))}}
this is some other text"""), "{\nhi\n}\nthis is some other text", String, silver_tests );
equalityTest (show(3, pp"{${group(ppConcat([line(), nest(3, text("hi")), line()]))}}this is some other text"), "{\nhi\n}this is some other text", String, silver_tests );


