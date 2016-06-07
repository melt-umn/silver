grammar silver:modification:copper;

import silver:driver:util only computeDependencies;

terminal Parser_kwd 'parser' lexer classes {KEYWORD}; -- not RESERVED?

-- TODO: You know, maybe parser specs should get moved over here as well.

-- TODO: parserDcl now just gets the AGDcls needed to be declared for prefixes then forwards to
-- decls using that list with parserDclBase handling what parserDcl did
-- This means we need to include the current grammar by default
-- These new decls should just get added to the cstAst directly
concrete production parserDcl
top::AGDcl ::= 'parser' n::Name '::' t::Type '{' m::ParserComponents '}'
{
  top.pp = "parser " ++ m.pp ++ ";"; -- TODO?
  
  top.moduleNames = m.moduleNames;

  top.errors := t.errors ++ m.errors ++ liftedAGDcls.errors;

  -- TODO: dunno, should we keep this separate? For now, masquerade as a function.
  -- Only bug is that you can aspect it, but it's pointless to do so, you can't affect anything.
  top.defs = [funDef(top.grammarName, n.location, namedSig)];
  
  production liftedAGDcls :: AGDcl = m.liftedAGDcls;
  liftedAGDcls.config = top.config;
  liftedAGDcls.grammarName = top.grammarName;
  liftedAGDcls.env = m.env;
  liftedAGDcls.compiledGrammars = top.compiledGrammars;
  liftedAGDcls.grammarDependencies = top.grammarDependencies;
  liftedAGDcls.flowEnv = top.flowEnv;
  
  -- Parser spec grammarDependancies based off grammars included in the parser spec
  m.grammarDependencies = computeDependencies(m.moduleNames, top.compiledGrammars);
  
  -- Compute the module exported defs for all grammars in the parser spec to add to the new environment
  production med :: ModuleExportedDefs =
    moduleExportedDefs(top.location, top.compiledGrammars, m.grammarDependencies, m.moduleNames, []);
  
  m.env = appendEnv(toEnv(liftedAGDcls.defs ++ med.defs), top.env);
  
  production fName :: String = top.grammarName ++ ":" ++ n.name;

  production namedSig :: NamedSignature =
    namedSignature(fName,
      [namedSignatureElement("stringToParse", stringTypeExp()),
       namedSignatureElement("filenameToReport", stringTypeExp())],
      namedSignatureElement("__func__lhs", nonterminalTypeExp("core:ParseResult", [t.typerep])),
      []);

  production spec :: ParserSpec =
    parserSpec(top.location, top.grammarName, fName, t.typerep.typeName, m.moduleNames, m.terminalPrefixes, liftedAGDcls.syntaxAst);
  spec.compiledGrammars = top.compiledGrammars;

  top.parserSpecs = [spec]; -- Note that this is undecorated.
}

synthesized attribute liftedAGDcls::AGDcl;

nonterminal ParserComponents with config, env, grammarName, location, pp, errors, moduleNames, compiledGrammars, grammarDependencies, terminalPrefixes, liftedAGDcls;

concrete production nilParserComponent
top::ParserComponents ::=
{
  top.pp = "";
  top.moduleNames = [];
  top.errors := [];
  top.terminalPrefixes = [];
  top.liftedAGDcls = emptyAGDcl(location=top.location);
}

concrete production consParserComponent
top::ParserComponents ::= c1::ParserComponent  c2::ParserComponents
{
  top.pp = c1.pp ++ ", " ++ c2.pp;
  top.moduleNames = c1.moduleNames ++ c2.moduleNames;
  top.errors := c1.errors ++ c2.errors;
  top.terminalPrefixes = c1.terminalPrefixes ++ c2.terminalPrefixes;
  top.liftedAGDcls = appendAGDcl(c1.liftedAGDcls, c2.liftedAGDcls, location=top.location);
}

nonterminal ParserComponent with config, env, grammarName, location, pp, errors, moduleNames, compiledGrammars, grammarDependencies, terminalPrefixes, liftedAGDcls;

concrete production parserComponent
top::ParserComponent ::= m::ModuleName mods::ParserComponentModifiers ';'
{
  top.pp = m.pp;
  top.moduleNames = m.moduleNames;
  top.errors := m.errors ++ mods.errors;
  top.terminalPrefixes = mods.terminalPrefixes;
  top.liftedAGDcls = mods.liftedAGDcls;
  
  mods.componentGrammarName = head(m.moduleNames);
}

autocopy attribute componentGrammarName::String;

{-- Have special env built from just this parser component and the global env -}
nonterminal ParserComponentModifiers with config, env, grammarName, componentGrammarName, compiledGrammars, grammarDependencies, location, pp, errors, terminalPrefixes, liftedAGDcls;

concrete production nilParserComponentModifier
top::ParserComponentModifiers ::=
{
  top.pp = "";
  top.errors := [];
  top.terminalPrefixes = [];
  top.liftedAGDcls = emptyAGDcl(location=top.location);
}

concrete production consParserComponentModifier
top::ParserComponentModifiers ::= h::ParserComponentModifier t::ParserComponentModifiers
{
  top.pp = h.pp ++ t.pp;
  top.errors := h.errors ++ t.errors;
  top.terminalPrefixes = h.terminalPrefixes ++ t.terminalPrefixes;
  top.liftedAGDcls = appendAGDcl(h.liftedAGDcls, t.liftedAGDcls, location=top.location);
}

nonterminal ParserComponentModifier with config, env, grammarName, componentGrammarName, compiledGrammars, grammarDependencies, location, pp, errors, terminalPrefixes, liftedAGDcls;

-- Separate bit translating the parser declaration.
aspect production parserDcl
top::AGDcl ::= 'parser' n::Name '::' t::Type '{' m::ParserComponents '}'
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

