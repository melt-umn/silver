grammar silver:modification:impide;

import silver:driver;
import silver:translation:java:driver;
import silver:translation:java:core only makeParserName, makeName, makeClassName;

import silver:util:cmdargs;

{--
  The file where production compilation (used to be called buildWriteFile) is originally 
  defined in "silver/translation/java/driver/BuildProcess.sv"; here we're just aspecting 
  that, using '<-' to contribute things to the production attributes declared there.
--}

aspect production compilation
top::Compilation ::= g::Grammars _ buildGrammar::String silverHome::String silverGen::String
{
  -- Empty if no ide decl in that grammar, otherwise has at least one spec... note that
  -- we're going to go with assuming there's just one IDE declaration...
  production ide :: IdeSpec = head(head(builtGrammar).ideSpecs);

  local parserClassName :: String = makeParserName(ide.ideParserSpec.fullName);
  local parserPackageName :: String = makeName(ide.ideParserSpec.sourceGrammar);
  local parserPackagePath :: String = grammarToPath2(ide.ideParserSpec.sourceGrammar);
  local ideParserFullPath :: String = getIDEParserFile(ide.ideParserSpec.sourceGrammar, parserClassName, "${src}/");
  local delegateBuilderName :: String = getDelegateBuilderName(ide.funcDcls);
  local actionExportName :: String = findFunction(ide.funcDcls, "exporter", "ExportLANG", "ExportDummy"); --getExportActionName(ide.funcDcls);
  local folderFileName :: String = findFunction(ide.funcDcls, "folder", "LANGFoldingUpdater", "DummyFoldingUpdater");
  production pkgName :: String = makeName(buildGrammar);

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
    "<property name='ide.parser.ide_copperfile' value='" ++ ideParserFullPath ++ "' />",
    "<property name='ide.delegate.builder.name' value='" ++ delegateBuilderName ++ "' />",
    "<property name='ide.fileextension' value='" ++ ide.ideExtension ++ "' />"] ++ 

    getIDEFunctionsDcls(ide.funcDcls) ++

    [
    "<target name='ide' depends='arg-check, filters, enhance, jars, copper, grammars, create-folders, customize, postbuild'>\n"++
    "    <delete dir='" ++ getIDETempFolder() ++ "'/>\n"++
    "</target>",
    "<target name='arg-check'>" ++ getArgCheckTarget() ++ "</target>",
    "<target name='filters'>" ++ getFiltersTarget() ++ "</target>",
    "<target name='create-folders'>" ++ getCreateFoldersTarget(delegateBuilderName, actionExportName, parserClassName, folderFileName, ide.pluginConfig) ++ "</target>",
    "<target name='customize' if=\"to-customize\" depends='arg-check, filters'>" ++ getCustomizeTarget() ++ "</target>",
    "<target name='postbuild' if=\"to-postbuild\">" ++ getAntPostBuildTarget() ++ "</target>",--this is for ant post-build; not to be confused with IDE post-build
    "<target name='enhance' depends='enhance-build, enhance-postbuild, enhance-export, enhance-fold'></target>",
    "<target name='enhance-build' depends='arg-check, filters' if=\"ide-function-builder-exists\">" ++ getEnhanceTarget(ide.funcDcls, getEnhanceBuildAction) ++ "</target>",--getEnhanceBuildTarget(ide.funcDcls)
    "<target name='enhance-postbuild' depends='arg-check, filters' if=\"ide-function-postbuilder-exists\">" ++ getEnhanceTarget(ide.funcDcls, getEnhancePostBuildAction) ++ "</target>",--getEnhancePostBuildTarget(ide.funcDcls)
    "<target name='enhance-export' depends='arg-check, filters' if=\"ide-function-exporter-exists\">" ++ getEnhanceTarget(ide.funcDcls, getEnhanceExportAction) ++ "</target>", --getEnhanceExportTarget(ide.funcDcls)
    "<target name='enhance-fold' depends='arg-check, filters' if=\"ide-function-folder-exists\">" ++ getEnhanceTarget(ide.funcDcls, getEnhanceFoldAction) ++ "</target>", 
    getBuildTargets()
    ];

  extraDistDeps <- if !isIde then [] else ["ide"]; -- Here's where we demand that target be built ('dist' is a dummy target that just depends on 'jars' initially)
  
  extraGrammarsDeps <- if !isIde then [] else ["enhance"]; -- enhance the language implementation by adding more source files, for use of IDE. (see target enhance)

  -- attributes required as an OSGi module
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
    return "<property name='" ++ getIDEFunctionPropertyKey(funcDcl) ++ "' value='" ++ makeClassName(funcDcl.snd) ++ "' />";
}

