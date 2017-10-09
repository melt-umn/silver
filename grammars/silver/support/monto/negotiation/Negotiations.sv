grammar silver:support:monto:negotiation;

import lib:json;
import silver:support:monto:products;

synthesized attribute montoVersion :: ProtocolVersion;

nonterminal ServiceBrokerNegotiation with montoVersion, json;

abstract production serviceBrokerNegotiation
top::ServiceBrokerNegotiation ::= monto::ProtocolVersion broker::SoftwareVersion extensions::[String]
{
  top.montoVersion = monto;
  top.json = jsonObject(
    [ pair("monto", top.montoVersion.json)
    , pair("broker", broker.json)
    , pair("extensions", jsonArray(map(jsonString, extensions)))
    ]);
}

nonterminal ServiceNegotiation with montoVersion, json;

abstract production serviceNegotiation
top::ServiceNegotiation ::= monto::ProtocolVersion service::SoftwareVersion extensions::[String] products::[ProductDescriptor]
{
  top.montoVersion = monto;
  top.json = jsonObject(
    [ pair("monto", top.montoVersion.json)
    , pair("service", service.json)
    , pair("extensions", jsonArray(map(jsonString, extensions)))
    , pair("products", jsonArray(map((.json), products)))
    ]);
}
