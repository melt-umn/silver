grammar silver:translation:java:driver;
import silver:translation:java:core;

import silver:driver;
import silver:definition:env;
import silver:definition:core;

import silver:util;
import silver:util:cmdargs;

synthesized attribute noJavaGeneration :: Boolean occurs on CmdArgs;
synthesized attribute buildSingleJar :: Boolean occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= _
{
  top.noJavaGeneration = false;
  top.buildSingleJar = false;
}
abstract production xjFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.noJavaGeneration = true;
  forwards to rest;
}
abstract production onejarFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.buildSingleJar = true;
  forwards to rest;
}


aspect production run
top::RunUnit ::= iIn::IO args::[String]
{
  flags <- [pair("--xj", flag(xjFlag)),
            pair("--onejar", flag(onejarFlag))
           ];
  flagdescs <- ["\t--onejar: include runtime libraries in the jar\n"];

  postOps <- if a.noJavaGeneration then [] else 
    [genJava(a, grammarsToTranslate, silvergen), 
     genBuild(a, grammarsDependedUpon, silverhome, silvergen, depAnalysis)]; 
}


abstract production genJava
top::Unit ::= a::Decorated CmdArgs  specs::[Decorated RootSpec]  silvergen::String
{
  local attribute pr::IO;
  pr = print("Generating Java Translation.\n", top.ioIn);

  local attribute i :: IO;
  i = writeAll(pr, a, specs, silvergen);
 
  top.io = i;
  top.code = 0;
  top.order = 4;
}

abstract production genBuild
top::Unit ::= a::Decorated CmdArgs allspecs::[String] silverhome::String silvergen::String da::Decorated DependencyAnalysis
{
  local attribute buildFile :: IO;
  buildFile = writeBuildFile(top.ioIn, a, allspecs, silverhome, silvergen, da);

  top.io = buildFile;
  top.code = 0;
  top.order = 6;
}

function writeAll
IO ::= i::IO  a::Decorated CmdArgs  l::[Decorated RootSpec]  silvergen::String
{
  local attribute now :: IO;
  now = writeSpec(i, head(l), a, silvergen);

  local attribute recurse :: IO;
  recurse = writeAll(now, a, tail(l), silvergen);

  return if null(l) then i else recurse;
}

function writeSpec
IO ::= i::IO  r::Decorated RootSpec  a::Decorated CmdArgs  silvergen::String
{
  local attribute printio :: IO;
  printio = print("\t[" ++ r.declaredName ++ "]\n", i);

  local attribute package :: String;
  package = grammarToPath(r.declaredName);

  production attribute specLocation :: String;
  specLocation = silvergen ++ "/src/" ++ package; 

  local attribute mki :: IO;
  mki = writeFile(specLocation ++ "Init.java", makeInit(r), printio);

  local attribute mains :: [Decorated DclInfo];
  mains = getValueDcl(r.declaredName ++ ":main", toEnv(r.defs));

  local attribute mainIO :: IO;
  mainIO = if null(mains) then mki else writeFile(specLocation ++ "Main.java", makeMain(r), mki);

  return writeClasses(mainIO, specLocation, r.javaClasses);
}

function makeMain
String ::= r::Decorated RootSpec
{
  local attribute package :: String;
  package = makeName(r.declaredName);

  return 
"package " ++ package ++ ";\n\n" ++

"public class Main {\n" ++
"\tpublic static void main(String[] args) {\n" ++
"\t\t" ++ package ++ ".Init.initAllStatics();\n" ++
"\t\t" ++ package ++ ".Init.init();\n" ++
"\t\t" ++ package ++ ".Init.postInit();\n" ++
"\t\ttry {\n" ++
"\t\t\tcommon.Node rv = (common.Node) " ++ package ++ ".Pmain.invoke(new Object[]{cvargs(args), null});\n" ++
"\t\t\tcommon.DecoratedNode drv = rv.decorate(common.TopNode.singleton, (common.Lazy[])null);\n" ++
"\t\t\tdrv.synthesized(core.Init.core_io__ON__core_IOVal); // demand the io token\n" ++
"\t\t\tSystem.exit( (Integer)drv.synthesized(core.Init.core_iovalue__ON__core_IOVal) );\n" ++
"\t\t} catch(Throwable t) {\n" ++
"\t\t\tcommon.Util.printStackCauses(t);\n" ++
"\t\t}\n" ++
"\t}\n" ++
"\tpublic static common.ConsCell cvargs(String [] args){\n" ++ 
"\t\tcommon.ConsCell result = common.ConsCell.nil;\n" ++ 
"\t\tfor(int i = args.length - 1; i >= 0; i --) {\n" ++ 
"\t\t\tresult = new common.ConsCell(new common.StringCatter(args[i]), result);\n" ++ 
"\t\t}\n" ++ 
"\t\treturn result;\n" ++ 
"\t}\n" ++ 
"}\n";
}

