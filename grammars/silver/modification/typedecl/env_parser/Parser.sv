grammar silver:modification:typedecl:env_parser;

import silver:modification:typedecl hiding Type_t;

import silver:definition:env:env_parser;
import silver:definition:env;

import silver:definition:core only grammarName, location, env;

terminal TypeTerm          'type'        lexer classes {C_1};


concrete production aDclInfoType
top::IDclInfo ::= 'type' '(' l::ILocation ',' s::IName ',' td::ITyVarDcls ',' t::ITypeRep ')'
{
  t.env = newScopeEnv(td.defs, top.env);
  
  top.defs = [typeAliasDef(top.grammarName, l.alocation, s.aname, td.tyvars, t.typerep)];
}

