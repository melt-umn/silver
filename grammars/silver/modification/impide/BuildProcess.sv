grammar silver:modification:impide;

import silver:driver;
import silver:translation:java:driver;
import silver:translation:java:core only makeParserName, makeName, makeClassName, makeNTClassName;

import silver:util:cmdargs;

{--
  The file where production compilation (used to be called buildWriteFile) is originally 
  defined in "silver/translation/java/driver/BuildProcess.sv"; here we're just aspecting 
  that, using '<-' to contribute things to the production attributes declared there.
--}

aspect production compilation
top::Compilation ::= g::Grammars  _  buildGrammar::String  benv::BuildEnv
{
  -- The RootSpec representing the grammar actually being built (specified on the command line)
  local builtGrammar :: [Decorated RootSpec] = searchEnvTree(buildGrammar, g.compiledGrammars);
  
  -- Empty if no ide decl in that grammar, otherwise has at least one spec... note that
  -- we're going to go with assuming there's just one IDE declaration...
  local ide :: IdeSpec = head(head(builtGrammar).ideSpecs);
  local isIde :: Boolean = !null(builtGrammar) && !null(head(builtGrammar).ideSpecs);

  local pkgName :: String = makeName(ide.pluginGrammar); -- should be equal to buildGrammar

  -- $${jg}/ide/$${ide.pkg.name}/plugin
  local pluginPath :: String = s"${benv.silverGen}ide/${pkgName}/plugin";
  top.postOps <- if !isIde then [] else [generateNCS(g.compiledGrammars, ide, pluginPath, pkgName)];

  classpathCompiler <- if !isIde then [] else ["${sh}/jars/IDEPluginRuntime.jar"];

  extraTopLevelDecls <- if !isIde then [] else [
    s"""
    <property name='grammar.path' value='${head(builtGrammar).grammarSource}'/>
    <property name='res' value='$${sh}/resources'/>
    <property name='ide.version' value='${ide.ideVersion}'/>
    <property name='lang.name' value='${ide.ideName}'/>
    <property name='lang.composed' value='${pkgName}'/>
    <property name='ide.pkg.name' value='${pkgName}'/>
    <property name='ide.proj.parent.path' location='$${jg}/ide/$${ide.pkg.name}'/>
    <property name='ide.proj.plugin.path' location='$${ide.proj.parent.path}/plugin'/>
    <property name='ide.pkg.path' location='$${ide.proj.plugin.path}/src/${pkgToPath(pkgName)}'/>
    <property name='ide.parser.classname' value='${ide.pluginParserClass}' />
    <target name='ide' depends='jars'>
      <tstamp>
        <format property='ide.build-timestamp' pattern='yyMMddHHmmss' timezone='UTC'/>
      </tstamp>
      <filter token="GROUP_ID" value='$${ide.pkg.name}'/>
      <filter token="PKG_NAME" value='$${ide.pkg.name}'/>
      <filter token="LANG_NAME" value='$${lang.name}'/>
      <filter token="IDE_VERSION" value='$${ide.version}'/>
      <filter token="IDE_BUILD_TIMESTAMP" value='$${ide.build-timestamp}'/>
      <filter token="LANG_COMPOSED" value='$${lang.composed}'/>
      <filter token="FEATURE_DESCRIPTION_URL" value='http://some.user.provided.url'/>
      <filter token="FEATURE_DESCRIPTION_TEXT" value='no description of the software'/>
      <filter token="FEATURE_COPYRIGHT_URL" value='http://some.user.provided.url'/>
      <filter token="FEATURE_COPYRIGHT_TEXT" value='no copyright information available'/>
      <filter token="FEATURE_LICENSE_URL" value='http://some.user.provided.url'/>
      <filter token="FEATURE_LICENSE_TEXT" value='no license information available'/>

    <!-- 1. create project structure -->
      <mkdir dir='$${ide.proj.plugin.path}/resource/'/>
      <mkdir dir='$${ide.pkg.path}/'/>
      <copy todir="$${ide.proj.parent.path}" overwrite="false" filtering="true">
        <fileset dir="$${res}/ide_skeleton"/>
      </copy>

    <!-- 2. copper parser -->
      <copper
          packageName='$${ide.pkg.name}.copper.parser'
          parserName='$${ide.parser.classname}'
          outputFile='$${ide.pkg.path}/copper/parser/$${ide.parser.classname}.java'
          useSkin='XML' warnUselessNTs='false' dumpFormat='HTML' dump='ERROR_ONLY'
          dumpFile='$${ide.parser.classname}.copperdump.html'>
        <inputs file='$${ide.pkg.path}/copper/parser/$${ide.parser.classname}.copper'/>
      </copper>
    <!-- 5. plugin dependencies -->
      <copy file="$${lang.composed}.jar" tofile="$${ide.proj.plugin.path}/$${lang.composed}.jar"/>
      <copy file="$${sh}/jars/CopperRuntime.jar" tofile="$${ide.proj.plugin.path}/CopperRuntime.jar"/>
      <copy file="$${sh}/jars/SilverRuntime.jar" tofile="$${ide.proj.plugin.path}/SilverRuntime.jar"/>
      <copy file="$${sh}/jars/IDEPluginRuntime.jar" tofile="$${ide.proj.plugin.path}/IDEPluginRuntime.jar"/>
    <!-- 10. Ide resources -->
${implode("", map(copyIdeResource, ide.ideResources))}
    </target>
"""
    ];

  extraDistDeps <- if !isIde then [] else ["ide"]; -- Here's where we demand that target be built ('dist' is a dummy target that just depends on 'jars' initially)
  
  -- We're not actually using the generated (by silver / ant) jar as an OSGi bundle
  -- and if we ever do, we should probably put this as part of the normal build process instead.
  -- extraManifestAttributes <-
}


function toUpperCase
String ::= original::String
{
  return error("Not Yet Implemented: toUpperCase");
} foreign {
  "java" : return "(new common.StringCatter(%original%.toString().toUpperCase()))";
}

function pkgToPath
String ::= pkg::String
{
  return substitute(".", "/", pkg);
}

function copyIdeResource
String ::= r::Pair<String String>
{
  return s"""
      <mkdir dir='$${ide.proj.plugin.path}/resource/${r.fst}'/>
      <copy todir="$${ide.proj.plugin.path}/resource/${r.fst}" overwrite="false" filtering="false">
        <fileset dir="$${grammar.path}/${r.snd}"/>
      </copy>
""";
}
