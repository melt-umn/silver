grammar silver:extension:strategyattr;

import silver:metatranslation;

annotation genName::String; -- Used to generate the names of lifted strategy attributes

autocopy attribute recVars::[Pair<String String>];
monoid attribute liftedStrategies::[Pair<String StrategyExpr>] with [], ++;
synthesized attribute attrRefName::Maybe<String>;

monoid attribute matchesFrame::Boolean with false, ||;

functor attribute translation;
synthesized attribute directRefTranslation::Maybe<(Expr ::= NamedSignatureElement Location)>;

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
    | choice(functorRef(n), s) -> functorRef(n)
    | all(id()) -> id()
    | some(fail()) -> fail()
    | one(fail()) -> fail()
    | rec(n, s) when top.attrName.isJust -> replace n with top.attrName in s
    | rewriteRule(...) when !... .matchesFrame -> fail()
    | recVarRef(n) when !n.matchesFrame -> fail()
    | strategyRef(n) when !n.matchesFrame -> fail()
    | functorRef(n) when !n.matchesFrame -> fail()
    end);
-}

nonterminal StrategyExpr with
  config, grammarName, env, location, unparse, errors, frame, compiledGrammars, flowEnv, flowDefs, -- Normal expression stuff
  genName, recVars, liftedStrategies,
  translation<Expr>;

flowtype StrategyExpr =
  decorate {grammarName, env, config, recVars}, -- NOT frame
  unparse {}, errors {decorate, frame, compiledGrammars, flowEnv}, flowDefs {decorate, frame, compiledGrammars, flowEnv},
  liftedStrategies {decorate},
  translation {decorate, frame};

propagate errors on StrategyExpr excluding strategyRef, functorRef;
propagate flowDefs, liftedStrategies on StrategyExpr;

-- Basic combinators
abstract production id
top::StrategyExpr ::=
{
  top.unparse = "id";
  top.translation = Silver_Expr { core:just($name{top.frame.lhsNtName}) };
}

abstract production fail
top::StrategyExpr ::=
{
  top.unparse = "fail";
  top.translation = Silver_Expr { core:nothing() };
}

abstract production sequence
top::StrategyExpr ::= s1::StrategyExpr s2::StrategyExpr
{
  top.unparse = s"(${s1.unparse} <* ${s2.unparse})";
  top.liftedStrategies <-
    case s2 of
    | recVarRef(_) -> []
    | strategyRef(_) -> []
    | functorRef(_) -> []
    | _ -> [pair(s2.genName, s2)]
    end;
  
  local s2Name::String =
    case s2 of
    | recVarRef(n) -> s2.genName ++ "_" ++ n.name
    | strategyRef(n) -> n.name
    | functorRef(n) -> n.name
    | _ -> s2.genName
    end;
  top.translation =
    case s2 of
    | functorRef(_) ->
      Silver_Expr {
        -- TODO: This could be a monadic bind, but bindMaybe isn't in core
        case $Expr{s1.translation} of
        | just(res) -> core:just(decorate res with {}.$name{s2Name}) -- TODO: Decorate with all inh attributes
        | nothing() -> nothing()
        end
      }
    | _ ->
      Silver_Expr {
        -- TODO: This could be a monadic bind, but bindMaybe isn't in core
        case $Expr{s1.translation} of
        | just(res) -> decorate res with {}.$name{s2Name} -- TODO: Decorate with all inh attributes
        | nothing() -> nothing()
        end
      }
    end;
}

abstract production choice
top::StrategyExpr ::= s1::StrategyExpr s2::StrategyExpr
{
  top.unparse = s"(${s1.unparse} <+ ${s2.unparse})";
  
  top.translation = Silver_Expr { core:orElse($Expr{s1.translation}, $Expr{s2.translation}) };
}

-- Traversals
abstract production allTraversal
top::StrategyExpr ::= s::StrategyExpr
{
  top.unparse = s"all(${s.unparse})";
  top.liftedStrategies <-
    case s of
    | recVarRef(_) -> []
    | strategyRef(_) -> []
    | functorRef(_) -> []
    | _ -> [pair(s.genName, s)]
    end;
  
  local sName::String =
    case s of
    | recVarRef(n) -> s.genName ++ "_" ++ n.name
    | strategyRef(n) -> n.name
    | functorRef(n) -> n.name
    | _ -> s.genName
    end;
  top.translation =
    case s of
    | functorRef(qNameAttrOccur(attr)) ->
      Silver_Expr {
        core:just(
          $Expr{
            -- From the functor attribute extension
            mkFullFunctionInvocation(
              top.location,
              baseExpr(qName(top.location, top.frame.fullName), location=top.location),
              map(makeArg(top.location, top.env, attr, _), top.frame.signature.inputElements),
              map(
                makeAnnoArg(top.location, top.frame.signature.outputElement.elementName, _),
                top.frame.signature.namedInputElements))})
      }
    | _ ->
      caseExpr(
        map(
          \ n::String -> Silver_Expr { $name{n}.$name{sName} },
          top.frame.signature.inputNames),
        [matchRule(
           map(
             \ n::String ->
             decorate
               varPattern(name(n ++ "_" ++ sName, top.location), location=top.location)
             with { config = top.config; env = top.env; frame = top.frame; patternVarEnv = []; },
             top.frame.signature.inputNames),
           nothing(),
           mkStrFunctionInvocation(
             top.location, top.frame.fullName,
             map(
               \ n::String -> Silver_Expr { $name{n ++ "_" ++ sName} },
               top.frame.signature.inputNames)),
           location=top.location)],
        Silver_Expr { core:nothing() },
        nonterminalType("core:Maybe", [top.frame.signature.outputElement.typerep]),
        location=top.location)
    end;
}

