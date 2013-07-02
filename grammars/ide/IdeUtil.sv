grammar ide;

{--
  Print an info-level message into IDE console.
--}
function pinfo
IO ::= s::String i::IO
{
  return error("Not Yet Implemented: pinfo");
} foreign {
  "java" : return "common.Util.io(%i%, edu.umn.cs.melt.ide.util.Util.info(%s%.toString()))";
}

{--
  Print an error-level message into IDE console.
--}
function perror
IO ::= s::String i::IO
{
  return error("Not Yet Implemented: perror");
} foreign {
  "java" : return "common.Util.io(%i%, edu.umn.cs.melt.ide.util.Util.error(%s%.toString()))";
}

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
  "java" : return "common.Util.io(%i%, edu.umn.cs.melt.ide.util.Util.ant(%buildFile%.toString(), %arguments%.toString(), %target%.toString()))";
}

{--
 Depth constants used for refresh(IO ::= String Integer)
--}
--the target resource itself, not including any of its members.
global ideDepthZero :: Integer = 0;
--the target resource and its direct members.
global ideDepthOne :: Integer = 1;
--the target resource and its direct and indirect members at any depth.
global ideDepthInfinite :: Integer = 2;

{--
  Refresh a project with a given level:
 
  projectName: name of the project
  depth: the depth down to which to refresh this project. Legal value: ideDepthZero, ideDepthOne, ideDepthInfinite
--}
function refresh
IO ::= projectName::String depth::Integer i::IO
{
  return error("Not Yet Implemented: refresh");
} foreign {
  "java" : return "common.Util.io(%i%, edu.umn.cs.melt.ide.util.Util.refresh(%projectName%.toString(), %depth%))";
}
