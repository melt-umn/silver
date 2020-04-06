grammar silver:extension:strategyattr;

import silver:metatranslation;

inherited attribute genName::String; -- Used to generate the names of lifted strategy attributes
autocopy attribute recVars::[Pair<String String>];
monoid attribute liftedStrategies::[Pair<String StrategyExpr>] with [], ++;

monoid attribute matchesFrame::Boolean with false, ||;

functor attribute translation;

{-
strategy attribute optimize =
  innermost(
    rule on top::StrategyExpr of
    | sequence(fail(), _) -> fail()
    | sequence(_, fail()) -> fail()
    | sequence(id(), s) -> s
    | sequence(s, id()) -> s
    | choice(fail(), s) -> s
    | choice(s, fail()) -> s
    | choice(id(), s) -> id()
    | all, some, one...
    | rec(n, s) when top.attrName.isJust -> replace n with top.attrName in s
    | rewriteRule(...) when !... .matchesFrame -> fail()
    | nameRef(n) when !n.matchesFrame -> fail()
    end);
-}

nonterminal StrategyExpr with
  config, grammarName, env, location, unparse, errors, frame, compiledGrammars, flowEnv, flowDefs, -- Normal expression stuff
  genName, recVars, liftedStrategies,
  translation<Expr>;

flowtype StrategyExpr =
  decorate {grammarName, env, genName, recVars}, -- NOT frame
  unparse {}, errors {decorate, frame, config, compiledGrammars, flowEnv}, flowDefs {decorate, frame, config, compiledGrammars, flowEnv},
  liftedStrategies {decorate},
  translation {decorate, frame, config};

propagate errors on StrategyExpr excluding nameRef;
propagate flowDefs, liftedStrategies on StrategyExpr;

-- Basic combinators
concrete production id_c
top::StrategyExpr ::= 'id'
{ forwards to id(location=top.location); }
abstract production id
top::StrategyExpr ::=
{
  top.unparse = "id";
  top.translation = Silver_Expr { core:just($name{top.frame.lhsNtName}) };
}

concrete production fail_c
top::StrategyExpr ::= 'fail'
{ forwards to fail(location=top.location); }
abstract production fail
top::StrategyExpr ::=
{
  top.unparse = "fail";
  top.translation = Silver_Expr { core:nothing() };
}

concrete production sequence_c
top::StrategyExpr ::= s1::StrategyExpr '<*' s2::StrategyExpr
{
  top.unparse = s"(${s1.unparse} <* ${s2.unparse})";
  forwards to sequence(top.genName, s1, s2, location=top.location);
}
abstract production sequence
top::StrategyExpr ::= genName::String s1::StrategyExpr s2::StrategyExpr
{
  top.unparse = s"(${s1.unparse} <* ${s2.unparse})";
  top.liftedStrategies <- [pair(s2.genName, s2)];
  s1.genName = genName;
  s2.genName = genName ++ "_cont";
  
  top.translation =
    Silver_Expr {
      -- TODO: This could be a monadic bind, but that isn't in core
      case $Expr{s1.translation} of
      | just(res) -> decorate res with {}.$name{s2.genName} -- TODO: Decorate with all inh attributes
      | nothing() -> nothing()
      end
    };
}

concrete production choice_c
top::StrategyExpr ::= s1::StrategyExpr '<+' s2::StrategyExpr
{
  top.unparse = s"(${s1.unparse} <+ ${s2.unparse})";
  forwards to choice(top.genName, s1, s2, location=top.location);
}
abstract production choice
top::StrategyExpr ::= genName::String s1::StrategyExpr s2::StrategyExpr
{
  top.unparse = s"(${s1.unparse} <+ ${s2.unparse})";
  
  s1.genName = genName ++ "_left";
  s2.genName = genName ++ "_right";
  
  top.translation = Silver_Expr { core:orElse($Expr{s1.translation}, $Expr{s2.translation}) };
}

-- Traversals
concrete production allTraversal_c
top::StrategyExpr ::= 'all' '(' s::StrategyExpr ')'
{
  top.unparse = s"all(${s.unparse})";
  forwards to allTraversal(top.genName, s, location=top.location);
}
abstract production allTraversal
top::StrategyExpr ::= genName::String s::StrategyExpr
{
  top.unparse = s"all(${s.unparse})";
  
  s.genName = genName ++ "_all";
  
  top.translation =
    caseExpr(
      map(
        \ e::NamedSignatureElement -> Silver_Expr { $name{e.elementName} },
        top.frame.signature.inputElements),
      ml.matchRuleList,
      Silver_Expr { core:nothing() },
      nonterminalType("core:Maybe", [top.frame.signature.outputElement.typerep]),
      location=top.location);
}

concrete production someTraversal_c
top::StrategyExpr ::= 'some' '(' s::StrategyExpr ')'
{
  top.unparse = s"some(${s.unparse})";
  forwards to someTraversal(top.genName, s, location=top.location);
}
abstract production someTraversal
top::StrategyExpr ::= genName::String s::StrategyExpr
{
  top.unparse = s"some(${s.unparse})";
  
  s.genName = genName ++ "_some";
  
}

concrete production oneTraversal_c
top::StrategyExpr ::= 'one' '(' s::StrategyExpr ')'
{
  top.unparse = s"one(${s.unparse})";
  forwards to oneTraversal(top.genName, s, location=top.location);
}
abstract production oneTraversal
top::StrategyExpr ::= genName::String s::StrategyExpr
{
  top.unparse = s"one(${s.unparse})";
  
  s.genName = genName ++ "_one";
  
  
}

