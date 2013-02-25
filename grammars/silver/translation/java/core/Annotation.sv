grammar silver:translation:java:core;

aspect production annotationDcl
top::AGDcl ::= 'annotation' a::Name tl::BracketedOptTypeList '::' te::Type ';'
{
  local className :: String = "A" ++ a.name;

-- We report the type of the method as "Object" to avoid having to deal with
-- parameterized types. The fix would probably be to make nonterminals parameterized,
-- then the interface parameterized likewise. But perhaps Java won't support all
-- the features we'll eventually want...

  top.genFiles := [pair(className ++ ".java",

"package " ++ makeName(top.grammarName) ++ ";\n\n" ++

"public interface " ++ className ++ " {\n\n" ++

"\tpublic Object getAnno_" ++ makeIdName(fName) ++ "();\n\n" ++

"}\n")];
}

