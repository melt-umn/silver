grammar silver:composed:idetest;

-- This grammar is a duplicate of Default, but...

import silver:host;
import silver:host:env;
import silver:translation:java;
import silver:driver;

--import silver:extension:doc;
import silver:analysis:warnings:defs;
import silver:analysis:warnings:exporting;

import ide;

parser svParse::Root {
  silver:host;

  silver:extension:convenience;

  silver:extension:list;
  silver:extension:easyterminal;

  silver:extension:deprecation;
  silver:extension:testing;
--  silver:extension:concreteSyntaxForTrees ;
  -- doc?

  silver:modification:let_fix;
  silver:modification:collection;
  silver:modification:patternmatching;
  silver:modification:autocopyattr;
  silver:modification:ffi;
  silver:modification:typedecl;
  silver:modification:copper;
  silver:modification:defaultattr;
  
  -- slight hacks, for the moment
  silver:modification:copper_mda;
  silver:modification:impide;
}

parser sviParse::IRoot {
  silver:host:env;

  silver:modification:collection:env_parser;
  silver:modification:autocopyattr:env_parser;
  silver:modification:ffi:env_parser;
  silver:modification:typedecl:env_parser;
  silver:modification:copper:env_parser;
  
  silver:extension:list:env_parser;
}

function main 
IOVal<Integer> ::= args::[String] ioin::IO
{
  return cmdLineRun(args, svParse, sviParse, ioin);
}

-- IDE declaration block
temp_imp_ide_dcl svParse ".sv" { 
  builder analyze;          --a function whose signature must be "IOVal<[IdeMessage]> ::= args::[IdeProperty] i::IO"
  postbuilder generate;     --a function whose signature must be "IOVal<[IdeMessage]> ::= args::[IdeProperty] i::IO"
  exporter export;          --a function whose signature must be "IOVal<[IdeMessage]> ::= args::[IdeProperty] env::IdeEnv i::IO"
  property grammar_to_compile string;
  property grammar_to_include string;
};

function export
IOVal<[IdeMessage]> ::= args::[IdeProperty] env::IdeEnv i::IO
{

  --return ioval(pinfo("Export function called in Silver.", pinfo("Generated path: " ++ env.generatedPath, i)), []);
  
  local buildFile::String = env.generatedPath ++ "/build.xml";

  local grammarName::String = head(getGrammarToCompile(args));

  local jarFile::String = env.generatedPath ++ "/" ++ grammarName ++ ".jar";

  local targetFile::String = env.projectPath ++ "/" ++ grammarName ++ ".jar";

  local fileExists::IOVal<Boolean> = isFile(buildFile, i);

  local jarExists::IOVal<Boolean> = isFile(jarFile, ant(buildFile, "", "", fileExists.io));

  return if !fileExists.iovalue then ioval(perror("Export failed.", i), [makeSysIdeMessage(2, "build.xml doesn't exist. Has the project been successfully built before?")])
    else if !jarExists.iovalue then ioval(perror("Export failed.", i), [makeSysIdeMessage(2, "Ant failed to generated the jar.")])
    else ioval(copyFile(jarFile, targetFile, jarExists.io), []);
}

function generate
IOVal<[IdeMessage]> ::= args::[IdeProperty] i::IO
{

  local sargs::[String] = getArgStrings(args) ++ getGrammarToCompile(args);

  local ru :: IOVal<[IdeMessage]> = ideGenerate(sargs, svParse, sviParse, i);

  return ru;

}

function analyze
IOVal<[IdeMessage]> ::= args::[IdeProperty] i::IO
{

  local sargs::[String] = getArgStrings(args) ++ getGrammarToCompile(args);

  local ru :: IOVal<[IdeMessage]> = ideAnalyze(sargs, svParse, sviParse, i);

  return ru;
}

function getArgStrings
[String] ::= args::[IdeProperty]
{
  return
    if(null(args))
    then []
    else getArgString(head(args)) ++ getArgStrings(tail(args));
}

function getArgString
[String] ::= arg::IdeProperty
{
  return
    if arg.propName == "grammar_to_include"
    then ["-I", arg.propType, "--build-xml-location", arg.propType ++ "/bin/build.xml"]--FIXME: add new attribute propValue; now we just reuse propType (improperly)
    else [];
}

function getGrammarToCompile
[String] ::= args::[IdeProperty]
{
  return
    if(null(args))
    then []
    else if head(args).propName == "grammar_to_compile"
	    then [head(args).propType]--FIXME: add new attribute propValue; now we just reuse propType (improperly)
	    else getGrammarToCompile(tail(args));
}

