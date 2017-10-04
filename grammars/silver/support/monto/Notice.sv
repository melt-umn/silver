grammar silver:support:monto;

import lib:json;
import silver:support:monto:products;

nonterminal ServiceNotice with json;

abstract production noticeUnusedDependency
top::ServiceNotice ::= identifier::ProductIdentifier
{
  top.json = jsonObject(
    [ pair("type", jsonString("unused_dependency"))
    , pair("value", identifier.json)
    ]);
}
