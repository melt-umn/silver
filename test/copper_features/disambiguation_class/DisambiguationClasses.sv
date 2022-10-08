imports silver:testing ;
imports copper_features;

-- Test disambiguation classes.
-- Use lexeme in groups
-- Distinguish terminals properly

parser attribute selectedId::TerminalId
  action { selectedId = Id3; };

lexer class Identifier disambiguate {
  pluck selectedId;
};

ignore terminal Space ' ';

terminal SelectId1 'id1';
terminal SelectId2 'id2';
terminal SelectId3 'id3';

terminal Id1 /[a-z]*/ lexer classes {Identifier};
terminal Id2 /[a-z]*/ lexer classes {Identifier};
terminal Id3 /[a-z]*/ lexer classes {Identifier};

terminal Foo 'foo';
terminal Bar 'bar';
terminal Baz 'baz';

synthesized attribute n::Integer;
nonterminal DCRoot with n;
concrete production root
top::DCRoot ::= s::Select b::Body
{ top.n = b.n; }

nonterminal Select;
concrete productions top::Select
| 'id1' {} action { selectedId = Id1; }
| 'id2' {} action { selectedId = Id2; }
| 'id3' {} action { selectedId = Id3; }

nonterminal Body with n;
concrete productions top::Body
| 'foo' Id1 { top.n = 1; }
| 'foo' Id2 { top.n = 2; }
| 'foo' Id3 { top.n = 3; }
| 'bar' Id1 { top.n = 1; }
| 'bar' Id2 { top.n = 2; }
| 'baz' Id1 { top.n = 1; }

parser dcparse::DCRoot {
  copper_features:disambiguation_class;
}

equalityTest ( dcparse("id1 foo abcd", "ASDF").parseSuccess, true, Boolean, copper_tests ) ;
equalityTest ( dcparse("id2 foo abcd", "ASDF").parseSuccess, true, Boolean, copper_tests ) ;
equalityTest ( dcparse("id2 bar abcd", "ASDF").parseSuccess, true, Boolean, copper_tests ) ;
equalityTest ( dcparse("id3 bar abcd", "ASDF").parseSuccess, false, Boolean, copper_tests ) ;
equalityTest ( dcparse("id3 baz abcd", "ASDF").parseSuccess, true, Boolean, copper_tests ) ;

equalityTest ( dcparse("id1 foo abcd", "ASDF").parseTree.n, 1, Integer, copper_tests ) ;
equalityTest ( dcparse("id2 foo abcd", "ASDF").parseTree.n, 2, Integer, copper_tests ) ;
equalityTest ( dcparse("id2 bar abcd", "ASDF").parseTree.n, 2, Integer, copper_tests ) ;
equalityTest ( dcparse("id3 baz abcd", "ASDF").parseTree.n, 1, Integer, copper_tests ) ;

