grammar silver:support:monto;

import silver:json;
import silver:support:monto:negotiation;
import silver:support:monto:products;

-- These are "stringly typed" to minimize the effort to FFI to them; you should
-- always use the strongly typed service production.
synthesized attribute doNegotiation :: (Pair<String Boolean> ::= ServiceBrokerNegotiation);
synthesized attribute onRequest :: (Pair<String Integer> ::= ProductIdentifier [Product]);

nonterminal Service with doNegotiation, onRequest;

abstract production service
top::Service ::= negotiation::ServiceNegotiation providers::[ServiceProvider]
{
  top.doNegotiation = \sbn::ServiceBrokerNegotiation ->
    pair(negotiation.json.jsonString, negotiationsCompatible(negotiation, sbn));
  top.onRequest = \pi::ProductIdentifier deps::[Product] ->
    case find(\p::ServiceProvider -> productDescriptorEq(p.descriptor, asDescriptor(pi)), providers) of
    | just(p) ->
      let tmp::Pair<Json Integer> = case p.processService(pi.productPath, deps) of
      | pair(left(errs), ntcs) -> pair(jsonObject(
          [ pair("errors", jsonArray(map((.json), errs)))
          , pair("notices", jsonArray(map((.json), ntcs)))
          ]), 500)
      | pair(right(prd), ntcs) -> pair(jsonObject(
          [ pair("product", prd.json)
          , pair("notices", jsonArray(map((.json), ntcs)))
          ]), 200)
      end in pair(tmp.fst.jsonString, tmp.snd) end
    | nothing() -> pair(pi.json.jsonString, 400)
    end;
}

function asDescriptor
ProductDescriptor ::= pi::ProductIdentifier
{
  return productDescriptor(pi.productName, pi.productLang);
}

-- TODO: Move this to core.
function find
Maybe<a> ::= f::(Boolean ::= a) l::[a]
{
  return if null(l) then
    nothing()
  else if f(head(l)) then
    just(head(l))
  else
    find(f, tail(l));
}
