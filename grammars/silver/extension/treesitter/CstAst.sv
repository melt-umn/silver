grammar silver:extension:treesitter;

imports silver:definition:env;
imports silver:definition:core;
imports silver:definition:type;
imports silver:definition:concrete_syntax;
imports silver:definition:concrete_syntax:ast;
imports silver:definition:regex;

synthesized attribute highlighting_lexer_class :: String;
attribute highlighting_lexer_class occurs on SyntaxTerminalModifiers, SyntaxTerminalModifier;

synthesized attribute ts_lhs :: String occurs on SyntaxDcl;
synthesized attribute ts_prec_assoc_entry :: Pair<String Pair<Integer String>> occurs on SyntaxDcl;
synthesized attribute ts_terminal_regex :: String occurs on SyntaxDcl;
synthesized attribute ts_production_inputs :: [String] occurs on SyntaxDcl;
synthesized attribute ts_nonterminal_rules_rhs :: [[String]];
attribute ts_nonterminal_rules_rhs occurs on Syntax, SyntaxDcl;
synthesized attribute atom_scope :: String occurs on SyntaxDcl;

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
synthesized attribute ts_prec_assoc_env :: [Pair<String Pair<Integer String>>] occurs on Syntax;
synthesized attribute ts_extras :: String occurs on Syntax;
synthesized attribute ts_terminal_rules :: String occurs on Syntax;
synthesized attribute ts_nonterminal_rules :: [Pair<String [[String]]>] occurs on Syntax;
synthesized attribute atom_scopes :: [String] occurs on Syntax;

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
lexer class atom_markup_underline_link;
lexer class atom_markup_bold;
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

function nonterminal_to_TS_string
String ::= env::[Pair<String Pair<Integer String>>] nt::Pair<String [[String]]>
{
  return nt.fst ++ ": $ => " ++ nonterminal_RHS_to_TS_string(env, nt.snd);
}

function nonterminal_RHS_to_TS_string
String ::= env::[Pair<String Pair<Integer String>>] nonterminalRHS::[[String]]
{
  return 
    if length(nonterminalRHS) > 1 then
      "choice(" ++ productions_to_TS_string(env, nonterminalRHS) ++ ")"
    else
      productions_to_TS_string(env, nonterminalRHS);
}

function productions_to_TS_string
String ::= env::[Pair<String Pair<Integer String>>] nonterminalProds::[[String]]
{
  return implode(", ", map(production_to_TS_string(env, _), nonterminalProds));
}

function production_to_TS_string
String ::= env::[Pair<String Pair<Integer String>>] prod_inputs::[String]
{
  local attribute prec_assoc_info :: Maybe<Pair<Integer String>> =
    dictionaryLookupList(stringEq, env, map(treeSitterIdentifierToDeclaration, prod_inputs));
  return 
    if prec_assoc_info.isJust then
      get_prec_assoc_info_as_TS_string(prec_assoc_info.fromJust) ++
      production_input_list_to_string(prod_inputs) ++ ")"
    else 
      production_input_list_to_string(prod_inputs);
}

function get_prec_assoc_info_as_TS_string
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

function production_input_list_to_string
String ::= prod_inputs::[String]
{
  return 
  if length(prod_inputs) > 1 then
    "seq(" ++ implode(", ", prod_inputs) ++ ")"
  else
    head(prod_inputs);
}

function dictionaryLookupList
Maybe<b> ::= eq::(Boolean ::= a a) dict::[Pair<a b>] keyList::[a]
{
  return 
  if null(keyList) then nothing()
  else
    orElse(dictionaryLookup(eq, dict, head(keyList)), dictionaryLookupList(eq, dict, tail(keyList)));
}


function dictionaryLookup
Maybe<b> ::= eq::(Boolean ::= a a) dict::[Pair<a b>] key::a
{
  return 
    if null(dict) then 
      nothing()
    else if eq(head(dict).fst, key) then 
      just(head(dict).snd)
    else 
      dictionaryLookup(eq, tail(dict), key);
}

