grammar silver:modification:impide;

import silver:driver;
import silver:translation:java:driver;
import silver:translation:java:core only makeParserName, makeName;

import silver:util:cmdargs;

-- The file where function writeBuildFile is originally defined is silver/translation/java/driver/BuildProcess.sv
-- Here we're just aspecting that, using '<-' to contribute things to the production attributes declared there

aspect function writeBuildFile
IO ::= i::IO a::Decorated CmdArgs specs::[String] silverhome::String silvergen::String da::Decorated DependencyAnalysis grammarLoc::String
{
  -- The RootSpec representing the grammar actually being built (specified on the command line)
  local builtGrammar :: [Decorated RootSpec] = getRootSpec(head(a.buildGrammar), da.compiledList);
  
  -- Empty if no ide decl in that grammar, otherwise has at least one spec... note that
  -- we're going to go with assuming there's just one IDE declaration...
  local isIde :: Boolean = !null(builtGrammar) && !null(head(builtGrammar).ideSpecs);
  local ide :: IdeSpec = head(head(builtGrammar).ideSpecs);

  local parserClassName :: String = makeParserName(ide.ideParserSpec.fullName);
  local parserPackageName :: String = makeName(ide.ideParserSpec.sourceGrammar);
  local parserPackagePath :: String = grammarToPath(ide.ideParserSpec.sourceGrammar);
  local parserFullPath :: String = "${src}/" ++ parserPackagePath ++ parserClassName ++ ".copper";
  local ideParserFullPath :: String = "${src}/" ++ parserPackagePath ++ parserClassName ++ "_ide.copper";
  local pkgName :: String = grammarToPackage(head(a.buildGrammar));

  extraTopLevelDecls <- if !isIde then [] else [
    "<property name='grammar.path' value='" ++ grammarLoc ++ "'/>", 
    "<property name='res' value='${sh}/resources'/>", --TODO: add all templates to here.
    "<property name='ide.version' value='1.0.0'/>",
    -- derive the name of language from grammar. TODO: In future we must allow users to define the name themselves.
    "<property name='lang.name' value='" ++ deriveLangNameFromPackage(pkgName) ++ "'/>",
    "<property name='lang.composed' value='" ++ pkgName ++ "'/>", 
    "<property name='ide.pkg.name' value='" ++ pkgName ++ "'/>",
    "<property name='ide.proj.name' value='${ide.pkg.name}'/>",
    "<property name='ide.proj.parent.path' location='${jg}/ide/${ide.proj.name}'/>",
    "<property name='ide.proj.plugin.path' location='${ide.proj.parent.path}/plugin'/>",
    "<property name='ide.proj.feature.path' location='${ide.proj.parent.path}/feature'/>",
    "<property name='ide.proj.updatesite.path' location='${ide.proj.parent.path}/updatesite'/>",
    "<property name='ide.pkg.path' location='${ide.proj.plugin.path}/src/" ++ pkgToPath(pkgName) ++ "'/>", 
    "<property name='ide.parser.package' value='" ++ parserPackageName ++ "' />",
    "<property name='ide.parser.classname' value='" ++ parserClassName ++ "' />",
    "<property name='ide.parser.copperfile' value='" ++ parserFullPath ++ "' />",
    "<property name='ide.parser.ide_copperfile' value='" ++ ideParserFullPath ++ "' />",
    "<property name='ide.fileextension' value='" ++ ide.ideExtension ++ "' />",

    "<target name='ide' depends='arg-check, filters, jars, copper, grammars, create-folders, customize'>\n\n</target>\n\n",
    "<target name='arg-check'>" ++ getArgCheckTarget() ++ "</target>",
    "<target name='filters'>" ++ getFiltersTarget() ++ "</target>",
    "<target name='create-folders'>" ++ getCreateFoldersTarget() ++ "</target>\n\n",
    "<target name='customize' if=\"to-customize\">" ++ getCustomizeTarget() ++ "</target>\n\n",

    getBuildTargets()
    ];

  -- The following line may be commented out for test purpose.
  extraDistDeps <- if !isIde then [] else ["ide"]; -- Here's where we demand that target be built ('dist' is a dummy target that just depends on 'jars' initially)
  
  -- attributes required as an OSGi module
  extraManifestAttributes <- if !isIde then [] else [
    "<attribute name='Bundle-ManifestVersion' value='1' />",
    "<attribute name='Bundle-Name' value='${lang.composed}' />",
    "<attribute name='Bundle-SymbolicName' value='${lang.composed}' />", -- according to OSGi recommendation, use reversed domain name
    "<attribute name='Bundle-Version' value='${ide.version}' />",
    "<attribute name='Bundle-Vendor' value='${user.name}' />",
    "<attribute name='Export-Package' value='" ++ implode(", ", map(grammarToExportString, specs)) ++ "' />",
    "<attribute name='Bundle-RequiredExecutionEnvironment' value='J2SE-1.5' />",
    "<attribute name='Require-Bundle' value='edu.umn.cs.melt.copper;bundle-version=\"1.0.0\", edu.umn.cs.melt.silver;bundle-version=\"1.0.0\"' />" 
    -- TODO: generate version of silver/copper bundles dynamically
    ];
}

