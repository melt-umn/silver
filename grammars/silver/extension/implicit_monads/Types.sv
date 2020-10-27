grammar silver:extension:implicit_monads;


terminal Explicit_t 'Restricted';
terminal Implicit_t 'Implicit';
terminal Unrestricted_t 'Unrestricted';


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
top::AGDcl ::= 'Restricted' 'inherited' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.unparse = "Restricted inherited attribute " ++ a.unparse ++ tl.unparse ++ " :: " ++ te.unparse ++ ";";

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  top.defs := [restrictedInhDef(top.grammarName, a.location, fName, tl.freeVariables, te.typerep)];

  local fwd::AGDcl = attributeDclInh('inherited', 'attribute', a, tl, '::', typerepTypeExpr(explicitType(te.typerep), location=te.location), ';', location=top.location);

  forwards to fwd;
}

concrete production attributeDclSyn_Restricted
top::AGDcl ::= 'Restricted' 'synthesized' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.unparse = "Restricted synthesized attribute " ++ a.unparse ++ tl.unparse ++ " :: " ++ te.unparse ++ ";";

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  top.defs := [restrictedSynDef(top.grammarName, a.location, fName, tl.freeVariables, te.typerep)];

  local fwd::AGDcl = attributeDclSyn('synthesized', 'attribute', a, tl, '::', typerepTypeExpr(explicitType(te.typerep), location=te.location), ';', location=top.location);

  forwards to fwd;
}




concrete production attributeDclInh_Implicit
top::AGDcl ::= 'Implicit' 'inherited' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.unparse = "Implicit inherited attribute " ++ a.unparse ++ tl.unparse ++ " :: " ++ te.unparse ++ ";";

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  top.defs := [implicitInhDef(top.grammarName, a.location, fName, tl.freeVariables, te.typerep)];

  local fwd::AGDcl = attributeDclInh('inherited', 'attribute', a, tl, '::', typerepTypeExpr(implicitType(te.typerep), location=te.location), ';', location=top.location);

  forwards to fwd;
}

concrete production attributeDclSyn_Implicit
top::AGDcl ::= 'Implicit' 'synthesized' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.unparse = "Implicit synthesized attribute " ++ a.unparse ++ tl.unparse ++ " :: " ++ te.unparse ++ ";";

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  top.defs := [implicitSynDef(top.grammarName, a.location, fName, tl.freeVariables, te.typerep)];

  local fwd::AGDcl = attributeDclSyn('synthesized', 'attribute', a, tl, '::', typerepTypeExpr(implicitType(te.typerep), location=te.location), ';', location=top.location);

  forwards to fwd;
}




concrete production attributeDclInh_Unrestricted
top::AGDcl ::= 'Unrestricted' 'inherited' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.unparse = "Unrestricted inherited attribute " ++ a.unparse ++ tl.unparse ++ " :: " ++ te.unparse ++ ";";

  forwards to attributeDclInh('inherited', 'attribute', a, tl, '::', te, ';', location=top.location);
}

concrete production attributeDclSyn_Unrestricted
top::AGDcl ::= 'Unrestricted' 'synthesized' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.unparse = "Unrestricted synthesized attribute " ++ a.unparse ++ tl.unparse ++ " :: " ++ te.unparse ++ ";";

  forwards to attributeDclSyn('synthesized', 'attribute', a, tl, '::', te, ';', location=top.location);
}