function getIDEFunctionPropertyKey
String ::= funcDcl :: Pair<String String>
{
    return "ide.function." ++ funcDcl.fst;
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

function getDelegateBuilderName
String ::= funcDcls :: [Pair<String String>]
{
  local pr :: Pair<Boolean Boolean> = findAllBuilderFunctions(funcDcls, pair(false, false));
  return case pr of
           pair(true, false) -> "BlockingBuilder"
         | pair(false, true) -> "NonblockingBuilder"
         | pair(true, true) -> "TwoStageBuilder"
         | _ -> "DummyBuilder"
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

function getEnhanceFoldAction
String ::= funcDcl::Pair<String String>
{
    return if "folder"==funcDcl.fst
           then 
                "\n<copy file=\"${res}/src/edu/umn/cs/melt/ide/enhance/Fold.java.template\"\n" ++ -- [Location] ::= <<CST root's type>>
                "        tofile=\"${src}/" ++ grammarToPath(funcDcl.snd) ++ "/Fold.java\" filtering=\"true\" overwrite=\"true\"/>"
           else
                "";
}

function getEnhanceExportAction
String ::= funcDcl::Pair<String String>
{
    return if "exporter"==funcDcl.fst
           then 
                "\n<copy file=\"${res}/src/edu/umn/cs/melt/ide/enhance/Export.java.template\"\n" ++ --NIdeMessage[] ::= NIdeProperty[] NIdeEnv
                "        tofile=\"${src}/" ++ grammarToPath(funcDcl.snd) ++ "/Export.java\" filtering=\"true\" overwrite=\"true\"/>"
           else
                "";
}

-- the returned pair indicate whether builder (fst) and/or post-builder (snd) exist
function findAllBuilderFunctions
Pair<Boolean Boolean> ::= funcDcls::[Pair<String String>] pr::Pair<Boolean Boolean>
{
    return if null(funcDcls) --length(funcDcls) < 1
           then pr
           else let
                    hd :: Pair<String String> = head(funcDcls)
                in
                    if(hd.fst=="builder") then findAllBuilderFunctions(tail(funcDcls), pair(true, pr.snd))
                    else if(hd.fst=="postbuilder") then findAllBuilderFunctions(tail(funcDcls), pair(pr.fst, true))
                    else findAllBuilderFunctions(tail(funcDcls), pr)
                end;
}

function getEnhanceBuildAction
String ::= funcDcl::Pair<String String>
{
    return if "builder"==funcDcl.fst
           then 
                "\n<copy file=\"${res}/src/edu/umn/cs/melt/ide/enhance/Build.java.template\"\n" ++ --NIdeMessage[] ::= NIdeProperty[]
                "        tofile=\"${src}/" ++ grammarToPath(funcDcl.snd) ++ "/Build.java\" filtering=\"true\" overwrite=\"true\"/>"
           else
                "";
}

function getEnhancePostBuildAction
String ::= funcDcl::Pair<String String>
{
    return if "postbuilder"==funcDcl.fst
           then 
                "\n<copy file=\"${res}/src/edu/umn/cs/melt/ide/enhance/PostBuild.java.template\"\n" ++ --NIdeMessage[] ::= NIdeProperty[]
                "        tofile=\"${src}/" ++ grammarToPath(funcDcl.snd) ++ "/PostBuild.java\" filtering=\"true\" overwrite=\"true\"/>"
           else
                "";

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

function getArgCheckTarget
String ::=
{
    return
    "\n" ++

    "  <condition property=\"is-all-in-one\">\n"++
    "    <not><equals arg1=\"${multiple-plugins}\" arg2=\"true\"/></not>\n"++	--"all-in-one" mode is default
    "  </condition>\n"++
    "  \n"++
    "  <condition property=\"to-customize\">\n"++
    "    <available file=\"${grammar.path}/plugin\" type=\"dir\"/>\n"++
    "  </condition>\n"++
    "  \n"++
    "  <condition property=\"to-postbuild\">\n"++
    "    <available file=\"${grammar.path}/postbuild.xml\" type=\"file\"/>\n"++
    "  </condition>\n"++
    "  \n"++
    "  <condition property=\"ide-function-folder-exists\">\n"++
    "    <isset property=\"ide.function.folder\"/>\n"++
    "  </condition>\n"++
    "  <condition property=\"ide-function-exporter-exists\">\n"++
    "    <isset property=\"ide.function.exporter\"/>\n"++
    "  </condition>\n"++
    "  <condition property=\"ide-function-builder-exists\">\n"++
    "    <isset property=\"ide.function.builder\"/>\n"++
    "  </condition>\n"++
    "  <condition property=\"ide-function-postbuilder-exists\">\n"++
    "    <isset property=\"ide.function.postbuilder\"/>\n"++
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
    "  <filter token=\"FEATURE_LICENSE_TEXT\" value='no license information available'/>\n" ++
    "  <filter token=\"FOLDER_CLASS_QNAME\" value='${ide.function.folder}'/>\n" ++
    "  <filter token=\"BUILDER_CLASS_QNAME\" value='${ide.function.builder}'/>\n" ++
    "  <filter token=\"POST_BUILDER_CLASS_QNAME\" value='${ide.function.postbuilder}'/>\n" ++
    "  <filter token=\"EXPORTER_CLASS_QNAME\" value='${ide.function.exporter}'/>\n" ++
    "  <filter token=\"DELEGATE_BUILDER_NAME\" value='${ide.delegate.builder.name}'/>\n" ++
    "  <filter token=\"LANG_COMPOSED_PKG\" value='${lang.composed}'/>\n" ++ 
    "  <filter token=\"START_NONTERMINAL_CLASS\" value='${start.nonterminal.class}'/>\n" ++
    "  <filter token=\"IDE_RT_VERSION\" value='${ide.rt.version}'/>\n" ++
    "\n";
}

function getCreateFoldersTarget
String ::= delegateBuilderName::String actionExportName::String parserClassName::String folderFileName::String config::PluginConfig
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
    "  <antcall target=\"create build.properties (all-on-one)\" inheritAll=\"true\"/>\n" ++
    "\n" ++

    "  <!-- 4. plugin.xml -->\n" ++
    -- the single-file template is no longer used.
    -- "  <copy file=\"${res}/plugin.xml.template\" tofile=\"${ide.proj.plugin.path}/plugin.xml\" filtering=\"true\"/>\n" ++
    "  <copy file=\"" ++ getIDETempFolder() ++ "/plugin.xml.template\" tofile=\"${ide.proj.plugin.path}/plugin.xml\" filtering=\"true\"/>\n" ++
    "  \n" ++

    "  <!-- 5. plugin dependencies -->\n" ++
    "  <!-- (1) language implementation -->\n" ++
    "  <copy file=\"${lang.composed}.jar\" tofile=\"${ide.proj.plugin.path}/${lang.composed}.jar\"/>\n" ++
    "  <!-- (2) runtimes; copied only in all-in-one mode -->\n" ++
    "  <antcall target=\"copy plugin dependencies\"/>\n"++
    "  \n" ++

    "  <!-- 6. manifest file -->\n" ++
    "  <mkdir dir='${ide.proj.plugin.path}/META-INF/'/>\n" ++
    -- commented out to support different build modes
    -- "<copy file=\"${res}/META-INF/MANIFEST.MF.template\" tofile=\"${ide.proj.plugin.path}/META-INF/MANIFEST.MF\" filtering=\"true\"/>\n" ++
    "  <antcall target=\"create manifest file\" inheritAll=\"true\"/>\n" ++
    "  <antcall target=\"create manifest file (all-on-one)\" inheritAll=\"true\"/>\n" ++
    "  \n" ++

    "  <!-- 7. customized IDE parser -->\n" ++
    "  <mkdir dir='${ide.pkg.path}/copper/engine/'/>\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/copper/engine/EnhancedSilverParser.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/copper/engine/EnhancedSilverParser.java\" filtering=\"true\"/>\n" ++
    "  <mkdir dir='${ide.pkg.path}/imp/controller'/>\n" ++
    -- commented out to support different build modes
    --"<copy file=\"${res}/src/edu/umn/cs/melt/ide/imp/controller/parseController.java.template\"\n" ++
    --"      tofile=\"${ide.pkg.path}/imp/controller/${lang.name}ParseController.java\" filtering=\"true\"/>\n" ++
    "  <antcall target=\"create parser controller\" inheritAll=\"true\"/>\n" ++
    "  <antcall target=\"create parser controller (all-on-one)\" inheritAll=\"true\"/>\n" ++
    "  \n" ++

    "  <!-- 8. core plug-in classes -->\n" ++
    "  <mkdir dir='${ide.pkg.path}/'/>\n" ++  
    "  <!-- An initializer to be called during plugin start-up -->\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/Initializer.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/${lang.name}Initializer.java\" filtering=\"true\"/>\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/StartupHook.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/StartupHook.java\" filtering=\"true\"/>\n" ++
    "  <!-- The project properties -->\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/Service.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/${lang.name}Service.java\" filtering=\"true\"/>\n" ++
    "  \n" ++

    "  <mkdir dir='${ide.pkg.path}/imp/'/>\n" ++  
    "  <!-- Plugin main class (OSGi starter class) -->\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/imp/plugin.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/imp/${lang.name}Plugin.java\" filtering=\"true\"/>\n" ++
    "  \n" ++

    "  <mkdir dir='${ide.pkg.path}/imp/actions'/>\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/imp/actions/EnableLANGNature.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/imp/actions/Enable${lang.name}Nature.java\" filtering=\"true\"/>\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/imp/actions/"++actionExportName++".java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/imp/actions/Export${lang.name}.java\" filtering=\"true\"/>\n" ++
    "  \n" ++

    "  <mkdir dir='${ide.pkg.path}/imp/builders'/>\n" ++
    "  <!-- Project builder and supporting classes. The class interfacing with Eclipse/IMP build framework is ${lang.name}Builder. -->\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/imp/builders/nature.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/imp/builders/${lang.name}Nature.java\" filtering=\"true\"/>\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/imp/builders/builder.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/imp/builders/${lang.name}Builder.java\" filtering=\"true\"/>\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/imp/builders/PostActionHandler.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/imp/builders/PostActionHandler.java\" filtering=\"true\"/>\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/imp/builders/Utility.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/imp/builders/Utility.java\" filtering=\"true\"/>\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/imp/builders/DelegateBuilder.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/imp/builders/DelegateBuilder.java\" filtering=\"true\"/>\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/imp/builders/"++delegateBuilderName++".java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/imp/builders/"++delegateBuilderName++".java\" filtering=\"true\"/>\n" ++
    "  <antcall target=\"create-builder\" inheritAll=\"true\"/>\n" ++
    "  <antcall target=\"create-postbuilder\" inheritAll=\"true\"/>\n" ++
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

    "  <mkdir dir='${ide.pkg.path}/imp/folding'/>\n" ++
    "  <!-- Language folding classes, supported by IMP -->\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/imp/folding/" ++ folderFileName ++ ".java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/imp/folding/${lang.name}FoldingUpdater.java\" filtering=\"true\"/>\n" ++
    "  \n" ++

    "  <mkdir dir='${ide.pkg.path}/eclipse/wizard'/>\n" ++
    "  <!-- A wizard for creating new project. -->\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/eclipse/wizard/NewProjectWizard.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/eclipse/wizard/NewProjectWizard.java\" filtering=\"true\"/>\n" ++
    "  <copy file=\"" ++ getIDETempFolder() ++ "eclipse/wizard/PropertyGenerator.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/eclipse/wizard/PropertyGenerator.java\" filtering=\"true\"/>\n" ++
    "  \n" ++

    "  <mkdir dir='${ide.pkg.path}/eclipse/property'/>\n" ++
    "  <!-- A property page for the project -->\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/eclipse/property/LANGPropertyPage.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/eclipse/property/${lang.name}PropertyPage.java\" filtering=\"true\"/>\n" ++
    "  <copy file=\"" ++ getIDETempFolder() ++ "eclipse/property/PropertyControlsProvider.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/eclipse/property/PropertyControlsProvider.java\" filtering=\"true\"/>\n" ++
    "  \n" ++

    (if(config.hasSourceLinker)
    then
    "  <!-- A property page for the project's build configuration -->\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/eclipse/property/LinkSourceWizard.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/eclipse/property/LinkSourceWizard.java\" filtering=\"true\"/>\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/eclipse/property/LANGBuildConfigPropertyPage.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/eclipse/property/${lang.name}BuildConfigPropertyPage.java\" filtering=\"true\"/>\n" ++
    "  \n"
    else
    "") ++

    "  <mkdir dir='${ide.pkg.path}/eclipse/resource'/>\n" ++
    "  <!-- Project resource management -->\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/eclipse/resource/LinkedResourceTracker.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/eclipse/resource/LinkedResourceTracker.java\" filtering=\"true\"/>\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/eclipse/resource/ResourceChangeListener.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/eclipse/resource/ResourceChangeListener.java\" filtering=\"true\"/>\n" ++
    "  \n" ++

    "  <mkdir dir='${ide.pkg.path}/eclipse/perspective'/>\n" ++
    "  <!-- A perspective for development using this language. -->\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/eclipse/perspective/LANGPerspective.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/eclipse/perspective/${lang.name}Perspective.java\" filtering=\"true\"/>\n" ++
    "  \n" ++

    "  <mkdir dir='${ide.pkg.path}/eclipse/console'/>\n" ++
    "  <!-- An IDE console specific to project of this language. -->\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/eclipse/console/LANGConsole.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/eclipse/console/${lang.name}Console.java\" filtering=\"true\"/>\n" ++
    "  \n" ++

    "  <!-- 9. IDE runtime/Silver Eclipse API supports -->\n" ++
    "  <mkdir dir='${ide.pkg.path}/silver/cst'/>\n" ++
    "  <!-- CST traversal service -->\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/silver/cst/DefinitionFinder.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/silver/cst/DefinitionFinder.java\" filtering=\"true\"/>\n" ++
    "  <copy file=\"${res}/src/edu/umn/cs/melt/ide/silver/cst/NonTerminalFinder.java.template\"\n" ++
    "        tofile=\"${ide.pkg.path}/silver/cst/NonTerminalFinder.java\" filtering=\"true\"/>\n" ++
    "  \n" ++

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
    "  <antcall target=\"create Eclipse feature (all-on-one)\" inheritAll=\"true\"/>\n" ++
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
    "  <antcall target=\"set classpaths for Eclipse (all-on-one)\" inheritAll=\"true\"/>\n" ++

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

"<!-- Optional targets depending on whether the corresponding function is defined -->\n" ++
"<target name='create-builder' if=\"ide-function-builder-exists\" depends=\"filters\">\n"++
"  <copy file=\"${res}/src/edu/umn/cs/melt/ide/imp/builders/build_invoker.java.template\"\n"++
"        tofile=\"${ide.pkg.path}/imp/builders/${lang.name}BuildInvoker.java\" filtering=\"true\"/>\n"++
"</target>\n"++
"<target name='create-postbuilder' if=\"ide-function-postbuilder-exists\" depends=\"filters\">\n"++
"  <copy file=\"${res}/src/edu/umn/cs/melt/ide/imp/builders/postbuild_invoker.java.template\"\n"++
"        tofile=\"${ide.pkg.path}/imp/builders/${lang.name}PostBuildInvoker.java\" filtering=\"true\"/>\n"++
"</target>\n"++

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

"<target name=\"create parser controller\" unless=\"is-all-in-one\" depends=\"filters\">\n"++	
"  <copy file=\"${res}/src/edu/umn/cs/melt/ide/imp/controller/parseController.java.template\"\n" ++
"      tofile=\"${ide.pkg.path}/imp/controller/${lang.name}ParseController.java\" filtering=\"true\"/>\n" ++
"</target>\n"++
"<target name=\"create parser controller (all-on-one)\" if=\"is-all-in-one\" depends=\"filters\">\n"++	
"  <copy file=\"${res}/src/edu/umn/cs/melt/ide/imp/controller/parseController.java.template.all_in_one\"\n" ++
"      tofile=\"${ide.pkg.path}/imp/controller/${lang.name}ParseController.java\" filtering=\"true\"/>\n" ++
"</target>\n"++
"\n"++

"<target name=\"create Eclipse feature\" unless=\"is-all-in-one\" depends=\"filters\">\n"++	
"  <copy file=\"${res}/pom_templates/feature_templates/feature.xml.template\"\n" ++
"      tofile=\"${ide.proj.feature.path}/feature.xml\" filtering=\"true\"/>\n" ++
"</target>\n"++
"<target name=\"create Eclipse feature (all-on-one)\" if=\"is-all-in-one\" depends=\"filters\">\n"++	
"  <copy file=\"${res}/pom_templates/feature_templates/feature.xml.template.all_in_one\"\n" ++
"      tofile=\"${ide.proj.feature.path}/feature.xml\" filtering=\"true\"/>\n" ++
"</target>\n"++
"\n"++

-- these dependencies are copied to plugin folder only if it's all-in-one mode.
"<target name=\"copy plugin dependencies\" if=\"is-all-in-one\">\n"++	
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

