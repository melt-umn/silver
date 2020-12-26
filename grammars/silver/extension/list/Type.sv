grammar silver:extension:list;

abstract production listType
top::Type ::= el::Type
{
  top.freeVariables = el.freeVariables; -- TypeExp.sv
  top.substituted = listType(el.substituted);
  top.flatRenamed = listType(el.flatRenamed);
  top.typepp = "[" ++ el.typepp ++ "]";

  top.unify =
    case top.unifyWith of
    | listType(fel) -> unify(el,fel)
    | decoratedType(appType(nonterminalType("core:List", _), el2)) -> unify(el, el2)
    | _ -> errorSubst("Tried to unify list with " ++ prettyType(top.unifyWith))
    end;
  
  -- Suppress its "nonterminal"ness
  top.isDecorable = false;
  top.isDecorated = false;
  --top.accessHandler = errorAccessHandler; -- permit this, since we need it for default, non-specialized java version
  top.lengthDispatcher = listLengthBouncer(_, location=_);
  top.appendDispatcher = listPlusPlus(_, _, location=_);
  
  --top.transType -- for translation.
  
  forwards to decoratedType(appType(nonterminalType("core:List", 1), el));
}

