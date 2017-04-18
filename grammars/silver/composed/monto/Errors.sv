grammar silver:composed:monto;

import silver:composed:Default;
import silver:definition:core;

import lib:json;
import lib:monto:helpers;

function errorCallback
Json ::= src::String fileName::String
{
  local result :: ParseResult<Root> = svParse(src, fileName);
  return jsonArray(if !result.parseSuccess
    then case result.parseError of
         | syntaxError(s, l, _, _) -> [errorProduct(
             l.index,
             l.endIndex - l.index,
             errorLevelError(),
             "parser",
             s)]
         | _ -> error("TODO")
         end
    else map(messageToErrorHack, result.parseTree.errors));
}