function getArgCheckTarget
String ::=
{
    return
    "\n" ++
    "  <condition property=\"is-all-in-one\">\n"++
    "    <equals arg1=\"${all-in-one}\" arg2=\"true\" />\n"++
    "  </condition>\n"++
    "  \n"++
    "  <condition property=\"to-customize\">\n"++
    "    <available file=\"${grammar.path}/plugin\" type=\"dir\"/>\n"++
    "  </condition>\n";
}

function getFiltersTarget
String ::=
{
    return
    "\n" ++
    "  <!-- define variables used in template file -->\n" ++
    "  <filter token=\"GROUP_ID\" value='${ide.pkg.name}'/>\n" ++
    "  <filter token=\"PKG_NAME\" value='${ide.pkg.name}'/>\n" ++
    "  <filter token=\"PARSER_NAME\" value='${ide.parser.classname}'/>\n" ++
    "  <filter token=\"LANG_NAME\" value='${lang.name}'/>\n" ++
    "  <filter token=\"SOURCE_EXT\" value='${ide.fileextension}'/>\n" ++
    "  <filter token=\"IDE_VERSION\" value='${ide.version}'/>\n" ++
    "  <filter token=\"PROJ_NAME\" value='${lang.name}_IDE_PROJECT'/>\n" ++
    "  <filter token=\"COPPER_RUNTIME_PATH\" value='${sh}/jars/CopperRuntime.jar'/>\n" ++
    "  <filter token=\"LANG_COMPOSED\" value='${lang.composed}'/>\n" ++
    "  <filter token=\"FEATURE_DESCRIPTION_URL\" value='http://some.user.provided.url'/>\n" ++	-- TODO User-provided variables
    "  <filter token=\"FEATURE_DESCRIPTION_TEXT\" value='no description of the software'/>\n" ++
    "  <filter token=\"FEATURE_COPYRIGHT_URL\" value='http://some.user.provided.url'/>\n" ++
    "  <filter token=\"FEATURE_COPYRIGHT_TEXT\" value='no copyright information available'/>\n" ++
    "  <filter token=\"FEATURE_LICENSE_URL\" value='http://some.user.provided.url'/>\n" ++
    "  <filter token=\"FEATURE_LICENSE_TEXT\" value='no license information available'/>\n";
}

