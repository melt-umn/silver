
-- Test disambiguation groups.
-- Use lexeme in groups
-- Distinguish terminals properly

nonterminal DGRoot with dgFoo;
synthesized attribute dgFoo :: Boolean;

terminal Foo_t 'foo';
terminal Bar_t 'bar';
terminal Id /[a-z]*/;

concrete production rt
r::DGRoot ::= Id {r.dgFoo = false;}
concrete production rt2
r::DGRoot ::= Foo_t {r.dgFoo = true;}
concrete production rt3
r::DGRoot ::= Bar_t {r.dgFoo = false;}

disambiguate Foo_t, Id {
  pluck Foo_t;
}

disambiguate Bar_t, Id {
  pluck disambiguationFailure;
}

parser dgparse::DGRoot {
  copper_features;
}

equalityTest ( dgparse("foo", "ASDF").parseSuccess, true, Boolean, copper_tests ) ;
equalityTest ( dgparse("bar", "ASDF").parseSuccess, false, Boolean, copper_tests ) ;
equalityTest ( dgparse("!#@$%", "ASDF").parseSuccess, false, Boolean, copper_tests ) ;
equalityTest ( dgparse("Foo", "ASDF").parseSuccess, false, Boolean, copper_tests ) ;


equalityTest ( dgparse("foo", "ASDF").parseTree.dgFoo, true, Boolean, copper_tests ) ;
equalityTest ( dgparse("fo", "ASDF").parseTree.dgFoo, false, Boolean, copper_tests ) ;
equalityTest ( dgparse("br", "ASDF").parseTree.dgFoo, false, Boolean, copper_tests ) ;
equalityTest ( dgparse("foobar", "ASDF").parseTree.dgFoo, false, Boolean, copper_tests ) ;

