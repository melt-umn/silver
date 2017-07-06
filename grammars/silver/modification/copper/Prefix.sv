grammar silver:modification:copper;

import core:monad;

import silver:definition:regex;
import silver:extension:easyterminal; -- only Terminal_t, EasyTerminalRef;

terminal Prefix_t 'prefix' lexer classes {KEYWORD, RESERVED};

concrete production prefixParserComponentModifier
top::ParserComponentModifier ::= 'prefix' ts::TerminalPrefixItems 'with' s::TerminalPrefix
{  
  top.pp = "prefix " ++ ts.pp ++ " with " ++ s.pp;
  top.errors := ts.errors ++ s.errors;
  top.terminalPrefixes =
    do (bindList, returnList) {
      t::QName <- ts.prefixItemNames;
      td::Decorated QName =
        decorate t with {
          config = top.config;
          grammarName = top.grammarName;
          env = top.env;
        };
      return pair(td.lookupType.fullName, makeCopperName(s.prefixFullName));
    };
  top.liftedAGDcls = s.liftedAGDcls;
}

synthesized attribute prefixFullName::String;
nonterminal TerminalPrefix with config, env, grammarName, componentGrammarName, compiledGrammars, location, pp, errors, liftedAGDcls, prefixFullName;

concrete production nameTerminalPrefix
top::TerminalPrefix ::= s::QName
{
  top.pp = s.pp;
  top.errors := s.lookupType.errors;
  top.liftedAGDcls = emptyAGDcl(location=top.location);
  top.prefixFullName = s.lookupType.fullName;
}

concrete production newTermModifiersTerminalPrefix
top::TerminalPrefix ::= r::RegExpr tm::TerminalModifiers
{
  top.pp = r.pp ++ " " ++ tm.pp;
  -- Prefix terminal name isn't based off the prefix right now since that might not be alphanumeric
  -- TODO make the terminal name based off alphanumeric characters from the regex for easier debugging of parse conflicts
  local terminalName::String = "_Prefix" ++ toString(genInt());
  top.liftedAGDcls = terminalDclDefault(
    terminalKeywordModifierNone(location=top.location),
    name(terminalName, top.location),
    r, tm,
    location=top.location);
  forwards to nameTerminalPrefix(qName(top.location, terminalName), location=top.location);
}

concrete production newTermTerminalPrefix
top::TerminalPrefix ::= r::RegExpr
{
  top.pp = r.pp;
  forwards to newTermModifiersTerminalPrefix(r, terminalModifiersNone(location=top.location), location=top.location);
}

concrete production seperatedTerminalPrefix
top::TerminalPrefix ::= t::String_t
{
  top.pp = t.lexeme;
  local seperatorLookup::[DclInfo] = getValueDcl("_prefix_seperator", top.env);
  local seperator::String = 
    case seperatorLookup of
      prefixSeparatorDcl(sg, sl, s) :: _ -> s
    | _ -> ""
    end;
  local givenPrefix::String = substring(1, length(t.lexeme) - 1, t.lexeme);
  local regex::RegExpr =
    regExpr('/', literalRegex(givenPrefix ++ seperator), '/', location=top.location);
  top.errors <- 
    case seperatorLookup of
      prefixSeparatorDcl(sg, sl, s) :: _ -> []
    | _ -> [wrn(top.location, "Prefix seperator is not defined, using the empty seperator")]
    end;
  forwards to newTermTerminalPrefix(regex, location=top.location);
}

synthesized attribute prefixItemNames::[QName];
nonterminal TerminalPrefixItems with config, env, grammarName, componentGrammarName, compiledGrammars, grammarDependencies, location, pp, errors, prefixItemNames;

concrete production consTerminalPrefixItem
top::TerminalPrefixItems ::= t::TerminalPrefixItem ',' ts::TerminalPrefixItems
{
  top.pp = ts.pp ++ ", " ++ t.pp;
  top.errors := ts.errors ++ t.errors;
  top.prefixItemNames = ts.prefixItemNames ++ t.prefixItemNames;
}

