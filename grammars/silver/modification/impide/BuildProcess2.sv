grammar silver:modification:impide;

import silver:driver;
import silver:translation:java;
import silver:util:cmdargs;

{--
 - @param grams  Compiled grammars (used to generate parser)
 - @param ide  The ide specification to generate files for.
 - @param ideGenPath  The path to the ide generation directory
 -   (e.g. generated/ide/silver.composed.idetest)
 -   (contains: plugin feature updatesite)
 -}
abstract production generateNCS
top::Unit ::= grams::EnvTree<Decorated RootSpec> ide::IdeSpec ideGenPath::String pkgName::String
{
  ide.compiledGrammars = grams;
  
  local io0::IO = print("[IDE plugin] Generating IDE plugin.\n", top.ioIn);
  local io1::IO = deleteTree(ideGenPath, io0);
  local io2::IO =
    mkdirs(s"${ideGenPath}/plugin/src/${pkgToPath(pkgName)}/",
      ["imp/coloring", "eclipse/property", "eclipse/wizard/newproject", "eclipse/wizard/newfile", "copper/parser"], io1);
  local io3::IO = writeFiles(ideGenPath ++ "/plugin/", ide.pluginFiles, io2);

  top.io = io3;

  top.code = 0;
  top.order = 7;
}

function mkdirs
IO ::= path::String  paths::[String]  i::IO
{
  return if null(paths) then i
  else mkdirs(path, tail(paths),
         mkdir(path ++ head(paths), i).io);
}

