grammar silver:extension:ideinterface;

import silver:driver;
import silver:util; 
import silver:util:cmdargs;

import silver:translation:java:driver; 
import silver:modification:copper;

-- needed for determining if treesitter demo flag provided
import silver:extension:treesitter;

synthesized attribute genIDEInterface :: Boolean occurs on CmdArgs;

aspect production endCmdArgs 
top::CmdArgs ::= _
{
  top.genIDEInterface = false;
}

abstract production ideInterfaceFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.noJavaGeneration = true;
  top.genIDEInterface = true;
  forwards to rest;
}

aspect function parseArgs
Either<String Decorated CmdArgs> ::= args::[String]
{
  flags <- [pair("--ide-interface", flag(ideInterfaceFlag))];
  flagdescs <- ["\t--ide-interface: Generate the interface file used for IDEs"];
}

aspect production compilation
top::Compilation ::= g::Grammars _ buildGrammar::String benv::BuildEnv
{
  local buildParsers::[ParserSpec] = obtainParserSpecs(head(keepGrammars([buildGrammar], g.grammarList)), benv);
  top.postOps <- 
    if top.config.genIDEInterface then
      [genIdeInterfaceFile(buildParsers, g.compiledGrammars, top.config.treesitterDemo)]
    else
      [];
}

abstract production genIdeInterfaceFile
top::DriverAction ::= specs::[ParserSpec] compiledGrammars::EnvTree<Decorated RootSpec> demo::Boolean
{
  local ide_interface_file :: String = "ideInterface.txt";

  local spec::ParserSpec = head(specs);
  spec.compiledGrammars = compiledGrammars;

  local specCst :: SyntaxRoot = spec.cstAst;
  specCst.demoSpec = demo;

  local ideInterface :: String = specCst.ideSyntaxRoot.serializedInterface;

  
  local err :: IO = 
    print("CST Errors while Generating Ide-Interface for Parser " ++ spec.fullName ++ ":\n" ++
      implode("\n", specCst.cstErrors) ++ "\n", top.ioIn);


  local doWR :: IO =
    writeFile(ide_interface_file, ideInterface,
      print(s"Generating IDE Interface from Parser " ++ spec.fullName ++ ".\n", top.ioIn));

  top.io =
    if null(specs)
    then print("Did not find a parser spec for which to generate an IDE interface.\n", top.ioIn)
    else if length(specs) > 1
    then print(s"Found multiple parser specs for which to generate an IDE interface: ${implode(", ", map((.fullName), specs))}.\n", top.ioIn)
    else if null(specCst.cstErrors)
    then doWR
    else err;

  top.code = if length(specs) == 1 && null(specCst.cstErrors) then 0 else 1;
  top.order = 7;
}
