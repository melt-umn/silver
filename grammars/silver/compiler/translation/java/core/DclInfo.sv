grammar silver:compiler:translation:java:core;


attribute attrOccursIndexName, attrOccursInitIndex, attrGlobalOccursInitIndex, attrOccursIndex occurs on OccursDclInfo;

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
 - Index of the attribute used for initializating attribute equations.
 - Only defined for global occurs-on declarations, doesn't depend on transContextDeps.
 - e.g. silver.def.core.silver_def_core_pp__ON__silver_def_core_Expr
 -}
synthesized attribute attrGlobalOccursInitIndex :: String;
{--
 - Index of the attribute used for accessing the attribute on a DecoratedNode.
 - e.g. silver.def.core.silver_def_core_pp__ON__silver_def_core_Expr
 - or foo.bar.PExpr.d_foo_bar_inh__a for an inherited occurs-on constraint
 -}
synthesized attribute attrOccursIndex :: String;

aspect default production
top::OccursDclInfo ::=
{
  top.attrGlobalOccursInitIndex = error("Should only be accessed on global occursDcl");
}

aspect production occursDcl
top::OccursDclInfo ::= fnnt::String fnat::String ntty::Type atty::Type
{
  top.attrOccursIndexName = makeIdName(fnat ++ "__ON__" ++ fnnt);
  top.attrOccursInitIndex = top.attrGlobalOccursInitIndex;
  top.attrGlobalOccursInitIndex = makeName(top.sourceGrammar) ++ ".Init." ++ top.attrOccursIndexName;
  top.attrOccursIndex = top.attrOccursInitIndex;
}
aspect production occursInstConstraintDcl
top::OccursDclInfo ::= fnat::String ntty::Type atty::Type tvs::[TyVar]
{
  top.attrOccursIndexName = makeIdName(fnat ++ "__ON__" ++ ntty.transTypeName);
  top.attrOccursInitIndex = top.attrOccursIndex;
  top.attrOccursIndex = makeConstraintDictName(fnat, ^ntty, tvs);
}
aspect production occursSigConstraintDcl
top::OccursDclInfo ::= fnat::String ntty::Type atty::Type ns::NamedSignature
{
  top.attrOccursIndexName = makeIdName(fnat ++ "__ON__" ++ ntty.transTypeName);
  top.attrOccursInitIndex = makeProdName(ns.fullName) ++ "." ++ top.attrOccursIndexName;
  top.attrOccursIndex = s"((${makeProdName(ns.fullName)})(context.getNode())).${makeConstraintDictName(fnat, ^ntty, ns.freeVariables)}";
}
aspect production occursSuperDcl
top::OccursDclInfo ::= fnat::String atty::Type baseDcl::InstDclInfo
{
  top.attrOccursIndexName = makeIdName(fnat ++ "__ON__" ++ transTypeNameWith(baseDcl.typeScheme.typerep, baseDcl.typeScheme.boundVars));
  top.attrOccursInitIndex = top.attrOccursIndex;
  top.attrOccursIndex = baseDcl.transContext ++ s".${makeInstanceSuperAccessorName(fnat)}()";
}
aspect production annoInstanceDcl
top::OccursDclInfo ::= fnnt::String fnat::String ntty::Type atty::Type
{
  top.attrOccursIndexName = error("Not actually an attribute");
  top.attrOccursInitIndex = error("Not actually an attribute");
  top.attrGlobalOccursInitIndex = error("Not actually an attribute");
  top.attrOccursIndex = error("Not actually an attribute");
}
aspect production annoInstConstraintDcl
top::OccursDclInfo ::= fnat::String ntty::Type atty::Type tvs::[TyVar]
{
  top.attrOccursIndexName = error("Not actually an attribute");
  top.attrOccursInitIndex = error("Not actually an attribute");
  top.attrOccursIndex = error("Not actually an attribute");
}
aspect production annoSigConstraintDcl
top::OccursDclInfo ::= fnat::String ntty::Type atty::Type ns::NamedSignature
{
  top.attrOccursIndexName = error("Not actually an attribute");
  top.attrOccursInitIndex = error("Not actually an attribute");
  top.attrOccursIndex = error("Not actually an attribute");
}
aspect production annoSuperDcl
top::OccursDclInfo ::= fnat::String atty::Type baseDcl::InstDclInfo
{
  top.attrOccursIndexName = error("Not actually an attribute");
  top.attrOccursInitIndex = error("Not actually an attribute");
  top.attrOccursIndex = error("Not actually an attribute");
}

attribute attrOccursIndexName, attrOccursInitIndex, attrOccursIndex occurs on ValueDclInfo;

aspect default production
top::ValueDclInfo ::=
{
  top.attrOccursIndexName = error("Internal compiler error: not a local/production attribute");
  top.attrOccursInitIndex = error("Internal compiler error: not a local/production attribute");
  top.attrOccursIndex = error("Internal compiler error: not a local/production attribute");
}

aspect production localDcl
top::ValueDclInfo ::= fn::String ty::Type
{
  local attribute li :: Integer;
  li = lastIndexOf(":local:", fn);
  top.attrOccursIndexName = makeIdName(substring(li+7, length(fn), fn) ++ "__ON__" ++ substring(0,li,fn));
  top.attrOccursInitIndex = top.attrOccursIndex;
  top.attrOccursIndex = makeName(top.sourceGrammar) ++ ".Init." ++ top.attrOccursIndexName;
}
aspect production nondecLocalDcl
top::ValueDclInfo ::= fn::String ty::Type
{
  local attribute li :: Integer;
  li = lastIndexOf(":local:", fn);
  top.attrOccursIndexName = makeIdName(substring(li+7, length(fn), fn) ++ "__ON__" ++ substring(0,li,fn));
  top.attrOccursInitIndex = top.attrOccursIndex;
  top.attrOccursIndex = makeName(top.sourceGrammar) ++ ".Init." ++ top.attrOccursIndexName;
}
aspect production forwardLocalDcl
top::ValueDclInfo ::= fn::String ty::Type
{
  local attribute li :: Integer;
  li = lastIndexOf(":local:", fn);
  top.attrOccursIndexName = makeIdName(substring(li+7, length(fn), fn) ++ "__ON__" ++ substring(0,li,fn));
  top.attrOccursInitIndex = top.attrOccursIndex;
  top.attrOccursIndex = makeName(top.sourceGrammar) ++ ".Init." ++ top.attrOccursIndexName;
}

