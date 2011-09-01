grammar silver:modification:ffi;

-- TODO: eventually, this type should carry around a translation type of some sort...

abstract production foreignTypeExp
top::TypeExp ::= fn::String params::[TypeExp]
{
  top.freeVariables = setUnionTyVarsAll(mapFreeVariables(params));
  top.substituted = foreignTypeExp(fn, mapSubst(params, top.substitution));
  top.typepp = fn ++ if !null(params) then "<" ++ implode(" ", mapTypePP(params, top.boundVariables)) ++ ">" else "";

  -- Unification.sv
  top.unify = case top.unifyWith of
               foreignTypeExp(ofn, op) -> if fn == ofn
                                          then unifyAll( params, op )
                                          else errorSubst("Tried to unify conflicting foreign types " ++ fn ++ " and " ++ ofn)
             | _ -> errorSubst("Tried to unify foreign type " ++ fn ++ " with " ++ prettyType(top.unifyWith))
              end;

  -- env
  top.unparse = "foreigntype('" ++ fn ++ "', " ++ unparseTypes(params, top.boundVariables) ++ ")";
  
  top.typeHandler = nominalTypeForeign;
  
  forwards to defaultTypeExp();
}

abstract production nominalTypeForeign
top::Type ::= q::Decorated QNameUpper botl::BracketedOptTypeList
{
  top.errors := case q.lookupType.typerep of
                  foreignTypeExp(ofn, op) ->
                     if length(op) == length(botl.typelist.types)
                     then []
                     else [err(top.location, q.pp ++ " has " ++ toString(length(op)) ++ " type variables, but there are " ++ toString(length(botl.typelist.types)) ++ " supplied here.")]
                end;
  top.errors <- botl.typelist.errors;
  top.typerep = case q.lookupType.typerep of
                  foreignTypeExp(ofn, op) -> if length(op) == length(botl.typelist.types)
                                             then foreignTypeExp(ofn, botl.typelist.types)
                                             else freshenCompletely(q.lookupType.typerep)
                end;
}
