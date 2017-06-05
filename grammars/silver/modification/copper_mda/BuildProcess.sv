grammar silver:modification:copper_mda;

import silver:driver;
import silver:translation:java:driver;
import silver:translation:java:core only makeParserName, makeName;

import silver:util:cmdargs;

aspect production compilation
top::Compilation ::= g::Grammars  _  buildGrammar::String  benv::BuildEnv
{
  top.postOps <-
    foldr(\ a::Decorated RootSpec b::[DriverAction] ->
      map(generateMdaSpec(g.compiledGrammars, _, benv.silverGen ++ "src/"), a.mdaSpecs) ++ b,
      [], grammarsToTranslate);

  local targets :: String = 
    foldr(\ a::Decorated RootSpec b::String ->
      foldr(\ c::MdaSpec d::String -> 
        mdaBuildSpecTarget(c, benv.silverGen) ++ d,
        "", a.mdaSpecs) ++ b,
      "", grammarsToTranslate);

  extraTopLevelDecls <- if length(targets) == 0 then []  
                        else ["  <target name='copper_mda'>\n" ++ targets ++ "  </target>\n"];
  extraGrammarsDeps <- if length(targets) == 0 then [] 
                         else ["copper_mda"];
}

abstract production generateMdaSpec
top::DriverAction ::= grams::EnvTree<Decorated RootSpec>  spec::MdaSpec  silvergen::String
{
  spec.compiledGrammars = grams;

  local ast::SyntaxRoot = spec.cstAst;
  local parserName::String = makeParserName(spec.fullName);
  local copperFile::String = silvergen ++ grammarToPath(spec.sourceGrammar) ++ parserName ++ ".copper";
  
  local err :: IO = 
    print("CST Errors while Testing MDA" ++ spec.fullName ++ ":\n" ++
      foldr(\ a::String b::String -> 
        a ++ "\n" ++ b, "", ast.cstErrors) ++
      "\n", top.ioIn);

  local printio::IO = print("MDA test file: " ++ spec.fullName ++ "\n", top.ioIn);
  local writeio::IO = writeFile(copperFile, ast.xmlCopper, printio);

  top.io = if null(ast.cstErrors) then writeio else err;
  top.code = if null(ast.cstErrors) then 0 else 1;
  top.order = 5;
}

function mdaBuildSpecTarget
String ::= spec::MdaSpec  silvergen::String
{
  return "    <copper useSkin='XML' runMDA='true' warnUselessNTs='false'>\n" ++
         "      <inputs file='${src}/" ++ grammarToPath(spec.sourceGrammar) ++ 
           makeParserName(spec.fullName) ++ ".copper'/>\n    </copper>\n";
}