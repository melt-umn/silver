grammar silver:translation:java:core;

aspect production annotationDcl
top::AGDcl ::= 'annotation' a::Name tl::BracketedOptTypeList '::' te::Type ';'
{
  local className :: String = "A" ++ a.name;

-- We report the trans type, despite the fact that the attribute may be parameterized!
-- It should be fine, though. If we're a tv, then it's 'Object' and anything
-- else will be a subtype...

  top.genFiles := [pair(className ++ ".java",

"package " ++ makeName(top.grammarName) ++ ";\n\n" ++

"public interface " ++ className ++ " {\n\n" ++

"\tpublic " ++ te.typerep.transType ++ " getAnno_" ++ makeIdName(fName) ++ "();\n\n" ++

"}\n")];
}

