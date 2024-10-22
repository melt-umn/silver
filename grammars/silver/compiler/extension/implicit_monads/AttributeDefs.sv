grammar silver:compiler:extension:implicit_monads;


concrete production attributeDclInh_Restricted
top::AGDcl ::= 'restricted' 'inherited' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.unparse = "restricted inherited attribute " ++ a.unparse ++ tl.unparse ++ " :: " ++ te.unparse ++ ";";
  propagate grammarName, flowEnv;

  top.moduleNames := [];

  tl.initialEnv = top.env;
  tl.env = tl.envBindingTyVars;
  te.env = tl.envBindingTyVars;

  top.errors := [];

  top.errors <-
    if length(getAttrDclAll(fName, top.env)) > 1
    then [errFromOrigin(a, "Attribute '" ++ fName ++ "' is already bound.")]
    else [];

  top.errors <- tl.errorsTyVars;

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  nondecorated local fwd::AGDcl =
    defsAGDcl([restrictedInhDef(top.grammarName, a.nameLoc, fName, tl.freeVariables, te.typerep)]);

  forwards to fwd;
}


concrete production attributeDclSyn_Restricted
top::AGDcl ::= 'restricted' 'synthesized' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.unparse = "restricted synthesized attribute " ++ a.unparse ++ tl.unparse ++ " :: " ++ te.unparse ++ ";";
  propagate grammarName, flowEnv;

  top.moduleNames := [];

  tl.initialEnv = top.env;
  tl.env = tl.envBindingTyVars;
  te.env = tl.envBindingTyVars;

  top.errors := [];

  top.errors <-
    if length(getAttrDclAll(fName, top.env)) > 1
    then [errFromOrigin(a, "Attribute '" ++ fName ++ "' is already bound.")]
    else [];

  top.errors <- tl.errorsTyVars;

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  nondecorated local fwd::AGDcl =
    defsAGDcl([restrictedSynDef(top.grammarName, a.nameLoc, fName, tl.freeVariables, te.typerep)]);

  forwards to fwd;
}




concrete production attributeDclInh_Implicit
top::AGDcl ::= 'implicit' 'inherited' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.unparse = "implicit inherited attribute " ++ a.unparse ++ tl.unparse ++ " :: " ++ te.unparse ++ ";";
  propagate grammarName, flowEnv;

  top.moduleNames := [];

  tl.initialEnv = top.env;
  tl.env = tl.envBindingTyVars;
  te.env = tl.envBindingTyVars;

  top.errors := if isMonad(te.typerep, top.env)
                then []
                else [errFromOrigin(top, "Implicit attributes must have a monadic type; " ++
                                        prettyType(te.typerep) ++ " is not monadic")];
  top.errors <-
    if length(getAttrDclAll(fName, top.env)) > 1
    then [errFromOrigin(a, "Attribute '" ++ fName ++ "' is already bound.")]
    else [];

  top.errors <- tl.errorsTyVars;

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  nondecorated local fwd::AGDcl =
    defsAGDcl([implicitInhDef(top.grammarName, a.nameLoc, fName, tl.freeVariables, te.typerep)]);

  forwards to fwd;
}


concrete production attributeDclSyn_Implicit
top::AGDcl ::= 'implicit' 'synthesized' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.unparse = "implicit synthesized attribute " ++ a.unparse ++ tl.unparse ++ " :: " ++ te.unparse ++ ";";
  propagate grammarName, flowEnv;

  top.moduleNames := [];

  tl.initialEnv = top.env;
  tl.env = tl.envBindingTyVars;
  te.env = tl.envBindingTyVars;

  top.errors := if isMonad(te.typerep, top.env)
                then []
                else [errFromOrigin(top, "Implicit attributes must have a monadic type; " ++
                                        prettyType(te.typerep) ++ " is not monadic")];
  top.errors <-
    if length(getAttrDclAll(fName, top.env)) > 1
    then [errFromOrigin(a, "Attribute '" ++ fName ++ "' is already bound.")]
    else [];

  top.errors <- tl.errorsTyVars;

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  nondecorated local fwd::AGDcl =
    defsAGDcl([implicitSynDef(top.grammarName, a.nameLoc, fName, tl.freeVariables, te.typerep)]);

  forwards to fwd;
}




concrete production attributeDclInh_Unrestricted
top::AGDcl ::= 'unrestricted' 'inherited' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.unparse = "unrestricted inherited attribute " ++ a.unparse ++ tl.unparse ++ " :: " ++ te.unparse ++ ";";

  forwards to attributeDclInh('inherited', 'attribute', @a, @tl, '::', @te, ';');
}


concrete production attributeDclSyn_Unrestricted
top::AGDcl ::= 'unrestricted' 'synthesized' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.unparse = "unrestricted synthesized attribute " ++ a.unparse ++ tl.unparse ++ " :: " ++ te.unparse ++ ";";

  forwards to attributeDclSyn('synthesized', 'attribute', @a, @tl, '::', @te, ';');
}

