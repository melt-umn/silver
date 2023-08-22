grammar silver:compiler:analysis:typechecking:core;

import silver:compiler:definition:type;

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

  local finleft :: Type = performSubstitution(l, top.finalSubst).defaultSpecialization;

  local finright :: Type = performSubstitution(r, top.finalSubst).defaultSpecialization;

  local fv :: [TyVar] = setUnionTyVars(finleft.freeVariables, finright.freeVariables);

  top.leftpp = prettyTypeWith(finleft, fv);
  top.rightpp = prettyTypeWith(finright, fv);
}
