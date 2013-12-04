grammar silver:driver;

{--
 - Turns a list of files that compose a grammar into a RootSpec, having compiled them.
 - @param svParser  The parser to use to contruct Roots
 - @param gpath  The path where we found the grammar. Ends in a slash/
 - @param files  The list of .sv files to read.
 - @param ioin  The io token
 - @return An ioval wrapping a GrammarParts structure containing the whole grammar.
 -}
function compileFiles
IOVal<Grammar> ::= svParser::SVParser  gpath::String  files::[String]  ioin::IO
{
  local file :: String = head(files);
  
  -- Print the path we're reading, and read the file.
  local text :: IOVal<String> =
    readFile(gpath ++ file, print("\t[" ++ gpath ++ file ++ "]\n", ioin));

  -- This is where a .sv file actually gets parsed:
  local r :: ParseResult<Root> = svParser(text.iovalue, file);

  -- Continue parsing the rest of the files.
  local recurse :: IOVal<Grammar> = compileFiles(svParser, gpath, tail(files), text.io);

  return if null(files) then ioval(ioin, nilGrammar())
         -- Using nilGrammar in this case because there seems to be no end to io token demanding issues:
         else if !r.parseSuccess then ioval(exit(-1, print(r.parseErrors ++ "\n\n", text.io)), nilGrammar())
         else ioval(recurse.io, consGrammar(grammarPart(r.parseTree, file), recurse.iovalue));
}

