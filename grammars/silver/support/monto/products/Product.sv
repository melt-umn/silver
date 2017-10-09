grammar silver:support:monto:products;

import lib:json;

synthesized attribute productName :: String;
nonterminal ProductValue with json, productName;

abstract production productValue
top::ProductValue ::= name::String json::Json
{
  top.json = json;
  top.productName = name;
}

synthesized attribute productLang :: String;
synthesized attribute productPath :: String;
synthesized attribute productValue :: ProductValue;
nonterminal Product with json, productName, productLang, productPath, productValue;

abstract production product
top::Product ::= value::ProductValue metas::[MetaItem] language::String path::String
{
  top.productName = top.productValue.productName;
  top.productLang = language;
  top.productPath = path;
  top.productValue = value;
  top.json = jsonObject(
    [ pair("contents", value.json)
    , pair("language", jsonString(top.productLang))
    , pair("name", jsonString(top.productName))
    , pair("meta", jsonArray(map((.json), metas)))
    , pair("path", jsonString(top.productPath))
    ]);
}

nonterminal ProductDescriptor with json, productName, productLang;

abstract production productDescriptor
top::ProductDescriptor ::= name::String language::String
{
  top.productName = name;
  top.productLang = language;
  top.json = jsonObject(
    [ pair("name", jsonString(top.productName))
    , pair("language", jsonString(top.productLang))
    ]);
}

function productDescriptorEq
Boolean ::= l::ProductDescriptor r::ProductDescriptor
{
  return l.productName == r.productName && l.productLang == r.productLang;
}

nonterminal ProductIdentifier with json, productName, productLang, productPath;

abstract production productIdentifier
top::ProductIdentifier ::= name::String language::String path::String
{
  top.productName = name;
  top.productLang = language;
  top.productPath = path;
  top.json = jsonObject(
    [ pair("name", jsonString(top.productName))
    , pair("language", jsonString(top.productLang))
    , pair("path", jsonString(top.productPath))
    ]);
}

nonterminal MetaItem with json;

abstract production metaItem
top::MetaItem ::= service::String metaType::String value::String
{
  top.json = jsonObject(
    [ pair("service", jsonString(service))
    , pair("type", jsonString(metaType))
    , pair("value", jsonString(value))
    ]);
}

abstract production metaDegraded
top::MetaItem ::= service::String value::String
{
  forwards to metaItem(service, "degraded", value);
}
