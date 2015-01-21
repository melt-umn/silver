
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
equalityTest ( s""" " """, " \" ", String, silver_tests ) ;
equalityTest ( s""" \" """, " \\\" ", String, silver_tests ) ;

