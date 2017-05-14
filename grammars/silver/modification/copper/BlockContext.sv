grammar silver:modification:copper;

attribute permitActions, permitPluck occurs on BlockContext;

{--
 - Actions include parser attribute manipulation. print statement.
 -}
synthesized attribute permitActions :: Boolean;
synthesized attribute permitPluck :: Boolean;

aspect default production
top::BlockContext ::=
{
  top.permitActions = false;
  top.permitPluck = false;
}

abstract production actionContext
top::BlockContext ::= sig::NamedSignature
{
  top.fullName = sig.fullName;
  top.signature = sig;

  top.lazyApplication = false;
  top.permitActions = true;
  --top.permitProductionAttributes = false; -- denied by default
  top.permitLocalAttributes = true;
  -- TODO: signature? We DO have such info, but unclear what answer should be given...
}

abstract production disambiguationContext
top::BlockContext ::= sig::NamedSignature
{
  top.permitPluck = true;
  forwards to actionContext(sig);
}

