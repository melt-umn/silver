grammar silver:modification:copper;

terminal Parser_kwd 'parser' lexer classes {KEYWORD};

-- TODO: You know, maybe parser specs should get moved over here as well.

concrete production parserDcl
top::AGDcl ::= 'parser' n::Name '::' t::Type '{' m::ModuleList '}'
{
  top.pp = "parser " ++ m.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);
  
  top.moduleNames = m.moduleNames;

  top.errors := t.errors ++ m.errors;

  -- TODO: dunno, should we keep this separate? For now, masquerade as a function.
  -- Only bug is that you can aspect it, but it's pointless to do so, you can't affect anything.
  top.defs = [funDef(top.grammarName, n.location, namedSig)];
  
  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ n.name;

  production attribute namedSig :: NamedSignature;
  namedSig = namedSignature(fName,
                               [namedSignatureElement("stringToParse", stringTypeExp()),
                                namedSignatureElement("filenameToReport", stringTypeExp())],
                               namedSignatureElement("__func__lhs", nonterminalTypeExp("core:ParseResult", [t.typerep])),
                               []);

  production spec :: ParserSpec = parserSpec(top.location, top.grammarName, fName, t.typerep.typeName, m.moduleNames);
  spec.compiledGrammars = top.compiledGrammars;

  top.parserSpecs = [spec]; -- Note that this is undecorated.
}

nonterminal ModuleList with config, location, grammarName, file, moduleNames, compiledGrammars, errors, pp, grammarDependencies;

concrete production moduleListOne
top::ModuleList ::= c1::ModuleName ';'
{
  top.pp = c1.pp;
  top.location = c1.location;
  top.moduleNames = c1.moduleNames;

  top.errors := c1.errors;
}

concrete production moduleListCons
top::ModuleList ::= c1::ModuleName ';' c2::ModuleList
{
  top.pp = c1.pp ++ ", " ++ c2.pp;
  top.location = c1.location;
  top.moduleNames = c1.moduleNames ++ c2.moduleNames;

  top.errors := c1.errors ++ c2.errors;
}


aspect production parserDcl
top::AGDcl ::= 'parser' n::Name '::' t::Type '{' m::ModuleList '}'
{
  local attribute className :: String;
  className = "P" ++ n.name;

  local attribute packageName :: String;
  packageName = makeName(top.grammarName);

  local attribute parserName :: String;
  parserName = makeParserName(fName);

  top.initWeaving := "\tpublic static int " ++ localVar ++ " = 0;\n";
  top.valueWeaving := "";

  local attribute localVar :: String;
  localVar = "count_local__ON__" ++ makeIdName(fName);

  top.genFiles :=
    [pair(className ++ ".java",
          generateFunctionClassString(top.grammarName, n.name, namedSig, parseResult)),
     pair(makeParserName(spec.fullName) ++ ".copper",
          spec.cstAst.xmlCopper)];
  
  local attribute parseResult :: String;
  parseResult = 
   "try {\n" ++
"\t\t\treturn new core.PparseSucceeded( new " ++ packageName ++ "." ++ parserName ++ "().parse(new java.io.StringReader(((common.StringCatter)common.Util.demand(c_stringToParse)).toString()), ((common.StringCatter)common.Util.demand(c_filenameToReport)).toString()) );\n" ++
"\t\t} catch(edu.umn.cs.melt.copper.runtime.logging.CopperParserException e) {\n" ++
"\t\t\treturn new core.PparseFailed( new common.StringCatter(e.getMessage()) );\n" ++
"\t\t} catch(Throwable t) {\n" ++
"\t\t\tthrow new common.exceptions.TraceException(\"An error occured while parsing\", t);\n" ++
"\t\t}\n";
}

