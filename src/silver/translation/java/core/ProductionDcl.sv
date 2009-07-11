grammar silver:translation:java:core;
import silver:definition:core;
import silver:definition:env;

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody{

  local attribute className :: String;
  className = "P" ++ id.name;

  local attribute sigNames :: [String];
  sigNames = getFullNamesSignature(namedSig.inputElements);

  top.setupInh = body.setupInh;
  top.initProd = "		//PRODUCTION " ++ id.name ++ " " ++ ns.pp ++ "\n" ++ body.translation;
  top.initAspect = "";

  top.javaClasses = [[className,
		
"package " ++ makeName(top.grammarName) ++ ";\n\n" ++

"public class " ++ className ++ " extends common.Node{\n\n" ++	

makeIndexDcls(0, sigNames) ++ "\n" ++
"	public static common.Lazy forward;\n" ++
"	public static java.util.Map<String, common.Lazy> forwardAttributes = new java.util.HashMap<String, common.Lazy>();\n\n" ++

"	public static java.util.Map<String, common.Lazy> localAttributes = new java.util.HashMap<String, common.Lazy>();\n" ++
"	public static java.util.Map<String, common.Lazy> synthesizedAttributes = new java.util.HashMap<String, common.Lazy>();\n" ++
"	public static java.util.Map<Object, java.util.Map<String, common.Lazy>> inheritedAttributes = new java.util.HashMap<Object, java.util.Map<String, common.Lazy>>();\n\n" ++	


"	static{\n" ++
makeStaticDcls(className, sigNames) ++
"	}\n\n" ++ 
	
"	public " ++ className ++ "(" ++ makeConstructor(sigNames) ++ "){\n" ++
"		super(\"" ++ fName ++ "\", " ++ toString(length(namedSig.inputElements)) ++ ", " ++
                      className ++ ".inheritedAttributes, " ++ 
                      className ++ ".synthesizedAttributes, " ++ 
                      className ++ ".localAttributes);\n\n" ++
makeChildAssign(sigNames) ++ "\n" ++

"		this.forward(" ++ className ++ ".forward, " ++ className ++ ".forwardAttributes);\n" ++
"	}\n" ++
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
