grammar silver:modification:autocopyattr:env_parser;
import silver:modification:autocopyattr hiding AutoCopy_kwd; -- TODO: hiding is a bit of hack?
import silver:definition:env;
import silver:definition:env:parser;

import silver:definition:core only grammarName, location, env;

terminal AutoCopyTerm 'autocopy' lexer classes {C_1};

concrete production aDclInfoAutoCopy
top::IDclInfo ::= 'autocopy' '(' l::ILocation ',' fn::Name ',' td::ITyVarDcls ',' t::ITypeRep ')'
{
  t.env = newScopeEnv(td.defs, top.env);
  
  top.defs = addAutocopyDcl(top.grammarName, l.location, fn.aname, td.tyvars, t.typerep, emptyDefs());
}

