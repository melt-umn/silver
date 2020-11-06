grammar silver:analysis:typechecking:core;

import silver:definition:type;

synthesized attribute leftpp :: String;
synthesized attribute rightpp :: String;
synthesized attribute typeerror :: Boolean;

nonterminal TypeCheck with upSubst, downSubst, finalSubst, leftpp, rightpp, typeerror;

-- TODO: we could probably change this to 'expected' and 'actual' and generate the error message here?
abstract production check
top::TypeCheck ::= l::Type r::Type
{
  top.upSubst = unifyCheck(l, r, top.downSubst);

  top.typeerror = top.upSubst.failure;

  local finleft :: Type = performSubstitution(l, top.finalSubst);

  local finright :: Type = performSubstitution(r, top.finalSubst);

  local fv :: [TyVar] = setUnionTyVars(finleft.freeVariables, finright.freeVariables);

  top.leftpp = prettyTypeWith(finleft, fv);
  top.rightpp = prettyTypeWith(finright, fv);
}

abstract production checkNonterminal
top::TypeCheck ::= l::Type
{
  local refined :: Type =
    performSubstitution(l, top.downSubst);

  top.upSubst = composeSubst(ignoreFailure(top.downSubst), refined.unifyInstanceNonterminal);

  top.typeerror = top.upSubst.failure && !refined.isError;

  top.leftpp = prettyType(performSubstitution(l, top.finalSubst));
  top.rightpp = "a nonterminal";
}
abstract production checkDecorated
top::TypeCheck ::= l::Type
{
  local refined :: Type =
    performSubstitution(l, top.downSubst);

  top.upSubst = composeSubst(ignoreFailure(top.downSubst), refined.unifyInstanceDecorated);

  top.typeerror = top.upSubst.failure && !refined.isError;

  top.leftpp = prettyType(performSubstitution(l, top.finalSubst));
  top.rightpp = "a decorated nonterminal";
}

