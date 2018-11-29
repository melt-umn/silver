grammar silver:extension:treesitter;

imports silver:definition:env;
imports silver:definition:core;
imports silver:definition:type;
imports silver:definition:concrete_syntax;
imports silver:definition:concrete_syntax:ast;
imports silver:definition:regex;

synthesized attribute tree_sitter_lhs :: String;
synthesized attribute tree_sitter_rhs :: String;
synthesized attribute tree_sitter_extras :: Boolean;

synthesized attribute tree_sitter :: String;

attribute tree_sitter_lhs occurs on SyntaxDcl;
attribute tree_sitter_rhs occurs on SyntaxDcl;
attribute tree_sitter_extras occurs on SyntaxDcl;

attribute tree_sitter occurs on SyntaxDcl, Syntax;

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

  rules: {
    ----
    ${ implode("\n\n    ", map ((.tree_sitter), ntDcls)) }

    ----

    ${getTreeSitterString(ntDcls)} 

    ${getTreeSitterString(filter(isTerminal, syntaxDcls))}
  }
});
""" ;

}



aspect production nilSyntax
top::Syntax ::=
{
  top.tree_sitter = "";
}

aspect production consSyntax
top::Syntax ::= s1::SyntaxDcl s2::Syntax
{
-- consider an inherited attribute as the string used instead of "\n"

  top.tree_sitter = s1.tree_sitter ++ ",\n              " ++ s2.tree_sitter;
}



aspect production syntaxNonterminal
top::SyntaxDcl ::= t::Type subdcls::Syntax --modifiers::SyntaxNonterminalModifiers
{
  top.tree_sitter_lhs = "";
  top.tree_sitter_rhs = "";
  top.tree_sitter_extras = false;

  top.tree_sitter = t.typeName ++ " => choice( " ++ subdcls.tree_sitter  ++ ")";
}



aspect production syntaxTerminal
top::SyntaxDcl ::= n::String regex::Regex modifiers::SyntaxTerminalModifiers
{
  top.tree_sitter_lhs = makeIdName(n);
  top.tree_sitter_rhs = "'" ++ regex.regString ++ "'";
  top.tree_sitter_extras = modifiers.ignored;
}


aspect production syntaxProduction
top::SyntaxDcl ::= ns::NamedSignature  modifiers::SyntaxProductionModifiers
{
  top.tree_sitter_lhs = makeIdName(ns.outputElement.typerep.typeName);
  top.tree_sitter_rhs =
    (if listLength(ns.inputElements) > 1 then
       "seq($." ++ makeIdName(head(ns.inputElements).typerep.typeName) ++ foldl(commaSepNamedSignatures,"",tail(ns.inputElements)) ++ ")"
    else
       ("$." ++ makeIdName((head(ns.inputElements)).typerep.typeName)));
  top.tree_sitter_extras = false;

  top.tree_sitter = top.tree_sitter_rhs;
}

aspect production syntaxLexerClass
top::SyntaxDcl ::= n::String modifiers::SyntaxLexerClassModifiers
{
  top.tree_sitter_lhs = "";
  top.tree_sitter_rhs = "";
  top.tree_sitter_extras = false;
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


function getTreeSitterString
String ::= declarations::[Decorated SyntaxDcl]
{
  return implode(",\n", map(dclTreesitterString, declarations));
}


function dclTreesitterString
String ::= declaration::Decorated SyntaxDcl
{
  -- return  declaration.tree_sitter
  -- then remove calls to this function
  return case declaration of
  | syntaxLexerClass(_, _) -> ""
  | syntaxTerminal(_, _, _) ->
      if !declaration.tree_sitter_extras then
        declaration.tree_sitter_lhs ++ ": $ => " ++ declaration.tree_sitter_rhs
      else
        ""
  | syntaxNonterminal(_, _) -> declaration.tree_sitter 
  | syntaxProduction(_, _) -> declaration.tree_sitter_lhs ++ ":$ => " ++ declaration.tree_sitter_rhs
   end;
}

function commaSepNamedSignatures
String ::= accum::String inputElement::NamedSignatureElement
{
  return accum ++ ", $." ++ makeIdName(inputElement.typerep.typeName);
}

function makeIdName
String ::= str::String
{
  return substitute(":", "_", str);
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
