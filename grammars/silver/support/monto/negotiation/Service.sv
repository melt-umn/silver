grammar silver:support:monto:negotiation;

import lib:json;
import silver:support:monto:products;

nonterminal ServiceNegotiation with json;

abstract production serviceNegotiation
top::ServiceNegotiation ::= monto::ProtocolVersion service::SoftwareVersion extensions::[String] products::[ProductDescriptor]
{
  top.json = jsonObject(
    [ pair("monto", monto.json)
    , pair("service", service.json)
    , pair("extensions", jsonArray(map(jsonString, extensions)))
    , pair("products", jsonArray(map((.json), products)))
    ]);
}
