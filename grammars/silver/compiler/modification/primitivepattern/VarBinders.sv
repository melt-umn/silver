grammar silver:compiler:modification:primitivepattern;

option silver:compiler:analysis:warnings:flow;  -- needed due to receivedDeps attribute

import silver:compiler:translation:java:core;
import silver:compiler:translation:java:type;

import silver:compiler:modification:let_fix only makeSpecialLocalBinding, lexicalLocalDef;

import silver:compiler:definition:flow:ast only hasVertex, noVertex, PatternVarProjection, patternVarProjection, anonVertexType, ExprVertexInfo, FlowVertex, inhVertex;
-- also unfortunately placed references to flowEnv

import silver:compiler:analysis:warnings:flow only receivedDeps;  -- Used in computing flow errors

nonterminal VarBinders with 
  config, grammarName, env, compiledGrammars, frame,
  location, unparse, errors, defs, boundNames,
  bindingTypes, bindingIndex, translation, varBinderCount,
  finalSubst, flowProjections, bindingNames, flowEnv, matchingAgainst;
nonterminal VarBinder with
  config, grammarName, env, compiledGrammars, frame,
  location, unparse, errors, defs, boundNames,
  bindingType, bindingIndex, translation,
  finalSubst, flowProjections, bindingName, flowEnv, matchingAgainst;

flowtype decorate {grammarName, env, flowEnv, finalSubst, frame, compiledGrammars, config, bindingTypes, bindingIndex, bindingNames, matchingAgainst} on VarBinders;
flowtype decorate {grammarName, env, flowEnv, finalSubst, frame, compiledGrammars, config, bindingType, bindingIndex, bindingName, matchingAgainst} on VarBinder;

flowtype forward {decorate} on VarBinders, VarBinder;
flowtype errors {decorate, receivedDeps} on VarBinders, VarBinder;
flowtype defs {decorate} on VarBinders, VarBinder;
flowtype boundNames {} on VarBinders, VarBinder;

propagate errors, defs, boundNames on VarBinders, VarBinder;

--- Types of each child
inherited attribute bindingTypes :: [Type];
inherited attribute bindingType :: Type;
--- Index of each child
inherited attribute bindingIndex :: Integer;
--- Names of each child (for flow analysis)
inherited attribute bindingNames :: [String];
inherited attribute bindingName :: String;
--- Extractions of decoration sites from children
synthesized attribute flowProjections :: [PatternVarProjection];

-- The DclInfo of the production we're matching against
autocopy attribute matchingAgainst :: Maybe<ValueDclInfo>;

synthesized attribute varBinderCount :: Integer;

monoid attribute boundNames::[String];

concrete production oneVarBinder
top::VarBinders ::= v::VarBinder
{
  top.unparse = v.unparse;

  top.translation = v.translation;
  top.varBinderCount = 1;
  top.flowProjections = v.flowProjections;

  v.bindingIndex = top.bindingIndex;
  v.bindingType =
    if null(top.bindingTypes)
    then errorType()
    else head(top.bindingTypes);
  v.bindingName =
    if null(top.bindingNames)
    then "__NONAME"
    else head(top.bindingNames);
}
concrete production consVarBinder
top::VarBinders ::= v::VarBinder ',' vs::VarBinders
{
  top.unparse = v.unparse ++ ", " ++ vs.unparse;

  top.translation = v.translation ++ vs.translation;
  top.varBinderCount = 1 + vs.varBinderCount;
  top.flowProjections = v.flowProjections ++ vs.flowProjections;

  v.bindingIndex = top.bindingIndex;
  vs.bindingIndex = top.bindingIndex + 1;

  v.bindingType =
    if null(top.bindingTypes)
    then errorType()
    else head(top.bindingTypes);
  vs.bindingTypes =
    if null(top.bindingTypes)
    then []
    else tail(top.bindingTypes);
  v.bindingName =
    if null(top.bindingNames)
    then "__NONAME"
    else head(top.bindingNames);
  vs.bindingNames =
    if null(top.bindingNames)
    then []
    else tail(top.bindingNames);
}
concrete production nilVarBinder
top::VarBinders ::=
{
  top.unparse = "";
  
  top.translation = "";
  top.varBinderCount = 0;
  top.flowProjections = [];
}

