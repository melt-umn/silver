grammar silver:translation:java:driver;
import silver:translation:java:core;
import silver:translation:java:command;

import silver:driver;
import silver:definition:env;
import silver:definition:core hiding grammarName;

import silver:util;
import silver:util:command;

import core;

aspect production run
top::RunUnit ::= iIn::IO args::String
{
  postOps <- if a.noJavaGeneration then [] else [genJava(a, grammars)]; 
}

abstract production genJava
top::Unit ::= a::Command specs::[Decorated RootSpec]
{
  forwards to genJavaHelp(a, specs)
	with {
		ioIn = print("Generating Java Translation.\n", top.ioIn);
	};
}

abstract production genJavaHelp
top::Unit ::= a::Command specs::[Decorated RootSpec]
{
  local attribute i :: IO;
  i = writeAll(top.ioIn, a, specs);
 
  local attribute buildFile :: IO;
  buildFile = writeBuildFile(i, a, specs).io;

  top.io = buildFile;
  top.code = 0;
  top.order = 4;
}

function writeAll
IO ::= i::IO a::Decorated Command l::[Decorated RootSpec]
{
  local attribute now :: IO;
  now = writeSpec(i, head(l), a);

  local attribute recurse :: IO;
  recurse = writeAll(now, a, tail(l));

  return if null(l) then i else recurse;
}

function writeSpec
IO ::= i::IO r::Decorated RootSpec a::Decorated Command
{
  local attribute package :: String;
  package = substitute("/", ":", r.declaredName) ++ "/";

  local attribute envArg :: IOString;
  envArg = envVar("SILVER_JAVA", i);

  production attribute specLocation :: String;
  specLocation = envArg.sValue ++ (if substring(length(envArg.sValue)-1, length(envArg.sValue), envArg.sValue) != "/" then "/src/" else "src/") ++ package; 

  local attribute mkdir :: IO;
  mkdir = system("mkdir -p " ++ specLocation, envArg.io).io;

  local attribute mki :: IO;
  mki = writeFile(specLocation ++ "Init.java", makeInit(r), mkdir);

  local attribute mains :: [Decorated EnvItem];
  mains = getFunctionDcl(r.declaredName ++ ":main", toEnv(r.defs));

  local attribute mainIO :: IO;
  mainIO = if null(mains) then mki else writeFile(specLocation ++ "Main.java", makeMain(r), mki);

  return if !r.interface then writeClasses(mainIO, specLocation, r.javaClasses) else envArg.io;
}

function makeMain
String ::= r::Decorated RootSpec{
  local attribute package :: String;
  package = makeName(r.declaredName);

  return 
"package " ++ package ++ ";\n\n" ++

"public class Main {\n" ++
"\tpublic static void main(String[] args) {\n" ++
"\t\t" ++ package ++ ".Init.init();\n" ++
"\t\t" ++ package ++ ".Init.postInit();\n" ++
"\t\t\tnew " ++ package ++ ".Pmain(fold(args), new Object()).doReturn();\n" ++
"\t}\n" ++
"\tpublic static common.StringCatter fold(String [] args){\n" ++ 
"\t\tStringBuffer result = new StringBuffer();\n" ++ 
"\t\tfor(String arg : args){\n" ++ 
"\t\t\tresult.append(\" \").append(arg);\n" ++ 
"\t\t}\n" ++ 
"\t\treturn new common.StringCatter(result.toString().trim());\n" ++ 
"\t}\n" ++ 
"}\n";
}

