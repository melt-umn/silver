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

{-- Terminal shift, parser attribute initialization -}
abstract production actionContext
top::BlockContext ::=
{
  top.fullName = "__action__"; -- Used as part of naming locals... maybe we should fix that? TODO
  top.signature = bogusNamedSignature();
  
  top.lazyApplication = false;
  top.permitActions = true;
  --top.permitProductionAttributes = false; -- denied by default
  top.permitLocalAttributes = true;
  -- TODO: signature? We DO have such info, but unclear what answer should be given...
}

{-- Disambiguation groups -}
abstract production disambiguationContext
top::BlockContext ::=
{
  top.permitPluck = true;
  forwards to actionContext();
}

{-- Production reduce actions -}
abstract production reduceActionContext
top::BlockContext ::= sig::NamedSignature
{
  top.fullName = sig.fullName;
  top.signature = sig; -- TODO: figure out if this is ever used for actions?
  top.className = makeClassName(top.fullName); -- child references in production actions use it

  forwards to actionContext();
}

