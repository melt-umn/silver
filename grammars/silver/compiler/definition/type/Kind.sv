grammar silver:compiler:definition:type;

synthesized attribute baseKind::Kind;
synthesized attribute argKinds::[Kind];

tracked nonterminal Kind with compareTo, isEqual, baseKind, argKinds;
propagate compareTo, isEqual on Kind;

aspect default production
top::Kind ::=
{
  top.baseKind = new(top);
  top.argKinds = [];
}

abstract production starKind
top::Kind ::=
{}

abstract production inhSetKind
top::Kind ::=
{}

abstract production arrowKind
top::Kind ::= k1::Kind k2::Kind
{
  top.baseKind = k2.baseKind;
  top.argKinds = new(k1) :: k2.argKinds;
}

global constructorKind::(Kind ::= Integer) = \ arity::Integer ->
  foldr(arrowKind, starKind(), repeat(starKind(), arity));
