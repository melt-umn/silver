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
  production builtGrammar :: [Decorated RootSpec] = searchEnvTree(buildGrammar, g.compiledGrammars);
  
  -- Empty if no ide decl in that grammar, otherwise has at least one spec... note that
  -- we're going to go with assuming there's just one IDE declaration...
  production ide :: IdeSpec = head(head(builtGrammar).ideSpecs);
  production isIde :: Boolean = !null(builtGrammar) && !null(head(builtGrammar).ideSpecs);

  -- TODO only used for the adaptive stuff, so will remove shortly.
  local startNTClassName::String = makeNTClassName(ide.ideParserSpec.startNT);
  extraTopLevelDecls <- if !isIde then [] else [
    "<property name='start.nonterminal.class' value='" ++ startNTClassName ++ "'/>"]; 

  local parserClassName :: String = makeParserName(ide.ideParserSpec.fullName);
  local parserPackageName :: String = makeName(ide.ideParserSpec.sourceGrammar);
  local parserPackagePath :: String = grammarToPath2(ide.ideParserSpec.sourceGrammar);
  local ideParserFullPath :: String = getIDEParserFile(ide.ideParserSpec.sourceGrammar, parserClassName, "${src}/");
  production pkgName :: String = makeName(buildGrammar);

  top.postOps <- if !isIde then [] else [generateNCS(g.compiledGrammars, allParsers, benv.silverGen, ide, pkgName, startNTClassName)];

  classpathCompiler <- if !isIde then [] else ["${sh}/jars/IDEPluginRuntime.jar"];

  extraTopLevelDecls <- if !isIde then [] else [
    getIDERuntimeVersion(),
    "<property name='grammar.path' value='" ++ head(builtGrammar).grammarSource ++ "'/>", 
    "<property name='res' value='${sh}/resources'/>", --TODO: add all templates to here.
    "<property name='ide.version' value='" ++ -- use the default number "1.0.0" if not defined
        (if(ide.productInfo.prodVersion=="") then "1.0.0" else ide.productInfo.prodVersion) 
    ++ "'/>",
    "<property name='lang.name' value='" ++ -- use a derived name if not defined
        (if(ide.productInfo.prodName=="") then deriveLangNameFromPackage(pkgName) else ide.productInfo.prodName) 
    ++ "'/>",
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
    --"<property name='ide.parser.copperfile' value='" ++ parserFullPath ++ "' />",
    "<property name='ide.parser.ide_copperfile' value='" ++ ideParserFullPath ++ "' />",    "<property name='ide.fileextension' value='" ++ ide.ideExtension ++ "' />"] ++ 

    configWizards(ide.wizards) ++ 

    getIDEFunctionsDcls(ide.funcDcls) ++

    [
    "<target name='ide' depends='arg-check, filters, jars, copper, grammars, create-folders, customize, postbuild'>\n"++
    "    <delete dir='" ++ getIDETempFolder() ++ "'/>\n"++
    "</target>",
    "<target name='ide-init'>" ++ getIDEInitTarget() ++ "</target>",
    "<target name='arg-check'>" ++ getArgCheckTarget() ++ "</target>",
    "<target name='filters'>" ++ getFiltersTarget() ++ "</target>",
    "<target name='create-folders'>" ++ getCreateFoldersTarget(parserClassName, ide.pluginConfig) ++ "</target>",
    "<target name='customize' if=\"to-customize\" depends='arg-check, filters'>" ++ getCustomizeTarget() ++ "</target>",
    "<target name='postbuild' if=\"to-postbuild\">" ++ getAntPostBuildTarget() ++ "</target>",--this is for ant post-build; not to be confused with IDE post-build
    getBuildTargets()
    ];

  extraDistDeps <- if !isIde then [] else ["ide"]; -- Here's where we demand that target be built ('dist' is a dummy target that just depends on 'jars' initially)
  
  extraGrammarsDeps <- if !isIde then [] else ["ide-init"]; -- enhance the language implementation by adding more source files, for use of IDE. (see target enhance)

  -- attributes required as an OSGi module TODO: currently we don't actually use the generated jar as an osgi bundle. remove?
  extraManifestAttributes <- if !isIde then [] else [
    "<attribute name='Bundle-ManifestVersion' value='1' />",
    "<attribute name='Bundle-Name' value='${lang.composed}' />",
    "<attribute name='Bundle-SymbolicName' value='${lang.composed}' />", -- according to OSGi recommendation, use reversed domain name
    "<attribute name='Bundle-Version' value='${ide.version}' />",
    "<attribute name='Bundle-Vendor' value='${user.name}' />",
    "<attribute name='Export-Package' value='" ++ implode(", ", map(grammarToExportString, grammarsDependedUpon)) ++ "' />",
    "<attribute name='Bundle-RequiredExecutionEnvironment' value='J2SE-1.5' />",
    "<attribute name='Require-Bundle' value='edu.umn.cs.melt.copper;bundle-version=\"1.0.0\", edu.umn.cs.melt.silver;bundle-version=\"1.0.0\"' />" 
    -- TODO: generate version of silver/copper bundles dynamically
    ];
}

