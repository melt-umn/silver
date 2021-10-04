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
  
  local io0::IOToken = printT("[IDE plugin] Generating IDE plugin.\n", top.ioIn);
  local io1::IOToken = deleteTreeT(ideGenPath, io0);
  local io2::IOToken =
    mkdirs(s"${ideGenPath}/plugin/src/${pkgToPath(pkgName)}/",
      ["imp/coloring", "eclipse/property", "eclipse/wizard/newproject", "eclipse/wizard/newfile"], io1);
  local io3::IOToken = writeFiles(ideGenPath ++ "/plugin/", ide.pluginFiles, io2);

  top.io = io3;

  top.code = 0;
  top.order = 7;
}

function mkdirs
IOToken ::= path::String  paths::[String]  i::IOToken
{
  return if null(paths) then i
  else mkdirs(path, tail(paths),
         mkdirT(path ++ head(paths), i).io);
}

