grammar silver:compiler:refactor;

import silver:compiler:driver;
import silver:compiler:translation:java:driver;
import silver:compiler:definition:env;
import silver:compiler:definition:core;

import silver:util:cmdargs;

import silver:langutil:unparse;

synthesized attribute doRefactor :: Boolean occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= _
{
  top.doRefactor = false;
}
abstract production refactorFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.doRefactor = true;
  top.noBindingChecking = true;
  top.noJavaGeneration = true;
  forwards to @rest;
}

aspect function parseArgs
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [ flagSpec(name="--refactor", paramString=nothing(),
               help="Apply a transformation to the Silver specification",
               flagParser=flag(refactorFlag))
           ];
}
aspect production compilation
top::Compilation ::= g::Grammars  _  buildGrammars::[String]  benv::BuildEnv
{
  top.postOps <-
    if top.config.doRefactor
    then [doRefactor(top.config, grammarsRelevant)]
    else [];
}

abstract production doRefactor
top::DriverAction ::= a::Decorated CmdArgs  specs::[Decorated RootSpec]
{
  top.run = do {
    eprintln("Applying Refactoring.");
    traverse_(refactorSpec, specs);
    return 0;
  };
  top.order = 4;
}

function refactorSpec
IO<()> ::= r::Decorated RootSpec
{
  return do {
    eprintln("\t[" ++ r.declaredName ++ "]");
    traverse_(\ item::(String, Root) ->
      when_(!endsWith(".md", item.1),  -- TODO: Make this work with literate Silver files
        do {
          let fullPath::String = r.grammarSource ++ item.1;
          text::String <- readFile(fullPath);
          let newText::String = unparse(text, item.2);
          writeFile(fullPath, newText);
        }),
      r.transformedFiles);
  };
}
