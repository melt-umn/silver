grammar silver:definition:core;

{--
 - Permissions management information for certain features that can appear in production
 - statements, etc.  i.e. "can forward/return/pluck?"
 - TODO: Probably should have a 'real' one for aspects...
 -}
nonterminal BlockContext with permitReturn, permitForward, permitProductionAttributes, permitLocalAttributes, lazyApplication, hasFullSignature, hasPartialSignature;


synthesized attribute permitReturn :: Boolean;
synthesized attribute permitForward :: Boolean;
synthesized attribute permitProductionAttributes :: Boolean;
synthesized attribute permitLocalAttributes :: Boolean;

{--
 - Whether the signature includes the name of a LHS.
 - Not strictly necessary to set partial to true, but to be expected...
 -}
synthesized attribute hasFullSignature :: Boolean;
{--
 - Whether the signature includes the type of a LHS & name/type pairs for RHS.
 - And the name. e.g. top.signature.fullName.
 -}
synthesized attribute hasPartialSignature :: Boolean;
{--
 - Whether expressions should be evaluated lazily in this context.
 - (False for action blocks, for example.)
 -}
synthesized attribute lazyApplication :: Boolean;


{-
 - We're deliberately continuing to have a "default production" here because
 - default is a legit thing, rather than just an artifact of trying to avoid
 - writing too many equations.
 - TODO: Changed my mind. Eliminate this, and actually give names to all 
 - contexts that use the default...
 -}

abstract production defaultContext
top::BlockContext ::=
{
  top.permitReturn = false;
  top.permitForward = false;
  top.permitProductionAttributes = true;
  top.permitLocalAttributes = true;
  top.lazyApplication = true;
  top.hasPartialSignature = false;
  top.hasFullSignature = false;
}

abstract production functionContext
top::BlockContext ::=
{
  top.permitReturn = true;
  top.hasPartialSignature = true;
  forwards to defaultContext();
}

abstract production productionContext
top::BlockContext ::=
{
  top.permitForward = true;
  top.hasPartialSignature = true;
  top.hasFullSignature = true;
  forwards to defaultContext();
}

