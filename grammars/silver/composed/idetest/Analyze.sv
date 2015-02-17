-- This file defines the error demanding function that can be interfaced by IDE plugin written in Java.

--grammar silver:analysis:binding:driver;
grammar silver:composed:idetest;

import silver:driver;
import silver:util:cmdargs;

import silver:definition:core;
import silver:definition:env;

import ide;

-- This function is mostly copied from function cmdLineRun in driver/BuildProcess.sv
function ideAnalyze
IOVal<[IdeMessage]> ::= args::[String]  svParser::SVParser  sviParser::SVIParser projectPath::String ioin::IO
{
  -- Figure out arguments
  local argResult :: Either<String  Decorated CmdArgs> = parseArgs(args);
  local a :: Decorated CmdArgs = case argResult of right(t) -> t end;
  local argErrors :: [String] = case argResult of | left(s) -> [s] | _ -> [] end;

  -- Figure out build env from environment and args
  local benvResult :: IOVal<Either<BuildEnv  [String]>> = determineBuildEnv(a, ioin);
  local benv :: BuildEnv = case benvResult.iovalue of left(t) -> t end;
  local envErrors :: [String] = case benvResult.iovalue of | right(s) -> s | _ -> [] end;
  
  -- Let's start preparing to build
  local buildGrammar :: String = head(a.buildGrammar);
  local checkbuild :: IOVal<[String]> =
    checkPreBuild(a, benv, buildGrammar, benvResult.io);

  -- Build!
  local buildrun :: IOVal<Decorated Compilation> =
    buildRun(svParser, sviParser, a, benv, buildGrammar, checkbuild.io);
  local unit :: Decorated Compilation = buildrun.iovalue;

  ---- DIFFERENCE: We do *not* run the actions in the functions. Only check for errors.

  local messages :: [IdeMessage] = getAllBindingErrors(unit.allGrammars, projectPath);

  return if !null(argErrors) then
    ioval(ioin, [makeSysIdeMessage(ideMsgLvError, "Parsing failed during build. If source code/resources are changed outside IDE, refresh and rebuild is needed.")])
  else if !null(envErrors) then
    ioval(benvResult.io, getSysMessages(envErrors))
  else if null(unit.grammarList) then
    ioval(buildrun.io, [makeSysIdeMessage(ideMsgLvError, 
            (if buildGrammar=="" 
             then "No grammar is specified for compilation. Check configuration for this project." 
             else ("The specified grammar \"" ++ buildGrammar ++ "\" could not be found. Check configuration for this project."))
            )])
  else ioval(buildrun.io, messages);
}

-- This function is mostly copied from function cmdLineRun in driver/BuildProcess.sv
function ideGenerate
IOVal<[IdeMessage]> ::= args::[String]  svParser::SVParser  sviParser::SVIParser  ioin::IO
{
  -- Figure out arguments
  local argResult :: Either<String  Decorated CmdArgs> = parseArgs(args);
  local a :: Decorated CmdArgs = case argResult of right(t) -> t end;
  local argErrors :: [String] = case argResult of | left(s) -> [s] | _ -> [] end;

  -- Figure out build env from environment and args
  local benvResult :: IOVal<Either<BuildEnv  [String]>> = determineBuildEnv(a, ioin);
  local benv :: BuildEnv = case benvResult.iovalue of left(t) -> t end;
  local envErrors :: [String] = case benvResult.iovalue of | right(s) -> s | _ -> [] end;
  
  -- Let's start preparing to build
  local buildGrammar :: String = head(a.buildGrammar);
  local checkbuild :: IOVal<[String]> =
    checkPreBuild(a, benv, buildGrammar, benvResult.io);

  -- Build!
  local buildrun :: IOVal<Decorated Compilation> =
    buildRun(svParser, sviParser, a, benv, buildGrammar, checkbuild.io);
  local unit :: Decorated Compilation = buildrun.iovalue;

  -- Run the resulting build actions
  local actions :: IOVal<Integer> = runAll(sortUnits(unit.postOps), buildrun.io);

  return ioval(actions.io, []); -- TODO: the original code did no error checking here, so I've preserved it. but wtf?
  -- it seems this is only called if the previous does NOT fail. So there should be no additional errors.
}

function getSysMessages
[IdeMessage] ::= es::[String]
{
  return if null(es)
         then []
         else [makeSysIdeMessage(ideMsgLvError, head(es))] ++ getSysMessages(tail(es));
}

function getAllBindingErrors
[IdeMessage] ::= specs::[Decorated RootSpec] projectPath::String
{
  local spec :: Decorated RootSpec = head(specs);
  -- remainder of the path after 'projectPath' prefix.
  local grmPath :: String = spec.grammarSource;

  return if null(specs) then []
  else getIdeMessages(grmPath, spec) ++ getAllBindingErrors(tail(specs), projectPath);
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

