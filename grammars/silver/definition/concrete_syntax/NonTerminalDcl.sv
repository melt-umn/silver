grammar silver:definition:concrete_syntax;

import silver:driver only noOrigins, forceOrigins;

aspect production noWrapperNonterminalDcl
top::AGDcl ::= quals::NTDeclQualifiers 'nonterminal' id::Name tl::BracketedOptTypeExprs nm::NonterminalModifiers ';'
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
  
  production isThisTracked::Boolean = top.config.forceOrigins || ((!top.config.noOrigins) && quals.tracked);
  local exportedLayoutTerms::[String] = map((.fullName), syntax.allIgnoreTerminals);
  local exportedProds::[String] = map((.fullName), syntax.allProductions);
  
  top.syntaxAst =
    [syntaxNonterminal(
      nonterminalType(fName, tl.types, isThisTracked), nilSyntax(),
      exportedProds, exportedLayoutTerms,
      foldr(consNonterminalMod, nilNonterminalMod(), nm.nonterminalModifiers))];
}

synthesized attribute nonterminalModifiers :: [SyntaxNonterminalModifier] occurs on NonterminalModifiers, NonterminalModifierList, NonterminalModifier;

aspect production nonterminalModifiersNone
top::NonterminalModifiers ::=
{
  top.nonterminalModifiers = [];
}
aspect production nonterminalModifierSome
top::NonterminalModifiers ::= nm::NonterminalModifierList
{
  top.nonterminalModifiers = nm.nonterminalModifiers;
}

aspect production nonterminalModifierSingle
top::NonterminalModifierList ::= nm::NonterminalModifier
{
  top.nonterminalModifiers = nm.nonterminalModifiers;
}
aspect production nonterminalModifiersCons
top::NonterminalModifierList ::= h::NonterminalModifier ',' t::NonterminalModifierList
{
  top.nonterminalModifiers = h.nonterminalModifiers ++ t.nonterminalModifiers;
}