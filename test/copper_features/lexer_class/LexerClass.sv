grammar copper_features:lexer_class;

imports silver:testing ;
imports lib:extcore ;
imports copper_features;

lexer class Identifier;
lexer class Identifier2 extends Identifier;

lexer class Keyword dominates {Identifier};

lexer class AKeyword extends Keyword;
lexer class BKeyword extends AKeyword;
lexer class CKeyword submits to BKeyword, extends AKeyword;

lexer class C1 extends C3;
lexer class C2 extends C1;
lexer class C3 extends C2;

terminal Id /[a-zA-Z]+/ lexer classes Identifier2;

terminal Foo 'foo' lexer classes {BKeyword, C2};
terminal FooId /foo[a-zA-Z]*/ lexer classes CKeyword;

parser attribute aIds::[TerminalId]
  action {aIds = AKeyword;};

-- The easiest way to test this and get data out of an action block is pushToken, ugh.
terminal Res /true|false/;
terminal GetRes 'getRes'
  lexer classes {Keyword},
  action {
    pushToken(Res, toString(
      terminalSetEq(AKeyword, [Foo, FooId]) &&
      terminalSetEq(BKeyword, [Foo]) &&
      terminalSetEq(CKeyword, [FooId]) &&
      terminalSetEq(C1, [Foo]) &&
      terminalSetEq(C2, [Foo]) &&
      terminalSetEq(C3, [Foo])));
  };

synthesized attribute result::String;
nonterminal Root with result;

concrete productions top::Root
| Id { top.result = "Id"; }
| Foo { top.result = "Foo"; }
| FooId { top.result = "FooId"; }
| GetRes res::Res { top.result = res.lexeme; }

parser parse :: Root {
  copper_features:lexer_class;
}


equalityTest ( parse("bar", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( parse("bar", "").parseTree.result, "Id", String, copper_tests );

equalityTest ( parse("foo", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( parse("foo", "").parseTree.result, "Foo", String, copper_tests );

equalityTest ( parse("fooxyz", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( parse("fooxyz", "").parseTree.result, "FooId", String, copper_tests );

equalityTest ( parse("getRes", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( parse("getRes", "").parseTree.result, "true", String, copper_tests );