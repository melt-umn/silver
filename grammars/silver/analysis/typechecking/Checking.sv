grammar silver:analysis:typechecking;

import silver:definition:type;
import silver:analysis:typechecking:core;

synthesized attribute leftpp :: String;
synthesized attribute rightpp :: String;
synthesized attribute typeerror :: Boolean;

nonterminal TypeCheck with upSubst, downSubst, finalSubst, leftpp, rightpp, typeerror;

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
  fv = setUnionTyVars(finleft, finright);
  
  top.leftpp = prettyTypeWith(finleft, fv);
  top.rightpp = prettyTypeWith(finright, fv);
}
