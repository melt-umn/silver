grammar silver:modification:autocopyattr:java;
import silver:modification:autocopyattr;

import silver:definition:core;
import silver:definition:env;

import silver:translation:java:core;
import silver:translation:java:env;

import silver:util;


aspect production autocopyDcl
top::AGDcl ::= 'autocopy' 'attribute' a::Name '::' t::Type ';'
{
  local attribute className :: String;
  className = "D" ++ a.name;

  top.javaClasses = [[className,
		
"package " ++ makeName(top.grammarName) ++ ";\n\n" ++

"import java.util.*;\n\n" ++

"public class " ++ className ++ " extends common.Decorator {\n\n" ++

"public static final " ++ className ++ " singleton = new " ++ className ++ "();\n\n" ++

"\tpublic void decorate(Class production) {\n" ++
"\t\tdecorateAutoCopy(production, \"" ++ fName ++ "\");\n" ++
"\t}\n" ++
"}\n"
		]];
		
  top.setupInh := "";
  top.initProd := "";
  top.initAspect := "";
  top.postInit := "";
}

aspect production attributionDcl
top::AGDcl ::= 'attribute' a::QName 'occurs' 'on' nt::QName ';'
{
 
  top.initAspect <- if a.lookupAttribute.typerep.isAutoCopy
                    then "\t\t" ++ makeNTClassName(nt.lookupValue.fullName) ++ ".decorators.add(" ++ makeDecoratorClassName(a.lookupAttribute.fullName) ++ ".singleton);\n"
                    else "";

}

function makeDecoratorClassName
String ::= s::String {
  return makeClassNameHelp(split(":", s), "D");
}
