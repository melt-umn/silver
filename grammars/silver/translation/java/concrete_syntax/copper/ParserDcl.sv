grammar silver:translation:java:concrete_syntax:copper;
import silver:translation:java:core;
import silver:translation:java:env;
import silver:definition:concrete_syntax;
import silver:definition:core;
import silver:definition:env;

attribute javaClasses, initProd occurs on ParserDcl;
attribute disambiguationGroupDcls occurs on ParserDcl, ModuleList, ModuleName, Module, ModuleExportedDefs;
attribute parserAttrDcls occurs on ParserDcl, ModuleList, ModuleName, Module, ModuleExportedDefs;

aspect production parserDcl
top::AGDcl ::= p::ParserDcl
{
  top.disambiguationGroupDcls = [];
  top.parserAttrDcls = [];
  top.javaClasses = p.javaClasses;
  top.setupInh := "";
  top.initProd := p.initProd;
  top.initAspect := "";
  top.postInit := "";
}

aspect production parserStmt
top::ParserDcl ::= 'parser' n::Name '::' t::Type '{' m::ModuleList '}'
{
  top.disambiguationGroupDcls = m.disambiguationGroupDcls;
  top.parserAttrDcls = m.parserAttrDcls;
}

aspect production moduleListOne
top::ModuleList ::= c1::ModuleName ';'
{
  top.disambiguationGroupDcls = c1.disambiguationGroupDcls;
  top.parserAttrDcls = c1.parserAttrDcls;
}

aspect production moduleListCons
top::ModuleList ::= c1::ModuleName ';' c2::ModuleList
{
  top.disambiguationGroupDcls = c1.disambiguationGroupDcls ++ c2.disambiguationGroupDcls;
  top.parserAttrDcls = c1.parserAttrDcls ++ c2.parserAttrDcls;
}

aspect production moduleName
top::ModuleName ::= pkg::QName
{
  top.disambiguationGroupDcls = m.disambiguationGroupDcls;
  top.parserAttrDcls = m.parserAttrDcls;
}

aspect production module 
top::Module ::= c::[Decorated RootSpec] g::Decorated QName a::String o::[String] h::[String] w::[EnvMap]
{
  top.disambiguationGroupDcls = med.disambiguationGroupDcls;		  
  top.parserAttrDcls = med.parserAttrDcls;		  
}

aspect production moduleExportedDefs
top::ModuleExportedDefs ::= compiled::[Decorated RootSpec] need::[String] seen::[String]
{
  top.disambiguationGroupDcls = if null(need) || null(rs) then [] else (head(rs).disambiguationGroupDcls ++ recurse.disambiguationGroupDcls);
  top.parserAttrDcls = if null(need) || null(rs) then [] else (head(rs).parserAttrDcls ++ recurse.parserAttrDcls);
}

aspect production parserStmt
top::ParserDcl ::= 'parser' n::Name '::' t::Type '{' m::ModuleList '}' {

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

  top.initProd :=
	"\t\t" ++ fullClassName ++ ".synthesizedAttributes.put(\"__return\", new common.Lazy(){\n" ++ 
	"\t\t\tpublic Object eval(common.DecoratedNode context) {\n" ++
	"\t\t\t\ttry{\n" ++
	"\t\t\t\t\treturn new " ++ packageName ++ "." ++ parserName ++ "().parse(new java.io.StringReader(((common.StringCatter)context.child(" ++ fullClassName ++ ".i_c)).toString()), \"_NULL_\");\n" ++
	"\t\t\t\t}catch(edu.umn.cs.melt.copper.runtime.logging.CopperParserException e){ if(\"1\".equals(System.getenv(\"SILVERTRACE\"))) { throw new RuntimeException(\"An error occured while parsing.\", e); } System.err.println(\"Parse error encountered. (Set SILVERTRACE=1 to unsuppress stack trace.)\\n\" + e.getMessage()); System.exit(-1); return null; }\n" ++
	"\t\t\t\tcatch(Exception e){throw new RuntimeException(\"An error occured while parsing.\", e);}\n" ++
	"\t\t\t}\n" ++
	"\t\t});\n";


  top.javaClasses = [[className,
"package " ++ packageName ++ ";\n\n" ++

"public class " ++ className ++ " extends common.FunctionNode{\n\n" ++	

makeIndexDcls(0, sigNames) ++ "\n" ++
"\tpublic static final Class<?> childTypes[] = {common.StringCatter.class};\n\n" ++

"\tpublic static final java.util.Map<String, common.Lazy> synthesizedAttributes = new java.util.TreeMap<String, common.Lazy>();\n" ++

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
"\t\tthrow new RuntimeException(\"Parsers do not have children\");\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic common.Lazy getForward() {\n" ++
"\t\tthrow new RuntimeException(\"Parsers do not forward\");\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic common.Lazy getForwardInh(String name) {\n" ++
"\t\tthrow new RuntimeException(\"Parsers do not forward\");\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic common.Lazy getLocal(String name) {\n" ++
"\t\tthrow new RuntimeException(\"Parsers do not have locals\");\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic String getName() {\n" ++
"\t\treturn \"" ++ top.fullName ++ "\";\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic " ++ t.typerep.transType ++ " doReturn(){\n" ++			
"\t\treturn (" ++ t.typerep.transType ++ ")super.doReturn();\n" ++
"\t}\n" ++ 
"}\n"
]];

}
