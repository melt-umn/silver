grammar silver:support:monto:products;

import silver:json;
import silver:langutil;

abstract production errorsProduct
top::ProductValue ::= errs::[Error]
{
  forwards to productValue("errors", jsonArray(map((.json), errs)));
}

nonterminal Error with json;

abstract production byteRangeError
top::Error ::= startByte::Integer endByte::Integer message::String severity::ErrorSeverity
{
  top.json = jsonObject(obj);
  local obj :: [Pair<String Json>] =
    [ pair("start_byte", jsonInteger(startByte))
    , pair("end_byte", jsonInteger(endByte))
    , pair("message", jsonString(message))
    , pair("severity", severity.json)
    ];
}

abstract production messageError
top::Error ::= msg::Message
{
  top.json = jsonObject(obj);
  local severity :: ErrorSeverity = case msg.severity of
  | 0 -> severityInfo()
  | 1 -> severityWarning()
  | _ -> severityError()
  end;
  local obj :: [Pair<String Json>] =
    [ pair("start_byte", jsonInteger(msg.where.index))
    , pair("end_byte", jsonInteger(msg.where.endIndex))
    , pair("message", jsonString(msg.output))
    , pair("severity", severity.json)
    ];
}

nonterminal ErrorSeverity with json;

abstract production severityError
top::ErrorSeverity ::=
{
  top.json = jsonString("error");
}

abstract production severityWarning
top::ErrorSeverity ::=
{
  top.json = jsonString("warning");
}

abstract production severityInfo
top::ErrorSeverity ::=
{
  top.json = jsonString("info");
}
