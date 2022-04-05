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
  --encoding environment produced by the patterns with their vars
  local pattEncodingEnv::[(String, (Term, Term))] =
        buildPattEncodingEnv(top.encodingEnv, pl);

  --pattern/condition matches and the expression evaluates
  top.encodedExpr = error("not done");

  --pattern/condition matches and the expression does not evaluate AND
  --pattern matches but condition somehow fails
  top.encodedFailure = error("not done");

  --pattern doesn't match or condition isn't true
  top.noMatch = error("not done");
}


--Build the encoding environment from the patterns in a match rule
function buildPattEncodingEnv
[(String, (Term, Term))] ::= env::[(String, (Term, Term))]
                             patts::[Decorated Pattern]
{
  return
     case patts of
     | [] -> env
     | p::tl ->
       buildPattEncodingEnv(
          decorate p with {encodingEnv=env;}.encodingEnv_up, tl)
     end;
}





attribute
   encodingEnv, encodingEnv_up, encodedPatt
occurs on Pattern;

synthesized attribute encodedPatt::AbellaTerm;


aspect production prodAppPattern_named
top::Pattern ::= prod::QName '(' ps::PatternList ',' nps::NamedPatternList ')'
{
  ps.encodingEnv = top.encodingEnv;
  top.encodingEnv_up = ps.encodingEnv_up;
}

aspect production wildcPattern
top::Pattern ::= '_'
{
  top.encodingEnv_up = top.encodingEnv;
}

aspect production varPattern
top::Pattern ::= v::Name
{
  local varTree::Term = varTerm(capitalize(v.name), genInt());
  local varNodeTree::Term =
        varTerm(capitalize(v.name) ++ "NTr", genInt());
  top.encodingEnv_up =
      (v.name, (varTree, varNodeTree))::top.encodingEnv;
}

aspect production errorPattern
top::Pattern ::= msg::[Message]
{
  top.encodingEnv_up = top.encodingEnv;
}

aspect production intPattern
top::Pattern ::= num::Int_t
{
  top.encodingEnv_up = top.encodingEnv;
}

aspect production fltPattern
top::Pattern ::= num::Float_t
{
  top.encodingEnv_up = top.encodingEnv;
}

aspect production strPattern
top::Pattern ::= str::String_t
{
  top.encodingEnv_up = top.encodingEnv;
}

aspect production truePattern
top::Pattern ::= 'true'
{
  top.encodingEnv_up = top.encodingEnv;
}

aspect production falsePattern
top::Pattern ::= 'false'
{
  top.encodingEnv_up = top.encodingEnv;
}

aspect production nilListPattern
top::Pattern ::= '[' ']'
{
  top.encodingEnv_up = top.encodingEnv;
}

aspect production consListPattern
top::Pattern ::= hp::Pattern '::' tp::Pattern
{
  hp.encodingEnv = top.encodingEnv;
  tp.encodingEnv = hp.encodingEnv_up;
  top.encodingEnv_up = tp.encodingEnv_up;
}



attribute
   encodingEnv, encodingEnv_up, encodedPattList
occurs on PatternList;

synthesized attribute encodedPattList::[Term];

aspect production patternList_one
top::PatternList ::= p::Pattern
{
  p.encodingEnv = top.encodingEnv;
  top.encodingEnv_up = p.encodingEnv_up;

  top.encodedPattList = [p.encodedPatt];
}
aspect production patternList_more
top::PatternList ::= p::Pattern ',' ps1::PatternList
{
  p.encodingEnv = top.encodingEnv;
  ps1.encodingEnv = p.encodingEnv_up;
  top.encodingEnv_up = ps1.encodingEnv_up;

  top.encodedPattList = p.encodedPatt::ps1.encodedPattList;
}
aspect production patternList_nil
top::PatternList ::=
{
  top.encodingEnv_up = top.encodingEnv;

  top.encodedPattList = [];
}

