grammar silver:modification:autocopyattr:env_parser;
import silver:modification:autocopyattr hiding AutoCopy_kwd; -- TODO: hiding is a bit of hack?
import silver:definition:env;
import silver:definition:env:parser;

import silver:definition:core only grammarName, location;

terminal AutoCopyTerm 'autocopy' lexer classes {C_1};

concrete production aDclInfoAutoCopy
top::aDclInfo ::= 'autocopy' '(' l::aLocation ',' fn::Name ',' t::aTypeRep ')'
{
  top.defs = addAutocopyDcl(top.grammarName, l.location, fn.aname, t.typerep, emptyDefs());
}

