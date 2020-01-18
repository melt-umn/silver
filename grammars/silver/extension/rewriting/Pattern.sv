grammar silver:extension:rewriting;

synthesized attribute transform<a>::a;

attribute transform<Strategy> occurs on MRuleList, MatchRule;

aspect production mRuleList_one
top::MRuleList ::= m::MatchRule
{
  top.transform = m.transform;
}

aspect production mRuleList_cons
top::MRuleList ::= h::MatchRule '|' t::MRuleList
{
  top.transform = choice(h.transform, t.transform);
}

aspect production matchRule_c
top::MatchRule ::= pt::PatternList _ e::Expr
{
  top.transform = rewriteRule(pt.firstTransform, e.transform);
  e.boundVars = pt.varBindings;
}

aspect production matchRuleWhen_c
top::MatchRule ::= pt::PatternList 'when' cond::Expr _ e::Expr
{
  top.transform =
    sequence(
      require(pt.firstTransform, cond.transform),
      rewriteRule(pt.firstTransform, e.transform));
  cond.boundVars = pt.varBindings;
  e.boundVars = pt.varBindings;
}

attribute transform<ASTExprs> occurs on PatternList;
synthesized attribute firstTransform::ASTExpr occurs on PatternList;

synthesized attribute varBindings::[String] occurs on PatternList, Pattern;

aspect production patternList_one
top::PatternList ::= p::Pattern
{
  top.transform = consASTExpr(p.transform, nilASTExpr());
  top.firstTransform = p.transform;
  top.varBindings = p.varBindings;
}
aspect production patternList_more
top::PatternList ::= p::Pattern ',' ps::PatternList
{
  top.transform = consASTExpr(p.transform, ps.transform);
  top.firstTransform = p.transform;
  top.varBindings = p.varBindings ++ ps.varBindings;
}

aspect production patternList_nil
top::PatternList ::=
{
  top.transform = nilASTExpr();
  top.firstTransform = error("Empty pattern list");
  top.varBindings = [];
}

attribute transform<ASTExpr> occurs on Pattern;

aspect default production
top::Pattern ::=
{
  top.varBindings = [];
}

aspect production prodAppPattern
top::Pattern ::= prod::QName '(' ps::PatternList ')'
{
  top.transform =
    prodCallASTExpr(prod.lookupValue.fullName, ps.transform, nilNamedASTExpr());
  top.varBindings = ps.varBindings;
} 

aspect production wildcPattern
top::Pattern ::= '_'
{
  top.transform = wildASTExpr();
}

aspect production varPattern
top::Pattern ::= v::Name
{
  top.transform = varASTExpr(v.name);
  top.varBindings = [v.name];
}

aspect production errorPattern
top::Pattern ::= msg::[Message]
{
  top.transform = error("transform undefined in the presence of errors");
}

aspect production intPattern
top::Pattern ::= num::Int_t
{
  top.transform = integerASTExpr(toInteger(num.lexeme));
}

aspect production fltPattern
top::Pattern ::= num::Float_t
{
  top.transform = floatASTExpr(toFloat(num.lexeme));
}

aspect production strPattern
top::Pattern ::= str::String_t
{
  top.transform = stringASTExpr(unescapeString(substring(1, length(str.lexeme) - 1, str.lexeme)));
}

aspect production truePattern
top::Pattern ::= 'true'
{
  top.transform = booleanASTExpr(true);
}

aspect production falsePattern
top::Pattern ::= 'false'
{
  top.transform = booleanASTExpr(false);
}

aspect production nilListPattern
top::Pattern ::= '[' ']'
{
  top.transform = nilListASTExpr();
}

aspect production consListPattern
top::Pattern ::= hp::Pattern '::' tp::Pattern
{
  top.transform = consListASTExpr(hp.transform, tp.transform);
  top.varBindings = hp.varBindings ++ tp.varBindings;
}
