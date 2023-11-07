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
-- TODO: Should this be a Maybe?
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
abstract production dontTranslateFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.noJavaGeneration = true;
  forwards to @rest;
}
abstract production onejarFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.buildSingleJar = true;
  forwards to @rest;
}
abstract production relativejarFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.relativeJar = true;
  forwards to @rest;
}
abstract production includeRTJarFlag
top::CmdArgs ::= s::String rest::CmdArgs
{
  top.includeRTJars = s :: forward.includeRTJars;
  forwards to @rest;
}
abstract production buildXmlFlag
top::CmdArgs ::= s::String rest::CmdArgs
{
  top.buildXmlLocation = s :: forward.buildXmlLocation;
  forwards to @rest;
}

aspect function parseArgs
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [ flagSpec(name="--dont-translate", paramString=nothing(),
               help="check for errors without producing Java code",
               flagParser=flag(dontTranslateFlag))
           , flagSpec(name="--onejar", paramString=nothing(),
               help="a typo of --one-jar",
               flagParser=flag(onejarFlag))
           , flagSpec(name="--one-jar", paramString=nothing(),
               help="include runtime libraries in the JAR",
               flagParser=flag(onejarFlag))
           , flagSpec(name="--relative-jar", paramString=nothing(),
               help="assume runtime libraries will be in the same directory as the JAR",
               flagParser=flag(relativejarFlag))
           , flagSpec(name="--include-jar", paramString=nothing(),
               help="links to an additional JAR",
               flagParser=option(includeRTJarFlag))
           , flagSpec(name="--build-xml-location", paramString=nothing(),
               help="sets the path the Ant build.xml will be saved to. Used by Eclipse",
               flagParser=option(buildXmlFlag))
           ];
}
aspect production compilation
top::Compilation ::= g::Grammars  _  buildGrammars::[String]  benv::BuildEnv
{
  -- Main class, jar name, etc. are based on the first specified grammar.
  local buildGrammar::String = head(buildGrammars);

  -- This is a little bit of a hack. It's only job is to allow the Eclipse support
  -- for Silver to put this file elsewhere than the local directory.
  -- e.g. --build-xml-location /path/to/workspace/project/build.xml
  local buildXmlLocation :: String =
    if null(top.config.buildXmlLocation) then "build.xml"
    else head(top.config.buildXmlLocation);
  
  production attribute keepFiles :: [String] with ++;
  keepFiles := [];

  -- Seed flow deps with {config}
  keepFiles <- if false then error(genericShow(top.config)) else [];

  top.postOps <-
    [genBuild(buildXmlLocation, buildXml)] ++
    (if top.config.noJavaGeneration then []
     else [genJava(top.config, benv.silverGen, keepFiles, grammarsToTranslate)]);

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
"      <compilerarg value=\"-Xlint:unchecked\" />\n" ++
    flatMap(includeJavaFiles, grammarsDependedUpon) ++
"    </javac>\n" ++
"  </target>\n" ++
"</project>\n";
}

abstract production genJava
top::DriverAction ::= a::Decorated CmdArgs  silverGen::String  keepFiles::[String]  specs::[Decorated RootSpec]
{
  top.run = do {
    eprintln("Generating Translation.");
    traverse_(writeSpec(silverGen, keepFiles, _), specs);
    return 0;
  };
  top.order = 4;
}

abstract production genBuild
top::DriverAction ::= buildFileLocation::String  buildXml::String
{
  top.run = do { writeFile(buildFileLocation, buildXml); return 0; };
  top.order = 6;
}

function writeSpec
IO<()> ::= silverGen::String keepFiles::[String] r::Decorated RootSpec
{
  local srcPath :: String = silverGen ++ "src/" ++ grammarToPath(r.declaredName);
  local binPath :: String = silverGen ++ "bin/" ++ grammarToPath(r.declaredName);

  return do {
    eprintln("\t[" ++ r.declaredName ++ "]");
    isD::Boolean <- isDirectory(srcPath);
    unless(isD, do {
      mkDSuccess::Boolean <- mkdir(srcPath);
      unless(mkDSuccess, do {
      eprintln("Unrecoverable Error: Unable to create directory: " ++ srcPath ++
        "\nWarning: if some interface file writes were successful, but others not, Silver's temporaries are in an inconsistent state. Use the --clean flag next run.");
        exit(-5);
      });
    });
    srcDirContents::[String] <- listContents(srcPath);
    oldSrcFiles::[String] <- filterM(isFile, map(append(srcPath, _), srcDirContents));
    deleteFiles(removeAll(keepFiles, oldSrcFiles));
    deleteDirFiles(binPath);
    writeFiles(srcPath, r.genFiles);
    writeBinaryFiles(srcPath, r.genBinaryFiles);
  };
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

