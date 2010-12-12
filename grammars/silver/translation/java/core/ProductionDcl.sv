grammar silver:translation:java:core;

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody{

  local attribute className :: String;
  className = "P" ++ id.name;

  local attribute sigNames :: [String];
  sigNames = getNamesSignature(namedSig.inputElements);

  top.setupInh := body.setupInh;
  top.initProd := "\t\t//PRODUCTION " ++ id.name ++ " " ++ ns.pp ++ "\n" ++ body.translation;
  top.initValues := "";
  top.postInit := "\t\tcommon.Decorator.applyDecorators(" ++ fnnt ++ ".decorators, " ++ className ++ ".class);\n";

  local attribute fnnt :: String;
  fnnt = makeNTClassName(ns.outputElement.typerep.typeName);

  top.javaClasses = [[className,
		
"package " ++ makeName(top.grammarName) ++ ";\n\n" ++

"public class " ++ className ++ " extends " ++ fnnt ++ " {\n\n" ++

makeIndexDcls(0, sigNames) ++ "\n" ++
"\tpublic static final Class<?> childTypes[] = {" ++ makeChildTypesList(ns.inputElements) ++ "};\n\n" ++

"\tpublic static common.Lazy forward;\n" ++
"\tpublic static final java.util.Map<String, common.Lazy> forwardAttributes = new java.util.TreeMap<String, common.Lazy>();\n\n" ++

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
"\t\treturn forward;\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic common.Lazy getForwardInh(String name) {\n" ++
"\t\treturn forwardAttributes.get(name);\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic common.Lazy getLocal(String name) {\n" ++
"\t\treturn localAttributes.get(name);\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic String getName() {\n" ++
"\t\treturn \"" ++ fName ++ "\";\n" ++
"\t}\n\n" ++

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
	 else (if head(s).typerep.mayBeSuppliedInhAttrs then
	      "\t" ++ className ++ ".inheritedAttributes.put(i_" ++ head(s).elementName ++ ", " ++ 
                                                            "new java.util.TreeMap<String, common.Lazy>());\n"
               else "") ++ makeStaticDcls(className, tail(s));
}

function makeConstructor
String ::= s::[String]{
  return if null(s) then "" else "Object c_" ++ head(s) ++ (if null(tail(s)) then "" else (", " ++ makeConstructor(tail(s))));
}

function makeChildArray
String ::= s::[String]{
  return if null(s) then "" else "c_" ++ head(s) ++ (if null(tail(s)) then "" else (", " ++ makeChildArray(tail(s))));
}

-- meant to turn  ::= Foo String Bar
-- into {grammar.NFoo.class, String.class, other.NBar.class}
-- TODO: this might be broken for decorated types? also function/production types.
-- by broken I mean it won't reveal any useful type information (java.lang.Constructor.class)
-- Also, it preserves no information about _what_ terminal!
function makeChildTypesList
String ::= ns::[Decorated NamedSignatureElement]
{
  return if null(ns)
         then ""
         else head(ns).typerep.transType ++ ".class"
              ++ if null(tail(ns))
                 then ""
                 else ", " ++ makeChildTypesList(tail(ns));
}

