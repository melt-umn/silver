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

concrete production intPattern
p::Pattern ::= num::Int_t
{
  p.pp = num.lexeme ;
  p.location = loc(p.file, num.line, num.column);
  
  p.patternIsVariable = false;
  p.patternProduction = nothing();
  p.patternVariableName = nothing();
  p.patternSubPatternList = decorate patternList_nil() with {};
}

concrete production strPattern
p::Pattern ::= str::String_t
{
  p.pp = str.lexeme ;
  p.location = loc(p.file, str.line, str.column);
  
  p.patternIsVariable = false;
  p.patternProduction = nothing();
  p.patternVariableName = nothing();
  p.patternSubPatternList = decorate patternList_nil() with {};
}

concrete production truePattern
p::Pattern ::= 'true'
{
  p.pp = "true";
  p.location = loc(p.file, $1.line, $1.column);
  
  p.patternIsVariable = false;
  p.patternProduction = nothing();
  p.patternVariableName = nothing();
  p.patternSubPatternList = decorate patternList_nil() with {};
}

concrete production falsePattern
p::Pattern ::= 'false'
{
  p.pp = "false" ;
  p.location = loc(p.file, $1.line, $1.column);
  
  p.patternIsVariable = false;
  p.patternProduction = nothing();
  p.patternVariableName = nothing();
  p.patternSubPatternList = decorate patternList_nil() with {};
}

concrete production nilListPattern
p::Pattern ::= '[' ']'
{
  p.pp = "[]";
  p.location = loc(p.file, $1.line, $1.column);
  
  p.patternIsVariable = false;
  p.patternProduction = nothing();
  p.patternVariableName = nothing();
  p.patternSubPatternList = decorate patternList_nil() with {};
}

concrete production consListPattern
p::Pattern ::= hp::Pattern '::' tp::Pattern
{
  p.pp = hp.pp ++ "::" ++ tp.pp;
  p.location = loc(p.file, $2.line, $2.column);
  
  p.patternIsVariable = false;
  p.patternProduction = nothing();
  p.patternVariableName = nothing();
  p.patternSubPatternList = decorate patternList_more(hp,',',patternList_one(tp)) with {}; -- TODO hahahahahahahahahahaha
  -- I can't wait to run into a bug and come find this sitting here someday!!
}


