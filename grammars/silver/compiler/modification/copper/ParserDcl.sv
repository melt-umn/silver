grammar silver:compiler:modification:copper;

import silver:compiler:driver:util only computeDependencies;

terminal Parser_kwd 'parser' lexer classes {KEYWORD,RESERVED};

-- TODO: You know, maybe parser specs should get moved over here as well.

concrete production parserDcl
top::AGDcl ::= 'parser' n::Name '::' t::TypeExpr '{' m::ParserComponents '}'
{
  top.unparse = "parser " ++ m.unparse ++ ";"; -- TODO?
  
  propagate errors, moduleNames;

  -- Right now parsers masquerade as functions. This is probably fine.
  -- Only bug is that you can aspect it, but it's pointless to do so, you can't affect anything.
  top.defs := [funDef(top.grammarName, n.location, namedSig)];
  
  -- Parser spec grammarDependancies based off grammars included in the parser spec
  m.grammarDependencies = computeDependencies(m.moduleNames, top.compiledGrammars);
  
  -- Compute the module exported defs for all grammars in the parser spec to add to the new environment
  production med :: ModuleExportedDefs =
    moduleExportedDefs(top.location, top.compiledGrammars, m.grammarDependencies, m.moduleNames, []);
  
  m.env = appendEnv(toEnv(med.defs), top.env);
  
  production fName :: String = top.grammarName ++ ":" ++ n.name;

  production namedSig :: NamedSignature =
    namedSignature(fName, [],
      [namedSignatureElement("stringToParse", stringType()),
       namedSignatureElement("filenameToReport", stringType())],
      namedSignatureElement("__func__lhs", appType(nonterminalType("silver:core:ParseResult", [starKind()], false), t.typerep)),
      []);

  production spec :: ParserSpec =
    parserSpec(fName, t.typerep.typeName, m.moduleNames, m.customLayout, m.terminalPrefixes, m.grammarTerminalPrefixes, m.syntaxAst, sourceGrammar=top.grammarName, location=top.location);
  spec.compiledGrammars = top.compiledGrammars;

  top.parserSpecs := [spec]; -- Note that this is undecorated.
}

nonterminal ParserComponents with config, env, flowEnv, grammarName, location, unparse, errors, moduleNames, compiledGrammars, grammarDependencies, terminalPrefixes, grammarTerminalPrefixes, syntaxAst, genFiles;

propagate errors, moduleNames, terminalPrefixes, grammarTerminalPrefixes, syntaxAst, genFiles on ParserComponents;

concrete production nilParserComponent
top::ParserComponents ::=
{
  top.unparse = "";
}

concrete production consParserComponent
top::ParserComponents ::= c1::ParserComponent  c2::ParserComponents
{
  top.unparse = c1.unparse ++ ", " ++ c2.unparse;
}

closed nonterminal ParserComponent with config, env, flowEnv, grammarName, location, unparse, errors, moduleNames, compiledGrammars, grammarDependencies, terminalPrefixes, grammarTerminalPrefixes, syntaxAst, genFiles;

propagate errors, moduleNames, terminalPrefixes, grammarTerminalPrefixes, syntaxAst, genFiles on ParserComponent;

aspect default production
top::ParserComponent ::=
{
  propagate errors, moduleNames, terminalPrefixes, syntaxAst, genFiles;
}

concrete production parserComponent
top::ParserComponent ::= m::ModuleName mods::ParserComponentModifiers ';'
{
  top.unparse = m.unparse;
  
  mods.componentGrammarName = head(m.moduleNames);
}

autocopy attribute componentGrammarName::String;

{-- Have special env built from just this parser component and the global env -}
nonterminal ParserComponentModifiers with config, env, flowEnv, grammarName, componentGrammarName, compiledGrammars, grammarDependencies, location, unparse, errors, terminalPrefixes, grammarTerminalPrefixes, syntaxAst, genFiles;

propagate errors, terminalPrefixes, grammarTerminalPrefixes, syntaxAst, genFiles on ParserComponentModifiers;

concrete production nilParserComponentModifier
top::ParserComponentModifiers ::=
{
  top.unparse = "";
}

concrete production consParserComponentModifier
top::ParserComponentModifiers ::= h::ParserComponentModifier t::ParserComponentModifiers
{
  top.unparse = h.unparse ++ t.unparse;
}

nonterminal ParserComponentModifier with config, env, flowEnv, grammarName, componentGrammarName, compiledGrammars, grammarDependencies, location, unparse, errors, terminalPrefixes, grammarTerminalPrefixes, syntaxAst, genFiles;

propagate errors, terminalPrefixes, grammarTerminalPrefixes, syntaxAst, genFiles on ParserComponentModifier;

aspect default production
top::ParserComponentModifier ::=
{
  propagate errors, terminalPrefixes, syntaxAst, genFiles;
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
  top.genFiles := m.genFiles ++
    [pair(className ++ ".java",
          generateFunctionClassString(top.grammarName, n.name, namedSig, parseResult))];
  
  local parseResult :: String =
    s"""return common.Util.callCopperParser(new ${packageName}.${parserName}(), c_stringToParse, c_filenameToReport);""";
}

