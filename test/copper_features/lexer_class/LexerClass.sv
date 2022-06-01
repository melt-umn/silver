grammar copper_features:lexer_class;

imports silver:testing ;
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
equalityTest ( parse("bar", "").parseTerminals, [terminalDescriptor("bar", ["copper_features:lexer_class:Identifier2"], "copper_features:lexer_class:Id", loc("", 1, 0, 1, 3, 0, 3))], [TerminalDescriptor], copper_tests );

equalityTest ( parse("foo", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( parse("foo", "").parseTree.result, "Foo", String, copper_tests );
equalityTest ( parse("foo", "").parseTerminals, [terminalDescriptor("foo", ["copper_features:lexer_class:C2", "copper_features:lexer_class:BKeyword"], "copper_features:lexer_class:Foo", loc("", 1, 0, 1, 3, 0, 3))], [TerminalDescriptor], copper_tests );

equalityTest ( parse("fooxyz", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( parse("fooxyz", "").parseTree.result, "FooId", String, copper_tests );
equalityTest ( parse("fooxyz", "").parseTerminals, [terminalDescriptor("fooxyz", ["copper_features:lexer_class:CKeyword"], "copper_features:lexer_class:FooId", loc("", 1, 0, 1, 6, 0, 6))], [TerminalDescriptor], copper_tests );

equalityTest ( parse("getRes", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( parse("getRes", "").parseTree.result, "true", String, copper_tests );