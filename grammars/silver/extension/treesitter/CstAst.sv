grammar silver:extension:treesitter;

imports silver:definition:env;
imports silver:definition:core;
imports silver:definition:type;
imports silver:definition:concrete_syntax;
imports silver:definition:concrete_syntax:ast;
imports silver:definition:regex;

synthesized attribute highlighting_lexer_class :: String;
attribute highlighting_lexer_class occurs on SyntaxTerminalModifiers, SyntaxTerminalModifier;

synthesized attribute tree_sitter_lhs :: String occurs on SyntaxDcl;
synthesized attribute tree_sitter_rhs :: String occurs on SyntaxDcl;
synthesized attribute tree_sitter :: String occurs on SyntaxDcl;
synthesized attribute atom_scope :: String occurs on SyntaxDcl;

synthesized attribute tree_sitter_extras :: String occurs on Syntax;
synthesized attribute tree_sitter_terminal_rules :: String occurs on Syntax;
synthesized attribute tree_sitter_nonterminal_rules :: String occurs on Syntax;
synthesized attribute tree_sitter_nonterminal_rules_rhs :: String occurs on Syntax;
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

aspect production cstRoot
top::SyntaxRoot ::= parsername::String  startnt::String  s::Syntax  terminalPrefixes::[Pair<String String>]
{

  -- the 'normalized' version from production attribute 's2'.  This groups productions with
  -- the same left hand side together as subdcls on nonterminals.

  -- The tree sitter grammar.
  top.jsTreesitter =

s"""
module.exports = grammar({
  name: '${top.lang}',

  extras: $$ => [
    ${s2.tree_sitter_extras}
  ],

  rules: {
    ${s2.tree_sitter_nonterminal_rules}

    ${s2.tree_sitter_terminal_rules}
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
--  ${ implode("\n\n    ", map ((.tree_sitter), ntDcls)) }


aspect production nilSyntax
top::Syntax ::=
{
  top.tree_sitter_terminal_rules = "";
  top.tree_sitter_nonterminal_rules = "";
  top.tree_sitter_nonterminal_rules_rhs = "";
  top.tree_sitter_extras = "";
  top.atom_scopes = [];
}

aspect production consSyntax
top::Syntax ::= s1::SyntaxDcl s2::Syntax
{
  -- consider an inherited attribute as the string used instead of "\n"
  -- six spaces of indentation after newline
  top.tree_sitter_nonterminal_rules = 
    if (isNonTerminal(s1)) then
      s1.tree_sitter ++ ",\n    " ++ s2.tree_sitter_nonterminal_rules
    else
      s2.tree_sitter_nonterminal_rules;

  top.tree_sitter_nonterminal_rules_rhs =
    if (isNonTerminal(s1)) then
      s1.tree_sitter_rhs ++ ",\n     " ++ s2.tree_sitter_nonterminal_rules_rhs
    else if (isProduction(s1)) then 
      s1.tree_sitter_rhs ++ ",\n     " ++ s2.tree_sitter_nonterminal_rules_rhs
    else
      s2.tree_sitter_nonterminal_rules_rhs;

  top.tree_sitter_terminal_rules =
    if (isTerminal(s1)) then
      s1.tree_sitter ++ ",\n    " ++ s2.tree_sitter_terminal_rules
    else
      s2.tree_sitter_terminal_rules;

  top.tree_sitter_extras =
    if (isIgnoreTerminal(s1)) then
      treeSitterDeclarationToIdentifier(s1.tree_sitter_lhs) ++ ",\n    " ++ s2.tree_sitter_extras
    else
      s2.tree_sitter_extras;

  top.atom_scopes =
    if (isTerminal(s1)) then
      cons(s1.atom_scope, s2.atom_scopes)
    else
      s2.atom_scopes;
}



aspect production syntaxNonterminal
top::SyntaxDcl ::= t::Type subdcls::Syntax --modifiers::SyntaxNonterminalModifiers
{
  top.tree_sitter_lhs = toTreeSitterDeclaration(t.typeName);
  top.tree_sitter_rhs = "choice(" ++ subdcls.tree_sitter_nonterminal_rules_rhs ++ ")";
  top.tree_sitter = top.tree_sitter_lhs ++ ": $ => " ++ top.tree_sitter_rhs;
  top.atom_scope = "";
}

aspect production syntaxTerminal
top::SyntaxDcl ::= n::String regex::Regex modifiers::SyntaxTerminalModifiers
{
  local attribute treesitter_name :: String = toTreeSitterDeclaration(n);
  top.tree_sitter_lhs = 
    if modifiers.ignored then
      toTreeSitterIgnoreDeclaration(n)
    else
      treesitter_name;
  top.tree_sitter_rhs = s"""/${regex.regString}/""";
  top.tree_sitter = top.tree_sitter_lhs ++ ": $ => " ++ top.tree_sitter_rhs;
  top.atom_scope = 
    if modifiers.highlighting_lexer_class == "" then
      ""
    else
      s"""'${treesitter_name}': '${toAtomScope(modifiers.highlighting_lexer_class)}.${treesitter_name}'""";
}


aspect production syntaxProduction
top::SyntaxDcl ::= ns::NamedSignature  modifiers::SyntaxProductionModifiers
{
  top.tree_sitter_lhs = toTreeSitterDeclaration(ns.outputElement.typerep.typeName);
  top.tree_sitter_rhs =
    (if listLength(ns.inputElements) > 1 then
       "seq(" ++ toTreeSitterIdentifier(head(ns.inputElements).typerep.typeName) ++ foldl(commaSepNamedSignatures,"",tail(ns.inputElements)) ++ ")"
    else
       (toTreeSitterIdentifier((head(ns.inputElements)).typerep.typeName)));
  top.tree_sitter = top.tree_sitter_lhs ++ ": $ => " ++ top.tree_sitter_rhs;
  top.atom_scope = "";
}

aspect production syntaxLexerClass
top::SyntaxDcl ::= n::String modifiers::SyntaxLexerClassModifiers
{
  top.tree_sitter_lhs = "";
  top.tree_sitter_rhs = "";
  top.tree_sitter = "";
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

function commaSepNamedSignatures
String ::= accum::String inputElement::NamedSignatureElement
{
  return accum ++ ", " ++ toTreeSitterIdentifier(inputElement.typerep.typeName);
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
  return "_" ++ substitute(":", "_", str);
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

function isTerminal
Boolean ::= declaration::Decorated SyntaxDcl
{
  return case declaration of
  | syntaxLexerClass(_, _) -> false
  | syntaxTerminal(_, _, _) -> true
  | syntaxNonterminal(_, _) -> false
  | syntaxProduction(_, _) -> false
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
  end;
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