concrete production oneTerminalPrefixItem
top::TerminalPrefixItems ::= t::TerminalPrefixItem
{
  top.pp = t.pp;
  top.errors := t.errors;
  top.prefixItemNames = t.prefixItemNames;
}

concrete production allTerminalPrefixItem
top::TerminalPrefixItems ::=
{
  production med :: ModuleExportedDefs =
    moduleExportedDefs(top.location, top.compiledGrammars, top.grammarDependencies, [top.componentGrammarName], []);

  local syntax::Syntax = foldr(consSyntax, nilSyntax(), med.syntaxAst);
  syntax.containingGrammar = error("This shouldn't be needed...");
  syntax.cstEnv = error("This shouldn't be needed...");
  syntax.cstNTProds = error("This shouldn't be needed...");
  syntax.prefixesForTerminals = error("This shouldn't be needed...");
  syntax.univLayout = error("This shouldn't be needed...");

  top.pp = "";
  top.errors := [];
  top.prefixItemNames =
    do (bindList, returnList) {
      sd::Decorated SyntaxDcl <- syntax.allMarkingTerminals;
      return qName(top.location, case sd of syntaxTerminal(n, _, _) -> n end);
    };
}

nonterminal TerminalPrefixItem with config, env, grammarName, componentGrammarName, compiledGrammars, location, pp, errors, prefixItemNames;

concrete production qNameTerminalPrefixItem
top::TerminalPrefixItem ::= t::QName
{
  top.pp = t.pp;
  top.errors := t.lookupType.errors;
  top.prefixItemNames = [t];
}

concrete production easyTerminalRefTerminalPrefixItem
top::TerminalPrefixItem ::= t::EasyTerminalRef
{
  top.pp = t.pp;
  top.errors := t.errors;
  top.prefixItemNames = map(qName(top.location, _), map((.fullName), t.dcls));
}

-- For now, manually write this to specify priorities between terminals
terminal Prefer_t 'prefer' lexer classes {KEYWORD, RESERVED};
terminal Over_t   'over'   lexer classes {KEYWORD}; -- not RESERVED

concrete production disambiguateParserComponent
top::ParserComponent ::= 'prefer' t::QName 'over' ts::TermList ';'
{
  top.pp = "prefer " ++ t.pp ++ " over " ++ ts.pp;
  top.errors := t.lookupType.errors ++ ts.errors;
  top.moduleNames = [];
  top.terminalPrefixes = [];
  top.liftedAGDcls =
    disambiguationGroupDcl(
      'disambiguate',
      termListCons(t, ',', ts, location=top.location),
      actionCode_c(
        '{',
        productionStmtsSnoc(
          productionStmtsNil(location=top.location),
          pluckDef('pluck', baseExpr(t, location=top.location), ';', location=top.location),
          location=top.location),
        '}',
        location=top.location),
      location=top.location);
}

-- Prefix seperator
terminal Separator_kwd 'separator' lexer classes {KEYWORD}; -- not RESERVED?

concrete production prefixSeparatorParserComponent
top::ParserComponent ::= 'prefix' 'separator' s::String_t ';'
{
  top.pp = s"prefix separator ${s.lexeme};";
  top.errors := [];
  top.moduleNames = [];
  top.terminalPrefixes = [];
  top.liftedAGDcls = prefixSeparatorAGDcl($1, $2, $3, $4, location=top.location);
}

concrete production prefixSeparatorAGDcl
top::AGDcl ::= 'prefix' 'separator' s::String_t ';'
{
  top.pp = s"prefix separator ${s.lexeme};";
  top.errors := 
    case getValueDcl("_prefix_seperator", top.env) of
      [_] -> []
    | _ -> [err(top.location, "Duplicate prefix seperator declaration")]
    end;
  top.defs = [prefixSeparatorDef(top.grammarName, top.location, substring(1,length(s.lexeme)-1,s.lexeme))];
}