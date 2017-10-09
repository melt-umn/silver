grammar silver:support:monto:products;

import lib:json;

abstract production sourceProduct
top::ProductValue ::= source::String
{
  forwards to productValue("source", jsonString(source));
}
