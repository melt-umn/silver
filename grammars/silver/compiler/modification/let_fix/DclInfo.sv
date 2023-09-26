grammar silver:compiler:modification:let_fix;

import silver:compiler:definition:flow:ast only VertexType, FlowVertex;

abstract production lexicalLocalDcl
top::ValueDclInfo ::= fn::String ty::Type fi::Maybe<VertexType> fd::[FlowVertex] rs::[(String, UniqueRefSite)]
{
  top.fullName = fn;
  top.isEqual =
    -- Should never show up in an interface file anyway...
    case top.compareTo of
    | lexicalLocalDcl(fn2, ty2, _, _, _) -> fn == fn2 && ty == ty2
    | _ -> false
    end;

  top.typeScheme = monoType(ty);

  top.refDispatcher = lexicalLocalReference(_, fi, fd, rs, location=_);
  top.defDispatcher = errorValueDef(_, _, location=_); -- should be impossible (never in scope at production level?)
  top.defLHSDispatcher = errorDefLHS(_, location=_); -- ditto
  top.transDefLHSDispatcher = errorTransAttrDefLHS(_, _, location=_);
}

function lexicalLocalDef
Def ::= sg::String sl::Location fn::String ty::Type fi::Maybe<VertexType> fd::[FlowVertex] rs::[(String, UniqueRefSite)]
{
  return valueDef(defaultEnvItem(lexicalLocalDcl(fn,ty,fi,fd,rs,sourceGrammar=sg,sourceLocation=sl)));
}

