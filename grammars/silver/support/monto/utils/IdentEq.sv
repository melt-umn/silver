grammar silver:support:monto:utils;

import silver:support:monto:products;

function identEq
Boolean ::= ident::ProductIdentifier prod::Product
{
  return prod.productName == ident.productName && prod.productLang == ident.productLang && prod.productPath == ident.productPath;
}
