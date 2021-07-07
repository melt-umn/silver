grammar silver:compiler:extension:list;

{- Everything about this is awful.
   We want to have `[a]` be able to unify with `f<a>`, while also maintaining the
   expected [a] pretty-print AND the specialized translation.  
   This is in itself interfering, but this makes the situation even more complicated:
   listType exists for pretty-printing, while listCtrType provides something
   for the `f` variable in `f<a>` to unify with while maintaining the proper
   semantic behavior and translation. 
   TODO: Think really, really hard about just making this ... not an extension.
   Would lose the non-specialized implementations, but maybe that's okay since
   alternative Silver translations should probably provide a more efficient
   implementation of lists anyway?
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
  --top.accessHandler = errorAccessHandler; -- permit this, since we need it for default, non-specialized java version
  top.lengthDispatcher = listLengthBouncer(_, location=_);
  
  --top.transType -- for translation.
  
  -- We would *like* this to be Decorated silver:core:List to allow caching of
  -- i_emptyList, i_lengthList, etc. in the non-specialized translation.
  -- That's no longer possible with the switch to appType, but this has no
  -- effect on the performance of the java translation.
  forwards to nonterminalType("silver:core:List", [starKind()], false);
}
