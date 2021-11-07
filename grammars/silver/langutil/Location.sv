grammar silver:langutil;

import silver:langutil:pp;

attribute unparse, pp occurs on Location;

aspect production loc
top::Location ::= filename::String  line::Integer  column::Integer
                  endLine::Integer  endColumn::Integer
                  index::Integer  endIndex::Integer
{
  top.unparse = filename ++ ":" ++ toString(line) ++ ":" ++ toString(column);
  top.pp = text(top.unparse);
}

aspect production txtLoc
top::Location ::= txt::String
{
  top.unparse = txt;
  top.pp = text(txt);
}
