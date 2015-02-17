grammar ide;

type IdeResource foreign;

-- Delete a resource, returning true if successful.
function delete
IOVal<Boolean> ::= res::IdeResource i::IO
{
  return error("Not Yet Implemented: delete");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.Util.deleteResource(%res%, %i%)";
}

-- Copy a resource to another location within the project, using a path 
-- relative to project’s root. Returns true if successful.
function copyTo
IOVal<Boolean> ::= res::IdeResource path::String i::IO
{
  return error("Not Yet Implemented: copyTo");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.Util.copyResource(%res%, %path%, %i%)";
}

-- Move (shortcut for copy and delete) a resource to another location within 
-- the project, using a path relative to project’s root. Returns true if successful.
function moveTo
IOVal<Boolean> ::= res::IdeResource path::String i::IO
{
  return error("Not Yet Implemented: moveTo");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.Util.moveResource(%res%, %path%, %i%)";
}

-- Create a new file with given input as contents. The path is relative to 
-- project root. Returns the resource if created successfully.
function createFile
IOVal<Maybe<IdeResource>> ::= proj::IdeProject path::String input::String i::IO
{
  return error("Not Yet Implemented: createFile");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.Util.createResource(%proj%, %path%, %input%, %i%)";
}

-- Create a new folder. Returns the resource if created successfully.
function createFolder
IOVal<Maybe<IdeResource>> ::= proj::IdeProject path::String i::IO
{
  return error("Not Yet Implemented: createFolder");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.Util.createFolderResource(%proj%, %path%, %i%)";
}

-- Get the path of given resource, relative to project’s root. An empty string would be returned if the resource is linked.
function getRelativePath
IOVal<String> ::= res::IdeResource i::IO
{
  return error("Not Yet Implemented: getRelativePath");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.Util.getRelativePath(%res%, %i%)";
}

-- Get the absolute path of given resource.
function getAbsolutePath
IOVal<String> ::= res::IdeResource i::IO
{
  return error("Not Yet Implemented: getAbsolutePath");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.Util.getAbsolutePath(%res%, %i%)";
}
	
-- Get members of given resource. Returns an empty list if the resource is a 
-- file. 
function getResourceMembers
IOVal<[IdeResource]> ::= res::IdeResource i::IO
{
  return error("Not Yet Implemented: getResourceMembers");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.Util.getResourceMembers(%res%, %i%)";
}

-- Check if the resource is a folder
function resourceIsFolder
IOVal<Boolean> ::= res::IdeResource i::IO
{
  return error("Not Yet Implemented: isFolder");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.Util.resourceIsFolder(%res%, %i%)";
}
	
-- Check if the resource is a file.
function resourceIsFile
IOVal<Boolean> ::= res::IdeResource i::IO
{
  return error("Not Yet Implemented: isFile");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.Util.resourceIsFile(%res%, %i%)";
}
	
-- Check whether a resource is linked from outside project’s physical location. 
-- This is a feature from Eclipse. 
function resourceIsLinked
IOVal<Boolean> ::= res::IdeResource i::IO
{
  return error("Not Yet Implemented: isLinked");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.Util.resourceIsLinked(%res%, %i%)";
}

-- Check whether a resource is hidden in Eclipse. That is, invisible to IDE 
-- users.
function resourceIsHidden
IOVal<Boolean> ::= res::IdeResource i::IO
{
  return error("Not Yet Implemented: isHidden");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.Util.resourceIsHidden(%res%, %i%)";
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
    "edu.umn.cs.melt.ide.util.Util.resourceIsDerived(%res%, %i%)";
}

-- Check whether the resource does exist. After deleting a resource 
-- successfully, calling this function should return false.
function resourceExists
IOVal<Boolean> ::= res::IdeResource i::IO
{
  return error("Not Yet Implemented: exists");
} foreign {
  "java" : return 
    "edu.umn.cs.melt.ide.util.Util.resourceExists(%res%, %i%)";
}

