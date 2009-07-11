grammar silver:translation:java:command;
import silver:util:command;

synthesized attribute noJavaGeneration :: Boolean;
attribute noJavaGeneration occurs on PieceList, Command;

aspect production cRootAll
top::Command ::= c1::PieceList
{
  flagLookups <- [flagLookup("--xj", false)];

  uses <- ["\t--xj: do not generate java translation\n"];

  top.noJavaGeneration = !null(findFlag("--xj", top.flags));
}

