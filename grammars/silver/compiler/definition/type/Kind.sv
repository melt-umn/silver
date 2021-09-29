grammar silver:compiler:definition:type;

synthesized attribute baseKind::Kind;
synthesized attribute argKinds::[Kind];

nonterminal Kind with compareTo, isEqual, baseKind, argKinds;
propagate compareTo, isEqual on Kind;

aspect default production
top::Kind ::=
{
  top.baseKind = top;
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
  top.argKinds = k1 :: k2.argKinds;
}


-- TODO: Replace with default instance
instance Eq Kind {
  eq = \ k1::Kind k2::Kind -> decorate k1 with {compareTo = decorate k2 with {};}.isEqual;
}

global constructorKind::(Kind ::= Integer) = \ arity::Integer ->
  foldr(arrowKind, starKind(), repeat(starKind(), arity));
