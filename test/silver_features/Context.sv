
nonterminal Context;

abstract production context
t::Context ::=
{
  forwards to context();
}

--- Tests to ensure forward/return aren't allowed in wrong contexts

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

--- Tests to ensure return / forward are used sensibly: exactly one in functions, at most one in productions.

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

--- Tests to ensure that errors are raised if aspecting the wrong type of thing...

wrongCode "should be a 'production' aspect instead" {
  aspect function context
  Context ::=
  {
  }
}

function context2
Context ::= 
{ return error("SADF"); }

wrongCode "should be a 'function' aspect instead" {
  aspect production context2
  t::Context ::=
  {
  }
}

