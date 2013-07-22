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
  local argResult :: ParseResult<Decorated CmdArgs> = parseArgs(args);
  local a :: Decorated CmdArgs = argResult.parseTree;

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
      foldr(consGrammars, nilGrammars(), foldr(consMaybe, [], rootStream.iovalue)),
      foldr(consGrammars, nilGrammars(), foldr(consMaybe, [], reRootStream.iovalue)),
      buildGrammar, silverHome, silverGen);
  unit.config = a;
  
  -- Note that this is used above. This outputs deps, and rootStream informs it.
  local grammarStream :: [String] =
    eatGrammars(1, [buildGrammar], rootStream.iovalue, unit.grammarList);
  
  local reRootStream :: IOVal<[Maybe<RootSpec>]> =
    compileGrammars(svParser, sviParser, grammarPath, silverGen, unit.recheckGrammars, true, rootStream.io);

  local messages :: [IdeMessage] = getAllBindingErrors(unit.grammarList, projectPath);

  return if !argResult.parseSuccess then ioval(ioin, [makeSysIdeMessage(ideMsgLvError, "Parsing failed during build. If source code/resources are changed outside IDE, refresh and rebuild is needed.")])
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
  local argResult :: ParseResult<Decorated CmdArgs> = parseArgs(args);
  local a :: Decorated CmdArgs = argResult.parseTree;

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
      foldr(consGrammars, nilGrammars(), foldr(consMaybe, [], rootStream.iovalue)),
      foldr(consGrammars, nilGrammars(), foldr(consMaybe, [], reRootStream.iovalue)),
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
  local grmPath::String = translateToPath(spec.declaredName);

  return if null(specs)
         then []
         else if startsWith(projectPath, spec.grammarSource) -- check if this spec is physically located under project
              then rewriteMessages(grmPath, spec.errors) ++ getAllBindingErrors(tail(specs), projectPath)
                  -- if not, generate message for linked resource
              else rewriteMessagesLinked(grmPath, getGrammarRoot(spec.grammarSource, grmPath), spec.errors) ++ 
                   getAllBindingErrors(tail(specs), projectPath);

 --rewriteMessages(translateToPath(head(specs).declaredName), head(specs).errors) ++ getAllBindingErrors(tail(specs));
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

function translateToPath
String ::= declaredName::String
{
  return implode("/", explode(":", declaredName));
}

-- fullPath: /home/melt/test/a/b/c
-- grmPath: a/b/c
-- returns: /home/melt/test/
function getGrammarRoot
String ::= fullPath::String grmPath::String
{
  return substring(0, lastIndexOf(grmPath ++ "/", fullPath), fullPath);
}

