grammar silver:translation:java:core;

import silver:util;

attribute attrOccursIndexName, attrOccursIndex, attrOccursType occurs on DclInfo;

{--
 - The name of the occurs variable. e.g. silver_def_core_pp__ON__silver_def_core_Expr
 -}
synthesized attribute attrOccursIndexName :: String;
{--
 - Index of the attribute. e.g. silver.def.core.silver_def_core_pp__ON__silver_def_core_Expr
 -}
synthesized attribute attrOccursIndex :: String;
{--
 - Kludge to get occurs dcls working for now. Do not use in more places!
 -}
synthesized attribute attrOccursType :: String;

aspect production occursDcl
top::DclInfo ::= sg::String sl::Decorated Location fnnt::String fnat::String ntty::TypeExp atty::TypeExp
{
  top.attrOccursIndexName = makeIdName(fnat ++ "__ON__" ++ fnnt);
  top.attrOccursIndex = makeName(sg) ++ ".Init." ++ top.attrOccursIndexName;
}

aspect production synDcl
top::DclInfo ::= sg::String sl::Decorated Location fn::String bound::[TyVar] ty::TypeExp
{
  top.attrOccursType = "syn";
}
aspect production inhDcl
top::DclInfo ::= sg::String sl::Decorated Location fn::String bound::[TyVar] ty::TypeExp
{
  top.attrOccursType = "inh";
}

aspect production localDcl
top::DclInfo ::= sg::String sl::Decorated Location fn::String ty::TypeExp
{
  local attribute li :: Integer;
  li = lastIndexOf(":local:", fn);
  top.attrOccursIndexName = makeIdName(substring(li+7, length(fn), fn) ++ "__ON__" ++ substring(0,li,fn));
  top.attrOccursIndex = makeName(sg) ++ ".Init." ++ top.attrOccursIndexName;
}
