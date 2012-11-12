grammar silver:translation:java:driver;

import silver:translation:java:core;

import silver:driver;
import silver:definition:env;
import silver:definition:core;

import silver:util;
import silver:util:cmdargs;

synthesized attribute noJavaGeneration :: Boolean occurs on CmdArgs;
synthesized attribute buildSingleJar :: Boolean occurs on CmdArgs;
synthesized attribute includeRTJars :: [String] occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= _
{
  top.noJavaGeneration = false;
  top.buildSingleJar = false;
  top.includeRTJars = [];
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
abstract production includeRTJarFlag
top::CmdArgs ::= s::String rest::CmdArgs
{
  top.includeRTJars = s :: forward.includeRTJars;
  forwards to rest;
}

aspect function parseArgs
ParseResult<Decorated CmdArgs> ::= args::[String]
{
  flags <- [pair("--dont-translate", flag(xjFlag)),
            pair("--onejar", flag(onejarFlag)),
            pair("--XRTjar", option(includeRTJarFlag))
           ];
  flagdescs <- ["\t--onejar: include runtime libraries in the jar\n"];
}
aspect production run
top::RunUnit ::= iIn::IO args::[String]
{
  postOps <- if a.noJavaGeneration then [] else 
    [genJava(a, grammarsToTranslate, silvergen), 
     genBuild(a, grammarsDependedUpon, silverhome, silvergen, depAnalysis, grammarLocationString)]; 
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
top::Unit ::= a::Decorated CmdArgs allspecs::[String] silverhome::String silvergen::String da::Decorated DependencyAnalysis grammarLoc::String
{
  local attribute buildFile :: IO;
  buildFile = writeBuildFile(top.ioIn, a, allspecs, silverhome, silvergen, da, grammarLoc);

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

  local attribute mains :: [DclInfo];
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
IO ::= i::IO a::Decorated CmdArgs specs::[String] silverhome::String silvergen::String da::Decorated DependencyAnalysis grammarLoc::String
{
  -- The prefix 'extra' here is used partially for historical reasons, and partially
  -- because it makes it easy to search/highlight all uses of these

  production attribute extraTopLevelDecls :: [String] with ++;
  extraTopLevelDecls := [];

  production attribute extraDistDeps :: [String] with ++;
  extraDistDeps := ["jars"];
  
  production attribute extraJarsDeps :: [String] with ++;
  extraJarsDeps := ["grammars"];

  production attribute extraGrammarsDeps :: [String] with ++;
  extraGrammarsDeps := ["init"];
  
  production attribute classpathCompiler :: [String] with ++;
  classpathCompiler := [];
  
  production attribute classpathRuntime :: [String] with ++;
  classpathRuntime := ["${sh}/jars/SilverRuntime.jar"];
  
  -- The --XRTjar hack
  classpathRuntime <- a.includeRTJars;

  production attribute extraManifestAttributes :: [String] with ++;
  extraManifestAttributes := [
    "<attribute name='Built-By' value='${user.name}' />",
    "<attribute name='Implementation-Version' value='${TIME}' />",
    "<attribute name='Main-Class' value='" ++ makeName(a.buildGrammar) ++ ".Main' />"]; -- TODO: we "should" make main depend on whether there is a main...

  extraManifestAttributes <-
    if a.buildSingleJar then []
    else ["<attribute name='Class-Path' value='${man.classpath}' />"];
  
  local attribute outputFile :: String;
  outputFile = if length(a.outName) > 0 then a.outName else (makeName(a.buildGrammar) ++ ".jar");

  -- TODO: this is local directory! move build.xml to generated space
  return writeFile("build.xml", buildXml, i);

  local attribute buildXml :: String;
  buildXml =    
"<project name='" ++ a.buildGrammar ++ "' default='dist' basedir='.'>\n" ++
"  <description>Generated build script for the grammar " ++ a.buildGrammar ++ "</description>\n\n" ++

"  <property environment='env'/>\n" ++
"  <property name='jg' location='" ++ silvergen ++ "'/>\n" ++
"  <property name='sh' location='" ++ silverhome ++ "'/>\n" ++ 
"  <property name='bin' location='${jg}/bin'/>\n" ++
"  <property name='src' location='${jg}/src'/>\n\n" ++

"  <path id='lib.classpath'>\n" ++
    implode("", map(pathLocation, classpathRuntime)) ++
"  </path>\n\n" ++

"  <path id='compile.classpath'>\n" ++
"    <pathelement location='${src}' />\n" ++
"    <path refid='lib.classpath'/>\n" ++
    implode("", map(pathLocation, classpathCompiler)) ++
"  </path>\n\n" ++

implode("\n\n", extraTopLevelDecls) ++ "\n\n" ++

"  <target name='init'>\n" ++
"    <tstamp>\n" ++
"      <format property='TIME' pattern='yyyy.MM.dd.HH.mm.ss'/>\n" ++
"    </tstamp>\n" ++
"    <mkdir dir='${bin}'/>\n" ++
"  </target>\n\n" ++

"  <target name='dist' depends='" ++ implode(", ", extraDistDeps) ++ "'>\n" ++
"  </target>\n\n" ++

"  <target name='jars' depends='" ++ implode(", ", extraJarsDeps) ++ "'>\n" ++
"    <pathconvert refid='lib.classpath' pathsep=' ' property='man.classpath' />\n" ++
"    <jar destfile='" ++ outputFile ++ "' basedir='${bin}'>\n" ++
    implode("", map(includeName(_, "*.class"), specs)) ++ 
"      <manifest>\n" ++
"        " ++ implode("\n        ", extraManifestAttributes) ++ "\n" ++
"      </manifest>\n" ++

-- If we're building a single jar, then include the runtimes TODO: this method kinda sucks
    (if a.buildSingleJar then implode("", map(zipfileset, classpathRuntime)) else "") ++
 
"    </jar>\n" ++
"  </target>\n\n" ++

"  <target name='grammars' depends='" ++ implode(", ", extraGrammarsDeps) ++ "'>\n" ++
"    <javac debug='on' classpathref='compile.classpath' srcdir='${src}' destdir='${bin}' includeantruntime='false'>\n" ++
    implode("", map(includeName(_, "*.java"), specs)) ++ 
"    </javac>\n" ++
"  </target>\n" ++
"</project>\n";
}

function zipfileset
String ::= s::String
{
  return "      <zipfileset src='" ++ s ++ "' excludes='META-INF/*' />\n";
}
function pathLocation
String ::= s::String
{
  return "    <pathelement location='" ++ s ++ "' />\n";
}
function includeName
String ::= gram::String suffix::String
{
  return "      <include name='" ++ grammarToPath(gram) ++ suffix ++ "' />\n";
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

