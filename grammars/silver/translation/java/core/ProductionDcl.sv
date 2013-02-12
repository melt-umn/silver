grammar silver:translation:java:core;

import silver:util;

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  local className :: String = "P" ++ id.name;
  local sigNames :: [String] = namedSig.inputNames;

  top.setupInh := body.setupInh;
  top.initProd := "\t\t" ++ makeName(top.grammarName) ++ "." ++ className ++ ".initProductionAttributeDefinitions();\n";
  top.postInit := "\t\tcommon.Decorator.applyDecorators(" ++ fnnt ++ ".decorators, " ++ className ++ ".class);\n";

  top.initWeaving := "\tpublic static int " ++ localVar ++ " = 0;\n";
  top.valueWeaving := body.valueWeaving;

  local localVar :: String = "count_local__ON__" ++ makeIdName(fName);
  local fnnt :: String = makeNTClassName(namedSig.outputElement.typerep.typeName);

  top.genFiles := [pair(className ++ ".java",
		
"package " ++ makeName(top.grammarName) ++ ";\n\n" ++

"// " ++ ns.pp ++ "\n" ++
"public final class " ++ className ++ " extends " ++ fnnt ++ " {\n\n" ++

makeIndexDcls(0, sigNames) ++ "\n\n" ++

"\tpublic static final Class<?> childTypes[] = {" ++ implode(",", map(makeChildTypes, namedSig.inputElements)) ++ "};\n\n" ++

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

implode("", map(makeChildDcl, namedSig.inputElements)) ++ "\n" ++

"\tpublic " ++ className ++ "(" ++ 
  implode(", ", map(makeConstructorDcl, sigNames) ++ map(makeConstructorAnnoDcl, namedSig.namedInputNames)) ++ ") {\n" ++
"\t\tsuper(" ++ implode(", ", map(makeConstructorAnnoAccess, namedSig.namedInputNames)) ++ ");\n" ++
implode("", map(makeChildAssign, namedSig.inputElements)) ++
"\t}\n\n" ++

implode("", map(makeChildAccessor, namedSig.inputElements)) ++

"\t@Override\n" ++
"\tpublic Object getChild(final int index) {\n" ++
"\t\tswitch(index) {\n" ++
implode("", map(makeChildAccessCase, namedSig.inputElements)) ++
"\t\t\tdefault: return null;\n" ++ -- TODO: maybe handle this better?
"\t\t}\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic Object getChildLazy(final int index) {\n" ++
"\t\tswitch(index) {\n" ++
implode("", map(makeChildAccessCaseLazy, namedSig.inputElements)) ++
"\t\t\tdefault: return null;\n" ++ -- TODO: maybe handle this better?
"\t\t}\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic final int getNumberOfChildren() {\n" ++
"\t\treturn " ++ toString(length(namedSig.inputElements)) ++ ";\n" ++
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
"\t\t\treturn new " ++ className ++ "(" ++ implode(", ", unpackChildren(0, sigNames) ++ unpackAnnotations(0, namedSig.namedInputNames)) ++ ");\n" ++
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
  return if null(s) then "" 
  else (if head(s).typerep.isDecorable
    then "\tchildInheritedAttributes[i_" ++ head(s).elementName ++ "] = " ++ 
           "new common.Lazy[" ++ makeNTClassName(head(s).typerep.typeName) ++ ".num_inh_attrs];\n"
    else "") ++ makeStaticDcls(className, tail(s));
}

function makeConstructorDcl
String ::= s::String
{ return "final Object c_" ++ s;
}
function makeConstructorAccess
String ::= s::String
{ return "c_" ++ s; 
}
function makeConstructorAnnoDcl
String ::= s::String
{ return "final Object a_" ++ s;
}
function makeConstructorAnnoAccess
String ::= s::String
{ return "a_" ++ s; 
}
function unpackChildren
[String] ::= i::Integer  ns::[String]
{
  return if null(ns) then []
  else ("children[" ++ toString(i) ++ "]") :: unpackChildren(i + 1, tail(ns));
}
function unpackAnnotations
[String] ::= i::Integer  ns::[String]
{
  return if null(ns) then []
  else ("annotations[" ++ toString(i) ++ "]") :: unpackAnnotations(i + 1, tail(ns));
}
function makeChildTypes
String ::= ns::NamedSignatureElement
{ return ns.typerep.transClassType ++ ".class";
}
function makeChildDcl
String ::= n::NamedSignatureElement
{
  -- non final... gets replaced when eval'd
  return "\tprivate Object /*Thunk or " ++ n.typerep.transType ++ "*/ child_" ++ n.elementName ++ ";\n";
}
function makeChildAccessor
String ::= n::NamedSignatureElement
{
  return
    "\tpublic final Object /*Thunk or " ++ n.typerep.transType ++ "*/ getChild_" ++ n.elementName ++ "() {\n" ++
    "\t\treturn child_" ++ n.elementName ++ " = common.Util.demand(child_" ++ n.elementName ++ ");\n" ++
    "\t}\n\n";
}
function makeChildAccessCase
String ::= n::NamedSignatureElement
{
  return "\t\t\tcase i_" ++ n.elementName ++ ": return getChild_" ++ n.elementName ++ "();\n";
}
function makeChildAccessCaseLazy
String ::= n::NamedSignatureElement
{
  return "\t\t\tcase i_" ++ n.elementName ++ ": return child_" ++ n.elementName ++ ";\n";
}
function makeChildAssign
String ::= n::NamedSignatureElement
{
  return "\t\tthis.child_" ++ n.elementName ++ " = c_" ++ n.elementName ++ ";\n";
}

