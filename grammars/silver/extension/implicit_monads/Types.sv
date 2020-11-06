grammar silver:extension:implicit_monads;


abstract production explicitType
top::Type ::= t::Type
{
  top.typepp = t.typepp;

  top.substituted = explicitType(t.substituted);
  top.flatRenamed = explicitType(t.flatRenamed);

  forwards to t;
}



abstract production implicitType
top::Type ::= t::Type
{
  top.typepp = t.typepp;

  top.substituted = implicitType(t.substituted);
  top.flatRenamed = implicitType(t.flatRenamed);

  forwards to if isMonad(t)
              then t
              else errorType();
}









concrete production attributeDclInh_Restricted
top::AGDcl ::= 'restricted' 'inherited' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.unparse = "restricted inherited attribute " ++ a.unparse ++ tl.unparse ++ " :: " ++ te.unparse ++ ";";

  top.defs := [restrictedInhDef(top.grammarName, a.location, fName, tl.freeVariables, explicitType(te.typerep))];

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  local fwd::AGDcl = defsAGDcl([restrictedInhDef(top.grammarName, a.location, fName, tl.freeVariables, explicitType(te.typerep))], location=top.location);

  forwards to fwd;
}

concrete production attributeDclSyn_Restricted
top::AGDcl ::= 'restricted' 'synthesized' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.unparse = "restricted synthesized attribute " ++ a.unparse ++ tl.unparse ++ " :: " ++ te.unparse ++ ";";

  top.defs := [restrictedSynDef(top.grammarName, a.location, fName, tl.freeVariables, explicitType(te.typerep))];

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  local fwd::AGDcl = defsAGDcl([restrictedSynDef(top.grammarName, a.location, fName, tl.freeVariables, explicitType(te.typerep))], location=top.location);

  forwards to fwd;
}




concrete production attributeDclInh_Implicit
top::AGDcl ::= 'implicit' 'inherited' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.unparse = "implicit inherited attribute " ++ a.unparse ++ tl.unparse ++ " :: " ++ te.unparse ++ ";";

  top.defs := [implicitInhDef(top.grammarName, a.location, fName, tl.freeVariables, implicitType(te.typerep))];

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  local fwd::AGDcl = defsAGDcl([implicitInhDef(top.grammarName, a.location, fName, tl.freeVariables, implicitType(te.typerep))], location=top.location);

  forwards to fwd;
}

concrete production attributeDclSyn_Implicit
top::AGDcl ::= 'implicit' 'synthesized' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.unparse = "implicit synthesized attribute " ++ a.unparse ++ tl.unparse ++ " :: " ++ te.unparse ++ ";";

  top.defs := [implicitSynDef(top.grammarName, a.location, fName, tl.freeVariables, implicitType(te.typerep))];

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  local fwd::AGDcl = defsAGDcl([implicitSynDef(top.grammarName, a.location, fName, tl.freeVariables, implicitType(te.typerep))], location=top.location);

  forwards to fwd;
}




concrete production attributeDclInh_Unrestricted
top::AGDcl ::= 'unrestricted' 'inherited' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.unparse = "unrestricted inherited attribute " ++ a.unparse ++ tl.unparse ++ " :: " ++ te.unparse ++ ";";

  forwards to attributeDclInh('inherited', 'attribute', a, tl, '::', te, ';', location=top.location);
}

concrete production attributeDclSyn_Unrestricted
top::AGDcl ::= 'unrestricted' 'synthesized' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.unparse = "unrestricted synthesized attribute " ++ a.unparse ++ tl.unparse ++ " :: " ++ te.unparse ++ ";";

  forwards to attributeDclSyn('synthesized', 'attribute', a, tl, '::', te, ';', location=top.location);
}

