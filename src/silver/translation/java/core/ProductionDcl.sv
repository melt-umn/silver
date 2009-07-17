grammar silver:translation:java:core;
import silver:definition:core;
import silver:definition:env;

import silver:translation:java:env;

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody{

  local attribute className :: String;
  className = "P" ++ id.name;

  local attribute sigNames :: [String];
  sigNames = getFullNamesSignature(namedSig.inputElements);

  top.setupInh := body.setupInh;
  top.initProd := "\t\t//PRODUCTION " ++ id.name ++ " " ++ ns.pp ++ "\n" ++ body.translation;
  top.initAspect := "";
  top.postInit := "\t\tcommon.Decorator.applyDecorators(" ++ fn ++ ".decorators, " ++ className ++ ".class);\n";

  local attribute fns :: [Decorated EnvItem];
  fns = getFullNameDcl(ns.outputElement.typerep.typeName, top.env);
  local attribute fn :: String;
  fn = makeNTClassName(if null(fns)
                       then ns.outputElement.typerep.typeName
                       else head(fns).fullName);

  top.javaClasses = [[className,
		
"package " ++ makeName(top.grammarName) ++ ";\n\n" ++

"public class " ++ className ++ " extends " ++ fn ++ " {\n\n" ++	

makeIndexDcls(0, sigNames) ++ "\n" ++
"\tpublic static Class childTypes[] = {" ++ makeChildTypesList(ns.inputElements, top.env) ++ "};\n\n" ++
"\tpublic static common.Lazy forward;\n" ++
"\tpublic static java.util.Map<String, common.Lazy> forwardAttributes = new java.util.HashMap<String, common.Lazy>();\n\n" ++

"\tpublic static java.util.Map<String, common.Lazy> localAttributes = new java.util.HashMap<String, common.Lazy>();\n" ++
"\tpublic static java.util.Map<String, common.Lazy> synthesizedAttributes = new java.util.HashMap<String, common.Lazy>();\n" ++
"\tpublic static java.util.Map<Object, java.util.Map<String, common.Lazy>> inheritedAttributes = new java.util.HashMap<Object, java.util.Map<String, common.Lazy>>();\n\n" ++	


"\tstatic{\n" ++
makeStaticDcls(className, sigNames) ++
"\t}\n\n" ++ 
	
"\tpublic " ++ className ++ "(" ++ makeConstructor(sigNames) ++ "){\n" ++
"\t\tsuper(\"" ++ fName ++ "\", " ++ toString(length(namedSig.inputElements)) ++ ", " ++
                      className ++ ".inheritedAttributes, " ++ 
                      className ++ ".synthesizedAttributes, " ++ 
                      className ++ ".localAttributes);\n\n" ++
makeChildAssign(sigNames) ++ "\n" ++

"\t\tthis.forward(" ++ className ++ ".forward, " ++ className ++ ".forwardAttributes);\n" ++
"\t}\n" ++
"}\n"
		]];
}

function makeIndexDcls
String ::= i::Integer s::[String]{
  return if null(s) then "" else "\tpublic static final int i_" ++ head(s) ++ " = " ++ toString(i) ++ ";\n"  ++ makeIndexDcls(i+1, tail(s));
}

function makeStaticDcls
String ::= className::String s::[String]{
  return if null(s) 
	 then "" 
	 else "\t" ++ className ++ ".inheritedAttributes.put(i_" ++ head(s) ++ ", " ++ 
							   "new java.util.HashMap<String, common.Lazy>());\n"  ++ makeStaticDcls(className, tail(s));
}

function makeConstructor
String ::= s::[String]{
  return if null(s) then "" else "Object c_" ++ head(s) ++ (if null(tail(s)) then "" else (", " ++ makeConstructor(tail(s))));
}

function makeChildAssign
String ::= s::[String]{
  return if null(s) then "" else "\t\tthis.children[i_" ++ head(s) ++ "] = c_" ++ head(s) ++ ";\n"  ++ makeChildAssign(tail(s));
}

-- meant to turn  ::= Foo String Bar
-- into {grammar.NFoo.class, String.class, other.NBar.class}
-- TODO: this might be broken for decorated types? also function/production types.
-- by broken I mean it won't reveal any useful type information (java.lang.Constructor.class)
-- Also, it preserves no information about _what_ terminal!
function makeChildTypesList
String ::= ns::[Decorated NamedSignatureElement] e::Decorated Env {

  -- I don't see a shorter way to get its fully qualified name?
  -- note: uses laziness. won't be evaluated if ns is null
  local attribute fns :: [Decorated EnvItem];
  fns = getFullNameDcl(head(ns).typerep.typeName, e);
  local attribute fn :: String;
  fn = if null(fns) then head(ns).typerep.typeName else head(fns).fullName;
  
  return if null(ns)
         then ""
         else (if head(ns).typerep.isNonTerminal
               then makeNTClassName(fn)
               else head(ns).typerep.transType)
              ++ ".class"
              ++ if null(tail(ns))
                 then ""
                 else ", " ++ makeChildTypesList(tail(ns), e);
}

