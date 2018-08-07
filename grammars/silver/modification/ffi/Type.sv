grammar silver:modification:ffi;

-- TODO: eventually, this type should carry around a translation type of some sort...

abstract production foreignType
top::Type ::= fn::String params::[Type]
{
  top.freeVariables = setUnionTyVarsAll(map((.freeVariables), params));
  top.substituted = foreignType(fn, mapSubst(params, top.substitution));
  top.flatRenamed = foreignType(fn, mapRenameSubst(params, top.substitution));
  top.typepp = fn ++ if !null(params) then "<" ++ implode(" ", mapTypePP(params, top.boundVariables)) ++ ">" else "";

  -- Unification.sv
  top.unify = 
    case top.unifyWith of
    | foreignType(ofn, op) ->
        if fn == ofn
        then unifyAll( params, op )
        else errorSubst("Tried to unify conflicting foreign types " ++ fn ++ " and " ++ ofn)
    | _ -> errorSubst("Tried to unify foreign type " ++ fn ++ " with " ++ prettyType(top.unifyWith))
    end;
}

