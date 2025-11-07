grammar scopegraphs;

fun main IO<Integer> ::= args::[String] = pure(0);

--

type MyInhs = {foo, bar};
inherited attribute foo::[Decorated Scope with MyInhs] occurs on Scope;
inherited attribute bar::[Decorated Scope with MyInhs] occurs on Scope;

nonterminal Root;

production root
top::Root ::= 
{
  scope<MyInhs> fooScope;
}
