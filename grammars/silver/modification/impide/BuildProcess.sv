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
    <macrodef name="getIDERuntimeVersion">
      <sequential>
        <loadproperties>
          <!-- From ZIP entries in runtime jar, -->
          <zipentry zipfile="$${sh}/jars/IDEPluginRuntime.jar" name="META-INF/MANIFEST.MF"/>
          <filterchain>
            <!-- load the line containing "Bundle-Version", -->
            <linecontains>
              <contains value="Bundle-Version"/>
            </linecontains>
            <!-- as Ant property, with name set to be "ide_rt.Bundle-Version". -->
            <prefixlines prefix="ide_rt."/>
          </filterchain>
        </loadproperties>
      </sequential>
    </macrodef>
    <!-- Load version of IDE runtime into $${ide_rt.Bundle-Version} -->
    <getIDERuntimeVersion />
    <property name='ide.rt.version' value='$${ide_rt.Bundle-Version}'/>
    <property name='grammar.path' value='${head(builtGrammar).grammarSource}'/>
    <property name='res' value='$${sh}/resources'/>
    <property name='ide.version' value='${ide.ideVersion}'/>
    <property name='lang.name' value='${ide.ideName}'/>
    <property name='lang.composed' value='${pkgName}'/>
    <property name='ide.pkg.name' value='${pkgName}'/>
    <property name='ide.proj.parent.path' location='$${jg}/ide/$${ide.pkg.name}'/>
    <property name='ide.proj.plugin.path' location='$${ide.proj.parent.path}/plugin'/>
    <property name='ide.proj.feature.path' location='$${ide.proj.parent.path}/feature'/>
    <property name='ide.proj.updatesite.path' location='$${ide.proj.parent.path}/updatesite'/>
    <property name='ide.pkg.path' location='$${ide.proj.plugin.path}/src/${pkgToPath(pkgName)}'/>
    <property name='ide.parser.classname' value='${ide.pluginParserClass}' />
    <target name='ide' depends='arg-check, filters, jars, copper, grammars, create-folders, customize, postbuild'>
    </target>
    <target name='ide-init'>
      <tstamp>
        <format property='ide.build-timestamp' pattern='yyMMddHHmmss' timezone='UTC'/>
      </tstamp>
    </target>
    <target name='arg-check'>
      <condition property="to-customize">
        <available file="$${grammar.path}/plugin" type="dir"/>
      </condition>
      <condition property="to-postbuild">
        <available file="$${grammar.path}/postbuild.xml" type="file"/>
      </condition>
    </target>
    <target name='filters'>
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
      <filter token="IDE_RT_VERSION" value='$${ide.rt.version}'/>
    </target>
    <target name='create-folders'>
    <!-- 1. create project folder -->
      <mkdir dir='$${ide.proj.feature.path}'/>
      <mkdir dir='$${ide.proj.updatesite.path}'/>
      <mkdir dir='$${ide.proj.plugin.path}/META-INF/'/>
      <mkdir dir='$${ide.proj.plugin.path}/resource/'/>
      <mkdir dir='$${ide.pkg.path}/'/>  
    <!-- 2. copper parser -->
      <copper
          packageName='$${ide.pkg.name}.copper.parser'
          parserName='$${ide.parser.classname}'
          outputFile='$${ide.pkg.path}/copper/parser/$${ide.parser.classname}.java'
          useSkin='XML' warnUselessNTs='false' dumpFormat='HTML' dump='ERROR_ONLY'
          dumpFile='$${ide.parser.classname}.copperdump.html'>
        <inputs file='$${ide.pkg.path}/copper/parser/$${ide.parser.classname}.copper'/>
      </copper>
    <!-- 3. build properties -->
      <copy file="$${res}/build.properties.template" tofile="$${ide.proj.plugin.path}/build.properties" filtering="true"/>
    <!-- 5. plugin dependencies -->
      <copy file="$${lang.composed}.jar" tofile="$${ide.proj.plugin.path}/$${lang.composed}.jar"/>
      <copy file="$${sh}/jars/CopperRuntime.jar" tofile="$${ide.proj.plugin.path}/edu.umn.cs.melt.copper.jar"/>
      <copy file="$${sh}/jars/SilverRuntime.jar" tofile="$${ide.proj.plugin.path}/edu.umn.cs.melt.silver.jar"/>
      <copy file="$${sh}/jars/IDEPluginRuntime.jar" tofile="$${ide.proj.plugin.path}/edu.umn.cs.melt.ide.copper-$${ide.rt.version}.jar"/>
    <!-- 6. manifest file -->
      <copy file="$${res}/META-INF/MANIFEST.MF.template" tofile="$${ide.proj.plugin.path}/META-INF/MANIFEST.MF" filtering="true"/>
    <!-- 8. core plug-in classes -->
      <mkdir dir='$${ide.pkg.path}/imp/'/>  
    <!-- Plugin main class (OSGi starter class) -->
      <copy file="$${res}/src/edu/umn/cs/melt/ide/imp/plugin.java.template"
            tofile="$${ide.pkg.path}/imp/$${lang.name}Plugin.java" filtering="true"/>
    <!-- 10. Images and other media resources -->
      <mkdir dir='$${ide.proj.plugin.path}/icons'/>
      <copy todir="$${ide.proj.plugin.path}/icons/">
        <fileset dir="$${res}/icons/"/>
      </copy>
${implode("", map(copyIdeResource, ide.ideResources))}
    <!-- 11. pom.xml (using tycho) for building plugin, feature and repository -->
      <copy file="$${res}/pom_templates/parent.pom.xml.template" tofile="$${ide.proj.parent.path}/pom.xml" filtering="true"/>
      <copy file="$${res}/pom_templates/plugin.pom.xml.template" tofile="$${ide.proj.plugin.path}/pom.xml" filtering="true"/>
      <copy file="$${res}/pom_templates/feature_templates/build.properties.template" tofile="$${ide.proj.feature.path}/build.properties" filtering="true"/>
      <copy file="$${res}/pom_templates/feature_templates/feature.xml.template"
            tofile="$${ide.proj.feature.path}/feature.xml" filtering="true"/>
      <copy file="$${res}/pom_templates/feature_templates/pom.xml.template" tofile="$${ide.proj.feature.path}/pom.xml" filtering="true"/>
      <copy file="$${res}/pom_templates/updatesite_templates/category.xml.template" tofile="$${ide.proj.updatesite.path}/category.xml" filtering="true"/>
      <copy file="$${res}/pom_templates/updatesite_templates/pom.xml.template" tofile="$${ide.proj.updatesite.path}/pom.xml" filtering="true"/>
    </target>
    <target name='customize' if="to-customize" depends='arg-check, filters'>
      <copy todir="$${ide.proj.plugin.path}" overwrite="true" filtering="true">
        <fileset dir="$${grammar.path}/plugin/"/>
      </copy>
    </target>
    <!--this is for ant post-build; not to be confused with IDE postbuilder function-->
    <target name='postbuild' if="to-postbuild">
      <ant antfile="$${grammar.path}/postbuild.xml">
        <!-- all the global properties defined in build.xml will be passed along to postbuild.xml -->
      </ant>
    </target>
"""

    ];

  extraDistDeps <- if !isIde then [] else ["ide"]; -- Here's where we demand that target be built ('dist' is a dummy target that just depends on 'jars' initially)
  
  extraGrammarsDeps <- if !isIde then [] else ["ide-init"]; -- enhance the language implementation by adding more source files, for use of IDE. (see target enhance)

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
