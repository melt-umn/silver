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
 - Prefixes to inject onto terminals in the composed parser.
 -}
monoid attribute terminalPrefixes :: [Pair<String String>] with [], ++;

{--
 - Prefixes to inject onto the marking terminals of grammars in the composed parser.
 -}
monoid attribute grammarTerminalPrefixes :: [Pair<String String>] with [], ++;


abstract production parserSpec
top::ParserSpec ::=
  sl::Location  sg::String  fn::String  snt::String  grams::[String]
  customStartLayout::Maybe<[String]>
  terminalPrefixes::[Pair<String String>]  grammarTerminalPrefixes::[Pair<String String>]
  addedDcls::[SyntaxDcl]
{
  top.sourceLocation = sl;
  top.sourceGrammar = sg;
  top.fullName = fn;
  top.startNT = snt;
  top.moduleNames := grams;

  -- We've decided we're using only the grammars in this parser to compute dependencies, as opposed 
  -- to all grammars imported in the env. 
  -- This could affect which conditional imports get triggered, and thus what gets included in the parser
  production deps :: [String] = computeDependencies(grams, top.compiledGrammars);
  production med :: ModuleExportedDefs = moduleExportedDefs(sl, top.compiledGrammars, deps, grams, []);
  
  -- Compute the list of terminal prefixes for marking terminals here, because the set of marking
  -- terminals exported by a grammar depends on conditional imports that may be triggered by other
  -- grammars included in the parser.
  -- Also works around a build bug (https://github.com/melt-umn/silver/issues/36) - the grammar
  -- containing the parser spec (and thus the ParserSpec itself) does not get rebuilt when only its
  -- dependencies are modified, thus the set of marking terminals exported by a grammar when the
  -- ParserSpec is built may be out of date.
  -- componentGrammarMarkingTerminals is also needed to help determine the prefix seperator.
  production componentGrammarMarkingTerminals::[Pair<String [String]>] =
    map(
      \ g::String ->
        pair(g, 
          foldr(
            consSyntax, nilSyntax(),
            moduleExportedDefs(sl, top.compiledGrammars, deps, [g], []).syntaxAst).allMarkingTerminals),
      grams);
  production markingTerminalPrefixes::[Pair<String String>] =
    flatMap(
      \ gp::Pair<String String> ->
        map(pair(_, gp.snd), lookupBy(stringEq, gp.fst, componentGrammarMarkingTerminals).fromJust),
      grammarTerminalPrefixes);

  top.cstAst =
    cstRoot(
      fn, snt, foldr(consSyntax, nilSyntax(), addedDcls ++ med.syntaxAst),
      customStartLayout, terminalPrefixes ++ markingTerminalPrefixes, componentGrammarMarkingTerminals);
}

