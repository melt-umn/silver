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
aspect production compilation
top::Compilation ::= g::Grammars buildGrammar::String silverHome::String silverGen::String
{
  -- TODO: production because of hack!
  production allParsers :: [ParserSpec] = foldr(append, [], map((.parserSpecs), grammarsToTranslate));
  -- TODO: temporarily all parsers!
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

