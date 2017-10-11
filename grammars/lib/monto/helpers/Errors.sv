grammar lib:monto:helpers;

import silver:json;
import silver:langutil;

nonterminal ErrorLevel with errorLevel;

synthesized attribute errorLevel :: String;

function messageToError
Json ::= msg::Message
{
  return errorProduct(
    msg.where.index,
    msg.where.endIndex - msg.where.index,
    severityToErrorLevel(msg.severity),
    "compilation", -- TODO
    msg.message);
}

abstract production errorProduct
top::Json ::= errorOffset::Integer errorLength::Integer level::ErrorLevel category::String description::String
{
  forwards to jsonObject(
    [ pair("offset", jsonInteger(errorOffset))
    , pair("length", jsonInteger(errorLength))
    , pair("level", jsonString(level.errorLevel))
    , pair("category", jsonString(category))
    , pair("description", jsonString(description))
    ]);
}

function severityToErrorLevel
ErrorLevel ::= severity::Integer
{
  return if severity >= 2
         then errorLevelError()
         else if severity >= 1
         then errorLevelWarning()
         else errorLevelInfo();
}

abstract production errorLevelInfo
top::ErrorLevel ::=
{ top.errorLevel = "info"; }

abstract production errorLevelWarning
top::ErrorLevel ::=
{ top.errorLevel = "warning"; }

abstract production errorLevelError
top::ErrorLevel ::=
{ top.errorLevel = "error"; }
