grammar silver:extension:testing;

import silver:definition:core;
import silver:definition:env;

terminal WrongCode_kwd 'wrongCode' lexer classes {KEYWORD};

-- ugh, the production convenience stuff is causing problems
--disambiguate WrongCode_kwd, IdLower_t { pluck WrongCode_kwd; }

concrete production wrongDecl
top::AGDcl ::= 'wrongCode' s::String_t '{' ags::AGDcls '}'
{
  top.pp = "wrongCode" ++ s.lexeme ++ "{" ++ ags.pp ++ "}";
  top.location = loc(top.file, $1.line, $1.column);
  
  top.errors := if indexOf(substring(1,length(s.lexeme)-1,s.lexeme),
                          foldMessages(ags.errors)) == -1
               then [err(top.location, "Wrong code did not raise an error containing " ++ s.lexeme ++ ". Bubbling up errors from lines " ++ toString($3.line) ++ " to " ++ toString($5.line))] ++ ags.errors
               else [];
  
  -- do extend its environment with its defs
  ags.env = newScopeEnv(ags.defs, top.env);
  
  forwards to emptyAGDcl();
}

