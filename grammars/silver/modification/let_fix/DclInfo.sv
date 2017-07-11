grammar silver:modification:let_fix;

import silver:definition:flow:ast only ExprVertexInfo, FlowVertex;

abstract production lexicalLocalDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::TypeExp fi::ExprVertexInfo fd::[FlowVertex]
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.unparse = error("Internal compiler error: locally scoped declaration that should never appear in interface files");
  
  top.typerep = ty;

  top.refDispatcher = lexicalLocalReference(_, fi, fd, location=_);
  top.defDispatcher = errorValueDef(_, _, location=_); -- should be impossible (never in scope at production level?)
  top.defLHSDispatcher = errorDefLHS(_, location=_); -- ditto
}

function lexicalLocalDef
Def ::= sg::String sl::Location fn::String ty::TypeExp fi::ExprVertexInfo fd::[FlowVertex]
{
  return valueDef(defaultEnvItem(lexicalLocalDcl(sg,sl,fn,ty,fi,fd)));
}

