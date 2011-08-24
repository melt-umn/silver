
import silver:util:cmdargs;
import silver:testing ;
import lib:extcore ;
import stdlib;

synthesized attribute isVerbose :: Boolean occurs on CmdArgs;
synthesized attribute isSilly :: Boolean occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= _
{
  top.isVerbose = false;
  top.isSilly = true;
}

abstract production verboseFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.isVerbose = true;
  forwards to rest;
}
abstract production sillyFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.isSilly = true;
  forwards to rest;
}
abstract production noSillyFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.isSilly = false;
  forwards to rest;
}


global flags1 :: [Pair<String Flag>] =
  [pair("-verbose", flag(verboseFlag)),
   pair("-silly", flag(sillyFlag)),
   pair("-nosilly", flag(noSillyFlag))];

-- Don't parse anything unrecognized.
equalityTest( interpretCmdArgs(flags1, ["a", "b", "c"]).cmdRemaining, 
              ["a", "b", "c"], 
              [String], core_tests );
-- Stop after we stop recognizing things.
equalityTest( interpretCmdArgs(flags1, ["-verbose", "a", "b", "c"]).cmdRemaining, 
              ["a", "b", "c"], 
              [String], core_tests );
-- Do not continue after we've found anything unexpected.
equalityTest( interpretCmdArgs(flags1, ["a", "-verbose", "b", "c"]).cmdRemaining, 
              ["a", "-verbose", "b", "c"], 
              [String], core_tests );

-- Check that verbose is still the default
equalityTest( interpretCmdArgs(flags1, ["a", "-verbose", "b", "c"]).isVerbose, 
              false, 
              Boolean, core_tests );
-- Check that verbose is no longer the default
equalityTest( interpretCmdArgs(flags1, ["-verbose", "a", "b", "c"]).isVerbose, 
              true, 
              Boolean, core_tests );

-- Silly is the default.
equalityTest( interpretCmdArgs(flags1, []).isSilly, 
              true, 
              Boolean, core_tests );
-- Silly is on.
equalityTest( interpretCmdArgs(flags1, ["-silly"]).isSilly, 
              true, 
              Boolean, core_tests );
-- Silly is off.
equalityTest( interpretCmdArgs(flags1, ["-nosilly"]).isSilly, 
              false, 
              Boolean, core_tests );
-- Check that the first flag takes priority.
equalityTest( interpretCmdArgs(flags1, ["-nosilly", "-silly"]).isSilly, 
              false, 
              Boolean, core_tests );
-- One more...
equalityTest( interpretCmdArgs(flags1, ["-silly", "-nosilly", "-silly"]).isSilly, 
              true, 
              Boolean, core_tests );


-- an "option" is a flag with one parameter.
global flags2 :: [Pair<String Flag>] =
  [pair("-I", option(includeFlag))] ++ flags1;

synthesized attribute includePaths :: [String] occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= _
{
  top.includePaths = [];
}
abstract production includeFlag
top::CmdArgs ::= p::String rest::CmdArgs
{
  top.includePaths = p :: forward.includePaths;
  forwards to rest;
}

-- Stil don't parse anything unrecognized.
equalityTest( interpretCmdArgs(flags2, ["a", "b", "c"]).cmdRemaining, 
              ["a", "b", "c"], 
              [String], core_tests );
-- parse everything recognized
equalityTest( interpretCmdArgs(flags2, ["-verbose", "-nosilly", "-I", "document and settings", "-silly", "-I", "elsewhere"]).cmdRemaining, 
              [], 
              [String], core_tests );
-- from the way we defined includeFlag, we should get all of them (and in order!)
equalityTest( interpretCmdArgs(flags2, ["-verbose", "-nosilly", "-I", "document and settings", "-silly", "-I", "elsewhere"]).includePaths, 
              ["document and settings", "elsewhere"],
              [String], core_tests );
-- All the other flags should still be working
equalityTest( interpretCmdArgs(flags2, ["-verbose", "-nosilly", "-I", "document and settings", "-silly", "-I", "elsewhere"]).isVerbose, 
              true,
              Boolean, core_tests );
equalityTest( interpretCmdArgs(flags2, ["-verbose", "-nosilly", "-I", "document and settings", "-silly", "-I", "elsewhere"]).isSilly, 
              false,
              Boolean, core_tests );

-- Test the error message generator
-- Should be fine
equalityTest( interpretCmdArgs(flags2, ["-verbose", "-nosilly", "-I", "document and settings", "-silly", "-I", "elsewhere"]).cmdError.isJust, 
              false,
              Boolean, core_tests );
-- Should have an error
equalityTest( interpretCmdArgs(flags2, ["-verbose", "-nosilly", "-I", "document and settings", "-silly", "-I"]).cmdError.isJust, 
              true,
              Boolean, core_tests );
-- Should have this specific error
equalityTest( interpretCmdArgs(flags2, ["-verbose", "-nosilly", "-I", "document and settings", "-silly", "-I"]).cmdError.fromJust, 
              "-I is missing its parameter.",
              String, core_tests );
-- Should error on unrecognized flag
equalityTest( interpretCmdArgs(flags2, ["-verbose", "-notsilly", "-I", "document and settings", "-silly", "-I", "elsewhere"]).cmdError.fromJust, 
              "Unrecognized flag: -notsilly",
              String, core_tests );
-- Should NOT raise an error!
equalityTest( interpretCmdArgs(flags2, ["not a flag", "-notsilly"]).cmdError.isJust, 
              false,
              Boolean, core_tests );


