grammar silver:modification:primitivepattern;

import silver:translation:java:core;
import silver:translation:java:type;

import silver:modification:let_fix only makeSpecialLocalBinding, lexicalLocalDef;
import silver:definition:flow:ast only noVertex;

nonterminal VarBinders with 
  config, grammarName, env, compiledGrammars, signature, blockContext,
  location, pp, errors, defs,
  bindingTypes, bindingIndex, translation;
nonterminal VarBinder with
  config, grammarName, env, compiledGrammars, signature, blockContext,
  location, pp, errors, defs,
  bindingType, bindingIndex, translation;

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

  top.defs = [lexicalLocalDef(top.grammarName, n.location, n.name, ty, noVertex(), [])]; -- TODO: these deps??

  top.translation = 
    makeSpecialLocalBinding(n.name, 
      "(" ++ ty.transType ++ ")scrutinee." ++ 
        (if top.bindingType.isDecorable
         then "childDecorated("
         else "childAsIs(") ++
        toString(top.bindingIndex) ++ ")",
      ty.transType);
  
  -- We prevent this to prevent newbies from thinking patterns are "typecase"
  -- (Types have to be upper case)
  top.errors := 
    if !isUpper(substring(0,1,n.name)) then []
    else [err(top.location, "Pattern variable names start with a lower case letter")];

  -- We prevent this to avoid people possibly forgetting the parens, e.g. writing 'nothing'
  top.errors <- 
    case getValueDcl(n.name, top.env) of
    | prodDcl(_,_,_) :: _ -> [err(top.location, "Production name can't be used in pattern")]
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

