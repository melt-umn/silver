grammar silver:compiler:modification:list;

{-
  listType exists here purely to provide a better pretty print.
 -}
abstract production listType
top::Type ::= el::Type
{
  propagate substituted, flatRenamed;
  top.typepp = "[" ++ el.typepp ++ "]";

  forwards to appType(listCtrType(), el);
}

abstract production listCtrType
top::Type ::=
{
  propagate substituted, flatRenamed;

  top.freeVariables = [];
  top.freeSkolemVars := [];
  top.freeFlexibleVars := [];
  top.typepp = "[]";
  
  -- Suppress its "nonterminal"ness
  top.isNonterminal = false;
  top.isDecorated = false;

  top.tracked = false;
  top.kindrep = arrowKind(starKind(),starKind());

  top.unify =
    case top.unifyWith of
    | listCtrType() -> emptySubst()
    | _ -> errorSubst("Tried to unify List with " ++ prettyType(top.unifyWith))
    end;

}
