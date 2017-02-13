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
  extraTopLevelDecls <- [
    "  <taskdef name='copper' classname='edu.umn.cs.melt.copper.ant.CopperAntTask' classpathref='compile.classpath'/>",
    "  <target name='copper'>\n" ++ implode("", map(buildAntParserPart(_, top.config), allParsers)) ++ "  </target>"];
  extraGrammarsDeps <- ["copper"];

  production allParsers :: [ParserSpec] =
    foldr(append, [], map((.parserSpecs), grammarsRelevant));
  
  top.postOps <-
    map(parserSpecUnit(_, g.compiledGrammars, benv.silverGen), allParsers);
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

abstract production parserSpecUnit
top::DriverAction ::= spec::ParserSpec  cg::EnvTree<Decorated RootSpec>  silverGen::String
{
  local file :: String =
    silverGen ++ "src/" ++ grammarToPath(spec.sourceGrammar) ++ makeParserName(spec.fullName) ++ ".copper";

  spec.compiledGrammars = cg;
  local newSpec :: String =
    spec.cstAst.xmlCopper;

  local ex :: IOVal<Boolean> = isFile(file, top.ioIn);
  local oldSpec :: IOVal<String> = readFile(file, ex.io);
  
  local join :: IO = if ex.iovalue then oldSpec.io else ex.io;

  local doUTD :: IO =
    print("Parser " ++ spec.fullName ++ " up to date.\n", join);
  
  local doWR :: IO =
    writeFile(file, newSpec,
      print("Generating Parser " ++ spec.fullName ++ ".\n", join));

  top.io = if ex.iovalue && oldSpec.iovalue == newSpec then doUTD else doWR;
  top.code = 0; -- should always be okay...
  top.order = 7;
}

