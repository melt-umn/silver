grammar silver:translation:java:core;

import silver:definition:type:io; -- for main type check only

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody
{
  top.setupInh := body.setupInh;
  top.initProd := "\t\t//FUNCTION " ++ id.name ++ " " ++ ns.pp ++ "\n" ++ body.translation;

  top.javaClasses = [["P" ++ id.name, 
                      generateFunctionClassString(top.grammarName, id.name, namedSig, "return (" ++ ns.outputElement.typerep.transType ++ ")super.doReturn();\n")
                    ]];

  -- main function signature check TODO: this should probably be elsewhere!
  top.errors <-
        if id.name == "main" &&
           unify(functionTypeExp(ns.outputElement.typerep, getTypesSignature(ns.inputElements)),
                 functionTypeExp(nonterminalTypeExp("core:IOVal", [intTypeExp()]), [
                                   decoratedTypeExp(nonterminalTypeExp("core:List", [stringTypeExp()])),
                                   ioTypeExp()])).failure
        then [err(top.location, "main function must have type signature Function(IOVal<Integer> ::= [String] IO). Instead it has type " ++ prettyType(functionTypeExp(ns.outputElement.typerep, getTypesSignature(ns.inputElements))))]
        else [];
}

function generateFunctionClassString
String ::= whatGrammar::String whatName::String whatSig::Decorated NamedSignature whatResult::String
{
  local attribute className :: String;
  className = "P" ++ whatName;

  local attribute sigNames :: [String];
  sigNames = getNamesSignature(whatSig.inputElements);

  return 
"package " ++ makeName(whatGrammar) ++ ";\n\n" ++

"public class " ++ className ++ " extends common.FunctionNode{\n\n" ++	

makeIndexDcls(0, sigNames) ++ "\n" ++
"\tpublic static final Class<?> childTypes[] = {" ++ makeChildTypesList(whatSig.inputElements) ++ "};\n\n" ++

"\tpublic static final java.util.Map<String, common.Lazy> localAttributes = new java.util.TreeMap<String, common.Lazy>();\n" ++
"\tpublic static final java.util.Map<String, common.Lazy> synthesizedAttributes = new java.util.TreeMap<String, common.Lazy>();\n" ++
"\tpublic static final java.util.Map<Object, java.util.Map<String, common.Lazy>> inheritedAttributes = new java.util.HashMap<Object, java.util.Map<String, common.Lazy>>();\n\n" ++	


"\tstatic{\n" ++
makeStaticDcls(className, whatSig.inputElements) ++
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
"\tpublic common.Lazy getLocal(String name) {\n" ++
"\t\treturn localAttributes.get(name);\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic String getName() {\n" ++
"\t\treturn \"" ++ whatSig.fullName ++ "\";\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic " ++ whatSig.outputElement.typerep.transType ++ " doReturn(){\n" ++			
"\t\t" ++ whatResult ++
"\t}\n" ++ 

"\tpublic static final common.NodeFactory<" ++ className ++ "> factory = new Factory();\n\n" ++

"\tpublic static final class Factory implements common.NodeFactory<" ++ className ++ "> {\n\n" ++

"\t\t@Override\n" ++
"\t\tpublic " ++ className ++ " construct(final Object[] children) {\n" ++
"\t\t\treturn new " ++ className ++ "(children);\n" ++
"\t\t}\n\n" ++
"\t};\n" ++

"}\n";
}
