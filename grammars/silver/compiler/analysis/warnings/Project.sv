grammar silver:compiler:analysis:warnings;

imports silver:util:cmdargs;
imports silver:compiler:driver only parseArgs;

synthesized attribute warnAll :: Boolean occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= l::[String]
{
  top.warnAll = false;
}
abstract production warnAllFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.warnAll = true;
  forwards to rest;
}

aspect function parseArgs
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [pair("--warn-all", flag(warnAllFlag))];
  flagdescs <- ["\t--warn-all  : enable all warnings"];
}

