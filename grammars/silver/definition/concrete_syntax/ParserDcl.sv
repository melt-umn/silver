grammar silver:definition:concrete_syntax;

terminal Parser_kwd /parser/ lexer classes {KEYWORD};

concrete production parserDcl
top::AGDcl ::= 'parser' n::Name '::' t::Type '{' m::ModuleList '}'
{
  top.pp = "parser " ++ m.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);
  
  top.moduleNames = m.moduleNames;

  top.errors := t.errors ++ m.errors;

  -- TODO: dunno, should we keep this separate? For now, masquerade as a function.
  -- Only bug is that you can aspect it, but it's pointless to do so, you can't affect anything.
  top.defs = addFunDcl(top.grammarName, n.location, namedSig, emptyDefs());
  
  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ n.name;

  production attribute namedSig :: NamedSignature;
  namedSig = namedSignature(fName,
                               [namedSignatureElement("stringToParse", stringTypeExp()),
                                namedSignatureElement("filenameToReport", stringTypeExp())],
                               namedSignatureElement("__func__lhs", nonterminalTypeExp("core:ParseResult", [t.typerep])));

  top.parserSpecs = [parserSpec(top.location, top.grammarName, fName, t.typerep.typeName, m.moduleNames)];

  forwards to defaultAGDcl();
}

nonterminal ModuleList with config, location, grammarName, file, moduleNames, compiledGrammars, errors, pp;

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


