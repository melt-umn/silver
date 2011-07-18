
nonterminal Context;

abstract production context
t::Context ::=
{
  forwards to context();
}

wrongCode "Forwarding is not permitted in this context" {
  aspect production context
  t::Context ::=
  {
    forwards to context();
  }
}

wrongCode "Return is not valid in this context" {
  abstract production bar
  t::Context ::=
  {
    return context();
  }
}

