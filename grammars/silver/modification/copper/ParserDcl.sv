grammar silver:modification:copper;

import silver:driver:util only computeDependencies;

terminal Parser_kwd 'parser' lexer classes {KEYWORD}; -- not RESERVED?

-- TODO: You know, maybe parser specs should get moved over here as well.

concrete production parserDcl
top::AGDcl ::= 'parser' n::Name '::' t::TypeExpr '{' m::ParserComponents '}'
{
  top.unparse = "parser " ++ m.unparse ++ ";"; -- TODO?
  
  top.moduleNames = m.moduleNames;

  top.errors := t.errors ++ m.errors ++ liftedAGDcls.errors;

  -- Right now parsers masquerade as functions. This is probably fine.
  -- Only bug is that you can aspect it, but it's pointless to do so, you can't affect anything.
  top.defs = [funDef(top.grammarName, n.location, namedSig)];
  
  -- TODO: These declarations should probably bubble up to the top level instead of being decorated here
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
      [namedSignatureElement("stringToParse", stringType()),
       namedSignatureElement("filenameToReport", stringType())],
      namedSignatureElement("__func__lhs", nonterminalType("core:ParseResult", [t.typerep])),
      []);

  production spec :: ParserSpec =
    parserSpec(top.location, top.grammarName, fName, t.typerep.typeName, m.moduleNames, m.customLayout, m.terminalPrefixes, liftedAGDcls.syntaxAst);
  spec.compiledGrammars = top.compiledGrammars;

  top.parserSpecs = [spec]; -- Note that this is undecorated.
}

synthesized attribute liftedAGDcls::AGDcl;

nonterminal ParserComponents with config, env, grammarName, location, unparse, errors, moduleNames, compiledGrammars, grammarDependencies, terminalPrefixes, liftedAGDcls;

concrete production nilParserComponent
top::ParserComponents ::=
{
  top.unparse = "";
  top.moduleNames = [];
  top.errors := [];
  top.terminalPrefixes = [];
  top.liftedAGDcls = emptyAGDcl(location=top.location);
}

concrete production consParserComponent
top::ParserComponents ::= c1::ParserComponent  c2::ParserComponents
{
  top.unparse = c1.unparse ++ ", " ++ c2.unparse;
  top.moduleNames = c1.moduleNames ++ c2.moduleNames;
  top.errors := c1.errors ++ c2.errors;
  top.terminalPrefixes = c1.terminalPrefixes ++ c2.terminalPrefixes;
  top.liftedAGDcls = appendAGDcl(c1.liftedAGDcls, c2.liftedAGDcls, location=top.location);
}

closed nonterminal ParserComponent with config, env, grammarName, location, unparse, errors, moduleNames, compiledGrammars, grammarDependencies, terminalPrefixes, liftedAGDcls;

aspect default production
top::ParserComponent ::=
{
  top.moduleNames = [];
  top.terminalPrefixes = [];
  top.liftedAGDcls = emptyAGDcl(location=top.location);
}

concrete production parserComponent
top::ParserComponent ::= m::ModuleName mods::ParserComponentModifiers ';'
{
  top.unparse = m.unparse;
  top.moduleNames = m.moduleNames;
  top.errors := m.errors ++ mods.errors;
  top.terminalPrefixes = mods.terminalPrefixes;
  top.liftedAGDcls = mods.liftedAGDcls;
  
  mods.componentGrammarName = head(m.moduleNames);
}

autocopy attribute componentGrammarName::String;

{-- Have special env built from just this parser component and the global env -}
nonterminal ParserComponentModifiers with config, env, grammarName, componentGrammarName, compiledGrammars, grammarDependencies, location, unparse, errors, terminalPrefixes, liftedAGDcls;

concrete production nilParserComponentModifier
top::ParserComponentModifiers ::=
{
  top.unparse = "";
  top.errors := [];
  top.terminalPrefixes = [];
  top.liftedAGDcls = emptyAGDcl(location=top.location);
}

concrete production consParserComponentModifier
top::ParserComponentModifiers ::= h::ParserComponentModifier t::ParserComponentModifiers
{
  top.unparse = h.unparse ++ t.unparse;
  top.errors := h.errors ++ t.errors;
  top.terminalPrefixes = h.terminalPrefixes ++ t.terminalPrefixes;
  top.liftedAGDcls = appendAGDcl(h.liftedAGDcls, t.liftedAGDcls, location=top.location);
}

nonterminal ParserComponentModifier with config, env, grammarName, componentGrammarName, compiledGrammars, grammarDependencies, location, unparse, errors, terminalPrefixes, liftedAGDcls;

aspect default production
top::ParserComponentModifier ::=
{
  top.terminalPrefixes = [];
  top.liftedAGDcls = emptyAGDcl(location=top.location);
}

-- Separate bit translating the parser declaration.
aspect production parserDcl
top::AGDcl ::= 'parser' n::Name '::' t::TypeExpr '{' m::ParserComponents '}'
{
  local className :: String = "P" ++ n.name;

  local packageName :: String = makeName(top.grammarName);

  local parserName :: String = makeParserName(fName);

  top.initWeaving := "\tpublic static int " ++ localVar ++ " = 0;\n";
  top.valueWeaving := "";

  local localVar :: String = "count_local__ON__" ++ makeIdName(fName);

  -- We generate the copper files in BuildProcess instead of here, so that they
  -- are regenerated when a dependency changes.
  
  -- TODO: As a hack, even though we don't propogates defs up to the top level, we
  -- do generate files for the lifted dcl. Needed to generate terminal class files.
  top.genFiles := liftedAGDcls.genFiles ++
    [pair(className ++ ".java",
          generateFunctionClassString(top.grammarName, n.name, namedSig, parseResult))];
  
  local parseResult :: String =
    s"""return common.Util.callCopperParser(new ${packageName}.${parserName}(), c_stringToParse, c_filenameToReport);""";
}

