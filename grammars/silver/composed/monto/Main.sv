grammar silver:composed:monto;

import silver:composed:Default;

import lib:json;
import lib:monto; 
import lib:monto:helpers;

global callbacks :: [Pair<String (Json ::= String String)>] =
  [ pair("errors", errorCallback)
  , pair("highlighting", listBasedHighlightingCallback(svParse, styles))
  ];

function callbackPairToName
ProductDescription ::= p::Pair<String (Json ::= String String)>
{
  return productDescription("silver", p.fst);
}

function main 
IOVal<Integer> ::= args::[String] ioIn::IO
{
  local cfg :: Config = config(
    "127.0.0.1",
    "edu.umn.cs.melt.silver.monto",
    "Silver",
    "The integration between Silver and Monto.",
    [ sourceDependency("silver")
    ],
    map(callbackPairToName, callbacks));
  return ioval(runMonto(cfg, callback, ioIn), 0);
}

function callback
[MontoMessage] ::= req::Request
{
  local srcRqmt :: Requirement = head(req.requirements);
  return map(\p::Pair<String (Json ::= String String)> ->
    productMessage(product(
      srcRqmt.id,
      req.source,
      req.serviceId,
      p.fst,
      "dcv2",
      p.snd(srcRqmt.contents, srcRqmt.source.physicalName))),
    callbacks);
}
