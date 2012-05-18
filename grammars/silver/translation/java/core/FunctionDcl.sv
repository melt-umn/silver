grammar silver:translation:java:core;

import silver:modification:ffi only foreignTypeExp; -- for main type check only
import silver:util;

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody
{
  top.setupInh := body.setupInh;
  top.initProd := "\t\t//FUNCTION " ++ id.name ++ " " ++ ns.pp ++ "\n" ++ body.translation;

  top.initWeaving := "\tpublic static int " ++ localVar ++ " = 0;\n";
  top.valueWeaving := body.valueWeaving;

  local attribute localVar :: String;
  localVar = "count_local__ON__" ++ makeIdName(fName);

  top.javaClasses = [["P" ++ id.name, 
                      generateFunctionClassString(top.grammarName, id.name, namedSig, "final common.DecoratedNode context = new P" ++ id.name ++ "(args).decorate(common.TopNode.singleton, (common.Lazy[])null);\n\t\t//" ++ head(body.uniqueSignificantExpression).pp ++ "\n\t\t return (" ++ namedSig.outputElement.typerep.transType ++ ")(" ++ head(body.uniqueSignificantExpression).translation ++ ");\n")
                    ]];

  -- main function signature check TODO: this should probably be elsewhere!
  top.errors <-
    if id.name == "main" &&
       unify(namedSig.typerep,
         functionTypeExp(nonterminalTypeExp("core:IOVal", [intTypeExp()]), [
           decoratedTypeExp(nonterminalTypeExp("core:List", [stringTypeExp()])),
           foreignTypeExp("core:IO", [])])).failure
    then [err(top.location, "main function must have type signature (IOVal<Integer> ::= [String] IO). Instead it has type " ++ prettyType(namedSig.typerep))]
    else [];
}

function generateFunctionClassString
String ::= whatGrammar::String whatName::String whatSig::Decorated NamedSignature whatResult::String
{
  local attribute className :: String;
  className = "P" ++ whatName;

  local attribute localVar :: String;
  localVar = "count_local__ON__" ++ makeIdName(whatGrammar) ++ "_" ++ whatName;

  local attribute sigNames :: [String];
  sigNames = whatSig.inputNames;

  return 
"package " ++ makeName(whatGrammar) ++ ";\n\n" ++

"public final class " ++ className ++ " extends common.FunctionNode {\n\n" ++	

makeIndexDcls(0, sigNames) ++ "\n" ++
"\tpublic static final Class<?> childTypes[] = {" ++ makeChildTypesList(whatSig.inputElements) ++ "};\n\n" ++

"\tpublic static final int num_local_attrs = Init." ++ localVar ++ ";\n" ++
"\tpublic static final String[] occurs_local = new String[num_local_attrs];\n\n" ++

"\tpublic static final common.Lazy[][] childInheritedAttributes = new common.Lazy[" ++ toString(length(sigNames)) ++ "][];\n\n" ++	

"\tpublic static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];\n" ++
"\tpublic static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];\n\n" ++	


"\tstatic{\n" ++
makeStaticDcls(className, whatSig.inputElements) ++
"\t}\n\n" ++ 
	
"\tpublic " ++ className ++ "(" ++ makeConstructor(sigNames) ++ ") {\n" ++
"\t\tthis(new Object[]{" ++ makeChildArray(sigNames) ++ "});\n" ++
"\t}\n\n" ++

"\tpublic " ++ className ++ "(final Object[] args) {\n" ++
"\t\tsuper(args);\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic common.Lazy[] getLocalInheritedAttributes(final int key) {\n" ++
"\t\treturn localInheritedAttributes[key];\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic common.Lazy[] getChildInheritedAttributes(final int key) {\n" ++
"\t\treturn childInheritedAttributes[key];\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic common.Lazy getLocal(final int key) {\n" ++
"\t\treturn localAttributes[key];\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic final int getNumberOfLocalAttrs() {\n" ++
"\t\treturn num_local_attrs;\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic final String getNameOfLocalAttr(final int index) {\n" ++
"\t\treturn occurs_local[index];\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic String getName() {\n" ++
"\t\treturn \"" ++ whatSig.fullName ++ "\";\n" ++
"\t}\n\n" ++

"\tpublic static " ++ whatSig.outputElement.typerep.transType ++ " invoke(final Object[] args) {\n" ++
"\t\ttry {\n" ++
"\t\t" ++ whatResult ++
"\t\t} catch(Throwable t) { throw new common.exceptions.TraceException(\"Error while evaluating function " ++ whatSig.fullName ++ "\", t); }\n" ++
"\t}\n" ++ 

"\tpublic static final common.NodeFactory<" ++ whatSig.outputElement.typerep.transType ++ "> factory = new Factory();\n\n" ++

"\tpublic static final class Factory extends common.NodeFactory<" ++ whatSig.outputElement.typerep.transType ++ "> {\n\n" ++

"\t\t@Override\n" ++
"\t\tpublic " ++ whatSig.outputElement.typerep.transType ++ " invoke(final Object[] args) {\n" ++
"\t\t\treturn " ++ className ++ ".invoke(args);\n" ++
"\t\t}\n\n" ++
"\t};\n" ++

"}\n";
}

