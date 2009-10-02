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
  preOps <- if a.noJavaGeneration then [] else [checkJavaGen(a)];
  postOps <- if a.noJavaGeneration then [] else [genJava(a, grammars, nonTreeGrammars)]; 
}

abstract production checkJavaGen
top::Unit ::= a::Command
{
  local attribute envArg :: IOString;
  envArg = envVar("SILVER_JAVA", top.ioIn);

  local attribute problem :: Boolean;
  problem = !(length(envArg.sValue) > 0 || length(a.javaGen) > 0);

  top.io = if problem then print("Missing a location to generate java files. Please set SILVER_JAVA or use -J <path>\n",envArg.io) else envArg.io;
  top.code = if problem then 1 else 0;
  top.order = 0;
}

abstract production genJava
top::Unit ::= a::Command specs::[Decorated RootSpec] extras::[String]
{
  forwards to genJavaHelp(a, specs, extras)
	with {
		ioIn = print("Generating Java Translation.\n", top.ioIn);
	};
}

abstract production genJavaHelp
top::Unit ::= a::Command specs::[Decorated RootSpec] extras::[String]
{
  local attribute i :: IO;
  i = writeAll(top.ioIn, a, specs, extras);
 
  local attribute buildFile :: IO;
  buildFile = writeBuildFile(i, a, specs).io;

  top.io = buildFile;
  top.code = 0;
  top.order = 4;
}

function writeAll
IO ::= i::IO a::Decorated Command l::[Decorated RootSpec] extras::[String]
{
  local attribute now :: IO;
  now = writeSpec(i, head(l), a, extras);

  local attribute recurse :: IO;
  recurse = writeAll(now, a, tail(l), extras);

  return if null(l) then i else recurse;
}

