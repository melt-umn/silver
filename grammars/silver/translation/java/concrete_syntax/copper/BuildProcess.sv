grammar silver:translation:java:concrete_syntax:copper;
import silver:translation:java:core;
import silver:translation:java:driver;
import silver:translation:java:command;
import silver:driver;

import silver:definition:core hiding grammarName;
import silver:definition:env;
import silver:definition:concrete_syntax;

import silver:util;
import silver:util:command;

aspect production run
top::RunUnit ::= iIn::IO args::String
{
  postOps <- [generateCS(depAnalysis.taintedParsers, silvergen)]; 
}

-- InterfaceUtil.sv
synthesized attribute taintedParsers:: [Decorated ParserSpec] occurs on DependencyAnalysis;

aspect production dependencyAnalysis
top::DependencyAnalysis ::= ifaces::[Decorated Interface]
{
  local attribute parserSpecs :: [Decorated ParserSpec];
  parserSpecs = collectParserSpecs(ifspecs ++ top.compiledGrammars); -- collect them from everything
  
  top.taintedParsers = findTaintedParsers(parserSpecs, taintedaltered, altered);
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
  copperFile = silvergen ++ substitute("/", ":", hackilyFindGrammarName(head(specs).fullName)) ++ "/" ++ parserName ++ ".copper";

  local attribute copperBody :: String;
  copperBody = makeCopperGrammarSpec(parserName, head(specs));
 
  return if null(specs) then i else writeCSSpec(writeFile(copperFile, copperBody, printio), silvergen, tail(specs));
}

aspect production writeBuildFile
top::IOString ::= i::IO a::Decorated Command specs::[Decorated RootSpec] silverhome::String silvergen::String da::Decorated DependencyAnalysis
{
  extraTaskdefs <- ["  <taskdef name='copper' classname='edu.umn.cs.melt.copper.ant.CopperAntTask' classpathref='lib.classpath'/>\n" ];
  extraTargets <- ["  <target name='copper'>\n" ++ buildAntParserPart(da.taintedParsers) ++ "  </target>\n"];
  extraDepends <- ["copper"];
}

function buildAntParserPart
String ::= r::[Decorated ParserSpec]{

  local attribute parserName :: String;
  parserName = makeParserName(head(r).fullName);
  
  local attribute hackgn :: String;
  hackgn = hackilyFindGrammarName(head(r).fullName);
  
  local attribute pn :: String; -- package name
  pn = substitute(".", ":", hackgn);
  
  local attribute pl :: String;
  pl = substitute("/", ":", hackgn);

  return if null(r) then "" else( 
"    <copper fullClassName='" ++ pn ++ "." ++ parserName ++ "' inputFile='${src}/" ++ pl ++ "/" ++ parserName ++ ".copper' " ++ 
	"outputFile='${src}/" ++ pl ++ "/" ++ parserName ++ ".java' skin='xml' warnUselessNTs='no'/>\n" ++
 	 buildAntParserPart(tail(r)));
}

-- This exists to infer the path to output copper files to... just from the ParserSpec, without knowing what grammar it is in.
function hackilyFindGrammarName
String ::= svName::String
{
  return substring(0, length(svName) - length(last(split(":", svName))) - 1, svName);

}
