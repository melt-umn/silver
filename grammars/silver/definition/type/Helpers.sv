grammar silver:definition:type;

-- set contains:  s 'in' sl
function containsTyVar
Boolean ::= s::TyVar sl::[TyVar]
{
  return (!null(sl)) && (tyVarEqual(s, head(sl)) || containsTyVar(s, tail(sl)));
}

-- set difference:  startswith - toremove
function removeTyVars
[TyVar] ::= startswith::[TyVar] toremove::[TyVar]
{
  return if null(startswith) then [] 
         else if containsTyVar(head(startswith), toremove)
	      then removeTyVars(tail(startswith), toremove)
	      else cons(head(startswith), removeTyVars(tail(startswith), toremove));
}

-- HUGE LIE: This is not a set. Well, maybe. We *might* depend on this being ordered.
function setUnionTyVars
[TyVar] ::= a::[TyVar] b::[TyVar]
{
  return a ++ removeTyVars(b, a);
}
function setUnionTyVarsAll
[TyVar] ::= tvs::[[TyVar]]
{
  return if null(tvs)
          then []
         else if null(tail(tvs))
          then head(tvs)
          else setUnionTyVars( head(tvs), setUnionTyVarsAll(tail(tvs)));
}
