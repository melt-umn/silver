
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

wrongCode "does not have a return value" {
  function noretlol
  Context ::=
  { }
}

wrongCode "has more than one declared return value" {
  function retretlol
  Context ::=
  { return error("a");
    return error("b");
  }
}

wrongCode "has more than one forward declaration" {
  abstract production fwdfwdlol
  t::Context ::=
  {
    forwards to context();
    forwards to fwdfwdlol();
  }
}
