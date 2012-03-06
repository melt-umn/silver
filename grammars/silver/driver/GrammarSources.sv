grammar silver:driver;

{--
 - Turns a list of files that compose a grammar into a RootSpec, having compiled them.
 -}
nonterminal Roots with config, env, io, rSpec, rParser, defs, compiledGrammars, importedDefs, globalImports;

abstract production compileFiles
top::Roots ::= iIn::IO gn::String files::[String] gpath::String
{
  --the text of the file.
  local attribute text :: IOVal<String>;
  text = readFile(gpath ++ head(files), print("\t[" ++ gpath ++ head(files) ++ "]\n", iIn));

  production attribute r :: Root;
  r = parseTreeOrDieWithoutStackTrace(top.rParser(text.iovalue, head(files)));
  r.env = top.env;
  r.globalImports = top.globalImports;
  r.file = head(files);
  r.compiledGrammars = top.compiledGrammars;
  r.grammarName = gn;
  r.config = top.config;

  --the rest of the files.
  production attribute recurse :: Roots;
  recurse = compileFiles(text.io, gn, tail(files), gpath);
  recurse.rParser = top.rParser;
  recurse.env = top.env;
  recurse.compiledGrammars = top.compiledGrammars;
  recurse.globalImports = top.globalImports;
  recurse.config = top.config;

  top.rSpec = if null(files) then emptyRootSpec() else consRootSpec(r, recurse.rSpec); 
  top.io = if null(files)
           then iIn
           else recurse.io;
  top.defs = if null(files) then emptyDefs() else appendDefs(r.defs, recurse.defs);
  top.importedDefs = if null(files) then emptyDefs() else appendDefs(r.importedDefs, recurse.importedDefs);
}

