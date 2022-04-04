grammar silver:compiler:extension:abella_compilation:encoding;

import silver:compiler:extension:patternmatching;


aspect production caseExpr
top::Expr ::= es::[Expr] ml::[AbstractMatchRule] complete::Boolean failExpr::Expr retType::Type
{
  
}



attribute
   encodedExpr, encodedFailure, encodingEnv, top,
   matchValue, matchType, noMatch
occurs on AbstractMatchRule;

--(tree name, tree structure, node tree) if nonterminal (left)
--thing to match if primitive (right)
inherited attribute matchValue::Either<(Term, Term, Term) Term>;
--type of the thing we're trying to match
inherited attribute matchType::AbellaType;
--the value doesn't match the current pattern
synthesized attribute noMatch::[Metaterm];

aspect production matchRule
top::AbstractMatchRule ::= pl::[Decorated Pattern]
     --Condition, if it exists, is either a Boolean expression or a
     --   pair of an expression and a pattern for it to match
     cond::Maybe<Pair<Expr Maybe<Pattern>>> e::Expr
{
  --pattern/condition matchs and the expression evaluates
  top.encodedExpr = error("not done");

  --pattern/condition matches and the expression does not evaluate AND
  --pattern matches but condition somehow fails
  top.encodedFailure = error("not done");

  --pattern doesn't match or condition isn't true
  top.noMatch = error("not done");
}






aspect production prodAppPattern_named
top::Pattern ::= prod::QName '(' ps::PatternList ',' nps::NamedPatternList ')'
{
}

aspect production wildcPattern
top::Pattern ::= '_'
{
}

aspect production varPattern
top::Pattern ::= v::Name
{
}

aspect production errorPattern
top::Pattern ::= msg::[Message]
{
}

aspect production intPattern
top::Pattern ::= num::Int_t
{
}

aspect production fltPattern
top::Pattern ::= num::Float_t
{
}

aspect production strPattern
top::Pattern ::= str::String_t
{
}

aspect production truePattern
top::Pattern ::= 'true'
{
}

aspect production falsePattern
top::Pattern ::= 'false'
{
}

aspect production nilListPattern
top::Pattern ::= '[' ']'
{

}

aspect production consListPattern
top::Pattern ::= hp::Pattern '::' tp::Pattern
{

}




aspect production patternList_one
top::PatternList ::= p::Pattern
{
  
}
aspect production patternList_more
top::PatternList ::= p::Pattern ',' ps1::PatternList
{
  
}
aspect production patternList_nil
top::PatternList ::=
{
  
}

