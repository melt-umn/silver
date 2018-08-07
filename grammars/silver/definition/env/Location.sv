grammar silver:definition:env;

synthesized attribute unparse :: String; -- TODO remove?

attribute unparse occurs on Location;

aspect production loc
top::Location ::= filename::String  line::Integer  column::Integer
                  endLine::Integer  endColumn::Integer
                  index::Integer  endIndex::Integer
{
  top.unparse = "'" ++ filename ++ "', " ++
    implode(", ", [toString(line), toString(column), toString(endLine), toString(endColumn), toString(index), toString(endIndex)]);
}

function bogusLocation
Location ::=
{
  return loc("??", -1, -1, -1, -1, -1, -1);
}

