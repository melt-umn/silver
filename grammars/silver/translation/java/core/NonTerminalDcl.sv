grammar silver:translation:java:core;

aspect production defaultNonterminalDcl
top::AGDcl ::= id::Name tl::TypeList
{
  local attribute className :: String;
  className = "N" ++ id.name;
  
  top.javaClasses = [[className,
		
"package " ++ makeName(top.grammarName) ++ ";\n\n" ++

"import java.util.*;\n\n" ++

"public abstract class " ++ className ++ " extends common.Node {\n\n" ++
"\tpublic static final HashSet<String> occurs = new HashSet<String>();\n" ++
"\tpublic static final LinkedList<common.Decorator> decorators = new LinkedList<common.Decorator>();\n\n" ++

"\tprotected " ++ className ++ "(Object[] children) {\n" ++
"\t\tsuper(children);\n" ++
"\t}\n" ++	
"}\n"
		]];

}

