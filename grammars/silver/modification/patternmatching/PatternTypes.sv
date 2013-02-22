grammar silver:modification:patternmatching;

import silver:definition:core;
import silver:definition:env;
import silver:extension:list only LSqr_t, RSqr_t;

-- See comment in pattern.sv regarding errors #HACK2012
nonterminal Pattern with location, config, pp, env, file, errors, patternIsVariable, patternVariableName, patternSubPatternList, patternSortKey;

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
  p.pp = prod.pp ++ "(" ++ ps.pp ++ ")";
  p.location = prod.location;
  p.errors := ps.errors;

  p.patternIsVariable = false;
  p.patternVariableName = nothing();
  p.patternSubPatternList = ps.patternList;
  p.patternSortKey = prod.lookupValue.fullName;
} 

concrete production wildcPattern
p::Pattern ::= '_'
{
  p.pp = "_";
  p.location = $1.location;
  p.errors := [];

  p.patternIsVariable = true;
  p.patternVariableName = nothing();
  p.patternSubPatternList = [];
  p.patternSortKey = "~var";
}

concrete production varPattern
p::Pattern ::= v::Name
{
  p.pp = v.name;
  p.location = v.location;
  -- MUST start with lower case #HACK2012
  p.errors := (if isUpper(substring(0,1,v.name))
                 then [err(v.location, "Pattern variable names start with a lower case letter")]
                 else [])
  -- MUST NOT shadow any _production_ names #HACK2012
  -- TODO: Add function to find all prodDcl in env
             ++ (case getValueDcl(v.name, p.env) of
                 | prodDcl(_,_,_) :: _ -> [err(v.location, "Production name can't be used in pattern")]
                 | _ -> []
                 end);


  p.patternIsVariable = true;
  p.patternVariableName = just(v.name);
  p.patternSubPatternList = [];
  p.patternSortKey = "~var";
}

--------------------------------------------------------------------------------

concrete production intPattern
p::Pattern ::= num::Int_t
{
  p.pp = num.lexeme;
  p.location = $1.location;
  p.errors := [];
  
  p.patternIsVariable = false;
  p.patternVariableName = nothing();
  p.patternSubPatternList = [];
  p.patternSortKey = num.lexeme;
}

concrete production strPattern
p::Pattern ::= str::String_t
{
  p.pp = str.lexeme;
  p.location = $1.location;
  p.errors := [];
  
  p.patternIsVariable = false;
  p.patternVariableName = nothing();
  p.patternSubPatternList = [];
  p.patternSortKey = str.lexeme;
}

concrete production truePattern
p::Pattern ::= 'true'
{
  p.pp = "true";
  p.location = $1.location;
  p.errors := [];
  
  p.patternIsVariable = false;
  p.patternVariableName = nothing();
  p.patternSubPatternList = [];
  p.patternSortKey = "true";
}

concrete production falsePattern
p::Pattern ::= 'false'
{
  p.pp = "false";
  p.location = $1.location;
  p.errors := [];
  
  p.patternIsVariable = false;
  p.patternVariableName = nothing();
  p.patternSubPatternList = [];
  p.patternSortKey = "false";
}

concrete production nilListPattern
p::Pattern ::= '[' ']'
{
  p.pp = "[]";
  p.location = $1.location;
  p.errors := [];
  
  p.patternIsVariable = false;
  p.patternVariableName = nothing();
  p.patternSubPatternList = [];
  p.patternSortKey = "core:nil";
}

concrete production consListPattern
p::Pattern ::= hp::Pattern '::' tp::Pattern
{
  p.pp = hp.pp ++ "::" ++ tp.pp;
  p.location = $2.location;
  p.errors := hp.errors ++ tp.errors;
  
  p.patternIsVariable = false;
  p.patternVariableName = nothing();
  p.patternSubPatternList = [hp, tp];
  p.patternSortKey = "core:cons";
}

