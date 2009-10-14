grammar silver:translation:java:command;
import silver:util:command;

synthesized attribute noJavaGeneration :: Boolean occurs on Command;
synthesized attribute buildSingleJar :: Boolean occurs on Command;

aspect production cRootAll
top::Command ::= c1::PieceList
{
  flagLookups <- [flagLookup("--xj", false),
                  flagLookup("--onejar", false)];

  uses <- ["\t--xj: do not generate java translation\n",
           "\t--onejar: include runtime libraries in the jar\n"];

  top.noJavaGeneration = !null(findFlag("--xj", top.flags));
  top.buildSingleJar = !null(findFlag("--onejar", top.flags));
}

