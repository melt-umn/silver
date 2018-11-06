grammar silver:extension:treesitter;

import silver:driver;
import silver:util;
import silver:util:cmdargs;

import silver:translation:java:driver;
import silver:modification:copper;

synthesized attribute treesitterLangOption :: [String] occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= _
{
  top.treesitterLangOption = [];
}

abstract production treesitterLangFlag
top::CmdArgs ::= loc::String rest::CmdArgs
{
  top.treesitterLangOption = loc :: rest.treesitterLangOption;
  top.noJavaGeneration = true;
  forwards to rest;
}

aspect function parseArgs
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [pair("--treesitter-spec", option(treesitterLangFlag))];
  flagdescs <- ["\t--treesitter-spec : the name of the language for which to generate a Tree-sitter spec"];
}

aspect production compilation
top::Compilation ::= g::Grammars  _  buildGrammar::String  benv::BuildEnv
{
  local lang::String = head(top.config.treesitterLangOption); -- TODO: check only one item for treesitterLangOption is provided
  local buildParsers::[ParserSpec] = obtainParserSpecs(head(keepGrammars([buildGrammar], g.grammarList)), benv);
  top.postOps <-
    if !null(top.config.treesitterLangOption)
    then [genTreesitterSpec(buildParsers, g.compiledGrammars, lang)]
    else [];
}

abstract production genTreesitterSpec
top::DriverAction ::= specs::[ParserSpec]  cg::EnvTree<Decorated RootSpec>  lang::String
{
  local file :: String = "grammar.js";

  local spec::ParserSpec = head(specs);
  spec.compiledGrammars = cg;

  local specCst :: SyntaxRoot = spec.cstAst;
  specCst.lang = lang;
  
  local newSpec :: String = specCst.jsTreesitter;

  local err :: IO = 
    print("CST Errors while Generating Tree-sitter Grammar for Parser " ++ spec.fullName ++ ":\n" ++
      implode("\n", specCst.cstErrors) ++ "\n", top.ioIn);
  
  local doWR :: IO =
    writeFile(file, newSpec,
      print(s"Generating Tree-sitter Grammar for ${lang} from Parser " ++ spec.fullName ++ ".\n", top.ioIn));

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

