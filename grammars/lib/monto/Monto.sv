grammar lib:monto;

{- Defines production for passing Monto messages and products around a program -}

type MontoMessage foreign;
type MontoProduct foreign;

{- Creating and operating on monto messages -} 

function createMessage
MontoMessage ::= source::String language::String contents::String selections::String
{
  return error("Foreign function");
} foreign {
  "java": return "new monto.MontoMessage(%source%.toString(), %language%.toString(), %contents%.toString(), %selections%.toString())";
}

function getSource
String ::= m::MontoMessage
{
  return error("Foreign function");
} foreign {
  "java": return "new common.StringCatter(((monto.MontoMessage)%m%).getSource())";
}

function getLanguage
String ::= m::MontoMessage
{
  return error("Foreign function");
} foreign {
  "java": return "new common.StringCatter(((monto.MontoMessage)%m%).getLanguage())";
}

function getContents
String ::= m::MontoMessage
{
  return error("Foreign function");
} foreign {
  "java": return "new common.StringCatter(((monto.MontoMessage)%m%).getContents())";
}

function getSelections
String ::= m::MontoMessage
{
  return error("Foreign function");
} foreign {
  "java": return "new common.StringCatter(((monto.MontoMessage)%m%).getSelections())";
}

{- Creating products -}

function createProduct
MontoProduct ::= source::String product::String language::String contents::String
{
  return error("Foreign function");
} foreign {
  "java": return "new monto.MontoProduct(%source%.toString(), %product%.toString(), %language%.toString(), %contents%.toString())";
}

{- Defines functions for interfacing with MontoServer objects -}

type MontoServer foreign;

function create
MontoServer ::= eval::([MontoProduct] ::= MontoMessage) inUrl::String outUrl::String
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

