grammar silver:compiler:modification:copper_mda;

import silver:compiler:definition:concrete_syntax:copper as copper;
import silver:compiler:driver;
import silver:compiler:translation:java:driver;
import silver:compiler:translation:java:core only makeParserName, makeName;
import silver:reflect:nativeserialize;
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
  local outDir :: String = silverGen ++ "src/" ++ grammarToPath(spec.sourceGrammar);
  local parserName :: String = makeParserName(spec.fullName);
  local dumpFile :: String = outDir ++ parserName ++ ".copperdump";

  local buildGrammar::IO<Integer> =
    if null(specCstAst.cstErrors) then do {
      mkdir(outDir);
      print("Running MDA for " ++ spec.fullName ++ ".\n");
      ret::Integer <- copper:compileParserBean(specCstAst.copperParser,
        makeName(spec.sourceGrammar), parserName, true, "", false, "", false);
      case nativeSerialize(new(specCstAst)) of
      | left(e) -> error("BUG: specCstAst was not serializable; hopefully this was caused by the most recent change to the copper_mda modification: " ++ e)
      | right(dump) -> writeBinaryFile(dumpFile, dump)
      end;
      return ret;
    } else do {
      -- Should this be stderr?
      print("CST errors while preparing for MDA " ++ spec.fullName ++ ":\n" ++
        implode("\n", specCstAst.cstErrors) ++ "\n");
      return 1;
    };

  local val::IOVal<Integer> = evalIO(do {
    dumpFileExists :: Boolean <- isFile(dumpFile);
    if dumpFileExists then do {
      dumpFileContents::ByteArray <- readBinaryFile(dumpFile);
      let dumpMatched::Either<String Boolean> = map(eq(specCstAst, _), nativeDeserialize(dumpFileContents));
      if dumpMatched == right(true) then do {
        print("MDA test " ++ spec.fullName ++ " is up to date.\n");
        return 0;
      } else do {
        buildGrammar;
      };
    } else do {
      buildGrammar;
    };
  }, top.ioIn);

  top.io = val.io;
  top.code = val.iovalue;
  top.order = 5;
}