abstract production someTraversal
top::StrategyExpr ::= s::StrategyExpr
{
  top.unparse = s"some(${s.unparse})";
  top.liftedStrategies <-
    case s of
    | nameRef(_) -> []
    | _ -> [pair(s.genName, s)]
    end;
  
  local sName::String =
    case s of
    | nameRef(n) -> n.name
    | _ -> s.genName
    end;
  top.translation =
    mkStrFunctionInvocation(
      top.location, top.frame.fullName,
      map(
        \ n::String ->
          Silver_Expr { core:fromMaybe($name{n}, $name{n}.$name{sName}) },
        top.frame.signature.inputNames));
}
abstract production oneTraversal
top::StrategyExpr ::= s::StrategyExpr
{
  top.unparse = s"one(${s.unparse})";
  
}

-- Recursive strategies
abstract production rec
top::StrategyExpr ::= n::Name s::StrategyExpr
{
  top.unparse = s"rec ${n.name} -> (${s.unparse})";
  
  
}

-- Rules
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
abstract production nameRef
top::StrategyExpr ::= id::QName
{
  top.unparse = id.unparse;
  
  local attrDcl::DclInfo = id.lookupAttribute.dcl;
  attrDcl.givenNonterminalType = error("Not actually needed"); -- Ugh environment needs refactoring
  forwards to
    if lookupBy(stringEq, id.name, top.recVars).isJust
    then recVarRef(id, genName=top.genName, location=top.location)
    else if !null(id.lookupAttribute.errors)
    then errorRef(id.lookupAttribute.errors, id, genName=top.genName, location=top.location)
    else case decorate id.lookupAttribute.dcl with { givenNonterminalType = error("Not actually needed"); }.typerep of -- Ugh environment needs refactoring
    | nonterminalType("core:Maybe", _) -> strategyRef(qNameAttrOccur(id, location=top.location), genName=top.genName, location=top.location)
    | _ -> functorRef(qNameAttrOccur(id, location=top.location), genName=top.genName, location=top.location)
    end;
}
abstract production errorRef
top::StrategyExpr ::= msg::[Message] id::Decorated QName
{
  top.unparse = id.unparse;
  
  top.errors <- msg;
  top.translation = Silver_Expr { core:nothing() };
}
abstract production recVarRef
top::StrategyExpr ::= id::Decorated QName
{
  top.unparse = id.unparse;
  
  local recAttr::String = lookupBy(stringEq, id.name, top.recVars).fromJust;
  
  top.translation = Silver_Expr { $name{top.frame.signature.outputElement.elementName}.$qName{recAttr} };
}
abstract production strategyRef
top::StrategyExpr ::= id::QNameAttrOccur
{
  top.unparse = id.unparse;
  
  local attrDcl::DclInfo = case id of qNameAttrOccur(a) -> a.lookupAttribute.dcl end;
  attrDcl.givenNonterminalType = error("Not actually needed"); -- Ugh environment needs refactoring
  top.errors :=
    case attrDcl.typerep, attrDcl.dclBoundVars of
    | nonterminalType("core:Maybe", [varType(a1)]), [a2] when tyVarEqual(a1, a2) -> []
    | nonterminalType("core:Maybe", [nonterminalType(nt, _)]), _ ->
      case getOccursDcl(attrDcl.fullName, nt, top.env) of
      | [] -> [wrn(id.location, s"Attribute ${id.name} cannot be used as a strategy, because it doesn't occur on its own nonterminal type ${nt}")]
      | _ -> []
      end
    | errorType(), _ -> []
    | _, _ -> [err(id.location, s"Attribute ${id.name} cannot be used as a strategy")]
    end;
  
  id.attrFor = top.frame.signature.outputElement.typerep;
  
  top.translation =
    if id.matchesFrame
    then Silver_Expr { $name{top.frame.signature.outputElement.elementName}.$QNameAttrOccur{id} }
    else Silver_Expr { core:nothing() };
}
abstract production functorRef
top::StrategyExpr ::= id::QNameAttrOccur
{
  top.unparse = id.unparse;
  
  local attrDcl::DclInfo = case id of qNameAttrOccur(a) -> a.lookupAttribute.dcl end;
  attrDcl.givenNonterminalType = error("Not actually needed"); -- Ugh environment needs refactoring
  top.errors :=
    case attrDcl.typerep, attrDcl.dclBoundVars of
    | varType(a1), [a2] when tyVarEqual(a1, a2) -> []
    | nonterminalType(nt, _), _ ->
      case getOccursDcl(attrDcl.fullName, nt, top.env) of
      | [] -> [wrn(id.location, s"Attribute ${id.name} cannot be used as a functor, because it doesn't occur on its own nonterminal type ${nt}")]
      | _ -> []
      end
    | errorType(), _ -> []
    | _, _ -> [err(id.location, s"Attribute ${id.name} cannot be used as a functor")]
    end;
  
  id.attrFor = top.frame.signature.outputElement.typerep;
  
  top.translation =
    if id.matchesFrame
    then Silver_Expr { core:just($name{top.frame.signature.outputElement.elementName}.$QNameAttrOccur{id}) }
    else Silver_Expr { core:nothing() };
}

attribute matchesFrame occurs on QNameAttrOccur;

aspect production qNameAttrOccur
top::QNameAttrOccur ::= at::QName
{
  top.matchesFrame := top.found &&
    case top.typerep of
    | nonterminalType("core:Maybe", [t]) -> !unify(top.attrFor, t).failure
    | t -> !unify(top.attrFor, t).failure
    end;
}
