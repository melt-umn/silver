grammar silver:modification:annotation;

synthesized attribute isAnnotation :: Boolean occurs on DclInfo;

aspect default production
top::DclInfo ::=
{
  top.isAnnotation = false;
}

abstract production annoDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  ty.boundVariables = top.boundVariables ++ bound; -- explicit to make sure it errors if we can't
  top.unparse = "anno(" ++ sl.unparse ++ ", '" ++ fn ++ "', " ++ unparseTyVars(bound, ty.boundVariables) ++ ", " ++ ty.unparse ++ ")";
  
  top.typerep = ty;
  top.dclBoundVars = bound;

  top.decoratedAccessHandler = accessBounceUndecorate(annoAccessHandler, _, _, _);
  top.undecoratedAccessHandler = annoAccessHandler;
  -- No def dispatcher

  -- TODO fix this
  top.attrOccursType = "syn";
}

abstract production annoInstanceDcl
top::DclInfo ::= sg::String sl::Location fnnt::String fnat::String ntty::TypeExp atty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fnnt;
  
  ntty.boundVariables = top.boundVariables ++ ntty.freeVariables;
  atty.boundVariables = ntty.boundVariables;
  top.unparse = "anno@(" ++ sl.unparse ++ ", '" ++ fnnt ++ "', '" ++ fnat ++ "', " ++ 
                       unparseTyVars(ntty.freeVariables, ntty.boundVariables) ++ ", " ++
                       ntty.unparse ++ ", " ++ 
                       atty.unparse ++ ")";

  -- There should be no type variables in atty that aren't in ntty. (Important constraint!)
  -- that's why we only use ntty.FV above.
  
  -- ALSO IMPORTANT: ntty and atty should be tyvar'd up, not skolem'd up. You dig?
  
  -- Here we use givenNonterminalType to find the attribute type:
  local attribute subst :: Substitution;
  subst = unifyDirectional(ntty, top.givenNonterminalType); -- must rewrite FROM ntty TO gNT
  
  top.typerep = if subst.failure
                then -- We didn't get a sensible type for givenNonterminalType. Let's do our best? (This error should already be caught!)
                     freshenCompletely(atty)
                else performSubstitution(atty, subst);
  
  top.attrOccurring = fnat;

  -- TODO: fix this
  top.attrOccursIndexName = makeIdName(fnat ++ "__ON__" ++ fnnt);
  top.attrOccursIndex = makeName(sg) ++ ".Init." ++ top.attrOccursIndexName;
  
  -- UGH
  top.isAnnotation = true;
}

