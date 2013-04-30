grammar silver:modification:primitivepattern;

import silver:translation:java:core;
import silver:translation:java:type;

import silver:modification:let_fix only makeSpecialLocalBinding, lexicalLocalDef;

nonterminal VarBinders with 
  config, file, grammarName, env, compiledGrammars, signature, blockContext,
  location, pp, errors, defs,
  bindingTypes, bindingIndex, translation;
nonterminal VarBinder with
  config, file, grammarName, env, compiledGrammars, signature, blockContext,
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
  
  -- bindingType comes straight from the type in the production signature, so this logic
  -- should be 100% cool because only statically known nonterminal types are ones that
  -- become decorated.  So it's impossible for inference to affect this logic:
  local attribute ty :: TypeExp;
  ty = if top.bindingType.isDecorable
       then decoratedTypeExp(top.bindingType)
       else top.bindingType;

  top.defs = [lexicalLocalDef(top.grammarName, n.location, n.name, ty)];

  top.translation = makeSpecialLocalBinding(n.name, 
             "(" ++ ty.transType ++ ")scrutinee." ++ 
                (if top.bindingType.isDecorable
                 then "childDecorated("
                 else "childAsIs(")
             ++ toString(top.bindingIndex) ++ ")", ty.transType);
  
  --top.errors := []; -- TODO: check for rebinding? or not perhaps...

  ---- TODO: Should be here, but does nothing
  -- MUST start with lower case #HACK2012
  top.errors := (if isUpper(substring(0,1,n.name))
                 then [err(top.location, "Pattern variable names start with a lower case letter")]
                 else [])
  -- MUST NOT shadow any _production_ names #HACK2012
  -- TODO: Add function to find all prodDcl in env
             ++ (case getValueDcl(n.name, top.env) of
                 | prodDcl(_,_,_) :: _ -> [err(top.location, "Production name can't be used in pattern")]
                 | _ -> []
                 end) ;
}
concrete production ignoreVarBinder
top::VarBinder ::= '_'
{
  top.pp = "_";
  top.defs = [];
  top.errors := [];
  top.translation = "";
}

