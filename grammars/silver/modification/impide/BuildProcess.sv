grammar silver:modification:impide;

import silver:driver;
import silver:translation:java:driver;
import silver:translation:java:core only makeParserName, makeName;

import silver:util:cmdargs;

-- The file where writeBuildFile is originally define is silver/translation/java/driver/BuildProcess.sv
-- Here we're just aspecting that, which lets use contibute (<-) things to the production attributes declared there

aspect function writeBuildFile
IO ::= i::IO a::Decorated CmdArgs specs::[String] silverhome::String silvergen::String da::Decorated DependencyAnalysis
{
  -- The RootSpec representing the grammar actually being built (specified on the command line)
  local builtGrammar :: [Decorated RootSpec] = getRootSpec(a.buildGrammar, da.compiledList);
  
  -- Empty if no ide decl in that grammar, otherwise has at least one spec... note that
  -- we're going to go with assuming there's just one IDE declaration...
  local isIde :: Boolean = !null(builtGrammar) && !null(head(builtGrammar).ideSpecs);
  local ide :: IdeSpec = head(head(builtGrammar).ideSpecs);

  local parserClassName :: String = makeParserName(ide.ideParserSpec.fullName);
  local parserPackageName :: String = makeName(ide.ideParserSpec.sourceGrammar);
  local parserPackagePath :: String = grammarToPath(ide.ideParserSpec.sourceGrammar);
  local parserFullPath :: String = "${src}/" ++ parserPackagePath ++ parserClassName ++ ".copper";

  extraTopLevelDecls <- if !isIde then [] else [
    -- Defining new properties. Dummy values for now:
    "<property name='ide.version' value='1.0.0'/>",
    "<property name='lang.name' value='" ++ a.buildGrammar ++ "'/>",
    "<property name='lang.composed' value='" ++ grammarToPackage(a.buildGrammar) ++ "'/>",
    "<property name='ide.proj.name' value='${ide.pkg.name}'/>",
    -- These seem accurate:
    "<property name='ide.pkg.name' value='" ++ grammarToPackage(a.buildGrammar) ++ "'/>",
    "<property name='ide.proj.parent.path' location='${jg}/ide/${ide.proj.name}'/>",
    "<property name='ide.proj.plugin.path' location='${ide.proj.parent.path}/plugin'/>",
    "<property name='ide.proj.feature.path' location='${ide.proj.parent.path}/feature'/>",
    "<property name='ide.proj.updatesite.path' location='${ide.proj.parent.path}/updatesite'/>",
    "<property name='ide.pkg.path' location='${ide.proj.plugin.path}/src/what/is/this'/>", -- TODO this path needs fixing. What is this property USED for?
    -- I've added these myself, for you to make use of
    "<property name='ide.parser.package' value='" ++ parserPackageName ++ "' />",
    "<property name='ide.parser.classname' value='" ++ parserClassName ++ "' />",
    "<property name='ide.parser.copperfile' value='" ++ parserFullPath ++ "' />",
    "<property name='ide.fileextension' value='" ++ ide.ideExtension ++ "' />",

    -- Here's where you define a target! (as a top level decl)
    "<target name='ide' depends='jars, copper, grammars'></target>\n\n"
    ];

  extraDistDeps <- if !isIde then [] else ["ide"]; -- Here's where we demand that target be built ('dist' is a dummy target that just depends on 'jars' initially)
  
  -- We can add all the OSGi stuff to the manifest here
  extraManifestAttributes <- if !isIde then [] else [
    "<attribute name='Bundle-ManifestVersion' value='1' />",
    "<attribute name='Bundle-Name' value='" ++ a.buildGrammar ++ "' />", -- TODO
    "<attribute name='Bundle-SymbolicName' value='" ++ grammarToPackage(a.buildGrammar) ++ "' />", -- TODO
    "<attribute name='Bundle-Version' value='${ide.version}' />",
    "<attribute name='Bundle-Vendor' value='${user.name}' />",
    "<attribute name='Export-Package' value='" ++ implode(", ", map(grammarToExportString, specs)) ++ "' />",
    "<attribute name='Bundle-RequiredExecutionEnvironment' value='J2SE-1.5' />",
    "<attribute name='Require-Bundle' value='edu.umn.cs.melt.copper;bundle-version=\"1.0.0\", edu.umn.cs.melt.silver;bundle-version=\"1.0.0\"' />"
    ];
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


{---
core;version="${ide.version}", lib;version="${ide.version}", lib.extcore;version="${ide.version}", silver;version="${ide.version}", silver.analysis;version="${ide.version}", silver.analysis.binding;version="${ide.version}", silver.analysis.binding.driver;version="${ide.version}", silver.analysis.typechecking;version="${ide.version}", silver.analysis.typechecking.core;version="${ide.version}", silver.composed;version="${ide.version}", silver.composed.Default;version="${ide.version}", silver.definition;version="${ide.version}", silver.definition.concrete_syntax;version="${ide.version}", silver.definition.concrete_syntax.ast;version="${ide.version}", silver.definition.concrete_syntax.ast.env_parser;version="${ide.version}", silver.definition.core;version="${ide.version}", silver.definition.env;version="${ide.version}", silver.definition.env.env_parser;version="${ide.version}", silver.definition.regex;version="${ide.version}", silver.definition.type;version="${ide.version}", silver.definition.type.gatherfreevars;version="${ide.version}", silver.definition.type.io;version="${ide.version}", silver.definition.type.syntax;version="${ide.version}", silver.driver;version="${ide.version}", silver.extension;version="${ide.version}", silver.extension.convenience;version="${ide.version}", silver.extension.deprecation;version="${ide.version}", silver.extension.easyterminal;version="${ide.version}", silver.extension.list;version="${ide.version}", silver.extension.list.env_parser;version="${ide.version}", silver.extension.list.java;version="${ide.version}", silver.extension.testing;version="${ide.version}", silver.host;version="${ide.version}", silver.host.env;version="${ide.version}", silver.modification;version="${ide.version}", silver.modification.autocopyattr;version="${ide.version}", silver.modification.autocopyattr.convenience;version="${ide.version}", silver.modification.autocopyattr.env_parser;version="${ide.version}", silver.modification.autocopyattr.java;version="${ide.version}", silver.modification.collection;version="${ide.version}", silver.modification.collection.env_parser;version="${ide.version}", silver.modification.collection.java;version="${ide.version}", silver.modification.copper;version="${ide.version}", silver.modification.copper.env_parser;version="${ide.version}", silver.modification.ffi;version="${ide.version}", silver.modification.ffi.env_parser;version="${ide.version}", silver.modification.ffi.java;version="${ide.version}", silver.modification.ffi.util;version="${ide.version}", silver.modification.let_fix;version="${ide.version}", silver.modification.let_fix.java;version="${ide.version}", silver.modification.patternmatching;version="${ide.version}", silver.modification.typedecl;version="${ide.version}", silver.modification.typedecl.env_parser;version="${ide.version}", silver.translation;version="${ide.version}", silver.translation.java;version="${ide.version}", silver.translation.java.core;version="${ide.version}", silver.translation.java.driver;version="${ide.version}", silver.translation.java.type;version="${ide.version}", silver.translation.java.type.io;version="${ide.version}", silver.util;version="${ide.version}", silver.util.cmdargs;version="${ide.version}", silver.util.raw;version="${ide.version}", silver.util.raw.treemap;version="${ide.version}", silver.util.treemap;version="${ide.version}"
-}

