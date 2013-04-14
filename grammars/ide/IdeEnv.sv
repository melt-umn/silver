grammar ide;

synthesized attribute projectName :: String;
synthesized attribute projectPath :: String;
synthesized attribute platformPath :: String;
synthesized attribute generatedPath :: String;

{--
The nonterminal representing the environment for a project in generated IDE.  
--}
nonterminal IdeEnv with projectName, projectPath, generatedPath, platformPath;

{--
 Create an environment for target project
--}
abstract production makeIdeEnv
top::IdeEnv ::= projectName::String projectPath::String generatedPath::String platformPath::String
{
  top.projectName = projectName;
  top.projectPath = projectPath;
  top.generatedPath = generatedPath;
  top.platformPath = platformPath;
}

