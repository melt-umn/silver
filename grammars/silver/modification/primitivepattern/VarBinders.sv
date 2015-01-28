grammar silver:modification:primitivepattern;

import silver:translation:java:core;
import silver:translation:java:type;

import silver:modification:let_fix only makeSpecialLocalBinding, lexicalLocalDef;
import silver:definition:flow:ast only noVertex;

nonterminal VarBinders with 
  config, grammarName, env, compiledGrammars, signature, blockContext,
  location, pp, errors, defs,
  bindingTypes, bindingIndex, translation,
  finalSubst;
nonterminal VarBinder with
  config, grammarName, env, compiledGrammars, signature, blockContext,
  location, pp, errors, defs,
  bindingType, bindingIndex, translation,
  finalSubst;

inherited attribute bindingType :: TypeExp;
inherited attribute bindingIndex :: Integer;


concrete production oneVarBinder
top::VarBinders ::= v::VarBinder
{
  top.pp = v.pp;
  top.defs = v.defs;
  top.errors := v.errors;

  top.translation = v.translation;

  v.bindingIndex = top.bindingIndex;
  v.bindingType = if null(top.bindingTypes)
                  then errorType()
                  else head(top.bindingTypes);
  
  top.errors <- if null(top.bindingTypes)
                then [err(top.location, "More patterns than expected in pattern list")]
                else [];
  top.errors <- if length(top.bindingTypes) > 1
                then [err(top.location, "Fewer patterns than expected in pattern list")]
                else [];
}
concrete production consVarBinder
top::VarBinders ::= v::VarBinder ',' vs::VarBinders
{
  top.pp = v.pp ++ ", " ++ vs.pp;
  top.defs = v.defs ++ vs.defs;
  top.errors := v.errors ++ vs.errors;

  top.translation = v.translation ++ vs.translation;

  v.bindingIndex = top.bindingIndex;
  vs.bindingIndex = top.bindingIndex + 1;

  v.bindingType = if null(top.bindingTypes)
                  then errorType()
                  else head(top.bindingTypes);
  vs.bindingTypes = if null(top.bindingTypes)
                  then []
                  else tail(top.bindingTypes);
}
concrete production nilVarBinder
top::VarBinders ::=
{
  top.pp = "";
  top.defs = [];
  top.errors := [];
  
  top.translation = "";

  top.errors <- if !null(top.bindingTypes)
                then [err(top.location, "Fewer patterns than expected in pattern list")]
                else [];
}

concrete production varVarBinder
top::VarBinder ::= n::Name
{
  top.pp = n.pp;
  
  -- top.bindingType comes straight from the type in the production signature.
  -- Consequently, the child is only auto-decorated if
  -- top.bindingType.isDecorable, and never otherwise.
  -- (We *DO NOT* want to substitute first... because that will turn the type
  -- variables into concrete types! and type variables in a production are
  -- NOT automatically decorated!)
  local ty :: TypeExp =
    if top.bindingType.isDecorable
    then decoratedTypeExp(top.bindingType)
    else top.bindingType;

  local fName :: String = toString(genInt()) ++ ":" ++ n.name;

  top.defs = [lexicalLocalDef(top.grammarName, n.location, fName, ty, noVertex(), [])]; -- TODO: these deps??

  -- finalSubst is not necessary, downSubst would work fine, but is not threaded through here.
  -- the point is that 'ty' for Pair<String Integer> would currently show Pair<a b>
  -- since top.bindingType comes straight from the production's type in the environment.
  -- we need to do some substitution to connect it with the real types.
  -- (in the env above its okay, since that must always be consulted with the current substitution,
  -- but here we're rendering the translation. it's the end of the line.)
  local actualTy :: TypeExp = performSubstitution(ty, top.finalSubst);

  top.translation = 
    makeSpecialLocalBinding(fName, 
      "(" ++ actualTy.transType ++ ")scrutinee." ++ 
        (if top.bindingType.isDecorable
         then "childDecorated("
         else "childAsIs(") ++
        toString(top.bindingIndex) ++ ")",
      actualTy.transType);
  
  -- We prevent this to prevent newbies from thinking patterns are "typecase"
  -- (Types have to be upper case)
  top.errors := 
    if !isUpper(substring(0,1,n.name)) then []
    else [err(top.location, "Pattern variables must start with a lower case letter")];

  -- We prevent this to avoid people possibly forgetting the parens, e.g. writing 'nothing'
  -- One thing we could do is specifically raise this error, only if it's the production would be the right type.
  -- this would allow us to match 'left' and 'right' on a Pair, for example, but error on Either
  top.errors <- 
    case getValueDcl(n.name, top.env) of
    | prodDcl(_,_,_) :: _ -> [err(top.location, "Pattern variables cannot have the same name as productions (to avoid confusion)")]
    | _ -> []
    end;
}
concrete production ignoreVarBinder
top::VarBinder ::= '_'
{
  top.pp = "_";
  top.defs = [];
  top.errors := [];
  top.translation = "";
}

