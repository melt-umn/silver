grammar silver:modification:copper;

import silver:extension:easyterminal; -- only Terminal_t, EasyTerminalRef;

terminal Parser_kwd 'parser' lexer classes {KEYWORD}; -- not RESERVED?

-- TODO: You know, maybe parser specs should get moved over here as well.

-- parserDcl now just gets the list of terminals needed to be declared as prefixes then forwards to
-- decls using that list with parserDclBase handling what parserDcl did
concrete production parserDcl
top::AGDcl ::= 'parser' n::Name '::' t::Type '{' m::ParserComponents '}'
{
  top.pp = "parser " ++ m.pp ++ ";"; -- TODO, should this be here?
  
  top.moduleNames = m.moduleNames;
  
  local terminalPrefixNeededDefs :: [String] = nubBy(stringEq, map(snd, m.terminalPrefixes));
  forwards to parserDclBase(n, t, m, location=top.location);{-
    foldr(
      appendAGDcl(_, _, location=top.location),
      parserDclBase(n, t, m, location=top.location),
      map(\n::String ->
        terminalDclDefault(
          terminalKeywordModifierNone(location=top.location),
          -- Prefix terminal name isn't based off the prefix since that might not be alphanumeric
          name("_Prefix" ++ toString(genInt()), top.location),
          regExprEasyTerm(terminal(Terminal_t, n), location=top.location),
          terminalModifiersNone(location=top.location),
          location=top.location),
        terminalPrefixNeededDefs));-}
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
  
  local terminalPrefixNeededDefs :: [String] = nubBy(stringEq, map(snd, m.terminalPrefixes));
  local terminalPrefixDecl :: AGDcl =
    foldr(
      appendAGDcl(_, _, location=top.location),
      emptyAGDcl(location=top.location),
      map(\n::String ->
        terminalDclDefault(
          terminalKeywordModifierNone(location=top.location),
          name("_Prefix" ++ toString(genInt()), top.location), -- Prefix terminal name isn't based off the prefix since that might not be alphanumeric
          regExprEasyTerm(terminal(Terminal_t, n), location=top.location),
          terminalModifiersNone(location=top.location),
          location=top.location),
        terminalPrefixNeededDefs));

  top.parserSpecs = [spec]; -- Note that this is undecorated.
}

nonterminal ParserComponents with config, env, grammarName, location, pp, errors, moduleNames, compiledGrammars, grammarDependencies, terminalPrefixes;

concrete production nilParserComponent
top::ParserComponents ::=
{
  top.pp = "";
  top.moduleNames = [];
  top.errors := [];
  top.terminalPrefixes = [];
}

concrete production consParserComponent
top::ParserComponents ::= c1::ParserComponent  c2::ParserComponents
{
  top.pp = c1.pp ++ ", " ++ c2.pp;
  top.moduleNames = c1.moduleNames ++ c2.moduleNames;
  top.errors := c1.errors ++ c2.errors;
  top.terminalPrefixes = c1.terminalPrefixes ++ c2.terminalPrefixes;
}

nonterminal ParserComponent with config, env, grammarName, location, pp, errors, moduleNames, compiledGrammars, grammarDependencies, terminalPrefixes;

concrete production parserComponent
top::ParserComponent ::= m::ModuleName mods::ParserComponentModifiers ';'
{
  top.pp = m.pp;
  top.moduleNames = m.moduleNames;
  top.errors := m.errors ++ mods.errors;
  top.terminalPrefixes = mods.terminalPrefixes;
  
  mods.env = appendEnv(top.env, toEnv(m.defs));
}

{-- Have special env built from just this parser component -}
nonterminal ParserComponentModifiers with config, env, grammarName, location, pp, errors, terminalPrefixes;

concrete production nilParserComponentModifier
top::ParserComponentModifiers ::=
{
  top.pp = "";
  top.errors := [];
  top.terminalPrefixes = [];
}

concrete production consParserComponentModifier
top::ParserComponentModifiers ::= h::ParserComponentModifier t::ParserComponentModifiers
{
  top.pp = h.pp ++ t.pp;
  top.errors := h.errors ++ t.errors;
  top.terminalPrefixes = h.terminalPrefixes ++ t.terminalPrefixes;
}

nonterminal ParserComponentModifier with config, env, grammarName, location, pp, errors, terminalPrefixes;

terminal Prefix_t 'prefix' lexer classes {KEYWORD}; -- not RESERVED

{-
concrete production prefixParserComponentModifier
top::ParserComponentModifier ::= 'prefix' t::QName 'with' s::Terminal_t
{
  top.pp = "prefix " ++ t.pp ++ " with " ++ s.lexeme;
  top.errors := t.lookupType.errors;
  top.terminalPrefixes = [pair(t.lookupType.fullName, substring(1, length(s.lexeme)-1, s.lexeme))];
}

concrete production prefixQuotedParserComponentModifier
top::ParserComponentModifier ::= 'prefix' t::EasyTerminalRef 'with' s::Terminal_t
{
  top.pp = "prefix " ++ t.pp ++ " with " ++ s.lexeme;
  
  top.errors := t.errors;
  
  top.terminalPrefixes =
    if null(t.dcls) then []
    else [pair(head(t.dcls).fullName, substring(1, length(s.lexeme)-1, s.lexeme))];
  
  --forwards to prefixParserComponentModifier($1, qName(t.location, if null(t.dcls) then "terminal:not:found" else head(t.dcls).fullName), $3, s, location=top.location);
}
-}

concrete production prefixParserComponentModifier
top::ParserComponentModifier ::= 'prefix' t::QName 'with' s::QName
{
  top.pp = "prefix " ++ t.pp ++ " with " ++ s.pp;
  top.errors := t.lookupType.errors ++ s.lookupType.errors;
  top.terminalPrefixes = [pair(t.lookupType.fullName, makeCopperName(s.lookupType.fullName))];
}
{-
concrete production prefixQuotedParserComponentModifier
top::ParserComponentModifier ::= 'prefix' t::EasyTerminalRef 'with' s::EasyTerminalRef
{
  top.pp = "prefix " ++ t.pp ++ " with " ++ s.pp;
  
  top.errors := t.errors;
  
  top.terminalPrefixes =
    if null(t.dcls) then []
    else [pair(head(t.dcls).fullName, substring(1, length(s.lexeme)-1, s.lexeme))];
  
  --forwards to prefixParserComponentModifier($1, qName(t.location, if null(t.dcls) then "terminal:not:found" else head(t.dcls).fullName), $3, s, location=top.location);
}
-}


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

