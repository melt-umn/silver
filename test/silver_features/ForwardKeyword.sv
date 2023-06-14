
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

abstract production bar3
t::ForwardKeyword ::=
{
  forward production attribute fwrd::ForwardKeyword = bar2();

  t.fkSyn1 = fwrd.fkSyn1;
  t.fkSyn2 = fwrd.fkSyn2;
}

equalityTest ( decorate bar3() with {fkInh1 = "foobar";}.fkSyn1, "foobar", String, silver_tests ) ;

nonterminal OtherForwardNT with fkInh1, fkInh2;
wrongCode "Forward production attribute fwrd has type silver_features:ForwardKeyword that does not match the production left side silver_features:OtherForwardNT" {
production wrongLHSType
top::OtherForwardNT ::=
{
  forward production attribute fwrd::ForwardKeyword;
  fwrd = bar2();
}
}
