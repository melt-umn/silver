grammar silver:modification:copper;

import core:monad;

import silver:definition:regex;
import silver:extension:easyterminal; -- only Terminal_t, EasyTerminalRef;

terminal Prefix_t 'prefix' lexer classes {KEYWORD, RESERVED};

concrete production prefixParserComponentModifier
top::ParserComponentModifier ::= 'prefix' ts::TerminalPrefixItems 'with' s::TerminalPrefix
{  
  top.unparse = "prefix " ++ ts.unparse ++ " with " ++ s.unparse;
  top.terminalPrefixes <- map(pair(_, s.terminalPrefix), ts.prefixItemNames);
  s.prefixedTerminals = ts.prefixItemNames;
}

autocopy attribute prefixedTerminals::[String];
synthesized attribute terminalPrefix::String;
nonterminal TerminalPrefix with config, env, flowEnv, grammarName, componentGrammarName, compiledGrammars, prefixedTerminals, location, unparse, errors, syntaxAst, genFiles, terminalPrefix;

propagate errors, syntaxAst, genFiles on TerminalPrefix;

concrete production nameTerminalPrefix
top::TerminalPrefix ::= s::QName
{
  top.unparse = s.unparse;
  top.errors <- s.lookupType.errors;
  top.terminalPrefix = makeCopperName(s.lookupType.fullName);
}

concrete production newTermModifiersTerminalPrefix
top::TerminalPrefix ::= r::RegExpr tm::TerminalModifiers
{
  top.unparse = r.unparse ++ " " ++ tm.unparse;
  production regex::Regex = r.terminalRegExprSpec;
  local terminalName::String =
    "Prefix_" ++ toString(top.location.line) ++
    case regex.asLiteral of
    | just(a) when isAlpha(a) -> "_" ++ a
    | _ -> ""
    end;
  local terminalFullName::String = top.grammarName ++ ":" ++ terminalName;
  top.syntaxAst <-
    [syntaxTerminal(
       terminalFullName, regex,
       foldr(consTerminalMod, nilTerminalMod(), tm.terminalModifiers))];
  top.genFiles <- terminalTranslation(terminalName, top.grammarName, tm.lexerClasses);
  top.terminalPrefix = makeCopperName(terminalFullName);
}

concrete production newTermTerminalPrefix
top::TerminalPrefix ::= r::RegExpr
{
  top.unparse = r.unparse;
  forwards to newTermModifiersTerminalPrefix(r, terminalModifiersNone(location=top.location), location=top.location);
}

concrete production seperatedTerminalPrefix
top::TerminalPrefix ::= t::String_t
{
  top.unparse = t.lexeme;
  forwards to
    newTermModifiersTerminalPrefix(
      -- We pass the string prefix as a regex that does not contain the prefix separator
      regExpr('/', regexLiteral(substring(1, length(t.lexeme) - 1, t.lexeme)), '/', location=top.location),
      -- Specify which terminals this prefix prefixes.  This is used to find the separator to
      -- append to the regex when normalizing the CST AST
      terminalModifierSingle(
        terminalModifierUsePrefixSeperatorFor(top.prefixedTerminals, location=top.location),
        location=top.location),
      location=top.location);
}

-- Needed when generating seperated terminal declarations, this is pretty useless otherwise so abstract only
abstract production terminalModifierUsePrefixSeperatorFor
top::TerminalModifier ::= terms::[String]
{
  top.unparse = s"use prefix separator for {${implode(", ", terms)}}";

  top.terminalModifiers := [termUsePrefixSeperatorFor(terms)];
  top.errors := [];
}

synthesized attribute prefixItemNames::[String];
nonterminal TerminalPrefixItems with config, env, grammarName, componentGrammarName, compiledGrammars, grammarDependencies, location, unparse, errors, prefixItemNames;

propagate errors on TerminalPrefixItems;

concrete production consTerminalPrefixItem
top::TerminalPrefixItems ::= t::TerminalPrefixItem ',' ts::TerminalPrefixItems
{
  top.unparse = ts.unparse ++ ", " ++ t.unparse;
  top.prefixItemNames = ts.prefixItemNames ++ t.prefixItemNames;
}

concrete production oneTerminalPrefixItem
top::TerminalPrefixItems ::= t::TerminalPrefixItem
{
  top.unparse = t.unparse;
  top.prefixItemNames = t.prefixItemNames;
}

abstract production nilTerminalPrefixItem
top::TerminalPrefixItems ::=
{
  top.unparse = "";
  top.prefixItemNames = [];
}

