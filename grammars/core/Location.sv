--grammar core;

--annotation location :: Location;

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

