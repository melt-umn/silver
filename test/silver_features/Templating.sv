
equalityTest ( template """""", "", String, silver_tests ) ;

equalityTest ( template """hello""", "hello", String, silver_tests ) ;

global aStrValue :: String = template """
	$$""";

equalityTest ( template """${aStrValue}""", "\n\t$", String, silver_tests ) ;

equalityTest ( template """${toString(1)}""", "1", String, silver_tests ) ;