-- Empty list = prefix all marking terminals
concrete production allTerminalPrefixItems
top::TerminalPrefixItems ::=
{
  -- TODO: Potentially missing marking terminals from conditionally exported grammars
  production med :: ModuleExportedDefs =
    moduleExportedDefs(top.location, top.compiledGrammars, top.grammarDependencies, [top.componentGrammarName], []);

  local syntax::Syntax = foldr(consSyntax, nilSyntax(), med.syntaxAst);
  syntax.containingGrammar = error("This shouldn't be needed...");
  syntax.cstEnv = error("This shouldn't be needed...");
  syntax.cstNTProds = error("This shouldn't be needed...");
  syntax.classTerminals = error("This shouldn't be needed...");
  syntax.parserAttributeAspects = error("This shouldn't be needed...");
  syntax.layoutTerms = error("This shouldn't be needed...");
  syntax.prefixesForTerminals = error("This shouldn't be needed...");
  syntax.superClasses = error("This shouldn't be needed...");
  syntax.subClasses = error("This shouldn't be needed...");

  forwards to
    foldr(
      consTerminalPrefixItem(_, ',', _, location=top.location),
      nilTerminalPrefixItem(location=top.location),
      map(\ sd::Decorated SyntaxDcl ->
        qNameTerminalPrefixItem(qName(top.location, sd.fullName), location=top.location),
        syntax.allMarkingTerminals));
}

nonterminal TerminalPrefixItem with config, env, grammarName, componentGrammarName, compiledGrammars, location, unparse, errors, prefixItemNames;

concrete production qNameTerminalPrefixItem
top::TerminalPrefixItem ::= t::QName
{
  top.unparse = t.unparse;
  top.errors := t.lookupType.errors;
  top.prefixItemNames = [t.lookupType.fullName];
}

concrete production easyTerminalRefTerminalPrefixItem
top::TerminalPrefixItem ::= t::EasyTerminalRef
{
  forwards to
    qNameTerminalPrefixItem(
      qName(top.location, head(t.dcls).fullName),
      location=top.location);
}

-- For now, manually write this to specify priorities between terminals
terminal Prefer_t 'prefer' lexer classes {KEYWORD, RESERVED};
terminal Over_t   'over'   lexer classes {KEYWORD}; -- not RESERVED

concrete production disambiguateParserComponent
top::ParserComponent ::= 'prefer' t::QName 'over' ts::TermList ';'
{
  top.unparse = "prefer " ++ t.unparse ++ " over " ++ ts.unparse;
  top.errors <- t.lookupType.errors;
  
  local pluckTAction::ProductionStmt = Silver_ProductionStmt { pluck $QName{t}; };
  -- Most of these aren't actually needed since we just want the translation. 
  pluckTAction.config = top.config;
  pluckTAction.env = top.env;
  pluckTAction.flowEnv = top.flowEnv;
  pluckTAction.grammarName = top.grammarName;
  pluckTAction.compiledGrammars = top.compiledGrammars;
  pluckTAction.frame = error("Not needed");
  pluckTAction.downSubst = emptySubst();
  pluckTAction.finalSubst = emptySubst();
  
  local tName::String = t.lookupType.dcl.fullName;
  top.syntaxAst <-
    -- Generate a disambiguation function for every combination of ts.
    -- TODO: we can't use Copper's subset disambiguation functions here unfourtunately,
    -- since we currently require those to be disjoint.
    -- Also a slight bug here, if any of ts are undefined we will report errors
    -- more than once.
    map(
      \ tsNames::[String] -> 
        syntaxDisambiguationGroup(
          s"Prefer_${toString(top.location.line)}_${tName}__${implode("__", tsNames)}",
          tName :: tsNames, false, pluckTAction.translation),
      tail(powerSet(ts.termList)));
}

-- Prefix separator
terminal Separator_kwd 'separator' lexer classes {KEYWORD};

concrete production lexerClassModifierPrefixSeperator
top::LexerClassModifier ::= 'prefix' 'separator' s::String_t
{
  top.unparse = s"prefix separator ${s.lexeme}";

  top.lexerClassModifiers := [lexerClassPrefixSeperator(substring(1, length(s.lexeme) - 1, s.lexeme))];
}

{- Not supported due to ambiguity with modifiers on prefix terminal defined
 - inside parser spec.  But not really that useful anyway?
concrete production terminalModifierPrefixSeperator
top::TerminalModifier ::= 'prefix' 'separator' s::String_t
{
  top.unparse = s"prefix separator ${s.lexeme}";

  top.terminalModifiers := [termPrefixSeperator(substring(1, length(s.lexeme) - 1, s.lexeme))];
}
-}
