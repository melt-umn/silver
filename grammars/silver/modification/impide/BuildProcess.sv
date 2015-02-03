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

  local parserPackageName :: String = makeName(ide.ideParserSpec.sourceGrammar);
  local parserPackagePath :: String = grammarToPath2(ide.ideParserSpec.sourceGrammar);
  local ideParserFullPath :: String = getIDEParserFile(ide.ideParserSpec.sourceGrammar, ide.pluginParserClass, "${src}/");
  local pkgName :: String = makeName(buildGrammar);

  top.postOps <- if !isIde then [] else [generateNCS(g.compiledGrammars, benv.silverGen, ide, pkgName)];

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
    <property name='ide.parser.ide_copperfile' value='${ideParserFullPath}' />
    <target name='ide' depends='arg-check, filters, jars, copper, grammars, create-folders, customize, postbuild'>
      <delete dir='${getIDETempFolder()}}'/>
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
    </target>""",
    "<target name='create-folders'>" ++ getCreateFoldersTarget(ide) ++ "</target>",
 s"""
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
    <!-- Supporting targets based on the build mode -->
    <target name="create build.properties" depends="filters">
      <copy file="$${res}/build.properties.template" tofile="$${ide.proj.plugin.path}/build.properties" filtering="true"/>
    </target>
    <target name="create manifest file" depends="filters">	
      <copy file="$${res}/META-INF/MANIFEST.MF.template" tofile="$${ide.proj.plugin.path}/META-INF/MANIFEST.MF" filtering="true"/>
    </target>
    <target name="create Eclipse feature" depends="filters">	
      <copy file="$${res}/pom_templates/feature_templates/feature.xml.template"
            tofile="$${ide.proj.feature.path}/feature.xml" filtering="true"/>
    </target>
    <target name="copy plugin dependencies">	
      <copy file="$${sh}/jars/CopperRuntime.jar" tofile="$${ide.proj.plugin.path}/edu.umn.cs.melt.copper.jar"/>
      <copy file="$${sh}/jars/SilverRuntime.jar" tofile="$${ide.proj.plugin.path}/edu.umn.cs.melt.silver.jar"/>
      <copy file="$${sh}/jars/IDEPluginRuntime.jar" tofile="$${ide.proj.plugin.path}/edu.umn.cs.melt.ide.copper-$${ide.rt.version}.jar"/>
    </target>"""

    ];

  extraDistDeps <- if !isIde then [] else ["ide"]; -- Here's where we demand that target be built ('dist' is a dummy target that just depends on 'jars' initially)
  
  extraGrammarsDeps <- if !isIde then [] else ["ide-init"]; -- enhance the language implementation by adding more source files, for use of IDE. (see target enhance)

  -- We're not actually using the generated (by silver / ant) jar as an OSGi bundle
  -- and if we ever do, we should probably put this as part of the normal build process instead.
  -- extraManifestAttributes <-
}


function grammarToPath
String ::= grm :: String 
{
    local attribute lastInd :: Integer = lastIndexOf(":", grm);
    local attribute grammarPart :: String = substitute(":", "/", substring(0, lastInd, grm));
    return grammarPart;
}

function grammarToPath2
String ::= grm :: String 
{
    return substitute(":", "/", grm) ++ "/";
}



function getCreateFoldersTarget
String ::= ide::IdeSpec
{
  return 
    "  \n" ++
    "  <!-- 0. clean up -->\n" ++
    "  <delete dir='${ide.proj.parent.path}'/>\n" ++
    "\n" ++

    "  <!-- 1. create project folder -->\n" ++
    "  <mkdir dir='${ide.proj.plugin.path}'/>\n" ++
    "  <mkdir dir='${ide.proj.plugin.path}/src'/>\n" ++
    "  <mkdir dir='${ide.proj.plugin.path}/bin'/>\n" ++
    "  <mkdir dir='${ide.proj.feature.path}'/>\n" ++
    "  <mkdir dir='${ide.proj.updatesite.path}'/>\n" ++
    "\n" ++

    "  <!-- 2. copper parser -->\n" ++
    "  <mkdir dir='${ide.pkg.path}/copper/parser/'/>\n" ++
    "  <copper\n" ++
    "    packageName='${ide.pkg.name}.copper.parser'\n" ++
    "    parserName='${ide.parser.classname}'\n" ++
    "    outputFile='${ide.pkg.path}/copper/parser/${ide.parser.classname}.java'\n" ++
    "    useSkin='XML' warnUselessNTs='false' dumpFormat='HTML' dump='ERROR_ONLY'\n" ++
    "    dumpFile='${ide.parser.classname}.copperdump.html'>\n" ++
    "      <inputs file='${ide.parser.ide_copperfile}'/>\n" ++
    "  </copper>\n" ++
    "\n" ++

    "  <!-- 3. build properties -->\n" ++
    -- commented out to support different build modes
    -- "<copy file=\"${res}/build.properties.template\" tofile=\"${ide.proj.plugin.path}/build.properties\" filtering=\"true\"/>\n" ++
    "  <antcall target=\"create build.properties\" inheritAll=\"true\"/>\n" ++
    "\n" ++

    "  <!-- 4. plugin.xml -->\n" ++
    -- the single-file template is no longer used.
    -- "  <copy file=\"${res}/plugin.xml.template\" tofile=\"${ide.proj.plugin.path}/plugin.xml\" filtering=\"true\"/>\n" ++
    "  <copy file=\"" ++ getIDETempFolder() ++ "/plugin.xml.template\" tofile=\"${ide.proj.plugin.path}/plugin.xml\" filtering=\"true\"/>\n" ++
    "  \n" ++
    "  <copy file=\"" ++ getIDETempFolder() ++ "/SVIdeInterface.java.template\" tofile=\"${ide.pkg.path}/SVIdeInterface.java\" filtering=\"true\"/>\n" ++
    "  \n" ++

    "  <!-- 5. plugin dependencies -->\n" ++
    "  <!-- (1) language implementation -->\n" ++
    "  <copy file=\"${lang.composed}.jar\" tofile=\"${ide.proj.plugin.path}/${lang.composed}.jar\"/>\n" ++
    "  <!-- (2) runtimes -->\n" ++
    "  <antcall target=\"copy plugin dependencies\"/>\n"++
    "  \n" ++

    "  <!-- 6. manifest file -->\n" ++
    "  <mkdir dir='${ide.proj.plugin.path}/META-INF/'/>\n" ++
    -- commented out to support different build modes
    -- "<copy file=\"${res}/META-INF/MANIFEST.MF.template\" tofile=\"${ide.proj.plugin.path}/META-INF/MANIFEST.MF\" filtering=\"true\"/>\n" ++
    "  <antcall target=\"create manifest file\" inheritAll=\"true\"/>\n" ++
    "  \n" ++

    "  <!-- 8. core plug-in classes -->\n" ++
    "  <mkdir dir='${ide.pkg.path}/'/>\n" ++  
    "  <mkdir dir='${ide.pkg.path}/imp/'/>\n" ++  
    "  <!-- Plugin main class (OSGi starter class) -->\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/imp/plugin.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/imp/${lang.name}Plugin.java\" filtering=\"true\"/>\n" ++
    "  \n" ++

    "  <mkdir dir='${ide.pkg.path}/imp/coloring'/>\n" ++
    "  <!-- Language syntax highlighting classes, supported by IMP -->\n" ++
    "  <copy todir=\"${ide.pkg.path}/imp/coloring/\" overwrite=\"true\" filtering=\"true\">\n" ++
    "        <fileset dir=\"" ++ getIDETempFolder() ++ "imp/coloring/\"/>\n" ++
    "        <globmapper from=\"*.java.template\" to=\"*.java\"/>\n" ++
    "  </copy>\n" ++
    "  \n" ++

    "  <mkdir dir='${ide.pkg.path}/eclipse/wizard'/>\n" ++
    "  <mkdir dir='${ide.pkg.path}/eclipse/wizard/newproject'/>\n" ++
    "  <!-- A wizard for creating new project. -->\n" ++
    "  <copy file=\"" ++ getIDETempFolder() ++ "eclipse/wizard/newproject/PropertyGenerator.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/eclipse/wizard/newproject/PropertyGenerator.java\" filtering=\"true\"/>\n" ++
    "  \n" ++

    (if !null(ide.wizards) -- This is really about the new file wizard specifically, FIXME
    then
    "  <mkdir dir='${ide.pkg.path}/eclipse/wizard/newfile'/>\n" ++
    "  <!-- A wizard for creating new source file. -->\n" ++
    "  <copy file=\"" ++ getIDETempFolder() ++ "eclipse/wizard/newfile/PropertyControlsProvider.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/eclipse/wizard/newfile/PropertyControlsProvider.java\" filtering=\"true\"/>\n" ++
    "  \n"
    else
    "") ++

    (if !null(ide.propDcls) -- TODO: I guess this is, in fact, about whether the project has properties, but maybe we shouldn't access them form the outside like this?
    then
    "  <mkdir dir='${ide.pkg.path}/eclipse/property'/>\n" ++
    "  <!-- A project property page -->\n" ++
    "  <copy file=\"" ++ getIDETempFolder() ++ "eclipse/property/PropertyControlsProvider.java.template\"\n" ++    -- this file always get copied even if it's not used
    "        tofile=\"${ide.pkg.path}/eclipse/property/PropertyControlsProvider.java\" filtering=\"true\"/>\n" ++
    "  \n"
    else
    "") ++    

    "  <!-- 10. Images and other media resources -->\n" ++
    "  <mkdir dir='${ide.proj.plugin.path}/icons'/>\n" ++
    "  <copy todir=\"${ide.proj.plugin.path}/icons/\">\n" ++
    "        <fileset dir=\"${res}/icons/\"/>\n" ++
    "  </copy>\n" ++
    "  \n" ++

    "  <!-- 11. pom.xml (using tycho) for building plugin, feature and repository -->\n" ++
    "  <!-- parent -->\n" ++
    "  <copy file=\"${res}/pom_templates/parent.pom.xml.template\" tofile=\"${ide.proj.parent.path}/pom.xml\" filtering=\"true\"/>\n" ++
    "  <!-- plugin -->\n" ++
    "  <copy file=\"${res}/pom_templates/plugin.pom.xml.template\" tofile=\"${ide.proj.plugin.path}/pom.xml\" filtering=\"true\"/>\n" ++
    "  <!-- feature -->\n" ++
    "  <copy file=\"${res}/pom_templates/feature_templates/build.properties.template\" tofile=\"${ide.proj.feature.path}/build.properties\" filtering=\"true\"/>\n" ++
    -- commented out to support different build modes
    -- "<copy file=\"${res}/pom_templates/feature_templates/feature.xml.template\" tofile=\"${ide.proj.feature.path}/feature.xml\" filtering=\"true\"/>\n" ++
    "  <antcall target=\"create Eclipse feature\" inheritAll=\"true\"/>\n" ++
    "  <copy file=\"${res}/pom_templates/feature_templates/pom.xml.template\" tofile=\"${ide.proj.feature.path}/pom.xml\" filtering=\"true\"/>\n" ++
    "  <!-- update site (repository) -->\n" ++
    "  <copy file=\"${res}/pom_templates/updatesite_templates/category.xml.template\" tofile=\"${ide.proj.updatesite.path}/category.xml\" filtering=\"true\"/>\n" ++
    "  <copy file=\"${res}/pom_templates/updatesite_templates/pom.xml.template\" tofile=\"${ide.proj.updatesite.path}/pom.xml\" filtering=\"true\"/>\n" ++
    "  \n"

  ;
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

function grammarToExportString
String ::= g::String
{
  return makeName(g) ++ ";version=\"${ide.version}\"";
}

function getIDEParserFile
String ::= grammarName::String parserClassName::String silverGen::String
{
  return silverGen ++ grammarToPath2(grammarName) ++ parserClassName ++ "_ide.copper";
}

