grammar silver:compiler:driver;

{--
 - Parses a list of files.
 - @param svParser  The parser to use to contruct Roots
 - @param gpath  The path where we found the grammar. Ends in a slash/
 - @param files  The list of .sv files to read.
 - @return An IO action constructing the list of parse results and parse errors.
 -}
function compileFiles
IO<([Root], [ParseError])> ::= svParser::SVParser  gpath::String  files::[String]
{
  return
    case files of
    | file :: rest -> do {
        rawText :: String <- readFile(gpath ++ file);
        let text :: String = transformFile(file, rawText);

        -- This is where a .sv file actually gets parsed:
        let r :: ParseResult<Root> = svParser(text, file);

        -- Continue parsing the rest of the files.
        recurse :: ([Root], [ParseError]) <- compileFiles(svParser, gpath, rest);
        return
          case r of
          | parseSucceeded(rtree, _) -> (rtree :: recurse.1, recurse.2)
          | parseFailed(errval, _) -> (recurse.1, errval :: recurse.2)
          end;
      }
    | [] -> pure(([], []))
    end;
}
