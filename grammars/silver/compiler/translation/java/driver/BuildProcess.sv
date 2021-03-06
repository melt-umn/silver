grammar silver:compiler:translation:java:driver;

import silver:compiler:translation:java:core;

import silver:compiler:driver;
import silver:compiler:definition:env;
import silver:compiler:definition:core;

import silver:util:cmdargs;

import silver:reflect:nativeserialize;

synthesized attribute noJavaGeneration :: Boolean occurs on CmdArgs;
synthesized attribute buildSingleJar :: Boolean occurs on CmdArgs;
synthesized attribute relativeJar :: Boolean occurs on CmdArgs;
synthesized attribute includeRTJars :: [String] occurs on CmdArgs;
synthesized attribute buildXmlLocation :: [String] occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= _
{
  top.noJavaGeneration = false;
  top.buildSingleJar = false;
  top.relativeJar = false;
  top.includeRTJars = [];
  top.buildXmlLocation = [];
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
abstract production relativejarFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.relativeJar = true;
  forwards to rest;
}
abstract production includeRTJarFlag
top::CmdArgs ::= s::String rest::CmdArgs
{
  top.includeRTJars = s :: forward.includeRTJars;
  forwards to rest;
}
abstract production buildXmlFlag
top::CmdArgs ::= s::String rest::CmdArgs
{
  top.buildXmlLocation = s :: forward.buildXmlLocation;
  forwards to rest;
}

aspect function parseArgs
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [pair("--dont-translate", flag(xjFlag)),
            pair("--onejar", flag(onejarFlag)),
            pair("--one-jar", flag(onejarFlag)),
            pair("--relative-jar", flag(relativejarFlag)),
            pair("--include-jar", option(includeRTJarFlag)),
            pair("--build-xml-location", option(buildXmlFlag))
           ];
  flagdescs <- ["\t--one-jar  : include runtime libraries in the jar"];
}
aspect production compilation
top::Compilation ::= g::Grammars  _  buildGrammar::String  benv::BuildEnv
{
  -- This is a little bit of a hack. It's only job is to allow the Eclipse support
  -- for Silver to put this file elsewhere than the local directory.
  -- e.g. --build-xml-location /path/to/workspace/project/build.xml
  local buildXmlLocation :: String =
    if null(top.config.buildXmlLocation) then "build.xml"
    else head(top.config.buildXmlLocation);
  
  top.postOps <-
    [genBuild(buildXmlLocation, buildXml)] ++
    (if top.config.noJavaGeneration then [] else [genJava(top.config, grammarsToTranslate, benv.silverGen)]);

  -- From here on, it's all build.xml stuff:

  -- Presently, copper, copper_mda, and impide all contribute new targets into build.xml:
  production attribute extraTopLevelDecls :: [String] with ++;
  extraTopLevelDecls := [];

  -- Presently, impide and copper_mda introduce a new top-level goal:
  production attribute extraDistDeps :: [String] with ++;
  extraDistDeps := if top.config.noJavaGeneration then [] else ["jars"];
  
  -- Presently, unused?
  production attribute extraJarsDeps :: [String] with ++;
  extraJarsDeps := ["grammars"];

  -- Presently, copper and copper_mda
  production attribute extraGrammarsDeps :: [String] with ++;
  extraGrammarsDeps := ["init"];
  
  production attribute classpathCompiler :: [String] with ++;
  classpathCompiler := ["${sh}/jars/commonmark-0.17.1.jar"];
  
  production attribute classpathRuntime :: [String] with ++;
  classpathRuntime := ["${sh}/jars/commonmark-0.17.1.jar", "${sh}/jars/SilverRuntime.jar"];
  
  -- The --XRTjar hack
  classpathRuntime <- top.config.includeRTJars;

  production attribute extraManifestAttributes :: [String] with ++;
  extraManifestAttributes := [
    "<attribute name='Built-By' value='${user.name}' />",
    "<attribute name='Implementation-Version' value='${TIME}' />",
    "<attribute name='Main-Class' value='" ++ makeName(buildGrammar) ++ ".Main' />"]; -- TODO: we "should" make main depend on whether there is a main...

  extraManifestAttributes <-
    if top.config.buildSingleJar then []
    else ["<attribute name='Class-Path' value='${man.classpath}' />"];
  
  local attribute outputFile :: String;
  outputFile = if !null(top.config.outName) then head(top.config.outName)
    else (if g.jarName.isJust then g.jarName.fromJust else makeName(buildGrammar)) ++ ".jar";

  local attribute buildXml :: String;
  buildXml =    
"<project name='" ++ buildGrammar ++ "' default='dist' basedir='.'>\n" ++
"  <description>Generated build script for the grammar " ++ buildGrammar ++ "</description>\n\n" ++

"  <property environment='env'/>\n" ++
"  <property name='jg' location='" ++ benv.silverGen ++ "'/>\n" ++
"  <property name='sh' location='" ++ benv.silverHome ++ "'/>\n" ++ 
"  <property name='bin' location='${jg}/bin'/>\n" ++
"  <property name='src' location='${jg}/src'/>\n\n" ++

"  <path id='lib.classpath'>\n" ++
    flatMap(pathLocation, classpathRuntime) ++
"  </path>\n\n" ++

"  <path id='compile.classpath'>\n" ++
"    <pathelement location='${src}' />\n" ++
"    <path refid='lib.classpath'/>\n" ++
    flatMap(pathLocation, classpathCompiler) ++
    flatMap(pathLocation, map(\s::String -> s ++ "bin/", benv.silverHostGen)) ++
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
-- Uncondintionally compute this, but it's included conditionally as a manifest attribute
"    <pathconvert refid='lib.classpath' pathsep=' ' property='man.classpath'>\n" ++
(
 if top.config.relativeJar then
-- Removes all paths from the classpath. This means we expect to find all these
-- jars in the same directory as this jar.
"      <flattenmapper />\n"
 else
-- Escape spaces as url-encoded spaces. maybe there's a better way?
-- This solves the problem of spaces in paths, where Class-Path in manifests are split on spaces.
"      <filtermapper><replacestring from=' ' to='%20' /></filtermapper>\n"
) ++
"    </pathconvert>\n" ++
"    <jar destfile='" ++ outputFile ++ "' zip64Mode='as-needed'>\n" ++
    flatMap(includeClassFiles, grammarsRelevant) ++
"      <manifest>\n" ++
"        " ++ implode("\n        ", extraManifestAttributes) ++ "\n" ++
"      </manifest>\n" ++

-- If we're building a single jar, then include the runtimes TODO: this method kinda sucks
    (if top.config.buildSingleJar then implode("", map(zipfileset, classpathRuntime)) else "") ++
 
"    </jar>\n" ++
"  </target>\n\n" ++

"  <target name='grammars' depends='" ++ implode(", ", extraGrammarsDeps) ++ "'>\n" ++
"    <javac debug='on' classpathref='compile.classpath' srcdir='${src}' destdir='${bin}' includeantruntime='false' source='1.8' target='1.8' release='8'>\n" ++
    flatMap(includeJavaFiles, grammarsDependedUpon) ++
"    </javac>\n" ++
"  </target>\n" ++
"</project>\n";
}

