grammar silver:compiler:driver;

{--
 - Parses a list of files.
 - @param svParser  The parser to use to contruct Roots
 - @param gpath  The path where we found the grammar. Ends in a slash/
 - @param files  The list of .sv files to read.
 - @param ioin  The io token
 - @return An ioval wrapping the list of parse results and parse errors.
 -}
function compileFiles
IOVal<Pair<[Root] [ParseError]>> ::= svParser::SVParser  gpath::String  files::[String]  ioin::IO
{
  local file :: String = head(files);
  
  local rawText :: IOVal<String> =
    readFile(gpath ++ file, ioin);
  local text :: IOVal<String> =
    ioval(rawText.io, transformFile(file, rawText.iovalue));

  -- This is where a .sv file actually gets parsed:
  local r :: ParseResult<Root> = svParser(text.iovalue, file);

  -- Continue parsing the rest of the files.
  local recurse :: IOVal<Pair<[Root] [ParseError]>> = compileFiles(svParser, gpath, tail(files), text.io);

  return if null(files) then ioval(ioin, pair([], []))
         -- Using [] in this case because there seems to be no end to io token demanding issues:
         else case r of
         | parseSucceeded(rtree, _) ->
             ioval(recurse.io, pair(rtree :: recurse.iovalue.fst, recurse.iovalue.snd))
         | parseFailed(errval, _) -> 
             ioval(recurse.io, pair(recurse.iovalue.fst, errval :: recurse.iovalue.snd))
         end;
}
