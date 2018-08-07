grammar silver:definition:concrete_syntax;

import silver:driver:util only computeDependencies; -- TODO this is a bad dependency!!

{--
 - An abstract representation of a parser declaration.
 -}
nonterminal ParserSpec with 
  sourceGrammar, sourceLocation, fullName,
  compiledGrammars,
  cstAst, startNT, moduleNames;

{--
 - Given compiledGrammars, gives back the SyntaxRoot representing this parser.
 -}
synthesized attribute cstAst :: SyntaxRoot;

{--
 - The full name of the start nonterminal of this parser spec.
 -}
synthesized attribute startNT :: String;

{--
 - Prefixes to inject onto marking terminals in the composed parser.
 -}
synthesized attribute terminalPrefixes :: [Pair<String String>];


abstract production parserSpec
top::ParserSpec ::= sl::Location  sg::String  fn::String  snt::String  grams::[String]  terminalPrefixes::[Pair<String String>] addedDcls::[SyntaxDcl]
{
  top.sourceLocation = sl;
  top.sourceGrammar = sg;
  top.fullName = fn;
  top.startNT = snt;
  top.moduleNames = grams;

  -- We've decided we're using only the grammars in this parser to compute dependencies, as opposed 
  -- to all grammars imported in the env. 
  -- This could affect which conditional imports get triggered, and thus what gets included in the parser
  production med :: ModuleExportedDefs =
    moduleExportedDefs(sl, top.compiledGrammars, computeDependencies(grams, top.compiledGrammars), grams, []);

  top.cstAst = cstRoot(fn, snt, foldr(consSyntax, nilSyntax(), addedDcls ++ med.syntaxAst), terminalPrefixes);
  
  local decomposedTerminalPrefixes :: Pair<[String] [String]> =
    unzipPairs(terminalPrefixes);
}

