grammar silver:extension:treesitter;

imports silver:definition:env;
imports silver:definition:core;
imports silver:definition:type;
imports silver:definition:concrete_syntax;
imports silver:definition:concrete_syntax:ast;
imports silver:definition:regex;
imports silver:util:raw:treemap;

{-- Throughout the code the following abbreviations mean
  ts : Treesitter
--}

synthesized attribute highlighting_lexer_class :: String;
attribute highlighting_lexer_class occurs on SyntaxTerminalModifiers, SyntaxTerminalModifier;

synthesized attribute ts_lhs :: String occurs on SyntaxDcl;
{--
  info for precedence and associativity of terminals for treesitter. This exists
  because we need to transfer precedence of terminals such as '+' to rules that
  include them in treesitter aka
    terminal Plus_t '+' precedence = 3, association = left
      in treesitter would look like
    add_expr: $ => prec.left(3, seq($.expr, $.Plus_t, $.expr))
  format for this is (terminal_name, (precedence, associativity)) where
  0 precedence means no precedence and associativity can be
  'left', 'right', 'none' so the above example would be
    ("Plus_t", (3, "left"))
--}
synthesized attribute ts_prec_assoc_entry :: Pair<String Pair<Integer String>> occurs on SyntaxDcl;
synthesized attribute ts_terminal_regex :: String occurs on SyntaxDcl;
-- the list of treesitter strings for production inputs
synthesized attribute ts_production_inputs :: [String] occurs on SyntaxDcl;
-- the list of productions that can be used to create a nonterminal
synthesized attribute ts_nonterminal_rules_rhs :: [[String]];
attribute ts_nonterminal_rules_rhs occurs on Syntax, SyntaxDcl;
synthesized attribute atom_scope :: String occurs on SyntaxDcl;

-- A list of ts_prec_assoc_entrys
synthesized attribute ts_prec_assoc_env :: [Pair<String Pair<Integer String>>] occurs on Syntax;
synthesized attribute ts_extras :: String occurs on Syntax;
synthesized attribute ts_conflicts :: String occurs on Syntax;
synthesized attribute ts_terminal_rules :: String occurs on Syntax;
synthesized attribute ts_nonterminal_rules :: [Pair<String [[String]]>] occurs on Syntax;
synthesized attribute atom_scopes :: [String] occurs on Syntax;

{-- ATOM HIGHLIGHTING LEXER CLASSES --}
lexer class atom_comment;
lexer class atom_comment_line;
lexer class atom_comment_line_doubleSlash;
lexer class atom_comment_line_doubleDash;
lexer class atom_comment_line_numberSign;
lexer class atom_comment_line_percentage;
lexer class atom_comment_block;
lexer class atom_comment_block_documentation;

lexer class atom_constant;
lexer class atom_constant_numeric;
lexer class atom_constant_character;
lexer class atom_constant_character_escape;
lexer class atom_constant_language;
lexer class atom_constant_other;

lexer class atom_entity;
lexer class atom_entity_name;
lexer class atom_entity_name_function;
lexer class atom_entity_name_type;
lexer class atom_entity_name_tag;
lexer class atom_entity_name_section;
lexer class atom_entity_other;
lexer class atom_entity_other_inheritedClass;
lexer class atom_entity_other_attributeName;

lexer class atom_invalid;
lexer class atom_invalid_illegal;
lexer class atom_invalid_deprecated;

lexer class atom_keyword;
lexer class atom_keyword_control;
lexer class atom_keyword_operator;
lexer class atom_keyword_other;

lexer class atom_markup;
lexer class atom_markup_underline;

lexer class atom_markup_heading;
lexer class atom_markup_italic;
lexer class atom_markup_list;
lexer class atom_markup_list_numbered;
lexer class atom_markup_list_unnumbered;
lexer class atom_markup_quote;
lexer class atom_markup_raw;
lexer class atom_markup_other;

lexer class atom_meta;

lexer class atom_storage;
lexer class atom_storage_type;
lexer class atom_storage_modifier;

lexer class atom_string;
lexer class atom_string_quoted;
lexer class atom_string_quoted_single;
lexer class atom_string_quoted_double;
lexer class atom_string_quoted_triple;
lexer class atom_string_quoted_other;
lexer class atom_string_unquoted;
lexer class atom_string_interpolated;
lexer class atom_string_regexp;
lexer class atom_string_other;

