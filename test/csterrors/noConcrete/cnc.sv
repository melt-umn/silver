grammar noConcrete;

nonterminal Foo;
nonterminal Bar;

concrete production foo_c
top::Foo ::= b::Bar
{}

parser extendedParser::Foo
{
  noConcrete;
}