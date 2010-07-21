grammar silver:modification:ffi:java;
import silver:modification:ffi;
import silver:modification:ffi:util;
import silver:translation:java:core;
import silver:translation:java:env;
import silver:definition:core;
import silver:definition:env;

synthesized attribute ffiTranslationString :: [String] occurs on FFIDef, FFIDefs;

aspect production ffidefsOne
top::FFIDefs ::= one::FFIDef
{
  top.ffiTranslationString = one.ffiTranslationString;
}

aspect production ffidefsMany
top::FFIDefs ::= one::FFIDef more::FFIDefs
{
  top.ffiTranslationString = one.ffiTranslationString ++ more.ffiTranslationString;
}

aspect production ffidef
top::FFIDef ::= name::String_t ':' 'return' code::String_t ';'
{
  top.ffiTranslationString = if name.lexeme == "\"java\"" then [cleanStringLexeme(code.lexeme)] else [];
}

function childAccessor
String ::= t::Decorated TypeRep fName::String className::String
{
  return "((" ++ t.transType ++ ")context.child(" ++ className ++ ".i_" ++ fName  ++ "))";
}

function computeSigTranslation
String ::= str::String sig::Decorated NamedSignature
{
  return percentSubst(str, getNamesSignature(sig.inputElements), mapSignature(childAccessor, sig.inputElements, makeClassName(sig.fullName)));
}



aspect production functionDclFFI
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 'foreign' '{' ffidefs::FFIDefs '}'
{
  local attribute className :: String;
  className = "P" ++ id.name;

  local attribute sigNames :: [String];
  sigNames = getNamesSignature(namedSig.inputElements);

  top.setupInh := forward.setupInh; -- hacky hacky!
  top.initProd := forward.initProd; -- hacky hacky!
  top.initValues := "";
  top.postInit := "";

  top.javaClasses = [[className,
		
"package " ++ makeName(top.grammarName) ++ ";\n\n" ++

"public class " ++ className ++ " extends common.FunctionNode{\n\n" ++	

makeIndexDcls(0, sigNames) ++ "\n" ++
"\tpublic static final Class<?> childTypes[] = {" ++ makeChildTypesList(namedSig.inputElements, top.env) ++ "};\n\n" ++

"\tpublic static final java.util.Map<String, common.Lazy> localAttributes = new java.util.TreeMap<String, common.Lazy>();\n" ++
"\tpublic static final java.util.Map<String, common.Lazy> synthesizedAttributes = new java.util.TreeMap<String, common.Lazy>();\n" ++
"\tpublic static final java.util.Map<Object, java.util.Map<String, common.Lazy>> inheritedAttributes = new java.util.HashMap<Object, java.util.Map<String, common.Lazy>>();\n\n" ++	


"\tstatic{\n" ++
makeStaticDcls(className, namedSig.inputElements) ++
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
"\tpublic " ++ namedSig.outputElement.typerep.transType ++ " doReturn(){\n" ++			
"\t\t" ++

      (if null(ffidefs.ffiTranslationString)
       then "return (" ++ namedSig.outputElement.typerep.transType ++ ")super.doReturn()"
       else "common.DecoratedNode context = this.decorate(); return (" ++ namedSig.outputElement.typerep.transType ++ ")" ++ computeSigTranslation(head(ffidefs.ffiTranslationString), namedSig))
      ++
      
      ";\n" ++
"\t}\n" ++ 
"}\n"
		]];

  top.errors <- if length(ffidefs.ffiTranslationString) > 1
                then [err(ffidefs.location, "There is more than one Java translation in this FFI function")]
                else [];
}
