grammar silver:extension:doc:core;

import silver:driver:util;

attribute genFiles occurs on RootSpec;

aspect production interfaceRootSpec
top::RootSpec ::= _ _
{
  top.genFiles := [];
}

aspect production errorRootSpec
top::RootSpec ::= _ _ _ _
{
  top.genFiles := [];
}

aspect production grammarRootSpec
top::RootSpec ::= g::Grammar  _ _ _
{
  top.genFiles := if g.docsNoDoc 
				  then []
				  else if "true" == g.docsSplit
				  then [] -- TODO: Get doc split to work
				  else [pair("index.md", toFile(g.docs, g.docsHeader))];
}

function toFile
String ::= comments::[DocItem] header::String
{
  local commentMarkdown::String = toMarkdown(comments);
  return header ++ commentMarkdown;
}

function toMarkdown
String ::= items::[DocItem]
{
  return case items of
	 | commentDocItem(c) :: rest -> (let signature :: String = 
										if 0 == length(c.signature)
										then ""
										else "\n ######`" ++ c.signature ++ "`"
									in "\n\n#### _" ++ c.modifiers ++ "_ `" ++ c.dclName ++ "`" ++ signature ++ "\n" ++ "> " ++ c.body ++ toMarkdown(rest)
									end)
	 | _ :: rest -> toMarkdown(rest)
	 | [] -> ""
	 end;
}
