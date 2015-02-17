grammar silver:composed:idetest;
-- This grammar is a duplicate of silver:idetest:Default, with IDE declaration block added.

import silver:host;
import silver:host:env;
import silver:translation:java;
import silver:driver;

import silver:analysis:warnings:defs;
import silver:analysis:warnings:exporting;

-- NOTE: this is needed for the correct generation of IDE, 
-- even if we just use an empty IDE declaration block.
import ide;

parser svParse::Root {
  silver:host;

  silver:extension:convenience;

  silver:extension:list;
  silver:extension:easyterminal;

  silver:extension:deprecation;
  silver:extension:testing;

  silver:extension:auto_ast;

  silver:extension:templating;
  silver:extension:patternmatching;

  silver:modification:let_fix;
  silver:modification:collection;
  silver:modification:primitivepattern;
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

-- This function is not used by IDE
function main 
IOVal<Integer> ::= args::[String] ioin::IO
{
  return cmdLineRun(args, svParse, sviParse, ioin);
}

-- IDE declaration block
temp_imp_ide_dcl svParse ".sv" { 
  builder analyze;          --a function whose signature must be "IOVal<[IdeMessage]> ::= args::[IdeProperty] env::IdeEnv i::IO"
  postbuilder generate;     --a function whose signature must be "IOVal<[IdeMessage]> ::= args::[IdeProperty] env::IdeEnv i::IO"
  exporter export;          --a function whose signature must be "IOVal<[IdeMessage]> ::= args::[IdeProperty] env::IdeEnv i::IO"
  folder fold;              --a function whose signature must be "[Location] ::= <<CST root's type>>"

  property grammar_to_compile string required display="Grammar";

  wizard new file {
    stub generator getStubForNewFile; --a function whose signature must be "String ::= args::[IdeProperty]"
    property declared_grammar string required display="Grammar";
  }

  name "Silver";
  version "0.2.2";
  resource grammars "../../../../grammars/"; -- I have "../grammars" to be explicit about what's going on here.
  resource jars "../../../../jars/"; -- ditto
}

-- Declarations of IDE functions referred in decl block.

function analyze
IOVal<[IdeMessage]> ::= args::[IdeProperty] env::IdeEnv i::IO
{
  local argio::IOVal<[String]> = getArgStrings(args, env, i);

  local ru :: IOVal<[IdeMessage]> = ideAnalyze(argio.iovalue, svParse, sviParse, env.projectPath, argio.io);

  return ru;
}

function generate
IOVal<[IdeMessage]> ::= args::[IdeProperty] env::IdeEnv i::IO
{
  local argio::IOVal<[String]> = getArgStrings(args, env, i);

  local ru :: IOVal<[IdeMessage]> = ideGenerate(argio.iovalue, svParse, sviParse, argio.io);

  return ru;

}

function export
IOVal<[IdeMessage]> ::= args::[IdeProperty] env::IdeEnv i::IO
{
  local grammarName::String = makeName(head(getGrammarToCompile(args)));
  local buildFile::String = env.generatedPath ++ "/build.xml";
  local jarFile::String = env.generatedPath ++ "/" ++ grammarName ++ ".jar";
  local targetFile::String = env.projectPath ++ "/" ++ grammarName ++ ".jar";

  local fileExists::IOVal<Boolean> = isFile(buildFile, i);

  local jarExists::IOVal<Boolean> = isFile(jarFile, ant(buildFile, "", "", fileExists.io));

  return if !fileExists.iovalue then
    ioval(fileExists.io, [makeSysIdeMessage(ideMsgLvError, "build.xml doesn't exist. Has the project been successfully built before?")])
  else if !jarExists.iovalue then
    ioval(jarExists.io, [makeSysIdeMessage(ideMsgLvError, "Ant failed to generate the jar.")])
  else
    ioval(refresh(env.projectName, copyFile(jarFile, targetFile, jarExists.io)), []);
}

function fold
[Location] ::= cst::Root
{
    return cst.foldableRanges; -- see ./Folding.sv
}

function getStubForNewFile
String ::= args::[IdeProperty]
{
    local gram :: Maybe<String> = lookupIdeProperty("declared_grammar", args);
    return if gram.isJust
    then "grammar " ++ gram.fromJust ++ ";\n\n"
    else "";
}

function getArgStrings
IOVal<[String]> ::= args::[IdeProperty] env::IdeEnv io::IO
{
  local jarsio :: IOVal<String> = getIdeResource("jars", io);
  local grammarsio :: IOVal<String> = getIdeResource("grammars", jarsio.io);
  
  local compile_args :: [String] =
    [
     "--silver-home", jarsio.iovalue ++ "..",
     "-G", env.generatedPath,
     "-I", env.projectPath,
     --"-I", grammarsio.iovalue, -- This actually get automatically added, by virtue of silver home finding grammars under it
     "--build-xml-location", env.generatedPath ++ "/build.xml"] ++
     getGrammarToCompile(args);
  
  return ioval(grammarsio.io, compile_args);
}

function getGrammarToCompile
[String] ::= args::[IdeProperty]
{
  return
    if(null(args))
    then []
    else if head(args).propName == "grammar_to_compile"
	    then [head(args).propValue]
	    else getGrammarToCompile(tail(args));
}

