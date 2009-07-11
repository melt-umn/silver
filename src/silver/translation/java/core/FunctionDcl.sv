grammar silver:translation:java:core;
import silver:translation:java:env;
import silver:definition:core;
import silver:definition:env;

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody{

  local attribute className :: String;
  className = "P" ++ id.name;

  local attribute sigNames :: [String];
  sigNames = getFullNamesSignature(namedSig.inputElements);

  top.setupInh = body.setupInh;
  top.initProd = "		//FUNCTION " ++ id.name ++ " " ++ ns.pp ++ "\n" ++ body.translation;
  top.initAspect = "";

  top.javaClasses = [[className,
		
"package " ++ makeName(top.grammarName) ++ ";\n\n" ++

"public class " ++ className ++ " extends common.FunctionNode{\n\n" ++	

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
"	}\n\n" ++

"	public " ++ ns.outputElement.typerep.transType ++ " doReturn(){\n" ++			
"		return (" ++ ns.outputElement.typerep.transType ++ ")super.doReturn();\n" ++
"	}\n" ++ 
"}\n"
		]];
}
