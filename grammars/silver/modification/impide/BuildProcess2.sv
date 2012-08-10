grammar silver:modification:impide;

import silver:modification:impide:cstast;
import silver:modification:copper;
import silver:definition:concrete_syntax;
import silver:definition:concrete_syntax:ast;
import silver:driver;
import silver:translation:java;
import silver:translation:java:driver;
import silver:util:cmdargs;

aspect production run
top::RunUnit ::= iIn::IO args::[String]
{
  -- The RootSpec representing the grammar actually being built (specified on the command line)
  local builtGrammar :: [Decorated RootSpec] = getRootSpec(a.buildGrammar, depAnalysis.compiledList);
  
  -- Empty if no ide decl in that grammar, otherwise has at least one spec... note that
  -- we're going to go with assuming there's just one IDE declaration...
  local isIde :: Boolean = !null(builtGrammar) && !null(head(builtGrammar).ideSpecs);

  postOps <- if !isIde then [] else [generateNCS(grammarEnv, depAnalysis.taintedParsers, silvergen)];
}

abstract production generateNCS
top::Unit ::= grams::EnvTree<Decorated RootSpec> specs::[ParserSpec] silvergen::String
{
  local attribute pr::IO;
  pr = print("Generating Parsers and Scanners for IMP-based IDE.\n", top.ioIn);
  
  top.io = writeNCSSpec(pr, grams, silvergen ++ "src/", specs);
  top.code = 0;
  top.order = 7;
}

function writeNCSSpec
IO ::= i::IO grams::EnvTree<Decorated RootSpec> silvergen::String specs::[ParserSpec]
{
  local attribute p :: ParserSpec;
  p = head(specs);
  p.compiledGrammars = grams;
  
  local attribute ast :: SyntaxRoot;
  ast = p.cstAst;
  
  local attribute parserName :: String;
  parserName = makeParserName(p.fullName);

  local attribute copperFile :: String;
  copperFile = silvergen ++ grammarToPath(p.sourceGrammar) ++ parserName ++ "_ide.copper";

  local attribute printio :: IO;
  printio = print("\t[" ++ p.fullName ++ "]\n", i);
  
  local attribute writeio :: IO;
  writeio = writeFile(copperFile, ast.nxmlCopper, printio);
  
  return if null(specs) then i
         else writeNCSSpec(writeio, grams, silvergen, tail(specs));
}

