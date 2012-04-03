
nonterminal DefaultAttrNT with defaultsyn;

synthesized attribute defaultsyn :: Integer;

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

aspect default production
top::DefaultAttrNT ::=
{
  top.defaultsyn = 0;
}

equalityTest ( defaultattr1().defaultsyn, 1, Integer, silver_tests ) ;
equalityTest ( defaultattr2().defaultsyn, 2, Integer, silver_tests ) ;
equalityTest ( defaultattrn().defaultsyn, 0, Integer, silver_tests ) ;
equalityTest ( defaultattr1().defaultsyn, 1, Integer, silver_tests ) ;

