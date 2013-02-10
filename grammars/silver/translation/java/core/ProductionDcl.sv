grammar silver:translation:java:core;

import silver:util;

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  local attribute className :: String;
  className = "P" ++ id.name;

  local attribute sigNames :: [String];
  sigNames = namedSig.inputNames;

  top.setupInh := body.setupInh;
  top.initProd := "\t\t" ++ makeName(top.grammarName) ++ "." ++ className ++ ".initProductionAttributeDefinitions();\n";
  top.postInit := "\t\tcommon.Decorator.applyDecorators(" ++ fnnt ++ ".decorators, " ++ className ++ ".class);\n";

  top.initWeaving := "\tpublic static int " ++ localVar ++ " = 0;\n";
  top.valueWeaving := body.valueWeaving;

  local attribute localVar :: String;
  localVar = "count_local__ON__" ++ makeIdName(fName);

  local attribute fnnt :: String;
  fnnt = makeNTClassName(namedSig.outputElement.typerep.typeName);

  top.genFiles := [pair(className ++ ".java",
		
"package " ++ makeName(top.grammarName) ++ ";\n\n" ++

"// " ++ ns.pp ++ "\n" ++
"public final class " ++ className ++ " extends " ++ fnnt ++ " {\n\n" ++

makeIndexDcls(0, sigNames) ++ "\n" ++
"\tpublic static final Class<?> childTypes[] = {" ++ makeChildTypesList(namedSig.inputElements) ++ "};\n\n" ++

"\tpublic static final int num_local_attrs = Init." ++ localVar ++ ";\n" ++
"\tpublic static final String[] occurs_local = new String[num_local_attrs];\n\n" ++

"\tpublic static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[" ++ fnnt ++ ".num_inh_attrs];\n\n" ++

"\tpublic static final common.Lazy[] synthesizedAttributes = new common.Lazy[" ++ fnnt ++ ".num_syn_attrs];\n" ++
"\tpublic static final common.Lazy[][] childInheritedAttributes = new common.Lazy[" ++ toString(length(sigNames)) ++ "][];\n\n" ++	

"\tpublic static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];\n" ++
"\tpublic static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];\n\n" ++

"\tstatic{\n" ++
makeStaticDcls(className, namedSig.inputElements) ++
"\t}\n\n" ++ 

"\tpublic " ++ className ++ "(" ++ implode(", ", map(makeConstructorDcl, sigNames) ++ map(makeConstructorAnnoDcl, namedSig.namedInputNames)) ++ ") {\n" ++
"\t\tthis(new Object[]{" ++ implode(", ", map(makeConstructorAccess, sigNames)) ++ "}" ++
  (if null(namedSig.namedInputNames)
  then ");\n"
  else ", " ++ implode(", ", map(makeConstructorAnnoAccess, namedSig.namedInputNames)) ++ ");\n") ++
"\t}\n\n" ++

"\tpublic " ++ className ++ "(" ++ implode(", ", "final Object[] children" :: map(makeConstructorAnnoDcl, namedSig.namedInputNames)) ++ ") {\n" ++
"\t\tsuper(" ++ implode(", ", "children" :: map(makeConstructorAnnoAccess, namedSig.namedInputNames)) ++ ");\n" ++
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
"\tpublic boolean hasForward() {\n" ++
"\t\treturn " ++ (if null(body.uniqueSignificantExpression) then "false" else "true") ++ ";\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic common.Node evalForward(final common.DecoratedNode context) {\n" ++
"\t\t" ++ (if null(body.uniqueSignificantExpression) 
           then "throw new common.exceptions.SilverInternalError(\"Production " ++ fName ++ " erroneously claimed to forward\");\n"
           else "return " ++ head(body.uniqueSignificantExpression).translation ++ ";\n") ++
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
"\t\tpublic " ++ className ++ " invoke(final Object[] children, final Object[] annotations) {\n" ++
"\t\t\treturn new " ++ className ++ "(children" ++ unpackAnnotations(0, namedSig.namedInputNames) ++ ");\n" ++
"\t\t}\n\n" ++
"\t};\n" ++

"}\n")];

  -- main function signature check TODO: this should probably be elsewhere!
  top.errors <-
        if id.name == "main"
        then [err(top.location, "main should be a function!")]
        else [];
}

function makeIndexDcls
String ::= i::Integer s::[String]
{
  return if null(s) then "" else "\tpublic static final int i_" ++ head(s) ++ " = " ++ toString(i) ++ ";\n"  ++ makeIndexDcls(i+1, tail(s));
}

function makeStaticDcls
String ::= className::String s::[NamedSignatureElement]
{
  return if null(s) 
	 then "" 
	 else (if head(s).typerep.isDecorable then
	      "\tchildInheritedAttributes[i_" ++ head(s).elementName ++ "] = " ++ 
                                                            "new common.Lazy[" ++ makeNTClassName(head(s).typerep.typeName) ++ ".num_inh_attrs];\n"
               else "") ++ makeStaticDcls(className, tail(s));
}

function makeConstructorDcl
String ::= s::String
{ return "final Object c_" ++ s; }
function makeConstructorAccess
String ::= s::String
{ return "c_" ++ s; 
}
function makeConstructorAnnoDcl
String ::= s::String
{ return "final Object a_" ++ s; }
function makeConstructorAnnoAccess
String ::= s::String
{ return "a_" ++ s; 
}
function unpackAnnotations
String ::= i::Integer  ns::[String]
{
  return if null(ns) then ""
  else ", annotations[" ++ toString(i) ++ "]" ++ unpackAnnotations(i + 1, tail(ns));
}


-- meant to turn  ::= Foo String Bar
-- into {grammar.NFoo.class, String.class, other.NBar.class}
function makeChildTypesList
String ::= ns::[NamedSignatureElement]
{
  return if null(ns)
         then ""
         else head(ns).typerep.transClassType ++ ".class"
              ++ if null(tail(ns))
                 then ""
                 else ", " ++ makeChildTypesList(tail(ns));
}

