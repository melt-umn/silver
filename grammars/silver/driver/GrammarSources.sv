grammar silver:driver;

{--
 - Turns a list of files that compose a grammar into a RootSpec, having compiled them.
 -}
nonterminal Roots with config, env, io, rSpec, rParser, compiledGrammars, globalImports, grammarDependencies, flowEnv;

abstract production compileFiles
top::Roots ::= iIn::IO gn::String files::[String] gpath::String
{
  -- Print the path we're reading, and read the file.
  local attribute text :: IOVal<String>;
  text = readFile(gpath ++ head(files), print("\t[" ++ gpath ++ head(files) ++ "]\n", iIn));

  -- This is where a .sv file actually gets parsed:
  production attribute r :: Root;
  r = parseTreeOrDieWithoutStackTrace(top.rParser(text.iovalue, head(files)));
  -- These are file-specific inherited attributes:
  r.file = head(files);
  r.grammarName = gn;
  -- These are grammar-specific inherited attributes:
  r.env = top.env;
  r.globalImports = top.globalImports;
  r.grammarDependencies = top.grammarDependencies;
  r.flowEnv = top.flowEnv;
  -- These are compilation-wide inherited attributes:
  r.compiledGrammars = top.compiledGrammars;
  r.config = top.config;

  -- Continue parsing the rest of the files.
  production attribute recurse :: Roots;
  recurse = compileFiles(text.io, gn, tail(files), gpath);
  recurse.rParser = top.rParser;
  -- Echo grammar-wide stuffs:
  recurse.env = top.env;
  recurse.globalImports = top.globalImports;
  recurse.grammarDependencies = top.grammarDependencies;
  recurse.flowEnv = top.flowEnv;
  -- Echo compilation-wide stuffs:
  recurse.compiledGrammars = top.compiledGrammars;
  recurse.config = top.config;

  top.rSpec =        if null(files) then emptyRootSpec() else consRootSpec(r, recurse.rSpec); 
  top.io =           if null(files) then iIn             else recurse.io;
}

