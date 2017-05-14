grammar silver:definition:core;

{--
 - Permissions management information for certain features that can appear in production
 - statements, etc.  i.e. "can forward/return/pluck?"
 -}
nonterminal BlockContext with permitReturn, permitForward, permitProductionAttributes, permitLocalAttributes, lazyApplication, hasFullSignature, hasPartialSignature, fullName, lhsNtName, signature;


{-- Are 'return' equations allowed in this context? -}
synthesized attribute permitReturn :: Boolean;
{-- Are 'forwards to' equations allowed in this context? -}
synthesized attribute permitForward :: Boolean;
{-- Are 'local' equations allowed in this context? -}
synthesized attribute permitLocalAttributes :: Boolean;
{-- Are 'production attribute' equations allowed in this context?
    DISTINCT from locals, due to action blocks. -}
synthesized attribute permitProductionAttributes :: Boolean;

{--
 - Whether the signature includes the name of a LHS.
 - Not strictly necessary to set partial to true, but to be expected...
 - REFACTORING NOTES: Used to:
 - 1. Figure out how to get a production graph (REPLACE THIS) (i.e. function vs production)
 - 2. Ignore checking LHS Inhs in functions, which don't have LHS inhs...
 -}
synthesized attribute hasFullSignature :: Boolean;
{--
 - Whether the signature includes the type of a LHS & name/type pairs for RHS.
 - And the name. e.g. top.frame.fullName.
 - REFACTORING NOTES: Used to:
 - 1. Decide if syn eq should be exported by NT alone (default eq) or OCC/NT (normal syn eq)
 - 2. Decide if we need to look at deps of syn eqs (i.e. default eqs don't get checked locally)
 - 3. Decide to emit a default equation or synthesized equation
 -}
synthesized attribute hasPartialSignature :: Boolean;
{--
 - Whether expressions should be evaluated lazily in this context.
 - (False for action blocks, for example.)
 -}
synthesized attribute lazyApplication :: Boolean;
{--
 - The full name of the LHS nonterminal.
 - ONLY ACCESSIBLE IF `top.hasFullSignature` is true!
 -}
synthesized attribute lhsNtName :: String;
{--
 - The signature of the current context.
 - TODO: rename 'signature' once that attribute has been purged...
 -}
synthesized attribute signature :: NamedSignature;


aspect default production
top::BlockContext ::=
{
  top.lhsNtName = error("LHS NT accessed for non-production");
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
top::BlockContext ::= sig::NamedSignature
{
  top.fullName = sig.fullName;
  top.signature = sig;

  top.permitReturn = true;
  top.hasPartialSignature = true;
  top.permitProductionAttributes = true;
  top.permitLocalAttributes = true;
}

abstract production productionContext
top::BlockContext ::= sig::NamedSignature
{
  top.fullName = sig.fullName;
  top.lhsNtName = sig.outputElement.typerep.typeName;
  top.signature = sig;

  top.permitForward = true;
  top.hasPartialSignature = true;
  top.hasFullSignature = true;
  top.permitProductionAttributes = true;
  top.permitLocalAttributes = true;
}

abstract production aspectFunctionContext
top::BlockContext ::= sig::NamedSignature
{
  top.permitReturn = false;
  forwards to functionContext(sig);
}

abstract production aspectProductionContext
top::BlockContext ::= sig::NamedSignature
{
  top.permitForward = false;
  forwards to productionContext(sig);
}

abstract production globalExprContext
top::BlockContext ::=
{
  top.fullName = "_NULL_"; -- maybe we should actually error?
  top.signature = bogusNamedSignature(); -- TODO: do something about this?
}

