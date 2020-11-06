grammar silver:modification:copper;

import silver:driver;
import silver:translation:java:driver;

import silver:util:cmdargs;

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
aspect production compilation
top::Compilation ::= g::Grammars  _  buildGrammar::String  benv::BuildEnv
{
  classpathCompiler <- ["${sh}/jars/CopperCompiler.jar"];
  classpathRuntime <- ["${sh}/jars/CopperRuntime.jar"];

  -- Get the parsers
  production allParsers :: [ParserSpec] =
    flatMap(obtainParserSpecs(_, benv), grammarsRelevant);
  
  -- Have them get compiled by copper
  extraGrammarsDeps <- ["copper"];
  extraTopLevelDecls <- [
    "  <taskdef name='copper' classname='edu.umn.cs.melt.copper.ant.CopperAntTask' classpathref='compile.classpath'/>",
    "  <target name='copper'>\n" ++ sflatMap(buildAntParserPart(_, top.config), allParsers) ++ "  </target>"];

  -- Generate the .copper files
  top.postOps <-
    map(parserSpecUnit(_, g.compiledGrammars, benv.silverGen), allParsers);
}

-- Skips parser specs from SILVER_HOST_GEN
-- The way that feature works, they shouldn't need regeneration.
function obtainParserSpecs
[ParserSpec] ::= g::Decorated RootSpec  benv::BuildEnv
{
  return if g.generateLocation != benv.silverGen then []
         else g.parserSpecs;
}

function buildAntParserPart
String ::= p::ParserSpec  a::Decorated CmdArgs
{
  local parserName :: String = makeParserName(p.fullName);
  
  local packagepath :: String = grammarToPath(p.sourceGrammar);
  
  local varyingopts :: String =
    if a.forceCopperDump then
      "avoidRecompile='false' dump='ON'"
    else
      "avoidRecompile='true' dump='ERROR_ONLY'";

  return s"""
    <copper packageName='${makeName(p.sourceGrammar)}' parserName='${parserName}' outputFile='$${src}/${packagepath ++ parserName}.java' useSkin='XML' warnUselessNTs='false' ${varyingopts} dumpFormat='HTML' dumpFile='${parserName}.copperdump.html'>
      <inputs file='$${src}/${packagepath ++ parserName}.copper'/>
    </copper>
""";
}

{--
 - At first, it might seem that parsers could be generated along with anything else in a grammar.
 - (i.e. genFiles a .copper file)
 - Unfortunately, it turns out this is not the case, due to a possibly over-aggressive
 - build optimization we do: if the grammar was not directly modified, we assume it doesn't need
 - to be re-translated. (TECHNICALLY, this isn't always the case...)
 -
 - So, parsers can change as a result of the grammars they depend on changing, so these
 - DO need re-translation. So we treat them specially.
 -
 - If we ever fix the overall build process to re-translate grammars that depend on changed grammars,
 - then we might be able to merge this into the normal process, maybe.
 -}
abstract production parserSpecUnit
top::DriverAction ::= spec::ParserSpec  cg::EnvTree<Decorated RootSpec>  silverGen::String
{
  local dir :: String =
    silverGen ++ "src/" ++ grammarToPath(spec.sourceGrammar);
  local file :: String =
    dir ++ makeParserName(spec.fullName) ++ ".copper";

  spec.compiledGrammars = cg;
  local newSpec :: String =
    spec.cstAst.xmlCopper;

  local specCst :: SyntaxRoot = spec.cstAst;

  local ex :: IOVal<Boolean> = isFile(file, top.ioIn);
  local oldSpec :: IOVal<String> = readFile(file, ex.io);
  
  local join :: IO = if ex.iovalue then oldSpec.io else ex.io;

  local err :: IO = 
    print("CST errors while generating parser " ++ spec.fullName ++ ":\n" ++
      implode("\n", specCst.cstErrors) ++ "\n", join);

  local doUTD :: IO =
    print("Parser " ++ spec.fullName ++ " up to date.\n", join);
  
  local doWR :: IO =
    writeFile(file, newSpec,
      print("Generating parser " ++ spec.fullName ++ ".\n",
        -- hack to ensure directory exists (for --dont-translate)
        mkdir(dir, join).io));

  top.io = if null(specCst.cstErrors) then 
             if ex.iovalue && oldSpec.iovalue == newSpec then doUTD 
               else doWR
             else err;
             
  top.code = if null(specCst.cstErrors) then 0 else 1;
  top.order = 7;
}

