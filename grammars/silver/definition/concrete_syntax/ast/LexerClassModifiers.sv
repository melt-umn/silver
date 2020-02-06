grammar silver:definition:concrete_syntax:ast;

-- From TerminalModifiers
--synthesized attribute dominatesXML :: String;
--synthesized attribute submitsXML :: String;

autocopy attribute className :: String;

{--
 - Modifiers for lexer classes.
 -}
nonterminal SyntaxLexerClassModifiers with cstEnv, cstErrors, className, classTerminals, superClasses, subClasses, superClassContribs, disambiguationClasses, dominatesXML, submitsXML, containingGrammar;

abstract production consLexerClassMod
top::SyntaxLexerClassModifiers ::= h::SyntaxLexerClassModifier  t::SyntaxLexerClassModifiers
{
  top.cstErrors := h.cstErrors ++ t.cstErrors;
  top.superClassContribs = h.superClassContribs ++ t.superClassContribs;
  top.disambiguationClasses = h.disambiguationClasses ++ t.disambiguationClasses;
  top.dominatesXML = h.dominatesXML ++ t.dominatesXML;
  top.submitsXML = h.submitsXML ++ t.submitsXML;
}

abstract production nilLexerClassMod
top::SyntaxLexerClassModifiers ::= 
{
  top.cstErrors := [];
  top.superClassContribs = [];
  top.disambiguationClasses = [];
  top.dominatesXML = "";
  top.submitsXML = "";
}



{--
 - Modifiers for lexer classes.
 -}
nonterminal SyntaxLexerClassModifier with cstEnv, cstErrors, className, classTerminals, superClasses, subClasses, superClassContribs, disambiguationClasses, dominatesXML, submitsXML, containingGrammar;

{- We default ALL attributes, so we can focus only on those that are interesting in each case... -}
aspect default production
top::SyntaxLexerClassModifier ::=
{
  --top.cstErrors := [];
  top.superClassContribs = [];
  top.disambiguationClasses = [];
  top.dominatesXML = "";
  top.submitsXML = "";
}

{--
 - Other lexer classes of which this is considered a sub-class.
 -}
abstract production lexerClassExtends
top::SyntaxLexerClassModifier ::= super::[String]
{
  production superRefs :: [[Decorated SyntaxDcl]] = lookupStrings(super, top.cstEnv);

  top.cstErrors := flatMap(\ a::Pair<String [Decorated SyntaxDcl]> ->
                     if !null(a.snd) then []
                     else ["Lexer Class " ++ a.fst ++ " was referenced but " ++
                           "this grammar was not included in this parser. (Referenced from extends clause for lexer class)"],
                   zipWith(pair, super, superRefs));
  top.superClassContribs = map(pair(top.className, _), super);
}

{--
 - The submits list for the lexer class. Either lexer classes or terminals.
 -}
abstract production lexerClassSubmits
top::SyntaxLexerClassModifier ::= sub::[String]
{
  production allSubs :: [String] = unionsBy(stringEq, sub :: lookupStrings(sub, top.subClasses));
  production subRefs :: [[Decorated SyntaxDcl]] = lookupStrings(allSubs, top.cstEnv);

  top.cstErrors := flatMap(\ a::Pair<String [Decorated SyntaxDcl]> ->
                     if !null(a.snd) then []
                     else ["Terminal / Lexer Class " ++ a.fst ++ " was referenced but " ++
                           "this grammar was not included in this parser. (Referenced from submit clause for lexer class)"], --TODO: come up with a way to reference a given lexer class (line numbers would be great)
                   zipWith(pair, sub, subRefs)); 
  top.submitsXML = implode("", map(xmlCopperRef, map(head, subRefs)));
}
{--
 - The dominates list for the lexer class. Either lexer classes or terminals.
 -}
abstract production lexerClassDominates
top::SyntaxLexerClassModifier ::= dom::[String]
{
  production allDoms :: [String] = unionsBy(stringEq, dom :: lookupStrings(dom, top.subClasses));
  production domRefs :: [[Decorated SyntaxDcl]] = lookupStrings(allDoms, top.cstEnv);

  top.cstErrors := flatMap(\ a::Pair<String [Decorated SyntaxDcl]> ->
                     if !null(a.snd) then []
                     else ["Terminal / Lexer Class " ++ a.fst ++ " was referenced but " ++
                           "this grammar was not included in this parser. (Referenced from dominates clause for lexer class)"],
                   zipWith(pair, dom, domRefs));
  top.dominatesXML = implode("", map(xmlCopperRef, map(head, domRefs)));
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
""");
  -- TODO: Figure out the actual flowtype here
  syntaxDcl.cstEnv = top.cstEnv;
  syntaxDcl.containingGrammar = top.containingGrammar;

  top.cstErrors := []; -- TODO: Check for duplicate disambiguation for a lexer class
  top.disambiguationClasses = [syntaxDcl];
}