function writeBuildFile
IO ::= i::IO a::Decorated CmdArgs specs::[String] silverhome::String silvergen::String da::Decorated DependencyAnalysis
{
  production attribute extraTargets :: [String] with ++;
  extraTargets := [];

  production attribute extraTaskdefs :: [String] with ++;
  extraTaskdefs := [];

  production attribute extraDepends :: [String] with ++;
  extraDepends := ["init"];

  local attribute outputFile :: String;
  outputFile = if length(a.outName) > 0 then a.outName else (makeName(a.buildGrammar) ++ ".jar");

  -- TODO: this is local directory! move build.xml to generated space
  return writeFile("build.xml", buildXml, i);

  local attribute buildXml :: String;
  buildXml =    
"<project name='" ++ a.buildGrammar ++ "' default='dist' basedir='.'>\n" ++
"  <description>Build the grammar " ++ a.buildGrammar ++ " </description>\n\n" ++

"  <property environment='env'/>\n" ++
"  <property name='jg' location='" ++ silvergen ++ "'/>\n" ++
"  <property name='sh' location='" ++ silverhome ++ "'/>\n" ++ 
"  <property name='bin' location='${jg}/bin'/>\n" ++
"  <property name='src' location='${jg}/src'/>\n\n" ++

"  <path id='lib.classpath'>\n" ++
"    <fileset dir='${sh}/jars' includes='SilverRuntime.jar CopperRuntime.jar CopperCompiler.jar' />\n" ++ -- TODO: separate this information out. (runtime/compiler cps)
"  </path>\n\n" ++

"  <path id='src.classpath'>\n" ++
"    <pathelement location='${src}' />\n" ++
"  </path>\n\n" ++

"  <path id='compile.classpath'>\n" ++
"    <path refid='src.classpath'/>\n" ++
"    <path refid='lib.classpath'/>\n" ++
"  </path>\n\n" ++

implode("\n", extraTaskdefs) ++ "\n\n" ++

"  <target name='init'>\n\n" ++
"    <!-- Create the time stamp -->\n" ++
"    <tstamp>\n" ++
"      <format property='TIME' pattern='MM/dd/yyyy hh:mm aa'/>\n" ++
"    </tstamp>\n\n" ++

"    <mkdir dir='${bin}'/>\n" ++
"  </target>\n\n" ++

"  <target name='dist' depends='grammars'>\n\n" ++

"    <pathconvert refid='lib.classpath' pathsep=' ' property='man.classpath' />\n" ++

"    <jar destfile='" ++ outputFile ++ "' basedir='${bin}'>\n" ++

    buildGrammarList(specs, "*.class") ++ 

"      <manifest>\n" ++
"       <attribute name='Main-Class' value='" ++ makeName(a.buildGrammar) ++ ".Main' />\n" ++

(if !a.buildSingleJar then 
"       <attribute name='Class-Path' value='${man.classpath}' />\n"
 else "") ++
"       <attribute name='Built-By' value='${user.name}' />\n" ++
"       <attribute name='Implementation-Version' value='${TIME}' />\n" ++
"      </manifest>\n" ++

-- If we're building a single jar, then include it, and don't write out a script.
(if a.buildSingleJar then
"      <zipfileset src='${sh}/jars/CopperRuntime.jar' excludes='META-INF/*' />\n" ++
"      <zipfileset src='${sh}/jars/SilverRuntime.jar' excludes='META-INF/*' />\n"
 else "") ++
 
"    </jar>\n\n" ++

"  </target>\n\n" ++

"  <target name='grammars' depends='" ++ implode(", ", extraDepends) ++ "'>\n" ++

"      <javac debug='on' classpathref='compile.classpath' srcdir='${src}' destdir='${bin}' includeantruntime='false'>\n" ++
    buildGrammarList(specs, "*.java") ++ 
"      </javac>\n" ++
"  </target>\n\n" ++

implode("\n", extraTargets) ++ "\n\n" ++

"</project>\n";
}

function buildGrammarList
String ::= r::[String] s::String
{
  return if null(r) then "" else
"       <include name='" ++ grammarToPath(head(r)) ++ s ++ "' />\n" ++ buildGrammarList(tail(r), s);
}

function writeClasses
IO ::= i::IO l::String s::[[String]]
{
  return if null(s) then i else writeFile(l ++ head(head(s)) ++ ".java", head(tail(head(s))), writeClasses(i, l, tail(s)));
}

function makeInit
String ::= r::Decorated RootSpec
{
  local attribute className :: String;
  className = makeName(r.declaredName) ++ ".Init";

  return 
"package " ++ makeName(r.declaredName) ++ ";\n\n" ++

"public class Init{\n\n" ++

"\tprivate static boolean preInit = false;\n" ++
"\tprivate static boolean init = false;\n" ++
"\tprivate static boolean postInit = false;\n\n" ++

"\tpublic static void initAllStatics(){\n" ++
"\t\tif(" ++ className ++ ".preInit) return;\n\n" ++
"\t\t" ++ className ++ ".preInit = true;\n\n" ++

makeOthers(r.allGrammarDependencies, "initAllStatics") ++ "\n" ++

"\t}\n\n" ++


"\tpublic static void init(){\n" ++
"\t\tif(" ++ className ++ ".init) return;\n\n" ++
"\t\t" ++ className ++ ".init = true;\n\n" ++

"\t\t" ++ className ++ ".setupInheritedAttributes();\n\n" ++	

makeOthers(r.allGrammarDependencies, "init") ++ "\n" ++

"\t\t" ++ className ++ ".initProductionAttributeDefinitions();\n" ++

"\t}\n\n" ++

"\tpublic static void postInit(){\n" ++
"\t\tif(" ++ className ++ ".postInit) return;\n\n" ++
"\t\t" ++ className ++ ".postInit = true;\n\n" ++

makeOthers(r.allGrammarDependencies, "postInit") ++ "\n\n" ++

r.postInit ++

"\t}\n\n" ++

"\tprivate static void setupInheritedAttributes(){\n" ++
r.setupInh ++
"\t}\n\n" ++

"\tprivate static void initProductionAttributeDefinitions(){\n" ++
r.initProd ++
"\t}\n\n" ++

r.initWeaving ++ 
r.valueWeaving ++

r.initValues ++

"}\n";

}

function makeOthers
String ::= others::[String] nme::String
{
  return if null(others) then "" else "\t\t" ++ makeName(head(others)) ++ ".Init."++nme++"();\n" ++ makeOthers(tail(others),nme);
}
