grammar silver:definition:core;

nonterminal Expected;

abstract production expected_type
top::Expected ::= t::TypeExp {}

abstract production expected_decorated
top::Expected ::= {}

abstract production expected_undecorated
top::Expected ::= {}

abstract production expected_default
top::Expected ::= {}

abstract production coerce_expected
top::Expr ::= e::Expr expect::TypeExp
{
  forwards to e with {
    expected = expected_type(expect);
  };
}

