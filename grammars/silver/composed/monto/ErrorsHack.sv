grammar silver:composed:monto;

import silver:definition:core;
import lib:json;
import lib:monto:helpers;

function messageToErrorHack
Json ::= msg::Message
{
  return errorProduct(
    msg.loc.index,
    msg.loc.endIndex - msg.loc.index,
    severityToErrorLevel(msg.severity),
    "compilation", -- TODO
    msg.msg);
}
