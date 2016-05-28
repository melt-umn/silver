grammar silver:modification:copper;

import core:monad;

import silver:extension:easyterminal; -- only Terminal_t, EasyTerminalRef;

terminal Parser_kwd 'parser' lexer classes {KEYWORD}; -- not RESERVED?

-- TODO: You know, maybe parser specs should get moved over here as well.

-- parserDcl now just gets the AGDcls needed to be declared for prefixes then forwards to
-- decls using that list with parserDclBase handling what parserDcl did
-- Also include the current grammar by default
concrete production parserDcl
top::AGDcl ::= 'parser' n::Name '::' t::Type '{' m::ParserComponents '}'
{
  top.pp = "parser " ++ m.pp ++ ";"; -- TODO, should this be here?
  
  top.moduleNames = m.moduleNames;
  
  forwards to
    appendAGDcl(
      m.liftedAGDcls,
      parserDclBase(
        n, t,
        consParserComponent(
          parserComponent(
            moduleName(qName(top.location, top.grammarName), location=top.location),
            nilParserComponentModifier(location=top.location),
            ';',
            location=top.location),
          m.liftedComponents,
          location=top.location),
        location=top.location),
      location=top.location);
}

abstract production parserDclBase
top::AGDcl ::= n::Name t::Type m::ParserComponents
{
  top.pp = "parser " ++ m.pp ++ ";"; -- TODO?
  
  top.moduleNames = m.moduleNames;

  top.errors := t.errors ++ m.errors;

  -- TODO: dunno, should we keep this separate? For now, masquerade as a function.
  -- Only bug is that you can aspect it, but it's pointless to do so, you can't affect anything.
  top.defs = [funDef(top.grammarName, n.location, namedSig)];
  
  production fName :: String = top.grammarName ++ ":" ++ n.name;

  production namedSig :: NamedSignature =
    namedSignature(fName,
      [namedSignatureElement("stringToParse", stringTypeExp()),
       namedSignatureElement("filenameToReport", stringTypeExp())],
      namedSignatureElement("__func__lhs", nonterminalTypeExp("core:ParseResult", [t.typerep])),
      []);

  production spec :: ParserSpec =
    parserSpec(top.location, top.grammarName, fName, t.typerep.typeName, m.moduleNames, m.terminalPrefixes);
  spec.compiledGrammars = top.compiledGrammars;

  top.parserSpecs = [spec]; -- Note that this is undecorated.
}

synthesized attribute liftedAGDcls::AGDcl;
synthesized attribute liftedComponents<a>::a;

nonterminal ParserComponents with config, env, grammarName, location, pp, errors, moduleNames, compiledGrammars, grammarDependencies, terminalPrefixes, liftedAGDcls, liftedComponents<ParserComponents>;

concrete production nilParserComponent
top::ParserComponents ::=
{
  top.pp = "";
  top.moduleNames = [];
  top.errors := [];
  top.terminalPrefixes = [];
  top.liftedAGDcls = emptyAGDcl(location=top.location);
  propagate liftedComponents;
}

concrete production consParserComponent
top::ParserComponents ::= c1::ParserComponent  c2::ParserComponents
{
  top.pp = c1.pp ++ ", " ++ c2.pp;
  top.moduleNames = c1.moduleNames ++ c2.moduleNames;
  top.errors := c1.errors ++ c2.errors;
  top.terminalPrefixes = c1.terminalPrefixes ++ c2.terminalPrefixes;
  top.liftedAGDcls = appendAGDcl(c1.liftedAGDcls, c2.liftedAGDcls, location=top.location);
  propagate liftedComponents;
}

nonterminal ParserComponent with config, env, grammarName, location, pp, errors, moduleNames, compiledGrammars, grammarDependencies, terminalPrefixes, liftedAGDcls, liftedComponents<ParserComponent>;

concrete production parserComponent
top::ParserComponent ::= m::ModuleName mods::ParserComponentModifiers ';'
{
  top.pp = m.pp;
  top.moduleNames = m.moduleNames;
  top.errors := m.errors ++ mods.errors;
  top.terminalPrefixes = mods.terminalPrefixes;
  top.liftedAGDcls = mods.liftedAGDcls;
  
  mods.env = appendEnv(top.env, toEnv(m.defs));
  mods.componentGrammarName = head(m.moduleNames);
  propagate liftedComponents;
}

autocopy attribute componentGrammarName::String;

{-- Have special env built from just this parser component and the global env -}
nonterminal ParserComponentModifiers with config, env, grammarName, componentGrammarName, compiledGrammars, location, pp, errors, terminalPrefixes, liftedAGDcls, liftedComponents<ParserComponentModifiers>;

concrete production nilParserComponentModifier
top::ParserComponentModifiers ::=
{
  top.pp = "";
  top.errors := [];
  top.terminalPrefixes = [];
  top.liftedAGDcls = emptyAGDcl(location=top.location);
  propagate liftedComponents;
}

concrete production consParserComponentModifier
top::ParserComponentModifiers ::= h::ParserComponentModifier t::ParserComponentModifiers
{
  top.pp = h.pp ++ t.pp;
  top.errors := h.errors ++ t.errors;
  top.terminalPrefixes = h.terminalPrefixes ++ t.terminalPrefixes;
  top.liftedAGDcls = appendAGDcl(h.liftedAGDcls, t.liftedAGDcls, location=top.location);
  propagate liftedComponents;
}

nonterminal ParserComponentModifier with config, env, grammarName, componentGrammarName, compiledGrammars, location, pp, errors, terminalPrefixes, liftedAGDcls, liftedComponents<ParserComponentModifier>;

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

concrete production prefixNewTermModifiersParserComponentModifier
top::ParserComponentModifier ::= 'prefix' ts::TerminalPrefixItems 'with' r::RegExpr tm::TerminalModifiers
{
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
  local syntax::Syntax = foldr(consSyntax, nilSyntax(), head(searchEnvTree(top.componentGrammarName, top.compiledGrammars)).syntaxAst);
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

-- Separate bit translating the parser declaration.
aspect production parserDclBase
top::AGDcl ::= n::Name t::Type m::ParserComponents
{
  local className :: String = "P" ++ n.name;

  local packageName :: String = makeName(top.grammarName);

  local parserName :: String = makeParserName(fName);

  top.initWeaving := "\tpublic static int " ++ localVar ++ " = 0;\n";
  top.valueWeaving := "";

  local localVar :: String = "count_local__ON__" ++ makeIdName(fName);

  -- We generate the copper files in BuildProcess instead of here, so that they
  -- are regenerated when a dependency changes.
  
  top.genFiles :=
    [pair(className ++ ".java",
          generateFunctionClassString(top.grammarName, n.name, namedSig, parseResult))];
  
  local parseResult :: String =
    s"""return common.Util.callCopperParser(new ${packageName}.${parserName}(), c_stringToParse, c_filenameToReport);""";
}

