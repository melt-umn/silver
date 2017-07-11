-- This file defines the error demanding function that can be interfaced by IDE plugin written in Java.

--grammar silver:analysis:binding:driver;
grammar silver:composed:idetest;

import silver:driver;
import silver:util:cmdargs;

import silver:definition:core;
import silver:definition:env;

import ide;

import silver:langutil only message;

function ideAnalyze
IOVal<[IdeMessage]> ::= args::[String]  svParser::SVParser  sviParser::SVIParser ioin::IO
{
  local unit :: IOErrorable<Decorated Compilation> =
    cmdLineRunInitial(args, svParser, sviParser, ioin);

  return case unit.iovalue of
  | left(re) -> ioval(unit.io, [makeSysIdeMessage(ideMsgLvError, re.message)])
  | right(comp) -> ioval(unit.io, getAllBindingErrors(comp.allGrammars))
  end;
}

function ideGenerate
IOVal<[IdeMessage]> ::= args::[String]  svParser::SVParser  sviParser::SVIParser  ioin::IO
{
  local unit :: IOErrorable<Decorated Compilation> =
    cmdLineRunInitial(args, svParser, sviParser, ioin);

  return case unit.iovalue of
  | left(re) -> ioval(unit.io, [makeSysIdeMessage(ideMsgLvError, re.message)])
  -- this is only called if the previous does NOT fail. So there should be no additional errors.
  | right(comp) -> ioval(runAll(sortUnits(comp.postOps), unit.io).io, [])
  end;
}

function getAllBindingErrors
[IdeMessage] ::= specs::[Decorated RootSpec]
{
  local spec :: Decorated RootSpec = head(specs);
  local grmPath :: String = spec.grammarSource;

  return if null(specs) then []
  else getIdeMessages(grmPath, spec) ++ getAllBindingErrors(tail(specs));
}

function getIdeMessages
[IdeMessage] ::= path::String spec::Decorated RootSpec
{
  return map(rewriteMessage(path, _),
    if !null(spec.parsingErrors)
    then spec.parsingErrors
    else spec.errors);
}

function rewriteMessage
IdeMessage ::= path::String m::Message
{
  return makeIdeMessage(path, m.loc, m.severity, m.msg);
}

