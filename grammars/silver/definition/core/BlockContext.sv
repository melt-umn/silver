grammar silver:definition:core;

{--
 - Permissions management information for certain features that can appear in production
 - statements, etc.  i.e. "can forward/return/pluck?"
 - TODO: Probably should have a 'real' one for aspects...
 -}
nonterminal BlockContext with permitReturn, permitForward, permitProductionAttributes, lazyApplication;

synthesized attribute permitReturn :: Boolean;
synthesized attribute permitForward :: Boolean;
synthesized attribute permitProductionAttributes :: Boolean;
synthesized attribute lazyApplication :: Boolean;

{-
 - We're deliberately continuing to have a "default production" here because
 - default is a legit thing, rather than just an artifact of trying to avoid
 - writing too many equations.
 -}

abstract production defaultContext
top::BlockContext ::=
{
  top.permitReturn = false;
  top.permitForward = false;
  top.permitProductionAttributes = true;
  top.lazyApplication = true;
}

abstract production functionContext
top::BlockContext ::=
{
  top.permitReturn = true;
  forwards to defaultContext();
}

abstract production productionContext
top::BlockContext ::=
{
  top.permitForward = true;
  forwards to defaultContext();
}

