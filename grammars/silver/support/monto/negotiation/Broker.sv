grammar silver:support:monto:negotiation;

import lib:json;
import silver:support:monto:products;

nonterminal ServiceBrokerNegotiation with json;

abstract production serviceBrokerNegotiation
top::ServiceNegotiation ::= monto::ProtocolVersion broker::SoftwareVersion extensions::[String]
{
  top.json = jsonObject(
    [ pair("monto", monto.json)
    , pair("broker", broker.json)
    , pair("extensions", jsonArray(map(jsonString, extensions)))
    ]);
}