{--
Ant operations to read the version from Manifest file in the IDE Runtime jar, which has been fetched and stored locally.
--}
function getIDERuntimeVersion
String ::= 
{
  return 
    "<macrodef name=\"getIDERuntimeVersion\">\n"++
    "    <sequential>\n"++
    "        <loadproperties>\n"++
    "            <!-- From ZIP entries in runtime jar, -->\n"++
    "            <zipentry zipfile=\"${sh}/jars/IDEPluginRuntime.jar\" name=\"META-INF/MANIFEST.MF\"/>\n"++
    "            <filterchain>\n"++
    "                <!-- load the line containing \"Bundle-Version\", -->\n"++
    "                <linecontains>\n"++
    "                  <contains value=\"Bundle-Version\"/>\n"++
    "                </linecontains>\n"++
    "                <!-- as Ant property, with name set to be \"ide_rt.Bundle-Version\". -->\n"++
    "                <prefixlines prefix=\"ide_rt.\"/>\n"++
    "            </filterchain>\n"++
    "        </loadproperties>\n"++
    "    </sequential>\n"++
    "</macrodef>\n"++
    "\n"++
    "<!-- Load version of IDE runtime into ${ide_rt.Bundle-Version} -->\n"++
    "<getIDERuntimeVersion />\n"++
    "\n"++
    "<property name='ide.rt.version' value='${ide_rt.Bundle-Version}'/>\n";
}

function configWizards
[String] ::= wizards :: [IdeWizardDcl]
{
    return map(configWizard, wizards);
}

function configWizard
String ::= wizard :: IdeWizardDcl
{
    return "<property name='ide.function.stubgen." ++ wizard.wizName ++ ".name' value='" ++ makeClassName(wizard.wizFunc) ++ "' />\n";
}

function getIDEFunctionsDcls
[String] ::= funcDcls :: [Pair<String String>]
{
    return if null(funcDcls) --length(funcDcls) < 1
           then []
           else map(getIDEFunctionDcl, funcDcls);
}

function getIDEFunctionDcl
String ::= funcDcl :: Pair<String String>
{
    return "<property name='ide.function." ++ funcDcl.fst ++ "' value='" ++ makeClassName(funcDcl.snd) ++ "' />";
}

-- Find a function from the given function list; if found return the argument found, else notFound
function findFunction
String ::= funcDcls :: [Pair<String String>] funcToFind::String found::String notFound::String
{
    return if null(funcDcls) --length(funcDcls) < 1
           then notFound
           else let
                    hd :: Pair<String String> = head(funcDcls)
                in
                    if(hd.fst==funcToFind) then found
                    else findFunction(tail(funcDcls), funcToFind, found, notFound)
                end;
}

