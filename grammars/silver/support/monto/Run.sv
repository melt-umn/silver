grammar silver:support:monto;

function runService
IO ::= service::Service port::Integer ioIn::IO
{
  return error("Foreign function");
} foreign {
  "java": return "common.Util.io(%ioIn%, monto.Server.run(%service%, %port%))";
}
