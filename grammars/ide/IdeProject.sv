grammar ide;

-- A project resource can be either a file or a folder.
-- This type corresponds to Eclipse’s IResource class 
--nonterminal IdeResource foreign;
type IdeResource foreign;

-- This type corresponds to Eclipse’s IProject class 
--nonterminal IdeProject foreign;
type IdeProject foreign;

synthesized attribute project :: IdeProject occurs on IdeEnv;

-- Functions

{-- IdeProject --}

-- Get the name of the given project.
function getProjectName
IOVal<String> ::= proj::IdeProject i::IO
{
  return error("Not Yet Implemented: getName");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.ProjectUtil.dispatchService(edu.umn.cs.melt.ide.util.ProjectUtil.PROJECT_GET_NAME, %i%, %proj%, null)";
}

-- Refresh the given project down to given depth, for which only pre-defined 
-- constants (0, 1, indefinite) can be used. 
function refreshProject
IO ::= proj::IdeProject depth::Integer i::IO
{
  return error("Not Yet Implemented: refresh");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.ProjectUtil.dispatchService(edu.umn.cs.melt.ide.util.ProjectUtil.PROJECT_REFRESH, %i%, %proj%, %depth%)";
}

-- Get the absoluet path of given project.
function getAbsolutePath
IOVal<String> ::= proj::IdeProject i::IO
{
  return error("Not Yet Implemented: getAbsolutePath");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.ProjectUtil.dispatchService(edu.umn.cs.melt.ide.util.ProjectUtil.PROJECT_GET_ABS_PATH, %i%, %proj%, null)";
}
	
-- Get the generated path of given project, relative to root.
function getGeneratedPath
IOVal<String> ::= proj::IdeProject i::IO
{
  return error("Not Yet Implemented: getGeneratedPath");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.ProjectUtil.dispatchService(edu.umn.cs.melt.ide.util.ProjectUtil.PROJECT_GET_GEN_PATH, %i%, %proj%, null)";
}
	
-- Get members (direct sub-resources) of given project. 
function getProjectMembers
IOVal<Maybe<[IdeResource]>> ::= proj::IdeProject i::IO
{
  return error("Not Yet Implemented: getProjectMembers");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.ProjectUtil.dispatchService(edu.umn.cs.melt.ide.util.ProjectUtil.PROJECT_GET_MEMBERS, %i%, %proj%, null)";
}

{-- IdeResource --}

-- Delete a resource, returning true if successful.
function delete
IOVal<Boolean> ::= res::IdeResource i::IO
{
  return error("Not Yet Implemented: delete");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.ProjectUtil.dispatchService(edu.umn.cs.melt.ide.util.ProjectUtil.RESOURCE_DELETE, %i%, %res%, null)";
}

-- Copy a resource to another location within the project, using a path 
-- relative to project’s root. Returns true if successful.
function copyTo
IOVal<Boolean> ::= res::IdeResource path::String i::IO
{
  return error("Not Yet Implemented: copyTo");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.ProjectUtil.dispatchService(edu.umn.cs.melt.ide.util.ProjectUtil.RESOURCE_COPY_TO, %i%, %res%, %path%)";
}

-- Move (shortcut for copy and delete) a resource to another location within 
-- the project, using a path relative to project’s root. Returns true if successful.
function moveTo
IOVal<Boolean> ::= res::IdeResource path::String i::IO
{
  return error("Not Yet Implemented: moveTo");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.ProjectUtil.dispatchService(edu.umn.cs.melt.ide.util.ProjectUtil.RESOURCE_MOVE_TO, %i%, %res%, %path%)";
}

-- Create a new file with given input as contents. The path is relative to 
-- project root. Returns the resource if created successfully.
function createFile
IOVal<Maybe<IdeResource>> ::= proj::IdeProject path::String input::String i::IO
{
  return error("Not Yet Implemented: createFile");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.ProjectUtil.dispatchService(edu.umn.cs.melt.ide.util.ProjectUtil.RESOURCE_CREATE_FILE, %i%, %proj%, new Object[]{%path%, %input%})";
}

-- Create a new folder. Returns the resource if created successfully.
function createFolder
IOVal<Maybe<IdeResource>> ::= proj::IdeProject path::String i::IO
{
  return error("Not Yet Implemented: createFolder");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.ProjectUtil.dispatchService(edu.umn.cs.melt.ide.util.ProjectUtil.RESOURCE_CREATE_FOLDER, %i%, %proj%, %path%)";
}

-- Get the path of given resource, relative to project’s root.
function getRelatovePath
IOVal<String> ::= res::IdeResource i::IO
{
  return error("Not Yet Implemented: getRelatovePath");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.ProjectUtil.dispatchService(edu.umn.cs.melt.ide.util.ProjectUtil.RESOURCE_GET_REL_PATH, %i%, %res%, null)";
}
	
-- Get members of given resource. Returns an empty list if the resource is a 
-- file. 
function getResourceMembers
IOVal<[IdeResource]> ::= res::IdeResource i::IO
{
  return error("Not Yet Implemented: getResourceMembers");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.ProjectUtil.dispatchService(edu.umn.cs.melt.ide.util.ProjectUtil.RESOURCE_GET_MEMBERS, %i%, %res%, null)";
}

