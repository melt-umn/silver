grammar silver:compiler:modification:copper;

import silver:compiler:definition:concrete_syntax:copper as copper;
import silver:compiler:driver;
import silver:compiler:translation:java:driver;
import silver:util:cmdargs;

{---------------------------------}
{- Define the --copperdump flag. -}
{---------------------------------}

synthesized attribute forceCopperDump :: Boolean occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= _
{
  top.forceCopperDump = false;
}

abstract production copperdumpFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.forceCopperDump = true;
  forwards to rest;
}

aspect function parseArgs
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [pair("--copperdump", flag(copperdumpFlag))];
  flagdescs <- ["\t--copperdump  : force Copper to dump parse table information"];
}

{--------------------------------}
{- Request building of parsers. -}
{--------------------------------}

-- Skips parser specs from SILVER_HOST_GEN
-- The way that feature works, they shouldn't need regeneration.
function obtainParserSpecs
[ParserSpec] ::= g::Decorated RootSpec  benv::BuildEnv
{
  return if g.generateLocation != benv.silverGen then []
         else g.parserSpecs;
}

aspect production compilation
top::Compilation ::= g::Grammars  _  buildGrammar::String  benv::BuildEnv
{
  -- Add the Copper compiler to the CLASSPATH. In theory, this is only
  -- necessary when building Silver (or other programs that invoke the Copper
  -- compiler directly), and could be replaced with the Copper runtime
  -- otherwise. If we re-do the build system, we could make the Copper compiler
  -- JAR not include the runtime, link against the runtime here, and make
  -- importing the silver:compiler:definition:concrete_syntax:copper grammar
  -- add the Copper compiler back.
  classpathRuntime <- ["${sh}/jars/CopperCompiler.jar"];

  -- Get the parsers.
  production allParsers :: [ParserSpec] =
    flatMap(obtainParserSpecs(_, benv), grammarsRelevant);

  -- Generate the .java files.
  top.postOps <-
    map(buildParserAction(_, g.compiledGrammars, benv.silverGen, top.config), allParsers);
}

{------------------------------}
{- Build the parsers to Java. -}
{------------------------------}

@{- Writes a parser out to a file. -}
abstract production buildParserAction
top::DriverAction ::= spec::ParserSpec  compiledGrammars::EnvTree<Decorated RootSpec>  silverGen::String  cmdArgs::Decorated CmdArgs
{
  spec.compiledGrammars = compiledGrammars;

  local specCstAst :: SyntaxRoot = spec.cstAst;

  -- cmdArgs _could_ be top.config, if the driver were to decorate DriverAction
  -- with config. However, the driver doesn't, and it seems like it'd be a pain
  -- to make it do so.
  local val::IOVal<Integer> = evalIO(do {
    let outDir :: String = silverGen ++ "src/" ++ grammarToPath(spec.sourceGrammar);
    let parserName :: String = makeParserName(spec.fullName);

    if null(specCstAst.cstErrors) then do {
      mkdir(outDir);
      print("Generating parser " ++ spec.fullName ++ ".\n");
      copper:compileParserBean(specCstAst.copperParser,
        makeName(spec.sourceGrammar), parserName,
        outDir ++ parserName ++ ".java", cmdArgs.forceCopperDump,
        parserName ++ ".html");
    } else do {
      -- Should this be stderr?
      print("CST errors while generating parser " ++ spec.fullName ++ ":\n" ++
        implode("\n", specCstAst.cstErrors) ++ "\n");
      return 1;
    };
  }, top.ioIn);

  top.io = val.io;
  top.code = val.iovalue;
  top.order = 7;
}
