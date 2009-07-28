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

"public abstract class " ++ className ++ " extends common.Node {\n\n" ++
"\tpublic static final HashSet<String> occurs = new HashSet<String>();\n" ++
"\tpublic static final LinkedList<common.Decorator> decorators = new LinkedList<common.Decorator>();\n\n" ++
	
"}\n"
		]];

}

