grammar silver:compiler:extension:strategyattr;

import silver:compiler:metatranslation;

annotation genName::String; -- Used to generate the names of lifted strategy attributes

autocopy attribute recVarNameEnv::[Pair<String String>]; -- name, (isTotal, genName)
autocopy attribute recVarTotalEnv::[Pair<String Boolean>]; -- name, (isTotal, genName)
inherited attribute outerAttr::Maybe<String>;
autocopy attribute inlinedStrategies::[String];
monoid attribute liftedStrategies::[Pair<String Decorated StrategyExpr>];
synthesized attribute attrRefName::Maybe<String>;
synthesized attribute isId::Boolean;
synthesized attribute isTotal::Boolean;
inherited attribute givenInputElements::[NamedSignatureElement];
synthesized attribute attrRefNames::[Maybe<String>];
monoid attribute containsFail::Boolean with false, ||;
monoid attribute allId::Boolean with true, &&;
monoid attribute freeRecVars::[String];
monoid attribute partialRefs::[String];
monoid attribute totalRefs::[String];
monoid attribute matchesFrame::Boolean with false, ||;

synthesized attribute partialTranslation::Expr; -- Maybe<a> on a
synthesized attribute totalTranslation::Expr; -- a on a, can raise a runtime error if demanded on partial strategy expression

-- Nonterminal-independent algebraic simplifications
-- Theoretically these could be applied to the strategy before lifting/propagation,
-- but probably not much of an improvement.
partial strategy attribute genericStep =
  rule on top::StrategyExpr of
  | sequence(fail(), _) -> fail(location=top.location, genName=top.genName)
  | sequence(_, fail()) -> fail(location=top.location, genName=top.genName)
  | sequence(id(), s) -> s
  | sequence(s, id()) -> s
  | choice(fail(), s) -> s
  | choice(s, fail()) -> s
  | choice(s, _) when s.isTotal -> s
  | allTraversal(id()) -> id(location=top.location, genName=top.genName)
  | someTraversal(fail()) -> fail(location=top.location, genName=top.genName)
  | oneTraversal(fail()) -> fail(location=top.location, genName=top.genName)
  | prodTraversal(_, ss) when ss.containsFail -> fail(location=top.location, genName=top.genName)
  | recComb(n, s) when !contains(n.name, s.freeRecVars) -> s
  | inlined(_, fail()) -> fail(location=top.location, genName=top.genName)
  end;
-- Nonterminal-dependent, production-independent optimizations
partial strategy attribute ntStep =
  rule on top::StrategyExpr of
  -- Only inline references to partial strategies, as inlining total
  -- strategies would not permit any additional simplification.
  | partialRef(n) when
      n.matchesFrame && n.attrDcl.isStrategy &&
      !contains(n.attrDcl.fullName, top.inlinedStrategies) &&
      null(n.attrDcl.givenRecVarNameEnv) ->
    inlined(n, n.attrDcl.strategyExpr, location=top.location, genName=top.genName)
  | partialRef(n) when !n.matchesFrame -> fail(location=top.location, genName=top.genName)
  | inlined(n, _) when !n.matchesFrame -> fail(location=top.location, genName=top.genName)
  | inlined(n, id()) when n.matchesFrame -> id(location=top.location, genName=top.genName)
  | inlined(n1, totalRef(n2)) when n1.matchesFrame -> totalRef(n2, location=top.location, genName=top.genName)
  end;
-- Production-dependent optimizations
partial strategy attribute prodStep =
  rule on top::StrategyExpr of
  | allTraversal(s) when !attrMatchesChild(top.env, fromMaybe(s.genName, s.attrRefName), top.frame) -> id(location=top.location, genName=top.genName)
  | someTraversal(s) when !attrMatchesChild(top.env, fromMaybe(s.genName, s.attrRefName), top.frame) -> fail(location=top.location, genName=top.genName)
  | oneTraversal(s) when !attrMatchesChild(top.env, fromMaybe(s.genName, s.attrRefName), top.frame) -> fail(location=top.location, genName=top.genName)
  | prodTraversal(p, s) when p.lookupValue.fullName != top.frame.fullName -> fail(location=top.location, genName=top.genName)
  | rewriteRule(_, _, ml) when !ml.matchesFrame -> fail(location=top.location, genName=top.genName)
  end <+
  rewriteRule(
    id, id,
    onceBottomUp(
      rule on top::MRuleList of
      | mRuleList_cons(h, _, t) when !h.matchesFrame -> t
      | mRuleList_cons(h, _, mRuleList_one(t)) when !t.matchesFrame -> mRuleList_one(h, location=top.location)
      end));
attribute prodStep occurs on MRuleList;

strategy attribute simplify = innermost(genericStep <+ ntStep);
strategy attribute optimize =
  (sequence(optimize, simplify) <+
   choice(optimize, optimize) <+
   allTraversal(simplify) <+
   someTraversal(simplify) <+
   oneTraversal(simplify) <+
   prodTraversal(id, simplify) <+
   recComb(id, optimize) <+
   inlined(id, optimize) <+
   id) <*
  try((genericStep <+ ntStep <+ prodStep) <* optimize);

