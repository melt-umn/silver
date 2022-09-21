grammar silver:compiler:definition:concrete_syntax:ast;

import silver:util:treemap as tm;

-- From TerminalModifiers
-- monoid attribute dominates_ :: [Decorated SyntaxDcl];
-- monoid attribute submits_ :: [Decorated SyntaxDcl];
-- synthesized attribute prefixSeperator :: Maybe<String>;

inherited attribute className :: String;

{--
 - Modifiers for lexer classes.
 -}
nonterminal SyntaxLexerClassModifiers with compareTo, isEqual, cstEnv, cstErrors, className, classTerminals, superClasses, subClasses, superClassContribs, disambiguationClasses, prefixSeperator, containingGrammar, dominates_, submits_;

propagate compareTo, isEqual, cstErrors, superClassContribs, disambiguationClasses, prefixSeperator, dominates_, submits_
  on SyntaxLexerClassModifiers;

abstract production consLexerClassMod
top::SyntaxLexerClassModifiers ::= h::SyntaxLexerClassModifier  t::SyntaxLexerClassModifiers
{
  top.cstErrors <-
    if h.prefixSeperator.isJust && t.prefixSeperator.isJust
    then ["Multiple prefix separators for class " ++ top.className]
    else [];
}

abstract production nilLexerClassMod
top::SyntaxLexerClassModifiers ::= 
{}



{--
 - Modifiers for lexer classes.
 -}
closed nonterminal SyntaxLexerClassModifier with location, sourceGrammar, compareTo, isEqual, cstEnv, cstErrors, className, classTerminals, superClasses, subClasses, superClassContribs, disambiguationClasses, prefixSeperator, containingGrammar, dominates_, submits_;

propagate compareTo, isEqual on SyntaxLexerClassModifier;

{- We default ALL attributes, so we can focus only on those that are interesting in each case... -}
aspect default production
top::SyntaxLexerClassModifier ::=
{
  -- Empty values as defaults
  propagate cstErrors, superClassContribs, disambiguationClasses, dominates_, submits_, prefixSeperator;
}

{--
 - Other lexer classes of which this is considered a sub-class.
 -}
abstract production lexerClassExtends
top::SyntaxLexerClassModifier ::= super::[String]
{
  -- Lexer classes not included in this parser are ignored, so library-defined
  -- lexer classes can be optionally used without requring the library to be
  -- included in the parser.  See https://github.com/melt-umn/silver/issues/694
  production superRefs :: [Decorated SyntaxDcl] = concat(lookupStrings(super, top.cstEnv));

  top.superClassContribs := map(pair(top.className, _), map((.fullName), superRefs));
}

{--
 - The submits list for the lexer class. Either lexer classes or terminals.
 -}
abstract production lexerClassSubmits
top::SyntaxLexerClassModifier ::= sub::[String]
{
  production allSubs :: [String] = unions(sub :: lookupStrings(sub, top.subClasses));
  production subRefs :: [[Decorated SyntaxDcl]] = lookupStrings(allSubs, top.cstEnv);

  top.cstErrors := flatMap(\ a::Pair<String [Decorated SyntaxDcl]> ->
                     if !null(a.snd) then []
                     else ["Terminal / Lexer Class " ++ a.fst ++ " was referenced but " ++
                           "this grammar was not included in this parser. (Referenced from submit clause for lexer class)"], --TODO: come up with a way to reference a given lexer class (line numbers would be great)
                   zipWith(pair, sub, subRefs)); 
  
  top.submits_ := map(head, subRefs);
}
{--
 - The dominates list for the lexer class. Either lexer classes or terminals.
 -}
abstract production lexerClassDominates
top::SyntaxLexerClassModifier ::= dom::[String]
{
  production allDoms :: [String] = unions(dom :: lookupStrings(dom, top.subClasses));
  production domRefs :: [[Decorated SyntaxDcl]] = lookupStrings(allDoms, top.cstEnv);

  top.cstErrors := flatMap(\ a::Pair<String [Decorated SyntaxDcl]> ->
                     if !null(a.snd) then []
                     else ["Terminal / Lexer Class " ++ a.fst ++ " was referenced but " ++
                           "this grammar was not included in this parser. (Referenced from dominates clause for lexer class)"],
                   zipWith(pair, dom, domRefs));

  top.dominates_ := map(head, domRefs);
}

{--
 - A disambiguation function that should be created for the members of a lexer class.
 -}
abstract production lexerClassDisambiguate
top::SyntaxLexerClassModifier ::= acode::String
{
  production terms :: [String] = searchEnvTree(top.className, top.classTerminals);
  production funName::String = s"disambiguate_${makeCopperName(top.className)}";
  
  production syntaxDcl::SyntaxDcl =
    syntaxDisambiguationGroup(funName, terms, true, s"""
common.ConsCell tempShiftableList = common.ConsCell.nil;
for (int i = nextMember(0, shiftable); i >= 0; i = nextMember(i+1, shiftable)) {
	tempShiftableList = new common.ConsCell(i, tempShiftableList);
}
final common.ConsCell shiftableList = tempShiftableList;
${acode}
""", location=top.location, sourceGrammar=top.sourceGrammar);
  syntaxDcl.cstEnv = top.cstEnv;
  syntaxDcl.containingGrammar = top.containingGrammar;
  syntaxDcl.classTerminals = top.classTerminals;
  syntaxDcl.superClasses = top.superClasses;
  syntaxDcl.subClasses = top.subClasses;

  -- These are required by the flow type of xmlCopper,
  -- but aren't really required by the syntaxDisambiguationGroup production.
  syntaxDcl.parserAttributeAspects = tm:empty();
  syntaxDcl.layoutTerms = tm:empty();
  syntaxDcl.prefixesForTerminals = tm:empty();
  syntaxDcl.componentGrammarMarkingTerminals = tm:empty();
  syntaxDcl.prettyNames = tm:empty();

  -- TODO: Check for duplicate disambiguation for a lexer class
  
  top.disambiguationClasses := [syntaxDcl];
}

{--
 - The default prefix separator for the members of a lexer class.
 -}
abstract production lexerClassPrefixSeperator
top::SyntaxLexerClassModifier ::= sep::String
{
  top.cstErrors := [];
  top.prefixSeperator := just(sep);
}
