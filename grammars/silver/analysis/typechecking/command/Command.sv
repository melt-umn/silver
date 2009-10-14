grammar silver:analysis:typechecking:command;
import silver:util:command;

synthesized attribute noTypeChecking :: Boolean;
attribute noTypeChecking occurs on PieceList, Command;

aspect production cRootAll
top::Command ::= c1::PieceList
{
  flagLookups <- [flagLookup("--xt", false)];

  uses <- ["\t--xt: compile with no type checking\n"];

  top.noTypeChecking = !null(findFlag("--xt", top.flags));
}