
nonterminal DefaultAttrNT with defaultsyn, defaultsyn2;

synthesized attribute defaultsyn :: Integer;
synthesized attribute defaultsyn2 :: Integer;

abstract production defaultattr1
top::DefaultAttrNT ::=
{
  top.defaultsyn = 1;
}

abstract production defaultattr2
top::DefaultAttrNT ::=
{
  top.defaultsyn = 2;
}

abstract production defaultattrn
top::DefaultAttrNT ::=
{
}

abstract production defaultfwd
top::DefaultAttrNT ::=
{
  top.defaultsyn = 3;
  forwards to defaultattrn();
}

aspect default production
top::DefaultAttrNT ::=
{
  top.defaultsyn = 0;
  top.defaultsyn2 = top.defaultsyn;
}

equalityTest ( defaultattr1().defaultsyn, 1, Integer, silver_tests ) ;
equalityTest ( defaultattr2().defaultsyn, 2, Integer, silver_tests ) ;
equalityTest ( defaultattrn().defaultsyn, 0, Integer, silver_tests ) ;
equalityTest ( defaultattr1().defaultsyn, 1, Integer, silver_tests ) ;
equalityTest ( defaultfwd().defaultsyn, 3, Integer, silver_tests ) ;

equalityTest ( defaultattr1().defaultsyn2, 1, Integer, silver_tests ) ;
equalityTest ( defaultattr2().defaultsyn2, 2, Integer, silver_tests ) ;
equalityTest ( defaultattrn().defaultsyn2, 0, Integer, silver_tests ) ;
equalityTest ( defaultfwd().defaultsyn2, 0, Integer, silver_tests ) ; -- Difference from above


