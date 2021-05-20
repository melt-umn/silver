grammar silver:core;

@@{-
   - @warning This grammar contains only the definitions of the AST nonterminals, needed by the runtime library.
   -          The full reflection library that users should import is `silver:reflect`
   -}

tracked nonterminal AST;

abstract production nonterminalAST
top::AST ::= prodName::String children::ASTs annotations::NamedASTs
{}

abstract production terminalAST
top::AST ::= terminalName::String lexeme::String location::Location
{}

abstract production listAST
top::AST ::= vals::ASTs
{}

abstract production stringAST
top::AST ::= s::String
{}

abstract production integerAST
top::AST ::= i::Integer
{}

abstract production floatAST
top::AST ::= f::Float
{}

abstract production booleanAST
top::AST ::= b::Boolean
{}

abstract production anyAST
top::AST ::= x::a
{}

tracked nonterminal ASTs;

abstract production consAST
top::ASTs ::= h::AST t::ASTs
{}

abstract production nilAST
top::ASTs ::=
{}

tracked nonterminal NamedASTs;

abstract production consNamedAST
top::NamedASTs ::= h::NamedAST t::NamedASTs
{}

abstract production nilNamedAST
top::NamedASTs ::=
{}

tracked nonterminal NamedAST;

abstract production namedAST
top::NamedAST ::= n::String v::AST
{}
