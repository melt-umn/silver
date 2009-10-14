grammar silver:translation:java:concrete_syntax:copper;
import silver:translation:java:core;
import silver:translation:java:driver;
import silver:translation:java:command;
import silver:driver;

import silver:definition:core hiding grammarName;
import silver:definition:env;
import silver:definition:concrete_syntax;

import core;
import silver:util;
import silver:util:command;

aspect production run
top::RunUnit ::= iIn::IO args::String
{
  postOps <- [generateCS(a, grammars, silvergen)]; 
}

abstract production generateCS
top::Unit ::= a::Command specs::[Decorated RootSpec] silvergen::String
{
  local attribute pr::IO;
  pr = print("Generating Parsers and Scanners.\n", top.ioIn);
  
  top.io = writeAllCS(pr, a, specs, silvergen);
  top.code = 0;
  top.order = 5;
}

function writeAllCS
IO ::= i::IO a::Decorated Command l::[Decorated RootSpec] silvergen::String
{
  local attribute now :: IO;
  now = writeCSSpecs(i, head(l), a, silvergen);

  local attribute recurse :: IO;
  recurse = writeAllCS(now, a, tail(l), silvergen);

  return if null(l) then i else recurse;
}

-- duplicated from translation:java:driver/buildprocess
function writeCSSpecs
IO ::= i::IO r::Decorated RootSpec a::Decorated Command silvergen::String
{
  local attribute package :: String;
  package = substitute("/", ":", r.declaredName);

  production attribute specLocation :: String;
  specLocation = silvergen ++ "/src/" ++ package; 

  return writeCSSpec(i, specLocation, r.parserDcls);
}

function writeCSSpec
IO ::= i::IO l::String specs::[Decorated ParserSpec]
{
  local attribute parserName :: String;
  parserName = makeParserName(head(specs).fullName);

  local attribute copperFile :: String;
  copperFile = l ++ "/" ++ parserName ++ ".copper";

  local attribute copperBody :: String;
  copperBody = makeCopperGrammarSpec(parserName, head(specs));
 
  return if null(specs) then i else writeCSSpec(writeFile(copperFile, copperBody, i), l, tail(specs));
}

aspect production writeBuildFile
top::IOString ::= i::IO a::Decorated Command specs::[Decorated RootSpec] silverhome::String silvergen::String 
{
  extraTaskdefs <- ["  <taskdef name='copper' classname='edu.umn.cs.melt.copper.ant.CopperAntTask' classpathref='lib.classpath'/>\n" ];
  extraTargets <- ["  <target name='copper'>\n" ++ buildAntGrammarParts(specs) ++ "  </target>\n"];
  extraDepends <- ["copper"];
}

function buildAntGrammarParts
String ::= r::[Decorated RootSpec]{
  return if null(r) then "" else buildAntGrammarPart(head(r)) ++ buildAntGrammarParts(tail(r));
}


function buildAntGrammarPart
String ::= r::Decorated RootSpec {
  local attribute fName :: String;
  fName = r.declaredName;

  local attribute pkgName :: String;
  pkgName = makeName(fName);

  local attribute pkgPath :: String;
  pkgPath = substitute("/", ":", fName);

  return if null(r.parserDcls) then "" else buildAntParserPart(pkgName, pkgPath, r.parserDcls);
}

function buildAntParserPart
String ::= pn::String pl::String r::[Decorated ParserSpec]{

  local attribute parserName :: String;
  parserName = makeParserName(head(r).fullName);

  return if null(r) then "" else( 
"    <copper fullClassName='" ++ pn ++ "." ++ parserName ++ "' inputFile='${src}/" ++ pl ++ "/" ++ parserName ++ ".copper' " ++ 
	"outputFile='${src}/" ++ pl ++ "/" ++ parserName ++ ".java' skin='xml'/>\n" ++
 	 buildAntParserPart(pn, pl, tail(r)));
}
