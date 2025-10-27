grammar silver:compiler:driver;

-- Entry point driver function for debugging the Silver compiler with CodeProber
fun codeProberRun
IO<Compilation> ::= args::[String]  svParser::SVParser = do {
  eprintln("codeProberRun " ++ implode(" ", args));
  let fileName = last(args);
  let svArgs = init(args); 

  res::Either<RunError Compilation> <- cmdLineRunInitial(svArgs, svParser).run;
  eprintln("codeProberRun finished");

  comp::Compilation <-
    case res of
    | left(re) -> do { eprintln(re.errMsg); exit(re.code); }
    | right(comp) -> pure(comp)
    end;

  setTreeIsInMainFile(false, comp);
  traverse_(setTreeIsInMainFile(true, _),
    flatMap(\ r::Decorated RootSpec ->
      filter(\ f::Decorated File ->
        isSameFile(r.grammarSource ++ "/" ++ getParsedOriginLocation(f).fromJust.filename, fileName),
        r.files),
      comp.recompiledGrammars));

  return comp;
};

monoid attribute files::[Decorated File] occurs on RootSpec, Grammar;
propagate files on RootSpec, Grammar;
aspect production consGrammar
top::Grammar ::= h::File _
{
  top.files <- [h];
}

function isSameFile
Boolean ::= p1::String  p2::String
{
  return error("foreign function");
} foreign {
  "java": return "java.nio.file.Files.isSameFile(java.nio.file.Paths.get(%p1%.toString()), java.nio.file.Paths.get(%p2%.toString()))";
}