nonterminal StrategyExpr with
  config, grammarName, env, location, unparse, errors, frame, compiledGrammars, flowEnv, flowDefs, -- Normal expression stuff
  genName, outerAttr, recVarNameEnv, recVarTotalEnv, liftedStrategies, attrRefName, isId, isTotal, freeRecVars, partialRefs, totalRefs, -- Frame-independent attrs
  partialTranslation, totalTranslation, matchesFrame, -- Frame-dependent attrs
  inlinedStrategies, genericStep, ntStep, prodStep, simplify, optimize; -- Optimization stuff

nonterminal StrategyExprs with
  config, grammarName, env, unparse, errors, frame, compiledGrammars, flowEnv, flowDefs, -- Normal expression stuff
  recVarNameEnv, recVarTotalEnv, givenInputElements, liftedStrategies, attrRefNames, containsFail, allId, freeRecVars, partialRefs, totalRefs, -- Frame-independent attrs
  inlinedStrategies, simplify; -- Optimization stuff

flowtype StrategyExpr =
  decorate {env, grammarName, config, recVarNameEnv, recVarTotalEnv, outerAttr}, -- NOT frame
  -- Normal expression stuff
  unparse {}, errors {decorate, frame, compiledGrammars, flowEnv}, flowDefs {decorate, frame, compiledGrammars, flowEnv},
  -- Frame-independent attrs
  liftedStrategies {decorate}, attrRefName {decorate}, isId {decorate}, isTotal {decorate}, freeRecVars {decorate}, partialRefs {decorate}, totalRefs {decorate},
  -- Frame-dependent attrs
  partialTranslation {decorate, frame}, totalTranslation {decorate, frame}, matchesFrame {decorate, frame};

flowtype StrategyExprs =
  decorate {env, grammarName, config, recVarNameEnv, recVarTotalEnv}, -- NOT frame
  -- Normal expression stuff
  unparse {}, errors {decorate, frame, givenInputElements, compiledGrammars, flowEnv}, flowDefs {decorate, frame, compiledGrammars, flowEnv},
  -- Frame-independent attrs
  liftedStrategies {decorate}, attrRefNames {decorate, givenInputElements},
  containsFail {decorate}, allId {decorate}, freeRecVars {decorate}, partialRefs {decorate}, totalRefs {decorate};

propagate errors on StrategyExpr, StrategyExprs excluding partialRef, totalRef;
propagate flowDefs on StrategyExpr, StrategyExprs;
propagate containsFail, allId on StrategyExprs;
propagate freeRecVars on StrategyExpr, StrategyExprs excluding recComb;
propagate partialRefs, totalRefs on StrategyExpr, StrategyExprs;
propagate simplify on StrategyExprs;
propagate prodStep on MRuleList;
propagate genericStep, ntStep, prodStep, simplify, optimize on StrategyExpr;

-- Convert an expression of type a to Maybe<a>
function asPartial
Expr ::= e::Expr
{ return Silver_Expr { silver:core:just($Expr{e}) }; }

-- Convert an expression of type Maybe<a> to a
function asTotal
Expr ::= t::Type e::Expr
{
  return
    Silver_Expr {
      let res::$TypeExpr{typerepTypeExpr(t, location=e.location)} =
          silver:core:error("Total result demanded when partial strategy failed")
      in silver:core:fromMaybe(res, $Expr{e})
      end
    };
}

aspect default production
top::StrategyExpr ::=
{
  -- At least 1 of these should be defined for every production:
  top.partialTranslation = asPartial(top.totalTranslation);
  top.totalTranslation = asTotal(top.frame.signature.outputElement.typerep, top.partialTranslation);
  
  top.attrRefName = nothing();
  top.matchesFrame := true; -- Consulted only when attrRefName is just(...)
  top.isId = false;
  top.isTotal = false;
}

-- Basic combinators
abstract production id
top::StrategyExpr ::=
{
  top.unparse = "id";
  propagate liftedStrategies;
  top.isId = true;
  top.isTotal = true;
  top.totalTranslation = Silver_Expr { $name{top.frame.signature.outputElement.elementName} };
}

abstract production fail
top::StrategyExpr ::=
{
  top.unparse = "fail";
  propagate liftedStrategies;
  top.partialTranslation = Silver_Expr { silver:core:nothing() };
}