-- Recursive strategies
concrete production rec_c
top::StrategyExpr ::= 'rec' n::Name Arrow_t s::StrategyExpr
{
  top.unparse = s"rec ${n.name} -> (${s.unparse})";
  forwards to rec(top.genName, n, s, location=top.location);
}
abstract production rec
top::StrategyExpr ::= genName::String n::Name s::StrategyExpr
{
  top.unparse = s"rec ${n.name} -> (${s.unparse})";
  
  s.genName = genName ++ "_" ++ n.name;
  
  
}

-- Rules
concrete production rewriteRule_c
top::StrategyExpr ::= 'rule' 'on' ty::TypeExpr 'of' Opt_Vbar_t ml::MRuleList 'end'
{ forwards to rewriteRule(ty, ml, location=top.location); }
abstract production rewriteRule
top::StrategyExpr ::= ty::TypeExpr ml::MRuleList
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
  
  top.translation =
    caseExpr(
      [Silver_Expr { $name{top.frame.signature.outputElement.elementName} }],
      ml.matchRuleList,
      Silver_Expr { core:nothing() },
      nonterminalType("core:Maybe", [ty.typerep]),
      location=top.location);
}

-- Hack dummy expr with a given type
abstract production hackExprType
top::Expr ::= t::Type
{
  top.typerep = t;
  forwards to errorExpr([], location=top.location);
}

attribute matchesFrame occurs on MRuleList, MatchRule, PatternList, Pattern;
propagate matchesFrame on MRuleList, MatchRule, PatternList;

attribute translation occurs on MRuleList;
propagate translation on MRuleList;

attribute translation occurs on MatchRule;

aspect production matchRule_c
top::MatchRule ::= pt::PatternList _ e::Expr
{
  top.translation =
    matchRule_c(
      pt, $2, Silver_Expr { core:just($Expr{e}) },
      location=top.location);
}

aspect production matchRuleWhen_c
top::MatchRule ::= pt::PatternList 'when' cond::Expr _ e::Expr
{
  top.translation =
    matchRuleWhen_c(
      pt, 'when', cond, $4, Silver_Expr { core:just($Expr{e}) },
      location=top.location);
}

aspect default production
top::Pattern ::=
{
  top.matchesFrame := true;
}

aspect production prodAppPattern_named
top::Pattern ::= prod::QName '(' ps::PatternList ',' nps::NamedPatternList ')'
{
  top.matchesFrame := prod.lookupValue.fullName == top.frame.fullName;
}

-- References to other attributes or rec variables
concrete production nameRef_c
top::StrategyExpr ::= id::StrategyQName
{ forwards to nameRef(qNameAttrOccur(id.ast, location=top.location), location=top.location); }
abstract production nameRef
top::StrategyExpr ::= id::QNameAttrOccur
{
  top.unparse = id.unparse;
  
  local recAttr::Maybe<String> = lookupBy(stringEq, id.name, top.recVars);
  local attrDcl::DclInfo = case id of qNameAttrOccur(a) -> a.lookupAttribute.dcl end;
  attrDcl.givenNonterminalType = error("Not needed"); -- Ugh environment needs refactoring
  top.errors :=
    if recAttr.isJust then []
    else
      case id of qNameAttrOccur(a) -> a.lookupAttribute.errors end ++
      case attrDcl.typerep, attrDcl.dclBoundVars of
      | nonterminalType("core:Maybe", [varType(a1)]), [a2] when tyVarEqual(a1, a2) -> []
      | varType(a1), [a2] when tyVarEqual(a1, a2) -> []
      | nonterminalType("core:Maybe", [nonterminalType(nt, _)]), _ ->
        case getOccursDcl(attrDcl.fullName, nt, top.env) of
        | [] -> [wrn(id.location, s"Attribute ${id.name} doesn't occur on its own nonterminal type ${nt}")]
        | _ -> []
        end
      | nonterminalType(nt, _), _ ->
        case getOccursDcl(attrDcl.fullName, nt, top.env) of
        | [] -> [wrn(id.location, s"Attribute ${id.name} doesn't occur on its own nonterminal type ${nt}")]
        | _ -> []
        end
      | errorType(), _ -> []
      | _, _ -> [err(id.location, "Attribute has invalid type for use in a strategy")]  
      end;
  
  id.attrFor = top.frame.signature.outputElement.typerep;
  
  top.translation =
    case recAttr, id.matchesFrame, id.typerep of
    | just(a), _, _ ->
      Silver_Expr { $name{top.frame.signature.outputElement.elementName}.$qName{a} }
    | nothing(), true, nonterminalType("core:Maybe", _) ->
      Silver_Expr { $name{top.frame.signature.outputElement.elementName}.$QNameAttrOccur{id} }
    | nothing(), true, _ ->
      Silver_Expr { core:just($name{top.frame.signature.outputElement.elementName}.$QNameAttrOccur{id}) }
    | nothing(), false, _ ->
      Silver_Expr { core:nothing() }
    end;
}

attribute matchesFrame occurs on QNameAttrOccur;

aspect production qNameAttrOccur
top::QNameAttrOccur ::= at::QName
{
  top.matchesFrame := !top.found ||
    case top.typerep of
    | nonterminalType("core:Maybe", [t]) -> !unify(top.attrFor, t).failure
    | t -> !unify(top.attrFor, t).failure
    end;
}
