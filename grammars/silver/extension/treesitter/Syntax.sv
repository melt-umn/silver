grammar silver:extension:treesitter;

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

-- A list of ts_prec_assoc_entrys
synthesized attribute ts_prec_assoc_env :: [Pair<String Pair<Integer String>>] occurs on Syntax;

synthesized attribute ts_extras :: String occurs on Syntax;
synthesized attribute ts_conflicts :: String occurs on Syntax;
synthesized attribute ts_terminal_rules :: String occurs on Syntax;
synthesized attribute ts_nonterminal_rules :: [Pair<String [[String]]>] occurs on Syntax;
synthesized attribute ts_terminal_regex :: String occurs on SyntaxDcl;
-- the list of treesitter strings for production inputs
synthesized attribute ts_production_inputs :: [String] occurs on SyntaxDcl;
-- the list of productions that can be used to create a nonterminal
synthesized attribute ts_nonterminal_rules_rhs :: [[String]];
attribute ts_nonterminal_rules_rhs occurs on Syntax, SyntaxDcl;

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
  top.terminalLexerClassesEnv = [];
}

aspect production consSyntax
top::Syntax ::= s1::SyntaxDcl s2::Syntax
{
  -- consider an inherited attribute as the string used instead of "\n"
  -- six spaces of indentation after newline
  top.terminalLexerClassesEnv =
    if (isTerminal(s1) && !null(s1.lexer_classes)) then
      append(map(pair(s1.ts_lhs, _), s1.lexer_classes),
             s2.terminalLexerClassesEnv)
    else
      s2.terminalLexerClassesEnv;

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
}

{-- SYNTAX DCL PRODUCTIONS --}
aspect production syntaxNonterminal
top::SyntaxDcl ::= t::Type subdcls::Syntax --modifiers::SyntaxNonterminalModifiers
{
  -- irrelevant attributes
  top.ts_terminal_regex = "";
  top.ts_production_inputs = [];
  top.ts_prec_assoc_entry = pair("", pair(0, "none"));
  top.lexer_classes = [];
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
  -- top.jsonAtom = TODO;
  local attribute treesitter_name :: String = toTsDeclaration(n);
  top.ts_lhs =
    -- if we can add a leading _ to not put ignore terminals in the syntax tree that do not need to be there
    if modifiers.ignored then -- TODO: do we want to ignore these?
      toTsIgnoreDeclaration(n)
    else
      treesitter_name;
  top.lexer_classes = modifiers.lexer_classes;
  top.ts_terminal_regex = s"""/${regex.regString}/""";
  top.ts_prec_assoc_entry = pair(top.ts_lhs, getPrecAssocInfo(modifiers));
}

aspect production syntaxProduction
top::SyntaxDcl ::= ns::NamedSignature  modifiers::SyntaxProductionModifiers
{
  -- irrelevant attributes
  top.ts_terminal_regex = "";
  top.ts_nonterminal_rules_rhs = [];
  top.ts_prec_assoc_entry = pair("", pair(0, "none"));
  top.lexer_classes = [];

  -- relevant attributes
  top.ts_lhs = toTsDeclaration(ns.outputElement.typerep.typeName);
  top.ts_production_inputs = map(productionElementToString, ns.inputElements);
}

aspect production syntaxLexerClass
top::SyntaxDcl ::= n::String modifiers::SyntaxLexerClassModifiers
{
  -- relevant attributes
  top.ts_lhs = n;
  --top.jsonAtom = 
  -- irrelevant attributes
  top.ts_terminal_regex = "";
  top.ts_production_inputs = [];
  top.ts_nonterminal_rules_rhs = [];
  top.ts_prec_assoc_entry = pair("", pair(0, "none"));
  top.lexer_classes = [];
}

aspect production syntaxDisambiguationGroup
top::SyntaxDcl ::= n::String terms::[String] applicableToSubsets::Boolean acode::String
{
  -- relevant attributes

  -- used for the conflicts array to handle ambiguities
  top.ts_lhs = s"""[${implode(",", map(toTsIdentifier, terms))}]""";

  -- irrelevant attributes
  top.ts_terminal_regex = "";
  top.ts_production_inputs = [];
  top.ts_nonterminal_rules_rhs = [];
  top.ts_prec_assoc_entry = pair("", pair(0, "none"));
  top.lexer_classes = [];
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
  | syntaxDisambiguationGroup(_, _, _, _) -> true
  | _ -> false
  end;
}

function hasDisambiguationFunction
Boolean ::= declaration::Decorated SyntaxDcl
{
  return 
  case declaration of
  | syntaxDisambiguationGroup(_, _, _, _) -> true
  | syntaxLexerClass(_, mods) -> length(mods.disambiguationClasses) > 0
  | _ -> false
  end;
}
