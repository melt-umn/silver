grammar lib:monto;

{- The monto library is used for creating Silver programs that act as Monto as 'servers'. A Silver program will take a message from monto via the nextMessage function. The message typically contains DSL code to be evaluated or compiled. After the message has been evaluated, sendProduct can be used to send a Monto product back to the Monto broker. Note: nextMessage will hang until a message from the Monto broker has been received. -}

function nextMessage
IOVal<[String]> ::= inUrl::String outUrl::String ioIn::IO
{
 return error("Foreign function");
} foreign {
 "java": return "new core.Pioval(%ioIn%, monto.MontoConnection.nextMessage(%inUrl%.toString(), %outUrl%.toString()))";
}

function sendProduct
IO ::= source::String product::String language::String contents::String ioIn::IO
{
 return error("Foreign function");
} foreign {
 "java": return "common.Util.io(%ioIn%, monto.MontoConnection.sendProduct(%source%.toString(), %product%.toString(), %language%.toString(), %contents%.toString()))";
}

function disconnect
IO ::= ioIn::IO
{
 return error("Foreign function");
} foreign {
 "java": return "common.Util.io(%ioIn%, monto.MontoConnection.disconnect())";
}