-- note: duplication in copper's buildprocess.sv
function writeSpec
IO ::= i::IO r::Decorated RootSpec a::Decorated Command extras::[String]
{
  local attribute package :: String;
  package = substitute("/", ":", r.declaredName) ++ "/";

  local attribute envArg :: IOString;
  envArg = envVar("SILVER_JAVA", i);
  
  local attribute javaGenLoc :: String;
  javaGenLoc = if length(a.javaGen) > 0 then a.javaGen else envArg.sValue;

  production attribute specLocation :: String;
  specLocation = javaGenLoc ++ (if substring(length(javaGenLoc)-1, length(javaGenLoc), javaGenLoc) != "/" then "/src/" else "src/") ++ package; 

  local attribute mkdir :: IO;
  mkdir = system("mkdir -p " ++ specLocation, envArg.io).io;

  local attribute mki :: IO;
  mki = writeFile(specLocation ++ "Init.java", makeInit(r, if a.grammarName == r.impliedName then extras else []), mkdir);

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

  production attribute extraDepends :: [String] with ++;
  extraDepends := ["init"];

  local attribute mains :: [Decorated EnvItem];
  mains = getFunctionDcl(a.grammarName ++ ":main", toEnv(head(getRootSpec(a.grammarName, specs)).defs));

  local attribute envArg :: IOString;
  envArg = envVar("SILVER_JAVA", i);
  
  local attribute javaGenLoc :: String;
  javaGenLoc = if length(a.javaGen) > 0 then a.javaGen else envArg.sValue;

  top.io = writeFile("build.xml", buildXml, envArg.io);

  local attribute buildXml :: String;
  buildXml =    
"<project name='" ++ a.grammarName ++ "' default='dist' basedir='.'>\n" ++
"  <description>Build the grammar " ++ a.grammarName ++ " </description>\n\n" ++

"  <property environment='env'/>\n" ++
"  <property name='jg' location='" ++ javaGenLoc ++ "'/>\n" ++
"  <property name='lib' location='${jg}/lib'/>\n" ++ 
"  <property name='bin' location='${jg}/bin'/>\n" ++
"  <property name='dist' location='.'/>\n" ++
"  <property name='src' location='${jg}/src'/>\n\n" ++

"  <path id='lib.classpath'>\n" ++
"    <fileset dir='${lib}' includes='**/*.jar' />\n" ++
"  </path>\n\n" ++

"  <path id='src.classpath'>\n" ++
"    <pathelement location='${src}' />\n" ++
"  </path>\n\n" ++

"  <path id='compile.classpath'>\n" ++
"    <path refid='src.classpath'/>\n" ++
"    <path refid='lib.classpath'/>\n" ++
"  </path>\n\n" ++

folds("\n", extraTaskdefs) ++ "\n\n" ++

"  <target name='init'>\n\n" ++
"    <!-- Create the time stamp -->\n" ++
"    <tstamp>\n" ++
"      <format property='TIME' pattern='MM/dd/yyyy hh:mm aa'/>\n" ++
"    </tstamp>\n\n" ++

"    <mkdir dir='${bin}'/>\n" ++
"  </target>\n\n" ++

"  <target name='dist' depends='grammars'>\n\n" ++

"    <pathconvert refid='lib.classpath' pathsep=' ' property='man.classpath' />\n" ++

"    <jar destfile='${dist}/" ++ makeName(a.grammarName) ++ ".jar' basedir='${bin}'>\n" ++

    buildGrammarList(specs, "*.class") ++ 

"      <manifest>\n" ++
(if !null(mains) then
"       <attribute name='Main-Class' value='" ++ makeName(a.grammarName) ++ ".Main' />\n"
 else "") ++

(if !a.buildSingleJar then 
"       <attribute name='Class-Path' value='${man.classpath}' />\n"
 else "") ++
"       <attribute name='Built-By' value='${user.name}' />\n" ++
"       <section name='version'>\n" ++
"         <attribute name='Specification-Version' value='' />\n" ++
"         <attribute name='Implementation-Version' value='${TIME}' />\n" ++
"       </section>\n" ++
"      </manifest>\n" ++

-- If we're building a single jar, then include it, and don't write out a script.
(if a.buildSingleJar then
"      <zipfileset src='${lib}/copper/CopperRuntime.jar' excludes='META-INF/*' />\n" ++
"      <zipfileset src='${lib}/silver/silver-common.jar' excludes='META-INF/*' />\n"
 else "") ++
 
"    </jar>\n\n" ++

"  </target>\n\n" ++

"  <target name='grammars' depends='" ++ folds(", ", extraDepends) ++ "'>\n" ++

"      <javac debug='on' source='1.5' classpathref='compile.classpath' srcdir='${src}' destdir='${bin}'>\n" ++
    buildGrammarList(specs, "*.java") ++ 
"      </javac>\n" ++
"  </target>\n\n" ++

folds("\n", extraTargets) ++ "\n\n" ++

"</project>\n";
}

function buildGrammarList
String ::= r::[Decorated RootSpec] s::String
{
  return if null(r) then "" else
"       <include name='" ++ substitute("/", ":", head(r).declaredName) ++ "/" ++ s ++ "' />\n" ++
  buildGrammarList(tail(r), s);
}

function writeClasses
IO ::= i::IO l::String s::[[String]]{
  return if null(s) then i else writeFile(l ++ head(head(s)) ++ ".java", head(tail(head(s))), writeClasses(i, l, tail(s)));
}

function makeInit
String ::= r::Decorated RootSpec extras::[String]{
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

makeOthers(r.moduleNames ++ extras, "init") ++ "\n" ++

"\t\t" ++ className ++ ".initAspectAttributeDefinitions();\n" ++
"\t\t" ++ className ++ ".initProductionAttributeDefinitions();\n" ++
"\t}\n\n" ++

"\tpublic static void postInit(){\n" ++
"\t\tif(" ++ className ++ ".postInit) return;\n\n" ++
"\t\t" ++ className ++ ".postInit = true;\n\n" ++
makeOthers(r.moduleNames ++ extras, "postInit") ++ "\n\n" ++
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
