grammar ide;

{--
  Call IDE-embedded ant against a build file.

  buildFile: the full absolute path and file name (example: /home/username/workspace/proj01/build.xml)
  arguments: the arguments in a single String (example: -a -b --lc -d"xxx")
  target: the target to be invoked in build file. For now only one target is supported.
--}
function ant
IO ::= buildFile::String arguments::String target::String i::IO
{
  return error("Not Yet Implemented: ant");
} foreign {
  "java" : return "common.Util.io(%i%, edu.umn.cs.melt.ide.util.Util.ant(%buildFile%, %arguments%, %target%))";
}

{--
 - Gets a path to a resource (directory, file) that was included in the IDE bundle via
 - a 'resource' declaration in the ide spec.
 - (e.g.  resource grammars "../../../grammars";
 -   can be obtained via getIdeResource("grammar", io) )
 - NOT TO BE CONFUSED WITH IdeResource. (i.e. files in the project)
 -}
function getIdeResource
IOVal<String> ::= resourceid::String  i::IO
{
  return error("Not Yet Implemented: getIdeResource");
} foreign {
  "java" : return "edu.umn.cs.melt.ide.util.Util.getIdeResource(%resourceid%, %i%)";
}

