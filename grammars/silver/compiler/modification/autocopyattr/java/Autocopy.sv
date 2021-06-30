grammar silver:compiler:modification:autocopyattr:java;
import silver:compiler:modification:autocopyattr;

import silver:compiler:definition:core;
import silver:compiler:definition:env;
import silver:compiler:definition:type:syntax;
import silver:compiler:definition:type;

import silver:compiler:translation:java:core;
import silver:compiler:translation:java:type;



aspect production attributeDclAuto
top::AGDcl ::= 'autocopy' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  local attribute className :: String;
  className = "D" ++ a.name;

  top.genFiles := [pair(className ++ ".java",
		
"package " ++ makeName(top.grammarName) ++ ";\n\n" ++

"import java.util.*;\n\n" ++

"public class " ++ className ++ " extends common.Decorator {\n\n" ++

"public static final " ++ className ++ " singleton = new " ++ className ++ "();\n\n" ++

"\tpublic void decorate(common.Prodleton production) {\n" ++
"\t\tdecorateAutoCopy(production, \"" ++ fName ++ "\");\n" ++
"\t}\n" ++
"}\n")];
}

aspect production attributionDcl
top::AGDcl ::= 'attribute' at::QName attl::BracketedOptTypeExprs 'occurs' 'on' nt::QName nttl::BracketedOptTypeExprs ';'
{
  top.setupInh <- 
    if at.lookupAttribute.dcl.isAutocopy then
      "\t\t" ++ makeNTName(nt.lookupType.fullName) ++ ".decorators.add(" ++ makeDecoratorClassName(at.lookupAttribute.fullName) ++ ".singleton);\n"
    else "";
}

function makeDecoratorClassName
String ::= s::String
{
  return substituteLast(".", ".D", makeName(s));
}

