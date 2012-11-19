grammar silver:translation:java:driver;

import silver:translation:java:core;

import silver:driver;
import silver:definition:env;
import silver:definition:core;

import silver:util;
import silver:util:cmdargs;

import silver:modification:copper only allParsers; -- TODO REMOVE THIS DEP
import silver:definition:concrete_syntax only ParserSpec; -- DITTO

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
  flagdescs <- ["\t--onejar  : include runtime libraries in the jar"];
}
aspect function runActions
IOVal<Integer> ::=
  silverHome::String
  silverGen::String
  buildGrammar::String
  unit::Decorated Compilation
  ioin::IO
{
  postOps <- if unit.config.noJavaGeneration then [] else 
    [genJava(unit.config, grammarsToTranslate, silverGen), 
     genBuild(unit.config, grammarsDependedUpon, silverHome, silverGen, allParsers)]; 
}


abstract production genJava
top::Unit ::= a::Decorated CmdArgs  specs::[Decorated RootSpec]  silverGen::String
{
  local attribute pr::IO;
  pr = print("Generating Java Translation.\n", top.ioIn);

  local attribute i :: IO;
  i = writeAll(pr, a, specs, silverGen);
 
  top.io = i;
  top.code = 0;
  top.order = 4;
}

abstract production genBuild
top::Unit ::= a::Decorated CmdArgs allspecs::[String] silverhome::String silverGen::String allParsers::[ParserSpec]
{
  local attribute buildFile :: IO;
  buildFile = writeBuildFile(top.ioIn, a, allspecs, silverhome, silverGen, allParsers);

  top.io = buildFile;
  top.code = 0;
  top.order = 6;
}

function writeAll
IO ::= i::IO  a::Decorated CmdArgs  l::[Decorated RootSpec]  silverGen::String
{
  local attribute now :: IO;
  now = writeSpec(i, head(l), silverGen);

  local attribute recurse :: IO;
  recurse = writeAll(now, a, tail(l), silverGen);

  return if null(l) then i else recurse;
}

function writeSpec
IO ::= i::IO  r::Decorated RootSpec  silverGen::String
{
  local attribute printio :: IO;
  printio = print("\t[" ++ r.declaredName ++ "]\n", i);

  production attribute specLocation :: String;
  specLocation = silverGen ++ "src/" ++ grammarToPath(r.declaredName); 

  return writeClasses(printio, specLocation, r.genFiles);
}

function writeClasses
IO ::= i::IO l::String s::[Pair<String String>]
{
  return if null(s) then i else writeFile(l ++ head(s).fst, head(s).snd, writeClasses(i, l, tail(s)));
}

function writeBuildFile
IO ::= i::IO a::Decorated CmdArgs specs::[String] silverhome::String silverGen::String allParsers::[ParserSpec]
{
  -- The prefix 'extra' here is used partially for historical reasons, and partially
  -- because it makes it easy to search/highlight all uses of these

  local builtGrammar :: String = head(a.buildGrammar);

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
    "<attribute name='Main-Class' value='" ++ makeName(builtGrammar) ++ ".Main' />"]; -- TODO: we "should" make main depend on whether there is a main...

  extraManifestAttributes <-
    if a.buildSingleJar then []
    else ["<attribute name='Class-Path' value='${man.classpath}' />"];
  
  local attribute outputFile :: String;
  outputFile = if !null(a.outName) then head(a.outName) else makeName(builtGrammar) ++ ".jar";

  -- TODO: this is local directory! move build.xml to generated space
  return writeFile("build.xml", buildXml, i);

  local attribute buildXml :: String;
  buildXml =    
"<project name='" ++ builtGrammar ++ "' default='dist' basedir='.'>\n" ++
"  <description>Generated build script for the grammar " ++ builtGrammar ++ "</description>\n\n" ++

"  <property environment='env'/>\n" ++
"  <property name='jg' location='" ++ silverGen ++ "'/>\n" ++
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

