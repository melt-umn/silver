grammar silver:extension:doc:command;
export silver:extension:doc:command;

import silver:util:command;
import silver:driver;

synthesized attribute generateDoc :: Boolean;
attribute generateDoc occurs on PieceList, Command;

aspect production cRootAll
top::Command ::= c1::PieceList
{
  flagLookups <- [flagLookup("--doc", false)];

  uses <- ["\t--doc: generate silver doc\n"];

  top.generateDoc = !null(findFlag("--doc", top.flags));
}

