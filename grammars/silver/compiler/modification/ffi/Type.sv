grammar silver:compiler:modification:ffi;

abstract production foreignType
top::Type ::= fn::String  transType::String  params::[Type]
{
  top.typeName = fn;
  top.freeVariables = setUnionTyVarsAll(map((.freeVariables), params));
  top.substituted = foreignType(fn, transType, mapSubst(params, top.substitution));
  top.flatRenamed = foreignType(fn, transType, mapRenameSubst(params, top.substitution));
  top.typepp = fn ++ if !null(params) then "<" ++ implode(" ", mapTypePP(params, top.boundVariables)) ++ ">" else "";
  top.kindArity = 0;

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

