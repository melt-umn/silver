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
  local argResult :: Either<String  Decorated CmdArgs> = parseArgs(args);
  local a :: Decorated CmdArgs = case argResult of right(t) -> t end;

  -- Let's locally set up and verify the environment
  local envSH :: IOVal<String> = envVar("SILVER_HOME", ioin);
  local envGP :: IOVal<String> = envVar("GRAMMAR_PATH", envSH.io);
  local envSG :: IOVal<String> = envVar("SILVER_GEN", envGP.io);
  
  local silverHome :: String =
    endWithSlash(head(a.silverHomeOption ++ [envSH.iovalue]));
  local silverGen :: String =
    endWithSlash(head(a.genLocation ++ (if envSG.iovalue == "" then [] else [envSG.iovalue]) ++ [silverHome ++ "generated/"]));
  local grammarPath :: [String] =
    map(endWithSlash, a.searchPath ++ [silverHome ++ "grammars/"] ++ explode(":", envGP.iovalue) ++ ["."]);
  local buildGrammar :: String = head(a.buildGrammar);

  local check :: IOVal<[String]> =
    checkEnvironment(a, silverHome, silverGen, grammarPath, buildGrammar, envSG.io);
  
  -- Compile grammars. There's some tricky circular program data flow here:
  local rootStream :: IOVal<[Maybe<RootSpec>]> =
    compileGrammars(svParser, sviParser, grammarPath, silverGen, buildGrammar :: grammarStream, true, check.io);
  
  local unit :: Compilation =
    compilation(
      foldr(consGrammars, nilGrammars(), catMaybes(rootStream.iovalue)),
      foldr(consGrammars, nilGrammars(), catMaybes(reRootStream.iovalue)),
      buildGrammar, silverHome, silverGen);
  unit.config = a;
  
  -- Note that this is used above. This outputs deps, and rootStream informs it.
  local grammarStream :: [String] =
    eatGrammars(1, [buildGrammar], rootStream.iovalue, unit.grammarList);
  
  local reRootStream :: IOVal<[Maybe<RootSpec>]> =
    compileGrammars(svParser, sviParser, grammarPath, silverGen, unit.recheckGrammars, true, rootStream.io);

  local messages :: [IdeMessage] = getAllBindingErrors(unit.grammarList, projectPath);

  local argErrors :: [String] =
    case argResult of
    | left(s) -> [s]
    | _ -> []
    end;

  return if !null(argErrors) then ioval(ioin, [makeSysIdeMessage(ideMsgLvError, "Parsing failed during build. If source code/resources are changed outside IDE, refresh and rebuild is needed.")])
    else if !null(check.iovalue) then ioval(check.io, getSysMessages(check.iovalue))
    else if !head(rootStream.iovalue).isJust then ioval(rootStream.io, [makeSysIdeMessage(ideMsgLvError, 
            (if buildGrammar=="" 
             then "No grammar is specified for compilation. Check configuration for this project." 
             else ("The specified grammar \"" ++ buildGrammar ++ "\" could not be found. Check configuration for this project."))
            )])
    else ioval(rootStream.io, messages);
}

-- This function is mostly copied from function cmdLineRun in driver/BuildProcess.sv
function ideGenerate
IOVal<[IdeMessage]> ::= args::[String]  svParser::SVParser  sviParser::SVIParser  ioin::IO
{
  local argResult :: Either<String  Decorated CmdArgs> = parseArgs(args);
  local a :: Decorated CmdArgs = case argResult of right(t) -> t end;

  -- Let's locally set up and verify the environment
  local envSH :: IOVal<String> = envVar("SILVER_HOME", ioin);
  local envGP :: IOVal<String> = envVar("GRAMMAR_PATH", envSH.io);
  local envSG :: IOVal<String> = envVar("SILVER_GEN", envGP.io);
  
  local silverHome :: String =
    endWithSlash(head(a.silverHomeOption ++ [envSH.iovalue]));
  local silverGen :: String =
    endWithSlash(head(a.genLocation ++ (if envSG.iovalue == "" then [] else [envSG.iovalue]) ++ [silverHome ++ "generated/"]));
  local grammarPath :: [String] =
    map(endWithSlash, a.searchPath ++ [silverHome ++ "grammars/"] ++ explode(":", envGP.iovalue) ++ ["."]);
  local buildGrammar :: String = head(a.buildGrammar);

  local check :: IOVal<[String]> =
    checkEnvironment(a, silverHome, silverGen, grammarPath, buildGrammar, envSG.io);
  
  -- Compile grammars. There's some tricky circular program data flow here.
  -- This does an "initial grammar stream" composed of 
  -- grammars and interface files that *locally* seem good.
  local rootStream :: IOVal<[Maybe<RootSpec>]> =
    compileGrammars(svParser, sviParser, grammarPath, silverGen, grammarStream, a.doClean, check.io);

  -- The list of grammars to build. This is circular with the above, producing
  -- a list that's terminated when the response count is equal to the number of emitted
  -- grammar names.
  local grammarStream :: [String] =
    buildGrammar :: eatGrammars(1, [buildGrammar], rootStream.iovalue, unit.grammarList);
  
  -- This is, essentially, a data structure representing a compilation.
  -- Note that it is pure: it doesn't take any actions.
  local unit :: Compilation =
    compilation(
      foldr(consGrammars, nilGrammars(), catMaybes(rootStream.iovalue)),
      foldr(consGrammars, nilGrammars(), catMaybes(reRootStream.iovalue)),
      buildGrammar, silverHome, silverGen);
  unit.config = a;
    
  -- There is a second circularity here where we use unit.recheckGrammars
  -- to supply the second parameter to unit.
  local reRootStream :: IOVal<[Maybe<RootSpec>]> =
    compileGrammars(svParser, sviParser, grammarPath, silverGen, unit.recheckGrammars, true, rootStream.io);

  -- unit.postOps is a "pure value," here's where we make it go.
  local actions :: IOVal<Integer> = runAll(sortUnits(unit.postOps), reRootStream.io);

  -- no check performed here
  return ioval(actions.io, []);
}

function getSysMessages
[IdeMessage] ::= es::[String]
{
  return if null(es)
         then []
         else let 
                  head :: String = head(es)
              in 
                  [makeSysIdeMessage(ideMsgLvError, head)] ++ getSysMessages(tail(es))
              end;
}

function getAllBindingErrors
[IdeMessage] ::= specs::[Decorated RootSpec] projectPath::String
{

  local spec :: Decorated RootSpec = head(specs);
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
                  head :: Message = head(es)
              in 
                  [makeLinkedResourceMessage(path, grmRoot, head.loc, head.severity, head.msg)] ++ rewriteMessagesLinked(path, grmRoot, tail(es))
              end;
}

{-- This doesn't work when the folder name (a.b.c), rather than folder strucutre (a/b/c), is mapped to grammar,
function translateToPath
String ::= declaredName::String
{
  return implode("/", explode(":", declaredName));
}
--}

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

