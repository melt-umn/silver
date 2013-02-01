grammar silver:extension:deprecation;

imports silver:definition:core;
imports silver:definition:env;

terminal Deprecated_kwd 'deprecated' lexer classes {KEYWORD};

-- ugh, the production convenience stuff is causing problems
--disambiguate Deprecated_kwd, IdLower_t { pluck Deprecated_kwd; }

concrete production deprecatedDecl
top::AGDcl ::= 'deprecated' s::String_t ';'
{
  top.pp = "deprecated" ++ s.lexeme ++ ";";
  top.location = loc(top.file, $1.line, $1.column);
  
  top.errors := [wrn(top.location, s.lexeme)];
  
  forwards to emptyAGDcl();
}

