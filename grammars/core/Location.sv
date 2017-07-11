grammar core;

annotation location :: Location;

{--
 - Data structure storing location information on tree nodes from a parse.
 -}
nonterminal Location with filename, line, column, endLine, endColumn, index, endIndex;

synthesized attribute filename :: String;
synthesized attribute line :: Integer;
synthesized attribute column :: Integer;
synthesized attribute endLine :: Integer;
synthesized attribute endColumn :: Integer;
synthesized attribute index :: Integer;
synthesized attribute endIndex :: Integer;

{--
 - The sole constructor for location information.
 -
 - filename, line and column can be mutated by action blocks during parsing,
 - but character index cannot.
 -
 - @param filename  The "virtual filename". Initially whatever the parser is given.
 - @param line  (Beginning) line number, inclusive. Lines are numbered starting with 1.
 - @param column (Beginning) column number, inclusive. Columns are numbered starting with 0. (For now.)
 - @param endLine (Ending) line number, inclusive.
 - @param endColumn (Ending) column number, exclusive.
 - @param index (Beginning) character index, inclusive.
 - @param endIndex (Ending) character index, exclusive.
 -
 - e.g. "Hi" as an entire file contents would have its entire location as:
 - (_, 1, 0, 1, 2, 0, 2)
 -}
abstract production loc
top::Location ::= filename::String  line::Integer  column::Integer
                  endLine::Integer  endColumn::Integer
                  index::Integer  endIndex::Integer
{
  --top.unparse = filename ++ ":" ++ toString(line) ++ ":" ++ toString(column);
  top.filename = filename;
  top.line = line;
  top.column = column;
  top.endLine = endLine;
  top.endColumn = endColumn;
  top.index = index;
  top.endIndex = endIndex;
}

{--
 - Less than or equal predicate, for use with sortBy, if desired.
 -}
function locationLte
Boolean ::= l1::Location l2::Location
{
  -- TODO: We could probaly just compare based on filename and index
  -- For the moment, though, use line & column instead.
  return l1.filename < l2.filename || (l1.filename == l2.filename &&
    (l1.line < l2.line || (l1.line == l2.line &&
    (l1.column < l2.column))));
}

