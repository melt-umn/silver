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
IO<Integer> ::= args::[String]  svParser::SVParser
{
  local unit :: IOErrorable<Decorated Compilation> =
    cmdLineRunInitial(args, svParser);
    
  return performActions(unit);
}

-- Compute the environment, and then setup and do a build run. No postOps executed, though.
function cmdLineRunInitial
IOErrorable<Decorated Compilation> ::= args::[String]  svParser::SVParser
{
  return do {
    env::(Decorated CmdArgs, BuildEnv) <- computeEnv(args);
    setupBuildRun(svParser, env.1, env.2);
  };
}

-- Perform the postOps from a cmdLineRunInitial.
function performActions
IO<Integer> ::= unit::IOErrorable<Decorated Compilation>
{
  return do {
    res::Either<RunError Decorated Compilation> <- unit.run;
    case res of
    | left(re) -> do {
        eprintln(re.message);
        return re.code;
      }
    | right(comp) -> runAll(comp.postOps)
    end;
  };
}

-- Parser args and environment
function computeEnv
IOErrorable<(Decorated CmdArgs, BuildEnv)> ::= args::[String]
{
  return
    -- Figure out arguments
    case parseArgs(args) of
    | left(argErrors) -> throwRunError(1, argErrors)
    | right(a) -> do {
      -- Figure out build env from environment and args
      benv::BuildEnv <- determineBuildEnv(a);
      -- Because we want printing the version to work even if the environment is messed up
      -- we premptively handle that here. This is slightly unfortunate.
      -- Ideally, version printing would be just another thing we could have the command
      -- line decide to go do, but currently it's hard to re-use code if we do that.
      if a.displayVersion then
        throwRunError(127, -- error code so 'ant' isnt run
          "Silver Version 0.4.5-dev\n" ++
          "SILVER_HOME = " ++ benv.silverHome ++ "\n" ++
          "SILVER_GEN = " ++ benv.silverGen ++ "\n" ++
          "GRAMMAR_PATH:\n" ++ implode("\n", benv.grammarPath))
      else pure((a, benv));
    }
    end;
}

-- Upon deciding that we're to build one or more grammars into a jar, we do this
function setupBuildRun
IOErrorable<Decorated Compilation> ::=
  svParser::SVParser
  a::Decorated CmdArgs
  benv::BuildEnv
{
  return do {
    -- Check environment stuff specific to building a grammar
    checkbuild::[String] <- lift(checkPreBuild(benv, a.buildGrammars));
    when_(!null(checkbuild), throwRunError(1, implode("\n", checkbuild)));

    -- Build!
    buildrun :: Decorated Compilation <- lift(buildRun(svParser, a, benv, a.buildGrammars));
    let missingGrammars::[String] =
      removeAll(map((.declaredName), buildrun.grammarList), a.buildGrammars);
    when_(!null(missingGrammars),
      throwRunError(1, "The specified grammar(s) " ++ implode(", ", missingGrammars) ++ " could not be found.\n"));

    return buildrun;
  };
}

{--
 - Given an environment and a grammar to build, returns a Compilation.
 - Note that it's the caller's responsibility to actually evaluate that
 - compilation's actions.
 -}
function buildRun
IO<Decorated Compilation> ::=
  svParser::SVParser
  a::Decorated CmdArgs
  benv::BuildEnv
  buildGrammars::[String]
{
  return mdo {
    -- Compile grammars. There's some tricky circular program data flow here.
    -- This does an "initial grammar stream" composed of 
    -- grammars and interface files that *locally* seem good.
    rootStream :: [Maybe<RootSpec>] <-
      unsafeInterleaveIO(compileGrammars(svParser, benv, grammarStream, a.doClean));

    -- The list of grammars to build. This is circular with the above, producing
    -- a list that's terminated when the response count is equal to the number of emitted
    -- grammar names.
    let grammarStream :: [String] =
      buildGrammars ++
      eatGrammars(length(buildGrammars), buildGrammars, rootStream, unit.grammarList);
    
    -- This is, essentially, a data structure representing a compilation.
    -- Note that it is pure: it doesn't take any actions.
    let unit :: Decorated Compilation =
      decorate
        compilation(
          foldr(consGrammars, nilGrammars(), catMaybes(rootStream)),
          foldr(consGrammars, nilGrammars(), catMaybes(reRootStream)),
          buildGrammars, benv)
      with {
        -- This is something we should probably get rid of, someday. Somehow. It's hard.
        config = a;
      };

    -- There is a second circularity here where we use unit.recheckGrammars
    -- to supply the second parameter to unit.
    reRootStream :: [Maybe<RootSpec>] <-
      unsafeInterleaveIO(compileGrammars(svParser, benv, unit.recheckGrammars, true));

    return unit;
  };
}

{--
 - Eat the stream `need` and produce the output stream of (maybe, if found) `RootSpec`s.
 -
 - @param benv   The compiler configuration, including search paths
 - @param need   A **stream** of grammars to compile.
 - @param clean  If true, ignore interface files entirely.
 -}
function compileGrammars
IO<[Maybe<RootSpec>]> ::=
  svParser::SVParser
  benv::BuildEnv
  need::[String]
  clean::Boolean
{
  return traverseA(\ g::String -> compileGrammar(svParser, benv, g, clean).run, need);
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

synthesized attribute code::Integer;

nonterminal RunError with code, message;
-- from silver:langutil, and silver:compiler:driver:util;

abstract production runError
top::RunError ::= c::Integer  m::String
{
  top.code = c;
  top.message = m;
}

-- A common return type for IO functions. Does IO and returns error or whatever.
type IOErrorable<a> = EitherT<RunError IO a>;

function throwRunError
IOErrorable<a> ::= c::Integer m::String
{
  return throwError(runError(c, m));
}
