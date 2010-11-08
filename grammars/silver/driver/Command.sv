grammar silver:driver;
import silver:util:command;
import silver:util;

synthesized attribute searchPath :: String;
synthesized attribute outName :: String;
synthesized attribute displayVersion :: Boolean;
synthesized attribute doClean :: Boolean;
synthesized attribute genLocation :: String;
attribute genLocation occurs on Command;
attribute doClean occurs on Command;
attribute displayVersion occurs on Command;
attribute searchPath occurs on Command;
attribute outName occurs on Command;

aspect production cRootAll
top::Command ::= c1::PieceList
{
  flagLookups <- [flagLookup("-I", true),
                  flagLookup("-o", true),
                  flagLookup("-v", false),
                  flagLookup("--clean", false),
                  flagLookup("-G", true)
                  ];
  uses <- ["\t-I <path>  : path to grammars (GRAMMAR_PATH)\n",
           "\t-o <file>  : name of binary file\n",
           "\t-v  : display version\n",
           "\t--clean  : overwrite interface files\n",
           "\t-G <path>  : Location to store generate files (SILVER_GEN)\n"
           ];

  local attribute includepaths :: [Flag];
  includepaths = findFlag("-I", top.flags);

  local attribute outputpath :: [Flag];
  outputpath = findFlag("-o", top.flags);

  local attribute generatedpath :: [Flag];
  generatedpath = findFlag("-G", top.flags);

  top.doClean = !null(findFlag("--clean", top.flags));
  top.displayVersion = !null(findFlag("-v", top.flags));
  top.outName = if null(outputpath) then "" else head(outputpath).chunk;
  top.searchPath = if null(includepaths) then "./" else head(includepaths).chunk;
  top.genLocation = if null(generatedpath) then "" else head(generatedpath).chunk;
}

