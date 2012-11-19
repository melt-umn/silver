grammar silver:translation:java:core;

import silver:driver:util;

attribute genFiles occurs on RootSpec;

aspect production interfaceRootSpec
top::RootSpec ::= _
{
  top.genFiles := [];
}

aspect production grammarRootSpec
top::RootSpec ::= g::Grammar  _
{
  top.genFiles := g.genFiles ++
  [pair("Init.java",
"package " ++ makeName(g.declaredName) ++ ";\n\n" ++

"public class Init{\n\n" ++

"\tprivate static boolean preInit = false;\n" ++
"\tprivate static boolean init = false;\n" ++
"\tprivate static boolean postInit = false;\n\n" ++

"\tpublic static void initAllStatics(){\n" ++
"\t\tif(preInit) return;\n" ++
"\t\tpreInit = true;\n\n" ++
  makeOthers(top.allGrammarDependencies, "initAllStatics") ++ "\n" ++
"\t}\n\n" ++

"\tpublic static void init(){\n" ++
"\t\tif(init) return;\n" ++
"\t\tinit = true;\n\n" ++
"\t\tsetupInheritedAttributes();\n\n" ++
  makeOthers(top.allGrammarDependencies, "init") ++ "\n" ++
"\t\tinitProductionAttributeDefinitions();\n" ++
"\t}\n\n" ++

"\tpublic static void postInit(){\n" ++
"\t\tif(postInit) return;\n" ++
"\t\tpostInit = true;\n\n" ++
  makeOthers(top.allGrammarDependencies, "postInit") ++ "\n\n" ++
  g.postInit ++
"\t}\n\n" ++

"\tprivate static void setupInheritedAttributes(){\n" ++
  g.setupInh ++
"\t}\n\n" ++

"\tprivate static void initProductionAttributeDefinitions(){\n" ++
  g.initProd ++
"\t}\n\n" ++

  g.initWeaving ++ 
  g.valueWeaving ++
  g.initValues ++
"}\n")];
}

function makeOthers
String ::= others::[String] nme::String
{
  return if null(others) then "" else "\t\t" ++ makeName(head(others)) ++ ".Init."++nme++"();\n" ++ makeOthers(tail(others),nme);
}

