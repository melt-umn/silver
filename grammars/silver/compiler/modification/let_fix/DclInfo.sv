grammar silver:compiler:modification:let_fix;

import silver:compiler:definition:flow:ast only ExprVertexInfo, FlowVertex;

abstract production lexicalLocalDcl
top::ValueDclInfo ::= fn::String ty::Type fi::ExprVertexInfo fd::[FlowVertex]
{
  top.fullName = fn;
  top.isEqual =
    -- Should never show up in an interface file anyway...
    case top.compareTo of
    | lexicalLocalDcl(fn2, ty2, _, _) -> fn == fn2 && ty == ty2
    | _ -> false
    end;

  top.typeScheme = monoType(ty);

  top.refDispatcher = lexicalLocalReference(_, fi, fd, location=_);
  top.defDispatcher = errorValueDef(_, _, location=_); -- should be impossible (never in scope at production level?)
  top.defLHSDispatcher = errorDefLHS(_, location=_); -- ditto
}

function lexicalLocalDef
Def ::= sg::String sl::Location fn::String ty::Type fi::ExprVertexInfo fd::[FlowVertex]
{
  return valueDef(defaultEnvItem(lexicalLocalDcl(fn,ty,fi,fd,sourceGrammar=sg,sourceLocation=sl)));
}

