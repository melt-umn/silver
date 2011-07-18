
import silver_features:defs:subdefs;

wrongCode "is already bound" {
  nonterminal Foo;
}

wrongCode "is already bound" {
  synthesized attribute foo :: String;
}

wrongCode "is already bound" {
  inherited attribute bar :: String;
}

wrongCode "already occurs on" {
  attribute foo occurs on Foo;
}

wrongCode "already occurs on" {
  attribute bar occurs on Foo;
}

wrongCode "is already bound" {
  abstract production foo
  f1::Foo ::= f2::Foo
  {}
}

-- from subdefs

wrongCode "shares a name with another production" {
  abstract production barBar
  f1::Bar ::= {}
}

{- ERR: TODO: SHOULD this be wrong code? It currently isn't

wrongCode "is already bound" {
  nonterminal Bar;
}

wrongCode "is already bound" {
  synthesized attribute bs :: String;
}

wrongCode "is already bound" {
  inherited attribute bi :: String;
}

---}

wrongCode "already occurs on" {
  attribute bs occurs on Bar;
}

wrongCode "already occurs on" {
  attribute bi occurs on Bar;
}



