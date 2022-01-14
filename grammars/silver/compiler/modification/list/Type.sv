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
  top.freeSkolemVars := [];
  top.typepp = "[]";
  
  -- Suppress its "nonterminal"ness
  top.isNonterminal = false;
  top.isDecorated = false;

  top.tracked = false;
  top.kindrep = arrowKind(starKind(),starKind());

  local unifExampl :: Substitution =
    case top.unifyWith of
    | nonterminalType(ofn, oks, otracked) ->
        if "silver:core:List" == ofn
        then if [starKind()] == oks
          then if otracked
            then error("Internal Error: Mismatching trackedness for silver:core:List "  ++ " when unifying. Try rebuilding with --clean. \nSee https://github.com/melt-umn/silver/pull/333 and https://github.com/melt-umn/silver/issues/36 .")
            else emptySubst()
          else error("kind mismatch during unification for " ++ prettyType(top) ++ " and " ++ prettyType(top.unifyWith)) -- Should be impossible
        else errorSubst("Tried to unify conflicting nonterminal types silver:core:List " ++ " and " ++ ofn)
    | listCtrType() -> emptySubst()
    | _ -> errorSubst("Tried to unify List with " ++ prettyType(top.unifyWith))
    end;
  top.unify = unifExampl;
  --top.unify = unsafeTracePrint(unifExampl, "Unify Debug: \n" ++ "\ttype: " ++ prettyType(top) ++ "\n\tunifywith: " ++ prettyType(top.unifyWith) ++ "\n\tunify: " ++  hackUnparse(unifExampl) ++ "\n");


  --forwards to nonterminalType("silver:core:List", [starKind()], false);

  top.freeFlexibleVars := [];
}
