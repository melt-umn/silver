grammar silver:extension:treesitter;

imports silver:definition:env;
imports silver:definition:core;
imports silver:definition:type;
imports silver:definition:concrete_syntax;
imports silver:definition:concrete_syntax:ast;
imports silver:definition:regex;

{-- Throughout the code the following abbreviations mean
  ts : Treesitter
--}
synthesized attribute lexer_classes :: [String];
attribute lexer_classes occurs on SyntaxTerminalModifiers, SyntaxTerminalModifier, SyntaxDcl;

-- these two are combined to get terminal/atom names for the scopes of the atom package
synthesized attribute terminalLexerClassesEnv :: [Pair<String String>] occurs on Syntax;

function getAtomNamesForTerminals
[Pair<String String>] ::= lexerClassAtomNameEnv::[Pair<String String>] terminalLexerClassesEnv::[Pair<String String>]
{
  local attribute atomNamesForAllTerminals :: [Pair<String Maybe<String>>] =
    map(getAtomNameForTerminal(lexerClassAtomNameEnv, _), terminalLexerClassesEnv);

  return map(getJustFromSnd, filter(lookupKeyHasValue, atomNamesForAllTerminals));
}

function getAtomNameForTerminal
Pair<String Maybe<String>> ::= lexerClassAtomNameEnv::[Pair<String String>] terminalAndLexerClass::Pair<String String>
{
  return pair(
    terminalAndLexerClass.fst, 
    lookupBy(stringEq, terminalAndLexerClass.snd, lexerClassAtomNameEnv));
}

function getAtomScopeName
String ::= terminalAndAtomName::Pair<String String>
{
  return s"""'${terminalAndAtomName.fst}': '${terminalAndAtomName.snd}'""";
}

function getModifiedCopperXML
String ::= parsername::String startnt::String s::Syntax conflicts::[Pair<String [String]>] terminalPrefixes::[Pair<String String>]
{
  local modifiedGrammarForConflicts :: Syntax = createModifiedGrammar(s, conflicts);
  local modRoot :: SyntaxRoot = cstRoot("ModifiedGrammarForTreesitter", startnt, modifiedGrammarForConflicts, terminalPrefixes);
  return modRoot.xmlCopper;
}

{-- ASPECT PRODUCTIONS --}
aspect production cstRoot
top::SyntaxRoot ::= parsername::String  startnt::String  s::Syntax  terminalPrefixes::[Pair<String String>]
{

  top.modifiedXMLCopper = 
    -- used so we don't infinitely recurse
    if stringEq(parsername, "ModifiedGrammarForTreesitter") then
      ""
    else
      getModifiedCopperXML(parsername, startnt, s2, s2.terminalConflicts, terminalPrefixes);

  -- the 'normalized' version from production attribute 's2'.  This groups productions with
  -- the same left hand side together as subdcls on nonterminals.

  -- the empty string environment keeps track of which nonterminals can produce
  -- the empty string. This is used to modify the grammar such that only the
  -- nonterminal that can produce the empty string is the start nonterminal.
  -- This is required for Treesitter grammars.
  -- The environment is a list of nonterminal rule, true/false pairs where
  -- true means the nonterminal can produce the emptty string.

  -- transform the grammar to remove nonterminals that can produce the empty string
  local attribute transformed_nts :: [Pair<String [[String]]>] =
    transformEmptyStringRules(s2.ts_nonterminal_rules);

  local attribute ts_startnt :: String = toTsDeclaration(startnt);

  -- add precedence and associativity information
  -- this function also collapses the list of input elements for the production
  -- into a valid treesitter string
  local attribute nonterminal_rules :: [String] =
    map(nonterminal_to_TS_string(s2.ts_prec_assoc_env, _), transformed_nts);

  -- partition the grammar rules so that the start nonterminal is the first rule
  -- listed in the tree-sitter grammar as needed.
  local attribute nonterminals_split :: Pair<[String] [String]> =
    partition(isStartNonTerminalRule(ts_startnt, _), nonterminal_rules);
  local attribute start_rule :: [String] = nonterminals_split.fst;
  local attribute rest_rules :: [String] = nonterminals_split.snd;

  -- The tree sitter grammar.
  top.jsTreesitter =

s"""
module.exports = grammar({
  ${implode("\n", map(implode(",", _), (map(snd, s2.terminalConflicts))))}


  name: '${top.lang}',

  extras: $$ => [
    ${s2.ts_extras}
  ],

  conflicts: $$ => [
    ${s2.ts_conflicts}
  ],

  rules: {
    ${implode(",\n    ", start_rule)},

    ${implode(",\n    ", rest_rules)},

    ${s2.ts_terminal_rules}
  }
});
""" ;

  -- The atom package file
  {--
  top.csonAtomPackage =
s"""
name: '${top.lang}'
scopeName: 'source.${top.lang}'
type: 'tree-sitter'
parser: 'tree-sitter-${top.lang}'
fileTypes: [
  '${top.lang}'
]
scopes:
""";
--}
}

