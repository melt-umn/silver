grammar silver:compiler:modification:list;

{- Everything about this is awful.
   We want to have `[a]` be able to unify with `f<a>`, while also maintaining the
   expected [a] pretty-print AND the specialized translation.  
   This is in itself interfering, but this makes the situation even more complicated:
   listType exists for pretty-printing, while listCtrType provides something
   for the `f` variable in `f<a>` to unify with while maintaining the proper
   semantic behavior and translation. 
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
  top.typepp = "[]";
  
  -- Suppress its "nonterminal"ness
  top.isNonterminal = false;
  top.isDecorated = false;

  top.tracked = false;
  top.kindrep = foldr(arrowKind,starKind(),[starKind()]);
}