abstract production sequence
top::StrategyExpr ::= s1::StrategyExpr s2::StrategyExpr
{
  top.unparse = s"(${s1.unparse} <* ${s2.unparse})";
  
  local s2Name::String = fromMaybe(top.genName ++ "_snd", s2.attrRefName);
  local s2Total::Boolean = attrIsTotal(top.env, s2Name); -- Can differ from s2.isTotal because we lift without env
  top.liftedStrategies :=
    s1.liftedStrategies ++
    if s2.attrRefName.isJust
    then []
    else [pair(s2Name, s2)];
  top.isTotal = s1.isTotal && s2.isTotal;
  
  s1.outerAttr = nothing();
  s2.outerAttr = nothing();
  
  -- Equations for all inh attributes on the nt that we know about.
  -- This is safe because the MWDA requires that all inh dependencies of a syn attribute
  -- be exported by the syn occurence anyway.
  -- TODO - future optimization potential: this is where common sub-trees shared between
  -- the incoming tree and the result of s1 get re-decorated.
  local allInhs::ExprInhs =
    foldr(
      exprInhsCons(_, _, location=top.location),
      exprInhsEmpty(location=top.location),
      map(
        \ a::DclInfo ->
          Silver_ExprInh {
            $name{a.fullName} = $name{top.frame.signature.outputElement.elementName}.$name{a.fullName};
          },
        filter(
          (.isInherited),
          flatMap(
            getAttrDcl(_, top.env),
            map((.attrOccurring), getAttrsOn(top.frame.lhsNtName, top.env))))));
  top.partialTranslation =
    -- Optimizations when one or both of these is total, in this case a
    -- monadic bind may not be required.
    case s1.isTotal, s2Total of
    | true, true ->
      Silver_Expr {
        silver:core:just(decorate $Expr{s1.totalTranslation} with { $ExprInhs{allInhs} }.$name{s2Name})
      }
    | true, false ->
      Silver_Expr {
        decorate $Expr{s1.totalTranslation} with { $ExprInhs{allInhs} }.$name{s2Name}
      }
    | false, true ->
      Silver_Expr {
        silver:core:map(
          \ res::$TypeExpr{typerepTypeExpr(top.frame.signature.outputElement.typerep, location=top.location)} ->
            decorate res with { $ExprInhs{allInhs} }.$name{s2Name},
          $Expr{s1.partialTranslation})
      }
    | false, false ->
      Silver_Expr {
        silver:core:bind(
          $Expr{s1.partialTranslation},
          \ res::$TypeExpr{typerepTypeExpr(top.frame.signature.outputElement.typerep, location=top.location)} ->
            decorate res with { $ExprInhs{allInhs} }.$name{s2Name})
      }
    end;
  local totalTrans::Expr =
    Silver_Expr {
      decorate $Expr{s1.totalTranslation} with { $ExprInhs{allInhs} }.$name{s2Name}
    };
  top.totalTranslation = if s2Total then totalTrans else asTotal(top.frame.signature.outputElement.typerep, totalTrans);
}

abstract production choice
top::StrategyExpr ::= s1::StrategyExpr s2::StrategyExpr
{
  top.unparse = s"(${s1.unparse} <+ ${s2.unparse})";
  propagate liftedStrategies;
  top.isTotal = s1.isTotal || s2.isTotal;
  
  s1.outerAttr = nothing();
  s2.outerAttr = nothing();
  
  top.partialTranslation =
    Silver_Expr {
      silver:core:orElse($Expr{s1.partialTranslation}, $Expr{s2.partialTranslation})
    };
  top.totalTranslation =
    if s1.isTotal
    then s1.totalTranslation
    else 
      Silver_Expr {
        silver:core:fromMaybe($Expr{s2.totalTranslation}, $Expr{s1.partialTranslation})
      };
}

-- Traversals
abstract production allTraversal
top::StrategyExpr ::= s::StrategyExpr
{
  top.unparse = s"all(${s.unparse})";
  
  local sName::String = fromMaybe(top.genName ++ "_all_arg", s.attrRefName);
  local sTotal::Boolean = attrIsTotal(top.env, sName); -- Can differ from s.isTotal because we lift without env
  top.liftedStrategies :=
    if s.attrRefName.isJust
    then []
    else [pair(sName, s)];
  top.isTotal = s.isTotal;
  
  s.outerAttr = nothing();
  
  local sBaseName::String = last(explode(":", sName));
  -- pair(child name, attr occurs on child)
  local childAccesses::[Pair<String Boolean>] =
    map(
      \ e::NamedSignatureElement ->
        pair(e.elementName, attrMatchesFrame(top.env, sName, e.typerep)),
      top.frame.signature.inputElements);
  top.partialTranslation =
    if sTotal
    then asPartial(top.totalTranslation)
    else
      {- Translation of all(s) for prod::(Foo ::= a::Foo b::Integer c::Bar):
           case a.s, c.s of
           | just(a_s), just(c_s) -> just(prod(a_s, b, c_s))
           | _, _ -> nothing()
           end
         Could also be implemented as chained monadic binds.  Maybe more efficient this way? -}
      caseExpr(
        flatMap(
          \ a::Pair<String Boolean> ->
            if a.snd then [Silver_Expr { $name{a.fst}.$name{sName} }] else [],
          childAccesses),
        [matchRule(
           flatMap(
             \ a::Pair<String Boolean> ->
               if a.snd
               then
                 [decorate Silver_Pattern { silver:core:just($name{a.fst ++ "_" ++ sBaseName}) }
                  with { config = top.config; env = top.env; frame = top.frame; patternVarEnv = []; }]
               else [],
             childAccesses),
           nothing(),
           Silver_Expr {
             silver:core:just(
               $Expr{
                 mkFullFunctionInvocation(
                   top.location,
                   baseExpr(qName(top.location, top.frame.fullName), location=top.location),
                   map(
                     \ a::Pair<String Boolean> ->
                       if a.snd
                       then Silver_Expr { $name{a.fst ++ "_" ++ sBaseName} }
                       else Silver_Expr { $name{a.fst} },
                     childAccesses),
                   map(
                     makeAnnoArg(top.location, top.frame.signature.outputElement.elementName, _),
                     top.frame.signature.namedInputElements))})
           },
           location=top.location)],
        false,
        Silver_Expr { silver:core:nothing() },
        appType(nonterminalType("silver:core:Maybe", [starKind()], false), top.frame.signature.outputElement.typerep),
        location=top.location);
  top.totalTranslation =
    if sTotal
    then
      {- When s is total, optimized translation of all(s) for prod::(Foo ::= a::Foo b::Integer c::Bar):
           prod(a.s, b, c.s) -}
       mkFullFunctionInvocation(
         top.location,
         baseExpr(qName(top.location, top.frame.fullName), location=top.location),
         map(
           \ a::Pair<String Boolean> ->
             if a.snd
             then Silver_Expr { $name{a.fst}.$name{sName} }
             else Silver_Expr { $name{a.fst} },
           childAccesses),
         map(
           makeAnnoArg(top.location, top.frame.signature.outputElement.elementName, _),
           top.frame.signature.namedInputElements))
    else asTotal(top.frame.signature.outputElement.typerep, top.partialTranslation);
}

