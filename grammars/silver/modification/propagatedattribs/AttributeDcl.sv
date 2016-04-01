
concrete production attributeDclFunctor
top::AGDcl ::= 'functor' 'attribute' a::Name ';'
{
  top.pp = "functor attribute " ++ a.pp ++ ";";
  
  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;
  
  local tl::BracketedOptTypeList =
    botlSome(
      '<',
      typeListSingle(
        typeVariableType(terminal(IdLower_t, "a"), location=top.location),
        location=top.location), '>',
      location=top.location);
  tl.grammarName = top.grammarName;
      
  local te::Type = typeVariableType(terminal(IdLower_t, "a"), location=top.location);
  
  top.defs = [functorDef(top.grammarName, a.location, fName, tl.freeVariables, te.typerep)];
  
  tl.initialEnv = top.env;
  tl.env = tl.envBindingTyVars;
  te.env = tl.envBindingTyVars;

  forwards to attributeDclSyn('synthesized', $2, a, tl, '::', te, ';', location=top.location);
}