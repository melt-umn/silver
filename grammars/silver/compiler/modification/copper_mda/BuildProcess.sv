grammar silver:compiler:modification:copper_mda;

import silver:compiler:definition:concrete_syntax:copper as copper;
import silver:compiler:driver;
import silver:compiler:translation:java:driver;
import silver:compiler:translation:java:core only makeParserName, makeName;
import silver:util:cmdargs;

aspect production compilation
top::Compilation ::= g::Grammars  _  buildGrammar::String  benv::BuildEnv
{
  -- TODO: consider examining all grammars, not just grammarsToTranslate?
  -- I believe this choice was originally because we weren't serializing MdaSpecs to
  -- interface files, but I think we could easily start doing that new with the new serialization code?
  top.postOps <- map(runMdaAction(_, g.compiledGrammars, benv.silverGen ++ "src/"),
    flatMap((.mdaSpecs), grammarsToTranslate));
}

abstract production runMdaAction
top::DriverAction ::= spec::MdaSpec  compiledGrammars::EnvTree<Decorated RootSpec>  silverGen::String
{
  spec.compiledGrammars = compiledGrammars;

  local specCstAst :: SyntaxRoot = spec.cstAst;
  local val::IOVal<Integer> = evalIO(do {
    let outDir :: String = silverGen ++ "src/" ++ grammarToPath(spec.sourceGrammar);
    let parserName :: String = makeParserName(spec.fullName);

    if null(specCstAst.cstErrors) then do {
      mkdir(outDir);
      print("Generating parser " ++ spec.fullName ++ ".\n");
      copper:compileParserBean(specCstAst.copperParser,
        makeName(spec.sourceGrammar), parserName,
        true, outDir ++ parserName ++ ".java", false, "");
    } else do {
      -- Should this be stderr?
      print("CST errors while preparing for MDA " ++ spec.fullName ++ ":\n" ++
        implode("\n", specCstAst.cstErrors) ++ "\n");
      return 1;
    };
  }, top.ioIn);

  top.io = val.io;
  top.code = val.iovalue;
  top.order = 5;
}
