grammar silver:extension:treesitter;

imports silver:definition:env;
imports silver:definition:core;
imports silver:definition:type;
imports silver:definition:concrete_syntax;
imports silver:definition:concrete_syntax:ast;
imports silver:definition:regex;

synthesized attribute tree_sitter_lhs :: String;
synthesized attribute tree_sitter_rhs :: String;
synthesized attribute tree_sitter :: String;

synthesized attribute tree_sitter_extras :: String;
synthesized attribute tree_sitter_terminal_rules :: String;
synthesized attribute tree_sitter_nonterminal_rules :: String;
synthesized attribute tree_sitter_nonterminal_rules_rhs :: String;

attribute tree_sitter_lhs occurs on SyntaxDcl;
attribute tree_sitter_rhs occurs on SyntaxDcl;
attribute tree_sitter occurs on SyntaxDcl;

attribute tree_sitter_extras occurs on Syntax;
attribute tree_sitter_terminal_rules occurs on Syntax;
attribute tree_sitter_nonterminal_rules occurs on Syntax;
attribute tree_sitter_nonterminal_rules_rhs occurs on Syntax;

aspect production cstRoot
top::SyntaxRoot ::= parsername::String  startnt::String  s::Syntax  terminalPrefixes::[Pair<String String>]
{

  -- the 'normalized' version from production attribute 's2'.  This groups productions with
  -- the same left hand side together as subdcls on nonterminals.
  local attribute syntaxDcls :: [Decorated SyntaxDcl] 
    = map(snd, s2.cstDcls);

  -- Nonterminal declarations
  local attribute ntDcls :: [Decorated SyntaxDcl] 
    = filter(isNonTerminal, syntaxDcls);

  -- The tree sitter grammar.
  top.jsTreesitter =

s"""
module.exports = grammar({
  name: '${top.lang}',

  extras: $$ => [
    ${s.tree_sitter_extras}
  ],

  rules: {
    ${implode(",\n\n    ", map( (.tree_sitter), ntDcls))},

    ${s.tree_sitter_terminal_rules}
  }
});
""" ;

}
--  ${ implode("\n\n    ", map ((.tree_sitter), ntDcls)) }


aspect production nilSyntax
top::Syntax ::=
{
  top.tree_sitter_terminal_rules = "";
  top.tree_sitter_nonterminal_rules = "";
  top.tree_sitter_nonterminal_rules_rhs = "";
  top.tree_sitter_extras = "";
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
}



aspect production syntaxNonterminal
top::SyntaxDcl ::= t::Type subdcls::Syntax --modifiers::SyntaxNonterminalModifiers
{
  top.tree_sitter_lhs = toTreeSitterDeclaration(t.typeName);
  top.tree_sitter_rhs = "choice(" ++ subdcls.tree_sitter_nonterminal_rules_rhs ++ ")";
  top.tree_sitter = top.tree_sitter_lhs ++ ": $ => " ++ top.tree_sitter_rhs;
}

aspect production syntaxTerminal
top::SyntaxDcl ::= n::String regex::Regex modifiers::SyntaxTerminalModifiers
{
  top.tree_sitter_lhs = 
    if modifiers.ignored then
      toTreeSitterIgnoreDeclaration(n)
    else
      toTreeSitterDeclaration(n);
  top.tree_sitter_rhs = s"""/${regex.regString}/""";
  top.tree_sitter = top.tree_sitter_lhs ++ ": $ => " ++ top.tree_sitter_rhs;
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
}

aspect production syntaxLexerClass
top::SyntaxDcl ::= n::String modifiers::SyntaxLexerClassModifiers
{
  top.tree_sitter_lhs = "";
  top.tree_sitter_rhs = "";
  top.tree_sitter = "";
}


{--
 - The name of the language specified by this Tree-sitter grammar.
 -}
inherited attribute lang :: String occurs on SyntaxRoot;

{--
 - Translation of a CST AST to Tree-sitter Javascript.
 -}
synthesized attribute jsTreesitter :: String occurs on SyntaxRoot;

-- TODO: Why is SyntaxRoot closed?  Needs a default.
aspect default production
top::SyntaxRoot ::=
{
  top.jsTreesitter = error("Shouldn't happen");
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