lexer class atom_support;
lexer class atom_support_function;
lexer class atom_support_class;
lexer class atom_support_type;
lexer class atom_support_constant;
lexer class atom_support_variable;
lexer class atom_support_other;

lexer class atom_variable;
lexer class atom_variable_parameter;
lexer class atom_variable_language;
lexer class atom_variable_other;

{-- ASPECT PRODUCTIONS --}
aspect production cstRoot
top::SyntaxRoot ::= parsername::String  startnt::String  s::Syntax  terminalPrefixes::[Pair<String String>]
{

  -- the 'normalized' version from production attribute 's2'.  This groups productions with
  -- the same left hand side together as subdcls on nonterminals.

  -- the empty string environment keeps track of which nonterminals can produce
  -- the empty string. This is used to modify the grammar such that only the
  -- nonterminal that can produce the empty string is the start nonterminal.
  -- This is required for Treesitter grammars.
  -- The environment is a list of nonterminal rule, true/false pairs where
  -- true means the nonterminal can produce the emptty string.

  -- add precedence and associativity information
  -- this function also collapses the list of input elements for the production
  -- into a valid treesitter string
  local attribute transformed_nts :: [Pair<String [[String]]>] =
    transformEmptyStringRules(s2.ts_nonterminal_rules);

  local attribute ts_startnt :: String = toTsDeclaration(startnt);
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
  ${implode("\n  ", s2.atom_scopes)}
""";

}

function isStartNonTerminalRule
Boolean ::= startnt::String rule::String
{
  return startsWith(startnt, rule);
}

{-- SYNTAX PRODUCTIONS --}
aspect production nilSyntax
top::Syntax ::=
{
  top.ts_terminal_rules = "";
  top.ts_nonterminal_rules = [];
  top.ts_nonterminal_rules_rhs = [];
  top.ts_extras = "";
  top.ts_conflicts = "";
  top.ts_prec_assoc_env = [];
  top.atom_scopes = [];
}

aspect production consSyntax
top::Syntax ::= s1::SyntaxDcl s2::Syntax
{
  -- consider an inherited attribute as the string used instead of "\n"
  -- six spaces of indentation after newline

  -- only used for terminals for now
  top.ts_prec_assoc_env =
    if (isTerminal(s1) && hasUsefulPrecAssocInfo(s1.ts_prec_assoc_entry.snd)) then
      cons(s1.ts_prec_assoc_entry, s2.ts_prec_assoc_env)
    else
      s2.ts_prec_assoc_env;

  top.ts_nonterminal_rules =
    if (isNonTerminal(s1)) then
      pair(s1.ts_lhs, s1.ts_nonterminal_rules_rhs) :: s2.ts_nonterminal_rules
    else
      s2.ts_nonterminal_rules;

  -- this is used mainly for the Syntax nodes that group the productions
  -- underneath the nonterminals.
  top.ts_nonterminal_rules_rhs =
    -- treesitter should now work with empty productions
    if (isProduction(s1)) then
      s1.ts_production_inputs :: s2.ts_nonterminal_rules_rhs
    else
      s2.ts_nonterminal_rules_rhs;

  top.ts_terminal_rules =
     -- don't include empty terminals since those exist in some places in ableC for some reason
    if (isTerminal(s1) && s1.ts_terminal_regex != "//") then
      s1.ts_lhs ++ ": $ => " ++ s1.ts_terminal_regex ++ ",\n    " ++ s2.ts_terminal_rules
    else
      s2.ts_terminal_rules;

  top.ts_extras =
    if (isIgnoreTerminal(s1)) then
      TsDeclarationToIdentifier(s1.ts_lhs) ++ ",\n    " ++ s2.ts_extras
    else
      s2.ts_extras;

  top.ts_conflicts =
    if (isSyntaxDisambiguationGroup(s1)) then
      s1.ts_lhs ++ ",\n    " ++ s2.ts_conflicts
    else
      s2.ts_conflicts;

  top.atom_scopes =
    if (isTerminal(s1)) then
      cons(s1.atom_scope, s2.atom_scopes)
    else
      s2.atom_scopes;
}

{-- SYNTAX DCL PRODUCTIONS --}
aspect production syntaxNonterminal
top::SyntaxDcl ::= t::Type subdcls::Syntax --modifiers::SyntaxNonterminalModifiers
{
  -- irrelevant attributes
  top.ts_terminal_regex = "";
  top.ts_production_inputs = [];
  top.ts_prec_assoc_entry = pair("", pair(0, "none"));
  top.atom_scope = "";

  -- relevant attributes
  top.ts_lhs = toTsDeclaration(t.typeName);
  top.ts_nonterminal_rules_rhs = subdcls.ts_nonterminal_rules_rhs;
}

aspect production syntaxTerminal
top::SyntaxDcl ::= n::String regex::Regex modifiers::SyntaxTerminalModifiers
{
  --irrelevant attributes
  top.ts_production_inputs = [];
  top.ts_nonterminal_rules_rhs = [];

  -- relevant attributes
  local attribute treesitter_name :: String = toTsDeclaration(n);
  top.ts_lhs =
    -- if we can add a leading _ to not put ignore terminals in the syntax tree that do not need to be there
    if modifiers.ignored && modifiers.highlighting_lexer_class == "" then
      toTsIgnoreDeclaration(n)
    else
      treesitter_name;
  top.ts_terminal_regex = s"""/${regex.regString}/""";
  top.ts_prec_assoc_entry = pair(top.ts_lhs, getPrecAssocInfo(modifiers));
  top.atom_scope =
    if modifiers.highlighting_lexer_class == "" then
      ""
    else
      s"""'${treesitter_name}': '${toAtomScope(modifiers.highlighting_lexer_class)}.${treesitter_name}'""";
}

aspect production syntaxProduction
top::SyntaxDcl ::= ns::NamedSignature  modifiers::SyntaxProductionModifiers
{
  -- irrelevant attributes
  top.ts_terminal_regex = "";
  top.ts_nonterminal_rules_rhs = [];
  top.atom_scope = "";
  top.ts_prec_assoc_entry = pair("", pair(0, "none"));

  -- relevant attributes
  top.ts_lhs = toTsDeclaration(ns.outputElement.typerep.typeName);
  top.ts_production_inputs = map(getProductionName, ns.inputElements);
}

aspect production syntaxLexerClass
top::SyntaxDcl ::= n::String modifiers::SyntaxLexerClassModifiers
{
  -- irrelevant attributes
  top.ts_lhs = "";
  top.ts_terminal_regex = "";
  top.ts_production_inputs = [];
  top.ts_nonterminal_rules_rhs = [];
  top.ts_prec_assoc_entry = pair("", pair(0, "none"));
  top.atom_scope = "";
}


aspect production syntaxDisambiguationGroup
top::SyntaxDcl ::= n::String terms::[String] acode::String
{
  -- irrelevant attributes
  top.ts_lhs = s"""[${implode(",", map(toTsIdentifier, terms))}]""";
  top.ts_terminal_regex = "";
  top.ts_production_inputs = [];
  top.ts_nonterminal_rules_rhs = [];
  top.ts_prec_assoc_entry = pair("", pair(0, "none"));
  top.atom_scope = "";
}

{-- SYNTAX TERMINAL MODIFIERS PRODUCTIONS --}
aspect production nilTerminalMod
top::SyntaxTerminalModifiers ::=
{
  top.highlighting_lexer_class = "";
}

aspect production consTerminalMod
top::SyntaxTerminalModifiers ::= h::SyntaxTerminalModifier  t::SyntaxTerminalModifiers
{
  top.highlighting_lexer_class = h.highlighting_lexer_class ++ t.highlighting_lexer_class;
}

aspect default production
top::SyntaxTerminalModifier ::=
{
  top.highlighting_lexer_class = "";
}

aspect production termClasses
top::SyntaxTerminalModifier ::= cls::[String]
{
  local attribute atomClasses :: [String] =
    filter(isAtomHighlightingLexerClass, cls);

  -- TODO: should we error if there is more than one?
  top.highlighting_lexer_class =
  if (length(atomClasses) >= 1) then
    stripAtomLexerClassPrefix(head(atomClasses))
  else
    "";
}

{--
 - The name of the language specified by this Tree-sitter grammar.
 -}
inherited attribute lang :: String occurs on SyntaxRoot;

{--
 - Translation of a CST AST to Tree-sitter Javascript.
 -}
synthesized attribute jsTreesitter :: String occurs on SyntaxRoot;
synthesized attribute csonAtomPackage :: String occurs on SyntaxRoot;

-- TODO: Why is SyntaxRoot closed?  Needs a default.
aspect default production
top::SyntaxRoot ::=
{
  top.jsTreesitter = error("Shouldn't happen");
  top.csonAtomPackage = error("Shouldn't happen");
}

{-- To Treesitter Strings Functions --}
{--
  Transforms a nonterminal represented as a pair(LHS, RHS) to a treesitter
  string representing the nonterminal using the precedence and associativity
  environment.
--}
function nonterminal_to_TS_string
String ::= env::[Pair<String Pair<Integer String>>] nt::Pair<String [[String]]>
{
  return nt.fst ++ ": $ => " ++ nonterminal_RHS_to_TS_string(env, nt.snd);
}

{--
  Transforms the right hand side of a nonterminal to a treesitter string.
--}
function nonterminal_RHS_to_TS_string
String ::= env::[Pair<String Pair<Integer String>>] nonterminalRHS::[[String]]
{
  return
    if length(nonterminalRHS) > 1 then
      "choice(" ++ productions_to_TS_string(env, nonterminalRHS) ++ ")"
    else
      productions_to_TS_string(env, nonterminalRHS);
}
{--
  Transforms the productions that make up a nonterminal into a string
--}
function productions_to_TS_string
String ::= env::[Pair<String Pair<Integer String>>] nonterminalProds::[[String]]
{
  return implode(",\n    ", map(production_to_TS_string(env, _), nonterminalProds));
}

{--
  Transforms a production into a treesitter string using the precedence and
  associativity environment
--}
function production_to_TS_string
String ::= env::[Pair<String Pair<Integer String>>] prod_inputs::[String]
{
  local attribute prec_assoc_info :: Maybe<Pair<Integer String>> =
    lookupByList(stringEq, env, map(TsIdentifierToDeclaration, prod_inputs));
  return
    if prec_assoc_info.isJust then
      prec_assoc_info_as_TS_string(prec_assoc_info.fromJust) ++
      production_input_list_to_string(prod_inputs) ++
      ")" -- closing paren from the precedence
    else
      production_input_list_to_string(prod_inputs);
}

{--
  Transforms the precedence and associativity info into a string that can be
  used by treesitter.
--}
function prec_assoc_info_as_TS_string
String ::= info::Pair<Integer String>
{
  -- both precedence and associativity specified
  return
  if info.fst != 0 && !stringEq(info.snd, "none") then
    s"""prec.${info.snd}(${toString(info.fst)}, """
  -- only precedence specified
  else if (info.fst != 0) then
    s"""prec(${toString(info.fst)}, """
  --only associativity specified
  else
    s"""prec.${info.snd}(""";
}
{--
  Transforms the production input list into a treesitter string
--}
function production_input_list_to_string
String ::= prod_inputs::[String]
{
  return
  if length(prod_inputs) > 1 then
    "seq(" ++ implode(", ", prod_inputs) ++ ")"
  else
    head(prod_inputs);
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

function getProductionName
String ::= prod::NamedSignatureElement
{
  return toTsIdentifier(prod.typerep.typeName);
}


{-- IS/HAS FUNCTIONS --}

function isTerminal
Boolean ::= declaration::Decorated SyntaxDcl
{
  return case declaration of
  | syntaxTerminal(_, _, _) -> true
  | _ -> false
  end;
}

function isIgnoreTerminal
Boolean ::= declaration::Decorated SyntaxDcl
{
  return case declaration of
  | syntaxTerminal(_, _, modifiers) -> modifiers.ignored
  | _ -> false
  end;
}

function isNonTerminal
Boolean ::= declaration::Decorated SyntaxDcl
{
  return case declaration of
  | syntaxNonterminal(_, _) -> true
  | _ -> false
  end;
}

function isProduction
Boolean ::= declaration::Decorated SyntaxDcl
{
  return case declaration of
  | syntaxProduction(_, _) -> true
  | _ -> false
  end;
}

function isLexerClass
Boolean ::= declaration::Decorated SyntaxDcl
{
  return case declaration of
  | syntaxLexerClass(_, _) -> true
  | _ -> false
  end;
}

function isSyntaxDisambiguationGroup
Boolean ::= declaration::Decorated SyntaxDcl
{
  return case declaration of
  | syntaxDisambiguationGroup(_, _, _) -> true
  | _ -> false
  end;
}

function isAtomHighlightingLexerClass
Boolean ::= str::String
{
  return startsWith("silver:extension:treesitter:atom_", str);
}

function hasUsefulPrecAssocInfo
Boolean ::= info::Pair<Integer String>
{
  return info.fst != 0 || info.snd != "none";
}

{-- TREESITTER SPECIFIC STRING MANIPULATION FUNCTIONS --}

{-- Transform a string in Silver/Copper to a treesitter string --}
function toTsDeclaration
String ::= str::String
{
  return substitute(":", "_", str);
}

{-- To cause terminals or nonterminals to not appear in the parse tree
  generated by tree sitter, they must begin with an _.
  TODO: switch name and context this function is used in since we may not always want to have ignore terminals not appear in syntax tree
-}
function toTsIgnoreDeclaration
String ::= str::String
{
  return "_" ++ toTsDeclaration(str);
}

{-- When identifying a tree sitter terminal or nonterminal it must be begin with
  $.
-}
function toTsIdentifier
String ::= str::String
{
  return "$." ++ substitute(":", "_", str);
}

function toAtomScope
String ::= str::String
{
  -- replaces all _ with . and all camel camel case words with a dash and lowercase
  -- i.e. helloWorld_2 becomes hello-world.2
  return toLowerCase(substituteRegex("[A-Z]","-$0",substitute("_", ".", str)));
}

function TsDeclarationToIdentifier
String ::= str::String
{
  return "$." ++ str;
}

function TsIdentifierToDeclaration
String ::= str::String
{
  -- remove the $.
  return substring(2, length(str), str);
}

function stripAtomLexerClassPrefix
String ::= str::String
{
  return substring(33, length(str), str);
}

{-- GENERAL PURPOSE LIST OF LIST MANIPULATION FUNCTIONS --}

{--
  Determines if a list of lists contains the empty list as one of its elements.
  This is equivalent to determining if a nonterminal can produce the empty string.
  @param listOfLists the list of lists (in this case the nonterminal productions)
  @return true if a list of lists contains the empty list as one of its elements. False otherwise.
--}
-- can the nonterminal produce the empty string.
-- this is equivalent to the nonterminal having a production with no input elements.
function emptyListWithinListOfLists
Boolean ::= listOfLists::[[a]]
{
  return any(map(null, listOfLists));
}

{--
  Removes empty list elements from lists of lists
  @param listOfLists the list of lists
  @return the original list of lists with empty list elements removed
--}
function removeEmptyLists
[[a]] ::= listOfLists::[[a]]
{
  return snd(partition(null, listOfLists));
}


{--
  ConsToAll cons elem to each list within the list of lists
  @param elem The element to cons onto each list in the list of lists
  @param listOfLists The list of lists
  @return The list of lists with elem cons to each list within the list of lists
--}
function consToAll
[[a]] ::= elem::a listOfLists::[[a]]
{
  return map(cons(elem, _), listOfLists);
}

{-- GENERAL PURPOSE STRING MANIPULATION FUNCTIONS --}

{--
 - Replaces all instances matching the 'regex' with 'replace' in 'str'
 -
 - @param search  The regex
 - @param replace  The string to substitute in
 - @param str  The string to operate on
 - @return  The modified form of 'str'
 -}
function substituteRegex
String ::= regex::String replace::String str::String
{
  return error("Not Yet Implemented: substituteRegex");
} foreign {
  "java" : return "new common.StringCatter(%str%.toString().replaceAll(%regex%.toString(),%replace%.toString()))";
}

{--
 - Convert all strings in str to lowercase
 -
 - @param str  The string to operate on
 - @return  The modified form of 'str'
 -}
function toLowerCase
String ::= str::String
{
  return error("Not Yet Implemented: toLowerCase");
} foreign {
  "java" : return "new common.StringCatter(%str%.toString().toLowerCase())";
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
