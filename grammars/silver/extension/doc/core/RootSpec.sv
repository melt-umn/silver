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
  top.genFiles := [pair("index.md", toFile(g.docs))];
}

function toFile
String ::= items::[DocItem]
{
  local docComments::String = extractDocComments(items);
  local header::String = extractHeader(items);
  local splitFiles::String = extractSplitFiles(items);
  return header ++ docComments;
}

function extractSplitFiles
String ::= items::[DocItem]
{
  return case items of
	 | configDocItem(c) :: rest -> c.splitFiles
	 | _ :: rest -> extractSplitFiles(rest)
	 | _ -> ""
	 end;
}

function extractHeader
String ::= items::[DocItem]
{
  return case items of
	 | configDocItem(c) :: rest -> c.header
	 | _ :: rest -> extractHeader(rest)
	 | _ -> ""
	 end;
}

function extractDocComments
String ::= items::[DocItem]
{
  return case items of
	 | commentDocItem(c) :: rest -> "\n\n#### _" ++ c.modifiers ++ "_ `" ++ c.dclName ++ "`" ++ c.signature ++ "\n" ++ "> " ++ c.body ++ extractDocComments(rest)
	 | _ :: rest -> extractDocComments(rest)
	 | [] -> ""
	 end;
}
