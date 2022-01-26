grammar silver:compiler:composed:idetest;

import silver:compiler:definition:env;
import silver:compiler:host;
import silver:util:cmdargs;
import silver:util:treemap as tm;

-- NOTE: this is needed for the correct generation of IDE, 
-- even if we just use an empty IDE declaration block.
import ide;

-- Just re-use these parser declarations, instead of duplicating them here.
import silver:compiler:composed:Default only svParse;

-- This function is not used by IDE
function main 
IOVal<Integer> ::= args::[String] ioin::IOToken
{
  return cmdLineRun(args, svParse, ioin);
}

-- IDE declaration block
temp_imp_ide_dcl svParse ".sv" { 
  builder analyze;
  postbuilder generate;
  exporter export;
  folder fold;

  property grammar_to_compile string required display="Grammar";
  property enable_mwda string default="false" display="Enable MWDA";

  wizard new file {
    stub generator getStubForNewFile; --a function whose signature must be "String ::= args::[IdeProperty]"
    property declared_grammar string required display="Grammar";
  }

  name "Silver";
  version "0.2.3";
  resource grammars "../../../../../grammars/"; -- I have "../grammars" to be explicit about what's going on here.
  resource jars     "../../../../../jars/";
}

-- Declarations of IDE functions referred in decl block.

function analyze
IOVal<[Message]> ::= project::IdeProject  args::[IdeProperty]  i::IOToken
{
  local argio :: IOVal<[String]> = getArgStrings(args, project, i);

  local ru :: IOVal<[Message]> = ideAnalyze(argio.iovalue, svParse, argio.io);

  return ru;
}

function generate
IOVal<[Message]> ::= project::IdeProject  args::[IdeProperty]  i::IOToken
{
  local argio :: IOVal<[String]> = getArgStrings(args, project, i);

  local ru :: IOVal<[Message]> = ideGenerate(argio.iovalue, svParse, argio.io);

  return ru;

}

global system_location :: Location = loc("", -1, -1, -1, -1, -1, -1);

function export
IOVal<[Message]> ::= project::IdeProject  args::[IdeProperty]  i::IOToken
{
  local proj_path :: IOVal<String> = getProjectPath(project, i);
  local gen_path :: IOVal<String> = getGeneratedPath(project, proj_path.io);

  local pkgName :: String = makeName(head(getGrammarToCompile(args)));
  local buildFile :: String = gen_path.iovalue ++ "/build.xml";
  local jarFile :: String = gen_path.iovalue ++ "/" ++ pkgName ++ ".jar";
  local targetFile :: String = proj_path.iovalue ++ "/" ++ pkgName ++ ".jar";

  local fileExists :: IOVal<Boolean> = isFileT(buildFile, gen_path.io);

  local jarExists :: IOVal<Boolean> = isFileT(jarFile, ant(buildFile, "", "", fileExists.io));

  return if !fileExists.iovalue then
    ioval(fileExists.io, [err(system_location, "build.xml doesn't exist. Has the project been successfully built before?")])
  else if !jarExists.iovalue then
    ioval(jarExists.io, [err(system_location, "Ant failed to generate the jar.")])
  else
    ioval(refreshProject(project, copyFileT(jarFile, targetFile, jarExists.io)), []);
}

function fold
[Location] ::= cst::Root
{
  -- Dummy values
  cst.config = decorate errorCmdArgs("") with {};
  cst.compiledGrammars = tm:empty();
  cst.grammarName = "";
  cst.env = emptyEnv();
  cst.globalImports = emptyEnv();
  cst.grammarDependencies = [];
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
IOVal<[String]> ::= args::[IdeProperty] project::IdeProject io::IOToken
{
  local jarsio :: IOVal<String> = getIdeResource("jars", io);
  local grammarsio :: IOVal<String> = getIdeResource("grammars", jarsio.io);
  local proj_path :: IOVal<String> = getProjectPath(project, grammarsio.io);
  local gen_path :: IOVal<String> = getGeneratedPath(project, proj_path.io);
  
  local compile_args :: [String] =
    [
     "--silver-home", jarsio.iovalue ++ "..",
     "-G", gen_path.iovalue,
     "-I", proj_path.iovalue,
     --"-I", grammarsio.iovalue, -- This actually get automatically added, by virtue of silver home finding grammars under it
     "--build-xml-location", gen_path.iovalue ++ "/build.xml"] ++
     (if getEnableMWDA(args) then ["--warn-all"] else []) ++
     getGrammarToCompile(args);
  
  return ioval(gen_path.io, compile_args);
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

function getEnableMWDA
Boolean ::= args::[IdeProperty]
{
  return
    if(null(args))
    then false
    else if head(args).propName == "enable_mwda"
	    then head(args).propValue == "true"
	    else getEnableMWDA(tail(args));
}

