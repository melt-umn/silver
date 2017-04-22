grammar silver:composed:monto;

import silver:composed:Default;

import lib:json;
import lib:monto; 
import lib:monto:helpers;

function callbacks
[Pair<String (Json ::= String String)>] ::= silverHome::String projectPath::String
  [ pair("errors", errorCallback(silverHome, projectPath))
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
  local paths::Pair<String String> =
    if length(args) != 2 then
      error("Usage: java -jar silver.composed.monto.jar <SILVER-HOME> <PROJECT-PATH>")
    else
      pair(head(args), head(tail(args)));
  local cfg :: Config = config(
    "127.0.0.1",
    "edu.umn.cs.melt.silver.monto",
    "Silver",
    "The integration between Silver and Monto.",
    [ sourceDependency("silver")
    ],
    map(callbackPairToName, callbacks(paths.fst, paths.snd)));
  return ioval(runMonto(cfg, callback(paths.fst, paths.snd, _), ioIn), 0);
}

function callback
[MontoMessage] ::= silverHome::String projectPath::String req::Request
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
    callbacks(silverHome, projectPath));
}
