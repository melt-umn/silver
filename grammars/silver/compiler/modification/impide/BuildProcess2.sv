grammar silver:compiler:modification:impide;

import silver:compiler:driver;
import silver:compiler:translation:java;
import silver:util:cmdargs;

{--
 - @param grams  Compiled grammars (used to generate parser)
 - @param ide  The ide specification to generate files for.
 - @param ideGenPath  The path to the ide generation directory
 -   (e.g. generated/ide/silver.compiler.composed.idetest)
 -   (contains: plugin feature updatesite)
 -}
abstract production generateNCS
top::DriverAction ::= grams::EnvTree<Decorated RootSpec> ide::IdeSpec ideGenPath::String pkgName::String
{
  ide.compiledGrammars = grams;
  
  top.run = do {
    eprintln("[IDE plugin] Generating IDE plugin.");
    deleteTree(ideGenPath);
    mkdirs(s"${ideGenPath}/plugin/src/${pkgToPath(pkgName)}/",
      ["imp/coloring", "eclipse/property", "eclipse/wizard/newproject", "eclipse/wizard/newfile"]);
    writeFiles(ideGenPath ++ "/plugin/", ide.pluginFiles);
    return 0;
  };

  top.order = 7;
}

function mkdirs
IO<()> ::= path::String  paths::[String]
{
  return traverse_(\ p::String -> do { mkdir(path ++ p); return (); }, paths);
}

