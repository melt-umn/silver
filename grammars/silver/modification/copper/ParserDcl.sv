grammar silver:modification:copper;

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

-- Just putting these here, for now


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

