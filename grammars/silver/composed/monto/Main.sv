grammar silver:composed:monto;

import silver:composed:Default;

import lib:json;
import lib:monto; 
import lib:monto:helpers;

function callbacks
[Pair<String (IOVal<Json> ::= String String IO)>] ::= silverHome::String grammarName::String
{
  return [ pair("errors", errorCallback(silverHome, grammarName, _, _, _))
         , pair("highlighting", ioWrap2(listBasedHighlightingCallback(svParse, styles)))
         ];
}

function callbackPairToName
ProductDescription ::= p::Pair<String a>
{
  return productDescription("silver", p.fst);
}

function main 
IOVal<Integer> ::= args::[String] ioIn::IO
{
  local paths::Pair<String String> = pair(mustBeAbsPath(head(args)), mustBeAbsPath(head(tail(args))));
  local cfg :: Config = config(
    "127.0.0.1",
    "edu.umn.cs.melt.silver.monto",
    "Silver",
    "The integration between Silver and Monto.",
    [ sourceDependency("silver")
    ],
    map(callbackPairToName, callbacks(paths.fst, paths.snd)));
  return if length(args) != 2 then
    error("Usage: java -jar silver.composed.monto.jar <SILVER-HOME> <GRAMMAR-NAME>")
  else
    ioval(runMontoIO(cfg, callback(paths.fst, paths.snd, _, _), ioIn), 0);
}

function callback
IOVal<[MontoMessage]> ::= silverHome::String grammarName::String req::Request ioin::IO
{
  return ioMap(callbackHelper(req, head(req.requirements), silverHome, grammarName, _, _),
    callbacks(silverHome, grammarName),
    ioin);
}

function callbackHelper
IOVal<MontoMessage> ::= req::Request srcRqmt::Requirement silverHome::String grammarName::String p::Pair<String (IOVal<Json> ::= String String IO)> ioin::IO
{
  local r :: IOVal<Json> = p.snd(srcRqmt.contents, srcRqmt.source.physicalName, ioin);
  return ioval(r.io, productMessage(product(
    srcRqmt.id,
    req.source,
    req.serviceId,
    p.fst,
    "dcv2",
    r.iovalue)));
}
