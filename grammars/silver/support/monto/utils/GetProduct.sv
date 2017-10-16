grammar silver:support:monto:utils;

import silver:support:monto;
import silver:support:monto:products;

function getProduct
Maybe<Product> ::= ident::ProductIdentifier deps::[Product]
{
  return if null(deps) then
    nothing()
  else if identEq(ident, head(deps)) then
    just(head(deps))
  else
    getProduct(ident, tail(deps));
}
