grammar silver:compiler:analysis:warnings:flow;

synthesized attribute errorMwda :: Boolean occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= l::[String]
{
  top.errorMwda = false;
}
abstract production mwdaFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.errorMwda = true;
  top.warnMissingInh = true;
  top.warnMissingSyn = true;
  top.warnEqdef = true;
  top.warnOrphaned = true;
  top.warnFwd = true;
  top.warnSharing = true;
  forwards to @rest;
}

-- Also add these under --warn-all
aspect production warnAllFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.warnMissingInh = true;
  top.warnMissingSyn = true;
  top.warnEqdef = true;
  top.warnOrphaned = true;
  top.warnFwd = true;
  top.warnSharing = true;
}

aspect function parseArgs
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [
    flagSpec(name="--mwda", paramString=nothing(),
      help="report MWDA violations as errors",
      flagParser=flag(mwdaFlag))];
}

fun mwdaWrn Message ::= config::Decorated CmdArgs l::Location m::String =
  if config.errorMwda
  then err(l, m)
  else wrn(l, m);

fun mwdaWrnFromOrigin
attribute config occurs on a =>
Message ::= a::Decorated a with {config}  m::String =
  mwdaWrn(a.config, getParsedOriginLocationOrFallback(a), m);

fun mwdaWrnAmbientOrigin Message ::= config::Decorated CmdArgs  m::String =
  mwdaWrn(config, getParsedOriginLocationOrFallback(ambientOrigin()), m);
