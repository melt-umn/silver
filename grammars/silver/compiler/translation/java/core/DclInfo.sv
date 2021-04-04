grammar silver:compiler:translation:java:core;


attribute attrOccursIndexName, attrOccursInitIndex, attrOccursIndex occurs on DclInfo;

{--
 - The name of the occurs variable. e.g. silver_def_core_pp__ON__silver_def_core_Expr
 -}
synthesized attribute attrOccursIndexName :: String;
{--
 - Index of the attribute used for initializating attribute equations.
 - e.g. silver.def.core.silver_def_core_pp__ON__silver_def_core_Expr
 - or foo.bar.PExpr.foo_bar_inh__ON__a for an inherited occurs-on constraing
 -}
synthesized attribute attrOccursInitIndex :: String;
{--
 - Index of the attribute used for accessing the attribute on a DecoratedNode.
 - e.g. silver.def.core.silver_def_core_pp__ON__silver_def_core_Expr
 - or foo.bar.PExpr.d_foo_bar_inh__a for an inherited occurs-on constraint
 -}
synthesized attribute attrOccursIndex :: String;

aspect default production
top::DclInfo ::=
{
  -- See TODO in the env DclInfo
  top.attrOccursIndexName = error("Internal compiler error: must be defined for all occurs declarations");
  top.attrOccursInitIndex = error("Internal compiler error: must be defined for all occurs declarations");

  top.attrOccursIndex = top.transContext;
}


aspect production occursDcl
top::DclInfo ::= fnnt::String fnat::String ntty::Type atty::Type
{
  top.attrOccursIndexName = makeIdName(fnat ++ "__ON__" ++ fnnt);
  top.attrOccursInitIndex = top.attrOccursIndex;
  top.attrOccursIndex = makeName(top.sourceGrammar) ++ ".Init." ++ top.attrOccursIndexName;
}
aspect production occursInstConstraintDcl
top::DclInfo ::= fnat::String ntty::Type atty::Type tvs::[TyVar]
{
  top.attrOccursIndexName = makeIdName(fnat ++ "__ON__" ++ ntty.typeName);
  top.attrOccursInitIndex = top.attrOccursIndex;
}
aspect production occursSigConstraintDcl
top::DclInfo ::= fnat::String ntty::Type atty::Type ns::NamedSignature
{
  top.attrOccursIndexName = makeIdName(fnat ++ "__ON__" ++ ntty.typeName);
  top.attrOccursInitIndex = makeProdName(ns.fullName) ++ "." ++ top.attrOccursIndexName;
}
aspect production occursSuperDcl
top::DclInfo ::= fnat::String atty::Type baseDcl::DclInfo
{
  top.attrOccursIndexName = makeIdName(fnat ++ "__ON__" ++ baseDcl.typeScheme.typeName);
  top.attrOccursInitIndex = top.attrOccursIndex;
}
aspect production annoInstanceDcl
top::DclInfo ::= fnnt::String fnat::String ntty::Type atty::Type
{
  top.attrOccursIndexName = error("Not actually an attribute");
  top.attrOccursInitIndex = error("Not actually an attribute");
  top.attrOccursIndex = error("Not actually an attribute");
}


aspect production localDcl
top::DclInfo ::= fn::String ty::Type
{
  -- TODO: BUG: See https://github.com/melt-umn/silver/issues/52
  -- This is the kind of nasty hack that we might fix with a FullName type, instead of hacking on 'fn'
  -- Also, this choice of name is actually buggy! Can cause java compiler errors with name collisions.
  local attribute li :: Integer;
  li = lastIndexOf(":local:", fn);
  top.attrOccursIndexName = makeIdName(substring(li+7, length(fn), fn) ++ "__ON__" ++ substring(0,li,fn));
  top.attrOccursInitIndex = top.attrOccursIndex;
  top.attrOccursIndex = makeName(top.sourceGrammar) ++ ".Init." ++ top.attrOccursIndexName;
}

