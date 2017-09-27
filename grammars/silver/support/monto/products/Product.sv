grammar silver:support:monto:products;

import lib:json;

synthesized attribute productName :: String;
nonterminal ProductValue with json, productName;

synthesized attribute productValue :: ProductValue;
nonterminal Product with json, productValue;

abstract production product
top::Product ::= name::String value::ProductValue metas::[MetaItem] language::String path::String
{
  top.json = jsonObject(
    [ pair("contents", value.json)
    , pair("language", jsonString(language))
    , pair("name", jsonString("name"))
    , pair("meta", jsonArray(map((.json), metas)))
    , pair("path", jsonString(path))
    ]);
  top.productValue = value;
}

nonterminal ProductDescriptor with json;

abstract production productDescriptor
top::ProductDescriptor ::= name::String language::String
{
  top.json = jsonObject(
    [ pair("name", jsonString("name"))
    , pair("language", jsonString("language"))
    ]);
}

nonterminal ProductIdentifier with json;

abstract production productIdentifier
top::ProductIdentifier ::= name::String language::String path::String
{
  top.json = jsonObject(
    [ pair("name", jsonString("name"))
    , pair("language", jsonString("language"))
    , pair("path", jsonString("path"))
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