-- Check if the resource is a folder
function resourceIsFolder
IOVal<Boolean> ::= res::IdeResource i::IO
{
  return error("Not Yet Implemented: isFolder");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.ProjectUtil.dispatchService(edu.umn.cs.melt.ide.util.ProjectUtil.RESOURCE_IS_FOLDER, %i%, %res%, null)";
}
	
-- Check if the resource is a file.
function resourceIsFile
IOVal<Boolean> ::= res::IdeResource i::IO
{
  return error("Not Yet Implemented: isFile");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.ProjectUtil.dispatchService(edu.umn.cs.melt.ide.util.ProjectUtil.RESOURCE_IS_FILE, %i%, %res%, null)";
}
	
-- Check whether a resource is linked from outside project’s physical location. 
-- This is a feature from Eclipse. 
function resourceIsLinked
IOVal<Boolean> ::= res::IdeResource i::IO
{
  return error("Not Yet Implemented: isLinked");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.ProjectUtil.dispatchService(edu.umn.cs.melt.ide.util.ProjectUtil.RESOURCE_IS_LINKED, %i%, %res%, null)";
}

-- Check whether a resource is hidden in Eclipse. That is, invisible to IDE 
-- users.
function resourceIsHidden
IOVal<Boolean> ::= res::IdeResource i::IO
{
  return error("Not Yet Implemented: isHidden");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.ProjectUtil.dispatchService(edu.umn.cs.melt.ide.util.ProjectUtil.RESOURCE_IS_HIDDEN, %i%, %res%, null)";
}
	
-- Determine if a resource is marked as derived. “Derived”, in Eclipse’s 
-- terminology, means a resource is the result of automatic generation, such 
-- as compilation, out of another resource(s). Such resource is thus not 
-- subject to user’s manual modification.
function resourceIsDerived
IOVal<Boolean> ::= res::IdeResource i::IO
{
  return error("Not Yet Implemented: isDerived");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.ProjectUtil.dispatchService(edu.umn.cs.melt.ide.util.ProjectUtil.RESOURCE_IS_DERIVED, %i%, %res%, null)";
}

-- Check whether the resource does exist. After deleting a resource 
-- successfully, calling this function should return false.
function resourceExists
IOVal<Boolean> ::= res::IdeResource i::IO
{
  return error("Not Yet Implemented: exists");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.ProjectUtil.dispatchService(edu.umn.cs.melt.ide.util.ProjectUtil.RESOURCE_EXISTS, %i%, %res%, null)";
}

{--
function test
IOVal<[IdeMessage]> ::= args::[IdeProperty] env::IdeEnv i::IO
{
    local proj::IdeProject = env.project;
    local projName::IOVal<String> = getProjectName(proj, i);

    local absPath::IOVal<String> = getAbsolutePath(proj, projName.io);
    local genPath::IOVal<String> = getGeneratedPath(proj, absPath.io);

    local ioa::IO = pinfo("projName=" ++ projName.iovalue ++ "; absPath=" ++ absPath.iovalue ++ "; genPath=" ++ genPath.iovalue, genPath.io);

    local pmembers::IOVal<Maybe<[IdeResource]>> = getProjectMembers(proj, ioa);

    local pmembersRetrieved :: Boolean = pmembers.iovalue.isJust;

    local subres::[IdeResource] = if pmembersRetrieved then pmembers.iovalue.fromJust else [];

    local iob::IO = processResource(subres, pmembers.io);

    return ioval(iob, []);
}

function processResource 
IO ::= res::[IdeResource] i::IO
{
    local hd :: IdeResource = head(res);

    local relPath::IOVal<String> = getRelatovePath(hd, i);
    local isFolder::IOVal<Boolean> = resourceIsFolder(hd, relPath.io);
    local ifFile::IOVal<Boolean> = resourceIsFile(hd, isFolder.io);
    local isLinked::IOVal<Boolean> = resourceIsLinked(hd, ifFile.io);
    local isHidden::IOVal<Boolean> = resourceIsHidden(hd, isLinked.io);
    local isDerived::IOVal<Boolean> = resourceIsDerived(hd, isHidden.io);
    local exists::IOVal<Boolean> = resourceExists(hd, isDerived.io);

    local io::IO = pinfo(
        "  [Path=" ++ relPath.iovalue ++ 
        "; isFolder=" ++ b2str(isFolder.iovalue) ++ 
        "; ifFile=" ++ b2str(ifFile.iovalue) ++ 
        "; isLinked=" ++ b2str(isLinked.iovalue) ++
        "; isHidden=" ++ b2str(isHidden.iovalue) ++ 
        "; isDerived=" ++ b2str(isDerived.iovalue) ++
        "; exists=" ++ b2str(exists.iovalue) ++ "]", exists.io);

    return if null(res) then i else processResource(tail(res), io);
}

function b2str
String ::= b::Boolean
{
    return if b then "true" else "false";
}
--}

