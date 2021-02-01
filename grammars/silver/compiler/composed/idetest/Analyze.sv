-- This file defines the error demanding function that can be interfaced by IDE plugin written in Java.

--grammar silver:compiler:analysis:binding:driver;
grammar silver:compiler:composed:idetest;

import silver:compiler:driver;
import silver:util:cmdargs;

import silver:compiler:definition:core;
import silver:compiler:definition:env;

import silver:rewrite;

-- This function is mostly copied from function cmdLineRun in driver/BuildProcess.sv
function ideAnalyze
IOVal<[Message]> ::= args::[String]  svParser::SVParser ioin::IO
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
    buildRun(svParser, a, benv, buildGrammar, checkbuild.io);
  local unit :: Decorated Compilation = buildrun.iovalue;

  ---- DIFFERENCE: We do *not* run the actions in the functions. Only check for errors.

  local messages :: [Message] = flatMap(rewriteMessages, unit.allGrammars);

  return if !null(argErrors) then
    ioval(ioin, [err(system_location, "Parsing failed during build. If source code/resources are changed outside IDE, refresh and rebuild is needed.")])
  else if !null(envErrors) then
    ioval(benvResult.io, getSysMessages(envErrors))
  else if null(unit.grammarList) then
    ioval(buildrun.io, [err(system_location, 
            (if buildGrammar=="" 
             then "No grammar is specified for compilation. Check configuration for this project." 
             else ("The specified grammar \"" ++ buildGrammar ++ "\" could not be found. Check configuration for this project."))
            )])
  else ioval(buildrun.io, messages);
}

-- This function is mostly copied from function cmdLineRun in driver/BuildProcess.sv
function ideGenerate
IOVal<[Message]> ::= args::[String]  svParser::SVParser  ioin::IO
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
    buildRun(svParser, a, benv, buildGrammar, checkbuild.io);
  local unit :: Decorated Compilation = buildrun.iovalue;

  -- Run the resulting build actions
  local actions :: IOVal<Integer> = runAll(sortUnits(unit.postOps), buildrun.io);

  return ioval(actions.io, []); -- TODO: the original code did no error checking here, so I've preserved it. but wtf?
  -- it seems this is only called if the previous does NOT fail. So there should be no additional errors.
}

function getSysMessages
[Message] ::= es::[String]
{
  return if null(es)
         then []
         else [err(system_location, head(es))] ++ getSysMessages(tail(es));
}

function rewriteMessages
[Message] ::= spec::Decorated RootSpec
{
  return map(rewriteMessage(spec.grammarSource, _), 
    if !null(spec.parsingErrors)
    then flatMap(snd, spec.parsingErrors)
    else flatMap(snd, spec.grammarErrors));
}

-- Message for the IDE require full paths.
function rewriteMessage
Message ::= path::String m::Message
{
  return
    rewriteWith_(
      allTopDown(
        rule on Location of
        | loc(file, a, b, c, d, e, f) -> loc(path ++ "/" ++ file, a, b, c, d, e, f)
        end),
      m).fromJust;
}

