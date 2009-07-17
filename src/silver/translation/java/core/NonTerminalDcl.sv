grammar silver:translation:java:core;
import silver:definition:core;

aspect production defaultNonterminalDcl
top::AGDcl ::= id::Name
{
  local attribute className :: String;
  className = "N" ++ id.name;
  
  top.setupInh := "";
  top.initProd := "";
  top.initAspect := "";
  top.postInit := "";
  top.javaClasses = [[className,
		
"package " ++ makeName(top.grammarName) ++ ";\n\n" ++

"import java.util.*;\n\n" ++

"public class " ++ className ++ " extends common.Node {\n\n" ++
"\tpublic static HashSet<String> occurs = new HashSet<String>();\n" ++
"\tpublic static LinkedList<common.Decorator> decorators = new LinkedList<common.Decorator>();\n\n" ++
	
"\tprotected " ++ className ++ "(String name, int children, Map<Object, Map<String, common.Lazy>> inhs, Map<String, common.Lazy> syns, Map<String, common.Lazy> locals) {\n" ++
"\t\tsuper(name, children, inhs, syns, locals);\n" ++
"\t}\n" ++
"}\n"
		]];

}

