grammar silver:extension:treesitter;
exports silver:extension:treesitter:atom;

import silver:driver;
import silver:util;
import silver:util:cmdargs;

import silver:translation:java:driver;
import silver:modification:copper;


synthesized attribute treesitterLangOption :: [String] occurs on CmdArgs;
synthesized attribute treesitterDemo :: Boolean occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= _
{
  top.treesitterLangOption = [];
  top.treesitterDemo = false;
}


abstract production treesitterLangFlag
top::CmdArgs ::= langName::String rest::CmdArgs
{
  top.treesitterLangOption = langName :: rest.treesitterLangOption;
  top.noJavaGeneration = true;
  forwards to rest;
}

abstract production treesitterDemoFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.treesitterDemo = true;
  forwards to rest;
}

aspect function parseArgs
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [
    pair("--treesitter-spec", option(treesitterLangFlag)),
    pair("--treesitter-demo", flag(treesitterDemoFlag))
   ];
  flagdescs <- [
    "\t--treesitter-spec : the name of the language for which to generate a Tree-sitter spec",
    "\t--treesitter-demo : build a Treesitter parser specifically for demos"];
}

aspect production compilation
top::Compilation ::= g::Grammars  _  buildGrammar::String  benv::BuildEnv
{
  local lang::String = head(top.config.treesitterLangOption); -- TODO: check only one item for treesitterLangOption is provided
  local buildParsers::[ParserSpec] = obtainParserSpecs(head(keepGrammars([buildGrammar], g.grammarList)), benv);
  top.postOps <-
    if !null(top.config.treesitterLangOption)
    then [genTreesitterSpec(buildParsers, g.compiledGrammars, lang, top.config.treesitterDemo)]
    else [];
}

abstract production genTreesitterSpec
top::DriverAction ::= specs::[ParserSpec]  cg::EnvTree<Decorated RootSpec>  lang::String demo::Boolean
{
  local treesitter_file :: String = "grammar.js";
  local conflicts_file :: String = "modified_copper.xml";

  local spec::ParserSpec = head(specs);
  spec.compiledGrammars = cg;

  local specCst :: SyntaxRoot = spec.cstAst;
  specCst.lang = lang;
  specCst.demoSpec = demo;
  
  local treesitterSpec :: String = specCst.jsTreesitter;
  local conflictsSpec :: String = specCst.modifiedXMLCopper;

  local err :: IO = 
    print("CST Errors while Generating Tree-sitter Grammar for Parser " ++ spec.fullName ++ ":\n" ++
      implode("\n", specCst.cstErrors) ++ "\n", top.ioIn);
  
  local doWR :: IO =
    writeFile(treesitter_file, treesitterSpec,
      writeFile(conflicts_file, conflictsSpec,
      print(s"Generating Tree-sitter Grammar for ${lang} from Parser " ++ spec.fullName ++ ".\n", top.ioIn)));

  top.io =
    if null(specs)
    then print("Did not find a parser spec for which to generate a Tree-sitter grammar.\n", top.ioIn)
    else if length(specs) > 1
    then print(s"Found multiple parser specs for which to generate a Tree-sitter grammar: ${implode(", ", map((.fullName), specs))}.\n", top.ioIn)
    else if null(specCst.cstErrors)
    then doWR 
    else err;
  
  top.code = if length(specs) == 1 && null(specCst.cstErrors) then 0 else 1;
  top.order = 7;
}
