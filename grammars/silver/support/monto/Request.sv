grammar silver:support:monto;

import lib:json;
import silver:support:monto:products;

synthesized attribute requestIdentifier :: ProductIdentifier;
synthesized attribute requestProducts :: [Product];
nonterminal BrokerRequest with json, requestIdentifier, requestProducts;

abstract production brokerRequest
top::BrokerRequest ::= request::ProductIdentifier products::[Product]
{
  top.json = jsonObject(
    [ pair("products", jsonArray(map((.json), products)))
    , pair("request", request.json)
    ]);
  top.requestIdentifier = request;
  top.requestProducts = products;
}
