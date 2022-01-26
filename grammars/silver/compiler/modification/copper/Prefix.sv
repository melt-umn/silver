grammar silver:compiler:modification:copper;


import silver:regex;
import silver:compiler:extension:easyterminal; -- only Terminal_t, EasyTerminalRef;

terminal Prefix_t 'prefix' lexer classes {KEYWORD, RESERVED};

concrete production prefixParserComponentModifier
top::ParserComponentModifier ::= 'prefix' ts::TerminalPrefixItems 'with' s::TerminalPrefix
{  
  top.unparse = "prefix " ++ ts.unparse ++ " with " ++ s.unparse;
  top.terminalPrefixes <- map(pair(_, s.terminalPrefix), ts.prefixItemNames);
  top.grammarTerminalPrefixes <-
    if ts.isAllMarking then [pair(top.componentGrammarName, s.terminalPrefix)] else [];
  s.prefixedTerminals = ts.prefixItemNames;
  s.prefixedGrammars = if ts.isAllMarking then [top.componentGrammarName] else [];
}

autocopy attribute prefixedTerminals::[String];
autocopy attribute prefixedGrammars::[String];
synthesized attribute terminalPrefix::String;
nonterminal TerminalPrefix with config, env, flowEnv, grammarName, componentGrammarName, compiledGrammars, prefixedTerminals, prefixedGrammars, location, unparse, errors, syntaxAst, genFiles, terminalPrefix;

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
    [ syntaxTerminal(
        terminalFullName, regex,
        foldr(consTerminalMod, nilTerminalMod(), tm.terminalModifiers),
        location=top.location, sourceGrammar=top.grammarName)
    ];
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
      regExpr(regexLiteral(substring(1, length(t.lexeme) - 1, t.lexeme)), location=top.location),
      -- Specify which terminals this prefix prefixes.  This is used to find the separator to
      -- append to the regex when normalizing the CST AST
      terminalModifierSingle(
        terminalModifierUsePrefixSeperatorFor(top.prefixedTerminals, top.prefixedGrammars, location=top.location),
        location=top.location),
      location=top.location);
}

-- Needed when generating seperated terminal declarations, this is pretty useless otherwise so abstract only
abstract production terminalModifierUsePrefixSeperatorFor
top::TerminalModifier ::= terms::[String]  grams::[String]
{
  top.unparse = s"use prefix separator for {${implode(", ", terms)}} {${implode(", ", grams)}}";

  top.terminalModifiers := [termUsePrefixSeperatorFor(terms, grams)];
  top.errors := [];
}

synthesized attribute prefixItemNames::[String];
synthesized attribute isAllMarking::Boolean;
nonterminal TerminalPrefixItems with config, env, grammarName, componentGrammarName, compiledGrammars, grammarDependencies, location, unparse, errors, prefixItemNames, isAllMarking;

propagate errors on TerminalPrefixItems;

concrete production consTerminalPrefixItem
top::TerminalPrefixItems ::= t::TerminalPrefixItem ',' ts::TerminalPrefixItems
{
  top.unparse = ts.unparse ++ ", " ++ t.unparse;
  top.prefixItemNames = ts.prefixItemNames ++ t.prefixItemNames;
  top.isAllMarking = ts.isAllMarking;
}

concrete production oneTerminalPrefixItem
top::TerminalPrefixItems ::= t::TerminalPrefixItem
{
  top.unparse = t.unparse;
  top.prefixItemNames = t.prefixItemNames;
  top.isAllMarking = false;
}

abstract production nilTerminalPrefixItem
top::TerminalPrefixItems ::=
{
  top.unparse = "";
  top.prefixItemNames = [];
  top.isAllMarking = false;
}

-- Empty list = prefix all marking terminals
concrete production allMarkingTerminalPrefixItems
top::TerminalPrefixItems ::=
{
  top.unparse = "all_marking"; -- Doesn't match cst but whatever
  top.prefixItemNames = [];
  top.isAllMarking = true;
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
  pluckTAction.originRules = [];
  
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
          tName :: tsNames, false, pluckTAction.translation,
          location=top.location, sourceGrammar=top.grammarName),
      tail(powerSet(ts.termList)));
}

-- Prefix separator
terminal Separator_kwd 'separator' lexer classes {KEYWORD};

concrete production lexerClassModifierPrefixSeperator
top::LexerClassModifier ::= 'prefix' 'separator' s::String_t
{
  top.unparse = s"prefix separator ${s.lexeme}";

  top.lexerClassModifiers :=
    [ lexerClassPrefixSeperator(
        substring(1, length(s.lexeme) - 1, s.lexeme),
        location=top.location, sourceGrammar=top.grammarName)
    ];
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