function getEnhanceTarget
String ::= funcDcls::[Pair<String String>] doAction::(String::=Pair<String String>) 
{
    return if null(funcDcls)
           then "\n"
           else let
                    result :: String = doAction(head(funcDcls))
                in 
                    if result==""
                    then getEnhanceTarget(tail(funcDcls), doAction)
                    else result
                end;
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

function getIDEInitTarget
String ::=
{
    return
    "\n" ++

    "  <tstamp>\n" ++
    "    <format property='ide.build-timestamp' pattern='yyMMddHHmmss' timezone='UTC'/>\n" ++
    "  </tstamp>\n";
}


function getArgCheckTarget
String ::=
{
    return
    "\n" ++

    "  <condition property=\"to-customize\">\n"++
    "    <available file=\"${grammar.path}/plugin\" type=\"dir\"/>\n"++
    "  </condition>\n"++
    "  \n"++
    "  <condition property=\"to-postbuild\">\n"++
    "    <available file=\"${grammar.path}/postbuild.xml\" type=\"file\"/>\n"++
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
    "  <filter token=\"IDE_BUILD_TIMESTAMP\" value='${ide.build-timestamp}'/>\n" ++
    "  <filter token=\"PROJ_NAME\" value='${lang.name}_IDE_PROJECT'/>\n" ++
    "  <filter token=\"COPPER_RUNTIME_PATH\" value='${sh}/jars/CopperRuntime.jar'/>\n" ++
    "  <filter token=\"LANG_COMPOSED\" value='${lang.composed}'/>\n" ++
    "  <filter token=\"FEATURE_DESCRIPTION_URL\" value='http://some.user.provided.url'/>\n" ++	-- TODO User-provided variables
    "  <filter token=\"FEATURE_DESCRIPTION_TEXT\" value='no description of the software'/>\n" ++
    "  <filter token=\"FEATURE_COPYRIGHT_URL\" value='http://some.user.provided.url'/>\n" ++
    "  <filter token=\"FEATURE_COPYRIGHT_TEXT\" value='no copyright information available'/>\n" ++
    "  <filter token=\"FEATURE_LICENSE_URL\" value='http://some.user.provided.url'/>\n" ++
    "  <filter token=\"FEATURE_LICENSE_TEXT\" value='no license information available'/>\n" ++
    "  <filter token=\"NEWFILE_STUBGEN_CLASS_QNAME\" value='${ide.function.stubgen.newfile.name}'/>\n" ++
    "  <filter token=\"LANG_COMPOSED_PKG\" value='${lang.composed}'/>\n" ++ 
    "  <filter token=\"START_NONTERMINAL_CLASS\" value='${start.nonterminal.class}'/>\n" ++
    "  <filter token=\"IDE_RT_VERSION\" value='${ide.rt.version}'/>\n" ++
    "\n";
}

function getCreateFoldersTarget
String ::= parserClassName::String config::PluginConfig
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
    "  <!-- and the default implementation of AST visitor -->\n" ++
    "  <copy file=\"" ++ getIDETempFolder() ++ "/copper/parser/" ++ parserClassName ++ "_ASTVisitorAdapter.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/copper/parser/ASTVisitorAdapter.java\" filtering=\"true\"/>\n" ++
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

    "  <!-- 7. customized IDE parser -->\n" ++
    "  <mkdir dir='${ide.pkg.path}/imp/controller'/>\n" ++
    -- commented out to support different build modes
    --"<copy file=\"${res}/src/edu/umn/cs/melt/ide/imp/controller/parseController.java.template\"\n" ++
    --"      tofile=\"${ide.pkg.path}/imp/controller/${lang.name}ParseController.java\" filtering=\"true\"/>\n" ++
    "  <antcall target=\"create parser controller\" inheritAll=\"true\"/>\n" ++
    "  \n" ++

    "  <!-- 8. core plug-in classes -->\n" ++
    "  <mkdir dir='${ide.pkg.path}/'/>\n" ++  
    "  <!-- An initializer to be called during plugin start-up -->\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/Initializer.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/${lang.name}Initializer.java\" filtering=\"true\"/>\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/StartupHook.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/StartupHook.java\" filtering=\"true\"/>\n" ++
    "  \n" ++

    "  <mkdir dir='${ide.pkg.path}/imp/'/>\n" ++  
    "  <!-- Plugin main class (OSGi starter class) -->\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/imp/plugin.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/imp/${lang.name}Plugin.java\" filtering=\"true\"/>\n" ++
    "  \n" ++

    "  <mkdir dir='${ide.pkg.path}/imp/coloring'/>\n" ++
    "  <!-- Language syntax highlighting classes, supported by IMP -->\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/imp/coloring/Colorer.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/imp/coloring/Colorer.java\" filtering=\"true\"/>\n" ++
    "  <copy todir=\"${ide.pkg.path}/imp/coloring/\" overwrite=\"true\" filtering=\"true\">\n" ++
    "        <fileset dir=\"" ++ getIDETempFolder() ++ "imp/coloring/\"/>\n" ++
    "        <globmapper from=\"*.java.template\" to=\"*.java\"/>\n" ++
    "  </copy>\n" ++
    "  \n" ++

    "  <mkdir dir='${ide.pkg.path}/eclipse/wizard'/>\n" ++
    "  <mkdir dir='${ide.pkg.path}/eclipse/wizard/newproject'/>\n" ++
    "  <!-- A wizard for creating new project. -->\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/eclipse/wizard/newproject/NewProjectWizard.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/eclipse/wizard/newproject/NewProjectWizard.java\" filtering=\"true\"/>\n" ++
    "  <copy file=\"" ++ getIDETempFolder() ++ "eclipse/wizard/newproject/PropertyGenerator.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/eclipse/wizard/newproject/PropertyGenerator.java\" filtering=\"true\"/>\n" ++
    "  \n" ++

    (if(config.hasNewFileWizard)
    then
    "  <mkdir dir='${ide.pkg.path}/eclipse/wizard/newfile'/>\n" ++
    "  <!-- A wizard for creating new source file. -->\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/eclipse/wizard/newfile/NewSourceFileWizard.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/eclipse/wizard/newfile/NewSourceFileWizard.java\" filtering=\"true\"/>\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/eclipse/wizard/newfile/WizardNewSourceFilePage.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/eclipse/wizard/newfile/WizardNewSourceFilePage.java\" filtering=\"true\"/>\n" ++
    "  <copy file=\"" ++ getIDETempFolder() ++ "eclipse/wizard/newfile/PropertyControlsProvider.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/eclipse/wizard/newfile/PropertyControlsProvider.java\" filtering=\"true\"/>\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/eclipse/wizard/newfile/GenerateNewFileStub.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/eclipse/wizard/newfile/GenerateNewFileStub.java\" filtering=\"true\"/>\n" ++
    "  \n"
    else
    "") ++

    (if(!null(config.propertyTabs))
    then
    "  <mkdir dir='${ide.pkg.path}/eclipse/property'/>\n" ++
    "  <!-- A project property page -->\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/eclipse/property/IPropertyPageTab.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/eclipse/property/IPropertyPageTab.java\" filtering=\"true\"/>\n" ++
    "  <copy file=\"" ++ getIDETempFolder() ++ "eclipse/property/MultiTabPropertyPage.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/eclipse/property/MultiTabPropertyPage.java\" filtering=\"true\"/>\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/eclipse/property/TabCommons.java.template\"\n" ++    -- this file always get copied even if it's not used
    "        tofile=\"${ide.pkg.path}/eclipse/property/TabCommons.java\" filtering=\"true\"/>\n" ++
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
    "  \n" ++

    "  <!-- 12. eclipse project -->\n" ++
    "  <!-- These files are essential to opening the generated plugin in a local Eclipse application as a Java project. -->\n" ++
    "  <copy file=\"${res}/project.template\" tofile=\"${ide.proj.plugin.path}/.project\" filtering=\"true\"/>\n" ++
    -- commented out to support different build modes
    -- "<copy file=\"${res}/classpath.template\" tofile=\"${ide.proj.plugin.path}/.classpath\" filtering=\"true\"/>\n" ++
    "  <antcall target=\"set classpaths for Eclipse\" inheritAll=\"true\"/>\n" ++

    "  \n"
  ;
}

function getCustomizeTarget
String ::=
{
    return
    "\n" ++
    "<copy todir=\"${ide.proj.plugin.path}\" overwrite=\"true\" filtering=\"true\">\n" ++
    "  <fileset dir=\"${grammar.path}/plugin/\"/>\n" ++
    "</copy>\n";
}

function getAntPostBuildTarget
String ::=
{
    return
    "\n" ++
    "<ant antfile=\"${grammar.path}/postbuild.xml\">\n" ++
    "   <!-- all the global properties defined in build.xml will be passed along to postbuild.xml -->\n" ++
    "</ant>\n";
}

function getBuildTargets
String ::=
{
return

"<!-- Supporting targets based on the build mode -->\n" ++
"<target name=\"create build.properties\" depends=\"filters\">\n"++
"  <copy file=\"${res}/build.properties.template\" tofile=\"${ide.proj.plugin.path}/build.properties\" filtering=\"true\"/>\n"++
"</target>\n"++
"\n"++

"<target name=\"create manifest file\" depends=\"filters\">\n"++	
"  <copy file=\"${res}/META-INF/MANIFEST.MF.template\" tofile=\"${ide.proj.plugin.path}/META-INF/MANIFEST.MF\" filtering=\"true\"/>\n"++
"</target>\n"++
"\n"++

"<target name=\"set classpaths for Eclipse\" depends=\"filters\">\n"++	
"  <copy file=\"${res}/classpath.template\" tofile=\"${ide.proj.plugin.path}/.classpath\" filtering=\"true\"/>\n"++
"</target>\n"++
"\n"++

"<target name=\"create parser controller\" depends=\"filters\">\n"++	
"  <copy file=\"${res}/src/edu/umn/cs/melt/ide/imp/controller/parseController.java.template\"\n" ++
"      tofile=\"${ide.pkg.path}/imp/controller/${lang.name}ParseController.java\" filtering=\"true\"/>\n" ++
"</target>\n"++
"\n"++

"<target name=\"create Eclipse feature\" depends=\"filters\">\n"++	
"  <copy file=\"${res}/pom_templates/feature_templates/feature.xml.template\"\n" ++
"      tofile=\"${ide.proj.feature.path}/feature.xml\" filtering=\"true\"/>\n" ++
"</target>\n"++
"\n"++

"<target name=\"copy plugin dependencies\">\n"++	
"  <copy file=\"${sh}/jars/CopperRuntime.jar\" tofile=\"${ide.proj.plugin.path}/edu.umn.cs.melt.copper.jar\"/>\n"++
"  <copy file=\"${sh}/jars/SilverRuntime.jar\" tofile=\"${ide.proj.plugin.path}/edu.umn.cs.melt.silver.jar\"/>\n"++
"  <copy file=\"${sh}/jars/IDEPluginRuntime.jar\" tofile=\"${ide.proj.plugin.path}/edu.umn.cs.melt.ide.copper-${ide.rt.version}.jar\"/>\n"++
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

