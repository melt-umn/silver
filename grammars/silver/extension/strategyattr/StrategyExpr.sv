grammar silver:extension:strategyattr;

import silver:metatranslation;

inherited attribute genName::String; -- Used to generate the names of lifted strategy attributes
monoid attribute liftedStrategies::[Pair<String StrategyExpr>] with [], ++;

synthesized attribute translation<a>::a;

{-
strategy attribute optimize =
  innermost(
    rule on StrategyExpr of
    | sequence(fail(), _) -> fail()
    | sequence(_, fail()) -> fail()
    | sequence(id(), s) -> s
    | sequence(s, id()) -> s
    | choice(fail(), s) -> s
    | choice(s, fail()) -> s
    | choice(id(), s) -> id()
    | rewriteRule(...) when ....matchesFrame -> fail()
    end);
-}

nonterminal StrategyExpr with
  config, grammarName, env, location, unparse, errors, frame, compiledGrammars, flowEnv, flowDefs, -- Normal expression stuff
  genName, liftedStrategies,
  translation<Expr>;

flowtype StrategyExpr =
  decorate {grammarName, env, genName}, -- NOT frame
  unparse {}, errors {decorate, frame, config, compiledGrammars, flowEnv}, flowDefs {decorate, frame, config, compiledGrammars, flowEnv},
  liftedStrategies {decorate},
  translation {decorate, frame};

propagate errors, flowDefs, liftedStrategies on StrategyExpr;

abstract production attrRef
top::StrategyExpr ::= id::QName
{
  top.unparse = id.unparse;
  
  top.errors <- id.lookupAttribute.errors;
  -- TODO: Type checking!
  
  top.translation = Silver_Expr { $name{top.frame.lhsNtName}.$QName{id} };
}

-- Basic combinators
concrete production id
top::StrategyExpr ::= 'id'
{
  top.unparse = "id";
  top.translation = Silver_Expr { core:just($name{top.frame.lhsNtName}) };
}

concrete production fail
top::StrategyExpr ::= 'fail'
{
  top.unparse = "fail";
  top.translation = Silver_Expr { core:nothing() };
}

concrete production sequence
top::StrategyExpr ::= s1::StrategyExpr '<*' s2::StrategyExpr
{
  top.unparse = s"(${s1.unparse} <* ${s2.unparse})";
  top.liftedStrategies <- [pair(s2.genName, s2)];
  s1.genName = top.genName;
  s2.genName = top.genName ++ "_cont";
  
  top.translation =
    Silver_Expr {
      -- TODO: This could be a monadic bind, but that isn't in core
      case $Expr{s1.translation} of
      | just(res) -> decorate res with {}.$name{s2.genName} -- TODO: Decorate with all inh attributes
      | nothing() -> nothing()
      end
    };
}

concrete production choice
top::StrategyExpr ::= s1::StrategyExpr '<+' s2::StrategyExpr
{
  top.unparse = s"(${s1.unparse} <+ ${s2.unparse})";
  
  s1.genName = top.genName ++ "_left";
  s2.genName = top.genName ++ "_right";
  
  top.translation = Silver_Expr { core:orElse($Expr{s1.translation}, $Expr{s2.translation}) };
}

-- Traversals
concrete production allTraversal
top::StrategyExpr ::= 'all' '(' s::StrategyExpr ')'
{
  top.unparse = s"all(${s.unparse})";
  
  s.genName = top.genName ++ "_all";
  
  
}

concrete production someTraversal
top::StrategyExpr ::= 'some' '(' s::StrategyExpr ')'
{
  top.unparse = s"some(${s.unparse})";
  
  s.genName = top.genName ++ "_some";
  
}

concrete production oneTraversal
top::StrategyExpr ::= 'one' '(' s::StrategyExpr ')'
{
  top.unparse = s"one(${s.unparse})";
  
  s.genName = top.genName ++ "_one";
  
  
}

-- Recursive strategies
concrete production recStrategy
top::StrategyExpr ::= 'rec' n::Name Arrow_t s::StrategyExpr
{
  top.unparse = s"rec ${n.name} -> (${s.unparse})";
  
  s.genName = top.genName ++ "_" ++ n.name;
  
  
}

-- Rules
concrete production rewriteRule
top::StrategyExpr ::= 'rule' 'on' ty::TypeExpr 'of' Opt_Vbar_t ml::MRuleList 'end'
{
  top.unparse = "rule on " ++ ty.unparse ++ " of " ++ ml.unparse ++ " end";
  
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
  top.errors <-
    if !ty.typerep.isDecorable
    then [wrn(ty.location, "Only rules on nonterminals can have an effect")]
    else []; 
  
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

monoid attribute matchesFrame::Boolean with false, || occurs on MRuleList, MatchRule, PatternList, Pattern;
propagate matchesFrame on MRuleList, MatchRule, PatternList;

attribute translation<MRuleList> occurs on MRuleList;

aspect production mRuleList_one
top::MRuleList ::= m::MatchRule
{
  local defaultRule::MRuleList =
    mRuleList_one(
      matchRule_c(
        makeWildPatternList(top.frame, top.location),
        terminal(Arrow_kwd, "->"),
        Silver_Expr { core:nothing() },
        location=top.location),
      location=top.location);
  top.translation =
    if m.matchesFrame
    then mRuleList_cons(m.translation, '|', defaultRule, location=top.location)
    else defaultRule;
}

aspect production mRuleList_cons
top::MRuleList ::= h::MatchRule '|' t::MRuleList
{
  top.translation =
    if h.matchesFrame
    then mRuleList_cons(h.translation, '|', t.translation, location=top.location)
    else t.translation;
  
}

attribute translation<MatchRule> occurs on MatchRule;

aspect production matchRule_c
top::MatchRule ::= pt::PatternList _ e::Expr
{
  top.translation =
    matchRule_c(
      pt.translation, terminal(Arrow_kwd, "->"), Silver_Expr { core:just($Expr{e}) },
      location=top.location);
}

aspect production matchRuleWhen_c
top::MatchRule ::= pt::PatternList 'when' cond::Expr _ e::Expr
{
  top.translation =
    matchRuleWhen_c(
      pt.translation, 'when', cond, terminal(Arrow_kwd, "->"), Silver_Expr { core:just($Expr{e}) },
      location=top.location);
}

attribute translation<PatternList> occurs on PatternList;

aspect production patternList_one
top::PatternList ::= p::Pattern
{
  top.translation = p.translation;
}
aspect production patternList_more
top::PatternList ::= p::Pattern ',' ps::PatternList
{
  top.translation = p.translation;
}

aspect production patternList_nil
top::PatternList ::=
{
  top.translation = error("Top-level pattern list shouldn't be empty");
}

monoid attribute letBindings::[AssignExpr] with [], ++ occurs on NamedPatternList, NamedPattern, Pattern;

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

attribute translation<PatternList> occurs on Pattern;

aspect default production
top::Pattern ::=
{
  top.translation = makeWildPatternList(top.frame, top.location);
  top.matchesFrame := true;
}

aspect production prodAppPattern_named
top::Pattern ::= prod::QName '(' ps::PatternList ',' nps::NamedPatternList ')'
{
  top.translation = ps;
  top.matchesFrame := prod.lookupValue.fullName == top.frame.fullName;
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

function makeWildPatternList
PatternList ::= frame::BlockContext loc::Location
{
  return
    foldr(
      patternList_more(_, ',', _, location=loc),
      patternList_nil(location=loc),
      repeat(wildcPattern('_', location=loc), length(frame.signature.inputElements ++ frame.signature.namedInputElements)));
}
