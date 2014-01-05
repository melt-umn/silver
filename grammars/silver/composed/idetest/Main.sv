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

  product {
    name "SILVER";
    version "0.2.2";
  }

  option source linker on;
}

-- Declarations of IDE functions referred in decl block.
function fold
[Location] ::= cst::Root
{
    return   
      case cst of
        root(_, moduleStmts, importStmts, agDcls) -> [importStmts.location] ++ agDcls.foldableRanges -- see ./Folding.sv
        | _ -> []
      end;
}

function export
IOVal<[IdeMessage]> ::= args::[IdeProperty] env::IdeEnv i::IO
{
  local buildFile::String = getBuildXmlPath(env);

  local grammarQName::String = head(getGrammarToCompile(args));

  local grammarName::String = substitute(":", ".", grammarQName);

  local jarFile::String = env.generatedPath ++ "/" ++ grammarName ++ ".jar";

  local targetFile::String = env.projectPath ++ "/" ++ grammarName ++ ".jar";

  local fileExists::IOVal<Boolean> = isFile(buildFile, i);

  local jarExists::IOVal<Boolean> = isFile(jarFile, ant(buildFile, "", "", fileExists.io));

  return if !fileExists.iovalue then ioval(perror("Export failed.", i), [makeSysIdeMessage(ideMsgLvError, "build.xml doesn't exist. Has the project been successfully built before?")])
    else if !jarExists.iovalue then ioval(perror("Export failed.", i), [makeSysIdeMessage(ideMsgLvError, "Ant failed to generate the jar.")])
    else ioval(refresh(env.projectName, ideDepthOne, copyFile(jarFile, targetFile, jarExists.io)), []);
}

function generate
IOVal<[IdeMessage]> ::= args::[IdeProperty] env::IdeEnv i::IO
{
  local argio::IOVal<[String]> = getArgStrings(env, i);

  local sargs::[String] = argio.iovalue ++ getGrammarToCompile(args);

  local ru :: IOVal<[IdeMessage]> = ideGenerate(sargs, svParse, sviParse, argio.io);

  return ru;

}

function analyze
IOVal<[IdeMessage]> ::= args::[IdeProperty] env::IdeEnv i::IO
{
  local argio::IOVal<[String]> = getArgStrings(env, i);

  local sargs::[String] = argio.iovalue ++ getGrammarToCompile(args);

  local ru :: IOVal<[IdeMessage]> = ideAnalyze(sargs, svParse, sviParse, env.projectPath, argio.io);

  return ru;
}

function getArgStrings
IOVal<[String]> ::= env::IdeEnv io::IO
{
  -- get paths of linked source folders
  local pmembers::IOVal<Maybe<[IdeResource]>> = getProjectMembers(env.project, io);
  local subres::[IdeResource] = if pmembers.iovalue.isJust then pmembers.iovalue.fromJust else [];
  local paths::IOVal<[String]> = collectLinkedPaths(subres, ["-I", env.projectPath], pmembers.io);

  return ioval(paths.io, paths.iovalue ++ ["--build-xml-location", getBuildXmlPath(env)]);
}

function collectLinkedPaths 
IOVal<[String]> ::= res::[IdeResource] paths::[String] i::IO
{
    local hd :: IdeResource = head(res);

    local absPath::IOVal<String> = getAbsolutePath(hd, i);
    local isFolder::IOVal<Boolean> = resourceIsFolder(hd, absPath.io);
    local isLinked::IOVal<Boolean> = resourceIsLinked(hd, isFolder.io);

    local paths2::[String] = paths ++ 
            if (isFolder.iovalue && isLinked.iovalue) 
            then ["-I", absPath.iovalue]
            else [];

    return if null(res) 
           then ioval(i, paths) 
           else collectLinkedPaths(tail(res), paths2, isLinked.io);
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

function getBuildXmlPath
String ::= env::IdeEnv
{
  return env.generatedPath ++ "/build.xml";
}

