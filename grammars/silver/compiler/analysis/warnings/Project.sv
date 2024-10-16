grammar silver:compiler:analysis:warnings;

imports silver:util:cmdargs;
imports silver:compiler:driver only parseArgs;

abstract production warnAllFlag
top::CmdArgs ::= rest::CmdArgs
{
  -- This prod should be aspected to turn on all relevant warning flags
  forwards to @rest;
}

aspect function parseArgs
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [flagSpec(name="--warn-all", paramString=nothing(),
              help="enable all warnings",
              flagParser=flag(warnAllFlag))];
}

