grammar silver:composed:monto;

import silver:composed:Default;
import silver:definition:core;
import silver:driver;

import lib:json;
import lib:monto:helpers;

function errorCallback
IOVal<Json> ::= silverHome::String buildGrammar::String src::String fileName::String ioin::IO
{
  local benv :: BuildEnv = buildEnv(silverHome, stringConcat(silverHome, "generated/"), [buildGrammar]);
  local rootspec :: IOVal<Maybe<RootSpec>> = compileGrammar(svParse, sviParse, benv, buildGrammar, false, ioin);
  local parseErrors :: Json = jsonArray(map(silverMessageToError, rootspec.iovalue.fromJust.parsingErrors));
  return if rootspec.iovalue.isJust then
    ioval(rootspec.io, parseErrors)
  else
    ioval(print("TODO compileGrammar returned nothing()", rootspec.io), jsonArray([]));
}

function silverMessageToError
Json ::= msg::Message
{
  return errorProduct(
    msg.loc.index,
    msg.loc.endIndex - msg.loc.index,
    severityToErrorLevel(msg.severity),
    "compilation", -- TODO
    msg.msg);
}
