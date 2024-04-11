grammar silver:compiler:definition:core;

concrete production dispatchSigDcl
top::AGDcl ::= 'dispatch' id::Name '=' sig::ProductionSignature ';'
{
  top.unparse = "dispatch " ++ id.unparse ++ " = " ++ sig.unparse ++ ";";

  top.defs := [dispatchDef(top.grammarName, id.nameLoc, sig.namedSignature)];

  production fName :: String = top.grammarName ++ ":" ++ id.name;
  sig.signatureName = fName;
  sig.implementedSig = nothing();
  sig.env = newScopeEnv(sig.defs, top.env);

  production namedSig :: NamedSignature = sig.namedSignature;

  top.errors <- 
    if length(getTypeDclAll(fName, top.env)) > 1 
    then [errFromOrigin(id, "Type '" ++ fName ++ "' is already bound.")]
    else [];

  top.errors <-
    if isLower(substring(0,1,id.name))
    then [errFromOrigin(id, "Types must be capitalized. Invalid dispatch name " ++ id.name)]
    else [];
}
