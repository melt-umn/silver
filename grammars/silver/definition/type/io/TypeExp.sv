grammar silver:definition:type:io;

import silver:definition:type;
import silver:definition:env;

abstract production ioTypeExp
top::TypeExp ::=
{
  top.freeVariables = []; -- TypeExp.sv
  top.substituted = top;  -- Substitutions.sv
  top.typepp = "IO";      -- PrettyPrinting.sv

  top.unify = case top.unifyWith of
               ioTypeExp() -> emptySubst()
             | _ -> errorSubst("Tried to unify IO with " ++ prettyType(top.unifyWith))
              end;
  
  top.unparse = "io";
}