function isStartNonTerminalRule
Boolean ::= startnt::String rule::String
{
  return startsWith(startnt, rule);
}

{--
 - The name of the language specified by this Tree-sitter grammar.
 -}
inherited attribute lang :: String occurs on SyntaxRoot;

{--
 - Translation of a CST AST to Tree-sitter Javascript.
 -}
synthesized attribute jsTreesitter :: String occurs on SyntaxRoot;
synthesized attribute modifiedXMLCopper :: String occurs on SyntaxRoot;
-- TODO: Why is SyntaxRoot closed?  Needs a default.
aspect default production
top::SyntaxRoot ::=
{
  top.jsTreesitter = error("Shouldn't happen");
  top.modifiedXMLCopper = error("Shouldn't happen");
}


{-- EMPTY STRING NONTERMINALS GRAMMAR MODIFICATION FUNCTIONS --}

{--
  Transforms the grammar so that no nonterminals can produce the empty string
  @param oldGrammar the original grammar
  @return the new grammar with no nonterminals that can produce the empty string
--}
function transformEmptyStringRules
[Pair<String [[String]]>] ::= oldGrammar::[Pair<String [[String]]>]
{
  local attribute emptyStringEnv :: [Pair<String Boolean>] =
    map(buildEmptyStringEnvEntry, oldGrammar);
  local attribute emptyStringRule::Maybe<String> =
    getFirstEmptyStringRule(emptyStringEnv);
  return
    -- if any nonterminal can produce the empty string
    if emptyStringRule.isJust then
        transformEmptyStringRules(
          emptyStringGrammarModification(emptyStringRule.fromJust, oldGrammar))
    else oldGrammar;
}

{--
  Modifies the oldGrammar so that the emptyRule can no longer produce the empty
  string but the grammar is equivalent
  @param emptyRule The rule that can produce the empty string.
    This should be given as treesitter declaration (e.g. List_c not $.List_c)
  @return the modified grammar. 
--}
function emptyStringGrammarModification
[Pair<String [[String]]>] ::= emptyRule::String oldGrammar::[Pair<String [[String]]>]
{
  return
    if (null(oldGrammar)) then []
    -- remove the empty productions from the rule
    else if stringEq(fst(head(oldGrammar)), emptyRule) then
      pair(emptyRule, refactorEmptyRule(TsDeclarationToIdentifier(emptyRule), removeEmptyLists(snd(head(oldGrammar)))))
      :: emptyStringGrammarModification(emptyRule, tail(oldGrammar))
    else
      pair(fst(head(oldGrammar)), refactorEmptyRule(TsDeclarationToIdentifier(emptyRule), snd(head(oldGrammar))))
      :: emptyStringGrammarModification(emptyRule, tail(oldGrammar));
}

{--
  @param emptyRule The rule that can produce the empty string.
    This should be given as a treesitter identifier (e.g. $.List_c not List_c)
  @param oldProductions The old productions of the nonterminal

  @return the new productions for the nonterminal adding productions so that
    all productions that have elements that can produce the empty string are
    removed in a newly added production.
    e.g. if old productions were [ [$.notEmpty, $.canBeEmpty] ] the function
      would return [ [$.notEmpty], [$.notEmpty, $.canBeEmpty] ]
--}
function refactorEmptyRule
[[String]] ::= emptyRule::String oldProductions::[[String]]
{
  return
    if null(oldProductions) then []
    else if containsBy(stringEq, emptyRule, head(oldProductions)) then
      append(
        accountForEmptyRuleWithinProd(emptyRule, head(oldProductions)),
        refactorEmptyRule(emptyRule, tail(oldProductions)))
    else head(oldProductions) :: refactorEmptyRule(emptyRule, tail(oldProductions));
}

