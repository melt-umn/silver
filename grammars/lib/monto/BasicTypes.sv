import lib:json;

nonterminal Config with brokerAddr, dependencies, description, label, products, serviceId;
nonterminal Dependency with language, product, serviceId;
nonterminal MontoMessage with json, messageContents, tag;
nonterminal Product with id, json, language, product, productContents, serviceId, source;
nonterminal ProductDescription with language, product;
nonterminal Request with requirements, serviceId, source;
nonterminal Requirement with contents, language, source, id;
nonterminal Source with json, logicalName, physicalName;

synthesized attribute brokerAddr :: String;
synthesized attribute contents :: String;
synthesized attribute dependencies :: [Dependency];
synthesized attribute description :: String;
synthesized attribute id :: Integer;
synthesized attribute json :: Json;
synthesized attribute label :: String;
synthesized attribute language :: String;
synthesized attribute logicalName :: Maybe<String>;
synthesized attribute messageContents :: Json;
synthesized attribute physicalName :: String;
synthesized attribute product :: String;
synthesized attribute productContents :: Json;
synthesized attribute products :: [ProductDescription];
synthesized attribute requirements :: [Requirement];
synthesized attribute serviceId :: String;
synthesized attribute source :: Source;
synthesized attribute tag :: String;

abstract production config
top::Config ::= brokerAddr::String serviceId::String label::String description::String dependencies::[Dependency] products::[ProductDescription]
{
  top.brokerAddr = brokerAddr;
  top.dependencies = dependencies;
  top.description = description;
  top.label = label;
  top.products = products;
  top.serviceId = serviceId;
}

abstract production sourceDependency
top::Dependency ::= language::String
{
  top.language = language;
}

abstract production productDependency
top::Dependency ::= serviceId::String product::String language::String
{
  top.language = language;
  top.product = product;
  top.serviceId = serviceId;
}

abstract production product
top::Product ::= id::Integer source::Source serviceId::String product::String language::String productContents::Json
{
  top.id = id;
  top.json = jsonObject(
    [ pair("id", jsonInteger(top.id))
    , pair("language", jsonString(top.language))
    , pair("product", jsonString(top.product))
    , pair("productContents", top.productContents)
    , pair("serviceId", jsonString(top.serviceId))
    , pair("source", top.source.json)
    ]);
  top.language = language;
  top.product = product;
  top.productContents = productContents;
  top.serviceId = serviceId;
  top.source = source;
}

abstract production productDescription
top::ProductDescription ::= language::String product::String
{
  top.language = language;
  top.product = product;
}

abstract production productMessage
top::MontoMessage ::= contents::Json
{
  top.json = jsonObject(
    [ pair("contents", top.messageContents)
    , pair("tag", jsonString(top.tag))
    ]);
  top.messageContents = contents;
  top.tag = "product";
}

abstract production request
top::Request ::= serviceId::String source::Source requirements::[Requirement]
{
  top.requirements = requirements;
  top.serviceId = serviceId;
  top.source = source;
}

abstract production requirement
top::Requirement ::= id::Integer contents::String language::String source::Source
{
  top.contents = contents;
  top.id = id;
  top.language = language;
  top.source = source;
}

abstract production source
top::Source ::= physicalName::String logicalName::Maybe<String>
{
  top.json = jsonObject(
    [ pair("physical_name", jsonString(top.physicalName))
    , pair("logical_name", case top.logicalName of
                           | just(name) -> jsonString(name)
                           | nothing() -> jsonNull()
                           end)
    ]);
  top.logicalName = logicalName;
  top.physicalName = physicalName;
}
