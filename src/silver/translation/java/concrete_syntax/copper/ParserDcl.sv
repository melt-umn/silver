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
  top.setupInh := "";
  top.initProd := p.initProd;
  top.initAspect := "";
  top.postInit := "";
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
	"\t\t" ++ fullClassName ++ ".synthesizedAttributes.put(\"__return\", new common.Lazy(){\n" ++ 
	"\t\t\tpublic Object eval(common.DecoratedNode context) {\n" ++
	"\t\t\t\ttry{\n" ++
	"\t\t\t\t\treturn new " ++ packageName ++ "." ++ parserName ++ "().parse(new java.io.StringReader(((common.StringCatter)context.child(" ++ fullClassName ++ ".i_c)).toString()), \"_NULL_\");\n" ++
	"\t\t\t\t}catch(java.lang.Exception e){System.out.println(\"Silver caught a copper parse error:\\n\\t\" + e.getMessage());System.exit(1);}\n" ++
	"\t\t\t\treturn null;\n" ++
	"\t\t\t}\n" ++
	"\t\t});\n";


  top.javaClasses = [[className,
"package " ++ packageName ++ ";\n\n" ++

"public class " ++ className ++ " extends common.FunctionNode{\n\n" ++	

makeIndexDcls(0, sigNames) ++ "\n" ++
"\tpublic static final Class<?> childTypes[] = {common.StringCatter.class};\n\n" ++

"\tpublic static common.Lazy forward;\n" ++
"\tpublic static final java.util.Map<String, common.Lazy> forwardAttributes = new java.util.TreeMap<String, common.Lazy>();\n\n" ++

"\tpublic static final java.util.Map<String, common.Lazy> localAttributes = new java.util.TreeMap<String, common.Lazy>();\n" ++
"\tpublic static final java.util.Map<String, common.Lazy> synthesizedAttributes = new java.util.TreeMap<String, common.Lazy>();\n" ++
"\tpublic static final java.util.Map<Object, java.util.Map<String, common.Lazy>> inheritedAttributes = new java.util.HashMap<Object, java.util.Map<String, common.Lazy>>();\n\n" ++	

"\tstatic{\n" ++
makeStaticDcls(className, sigNames) ++
"\t}\n\n" ++ 
	
"\tpublic " ++ className ++ "(" ++ makeConstructor(sigNames) ++ "){\n" ++
"\t\tsuper(1);\n" ++
makeChildAssign(sigNames) ++ "\n" ++
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
"\t\treturn \"" ++ top.fullName ++ "\";\n" ++
"\t}\n\n" ++

"\tpublic " ++ t.typerep.transType ++ " doReturn(){\n" ++			
"\t\treturn (" ++ t.typerep.transType ++ ")super.doReturn();\n" ++
"\t}\n" ++ 
"}\n"
]];

}