{--
  Creates a list of new productions from an original production where every
  instance of the emptyRule in the production is both included and excluded
  @param emptyRule The rule that can produce the empty string.
    This should be given as a treesitter identifier (e.g. $.List_c not List_c)
  @param prod The production - the list of inputs to the production
  @return The list of new productions that are equivalent to the original production
  assuming the rule that used to be able to produce the empty string no longer can.
--}
function accountForEmptyRuleWithinProd
[[String]] ::= emptyRule::String prod::[String]
{
  return
    if null(prod) then [ [] ]
    else if (stringEq(emptyRule, head(prod))) then
      -- split by including the empty rule and excluding the empty rule
      append(
        consToAll(head(prod), accountForEmptyRuleWithinProd(emptyRule, tail(prod))),
        accountForEmptyRuleWithinProd(emptyRule, tail(prod)))
    else
      consToAll(head(prod), accountForEmptyRuleWithinProd(emptyRule, tail(prod)));
}
{--
  - Builds an entry in the empty string environment.
  @param non_terminal The nonterminal for which the entry in the environment corresponds to
  @return A pair specifying the nonterminal name and a Boolean indicating if
    it can produce the empty string.
--}
function buildEmptyStringEnvEntry
Pair<String Boolean> ::= non_terminal::Pair<String [[String]]>
{
  -- emptyListWithinListOfLists is equivalent to determining if a nonterminal
  -- can produce the empty string
  return pair(fst(non_terminal), emptyListWithinListOfLists(snd(non_terminal)));
}

{--
  Checks if any nonterminal can produce the empty string
  @param emptyStringEnv The empty string environment
  @return true if any nonterminal
--}
function hasEmptyStringRules
Boolean ::= emptyStringEnv::[Pair<String Boolean>]
{
  return any(map(snd, emptyStringEnv));
}

{--
  Gets the first nonterminal in the empty string environment list that can produce
  the empty string if one exists.
  @param emptyStringEnv The empty string environment
  @return The first nonterminal in the empty string environment that produces
    the empty string if it exists.
--}
function getFirstEmptyStringRule
Maybe<String> ::= emptyStringEnv::[Pair<String Boolean>]
{
  return
    if null(emptyStringEnv) then nothing()
    else if snd(head(emptyStringEnv)) then just(fst(head(emptyStringEnv)))
    else getFirstEmptyStringRule(tail(emptyStringEnv));
}

{-- GETTER FUNCTIONS --}
-- creates a pair of the precedence and associativity from the terminal modifiers
function getPrecAssocInfo
Pair<Integer String> ::= modifiers::SyntaxTerminalModifiers
{
  return
  if (modifiers.opAssociation.isJust && modifiers.opPrecedence.isJust) then
    pair(modifiers.opPrecedence.fromJust, modifiers.opAssociation.fromJust)
  else if modifiers.opAssociation.isJust then -- no precedence specified
    pair(0, modifiers.opAssociation.fromJust)
  else if modifiers.opPrecedence.isJust then -- no associativity specified
    pair(modifiers.opPrecedence.fromJust, "none")
  else  -- no precedence or associativity specified
    pair(0, "none");
}


function hasUsefulPrecAssocInfo
Boolean ::= info::Pair<Integer String>
{
  return info.fst != 0 || info.snd != "none";
}


{--
  Returns the value associated with a key from the key list. It returns the
  first match found by the earliest key in the key list. If no key matches
  then nothing is returned.
--}
function lookupByList
Maybe<b> ::= eq::(Boolean ::= a a) dict::[Pair<a b>] keyList::[a]
{
  return
  if null(keyList) then nothing()
  else
    orElse(lookupBy(eq, head(keyList), dict), lookupByList(eq, dict, tail(keyList)));
}