concrete production varVarBinder
top::VarBinder ::= n::Name
{
  top.unparse = n.unparse;
  
  -- top.bindingType comes straight from the type in the production signature.
  -- Consequently, the child is only auto-decorated if
  -- isDecorable(top.bindingType, top.env), and never otherwise.
  -- (We *DO NOT* want to substitute first... because that will turn the type
  -- variables into concrete types! and type variables in a production are
  -- NOT automatically decorated!)
  local ty :: Type =
    if isDecorable(top.bindingType, top.env)
    then decoratedType(top.bindingType, freshInhSet())
    else top.bindingType;
  production finalTy::Type = performSubstitution(ty, top.finalSubst);
  production refSet::Maybe<[String]> = getMaxRefSet(finalTy, top.env);

  production fName :: String = "__pv" ++ toString(genIntT()) ++ ":" ++ n.name;
  
  -- If it's decorable, then we do projections through the production
  -- if it's not, then we treat it like a generic reference.
  top.flowProjections =
    if isDecorable(top.bindingType, top.env)
    then [patternVarProjection(top.bindingName, top.bindingType.typeName, fName)]
    else [];
  -- because we don't have an 'anonEq' (the nonterminal stitch point gets generated for us by the above contribution) we won't be reported as missing in this production. Checks for presence in remote productions have to be done explicitly

  -- Recall that we emit (vertex, [reference set]) for expressions with a vertex.
  -- and the correct value is computed based on how this gets used.
  -- (e.g. if 'new'
  local vt :: ExprVertexInfo =
    if isDecorable(top.bindingType, top.env)
    then hasVertex(anonVertexType(fName))
    else noVertex();
  local deps :: [FlowVertex] =
    if isDecorable(top.bindingType, top.env)
    then map(anonVertexType(fName).inhVertex, fromMaybe([], refSet))
    else [];

  top.defs <- [lexicalLocalDef(top.grammarName, n.location, fName, ty, vt, deps)];
  top.boundNames <- [n.name];

  -- finalSubst is not necessary, downSubst would work fine, but is not threaded through here.
  -- the point is that 'ty' for Pair<String Integer> would currently show Pair<a b>
  -- since top.bindingType comes straight from the production's type in the environment.
  -- we need to do some substitution to connect it with the real types.
  -- (in the env above its okay, since that must always be consulted with the current substitution,
  -- but here we're rendering the translation. it's the end of the line.)
  local actualTy :: Type = performSubstitution(ty, top.finalSubst);

  top.translation = 
    makeSpecialLocalBinding(fName, 
      "(" ++ actualTy.transType ++ ")scrutinee." ++ 
        (if isDecorable(top.bindingType, top.env)
         then "childDecorated("
         else "childAsIs(") ++
        toString(top.bindingIndex) ++ ")",
      actualTy.transType);
  
  -- We prevent this to prevent newbies from thinking patterns are "typecase"
  -- (Types have to be upper case)
  top.errors <-
    if !isUpper(substring(0,1,n.name)) then []
    else [err(top.location, "Pattern variables must start with a lower case letter")];

  -- We prevent this to avoid people possibly forgetting the parens, e.g. writing 'nothing'
  -- One thing we could do is specifically raise this error, only if it's the production would be the right type.
  -- this would allow us to match 'left' and 'right' on a Pair, for example, but error on Either
  top.errors <- 
    case getValueDcl(n.name, top.env) of
    | prodDcl(_,_) :: _ -> [err(top.location, "Pattern variables cannot have the same name as productions (to avoid confusion)")]
    | _ -> []
    end;
}
concrete production ignoreVarBinder
top::VarBinder ::= '_'
{
  top.unparse = "_";
  top.flowProjections = [];
  top.translation = "";
}

