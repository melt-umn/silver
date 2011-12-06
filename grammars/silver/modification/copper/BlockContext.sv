grammar silver:modification:copper;

attribute permitActions, permitPluck occurs on BlockContext;

{--
 - Actions include parser attribute manipulation. print statement.
 -}
synthesized attribute permitActions :: Boolean;
synthesized attribute permitPluck :: Boolean;

aspect production defaultContext
top::BlockContext ::=
{
  top.permitActions = false;
  top.permitPluck = false;
}

abstract production actionContext
top::BlockContext ::=
{
  top.lazyApplication = false;
  top.permitActions = true;
  top.permitProductionAttributes = false;
  forwards to defaultContext();
}

abstract production disambiguationContext
top::BlockContext ::=
{
  top.permitPluck = true;
  forwards to actionContext();
}

