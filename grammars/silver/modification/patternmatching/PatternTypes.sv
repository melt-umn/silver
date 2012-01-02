grammar silver:modification:patternmatching;

import silver:definition:core;
import silver:definition:env;
import silver:extension:list only LSqr_t, RSqr_t;

nonterminal Pattern with pp, env, file, patternIsVariable, patternVariableName, patternSubPatternList, patternSortKey;

{--
 - False if it actually matches anything specific, true if it's a variable/wildcard.
 -}
synthesized attribute patternIsVariable :: Boolean;
{--
 - The name of the variable, if any (e.g. wildcards!)
 -}
synthesized attribute patternVariableName :: Maybe<String>;
{--
 - Each child pattern below this one.
 -}
synthesized attribute patternSubPatternList :: [Decorated Pattern];
{--
 - The sort key of the pattern.
 - "~var" if patternIsVariable is true.
 - fullname if it's a production.
 - otherwise, it is type-depedent, but same values should be the same!
 -}
synthesized attribute patternSortKey :: String;

concrete production prodAppPattern
p::Pattern ::= prod::QName '(' ps::PatternList ')'
{
  p.pp = prod.pp ++ "(" ++ ps.pp ++ ")" ;

  p.patternIsVariable = false;
  p.patternVariableName = nothing();
  p.patternSubPatternList = ps.patternList;
  p.patternSortKey = prod.lookupValue.fullName;
} 

concrete production wildcPattern
p::Pattern ::= '_'
{
  p.pp = "_" ;

  p.patternIsVariable = true;
  p.patternVariableName = nothing();
  p.patternSortKey = "~var";
}

concrete production varPattern
p::Pattern ::= v::Name
{
  p.pp = v.name;

  p.patternIsVariable = true;
  p.patternVariableName = just(v.name);
  p.patternSortKey = "~var";
}

--------------------------------------------------------------------------------

concrete production intPattern
p::Pattern ::= num::Int_t
{
  p.pp = num.lexeme ;
  
  p.patternIsVariable = false;
  p.patternVariableName = nothing();
  p.patternSubPatternList = [];
  p.patternSortKey = num.lexeme;
}

concrete production strPattern
p::Pattern ::= str::String_t
{
  p.pp = str.lexeme ;
  
  p.patternIsVariable = false;
  p.patternVariableName = nothing();
  p.patternSubPatternList = [];
  p.patternSortKey = str.lexeme;
}

concrete production truePattern
p::Pattern ::= 'true'
{
  p.pp = "true";
  
  p.patternIsVariable = false;
  p.patternVariableName = nothing();
  p.patternSubPatternList = [];
  p.patternSortKey = "true";
}

concrete production falsePattern
p::Pattern ::= 'false'
{
  p.pp = "false" ;
  
  p.patternIsVariable = false;
  p.patternVariableName = nothing();
  p.patternSubPatternList = [];
  p.patternSortKey = "false";
}

concrete production nilListPattern
p::Pattern ::= '[' ']'
{
  p.pp = "[]";
  
  p.patternIsVariable = false;
  p.patternVariableName = nothing();
  p.patternSubPatternList = [];
  p.patternSortKey = "core:nil";
}

concrete production consListPattern
p::Pattern ::= hp::Pattern '::' tp::Pattern
{
  p.pp = hp.pp ++ "::" ++ tp.pp;
  
  p.patternIsVariable = false;
  p.patternVariableName = nothing();
  p.patternSubPatternList = [hp, tp];
  p.patternSortKey = "core:cons";
}

