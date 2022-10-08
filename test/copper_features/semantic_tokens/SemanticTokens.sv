grammar copper_features:semantic_tokens;

imports silver:testing ;
imports copper_features;

lexer class Op;
lexer class Fn;

terminal Plus_t '+' association=left, lexer classes {Op};
terminal LParen_t '(';
terminal RParen_t ')';

terminal Id_t /[a-zA-Z]+/;
terminal IdFn_t /[a-zA-Z]+/ lexer classes {Fn};

ignore terminal Whitespace /[\ \t\r\n]+/;

nonterminal Expr;
concrete productions top::Expr
| Id_t {}
| i::Id_t '(' ')' {} action {
  insert semantic token IdFn_t at i.location;
}
| e1::Expr '+' e2::Expr {}


parser parse :: Expr {
  copper_features:semantic_tokens;
}

equalityTest ( flatMap((.lexerClasses), parse("a", "").parseTerminals), [], [String], copper_tests );
equalityTest ( flatMap((.lexerClasses), parse("a()", "").parseTerminals), ["copper_features:semantic_tokens:Fn"], [String], copper_tests );
equalityTest ( flatMap((.lexerClasses), parse("a+b", "").parseTerminals), ["copper_features:semantic_tokens:Op"], [String], copper_tests );
equalityTest ( flatMap((.lexerClasses), parse("a+b()+c", "").parseTerminals), ["copper_features:semantic_tokens:Op", "copper_features:semantic_tokens:Fn", "copper_features:semantic_tokens:Op"], [String], copper_tests );
