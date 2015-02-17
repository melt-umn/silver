grammar ide;

synthesized attribute projectName :: String;
synthesized attribute projectPath :: String;
synthesized attribute platformPath :: String;
synthesized attribute generatedPath :: String;
synthesized attribute project :: IdeProject occurs on IdeEnv;

{--
The nonterminal representing the environment for a project in generated IDE.  
  projectName: the project name.
  projectPath: the absolute path of this project's root folder.
  generatedPath: the absolute path to the folder to which the generated (compiled) 
                 files go. this folder is labeled as hidden in Eclipse.
  platformPath: the absolute path to Eclipse platform (that is, the folder of Eclipse).
--}
nonterminal IdeEnv with projectName, projectPath, generatedPath, platformPath;

{--
 Create an environment for target project
--}
abstract production makeIdeEnv
top::IdeEnv ::= projectName::String projectPath::String generatedPath::String platformPath::String project::IdeProject
{
  top.projectName = projectName;
  top.projectPath = projectPath;
  top.generatedPath = generatedPath;
  top.platformPath = platformPath;
  top.project = project;
}

