grammar ide;

type IdeProject foreign;
-- TODO: because we don't give Java translation of foreign types, we only pass them around as "Object"
-- This requires our runtime to accept "Object" and then cast it to IProject or whatever. UGLY!


-- Get the name of the given project.
function getProjectName
IOVal<String> ::= proj::IdeProject  i::IO
{
  return error("Not Yet Implemented: getName");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.Util.getProjectName(%proj%, %i%)";
}

-- Refresh the given project down to given depth, for which only pre-defined 
-- constants (0, 1, indefinite) can be used. 
function refreshProject
IO ::= proj::IdeProject  i::IO
{
  return error("Not Yet Implemented: refresh");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.Util.refreshProject(%proj%, %i%)";
}

-- Get the absoluet path of given project.
function getAbsoluteProjectPath
IOVal<String> ::= proj::IdeProject i::IO
{
  return error("Not Yet Implemented: getAbsoluteProjectPath");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.Util.getAbsoluteProjectPath(%proj%, %i%)";
}
	
-- Get the generated path of given project, relative to root.
function getGeneratedPath
IOVal<String> ::= proj::IdeProject i::IO
{
  return error("Not Yet Implemented: getGeneratedPath");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.Util.getGeneratedProjectPath(%proj%, %i%)";
}
	
-- Get members (direct sub-resources) of given project. 
function getProjectMembers
IOVal<Maybe<[IdeResource]>> ::= proj::IdeProject i::IO
{
  return error("Not Yet Implemented: getProjectMembers");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.Util.getProjectMembers(%proj%, %i%)";
}

