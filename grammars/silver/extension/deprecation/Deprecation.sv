grammar silver:extension:deprecation;

import silver:definition:core;
import silver:definition:env;

terminal Deprecated_kwd 'deprecated' ; -- no lexer class!

-- ugh, the production convenience stuff is causing problems
disambiguate Deprecated_kwd, IdLower_t { pluck Deprecated_kwd; }

concrete production deprecatedDecl
top::AGDcl ::= 'deprecated' s::String_t ';'
{
  top.pp = "deprecated" ++ s.lexeme ++ ";";
  top.location = loc(top.file, $1.line, $1.column);
  
  top.errors := [];
  top.warnings := [wrn(top.location, s.lexeme)];
  
  forwards to agDclDefault();
}

