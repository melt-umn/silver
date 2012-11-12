grammar silver:driver;

synthesized attribute searchPath :: [String];
synthesized attribute outName :: String;
synthesized attribute displayVersion :: Boolean;
synthesized attribute doClean :: Boolean;
synthesized attribute genLocation :: String;
synthesized attribute buildGrammar :: String;

attribute genLocation, doClean, displayVersion, searchPath, outName, buildGrammar occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= l::[String]
{
  top.doClean = false;
  top.displayVersion = false;
  top.outName = "";
  top.searchPath = ["./"];
  top.genLocation = "";
  top.buildGrammar= head(l);
}
abstract production versionFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.displayVersion = true;
  forwards to rest;
}
abstract production cleanFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.doClean = true;
  forwards to rest;
}
abstract production outFlag
top::CmdArgs ::= s::String rest::CmdArgs
{
  top.outName = s;
  forwards to rest;
}
abstract production includeFlag
top::CmdArgs ::= s::String rest::CmdArgs
{
  top.searchPath = s :: forward.searchPath;
  forwards to rest;
}
abstract production genFlag
top::CmdArgs ::= s::String rest::CmdArgs
{
  top.genLocation = s;
  forwards to rest;
}

