grammar silver:analysis:typechecking:core;

import silver:definition:type;

synthesized attribute leftpp :: String;
synthesized attribute rightpp :: String;
synthesized attribute typeerror :: Boolean;

nonterminal TypeCheck with upSubst, downSubst, finalSubst, leftpp, rightpp, typeerror;

-- TODO: we could probably change this to 'expected' and 'actual' and generate the error message here?
abstract production check
top::TypeCheck ::= l::TypeExp r::TypeExp
{
  top.upSubst = unifyCheck(l, r, top.downSubst);
  
  top.typeerror = top.upSubst.failure;
  
  local attribute finleft :: TypeExp;
  finleft = performSubstitution(l, top.finalSubst);
  
  local attribute finright :: TypeExp;
  finright = performSubstitution(r, top.finalSubst);
  
  local attribute fv :: [TyVar];
  fv = setUnionTyVars(finleft.freeVariables, finright.freeVariables);
  
  top.leftpp = prettyTypeWith(finleft, fv);
  top.rightpp = prettyTypeWith(finright, fv);
}

abstract production checkNonterminal
top::TypeCheck ::= l::TypeExp
{
  top.upSubst = composeSubst(ignoreFailure(top.downSubst), performSubstitution(l, top.downSubst).unifyInstanceNonterminal);
  
  top.typeerror = top.upSubst.failure;
  
  top.leftpp = prettyType(performSubstitution(l, top.finalSubst));
  top.rightpp = "a nonterminal";
}
abstract production checkDecorated
top::TypeCheck ::= l::TypeExp
{
  top.upSubst = composeSubst(ignoreFailure(top.downSubst), performSubstitution(l, top.downSubst).unifyInstanceDecorated);
  
  top.typeerror = top.upSubst.failure;
  
  top.leftpp = prettyType(performSubstitution(l, top.finalSubst));
  top.rightpp = "a decorated nonterminal";
}

