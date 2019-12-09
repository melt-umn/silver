grammar copper_features:lexer_class;

imports silver:testing ;
imports lib:extcore ;
imports copper_features;

terminal Id /[a-zA-Z]+/;

lexer class Keyword dominates {Id};

lexer class AKeyword extends Keyword;
lexer class BKeyword extends AKeyword;
lexer class CKeyword submits to BKeyword, extends AKeyword;

terminal Foo 'foo' lexer classes {BKeyword};
terminal FooId /foo[a-zA-Z]*/ lexer classes CKeyword;

parser attribute aIds::[TerminalId]
  action {aIds = AKeyword;};

-- The easiest way to test this and get data out of an action block is pushToken, ugh.
terminal Res /true|false/;
terminal GetA 'getA'
  lexer classes {Keyword},
  action {
    pushToken(Res, toString(
      containsBy(terminalIdEq, Foo, AKeyword) && 
      containsBy(terminalIdEq, FooId, AKeyword) && 
      !containsBy(terminalIdEq, Id, AKeyword) &&
      containsBy(terminalIdEq, Foo, BKeyword) && 
      !containsBy(terminalIdEq, FooId, BKeyword) && 
      !containsBy(terminalIdEq, Id, BKeyword) &&
      !containsBy(terminalIdEq, Foo, CKeyword) && 
      containsBy(terminalIdEq, FooId, CKeyword) && 
      !containsBy(terminalIdEq, Id, CKeyword)));
  };

synthesized attribute result::String;
nonterminal Root with result;

concrete productions top::Root
| Id { top.result = "Id"; }
| Foo { top.result = "Foo"; }
| FooId { top.result = "FooId"; }
| GetA res::Res { top.result = res.lexeme; }

parser parse :: Root {
  copper_features:lexer_class;
}


equalityTest ( parse("bar", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( parse("bar", "").parseTree.result, "Id", String, copper_tests );

equalityTest ( parse("foo", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( parse("foo", "").parseTree.result, "Foo", String, copper_tests );

equalityTest ( parse("fooxyz", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( parse("fooxyz", "").parseTree.result, "FooId", String, copper_tests );

equalityTest ( parse("getA", "").parseSuccess, true, Boolean, copper_tests );
equalityTest ( parse("getA", "").parseTree.result, "true", String, copper_tests );