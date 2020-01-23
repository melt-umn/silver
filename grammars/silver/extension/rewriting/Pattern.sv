grammar silver:extension:rewriting;

synthesized attribute transform<a>::a;

attribute transform<Strategy> occurs on MRuleList, MatchRule;

synthesized attribute wrappedMatchRuleList :: [AbstractMatchRule] occurs on MRuleList, MatchRule;

autocopy attribute decRuleExprsIn::[Pair<String Decorated Expr>] occurs on MRuleList, MatchRule;
inherited attribute ruleIndex::Integer occurs on MRuleList, MatchRule;

aspect production mRuleList_one
top::MRuleList ::= m::MatchRule
{
  top.transform = m.transform;
  top.wrappedMatchRuleList = m.wrappedMatchRuleList;
  m.ruleIndex = top.ruleIndex;
}

aspect production mRuleList_cons
top::MRuleList ::= h::MatchRule '|' t::MRuleList
{
  top.transform = choice(h.transform, t.transform);
  top.wrappedMatchRuleList = h.wrappedMatchRuleList ++ t.wrappedMatchRuleList;
  h.ruleIndex = top.ruleIndex;
  t.ruleIndex = top.ruleIndex + 1;
}

aspect production matchRule_c
top::MatchRule ::= pt::PatternList _ e::Expr
{
  -- Awful hack: pattern match type checking is happens on the forward "primitive match".
  -- However, we 
  top.transform =
    rewriteRule(
      pt.firstTransform,
      case lookupBy(stringEq, toString(top.ruleIndex), top.decRuleExprsIn) of
      | just(e) -> e.transform
      | nothing() -> error("Failed to find decorated RHS " ++ toString(top.ruleIndex))
      end);
  top.wrappedMatchRuleList =
    [matchRule(
      pt.patternList, nothing(),
      hackWrapKey(toString(top.ruleIndex), e, location=e.location),
      location=top.location)];
}

aspect production matchRuleWhen_c
top::MatchRule ::= pt::PatternList 'when' cond::Expr _ e::Expr
{
  top.transform =
    require(
      pt.firstTransform,
      case lookupBy(stringEq, toString(top.ruleIndex) ++ "_cond", top.decRuleExprsIn) of
      | just(e) -> e.transform
      | nothing() -> error("Failed to find decorated RHS " ++ toString(top.ruleIndex) ++ "_cond")
      end) <*
    rewriteRule(
      pt.firstTransform,
      case lookupBy(stringEq, toString(top.ruleIndex), top.decRuleExprsIn) of
      | just(e) -> e.transform
      | nothing() -> error("Failed to find decorated RHS " ++ toString(top.ruleIndex))
      end);
  top.wrappedMatchRuleList =
    [matchRule(
      pt.patternList,
      just(hackWrapKey(toString(top.ruleIndex) ++ "_cond", cond, location=e.location)),
      hackWrapKey(toString(top.ruleIndex), e, location=e.location),
      location=top.location)];
}

abstract production hackWrapKey
top::Expr ::= key::String e::Expr
{
  top.unparse = s"key(${key}, ${e.unparse})";
  top.decRuleExprs = [pair(key, forward)];
  forwards to e;
}

aspect production caseExpr_c
top::Expr ::= 'case' es::Exprs 'of' _ ml::MRuleList 'end'
{
  ml.ruleIndex = 0;
}

attribute transform<ASTExprs> occurs on PatternList;
synthesized attribute firstTransform::ASTExpr occurs on PatternList;

aspect production patternList_one
top::PatternList ::= p::Pattern
{
  top.transform = consASTExpr(p.transform, nilASTExpr());
  top.firstTransform = p.transform;
}
aspect production patternList_more
top::PatternList ::= p::Pattern ',' ps::PatternList
{
  top.transform = consASTExpr(p.transform, ps.transform);
  top.firstTransform = p.transform;
}

aspect production patternList_nil
top::PatternList ::=
{
  top.transform = nilASTExpr();
  top.firstTransform = error("Empty pattern list");
}

attribute transform<NamedASTExprs> occurs on NamedPatternList;

aspect production namedPatternList_one
top::NamedPatternList ::= p::NamedPattern
{
  top.transform = consNamedASTExpr(p.transform, nilNamedASTExpr());
}
aspect production namedPatternList_more
top::NamedPatternList ::= p::NamedPattern ',' ps::NamedPatternList
{
  top.transform = consNamedASTExpr(p.transform, ps.transform);
}

aspect production namedPatternList_nil
top::NamedPatternList ::=
{
  top.transform = nilNamedASTExpr();
}

attribute transform<NamedASTExpr> occurs on NamedPattern;

aspect production namedPattern
top::NamedPattern ::= qn::QName '=' p::Pattern
{
  top.transform = namedASTExpr(qn.lookupAttribute.fullName, p.transform);
}

attribute transform<ASTExpr> occurs on Pattern;

aspect production prodAppPattern_named
top::Pattern ::= prod::QName '(' ps::PatternList ',' nps::NamedPatternList ')'
{
  top.transform =
    prodCallASTExpr(prod.lookupValue.fullName, ps.transform, nps.transform);
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
}

-- Primitive pattern stuff
aspect production onePattern
top::PrimPatterns ::= p::PrimPattern
{
  top.decRuleExprs = p.decRuleExprs;
}
aspect production consPattern
top::PrimPatterns ::= p::PrimPattern '|' ps::PrimPatterns
{
  top.decRuleExprs = p.decRuleExprs ++ ps.decRuleExprs;
}

aspect production prodPatternNormal
top::PrimPattern ::= qn::Decorated QName  ns::VarBinders  e::Expr
{
  top.decRuleExprs = e.decRuleExprs;
  e.boundVars = top.boundVars ++ ns.varBindings;
}

aspect production prodPatternGadt
top::PrimPattern ::= qn::Decorated QName  ns::VarBinders  e::Expr
{
  top.decRuleExprs = e.decRuleExprs;
  e.boundVars = top.boundVars ++ ns.varBindings;
}

aspect production integerPattern
top::PrimPattern ::= i::Int_t _ e::Expr
{
  top.decRuleExprs = e.decRuleExprs;
}
aspect production floatPattern
top::PrimPattern ::= f::Float_t _ e::Expr
{
  top.decRuleExprs = e.decRuleExprs;
}
aspect production stringPattern
top::PrimPattern ::= s::String_t _ e::Expr
{
  top.decRuleExprs = e.decRuleExprs;
}
aspect production booleanPattern
top::PrimPattern ::= i::String _ e::Expr
{
  top.decRuleExprs = e.decRuleExprs;
}
aspect production nilPattern
top::PrimPattern ::= e::Expr
{
  top.decRuleExprs = e.decRuleExprs;
}
aspect production conslstPattern
top::PrimPattern ::= h::Name t::Name e::Expr
{
  top.decRuleExprs = e.decRuleExprs;
}

synthesized attribute varBindings::[String] occurs on VarBinders, VarBinder;

aspect production oneVarBinder
top::VarBinders ::= v::VarBinder
{
  top.varBindings = v.varBindings;
}
aspect production consVarBinder
top::VarBinders ::= v::VarBinder ',' vs::VarBinders
{
  top.varBindings = v.varBindings ++ vs.varBindings;
}
aspect production nilVarBinder
top::VarBinders ::=
{
  top.varBindings = [];
}

aspect production varVarBinder
top::VarBinder ::= n::Name
{
  top.varBindings = [n.name];
}
aspect production ignoreVarBinder
top::VarBinder ::= '_'
{
  top.varBindings = [];
}
