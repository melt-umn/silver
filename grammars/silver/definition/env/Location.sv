grammar silver:definition:env;

attribute unparse occurs on Location;

aspect production loc
top::Location ::= filename::String  line::Integer  column::Integer
                  endLine::Integer  endColumn::Integer
                  index::Integer  endIndex::Integer
{
  top.unparse = "'" ++ filename ++ "', " ++ toString(line) ++ ", " ++ toString(column);
}

function bogusLocation
Location ::=
{
  return loc("??", -1, -1, -1, -1, -1, -1);
}
