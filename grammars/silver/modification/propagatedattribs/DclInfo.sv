
synthesized attribute propagateProd::(ProductionStmt ::= QName Location) occurs on DclInfo;

aspect default production
top::DclInfo ::=
{
  top.propagateProd = propagateError(_, location=_);
}

abstract production functorDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::TypeExp
{
  {-top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;-}

  ty.boundVariables = top.boundVariables ++ bound; -- explicit to make sure it errors if we can't
  top.unparse = "functor(" ++ sl.unparse ++ ", '" ++ fn ++ "', " ++ unparseTyVars(bound, ty.boundVariables) ++ ", " ++ ty.unparse ++ ")";
  
  {-top.typerep = ty;
  top.dclBoundVars = bound;

  top.isSynthesized = true;
  
  -- the core dispatchers
  top.decoratedAccessHandler = synDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler(_, _, location=_), _, _, _); -- TODO: should probably be an error handler!
  top.attrDefDispatcher = synthesizedAttributeDef(_, _, _, location=_);-}
  
  top.propagateProd = propagateFunctor(_, location=_);
  
  forwards to synDcl(sg,sl,fn,bound,ty);
}

function functorDef
Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::TypeExp
{
  return attrDef(defaultEnvItem(functorDcl(sg,sl,fn,bound,ty)));
}