abstract production writeBuildFile
top::IOString ::= i::IO a::Decorated Command specs::[Decorated RootSpec]{

  production attribute extraTargets :: [String] with ++;
  extraTargets := [];

  production attribute extraTaskdefs :: [String] with ++;
  extraTaskdefs := [];

  production attribute extraPaths :: [String] with ++;
  extraPaths := [];

  local attribute mains :: [Decorated EnvItem];
  mains = getFunctionDcl(a.grammarName ++ ":main", toEnv(head(getRootSpec(a.grammarName, specs)).defs));

  top.io = writeFile(a.generatedPath ++ "/build.xml", buildXml, i);

  local attribute buildXml :: String;
  buildXml =    
"<project name='" ++ a.grammarName ++ "' default='dist' basedir='.'>\n" ++
"  <description>Build the grammar " ++ a.grammarName ++ " </description>\n" ++

"  <!-- set global properties for this build -->\n" ++
"  <property environment='env'/>\n\n" ++

"  <property name='lib' location='${env.SILVER_JAVA}/lib'/>\n\n" ++ 

"  <property name='build' location='build'/>\n" ++
"  <property name='dist'  location='dist'/>\n\n" ++

"  <path id='lib.classpath'>\n" ++
"    <fileset dir='${env.SILVER_JAVA}/lib/' includes='**/*.jar' />\n" ++
"  </path>\n\n" ++

"  <path id='src.classpath'>\n" ++
"    <pathelement location='${env.SILVER_JAVA}/src' />\n" ++
"  </path>\n\n" ++

"  <path id='compile.classpath'>\n" ++
"    <path refid='src.classpath'/>\n" ++
"    <path refid='lib.classpath'/>\n" ++
"  </path>\n\n" ++

folds("\n", extraTaskdefs) ++ "\n\n" ++

"  <macrodef name='grammar'>\n" ++
"    <attribute name='name'/>\n" ++
"    <sequential>\n\n" ++

"      <javac debug='on' source='1.5' classpathref='compile.classpath' srcdir='${env.SILVER_JAVA}/src' destdir='${build}'>\n" ++
"       <include name='@{name}/*.java' />\n" ++
"      </javac>\n\n" ++

"    </sequential>\n" ++
"  </macrodef>\n\n" ++

"  <target name='init'>\n\n" ++
"    <!-- Create the time stamp -->\n" ++
"    <tstamp>\n" ++
"      <format property='TIME' pattern='MM/dd/yyyy hh:mm aa'/>\n" ++
"    </tstamp>\n\n" ++

"    <mkdir dir='${build}'/>\n" ++
"    <mkdir dir='${dist}'/>\n" ++
"  </target>\n\n" ++

"  <target name='run' depends='dist'>\n" ++ 
"    <java jar='${dist}/" ++ makeName(a.grammarName) ++ ".jar' fork='true'>\n" ++ 
"      <arg value='${args}'/>\n" ++ 
"    </java>\n" ++ 
"  </target>\n\n" ++ 

"  <target name='dist' depends='grammars'>\n\n" ++

"    <pathconvert refid='lib.classpath' pathsep=' ' property='man.classpath'>\n" ++
"      <map from='${lib}' to='lib' />\n" ++
"    </pathconvert>\n\n" ++

-- If we're building a single jar, omit copying any libraries
(if a.buildSingleJar then "" else
"     <copy toDir='${dist}/lib/'>\n" ++ 
"        <fileset dir='${env.SILVER_JAVA}/lib/'/>\n" ++ 
"     </copy>\n\n" ) ++ 

"    <jar destfile='${dist}/" ++ makeName(a.grammarName) ++ ".jar' basedir='${build}'>\n" ++
"      <manifest>\n" ++
(if !null(mains) then "        <attribute name='Main-Class' value='" ++ makeName(a.grammarName) ++ ".Main' />\n" else "") ++
"       <attribute name='Class-Path' value='${man.classpath}' />\n" ++
"       <attribute name='Built-By' value='${user.name}' />\n" ++
"       <section name='version'>\n" ++
"         <attribute name='Specification-Version' value='' />\n" ++
"         <attribute name='Implementation-Version' value='${TIME}' />\n" ++
"       </section>\n" ++
"      </manifest>\n" ++

-- If we're building a single jar, then include it, and don't write out a script.
(if a.buildSingleJar then
"      <zipfileset src='${env.SILVER_JAVA}/lib/copper/CopperRuntime.jar' excludes='META-INF/*' />" ++
"      <zipfileset src='${env.SILVER_JAVA}/lib/silver/silver-common.jar' excludes='META-INF/*' />" ++
"    </jar>\n\n"

else
"    </jar>\n\n" ++
(if !null(mains) then 
"    <echo file='${dist}/" ++ a.outName ++ "'>java -cp ./lib/:./" ++ makeName(a.grammarName) ++ ".jar " ++ makeName(a.grammarName) ++ ".Main $1</echo>\n" ++
"    <chmod file='${dist}/" ++ a.outName ++ "' perm='+x'/>\n\n" 
else "") 

) ++ -- end if we're building a single jar.

"  </target>\n\n" ++

"  <target name='grammars' depends='" ++ folds(", ", ["init"] ++ getDeclaredNames(specs)) ++ "'/>\n" ++

buildAntParts(specs) ++ "\n" ++

folds("\n", extraTargets) ++ "\n\n" ++

"  <target name='clean' description='clean up' >\n" ++
"    <delete dir='${build}'/>\n" ++
"    <delete dir='${dist}'/>\n" ++
"  </target>\n\n" ++

"</project>\n";
}

