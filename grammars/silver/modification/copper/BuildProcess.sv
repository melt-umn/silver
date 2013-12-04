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
top::Compilation ::= g::Grammars _ buildGrammar::String silverHome::String silverGen::String
{
  classpathCompiler <- ["${sh}/jars/CopperCompiler.jar"];
  classpathRuntime <- ["${sh}/jars/CopperRuntime.jar"];
  extraTopLevelDecls <- [
    "  <taskdef name='copper' classname='edu.umn.cs.melt.copper.ant.CopperAntTask' classpathref='compile.classpath'/>",
    "  <target name='copper'>\n" ++ buildAntParserPart(allParsers, top.config) ++ "  </target>"];
  extraGrammarsDeps <- ["copper"];

  production allParsers :: [ParserSpec] =
    foldr(append, [], map((.parserSpecs), grammarsRelevant));
  
  top.postOps <-
    map(parserSpecUnit(_, g.compiledGrammars, silverGen), allParsers);
}

function buildAntParserPart
String ::= r::[ParserSpec] a::Decorated CmdArgs
{
  local attribute p :: ParserSpec;
  p = head(r);

  local attribute parserName :: String;
  parserName = makeParserName(p.fullName);
  
  local attribute packagename :: String;
  packagename = makeName(p.sourceGrammar);
  
  local attribute packagepath :: String;
  packagepath = grammarToPath(p.sourceGrammar);

  return if null(r) then "" else( 
"    <copper packageName='" ++ packagename ++ "' parserName='" ++ parserName ++ "' outputFile='${src}/" ++ packagepath ++ parserName ++ ".java' useSkin='XML' warnUselessNTs='false' avoidRecompile='true' dump='" ++ (if a.forceCopperDump then "ON" else "ERROR_ONLY") ++ "' dumpFormat='HTML' dumpFile='" ++ parserName ++ ".copperdump.html'>\n" ++
"      <inputs file='${src}/" ++ packagepath ++ parserName ++ ".copper'/>\n    </copper>\n" ++
  buildAntParserPart(tail(r), a));
}

abstract production parserSpecUnit
top::Unit ::= spec::ParserSpec  cg::EnvTree<Decorated RootSpec>  silverGen::String
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

