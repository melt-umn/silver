
nonterminal ForwardKeyword;

synthesized attribute fkSyn1::String occurs on ForwardKeyword;
inherited attribute fkInh1::String occurs on ForwardKeyword;
synthesized attribute fkSyn2::String occurs on ForwardKeyword;
inherited attribute fkInh2::String occurs on ForwardKeyword;

abstract production bar
t::ForwardKeyword ::= 
{
  t.fkSyn1 = forward.fkSyn1 ++ "bar";
  t.fkSyn2 = forward.fkSyn2 ++ "bar";
  
  forwards to bar2() with {
    fkInh1 = "foo";
  };
  
  forward.fkInh2 = forward.fkSyn1;
}

abstract production bar2
t::ForwardKeyword ::=
{
  t.fkSyn1 = t.fkInh1;
  t.fkSyn2 = t.fkInh2;
}

equalityTest ( bar().fkSyn1, "foobar", String, silver_tests ) ;
equalityTest ( bar().fkSyn2, "foobar", String, silver_tests ) ;

