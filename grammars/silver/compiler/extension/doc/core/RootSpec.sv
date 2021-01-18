grammar silver:compiler:extension:doc:core;

import silver:compiler:driver:util;

attribute genFiles occurs on RootSpec;

aspect production interfaceRootSpec
top::RootSpec ::= _ _ _
{
  top.genFiles := [];
}

aspect production errorRootSpec
top::RootSpec ::= _ _ _ _ _
{
  top.genFiles := [];
}

aspect production grammarRootSpec
top::RootSpec ::= g::Grammar  _ _ _ _
{
  top.genFiles := if g.upDocConfig.noDocs 
                  then []
                  else 
                  -- if "true" == g.docsSplit
                  -- then toSplitFiles(g.docs, [])
                  -- else [toSingleFile(g.docs)];
                  map((\x::CommentItem -> pair(toString(genInt()) ++ ".md", x.body)), g.docs);

  g.docEnv = add(g.docDcls, empty(compareString));
}

-- function toSplitFiles
-- [Pair<String String>] ::= comments::[CommentItem] sortedComments::[Pair<String String>]
-- {
--   return 
--     case comments of
--     | c :: rest -> toSplitFiles(rest, placeComment(c, sortedComments, header), header)
--     | [] -> pair("index.md", makeIndexFile(sortedComments, header)) :: sortedComments
--     end;
-- }

-- function placeComment
-- [Pair<String String>] ::= comment::CommentItem sortedComments::[Pair<String String>] header::String
-- {
--   local markdown::String = toMarkdown(comment);
--   return 
--     case sortedComments of
--     | pair(filename, contents) :: rest -> 
--         if filename == toMarkdownExtension(comment.file)
--         then pair(filename, contents ++ markdown) :: rest
--         else pair(filename, contents) :: placeComment(comment, rest, header)
--     | [] -> [pair(toMarkdownExtension(comment.file), header ++ markdown)]
--     end;
-- }

-- function makeIndexFile
-- String ::= sortedComments::[Pair<String String>] header::String
-- {
--   return 
--     case sortedComments of
--     | pair(f, _) :: rest -> makeIndexFile(rest, header) ++ "\n" ++ makeLink(f) ++ "\n"
--     | [] -> header
--     end;
-- }

-- function makeLink
-- String ::= mdFileName::String
-- { 
--   local txt::String = substitute( ".md", "", mdFileName );
--   local lnk::String = substitute( ".md", ".html", mdFileName );
--   return "[" ++ txt ++ "](" ++ lnk ++ ")" ;
-- }


-- function toSingleFile
-- Pair<String String> ::= comments::[CommentItem] header::String
-- {
--   local commentMarkdown::String = toSingleMarkdown(comments);
--   return pair("index.md", header ++ commentMarkdown);
-- }

-- function toSingleMarkdown
-- String ::= comments::[CommentItem]
-- {
--   return case comments of
-- 	 | c :: rest -> toMarkdown(c) ++ toSingleMarkdown(rest)
-- 	 | [] -> ""
-- 	 end;
-- }

-- function toMarkdown
-- String ::= c::CommentItem
-- {
--   return 
--     case c of
--     | dclCommentItem(mod, name, sig, file, body)->
--         let signature :: String = 
--           if 0 == length(sig)
--           then ""
--           else "\n###### `" ++ sig ++ "`"
--         in
--           "\n\n#### _" ++ mod ++
--           "_ `" ++ name ++
--           "`" ++ signature ++
--           "\n> " ++ body.body ++
--           "\nIn file: " ++ file
--         end
--     | bodilessDclCommentItem(mod, name, sig, file) ->
--         let signature :: String = 
--           if 0 == length(sig)
--           then ""
--           else "\n###### `" ++ sig ++ "`"
--         in
--           "\n\n#### _" ++ mod ++
--           "_ `" ++ name ++
--           "`" ++ signature ++
--           "\nIn file: " ++ file
--         end
--     end;
-- }

-- function toMarkdownExtension
-- String ::= filename::String
-- {
--   return substitute(".sv", ".md", filename);
-- }

