grammar silver:support:monto;

import silver:json;
import silver:support:monto:products;

nonterminal ServiceError with json;

abstract production errorUnmetDependency
top::ServiceError ::= identifier::ProductIdentifier
{
  top.json = jsonObject(
    [ pair("type", jsonString("unmet_dependency"))
    , pair("value", identifier.json)
    ]);
}

abstract production errorOther
top::ServiceError ::= msg::String
{
  top.json = jsonObject(
    [ pair("type", jsonString("other"))
    , pair("value", jsonString(msg))
    ]);
}
