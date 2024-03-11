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

  top.refDispatcher = lexicalLocalReference(fi, fd, rs);
  top.defDispatcher = errorValueDef; -- should be impossible (never in scope at production level?)
  top.defLHSDispatcher = errorDefLHS; -- ditto
  top.transDefLHSDispatcher = errorTransAttrDefLHS;
}

fun lexicalLocalDef
Def ::= sg::String sl::Location fn::String ty::Type fi::Maybe<VertexType> fd::[FlowVertex] rs::[(String, UniqueRefSite)] =
  valueDef(defaultEnvItem(lexicalLocalDcl(fn,ty,fi,fd,rs,sourceGrammar=sg,sourceLocation=sl)));

