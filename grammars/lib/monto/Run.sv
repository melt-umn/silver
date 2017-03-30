grammar lib:monto;

import lib:json;

function runMonto
IO ::= config::Config callback::([MontoMessage] ::= Request) ioIn::IO
{
  return runMontoIO(config, \req::Request cbio::IO -> ioval(cbio, callback(req)), ioIn);
}

function runMontoIO
IO ::= config::Config callback::(IOVal<[MontoMessage]> ::= Request IO) ioIn::IO
{
  return runMontoRaw(config, \req::Request cbio::IO -> runMontoIOHelper(callback, req, cbio), ioIn);
}

{--
 - TODO Is there some way to make this nicer? Are let expressions stable?
 -}
function runMontoIOHelper
IOVal<[Json]> ::= callback::(IOVal<[MontoMessage]> ::= Request IO) req::Request ioIn::IO
{
  local ioProds :: IOVal<[MontoMessage]> = callback(req, ioIn);
  return ioval(ioProds.io, map((.json), ioProds.iovalue));
}

function runMontoRaw
IO ::= config::Config callback::(IOVal<[Json]> ::= Request IO) ioIn::IO
{
  return error("Foreign function");
} foreign {
  "java": return "common.Util.io(%ioIn%, null /* To break bootstrap dependency loop */)";
  -- "java": return "common.Util.io(%ioIn%, monto.Server.run(%config%, %callback%))";
}
