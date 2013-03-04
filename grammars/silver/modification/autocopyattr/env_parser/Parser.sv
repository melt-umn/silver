grammar silver:modification:autocopyattr:env_parser;
import silver:modification:autocopyattr hiding AutoCopy_kwd; -- TODO: hiding is a bit of hack?
import silver:definition:env;
import silver:definition:env:env_parser;

import silver:definition:core only grammarName, location, env;

terminal AutoCopyTerm 'autocopy' lexer classes {C_1};

concrete production aDclInfoAutoCopy
top::IDclInfo ::= 'autocopy' '(' l::ILocation ',' fn::IName ',' td::ITyVarDcls ',' t::ITypeRep ')'
{
  t.env = newScopeEnv(td.defs, top.env);
  
  top.defs = [autocopyDef(top.grammarName, l.alocation, fn.aname, td.tyvars, t.typerep)];
}

