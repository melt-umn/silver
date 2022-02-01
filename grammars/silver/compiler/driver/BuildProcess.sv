grammar silver:compiler:driver;

imports silver:compiler:definition:core;
imports silver:compiler:definition:env;

imports silver:util:cmdargs;

exports silver:compiler:driver:util;

type SVParser = (ParseResult<Root> ::= String String);

{--
 - Run the silver compiler, as if invoked from the command line.
 -}
function cmdLineRun
IOVal<Integer> ::= args::[String]  svParser::SVParser  ioin::IOToken
{
  local unit :: IOErrorable<Decorated Compilation> =
    cmdLineRunInitial(args, svParser, ioin);
    
  return performActions(unit);
}

-- Compute the environment, and then setup and do a build run. No postOps executed, though.
function cmdLineRunInitial
IOErrorable<Decorated Compilation> ::=
  args::[String]  svParser::SVParser  ioin::IOToken
{
  return
    runChainArg(
      computeEnv,
      setupBuildRun(svParser, _, _),
      args, ioin);
}

-- Perform the postOps from a cmdLineRunInitial.
function performActions
IOVal<Integer> ::= unitin::IOErrorable<Decorated Compilation>
{
  return case unitin.iovalue of
  | left(re) -> ioval(eprintlnT(re.message, unitin.io), re.code)
  | right(comp) -> runAll(sortUnits(comp.postOps), unitin.io)
  end;
}

-- Parser args and environment
function computeEnv
IOErrorable<Pair<Decorated CmdArgs  BuildEnv>> ::=
  args::[String]
  ioin::IOToken
{
  -- Figure out arguments
  local argResult :: Either<String  Decorated CmdArgs> =
    parseArgs(args);
  local a :: Decorated CmdArgs =
    case argResult of right(t) -> t | _ -> error("Form is checked elsewhere before use") end;
  local argErrors :: [String] =
    case argResult of | left(s) -> [s] | _ -> [] end;

  -- Figure out build env from environment and args
  local benvResult :: IOVal<Either<BuildEnv  [String]>> =
    determineBuildEnv(a, ioin);
  local benv :: BuildEnv =
    case benvResult.iovalue of left(t) -> t | right(_) -> error("Form is checked elsewhere before use") end;
  local envErrors :: [String] =
    case benvResult.iovalue of | right(s) -> s | _ -> [] end;

  return if !null(argErrors) then
    ioval(ioin, left(runError(1, head(argErrors))))
  -- Because we want printing the version to work even if the environment is messed up
  -- we premptively handle that here. This is slightly unfortunate.
  -- Ideally, version printing would be just another thing we could have the command
  -- line decide to go do, but currently it's hard to re-use code if we do that.
  else if !null(envErrors) then
    ioval(benvResult.io, left(runError(1, implode("\n", envErrors))))
  else if a.displayVersion then
    ioval(benvResult.io, left(runError(1, -- error code so 'ant' isnt run
      "Silver Version 0.4.1-dev\n" ++
      "SILVER_HOME = " ++ benv.silverHome ++ "\n" ++
      "SILVER_GEN = " ++ benv.silverGen ++ "\n" ++
      "GRAMMAR_PATH:\n" ++ implode("\n", benv.grammarPath) ++ "\n\n" ++
      implode("\n", envErrors))))
  else
    ioval(benvResult.io, right(pair(a, benv)));
}

-- Upon deciding that we're to build a single grammar into a jar, we do this
function setupBuildRun
IOErrorable<Decorated Compilation> ::=
  svParser::SVParser
  envin::Pair<Decorated CmdArgs  BuildEnv>
  ioin::IOToken
{
  local a::Decorated CmdArgs = envin.fst;
  local benv::BuildEnv = envin.snd;

  -- Check environment stuff specific to building a grammar
  local buildGrammar :: String = head(a.buildGrammar);
  local checkbuild :: IOVal<[String]> =
    checkPreBuild(a, benv, buildGrammar, ioin);

  -- Build!
  local buildrun :: IOVal<Decorated Compilation> =
    buildRun(svParser, a, benv, buildGrammar, checkbuild.io);

  return if !null(checkbuild.iovalue) then
    ioval(checkbuild.io, left(runError(1, implode("\n", checkbuild.iovalue))))
  else if null(buildrun.iovalue.grammarList) then
    ioval(buildrun.io, left(runError(1, "The specified grammar (" ++ buildGrammar ++ ") could not be found.\n")))
  else
    ioval(buildrun.io, right(buildrun.iovalue));
}

{--
 - Given an environment and a grammar to build, returns a Compilation.
 - Note that it's the caller's responsibility to actually evaluation that
 - compilation's actions.
 -}
function buildRun
IOVal<Decorated Compilation> ::=
  svParser::SVParser
  a::Decorated CmdArgs
  benv::BuildEnv
  buildGrammar::String
  ioin::IOToken
{
  -- Compile grammars. There's some tricky circular program data flow here.
  -- This does an "initial grammar stream" composed of 
  -- grammars and interface files that *locally* seem good.
  local rootStream :: IOVal<[Maybe<RootSpec>]> =
    compileGrammars(svParser, benv, grammarStream, a.doClean, ioin);

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
    compileGrammars(svParser, benv, unit.recheckGrammars, true, rootStream.io);

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
  
  local newDeps :: [String] = removeAll(sofar, directDeps);
  
  return
    if n == 0 then
      []
    else if !head(rootStream).isJust then
      eatGrammars(n-1, sofar, tail(rootStream), grammars)
    else
      newDeps ++ eatGrammars(n-1+length(newDeps), newDeps ++ sofar, tail(rootStream), tail(grammars));
}


nonterminal RunError with code, message;
-- from silver:langutil, and silver:compiler:driver:util;

abstract production runError
top::RunError ::= c::Integer  m::String
{
  top.code = c;
  top.message = m;
}

-- A common return type for IO functions. Does IO and returns error or whatever.
type IOErrorable<a> = IOVal<Either<RunError a>>;
-- A function that does IO and either errors or returns a value
type RunChain<a b> = (IOErrorable<b> ::= a IOToken);

-- Function composition of RunChains. IO-y Either monad, of sorts.
function runChain
RunChain<a c> ::= l::RunChain<a b>  r::RunChain<b c>
{
  return runChainArg(l, r, _, _);
}

function runChainArg
IOErrorable<c> ::= l::RunChain<a b>  r::RunChain<b c> x::a ioin::IOToken
{
  -- Apply to left.
  local lcall :: IOErrorable<b> = l(x, ioin);
  
  -- apply return value to right, if possible. otherwise, propagate error
  local rcall :: IOErrorable<c> =
    case lcall.iovalue of
    | left(re) -> ioval(lcall.io, left(re))
    | right(y) -> r(y, lcall.io)
    end;
  
  return rcall;
}

