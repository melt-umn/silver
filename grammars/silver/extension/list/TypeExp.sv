grammar silver:extension:list;

import silver:definition:type;
import silver:definition:env;
import silver:definition:core;

abstract production listTypeExp
top::TypeExp ::= el::TypeExp
{
  top.freeVariables = el.freeVariables; -- TypeExp.sv
  top.substituted = listTypeExp(el.substituted);
  top.typepp = "[" ++ el.typepp ++ "]";

  top.unify = case top.unifyWith of
               listTypeExp(fel) -> unify(el,fel)
             | decoratedTypeExp(nonterminalTypeExp("core:List", ftes)) -> unify(el, head(ftes))
             | _ -> errorSubst("Tried to unify list with " ++ prettyType(top.unifyWith))
              end;
  
  -- Suppress its "nonterminal"ness
  top.isDecorable = false;
  top.isDecorated = false;
  --top.accessDispatcher = errorAccessDispatcher; -- permit this, since we need it for default, non-specialized java version
  top.lengthDispatcher = listLengthBouncer;
  top.appendDispatcher = listPlusPlus;
  
  top.unparse = "[" ++ el.unparse ++ "]";
  --top.transType -- for translation.
  
  forwards to decoratedTypeExp(nonterminalTypeExp("core:List", [el]));
}