function buildAntParts
String ::= r::[Decorated RootSpec]{
  return if null(r) then "" else buildAntPart(head(r)).sValue ++ buildAntParts(tail(r));
}

abstract production buildAntPart
top::IOString ::= r::Decorated RootSpec{

  production attribute depends :: [String] with ++;
  depends := [];

  top.sValue = 
"  <target name='" ++ r.declaredName ++ "' depends='" ++ folds(", ", ["init"] ++ remove(r.declaredName, r.moduleNames) ++ depends)++ "'>\n" ++
"    <grammar name='" ++ substitute("/", ":", r.declaredName) ++ "'/>\n" ++
"  </target>\n";
}



function writeClasses
IO ::= i::IO l::String s::[[String]]{
  return if null(s) then i else writeFile(l ++ head(head(s)) ++ ".java", head(tail(head(s))), writeClasses(i, l, tail(s)));
}

function makeInit
String ::= r::Decorated RootSpec{
  local attribute className :: String;
  className = makeName(r.declaredName) ++ ".Init";

  return 
"package " ++ makeName(r.declaredName) ++ ";\n\n" ++

"public class Init{\n\n" ++

"\tprivate static boolean init = false;\n" ++
"\tprivate static boolean postInit = false;\n\n" ++

"\tpublic static void init(){\n" ++
"\t\tif(" ++ className ++ ".init) return;\n\n" ++

"\t\t" ++ className ++ ".setupInheritedAttributes();\n\n" ++	

"\t\t" ++ className ++ ".init = true;\n\n" ++

makeOthers(r.moduleNames, "init") ++ "\n" ++

"\t\t" ++ className ++ ".initAspectAttributeDefinitions();\n" ++
"\t\t" ++ className ++ ".initProductionAttributeDefinitions();\n" ++
"\t}\n\n" ++

"\tpublic static void postInit(){\n" ++
"\t\tif(" ++ className ++ ".postInit) return;\n\n" ++
"\t\t" ++ className ++ ".postInit = true;\n\n" ++
makeOthers(r.moduleNames, "postInit") ++ "\n\n" ++
r.postInit ++
"\t}\n\n" ++

"\tprivate static void setupInheritedAttributes(){\n" ++
r.setupInh ++
"\t}\n\n" ++

"\tprivate static void initProductionAttributeDefinitions(){\n" ++
r.initProd ++
"\t}\n\n" ++

"\tprivate static void initAspectAttributeDefinitions(){\n" ++
r.initAspect ++
"\t}\n" ++
"}\n";

}

function makeOthers
String ::= others::[String] nme::String {
  return if null(others) then "" else "\t\t" ++ makeName(head(others)) ++ ".Init."++nme++"();\n" ++ makeOthers(tail(others),nme);
}
