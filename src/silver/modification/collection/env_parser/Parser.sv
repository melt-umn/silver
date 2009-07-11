grammar silver:modification:collection:env_parser;
import silver:modification:collection;
import silver:definition:env;
import silver:definition:env:parser;

terminal CollectionTerm 'collection' lexer classes {C_1};
terminal PlusPlusTerm '++' lexer classes {C_1};

nonterminal aOperation with operation;

concrete production aTypeRepCollection
top::aTypeRep ::= v::CollectionTerm '(' o::aOperation ',' t::aTypeRep ')'{
  top.typerep = collectionTypeRep(o.operation, t.typerep);
}

concrete production operationName
top::aOperation ::= n::Name {
  top.operation = nameOperation(n.lexeme);
}

concrete production operationPlusPlus
top::aOperation ::= '++' {
  top.operation = plusPlusOperation();
}
