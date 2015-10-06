grammar lib:monto;

{- Defines functions for interfacing with Monto -}

type MontoServer foreign;

function create
MontoServer ::= eval::([String] ::= [String]) inUrl::String outUrl::String
{
  return error("Foreign function");
} foreign {
  "java": return "new monto.MontoConnection(%eval%, %inUrl%.toString(), %outUrl%.toString())";
}

function run
IOVal<Integer> ::= server::MontoServer ioIn::IO
{
  return error("Foreign function");
} foreign {
  "java": return "new core.Pioval(%ioIn%, ((monto.MontoConnection)%server%).run())";
}

