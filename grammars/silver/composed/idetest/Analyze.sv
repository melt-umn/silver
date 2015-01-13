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

  local messages :: [IdeMessage] = getAllBindingErrors(unit.grammarList, projectPath);

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
  local grmPath::String = translateToPath(spec.grammarSource, projectPath);--spec.declaredName

  return if null(specs)
            then []
            else if startsWith(projectPath, spec.grammarSource) -- check if this spec is physically located under project
                 then getIdeMessages(grmPath, spec) ++ getAllBindingErrors(tail(specs), projectPath)
                 -- if not, generate message for linked resource
                 else getIdeMessagesLinked(grmPath, getGrammarRoot(spec.grammarSource, grmPath), spec) ++ 
                      getAllBindingErrors(tail(specs), projectPath);
}

function getIdeMessages
[IdeMessage] ::= path::String spec::Decorated RootSpec
{
  return if !null(spec.parsingErrors)
         then rewriteMessages(path, spec.parsingErrors) -- parsing errors (if we have any parsing error, don't bother to further inspect binding errors)
         else rewriteMessages(path, spec.errors);       -- binding errors
}

function rewriteMessages
[IdeMessage] ::= path::String es::[Message]
{
  return if null(es)
         then []
         else let 
                  head :: Message = head(es)
              in 
                  [makeIdeMessage(path, head.loc, head.severity, head.msg)] ++ rewriteMessages(path, tail(es))
              end;
}

function getIdeMessagesLinked
[IdeMessage] ::= path::String grmRoot::String spec::Decorated RootSpec
{
  return if null(spec.parsingErrors)
         then rewriteMessagesLinked(path, grmRoot, spec.errors)
         else rewriteMessagesLinked(path, grmRoot, spec.parsingErrors);
}

function rewriteMessagesLinked
[IdeMessage] ::= path::String grmRoot::String es::[Message]
{
  return if null(es)
         then []
         else let 
                  head :: Message = unsafeTrace(head(es), print("got linked: " ++ head(es).pp ++ "\n", unsafeIO()))
              in 
                  [makeLinkedResourceMessage(path, grmRoot, head.loc, head.severity, head.msg)] ++ rewriteMessagesLinked(path, grmRoot, tail(es))
              end;
}

{--
 - Turns a absolute path into a relative one, assuming it's below 'projectPath' as root.
 -}
function translateToPath
String ::= grammarSource::String projectPath::String 
{
  local found :: Boolean = startsWith(projectPath, grammarSource);
  return if found
         then substring(length(projectPath), length(grammarSource), grammarSource)
         else "";-- If not found, the generated message will contain invalid resource path and won't be marked in IDE.
}

-- fullPath: /home/melt/test/a/b/c
-- grmPath: a/b/c
-- returns: /home/melt/test/
function getGrammarRoot
String ::= fullPath::String grmPath::String
{
  return substring(0, lastIndexOf(grmPath ++ "/", fullPath), fullPath);
}

