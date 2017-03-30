grammar silver:composed:monto;

import silver:composed:Default;

import lib:json;
import lib:monto;

function main 
IOVal<Integer> ::= args::[String] ioin::IO
{
  local cfg :: Config = config(
    "127.0.0.1",
    "edu.umn.cs.melt.silver.monto",
    "Silver",
    "The integration between Silver and Monto.",
    [ sourceDependency("java")
    ],
    [ productDescription("java", "errors")
    ]);
  local nextIo :: IO = run(cfg, callback, ioin);
  return ioval(nextIo, 0);
}

function callback
Product ::= req::Request
{
  return product(
    0, -- TODO
    req.source,
    req.serviceId,
    "errors",
    "java",
    jsonString("TODO")); -- TODO
}
