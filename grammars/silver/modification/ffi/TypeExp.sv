grammar silver:modification:ffi;
import silver:definition:core;
import silver:definition:env;
import silver:definition:type;

-- TODO: eventually, this type should carry around a translation type of some sort...

abstract production foreignTypeExp
top::TypeExp ::= fn::String
{
  top.freeVariables = []; -- TypeExp.sv
  top.substituted = top;  -- Substitutions.sv
  top.typepp = fn;      -- PrettyPrinting.sv

  -- Unification.sv
  top.unify = case top.unifyWith of
               foreignTypeExp(ofn) -> if fn == ofn
                                      then emptySubst()
                                      else errorSubst("Tried to unify conflicting foreign types " ++ fn ++ " and " ++ ofn)
             | _ -> errorSubst("Tried to unify foreign type " ++ fn ++ " with " ++ prettyType(top.unifyWith))
              end;

  -- env
  top.unparse = "foreigntype('" ++ fn ++ "')";
  
  forwards to defaultTypeExp();
}

