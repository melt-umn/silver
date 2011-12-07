grammar silver:modification:copper;

aspect production parserDcl
top::AGDcl ::= 'parser' n::Name '::' t::Type '{' m::ModuleList '}'
{
  local attribute className :: String;
  className = "P" ++ n.name;

  local attribute packageName :: String;
  packageName = makeName(top.grammarName);

  local attribute parserName :: String;
  parserName = makeParserName(fName);

  top.initWeaving := "\tpublic static int " ++ localVar ++ " = 0;\n";
  top.valueWeaving := "";

  local attribute localVar :: String;
  localVar = "count_local__ON__" ++ makeIdName(fName);

  top.javaClasses = [[className,
                      generateFunctionClassString(top.grammarName, n.name, namedSig, parseResult)
                    ]];
  
  local attribute parseResult :: String;
  parseResult = 
   "try {\n" ++
"\t\t\treturn new core.PparseSucceeded( new " ++ packageName ++ "." ++ parserName ++ "().parse(new java.io.StringReader(((common.StringCatter)common.Util.demand(args[0])).toString()), ((common.StringCatter)common.Util.demand(args[1])).toString()) );\n" ++
"\t\t} catch(edu.umn.cs.melt.copper.runtime.logging.CopperParserException e) {\n" ++
"\t\t\treturn new core.PparseFailed( new common.StringCatter(e.getMessage()) );\n" ++
"\t\t} catch(Throwable t) {\n" ++
"\t\t\tthrow new common.exceptions.TraceException(\"An error occured while parsing\", t);\n" ++
"\t\t}\n";
}

