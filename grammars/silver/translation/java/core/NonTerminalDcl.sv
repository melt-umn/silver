grammar silver:translation:java:core;

aspect production nonterminalDcl
top::AGDcl ::= cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeList ';'
{
  local className :: String = "N" ++ id.name;
  
  local inhVar :: String = "count_inh__ON__" ++ id.name;
  local synVar :: String = "count_syn__ON__" ++ id.name;
  
  local myAnnos :: [NamedSignatureElement] =
    annotationsForNonterminal(nonterminalTypeExp(fName, tl.types), top.env);
  
  top.initWeaving := "\tpublic static int " ++ inhVar ++ " = 0;\n"
                  ++ "\tpublic static int " ++ synVar ++ " = 0;\n";
  
  top.genFiles := [pair(className ++ ".java",
		
"package " ++ makeName(top.grammarName) ++ ";\n\n" ++

"import java.util.*;\n\n" ++

"public abstract class " ++ className ++ " extends common.Node {\n\n" ++

"\tpublic static final int num_inh_attrs = Init." ++ inhVar ++ ";\n" ++
"\tpublic static final int num_syn_attrs = Init." ++ synVar ++ ";\n\n" ++

"\tpublic static final String[] occurs_inh = new String[num_inh_attrs];\n" ++
"\tpublic static final String[] occurs_syn = new String[num_syn_attrs];\n" ++
"\tpublic static final LinkedList<common.Decorator> decorators = new LinkedList<common.Decorator>();\n\n" ++

"\tpublic static final common.Lazy[] defaultSynthesizedAttributes = new common.Lazy[num_syn_attrs];\n\n" ++

"\tprotected " ++ className ++ "(" ++ implode(", ", map((.annoSigElem), myAnnos)) ++ ") {\n" ++
implode("", map(makeAnnoAssign, myAnnos)) ++
"\t}\n\n" ++

implode("", map((.annoDeclElem), myAnnos)) ++ "\n" ++

"\t@Override\n" ++
"\tpublic final int getNumberOfInhAttrs() {\n" ++
"\t\treturn num_inh_attrs;\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic final int getNumberOfSynAttrs() {\n" ++
"\t\treturn num_syn_attrs;\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic final common.Lazy getDefaultSynthesized(final int index) {\n" ++
"\t\treturn defaultSynthesizedAttributes[index];\n" ++
"\t}\n\n" ++

"\t@Override\n" ++
"\tpublic final String getNameOfInhAttr(final int index) {\n" ++
"\t\treturn occurs_inh[index];\n" ++
"\t}\n\n" ++
	
"\t@Override\n" ++
"\tpublic final String getNameOfSynAttr(final int index) {\n" ++
"\t\treturn occurs_syn[index];\n" ++
"\t}\n\n" ++

"}\n")];

}

