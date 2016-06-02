grammar silver:modification:copper;

import core:monad;

import silver:driver:util only computeDependencies;
import silver:definition:regex;
import silver:extension:easyterminal; -- only Terminal_t, EasyTerminalRef;

terminal Prefix_t 'prefix' lexer classes {KEYWORD}; -- not RESERVED

concrete production prefixParserComponentModifier
top::ParserComponentModifier ::= 'prefix' ts::TerminalPrefixItems 'with' s::QName
{
  top.pp = "prefix " ++ ts.pp ++ " with " ++ s.pp;
  top.errors := ts.errors ++ s.lookupType.errors;
  top.terminalPrefixes =
    do (bindList, returnList) {
      t::QName <- ts.prefixNames;
      td::Decorated QName =
        decorate t with {
          config = top.config;
          grammarName = top.grammarName;
          env = top.env;
        };
      return pair(td.lookupType.fullName, makeCopperName(s.lookupType.fullName));
    };
  top.liftedAGDcls = emptyAGDcl(location=top.location);
  propagate liftedComponents;
}

-- TODO: Move RHS of 'with' to a new nonterminal
concrete production prefixNewTermModifiersParserComponentModifier
top::ParserComponentModifier ::= 'prefix' ts::TerminalPrefixItems 'with' r::RegExpr tm::TerminalModifiers
{
  top.pp = "prefix " ++ ts.pp ++ " with " ++ r.pp ++ " " ++ tm.pp;
  -- Prefix terminal name isn't based off the prefix right now since that might not be alphanumeric
  -- TODO make the terminal name based off alphanumeric characters from the regex for easier debugging
  local terminalName::String = "_Prefix" ++ toString(genInt());
  top.liftedAGDcls = terminalDclDefault(
    terminalKeywordModifierNone(location=top.location),
    name(terminalName, top.location),
    r, tm,
    location=top.location);
  
  forwards to prefixParserComponentModifier($1, ts, $3, qName(top.location, terminalName), location=top.location);
}

concrete production prefixNewTermParserComponentModifier
top::ParserComponentModifier ::= 'prefix' ts::TerminalPrefixItems 'with' r::RegExpr
{
  forwards to prefixNewTermModifiersParserComponentModifier($1, $2, $3, $4, terminalModifiersNone(location=top.location), location=top.location);
}

concrete production seperatedPrefixItem
top::ParserComponentModifier ::= 'prefix' ts::TerminalPrefixItems 'with' t::String_t
{
  top.pp = "prefix " ++ ts.pp ++ " with " ++ t.lexeme;
  local seperatorLookup::[DclInfo] = getValueDcl("_prefix_seperator", top.env);
  local seperator::String = 
    case seperatorLookup of
      prefixSeparatorDcl(sg, sl, s) :: _ -> s
    | _ -> ""
    end;
  local prefix::String = substring(1, length(t.lexeme) - 1, t.lexeme);
  local regex::RegExpr =
    regExpr('/', literalRegex(prefix ++ seperator), '/', location=top.location);
  top.errors <- 
    case seperatorLookup of
      prefixSeparatorDcl(sg, sl, s) :: _ -> []
    | _ -> [wrn(top.location, "Prefix seperator is not defined, using the empty seperator")]
    end;
  forwards to prefixNewTermParserComponentModifier($1, ts, $3, regex, location=top.location);
}

synthesized attribute prefixNames::[QName];
nonterminal TerminalPrefixItems with config, env, grammarName, componentGrammarName, compiledGrammars, location, pp, errors, prefixNames;

concrete production consTerminalPrefixItem
top::TerminalPrefixItems ::= ts::TerminalPrefixItems ',' t::TerminalPrefixItem
{
  top.pp = ts.pp ++ ", " ++ t.pp;
  top.errors := ts.errors ++ t.errors;
  top.prefixNames = ts.prefixNames ++ t.prefixNames;
}

concrete production oneTerminalPrefixItem
top::TerminalPrefixItems ::= t::TerminalPrefixItem
{
  top.pp = t.pp;
  top.errors := t.errors;
  top.prefixNames = t.prefixNames;
}

concrete production allTerminalPrefixItem
top::TerminalPrefixItems ::=
{
  -- TODO: We're computing dependencies for MED that are not the correct set
  -- We *should* be using the dependencies computed for all grammars included in this parser,
  -- but instead we're just considering the one. This maybe works okay for finding marking tokens
  -- since conditional exports probably won't introduce new ones, but this is a bug, currently!
  local med_deps :: [String] = computeDependencies([top.componentGrammarName], top.compiledGrammars);

  production med :: ModuleExportedDefs =
    moduleExportedDefs(top.location, top.compiledGrammars, med_deps, [top.componentGrammarName], []);

  local syntax::Syntax = foldr(consSyntax, nilSyntax(), med.syntaxAst);
  syntax.containingGrammar = error("This shouldn't be needed...");
  syntax.cstEnv = error("This shouldn't be needed...");
  syntax.cstNTProds = error("This shouldn't be needed...");
  syntax.prefixesForTerminals = error("This shouldn't be needed...");
  syntax.univLayout = error("This shouldn't be needed...");

  top.pp = "";
  top.errors := [];
  top.prefixNames =
    do (bindList, returnList) {
      sd::Decorated SyntaxDcl <- syntax.allMarkingTerminals;
      return qName(top.location, case sd of syntaxTerminal(n, _, _) -> n end);
    };
}

nonterminal TerminalPrefixItem with config, env, grammarName, componentGrammarName, compiledGrammars, location, pp, errors, prefixNames;

concrete production qNameTerminalPrefixItem
top::TerminalPrefixItem ::= t::QName
{
  top.pp = t.pp;
  top.errors := t.lookupType.errors;
  top.prefixNames = [t];
}

concrete production easyTerminalRefTerminalPrefixItem
top::TerminalPrefixItem ::= t::EasyTerminalRef
{
  top.pp = t.pp;
  top.errors := t.errors;
  top.prefixNames = map(qName(top.location, _), map((.fullName), t.dcls));
}

-- Prefix seperator
terminal Separator_kwd 'separator' lexer classes {KEYWORD}; -- not RESERVED?

concrete production prefixSeparatorAGDcl
top::AGDcl ::= 'prefix' 'separator' s::String_t ';'
{
  top.pp = s"prefix separator ${s.lexeme};";
  top.errors := []; -- TODO
  top.defs = [prefixSeparatorDef(top.grammarName, top.location, substring(1,length(s.lexeme)-1,s.lexeme))];
}