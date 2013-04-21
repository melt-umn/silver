
-- empty
equalityTest ( template """""", "", String, silver_tests ) ;
-- water only
equalityTest ( template """hello""", "hello", String, silver_tests ) ;

global aStrValue :: String = template """
	$$""";
-- escape only
equalityTest ( template """${aStrValue}""", "\n\t$", String, silver_tests ) ;
-- full expression
equalityTest ( template """${toString(1)}""", "1", String, silver_tests ) ;
-- water-escape
equalityTest ( template """hello${toString(1)}""", "hello1", String, silver_tests ) ;
-- escape-water
equalityTest ( template """${toString(1)}hello""", "1hello", String, silver_tests ) ;
-- e-w-e
equalityTest ( template """${toString(1)}a${aStrValue}""", "1a\n\t$", String, silver_tests ) ;
-- w-e-w
equalityTest ( template """a${toString(1)}b""", "a1b", String, silver_tests ) ;

