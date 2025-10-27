grammar silver:core;

annotation location :: Location;

@{--
 - Data structure storing location information on tree nodes from a parse.
 -}
data nonterminal Location with filename, line, column, endLine, endColumn, index, endIndex;

synthesized attribute filename :: String;
synthesized attribute line :: Integer;
synthesized attribute column :: Integer;
synthesized attribute endLine :: Integer;
synthesized attribute endColumn :: Integer;
synthesized attribute index :: Integer;
synthesized attribute endIndex :: Integer;

instance Eq Location {
  eq = \ l1::Location l2::Location ->
    -- TODO: We could probably just compare based on filename and index
    -- For the moment, though, use line & column instead.
    l1.filename == l2.filename &&
    l1.line == l2.line &&
    l1.column == l2.column;
}

instance Ord Location {
  lte = \ l1::Location l2::Location ->
    -- TODO: We could probably just compare based on filename and index
    -- For the moment, though, use line & column instead.
    l1.filename < l2.filename || (l1.filename == l2.filename &&
    (l1.line < l2.line || (l1.line == l2.line &&
    (l1.column < l2.column))));
}

@{--
 - The main constructor for location information.
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
 - `(_, 1, 0, 1, 2, 0, 2)`
 -}
abstract production loc
top::Location ::= filename::String  line::Integer  column::Integer
                  endLine::Integer  endColumn::Integer
                  index::Integer  endIndex::Integer
{
  top.filename = filename;
  top.line = line;
  top.column = column;
  top.endLine = endLine;
  top.endColumn = endColumn;
  top.index = index;
  top.endIndex = endIndex;
}

@{--
 - A secondary constructor for location information, for locations not from source code
 -
 - @param text The text to return as unparse as defined in langutil
 -}
abstract production txtLoc
top::Location ::= text::String
{
  top.filename = "N/A";
  top.line = -1;
  top.column = -1;
  top.endLine = -1;
  top.endColumn = -1;
  top.index = -1;
  top.endIndex = -1;
}


@{--
 - Offset one location "inside" another. For use when e.g. parsing a doc comment grammar out of a single
 -  terminal in the host language. use linesOffset, firstLineColsOffset, allLinesColsOffset, indexOffset if some
 -  part of the terminal is munged before being passed to the child parser (e.g. the {- and -} are removed from a comment.)
 -}
fun childParserLoc
Location ::= parent::Location child::Location linesOffset::Integer firstLineColsOffset::Integer allLinesColsOffset::Integer indexOffset::Integer =
  loc(
    parent.filename,
    parent.line - 1 + linesOffset + child.line,
    allLinesColsOffset + (if child.line == 1 then parent.column + firstLineColsOffset + child.column else child.column),
    parent.endLine - 1 + linesOffset + child.endLine,
    allLinesColsOffset + (if child.endLine == 1 then parent.endColumn + firstLineColsOffset + child.endColumn else child.endColumn),
    parent.index + indexOffset + child.index,
    parent.endIndex + indexOffset + child.endIndex
  );


@{--
 - A helper constructor for location information, for built-in locations
 -
 - @param module The name of the extension/modification/module defining the location
 -}
fun builtinLoc Location ::= module::String = txtLoc("Built in from " ++ module);

@{--
 - A helper constructor for location information, for invalid or undefined bogus locations
 -}
fun bogusLoc Location ::= = txtLoc("Invalid or undefined bogus location");
