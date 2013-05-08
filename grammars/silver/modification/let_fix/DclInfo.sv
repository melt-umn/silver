grammar silver:modification:let_fix;

abstract production lexicalLocalDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.unparse = error("Internal compiler error: locally scoped declaration that should never appear in interface files");
  
  top.typerep = ty;

  top.refDispatcher = lexicalLocalReference(_, location=_);
  top.defDispatcher = errorValueDef(_, _, location=_); -- should be impossible (never in scope at production level?)
  top.defLHSDispatcher = errorDefLHS(_, location=_); -- ditto
}

function lexicalLocalDef
Def ::= sg::String sl::Location fn::String ty::TypeExp
{
  return valueDef(defaultEnvItem(lexicalLocalDcl(sg,sl,fn,ty)));
}

