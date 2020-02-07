grammar silver:modification:copper;

import core:monad;

import silver:definition:regex;
import silver:extension:easyterminal; -- only Terminal_t, EasyTerminalRef;

terminal Prefix_t 'prefix' lexer classes {KEYWORD, RESERVED};

concrete production prefixParserComponentModifier
top::ParserComponentModifier ::= 'prefix' ts::TerminalPrefixItems 'with' s::TerminalPrefix
{  
  top.unparse = "prefix " ++ ts.unparse ++ " with " ++ s.unparse;
  top.errors := ts.errors ++ s.errors;
  top.terminalPrefixes = map(pair(_, s.terminalPrefix), ts.prefixItemNames);
  top.liftedAGDcls = s.liftedAGDcls;
  s.prefixedTerminals = ts.prefixItemNames;
}

autocopy attribute prefixedTerminals::[String];
synthesized attribute terminalPrefix::String;
nonterminal TerminalPrefix with config, env, grammarName, componentGrammarName, compiledGrammars, prefixedTerminals, location, unparse, errors, liftedAGDcls, terminalPrefix;

concrete production nameTerminalPrefix
top::TerminalPrefix ::= s::QName
{
  top.unparse = s.unparse;
  top.errors := s.lookupType.errors;
  top.liftedAGDcls = emptyAGDcl(location=top.location);
  top.terminalPrefix = makeCopperName(s.lookupType.fullName);
}

concrete production newTermModifiersTerminalPrefix
top::TerminalPrefix ::= r::RegExpr tm::TerminalModifiers
{
  top.unparse = r.unparse ++ " " ++ tm.unparse;
  top.errors := []; -- r and tm get checked when liftedAGDcls gets decorated
  -- Prefix terminal name isn't based off the prefix right now since that might not be alphanumeric
  -- TODO make the terminal name based off alphanumeric characters from the regex for easier debugging of parse conflicts
  local terminalName::String = "Prefix_" ++ toString(genInt());
  top.liftedAGDcls =
    terminalDclDefault(
      terminalKeywordModifierNone(location=top.location),
      name(terminalName, top.location),
      r, tm,
      location=top.location);
  top.terminalPrefix = makeCopperName(top.grammarName ++ ":" ++ terminalName);
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

  top.terminalModifiers = [termUsePrefixSeperatorFor(terms)];
  top.errors := [];
}

synthesized attribute prefixItemNames::[String];
nonterminal TerminalPrefixItems with config, env, grammarName, componentGrammarName, compiledGrammars, grammarDependencies, location, unparse, errors, prefixItemNames;

concrete production consTerminalPrefixItem
top::TerminalPrefixItems ::= t::TerminalPrefixItem ',' ts::TerminalPrefixItems
{
  top.unparse = ts.unparse ++ ", " ++ t.unparse;
  top.errors := ts.errors ++ t.errors;
  top.prefixItemNames = ts.prefixItemNames ++ t.prefixItemNames;
}

concrete production oneTerminalPrefixItem
top::TerminalPrefixItems ::= t::TerminalPrefixItem
{
  top.unparse = t.unparse;
  top.errors := t.errors;
  top.prefixItemNames = t.prefixItemNames;
}

abstract production nilTerminalPrefixItem
top::TerminalPrefixItems ::=
{
  top.unparse = "";
  top.errors := [];
  top.prefixItemNames = [];
}

concrete production allTerminalPrefixItems
top::TerminalPrefixItems ::=
{
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
  top.errors := t.lookupType.errors ++ ts.errors;
  top.moduleNames = [];
  top.terminalPrefixes = [];
  top.liftedAGDcls =
    -- Generate a disambiguation function for every combination of ts.
    -- TODO: we can't use Copper's subset disambiguation functions here unfourtunately,
    -- since we currently require those to be disjoint.
    -- Also a slight bug here, if any of ts are undefined we will report errors
    -- more than once.
    foldr1(
      appendAGDcl(_, _, location=top.location),
      map(
        \ ts::[QName] -> 
          disambiguationGroupDcl(
            'disambiguate',
            foldr(
              termList(_, _, location=top.location),
              termListNull(location=top.location),
              t :: ts),
            actionCode_c(
              '{',
              productionStmtsSnoc(
                productionStmtsNil(location=top.location),
                pluckDef(
                  'pluck', baseExpr(t, location=top.location), ';',
                  location=top.location),
                location=top.location),
              '}',
             location=top.location),
            -- HACK: disambiguation function names are based on line number.  TODO fixme
            location=loc("prefix", genInt() * 1234, -1, -1, -1, -1, -1)),
        tail(powerSet(ts.qnames))));
}

-- Prefix separator
terminal Separator_kwd 'separator' lexer classes {KEYWORD};

concrete production lexerClassModifierPrefixSeperator
top::LexerClassModifier ::= 'prefix' 'separator' s::String_t
{
  top.unparse = s"prefix separator ${s.lexeme}";

  top.lexerClassModifiers = [lexerClassPrefixSeperator(substring(1, length(s.lexeme) - 1, s.lexeme))];
  top.errors := [];
}

{- Not supported due to ambiguity with modifiers on prefix terminal defined
 - inside parser spec.  But not really that useful anyway?
concrete production terminalModifierPrefixSeperator
top::TerminalModifier ::= 'prefix' 'separator' s::String_t
{
  top.unparse = s"prefix separator ${s.lexeme}";

  top.terminalModifiers = [termPrefixSeperator(substring(1, length(s.lexeme) - 1, s.lexeme))];
  top.errors := [];
}
-}
