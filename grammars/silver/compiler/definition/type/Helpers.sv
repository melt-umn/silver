grammar silver:compiler:definition:type;

global appTypes::(Type ::= Type [Type]) = foldl(appType, _, _);

-- HUGE LIE: This is not a set. Well, maybe. We *might* depend on this being ordered.
function setUnionTyVars
[TyVar] ::= a::[TyVar] b::[TyVar]
{
  return a ++ removeAll(a, b);
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
