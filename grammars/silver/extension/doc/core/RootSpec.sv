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
				  then toSplitFiles(g.docs, [], g.docsHeader)
				  else [toSingleFile(g.docs, g.docsHeader)];
}

function toSplitFiles
[Pair<String String>] ::= comments::[CommentItem] sortedComments::[Pair<String String>] header::String
{
  return case comments of
	| c :: rest -> toSplitFiles(rest, placeComment(c, sortedComments, header), header)
	| [] -> sortedComments
	end;
}

function placeComment
[Pair<String String>] ::= comment::CommentItem sortedComments::[Pair<String String>] header::String
{
  local markdown::String = toMarkdown(comment);
  return case sortedComments of
	| pair(filename, contents) :: rest -> if filename == toMarkdownExtension(comment.file)
										  then pair(filename, contents ++ markdown) :: rest
										  else pair(filename, contents) :: placeComment(comment, rest, header)
	| [] -> [pair(toMarkdownExtension(comment.file), header ++ markdown)]
	end;
}


function toSingleFile
Pair<String String> ::= comments::[CommentItem] header::String
{
  local commentMarkdown::String = toSingleMarkdown(comments);
  return pair("index.md", header ++ commentMarkdown);
}

function toSingleMarkdown
String ::= comments::[CommentItem]
{
  return case comments of
	 | c :: rest -> toMarkdown(c) ++ toSingleMarkdown(rest)
	 | [] -> ""
	 end;
}

function toMarkdown
String ::= c::CommentItem
{
  return (let signature :: String = 
			if 0 == length(c.signature)
			then ""
			else "\n ######`" ++ c.signature ++ "`"
		  in
			"\n\n#### _" ++ c.modifiers
			++ "_ `" ++ c.dclName
			++ "`" ++ signature
			++ "\n> " ++ c.body
			++ "\nIn file: " ++ c.file
		  end);
}

function toMarkdownExtension
String ::= filename::String
{
  return replace(".sv", ".md", filename);
}
