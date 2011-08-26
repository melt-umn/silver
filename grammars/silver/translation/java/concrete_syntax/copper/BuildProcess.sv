grammar silver:translation:java:concrete_syntax:copper;

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

  postOps <- [generateCS(depAnalysis.taintedParsers, silvergen)]; 
}

-- InterfaceUtil.sv
synthesized attribute taintedParsers:: [Decorated ParserSpec] occurs on DependencyAnalysis;
synthesized attribute allParsers :: [Decorated ParserSpec] occurs on DependencyAnalysis;

aspect production dependencyAnalysis
top::DependencyAnalysis ::= ifaces::[Decorated Interface]
{
  top.allParsers = collectParserSpecs(ifspecs ++ top.compiledGrammars); -- collect them from everything
  
  top.taintedParsers = findTaintedParsers(top.allParsers, taintedaltered, altered);
}

function collectParserSpecs
[Decorated ParserSpec] ::= rs::[Decorated RootSpec]
{
  return if null(rs) then [] else head(rs).parserDcls ++ collectParserSpecs(tail(rs));
}

function findTaintedParsers
[Decorated ParserSpec] ::= rs::[Decorated ParserSpec] ta::[String] a::[String]
{
  return if null(rs)
         then []
              -- tainted parsers makes use of CS of a tainted grammar.  Also taint any parsers defined in an altered grammar.
         else (if containsAny(ta, head(rs).moduleNames) || startsWithAny(a, head(rs).fullName) 
               then [head(rs)]
               else [])
              ++ findTaintedParsers(tail(rs), ta, a);
}



abstract production generateCS
top::Unit ::= specs::[Decorated ParserSpec] silvergen::String
{
  local attribute pr::IO;
  pr = print("Generating Parsers and Scanners.\n", top.ioIn);
  
  top.io = writeCSSpec(pr, silvergen ++ "src/", specs);
  top.code = 0;
  top.order = 5;
}

function writeCSSpec
IO ::= i::IO silvergen::String specs::[Decorated ParserSpec]
{
  local attribute parserName :: String;
  parserName = makeParserName(head(specs).fullName);

  local attribute printio :: IO;
  printio = print("\t[" ++ head(specs).fullName ++ "]\n", i);
  
  local attribute copperFile :: String;
  copperFile = silvergen ++ grammarToPath(hackilyFindGrammarName(head(specs).fullName)) ++ parserName ++ ".copper";

  local attribute copperBody :: String;
  copperBody = makeCopperGrammarSpec(parserName, head(specs));
 
  return if null(specs) then i else writeCSSpec(writeFile(copperFile, copperBody, printio), silvergen, tail(specs));
}

aspect production writeBuildFile
top::IOVal<String> ::= i::IO a::Decorated CmdArgs specs::[Decorated RootSpec] silverhome::String silvergen::String da::Decorated DependencyAnalysis
{
  extraTaskdefs <- ["  <taskdef name='copper' classname='edu.umn.cs.melt.copper.ant.CopperAntTask' classpathref='lib.classpath'/>\n" ];
  extraTargets <- ["  <target name='copper'>\n" ++ buildAntParserPart(
                                                                      if a.forceCopperDump then da.allParsers else da.taintedParsers
                                                                      , a) ++ "  </target>\n"];
  extraDepends <- ["copper"];
}

function buildAntParserPart
String ::= r::[Decorated ParserSpec] a::Decorated CmdArgs
{
  local attribute parserName :: String;
  parserName = makeParserName(head(r).fullName);
  
  local attribute hackgn :: String;
  hackgn = hackilyFindGrammarName(head(r).fullName);
  
  local attribute pn :: String; -- package name
  pn = makeName(hackgn);
  
  local attribute pl :: String;
  pl = grammarToPath(hackgn);

  return if null(r) then "" else( 
"    <copper fullClassName='" ++ pn ++ "." ++ parserName ++ "' inputFile='${src}/" ++ pl ++ parserName ++ ".copper' " ++ 
	"outputFile='${src}/" ++ pl ++ parserName ++ ".java' skin='xml' warnUselessNTs='no' dump='true' dumpType='html'" ++
	(if a.forceCopperDump then "" else " dumpOnlyOnError='true'") ++ " dumpFile='" ++ parserName ++ ".copperdump.html'"  ++ 
	"/>\n" ++
 	 buildAntParserPart(tail(r), a));
}

-- This exists to infer the path to output copper files to... just from the ParserSpec, without knowing what grammar it is in.
function hackilyFindGrammarName
String ::= svName::String
{
  return substring(0, length(svName) - length(last(explode(":", svName))) - 1, svName);

}
