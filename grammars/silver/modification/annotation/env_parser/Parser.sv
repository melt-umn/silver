grammar silver:modification:annotation:env_parser;

import silver:modification:annotation;
import silver:definition:env:env_parser;
import silver:definition:core only grammarName;
import silver:definition:env;
import silver:definition:type;

terminal Anno_t 'anno' lexer classes {C_1};
terminal AnnoAt_t 'anno@' lexer classes {C_1};

concrete production aDclInfoAnno
top::IDclInfo ::= 'anno' '(' l::ILocation ',' fn::IName ',' td::ITyVarDcls ',' t::ITypeRep ')'
{
  t.env = newScopeEnv(td.defs, top.env);
  
  top.defs = [annoDef(top.grammarName, l.location, fn.aname, td.tyvars, t.typerep)];
}

concrete production aDclInfoAnnoInstance
top::IDclInfo ::= 'anno@' '(' l::ILocation ',' fnnt::IName ',' fnat::IName ',' td::ITyVarDcls ',' ntt::ITypeRep ',' att::ITypeRep ')'
{
  ntt.env = newScopeEnv(td.defs, top.env);
  att.env = ntt.env;
  
  local attribute fresh :: [TyVar];
  fresh = freshTyVars(length(td.tyvars));

  -- Recall that constraint on occurs DclInfos: the types need to be tyvars, not skolem constants.
  
  top.defs = [
    annoInstanceDef(top.grammarName, l.location, fnnt.aname, fnat.aname, 
      freshenTypeExpWith(ntt.typerep, td.tyvars, fresh),
      freshenTypeExpWith(att.typerep, td.tyvars, fresh))];
}

