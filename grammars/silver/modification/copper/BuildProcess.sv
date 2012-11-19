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
ParseResult<Decorated CmdArgs> ::= args::[String]
{
  flags <- [pair("--copperdump", flag(copperdumpFlag))];
  flagdescs <- ["\t--copperdump  : force Copper to dump parse table information"];
}
aspect function runActions
IOVal<Integer> ::=
  a::Decorated CmdArgs
  silverHome::String
  silverGen::String
  buildGrammar::String
  grammars::[Decorated RootSpec]
  ioin::IO
{
  -- TODO: production because of hack!
  production allParsers :: [ParserSpec] = foldr(append, [], map((.parserSpecs), grammarsToTranslate));
  -- TODO: temporarily all parsers!
  postOps <- [generateCS(grammarEnv, allParsers, silverGen)]; 
}




abstract production generateCS
top::Unit ::= grams::EnvTree<Decorated RootSpec> specs::[ParserSpec] silvergen::String
{
  local attribute pr::IO;
  pr = print("Generating Parsers and Scanners.\n", top.ioIn);
  
  top.io = writeCSSpec(pr, grams, silvergen ++ "src/", specs);
  top.code = 0;
  top.order = 5;
}

function writeCSSpec
IO ::= i::IO grams::EnvTree<Decorated RootSpec> silvergen::String specs::[ParserSpec]
{
  local attribute p :: ParserSpec;
  p = head(specs);
  p.compiledGrammars = grams;
  
  local attribute ast :: SyntaxRoot;
  ast = p.cstAst; -- TODO: we can check for errors on this parser now!! :D
  
  local attribute parserName :: String;
  parserName = makeParserName(p.fullName);

  local attribute copperFile :: String;
  copperFile = silvergen ++ grammarToPath(p.sourceGrammar) ++ parserName ++ ".copper";

  local attribute printio :: IO;
  printio = print("\t[" ++ p.fullName ++ "]\n", i);
  
  local attribute writeio :: IO;
  writeio = writeFile(copperFile, ast.xmlCopper, printio);
  
  return if null(specs) then i
         else writeCSSpec(writeio, grams, silvergen, tail(specs));
}

aspect function writeBuildFile
IO ::= i::IO a::Decorated CmdArgs specs::[String] silverhome::String silvergen::String allParsers::[ParserSpec]
{
  classpathCompiler <- ["${sh}/jars/CopperCompiler.jar"];
  classpathRuntime <- ["${sh}/jars/CopperRuntime.jar"];
  extraTopLevelDecls <- [
    "  <taskdef name='copper' classname='edu.umn.cs.melt.copper.ant.CopperAntTask' classpathref='compile.classpath'/>",
    "  <target name='copper'>\n" ++ buildAntParserPart(allParsers, a) ++ "  </target>"];
  extraGrammarsDeps <- ["copper"];
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
"    <copper fullClassName='" ++ packagename ++ "." ++ parserName ++ "' inputFile='${src}/" ++ packagepath ++ parserName ++ ".copper' " ++ 
	"outputFile='${src}/" ++ packagepath ++ parserName ++ ".java' skin='XML' warnUselessNTs='no' dump='true' dumpType='HTML'" ++
	(if a.forceCopperDump then "" else " dumpOnlyOnError='true'") ++ " dumpFile='" ++ parserName ++ ".copperdump.html'"  ++ 
	"/>\n" ++
 	 buildAntParserPart(tail(r), a));
}

