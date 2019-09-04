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
  return error("Foreign function");
} foreign {
  "java": return "common.Util.io(%ioIn%, monto.Server.run(%config%, %callback%))";
}
