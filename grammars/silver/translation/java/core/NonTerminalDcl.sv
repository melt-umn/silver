grammar silver:translation:java:core;

aspect production defaultNonterminalDcl
top::AGDcl ::= id::Name tl::TypeList
{
  local attribute className :: String;
  className = "N" ++ id.name;
  
  local attribute inhVar :: String;
  inhVar = "count_inh__ON__" ++ id.name;
  local attribute synVar :: String;
  synVar = "count_syn__ON__" ++ id.name;
  
  top.initWeaving := "\tpublic static int " ++ inhVar ++ " = 0;\n"
                  ++ "\tpublic static int " ++ synVar ++ " = 0;\n";
  
  top.javaClasses = [[className,
		
"package " ++ makeName(top.grammarName) ++ ";\n\n" ++

"import java.util.*;\n\n" ++

"public abstract class " ++ className ++ " extends common.Node {\n\n" ++

"\tpublic static final int num_inh_attrs = Init." ++ inhVar ++ ";\n" ++
"\tpublic static final int num_syn_attrs = Init." ++ synVar ++ ";\n\n" ++

"\tpublic static final HashSet<String> occurs = new HashSet<String>();\n" ++
"\tpublic static final LinkedList<common.Decorator> decorators = new LinkedList<common.Decorator>();\n\n" ++

"\tprotected " ++ className ++ "(Object[] children) {\n" ++
"\t\tsuper(children);\n" ++
"\t}\n" ++	
"}\n"
		]];

}

