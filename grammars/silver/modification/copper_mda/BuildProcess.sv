grammar silver:modification:copper_mda;

import silver:driver;
import silver:translation:java:driver;
import silver:translation:java:core only makeParserName, makeName;

import silver:util:cmdargs;

aspect production compilation
top::Compilation ::= g::Grammars  _  buildGrammar::String  benv::BuildEnv
{
  top.postOps <- map(generateMdaSpec(g.compiledGrammars, _, benv.silverGen ++ "src/"),
    flatMap((.mdaSpecs), grammarsToTranslate));

  -- TODO: consider examining all grammars, not just grammarsToTranslate?
  -- I believe this choice was originally because we weren't serializing MdaSpecs to
  -- interface files, but I think we could easily start doing that new with the new serialization code?
  local targets :: [MdaSpec] = flatMap((.mdaSpecs), grammarsToTranslate);

  extraTopLevelDecls <-
    if null(targets) then []
    else ["  <target name='copper_mda'>\n" ++ sflatMap(mdaBuildSpecTarget, targets) ++ "  </target>\n"];
  -- By adding the dependency here, the MDA check happens right after parsers are built normally.
  extraGrammarsDeps <-
    if null(targets) then [] else ["copper_mda"];
  -- By *also* adding it here, we do MDA checks even if --dont-translate is active
  -- (that is, even if the `grammars` target isn't built.)
  -- (don't worry: ant doesn't re-run the target.)
  extraDistDeps <-
    if null(targets) then [] else ["copper_mda"];
}

abstract production generateMdaSpec
top::DriverAction ::= grams::EnvTree<Decorated RootSpec>  spec::MdaSpec  silvergen::String
{
  spec.compiledGrammars = grams;

  local ast :: SyntaxRoot = spec.cstAst;
  local parserName :: String = makeParserName(spec.fullName);
  local dir :: String = silvergen ++ grammarToPath(spec.sourceGrammar);
  local copperFile :: String = dir ++ parserName ++ ".copper";
  
  local err :: IO = 
    print("CST errors while testing MDA " ++ spec.fullName ++ ":\n" ++
      foldr(\ a::String b::String -> 
        a ++ "\n" ++ b, "", ast.cstErrors) ++
      "\n", top.ioIn);

  local printio::IO = print("MDA test file: " ++ spec.fullName ++ "\n", top.ioIn);
  local writeio::IO =
    writeFile(copperFile, ast.xmlCopper,
      -- hack for when we're --dont-translate'ing, make sure the dir exists.
      mkdir(dir, printio).io);

  top.io = if null(ast.cstErrors) then writeio else err;
  top.code = if null(ast.cstErrors) then 0 else 1;
  top.order = 5;
}

function mdaBuildSpecTarget
String ::= spec::MdaSpec
{
  return "    <copper useSkin='XML' runMDA='true' warnUselessNTs='false'>\n" ++
         "      <inputs file='${src}/" ++ grammarToPath(spec.sourceGrammar) ++ 
           makeParserName(spec.fullName) ++ ".copper'/>\n    </copper>\n";
}
