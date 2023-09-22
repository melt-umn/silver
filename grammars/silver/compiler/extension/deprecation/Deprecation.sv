grammar silver:compiler:extension:deprecation;

imports silver:compiler:definition:core;
imports silver:compiler:definition:env;

terminal Deprecated_kwd 'deprecated' lexer classes {KEYWORD};

-- ugh, the production convenience stuff is causing problems
--disambiguate Deprecated_kwd, IdLower_t { pluck Deprecated_kwd; }

concrete production deprecatedDecl
top::AGDcl ::= 'deprecated' s::String_t ';'
{
  top.unparse = "deprecated" ++ s.lexeme ++ ";";
  
  top.errors := [wrnFromOrigin(top, s.lexeme)];
  
  forwards to emptyAGDcl();
}