abstract production genJava
top::DriverAction ::= a::Decorated CmdArgs  specs::[Decorated RootSpec]  silverGen::String
{
  local pr :: IO = print("Generating Translation.\n", top.ioIn);

  top.io = writeAll(pr, a, specs, silverGen);
  top.code = 0;
  top.order = 4;
}

abstract production genBuild
top::DriverAction ::= buildFileLocation::String  buildXml::String
{
  top.io = writeFile(buildFileLocation, buildXml, top.ioIn);
  top.code = 0;
  top.order = 6;
}

function writeAll
IO ::= i::IO  a::Decorated CmdArgs  l::[Decorated RootSpec]  silverGen::String
{
  local now :: IO = writeSpec(i, head(l), silverGen);
  local recurse :: IO = writeAll(now, a, tail(l), silverGen);

  return if null(l) then i else recurse;
}

function writeSpec
IO ::= i::IO  r::Decorated RootSpec  silverGen::String
{
  local srcPath :: String = silverGen ++ "src/" ++ grammarToPath(r.declaredName);
  local binPath :: String = silverGen ++ "bin/" ++ grammarToPath(r.declaredName);

  local mkiotest :: IOVal<Boolean> =
    isDirectory(srcPath, i);
  local mksrc :: IOVal<Boolean> =
    if mkiotest.iovalue then mkiotest else mkdir(srcPath, mkiotest.io);
  local clean :: IO =
    deleteDirFiles(srcPath, deleteDirFiles(binPath, mksrc.io).io).io;
  
  local printio :: IO =
    if mksrc.iovalue
    then print("\t[" ++ r.declaredName ++ "]\n", clean)
    else exit(-5, print("\nUnrecoverable Error: Unable to create directory: " ++ srcPath ++ "\nWarning: if some interface file writes were successful, but others not, Silver's temporaries are in an inconsistent state. Use the --clean flag next run.\n\n", mksrc.io));

  return writeBinaryFiles(srcPath, r.genBinaryFiles, writeFiles(srcPath, r.genFiles, printio));
}

{--
 - Given a path (with terminating /) and list of (file names relative to that root, contents),
 - write these out.
 -}
function writeFiles
IO ::= path::String s::[Pair<String String>] i::IO
{
  return if null(s) then i else writeFile(path ++ head(s).fst, head(s).snd, writeFiles(path, tail(s), i));
}

function writeBinaryFiles
IO ::= path::String s::[Pair<String ByteArray>] i::IO
{
  return if null(s) then i else writeBinaryFile(path ++ head(s).fst, head(s).snd, writeBinaryFiles(path, tail(s), i));
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
function includeJavaFiles
String ::= gram::String
{
  return s"      <include name='${grammarToPath(gram)}*.java' />\n";
}
function includeClassFiles
String ::= gram::Decorated RootSpec
{
  return s"      <fileset dir='${gram.generateLocation}bin/' includes='${grammarToPath(gram.declaredName)}*.class' />\n";
}

