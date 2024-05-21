grammar silver:compiler:definition:env;

-- Just to clarify:
-- call prettyType to pretty print the type.
-- get typeName to find out what nonterminal a NT or DNT is
synthesized attribute typeName :: String;

attribute typeName occurs on PolyType;

aspect production monoType
top::PolyType ::= ty::Type
{
  top.typeName = ty.typeName;
}

aspect production polyType
top::PolyType ::= tvs::[TyVar] ty::Type
{
  top.typeName = ty.typeName;
}

aspect production constraintType
top::PolyType ::= tvs::[TyVar] contexts::[Context] ty::Type
{
  top.typeName = ty.typeName;
}

attribute typeName occurs on Type;

aspect default production
top::Type ::=
{
  top.typeName = ""; -- We actually put a value here, since it's possible for us to request typeName of nonsensical things.
}

aspect production appType
top::Type ::= c::Type a::Type
{
  top.typeName = c.typeName;
}

aspect production nonterminalType
top::Type ::= fn::String _ _ _
{
  top.typeName = fn;
}

aspect production terminalType
top::Type ::= fn::String
{
  top.typeName = fn;
}

aspect production decoratedType
top::Type ::= te::Type _
{
  top.typeName = te.typeName;
}

aspect production uniqueDecoratedType
top::Type ::= te::Type _
{
  top.typeName = te.typeName;
}

aspect production varType
top::Type ::= tv::TyVar
{
  top.typeName = tv.typeName;
}

aspect production skolemType
top::Type ::= tv::TyVar
{
  top.typeName = tv.typeName;
}

aspect production dispatchType
top::Type ::= ns::NamedSignature
{
  top.typeName = ns.fullName;
}

attribute typeName occurs on TyVar;

aspect production tyVar
top::TyVar ::= 
{
  top.typeName = "_a" ++ toString(top.varId);
}

aspect production tyVarNamed
top::TyVar ::= n::String
{
  top.typeName = n;
}