abstract production someTraversal
top::StrategyExpr ::= s::StrategyExpr
{
  top.unparse = s"some(${s.unparse})";
  
  local sName::String = fromMaybe(top.genName ++ "_some_arg", s.attrRefName);
  local sTotal::Boolean = attrIsTotal(top.env, sName); -- Can differ from s.isTotal because we lift without env
  top.liftedStrategies :=
    if s.attrRefName.isJust
    then []
    else [pair(sName, s)];
  
  s.outerAttr = nothing();
  
  -- pair(child name, attr occurs on child)
  local childAccesses::[Pair<String Boolean>] =
    map(
      \ e::NamedSignatureElement ->
        pair(e.elementName, attrMatchesFrame(top.env, sName, e.typerep)),
      top.frame.signature.inputElements);
  local matchingChildren::[String] = map(fst, filter(snd, childAccesses));
  top.partialTranslation =
    if sTotal
    then
      if !null(matchingChildren)
      then asPartial(top.totalTranslation)
      else Silver_Expr { silver:core:nothing() }
    else
      {- Translation of some(s) for prod::(Foo ::= a::Foo b::Integer c::Bar):
           if a.s.isJust || c.s.isJust
           then just(prod(fromMaybe(a, a.s), b, fromMaybe(c, c.s)))
           else nothing()
         Not sure of a clean way to do this with monads -}
      Silver_Expr {
        if $Expr{
          foldr(
            or(_, '||', _, location=top.location),
            falseConst('false', location=top.location),
            map(
              \ a::String -> Silver_Expr { $name{a}.$name{sName}.isJust },
              matchingChildren))}
        then
          silver:core:just(
            $Expr{
              mkFullFunctionInvocation(
                top.location,
                baseExpr(qName(top.location, top.frame.fullName), location=top.location),
                map(
                  \ a::Pair<String Boolean> ->
                    if a.snd
                    then Silver_Expr { silver:core:fromMaybe($name{a.fst}, $name{a.fst}.$name{sName}) }
                    else Silver_Expr { $name{a.fst} },
                  childAccesses),
                map(
                  makeAnnoArg(top.location, top.frame.signature.outputElement.elementName, _),
                  top.frame.signature.namedInputElements))})
        else silver:core:nothing()
      };
  top.totalTranslation =
    if sTotal && !null(matchingChildren)
    then
      {- When s is total, optimized translation of all(s) for prod::(Foo ::= a::Foo b::Integer c::Bar):
           prod(a.s, b, c.s) -}
       mkFullFunctionInvocation(
         top.location,
         baseExpr(qName(top.location, top.frame.fullName), location=top.location),
         map(
           \ a::Pair<String Boolean> ->
             if a.snd
             then Silver_Expr { $name{a.fst}.$name{sName} }
             else Silver_Expr { $name{a.fst} },
           childAccesses),
         map(
           makeAnnoArg(top.location, top.frame.signature.outputElement.elementName, _),
           top.frame.signature.namedInputElements))
    else asTotal(top.frame.signature.outputElement.typerep, top.partialTranslation);
}
abstract production oneTraversal
top::StrategyExpr ::= s::StrategyExpr
{
  top.unparse = s"one(${s.unparse})";
  
  local sName::String = fromMaybe(top.genName ++ "_one_arg", s.attrRefName);
  local sTotal::Boolean = attrIsTotal(top.env, sName); -- Can differ from s.isTotal because we lift without env
  top.liftedStrategies :=
    if s.attrRefName.isJust
    then []
    else [pair(sName, s)];
  
  s.outerAttr = nothing();
  
  local sBaseName::String = last(explode(":", sName));
  -- pair(child name, attr occurs on child)
  local childAccesses::[Pair<String Boolean>] =
    map(
      \ e::NamedSignatureElement ->
        pair(e.elementName, attrMatchesFrame(top.env, sName, e.typerep)),
      top.frame.signature.inputElements);
  local matchingChildren::[String] = map(fst, filter(snd, childAccesses));
  top.partialTranslation =
    if sTotal
    then
      if !null(matchingChildren)
      then asPartial(top.totalTranslation)
      else Silver_Expr { silver:core:nothing() }
    else
      {- Translation of one(s) for prod::(Foo ::= a::Foo b::Integer c::Bar):
           case a.s, c.s of
           | just(a_s), _ -> just(prod(a_s, b, c))
           | _, just(c_s) -> just(prod(a, b, c_s))
           | _, _ -> nothing()
           end
         Could also be implemented as
           orElse(
             bind(a.s, \ a_s::Foo -> pure(prod(a_s, b, c))),
             bind(c.s, \ c_s::Bar -> pure(prod(a, b, c_s)))  -}
      caseExpr(
        map(
          \ a::String -> Silver_Expr { $name{a}.$name{sName} },
          matchingChildren),
        map(
          \ i::Integer ->
            let childI::String = head(drop(i, matchingChildren))
            in let childIndex::Integer = positionOf(childI, map(fst, childAccesses))
            in 
              matchRule(
                map(
                  \ p::Pattern -> decorate p with { config = top.config; env = top.env; frame = top.frame; patternVarEnv = []; },
                  repeat(wildcPattern('_', location=top.location), i) ++
                  Silver_Pattern { silver:core:just($name{childI ++ "_" ++ sBaseName}) } ::
                  repeat(wildcPattern('_', location=top.location), length(matchingChildren) - (i + 1))),
                nothing(),
                Silver_Expr {
                  silver:core:just(
                    $Expr{
                      mkFullFunctionInvocation(
                        top.location,
                        baseExpr(qName(top.location, top.frame.fullName), location=top.location),
                        map(
                          \ a::Pair<String Boolean> -> Silver_Expr { $name{a.fst} },
                          take(childIndex, childAccesses)) ++
                        Silver_Expr { $name{childI ++ "_" ++ sBaseName} } ::
                        map(
                          \ a::Pair<String Boolean> -> Silver_Expr { $name{a.fst} },
                          drop(childIndex + 1, childAccesses)),
                        map(
                          makeAnnoArg(top.location, top.frame.signature.outputElement.elementName, _),
                          top.frame.signature.namedInputElements))})
                },
                location=top.location)
            end end,
            range(0, length(matchingChildren))),
        false,
        Silver_Expr { silver:core:nothing() },
        appType(nonterminalType("silver:core:Maybe", [starKind()], false), top.frame.signature.outputElement.typerep),
        location=top.location);
  top.totalTranslation =
    if sTotal && !null(matchingChildren)
    then
      {- When s is total, optimized translation of one(s) for prod::(Foo ::= a::Foo b::Integer c::Bar):
           prod(a.s, b, c) -}
      mkFullFunctionInvocation(
        top.location,
        baseExpr(qName(top.location, top.frame.fullName), location=top.location),
        map(
          \ a::Pair<String Boolean> ->
            if a.fst == head(matchingChildren)
            then Silver_Expr { $name{a.fst}.$name{sName} }
            else Silver_Expr { $name{a.fst} },
          childAccesses),
        map(
          makeAnnoArg(top.location, top.frame.signature.outputElement.elementName, _),
          top.frame.signature.namedInputElements))
    else asTotal(top.frame.signature.outputElement.typerep, top.partialTranslation);
}

abstract production prodTraversal
top::StrategyExpr ::= prod::QName s::StrategyExprs
{
  top.unparse = s"${prod.unparse}(${s.unparse})";
  
  top.errors <- prod.lookupValue.errors;
  
  local numParams::Integer = length(s.givenInputElements);
  local numArgs::Integer = length(s.attrRefNames);
  top.errors <-
    if prod.lookupValue.found && numArgs != numParams
    then [err(top.location, s"Wrong number of arguments to ${prod.name}: expected ${toString(numParams)}, got ${toString(numArgs)}")]
    else [];
  
  propagate liftedStrategies;
  
  s.givenInputElements =
    if prod.lookupValue.found
    then prod.lookupValue.dcl.namedSignature.inputElements
    else [];
  
  -- pair(child name, if attr occurs on child then just(attr name) else nothing())
  local childAccesses::[Pair<String Maybe<String>>] =
    zipWith(pair, top.frame.signature.inputNames, s.attrRefNames);
  top.partialTranslation = -- This is never total
    if prod.lookupValue.fullName == top.frame.fullName
    then
      {- Translation of prod(s1, s2, s3, s4) for prod::(Foo ::= a::Foo b::Integer c::Bar d::Baz)
         where s4 is total:
           case a.s1, c.s3 of
           | just(a_s1), just(c_s3) -> just(prod(a_s1, b, c_s3, d.s4))
           | _, _ -> nothing()
           end
         Could also be implemented as chained monadic binds.  Maybe more efficient this way? -}
      caseExpr(
        flatMap(
          \ a::Pair<String Maybe<String>> ->
            case a.snd of
            | just(attr) when !attrIsTotal(top.env, attr) -> [Silver_Expr { $name{a.fst}.$name{attr} }]
            | _ -> []
            end,
          childAccesses),
        [matchRule(
           flatMap(
             \ a::Pair<String Maybe<String>> ->
               case a.snd of
               | just(attr) when !attrIsTotal(top.env, attr)  ->
                 [decorate Silver_Pattern { silver:core:just($name{a.fst ++ "_" ++ last(explode(":", attr))}) }
                  with { config = top.config; env = top.env; frame = top.frame; patternVarEnv = []; }]
               | _ -> []
               end,
             childAccesses),
           nothing(),
           Silver_Expr {
             silver:core:just(
               $Expr{
                 mkFullFunctionInvocation(
                   top.location,
                   baseExpr(qName(top.location, top.frame.fullName), location=top.location),
                   map(
                     \ a::Pair<String Maybe<String>> ->
                       case a.snd of
                       | just(attr) when attrIsTotal(top.env, attr) -> Silver_Expr { $name{a.fst}.$name{attr} }
                       | just(attr) -> Silver_Expr { $name{a.fst ++ "_" ++ last(explode(":", attr))} }
                       | nothing() -> Silver_Expr { $name{a.fst} }
                       end,
                     childAccesses),
                   map(
                     makeAnnoArg(top.location, top.frame.signature.outputElement.elementName, _),
                     top.frame.signature.namedInputElements))})
           },
           location=top.location)],
        false,
        Silver_Expr { silver:core:nothing() },
        appType(nonterminalType("silver:core:Maybe", [starKind()], false), top.frame.signature.outputElement.typerep),
        location=top.location)
    else Silver_Expr { silver:core:nothing() };
}

abstract production consStrategyExpr
top::StrategyExprs ::= h::StrategyExpr t::StrategyExprs
{
  top.unparse = s"${h.unparse}, ${t.unparse}";
   
  top.liftedStrategies :=
    -- Slight hack: when h is id (common case for prod traversals), there is no need for a new attribute.
    -- However this can't be avoided during the optimization phase, which happens after lifting.
    -- So, just don't lift the strategy, and we won't find the occurence of the non-existant attribute
    -- during translation - which means we will treat it as id anyway!
    (if h.attrRefName.isJust || h.isId
     then []
     else [pair(h.genName, h)]) ++
    t.liftedStrategies;
  
  local hType::Type = head(top.givenInputElements).typerep;
  local attr::String = fromMaybe(h.genName, h.attrRefName);
  local attrMatch::Boolean = attrMatchesFrame(top.env, attr, hType);
  top.attrRefNames =
   (if !null(top.givenInputElements) && attrMatch && !h.isId
    then just(attr)
    else nothing()) :: t.attrRefNames;
  top.errors <-
    if !null(top.givenInputElements) && !attrMatch && !h.isId
    then [wrn(h.location, s"This (non-identity) strategy attribute does not occur on ${prettyType(hType)} and will be treated as identity")]
    else [];
  
  top.containsFail <- case h of fail() -> true | _ -> false end;
  top.allId <- case h of id() -> true | _ -> false end;
  
  h.outerAttr = nothing();
  t.givenInputElements =
    if !null(top.givenInputElements) then tail(top.givenInputElements) else [];
}

abstract production nilStrategyExpr
top::StrategyExprs ::=
{
  top.unparse = "";
  top.liftedStrategies := [];
  top.attrRefNames = [];
}

-- Recursive strategies
abstract production recComb
top::StrategyExpr ::= n::Name s::StrategyExpr
{
  top.unparse = s"rec ${n.name} -> (${s.unparse})";
  
  local sName::String = fromMaybe(top.genName ++ "_rec_body", top.outerAttr);
  top.liftedStrategies :=
    if top.outerAttr.isJust
    then s.liftedStrategies
    else [pair(sName, s)];
  top.freeRecVars := remove(n.name, s.freeRecVars);
  top.isTotal =
    decorate s with {
      recVarTotalEnv = pair(n.name, true) :: s.recVarTotalEnv;
      env = s.env; config = s.config; grammarName = s.grammarName; recVarNameEnv = s.recVarNameEnv; outerAttr = s.outerAttr;
    }.isTotal;
  
  s.recVarNameEnv = pair(n.name, sName) :: top.recVarNameEnv;
  s.recVarTotalEnv = pair(n.name, top.isTotal) :: top.recVarTotalEnv;
  s.outerAttr = top.outerAttr;
  
  local sTotal::Boolean = attrIsTotal(top.env, sName);
  top.partialTranslation =
    if top.outerAttr.isJust
    then s.partialTranslation
    else if sTotal
    then asPartial(top.totalTranslation)
    else Silver_Expr { $name{top.frame.signature.outputElement.elementName}.$name{sName} };
  top.totalTranslation =
    if top.outerAttr.isJust
    then s.totalTranslation
    else if sTotal
    then Silver_Expr { $name{top.frame.signature.outputElement.elementName}.$name{sName} }
    else asTotal(top.frame.signature.outputElement.typerep, top.partialTranslation);
}

-- Rules
abstract production rewriteRule
top::StrategyExpr ::= id::Name ty::TypeExpr ml::MRuleList
{
  top.unparse = "rule on " ++ id.name ++ "::" ++ ty.unparse ++ " of " ++ ml.unparse ++ " end";
  propagate liftedStrategies;
  
  -- Pattern matching error checking (mostly) happens on what caseExpr forwards to,
  -- so we need to decorate one of those here.
  local checkExpr::Expr =
    letp(
      assignExpr(id, '::', ty, '=', errorExpr([], location=top.location), location=top.location),
      caseExpr(
        [hackExprType(ty.typerep, location=top.location)],
        ml.matchRuleList, false,
        errorExpr([], location=top.location),
        ty.typerep,
        location=top.location),
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
  top.errors <- ty.errorsKindStar;
  
  top.flowDefs <- checkExpr.flowDefs;
  
  ml.matchRulePatternSize = 1;
  
  local res::Expr =
    caseExpr(
      [Silver_Expr { $name{top.frame.signature.outputElement.elementName} }],
      ml.translation, false,
      Silver_Expr { silver:core:nothing() },
      appType(nonterminalType("silver:core:Maybe", [starKind()], false), ty.typerep),
      location=top.location);
  top.partialTranslation =
    if unify(ty.typerep, top.frame.signature.outputElement.typerep).failure
    then Silver_Expr { silver:core:nothing() }
    else if top.frame.signature.outputElement.elementName == id.name
    then res
    else Silver_Expr {
      let $Name{id}::$TypeExpr{ty} = $name{top.frame.signature.outputElement.elementName}
      in $Expr{res}
      end
    };
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

synthesized attribute translation<a>::a;
attribute translation<[AbstractMatchRule]> occurs on MRuleList;

aspect production mRuleList_one
top::MRuleList ::= m::MatchRule
{
  top.translation = [m.translation];
}

aspect production mRuleList_cons
top::MRuleList ::= h::MatchRule '|' t::MRuleList
{
  top.translation = h.translation :: t.translation;
}

attribute translation<AbstractMatchRule> occurs on MatchRule;

aspect production matchRule_c
top::MatchRule ::= pt::PatternList _ e::Expr
{
  top.translation =
    matchRule(
      pt.patternList, nothing(), Silver_Expr { silver:core:just($Expr{e}) },
      location=top.location);
}

aspect production matchRuleWhen_c
top::MatchRule ::= pt::PatternList 'when' cond::Expr _ e::Expr
{
  top.translation =
    matchRule(
      pt.patternList, just(pair(cond, nothing())), Silver_Expr { silver:core:just($Expr{e}) },
      location=top.location);
}

aspect production matchRuleWhenMatches_c
top::MatchRule ::= pt::PatternList 'when' cond::Expr 'matches' p::Pattern _ e::Expr
{
  top.translation =
    matchRule(
      pt.patternList, just(pair(cond, just(p))), Silver_Expr { silver:core:just($Expr{e}) },
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
  
  -- Forwarding depends on env here, these must be computed without env
  propagate liftedStrategies;
  top.attrRefName = just(fromMaybe(id.name, lookup(id.name, top.recVarNameEnv)));
  top.isId = false;
  
  local attrDcl::DclInfo = id.lookupAttribute.dcl;
  attrDcl.givenNonterminalType = error("Not actually needed"); -- Ugh environment needs refactoring
  forwards to
    if lookup(id.name, top.recVarNameEnv).isJust
    then recVarRef(id, genName=top.genName, location=top.location)
    else if !null(id.lookupAttribute.errors)
    then errorRef(id.lookupAttribute.errors, id, genName=top.genName, location=top.location)
    else if attrIsTotal(top.env, id.name)
    then totalRef(qNameAttrOccur(id, location=top.location), genName=top.genName, location=top.location)
    else partialRef(qNameAttrOccur(id, location=top.location), genName=top.genName, location=top.location);
}
abstract production errorRef
top::StrategyExpr ::= msg::[Message] id::Decorated QName
{
  top.unparse = id.unparse;
  
  propagate liftedStrategies;
  top.attrRefName = just(id.name);
  
  top.errors <- msg;
  top.partialTranslation = Silver_Expr { silver:core:nothing() };
}
abstract production recVarRef
top::StrategyExpr ::= id::Decorated QName
{
  top.unparse = id.unparse;
  
  propagate liftedStrategies;
  top.attrRefName = lookup(id.name, top.recVarNameEnv);
  top.isTotal = lookup(id.name, top.recVarTotalEnv).fromJust;
  top.freeRecVars <- [id.name];
  
  top.partialTranslation =
    if attrIsTotal(top.env, top.attrRefName.fromJust)
    then asPartial(top.totalTranslation)
    else Silver_Expr { $name{top.frame.signature.outputElement.elementName}.$qName{top.attrRefName.fromJust} };
  top.totalTranslation =
    if attrIsTotal(top.env, top.attrRefName.fromJust)
    then Silver_Expr { $name{top.frame.signature.outputElement.elementName}.$qName{top.attrRefName.fromJust} }
    else asTotal(top.frame.signature.outputElement.typerep, top.partialTranslation);
}
abstract production partialRef
top::StrategyExpr ::= attr::QNameAttrOccur
{
  top.unparse = attr.unparse;
  
  -- Lookup for error checking is *not* contextual, since we don't know the frame here
  local attrDcl::DclInfo = case attr of qNameAttrOccur(a) -> a.lookupAttribute.dcl end;
  attrDcl.givenNonterminalType = error("Not actually needed"); -- Ugh environment needs refactoring
  local attrTypeScheme::PolyType = attrDcl.typeScheme;
  top.errors :=
    if !attrDcl.isSynthesized
    then [err(attr.location, s"Attribute ${attr.name} cannot be used as a partial strategy, because it is not a synthesized attribute")]
    else case attrTypeScheme.typerep, attrTypeScheme.boundVars of
    | appType(nonterminalType("silver:core:Maybe", _, _), varType(a1)), [a2] when a1 == a2 && attrDcl.isSynthesized -> []
    | appType(nonterminalType("silver:core:Maybe", _, _), a), _ when pair(a.baseType, attrDcl.isSynthesized) matches pair(nonterminalType(nt, _, _), true) ->
      if null(getOccursDcl(attrDcl.fullName, nt, top.env))
      then [wrn(attr.location, s"Attribute ${attr.name} cannot be used as a partial strategy, because it doesn't occur on its own nonterminal type ${nt}")]
      else []
    | errorType(), _ -> []
    | _, _ -> [err(attr.location, s"Attribute ${attr.name} cannot be used as a partial strategy")]
    end;
  
  propagate liftedStrategies;
  top.attrRefName = just(attr.name);
  top.matchesFrame := attr.matchesFrame;
  top.isTotal = false;
  top.partialRefs <- [attrDcl.fullName];
  
  attr.attrFor = top.frame.signature.outputElement.typerep;
  
  top.partialTranslation =
    if attr.matchesFrame
    then Silver_Expr { $name{top.frame.signature.outputElement.elementName}.$QNameAttrOccur{attr} }
    else Silver_Expr { silver:core:nothing() };
}
abstract production totalRef
top::StrategyExpr ::= attr::QNameAttrOccur
{
  top.unparse = attr.unparse;
  
  -- Lookup for error checking is *not* contextual, since we don't know the frame here
  local attrDcl::DclInfo = case attr of qNameAttrOccur(a) -> a.lookupAttribute.dcl end;
  attrDcl.givenNonterminalType = error("Not actually needed"); -- Ugh environment needs refactoring
  local attrTypeScheme::PolyType = attrDcl.typeScheme;
  top.errors :=
    if !attrDcl.isSynthesized
    then [err(attr.location, s"Attribute ${attr.name} cannot be used as a total strategy, because it is not a synthesized attribute")]
    else case attrTypeScheme.typerep.baseType, attrTypeScheme.boundVars of
    | varType(a1), [a2] when a1 == a2 -> []
    | nonterminalType(nt, _, _), _ ->
      if null(getOccursDcl(attrDcl.fullName, nt, top.env))
      then [wrn(attr.location, s"Attribute ${attr.name} cannot be used as a total strategy, because it doesn't occur on its own nonterminal type ${nt}")]
      else []
    | errorType(), _ -> []
    | _, _ -> [err(attr.location, s"Attribute ${attr.name} cannot be used as a total strategy")]
    end;
  
  propagate liftedStrategies;
  top.attrRefName = just(attr.name);
  top.matchesFrame := attr.matchesFrame;
  top.isTotal = true;
  top.totalRefs <- [attrDcl.fullName];
  
  attr.attrFor = top.frame.signature.outputElement.typerep;
  
  top.totalTranslation = Silver_Expr { $name{top.frame.signature.outputElement.elementName}.$QNameAttrOccur{attr} };
}

-- The result of performing an inlining optimization
abstract production inlined
top::StrategyExpr ::= attr::Decorated QNameAttrOccur s::StrategyExpr
{
  top.unparse = s"(${s.unparse} aka ${attr.unparse})";
  propagate liftedStrategies;
  top.attrRefName = just(attr.attrDcl.fullName);
  top.isTotal = s.isTotal;
  top.partialTranslation =
    if attr.matchesFrame
    then s.partialTranslation
    else Silver_Expr { silver:core:nothing() };
  top.totalTranslation = s.totalTranslation;
  
  s.outerAttr = top.outerAttr;
  s.inlinedStrategies = attr.attrDcl.fullName :: top.inlinedStrategies;
}

attribute matchesFrame occurs on QNameAttrOccur;

aspect production qNameAttrOccur
top::QNameAttrOccur ::= at::QName
{
  top.matchesFrame := top.found &&
    case top.typerep of
    | appType(nonterminalType("silver:core:Maybe", _, _), t) -> !unify(top.attrFor, t).failure
    | t -> !unify(top.attrFor, t).failure
    end;
}

function attrIsTotal
Boolean ::= env::Decorated Env attrName::String
{
  local dcls::[DclInfo] = getAttrDcl(attrName, env);
  return
    case dcls of
    | [] -> false
    | d :: _ ->
      case decorate d with { givenNonterminalType = error("Not actually needed"); }.typeScheme.typerep of -- Ugh environment needs refactoring
      | appType(nonterminalType("silver:core:Maybe", _, _), _) -> false
      | _ -> true
      end
    end;
}

function attrMatchesFrame
Boolean ::= env::Decorated Env attrName::String attrFor::Type
{
  return
    decorate qNameAttrOccur(qName(loc("", -1, -1, -1, -1, -1, -1), attrName), location=loc("", -1, -1, -1, -1, -1, -1))
    with { env = env; attrFor = attrFor; }.matchesFrame;
}

function attrMatchesChild
Boolean ::= env::Decorated Env attrName::String frame::BlockContext
{
  return
    any(
      map(
        \ e::NamedSignatureElement -> attrMatchesFrame(env, attrName, e.typerep),
        frame.signature.inputElements));
}
