grammar silver:translation:java:core;
import silver:translation:java:env;
import silver:definition:core;
import silver:definition:env;

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody{

  local attribute className :: String;
  className = "P" ++ id.name;

  local attribute sigNames :: [String];
  sigNames = getNamesSignature(namedSig.inputElements);

  top.setupInh := body.setupInh;
  top.initProd := "\t\t//FUNCTION " ++ id.name ++ " " ++ ns.pp ++ "\n" ++ body.translation;
  top.initValues := "";
  top.postInit := "";

  top.javaClasses = [[className,
		
"package " ++ makeName(top.grammarName) ++ ";\n\n" ++

"public class " ++ className ++ " extends common.FunctionNode{\n\n" ++	

makeIndexDcls(0, sigNames) ++ "\n" ++
"\tpublic static final Class<?> childTypes[] = {" ++ makeChildTypesList(ns.inputElements, top.env) ++ "};\n\n" ++

"\tpublic static final java.util.Map<String, common.Lazy> localAttributes = new java.util.TreeMap<String, common.Lazy>();\n" ++
"\tpublic static final java.util.Map<String, common.Lazy> synthesizedAttributes = new java.util.TreeMap<String, common.Lazy>();\n" ++
"\tpublic static final java.util.Map<Object, java.util.Map<String, common.Lazy>> inheritedAttributes = new java.util.HashMap<Object, java.util.Map<String, common.Lazy>>();\n\n" ++	


"\tstatic{\n" ++
makeStaticDcls(className, ns.inputElements) ++
"\t}\n\n" ++ 
	
"\tpublic " ++ className ++ "(" ++ makeConstructor(sigNames) ++ ") {\n" ++
"\t\tthis(new Object[]{" ++ makeChildArray(sigNames) ++ "});\n" ++
"\t}\n\n" ++

"\tpublic " ++ className ++ "(Object[] args) {\n" ++
"\t\tsuper(args);\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic common.Lazy getSynthesized(String name) {\n" ++
"\t\treturn synthesizedAttributes.get(name);\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic java.util.Map<String, common.Lazy> getDefinedInheritedAttributes(Object key) {\n" ++
"\t\treturn inheritedAttributes.get(key);\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic common.Lazy getForward() {\n" ++
"\t\tthrow new RuntimeException(\"Functions do not forward!\");\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic common.Lazy getForwardInh(String name) {\n" ++
"\t\tthrow new RuntimeException(\"Functions do not forward!\");\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic common.Lazy getLocal(String name) {\n" ++
"\t\treturn localAttributes.get(name);\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic String getName() {\n" ++
"\t\treturn \"" ++ fName ++ "\";\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic " ++ ns.outputElement.typerep.transType ++ " doReturn(){\n" ++			
"\t\treturn (" ++ ns.outputElement.typerep.transType ++ ")super.doReturn();\n" ++
"\t}\n" ++ 
"}\n"
		]];
}
