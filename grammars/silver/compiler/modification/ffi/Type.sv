grammar silver:compiler:modification:ffi;

abstract production foreignType
top::Type ::= fn::String  transType::String  params::[Type]
{
  top.typeName = fn;
  top.freeVariables = setUnionTyVarsAll(map((.freeVariables), params));
  top.freeSkolemVars := setUnionTyVarsAll(map((.freeSkolemVars), params));
  top.freeFlexibleVars := setUnionTyVarsAll(map((.freeFlexibleVars), params));
  top.substituted = foreignType(fn, transType, mapSubst(params, top.substitution));
  top.flatRenamed = foreignType(fn, transType, mapRenameSubst(params, top.substitution));
  top.typepp = fn ++ if !null(params) then "<" ++ implode(" ", map(prettyTypeWith(_, top.boundVariables), params)) ++ ">" else "";
  top.kindrep = starKind();
  top.isTypeable = false; -- TODO: May want to add extra syntax to indicate that a foreign type's translation is an instance of Typed

  -- Unification.sv
  top.unify = 
    case top.unifyWith of
    | foreignType(ofn, _, op) ->
        if fn == ofn
        then unifyAll( params, op )
        else errorSubst("Tried to unify conflicting foreign types " ++ fn ++ " and " ++ ofn)
    | _ -> errorSubst("Tried to unify foreign type " ++ fn ++ " with " ++ prettyType(top.unifyWith))
    end;
}

-- What we get from the standard library's declaration, so we don't need to repeat it
global ioForeignType :: Type =
  foreignType("silver:core:IO", "common.IOToken", []);