aspect production cstRoot
top::SyntaxRoot ::= parsername::String  startnt::String  s::Syntax  terminalPrefixes::[Pair<String String>]
{

  -- the 'normalized' version from production attribute 's2'.  This groups productions with
  -- the same left hand side together as subdcls on nonterminals.

  -- add precedence and associativity information
  -- this function also collapses the list of input elements for the production
  -- into a valid treesitter string
  local attribute nonterminal_rules :: [String] =
    map(nonterminal_to_TS_string(s2.ts_prec_assoc_env, _), s2.ts_nonterminal_rules);

  -- partition the grammar rules so that the start nonterminal is the first rule
  -- listed in the tree-sitter grammar as needed.
  local attribute nonterminals_split :: Pair<[String] [String]> =
    partition(isStartNonTerminalRule(toTreeSitterDeclaration(startnt), _), nonterminal_rules);
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

aspect production nilSyntax
top::Syntax ::=
{
  top.ts_terminal_rules = "";
  top.ts_nonterminal_rules = [];
  top.ts_nonterminal_rules_rhs = [];
  top.ts_extras = "";
  top.ts_prec_assoc_env = [];
  top.atom_scopes = [];
}

aspect production consSyntax
top::Syntax ::= s1::SyntaxDcl s2::Syntax
{
  -- consider an inherited attribute as the string used instead of "\n"
  -- six spaces of indentation after newline
  top.ts_prec_assoc_env = 
    if (hasUsefulPrecAssocInfo(s1.ts_prec_assoc_entry.snd)) then
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
    -- if it is a production with input elements
    if (isProduction(s1) && !null(s1.ts_production_inputs)) then
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
      treeSitterDeclarationToIdentifier(s1.ts_lhs) ++ ",\n    " ++ s2.ts_extras
    else
      s2.ts_extras;

  top.atom_scopes =
    if (isTerminal(s1)) then
      cons(s1.atom_scope, s2.atom_scopes)
    else
      s2.atom_scopes;
}

aspect production syntaxNonterminal
top::SyntaxDcl ::= t::Type subdcls::Syntax --modifiers::SyntaxNonterminalModifiers
{
  -- irrelevant attributes
  top.ts_terminal_regex = "";
  top.ts_production_inputs = [];
  top.ts_prec_assoc_entry = pair("", pair(0, "none"));
  top.atom_scope = "";

  -- relevant attributes
  top.ts_lhs = toTreeSitterDeclaration(t.typeName);
  top.ts_nonterminal_rules_rhs = subdcls.ts_nonterminal_rules_rhs;
}

aspect production syntaxTerminal
top::SyntaxDcl ::= n::String regex::Regex modifiers::SyntaxTerminalModifiers
{
  --irrelevant attributes
  top.ts_production_inputs = [];
  top.ts_nonterminal_rules_rhs = [];

  -- relevant attributes
  local attribute treesitter_name :: String = toTreeSitterDeclaration(n);
  top.ts_lhs =
    -- if we can add a leading _ to not put ignore terminals in the syntax tree that do not need to be there
    if modifiers.ignored && modifiers.highlighting_lexer_class == "" then
      toTreeSitterIgnoreDeclaration(n)
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

aspect production syntaxProduction
top::SyntaxDcl ::= ns::NamedSignature  modifiers::SyntaxProductionModifiers
{
  -- irrelevant attributes
  top.ts_terminal_regex = "";
  top.ts_nonterminal_rules_rhs = [];
  top.atom_scope = "";
  top.ts_prec_assoc_entry = pair("", pair(0, "none"));

  -- relevant attributes
  top.ts_lhs = toTreeSitterDeclaration(ns.outputElement.typerep.typeName);
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

function toTreeSitterDeclaration
String ::= str::String
{
  return substitute(":", "_", str);
}

{-- To cause terminals or nonterminals to not appear in the parse tree
  generated by tree sitter, they must begin with an _.
  TODO: switch name and context this function is used in since we may not always want to have ignore terminals not appear in syntax tree
-}
function toTreeSitterIgnoreDeclaration
String ::= str::String
{
  return "_" ++ toTreeSitterDeclaration(str);
}

{-- When identifying a tree sitter terminal or nonterminal it must be begin with
  $.
-}
function toTreeSitterIdentifier
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

function treeSitterDeclarationToIdentifier
String ::= str::String
{
  return "$." ++ str;
}

function treeSitterIdentifierToDeclaration
String ::= str::String
{
  -- remove the $.
  return substring(2, length(str), str);
}

function hasUsefulPrecAssocInfo
Boolean ::= info::Pair<Integer String>
{
  return info.fst != 0 || info.snd != "none";
}

function isTerminal
Boolean ::= declaration::Decorated SyntaxDcl
{
  return case declaration of
  | syntaxLexerClass(_, _) -> false
  | syntaxTerminal(_, _, _) -> true
  | syntaxNonterminal(_, _) -> false
  | syntaxProduction(_, _) -> false
  | _ -> false
  end;
}

function isIgnoreTerminal
Boolean ::= declaration::Decorated SyntaxDcl
{
  return case declaration of
  | syntaxLexerClass(_, _) -> false
  | syntaxTerminal(_, _, modifiers) -> modifiers.ignored
  | syntaxNonterminal(_, _) -> false
  | syntaxProduction(_, _) -> false
  | _ -> false
  end;
}

function isNonTerminal
Boolean ::= declaration::Decorated SyntaxDcl
{
  return case declaration of
  | syntaxLexerClass(_, _) -> false
  | syntaxTerminal(_, _, _) -> false
  | syntaxNonterminal(_, _) -> true
  | syntaxProduction(_, _) -> false
  | _ -> false
  end;
}

function isProduction
Boolean ::= declaration::Decorated SyntaxDcl
{
  return case declaration of
  | syntaxLexerClass(_, _) -> false
  | syntaxTerminal(_, _, _) -> false
  | syntaxNonterminal(_, _) -> false
  | syntaxProduction(_, _) -> true
  | _ -> false
  end;
}

function isLexerClass
Boolean ::= declaration::Decorated SyntaxDcl
{
  return case declaration of
  | syntaxLexerClass(_, _) -> true
  | syntaxTerminal(_, _, _) -> false
  | syntaxNonterminal(_, _) -> false
  | syntaxProduction(_, _) -> false
  | _ -> false
  end;
}

function getProductionName
String ::= prod::NamedSignatureElement
{
  return toTreeSitterIdentifier(prod.typerep.typeName);
}

function isAtomHighlightingLexerClass
Boolean ::= str::String
{
  return startsWith("silver:extension:treesitter:atom_", str);
}

function stripAtomLexerClassPrefix
String ::= str::String
{
  return substring(33, length(str), str);
}

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
