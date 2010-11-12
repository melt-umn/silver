grammar silver:analysis:warnings;

exports silver:analysis:warnings:defs;

import silver:util:command;

-- TODO : WARNING AWFUL HACK AHEAD
-- I sneak the arguments into the environment, so we can find command line state deep in the code.
-- This would be better done with some configuration information getting copied down...

-- yeah, another one.  I said this was ugly, alright?
parser warnCmdParse :: Command {
  silver:util:command;
}


global cmdLineArgs :: Decorated Command = decorate warnCmdParse(envVar("SILVER_ARGS",unsafeIO()).iovalue,"<args-wrn>").parseTree with {};




synthesized attribute warnAll :: Boolean occurs on Command;

aspect production cRootAll
top::Command ::= c1::PieceList
{
  flagLookups <- [flagLookup("--warn-all",false)];
  uses <- ["\t--warn-all  Enable all warning messages"];

  top.warnAll = !null(findFlag("--warn-all", top.flags));
}

