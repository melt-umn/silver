grammar silver:translation:java:concrete_syntax:copper;
import silver:translation:java:core;
import silver:translation:java:env;
import silver:definition:concrete_syntax;
import silver:definition:core;
import silver:definition:env;

attribute javaClasses, initProd occurs on ParserDcl;
attribute disambiguationGroupDcls occurs on ParserDcl, ModuleStmtList, ModuleStmt, Module;


aspect production parserDcl
top::AGDcl ::= p::ParserDcl{
  top.disambiguationGroupDcls = [];
  top.javaClasses = p.javaClasses;
  top.setupInh = "";
  top.initProd = p.initProd;
  top.initAspect = "";
}

aspect production parserStmt
top::ParserDcl ::= 'parser' n::Name '::' t::Type '{' m::ModuleStmtList '}' {
  top.disambiguationGroupDcls = m.disambiguationGroupDcls;
}

aspect production moduleStmtListOne
top::ModuleStmtList ::= c1::ModuleStmt ';'{
  top.disambiguationGroupDcls = c1.disambiguationGroupDcls;
}

aspect production moduleStmtListCons
top::ModuleStmtList ::= c1::ModuleStmt ';' c2::ModuleStmtList {
  top.disambiguationGroupDcls = c1.disambiguationGroupDcls ++ c2.disambiguationGroupDcls;
}

aspect production moduleAll
top::ModuleStmt ::= pkg::QName
{
  top.disambiguationGroupDcls = m.disambiguationGroupDcls;
}

aspect production module 
top::Module ::= c::[Decorated RootSpec] g::Decorated QName a::String o::[String] h::[String] w::[EnvMap] {
  top.disambiguationGroupDcls = if null(mitem) 
	     then []
	     else head(mitem).disambiguationGroupDcls;		  
}

aspect production parserStmt
top::ParserDcl ::= 'parser' n::Name '::' t::Type '{' m::ModuleStmtList '}' {

  local attribute className :: String;
  className = "P" ++ n.name;

  local attribute packageName :: String;
  packageName = makeName(top.grammarName);

  local attribute fullClassName :: String;
  fullClassName = packageName ++ "." ++ className;

  local attribute sigNames :: [String];
  sigNames = ["c"];

  local attribute parserName :: String;
  parserName = makeParserName(top.fullName);

  top.initProd =
	"		" ++ fullClassName ++ ".synthesizedAttributes.put(\"__return\", new common.Lazy(){\n" ++ 
	"			public Object eval(common.DecoratedNode context) {\n" ++
	"				try{\n" ++
	"					return new " ++ packageName ++ "." ++ parserName ++ "().parse(new java.io.StringReader(((StringBuffer)context.child(" ++ fullClassName ++ ".i_c)).toString()), \"_NULL_\");\n" ++
	"				}catch(java.lang.Exception e){System.out.println(\"Silver caught a copper parse error:\\n\\t\" + e.getMessage());System.exit(1);}\n" ++
	"				return null;\n" ++
	"			}\n" ++
	"		});\n";


  top.javaClasses = [[className,
"package " ++ packageName ++ ";\n\n" ++

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
"		super(\"" ++ top.fullName ++ "\", 1, " ++
                      className ++ ".inheritedAttributes, " ++ 
                      className ++ ".synthesizedAttributes, " ++ 
                      className ++ ".localAttributes);\n\n" ++
makeChildAssign(sigNames) ++ "\n" ++

"		this.forward(" ++ className ++ ".forward, " ++ className ++ ".forwardAttributes);\n" ++
"	}\n\n" ++

"	public " ++ t.typerep.transType ++ " doReturn(){\n" ++			
"		return (" ++ t.typerep.transType ++ ")super.doReturn();\n" ++
"	}\n" ++ 
"}\n"
]];

}
