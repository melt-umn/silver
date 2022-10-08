grammar silver:compiler:extension:implicit_monads;

aspect production prodAppPattern
top::Pattern ::= prod::QName '(' ps::PatternList ')'
{
  -- TODO: is this right?  Seems like we should unify with ps pattern types?
  top.patternType = prod.lookupValue.typeScheme.typerep.outputType;
} 

aspect production prodAppPattern_named
top::Pattern ::= prod::QName '(' ps::PatternList ',' nps::NamedPatternList ')'
{
  top.patternType = prod.lookupValue.typeScheme.typerep.outputType;
}

aspect production wildcPattern
top::Pattern ::= '_'
{
  top.patternType = freshType();
}

aspect production varPattern
top::Pattern ::= v::Name
{
  top.patternType = freshType();
}

aspect production errorPattern
top::Pattern ::= msg::[Message]
{
  top.patternType = errorType();
}

aspect production intPattern
top::Pattern ::= num::Int_t
{
  top.patternType = intType();
}

aspect production fltPattern
top::Pattern ::= num::Float_t
{
  top.patternType = floatType();
}

aspect production strPattern
top::Pattern ::= str::String_t
{
  top.patternType = stringType();
}

aspect production truePattern
top::Pattern ::= 'true'
{
  top.patternType = boolType();
}

aspect production falsePattern
top::Pattern ::= 'false'
{
  top.patternType = boolType();
}

aspect production nilListPattern
top::Pattern ::= '[' ']'
{
  top.patternType = listType(freshType());
}

aspect production consListPattern
top::Pattern ::= hp::Pattern '::' tp::Pattern
{
  top.patternType = listType(hp.patternType);
}
