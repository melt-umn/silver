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
  return if rootspec.iovalue.isJust then
    ioval(rootspec.io, allErrors(rootspec.iovalue.fromJust))
  else
    ioval(print("compileGrammar returned nothing(); the grammar probably doesn't exist.", rootspec.io), jsonArray([]));
}

function allErrors
Json ::= rs::RootSpec
{
  local errors :: [Json] = if !null(rs.parsingErrors) then
    map(silverMessageToError(_, "parse"), rs.parsingErrors)
  else
    map(silverMessageToError(_, "semantic"), rs.errors);
  return jsonArray(errors);
}

function silverMessageToError
Json ::= msg::Message kind::String
{
  return errorProduct(
    msg.loc.index,
    msg.loc.endIndex - msg.loc.index,
    severityToErrorLevel(msg.severity),
    kind,
    msg.msg);
}