function getCreateFoldersTarget
String ::=
{
  return 
    "\n" ++
    "<!-- clean up -->\n" ++
    "<delete dir='${ide.proj.parent.path}'/>\n" ++
    "\n" ++

    "<!-- 1. create project folder -->\n" ++
    "<mkdir dir='${ide.proj.plugin.path}'/>\n" ++
    "<mkdir dir='${ide.proj.plugin.path}/src'/>\n" ++
    "<mkdir dir='${ide.proj.plugin.path}/bin'/>\n" ++
    "<mkdir dir='${ide.proj.feature.path}'/>\n" ++
    "<mkdir dir='${ide.proj.updatesite.path}'/>\n" ++
    "\n" ++

    "<!-- 3. copper parser -->\n" ++
    "<mkdir dir='${ide.pkg.path}/copper/parser/'/>\n" ++
    "<copper\n" ++ 
    "  fullClassName='${ide.pkg.name}.copper.parser.${ide.parser.classname}'\n" ++ 
    "  inputFile='${ide.parser.ide_copperfile}'\n" ++ 
    "  outputFile='${ide.pkg.path}/copper/parser/${ide.parser.classname}.java'\n" ++ 
    "  skin='XML' warnUselessNTs='no' dump='no'/>\n" ++ 
    "\n" ++

    "<!-- 4. build properties -->\n" ++
    -- commented out to support different build modes
    -- "<copy file=\"${res}/build.properties.template\" tofile=\"${ide.proj.plugin.path}/build.properties\" filtering=\"true\"/>\n" ++
    "<antcall target=\"create build.properties\" inheritAll=\"true\"/>\n" ++
    "<antcall target=\"create build.properties (all-on-one)\" inheritAll=\"true\"/>\n" ++

    "\n" ++

    "<!-- 5. plugin.xml -->\n" ++
    "<copy file=\"${res}/plugin.xml.template\" tofile=\"${ide.proj.plugin.path}/plugin.xml\" filtering=\"true\"/>\n" ++
    "\n" ++

    "<!-- copy plugin dependency (composed jar) -->\n" ++
    "<copy file=\"${lang.composed}.jar\" tofile=\"${ide.proj.plugin.path}/${lang.composed}.jar\"/>\n" ++
    -- called only in all-in-one mode
    "<antcall target=\"copy plugin dependencies\"/>\n"++
    "\n" ++

    "<!-- 6. manifest file -->\n" ++
    "<mkdir dir='${ide.proj.plugin.path}/META-INF/'/>\n" ++
    -- commented out to support different build modes
    -- "<copy file=\"${res}/META-INF/MANIFEST.MF.template\" tofile=\"${ide.proj.plugin.path}/META-INF/MANIFEST.MF\" filtering=\"true\"/>\n" ++
    "<antcall target=\"create manifest file\" inheritAll=\"true\"/>\n" ++
    "<antcall target=\"create manifest file (all-on-one)\" inheritAll=\"true\"/>\n" ++
    "\n" ++

    "<!-- 7. customized IDE parser -->\n" ++
    "<mkdir dir='${ide.pkg.path}/copper/engine/'/>\n" ++
    "<copy file=\"${res}/src/edu/umn/cs/melt/ide/copper/engine/EnhancedSilverParser.java.template\"\n" ++
    "      tofile=\"${ide.pkg.path}/copper/engine/EnhancedSilverParser.java\" filtering=\"true\"/>\n" ++
    "<mkdir dir='${ide.pkg.path}/imp/controller'/>\n" ++
    "<copy file=\"${res}/src/edu/umn/cs/melt/ide/imp/controller/parseController.java.template\"\n" ++
    "      tofile=\"${ide.pkg.path}/imp/controller/${lang.name}ParseController.java\" filtering=\"true\"/>\n" ++
    "\n" ++

    "<!-- 8. core plug-in classes -->\n" ++
    "<mkdir dir='${ide.pkg.path}/imp/'/>\n" ++  
    "<copy file=\"${res}/src/edu/umn/cs/melt/ide/imp/plugin.java.template\"\n" ++
    "      tofile=\"${ide.pkg.path}/imp/${lang.name}Plugin.java\" filtering=\"true\"/>\n" ++
    "\n" ++

    "<mkdir dir='${ide.pkg.path}/imp/actions'/>\n" ++
    "<copy file=\"${res}/src/edu/umn/cs/melt/ide/imp/actions/EnableLANGNature.java.template\"\n" ++
    "      tofile=\"${ide.pkg.path}/imp/actions/Enable${lang.name}Nature.java\" filtering=\"true\"/>\n" ++
    "\n" ++

    "<mkdir dir='${ide.pkg.path}/imp/builders'/>\n" ++
    "<copy file=\"${res}/src/edu/umn/cs/melt/ide/imp/builders/nature.java.template\"\n" ++
    "      tofile=\"${ide.pkg.path}/imp/builders/${lang.name}Nature.java\" filtering=\"true\"/>\n" ++
    "<copy file=\"${res}/src/edu/umn/cs/melt/ide/imp/builders/builder.java.template\"\n" ++
    "      tofile=\"${ide.pkg.path}/imp/builders/${lang.name}Builder.java\" filtering=\"true\"/>\n" ++
    "\n" ++

    "<mkdir dir='${ide.pkg.path}/imp/coloring'/>\n" ++
    "<copy file=\"${res}/src/edu/umn/cs/melt/ide/imp/coloring/Colorer.java.template\"\n" ++
    "      tofile=\"${ide.pkg.path}/imp/coloring/Colorer.java\" filtering=\"true\"/>\n" ++
    "\n" ++

    "<!-- 9. pom.xml (using tycho) -->\n" ++
    "<!-- parent -->\n" ++
    "<copy file=\"${res}/pom_templates/parent.pom.xml.template\" tofile=\"${ide.proj.parent.path}/pom.xml\" filtering=\"true\"/>\n" ++
    "<!-- plugin -->\n" ++
    "<copy file=\"${res}/pom_templates/plugin.pom.xml.template\" tofile=\"${ide.proj.plugin.path}/pom.xml\" filtering=\"true\"/>\n" ++
    "<!-- feature -->\n" ++
    "<copy file=\"${res}/pom_templates/feature_templates/build.properties.template\" tofile=\"${ide.proj.feature.path}/build.properties\" filtering=\"true\"/>\n" ++
    "<copy file=\"${res}/pom_templates/feature_templates/feature.xml.template\" tofile=\"${ide.proj.feature.path}/feature.xml\" filtering=\"true\"/>\n" ++
    "<copy file=\"${res}/pom_templates/feature_templates/pom.xml.template\" tofile=\"${ide.proj.feature.path}/pom.xml\" filtering=\"true\"/>\n" ++
    "<!-- update site (repository) -->\n" ++
    "<copy file=\"${res}/pom_templates/updatesite_templates/category.xml.template\" tofile=\"${ide.proj.updatesite.path}/category.xml\" filtering=\"true\"/>\n" ++
    "<copy file=\"${res}/pom_templates/updatesite_templates/pom.xml.template\" tofile=\"${ide.proj.updatesite.path}/pom.xml\" filtering=\"true\"/>\n" ++
    "\n" ++

    "<!-- 10. eclipse project -->\n" ++
    "<copy file=\"${res}/project.template\" tofile=\"${ide.proj.plugin.path}/.project\" filtering=\"true\"/>\n" ++
    -- commented out to support different build modes
    -- "<copy file=\"${res}/classpath.template\" tofile=\"${ide.proj.plugin.path}/.classpath\" filtering=\"true\"/>\n" ++
    "<antcall target=\"set classpaths for Eclipse\" inheritAll=\"true\"/>\n" ++
    "<antcall target=\"set classpaths for Eclipse (all-on-one)\" inheritAll=\"true\"/>\n" ++

    "\n"
  ;
}

