grammar silver:definition:env;

synthesized attribute boundVars :: [TyVar] occurs on PolyType;
attribute typerep occurs on PolyType;

aspect production monoType
top::PolyType ::= ty::Type
{
  top.boundVars = [];
  top.typerep = ty;
}

aspect production polyType
top::PolyType ::= tvs::[TyVar] ty::Type
{
  top.boundVars = freshTyVars(length(tvs));
  top.typerep = freshenTypeWith(ty, tvs, top.boundVars);
}

-- Just to clarify:
-- call prettyType to pretty print the type.
-- get typeName to find out what nonterminal a NT or DNT is

attribute typeName occurs on Type;

synthesized attribute typeName :: String;

aspect default production
top::Type ::=
{
  top.typeName = ""; -- We actually put a value here, since it's possible for us to request typeName of nonsensical things.
}

aspect production nonterminalType
top::Type ::= fn::String params::[Type]
{
  top.typeName = fn;
}

aspect production terminalType
top::Type ::= fn::String
{
  top.typeName = fn;
}

aspect production decoratedType
top::Type ::= te::Type
{
  top.typeName = te.typeName;
}

