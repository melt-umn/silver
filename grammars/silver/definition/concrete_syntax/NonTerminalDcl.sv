grammar silver:definition:concrete_syntax;

aspect production nonterminalDcl
top::AGDcl ::= cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeExprs nm::NonterminalModifiers ';'
{
  -- TODO: We are building this for every nonterminal declaration, when it should
  -- be the same for all nonterminals in the grammar
  local med :: ModuleExportedDefs =
    moduleExportedDefs(top.location, top.compiledGrammars, top.grammarDependencies, [top.grammarName], []);
  local syntax::Syntax = foldr(consSyntax, nilSyntax(), med.syntaxAst);
  syntax.containingGrammar = error("This shouldn't be needed...");
  syntax.cstEnv = error("This shouldn't be needed...");
  syntax.cstNTProds = error("This shouldn't be needed...");
  syntax.classTerminals = error("This shouldn't be needed...");
  syntax.parserAttributeAspects = error("This shouldn't be needed...");
  syntax.layoutTerms = error("This shouldn't be needed...");
  syntax.prefixesForTerminals = error("This shouldn't be needed...");
  syntax.superClasses = error("This shouldn't be needed...");
  syntax.subClasses = error("This shouldn't be needed...");
  
  local exportedLayoutTerms::[String] = syntax.allIgnoreTerminals;
  local exportedProds::[String] = map((.fullName), syntax.allProductions);
  
  top.syntaxAst :=
    [syntaxNonterminal(
      nonterminalType(fName, tl.types), nilSyntax(),
      exportedProds, exportedLayoutTerms,
      foldr(consNonterminalMod, nilNonterminalMod(), nm.nonterminalModifiers))];
}

monoid attribute nonterminalModifiers :: [SyntaxNonterminalModifier] with [], ++;
attribute nonterminalModifiers occurs on NonterminalModifiers, NonterminalModifierList, NonterminalModifier;
propagate nonterminalModifiers on NonterminalModifiers, NonterminalModifierList;
