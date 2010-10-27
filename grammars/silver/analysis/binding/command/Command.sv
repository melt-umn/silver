grammar silver:analysis:binding:command;
import silver:util:command;

synthesized attribute noBindingChecking :: Boolean;
attribute noBindingChecking occurs on Command;

aspect production cRootAll
top::Command ::= c1::PieceList
{
  flagLookups <- [flagLookup("--xb", false)];
  uses <- ["\t--xb: exclude error checking\n"];

  top.noBindingChecking = !null(findFlag("--xb", top.flags));
}
