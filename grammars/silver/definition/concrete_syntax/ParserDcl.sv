grammar silver:definition:concrete_syntax;

nonterminal ModuleList with location, grammarName, file, moduleNames, compiledGrammars, errors, pp;

terminal Parser_kwd /parser/ lexer classes {KEYWORD};

concrete production nameIdParser
top::Name ::= /parser/
{
  forwards to nameIdLower(terminal(IdLower_t, "parser", $1));
}

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

  production attribute namedSig :: Decorated NamedSignature;
  namedSig = namedSignatureDcl(fName,
                               [namedSignatureElement("stringToParse", stringTypeExp()),
                                namedSignatureElement("filenameToReport", stringTypeExp())],
                               namedSignatureElement("__func__lhs", nonterminalTypeExp("core:ParseResult", [t.typerep])));

  top.parserDcls = [parserSpecFromList(top.location, fName, t.typerep.typeName, m.moduleNames, top.compiledGrammars)];

  forwards to agDclDefault();
}

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

attribute ruleDcls, terminalDcls, nonTerminalDcls occurs on ModuleExportedDefs;

aspect production moduleExportedDefs
top::ModuleExportedDefs ::= compiled::[Decorated RootSpec] need::[String] seen::[String]
{
  top.ruleDcls = if null(need) || null(rs) then [] else (head(rs).ruleDcls ++ recurse.ruleDcls);
  top.terminalDcls = if null(need) || null(rs) then [] else (head(rs).terminalDcls ++ recurse.terminalDcls);
  top.nonTerminalDcls = if null(need) || null(rs) then [] else (head(rs).nonTerminalDcls ++ recurse.nonTerminalDcls);
}

