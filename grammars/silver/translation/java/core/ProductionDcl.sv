grammar silver:translation:java:core;

import silver:util;

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  local attribute className :: String;
  className = "P" ++ id.name;

  local attribute sigNames :: [String];
  sigNames = getNamesSignature(namedSig.inputElements);

  top.setupInh := body.setupInh;
  top.initProd := "\t\t" ++ makeName(top.grammarName) ++ "." ++ className ++ ".initProductionAttributeDefinitions();\n";
  top.postInit := "\t\tcommon.Decorator.applyDecorators(" ++ fnnt ++ ".decorators, " ++ className ++ ".class);\n";

  top.initWeaving := "\tpublic static int " ++ localVar ++ " = 0;\n";
  top.valueWeaving := body.valueWeaving;

  local attribute localVar :: String;
  localVar = "count_local__ON__" ++ makeIdName(fName);

  local attribute fnnt :: String;
  fnnt = makeNTClassName(ns.outputElement.typerep.typeName);

  top.javaClasses = [[className,
		
"package " ++ makeName(top.grammarName) ++ ";\n\n" ++

"// " ++ ns.pp ++ "\n" ++
"public final class " ++ className ++ " extends " ++ fnnt ++ " {\n\n" ++

makeIndexDcls(0, sigNames) ++ "\n" ++
"\tpublic static final Class<?> childTypes[] = {" ++ makeChildTypesList(ns.inputElements) ++ "};\n\n" ++

"\tpublic static final int num_local_attrs = Init." ++ localVar ++ ";\n" ++
"\tpublic static final String[] occurs_local = new String[num_local_attrs];\n\n" ++

"\tpublic static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[" ++ fnnt ++ ".num_inh_attrs];\n\n" ++

"\tpublic static final common.Lazy[] synthesizedAttributes = new common.Lazy[" ++ fnnt ++ ".num_syn_attrs];\n" ++
"\tpublic static final common.Lazy[][] childInheritedAttributes = new common.Lazy[" ++ toString(length(sigNames)) ++ "][];\n\n" ++	

"\tpublic static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];\n" ++
"\tpublic static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];\n\n" ++

"\tstatic{\n" ++
makeStaticDcls(className, ns.inputElements) ++
"\t}\n\n" ++ 

"\tpublic " ++ className ++ "(" ++ makeConstructor(sigNames) ++ ") {\n" ++
"\t\tthis(new Object[]{" ++ makeChildArray(sigNames) ++ "});\n" ++
"\t}\n\n" ++

"\tpublic " ++ className ++ "(final Object[] args) {\n" ++
"\t\tsuper(args);\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic common.Lazy getSynthesized(final int index) {\n" ++
"\t\treturn synthesizedAttributes[index];\n" ++
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
"\tpublic common.Node getForward(final common.DecoratedNode context) {\n" ++
"\t\treturn " ++ (if null(body.uniqueSignificantExpression) then "null" else head(body.uniqueSignificantExpression).translation) ++ ";\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic common.Lazy getForwardInheritedAttributes(final int index) {\n" ++
"\t\treturn forwardInheritedAttributes[index];\n" ++
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
"\t\treturn \"" ++ fName ++ "\";\n" ++
"\t}\n\n" ++

"\tstatic void initProductionAttributeDefinitions() {\n" ++
  body.translation ++
"\t}\n\n" ++

"\tpublic static final common.NodeFactory<" ++ className ++ "> factory = new Factory();\n\n" ++

"\tpublic static final class Factory extends common.NodeFactory<" ++ className ++ "> {\n\n" ++

"\t\t@Override\n" ++
"\t\tpublic " ++ className ++ " invoke(final Object[] children) {\n" ++
"\t\t\treturn new " ++ className ++ "(children);\n" ++
"\t\t}\n\n" ++
"\t};\n" ++

"}\n"
  ]];

  -- main function signature check TODO: this should probably be elsewhere!
  top.errors <-
        if id.name == "main"
        then [err(top.location, "main should be a function!")]
        else [];
}

function makeIndexDcls
String ::= i::Integer s::[String]{
  return if null(s) then "" else "\tpublic static final int i_" ++ head(s) ++ " = " ++ toString(i) ++ ";\n"  ++ makeIndexDcls(i+1, tail(s));
}

function makeStaticDcls
String ::= className::String s::[Decorated NamedSignatureElement]{
  return if null(s) 
	 then "" 
	 else (if head(s).typerep.isDecorable then
	      "\tchildInheritedAttributes[i_" ++ head(s).elementName ++ "] = " ++ 
                                                            "new common.Lazy[" ++ makeNTClassName(head(s).typerep.typeName) ++ ".num_inh_attrs];\n"
               else "") ++ makeStaticDcls(className, tail(s));
}

function makeConstructor
String ::= s::[String]{
  return if null(s) then "" else "final Object c_" ++ head(s) ++ (if null(tail(s)) then "" else (", " ++ makeConstructor(tail(s))));
}

function makeChildArray
String ::= s::[String]{
  return if null(s) then "" else "c_" ++ head(s) ++ (if null(tail(s)) then "" else (", " ++ makeChildArray(tail(s))));
}

-- meant to turn  ::= Foo String Bar
-- into {grammar.NFoo.class, String.class, other.NBar.class}
function makeChildTypesList
String ::= ns::[Decorated NamedSignatureElement]
{
  return if null(ns)
         then ""
         else head(ns).typerep.transClassType ++ ".class"
              ++ if null(tail(ns))
                 then ""
                 else ", " ++ makeChildTypesList(tail(ns));
}

