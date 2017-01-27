grammar silver:langutil;

attribute unparse occurs on Location;

aspect production loc
top::Location ::= filename::String  line::Integer  column::Integer
                  endLine::Integer  endColumn::Integer
                  index::Integer  endIndex::Integer
{
  top.unparse = filename ++ ":" ++ toString(line) ++ ":" ++ toString(column);
}

aspect production txtLoc
top::Location ::= text::String
{
  top.unparse = text;
}
