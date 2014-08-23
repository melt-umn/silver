grammar silver:driver;

imports silver:definition:core;
imports silver:definition:env;
imports silver:definition:env:env_parser;

imports silver:util;
imports silver:util:cmdargs;

exports silver:driver:util;

type SVParser = (ParseResult<Root> ::= String String);
type SVIParser = (ParseResult<IRoot> ::= String String);

{--
 - Run the silver compiler, as if invoked from the command line.
 -}
function cmdLineRun
IOVal<Integer> ::= args::[String]  svParser::SVParser  sviParser::SVIParser  ioin::IO
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

  return if !null(argErrors) then
    ioval(print(head(argErrors), ioin), 1)
  else if a.displayVersion then
    ioval(print(
      "Silver Version 0.4.0-dev\n" ++
      "SILVER_HOME = " ++ benv.silverHome ++ "\n" ++
      "SILVER_GEN = " ++ benv.silverGen ++ "\n" ++
      "GRAMMAR_PATH:\n" ++ implode("\n", benv.grammarPath) ++ "\n\n" ++
      implode("\n", envErrors), benvResult.io), 1) -- exit with an error code so 'ant' isnt run.
  else if !null(envErrors ++ checkbuild.iovalue) then
    ioval(print(implode("\n", envErrors ++ checkbuild.iovalue), checkbuild.io), 1)
  else if null(unit.grammarList) then
    ioval(print("The specified grammar (" ++ buildGrammar ++ ") could not be found.\n", buildrun.io), 1)
  else
    actions;
}

{--
 - Given an environment and a grammar to build, returns a Compilation.
 - Note that it's the caller's responsibility to actually evaluation that
 - compilation's actions.
 -}
function buildRun
IOVal<Decorated Compilation> ::=
  svParser::SVParser
  sviParser::SVIParser
  a::Decorated CmdArgs
  benv::BuildEnv
  buildGrammar::String
  ioin::IO
{
  -- Compile grammars. There's some tricky circular program data flow here.
  -- This does an "initial grammar stream" composed of 
  -- grammars and interface files that *locally* seem good.
  local rootStream :: IOVal<[Maybe<RootSpec>]> =
    compileGrammars(svParser, sviParser, benv, grammarStream, a.doClean, ioin);

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
      buildGrammar, benv);
  -- This is something we should probably get rid of, someday. Somehow. It's hard.
  unit.config = a;
    
  -- There is a second circularity here where we use unit.recheckGrammars
  -- to supply the second parameter to unit.
  local reRootStream :: IOVal<[Maybe<RootSpec>]> =
    compileGrammars(svParser, sviParser, benv, unit.recheckGrammars, true, rootStream.io);

  return ioval(reRootStream.io, unit);
}


{--
 - Consumes a stream of parses, outputs a stream of new dependencies.
 - Typically used as a circular program with 'compileGrammars'
 -
 - @param n  Expected number of new inputs from rootStream
 - @param sofar  Set of grammars already seen, and should not be requested again
 - @param rootStream  Stream of found/not found info. Should not be used except to test presence
 - @param grammars  List of grammars *in the same order as 'just' appears in rootStream*
 - @return  A stream of new dependencies
 -}
function eatGrammars
[String] ::= n::Integer  sofar::[String]  rootStream::[Maybe<a>]  grammars::[Decorated RootSpec]
{
  local it :: Decorated RootSpec = head(grammars);
  
  local directDeps :: [String] = mentionedGrammars(it);
  
  local newDeps :: [String] = rem(directDeps, sofar);
  
  return
    if n == 0 then
      []
    else if !head(rootStream).isJust then
      eatGrammars(n-1, sofar, tail(rootStream), grammars)
    else
      newDeps ++ eatGrammars(n-1+length(newDeps), newDeps ++ sofar, tail(rootStream), tail(grammars));
}


