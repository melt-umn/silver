grammar silver:extension:strategyattr;

inherited attribute attrName::String; -- Used to generate the names of lifted strategy attributes

monoid attribute liftedAttrs::[Pair<String StrategyExpr>] with [], ++;
synthesized attribute isId::Boolean;
synthesized attribute isFail::Boolean;

nonterminal StrategyExpr with
  config, grammarName, env, location, unparse, errors, frame, compiledGrammars, flowEnv, flowDefs, -- Normal expression stuff
  attrName, liftedAttrs, isId, isFail;

propagate errors, flowDefs, liftedAttrs on StrategyExpr;

abstract production nameStrategy
top::StrategyExpr ::= id::QName
{
  top.unparse = id.unparse;
  
  top.errors <- id.lookupAttribute.errors;
  -- TODO: Type checking!
}

-- Basic combinators
concrete production idStrategy
top::StrategyExpr ::= 'id'
{
  top.unparse = "id";
  top.isId = true;
  top.isFail = false;
}

concrete production failStrategy
top::StrategyExpr ::= 'fail'
{
  top.unparse = "fail";
  top.isId = false;
  top.isFail = true;
}

concrete production sequenceStrategy
top::StrategyExpr ::= s1::StrategyExpr '<*' s2::StrategyExpr
{
  top.unparse = "(${s1.pp} <* ${s2.pp})";
  top.isId = s1.isId && s2.isId;
  top.isFail = s1.isFail || s2.isFail;
}

concrete production choiceStrategy
top::StrategyExpr ::= s1::StrategyExpr '<+' s2::StrategyExpr
{
  top.unparse = "(${s1.pp} <+ ${s2.pp})";
  top.isId = s1.isId || (s1.isFail && s2.isId);
  top.isFail = s1.isFail && s2.isFail;
}

-- Traversals
concrete production allStrategy
top::StrategyExpr ::= 'all' '(' s::StrategyExpr ')'
{
  top.unparse = "all(${s.pp})";
  -- TODO: Can be more specific here
  top.isId = s.isId;
  top.isFail = s.isFail;
}

concrete production someStrategy
top::StrategyExpr ::= 'some' '(' s::StrategyExpr ')'
{
  top.unparse = "some(${s.pp})";
  -- TODO: Can be more specific here
  top.isId = s.isId;
  top.isFail = s.isFail;
}

concrete production oneStrategy
top::StrategyExpr ::= 'one' '(' s::StrategyExpr ')'
{
  top.unparse = "one(${s.pp})";
  -- TODO: Can be more specific here
  top.isId = s.isId;
  top.isFail = s.isFail;
}


-- Rules
concrete production ruleStrategy
top::StrategyExpr ::= 'rule' 'on' ty::TypeExpr 'of' Opt_Vbar_t ml::MRuleList 'end'
{
  top.unparse = "rule on " ++ ty.unparse ++ " of " ++ ml.unparse ++ " end";
  top.isId = false; -- Unusual, and no way of checking this without decorating RHSs
  top.isFail = ml.isFail;
  
  -- Pattern matching error checking (mostly) happens on what caseExpr forwards to,
  -- so we need to decorate one of those here.
  local checkExpr::Expr =
    caseExpr(
      [hackExprType(ty.typerep, location=top.location)],
      ml.matchRuleList,
      errorExpr([], location=top.location),
      ty.typerep,
      location=top.location);
  checkExpr.env = top.env;
  checkExpr.flowEnv = top.flowEnv;
  checkExpr.downSubst = emptySubst();
  checkExpr.finalSubst = checkExpr.upSubst;
  checkExpr.grammarName = top.grammarName;
  checkExpr.frame = top.frame;
  checkExpr.config = top.config;
  checkExpr.compiledGrammars = top.compiledGrammars;
  
  top.errors <- checkExpr.errors;
  
  top.flowDefs <- checkExpr.flowDefs;
  
  ml.matchRulePatternSize = 1;
}

-- Hack dummy expr with a given type
abstract production hackExprType
top::Expr ::= t::Type
{
  top.typerep = t;
  forwards to errorExpr([], location=top.location);
}

attribute isFail occurs on MRuleList, MatchRule;

aspect production mRuleList_one
top::MRuleList ::= m::MatchRule
{
  top.isFail = m.isFail;
}

aspect production mRuleList_cons
top::MRuleList ::= h::MatchRule '|' t::MRuleList
{
  top.isFail = h.isFail && t.isFail;
}

aspect production matchRule_c
top::MatchRule ::= pt::PatternList _ e::Expr
{
  top.isFail = false; -- TODO
}

aspect production matchRuleWhen_c
top::MatchRule ::= pt::PatternList 'when' cond::Expr _ e::Expr
{
  top.isFail = false; -- TODO
}

aspect production patternList_one
top::PatternList ::= p::Pattern
{
  
}
aspect production patternList_more
top::PatternList ::= p::Pattern ',' ps::PatternList
{
  
}

aspect production patternList_nil
top::PatternList ::=
{
  
}

aspect production namedPatternList_one
top::NamedPatternList ::= p::NamedPattern
{
  
}
aspect production namedPatternList_more
top::NamedPatternList ::= p::NamedPattern ',' ps::NamedPatternList
{
  
}

aspect production namedPatternList_nil
top::NamedPatternList ::=
{
  
}

aspect production namedPattern
top::NamedPattern ::= qn::QName '=' p::Pattern
{
  
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