function getCustomizeTarget
String ::=
{
    return
    "\n" ++
    "<copy todir=\"${ide.proj.plugin.path}\" overwrite=\"true\">\n" ++
    "  <fileset dir=\"${grammar.path}/plugin/\"/>\n" ++
    "</copy>\n";
}

function getBuildTargets
String ::=
{
return
"<!-- Supporting targets based on the build mode -->\n" ++
"<target name=\"create build.properties\" unless=\"is-all-in-one\" depends=\"filters\">\n"++
"  <copy file=\"${res}/build.properties.template\" tofile=\"${ide.proj.plugin.path}/build.properties\" filtering=\"true\"/>\n"++
"</target>\n"++
"<target name=\"create build.properties (all-on-one)\" if=\"is-all-in-one\" depends=\"filters\">\n"++
"  <copy file=\"${res}/build.properties.template.all_in_one\" tofile=\"${ide.proj.plugin.path}/build.properties\" filtering=\"true\"/>\n"++
"</target>\n"++
"\n"++
"<target name=\"create manifest file\" unless=\"is-all-in-one\" depends=\"filters\">\n"++	
"  <copy file=\"${res}/META-INF/MANIFEST.MF.template\" tofile=\"${ide.proj.plugin.path}/META-INF/MANIFEST.MF\" filtering=\"true\"/>\n"++
"</target>\n"++
"<target name=\"create manifest file (all-on-one)\" if=\"is-all-in-one\" depends=\"filters\">\n"++	
"  <copy file=\"${res}/META-INF/MANIFEST.MF.template.all_in_one\" tofile=\"${ide.proj.plugin.path}/META-INF/MANIFEST.MF\" filtering=\"true\"/>\n"++
"</target>\n"++
"\n"++
"<target name=\"set classpaths for Eclipse\" unless=\"is-all-in-one\" depends=\"filters\">\n"++	
"  <copy file=\"${res}/classpath.template\" tofile=\"${ide.proj.plugin.path}/.classpath\" filtering=\"true\"/>\n"++
"</target>\n"++
"<target name=\"set classpaths for Eclipse (all-on-one)\" if=\"is-all-in-one\" depends=\"filters\">\n"++	
"  <copy file=\"${res}/classpath.template.all_in_one\" tofile=\"${ide.proj.plugin.path}/.classpath\" filtering=\"true\"/>\n"++
"</target>\n"++
"\n"++
"<target name=\"copy plugin dependencies\" if=\"is-all-in-one\">\n"++	
"  <copy file=\"${sh}/jars/CopperRuntime.jar\" tofile=\"${ide.proj.plugin.path}/edu.umn.cs.melt.copper.jar\"/>\n"++
"  <copy file=\"${sh}/jars/SilverRuntime.jar\" tofile=\"${ide.proj.plugin.path}/edu.umn.cs.melt.silver.jar\"/>\n"++
"  <copy file=\"${sh}/jars/IDEPluginRuntime.jar\" tofile=\"${ide.proj.plugin.path}/edu.umn.cs.melt.ide.copper-1.0.0.jar\"/>\n"++
"</target>\n\n";
}

function toUpperCase
String ::= original::String
{
  return error("Not Yet Implemented: toUpperCase");
} foreign {
  "java" : return "(new common.StringCatter(%original%.toString().toUpperCase()))";
}

function deriveLangNameFromPackage
String ::= pkg::String
{
  return toUpperCase(head(explode(".", pkg)));
}

function pkgToPath
String ::= pkg::String
{
  return substitute(".", "/", pkg);
}

function grammarToPackage
String ::= g::String
{
  return substitute(":", ".", g);
}
function grammarToExportString
String ::= g::String
{
  return grammarToPackage(g) ++ ";version=\"${ide.version}\"";
}
