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


aspect default production
top::BlockContext ::=
{
  -- most restrictive possible
  top.permitReturn = false;
  top.permitForward = false;
  top.permitProductionAttributes = false;
  top.permitLocalAttributes = false;
  top.lazyApplication = true;
  top.hasPartialSignature = false;
  top.hasFullSignature = false;
}

abstract production functionContext
top::BlockContext ::=
{
  top.permitReturn = true;
  top.hasPartialSignature = true;
  top.permitProductionAttributes = true;
  top.permitLocalAttributes = true;
}

abstract production productionContext
top::BlockContext ::=
{
  top.permitForward = true;
  top.hasPartialSignature = true;
  top.hasFullSignature = true;
  top.permitProductionAttributes = true;
  top.permitLocalAttributes = true;
}

abstract production aspectFunctionContext
top::BlockContext ::=
{
  top.permitReturn = false;
  forwards to functionContext();
}

abstract production aspectProductionContext
top::BlockContext ::=
{
  top.permitForward = false;
  forwards to productionContext();
}

abstract production globalExprContext
top::BlockContext ::=
{
}

