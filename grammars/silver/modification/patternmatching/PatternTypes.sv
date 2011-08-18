grammar silver:modification:patternmatching;
import silver:definition:core;
import silver:definition:env;
import silver:definition:concrete_syntax;
import silver:definition:type;
import silver:definition:type:syntax;
import silver:analysis:typechecking:core;
import silver:analysis:typechecking;
import silver:modification:let_fix;
import silver:extension:list; -- Oh no, this is a hack! TODO

concrete production prodAppPattern
p::Pattern ::= prod::QName '(' ps::PatternList ')'
{
  p.pp = prod.pp ++ "(" ++ ps.pp ++ ")" ;
  --p.location = prod.location;

  p.patternIsVariable = false;
  p.patternVariableName = nothing();
  p.patternSubPatternList = ps.patternList;
  p.patternSortKey = prod.lookupValue.fullName;
} 

concrete production wildcPattern
p::Pattern ::= '_'
{
  p.pp = "_" ;
  --p.location = loc(p.file, $1.line, $1.column);

  p.patternIsVariable = true;
  p.patternVariableName = nothing();
  p.patternSortKey = "~var";
}

concrete production varPattern
p::Pattern ::= v::Name
{
  p.pp = v.name;
 -- p.location = v.location;

  p.patternIsVariable = true;
  p.patternVariableName = just(v.name);
  p.patternSortKey = "~var";
}

--------------------------------------------------------------------------------

concrete production intPattern
p::Pattern ::= num::Int_t
{
  p.pp = num.lexeme ;
  --p.location = loc(p.file, num.line, num.column);
  
  p.patternIsVariable = false;
  p.patternVariableName = nothing();
  p.patternSubPatternList = [];
  p.patternSortKey = num.lexeme;
}

concrete production strPattern
p::Pattern ::= str::String_t
{
  p.pp = str.lexeme ;
  --p.location = loc(p.file, str.line, str.column);
  
  p.patternIsVariable = false;
  p.patternVariableName = nothing();
  p.patternSubPatternList = [];
  p.patternSortKey = str.lexeme;
}

concrete production truePattern
p::Pattern ::= 'true'
{
  p.pp = "true";
  --p.location = loc(p.file, $1.line, $1.column);
  
  p.patternIsVariable = false;
  p.patternVariableName = nothing();
  p.patternSubPatternList = [];
  p.patternSortKey = "true";
}

concrete production falsePattern
p::Pattern ::= 'false'
{
  p.pp = "false" ;
--  p.location = loc(p.file, $1.line, $1.column);
  
  p.patternIsVariable = false;
  p.patternVariableName = nothing();
  p.patternSubPatternList = [];
  p.patternSortKey = "false";
}

concrete production nilListPattern
p::Pattern ::= '[' ']'
{
  p.pp = "[]";
--  p.location = loc(p.file, $1.line, $1.column);
  
  p.patternIsVariable = false;
  p.patternVariableName = nothing();
  p.patternSubPatternList = [];
  p.patternSortKey = "core:nil";
}

concrete production consListPattern
p::Pattern ::= hp::Pattern '::' tp::Pattern
{
  p.pp = hp.pp ++ "::" ++ tp.pp;
--  p.location = loc(p.file, $2.line, $2.column);
  
  p.patternIsVariable = false;
  p.patternVariableName = nothing();
  p.patternSubPatternList = [hp, tp];
  p.patternSortKey = "core:cons";
}


