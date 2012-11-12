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

aspect production run
top::RunUnit ::= iIn::IO args::[String]
{
  flags <- [pair("--copperdump", flag(copperdumpFlag))];
  flagdescs <- ["\t--copperdump: force copper to dump parse table information\n"];

  -- Only examine grammar depended upon to determine what parsers may be built
  -- but do include both interface files and rootspecs.
  depAnalysis.allParsers = foldr(append, [], map((.parserSpecs), grammarsToTranslate));

  postOps <- [generateCS(grammarEnv, depAnalysis.taintedParsers, silvergen)]; 
}

-- InterfaceUtil.sv
synthesized attribute taintedParsers:: [ParserSpec] occurs on DependencyAnalysis;
inherited attribute allParsers :: [ParserSpec] occurs on DependencyAnalysis;

aspect production dependencyAnalysis
top::DependencyAnalysis ::= ifaces::[Decorated Interface]  compiledRootSpecs::[Decorated RootSpec]
{
  top.taintedParsers = findTaintedParsers(top.allParsers, taintedaltered, altered);
}

function findTaintedParsers
[ParserSpec] ::= rs::[ParserSpec] ta::[String] a::[String]
{
  return if null(rs) then []
         else if containsAny(ta, head(rs).moduleNames) -- The parser uses an altered/tainted grammar. (TODO: should we include suspect grammars?)
              || startsWithAny(a, head(rs).fullName) -- The parser is IN an altered grammar. (NOT tainted, that's fine.)
              then [head(rs)] ++ findTaintedParsers(tail(rs), ta, a)
              else findTaintedParsers(tail(rs), ta, a);
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
IO ::= i::IO a::Decorated CmdArgs specs::[String] silverhome::String silvergen::String da::Decorated DependencyAnalysis grammarLoc::String
{
  classpathCompiler <- ["${sh}/jars/CopperCompiler.jar"];
  classpathRuntime <- ["${sh}/jars/CopperRuntime.jar"];
  extraTopLevelDecls <- [
    "  <taskdef name='copper' classname='edu.umn.cs.melt.copper.ant.CopperAntTask' classpathref='compile.classpath'/>",
    "  <target name='copper'>\n" ++ buildAntParserPart(if a.forceCopperDump then da.allParsers else da.taintedParsers, a) ++ "  </target>"];
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

