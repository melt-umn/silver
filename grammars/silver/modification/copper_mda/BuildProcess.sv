grammar silver:modification:copper_mda;

import silver:driver;
import silver:translation:java:driver;
import silver:translation:java:core only makeParserName, makeName;

import silver:util:cmdargs;

-- TODO CRIPES this is all bad, bad, bad code.

aspect production compilation
top::Compilation ::= g::Grammars  _  buildGrammar::String  benv::BuildEnv
{
  top.postOps <- [generateMdaSpecs(g.compiledGrammars, grammarsToTranslate, benv.silverGen)]; 

  local targets :: String = mdaBuildTarget(grammarsToTranslate, benv.silverGen);
  extraTopLevelDecls <- if length(targets) != 0 then ["  <target name='copper_mda'>\n" ++ targets ++ "  </target>\n"] else [];
  extraGrammarsDeps <- if length(targets) != 0 then ["copper_mda"] else [];
}

abstract production generateMdaSpecs
top::Unit ::= grams::EnvTree<Decorated RootSpec>  searchgrams::[Decorated RootSpec]  silvergen::String
{
  top.io = generateMdaSpec(grams, searchgrams, silvergen ++ "src/", top.ioIn);
  top.code = 0;
  top.order = 5;
}

function generateMdaSpec
IO ::= grams::EnvTree<Decorated RootSpec>  searchgrams::[Decorated RootSpec]  silvergen::String  i::IO
{
  return if null(searchgrams) then i
         else if null(head(searchgrams).mdaSpecs) then generateMdaSpec(grams, tail(searchgrams), silvergen, i)
         else generateMdaSpec(grams, tail(searchgrams), silvergen, noReallyGenerateMdaSpecs(grams, head(searchgrams).mdaSpecs, silvergen, i));
}

function noReallyGenerateMdaSpecs
IO ::= grams::EnvTree<Decorated RootSpec>  specs::[MdaSpec]  silvergen::String  i::IO
{
  local attribute p :: MdaSpec;
  p = head(specs);
  p.compiledGrammars = grams;
  
  local attribute ast :: SyntaxRoot;
  ast = p.cstAst;
  
  local attribute parserName :: String;
  parserName = makeParserName(p.fullName);

  local attribute copperFile :: String;
  copperFile = silvergen ++ grammarToPath(p.sourceGrammar) ++ parserName ++ ".copper";

  local attribute printio :: IO;
  printio = print("MDA test file: " ++ p.fullName ++ "\n", i);
  
  local attribute writeio :: IO;
  writeio = writeFile(copperFile, ast.xmlCopper, printio);
  
  return if null(specs) then i
         else noReallyGenerateMdaSpecs(grams, tail(specs), silvergen, writeio);
}

function mdaBuildTarget
String ::= searchgrams::[Decorated RootSpec]  silvergen::String
{
  return if null(searchgrams) then ""
         else if null(head(searchgrams).mdaSpecs) then mdaBuildTarget(tail(searchgrams), silvergen)
         else noReallyMdaBuildTarget(head(searchgrams).mdaSpecs, silvergen) ++ mdaBuildTarget(tail(searchgrams), silvergen);
}

function noReallyMdaBuildTarget
String ::= searchgrams::[MdaSpec]  silvergen::String
{
  local attribute p :: MdaSpec;
  p = head(searchgrams);

  local attribute parserName :: String;
  parserName = makeParserName(p.fullName);
  
  local attribute packagename :: String;
  packagename = makeName(p.sourceGrammar);
  
  local attribute packagepath :: String;
  packagepath = grammarToPath(p.sourceGrammar);

  local target :: String =
"    <copper useSkin='XML' runMDA='true' warnUselessNTs='false'>\n" ++
"      <inputs file='${src}/" ++ packagepath ++ parserName ++ ".copper'/>\n    </copper>\n";

  return if null(searchgrams) then ""
         else target ++ noReallyMdaBuildTarget(tail(searchgrams), silvergen);
}

